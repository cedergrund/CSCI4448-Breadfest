module org.example.breadfest {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.breadfest to javafx.fxml;
    exports org.example.breadfest;
}