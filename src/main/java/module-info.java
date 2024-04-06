module org.example.breadfest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens org.example.breadfest to javafx.fxml;
    exports org.example.breadfest;
}