package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.UnitRepository;
import org.liftoff.thepantry.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("units")
public class UnitController {

    @Autowired
    UnitRepository unitRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("units", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Unit());
        return "units/index";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute @Valid Unit newUnit, Errors errors, Model model, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            model.addAttribute("units", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            model.addAttribute("errors", errors);
            return "units/index";
        }

        if (!unitRepository.findByName(newUnit.getName()).isEmpty()) {
            ra.addFlashAttribute("ingredientError", "Ingredient '" + newUnit.getName() + "' already exists.");
            return "redirect:/ingredients";
        }

        unitRepository.save(newUnit);
        return "redirect:";
    }

}