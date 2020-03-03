package com.xiaomi.ai;

public class VoiceprintRegisterRequest {

    /* renamed from: a  reason: collision with root package name */
    String f9912a;
    String b;
    DataInputMode c;
    String d;
    String e;
    int f;
    int g;

    public enum DataInputMode {
        DATA_INPUT_MODE_RECORDER,
        DATA_INPUT_MODE_BUFFER
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(DataInputMode dataInputMode) {
        this.c = dataInputMode;
    }

    public void a(String str) {
        this.f9912a = str;
    }

    public void b(int i) {
        this.g = i;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.d = str;
    }
}
