package com.liftoff.helloworld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

//    @GetMapping("")
//    @ResponseBody
//    public String hello() {
//        return "Hello, World!";
//    }

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("greetme")
    public String greetme() {
        return "greetme";
    }

    @PostMapping("greetme")
    public String greetMe(@RequestParam String name, Model model) {
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "greetme";
    }
}
