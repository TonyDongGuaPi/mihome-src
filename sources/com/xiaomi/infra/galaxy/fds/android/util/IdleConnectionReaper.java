package com.xiaomi.infra.galaxy.fds.android.util;

import java.util.concurrent.TimeUnit;
import org.apache.http.conn.ClientConnectionManager;

public final class IdleConnectionReaper extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10147a = 60000;
    private final ClientConnectionManager b;

    public IdleConnectionReaper(ClientConnectionManager clientConnectionManager) {
        super("java-sdk-http-connection-reaper");
        this.b = clientConnectionManager;
        setDaemon(true);
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(60000);
                this.b.closeIdleConnections(60, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
