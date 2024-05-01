# CSCI4448-Breadfest


video link:
https://www.youtube.com/watch?v=bFWQBMaKmCs

**Project Description:**

* In the land of Rawr-dough Valley, one commodity is valued above all others: Bread! The foundational creation (and the valley’s #1 export!) comes in all forms, shapes, sizes... and magical ingredients. In fact, the valley lies on a hotbed of dark cave systems in which destructive dinosaurs lurk, protecting edible nuggets of bread supplementation. The variety in taste & utility of these nuggets brings bread innovation to a scale not seen anywhere outside the valley’s high mountains. And, at the culmination of this innovation lies Breadfest: the most renowned exhibition festival of Rawr-dough Valley’s master bread bakers!
* In ‘The Quest for Breadfest,’ you play as a baker hopeful - cyclically gathering ingredients from the valley’s caves and using them to bake an original bread - hoping one day to bake a bread worthy enough to join the elite class of master bread bakers that get to present their creations at Breadfest. With each bake, your cave collection skills grow, and your name earns glory. Will you make it out of the caves, or will they be your doom? Can you bake a worthy bread, or should you have become a healer or law-speaker like your parents wanted? Do you have what it takes to be a master baker? Find out in The Quest for Breadfest!

**Check out our Project 7 Interim Report in our project_7 deliverables folder :)**

### BDD --> 

#### Scenarios detailing the flow and user experience of the game:

1. **Beginning Cave Exploration** :
    - SCENARIO: The user enters the first room in the cave system by clicking the Cave Entrance illustration in the Home Room
    - GIVEN: The application has successfully launched and the user is in the home room with the cave entrance image and the baking house image
    - WHEN: The user clicks ont the Cave Entrance graphics indicated by the swirling purple animations
    - THEN: The displayed scene changes and puts the user in the first room of the cave 

2. **Navigating Through the Cave** :
    - SCENARIO: The user moves between cave rooms via the purple portals displayed at the cardinal directions in the room
    - GIVEN: The application has successfully launched and the user is in a cave room that has at least one purple portal displayed on a wall
    - WHEN: The user clicks on the purple portal
    - THEN: The displayed scene changes and puts the user in the cave room adjacent to their previous room in the direction of the respective direction chosen. 

3. **Collecting Ingredients in the Cave**
    - SCENARIO: The user adds an ingredient in a room to their inventory
    - GIVEN: The user is in a room in the cave that has at least one ingredient displayed on the floor
    - WHEN: The user clicks on one of the respective ingredient images
    - THEN: The displayed photo/gif of the ingredient disappears from the floor of that cave room
    - THEN: The name, type, and rarity of the ingredient is added to the player's inventory

4. **Gambling with Dinosaurs in the Cave**
    - SCENARIO: The user enters the 'combat' scene with a dinosaur in their room
    - GIVEN: The user is in a room in the cave that has at least one dinosaur displayed next to the user
    - WHEN: The user clicks on one of the dinosaurs displayed next to them
    - THEN: The user enters the 'combat' scene with the dinosaur where they can play dice
   
5. **Returning Home from the Cave**
    - SCENARIO: The user returns to the home screen from any given room in the cave
    - GIVEN: The user is in a room in the cave (any room but the home room with the cave entrance and baking house)
    - WHEN: The user clicks the 'Return Home' button in the top right corner of the displayed scene
    - THEN: The display changes from a cave room to the home room with the cave entrance and baking house
    - THEN: The patience meter on the bottom left of the screen should disappear
   
6. **Opening Inventory**
    - SCENARIO: The user opens and views the contents of their inventory from a cave room or the home room
    - GIVEN: The user does not have their inventory screen currently displayed, and has their player displayed in the center of the screen
    - GIVEN: The user is in any displayed scene in the game except actively baking in the baking house
    - WHEN: The user clicks the image of their avatar in the middle of the screen
    - THEN: The current scene displayed will change to a scene displaying hte contents of their inventory
    - THEN: The inventory will be shown as a list of all the ingredients the user has collected since the beginning of the game

7. **Baking Bread**
    - SCENARIO: The user opens and views the kitchen
    - GIVEN: The user has all the required baking ingredients
    - WHEN: The user selects all the ingredients they want to bake with
    - THEN: A pop-up will show up with the bread baked
    - THEN: Your honor will increase proportionately with the value of the bread you've baked

8. **Running Out of Patience**
    - SCENARIO: The user runs out of patience while either moving or gambling in the cave rooms, and are sent back to the home room
    - GIVEN: The user is in a cave room with their patience meter displayed in the bottom left corner of the screen
    - WHEN: The user's patience hits 0 (or some lower value) after attempting to move or losing at dice
    - THEN: A pop-up will inform the user that they have run out of patience
    - THEN: The displayed scene will change back to the home room scene after the user closes the pop-up