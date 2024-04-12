module org.example.breadfest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires kotlin.stdlib;
    requires java.compiler;


    opens org.example.breadfest to javafx.fxml;
    exports org.example.breadfest;
    exports org.example.breadfest.dice;
    opens org.example.breadfest.dice to javafx.fxml;
    exports org.example.breadfest.ingredients;
    opens org.example.breadfest.ingredients to javafx.fxml;
    exports org.example.breadfest.dinosaurs;
    opens org.example.breadfest.dinosaurs to javafx.fxml;
    exports org.example.breadfest.dice.Common;
    opens org.example.breadfest.dice.Common to javafx.fxml;
    exports org.example.breadfest.dice.Rare;
    opens org.example.breadfest.dice.Rare to javafx.fxml;
    exports org.example.breadfest.dice.Epic;
    opens org.example.breadfest.dice.Epic to javafx.fxml;
    exports org.example.breadfest.FXML_functions;
    opens org.example.breadfest.FXML_functions to javafx.fxml;
}