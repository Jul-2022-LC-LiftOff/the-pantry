package com.liftoff.thepantry.data;

import com.liftoff.thepantry.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
