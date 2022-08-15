package com.liftoff.thepantry.controllers;

import com.liftoff.thepantry.data.RecipeRepository;
import com.liftoff.thepantry.data.TagRepository;
import com.liftoff.thepantry.models.Recipe;
import com.liftoff.thepantry.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Recipes");
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("add")
    public String  displayAddRecipe(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute(new Recipe());
        return "recipes/add";
    }

    @PostMapping("add")
    public String processAddRecipe(@ModelAttribute @Valid Recipe newRecipe,
                                   Errors errors, Model model, @RequestParam String description, @RequestParam String instructions, @RequestParam List<Integer> tags) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Recipe");
            model.addAttribute("tags", tagRepository.findAll());

            return "recipes/add";
        }

        newRecipe.setDescription(description);

        newRecipe.setInstructions(instructions);

        List<Tag> tagObjs = (List<Tag>) tagRepository.findAllById(tags);
        newRecipe.setTags(tagObjs);

        recipeRepository.save(newRecipe);

        return "redirect:";
    }

}
