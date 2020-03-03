package com.jstun.demo;

import java.io.PrintStream;
import java.net.BindException;
import java.net.InetAddress;

public class DiscoveryTestDemo implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    InetAddress f6210a;

    public DiscoveryTestDemo(InetAddress inetAddress) {
        this.f6210a = inetAddress;
    }

    public void run() {
        try {
            System.out.println(new DiscoveryTest(this.f6210a, "jstun.javawi.de", 3478).a());
        } catch (BindException e) {
            PrintStream printStream = System.out;
            printStream.println(this.f6210a.toString() + ": " + e.getMessage());
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
            e2.printStackTrace();
        }
    }
}
