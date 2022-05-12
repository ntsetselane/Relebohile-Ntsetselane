module com.example.assinmentq {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.assinmentq to javafx.fxml;
    exports com.example.assinmentq;
}