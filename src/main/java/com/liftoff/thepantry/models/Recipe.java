package com.liftoff.thepantry.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity {

    @NotBlank
    private String ingredients;

    @NotBlank
    private String instructions;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String ingredients, String instructions, List<Tag> tagsList) {
        super();
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tagsList;
    }

    // Getters and setters

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
