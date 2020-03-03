package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class q9qgqg9 implements SensorEventListener {
    private static long g999gqq9ggqgqq;
    private static final List g9q9q9g9 = new CopyOnWriteArrayList();
    public static String gqg9qq9gqq9q9q = null;
    private static long gqgqgqq9gq9q9q9;
    private static final List q9gqqq99999qq = new CopyOnWriteArrayList();
    private static long q9q99gq99gggqg9qqqgg;
    private static final List q9qq99qg9qqgqg9gqgg9 = new CopyOnWriteArrayList();
    private static long qgg99qqg9gq;
    private static final List qgg9qgg9999g9g = new CopyOnWriteArrayList();
    private static long qq9q9ggg;
    private static final List qqq9gg9gqq9qgg99q = new CopyOnWriteArrayList();

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 1:
                if (System.currentTimeMillis() - g999gqq9ggqgqq > 80) {
                    double d = (double) sensorEvent.values[0];
                    double d2 = (double) sensorEvent.values[1];
                    double d3 = (double) sensorEvent.values[2];
                    if (q9qq99qg9qqgqg9gqgg9.size() < 5) {
                        q9qq99qg9qqgqg9gqgg9.add(String.format(gqg9qq9gqq9q9q("2d1f2305714f78446216281f2305714a", 50), new Object[]{Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3)}));
                    }
                    g999gqq9ggqgqq = System.currentTimeMillis();
                    return;
                }
                return;
            case 2:
                if (System.currentTimeMillis() - gqgqgqq9gq9q9q9 > 80) {
                    double d4 = (double) sensorEvent.values[0];
                    double d5 = (double) sensorEvent.values[1];
                    double d6 = (double) sensorEvent.values[2];
                    if (q9gqqq99999qq.size() < 5) {
                        q9gqqq99999qq.add(String.format(gqg9qq9gqq9q9q("2d7c2366712c78276275287c23667129", 81), new Object[]{Double.valueOf(d4), Double.valueOf(d5), Double.valueOf(d6)}));
                    }
                    gqgqgqq9gq9q9q9 = System.currentTimeMillis();
                    return;
                }
                return;
            case 4:
                if (System.currentTimeMillis() - qgg99qqg9gq > 80) {
                    double d7 = (double) sensorEvent.values[0];
                    double d8 = (double) sensorEvent.values[1];
                    double d9 = (double) sensorEvent.values[2];
                    if (g9q9q9g9.size() < 5) {
                        g9q9q9g9.add(String.format(gqg9qq9gqq9q9q("2d2a2330717a78716223282a2330717f", 7), new Object[]{Double.valueOf(d7), Double.valueOf(d8), Double.valueOf(d9)}));
                    }
                    qgg99qqg9gq = System.currentTimeMillis();
                    return;
                }
                return;
            case 5:
                if (System.currentTimeMillis() - qq9q9ggg > 80) {
                    int i = (int) sensorEvent.values[0];
                    if (qqq9gg9gqq9qgg99q.size() < 5) {
                        qqq9gg9gqq9qgg99q.add(String.valueOf(i));
                    }
                    qq9q9ggg = System.currentTimeMillis();
                    return;
                }
                return;
            case 9:
                if (System.currentTimeMillis() - q9q99gq99gggqg9qqqgg > 80) {
                    double d10 = (double) sensorEvent.values[0];
                    double d11 = (double) sensorEvent.values[1];
                    double d12 = (double) sensorEvent.values[2];
                    if (qgg9qgg9999g9g.size() < 5) {
                        qgg9qgg9999g9g.add(String.format(gqg9qq9gqq9q9q("2d0d2317715d78566204280d23177158", 32), new Object[]{Double.valueOf(d10), Double.valueOf(d11), Double.valueOf(d12)}));
                    }
                    q9q99gq99gggqg9qqqgg = System.currentTimeMillis();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static synchronized String gqg9qq9gqq9q9q() {
        String obj;
        synchronized (q9qgqg9.class) {
            obj = q9qq99qg9qqgqg9gqgg9.toString();
        }
        return obj;
    }

    public static synchronized String qgg9qgg9999g9g() {
        String obj;
        synchronized (q9qgqg9.class) {
            obj = qgg9qgg9999g9g.toString();
        }
        return obj;
    }

    public static synchronized String q9qq99qg9qqgqg9gqgg9() {
        String obj;
        synchronized (q9qgqg9.class) {
            obj = q9gqqq99999qq.toString();
        }
        return obj;
    }

    public static synchronized String q9gqqq99999qq() {
        String obj;
        synchronized (q9qgqg9.class) {
            obj = g9q9q9g9.toString();
        }
        return obj;
    }

    public static synchronized String g9q9q9g9() {
        String obj;
        synchronized (q9qgqg9.class) {
            obj = qqq9gg9gqq9qgg99q.toString();
        }
        return obj;
    }

    public static void qqq9gg9gqq9qgg99q() {
        q9qq99qg9qqgqg9gqgg9.clear();
        qgg9qgg9999g9g.clear();
        q9gqqq99999qq.clear();
        g9q9q9g9.clear();
        qqq9gg9gqq9qgg99q.clear();
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 32);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ 5);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
