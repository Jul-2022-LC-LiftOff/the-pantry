package org.liftoff.helloworld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping
    @ResponseBody
    public String index() {
    return "Hello, World!";
}

//    @GetMapping("index")
//    public String index(Model model) {
//        model.addAttribute();
//        return "Hello, successful controller!";
//    }
}
