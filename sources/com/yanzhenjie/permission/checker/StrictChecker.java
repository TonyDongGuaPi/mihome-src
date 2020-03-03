package com.yanzhenjie.permission.checker;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.List;
import org.apache.commons.lang.CharUtils;

public final class StrictChecker implements PermissionChecker {
    public boolean a(@NonNull Context context, @NonNull String... strArr) {
        if (Build.VERSION.SDK_INT < 21) {
            return true;
        }
        for (String a2 : strArr) {
            if (!a(context, a2)) {
                return false;
            }
        }
        return true;
    }

    public boolean a(@NonNull Context context, @NonNull List<String> list) {
        if (Build.VERSION.SDK_INT < 21) {
            return true;
        }
        for (String a2 : list) {
            if (!a(context, a2)) {
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = 19)
    private boolean a(Context context, String str) {
        char c = 65535;
        try {
            switch (str.hashCode()) {
                case -2062386608:
                    if (str.equals("android.permission.READ_SMS")) {
                        c = 19;
                        break;
                    }
                    break;
                case -1928411001:
                    if (str.equals("android.permission.READ_CALENDAR")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1921431796:
                    if (str.equals("android.permission.READ_CALL_LOG")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1888586689:
                    if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1479758289:
                    if (str.equals("android.permission.RECEIVE_WAP_PUSH")) {
                        c = 20;
                        break;
                    }
                    break;
                case -1238066820:
                    if (str.equals("android.permission.BODY_SENSORS")) {
                        c = 16;
                        break;
                    }
                    break;
                case -895679497:
                    if (str.equals("android.permission.RECEIVE_MMS")) {
                        c = 18;
                        break;
                    }
                    break;
                case -895673731:
                    if (str.equals("android.permission.RECEIVE_SMS")) {
                        c = 21;
                        break;
                    }
                    break;
                case -406040016:
                    if (str.equals("android.permission.READ_EXTERNAL_STORAGE")) {
                        c = 22;
                        break;
                    }
                    break;
                case -63024214:
                    if (str.equals("android.permission.ACCESS_COARSE_LOCATION")) {
                        c = 6;
                        break;
                    }
                    break;
                case -5573545:
                    if (str.equals("android.permission.READ_PHONE_STATE")) {
                        c = 9;
                        break;
                    }
                    break;
                case 52602690:
                    if (str.equals("android.permission.SEND_SMS")) {
                        c = 17;
                        break;
                    }
                    break;
                case 112197485:
                    if (str.equals("android.permission.CALL_PHONE")) {
                        c = 10;
                        break;
                    }
                    break;
                case 214526995:
                    if (str.equals("android.permission.WRITE_CONTACTS")) {
                        c = 4;
                        break;
                    }
                    break;
                case 463403621:
                    if (str.equals("android.permission.CAMERA")) {
                        c = 2;
                        break;
                    }
                    break;
                case 603653886:
                    if (str.equals("android.permission.WRITE_CALENDAR")) {
                        c = 1;
                        break;
                    }
                    break;
                case 610633091:
                    if (str.equals("android.permission.WRITE_CALL_LOG")) {
                        c = 12;
                        break;
                    }
                    break;
                case 784519842:
                    if (str.equals("android.permission.USE_SIP")) {
                        c = 14;
                        break;
                    }
                    break;
                case 952819282:
                    if (str.equals("android.permission.PROCESS_OUTGOING_CALLS")) {
                        c = 15;
                        break;
                    }
                    break;
                case 1271781903:
                    if (str.equals("android.permission.GET_ACCOUNTS")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1365911975:
                    if (str.equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        c = 23;
                        break;
                    }
                    break;
                case 1831139720:
                    if (str.equals("android.permission.RECORD_AUDIO")) {
                        c = 8;
                        break;
                    }
                    break;
                case 1977429404:
                    if (str.equals("android.permission.READ_CONTACTS")) {
                        c = 3;
                        break;
                    }
                    break;
                case 2133799037:
                    if (str.equals("com.android.voicemail.permission.ADD_VOICEMAIL")) {
                        c = CharUtils.b;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return a(context);
                case 1:
                    return b(context);
                case 2:
                    return c(context);
                case 3:
                    return d(context);
                case 4:
                    return e(context);
                case 5:
                    return true;
                case 6:
                case 7:
                    return f(context);
                case 8:
                    return a();
                case 9:
                    return g(context);
                case 10:
                    return true;
                case 11:
                    return h(context);
                case 12:
                    return i(context);
                case 13:
                    return j(context);
                case 14:
                    return k(context);
                case 15:
                    return true;
                case 16:
                    return l(context);
                case 17:
                case 18:
                    return true;
                case 19:
                    return m(context);
                case 20:
                case 21:
                    return true;
                case 22:
                    return b();
                case 23:
                    return c();
                default:
                    return true;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a(Context context) throws Throwable {
        return new CalendarReadTest(context).a();
    }

    private static boolean b(Context context) throws Throwable {
        return new CalendarWriteTest(context).a();
    }

    private static boolean c(Context context) throws Throwable {
        return new CameraTest(context).a();
    }

    private static boolean d(Context context) throws Throwable {
        return new ContactsReadTest(context).a();
    }

    private static boolean e(Context context) throws Throwable {
        return new ContactsWriteTest(context.getContentResolver()).a();
    }

    private static boolean f(Context context) throws Throwable {
        return new LocationTest(context).a();
    }

    private static boolean a() throws Throwable {
        return new RecordAudioTest().a();
    }

    private static boolean g(Context context) throws Throwable {
        return new PhoneStateReadTest(context).a();
    }

    private static boolean h(Context context) throws Throwable {
        return new CallLogReadTest(context).a();
    }

    private static boolean i(Context context) throws Throwable {
        return new CallLogWriteTest(context).a();
    }

    private static boolean j(Context context) throws Throwable {
        return new AddVoicemailTest(context).a();
    }

    private static boolean k(Context context) throws Throwable {
        return new SipTest(context).a();
    }

    private static boolean l(Context context) throws Throwable {
        return new SensorsTest(context).a();
    }

    private static boolean m(Context context) throws Throwable {
        return new SmsReadTest(context).a();
    }

    private static boolean b() throws Throwable {
        return new StorageReadTest().a();
    }

    private static boolean c() throws Throwable {
        return new StorageWriteTest().a();
    }
}
