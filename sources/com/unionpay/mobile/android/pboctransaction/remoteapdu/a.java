package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import cn.com.fmsh.communication.contants.Contants;
import com.unionpay.mobile.android.pboctransaction.AppIdentification;
import com.unionpay.mobile.android.pboctransaction.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.tsm.connect.IInitCallback;
import com.unionpay.mobile.tsm.connect.IRemoteApdu;
import com.unionpay.tsmservice.data.AppStatus;
import java.util.ArrayList;
import java.util.Iterator;

public final class a implements c {

    /* renamed from: a  reason: collision with root package name */
    b f9650a;
    /* access modifiers changed from: private */
    public IRemoteApdu b = null;
    private boolean c = false;
    private Context d = null;
    /* access modifiers changed from: private */
    public Handler e = null;
    /* access modifiers changed from: private */
    public boolean f = false;
    private final Handler.Callback g = new b(this);
    private final ServiceConnection h = new c(this);
    /* access modifiers changed from: private */
    public final IInitCallback.Stub i = new d(this);

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        ArrayList<AppIdentification> arrayList;
        String c2;
        k.a("plugin-tsm", "RemoteApduEngine.readList() +++");
        ArrayList<com.unionpay.mobile.android.model.c> arrayList2 = null;
        try {
            String str = this.c ? "D15600010100016111000000B0004101" : "D15600010100016111000000B0004001";
            k.a("plugin-tsm", "sid=" + str);
            IRemoteApdu iRemoteApdu = this.b;
            String writeApdu = iRemoteApdu.writeApdu("00a4040010" + str, 0);
            if (writeApdu != null && writeApdu.equalsIgnoreCase(Contants.Message.PACKET_CODE_DEFAULT)) {
                writeApdu = this.b.writeApdu("80CA2F0000", 0);
            }
            arrayList = e.b(writeApdu);
        } catch (Exception e2) {
            e2.printStackTrace();
            k.c("plugin-tsm", e2.getMessage());
            arrayList = null;
        }
        if (arrayList != null && arrayList.size() > 0) {
            arrayList2 = new ArrayList<>();
            Iterator<AppIdentification> it = arrayList.iterator();
            while (it.hasNext()) {
                AppIdentification next = it.next();
                if (next.c() && !AppStatus.APPLY.equalsIgnoreCase(next.b()) && (c2 = e.c(dVar.a(next))) != null && c2.length() > 0) {
                    arrayList2.add(new com.unionpay.mobile.android.model.a(4, next.a(), "", c2, 1));
                }
            }
        }
        k.a("plugin-tsm", "RemoteApduEngine.readList() ---");
        return arrayList2;
    }

    public final void a() {
        d();
        if (this.d != null) {
            Context context = this.d;
            k.a("plugin-tsm", "unbindTSMService() ++");
            if (this.f) {
                try {
                    context.unbindService(this.h);
                } catch (Exception unused) {
                }
                this.f = false;
            }
        }
    }

    public final void a(b bVar, Context context) {
        this.f9650a = bVar;
        this.d = context;
        this.e = new Handler(this.g);
        try {
            Intent intent = new Intent("com.unionpay.mobile.tsm.PBOCService");
            intent.setPackage("com.unionpay.mobile.tsm");
            context.startService(intent);
            if (this.e != null) {
                this.e.sendMessageDelayed(Message.obtain(this.e, 3000), 8000);
            }
            if (!context.bindService(intent, this.h, 1) && this.f9650a != null) {
                k.a("plugin-tsm", "startTSMService.initFailed()");
                this.f9650a.b();
            }
        } catch (Exception unused) {
            if (this.f9650a != null) {
                k.a("plugin-tsm", "startTSMService exception");
                this.f9650a.b();
            }
        }
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final byte[] a(byte[] bArr, int i2) {
        String str;
        if (bArr == null) {
            return null;
        }
        String a2 = e.a(bArr);
        k.a("plugin-tsm", "[---->]" + a2);
        try {
            str = this.b.writeApdu(a2, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            str = null;
        }
        k.a("plugin-tsm", "[<----]" + str);
        return e.a(str);
    }

    public final void b() {
    }

    public final void c() {
        d();
    }

    public final void d() {
        if (this.b != null) {
            try {
                this.b.closeChannel(0);
                this.b.closeChannel(1);
                this.b.closeChannel(2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
