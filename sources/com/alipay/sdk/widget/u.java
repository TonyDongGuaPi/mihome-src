package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

public class u {

    /* renamed from: a  reason: collision with root package name */
    private Stack<WebViewWindow> f1160a = new Stack<>();

    public WebViewWindow a() {
        return this.f1160a.pop();
    }

    public void a(WebViewWindow webViewWindow) {
        this.f1160a.push(webViewWindow);
    }

    public boolean b() {
        return this.f1160a.isEmpty();
    }

    public void c() {
        if (!b()) {
            Iterator it = this.f1160a.iterator();
            while (it.hasNext()) {
                ((WebViewWindow) it.next()).a();
            }
            this.f1160a.clear();
        }
    }
}
