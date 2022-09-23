package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("banner", "home");
        model.addAttribute("recipes", recipeRepository.findAll(PageRequest.of(0, 8)));
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

}