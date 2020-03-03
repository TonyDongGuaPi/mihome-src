package com.mics.widget.SpringView.container;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mics.R;

public class DefaultFooter extends BaseFooter {

    /* renamed from: a  reason: collision with root package name */
    private Context f7817a;
    private int b;
    private TextView c;
    private ProgressBar d;

    public void a(View view, int i) {
    }

    public void d(View view) {
    }

    public DefaultFooter(Context context) {
        this(context, R.drawable.mics_progress_small);
    }

    public DefaultFooter(Context context, int i) {
        this.f7817a = context;
        this.b = i;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.mics_default_footer, viewGroup, true);
        this.c = (TextView) inflate.findViewById(R.id.default_footer_title);
        this.d = (ProgressBar) inflate.findViewById(R.id.default_footer_progressbar);
        this.d.setIndeterminateDrawable(ContextCompat.getDrawable(this.f7817a, this.b));
        return inflate;
    }

    public void a(View view, boolean z) {
        if (z) {
            this.c.setText("松开载入更多");
        } else {
            this.c.setText("查看更多");
        }
    }

    public void a() {
        this.c.setVisibility(4);
        this.d.setVisibility(0);
    }

    public void b() {
        this.c.setText("查看更多");
        this.c.setVisibility(0);
        this.d.setVisibility(4);
    }
}
