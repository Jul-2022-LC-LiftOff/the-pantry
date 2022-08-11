package org.launchcode.hellospring.data;

import org.launchcode.hellospring.model.Recipe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RecipeListData {
    private static final Map<Integer, Recipe> events = new HashMap<>();

    //get all events
    public static Collection<Recipe> getAll() {
        return events.values();
    }

    //get a single event
    public static Recipe getById(int id) {
        return events.get(id);

    }

    public static ArrayList getRecipes(SearchCriteria searchCritera) {
        ArrayList<Recipe> searchResults = new ArrayList<>();

        Recipe recipe1 = new Recipe("Chicken Sandwich", "Chicken, Bread, Lettuce, Tomato" );
        Recipe recipe2 = new Recipe("Chicken Fry", "Chicken, Bread,Oil, Flour" );
        Recipe recipe3 = new Recipe("Coffee", "Coffee,Water,Milk" );
        searchResults.add(recipe1);
        searchResults.add(recipe2);
        searchResults.add(recipe3);
        // TODO : Get recipes from database and populate map

        return searchResults;
    }

    // add an event
    public static void add(Recipe event) {
        events.put(event.getId(), event);
    }
}
