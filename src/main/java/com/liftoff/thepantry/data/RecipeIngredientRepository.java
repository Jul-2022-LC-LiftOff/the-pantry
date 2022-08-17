package com.liftoff.thepantry.data;

import com.liftoff.thepantry.models.Ingredient;
import com.liftoff.thepantry.models.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findByRecipeId(int recipe_id);
}
