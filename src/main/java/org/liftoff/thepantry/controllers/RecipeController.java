package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.data.RecipeIngredientRepository;
import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.data.UnitRepository;
import org.liftoff.thepantry.models.Ingredient;
import org.liftoff.thepantry.models.Recipe;
import org.liftoff.thepantry.models.RecipeIngredient;
import org.liftoff.thepantry.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private UnitRepository unitRepository;

    // display/add/delete/edit/save recipe

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Recipe());
        return "recipes/index";
    }

    @PostMapping("add-recipe")
    public String addRecipe(Model model, @ModelAttribute @Valid Recipe newRecipe, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("recipes", recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            return "recipes/index";
        }
        recipeRepository.save(newRecipe);
        int recipeId = newRecipe.getId();
        return "redirect:edit/" + recipeId;
    }

    @PostMapping("delete-recipe")
    public String deleteRecipe(@RequestParam int recipeId) {
        List recipeIngredients = recipeIngredientRepository.findByRecipeId(recipeId);
        recipeIngredientRepository.deleteAll(recipeIngredients);
        recipeRepository.deleteById(recipeId);
        return "redirect:";
    }

    @GetMapping("edit/{recipeId}")
    public String editRecipe(Model model, @PathVariable int recipeId) {
        Optional optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optRecipe.get();
        model.addAttribute("recipe", recipe);
        model.addAttribute("units", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute("recipeIngredients", recipeIngredientRepository.findByRecipeId(recipeId));
        model.addAttribute(new RecipeIngredient());
        model.addAttribute(new Ingredient());
        return "recipes/edit";
    }

    @PostMapping("edit/save-recipe")
    public String saveRecipe(@ModelAttribute Recipe recipe, @RequestParam int recipeId, Errors errors) {
        if (errors.hasErrors()) {
            return "recipes/edit/" + recipeId;
        }
        recipe.setId(recipeId);
        recipeRepository.save(recipe);
        return "redirect:" + recipeId;
    }



    @PostMapping("edit/update-recipe")
    public String updateRecipe(@ModelAttribute Recipe recipe, Model model, @RequestParam int recipeId, Errors errors) {
        if (errors.hasErrors()) {
            return "recipes/edit/" + recipeId;
        }
        recipe.setId(recipeId);
        recipeRepository.save(recipe);
        return "redirect:" + recipeId;
    }




    // add/delete recipe ingredients

    @PostMapping("edit/add-ingredient")
    public String addRecipeIngredient(@ModelAttribute @Valid RecipeIngredient newRecipeIngredient,
                                      Errors errors, Model model, @RequestParam String amount, @RequestParam int recipeId, @RequestParam int unitId, @RequestParam int ingredientId) {
        if (errors.hasErrors()) {
            return "redirect:" + recipeId + "#ingredients";
        }
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if(optRecipe.isPresent()) {
            Recipe recipe = optRecipe.get();
            newRecipeIngredient.setRecipe(recipe);
        }
        Optional<Unit> optUnit = unitRepository.findById(unitId);
        if(optUnit.isPresent()) {
            Unit unit = optUnit.get();
            newRecipeIngredient.setUnit(unit);
        }
        Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredientId);
        if(optIngredient.isPresent()) {
            Ingredient ingredient = optIngredient.get();
            newRecipeIngredient.setIngredient(ingredient);
        }
        newRecipeIngredient.setAmount(amount);
        recipeIngredientRepository.save(newRecipeIngredient);
        return "redirect:" + recipeId + "#ingredients";
    }

    @PostMapping("edit/delete-ingredient")
    public String deleteRecipeIngredient(@RequestParam int recipeId, @RequestParam int recipeIngredientId) {
        recipeIngredientRepository.deleteById(recipeIngredientId);
        return "redirect:" + recipeId + "#ingredients";
    }

    // add new ingredient to list of ingredients

    @PostMapping("edit/new-ingredient")
    public String newIngredient(@ModelAttribute @Valid Ingredient newIngredient, Errors errors, Model model, @RequestParam int recipeId) {
        if (errors.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            model.addAttribute("errors", "Name is required.");
            return "ingredients/index";
        }
        ingredientRepository.save(newIngredient);
        return "redirect:" + recipeId + "#ingredients";
    }

}