package com.unionpay.mobile.android.pboctransaction.sdapdu;

import android.content.Context;
import android.util.Log;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.unionpay.mobile.android.pboctransaction.AppIdentification;
import com.unionpay.mobile.android.pboctransaction.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.utils.k;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public final class a implements c {

    /* renamed from: a  reason: collision with root package name */
    NativeSDWriter f9661a = null;
    private Context b = null;
    private boolean c = false;

    private static ArrayList<com.unionpay.mobile.android.model.c> b(d dVar) {
        String str;
        String str2;
        String str3;
        if (dVar.a() == null) {
            str2 = "uppay";
            str3 = " select UPCard failed!!!!";
        } else {
            String c2 = dVar.c();
            if (c2 == null) {
                str2 = "uppay";
                str3 = " getBankCardFileEntry failed!!!!";
            } else {
                byte[] a2 = e.a(c2);
                int i = 1;
                int i2 = ((a2[0] & 255) << 24) | ((a2[1] & 255) << 16);
                int i3 = Integer.MIN_VALUE;
                int i4 = 0;
                for (int i5 = 0; i5 < 10; i5++) {
                    if ((i3 & i2) == 0) {
                        i4++;
                    }
                    i3 >>>= 1;
                }
                if (i4 <= 0) {
                    return null;
                }
                ArrayList<com.unionpay.mobile.android.model.c> arrayList = new ArrayList<>(i4);
                int i6 = Integer.MIN_VALUE;
                while (i <= i4 && i < 11) {
                    if ((i2 & i6) == 0) {
                        String a3 = dVar.a(i, com.unionpay.mobile.android.utils.c.a());
                        if (a3 != null && a3.length() > 0) {
                            String d = e.d(a3.substring(0, 40));
                            try {
                                str = new String(e.a(d), "gbk");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                                str = d;
                            }
                            String substring = a3.substring(40, 60);
                            arrayList.add(new com.unionpay.mobile.android.model.a(8, Integer.toString(i), str, e.c(substring), 2));
                            k.c("uppay", i + InternalFrame.ID + substring);
                        }
                    } else {
                        d dVar2 = dVar;
                    }
                    i++;
                    i6 >>>= 1;
                }
                return arrayList;
            }
        }
        Log.e(str2, str3);
        return null;
    }

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.model.c> a(d dVar) {
        k.c("uppay", "SDEngine.readList() +++");
        ArrayList arrayList = null;
        if (!this.c) {
            return null;
        }
        ArrayList<com.unionpay.mobile.android.model.c> arrayList2 = new ArrayList<>();
        String a2 = dVar.a(new AppIdentification("A0000003330101", "1.0"));
        if (a2 != null && a2.length() > 0) {
            arrayList = new ArrayList(1);
            arrayList.add(new com.unionpay.mobile.android.model.a(8, "A0000003330101", "", e.c(a2), 1));
            k.c("uppay", "A0000003330101" + InternalFrame.ID + a2);
        }
        if (arrayList != null && arrayList.size() > 0) {
            arrayList2.addAll(arrayList);
        }
        ArrayList<com.unionpay.mobile.android.model.c> b2 = b(dVar);
        if (b2 != null && b2.size() > 0) {
            arrayList2.addAll(b2);
        }
        k.c("uppay", "SDEngine.readList() ---");
        return arrayList2;
    }

    public final void a() {
    }

    public final void a(b bVar, Context context) {
        this.b = context;
        if (bVar != null) {
            bVar.a();
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        String str = "";
        if (this.f9661a != null) {
            str = this.f9661a.a(e.a(bArr));
        }
        return e.a(str);
    }

    public final void b() {
        this.f9661a = new NativeSDWriter();
        b.a();
        ArrayList arrayList = new ArrayList();
        for (String add : b.f9662a) {
            arrayList.add(add);
        }
        this.c = this.f9661a.a((ArrayList<String>) arrayList);
    }

    public final void c() {
        if (this.f9661a != null) {
            this.f9661a.a();
        }
    }

    public final void d() {
    }
}
