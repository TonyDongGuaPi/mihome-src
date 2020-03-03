package com.xiaomi.smarthome.newui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.crash.MainCrashHandler;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.multi_item.DelegateAdapter;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.adapter.DeviceMainEmptyAdapter;
import com.xiaomi.smarthome.newui.adapter.DeviceMainGridAdapterV2;
import com.xiaomi.smarthome.newui.adapter.DeviceMainLoadingAdapter;
import com.xiaomi.smarthome.newui.adapter.NoDeviceAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import java.util.List;

public class DeviceRecyclerOrigin extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20247a = "DeviceRecycler";
    /* access modifiers changed from: private */
    public DropMenuAdapter b;
    private MainOperationAdapter c;
    private boolean d;
    private DeviceMainEmptyAdapter e;
    /* access modifiers changed from: private */
    public DropMenuAdapter f;
    /* access modifiers changed from: private */
    public CurrentDisplayMode g;
    private DeviceMainGridAdapterV2 h;
    private DelegateAdapter i;
    /* access modifiers changed from: private */
    public DropMenuStateHelper j;
    private final BroadcastReceiver k;

    enum CurrentDisplayMode {
        LAYOUT_MANGER_GRID_2,
        LAYOUT_MANGER_LOADING,
        LAYOUT_MANGER_EMPTY_AD
    }

    public DeviceRecyclerOrigin(Context context) {
        this(context, (AttributeSet) null);
    }

    public DeviceRecyclerOrigin(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DeviceRecyclerOrigin(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.d = false;
        this.g = null;
        this.j = DropMenuStateHelper.a();
        this.k = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String action = intent.getAction();
                    if (TextUtils.equals("action_on_logout", action)) {
                        DeviceRecyclerOrigin.this.j.f();
                    } else if (TextUtils.equals(action, DropMenuAdapter.f20253a)) {
                        DeviceRecyclerOrigin.this.j.a(intent.getStringExtra(DropMenuAdapter.c));
                        DeviceRecyclerOrigin.this.a();
                    } else if (TextUtils.equals(action, DropMenuAdapter.b)) {
                        if (DeviceRecyclerOrigin.this.g == CurrentDisplayMode.LAYOUT_MANGER_GRID_2 && DeviceRecyclerOrigin.this.b != null) {
                            DeviceRecyclerOrigin.this.b.h();
                        }
                        if (DeviceRecyclerOrigin.this.g == CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD && DeviceRecyclerOrigin.this.f != null) {
                            DeviceRecyclerOrigin.this.f.h();
                        }
                    }
                }
            }
        };
        setItemViewCacheSize(5);
        setHasFixedSize(true);
        setNestedScrollingEnabled(true);
        setLayoutManager(new GridLayoutManager(getContext(), -1) {
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        setItemAnimator(defaultItemAnimator);
    }

    private void a(boolean z) {
        this.i = new DelegateAdapter();
        this.h = new DeviceMainGridAdapterV2(getContext());
        if (!z) {
            this.b = new DropMenuAdapter(getContext(), "DropMenuAdapter");
            this.c = new MainOperationAdapter();
            this.i.a((IAdapter) this.b);
            this.i.a((IAdapter) this.c);
        }
        this.i.a((IAdapter) this.h);
        this.i.b((IAdapter) new NoDeviceAdapter(getContext()));
    }

    public void refresh(List<PageBean.Classify> list) {
        this.j.a(list);
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.g == CurrentDisplayMode.LAYOUT_MANGER_GRID_2 && this.b != null) {
            this.b.b();
        }
        if (this.g == CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD && this.f != null) {
            this.f.b();
        }
    }

    public void forceRefreshDevice() {
        if (this.g == CurrentDisplayMode.LAYOUT_MANGER_GRID_2 && this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public void changeToGridMode(List<Device> list, int i2, String str, DeviceListPageActionInterface deviceListPageActionInterface, Object obj, boolean z) {
        boolean z2 = true;
        LogUtilGrey.a(f20247a, "changeToGridMode", true);
        if (this.g != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            a(false);
            setAdapter(this.i);
        }
        DeviceListPageActionInterface deviceListPageActionInterface2 = deviceListPageActionInterface;
        this.h.a(deviceListPageActionInterface);
        this.h.f();
        this.h.a(list, i2, str, obj, z);
        MainOperationAdapter mainOperationAdapter = this.c;
        if (i2 != 0) {
            z2 = false;
        }
        mainOperationAdapter.a(z2);
        this.g = CurrentDisplayMode.LAYOUT_MANGER_GRID_2;
    }

    public void changeToGridOnlyMode(List<Device> list, int i2, String str, DeviceListPageActionInterface deviceListPageActionInterface, Object obj) {
        LogUtilGrey.a(f20247a, "changeToGridModeNoNav", true);
        if (this.g != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            a(true);
            setAdapter(this.i);
        }
        this.h.a(deviceListPageActionInterface);
        this.h.f();
        this.h.a(list, i2, str, obj, false);
        this.g = CurrentDisplayMode.LAYOUT_MANGER_GRID_2;
    }

    public void changeToLoadingMode() {
        LogUtilGrey.a(f20247a, "changeToLoadingMode", true);
        if (this.g != CurrentDisplayMode.LAYOUT_MANGER_LOADING) {
            DelegateAdapter delegateAdapter = new DelegateAdapter();
            delegateAdapter.a((IAdapter) new DeviceMainLoadingAdapter(getContext()));
            setAdapter(delegateAdapter);
        }
        this.g = CurrentDisplayMode.LAYOUT_MANGER_LOADING;
    }

    public void changeToEmptyDeviceMode() {
        LogUtilGrey.a(f20247a, "changeToEmptyDeviceMode", true);
        if (this.g != CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD) {
            DelegateAdapter delegateAdapter = new DelegateAdapter();
            this.e = new DeviceMainEmptyAdapter(getContext());
            this.f = new DropMenuAdapter(getContext(), "DropMenuAdapterFroEmptyPage");
            delegateAdapter.a((IAdapter) this.f);
            delegateAdapter.a((IAdapter) this.e);
            setAdapter(delegateAdapter);
        } else {
            this.e.b();
        }
        this.g = CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD;
    }

    public CurrentDisplayMode getCurrentMode() {
        return this.g;
    }

    public int getDeviceItemCount() {
        if (this.g != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            return -1;
        }
        return this.h.getItemCount();
    }

    public void onEnterEditMode() {
        this.d = true;
        if (this.b != null) {
            this.b.c();
        }
        if (this.c != null) {
            this.c.c();
        }
    }

    public void onExitEditMode() {
        this.d = false;
        if (this.b != null) {
            this.b.f();
        }
        if (this.c != null) {
            this.c.f();
        }
    }

    public boolean onBackPressed() {
        if (this.b == null) {
            return false;
        }
        return this.b.g();
    }

    public void onScrollStateChanged(int i2) {
        if (i2 == 2) {
            try {
                Fresco.getImagePipeline().pause();
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    FrescoInitial.a(true);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    MainCrashHandler.a((Throwable) e3);
                }
            }
        } else {
            Fresco.getImagePipeline().resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(f20247a, "onAttachedToWindow: ");
        try {
            Fresco.getImagePipeline().resume();
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                FrescoInitial.a(true);
            } catch (Exception e3) {
                e3.printStackTrace();
                MainCrashHandler.a((Throwable) e3);
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DropMenuAdapter.f20253a);
        intentFilter.addAction("action_on_logout");
        intentFilter.addAction(DropMenuAdapter.b);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.k, intentFilter);
    }

    public void onHomeChange() {
        this.j.f();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(f20247a, "onDetachedFromWindow: ");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.k);
        this.j.f();
    }

    public void destory() {
        if (this.h != null) {
            this.h.g();
        }
    }
}
