package com.alipay.mobile.security.bio.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.BufferedReader;
import java.io.FileReader;

public class NetworkUtil {
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
            for (NetworkInfo state : allNetworkInfo) {
                if (state.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getNetworkType(Context context) {
        String str;
        String str2 = "";
        if (context == null) {
            return str2;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return str2;
            }
            String lowerCase = activeNetworkInfo.getTypeName().toLowerCase();
            try {
                if ("wifi".equals(lowerCase)) {
                    return "wifi";
                }
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 11:
                        return "2g";
                    case 3:
                        str = "3g";
                        break;
                    case 5:
                        str = "3g";
                        break;
                    case 6:
                        str = "3g";
                        break;
                    case 7:
                        str = "3g";
                        break;
                    case 8:
                        str = "3g";
                        break;
                    case 9:
                        str = "3g";
                        break;
                    case 10:
                        str = "3g";
                        break;
                    case 12:
                        str = "3g";
                        break;
                    case 13:
                        str = "4g";
                        break;
                    case 14:
                        str = "3g";
                        break;
                    case 15:
                        str = "3g";
                        break;
                    default:
                        return "2g";
                }
                return str;
            } catch (Exception e) {
                e = e;
                str2 = lowerCase;
                BioLog.e("NetworkTypeUtil" + e.toString());
                return str2;
            }
        } catch (Exception e2) {
            e = e2;
            BioLog.e("NetworkTypeUtil" + e.toString());
            return str2;
        }
    }

    public static String getMacAddress() {
        try {
            return a("/sys/class/net/eth0/address").substring(0, 17);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(String str) {
        StringBuffer stringBuffer = new StringBuffer(1000);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        char[] cArr = new char[1024];
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                stringBuffer.append(String.valueOf(cArr, 0, read));
            } else {
                bufferedReader.close();
                return stringBuffer.toString();
            }
        }
    }
}
