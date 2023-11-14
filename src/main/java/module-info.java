module ensisa.birds {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens ensisa.birds to javafx.fxml;
    exports ensisa.birds;
}