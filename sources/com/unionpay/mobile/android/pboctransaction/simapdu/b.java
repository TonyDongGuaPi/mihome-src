package com.unionpay.mobile.android.pboctransaction.simapdu;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.unionpay.mobile.android.pboctransaction.AppIdentification;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.simapdu.a;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.l;
import com.unionpay.tsmservice.data.AppStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;

public final class b implements c {
    private static b d = new b();

    /* renamed from: a  reason: collision with root package name */
    private SEService f9663a = null;
    private Channel[] b = new Channel[3];
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.pboctransaction.b c;
    private Handler.Callback e = new c(this);
    private Handler f = new Handler(this.e);

    private b() {
    }

    private synchronized String a(String str, int i) throws a.C0076a {
        String str2 = null;
        if (str == null) {
            return null;
        }
        k.a("plugin-sim", "====>" + str);
        String upperCase = str.toUpperCase(Locale.CHINA);
        boolean z = false;
        if (i > this.b.length) {
            i = 0;
        }
        if (upperCase.startsWith("00A40400") || upperCase.startsWith("01A40400") || upperCase.startsWith("02A40400")) {
            z = true;
        }
        if (z) {
            a(i);
            String b2 = b(e.a(upperCase.substring(10, (((Integer.parseInt(upperCase.substring(8, 9), 16) * 16) + Integer.parseInt(upperCase.substring(9, 10), 16)) * 2) + 10)), i);
            if (!TextUtils.isEmpty(b2)) {
                return b2;
            }
            k.c("plugin-sim", " writeApdu openchannel exception!!!");
            throw new a.C0076a();
        }
        try {
            byte[] a2 = e.a(upperCase);
            if (a2 != null) {
                str2 = e.a(this.b[i].transmit(a2));
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new a.C0076a();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        k.a("plugin-sim", "<====" + str2);
        return str2;
    }

    private void a(int i) {
        k.a("plugin-sim", "closeChannel(int) +++");
        if (this.b[i] != null && i <= this.b.length) {
            try {
                this.b[i].close();
            } catch (Exception e2) {
                e2.printStackTrace();
                k.a("plugin-sim", " mChannel[channel].close() exception!!!");
            }
            this.b[i] = null;
        }
        k.a("plugin-sim", "closeChannel(int) ---");
    }

    private String b(byte[] bArr, int i) {
        Channel openLogicalChannel;
        try {
            Reader[] readers = this.f9663a.getReaders();
            if (readers.length <= 0 || (openLogicalChannel = readers[0].openSession().openLogicalChannel(bArr)) == null) {
                return "";
            }
            this.b[i] = openLogicalChannel;
            return e.a(openLogicalChannel.getSelectResponse());
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static synchronized b e() {
        b bVar;
        synchronized (b.class) {
            bVar = d;
        }
        return bVar;
    }

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        k.c("plugin-sim", " SIMEngine.readList() +++");
        ArrayList<com.unionpay.mobile.android.model.c> arrayList = null;
        try {
            ArrayList arrayList2 = new ArrayList(1);
            String a2 = dVar.a("A0000003330101");
            k.c("plugin-sim", "full AID:" + a2);
            if (a2 != null) {
                if (a2.length() >= 16) {
                    arrayList2.add(new AppIdentification(a2, (String) null));
                    if (arrayList2.size() > 0) {
                        ArrayList<com.unionpay.mobile.android.model.c> arrayList3 = new ArrayList<>();
                        try {
                            Iterator it = arrayList2.iterator();
                            while (it.hasNext()) {
                                AppIdentification appIdentification = (AppIdentification) it.next();
                                if (!AppStatus.APPLY.equalsIgnoreCase(appIdentification.b())) {
                                    String c2 = e.c(dVar.a(appIdentification));
                                    k.a("nfcphone", " cardNumber=" + c2);
                                    if (c2 != null && c2.length() > 0) {
                                        arrayList3.add(new com.unionpay.mobile.android.model.a(16, appIdentification.a(), "", c2, 1));
                                        k.a("nfcphone", " valid Number= " + c2);
                                    }
                                }
                            }
                            arrayList = arrayList3;
                        } catch (Throwable th) {
                            th = th;
                            arrayList = arrayList3;
                            Log.e("plugin-sim", " SimEngine Exception = " + th.getMessage());
                            k.c("plugin-sim", " SIMEngine.readList() ---");
                            return arrayList;
                        }
                    } else {
                        Log.e("plugin-sim", " SIMEngine --- there has no PBOC aids!!!");
                    }
                    k.c("plugin-sim", " SIMEngine.readList() ---");
                    return arrayList;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void a() {
        k.c("plugin-sim", "SIMEngine.destroy() +++ ");
        k.c("plugin-sim", " mSEService = " + this.f9663a);
        d();
        if (this.f9663a != null && this.f9663a.isConnected()) {
            k.a("TAG", " mSEService.isConnected() = " + this.f9663a.isConnected());
            k.c("plugin-sim", " mSEService.shutdown() ");
            this.f9663a.shutdown();
        }
        k.c("plugin-sim", "SIMEngine.destroy() --- ");
    }

    public final void a(com.unionpay.mobile.android.pboctransaction.b bVar, Context context) {
        this.c = bVar;
        try {
            new l();
            if (l.a() == null || !l.a().isConnected()) {
                this.f.sendEmptyMessage(2);
                return;
            }
            this.f9663a = l.a();
            this.f.sendEmptyMessage(1);
        } catch (Throwable th) {
            th.printStackTrace();
            Log.e("plugin-sim", " service ERROR!!!");
            this.f.sendEmptyMessage(2);
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        byte[] bArr2;
        k.c("plugin-sim", " SIMEngine.sendApdu() +++");
        try {
            bArr2 = e.a(a(e.a(bArr), i));
        } catch (a.C0076a e2) {
            e2.printStackTrace();
            k.c("plugin-sim", " " + e2.getMessage());
            bArr2 = null;
        }
        k.c("plugin-sim", " SIMEngine.sendApdu() ---");
        return bArr2;
    }

    public final void b() {
    }

    public final void c() {
        d();
    }

    public final void d() {
        k.a("plugin-sim", "closeChannels() +++");
        for (int i = 0; i < this.b.length; i++) {
            a(i);
        }
        k.a("plugin-sim", "closeChannels() ---");
    }
}
