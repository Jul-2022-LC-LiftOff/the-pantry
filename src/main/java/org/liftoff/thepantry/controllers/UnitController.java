package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeIngredientRepository;
import org.liftoff.thepantry.data.UnitRepository;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/units")
public class UnitController {

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("")
    public String index(Model model) {
        List activeUnits = recipeIngredientRepository.findAll().stream().map(p->p.getUnit()).collect(Collectors.toList());
        model.addAttribute("activeUnits", activeUnits);
        model.addAttribute("units", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Unit());
        return "admin/units/index";
    }

    @PostMapping("add")
    public String addIngredient(@ModelAttribute @Valid Unit newUnit, Errors errors, Model model, RedirectAttributes ra) {
        // error checking
        if (errors.hasErrors()) {
            model.addAttribute("units", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Name is required.");
            return "redirect:/admin/units/";
        }
        if (!unitRepository.findByName(newUnit.getName()).isEmpty()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Unit '" + newUnit.getName() + "' already exists.");
            return "redirect:/admin/units/";
        }

        // save unit
        unitRepository.save(newUnit);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Unit '" + newUnit.getName() + "' added successfully.");
        return "redirect:/admin/units/";
    }

    @PostMapping("edit")
    public String editUnit(@ModelAttribute Unit unit, @RequestParam int unitId, Model model, RedirectAttributes ra) {
        // error checking
        if (unit.getName()==null || unit.getName()=="") {
            model.addAttribute("ingredients", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Name is required.");
            return "redirect:/admin/units/";
        }
        if (!unitRepository.findByName(unit.getName()).isEmpty()) {
            ra.addFlashAttribute("class", "alert alert-danger");
            ra.addFlashAttribute("message", "Ingredient '" + unit.getName() + "' already exists.");
            return "redirect:/admin/units/";
        }

        // save unit
        unit.setId(unitId);
        unitRepository.save(unit);

        ra.addFlashAttribute("class", "alert alert-success");
        ra.addFlashAttribute("message", "Ingredient '" + unit.getName() + "' updated successfully.");
        return "redirect:/admin/units/";
    }

    @PostMapping("delete")
    public String deleteUnit(@RequestParam int unitId, RedirectAttributes ra) {
        Optional optUnit = unitRepository.findById(unitId);
        Unit unit = (Unit) optUnit.get();

        // delete unit
        unitRepository.delete(unit);

        ra.addFlashAttribute("class", "alert alert-danger");
        ra.addFlashAttribute("message", "Ingredient '" + unit.getName() + "' deleted successfully.");
        return "redirect:/admin/units/";
    }

}