/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vika.doorcontrol;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.vika.doorcontrol.HCNetSDK.NET_DVR_DEVICEINFO_V30;

/**
 *
 * @author pierr
 */
public interface LoginResultCallBack extends Callback {
        void invoke(int lUserID, int dwResult, NET_DVR_DEVICEINFO_V30 lpDeviceInfo, Pointer pUser);

}
