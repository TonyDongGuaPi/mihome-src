package com.xiaomi.smarthome.httpserver;

import java.io.IOException;
import java.io.PrintStream;

public class ServerRunner {
    public static void a(Class cls) {
        try {
            a((NanoHTTPD) cls.newInstance());
        } catch (Exception unused) {
        }
    }

    public static void a(NanoHTTPD nanoHTTPD) {
        try {
            nanoHTTPD.a();
        } catch (IOException e) {
            PrintStream printStream = System.err;
            printStream.println("Couldn't start server:\n" + e);
            System.exit(-1);
        }
        System.out.println("Server started, Hit Enter to stop.\n");
        try {
            System.in.read();
        } catch (Throwable unused) {
        }
        nanoHTTPD.b();
        System.out.println("Server stopped.\n");
    }
}
