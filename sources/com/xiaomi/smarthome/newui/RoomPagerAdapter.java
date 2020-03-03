package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.DeviceListAssemble;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.newui.widget.topnavi.HomeDeviceShowEvent;
import com.xiaomi.smarthome.newui.widget.topnavi.HomeRoomShowEvent;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.newui.widget.topnavi.StickEvent;
import com.xiaomi.smarthome.newui.widget.topnavi.StickLogConsumer;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.MyViewPager;
import com.xiaomi.smarthome.operation.indexdefault.IndexNoDeviceOperation;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class RoomPagerAdapter extends PagerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20366a = "action_device_tab_double_click";
    private static final String b = "MVI-RoomPagerAdapter";
    private WeakReference<DeviceMainPage> c = new WeakReference<>((Object) null);
    private List<PageBean.Classify> d = new ArrayList();
    private List<PageBean> e = new ArrayList();
    private final Context f;
    private MyViewPager g;
    private RecyclerView h;
    private final DeviceRecycler i;
    private final RoomRecycler j;
    private boolean k = false;
    private boolean l = false;
    private PageBean m = null;
    private int n = -1;
    private DeviceListPageActionInterface o;
    private boolean p = false;

    public int getCount() {
        return 2;
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return obj == view;
    }

    public void startUpdate(ViewGroup viewGroup) {
    }

    public RoomPagerAdapter(Context context, MyViewPager myViewPager, DeviceListPageActionInterface deviceListPageActionInterface) {
        this.f = context;
        this.g = myViewPager;
        this.i = new DeviceRecycler(this.f);
        this.i.mRoomPagerAdapter = this;
        this.i.setPage(deviceListPageActionInterface);
        this.j = new RoomRecycler(this.f);
        this.o = deviceListPageActionInterface;
        StickLogConsumer.f20916a.b(DarkModeCompat.a(context));
    }

    public void a() {
        LogUtilGrey.a(b, "onShouldReloading: isDataReady: " + this.l);
        if (this.l) {
            this.l = false;
            this.m = null;
            if (this.n == 0) {
                StickLogConsumer.f20916a.a((StickEvent) HomeDeviceShowEvent.f20907a);
            } else {
                StickLogConsumer.f20916a.a((StickEvent) HomeRoomShowEvent.f20910a);
            }
            StickLogConsumer.f20916a.c(true);
            StickLogConsumer.f20916a.c(false);
            notifyDataSetChanged();
            LogUtilGrey.a(b, "onShouldReloading: ");
        }
        this.i.onHomeChange();
    }

    public void b() {
        DeviceMainPage deviceMainPage = (DeviceMainPage) this.c.get();
        LogUtilGrey.a(b, "onDataAvailable " + deviceMainPage);
        if (!this.l && deviceMainPage != null) {
            StickLogConsumer.f20916a.c(true);
            this.l = true;
            deviceMainPage.l();
            LogUtilGrey.a(b, "onDataAvailable: ");
        }
    }

    public void a(List<PageBean.Classify> list) {
        LogUtilGrey.a(b, "changeToEmptyDeviceMode");
        this.l = true;
        notifyDataSetChanged();
    }

    private boolean k() {
        int a2 = SHApplication.getStateNotifier().a();
        LogUtilGrey.a(b, "checkShowEmptyDeviceAd: currentState: " + a2);
        if (a2 == 3) {
            LogUtilGrey.a(b, "checkShowEmptyDeviceAd: not login");
            return true;
        } else if (a2 == 2 || a2 == 1) {
            return false;
        } else {
            boolean E = HomeManager.a().E();
            LogUtilGrey.a(b, "checkShowEmptyDeviceAd: inEmptyHome: " + E);
            return E;
        }
    }

    public void a(List<PageBean.Classify> list, boolean z, boolean z2) {
        LogUtilGrey.a(b, "refresh: isDataReady: " + z);
        this.l = z;
        if (this.l) {
            if (this.m == null) {
                this.m = PageBean.a();
            }
            if (this.n == 0) {
                if (!this.p) {
                    this.p = true;
                    this.g.postDelayed(new Runnable() {
                        public void run() {
                            DeviceMainPage deviceMainPage = (DeviceMainPage) RoomPagerAdapter.this.e().get();
                            if (deviceMainPage != null && deviceMainPage.isValid()) {
                                RoomPagerAdapter.this.l();
                            }
                        }
                    }, 1000);
                }
            } else if (this.n == 1) {
                l();
            }
            this.i.refresh(list);
        } else {
            LogUtilGrey.a(b, "refresh checkShowEmptyDeviceAd ");
            DeviceMainPage deviceMainPage = (DeviceMainPage) this.c.get();
            if (deviceMainPage != null) {
                deviceMainPage.m();
            }
        }
        if (!this.d.equals(list)) {
            this.d.clear();
            this.e.clear();
            this.d.addAll(list);
            for (PageBean.Classify classify : this.d) {
                List<PageBean> list2 = classify.b;
                if (list2 != null && list2.size() > 0) {
                    this.e.addAll(list2);
                }
            }
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        View view;
        if (i2 == 0) {
            view = this.i;
        } else {
            view = this.j;
        }
        if (viewGroup.indexOfChild(view) == -1) {
            viewGroup.addView(view, -1);
        }
        if (!this.l) {
            return view;
        }
        a(i2);
        return view;
    }

    private void a(int i2) {
        if (this.l) {
            if (i2 == 0) {
                b(false);
            } else {
                l();
            }
        }
    }

    private void b(boolean z) {
        boolean k2 = k();
        LogUtilGrey.a(b, "refershDevicePage: showEmptyDeviceAd: " + k2 + " ;isDataReady: " + this.l + " ;mCurrentPage:" + this.m);
        if (k2) {
            this.i.changeToEmptyDeviceMode();
        } else if (this.m != null) {
            PageBean pageBean = this.m;
            String str = pageBean.f;
            if (!TextUtils.isEmpty(str)) {
                int i2 = 3;
                boolean z2 = true;
                if (!pageBean.h) {
                    char c2 = 65535;
                    int hashCode = str.hashCode();
                    if (hashCode != -2077299665) {
                        if (hashCode != -252753263) {
                            if (hashCode != 491886639) {
                                if (hashCode == 1189320177 && str.equals(HomeManager.h)) {
                                    c2 = 0;
                                }
                            } else if (str.equals(HomeManager.e)) {
                                c2 = 2;
                            }
                        } else if (str.equals(HomeManager.d)) {
                            c2 = 1;
                        }
                    } else if (str.equals(HomeManager.f)) {
                        c2 = 3;
                    }
                    switch (c2) {
                        case 0:
                            i2 = 0;
                            break;
                        case 1:
                            i2 = 5;
                            break;
                        case 2:
                            i2 = 1;
                            break;
                        case 3:
                            i2 = 2;
                            break;
                        default:
                            i2 = 4;
                            break;
                    }
                    String str2 = pageBean.e;
                } else {
                    String str3 = pageBean.e;
                }
                List<Device> a2 = DeviceListAssemble.f17943a.a(pageBean);
                if (!TextUtils.equals(pageBean.f, HomeManager.h) || a2.size() != 1 || !IRDeviceUtil.a(a2.get(0).did)) {
                    z2 = false;
                }
                if (z2) {
                    this.i.changeToEmptyDeviceMode();
                } else {
                    this.i.changeToGridMode(i2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        this.j.refresh();
        this.p = true;
    }

    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i2, Object obj) {
        RecyclerView recyclerView = (RecyclerView) obj;
        if (recyclerView != this.h) {
            LogUtilGrey.a(b, "setPrimaryItem: pos: " + i2);
            this.h = recyclerView;
            if (i2 == 0) {
                IndexNoDeviceOperation.m();
            }
            if (this.m != null) {
                HomeManager.a().b(this.m.g);
            }
        }
        if (this.n != i2) {
            if (i2 == 0) {
                StickLogConsumer.f20916a.a((StickEvent) HomeDeviceShowEvent.f20907a);
            } else {
                StickLogConsumer.f20916a.a((StickEvent) HomeRoomShowEvent.f20910a);
            }
            this.n = i2;
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        if (this.m != null && !TextUtils.isEmpty(this.m.f)) {
            DeviceMainPage deviceMainPage = (DeviceMainPage) this.c.get();
            if (deviceMainPage != null) {
                if (HomeManager.h.equals(this.m.f)) {
                    deviceMainPage.b();
                }
                int deviceItemCount = this.i.getDeviceItemCount();
                if (deviceItemCount != -1) {
                    deviceMainPage.a(deviceItemCount);
                }
            }
            if (HomeManager.e.equals(this.m.f)) {
                STAT.d.ae();
            } else if (HomeManager.f.equals(this.m.f)) {
                STAT.d.af();
            }
            STAT.d.a(this.m.e, this.m.h);
        }
    }

    public void c() {
        RecyclerView.LayoutManager layoutManager;
        Log.d(b, "onLogout: ");
        if (this.g != null) {
            int childCount = this.g.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.g.getChildAt(i2);
                if ((childAt instanceof RecyclerView) && (layoutManager = ((RecyclerView) childAt).getLayoutManager()) != null) {
                    layoutManager.scrollToPosition(0);
                }
            }
        }
        StickLogConsumer.f20916a.c(false);
    }

    public RecyclerView d() {
        return this.h;
    }

    public void a(DeviceMainPage deviceMainPage) {
        this.c = new WeakReference<>(deviceMainPage);
        this.j.setDeviceMainPage((DeviceMainPage) this.c.get());
    }

    public WeakReference<DeviceMainPage> e() {
        return this.c;
    }

    public DeviceRecycler f() {
        return this.i;
    }

    public RoomRecycler g() {
        return this.j;
    }

    public void a(String str) {
        LogUtilGrey.a(b, "doSelectRoom:  ;isDataReady: " + this.l + " ;mCurrentPage:" + this.m);
        if (!TextUtils.isEmpty(str)) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                PageBean pageBean = this.e.get(i2);
                if (pageBean != null && TextUtils.equals(pageBean.f, str)) {
                    if (pageBean != this.m) {
                        this.g.post(new Runnable() {
                            public void run() {
                                RoomPagerAdapter.this.m();
                            }
                        });
                    }
                    this.m = pageBean;
                    b(false);
                }
            }
        }
    }

    public void h() {
        this.k = true;
        this.i.onEnterEditMode();
        this.j.onEnterEditMode();
    }

    public void i() {
        this.k = false;
        this.i.onExitEditMode();
        this.j.onExitEditMode();
    }

    public boolean j() {
        if (this.i == null) {
            return false;
        }
        return this.i.onBackPressed();
    }

    public void a(boolean z) {
        if (z && this.n != -1) {
            if (this.n == 0) {
                StickLogConsumer.f20916a.a((StickEvent) HomeDeviceShowEvent.f20907a);
            } else {
                StickLogConsumer.f20916a.a((StickEvent) HomeRoomShowEvent.f20910a);
            }
        }
    }
}
