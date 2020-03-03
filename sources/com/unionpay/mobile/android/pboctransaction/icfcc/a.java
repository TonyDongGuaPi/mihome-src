package com.unionpay.mobile.android.pboctransaction.icfcc;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.unionpay.mobile.android.pboctransaction.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;

public final class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private String f9644a = null;
    private String b = "A0000003334355502D4D4F42494C45";
    /* access modifiers changed from: private */
    public cn.gov.pbc.tsm.client.mobile.android.bank.service.a c;
    /* access modifiers changed from: private */
    public b d;
    private Context e;
    private ServiceConnection f = new b(this);

    private byte[] a(byte[] bArr) {
        cn.gov.pbc.tsm.client.mobile.android.bank.service.a aVar;
        byte[] a2;
        String str;
        byte[] bArr2 = null;
        try {
            String a3 = e.a(bArr);
            if (b(a3)) {
                if (a3.contains(this.f9644a)) {
                    k.c("icfcc", "pbocAID = " + this.f9644a);
                    aVar = this.c;
                    a2 = e.a(this.f9644a);
                    str = "00";
                } else if (a3.contains(this.b)) {
                    k.c("icfcc", "upcardAID = " + this.b);
                    aVar = this.c;
                    a2 = e.a(this.b);
                    str = "01";
                }
                bArr2 = aVar.a(a2, str);
            }
        } catch (Exception unused) {
        }
        k.c("icfcc", " openSEChannel result=" + e.a(bArr2));
        return bArr2;
    }

    private static boolean b(String str) {
        return str.startsWith("00A40400") || str.startsWith("01A40400") || str.startsWith("02A40400");
    }

    public final String a(String str) {
        return "";
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0091 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList<com.unionpay.mobile.android.model.c> a(com.unionpay.mobile.android.pboctransaction.d r11) {
        /*
            r10 = this;
            cn.gov.pbc.tsm.client.mobile.android.bank.service.a r0 = r10.c
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r2 = "325041592e5359532e4444463031"
            cn.gov.pbc.tsm.client.mobile.android.bank.service.a r3 = r10.c     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            byte[] r2 = com.unionpay.mobile.android.pboctransaction.e.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r4 = "00"
            byte[] r2 = r3.a((byte[]) r2, (java.lang.String) r4)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r2 = com.unionpay.mobile.android.pboctransaction.e.a((byte[]) r2)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r3 = "4F"
            java.lang.String r2 = com.unionpay.mobile.android.pboctransaction.icfcc.c.a(r2, r3)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r3 = "icfcc"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r5 = "aid ="
            r4.<init>(r5)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            r4.append(r2)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            com.unionpay.mobile.android.utils.k.c(r3, r4)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            if (r2 == 0) goto L_0x0076
            r10.f9644a = r2     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            com.unionpay.mobile.android.pboctransaction.AppIdentification r3 = new com.unionpay.mobile.android.pboctransaction.AppIdentification     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r4 = ""
            r3.<init>(r2, r4)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r11 = r11.a((com.unionpay.mobile.android.pboctransaction.AppIdentification) r3)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r8 = com.unionpay.mobile.android.pboctransaction.e.c(r11)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            if (r8 == 0) goto L_0x0076
            int r11 = r8.length()     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            if (r11 <= 0) goto L_0x0076
            java.lang.String r11 = "icfcc"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r4 = "  "
            r2.<init>(r4)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            r2.append(r8)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            com.unionpay.mobile.android.utils.k.c(r11, r2)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            com.unionpay.mobile.android.model.a r11 = new com.unionpay.mobile.android.model.a     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            r5 = 8
            java.lang.String r6 = r3.a()     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            java.lang.String r7 = ""
            r9 = 1
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
            r0.add(r11)     // Catch:{ Exception -> 0x0091, all -> 0x0083 }
        L_0x0076:
            cn.gov.pbc.tsm.client.mobile.android.bank.service.a r11 = r10.c     // Catch:{ RemoteException -> 0x007e }
            java.lang.String r1 = "00"
            r11.b((java.lang.String) r1)     // Catch:{ RemoteException -> 0x007e }
            goto L_0x0082
        L_0x007e:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0082:
            return r0
        L_0x0083:
            r11 = move-exception
            cn.gov.pbc.tsm.client.mobile.android.bank.service.a r0 = r10.c     // Catch:{ RemoteException -> 0x008c }
            java.lang.String r1 = "00"
            r0.b((java.lang.String) r1)     // Catch:{ RemoteException -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0090:
            throw r11
        L_0x0091:
            cn.gov.pbc.tsm.client.mobile.android.bank.service.a r11 = r10.c     // Catch:{ RemoteException -> 0x0099 }
            java.lang.String r0 = "00"
            r11.b((java.lang.String) r0)     // Catch:{ RemoteException -> 0x0099 }
            goto L_0x009d
        L_0x0099:
            r11 = move-exception
            r11.printStackTrace()
        L_0x009d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.icfcc.a.a(com.unionpay.mobile.android.pboctransaction.d):java.util.ArrayList");
    }

    public final void a() {
        d();
        if (this.c != null) {
            try {
                this.c.a();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception unused) {
            }
        }
        if (this.e != null) {
            new Intent("com.unionpay.mobile.tsm.PBOCService");
            this.e.unbindService(this.f);
        }
    }

    public final void a(b bVar, Context context) {
        this.d = bVar;
        this.e = context;
        try {
            Intent intent = new Intent("cn.gov.pbc.tsm.client.mobile.android.bank.service");
            intent.setPackage("cn.gov.pbc.tsm.client.mobile.andorid");
            context.startService(intent);
            if (!context.bindService(intent, this.f, 1) && bVar != null) {
                k.a("icfcc", "startTSMService.initFailed()");
                bVar.b();
            }
        } catch (Exception unused) {
            if (bVar != null) {
                k.a("icfcc", "starticfccService exception");
                bVar.b();
            }
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        byte[] bArr2;
        String a2 = e.a(bArr);
        k.c("icfcc", "====>" + a2);
        if (this.c == null) {
            return null;
        }
        if (b(a2)) {
            return a(bArr);
        }
        try {
            bArr2 = this.c.b(bArr);
        } catch (RemoteException | Exception unused) {
            bArr2 = null;
        }
        k.c("icfcc", "<====" + e.a(bArr2));
        return bArr2;
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void d() {
        if (this.c != null) {
            try {
                this.c.b("00");
                this.c.b("01");
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception unused) {
            }
        }
    }
}
