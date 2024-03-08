module com.vika.doorcontrol {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires com.sun.jna;
    opens com.vika.doorcontrol to javafx.fxml;
    exports com.vika.doorcontrol;
}
