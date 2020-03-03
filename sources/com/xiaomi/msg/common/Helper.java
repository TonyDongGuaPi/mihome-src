package com.xiaomi.msg.common;

import cn.com.fmsh.communication.core.MessageHead;
import com.xiaomi.msg.logger.MIMCLog;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Helper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12080a = "Helper";
    private static Map<Long, Long> b = new ConcurrentHashMap();
    private static long c = 1;

    public static int a(byte b2) {
        return b2 & 255;
    }

    public static int a(short s) {
        return s & 65535;
    }

    public static long a(int i) {
        return ((long) i) & MessageHead.SERIAL_MAK;
    }

    public static long a() {
        long nextLong = Constants.D.nextLong();
        while (nextLong == -1) {
            nextLong = Constants.D.nextLong();
        }
        return nextLong;
    }

    public static String b() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (nextElement != null && (nextElement instanceof Inet4Address) && !nextElement.isLoopbackAddress() && nextElement.getHostAddress().indexOf(":") == -1) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            MIMCLog.d(f12080a, "getLocalIp error, ", e);
            return null;
        }
    }

    public static byte[] a(long j) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(j);
        return allocate.array();
    }

    public static long a(byte[] bArr) {
        return ByteBuffer.wrap(bArr).getLong();
    }

    public static byte[] b(short s) {
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort(s);
        return allocate.array();
    }

    public static short b(byte[] bArr) {
        return ByteBuffer.wrap(bArr).getShort();
    }

    public static long b(long j) {
        return b.get(Long.valueOf(j)).longValue();
    }

    public static synchronized long c(long j) {
        long longValue;
        synchronized (Helper.class) {
            if (!b.containsKey(Long.valueOf(j))) {
                b.put(Long.valueOf(j), Long.valueOf(c));
            }
            longValue = b.get(Long.valueOf(j)).longValue();
            b.put(Long.valueOf(j), Long.valueOf(1 + longValue));
        }
        return longValue;
    }

    public static void d(long j) {
        if (b.containsKey(Long.valueOf(j))) {
            b.remove(Long.valueOf(j));
        }
    }
}
