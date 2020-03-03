package com.xiaomi.msg.thread;

import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.data.ConnInfo;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnCheckThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12114a = "ConnCheckThread";
    private ConcurrentHashMap<Long, ConnInfo> b;
    private ConnectionHandler c = null;
    private XMDTransceiver d;

    public ConnCheckThread(ConcurrentHashMap<Long, ConnInfo> concurrentHashMap, XMDTransceiver xMDTransceiver) {
        this.b = concurrentHashMap;
        this.d = xMDTransceiver;
        setName(f12114a + Constants.D.nextInt(Constants.E));
    }

    public void a(ConnectionHandler connectionHandler) {
        this.c = connectionHandler;
    }

    public void run() {
        while (this.d.d()) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                for (Map.Entry next : this.b.entrySet()) {
                    long longValue = ((Long) next.getKey()).longValue();
                    ConnInfo connInfo = (ConnInfo) next.getValue();
                    String str = Constants.C + longValue + JSMethod.NOT_SET + f12114a;
                    if (!connInfo.h() && currentTimeMillis - connInfo.g() >= ((long) (connInfo.b() * 1000))) {
                        MIMCLog.c(str, String.format("%s connection=%d not created for timeout", new Object[]{this.c.getClass(), Long.valueOf(longValue)}));
                        MIMCLog.a(str, "nowTs=" + currentTimeMillis + " connInfo.getInitTime()=" + connInfo.g() + " connInfo.getTimeoutS()=" + connInfo.b() + " nowTs - connInfo.getInitTime()=" + (currentTimeMillis - connInfo.g()));
                        this.c.b(longValue, connInfo.o());
                        this.d.a(longValue, "Failed to create connection");
                    } else if (currentTimeMillis - connInfo.i() >= ((long) (connInfo.b() * 1000))) {
                        MIMCLog.c(str, String.format("%s close connection=%d for timeout", new Object[]{this.c.getClass(), Long.valueOf(longValue)}));
                        this.d.a(longValue, "TIMEOUT");
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                MIMCLog.d(Constants.C + f12114a, "check connection and stream validation error,", e);
            }
        }
    }
}
