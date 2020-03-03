package com.andview.refreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.andview.refreshview.callback.IFooterCallBack;

public class XRefreshViewFooter extends LinearLayout implements IFooterCallBack {

    /* renamed from: a  reason: collision with root package name */
    private Context f4773a;
    private View b;
    private View c;
    private TextView d;
    private TextView e;
    private boolean f = true;

    public XRefreshViewFooter(Context context) {
        super(context);
        a(context);
    }

    public XRefreshViewFooter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public void callWhenNotAutoLoadMore(final XRefreshView xRefreshView) {
        this.e.setText(R.string.xrefreshview_footer_hint_click);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                xRefreshView.notifyLoadMore();
            }
        });
    }

    public void onStateReady() {
        this.d.setVisibility(8);
        this.c.setVisibility(8);
        this.e.setText(R.string.xrefreshview_footer_hint_click);
        this.e.setVisibility(0);
    }

    public void onStateRefreshing() {
        this.d.setVisibility(8);
        this.c.setVisibility(0);
        this.e.setVisibility(8);
        show(true);
    }

    public void onReleaseToLoadMore() {
        this.d.setVisibility(8);
        this.c.setVisibility(8);
        this.e.setText(R.string.xrefreshview_footer_hint_release);
        this.e.setVisibility(0);
    }

    public void onStateFinish(boolean z) {
        if (z) {
            this.d.setText(R.string.xrefreshview_footer_hint_normal);
        } else {
            this.d.setText(R.string.xrefreshview_footer_hint_fail);
        }
        this.d.setVisibility(0);
        this.c.setVisibility(8);
        this.e.setVisibility(8);
    }

    public void onStateComplete() {
        this.d.setText(R.string.xrefreshview_footer_hint_complete);
        this.d.setVisibility(0);
        this.c.setVisibility(8);
        this.e.setVisibility(8);
    }

    public void show(boolean z) {
        if (z != this.f) {
            this.f = z;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams.height = z ? -2 : 0;
            this.b.setLayoutParams(layoutParams);
        }
    }

    public boolean isShowing() {
        return this.f;
    }

    private void a(Context context) {
        this.f4773a = context;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.f4773a).inflate(R.layout.xrefreshview_footer, this);
        viewGroup.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.b = viewGroup.findViewById(R.id.xrefreshview_footer_content);
        this.c = viewGroup.findViewById(R.id.xrefreshview_footer_progressbar);
        this.d = (TextView) viewGroup.findViewById(R.id.xrefreshview_footer_hint_textview);
        this.e = (TextView) viewGroup.findViewById(R.id.xrefreshview_footer_click_textview);
    }

    public int getFooterHeight() {
        return getMeasuredHeight();
    }
}
