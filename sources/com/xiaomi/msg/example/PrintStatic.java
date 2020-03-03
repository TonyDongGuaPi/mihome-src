package com.xiaomi.msg.example;

import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.example.handler.ServerStreamHandler;

public class PrintStatic extends Thread {

    /* renamed from: a  reason: collision with root package name */
    ServerStreamHandler f12100a;
    int b = 0;

    PrintStatic(ServerStreamHandler serverStreamHandler, XMDTransceiver xMDTransceiver) {
        this.f12100a = serverStreamHandler;
        this.f12100a.e().a(xMDTransceiver);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.f12100a.e().a() != this.b || this.b == 0) {
                this.b = this.f12100a.e().a();
            } else {
                this.f12100a.e().b();
            }
        }
    }
}
