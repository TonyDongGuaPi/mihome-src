package com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon;

import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;

public class MiotPacketParser {
    public static MiotBleAdvPacket a(BleAdvertiseItem bleAdvertiseItem) {
        if (bleAdvertiseItem.b != 22) {
            return null;
        }
        PacketReader packetReader = new PacketReader(bleAdvertiseItem);
        if (packetReader.a() != 65173) {
            return null;
        }
        try {
            return b(packetReader);
        } catch (Exception unused) {
            return a(packetReader);
        }
    }

    private static MiotBleAdvPacket a(PacketReader packetReader) {
        MiotBleAdvPacket miotBleAdvPacket = new MiotBleAdvPacket();
        try {
            miotBleAdvPacket.b = packetReader.d();
            return miotBleAdvPacket;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static MiotBleAdvPacket b(PacketReader packetReader) {
        MiotBleAdvPacket miotBleAdvPacket = new MiotBleAdvPacket();
        miotBleAdvPacket.f14276a = new MiotBleAdvPacket.FrameControl();
        int b = packetReader.b();
        miotBleAdvPacket.f14276a.f14279a = packetReader.a(b, 3);
        miotBleAdvPacket.f14276a.b = packetReader.a(b, 4);
        miotBleAdvPacket.f14276a.c = packetReader.a(b, 5);
        miotBleAdvPacket.f14276a.d = packetReader.a(b, 6);
        miotBleAdvPacket.f14276a.e = packetReader.a(b, 7);
        int b2 = packetReader.b();
        miotBleAdvPacket.f14276a.f = packetReader.a(b2, 0);
        miotBleAdvPacket.f14276a.g = packetReader.a(b2, 1);
        miotBleAdvPacket.f14276a.k = packetReader.a(b2, 2, 3);
        miotBleAdvPacket.f14276a.l = packetReader.a(b2, 4, 7);
        miotBleAdvPacket.b = packetReader.a();
        miotBleAdvPacket.c = packetReader.b();
        if (miotBleAdvPacket.f14276a.b) {
            miotBleAdvPacket.d = packetReader.c();
        }
        if (miotBleAdvPacket.f14276a.c) {
            int b3 = packetReader.b();
            miotBleAdvPacket.e = new MiotBleAdvPacket.Capability();
            miotBleAdvPacket.e.f14277a = packetReader.a(b3, 0);
            miotBleAdvPacket.e.b = packetReader.a(b3, 1);
            miotBleAdvPacket.e.c = packetReader.a(b3, 2);
            miotBleAdvPacket.e.d = packetReader.a(b3, 3, 4);
            miotBleAdvPacket.e.e = packetReader.a(b3, 5);
        }
        if (miotBleAdvPacket.a()) {
            miotBleAdvPacket.f = packetReader.e();
        }
        if (miotBleAdvPacket.f14276a.c && miotBleAdvPacket.e.e) {
            int b4 = packetReader.b();
            miotBleAdvPacket.g = new MiotBleAdvPacket.IoCapability();
            miotBleAdvPacket.g.k = packetReader.a(b4, 0, 3);
            miotBleAdvPacket.g.l = packetReader.a(b4, 4, 7);
            packetReader.b();
        }
        try {
            if (miotBleAdvPacket.f14276a.d) {
                miotBleAdvPacket.h = new MiotBleAdvPacket.Event();
                if (miotBleAdvPacket.f14276a.l >= 5) {
                    miotBleAdvPacket.h.b = packetReader.b();
                    miotBleAdvPacket.h.f14278a = packetReader.b();
                } else {
                    miotBleAdvPacket.h.f14278a = packetReader.a();
                    miotBleAdvPacket.h.b = packetReader.b();
                }
                if (miotBleAdvPacket.h.b > 0) {
                    for (int i = 0; i < miotBleAdvPacket.h.b; i++) {
                        packetReader.b();
                    }
                }
            }
            if (miotBleAdvPacket.f14276a.f14279a) {
                miotBleAdvPacket.i = new int[3];
                miotBleAdvPacket.i[0] = packetReader.b();
                miotBleAdvPacket.i[1] = packetReader.b();
                miotBleAdvPacket.i[2] = packetReader.b();
                if (miotBleAdvPacket.f14276a.l >= 4) {
                    miotBleAdvPacket.j = new int[4];
                    miotBleAdvPacket.j[0] = packetReader.b();
                    miotBleAdvPacket.j[1] = packetReader.b();
                    miotBleAdvPacket.j[2] = packetReader.b();
                    miotBleAdvPacket.j[3] = packetReader.b();
                } else {
                    miotBleAdvPacket.j = new int[1];
                    miotBleAdvPacket.j[0] = packetReader.b();
                }
            }
            if (miotBleAdvPacket.f14276a.e) {
                miotBleAdvPacket.k = new MiotBleAdvPacket.Mesh();
                int b5 = packetReader.b();
                miotBleAdvPacket.k.c = packetReader.a(b5, 0, 1);
                miotBleAdvPacket.k.d = packetReader.a(b5, 2, 3);
                miotBleAdvPacket.k.e = packetReader.a(b5, 4, 7);
                packetReader.b();
            }
        } catch (Exception unused) {
        }
        return miotBleAdvPacket;
    }
}
