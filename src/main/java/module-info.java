module com.max1maka {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.max1maka to javafx.fxml;
    exports com.max1maka;
}