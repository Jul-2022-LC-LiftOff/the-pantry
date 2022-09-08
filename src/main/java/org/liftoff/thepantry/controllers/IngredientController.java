package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute @Valid Ingredient newIngredient, Errors errors, Model model, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Name is required.");
            return "redirect:/ingredients/";
        }

        if (!ingredientRepository.findByName(newIngredient.getName()).isEmpty()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Ingredient '" + newIngredient.getName() + "' already exists.");
            return "redirect:/ingredients/";
        }

        ingredientRepository.save(newIngredient);
        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Ingredient '" + newIngredient.getName() + "' added successfully.");
        return "redirect:/ingredients/";
    }

    @PostMapping("delete")
    public String deleteIngredient(@RequestParam int ingredientId) {
        ingredientRepository.deleteById(ingredientId);
        return "redirect:";
    }

}