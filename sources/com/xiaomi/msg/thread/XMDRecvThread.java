package com.xiaomi.msg.thread;

import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.logger.MIMCLog;
import com.xiaomi.msg.utils.CommonUtils;
import com.xiaomi.msg.utils.XMDPacketDispatcher;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class XMDRecvThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12118a = "XMDRecvThread";
    private DatagramSocket b;
    private XMDPacketDispatcher c;
    private XMDTransceiver d;
    private Random e = new Random();

    public XMDRecvThread(XMDPacketDispatcher xMDPacketDispatcher, DatagramSocket datagramSocket, XMDTransceiver xMDTransceiver) {
        this.c = xMDPacketDispatcher;
        this.b = datagramSocket;
        this.d = xMDTransceiver;
        setName("recvThread" + Constants.D.nextInt(Constants.E));
    }

    public void run() {
        while (this.d.d()) {
            try {
                byte[] bArr = new byte[4096];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
                this.b.receive(datagramPacket);
                int nextInt = this.e.nextInt(100);
                if (nextInt < this.d.i()) {
                    MIMCLog.a(Constants.C + f12118a, "Probabilistic packet loss, randomNum=" + nextInt + " PacketLossRate" + this.d.i());
                } else {
                    if (datagramPacket.getLength() == 4) {
                        byte[] data = datagramPacket.getData();
                        if (data[0] == 0 && data[1] == 12 && data[2] == 18 && data[3] == 15) {
                            MIMCLog.a(Constants.C + f12118a, "recv dpdk ping!");
                            CommonUtils.a(this.b, new DatagramPacket(data, datagramPacket.getLength(), datagramPacket.getSocketAddress()), this.d.i());
                        }
                    }
                    this.c.a(datagramPacket);
                }
            } catch (Exception e2) {
                if (this.d.d()) {
                    MIMCLog.d(Constants.C + f12118a, "XMDRecvThread handle receive packet error,", e2);
                }
            }
        }
        MIMCLog.b(Constants.C + f12118a, "shutDown!");
    }
}
