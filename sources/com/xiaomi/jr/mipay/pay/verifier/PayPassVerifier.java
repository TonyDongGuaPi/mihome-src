package com.xiaomi.jr.mipay.pay.verifier;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.jr.mipay.pay.verifier.model.VerifyResult;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PayPassVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10960a = "PAY";
    public static final String b = "BINDCARD";
    public static final String c = "UNBINDCARD";
    public static final String d = "CHANGE_PAYPASS";
    public static final String e = "FORGET_PAYPASS";
    public static final String f = "BINDRING";
    public static final String g = "UNBINDRING";
    public static final String h = "ACTIVATERING";
    public static final String i = "ACTIVATE_FINGERPRINT";
    public static final String j = "BINDFINGER";
    public static final String k = "QR_CODE_PAY";
    private static volatile PayPassVerifier l;
    private Executor m = Executors.newCachedThreadPool();
    private Handler n = new Handler(Looper.getMainLooper());
    private WeakReference<VerifyPasswordListener> o;

    public interface VerifyPasswordListener {
        void a();

        void a(VerifyResult verifyResult);
    }

    private static PayPassVerifier d() {
        if (l == null) {
            synchronized (PayPassVerifier.class) {
                if (l == null) {
                    l = new PayPassVerifier();
                }
            }
        }
        return l;
    }

    private PayPassVerifier() {
    }

    public static Executor a() {
        return d().m;
    }

    public static Handler b() {
        return d().n;
    }

    public static WeakReference<VerifyPasswordListener> c() {
        return d().o;
    }

    public static void a(Context context, String str, VerifyPasswordListener verifyPasswordListener) {
        Intent intent = new Intent(context, VerifierActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra(MipayConstants.e, str);
        }
        d().o = new WeakReference<>(verifyPasswordListener);
        Utils.a(context, intent);
        context.startActivity(intent);
    }
}
