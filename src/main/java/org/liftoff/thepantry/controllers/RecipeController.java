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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private static String UPLOAD_FOLDER = "/uploads";

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
    public String deleteRecipe(@RequestParam int recipeId, RedirectAttributes ra) {
        Optional optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optRecipe.get();

        // delete recipe ingredients
        List recipeIngredients = recipeIngredientRepository.findByRecipeId(recipeId);
        recipeIngredientRepository.deleteAll(recipeIngredients);

        // delete recipe
        recipeRepository.deleteById(recipeId);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Recipe '" + recipe.getName() + "' deleted successfully");
        return "redirect:/recipes";
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
    public String saveRecipe(@ModelAttribute Recipe recipe, @RequestParam int recipeId, Model model, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "redirect:" + recipeId + "#errors";
        }

        recipe.setId(recipeId);
        recipeRepository.save(recipe);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Recipe '" + recipe.getName() + "' updated successfully");
        return "redirect:" + recipeId;
    }

    // add/delete recipe ingredients

    @PostMapping("edit/add-ingredient")
    public String updateRecipe(@ModelAttribute Recipe recipe, @ModelAttribute RecipeIngredient newRecipeIngredient, @RequestParam String amount, @RequestParam int ingredientId, @RequestParam int recipeId, @RequestParam int unitId, Errors errors, Model model, RedirectAttributes ra) {
        // save recipe
        recipe.setId(recipeId);
        recipeRepository.save(recipe);

        // error checking
        if (ingredientId==0) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Ingredient is required.");
            return "redirect:" + recipeId + "#errors";
        }
        if (!recipeIngredientRepository.findByRecipeIdAndIngredientId(recipeId, ingredientId).isEmpty()) {
            Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredientId);
            Ingredient ingredient = optIngredient.get();
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Recipe ingredient '" + optIngredient.get().getName() + "' already exists.");
            return "redirect:" + recipeId + "#errors";
        }

        // set recipe ingredient
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        if(optRecipe.isPresent()) {
            Recipe recipeSelected = optRecipe.get();
            newRecipeIngredient.setRecipe(recipeSelected);
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

        // save ingredient
        recipeIngredientRepository.save(newRecipeIngredient);
        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Recipe ingredient '" + optIngredient.get().getName() + "' added successfully.");
        return "redirect:" + recipeId + "#ingredients";
    }

    @PostMapping("edit/delete-ingredient")
    public String deleteRecipeIngredient(@RequestParam int recipeId, @RequestParam int recipeIngredientId, RedirectAttributes ra) {
        Optional<RecipeIngredient> optRecipeIngredient = recipeIngredientRepository.findById(recipeIngredientId);
        RecipeIngredient recipeIngredient = optRecipeIngredient.get();

        recipeIngredientRepository.deleteById(recipeIngredientId);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Recipe ingredient '" + recipeIngredient.getIngredient().getName() + "' deleted successfully");
        return "redirect:" + recipeId + "#ingredients";
    }

    // add new ingredient to list of ingredients

    @PostMapping("edit/new-ingredient")
    public String newIngredient(@ModelAttribute @Valid Ingredient newIngredient, Errors errors, Model model, @RequestParam int recipeId, RedirectAttributes ra) {
        // error checking
        if (errors.hasErrors()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Name is required for new ingredient.");
            return "redirect:" + recipeId + "#errors";
        }
        if (!ingredientRepository.findByName(newIngredient.getName()).isEmpty()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Ingredient '" + newIngredient.getName() + "' already exists.");
            return "redirect:" + recipeId + "#errors";
        }

        // save new ingredient
        ingredientRepository.save(newIngredient);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Ingredient '" + newIngredient.getName() + "' added successfully.");
        return "redirect:" + recipeId + "#ingredients";
    }

    // upload image file


}