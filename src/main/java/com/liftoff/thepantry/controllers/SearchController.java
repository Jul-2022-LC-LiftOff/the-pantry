package com.liftoff.thepantry.controllers;


import com.liftoff.thepantry.data.RecipeData;
import com.liftoff.thepantry.data.RecipeTag;
import com.liftoff.thepantry.data.SearchCriteria;
import com.liftoff.thepantry.models.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping()
public class SearchController {

    @GetMapping("search")
    public String searchRecipes(Model model) {
        RecipeTag tags = new RecipeTag();
        model.addAttribute("tags", tags.getTags());
        return "/searchrecipe";
    }

}
