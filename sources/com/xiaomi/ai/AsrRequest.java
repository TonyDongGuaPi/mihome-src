package com.xiaomi.ai;

public class AsrRequest {

    /* renamed from: a  reason: collision with root package name */
    public static final short f9894a = 1;
    public static final short b = 2;
    public static final short c = 4;
    public static final int d = 32000;
    public static final int e = 64000;
    public static final int f = 640;
    public static final int g = 1280;
    String h;
    boolean i;
    boolean j;
    boolean k = true;
    DataInputMode l = DataInputMode.DATA_INPUT_MODE_RECORDER;
    VadMode m = VadMode.VAD_MODE_LOCAL;
    String n;
    int o = 0;
    short p = 0;
    int q = 0;
    int r = 0;
    String s;
    String t;
    boolean u = false;
    int v = 0;
    NlpRequest w;

    public enum DataInputMode {
        DATA_INPUT_MODE_RECORDER,
        DATA_INPUT_MODE_BUFFER
    }

    public enum VadMode {
        VAD_MODE_LOCAL,
        VAD_MODE_CLOUD
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.h;
    }

    public void a(int i2) {
        this.v = i2;
    }

    public void a(DataInputMode dataInputMode) {
        this.l = dataInputMode;
    }

    public void a(VadMode vadMode) {
        this.m = vadMode;
    }

    public void a(NlpRequest nlpRequest) {
        this.w = nlpRequest;
    }

    public void a(String str) {
        this.t = str;
    }

    public void a(short s2, int i2, int i3) {
        this.p = (short) (this.p | s2);
        if (s2 == 4) {
            this.q = i2;
            this.r = i3;
        }
    }

    public void a(boolean z) {
        this.u = z;
    }

    public void b(int i2) {
        this.o = i2;
    }

    public void b(String str) {
        this.s = str;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void c(String str) {
        this.n = str;
    }

    public void c(boolean z) {
        this.j = z;
    }

    public void d(boolean z) {
        this.k = z;
    }

    public void e(boolean z) {
        this.p = (short) (z ? this.p | 256 : this.p & 65279);
    }
}
