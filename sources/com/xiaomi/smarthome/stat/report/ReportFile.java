package com.xiaomi.smarthome.stat.report;

import android.os.MemoryFile;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.IOException;

public class ReportFile {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22759a = 100;
    public static final int b = 1048576;
    private static final int e = 1048576;
    private static final byte[] f = {Constants.TagName.SYSTEM_NEW_VERSION};
    private int c = 0;
    private MemoryFile d = null;
    private byte[] g = {32};
    private String h = "";

    public ReportFile(String str) {
        this.h = str;
        try {
            this.d = new MemoryFile(this.h, 1048576);
            if (this.d.length() > 0) {
                this.d.readBytes(this.g, this.d.length() - 1, 0, 1);
                this.c = this.g[0] - 32;
                if (this.c < 0) {
                    this.c = 0;
                }
            }
        } catch (IOException unused) {
            if (this.d != null) {
                this.d.close();
            }
            this.d = null;
        }
    }

    public boolean a() {
        return this.d != null;
    }

    public synchronized boolean a(String str) {
        if (this.c >= 100) {
            return false;
        }
        try {
            int length = this.d.length();
            byte[] bytes = str.getBytes();
            if (bytes.length + length >= 1048576) {
                return false;
            }
            if (length > 0) {
                this.d.writeBytes(f, 0, length - 1, 1);
            }
            this.d.writeBytes(bytes, 0, length, bytes.length);
            this.c++;
            this.g[0] = (byte) (this.c + 32);
            this.d.writeBytes(this.g, 0, this.d.length(), 1);
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public synchronized String b() {
        if (this.d != null) {
            if (this.d.length() >= 2) {
                byte[] bArr = new byte[(this.d.length() - 1)];
                try {
                    this.d.readBytes(bArr, 0, 0, bArr.length);
                    return new String(bArr);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return "";
                }
            }
        }
        return "";
    }

    public synchronized void c() {
        if (this.d != null) {
            this.d.close();
            this.d = null;
        }
    }
}
