package com.liftoff.thepantry.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Recipe  extends AbstractEntity {

    @NotBlank
    private String ingredients;

    @NotBlank
    private String instructions;

    public Recipe() {
    }

    public Recipe(String ingredients, String instructions) {
        super();
        this.ingredients = ingredients;
        this.instructions = instructions;
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
}
