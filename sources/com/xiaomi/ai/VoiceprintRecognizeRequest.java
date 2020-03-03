package com.xiaomi.ai;

public class VoiceprintRecognizeRequest {

    /* renamed from: a  reason: collision with root package name */
    String f9911a;
    DataInputMode b;
    String c;
    String d;
    String e;
    int f;

    public enum DataInputMode {
        DATA_INPUT_MODE_RECORDER,
        DATA_INPUT_MODE_BUFFER
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(DataInputMode dataInputMode) {
        this.b = dataInputMode;
    }

    public void a(String str) {
        this.f9911a = str;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }
}
