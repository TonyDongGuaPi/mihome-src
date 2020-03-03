package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import com.tencent.smtt.export.external.interfaces.IX5WebHistoryItem;

public class WebHistoryItem {

    /* renamed from: a  reason: collision with root package name */
    private IX5WebHistoryItem f9106a = null;
    private android.webkit.WebHistoryItem b = null;

    private WebHistoryItem() {
    }

    static WebHistoryItem a(android.webkit.WebHistoryItem webHistoryItem) {
        if (webHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem2 = new WebHistoryItem();
        webHistoryItem2.b = webHistoryItem;
        return webHistoryItem2;
    }

    static WebHistoryItem a(IX5WebHistoryItem iX5WebHistoryItem) {
        if (iX5WebHistoryItem == null) {
            return null;
        }
        WebHistoryItem webHistoryItem = new WebHistoryItem();
        webHistoryItem.f9106a = iX5WebHistoryItem;
        return webHistoryItem;
    }

    public Bitmap getFavicon() {
        return this.f9106a != null ? this.f9106a.getFavicon() : this.b.getFavicon();
    }

    public String getOriginalUrl() {
        return this.f9106a != null ? this.f9106a.getOriginalUrl() : this.b.getOriginalUrl();
    }

    public String getTitle() {
        return this.f9106a != null ? this.f9106a.getTitle() : this.b.getTitle();
    }

    public String getUrl() {
        return this.f9106a != null ? this.f9106a.getUrl() : this.b.getUrl();
    }
}
