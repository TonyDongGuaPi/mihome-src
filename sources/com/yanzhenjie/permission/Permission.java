package com.yanzhenjie.permission;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.CharUtils;

public final class Permission {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2403a = "android.permission.READ_CALENDAR";
    public static final String b = "android.permission.WRITE_CALENDAR";
    public static final String c = "android.permission.CAMERA";
    public static final String d = "android.permission.READ_CONTACTS";
    public static final String e = "android.permission.WRITE_CONTACTS";
    public static final String f = "android.permission.GET_ACCOUNTS";
    public static final String g = "android.permission.ACCESS_FINE_LOCATION";
    public static final String h = "android.permission.ACCESS_COARSE_LOCATION";
    public static final String i = "android.permission.RECORD_AUDIO";
    public static final String j = "android.permission.READ_PHONE_STATE";
    @Deprecated
    public static final String k = "android.permission.CALL_PHONE";
    public static final String l = "android.permission.READ_CALL_LOG";
    public static final String m = "android.permission.WRITE_CALL_LOG";
    public static final String n = "com.android.voicemail.permission.ADD_VOICEMAIL";
    public static final String o = "android.permission.USE_SIP";
    public static final String p = "android.permission.PROCESS_OUTGOING_CALLS";
    public static final String q = "android.permission.BODY_SENSORS";
    @Deprecated
    public static final String r = "android.permission.SEND_SMS";
    @Deprecated
    public static final String s = "android.permission.RECEIVE_SMS";
    public static final String t = "android.permission.READ_SMS";
    public static final String u = "android.permission.RECEIVE_WAP_PUSH";
    public static final String v = "android.permission.RECEIVE_MMS";
    public static final String w = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String x = "android.permission.WRITE_EXTERNAL_STORAGE";

    public static final class Group {

        /* renamed from: a  reason: collision with root package name */
        public static final String[] f2404a = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
        public static final String[] b = {"android.permission.CAMERA"};
        public static final String[] c = {"android.permission.READ_CONTACTS", "android.permission.GET_ACCOUNTS"};
        public static final String[] d = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
        public static final String[] e = {"android.permission.RECORD_AUDIO"};
        public static final String[] f = {"android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE"};
        public static final String[] g = {"android.permission.BODY_SENSORS"};
        @Deprecated
        public static final String[] h = {"android.permission.SEND_SMS", "android.permission.RECEIVE_SMS"};
        public static final String[] i = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        public static final String[] j = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.READ_PHONE_STATE"};
        public static final String[] k = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};
    }

    public static List<String> a(Context context, String... strArr) {
        return a(context, (List<String>) Arrays.asList(strArr));
    }

    public static List<String> a(Context context, String[]... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String[] asList : strArr) {
            arrayList.addAll(Arrays.asList(asList));
        }
        return a(context, (List<String>) arrayList);
    }

    public static List<String> a(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String next : list) {
            char c2 = 65535;
            switch (next.hashCode()) {
                case -2062386608:
                    if (next.equals("android.permission.READ_SMS")) {
                        c2 = 18;
                        break;
                    }
                    break;
                case -1928411001:
                    if (next.equals("android.permission.READ_CALENDAR")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1921431796:
                    if (next.equals("android.permission.READ_CALL_LOG")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case -1888586689:
                    if (next.equals("android.permission.ACCESS_FINE_LOCATION")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -1479758289:
                    if (next.equals("android.permission.RECEIVE_WAP_PUSH")) {
                        c2 = 19;
                        break;
                    }
                    break;
                case -1238066820:
                    if (next.equals("android.permission.BODY_SENSORS")) {
                        c2 = 15;
                        break;
                    }
                    break;
                case -895679497:
                    if (next.equals("android.permission.RECEIVE_MMS")) {
                        c2 = 20;
                        break;
                    }
                    break;
                case -895673731:
                    if (next.equals("android.permission.RECEIVE_SMS")) {
                        c2 = 17;
                        break;
                    }
                    break;
                case -406040016:
                    if (next.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                        c2 = 21;
                        break;
                    }
                    break;
                case -63024214:
                    if (next.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case -5573545:
                    if (next.equals("android.permission.READ_PHONE_STATE")) {
                        c2 = 9;
                        break;
                    }
                    break;
                case 52602690:
                    if (next.equals("android.permission.SEND_SMS")) {
                        c2 = 16;
                        break;
                    }
                    break;
                case 112197485:
                    if (next.equals("android.permission.CALL_PHONE")) {
                        c2 = 10;
                        break;
                    }
                    break;
                case 214526995:
                    if (next.equals("android.permission.WRITE_CONTACTS")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 463403621:
                    if (next.equals("android.permission.CAMERA")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 603653886:
                    if (next.equals("android.permission.WRITE_CALENDAR")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 610633091:
                    if (next.equals("android.permission.WRITE_CALL_LOG")) {
                        c2 = 12;
                        break;
                    }
                    break;
                case 784519842:
                    if (next.equals("android.permission.USE_SIP")) {
                        c2 = CharUtils.b;
                        break;
                    }
                    break;
                case 952819282:
                    if (next.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
                        c2 = 14;
                        break;
                    }
                    break;
                case 1271781903:
                    if (next.equals("android.permission.GET_ACCOUNTS")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 1365911975:
                    if (next.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        c2 = 22;
                        break;
                    }
                    break;
                case 1831139720:
                    if (next.equals("android.permission.RECORD_AUDIO")) {
                        c2 = 8;
                        break;
                    }
                    break;
                case 1977429404:
                    if (next.equals("android.permission.READ_CONTACTS")) {
                        c2 = 3;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                case 1:
                    String string = context.getString(R.string.permission_name_calendar);
                    if (arrayList.contains(string)) {
                        break;
                    } else {
                        arrayList.add(string);
                        break;
                    }
                case 2:
                    String string2 = context.getString(R.string.permission_name_camera);
                    if (arrayList.contains(string2)) {
                        break;
                    } else {
                        arrayList.add(string2);
                        break;
                    }
                case 3:
                case 4:
                    String string3 = context.getString(R.string.permission_name_contacts);
                    if (arrayList.contains(string3)) {
                        break;
                    } else {
                        arrayList.add(string3);
                        break;
                    }
                case 5:
                    String string4 = context.getString(R.string.permission_name_get_accounts);
                    if (arrayList.contains(string4)) {
                        break;
                    } else {
                        arrayList.add(string4);
                        break;
                    }
                case 6:
                case 7:
                    String string5 = context.getString(R.string.permission_name_location);
                    if (arrayList.contains(string5)) {
                        break;
                    } else {
                        arrayList.add(string5);
                        break;
                    }
                case 8:
                    String string6 = context.getString(R.string.permission_name_microphone);
                    if (arrayList.contains(string6)) {
                        break;
                    } else {
                        arrayList.add(string6);
                        break;
                    }
                case 9:
                    String string7 = context.getString(R.string.permission_name_phone_state);
                    if (arrayList.contains(string7)) {
                        break;
                    } else {
                        arrayList.add(string7);
                        break;
                    }
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                    String string8 = context.getString(R.string.permission_name_phone);
                    if (arrayList.contains(string8)) {
                        break;
                    } else {
                        arrayList.add(string8);
                        break;
                    }
                case 15:
                    String string9 = context.getString(R.string.permission_name_sensors);
                    if (arrayList.contains(string9)) {
                        break;
                    } else {
                        arrayList.add(string9);
                        break;
                    }
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    String string10 = context.getString(R.string.permission_name_sms);
                    if (arrayList.contains(string10)) {
                        break;
                    } else {
                        arrayList.add(string10);
                        break;
                    }
                case 21:
                case 22:
                    String string11 = context.getString(R.string.permission_name_storage);
                    if (arrayList.contains(string11)) {
                        break;
                    } else {
                        arrayList.add(string11);
                        break;
                    }
            }
        }
        return arrayList;
    }
}
