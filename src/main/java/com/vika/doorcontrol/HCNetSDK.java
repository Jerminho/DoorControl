/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.vika.doorcontrol;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author pierr
 */
public interface HCNetSDK extends Library{
    HCNetSDK INSTANCE = (HCNetSDK) Native.load("HCNetSDK",HCNetSDK.class);
    
    // Define the native methods
    boolean NET_DVR_Init();
    
    /* remote control gateway

    * [in] lUserID - NET_DVR_Login_V40 return value

    * [in] lGatewayIndex - 1-begin 0xffffffff-all

    * [in] dwStaic - : 0-close，1-open，2-always open，3-always close

    */
    
    boolean NET_DVR_ControlGateway(int lUserID, int lGatewayIndex, int dwStaic);
    
    public class NET_DVR_DEVICEINFO_V30 extends Structure {
    public static final int SERIALNO_LEN = 48;

    public byte[] sSerialNumber = new byte[SERIALNO_LEN]; //serial number
    public byte byAlarmInPortNum;   //Number of Alarm input
    public byte byAlarmOutPortNum;  //Number of Alarm Output
    public byte byDiskNum;  //Number of Hard Disk
    public byte byDVRType;  //DVR Type, 1: DVR 2: ATM DVR 3: DVS ......
    public byte byChanNum;  //Number of Analog Channel
    public byte byStartChan;    //The first Channel No. E.g. DVS- 1, DVR- 1
    public byte byAudioChanNum; //Number of Audio Channel
    public byte byIPChanNum;    //Maximum number of IP Channel low
    public byte byZeroChanNum;  //Zero channel encoding number//2010- 01- 16
    public byte byMainProto;    //Main stream transmission protocol 0- private,  1- rtsp,2-both private and rtsp
    public byte bySubProto; //Sub stream transmission protocol 0- private,  1- rtsp,2-both private and rtsp
    public byte bySupport;  //Ability, the 'AND' result by bit: 0- not support;  1- support

    // Abilities
    public byte bySupport1; //Ability expand, the 'AND' result by bit: 0- not support;  1- support
    public byte bySupport2; //Ability expand, the 'AND' result by bit: 0- not support;  1- support
    public short wDevType; //device type
    public byte bySupport3; //Support  epresent by bit, 0 - not support 1 - support
    public byte byMultiStreamProto; //support multi stream, represent by bit, 0-not support ;1- support; bit1-stream 3 ;bit2-stream 4, bit7-main stream, bit8-sub stream
    public byte byStartDChan;   //Start digital channel
    public byte byStartDTalkChan;   //Start digital talk channel
    public byte byHighDChanNum; //Digital channel number high
    public byte bySupport4; //Support  epresent by bit, 0 - not support 1 - support
    public byte byLanguageType; //support language type by bit,0-support,1-not support
    public byte byVoiceInChanNum;   //voice in chan num
    public byte byStartVoiceInChanNo;   //start voice in chan num
    public byte bySupport5;  //0-no support,1-support,bit0-muti stream
    public byte bySupport6;
    public byte byMirrorChanNum;    //mirror channel num,<it represents direct channel in the recording host
    public short wStartMirrorChanNo;   //start mirror chan
    public byte bySupport7;        //Support  epresent by bit, 0 - not support 1 - support

    public byte byRes2;

    public NET_DVR_DEVICEINFO_V30() {
        super((Pointer)null);
        setAlignType(ALIGN_NONE);
    }

    public NET_DVR_DEVICEINFO_V30(Pointer p) {
        super(p);
        read();
    }
    
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(
            "sSerialNumber", 
            "byAlarmInPortNum", 
            "byAlarmOutPortNum", 
            "byDiskNum", 
            "byDVRType", 
            "byChanNum", 
            "byStartChan", 
            "byAudioChanNum", 
            "byIPChanNum", 
            "byZeroChanNum", 
            "byMainProto", 
            "bySubProto", 
            "bySupport", 
            "bySupport1", 
            "bySupport2", 
            "wDevType", 
            "bySupport3", 
            "byMultiStreamProto", 
            "byStartDChan", 
            "byStartDTalkChan", 
            "byHighDChanNum", 
            "bySupport4", 
            "byLanguageType", 
            "byVoiceInChanNum", 
            "byStartVoiceInChanNo", 
            "bySupport5", 
            "bySupport6", 
            "byMirrorChanNum", 
            "wStartMirrorChanNo", 
            "bySupport7", 
            "byRes2"
        );
    }

    public static class ByReference extends NET_DVR_DEVICEINFO_V30 implements Structure.ByReference {
    }

    public static class ByValue extends NET_DVR_DEVICEINFO_V30 implements Structure.ByValue {
    }
}
    
    public class NET_DVR_DEVICEINFO_V40 extends Structure {
    public NET_DVR_DEVICEINFO_V30 struDeviceV30;
    public byte bySupportLock;
    public byte byRetryLoginTime;
    public byte byPasswordLevel;
    public byte byProxyType;
    public int dwSurplusLockTime;
    public byte byCharEncodeType;
    public byte byRes1;
    public byte bySupport;
    public byte byRes;
    public int dwOEMCode;
    public byte bySupportDev5;
    public byte byLoginMode;
    public byte[] byRes2 = new byte[246]; // Size 246, you might need to adjust the size based on the actual struct
    
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(
            "struDeviceV30",
            "bySupportLock",
            "byRetryLoginTime",
            "byPasswordLevel",
            "byProxyType",
            "dwSurplusLockTime",
            "byCharEncodeType",
            "byRes1",
            "bySupport",
            "byRes",
            "dwOEMCode",
            "bySupportDev5",
            "byLoginMode",
            "byRes2"
        );
    }
}
    
public class NET_DVR_USER_LOGIN_INFO extends Structure {
    public String sDeviceAddress;
    public byte byUseTransport;
    public short wPort;
    public String sUserName;
    public String sPassword;
    public LoginResultCallBack cbLoginResult;
    public PointerByReference pUser;
    public boolean bUseAsynLogin;
    public byte byProxyType;
    public byte byUseUTCTime;
    public byte byLoginMode;
    public byte byHttps;
    public int iProxyID;
    public byte[] byRes3 = new byte[120]; // Size 120, you might need to adjust the size based on the actual struct
    
    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(
            "sDeviceAddress",
            "byUseTransport",
            "wPort",
            "sUserName",
            "sPassword",
            "cbLoginResult",
            "pUser",
            "bUseAsynLogin",
            "byProxyType",
            "byUseUTCTime",
            "byLoginMode",
            "byHttps",
            "iProxyID",
            "byRes3"
        );
    }
}    
    
    int NET_DVR_Login_V40(NET_DVR_USER_LOGIN_INFO pLoginInfo, NET_DVR_DEVICEINFO_V40 lpDeviceInfo);
    
    int NET_DVR_GetLastError();
    int SERIALNO_LEN = 48;
    int NET_DVR_PASSWORD_ERROR = 1; // Username or Password error
    int NET_DVR_USER_LOCKED = 153; // User is locked
}
