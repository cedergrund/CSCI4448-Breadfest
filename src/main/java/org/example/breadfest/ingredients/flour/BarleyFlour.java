package org.example.breadfest.ingredients.flour;

import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

public class BarleyFlour implements Ingredients {
    @Override
    public int getScore() {
        return this.getRarity().getIngredientScore();
    }

    @Override
    public IngredientTypes getType() {
        return IngredientTypes.Flour;
    }

    @Override
    public IngredientRarity getRarity() { return IngredientRarity.Rare; }
}
