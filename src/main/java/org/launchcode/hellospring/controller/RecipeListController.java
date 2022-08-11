package org.launchcode.hellospring.controller;

import org.launchcode.hellospring.data.RecipeListData;
import org.launchcode.hellospring.data.SearchCriteria;
import org.launchcode.hellospring.model.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping()
public class RecipeListController {
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

    @GetMapping("recipielist")
    public String processCreateEventForm(Model model) {
        SearchCriteria searchCriteria = null;
        System.out.println("------------ In processCreateEvent ");
        ArrayList searchResult = RecipeListData.getRecipes(searchCriteria);
         model.addAttribute("recipes",searchResult);
         System.out.println(" -- " + searchResult.toString());
        return "/recipelist";
    }
}
