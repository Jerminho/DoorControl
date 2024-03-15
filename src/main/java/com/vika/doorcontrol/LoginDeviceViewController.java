/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.vika.doorcontrol;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
 

/**
 *
 * @author pierr
 */
public class LoginDeviceViewController {
    
    
   public static int m_UserID = 1;
    
     @FXML
    private TextField deviceAddress;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField port;
    
   @FXML
    private void handleLogin() {
        String deviceAddress = this.deviceAddress.getText();
        String userName = this.userName.getText();
        String password = this.password.getText();
        String port = this.port.getText();

        if (deviceAddress.isEmpty() || userName.isEmpty() || password.isEmpty()) {
            displayMessage("Device address, username, and password are required.", "Validation Error", AlertType.ERROR);
            return;
        }
        
        
        // Prepare the login info
        HCNetSDK.NET_DVR_USER_LOGIN_INFO loginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();
        HCNetSDK.NET_DVR_DEVICEINFO_V40 deviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();
        deviceInfo.struDeviceV30.sSerialNumber = new byte[HCNetSDK.SERIALNO_LEN];
        loginInfo.sDeviceAddress = deviceAddress;
        loginInfo.sUserName = userName;
        loginInfo.sPassword = password;
        loginInfo.wPort = Short.parseShort(port);
        
        int lUserID = 1;
        lUserID = HCNetSDK.INSTANCE.NET_DVR_Login_V40(loginInfo, deviceInfo);
        if (lUserID < 0) {
            int errorCode = HCNetSDK.INSTANCE.NET_DVR_GetLastError();

            switch (errorCode) {
                case HCNetSDK.NET_DVR_PASSWORD_ERROR:
                    displayMessage("User name or password error!", "Error", AlertType.ERROR);
                    if (deviceInfo.bySupportLock == 1) {
                        displayMessage("Left " + deviceInfo.byRetryLoginTime + " try opportunity", "Warning", AlertType.WARNING);
                    }   break;
                case HCNetSDK.NET_DVR_USER_LOCKED:
                    if (deviceInfo.bySupportLock == 1) {
                        displayMessage("User is locked, the remaining lock time is " + deviceInfo.dwSurplusLockTime, "Warning", AlertType.WARNING);
                    }   break;
                default:
                    displayMessage("Net error or DVR is busy!", "Error", AlertType.ERROR);
                    break;
            }
        } else {
            // Login successful
            displayMessage("Login Successful", "Success", AlertType.INFORMATION);
            // Perform further actions upon successful login
            
        }
        
    }

    private void displayMessage(String message, String title, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void OpenDoor()throws IOException{
        
       if (HCNetSDK.INSTANCE.NET_DVR_ControlGateway(m_UserID, 1, 1)) {
           System.out.print("NET_DVR_ControleGateway: Open door succeed");
        }
        else{
            System.out.print("NET_DVR_ControleGateway: Open door failed error: " + HCNetSDK.INSTANCE.NET_DVR_GetLastError());
        }
    }
    
    @FXML
    public void CloseDoor()throws IOException{
       
       
       System.out.print("NET_DVR_ControleGateway: Close door succeed");
    }
    
        @FXML
    public void StayOpen()throws IOException{
        if (HCNetSDK.INSTANCE.NET_DVR_ControlGateway(m_UserID, 1, 2)) {
            System.out.print("NET_DVR_ControleGateway: Stay Open door succeed");
        }
        else{
            System.out.print("NET_DVR_ControleGateway: Stay Open door failed error: " + HCNetSDK.INSTANCE.NET_DVR_GetLastError());
        }
    }
    
    @FXML
    public void StayClosed() throws IOException{
       if (HCNetSDK.INSTANCE.NET_DVR_ControlGateway(m_UserID, 1, 3)) {
            System.out.print("NET_DVR_ControleGateway: Stay Closed door succeed");
        }
        else{
            System.out.print("NET_DVR_ControleGateway: Stay Closed failed error: " + HCNetSDK.INSTANCE.NET_DVR_GetLastError());
        }
    }
}
   
   
