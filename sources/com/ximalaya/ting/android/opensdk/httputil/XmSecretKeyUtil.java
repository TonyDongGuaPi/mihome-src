package com.ximalaya.ting.android.opensdk.httputil;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Base64;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.SercretPubKey;
import com.ximalaya.ting.android.opensdk.model.track.TrackBaseInfo;
import com.ximalaya.ting.android.opensdk.util.AESUtil;
import com.ximalaya.ting.android.opensdk.util.DHUtil;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.opensdk.util.dh.DhKeyPair;
import java.util.HashMap;
import java.util.Map;

public class XmSecretKeyUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1994a = "client.txt";
    private static XmSecretKeyUtil b = null;
    private static final String c = "secret_public_key";
    private static final String d = "secret_request_time";
    private static final String e = "secret_expired_time";
    private static final String f = "E0kmVeFAbSqiuv2ZSi0AKw==";
    private String g;
    private Context h;
    private boolean i = false;

    public static XmSecretKeyUtil a() {
        if (b == null) {
            synchronized (XmSecretKeyUtil.class) {
                if (b == null) {
                    b = new XmSecretKeyUtil();
                }
            }
        }
        return b;
    }

    public void a(Context context) {
        if (!this.i) {
            this.i = true;
            this.h = context.getApplicationContext();
            this.g = SharedPreferencesUtil.a(this.h).c(c);
            if (!d()) {
                e();
            }
        }
    }

    private boolean d() {
        long b2 = SharedPreferencesUtil.a(this.h).b(d, 0);
        int b3 = SharedPreferencesUtil.a(this.h).b(e, 0);
        if (!TextUtils.isEmpty(this.g) && (System.currentTimeMillis() - b2) / 1000 <= ((long) b3)) {
            return true;
        }
        this.g = null;
        b();
        return false;
    }

    public void b() {
        SharedPreferencesUtil.a(this.h).l(d);
        SharedPreferencesUtil.a(this.h).l(e);
        SharedPreferencesUtil.a(this.h).l(c);
        DHUtil.a(this.h);
    }

    private void e() {
        DhKeyPair a2 = DHUtil.a();
        if (a2 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(DTransferConstants.aV, a2.getPublicKey().toString());
            CommonRequest.X(hashMap, new IDataCallBack<SercretPubKey>() {
                public void a(@Nullable SercretPubKey sercretPubKey) {
                    XmSecretKeyUtil.this.a(sercretPubKey);
                }

                public void a(int i, String str) {
                    XmSecretKeyUtil.this.b();
                }
            });
        }
    }

    @WorkerThread
    public SercretPubKey c() {
        DhKeyPair a2 = DHUtil.a();
        if (a2 == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.aV, a2.getPublicKey().toString());
        SercretPubKey d2 = CommonRequest.d((Map<String, String>) hashMap);
        a(d2);
        return d2;
    }

    public void a(SercretPubKey sercretPubKey) {
        if (sercretPubKey != null) {
            this.g = sercretPubKey.a();
            SharedPreferencesUtil.a(this.h).a(c, this.g);
            SharedPreferencesUtil.a(this.h).a(d, System.currentTimeMillis());
            SharedPreferencesUtil.a(this.h).a(e, sercretPubKey.b());
            return;
        }
        b();
    }

    @WorkerThread
    public void a(TrackBaseInfo trackBaseInfo, boolean z) {
        byte[] bArr;
        if (trackBaseInfo != null) {
            if (!d()) {
                c();
            }
            if (z) {
                bArr = Base64.decode(f, 0);
            } else if (d()) {
                bArr = a(DHUtil.a(this.g, DHUtil.a()), 16).getBytes();
            } else {
                return;
            }
            if (bArr != null) {
                try {
                    trackBaseInfo.f(AESUtil.d(a(trackBaseInfo.n()), bArr));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                try {
                    trackBaseInfo.c(AESUtil.d(a(trackBaseInfo.g()), bArr));
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                try {
                    trackBaseInfo.d(AESUtil.d(a(trackBaseInfo.i()), bArr));
                } catch (Throwable th3) {
                    th3.printStackTrace();
                }
                try {
                    trackBaseInfo.e(AESUtil.d(a(trackBaseInfo.k()), bArr));
                } catch (Throwable th4) {
                    th4.printStackTrace();
                }
                try {
                    trackBaseInfo.a(AESUtil.d(a(trackBaseInfo.a()), bArr));
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
                try {
                    trackBaseInfo.b(AESUtil.d(a(trackBaseInfo.c()), bArr));
                } catch (Throwable th6) {
                    th6.printStackTrace();
                }
            }
        }
    }

    private static String a(String str, int i2) {
        if (str.length() < i2) {
            StringBuilder sb = new StringBuilder(str);
            while (sb.length() < i2) {
                sb.append("0");
            }
            str = sb.toString();
        }
        return str.substring(0, i2);
    }

    private static byte[] a(String str) {
        try {
            return Base64.decode(str, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
