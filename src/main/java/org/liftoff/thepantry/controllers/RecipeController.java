package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.*;
import org.liftoff.thepantry.models.Ingredient;
import org.liftoff.thepantry.models.Recipe;
import org.liftoff.thepantry.models.RecipeIngredient;
import org.liftoff.thepantry.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("add")
    public String  displayAddRecipe(Model model) {
        model.addAttribute("title", "Add Recipe");
//        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute(new Recipe());
        return "recipes/add";
    }

    @PostMapping("add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe,
                                   Errors errors, Model model, @RequestParam String description, @RequestParam String instructions) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("tags", tagRepository.findAll());

            return "recipes/add";
        }

        newRecipe.setDescription(description);

        newRecipe.setInstructions(instructions);

//        List<Tag> tagObjs = (List<Tag>) tagRepository.findAllById(tags);
//        newRecipe.setTags(tagObjs);

        recipeRepository.save(newRecipe);

        int recipeId = newRecipe.getId();

        return "redirect:edit/" + recipeId;
    }





    @GetMapping("edit/{recipeId}")
    public String editRecipe(Model model, @PathVariable int recipeId) {
        Optional optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optRecipe.get();
        model.addAttribute("recipe", recipe);
        model.addAttribute("title", "Edit Recipe");
        //model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("recipeIngredients", recipeIngredientRepository.findByRecipeId(recipeId));
        model.addAttribute("units", unitRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute(new RecipeIngredient());
        return "recipes/edit";
    }

    @PostMapping("edit/update-recipe")
    public String updateRecipe(@ModelAttribute Recipe recipe, Errors errors, Model model, @RequestParam int recipeId) {
        try {
            recipe.setId(recipeId);
            recipeRepository.save(recipe);
            return "redirect:" + recipeId;
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            return "redirect:" + recipeId;
        }
    }

    @PostMapping("edit/add-ingredient")
    public String addRecipeIngredient(@ModelAttribute @Valid RecipeIngredient newRecipeIngredient,
                                      Errors errors, Model model, @RequestParam String amount, @RequestParam int recipeId, @RequestParam int unitId, @RequestParam int ingredientId) {

        if (errors.hasErrors()) {
            return "redirect:" + recipeId;
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

    @PostMapping("edit/delete")
    public String deleteRecipeIngredient(@RequestParam int recipeId, @RequestParam int recipeIngredientId) {
        recipeIngredientRepository.deleteById(recipeIngredientId);
        return "redirect:" + recipeId + "#ingredients";
    }








    // Recipe Ingredients

//    @GetMapping("recipe-ingredients/{recipeId}")
//    public String displayRecipeIngredient(Model model, @PathVariable int recipeId) {
//
//        Optional optRecipe = recipeRepository.findById(recipeId);
//        Recipe recipe = (Recipe) optRecipe.get();
//        model.addAttribute("recipe", recipe);
//        model.addAttribute("recipeIngredients", recipeIngredientRepository.findByRecipeId(recipeId));
//        model.addAttribute("units", unitRepository.findAll());
//        model.addAttribute("ingredients", ingredientRepository.findAll());
//        model.addAttribute(new RecipeIngredient());
//        return "recipes/recipe-ingredients";
//    }
//
//    @PostMapping("recipe-ingredients/add")
//    public String addRecipeIngredient(@ModelAttribute @Valid RecipeIngredient newRecipeIngredient,
//                                      Errors errors, Model model, @RequestParam String amount, @RequestParam int recipeId, @RequestParam int unitId, @RequestParam int ingredientId) {
//
//        if (errors.hasErrors()) {
//            return "redirect:" + recipeId;
//        }
//
//        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
//        if(optRecipe.isPresent()) {
//            Recipe recipe = optRecipe.get();
//            newRecipeIngredient.setRecipe(recipe);
//        }
//
//        Optional<Unit> optUnit = unitRepository.findById(unitId);
//        if(optUnit.isPresent()) {
//            Unit unit = optUnit.get();
//            newRecipeIngredient.setUnit(unit);
//        }
//
//        Optional<Ingredient> optIngredient = ingredientRepository.findById(ingredientId);
//        if(optIngredient.isPresent()) {
//            Ingredient ingredient = optIngredient.get();
//            newRecipeIngredient.setIngredient(ingredient);
//        }
//
//        newRecipeIngredient.setAmount(amount);
//
//        recipeIngredientRepository.save(newRecipeIngredient);
//
//        return "redirect:" + recipeId;
//    }
//
//    @PostMapping("recipe-ingredients/delete")
//    public String deleteRecipeIngredient(@RequestParam int recipeId, @RequestParam int recipeIngredientId) {
//        recipeIngredientRepository.deleteById(recipeIngredientId);
//        return "redirect:" + recipeId;
//    }

}