package org.example.breadfest.ingredients.magick;

import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class PhoenixFeathers implements Ingredients {
    @Override
    public int getTastiness() {
        return 10;
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Magick;
    }
}
