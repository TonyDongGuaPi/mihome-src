package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.page.BaseFragment;

public class DeviceTagFragment extends BaseFragment implements SmartHomeDeviceManager.IClientDeviceListener {
    public static final String b = "drawer";
    public static final String c = "manager";
    public static final String d = "editor";
    public static final String e = "batch";

    /* renamed from: a  reason: collision with root package name */
    private BroadcastReceiver f19813a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(DeviceTagInterface.x, intent.getAction()) || TextUtils.equals(DeviceTagInterface.h, intent.getAction())) {
                DeviceTagFragment.this.g();
            } else if (TextUtils.equals(DeviceTagInterface.y, intent.getAction())) {
                DeviceTagFragment.this.h();
            }
        }
    };
    public View f;
    protected RecyclerView g;
    protected DeviceTagAdapter h;
    protected RecyclerViewExpandableItemManager i;
    protected RecyclerViewTouchActionGuardManager j;
    protected RecyclerViewDragDropManager k;
    protected RecyclerView.Adapter l;
    protected LinearLayoutManager m;
    protected boolean n = false;

    public void a(int i2, Device device) {
    }

    public void a(RecyclerView recyclerView, int i2) {
    }

    public void b(int i2) {
    }

    /* access modifiers changed from: protected */
    public void e() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f == null) {
            this.f = a(layoutInflater, viewGroup);
            this.g = (RecyclerView) this.f.findViewById(R.id.recycler_view);
            this.g.setOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i != 0) {
                        FragmentActivity activity = DeviceTagFragment.this.getActivity();
                        if (activity != null && DeviceTagFragment.this.isValid()) {
                            View currentFocus = activity.getCurrentFocus();
                            if (currentFocus != null) {
                                currentFocus.clearFocus();
                            }
                        } else {
                            return;
                        }
                    }
                    DeviceTagFragment.this.a(recyclerView, i);
                }
            });
            this.i = new RecyclerViewExpandableItemManager((Parcelable) null);
            this.k = new RecyclerViewDragDropManager();
            this.h = a();
            this.l = this.i.a((RecyclerView.Adapter) this.h);
            this.l = d();
            this.m = new LinearLayoutManager(getActivity());
            SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
            swipeDismissItemAnimator.setSupportsChangeAnimations(false);
            this.g.setLayoutManager(this.m);
            this.g.setAdapter(this.l);
            this.g.setItemAnimator(swipeDismissItemAnimator);
            this.g.setHasFixedSize(false);
            this.j = new RecyclerViewTouchActionGuardManager();
            this.j.b(true);
            this.j.a(true);
            this.j.a(this.g);
            e();
            this.i.a(this.g);
            M_();
            SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) this);
            IntentFilter intentFilter = new IntentFilter(DeviceTagInterface.x);
            intentFilter.addAction(DeviceTagInterface.h);
            intentFilter.addAction(DeviceTagInterface.y);
            LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.f19813a, intentFilter);
        }
        return this.f;
    }

    /* access modifiers changed from: protected */
    public DeviceTagAdapter a() {
        return new DeviceTagAdapter(getActivity(), getTag());
    }

    /* access modifiers changed from: protected */
    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.device_tag_manager_layout, viewGroup);
    }

    /* access modifiers changed from: protected */
    public RecyclerView.Adapter d() {
        return this.l;
    }

    public void onResume() {
        super.onResume();
        f();
    }

    /* access modifiers changed from: protected */
    public void f() {
        if (this.n) {
            this.h.g();
            this.n = false;
            M_();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.f19813a);
    }

    /* access modifiers changed from: protected */
    public void M_() {
        this.i.d();
    }

    public void a(int i2) {
        this.n = true;
    }

    /* access modifiers changed from: protected */
    public void g() {
        this.h.g();
        this.n = false;
        M_();
    }

    /* access modifiers changed from: protected */
    public void h() {
        g();
    }
}
