package org.liftoff.thepantry.controllers;


import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.data.SearchDTO;
import org.liftoff.thepantry.data.TagRepository;
import org.liftoff.thepantry.models.Tag;
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
@RequestMapping("search")
public class SearchController {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("")
    public String index(Model model) {
//        model.addAttribute("title", "Recipes");
//        model.addAttribute("recipes", recipeRepository.findAll());

        System.out.println("------------- In SearchController - index");
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute(new SearchDTO());
        return "search/index";
    }

    @PostMapping()
    public String searchRecipe(@ModelAttribute @Valid SearchDTO searchDTO, Errors errors, Model model) {

        System.out.println("------------- In SearchController - search");
        System.out.println("Search text = " + searchDTO.getIngredients());
        return "redirect:";
    }
}