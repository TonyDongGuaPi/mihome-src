package com.tutk.IOTC;

public class RDTAPIs {
    public static final int API_ER_ANDROID_NULL = -10000;
    public static final int RDT_ER_ALREADY_INITIALIZED = -10001;
    public static final int RDT_ER_CHANNEL_OCCUPIED = -10012;
    public static final int RDT_ER_EXCEED_MAX_CHANNEL = -10002;
    public static final int RDT_ER_FAIL_CREATE_MUTEX = -10005;
    public static final int RDT_ER_FAIL_CREATE_THREAD = -10004;
    public static final int RDT_ER_INVALID_ARG = -10014;
    public static final int RDT_ER_INVALID_RDT_ID = -10008;
    public static final int RDT_ER_LOCAL_ABORT = -10011;
    public static final int RDT_ER_LOCAL_EXIT = -10015;
    public static final int RDT_ER_MEM_INSUFF = -10003;
    public static final int RDT_ER_NOT_INITIALIZED = -10000;
    public static final int RDT_ER_NO_PERMISSION = -10013;
    public static final int RDT_ER_NoERROR = 0;
    public static final int RDT_ER_RCV_DATA_END = -10009;
    public static final int RDT_ER_RDT_DESTROYED = -10006;
    public static final int RDT_ER_REMOTE_ABORT = -10010;
    public static final int RDT_ER_REMOTE_EXIT = -10016;
    public static final int RDT_ER_TIMEOUT = -10007;
    public static int ms_verRDTApis = 0;

    public static native int RDT_Abort(int i);

    public static native int RDT_Create(int i, int i2, int i3);

    public static native int RDT_Create_Exit(int i, int i2);

    public static native int RDT_DeInitialize();

    public static native int RDT_Destroy(int i);

    public static native int RDT_GetRDTApiVer();

    public static native int RDT_Initialize();

    public static native int RDT_Read(int i, byte[] bArr, int i2, int i3);

    public static native void RDT_Set_Log_Path(String str, int i);

    public static native void RDT_Set_Max_Channel_Number(int i);

    public static native int RDT_Set_Max_Pending_ACK_Number(int i, int i2);

    public static native int RDT_Set_Max_SendBuffer_Size(int i, int i2);

    public static native int RDT_Status_Check(int i, St_RDT_Status st_RDT_Status);

    public static native int RDT_Write(int i, byte[] bArr, int i2);

    static {
        System.loadLibrary("RDTAPIs");
    }
}
