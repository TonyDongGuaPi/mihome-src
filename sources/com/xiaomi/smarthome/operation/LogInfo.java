package com.xiaomi.smarthome.operation;

public class LogInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f21042a;
    public String b;
    public String c;

    public LogInfo(String str, String str2, String str3) {
        this.f21042a = str;
        this.b = str2;
        this.c = str3;
    }

    public LogInfo() {
    }

    public static LogInfo a(Operation operation) {
        if (operation == null) {
            return new LogInfo();
        }
        return new LogInfo(operation.d, operation.f21043a, operation.c);
    }
}
