package org.liftoff.thepantry.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Recipe extends AbstractEntity {

    //@NotBlank
    private String description;

    //@NotBlank
    private String instructions;

    @OneToOne
    @JoinColumn(name = "id")
    private RecipeIngredient recipeIngredient;

    public Recipe() {
    }

    public Recipe(String description, String instructions) {
        super();
        this.description = description;
        this.instructions = instructions;
    }

    // getters and setters

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

}