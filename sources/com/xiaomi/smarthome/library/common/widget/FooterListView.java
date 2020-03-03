package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.facebook.react.uimanager.ViewProps;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class FooterListView extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f18840a;
    private View b;
    private View c;
    private int d;
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
        }
    };

    public FooterListView(Context context) {
        super(context);
        init();
    }

    public FooterListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public FooterListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.f18840a = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.pull_footer, (ViewGroup) null);
        super.addFooterView(this.f18840a);
        this.b = this.f18840a.findViewById(R.id.pull_footer);
        this.d = DisplayUtils.a(13.3f);
    }

    public void addFooterView(View view) {
        removeFooterView(this.f18840a);
        super.addFooterView(this.f18840a);
        super.addFooterView(view);
        refreshRooterView("addFooterView");
        this.c = view;
    }

    public void removeFooterView() {
        removeFooterView(this.c);
        removeFooterView(this.f18840a);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        refreshRooterView("onFinishInflate");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Log.d(getClass().getSimpleName(), ViewProps.ON_LAYOUT);
        super.onLayout(z, i, i2, i3, i4);
    }

    public void refreshRooterView(String str) {
        int heightWithoutFooter = getHeightWithoutFooter();
        if (getHeight() - heightWithoutFooter > DisplayUtils.a(13.3f)) {
            this.d = getHeight() - heightWithoutFooter;
        } else {
            this.d = DisplayUtils.a(13.3f);
        }
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        layoutParams.height = this.d;
        this.b.setLayoutParams(layoutParams);
    }

    public int getHeightWithoutFooter() {
        if (getAdapter() == null) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (!childAt.equals(this.f18840a)) {
                i += childAt.getHeight();
            }
        }
        return i;
    }
}
