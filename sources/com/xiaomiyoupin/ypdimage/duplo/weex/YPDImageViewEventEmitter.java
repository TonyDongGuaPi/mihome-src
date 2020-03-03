package com.xiaomiyoupin.ypdimage.duplo.weex;

import com.taobao.weex.ui.component.WXComponent;
import com.xiaomiyoupin.ypdimage.YPDSource;
import java.util.HashMap;
import java.util.Map;

class YPDImageViewEventEmitter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1795a = "onLoadEvent";
    private WXComponent b;
    private YPDSource.OnLoadEvent c = new OnLoadEventImpl(this);

    YPDImageViewEventEmitter(WXComponent wXComponent) {
        this.b = wXComponent;
    }

    /* access modifiers changed from: package-private */
    public YPDSource.OnLoadEvent a() {
        return this.c;
    }

    private static class OnLoadEventImpl implements YPDSource.OnLoadEvent {

        /* renamed from: a  reason: collision with root package name */
        private YPDImageViewEventEmitter f1796a;

        public OnLoadEventImpl(YPDImageViewEventEmitter yPDImageViewEventEmitter) {
            this.f1796a = yPDImageViewEventEmitter;
        }

        public void a() {
            this.f1796a.c();
        }

        public void b() {
            this.f1796a.d();
        }

        public void c() {
            this.f1796a.b();
        }

        public void a(float f, float f2) {
            this.f1796a.a(f, f2);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", "onLoadStart");
        a(f1795a, (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: private */
    public void a(float f, float f2) {
        HashMap hashMap = new HashMap();
        hashMap.put("current", Float.valueOf(f));
        hashMap.put("total", Float.valueOf(f2));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("name", "onLoadProgress");
        hashMap2.put("params", hashMap);
        a(f1795a, (Map<String, Object>) hashMap2);
    }

    /* access modifiers changed from: private */
    public void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", "onLoadFinish");
        a(f1795a, (Map<String, Object>) hashMap);
    }

    /* access modifiers changed from: private */
    public void d() {
        HashMap hashMap = new HashMap();
        hashMap.put("code", -1);
        hashMap.put("message", "图片加载失败");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("error", hashMap);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("name", "onLoadFailed");
        hashMap3.put("params", hashMap2);
        a(f1795a, (Map<String, Object>) hashMap3);
    }

    private void a(String str, Map<String, Object> map) {
        if (this.b != null) {
            this.b.fireEvent(str, map);
        }
    }
}
