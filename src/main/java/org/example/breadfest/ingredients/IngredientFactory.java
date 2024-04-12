package org.example.breadfest.ingredients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Map;


public class IngredientFactory {

    private final Map<String, List<String>> ingredient_names;

    public IngredientFactory() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String DIALOGUE_FILE_PATH = "src/main/java/org/example/breadfest/ingredients/ingredients.json";
        ingredient_names = mapper.readValue(new File(DIALOGUE_FILE_PATH), new TypeReference<>() {});
    }


    public Ingredient makeIngredient() throws Exception {

        IngredientTypes ingredient_type = IngredientTypes.getRandomIngredientType();
        IngredientRarity ingredient_rarity = IngredientRarity.getRandomIngredientRarity();

        return this.makeIngredientByType(ingredient_type, ingredient_rarity);

    }

    public Ingredient makeIngredientByType(IngredientTypes ingredient_type, IngredientRarity ingredient_rarity) throws Exception {

        String type = ingredient_type.toString() + ingredient_rarity.toString();
        String ingredient_name = JSONReadingHelper.generateRandomElementFromJSON(this.ingredient_names, type);

        return new Ingredient(ingredient_name, ingredient_type, ingredient_rarity);

    }

    public Ingredient makeNuclearIngredient() throws Exception {
        return makeIngredientByType(IngredientTypes.getRandomIngredientType(), IngredientRarity.Nuclear);
    }


}
