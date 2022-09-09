package org.liftoff.thepantry.controllers;


import org.json.JSONArray;
import org.json.JSONObject;
import org.liftoff.thepantry.data.IngredientRepository;
import org.liftoff.thepantry.data.SearchDTO;
import org.liftoff.thepantry.data.TagRepository;
import org.liftoff.thepantry.models.Ingredient;
import org.liftoff.thepantry.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {
//        model.addAttribute("title", "Recipes");
//        model.addAttribute("recipes", recipeRepository.findAll());

        System.out.println("------------- In SearchController - index");
     //   model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute(new SearchDTO());
        return "search/index";
    }

    @GetMapping(value = "/filter", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String filterResult(@RequestParam String q) {
        System.out.println("Searching for " + q);
        Ingredient ingredient = null;
        Iterable<Ingredient> ingredientIterator = ingredientRepository.findAll();
        int selectCount = 0;
        JSONArray jsonArray = new JSONArray();
        Iterator ingIterator = ingredientIterator.iterator();
        while(ingIterator.hasNext()) {
            ingredient = (Ingredient) ingIterator.next();
            if (ingredient.getName().toLowerCase(Locale.ROOT).contains(q.toLowerCase(Locale.ROOT))) {
                HashMap<String,String> outmap = new HashMap<>();
                outmap.put("value", String.valueOf(ingredient.getId()));
                outmap.put("text", ingredient.getName());
                selectCount++;
                jsonArray.put(outmap);

                System.out.println(" Adding to array " + ingredient.getName());
            }

        }


        System.out.println("Returning " + jsonArray.toString());

        return jsonArray.toString();
    }

    @PostMapping
    public String searchRecipe(@ModelAttribute @Valid SearchDTO searchDTO, Errors errors, Model model) {

        System.out.println("------------- In SearchController - search");
        System.out.println("Search text = " + searchDTO.getIngredients());
        return "redirect:";
    }
}