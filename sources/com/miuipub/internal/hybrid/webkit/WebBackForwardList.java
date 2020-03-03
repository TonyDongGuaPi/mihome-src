package com.miuipub.internal.hybrid.webkit;

import miuipub.hybrid.HybridBackForwardList;
import miuipub.hybrid.HybridHistoryItem;

public class WebBackForwardList extends HybridBackForwardList {

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.WebBackForwardList f8278a;

    public WebBackForwardList(android.webkit.WebBackForwardList webBackForwardList) {
        this.f8278a = webBackForwardList;
    }

    public HybridHistoryItem a() {
        return new WebHistoryItem(this.f8278a.getCurrentItem());
    }

    public int b() {
        return this.f8278a.getCurrentIndex();
    }

    public HybridHistoryItem a(int i) {
        return new WebHistoryItem(this.f8278a.getItemAtIndex(i));
    }

    public int c() {
        return this.f8278a.getSize();
    }
}
