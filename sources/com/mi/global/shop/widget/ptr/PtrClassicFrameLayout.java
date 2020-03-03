package com.mi.global.shop.widget.ptr;

import android.content.Context;
import android.util.AttributeSet;

public class PtrClassicFrameLayout extends PtrFrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private PtrClassicFrameHeader f7247a;

    public PtrClassicFrameLayout(Context context) {
        super(context);
        a();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.f7247a = new PtrClassicFrameHeader(getContext());
        setHeaderView(this.f7247a);
        addPtrUIHandler(this.f7247a);
    }

    public PtrClassicFrameHeader getHeader() {
        return this.f7247a;
    }

    public void setLastUpdateTimeKey(String str) {
        if (this.f7247a != null) {
            this.f7247a.setLastUpdateTimeKey(str);
        }
    }

    public void setLastUpdateTimeRelateObject(Object obj) {
        if (this.f7247a != null) {
            this.f7247a.setLastUpdateTimeRelateObject(obj);
        }
    }

    public void setLastRefreshTime(String str) {
        if (this.f7247a != null) {
            this.f7247a.setLastRefreshTime(str);
        }
    }
}
