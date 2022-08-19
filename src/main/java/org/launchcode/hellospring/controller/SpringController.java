package org.launchcode.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()

public class SpringController {
    @RequestMapping(value="hello",method={RequestMethod.GET,RequestMethod.POST})

    public String helloSpring(@RequestParam String name, Model model){
        String greeting="Hello "+name+" !";
        model.addAttribute("greeting",greeting);
        return "hello";
    }
    @GetMapping("hello/{name}")

    public String goodbyeSpring(@PathVariable String name,Model model){
        String greeting="Hello "  +name+" !";
        model.addAttribute("greeting",greeting);

        return "hello";
    }
    @GetMapping("form")

    public String helloForm(){
       return "form";
    }
    @GetMapping("hello-names")

    public String helloNames(Model model){
        List<String> names=new ArrayList<>();
        names.add("thanvi");
        names.add("jisha");
        names.add("govindan");
        names.add("nisha");
        model.addAttribute("names",names);
        return "hello-list";
    }

}
