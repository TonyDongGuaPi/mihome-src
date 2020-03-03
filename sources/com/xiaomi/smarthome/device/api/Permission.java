package com.xiaomi.smarthome.device.api;

public class Permission {
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    private static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
    private static final String BODY_SENSORS = "android.permission.BODY_SENSORS";
    @Deprecated
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";
    public static final String CAMERA = "android.permission.CAMERA";
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    private static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
    private static final String READ_CALENDAR = "android.permission.READ_CALENDAR";
    private static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    private static final String READ_SMS = "android.permission.READ_SMS";
    private static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
    @Deprecated
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    private static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    @Deprecated
    public static final String SEND_SMS = "android.permission.SEND_SMS";
    private static final String USE_SIP = "android.permission.USE_SIP";
    private static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
    private static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    private static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

    public static final class Group {
        public static final String[] CAMERA = {"android.permission.CAMERA"};
        public static final String[] CONTACTS = {"android.permission.READ_CONTACTS", "android.permission.GET_ACCOUNTS"};
        public static final String[] LOCATION = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
        public static final String[] MICROPHONE = {"android.permission.RECORD_AUDIO"};
        public static final String[] PHONE = {"android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE"};
        @Deprecated
        public static final String[] SMS = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS"};
        public static final String[] STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }
}
