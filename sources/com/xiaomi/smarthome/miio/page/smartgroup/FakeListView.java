package com.xiaomi.smarthome.miio.page.smartgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;

public class FakeListView extends AnimateLinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19889a = "FakeListView";
    private BaseAdapter b;

    public FakeListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        this.b = baseAdapter;
        refreshUI();
    }

    public void refreshUI() {
        if (this.b != null) {
            removeAllViews();
            for (int i = 0; i < this.b.getCount(); i++) {
                try {
                    addView(this.b.getView(i, (View) null, this), new LinearLayout.LayoutParams(-1, DisplayUtils.a(90.0f)));
                } catch (Throwable th) {
                    String str = f19889a;
                    Log.e(str, "" + th);
                }
            }
        }
    }
}
