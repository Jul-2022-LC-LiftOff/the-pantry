package org.liftoff.thepantry.data;

import org.liftoff.thepantry.models.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findByRecipeId(int recipe_id);
    List<RecipeIngredient> findByRecipeIdAndIngredientId(int recipe_id, int ingredient_id);
    List<RecipeIngredient> findByIdAndIngredientId(int id, int ingredient_id);
    List<RecipeIngredient> findByIngredientId(int ingredient_id);
}
