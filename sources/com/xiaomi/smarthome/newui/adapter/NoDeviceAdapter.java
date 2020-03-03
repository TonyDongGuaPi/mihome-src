package com.xiaomi.smarthome.newui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.multi_item.IAdapter;

public class NoDeviceAdapter extends IAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Context f20394a;

    public int a() {
        return 1;
    }

    public int getItemCount() {
        return 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public NoDeviceAdapter(Context context) {
        this.f20394a = context;
    }

    /* renamed from: a */
    public EmptyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new EmptyHolder(LayoutInflater.from(this.f20394a).inflate(R.layout.device_main_item_empty, viewGroup, false));
    }

    /* access modifiers changed from: protected */
    public void a(Rect rect, View view, int i, RecyclerView recyclerView, RecyclerView.State state) {
        int a2 = DisplayUtils.a(this.f20394a, 8.0f);
        rect.top = a2;
        rect.bottom = a2;
        int a3 = DisplayUtils.a(5.0f);
        rect.right = a3;
        rect.left = a3;
    }

    class EmptyHolder extends RecyclerView.ViewHolder {
        public EmptyHolder(View view) {
            super(view);
        }
    }
}
