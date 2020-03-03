package com.xiaomi.msg.example;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.example.handler.ServerConnectionHandler;
import com.xiaomi.msg.example.handler.ServerDatagramHandler;
import com.xiaomi.msg.example.handler.ServerStreamHandler;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.IOException;

public class Server {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12101a = "Server";

    public static void a(String[] strArr) throws InterruptedException, IOException {
        MIMCLog.a("./");
        MIMCLog.b("serverLog.txt");
        MIMCLog.b(1);
        XMDTransceiver xMDTransceiver = new XMDTransceiver(XiaomiOAuthConstants.SCOPE_MI_CLOUD_GALLERY);
        ServerDatagramHandler serverDatagramHandler = new ServerDatagramHandler();
        ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler();
        ServerStreamHandler serverStreamHandler = new ServerStreamHandler();
        xMDTransceiver.a((DatagramHandler) serverDatagramHandler);
        xMDTransceiver.a((ConnectionHandler) serverConnectionHandler);
        xMDTransceiver.a((StreamHandler) serverStreamHandler);
        serverStreamHandler.a(xMDTransceiver);
        new PrintStatic(serverStreamHandler, xMDTransceiver).start();
        xMDTransceiver.b();
        MIMCLog.b("Server", String.format("server iP: %s:%d", new Object[]{xMDTransceiver.a().getAddress().getHostAddress(), Integer.valueOf(xMDTransceiver.a().getPort())}));
    }
}
