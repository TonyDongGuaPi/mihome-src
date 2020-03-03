package com.jstun.demo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

public class DiscoveryInfo {

    /* renamed from: a  reason: collision with root package name */
    private InetAddress f6208a;
    private boolean b = false;
    private int c = 0;
    private String d;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private InetAddress l;

    public DiscoveryInfo(InetAddress inetAddress) {
        this.f6208a = inetAddress;
    }

    public boolean a() {
        return this.b;
    }

    public void a(int i2, String str) {
        this.b = true;
        this.c = i2;
        this.d = str;
    }

    public boolean b() {
        if (this.b) {
            return false;
        }
        return this.e;
    }

    public void c() {
        this.e = true;
    }

    public boolean d() {
        if (this.b) {
            return false;
        }
        return this.f;
    }

    public void e() {
        this.f = true;
    }

    public boolean f() {
        if (this.b) {
            return false;
        }
        return this.g;
    }

    public void g() {
        this.g = true;
    }

    public boolean h() {
        if (this.b) {
            return false;
        }
        return this.i;
    }

    public void i() {
        this.i = true;
    }

    public boolean j() {
        if (this.b) {
            return false;
        }
        return this.h;
    }

    public void k() {
        this.h = true;
    }

    public boolean l() {
        if (this.b) {
            return false;
        }
        return this.j;
    }

    public void m() {
        this.j = true;
    }

    public boolean n() {
        if (this.b) {
            return false;
        }
        return this.k;
    }

    public void o() {
        this.k = true;
    }

    public InetAddress p() {
        return this.l;
    }

    public InetAddress q() {
        return this.f6208a;
    }

    public void a(InetAddress inetAddress) {
        this.l = inetAddress;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Network interface: ");
        try {
            stringBuffer.append(NetworkInterface.getByInetAddress(this.f6208a).getName());
        } catch (SocketException unused) {
            stringBuffer.append("unknown");
        }
        stringBuffer.append("\n");
        stringBuffer.append("Local IP address: ");
        stringBuffer.append(this.f6208a.getHostAddress());
        stringBuffer.append("\n");
        if (this.b) {
            stringBuffer.append(this.d + " - Responsecode: " + this.c);
            return stringBuffer.toString();
        }
        stringBuffer.append("Result: ");
        if (this.e) {
            stringBuffer.append("Open access to the Internet.\n");
        }
        if (this.f) {
            stringBuffer.append("Firewall blocks UDP.\n");
        }
        if (this.g) {
            stringBuffer.append("Full Cone NAT handles connections.\n");
        }
        if (this.h) {
            stringBuffer.append("Restricted Cone NAT handles connections.\n");
        }
        if (this.i) {
            stringBuffer.append("Port restricted Cone NAT handles connections.\n");
        }
        if (this.j) {
            stringBuffer.append("Symmetric Cone NAT handles connections.\n");
        }
        if (this.k) {
            stringBuffer.append("Symmetric UDP Firewall handles connections.\n");
        }
        if (!this.e && !this.f && !this.g && !this.h && !this.i && !this.j && !this.k) {
            stringBuffer.append("unkown\n");
        }
        stringBuffer.append("Public IP address: ");
        if (this.l != null) {
            stringBuffer.append(this.l.getHostAddress());
        } else {
            stringBuffer.append("unknown");
        }
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }
}
