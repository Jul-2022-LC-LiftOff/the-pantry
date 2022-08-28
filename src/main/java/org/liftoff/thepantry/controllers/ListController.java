package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.data.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.StringTokenizer;

@Controller
@RequestMapping("list")
public class ListController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "list/index";
    }

    @PostMapping()
    public String searchRecipe(@ModelAttribute @Valid SearchDTO searchDTO, Errors errors, Model model) {

        System.out.println("------------- In ListController - search");
        System.out.println("Search text = " + searchDTO.getIngredients());
        String[] text2search = searchDTO.getIngredients().split("~~~");
        model.addAttribute("searchString", searchDTO.getIngredients());
        return "list/index";
    }
}
