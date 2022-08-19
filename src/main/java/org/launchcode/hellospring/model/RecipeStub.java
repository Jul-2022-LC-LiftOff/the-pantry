package org.launchcode.hellospring.model;

import java.util.Objects;

public class RecipeStub {
    private String recipeName;
    private int id;
    private String ingredients;
    private String recipeInstructions;
    private static int nextId = 1;

    public RecipeStub(String recipeName, String ingredients) {
        this.recipeName=recipeName;
        this.ingredients=ingredients;
        this.id=nextId;
        nextId++;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeStub event = (RecipeStub) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}