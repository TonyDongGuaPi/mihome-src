package com.xiaomi.smarthome.newui.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.multi_item.IAdapter;
import java.lang.ref.WeakReference;

public class DeviceMainLoadingAdapter extends IAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final int f20383a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private WeakReference<Context> e;
    /* access modifiers changed from: private */
    public int f = 1;
    private BroadcastReceiver g;

    /* access modifiers changed from: protected */
    public int a() {
        return 1;
    }

    public int getItemCount() {
        return 1;
    }

    static class LoginReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<DeviceMainLoadingAdapter> f20386a;

        public LoginReceiver(DeviceMainLoadingAdapter deviceMainLoadingAdapter) {
            this.f20386a = new WeakReference<>(deviceMainLoadingAdapter);
        }

        public void onReceive(Context context, Intent intent) {
            DeviceMainLoadingAdapter deviceMainLoadingAdapter = (DeviceMainLoadingAdapter) this.f20386a.get();
            if (deviceMainLoadingAdapter != null) {
                deviceMainLoadingAdapter.c();
                deviceMainLoadingAdapter.notifyDataSetChanged();
            }
        }
    }

    public DeviceMainLoadingAdapter(Context context) {
        this.e = new WeakReference<>(context);
        this.g = new LoginReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_on_login_success");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.g, intentFilter);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = (Context) this.e.get();
        if (context != null) {
            return new EmptyAdHolder((ViewGroup) LayoutInflater.from(context).inflate(R.layout.device_list_loading_layout, viewGroup, false));
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof EmptyAdHolder) {
            ((EmptyAdHolder) viewHolder).a();
        }
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        b();
    }

    private void b() {
        if (this.g != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.g);
            this.g = null;
        }
    }

    private class EmptyAdHolder extends RecyclerView.ViewHolder {
        private TextView b;
        private ImageView c;
        private boolean d = false;

        public EmptyAdHolder(ViewGroup viewGroup) {
            super(viewGroup);
            this.b = (TextView) viewGroup.findViewById(R.id.loading_tips);
            this.c = (ImageView) viewGroup.findViewById(R.id.loading);
            this.b.setTextColor(this.b.getContext().getResources().getColor(R.color.main_banner_text_color_2));
        }

        public void a() {
            if (!this.d) {
                ((AnimationDrawable) this.c.getDrawable()).start();
                this.d = true;
            }
            DeviceMainLoadingAdapter.this.c();
            b();
        }

        private void b() {
            switch (DeviceMainLoadingAdapter.this.f) {
                case 1:
                    this.b.setText(R.string.main_page_loading_state_initialing);
                    return;
                case 2:
                    this.b.setText(R.string.main_page_loading_state_logging);
                    return;
                case 3:
                    this.b.setText(R.string.main_page_loading_state_retrieving);
                    return;
                default:
                    this.b.setText("");
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!CoreApi.a().l()) {
            a(1);
            CoreApi.a().a((Context) this.e.get(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    DeviceMainLoadingAdapter.this.a(2);
                }
            });
            return;
        }
        int a2 = SHApplication.getStateNotifier().a();
        if (a2 == 2 || a2 == 1) {
            a(2);
        } else if (a2 == 3) {
            a(0);
        } else if (a2 == 4) {
            a(3);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        this.f = i;
    }
}
