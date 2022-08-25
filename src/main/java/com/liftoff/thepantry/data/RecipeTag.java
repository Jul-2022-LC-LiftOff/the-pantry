package com.liftoff.thepantry.data;

import java.util.ArrayList;
import java.util.List;

public class RecipeTag {
    private List<String> tags=new ArrayList<>();

    public RecipeTag() {
        tags.add("Keto");
        tags.add("Heathy");
        tags.add("Quick cook");
        tags.add("Bake");
    }

    public List getTags() {
        return tags;
    }
}
