package com.unionpay.mobile.android.pboctransaction.samsung;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import com.unionpay.client3.tsm.ITsmConnection;
import com.unionpay.client3.tsm.SeAppInfo;
import com.unionpay.mobile.android.model.a;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.data.AppStatus;
import java.util.ArrayList;

public final class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private Context f9654a;
    private com.unionpay.mobile.android.pboctransaction.b b;
    /* access modifiers changed from: private */
    public ITsmConnection c;
    private int d = 0;
    private Handler.Callback e = new c(this);
    /* access modifiers changed from: private */
    public Handler f = new Handler(this.e);

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (this.b == null) {
            return;
        }
        if (z) {
            this.b.a();
        } else {
            this.b.b();
        }
    }

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        ArrayList<com.unionpay.mobile.android.model.c> arrayList = null;
        if (this.c == null) {
            return null;
        }
        try {
            SeAppInfo[] seApps = this.c.getSeApps(this.d);
            if (seApps == null || seApps.length <= 0) {
                return null;
            }
            ArrayList<com.unionpay.mobile.android.model.c> arrayList2 = new ArrayList<>();
            int i = 0;
            while (i < seApps.length) {
                try {
                    String appAid = seApps[i].getAppAid();
                    if (appAid != null && appAid.startsWith("A000000333")) {
                        String appAid2 = seApps[i].getAppAid();
                        if (!(appAid2 == null || appAid2.length() <= 16 || AppStatus.APPLY.equalsIgnoreCase(appAid2.substring(14, 16)))) {
                            arrayList2.add(new a(1, seApps[i].getAppAid(), "", seApps[i].getPan(), 1));
                        }
                    }
                    i++;
                } catch (RemoteException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    e.printStackTrace();
                    return arrayList;
                } catch (Exception e3) {
                    e = e3;
                    arrayList = arrayList2;
                    e.printStackTrace();
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (RemoteException e4) {
            e = e4;
            e.printStackTrace();
            return arrayList;
        } catch (Exception e5) {
            e = e5;
            e.printStackTrace();
            return arrayList;
        }
    }

    public final void a() {
    }

    public final void a(com.unionpay.mobile.android.pboctransaction.b bVar, Context context) {
        this.b = bVar;
        this.f9654a = context;
        try {
            Intent intent = new Intent();
            intent.setAction("com.unionpay.client3.action.TSM_MODEL");
            intent.setPackage("com.unionpay");
            context.startService(intent);
            if (!context.bindService(intent, new d(this), 1)) {
                k.a("plugin-clientV3", "startSamsungService() failed!!!");
                a(false);
                return;
            }
            this.f.sendMessageDelayed(this.f.obtainMessage(1), 3000);
        } catch (Exception unused) {
            a(false);
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        if (this.c != null) {
            try {
                k.a("plugin-clientV3", "--->" + e.a(bArr));
                String sendApdu = this.c.sendApdu(this.d, e.a(bArr), i);
                k.a("plugin-clientV3", "<---" + sendApdu);
                return e.a(sendApdu);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void d() {
    }
}
