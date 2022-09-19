package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeIngredientRepository;
import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    // display recipe

    @GetMapping("recipe/{recipeId}")
    public String displayRecipe(Model model, @PathVariable int recipeId) {
        Optional optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optRecipe.get();
        model.addAttribute("banner", "recipe");
        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeIngredients", recipeIngredientRepository.findByRecipeId(recipeId));
        return "recipe";
    }

    // display/search recipes

    @GetMapping("recipes")
    public String displayRecipes(Model model) {
        model.addAttribute("banner", "recipes");
        model.addAttribute("title", "Browse Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }

    @PostMapping("recipes/results")
    public String displayRecipesBySearch(Model model, @RequestParam String searchTerm, RedirectAttributes ra) {
        ArrayList<Recipe> recipes;
        if (searchTerm.toLowerCase().isEmpty() || searchTerm.isBlank() || searchTerm.equals("")) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Please enter a search term.");
            return "redirect:/recipes";
        } else {
            recipes = findByValue(searchTerm, recipeRepository.findAll());
            if (recipes.isEmpty()) {
                ra.addFlashAttribute("class", "alert alert-danger");
                ra.addFlashAttribute("message", "No results for '" + searchTerm + "'");
                return "redirect:/recipes";
            }
            model.addAttribute("title", "Recipes matching '" + searchTerm +"'");
        }
        model.addAttribute("banner", "recipes");
        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    // find recipes by search term

    public static ArrayList<Recipe> findByValue(String aValue, Iterable<Recipe> allRecipes) {
        String value = aValue.toLowerCase();
        ArrayList<Recipe> results = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            if (recipe.getName().toLowerCase().contains(value)) {
                results.add(recipe);
            } else if (recipe.getDescription().toString().toLowerCase().contains(value)) {
                results.add(recipe);
            } else if (recipe.getInstructions().toString().toLowerCase().contains(value)) {
                results.add(recipe);
            }
        }

        return results;
    }

}
