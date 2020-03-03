package com.xiaomi.smarthome.newui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.miio.page.devicelistadv.DeviceListEmptyAdView;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.operation.OperationCollector;
import com.xiaomi.smarthome.operation.indexdefault.IndexNoDeviceOperation;
import java.util.List;

public class DeviceMainEmptyAdapter extends IAdapter {
    private static final String c = "DeviceMainEmptyAdapter";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public IndexNoDeviceOperation f20377a;
    private Context b;
    private long d = 0;
    /* access modifiers changed from: private */
    public boolean e = true;
    private int f;
    /* access modifiers changed from: private */
    public Device g;

    public int a() {
        return 1;
    }

    public int getItemCount() {
        return 1;
    }

    public DeviceMainEmptyAdapter(Context context) {
        this.b = context;
        if ((this.b instanceof FragmentActivity) && !HomeManager.A()) {
            this.f20377a = OperationCollector.b((FragmentActivity) this.b);
        }
    }

    /* renamed from: a */
    public EmptyAdHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.e = c();
        return a(viewGroup);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((EmptyAdHolder) viewHolder).a();
    }

    @NonNull
    private EmptyAdHolder a(ViewGroup viewGroup) {
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.b).inflate(R.layout.device_list_recommend_adv_layout, viewGroup, false);
        frameLayout.addView(new DeviceListEmptyAdView(this.b));
        return new EmptyAdHolder(frameLayout);
    }

    public void b() {
        boolean c2 = c();
        int a2 = SHApplication.getStateNotifier().a();
        if (!(this.e == c2 && this.f == a2)) {
            this.e = c2;
            this.f = a2;
            notifyDataSetChanged();
            if (this.f20377a != null) {
                this.f20377a.h();
            }
        }
        if (this.f20377a != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.d >= 1000) {
                this.d = currentTimeMillis;
                this.f20377a.h();
                return;
            }
            LogUtil.a(c, "refresh: ignore this refresh");
        }
    }

    private boolean c() {
        GridViewData gridViewData;
        if (SHApplication.getStateNotifier().a() != 4) {
            return true;
        }
        List<GridViewData> F = HomeManager.a().F();
        if (F.isEmpty() || F.size() != 1 || (gridViewData = F.get(0)) == null || gridViewData.b == null) {
            return true;
        }
        this.g = gridViewData.b;
        return !IRDeviceUtil.a(this.g.did);
    }

    class EmptyAdHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        DeviceListEmptyAdView f20378a;

        public EmptyAdHolder(ViewGroup viewGroup) {
            super(viewGroup);
            this.f20378a = (DeviceListEmptyAdView) viewGroup.getChildAt(0);
        }

        public void a() {
            if (DeviceMainEmptyAdapter.this.f20377a != null) {
                DeviceMainEmptyAdapter.this.f20377a.a(this.f20378a);
            }
            this.f20378a.updateIrOrEmptyView(DeviceMainEmptyAdapter.this.e, DeviceMainEmptyAdapter.this.g);
        }
    }
}
