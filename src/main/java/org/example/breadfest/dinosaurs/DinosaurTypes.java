package org.example.breadfest.dinosaurs;

import org.example.breadfest.dice.Dice;
import org.example.breadfest.dice.DieFactory;
import org.example.breadfest.ingredients.*;

import java.io.IOException;
import java.util.Random;

public enum DinosaurTypes {
    Common("Baby",40, 1.0),
    Rare("Mommy",80, 2.0),
    Epic("Daddy",150, 3.0),
    Nuclear("Nuclear",1, 100.0);

    private final String name_prefix;
    private final int base_patience;
    private final double damage_modifier;
    private final DieFactory die_factory;
    private final IngredientFactory ingredient_factory;

    DinosaurTypes(String name_prefix, int base_patience, double damage_modifier) {
        this.name_prefix = name_prefix;
        this.base_patience = base_patience;
        this.damage_modifier = damage_modifier;
        this.die_factory = new DieFactory();
        try {
            this.ingredient_factory = new IngredientFactory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static DinosaurTypes getRandomDinosaurType(int room_depth){

        if (room_depth <= 3){
            return Common;
        }
        else if (room_depth <= 6){
            return Rare;
        }
        else if (room_depth <= 9){
            return Epic;
        }
        else{
            return Nuclear;
        }

    }

    public String getNamePrefix() {
        return name_prefix;
    }

    public int getBasePatience(){
        return this.base_patience;
    }

    public double getDamageModifier() {
        return this.damage_modifier;
    }



    private DinosaurTypes getNextTypeForDie(){
        switch (this){
            case Common -> {
                return Rare;
            }
            case Rare -> {
                return Epic;
            }
            case Epic -> {
                // 50% chance for nuclear
                if ((int)Math.round(Math.random()) == 1){
                    return Nuclear;
                }
                else{
                    return Epic;
                }
            }
            default -> {
                return Nuclear;
            }
        }
    }
    private IngredientRarity getIngredientRarity(){
        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

        switch (this){
            case Common -> {

                if (random_roll < 0.01){
                    return IngredientRarity.Nuclear;
                }
                else if (random_roll < 0.15){
                    return IngredientRarity.Epic;
                }
                else if (random_roll < 0.55){
                    return IngredientRarity.Rare;
                }
                return IngredientRarity.Common;

            }
            case Rare -> {
                if (random_roll < 0.01){
                    return IngredientRarity.Nuclear;
                }
                else if (random_roll < 0.15){
                    return IngredientRarity.Legendary;
                }
                else if (random_roll < 0.55){
                    return IngredientRarity.Epic;
                }
                return IngredientRarity.Rare;

            }
            case Epic -> {
                if (random_roll < 0.01){
                    return IngredientRarity.Nuclear;
                }
                else if (random_roll < 0.25){
                    return IngredientRarity.Champion;
                }
                else if (random_roll < 0.6){
                    return IngredientRarity.Legendary;
                }
                return IngredientRarity.Epic;
            }
            default -> {
                return IngredientRarity.Champion;
            }
        }
    }

    public Dice getDice() throws Exception {
        return die_factory.DieFactory(this);
    }
    public Dice getRewardDice() throws Exception {
        return die_factory.DieFactory(this.getNextTypeForDie());
    }

    public Ingredient getRewardIngredient() throws Exception {
        IngredientTypes random_ingredient_type = IngredientTypes.getRandomIngredientType();
        IngredientRarity ingredient_rarity = this.getIngredientRarity();
        return ingredient_factory.makeIngredientByType(random_ingredient_type,ingredient_rarity);
    }

}
