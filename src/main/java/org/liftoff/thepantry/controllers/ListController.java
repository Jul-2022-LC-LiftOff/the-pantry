package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.data.RecipeRepository;
import org.liftoff.thepantry.data.SearchDTO;
import org.liftoff.thepantry.models.Ingredient;
import org.liftoff.thepantry.models.Recipe;
import org.liftoff.thepantry.models.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("list")
public class ListController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

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
        ArrayList<Integer> ingredientsArray = new ArrayList();
        ArrayList<Recipe> recipes = new ArrayList<>();
        Iterator searchIterator = Arrays.stream(text2search).iterator();
        while(searchIterator.hasNext()) {
            String searchText = (String) searchIterator.next();
            System.out.println(" Searching for " + searchText);
            if (searchText != null && searchText.length()>0) {
                   ingredientsArray.add(Integer.valueOf(searchText));
                   System.out.println(" added text to search " + searchText + " " + Integer.valueOf(searchText));
                   System.out.println(" array size = " + ingredientsArray.size());
            }
        }
        System.out.println("List of ingre to search = " + ingredientsArray);
        Iterable ingreIterable = ingredientRepository.findAllById(ingredientsArray);
        Iterator ingreIterator = ingreIterable.iterator();
        while (ingreIterator.hasNext()) {
            System.out.println(" searching for ingredients");
            Ingredient ing = (Ingredient) ingreIterator.next();
            System.out.println("Found ingre = " + ing.getName());
            List<RecipeIngredient> recIng = ing.getRecipeIngredients();
            for(RecipeIngredient ri : recIng ) {
                recipes.add(ri.getRecipe());
                System.out.println(" Added recipe with name " + ri.getRecipe().getName());
            }
        }

        model.addAttribute("recipes",recipes);
        return "list/index";
    }
}
