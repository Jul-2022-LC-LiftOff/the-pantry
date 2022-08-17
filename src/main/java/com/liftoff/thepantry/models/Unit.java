package com.liftoff.thepantry.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Unit extends AbstractEntity {

    @OneToMany
    @JoinColumn(name = "unit_id")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Unit() {
    }

    // Getters and Setters

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

}
