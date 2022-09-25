package org.liftoff.thepantry.data;

import org.liftoff.thepantry.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByName(String name);
    Page<Recipe> findByInstructionsContaining(String searchTerm, Pageable pageable);

    @Query(value = "SELECT * FROM Recipe order by rand() limit 1", nativeQuery = true)
    Recipe randomRecipe();
}
