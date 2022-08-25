package com.liftoff.thepantry.controllers;

import com.liftoff.thepantry.data.RecipeData;
import com.liftoff.thepantry.data.RecipeRepository;
import com.liftoff.thepantry.models.Recipe;
import com.liftoff.thepantry.data.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping()
public class RecipeListController {

    @Autowired
    private RecipeRepository recipeRepository;

    //    HashMap<String,String> recipeHash=new HashMap<>();


//public String displayListOfRecipe(Model model){
//        model.addAttribute("title","Search Recipe");
//        model.addAttribute("events", RecipeListData.getAll());
//    return "RecipeList";
//}
//    @GetMapping("RecipeList")
//    public String displayCreateEventForm(Model model) {
//        model.addAttribute("title", "Create Event");
//        model.addAttribute(new Recipe());
//        return "events/create";
//    }

    @GetMapping("list")
    public String listRecipes(Model model) {
        SearchCriteria searchCriteria = null;
        Recipe recipe = new Recipe();
        ArrayList searchResult = RecipeData.getRecipes(searchCriteria, recipeRepository.findAll());
         model.addAttribute("recipes",searchResult);

        return "/list";
    }

}
