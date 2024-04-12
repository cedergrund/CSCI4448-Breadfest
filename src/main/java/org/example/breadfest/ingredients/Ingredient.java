package org.example.breadfest.ingredients;

public class Ingredient {

    private final String name;
    private final IngredientTypes ingredient_type;
    private final IngredientRarity ingredient_rarity;

    public Ingredient(String name, IngredientTypes ingredient_type, IngredientRarity ingredient_rarity) {
        this.name = name;
        this.ingredient_type = ingredient_type;
        this.ingredient_rarity = ingredient_rarity;
    }

    public String getName(){
        return this.name;
    }

    public IngredientTypes getType(){
        return this.ingredient_type;
    }

    public IngredientRarity getRarity(){
        return this.ingredient_rarity;
    }

//    public int getScore(){
//        return (int) (this.ingredient_rarity.getIngredientScore() * this.ingredient_type.getIngredientMultiplier());
//    }
}
