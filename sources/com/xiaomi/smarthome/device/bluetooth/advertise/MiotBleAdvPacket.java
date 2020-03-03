package com.xiaomi.smarthome.device.bluetooth.advertise;

import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;

public class MiotBleAdvPacket {

    /* renamed from: a  reason: collision with root package name */
    public FrameControl f15118a;
    public int b;
    public int c;
    public String d;
    public Capability e;
    public Event f;
    public boolean g;
    public int h;

    public MiotBleAdvPacket(PacketReader packetReader) {
        try {
            this.f15118a = new FrameControl(packetReader);
            if (BluetoothHelper.a(this.f15118a.k)) {
                this.b = packetReader.a();
                this.c = packetReader.b();
                if (this.f15118a.e) {
                    this.d = packetReader.c();
                }
                if (this.f15118a.f) {
                    this.e = new Capability(packetReader);
                }
                if (this.f15118a.g) {
                    this.f = new Event(packetReader);
                }
                this.g = !packetReader.e();
            }
        } catch (Exception unused) {
        }
    }

    public boolean a() {
        return this.g && this.f15118a.j;
    }

    public static class FrameControl {

        /* renamed from: a  reason: collision with root package name */
        public boolean f15121a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public int k;

        public FrameControl(PacketReader packetReader) {
            int b2 = packetReader.b();
            this.f15121a = packetReader.a(b2, 0);
            this.b = packetReader.a(b2, 1);
            this.c = packetReader.a(b2, 2);
            this.d = packetReader.a(b2, 3);
            this.e = packetReader.a(b2, 4);
            this.f = packetReader.a(b2, 5);
            this.g = packetReader.a(b2, 6);
            this.h = packetReader.a(b2, 7);
            int b3 = packetReader.b();
            this.i = packetReader.a(b3, 0);
            this.j = packetReader.a(b3, 1);
            this.k = packetReader.a(b3, 4, 7);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("");
            sb.append("factoryNew = " + this.f15121a);
            sb.append("\n");
            sb.append("connected = " + this.b);
            sb.append("\n");
            sb.append("central = " + this.c);
            sb.append("\n");
            sb.append("encrypted = " + this.d);
            sb.append("\n");
            sb.append("withMac = " + this.e);
            sb.append("\n");
            sb.append("withCapability = " + this.f);
            sb.append("\n");
            sb.append("withEvent = " + this.g);
            sb.append("\n");
            sb.append("withCustomData = " + this.h);
            sb.append("\n");
            sb.append("withSubtitle = " + this.i);
            sb.append("\n");
            sb.append("binding = " + this.j);
            sb.append("\n");
            sb.append("version = " + this.k);
            sb.append("\n");
            return sb.toString();
        }
    }

    public static class Capability {

        /* renamed from: a  reason: collision with root package name */
        public boolean f15119a;
        public boolean b;
        public boolean c;
        public int d;

        public Capability(PacketReader packetReader) {
            int b2 = packetReader.b();
            this.f15119a = packetReader.a(b2, 0);
            this.b = packetReader.a(b2, 1);
            this.c = packetReader.a(b2, 2);
            this.d = packetReader.a(b2, 3, 4);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("connectable = " + this.f15119a);
            sb.append("\n");
            sb.append("centralable = " + this.b);
            sb.append("\n");
            sb.append("encryptable = " + this.c);
            sb.append("\n");
            sb.append("bindable = " + this.d);
            sb.append("\n");
            return sb.toString();
        }
    }

    public static class Event {

        /* renamed from: a  reason: collision with root package name */
        public int f15120a;
        public int b;

        public Event(PacketReader packetReader) {
            this.f15120a = packetReader.a();
            this.b = packetReader.b();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("");
            sb.append("eventId = " + this.f15120a);
            sb.append("\n");
            sb.append("eventData = " + this.b);
            sb.append("\n");
            return sb.toString();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f15118a != null) {
            sb.append(this.f15118a.toString());
            sb.append("\n");
        }
        sb.append("productId: " + this.b);
        sb.append("\n");
        sb.append("frameCounter: " + this.c);
        sb.append("\n");
        if (this.f15118a != null && this.f15118a.e) {
            sb.append("mac: " + this.d);
            sb.append("\n");
        }
        if (this.e != null) {
            sb.append(this.e.toString());
            sb.append("\n");
        }
        if (this.f != null) {
            sb.append(this.f.toString());
            sb.append("\n");
        }
        sb.append("valid: " + this.g);
        sb.append("\n");
        return sb.toString();
    }
}
