package org.liftoff.thepantry.controllers;

import org.liftoff.thepantry.data.TagRepository;
import org.liftoff.thepantry.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Tags");
        model.addAttribute("tags", tagRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
        return "tags/index";
    }

    @GetMapping("add")
    public String displayAddTag(Model model) {
        model.addAttribute("title", "Add Tag");
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute(new Tag());
        return "tags/add";
    }

    @PostMapping("add")
    public String processAddTag(@ModelAttribute @Valid Tag newTag, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tag");
            return "tags/add";
        }

        tagRepository.save(newTag);
        return "redirect:";
    }

}
