package org.example.breadfest.ingredients.fire;

import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class CayennePepper implements Ingredients {
    @Override
    public int getTastiness() {
        return 10;
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Fire;
    }
}
