package com.xiaomi.smarthome.library.common.widget.viewflow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class LoadingMoreView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f19079a;
    private TextView b;
    private ViewGroup c;
    private View d;

    public LoadingMoreView(Context context) {
        super(context);
        a(context);
    }

    public LoadingMoreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LoadingMoreView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a() {
        this.c = (ViewGroup) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.load_more_layout, this, true);
        this.f19079a = (LinearLayout) this.c.findViewById(R.id.load_more);
        this.b = (TextView) this.c.findViewById(R.id.load_tip);
        this.d = this.c.findViewById(R.id.click_load_more);
    }

    private void a(Context context) {
        a();
    }

    public void displayLoadingTips(int i, boolean z) {
        setVisibility(0);
        this.d.setVisibility(8);
        if (z) {
            this.f19079a.setVisibility(0);
            this.b.setVisibility(8);
            return;
        }
        this.f19079a.setVisibility(8);
        this.b.setVisibility(0);
        if (i > 0) {
            this.b.setText(R.string.load_done);
        } else {
            this.b.setText(R.string.no_data_tips);
        }
    }

    public void displayClickLoadMore(View.OnClickListener onClickListener) {
        setVisibility(0);
        this.d.setVisibility(0);
        this.f19079a.setVisibility(8);
        this.b.setVisibility(8);
        setOnClickListener(onClickListener);
    }

    public void displayLoding() {
        setVisibility(0);
        this.f19079a.setVisibility(0);
        this.b.setVisibility(8);
        this.d.setVisibility(8);
        setOnClickListener((View.OnClickListener) null);
    }

    public void displayError(String str, View.OnClickListener onClickListener) {
        setVisibility(0);
        this.f19079a.setVisibility(8);
        this.b.setVisibility(0);
        this.b.setText(str);
        this.d.setVisibility(8);
        setOnClickListener(onClickListener);
    }

    public void displayError(int i, View.OnClickListener onClickListener) {
        setVisibility(0);
        this.f19079a.setVisibility(8);
        this.b.setVisibility(0);
        this.b.setText(i);
        this.d.setVisibility(8);
        setOnClickListener(onClickListener);
    }
}
