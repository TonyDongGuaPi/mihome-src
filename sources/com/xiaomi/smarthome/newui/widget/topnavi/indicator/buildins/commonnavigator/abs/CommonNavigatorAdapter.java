package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins.commonnavigator.abs;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

public abstract class CommonNavigatorAdapter {

    /* renamed from: a  reason: collision with root package name */
    private final DataSetObservable f20930a = new DataSetObservable();

    public abstract int a();

    public abstract IPagerIndicator a(Context context);

    public abstract IPagerTitleView a(Context context, int i);

    public float b(Context context, int i) {
        return 1.0f;
    }

    public final void a(DataSetObserver dataSetObserver) {
        this.f20930a.registerObserver(dataSetObserver);
    }

    public final void b(DataSetObserver dataSetObserver) {
        this.f20930a.unregisterObserver(dataSetObserver);
    }

    public final void b() {
        this.f20930a.notifyChanged();
    }

    public final void c() {
        this.f20930a.notifyInvalidated();
    }
}
