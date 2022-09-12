package org.liftoff.thepantry.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
public class Recipe extends AbstractEntity {

    @Size(max = 500)
    private String description;

    @Size(max = 2500)
    private String instructions;

    private String image;

    @OneToOne
    @JoinColumn(name = "id")
    private RecipeIngredient recipeIngredient;

    public Recipe() {
    }

    public Recipe(String description, String instructions, String image) {
        super();
        this.description = description;
        this.instructions = instructions;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        if (image != null && image.isEmpty()) {
            image = null;
        }
        this.image = image;
    }

}