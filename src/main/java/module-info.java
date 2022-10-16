module com.example.pizzadatabse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pizzadatabse to javafx.fxml;
    exports com.example.pizzadatabse;
    exports com.example.pizzadatabse.Menu.MenuItems;
    opens com.example.pizzadatabse.Menu.MenuItems to javafx.fxml;
}