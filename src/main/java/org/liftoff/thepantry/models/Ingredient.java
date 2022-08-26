package org.liftoff.thepantry.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity {

    @OneToMany
    @JoinColumn(name = "ingredient_id")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Ingredient() {
    }

    // Getters and Setters

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

}
