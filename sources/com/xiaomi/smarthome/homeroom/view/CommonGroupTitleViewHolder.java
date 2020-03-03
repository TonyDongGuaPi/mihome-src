package com.xiaomi.smarthome.homeroom.view;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class CommonGroupTitleViewHolder extends BaseGroupViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f18357a;
    private TextView c;
    private TextView d;

    public void a(RecyclerView.Adapter adapter, int i) {
    }

    public CommonGroupTitleViewHolder(View view) {
        super(view);
        this.c = (TextView) view.findViewById(R.id.title_left);
        this.d = (TextView) view.findViewById(R.id.title_right);
        this.f18357a = view.findViewById(R.id.top_divider);
    }

    public void a(RecyclerView.Adapter adapter, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.setText(str);
        }
        this.d.setVisibility(8);
        if (this.f18357a != null) {
            this.f18357a.setVisibility(8);
        }
    }
}
