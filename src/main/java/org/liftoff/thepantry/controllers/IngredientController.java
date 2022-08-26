package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Ingredients");
        model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute @Valid Ingredient newIngredient, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Ingredients");
            return "ingredients/index";
        }

        ingredientRepository.save(newIngredient);
        return "redirect:";
    }

}
