package org.liftoff.thepantry.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {

    @ManyToMany
    @JoinTable(name = "recipe_tag", joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    private List<Recipe> recipes = new ArrayList<>();

    public Tag() {
    }

    // Getters and Setters

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

}
