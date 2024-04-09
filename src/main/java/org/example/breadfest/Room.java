package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurFactory;
import org.example.breadfest.ingredients.IngredientFactory;
import org.example.breadfest.ingredients.Ingredients;

import java.util.*;

public class Room {

    private final int depth;

    private char entry_direction;

    private Map<Character, Room> neighboring_rooms;

    private String background_image;


    List<Integer> available_locations_for_entities;
    private Ingredients[] ingredients_in_room_by_location;
    private Dinosaur[] dinosaurs_in_room_by_location;


    public Room(int depth){
        // depth of room from cave-room 0
        this.depth = depth;
        this.neighboring_rooms = new HashMap<>();

    }

    public boolean areThereNeighbors(){
        if (this.neighboring_rooms == null) {
            return false;
        }
        else {
            return true;
        }
    }

    private int generateNeighboringRoomsCount(){
        if (this.depth == 0){
            return 3;
        }

        double chance_for_dead_end = this.depth*0.1;
        double chance_for_connected_room = (1-chance_for_dead_end)/3;

        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

        if (random_roll < this.depth*0.1){
            return 0;
        }
        else if (random_roll < chance_for_dead_end+chance_for_connected_room){
            return 1;
        }
        else if (random_roll < chance_for_dead_end+2*chance_for_connected_room){
            return 2;
        }
        else {
            return 3;
        }
    }

    private int generateDinosaurCount(){

        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

       if (this.depth <= 2){
            if (random_roll < 0.2){
                return 2;
            }
            else if (random_roll < 0.6){
                return 1;
            }
            return 0;
        }
        else if (this.depth <= 6){
            if (random_roll < 0.2){
                return 0;
            }
            else if (random_roll < 0.6){
                return 1;
            }
            return 2;
        }
        else {
            // max 1 spawns above room depth 6. 50% chance of spawn
            if (random_roll < 0.5){
                return 1;
            }
            return 0;
        }

    }

    private int generateIngredientCount(){

        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

        if (this.depth <= 2){
            if (random_roll < 0.2){
                return 2;
            }
            else if (random_roll < 0.6){
                return 1;
            }
            return 0;
        }
        else if (this.depth <= 5){
            if (random_roll < 0.2){
                return 0;
            }
            else if (random_roll < 0.6){
                return 1;
            }
            return 2;
        }
        else if (this.depth <= 8){
            if (random_roll < 0.2){
                return 3;
            }
            else if (random_roll < 0.6){
                return 2;
            }
            return 1;
        }
        else{
            if (random_roll < 0.2){
                return 4;
            }
            return 2;
        }

    }

    private void generateIngredients() throws Exception {

        IngredientFactory ingredient_factory = new IngredientFactory();

        Random random_seed = new Random();

        if (this.depth == 0){
            if (random_seed.nextDouble()<0.01){
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int ingredient_location = this.available_locations_for_entities.remove(random_index);
                this.ingredients_in_room_by_location[ingredient_location] = ingredient_factory.makeNuclearIngredient();
            }
        }

        switch (this.generateIngredientCount()){
            case 4: {
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int ingredient_location = this.available_locations_for_entities.remove(random_index);
                this.ingredients_in_room_by_location[ingredient_location] = ingredient_factory.makeIngredient();
            }
            case 3: {
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int ingredient_location = this.available_locations_for_entities.remove(random_index);
                this.ingredients_in_room_by_location[ingredient_location] = ingredient_factory.makeIngredient();
            }
            case 2: {
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int ingredient_location = this.available_locations_for_entities.remove(random_index);
                this.ingredients_in_room_by_location[ingredient_location] = ingredient_factory.makeIngredient();
            }
            case 1: {
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int ingredient_location = this.available_locations_for_entities.remove(random_index);
                this.ingredients_in_room_by_location[ingredient_location] = ingredient_factory.makeIngredient();
            }

        }


    }

    private void generateDinosaurs() throws Exception {

        if (this.depth == 0){
            return;
        }

        DinosaurFactory dinosaur_factory = new DinosaurFactory();

        Random random_seed = new Random();

        switch (this.generateDinosaurCount()){
            case 2: {
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int dinosaur_location = this.available_locations_for_entities.remove(random_index);
                this.dinosaurs_in_room_by_location[dinosaur_location] = dinosaur_factory.makeADinosaurFromDepth(this.depth);
            }
            case 1: {
                int random_index = random_seed.nextInt(this.available_locations_for_entities.size());
                int dinosaur_location = this.available_locations_for_entities.remove(random_index);
                this.dinosaurs_in_room_by_location[dinosaur_location] = dinosaur_factory.makeADinosaurFromDepth(this.depth);
            }

        }

    }

    private void generateNeighboringRoomsMap(){

        int num_rooms_to_generate = this.generateNeighboringRoomsCount();

        if (num_rooms_to_generate == 0){
            return;
        }

        List<Character> available_directions = new ArrayList<Character>() {{
            add('N');
            add('S');
            add('E');
            add('W');
        }};

        available_directions.remove(this.entry_direction);
        Random random = new Random();

        switch (num_rooms_to_generate){
            case 3:{
                int direction_index = random.nextInt(available_directions.size());
                char new_room_direction = available_directions.remove(direction_index);

                this.neighboring_rooms.put(new_room_direction, new Room(this.depth+1));
            }
            case 2:{
                int direction_index = random.nextInt(available_directions.size());
                char new_room_direction = available_directions.remove(direction_index);

                this.neighboring_rooms.put(new_room_direction, new Room(this.depth+1));
            }
            case 1:{
                int direction_index = random.nextInt(available_directions.size());
                char new_room_direction = available_directions.remove(direction_index);

                this.neighboring_rooms.put(new_room_direction, new Room(this.depth+1));
                break;
            }

        }

    }

    private void generateBackgroundImage(){

        // TODO: add art for different room types, and randomly generate one based on num rooms
        this.background_image = "";

    }

    private void populateRoom() throws Exception {

        // add ingredients/dinosaurs to 8 available locations
        this.available_locations_for_entities = new ArrayList<>();
        for (int location = 0; location <= 7; location++) {
            available_locations_for_entities.add(location);
        }

        // add dinosaurs to room
        this.dinosaurs_in_room_by_location = new Dinosaur[8];
        this.generateDinosaurs();

        // add ingredients to room
        this.ingredients_in_room_by_location = new Ingredients[8];
        this.generateIngredients();

    }


    public void moveToRoom(char entry_direction, Room prev_room) throws Exception {

        // direction from which player enter the room (in what direction cave-room 0 is)
        this.entry_direction = entry_direction;

        // generating new neighbors
        this.neighboring_rooms = new HashMap<>();
        this.neighboring_rooms.put(entry_direction, prev_room);

        // generate neighboring rooms
        this.generateNeighboringRoomsMap();

        // generate background image
        this.generateBackgroundImage();

        // populate the room
        this.populateRoom();
    }



    // getters
    public Room getRoomNeighbor(char direction){
        return this.neighboring_rooms.get(direction);
    }

    public int getDepth() {
        return depth;
    }

    public String getBackgroundImage() {
        return this.background_image;
    }

    public Ingredients[] getRoomIngredients() {
        return ingredients_in_room_by_location;
    }

    public Dinosaur[] getRoomDinosaurs() {
        return dinosaurs_in_room_by_location;
    }
}
