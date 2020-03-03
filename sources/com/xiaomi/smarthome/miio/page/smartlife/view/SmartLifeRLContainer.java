package com.xiaomi.smarthome.miio.page.smartlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.miio.page.smartlife.SmartLifeItem;
import com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class SmartLifeRLContainer extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private Map<Integer, WeakReference<SmartLifeCircleView>> f19941a = new HashMap();
    private SmartLifeCircleView.SelectListener b = new SmartLifeCircleView.SelectListener() {
        public void a(boolean z, SmartLifeItem smartLifeItem) {
            if (SmartLifeRLContainer.this.c != null) {
                SmartLifeRLContainer.this.c.a(z, smartLifeItem);
            }
        }
    };
    /* access modifiers changed from: private */
    public SmartLifeCircleView.SelectListener c;

    public SmartLifeRLContainer(Context context) {
        super(context);
    }

    public SmartLifeRLContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public SmartLifeRLContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r2 = (com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView) r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a() {
        /*
            r6 = this;
            int r0 = r6.getChildCount()
            r1 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x0031
            android.view.View r2 = r6.getChildAt(r1)
            boolean r3 = r2 instanceof com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView
            if (r3 != 0) goto L_0x0010
            goto L_0x002e
        L_0x0010:
            com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView r2 = (com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView) r2
            com.xiaomi.smarthome.miio.page.smartlife.SmartLifeItem r3 = r2.getData()
            if (r3 != 0) goto L_0x0019
            goto L_0x002e
        L_0x0019:
            com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView$SelectListener r4 = r6.b
            r2.setOnSelectListener(r4)
            java.util.Map<java.lang.Integer, java.lang.ref.WeakReference<com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView>> r4 = r6.f19941a
            int r3 = r3.f19933a
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.ref.WeakReference r5 = new java.lang.ref.WeakReference
            r5.<init>(r2)
            r4.put(r3, r5)
        L_0x002e:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeRLContainer.a():void");
    }

    public void updateState(SmartLifeItem smartLifeItem, boolean z) {
        WeakReference weakReference;
        SmartLifeCircleView smartLifeCircleView;
        if (smartLifeItem != null && (weakReference = this.f19941a.get(Integer.valueOf(smartLifeItem.f19933a))) != null && (smartLifeCircleView = (SmartLifeCircleView) weakReference.get()) != null) {
            smartLifeCircleView.updateState(z);
        }
    }

    public void setOnSelectListener(SmartLifeCircleView.SelectListener selectListener) {
        this.c = selectListener;
    }

    public void startAnim() {
        SmartLifeCircleView smartLifeCircleView;
        for (WeakReference next : this.f19941a.values()) {
            if (!(next == null || (smartLifeCircleView = (SmartLifeCircleView) next.get()) == null)) {
                smartLifeCircleView.startAnimation();
            }
        }
    }

    public void stopAnim() {
        SmartLifeCircleView smartLifeCircleView;
        for (WeakReference next : this.f19941a.values()) {
            if (!(next == null || (smartLifeCircleView = (SmartLifeCircleView) next.get()) == null)) {
                smartLifeCircleView.stopAnim();
            }
        }
    }
}
