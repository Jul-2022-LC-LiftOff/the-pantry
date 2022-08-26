package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Ingredients");
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("add")
    public String displayAddIngredient(Model model) {
        model.addAttribute("title", "Add Ingredient");
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute(new Ingredient());
        return "ingredients/add";
    }

    @PostMapping("add")
    public String processAddIngredient(@ModelAttribute @Valid Ingredient newIngredient, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Ingredient");
            return "ingredients/add";
        }

        ingredientRepository.save(newIngredient);
        return "redirect:";
    }

}
