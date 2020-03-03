package com.xiaomi.stat.c;

import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.a.a.a.a;
import com.xiaomi.stat.b;
import com.xiaomi.stat.d.k;

class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f23038a;
    final /* synthetic */ d b;

    e(d dVar, IBinder iBinder) {
        this.b = dVar;
        this.f23038a = iBinder;
    }

    public void run() {
        a a2 = a.C0080a.a(this.f23038a);
        try {
            if (!b.e()) {
                this.b.f23037a[0] = a2.a(this.b.b, this.b.c);
            } else if (b.x()) {
                this.b.f23037a[0] = a2.b(this.b.b, this.b.c);
            } else {
                this.b.f23037a[0] = null;
            }
            k.b("UploadMode", " connected, do remote http post " + this.b.f23037a[0]);
            synchronized (i.class) {
                try {
                    i.class.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (RemoteException e2) {
            k.e("UploadMode", " error while uploading the data by IPC." + e2.toString());
            this.b.f23037a[0] = null;
        }
    }
}
