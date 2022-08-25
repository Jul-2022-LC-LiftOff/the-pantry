package com.liftoff.thepantry.controllers;

import com.liftoff.thepantry.data.UnitRepository;
import com.liftoff.thepantry.models.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("units")
public class UnitController {

    @Autowired
    UnitRepository unitRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Units");
        model.addAttribute("units", unitRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        model.addAttribute(new Unit());
        return "units/index";
    }

    @PostMapping("add")
    public String addUnit(@ModelAttribute @Valid Unit newUnit, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Units");
            return "units/index";
        }

        unitRepository.save(newUnit);
        return "redirect:";
    }

}
