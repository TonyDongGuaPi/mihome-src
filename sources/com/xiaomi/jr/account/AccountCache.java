package com.xiaomi.jr.account;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;

public class AccountCache {

    /* renamed from: a  reason: collision with root package name */
    public static final AccountCache f10271a = new AccountCache();
    private static Pair<String, Drawable> b;

    private AccountCache() {
    }

    public final void a(String str, Drawable drawable) {
        b = new Pair<>(str, drawable);
    }

    public final void a() {
        b = null;
    }

    public final Drawable a(String str) {
        if (b == null || !TextUtils.equals(str, (CharSequence) b.first)) {
            return null;
        }
        return (Drawable) b.second;
    }
}
