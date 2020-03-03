package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import com.taobao.weex.ui.component.AbstractEditComponent;

public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return (telephonyManager == null || telephonyManager.getPhoneType() == 0) ? false : true;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String b() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getDeviceId() : "";
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String c() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return Build.VERSION.SDK_INT >= 26 ? telephonyManager != null ? telephonyManager.getImei() : "" : telephonyManager != null ? telephonyManager.getDeviceId() : "";
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String d() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return Build.VERSION.SDK_INT >= 26 ? telephonyManager != null ? telephonyManager.getMeid() : "" : telephonyManager != null ? telephonyManager.getDeviceId() : "";
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String e() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getSubscriberId() : "";
    }

    public static int f() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getPhoneType();
        }
        return -1;
    }

    public static boolean g() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return telephonyManager != null && telephonyManager.getSimState() == 5;
    }

    public static String h() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getSimOperatorName() : "";
    }

    public static String i() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        String simOperator = telephonyManager != null ? telephonyManager.getSimOperator() : null;
        if (simOperator == null) {
            return null;
        }
        char c = 65535;
        int hashCode = simOperator.hashCode();
        if (hashCode != 49679477) {
            switch (hashCode) {
                case 49679470:
                    if (simOperator.equals("46000")) {
                        c = 0;
                        break;
                    }
                    break;
                case 49679471:
                    if (simOperator.equals("46001")) {
                        c = 3;
                        break;
                    }
                    break;
                case 49679472:
                    if (simOperator.equals("46002")) {
                        c = 1;
                        break;
                    }
                    break;
                case 49679473:
                    if (simOperator.equals("46003")) {
                        c = 4;
                        break;
                    }
                    break;
            }
        } else if (simOperator.equals("46007")) {
            c = 2;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return "中国移动";
            case 3:
                return "中国联通";
            case 4:
                return "中国电信";
            default:
                return simOperator;
        }
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public static String j() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return (((((((((((((("" + "DeviceId(IMEI) = " + telephonyManager.getDeviceId() + "\n") + "DeviceSoftwareVersion = " + telephonyManager.getDeviceSoftwareVersion() + "\n") + "Line1Number = " + telephonyManager.getLine1Number() + "\n") + "NetworkCountryIso = " + telephonyManager.getNetworkCountryIso() + "\n") + "NetworkOperator = " + telephonyManager.getNetworkOperator() + "\n") + "NetworkOperatorName = " + telephonyManager.getNetworkOperatorName() + "\n") + "NetworkType = " + telephonyManager.getNetworkType() + "\n") + "PhoneType = " + telephonyManager.getPhoneType() + "\n") + "SimCountryIso = " + telephonyManager.getSimCountryIso() + "\n") + "SimOperator = " + telephonyManager.getSimOperator() + "\n") + "SimOperatorName = " + telephonyManager.getSimOperatorName() + "\n") + "SimSerialNumber = " + telephonyManager.getSimSerialNumber() + "\n") + "SimState = " + telephonyManager.getSimState() + "\n") + "SubscriberId(IMSI) = " + telephonyManager.getSubscriberId() + "\n") + "VoiceMailNumber = " + telephonyManager.getVoiceMailNumber() + "\n";
    }

    public static void a(String str) {
        Utils.a().startActivity(IntentUtils.f(str, true));
    }

    @RequiresPermission("android.permission.CALL_PHONE")
    public static void b(String str) {
        Utils.a().startActivity(IntentUtils.g(str, true));
    }

    public static void a(String str, String str2) {
        Utils.a().startActivity(IntentUtils.d(str, str2, true));
    }

    @RequiresPermission("android.permission.SEND_SMS")
    public static void b(String str, String str2) {
        if (!StringUtils.a((CharSequence) str2)) {
            PendingIntent broadcast = PendingIntent.getBroadcast(Utils.a(), 0, new Intent(AbstractEditComponent.ReturnTypes.SEND), 0);
            SmsManager smsManager = SmsManager.getDefault();
            if (str2.length() >= 70) {
                for (String sendTextMessage : smsManager.divideMessage(str2)) {
                    smsManager.sendTextMessage(str, (String) null, sendTextMessage, broadcast, (PendingIntent) null);
                }
                return;
            }
            smsManager.sendTextMessage(str, (String) null, str2, broadcast, (PendingIntent) null);
        }
    }
}
