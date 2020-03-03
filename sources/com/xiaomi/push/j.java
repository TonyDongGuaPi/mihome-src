package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class j {

    static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f12817a;
        private final boolean b;

        a(String str, boolean z) {
            this.f12817a = str;
            this.b = z;
        }

        public String a() {
            return this.f12817a;
        }
    }

    private static final class b implements ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        boolean f12818a;
        private final LinkedBlockingQueue<IBinder> b;

        private b() {
            this.f12818a = false;
            this.b = new LinkedBlockingQueue<>(1);
        }

        public IBinder a() {
            if (!this.f12818a) {
                this.f12818a = true;
                return this.b.poll(30000, TimeUnit.MILLISECONDS);
            }
            throw new IllegalStateException();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private static final class c implements IInterface {

        /* renamed from: a  reason: collision with root package name */
        private IBinder f12819a;

        public c(IBinder iBinder) {
            this.f12819a = iBinder;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f12819a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.f12819a;
        }
    }

    public static a a(Context context) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
                b bVar = new b();
                Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, bVar, 1)) {
                    try {
                        IBinder a2 = bVar.a();
                        if (a2 != null) {
                            a aVar = new a(new c(a2).a(), false);
                            context.unbindService(bVar);
                            return aVar;
                        }
                        context.unbindService(bVar);
                    } catch (Exception e) {
                        throw e;
                    } catch (Throwable th) {
                        context.unbindService(bVar);
                        throw th;
                    }
                }
                throw new IOException("Google Play connection failed");
            } catch (Exception e2) {
                throw e2;
            }
        } else {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
    }
}
