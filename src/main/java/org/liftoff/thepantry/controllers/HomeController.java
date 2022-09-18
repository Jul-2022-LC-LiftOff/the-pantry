package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeIngredientRepository;
import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("banner", "home");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "index";
    }

    @GetMapping("about")
    public String about(Model model) {
        model.addAttribute("banner", "about");
        return "about";
    }

    @GetMapping("features")
    public String features(Model model) {
        model.addAttribute("banner", "features");
        return "features";
    }

    @GetMapping("recipe/{recipeId}")
    public String recipe(Model model, @PathVariable int recipeId) {
        Optional optRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optRecipe.get();
        model.addAttribute("banner", "recipe");
        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeIngredients", recipeIngredientRepository.findByRecipeId(recipeId));
        return "recipe";
    }

}