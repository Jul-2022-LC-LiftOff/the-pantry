package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.data.RecipeIngredientRepository;
import org.liftoff.thepantry.models.Ingredient;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("")
    public String index(Model model) {
        List activeIngredients = recipeIngredientRepository.findAll().stream().map(p->p.getIngredient()).collect(Collectors.toList());
        model.addAttribute("activeIngredients", activeIngredients);
        model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Ingredient());
        return "admin/ingredients/index";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute @Valid Ingredient newIngredient, Errors errors, Model model, RedirectAttributes ra) {
        // error checking
        if (errors.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Name is required.");
            return "redirect:/admin/ingredients/";
        }
        if (!ingredientRepository.findByName(newIngredient.getName()).isEmpty()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Ingredient '" + newIngredient.getName() + "' already exists.");
            return "redirect:/admin/ingredients/";
        }

        // save ingredient
        ingredientRepository.save(newIngredient);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Ingredient '" + newIngredient.getName() + "' added successfully.");
        return "redirect:/admin/ingredients/";
    }

    @PostMapping("edit")
    public String editIngredient(@ModelAttribute Ingredient ingredient, @RequestParam int ingredientId, Model model, RedirectAttributes ra) {
        // error checking
        if (ingredient.getName()==null || ingredient.getName()=="") {
            model.addAttribute("ingredients", ingredientRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Name is required.");
            return "redirect:/admin/ingredients/";
        }
        if (!ingredientRepository.findByName(ingredient.getName()).isEmpty()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Ingredient '" + ingredient.getName() + "' already exists.");
            return "redirect:/admin/ingredients/";
        }

        // save ingredient
        ingredient.setId(ingredientId);
        ingredientRepository.save(ingredient);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Ingredient '" + ingredient.getName() + "' updated successfully.");
        return "redirect:/admin/ingredients/";
    }

    @PostMapping("delete")
    public String deleteIngredient(@RequestParam int ingredientId, RedirectAttributes ra) {
        Optional optIngredient = ingredientRepository.findById(ingredientId);
        Ingredient ingredient = (Ingredient) optIngredient.get();

        // delete ingredient
        ingredientRepository.delete(ingredient);

        ra.addFlashAttribute("class", "alert alert-danger");
        ra.addFlashAttribute("message", "Ingredient '" + ingredient.getName() + "' deleted successfully.");
        return "redirect:/admin/ingredients/";
    }

}