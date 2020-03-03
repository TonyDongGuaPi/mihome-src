package com.xiaomi.smarthome.newui.widget.topnavi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.DropMenuAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20913a = "RoomAdapter";
    private List<PageBean.Classify> b;
    /* access modifiers changed from: private */
    public PageBean c;
    private Context d;
    /* access modifiers changed from: private */
    public TextView e = null;

    public RoomAdapter(Context context, List<PageBean.Classify> list, PageBean pageBean) {
        this.d = context;
        this.b = list;
        this.c = pageBean;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RoomHolder(new FlowLayoutCopy(this.d));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RoomHolder roomHolder = (RoomHolder) viewHolder;
        PageBean.Classify classify = this.b.get(i);
        FlowLayoutCopy a2 = roomHolder.f20915a;
        Context context = a2.getContext();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) a2.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
            a2.setLayoutParams(marginLayoutParams);
        }
        int i2 = 0;
        if (i == 0) {
            marginLayoutParams.topMargin = DisplayUtils.a(8.0f);
            if (getItemCount() == 1) {
                i2 = DisplayUtils.a(14.0f);
            }
            marginLayoutParams.bottomMargin = i2;
        } else if (i == getItemCount() - 1) {
            marginLayoutParams.topMargin = DisplayUtils.a(18.0f);
            marginLayoutParams.bottomMargin = DisplayUtils.a(14.0f);
        } else {
            marginLayoutParams.topMargin = DisplayUtils.a(14.0f);
            marginLayoutParams.bottomMargin = 0;
        }
        a2.removeAllViews();
        for (PageBean a3 : classify.b) {
            roomHolder.f20915a.addView(a(context, a3, (ViewGroup) roomHolder.f20915a));
        }
    }

    @NonNull
    private TextView a(Context context, final PageBean pageBean, ViewGroup viewGroup) {
        final TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.room_flow_item, viewGroup, false);
        textView.setText(pageBean.e != null ? pageBean.e : "");
        if (this.c == null || TextUtils.isEmpty(this.c.f) || !pageBean.f.equals(this.c.f)) {
            textView.setSelected(false);
        } else {
            textView.setSelected(true);
        }
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextView a2 = RoomAdapter.this.e;
                if (!(a2 == null || a2 == view)) {
                    a2.setSelected(false);
                }
                TextView unused = RoomAdapter.this.e = (TextView) view;
                PageBean unused2 = RoomAdapter.this.c = pageBean;
                textView.setSelected(true);
                RoomAdapter.this.a(pageBean.f, pageBean.e);
                a();
            }

            private void a() {
                try {
                    if (pageBean.h) {
                        STAT.d.g(4);
                    } else if (HomeManager.h.equals(pageBean.f)) {
                        STAT.d.g(1);
                    } else if (HomeManager.e.equals(pageBean.f)) {
                        STAT.d.g(2);
                    } else if (HomeManager.f.equals(pageBean.f)) {
                        STAT.d.g(3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return textView;
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        Intent intent = new Intent(DropMenuAdapter.f20253a);
        intent.putExtra(DropMenuAdapter.c, str);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        this.e = null;
    }

    public String a(int i) {
        return this.b.get(i).f20912a;
    }

    public int getItemCount() {
        return this.b.size();
    }

    static class RoomHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public FlowLayoutCopy f20915a;

        public RoomHolder(View view) {
            super(view);
            this.f20915a = (FlowLayoutCopy) view;
            this.f20915a.setSingleLine(false);
            this.f20915a.setItemSpacing(DisplayUtils.a(10.0f));
            this.f20915a.setLineSpacing(DisplayUtils.a(14.0f));
        }
    }
}
