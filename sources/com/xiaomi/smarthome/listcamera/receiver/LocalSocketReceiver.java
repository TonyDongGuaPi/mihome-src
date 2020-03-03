package com.xiaomi.smarthome.listcamera.receiver;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Parcel;
import android.util.Log;
import com.xiaomi.smarthome.camera.VideoFrame;
import java.io.IOException;
import java.io.InputStream;

public class LocalSocketReceiver extends Receiver {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19345a = "camera_host";
    LocalSocket b;
    private LocalServerSocket f;
    private byte[] g = new byte[1048576];
    private String h;
    private long i;

    /* access modifiers changed from: protected */
    public void a() {
        try {
            this.f = new LocalServerSocket(f19345a + this.h);
            this.b = this.f.accept();
            Log.e(Receiver.c, "Create Socket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        try {
            if (this.b != null) {
                this.b.close();
            }
            if (this.f != null) {
                this.f.close();
            }
            this.f = new LocalServerSocket(f19345a + this.h);
            this.b = this.f.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void c() {
        try {
            if (this.b == null) {
                b();
                return;
            }
            InputStream inputStream = this.b.getInputStream();
            Parcel obtain = Parcel.obtain();
            byte[] bArr = new byte[4];
            int read = inputStream.read(bArr, 0, 4);
            if (read == -1) {
                try {
                    this.b.close();
                    this.f.close();
                } catch (IOException unused) {
                }
                this.b = null;
            } else if (read == 4) {
                this.i = System.currentTimeMillis();
                byte b2 = (bArr[0] & 255) | 0 | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
                int i2 = 0;
                while (true) {
                    int i3 = b2 - i2;
                    if (i3 > this.g.length) {
                        i3 = this.g.length;
                    }
                    int read2 = inputStream.read(this.g, 0, i3);
                    if (read2 != -1) {
                        obtain.unmarshall(this.g, 0, read2);
                        i2 += read2;
                        if (i2 >= b2) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                obtain.setDataPosition(0);
                String readString = obtain.readString();
                int readInt = obtain.readInt();
                int readInt2 = obtain.readInt();
                long readLong = obtain.readLong();
                int readInt3 = obtain.readInt();
                long readLong2 = obtain.readLong();
                int readInt4 = obtain.readInt();
                boolean z = obtain.readInt() == 1;
                if (readInt3 <= 0) {
                    return;
                }
                if (readInt3 <= 2097152) {
                    byte[] bArr2 = new byte[readInt3];
                    try {
                        obtain.readByteArray(bArr2);
                        VideoFrame videoFrame = new VideoFrame(bArr2, readLong, readInt3, readInt, readInt2, readLong2, readInt4, z);
                        if (readString != null && this.e != null) {
                            this.e.a(readString, videoFrame);
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void d() {
        Log.e(Receiver.c, "Release");
    }

    /* access modifiers changed from: protected */
    public void e() {
        try {
            if (this.b != null) {
                if (this.b != null) {
                    this.b.close();
                }
                if (this.f != null) {
                    this.f.close();
                    return;
                }
                return;
            }
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress(f19345a + this.h));
            localSocket.close();
            if (this.f != null) {
                this.f.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalSocketReceiver(String str) {
        this.h = str;
    }
}
