package org.example.breadfest.ingredients;

public enum IngredientRarity {
    Common(10),
    Rare(25),
    Epic(50),
    Legendary(100),
    Champion(500),
    Nuclear(-1);

    private final int ingredient_score;

    IngredientRarity(int ingredient_score) {
        this.ingredient_score = ingredient_score;
    }

    public int getIngredientScore() {
        return this.ingredient_score;
    }

}
