package com.xiaomi.ai.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.ai.mibrain.MibrainUtils;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.UUID;

public class n {

    /* renamed from: a  reason: collision with root package name */
    public static String f9958a = "MiSpeechSDK:VoiceRecognizeUtils";
    private static String b = "aivs_id";
    private static String c = "_id_";

    public static float a(byte[] bArr, int i) {
        short[] a2 = e.a(bArr, i);
        if (a2 == null) {
            return -1.0f;
        }
        long j = 0;
        for (short abs : a2) {
            j += (long) Math.abs(abs);
        }
        return ((float) j) / ((float) r6);
    }

    public static String a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            Log.h(f9958a, "SocketException", e);
            return "";
        }
    }

    public static String a(Context context) {
        String str;
        String str2;
        String string = context.getSharedPreferences(b, 0).getString(c, (String) null);
        if (string == null) {
            str = f9958a;
            str2 = "READ ID null";
        } else {
            str = f9958a;
            str2 = "READ ID " + string;
        }
        Log.f(str, str2);
        return string;
    }

    private static String a(Reader reader) {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        while (true) {
            int read = reader.read(cArr);
            if (read < 0) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public static String a(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b2 : digest) {
                byte b3 = b2 & 255;
                if (b3 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e = e;
            str2 = f9958a;
            str3 = "NoSuchAlgorithmException";
            Log.h(str2, str3, e);
            return null;
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            str2 = f9958a;
            str3 = "UnsupportedEncodingException";
            Log.h(str2, str3, e);
            return null;
        }
    }

    private static void a(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(b, 0).edit();
        edit.putString(c, str);
        edit.apply();
        String str2 = f9958a;
        Log.f(str2, "SAVE ID " + str);
    }

    public static String b() {
        String str = "";
        String str2 = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
            while (true) {
                if (str != null) {
                    str = lineNumberReader.readLine();
                    if (str != null) {
                        str2 = str.trim();
                        break;
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str2 == null || "".equals(str2)) {
            try {
                return d("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (c(str2)) {
            return null;
        }
        return str2;
    }

    public static String b(Context context) {
        boolean z;
        String a2 = a(context);
        if (TextUtils.isEmpty(a2) || "d41d8cd98f00b204e9800998ecf8427e".equals(a2)) {
            z = false;
            a2 = a(c(context));
            if (TextUtils.isEmpty(a2)) {
                a2 = a(b());
                if (TextUtils.isEmpty(a2)) {
                    a2 = a(c());
                }
                if (TextUtils.isEmpty(a2) && Build.VERSION.SDK_INT >= 26) {
                    try {
                        a2 = a(Build.getSerial());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (TextUtils.isEmpty(a2)) {
                    a2 = a(UUID.randomUUID().toString());
                }
            }
        } else {
            z = true;
        }
        if (!TextUtils.isEmpty(a2) && !z) {
            a(context, a2);
        }
        return a2;
    }

    public static String b(String str) {
        return MibrainUtils.base64(String.format("{\"d\":\"%s\"}", new Object[]{str}).getBytes());
    }

    public static String c() {
        String str = "";
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            try {
                str = defaultAdapter.getAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (c(str)) {
            return null;
        }
        return str;
    }

    public static String c(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (Build.VERSION.SDK_INT < 23) {
                return telephonyManager.getDeviceId();
            }
            if (telephonyManager.getPhoneCount() == 0) {
                return null;
            }
            return Build.VERSION.SDK_INT >= 26 ? telephonyManager.getImei(0) : telephonyManager.getDeviceId(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean c(String str) {
        return "02:00:00:00:00:00".equals(str) || "00:00:00:00:00:00".equals(str) || "ff:ff:ff:ff:ff:ff".equals(str) || "FF:FF:FF:FF:FF:FF".equals(str);
    }

    private static String d(String str) {
        FileReader fileReader = new FileReader(str);
        String a2 = a((Reader) fileReader);
        fileReader.close();
        return a2;
    }
}
