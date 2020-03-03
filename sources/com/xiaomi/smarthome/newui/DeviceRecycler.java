package com.xiaomi.smarthome.newui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.crash.MainCrashHandler;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.multi_item.DelegateAdapter;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.adapter.DeviceMainEmptyAdapter;
import com.xiaomi.smarthome.newui.adapter.DeviceMainLoadingAdapter;
import com.xiaomi.smarthome.newui.adapter.NoDeviceAdapter;
import com.xiaomi.smarthome.newui.mainpage.DeviceMainGridAdapterV3;
import com.xiaomi.smarthome.newui.mainpage.MviRecyclerView;
import com.xiaomi.smarthome.newui.mainpage.devicepage.DevicePagePresenter;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageViewState;
import com.xiaomi.smarthome.newui.mainpage.devicepage.view.DevicePageView;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.ui.base.EditModeView;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DeviceRecycler extends MviRecyclerView<DevicePageView, DevicePagePresenter> implements DevicePageView {
    private static final String g = "MVI-DeviceRecycler";
    /* access modifiers changed from: private */
    public DropMenuAdapter h;
    private MainOperationAdapter i;
    private boolean j;
    private DeviceMainEmptyAdapter k;
    /* access modifiers changed from: private */
    public DropMenuAdapter l;
    private int m;
    public RoomPagerAdapter mRoomPagerAdapter;
    private PublishSubject<EditModeView.EditModeModel> n;
    /* access modifiers changed from: private */
    public CurrentDisplayMode o;
    private DeviceMainGridAdapterV3 p;
    private DelegateAdapter q;
    /* access modifiers changed from: private */
    public DropMenuStateHelper r;
    private DeviceListPageActionInterface s;
    private PublishSubject<Boolean> t;
    private final BroadcastReceiver u;

    enum CurrentDisplayMode {
        LAYOUT_MANGER_GRID_2,
        LAYOUT_MANGER_LOADING,
        LAYOUT_MANGER_EMPTY_AD
    }

    public void render(final MainPageViewState mainPageViewState) {
        if (GlobalSetting.q) {
            LogUtilGrey.a(g, "DeviceRecycler raw render in myLooper:" + Looper.myLooper() + ",main looper:" + Looper.getMainLooper());
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            post(new Runnable() {
                public void run() {
                    if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
                        DeviceRecycler.this.render(mainPageViewState);
                    }
                }
            });
            return;
        }
        LogUtilGrey.a(g, "DeviceRecycler render in " + mainPageViewState.b() + ",mGridAdapter=" + this.p + ",mRoomPagerAdapter=" + this.mRoomPagerAdapter);
        if (mainPageViewState.b()) {
            a(mainPageViewState);
            return;
        }
        if (this.mRoomPagerAdapter != null) {
            this.mRoomPagerAdapter.b();
        }
        List<MainPageDeviceModel> d = mainPageViewState.d();
        if (d == null || d.isEmpty()) {
            changeToEmptyDeviceMode();
        } else if (d.size() != 1 || !d.get(0).b()) {
            changeToGridMode(0);
            b(mainPageViewState);
        } else {
            changeToEmptyDeviceMode();
        }
    }

    private void a(MainPageViewState mainPageViewState) {
        changeToLoadingMode();
    }

    private void b(MainPageViewState mainPageViewState) {
        if (this.p != null) {
            this.p.a(mainPageViewState);
        }
    }

    public Observable<Boolean> pullToRefresh() {
        return Observable.wrap(HomeManager.a().K());
    }

    public Observable<EditModeView.EditModeModel> onEditModeAction() {
        return this.n;
    }

    public void enterEditMode() {
        EditModeView.EditModeModel editModeModel = new EditModeView.EditModeModel();
        editModeModel.b = new ArrayList();
        editModeModel.f22777a = 1;
        this.n.onNext(editModeModel);
    }

    public void exitEditMode() {
        EditModeView.EditModeModel editModeModel = new EditModeView.EditModeModel();
        editModeModel.b = new ArrayList();
        editModeModel.f22777a = 2;
        this.n.onNext(editModeModel);
    }

    public DeviceRecycler(Context context) {
        this(context, (AttributeSet) null);
        LogUtilGrey.a(g, "DeviceRecycler constructor");
    }

    public DeviceRecycler(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        LogUtilGrey.a(g, "DeviceRecycler constructor");
    }

    public DeviceRecycler(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.j = false;
        this.n = PublishSubject.create();
        this.o = null;
        this.r = DropMenuStateHelper.a();
        this.t = PublishSubject.create();
        this.u = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String action = intent.getAction();
                    if (TextUtils.equals("action_on_logout", action)) {
                        DeviceRecycler.this.r.f();
                    } else if (TextUtils.equals(action, DropMenuAdapter.f20253a)) {
                        DeviceRecycler.this.r.a(intent.getStringExtra(DropMenuAdapter.c));
                        DeviceRecycler.this.a();
                    } else if (TextUtils.equals(action, DropMenuAdapter.b)) {
                        if (DeviceRecycler.this.o == CurrentDisplayMode.LAYOUT_MANGER_GRID_2 && DeviceRecycler.this.h != null) {
                            DeviceRecycler.this.h.h();
                        }
                        if (DeviceRecycler.this.o == CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD && DeviceRecycler.this.l != null) {
                            DeviceRecycler.this.l.h();
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
        LogUtilGrey.a(g, "DeviceRecycler constructor");
    }

    public void setPage(DeviceListPageActionInterface deviceListPageActionInterface) {
        this.s = deviceListPageActionInterface;
    }

    private void a(boolean z) {
        this.q = new DelegateAdapter();
        this.p = new DeviceMainGridAdapterV3();
        this.p.a(this.s);
        if (!z) {
            this.h = new DropMenuAdapter(getContext(), "DropMenuAdapter");
            this.i = new MainOperationAdapter();
            this.q.a((IAdapter) this.h);
            this.q.a((IAdapter) this.i);
        }
        this.q.a((IAdapter) this.p);
        this.q.b((IAdapter) new NoDeviceAdapter(getContext()));
    }

    public void refresh(List<PageBean.Classify> list) {
        this.r.a(list);
        a();
        this.t.onNext(true);
    }

    public Observable<Boolean> dataSetChanged() {
        return this.t.throttleFirst(1000, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.o == CurrentDisplayMode.LAYOUT_MANGER_GRID_2 && this.h != null) {
            this.h.b();
        }
        if (this.o == CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD && this.l != null) {
            this.l.b();
        }
    }

    public void forceRefreshDevice() {
        if (this.o == CurrentDisplayMode.LAYOUT_MANGER_GRID_2 && this.p != null) {
            this.p.notifyDataSetChanged();
        }
    }

    public void changeToGridMode(int i2) {
        boolean z = true;
        LogUtilGrey.a(g, "changeToGridMode", true);
        this.m = i2;
        if (this.o != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            a(false);
            setAdapter(this.q);
        }
        if (this.i != null) {
            MainOperationAdapter mainOperationAdapter = this.i;
            if (i2 != 0) {
                z = false;
            }
            mainOperationAdapter.a(z);
        }
        this.o = CurrentDisplayMode.LAYOUT_MANGER_GRID_2;
    }

    public void changeToGridOnlyMode() {
        LogUtilGrey.a(g, "changeToGridModeNoNav", true);
        if (this.o != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            a(true);
            setAdapter(this.q);
        }
        this.o = CurrentDisplayMode.LAYOUT_MANGER_GRID_2;
    }

    public void changeToLoadingMode() {
        if (this.o != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            LogUtilGrey.a(g, "changeToLoadingMode", true);
            if (this.o != CurrentDisplayMode.LAYOUT_MANGER_LOADING) {
                DelegateAdapter delegateAdapter = new DelegateAdapter();
                delegateAdapter.a((IAdapter) new DeviceMainLoadingAdapter(getContext()));
                setAdapter(delegateAdapter);
            }
            this.o = CurrentDisplayMode.LAYOUT_MANGER_LOADING;
        }
    }

    public void changeToEmptyDeviceMode() {
        LogUtilGrey.a(g, "changeToEmptyDeviceMode", true);
        if (this.o != CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD) {
            DelegateAdapter delegateAdapter = new DelegateAdapter();
            this.k = new DeviceMainEmptyAdapter(getContext());
            this.l = new DropMenuAdapter(getContext(), "DropMenuAdapterFroEmptyPage");
            delegateAdapter.a((IAdapter) this.l);
            delegateAdapter.a((IAdapter) this.k);
            setAdapter(delegateAdapter);
        } else {
            this.k.b();
        }
        this.o = CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD;
    }

    public CurrentDisplayMode getCurrentMode() {
        return this.o;
    }

    public int getDeviceItemCount() {
        if (this.o != CurrentDisplayMode.LAYOUT_MANGER_GRID_2) {
            return -1;
        }
        return this.p.getItemCount();
    }

    public void onEnterEditMode() {
        this.j = true;
        if (this.h != null) {
            this.h.c();
        }
        if (this.i != null) {
            this.i.c();
        }
    }

    public void onExitEditMode() {
        this.j = false;
        if (this.h != null) {
            this.h.f();
        }
        if (this.i != null) {
            this.i.f();
        }
    }

    public boolean onBackPressed() {
        if (this.h == null) {
            return false;
        }
        return this.h.g();
    }

    public void onScrollStateChanged(int i2) {
        if (i2 == 2) {
            try {
                Fresco.getImagePipeline().pause();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    FrescoInitial.a(true);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    MainCrashHandler.a((Throwable) e2);
                }
            }
        } else {
            Fresco.getImagePipeline().resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(g, "onAttachedToWindow: ");
        try {
            Fresco.getImagePipeline().resume();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                FrescoInitial.a(true);
            } catch (Exception e2) {
                e2.printStackTrace();
                MainCrashHandler.a((Throwable) e2);
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DropMenuAdapter.f20253a);
        intentFilter.addAction("action_on_logout");
        intentFilter.addAction(DropMenuAdapter.b);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.u, intentFilter);
    }

    public void onHomeChange() {
        this.r.f();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(g, "onDetachedFromWindow: ");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.u);
        this.r.f();
    }

    public DevicePagePresenter createPresenter() {
        LogUtilGrey.a(g, "createPresenter");
        return new DevicePagePresenter();
    }

    public void destory() {
        DeviceMainGridAdapterV3 deviceMainGridAdapterV3 = this.p;
    }
}
