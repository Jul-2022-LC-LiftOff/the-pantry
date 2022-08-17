package com.liftoff.thepantry.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity {

    @NotBlank
    private String description;

    @NotBlank
    private String instructions;

    @ManyToMany
    @JoinTable(name = "recipe_tag", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String description, String instructions, List<Tag> tagsList, List<Ingredient> ingredientList) {
        super();
        this.description = description;
        this.instructions = instructions;
        this.tags = tagsList;
    }

    // Getters and setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

}
