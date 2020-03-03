package com.xiaomi.smarthome.application;

public class ApplicationLifeCycle {
    public void b() {
    }

    public ApplicationLifeCycle() {
        CommonApplication.addApplicationLifeCycle(this);
    }
}
