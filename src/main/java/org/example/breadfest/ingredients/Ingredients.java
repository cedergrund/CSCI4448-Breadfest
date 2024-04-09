package org.example.breadfest.ingredients;

import org.example.breadfest.ingredients.IngredientTypes;

public interface Ingredients {

    int getScore();

    IngredientTypes getType();

    IngredientRarity getRarity();
}
