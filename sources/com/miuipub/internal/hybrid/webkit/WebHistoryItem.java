package com.miuipub.internal.hybrid.webkit;

import android.graphics.Bitmap;
import miuipub.hybrid.HybridHistoryItem;

public class WebHistoryItem extends HybridHistoryItem {

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.WebHistoryItem f8280a;

    public WebHistoryItem(android.webkit.WebHistoryItem webHistoryItem) {
        this.f8280a = webHistoryItem;
    }

    public String a() {
        return this.f8280a.getUrl();
    }

    public String b() {
        return this.f8280a.getOriginalUrl();
    }

    public String c() {
        return this.f8280a.getTitle();
    }

    public Bitmap d() {
        return this.f8280a.getFavicon();
    }
}
