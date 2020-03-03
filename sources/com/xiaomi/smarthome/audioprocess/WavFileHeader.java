package com.xiaomi.smarthome.audioprocess;

import com.drew.metadata.wav.WavDirectory;

public class WavFileHeader {

    /* renamed from: a  reason: collision with root package name */
    public String f13754a = "RIFF";
    public int b = 0;
    public String c = WavDirectory.x;
    public String d = WavDirectory.u;
    public int e = 16;
    public short f = 1;
    public short g = 1;
    public int h = 8000;
    public int i = 0;
    public short j = 0;
    public short k = 8;
    public String l = "data";
    public int m = 0;

    public WavFileHeader() {
    }

    public WavFileHeader(int i2, int i3, int i4) {
        this.h = i2;
        this.k = (short) i3;
        this.g = (short) i4;
        this.i = ((this.h * this.g) * this.k) / 8;
        this.j = (short) ((this.g * this.k) / 8);
    }
}
