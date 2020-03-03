package org.cybergarage.net;

import com.mi.global.bbs.utils.ConnectionHelper;
import com.taobao.weex.el.parse.Operators;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import org.cybergarage.util.Debug;

public class HostInterface {
    public static final int IPV4_BITMASK = 1;
    public static final int IPV6_BITMASK = 16;
    public static final int LOCAL_BITMASK = 256;
    public static boolean USE_LOOPBACK_ADDR = false;
    public static boolean USE_ONLY_IPV4_ADDR = false;
    public static boolean USE_ONLY_IPV6_ADDR = false;
    private static String ifAddress = "";

    public static final void setInterface(String str) {
        ifAddress = str;
    }

    public static final String getInterface() {
        return ifAddress;
    }

    private static final boolean hasAssignedInterface() {
        return ifAddress.length() > 0;
    }

    private static final boolean isUsableAddress(InetAddress inetAddress) {
        if (!USE_LOOPBACK_ADDR && inetAddress.isLoopbackAddress()) {
            return false;
        }
        if (!USE_ONLY_IPV4_ADDR || !(inetAddress instanceof Inet6Address)) {
            return !USE_ONLY_IPV6_ADDR || !(inetAddress instanceof Inet4Address);
        }
        return false;
    }

    public static final int getNHostAddresses() {
        if (hasAssignedInterface()) {
            return 1;
        }
        int i = 0;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    if (isUsableAddress(inetAddresses.nextElement())) {
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            Debug.warning(e);
        }
        return i;
    }

    public static final InetAddress[] getInetAddress(int i, String[] strArr) {
        Enumeration<NetworkInterface> enumeration;
        if (strArr != null) {
            Vector vector = new Vector();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                try {
                    NetworkInterface byName = NetworkInterface.getByName(strArr[i2]);
                    if (byName != null) {
                        vector.add(byName);
                    }
                } catch (SocketException unused) {
                }
            }
            enumeration = vector.elements();
        } else {
            try {
                enumeration = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException unused2) {
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            Enumeration<InetAddress> inetAddresses = enumeration.nextElement().getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress nextElement = inetAddresses.nextElement();
                if ((i & 256) != 0 || !nextElement.isLoopbackAddress()) {
                    if ((i & 1) != 0 && (nextElement instanceof Inet4Address)) {
                        arrayList.add(nextElement);
                    } else if ((i & 16) != 0 && (nextElement instanceof InetAddress)) {
                        arrayList.add(nextElement);
                    }
                }
            }
        }
        return (InetAddress[]) arrayList.toArray(new InetAddress[0]);
    }

    public static final String getHostAddress(int i) {
        if (hasAssignedInterface()) {
            return getInterface();
        }
        int i2 = 0;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (isUsableAddress(nextElement)) {
                            if (i2 >= i) {
                                return nextElement.getHostAddress();
                            }
                            i2++;
                        }
                    }
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static final boolean isIPv6Address(String str) {
        try {
            if (InetAddress.getByName(str) instanceof Inet6Address) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final boolean isIPv4Address(String str) {
        try {
            if (InetAddress.getByName(str) instanceof Inet4Address) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static final boolean hasIPv4Addresses() {
        int nHostAddresses = getNHostAddresses();
        for (int i = 0; i < nHostAddresses; i++) {
            if (isIPv4Address(getHostAddress(i))) {
                return true;
            }
        }
        return false;
    }

    public static final boolean hasIPv6Addresses() {
        int nHostAddresses = getNHostAddresses();
        for (int i = 0; i < nHostAddresses; i++) {
            if (isIPv6Address(getHostAddress(i))) {
                return true;
            }
        }
        return false;
    }

    public static final String getIPv4Address() {
        int nHostAddresses = getNHostAddresses();
        for (int i = 0; i < nHostAddresses; i++) {
            String hostAddress = getHostAddress(i);
            if (isIPv4Address(hostAddress)) {
                return hostAddress;
            }
        }
        return "";
    }

    public static final String getIPv6Address() {
        int nHostAddresses = getNHostAddresses();
        for (int i = 0; i < nHostAddresses; i++) {
            String hostAddress = getHostAddress(i);
            if (isIPv6Address(hostAddress)) {
                return hostAddress;
            }
        }
        return "";
    }

    public static final String getHostURL(String str, int i, String str2) {
        if (isIPv6Address(str)) {
            str = Operators.ARRAY_START_STR + str + Operators.ARRAY_END_STR;
        }
        return ConnectionHelper.HTTP_PREFIX + str + ":" + Integer.toString(i) + str2;
    }
}
