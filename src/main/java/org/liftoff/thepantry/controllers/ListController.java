package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.data.RecipeIngredientRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("list")
public class ListController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("banner", "search");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "list/index";
    }

    @PostMapping()
    public String searchRecipe(@ModelAttribute @Valid SearchDTO searchDTO, Model model, Errors errors) {

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

        List<Integer> listOfSelectedRecipes = new ArrayList<>();
        while (ingreIterator.hasNext()) {
            System.out.println(" searching for ingredients");
            Ingredient ing = (Ingredient) ingreIterator.next();
            System.out.println("Found ingre = " + ing.getName());

          List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findByIngredientId(ing.getId());
          int nofRecipeIngredients = recipeIngredientList.size();

          for(int i = 0; i < nofRecipeIngredients; i++) {
              RecipeIngredient ri = recipeIngredientList.get(i);
              System.out.println(" Got recipe " + ri.getRecipe().getName());
              if (!listOfSelectedRecipes.contains(ri.getRecipe().getId())) {
                  // Keep a list of selected recipes to avoid duplicates.
                  System.out.println(" added to return array");
                  recipes.add(ri.getRecipe());
                  listOfSelectedRecipes.add(ri.getRecipe().getId());
              }
          }
        }
        System.out.println("Return map contains " + recipes.size());
        model.addAttribute("banner", "search");
        model.addAttribute("recipes",recipes);
        return "list/index";
    }
}
