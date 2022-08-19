package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/add")
    public String  displayAddRecipe(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());
        return "add";
    }

    @PostMapping("add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe,
                                    Errors errors, Model model, @RequestParam String ingredients, @RequestParam String instructions) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");

            return "add";
        }

        newRecipe.setIngredients(ingredients);

        newRecipe.setInstructions(instructions);

        recipeRepository.save(newRecipe);

        return "redirect:";
    }


}
