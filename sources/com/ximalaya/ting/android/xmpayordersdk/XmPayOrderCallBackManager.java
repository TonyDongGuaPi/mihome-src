package com.ximalaya.ting.android.xmpayordersdk;

import java.util.HashMap;
import java.util.Map;

public class XmPayOrderCallBackManager {

    /* renamed from: a  reason: collision with root package name */
    private static XmPayOrderCallBackManager f2396a;
    private Map<String, IXmPayOrderListener> b = new HashMap();

    public static XmPayOrderCallBackManager a() {
        if (f2396a == null) {
            synchronized (XmPayOrderCallBackManager.class) {
                if (f2396a == null) {
                    f2396a = new XmPayOrderCallBackManager();
                }
            }
        }
        return f2396a;
    }

    public IXmPayOrderListener a(String str) {
        return this.b.get(str);
    }

    public void a(String str, IXmPayOrderListener iXmPayOrderListener) {
        this.b.put(str, iXmPayOrderListener);
    }

    public void b() {
        this.b.clear();
        f2396a = null;
    }
}
