package com.alipay.deviceid.module.senative;

import android.content.Context;
import com.alipay.deviceid.module.x.bv;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class DeviceIdUtil {
    private static DeviceIdUtil _instance = null;
    private static boolean isLoad = true;
    private static final String mVersion = "1.0";
    private Context mContext = null;
    private int netType = -1;

    private native String getMappedIpAddressNative(String str, String str2, int i);

    private native int init(Object obj);

    public native String getErrorCode();

    public native int getVersion();

    public native byte[] zipAndEncryptData(Object obj, byte[] bArr);

    private DeviceIdUtil() {
    }

    static {
        try {
            System.loadLibrary("deviceid_1.0");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized DeviceIdUtil getInstance(Context context) {
        DeviceIdUtil deviceIdUtil;
        synchronized (DeviceIdUtil.class) {
            if (_instance == null) {
                DeviceIdUtil deviceIdUtil2 = new DeviceIdUtil();
                _instance = deviceIdUtil2;
                deviceIdUtil2.loadSo(context);
                _instance.mContext = context;
            }
            deviceIdUtil = _instance;
        }
        return deviceIdUtil;
    }

    public int initialize() {
        return init(this.mContext);
    }

    public byte[] packageDevideData(byte[] bArr) {
        return zipAndEncryptData(this.mContext, bArr);
    }

    public String getRealIpAddress(String str, int i) {
        String phoneIp = getPhoneIp();
        if (str == null || str.length() == 0) {
            return "";
        }
        if (phoneIp == null || phoneIp.length() <= 0 || this.netType != 1) {
            return (phoneIp == null || phoneIp.length() <= 0 || this.netType != 2) ? "" : getMappedIpAddressNative("0.0.0.0", str, i);
        }
        return getMappedIpAddressNative(phoneIp, str, i);
    }

    private void loadSo(Context context) {
        if (!isLoad) {
            bv bvVar = new bv(context);
            bv.f898a = "1.0";
            bvVar.a("deviceid");
        }
    }

    private String getPhoneIp() {
        String str;
        String str2;
        String str3 = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            str = null;
            while (networkInterfaces.hasMoreElements()) {
                try {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    String name = nextElement.getName();
                    if (name != null && nextElement.isUp() && !name.startsWith("ppp") && !name.startsWith("p2p") && !name.startsWith("lo")) {
                        Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement2 = inetAddresses.nextElement();
                            if (!nextElement2.isLoopbackAddress() && (nextElement2 instanceof Inet4Address) && (str2 = nextElement2.getHostAddress().toString()) != null && str2.length() > 0) {
                                if (name != null && name.startsWith("rmnet")) {
                                    str3 = str2;
                                } else if (name != null && name.startsWith("wlan")) {
                                    str = str2;
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            str = null;
        }
        if (str != null && str.length() > 0) {
            this.netType = 2;
            return str;
        } else if (str3 == null || str3.length() <= 0) {
            return "";
        } else {
            this.netType = 1;
            return str3;
        }
    }
}
