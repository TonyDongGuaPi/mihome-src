package com.xiaomi.smarthome.library.bluetooth.channel.packet;

public class InvalidPacket extends Packet {
    public String a() {
        return "invalid";
    }

    public String toString() {
        return "InvalidPacket{}";
    }

    public byte[] d() {
        return new byte[0];
    }
}
