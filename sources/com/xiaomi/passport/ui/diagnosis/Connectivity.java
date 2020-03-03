package com.xiaomi.passport.ui.diagnosis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Pair;
import com.xiaomi.accountsdk.account.URLConst;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class Connectivity {
    public static boolean isConnectionFast(int i, int i2) {
        if (i == 1) {
            return true;
        }
        if (i != 0) {
            return false;
        }
        switch (i2) {
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            default:
                return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isConnectedWifi(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (networkInfo == null || !networkInfo.isConnected() || networkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean isConnectedMobile(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 0;
    }

    public static boolean isConnectedFast(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.isConnected() && isConnectionFast(networkInfo.getType(), networkInfo.getSubtype());
    }

    static String whatsMyIp() {
        return sendRequestGetStringResult("https://api.ipify.org/");
    }

    static String myIpInfo() {
        Pair<String, ? extends Exception> sendRequest = sendRequest(URLConst.HyperTextTransferProtocol + "ip-api.com/json");
        if (sendRequest.first != null) {
            return (String) sendRequest.first;
        }
        return sendRequest.second != null ? ((Exception) sendRequest.second).toString() : "unknown error";
    }

    static String sendRequestGetStringResult(String str) {
        StringBuilder sb = new StringBuilder();
        Pair<String, ? extends Exception> sendRequest = sendRequest(str);
        if (sendRequest.second != null) {
            sb.append("Exception: " + sendRequest.second + "\n");
        }
        if (sendRequest.first != null) {
            sb.append("Response size: " + ((String) sendRequest.first).length() + "\n");
            String substring = ((String) sendRequest.first).length() > 50 ? ((String) sendRequest.first).substring(0, 50) : (String) sendRequest.first;
            sb.append("Response snapshot: " + substring + "\n");
        }
        return sb.toString();
    }

    static Pair<String, ? extends Exception> sendRequest(String str) {
        try {
            return Pair.create(SimpleRequest.getAsString(str, (Map<String, String>) null, (Map<String, String>) null, true).getBody(), (Object) null);
        } catch (IOException e) {
            e.printStackTrace();
            return Pair.create((Object) null, e);
        } catch (AccessDeniedException e2) {
            e2.printStackTrace();
            return Pair.create((Object) null, e2);
        } catch (AuthenticationFailureException e3) {
            e3.printStackTrace();
            return Pair.create((Object) null, e3);
        }
    }

    static String dnsResolveGetResultStr(String str) {
        StringBuilder sb = new StringBuilder();
        Pair<String, String[]> dnsResolve = dnsResolve(str);
        sb.append("default: " + ((String) dnsResolve.first) + "\n");
        sb.append("all result: \n");
        if (dnsResolve.second != null) {
            for (String str2 : (String[]) dnsResolve.second) {
                sb.append(str2 + "\n");
            }
        }
        return sb.toString();
    }

    static Pair<String, String[]> dnsResolve(String str) {
        String str2;
        String[] strArr;
        try {
            str2 = InetAddress.getByName(str).toString();
        } catch (UnknownHostException e) {
            String unknownHostException = e.toString();
            e.printStackTrace();
            str2 = unknownHostException;
        }
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            strArr = new String[allByName.length];
            int i = 0;
            while (i < allByName.length) {
                try {
                    strArr[i] = allByName[i].toString();
                    i++;
                } catch (UnknownHostException e2) {
                    e = e2;
                    e.printStackTrace();
                    return Pair.create(str2, strArr);
                }
            }
        } catch (UnknownHostException e3) {
            e = e3;
            strArr = null;
            e.printStackTrace();
            return Pair.create(str2, strArr);
        }
        return Pair.create(str2, strArr);
    }

    public static String getLocalAddress(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            return Formatter.formatIpAddress(connectionInfo.getIpAddress());
        }
        return null;
    }

    public static String getIPAddress(boolean z) {
        try {
            for (T inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                Iterator<T> it = Collections.list(inetAddresses.getInetAddresses()).iterator();
                while (true) {
                    if (it.hasNext()) {
                        InetAddress inetAddress = (InetAddress) it.next();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            boolean z2 = hostAddress.indexOf(58) < 0;
                            if (z) {
                                if (z2) {
                                    return hostAddress;
                                }
                            } else if (!z2) {
                                int indexOf = hostAddress.indexOf(37);
                                return indexOf < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, indexOf).toUpperCase();
                            }
                        }
                    }
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }
}
