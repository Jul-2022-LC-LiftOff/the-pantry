package org.liftoff.thepantry.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Amount is required")
    private String amount;

    @ManyToOne
    private Ingredient ingredient;

    @ManyToOne
    private Unit unit;

    @OneToOne
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(String amount, Recipe recipe, Ingredient ingredient, Unit unit) {
        this.amount = amount;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.unit = unit;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}
