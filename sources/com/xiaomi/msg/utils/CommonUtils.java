package com.xiaomi.msg.utils;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class CommonUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12124a = (Constants.C + "CommonUtils");
    private static Random b = new Random();

    public static void a(DatagramSocket datagramSocket, DatagramPacket datagramPacket, int i) {
        if (b.nextInt(100) >= i) {
            try {
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                MIMCLog.d(f12124a, "send udp packet exception:", e);
            }
        }
    }
}
