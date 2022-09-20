package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.RecipeData;
import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.models.Recipe;
import org.liftoff.thepantry.data.SearchCriteria;
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

    @GetMapping("recipelist")
    public String listRecipes(Model model) {
        SearchCriteria searchCriteria = null;
        Recipe recipe = new Recipe();
        ArrayList searchResult = RecipeData.getRecipes(searchCriteria, recipeRepository.findAll());
         model.addAttribute("recipes",searchResult);
         System.out.println(" -- " + searchResult.toString());
        return "/list";
    }

}