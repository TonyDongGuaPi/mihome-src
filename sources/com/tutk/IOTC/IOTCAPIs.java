package com.tutk.IOTC;

import java.io.PrintStream;

public class IOTCAPIs {
    public static final int API_ER_ANDROID_NULL = -10000;
    public static final int IOTC_ER_ABORTED = -52;
    public static final int IOTC_ER_AES_CERTIFY_FAIL = -29;
    public static final int IOTC_ER_ALREADY_INITIALIZED = -3;
    public static final int IOTC_ER_BLOCKED_CALL = -49;
    public static final int IOTC_ER_CAN_NOT_FIND_DEVICE = -19;
    public static final int IOTC_ER_CH_NOT_ON = -26;
    public static final int IOTC_ER_CLIENT_NOT_SECURE_MODE = -34;
    public static final int IOTC_ER_CLIENT_SECURE_MODE = -35;
    public static final int IOTC_ER_CONNECT_IS_CALLING = -20;
    public static final int IOTC_ER_DEVICE_EXCEED_MAX_SESSION = -48;
    public static final int IOTC_ER_DEVICE_MULTI_LOGIN = -45;
    public static final int IOTC_ER_DEVICE_NOT_LISTENING = -24;
    public static final int IOTC_ER_DEVICE_NOT_SECURE_MODE = -36;
    public static final int IOTC_ER_DEVICE_OFFLINE = -90;
    public static final int IOTC_ER_DEVICE_SECURE_MODE = -37;
    public static final int IOTC_ER_EXCEED_MAX_PACKET_SIZE = -53;
    public static final int IOTC_ER_EXCEED_MAX_SESSION = -18;
    public static final int IOTC_ER_EXIT_LISTEN = -39;
    public static final int IOTC_ER_FAIL_CONNECT_SEARCH = -27;
    public static final int IOTC_ER_FAIL_CREATE_MUTEX = -4;
    public static final int IOTC_ER_FAIL_CREATE_SOCKET = -6;
    public static final int IOTC_ER_FAIL_CREATE_THREAD = -5;
    public static final int IOTC_ER_FAIL_GET_LOCAL_IP = -16;
    public static final int IOTC_ER_FAIL_RESOLVE_HOSTNAME = -2;
    public static final int IOTC_ER_FAIL_SETUP_RELAY = -42;
    public static final int IOTC_ER_FAIL_SOCKET_BIND = -8;
    public static final int IOTC_ER_FAIL_SOCKET_OPT = -7;
    public static final int IOTC_ER_INVALID_ARG = -46;
    public static final int IOTC_ER_INVALID_MODE = -38;
    public static final int IOTC_ER_INVALID_SID = -14;
    public static final int IOTC_ER_LISTEN_ALREADY_CALLED = -17;
    public static final int IOTC_ER_LOGIN_ALREADY_CALLED = -11;
    public static final int IOTC_ER_MASTER_TOO_FEW = -28;
    public static final int IOTC_ER_NETWORK_UNREACHABLE = -41;
    public static final int IOTC_ER_NOT_ENOUGH_MEMORY = -58;
    public static final int IOTC_ER_NOT_INITIALIZED = -12;
    public static final int IOTC_ER_NOT_SUPPORT_PE = -47;
    public static final int IOTC_ER_NOT_SUPPORT_RELAY = -43;
    public static final int IOTC_ER_NO_PATH_TO_WRITE_DATA = -55;
    public static final int IOTC_ER_NO_PERMISSION = -40;
    public static final int IOTC_ER_NO_SERVER_LIST = -44;
    public static final int IOTC_ER_NoERROR = 0;
    public static final int IOTC_ER_REMOTE_NOT_SUPPORTED = -51;
    public static final int IOTC_ER_REMOTE_TIMEOUT_DISCONNECT = -23;
    public static final int IOTC_ER_SERVER_NOT_RESPONSE = -1;
    public static final int IOTC_ER_SERVER_NOT_SUPPORT = -54;
    public static final int IOTC_ER_SERVICE_IS_NOT_STARTED = -56;
    public static final int IOTC_ER_SESSION_CLOSED = -50;
    public static final int IOTC_ER_SESSION_CLOSE_BY_REMOTE = -22;
    public static final int IOTC_ER_SESSION_NO_FREE_CHANNEL = -31;
    public static final int IOTC_ER_STILL_IN_PROCESSING = -57;
    public static final int IOTC_ER_TCP_CONNECT_TO_SERVER_FAILED = -33;
    public static final int IOTC_ER_TCP_TRAVEL_FAILED = -32;
    public static final int IOTC_ER_TIMEOUT = -13;
    public static final int IOTC_ER_UNKNOWN_DEVICE = -15;
    public static final int IOTC_ER_UNLICENSE = -10;

    public static native int IOTC_Check_Device_On_Line(String str, int i, byte[] bArr, Object obj);

    public static native void IOTC_ConnModeChange_CallBack(Object obj);

    public static native int IOTC_Connect_ByUID(String str);

    public static native int IOTC_Connect_ByUID2(String str, String str2, int i);

    public static native int IOTC_Connect_ByUID_Parallel(String str, int i);

    public static native void IOTC_Connect_Stop();

    public static native int IOTC_Connect_Stop_BySID(int i);

    public static native int IOTC_DeInitialize();

    public static native int IOTC_Device_Login(String str, String str2, String str3);

    public static native int IOTC_Get_Login_Info(int[] iArr);

    public static native void IOTC_Get_Login_Info_ByCallBackFn(Object obj);

    public static native int IOTC_Get_Nat_Type();

    public static native int IOTC_Get_SessionID();

    public static native void IOTC_Get_Version(int[] iArr);

    public static native int IOTC_Initialize(int i, String str, String str2, String str3, String str4);

    public static native int IOTC_Initialize2(int i);

    public static native int IOTC_IsDeviceSecureMode(int i);

    public static native st_LanSearchInfo[] IOTC_Lan_Search(int[] iArr, int i);

    public static native st_LanSearchInfo2[] IOTC_Lan_Search2(int[] iArr, int i);

    public static native st_LanSearchInfo2[] IOTC_Lan_Search2_Ex(int[] iArr, int i, int i2);

    public static native int IOTC_Listen(int i);

    public static native int IOTC_Listen2(int i, String str, int i2);

    public static native void IOTC_Listen_Exit();

    public static native int IOTC_ReInitSocket(int i);

    public static native st_SearchDeviceInfo[] IOTC_Search_Device_Result(int[] iArr, int i);

    public static native int IOTC_Search_Device_Start(int i, int i2);

    public static native int IOTC_Search_Device_Stop();

    public static native int IOTC_Session_Channel_OFF(int i, int i2);

    public static native int IOTC_Session_Channel_ON(int i, int i2);

    public static native int IOTC_Session_Check(int i, St_SInfo st_SInfo);

    public static native int IOTC_Session_Check_ByCallBackFn(int i, Object obj);

    public static native int IOTC_Session_Check_Ex(int i, St_SInfoEx st_SInfoEx);

    public static native void IOTC_Session_Close(int i);

    public static native int IOTC_Session_Get_Free_Channel(int i);

    public static native int IOTC_Session_Read(int i, byte[] bArr, int i2, int i3, int i4);

    public static native int IOTC_Session_Read_Check_Lost(int i, byte[] bArr, int i2, int i3, int[] iArr, int[] iArr2, int i4);

    public static native int IOTC_Session_Write(int i, byte[] bArr, int i2, int i3);

    public static native int IOTC_Session_Write_Reliable(int i, byte[] bArr, int i2, int i3, int i4);

    public static native int IOTC_Session_Write_Reliable_Abort(int i, int i2);

    public static native void IOTC_Set_Device_Name(String str);

    public static native void IOTC_Set_Log_Path(String str, int i);

    public static native void IOTC_Set_Max_Session_Number(int i);

    public static native int IOTC_Set_Partial_Encryption(int i, int i2);

    public static native void IOTC_Setup_DetectNetwork_Timeout(int i);

    public static native int IOTC_Setup_Device_Search_VirtualPort(int i);

    public static native void IOTC_Setup_ErrorUpload(int i);

    public static native void IOTC_Setup_LANConnection_Timeout(int i);

    public static native void IOTC_Setup_P2PConnection_Timeout(int i);

    public static native void IOTC_Setup_Session_Alive_Timeout(int i);

    public static native void IOTC_TCPRelayOnly_TurnOn();

    public static native int IOTC_WakeUp_WakeDevice(String str);

    static {
        System.loadLibrary("IOTCAPIs");
    }

    public void onLineResultCB(int i, byte[] bArr) {
        PrintStream printStream = System.out;
        printStream.println("[parent] onLineResult Callback, result=" + i);
    }

    public void loginInfoCB(int i) {
        PrintStream printStream = System.out;
        printStream.println("[parent] LoginInfo Callback, nLogInfo=" + i);
    }

    public void sessionStatusCB(int i, int i2) {
        PrintStream printStream = System.out;
        printStream.println("[parent] SessionStatus Callback, nSID=" + i + ", nErrCode=" + i2);
    }

    public void ConnectModeChangeCB(int i, int i2) {
        PrintStream printStream = System.out;
        printStream.println("[parent] ConnectModeChangeCB Callback, nSID = " + i + ", nConnMode = " + i2);
    }
}
