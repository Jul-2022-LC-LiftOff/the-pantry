package org.liftoff.thepantry.data;

import org.liftoff.thepantry.models.Recipe;
import org.launchcode.hellospring.model.RecipeStub;

import java.util.ArrayList;

public class RecipeData {
    //private static final Map<Integer, RecipeStub> events = new HashMap<>();

    //get all events
    //public static Collection<RecipeStub> getAll() {
       // return events.values();
    //}

    //get a single event
    //public static RecipeStub getById(int id) {
       // return events.get(id);

    //}

    public static ArrayList getRecipes(SearchCriteria searchCritera, Iterable<Recipe> allRecipes) {
        ArrayList<Recipe> searchResults = new ArrayList<>();

//        Recipe recipe1 = new Recipe("Chicken Sandwich", "Chicken, Bread, Lettuce, Tomato" );
//        Recipe recipe2 = new Recipe("Chicken Fry", "Chicken, Bread,Oil, Flour" );
//        Recipe recipe3 = new Recipe("Coffee", "Coffee,Water,Milk" );
//        searchResults.add(recipe1);
//        searchResults.add(recipe2);
//        searchResults.add(recipe3);
        // TODO : Get recipes from database and populate map
        if (searchCritera == null )
            return (ArrayList<Recipe>) allRecipes;
        for(Recipe recipe : allRecipes) {

        }

        return searchResults;
    }

    //public static Recipe getRecipeByName(String name, Iterable<Recipe> allRecipes) {
        //for(Recipe recipe : allRecipes) {
            //if (recipe.getName().equalsIgnoreCase(name)) {
               // return recipe;
            //}
       // }
       // return null;
    //}

    // add an event
   // public static void add(RecipeStub event) {
       // events.put(event.getId(), event);
    //}
}
