package com.liftoff.thepantry.controllers;

import com.liftoff.thepantry.models.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/add")
    public String  displayAddRecipe(Model model) {
        model.addAttribute("title", "Add Recipe");
        model.addAttribute(new Recipe());
        return "add";
    }

//    @GetMapping("greetme")
//    public String greetme() {
//        return "greetme";
//    }

//    @PostMapping("greetme")
//    public String greetMe(@RequestParam String name, Model model) {
//        String greeting = "Hello, " + name + "!";
//        model.addAttribute("greeting", greeting);
//        return "greetme";
//    }

}
