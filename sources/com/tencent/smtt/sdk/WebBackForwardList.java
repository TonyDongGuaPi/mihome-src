package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.interfaces.IX5WebBackForwardList;

public class WebBackForwardList {

    /* renamed from: a  reason: collision with root package name */
    private IX5WebBackForwardList f9105a = null;
    private android.webkit.WebBackForwardList b = null;

    static WebBackForwardList a(android.webkit.WebBackForwardList webBackForwardList) {
        if (webBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList2 = new WebBackForwardList();
        webBackForwardList2.b = webBackForwardList;
        return webBackForwardList2;
    }

    static WebBackForwardList a(IX5WebBackForwardList iX5WebBackForwardList) {
        if (iX5WebBackForwardList == null) {
            return null;
        }
        WebBackForwardList webBackForwardList = new WebBackForwardList();
        webBackForwardList.f9105a = iX5WebBackForwardList;
        return webBackForwardList;
    }

    public int getCurrentIndex() {
        return this.f9105a != null ? this.f9105a.getCurrentIndex() : this.b.getCurrentIndex();
    }

    public WebHistoryItem getCurrentItem() {
        return this.f9105a != null ? WebHistoryItem.a(this.f9105a.getCurrentItem()) : WebHistoryItem.a(this.b.getCurrentItem());
    }

    public WebHistoryItem getItemAtIndex(int i) {
        return this.f9105a != null ? WebHistoryItem.a(this.f9105a.getItemAtIndex(i)) : WebHistoryItem.a(this.b.getItemAtIndex(i));
    }

    public int getSize() {
        return this.f9105a != null ? this.f9105a.getSize() : this.b.getSize();
    }
}
