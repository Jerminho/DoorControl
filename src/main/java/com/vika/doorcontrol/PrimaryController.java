package com.vika.doorcontrol;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToLoginScreen() throws IOException {
       App.setRoot("loginScreen");
    }
}