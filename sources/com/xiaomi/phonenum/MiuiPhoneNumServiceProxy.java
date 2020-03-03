package com.xiaomi.phonenum;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.phonenum.PhoneNumKeeper;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import com.xiaomi.simactivate.service.IPhoneNumService;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

class MiuiPhoneNumServiceProxy {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12547a = "MiuiPhoneNumKeeper";
    /* access modifiers changed from: private */
    public Logger b = LoggerManager.a();
    private Context c;
    private ServiceConnection d;
    /* access modifiers changed from: private */
    public IPhoneNumService e;
    /* access modifiers changed from: private */
    public boolean f = false;
    private boolean g = false;
    private String h = "";
    /* access modifiers changed from: private */
    public CountDownLatch i = new CountDownLatch(1);

    MiuiPhoneNumServiceProxy(Context context) {
        this.c = context.getApplicationContext();
    }

    public void a(final PhoneNumKeeper.SetupFinishedListener setupFinishedListener) {
        if (this.f) {
            setupFinishedListener.onSetupFinished(Error.NONE);
            return;
        }
        Intent intent = new Intent(Constant.c);
        intent.setPackage(Constant.d);
        this.d = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                IPhoneNumService unused = MiuiPhoneNumServiceProxy.this.e = IPhoneNumService.Stub.asInterface(iBinder);
                boolean unused2 = MiuiPhoneNumServiceProxy.this.f = true;
                MiuiPhoneNumServiceProxy.this.i.countDown();
                setupFinishedListener.onSetupFinished(Error.NONE);
            }

            public void onServiceDisconnected(ComponentName componentName) {
                MiuiPhoneNumServiceProxy.this.b.b(MiuiPhoneNumServiceProxy.f12547a, "onServiceDisconnected");
                IPhoneNumService unused = MiuiPhoneNumServiceProxy.this.e = null;
            }
        };
        if (!this.c.bindService(intent, this.d, 1)) {
            setupFinishedListener.onSetupFinished(Error.UNKNOW);
        }
    }

    public PhoneNum a(int i2, boolean z) throws IOException, RemoteException {
        c();
        return new PhoneNum.Builder().a(this.e.blockObtainPhoneNum(1, this.h, i2, z)).a();
    }

    public boolean a(int i2) throws RemoteException {
        c();
        return this.e.invalidatePhoneNum(1, this.h, i2);
    }

    public boolean b(int i2) throws RemoteException {
        c();
        return this.e.invalidateVerifiedToken(1, this.h, i2);
    }

    private void b() throws InterruptedException {
        this.i.await();
    }

    private void c() {
        try {
            b();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        if (!this.f || this.g || this.e == null) {
            throw new IllegalStateException("MpHelper is not setup.");
        }
    }

    public void a() {
        this.i = new CountDownLatch(1);
        this.f = false;
        if (!(this.d == null || this.e == null || this.c == null)) {
            this.c.unbindService(this.d);
        }
        this.g = true;
        this.c = null;
        this.d = null;
        this.e = null;
    }
}
