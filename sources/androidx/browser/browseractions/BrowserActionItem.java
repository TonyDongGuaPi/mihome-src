package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

public class BrowserActionItem {

    /* renamed from: a  reason: collision with root package name */
    private final String f473a;
    private final PendingIntent b;
    @DrawableRes
    private final int c;

    public BrowserActionItem(@NonNull String str, @NonNull PendingIntent pendingIntent, @DrawableRes int i) {
        this.f473a = str;
        this.b = pendingIntent;
        this.c = i;
    }

    public BrowserActionItem(@NonNull String str, @NonNull PendingIntent pendingIntent) {
        this(str, pendingIntent, 0);
    }

    public int a() {
        return this.c;
    }

    public String b() {
        return this.f473a;
    }

    public PendingIntent c() {
        return this.b;
    }
}
