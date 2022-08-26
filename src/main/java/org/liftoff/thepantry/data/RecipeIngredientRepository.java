package org.liftoff.thepantry.data;

import org.liftoff.thepantry.models.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findByRecipeId(int recipe_id);
}
