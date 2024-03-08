package com.vika.doorcontrol;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        // Attempt SDK
        if(HCNetSDK.INSTANCE.NET_DVR_Init()){
        System.out.println("NET_DVR_Init Succes!");
        
        App.setRoot("secondary");
        }else{
        System.out.println("NET_DVR_Init Error!");
        }
    }
}
