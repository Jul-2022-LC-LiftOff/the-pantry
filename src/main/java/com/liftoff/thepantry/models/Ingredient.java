package com.liftoff.thepantry.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Ingredient extends AbstractEntity {

    @ManyToOne
    private Recipe recipe;

    private String unit;

    private String amount;

    public Ingredient() {
    }

    public Ingredient(Recipe recipe, String unit, String amount) {
        super();
        this.recipe = recipe;
        this.unit = unit;
        this.amount = amount;
    }

    // Getters and Setters

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
