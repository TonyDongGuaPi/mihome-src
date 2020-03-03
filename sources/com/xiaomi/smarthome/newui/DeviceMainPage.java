package com.xiaomi.smarthome.newui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.support.animation.SpringAnimation;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.YoupinPopupActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.CommonExtraConfigManager;
import com.xiaomi.smarthome.config.SHBusinessManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceScanManager;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.VirtualDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.contentprovider.SmartHomeContentProvider;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.framework.page.TabFragment;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.ChangeHomeActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.international.ServerApi;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import com.xiaomi.smarthome.lite.LiteSoundManager;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.mibrain.activity.MiBrainActivityNew;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.consumables.ConsumableDataManager;
import com.xiaomi.smarthome.miio.message.p0.P0MessageHelper;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManagerNew;
import com.xiaomi.smarthome.newui.widget.AppBarStateChangeListener;
import com.xiaomi.smarthome.newui.widget.DeviceMainPageEditBarV2;
import com.xiaomi.smarthome.newui.widget.MainPageAppBarLayout;
import com.xiaomi.smarthome.newui.widget.ReactiveWallpaper;
import com.xiaomi.smarthome.newui.widget.SimplePushToRefreshHeader;
import com.xiaomi.smarthome.newui.widget.banner.CustomBannerHeaderView;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.MyIndicator;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.MyViewPager;
import com.xiaomi.smarthome.operation.indexdefault.IndexNoDeviceOperation;
import com.xiaomi.smarthome.scene.timer.TimerCommonConfigManager;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.xiaomi.smarthome.share.ShareAlertDialogHelper;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.voice.VoiceManager;
import com.xiaomi.voiceassistant.mijava.NLProcessor;
import com.yanzhenjie.permission.Action;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import miuipub.view.EditActionMode;

public class DeviceMainPage extends TabFragment implements DeviceScanManager.OnScanListener, DeviceListPageActionInterface {
    public static final int b = 4;
    public static final int c = 44;
    public static final String d = "pref_ir";
    public static final String e = "key_show_ir_hint";
    public static final String f = "force_update_data";
    public static final String g = "com.smarthome.refresh_list_view";
    public static int q = 201;
    /* access modifiers changed from: private */
    public static final String s = "DeviceMainPage";
    private static final int t = 1;
    private static final int u = 3;
    private static final int v = 5;
    private static final int w = 6;
    private static final int x = 8;
    /* access modifiers changed from: private */
    public MainPageAppBarLayout A;
    /* access modifiers changed from: private */
    public ViewStub B;
    /* access modifiers changed from: private */
    public View C;
    private TextView D;
    private ImageView E;
    /* access modifiers changed from: private */
    public TextView F;
    private CustomBannerHeaderView G;
    private View H;
    /* access modifiers changed from: private */
    public boolean I = false;
    private ViewStub J;
    private ImageView K;
    /* access modifiers changed from: private */
    public boolean L = false;
    private String M;
    private int N = 0;
    /* access modifiers changed from: private */
    public boolean O = false;
    private boolean P = true;
    /* access modifiers changed from: private */
    public boolean Q = true;
    /* access modifiers changed from: private */
    public PopupWindow R;
    /* access modifiers changed from: private */
    public boolean S = false;
    /* access modifiers changed from: private */
    public boolean T = false;
    private boolean U = true;
    /* access modifiers changed from: private */
    public boolean V = false;
    /* access modifiers changed from: private */
    public Boolean W = null;
    /* access modifiers changed from: private */
    public VirtualDeviceManager X;
    private ImageView Y;
    private ImageView Z;
    private DeviceMainPageEditBarV2.OnMenuBarPositionChangeListener aa = new DeviceMainPageEditBarV2.OnMenuBarPositionChangeListener() {
        private int b = -1;
        private int c = -1;

        public void a(int i) {
            String p = DeviceMainPage.s;
            LogUtil.a(p, "onPositionChanged " + i);
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) DeviceMainPage.this.ap.getLayoutParams();
            if (this.b == -1) {
                this.b = layoutParams.bottomMargin;
            }
            if (this.c == -1) {
                this.c = DeviceMainPage.this.getResources().getDimensionPixelOffset(R.dimen.bottom_bar_height);
            }
            layoutParams.bottomMargin = i - this.c;
            if (layoutParams.bottomMargin <= this.b) {
                layoutParams.bottomMargin = this.b;
            }
            DeviceMainPage.this.ap.setLayoutParams(layoutParams);
        }
    };
    private boolean ab;
    /* access modifiers changed from: private */
    public TextView ac;
    /* access modifiers changed from: private */
    public View ad;
    private ObjectAnimator ae;
    /* access modifiers changed from: private */
    public ObjectAnimator af;
    /* access modifiers changed from: private */
    public Handler ag = new Handler() {
        public void handleMessage(Message message) {
            if (!DeviceMainPage.this.L && DeviceMainPage.this.isValid()) {
                int i = message.what;
                if (i == 1) {
                    DeviceMainPage.this.k = true;
                } else if (i == 3) {
                    DeviceMainPage.this.k();
                } else if (i != 8) {
                    switch (i) {
                        case 5:
                            DeviceMainPage.this.m = true;
                            return;
                        case 6:
                            boolean unused = DeviceMainPage.this.S = false;
                            DeviceMainPage.this.o();
                            return;
                        default:
                            return;
                    }
                } else {
                    DeviceMainPage.this.af.start();
                }
            }
        }
    };
    private BroadcastReceiver ah = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            DeviceRecycler f;
            if (DeviceMainPage.this.T) {
                int i = 0;
                if (TextUtils.equals(intent.getAction(), HomeManager.S)) {
                    DeviceMainPage.this.A.refreshComplete(DeviceMainPage.this.z);
                    if (DeviceMainPage.this.Q) {
                        boolean unused = DeviceMainPage.this.Q = false;
                    }
                    DeviceMainPage.this.ag.removeMessages(5);
                    DeviceMainPage.this.ag.sendEmptyMessageDelayed(5, DeviceMainPage.this.O ? 3000 : 4000);
                    DeviceMainPage.this.a();
                    DeviceMainPage.this.B();
                    DeviceMainPage.this.A();
                } else if ("com.smarthome.refresh_list_view".equals(intent.getAction())) {
                    boolean unused2 = DeviceMainPage.this.V = intent.getBooleanExtra(SmartHomeDeviceManager.c, false) | DeviceMainPage.this.V;
                    DeviceMainPage.this.j();
                } else if (HomeManager.t.equals(intent.getAction())) {
                    LogUtilGrey.a(DeviceMainPage.s, "onReceive: home ready: ");
                    DeviceMainPage.this.j();
                    AreaInfoManager.a().a(DeviceMainPage.this.getContext(), false);
                    String stringExtra = intent.getStringExtra(HomeManager.v);
                    SHApplication.getThreadExecutor().submit(new Runnable() {
                        public void run() {
                            CameraInfoRefreshManager.a().g();
                        }
                    });
                    if (TextUtils.equals(stringExtra, HomeManager.A) && DeviceMainPage.this.aq != null && (f = DeviceMainPage.this.aq.f()) != null) {
                        f.forceRefreshDevice();
                    }
                } else if (UserMamanger.b.equals(intent.getAction())) {
                    DeviceMainPage.this.z();
                } else if (CommonActivity.ACTION_SPLIT_SCREEN_MODE_CHANGED.equals(intent.getAction())) {
                    if (SHApplication.isInSplitScreenMode()) {
                        DeviceMainPage.this.b(true);
                    }
                } else if (HomeManager.u.equals(intent.getAction())) {
                    DeviceMainPage.this.q();
                    DeviceMainPage.this.r();
                    if (DeviceMainPage.this.aq != null) {
                        DeviceMainPage.this.aq.a();
                    }
                    DeviceMainPage.this.e(true);
                    boolean unused3 = DeviceMainPage.this.I = true;
                    Home m = HomeManager.a().m();
                    if (!(DeviceMainPage.this.ak == null || m == null)) {
                        View q = DeviceMainPage.this.ak;
                        if (!m.p()) {
                            i = 4;
                        }
                        q.setVisibility(i);
                    }
                    DeviceMainPage.this.B();
                    DeviceMainPage.this.A();
                } else if (SHBusinessManager.f13942a.equals(intent.getAction())) {
                    DeviceMainPage.this.t();
                } else if (DeviceTagInterface.A.equals(intent.getAction())) {
                    DeviceMainPage.this.c(true);
                } else if (DeviceMainPage.f.equals(intent.getAction())) {
                    LogUtil.c(DeviceMainPage.s, "receive broadcast, will startRefresh...");
                    DeviceMainPage.this.e(true);
                } else if (GetDeviceTask.d.equals(intent.getAction())) {
                    DeviceMainPage.this.j();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public ViewStub ai;
    /* access modifiers changed from: private */
    public View aj;
    /* access modifiers changed from: private */
    public View ak;
    private DeviceScanManager al;
    /* access modifiers changed from: private */
    public boolean am;
    private AtomicBoolean an = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public MyIndicator ao;
    /* access modifiers changed from: private */
    public MyViewPager ap;
    /* access modifiers changed from: private */
    public RoomPagerAdapter aq;
    private BroadcastReceiver ar = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), DropMenuAdapter.f20253a)) {
                String stringExtra = intent.getStringExtra(DropMenuAdapter.c);
                if (DeviceMainPage.this.aq != null) {
                    DeviceMainPage.this.aq.a(stringExtra);
                }
            }
        }
    };
    private final BroadcastReceiver as = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            RecyclerView d;
            if (DeviceMainPage.this.aq != null && (d = DeviceMainPage.this.aq.d()) != null) {
                RecyclerView.LayoutManager layoutManager = d.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(0, 0);
                }
            }
        }
    };
    private long at = 0;
    private boolean au = false;
    /* access modifiers changed from: private */
    public AtomicBoolean av = new AtomicBoolean(false);
    private LoginManager.LoginManagerCallback aw = new LoginManager.LoginManagerCallback() {
        public void a() {
            DeviceMainPage.this.C();
        }

        public void b() {
            DeviceMainPage.this.D();
        }
    };
    public XQProgressDialog h;
    public volatile boolean i = false;
    public MLAlertDialog j;
    public volatile boolean k = false;
    BrainSettingCallback l;
    public boolean m = false;
    protected TextView n;
    MessageQueue.IdleHandler o = null;
    SmartHomeDeviceManager.IClientDeviceListener p = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                String p = DeviceMainPage.s;
                LogUtilGrey.a(p, "UPDATE_DEVICE_DATA_READY getCurrentState " + SHApplication.getStateNotifier().a() + ",device size=" + SmartHomeDeviceManager.a().d().size());
                if (SHApplication.getStateNotifier().a() == 4) {
                    SmartHomeContentProvider.notifyChange(DeviceMainPage.this.getActivity(), "online_devices_count");
                    if (!DeviceMainPage.this.O) {
                        FragmentActivity activity = DeviceMainPage.this.getActivity();
                        activity.getClass();
                        ((HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class)).b(HomeManager.a().l());
                        FragmentActivity activity2 = DeviceMainPage.this.getActivity();
                        activity2.getClass();
                        ((HomeEnvInfoViewModel) ViewModelProviders.a(activity2).a(HomeEnvInfoViewModel.class)).a(HomeManager.a().l());
                    }
                    boolean c = HomeManager.a().c();
                    String p2 = DeviceMainPage.s;
                    LogUtilGrey.a(p2, "onReceive: data ready; isHomeManagerInited: " + c);
                    boolean unused = DeviceMainPage.this.O = true;
                    if (c && DeviceMainPage.this.aq != null) {
                        LogUtil.a(DeviceMainPage.s, "Device Data Is Ready,But View Not!");
                        DeviceMainPage.this.aq.b();
                    }
                    DeviceMainPage.this.G();
                    DeviceListSwitchManager.a().j();
                    CameraInfoRefreshManager.a().g();
                    String p3 = DeviceMainPage.s;
                    LogUtil.a(p3, "UPDATE_DEVICE_DATA_READY mIsAdapterLoading=" + DeviceMainPage.this.Q);
                    if (DeviceMainPage.this.S) {
                        DeviceMainPage.this.ag.removeMessages(6);
                        DeviceMainPage.this.ag.sendEmptyMessageDelayed(6, 500);
                    }
                    if (!DeviceMainPage.this.av.getAndSet(true)) {
                        DeviceMainPage.this.J();
                    }
                }
            }
        }
    };
    public DviceEditInterface r;
    private ViewGroup y;
    /* access modifiers changed from: private */
    public CoordinatorLayout z;

    public List<Device> i() {
        return null;
    }

    @Nullable
    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    /* access modifiers changed from: private */
    public void q() {
        Home m2 = HomeManager.a().m();
        if (m2 == null || m2.p()) {
            this.al = DeviceScanManager.instance;
            return;
        }
        this.al = null;
        this.ac.setVisibility(8);
        this.ad.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void r() {
        this.A.setCameraVisibility(0);
        a(false, 0);
    }

    private void a(boolean z2, int i2) {
        String str = s;
        LogUtilGrey.a(str, "adjustAppBarLayoutHeightIfNeed " + z2);
        this.G.setCameraContainerVisibility(z2 ? 0 : 8, i2);
        int dimensionPixelSize = getResources().getDimensionPixelSize(z2 ? R.dimen.main_app_bar_layout_height_2 : R.dimen.main_app_bar_layout_height);
        if (this.A.getContentHeight() != dimensionPixelSize && this.A.getHeight() != dimensionPixelSize) {
            this.A.setContentHeight(dimensionPixelSize);
        }
    }

    public void a() {
        if (!this.an.getAndSet(true)) {
            this.ag.postDelayed(new Runnable() {
                public void run() {
                    P0MessageHelper.a((CommonActivity) DeviceMainPage.this.getActivity());
                }
            }, 1000);
        }
    }

    public void b() {
        this.ag.postDelayed(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x006d, code lost:
                r0 = com.xiaomi.smarthome.newui.DeviceMainPage.m(r10.f20236a).d();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r10 = this;
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    boolean r0 = r0.isValid()
                    if (r0 != 0) goto L_0x0009
                    return
                L_0x0009:
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    java.lang.Boolean r0 = r0.W
                    r1 = 1
                    if (r0 != 0) goto L_0x0027
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    android.content.Context r2 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r3 = "pref_ir"
                    java.lang.String r4 = "key_show_ir_hint"
                    boolean r2 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.b((android.content.Context) r2, (java.lang.String) r3, (java.lang.String) r4, (boolean) r1)
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
                    java.lang.Boolean unused = r0.W = r2
                L_0x0027:
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    java.lang.Boolean r0 = r0.W
                    boolean r0 = r0.booleanValue()
                    if (r0 == 0) goto L_0x00f7
                    com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    boolean r0 = r0.E()
                    if (r0 == 0) goto L_0x003e
                    return
                L_0x003e:
                    com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    java.util.List r0 = r0.F()
                    if (r0 == 0) goto L_0x00f6
                    int r2 = r0.size()
                    if (r2 == r1) goto L_0x0050
                    goto L_0x00f6
                L_0x0050:
                    r2 = 0
                    java.lang.Object r0 = r0.get(r2)
                    com.xiaomi.smarthome.homeroom.model.GridViewData r0 = (com.xiaomi.smarthome.homeroom.model.GridViewData) r0
                    com.xiaomi.smarthome.homeroom.model.GridViewData$GridType r0 = r0.f18311a
                    com.xiaomi.smarthome.homeroom.model.GridViewData$GridType r3 = com.xiaomi.smarthome.homeroom.model.GridViewData.GridType.TYPE_IR
                    if (r0 == r3) goto L_0x005e
                    return
                L_0x005e:
                    android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    com.xiaomi.smarthome.newui.-$$Lambda$DeviceMainPage$5$Cl0OmsnPM6oyMLU5f3p-4cSXw4k r3 = new com.xiaomi.smarthome.newui.-$$Lambda$DeviceMainPage$5$Cl0OmsnPM6oyMLU5f3p-4cSXw4k
                    r3.<init>()
                    int r0 = com.xiaomi.smarthome.device.utils.IRDeviceUtil.b((android.content.Context) r0, (com.xiaomi.smarthome.device.utils.IRDeviceUtil.IRDeviceListener) r3)
                    if (r0 != 0) goto L_0x00f7
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    com.xiaomi.smarthome.newui.RoomPagerAdapter r0 = r0.aq
                    android.support.v7.widget.RecyclerView r0 = r0.d()
                    android.support.v7.widget.RecyclerView$LayoutManager r3 = r0.getLayoutManager()
                    if (r3 == 0) goto L_0x00f7
                    android.view.View r3 = r3.findViewByPosition(r2)
                    if (r3 == 0) goto L_0x00f5
                    int r4 = r3.getTop()
                    if (r4 >= 0) goto L_0x008a
                    goto L_0x00f5
                L_0x008a:
                    boolean r4 = r0 instanceof com.xiaomi.smarthome.newui.DeviceRecycler
                    if (r4 != 0) goto L_0x008f
                    return
                L_0x008f:
                    com.xiaomi.smarthome.newui.DeviceRecycler r0 = (com.xiaomi.smarthome.newui.DeviceRecycler) r0
                    com.xiaomi.smarthome.newui.DeviceRecycler$CurrentDisplayMode r0 = r0.getCurrentMode()
                    com.xiaomi.smarthome.newui.DeviceRecycler$CurrentDisplayMode r4 = com.xiaomi.smarthome.newui.DeviceRecycler.CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD
                    if (r0 == r4) goto L_0x009a
                    return
                L_0x009a:
                    r0 = 2131428800(0x7f0b05c0, float:1.8479255E38)
                    android.view.View r0 = r3.findViewById(r0)
                    if (r0 == 0) goto L_0x00f4
                    int r3 = r0.getVisibility()
                    r4 = 8
                    if (r3 != r4) goto L_0x00ac
                    goto L_0x00f4
                L_0x00ac:
                    com.xiaomi.smarthome.newui.DeviceMainPage r3 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    android.support.v4.app.FragmentActivity r3 = r3.getActivity()
                    if (r3 == 0) goto L_0x00f7
                    boolean r4 = r3.isFinishing()
                    if (r4 != 0) goto L_0x00f7
                    r4 = 2
                    int[] r4 = new int[r4]
                    r0.getLocationOnScreen(r4)
                    android.graphics.Rect r5 = new android.graphics.Rect
                    r6 = r4[r2]
                    r7 = r4[r1]
                    r8 = r4[r2]
                    int r9 = r0.getWidth()
                    int r8 = r8 + r9
                    r1 = r4[r1]
                    int r0 = r0.getHeight()
                    int r1 = r1 + r0
                    r5.<init>(r6, r7, r8, r1)
                    com.xiaomi.smarthome.SmartHomeMainActivity r3 = (com.xiaomi.smarthome.SmartHomeMainActivity) r3
                    boolean r0 = r3.showIrHint(r5)
                    if (r0 == 0) goto L_0x00f7
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
                    java.lang.Boolean unused = r0.W = r1
                    android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r1 = "pref_ir"
                    java.lang.String r3 = "key_show_ir_hint"
                    com.xiaomi.smarthome.library.common.util.SharePrefsManager.a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r3, (boolean) r2)
                    goto L_0x00f7
                L_0x00f4:
                    return
                L_0x00f5:
                    return
                L_0x00f6:
                    return
                L_0x00f7:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.DeviceMainPage.AnonymousClass5.run():void");
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(List list) {
                if (list == null || list.size() <= 0) {
                    DeviceMainPage.this.ag.post(new Runnable() {
                        public final void run() {
                            DeviceMainPage.AnonymousClass5.this.a();
                        }
                    });
                }
            }

            /* access modifiers changed from: private */
            /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
                r0 = com.xiaomi.smarthome.newui.DeviceMainPage.m(r10.f20236a).d();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public /* synthetic */ void a() {
                /*
                    r10 = this;
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    boolean r0 = r0.isValid()
                    if (r0 != 0) goto L_0x0009
                    return
                L_0x0009:
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    com.xiaomi.smarthome.newui.RoomPagerAdapter r0 = r0.aq
                    android.support.v7.widget.RecyclerView r0 = r0.d()
                    android.support.v7.widget.RecyclerView$LayoutManager r1 = r0.getLayoutManager()
                    if (r1 == 0) goto L_0x009e
                    r2 = 0
                    android.view.View r1 = r1.findViewByPosition(r2)
                    if (r1 == 0) goto L_0x009d
                    int r3 = r1.getTop()
                    if (r3 >= 0) goto L_0x0028
                    goto L_0x009d
                L_0x0028:
                    boolean r3 = r0 instanceof com.xiaomi.smarthome.newui.DeviceRecycler
                    if (r3 != 0) goto L_0x002d
                    return
                L_0x002d:
                    com.xiaomi.smarthome.newui.DeviceRecycler r0 = (com.xiaomi.smarthome.newui.DeviceRecycler) r0
                    com.xiaomi.smarthome.newui.DeviceRecycler$CurrentDisplayMode r0 = r0.getCurrentMode()
                    com.xiaomi.smarthome.newui.DeviceRecycler$CurrentDisplayMode r3 = com.xiaomi.smarthome.newui.DeviceRecycler.CurrentDisplayMode.LAYOUT_MANGER_EMPTY_AD
                    if (r0 == r3) goto L_0x0038
                    return
                L_0x0038:
                    r0 = 2131428800(0x7f0b05c0, float:1.8479255E38)
                    android.view.View r0 = r1.findViewById(r0)
                    if (r0 == 0) goto L_0x009c
                    int r1 = r0.getVisibility()
                    r3 = 8
                    if (r1 != r3) goto L_0x004a
                    goto L_0x009c
                L_0x004a:
                    com.xiaomi.smarthome.newui.DeviceMainPage r1 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    android.support.v4.app.FragmentActivity r1 = r1.getActivity()
                    if (r1 == 0) goto L_0x009e
                    boolean r3 = r1.isFinishing()
                    if (r3 != 0) goto L_0x009e
                    com.xiaomi.smarthome.newui.DeviceMainPage r3 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
                    java.lang.Boolean unused = r3.W = r4
                    r3 = 2
                    int[] r3 = new int[r3]
                    r0.getLocationOnScreen(r3)
                    android.graphics.Rect r4 = new android.graphics.Rect
                    r5 = r3[r2]
                    r6 = 1
                    r7 = r3[r6]
                    r8 = r3[r2]
                    int r9 = r0.getWidth()
                    int r8 = r8 + r9
                    r3 = r3[r6]
                    int r0 = r0.getHeight()
                    int r3 = r3 + r0
                    r4.<init>(r5, r7, r8, r3)
                    com.xiaomi.smarthome.SmartHomeMainActivity r1 = (com.xiaomi.smarthome.SmartHomeMainActivity) r1
                    boolean r0 = r1.showIrHint(r4)
                    if (r0 == 0) goto L_0x009e
                    com.xiaomi.smarthome.newui.DeviceMainPage r0 = com.xiaomi.smarthome.newui.DeviceMainPage.this
                    java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
                    java.lang.Boolean unused = r0.W = r1
                    android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
                    java.lang.String r1 = "pref_ir"
                    java.lang.String r3 = "key_show_ir_hint"
                    com.xiaomi.smarthome.library.common.util.SharePrefsManager.a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r3, (boolean) r2)
                    goto L_0x009e
                L_0x009c:
                    return
                L_0x009d:
                    return
                L_0x009e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.DeviceMainPage.AnonymousClass5.a():void");
            }
        }, 2000);
    }

    private void b(int i2) {
        if (this.G == null || this.G.findViewById(R.id.recycler_view).getVisibility() != 4) {
            this.A.setCameraVisibility(i2);
            a(true, i2);
            return;
        }
        a(false, 0);
    }

    private void s() {
        if (this.ap != null && this.ao != null && this.aq != null && this.aq.d() != null) {
            this.ao.setTranslationY((float) this.ap.getHeight());
            this.aq.d().setTranslationY((float) this.ap.getHeight());
            final ViewTreeObserver viewTreeObserver = this.y.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        try {
                            viewTreeObserver.removeOnPreDrawListener(this);
                            SpringAnimation springAnimation = new SpringAnimation(DeviceMainPage.this.ao, SpringAnimation.TRANSLATION_Y, 0.0f);
                            SpringAnimation springAnimation2 = new SpringAnimation(DeviceMainPage.this.aq.d(), SpringAnimation.TRANSLATION_Y, 0.0f);
                            ((SpringAnimation) springAnimation.setMinimumVisibleChange(5.0f)).getSpring().setStiffness(200.0f).setDampingRatio(1.0f);
                            ((SpringAnimation) springAnimation2.setMinimumVisibleChange(5.0f)).getSpring().setStiffness(200.0f).setDampingRatio(1.0f);
                            springAnimation2.start();
                            springAnimation.start();
                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            return true;
                        }
                    }
                });
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.am = DarkModeCompat.a(getContext());
        DevicePushBindManager.instance.needScan();
        CoreApi.a().a((Context) getActivity(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (DeviceMainPage.this.l == null) {
                    DeviceMainPage.this.l = new BrainSettingCallback(DeviceMainPage.this);
                }
                VoiceManager.a().a((AsyncCallback) DeviceMainPage.this.l);
                AndroidCommonConfigManager.a().i();
                SHApplication.getThreadExecutor().submit(new Runnable() {
                    public void run() {
                        LightGroupManager.a().b();
                    }
                });
            }
        });
        this.ag.postDelayed(new Runnable() {
            public void run() {
                LiteSoundManager.a().b();
                CoreApi.a().a((Context) DeviceMainPage.this.getActivity(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        if (DeviceMainPage.this.l == null) {
                            DeviceMainPage.this.l = new BrainSettingCallback(DeviceMainPage.this);
                        }
                        VoiceManager.a().b(DeviceMainPage.this.l);
                    }
                });
            }
        }, Constants.x);
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.ar, new IntentFilter(DropMenuAdapter.f20253a));
        LoginManager.a().a(this.aw);
        q();
    }

    public void onScan(List<?> list) {
        if (this.ac != null && this.ad != null) {
            int k2 = AndroidCommonConfigManager.a().k();
            int size = list.size();
            boolean z2 = SHApplication.getStateNotifier().a() == 4;
            if (k2 == 0 || size <= 0 || !z2) {
                this.ac.setVisibility(8);
                this.ad.setVisibility(8);
            } else if (k2 == 1) {
                this.ac.setVisibility(8);
                this.ad.setVisibility(0);
            } else {
                this.ac.setVisibility(0);
                this.ad.setVisibility(8);
                this.ac.setText(String.valueOf(Math.min(size, 99)));
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f16923a == null) {
            this.f16923a = layoutInflater.inflate(R.layout.client_all_page_v2_top_navi_layout, (ViewGroup) null);
            this.T = true;
            w();
            LogUtilGrey.a(s, "registerClientDeviceListener: ");
            this.O = SmartHomeDeviceManager.a().u();
            SmartHomeDeviceManager.a().a(this.p);
            if (SHApplication.getStateNotifier().a() == 4) {
                C();
            }
            v();
        }
        this.L = false;
        return this.f16923a;
    }

    /* access modifiers changed from: private */
    public void t() {
        List<SHBusinessManager.BusinessContent> a2;
        if (this.P && SHApplication.getStateNotifier().a() == 4 && (a2 = SHBusinessManager.a().a(1)) != null && a2.size() > 0) {
            for (final SHBusinessManager.BusinessContent next : a2) {
                if (!TextUtils.isEmpty(next.b) && next.j != null && SHBusinessManager.a().a(next) && SHBusinessManager.a().a(1, next)) {
                    final Uri parse = Uri.parse(next.b);
                    SHBusinessManager.a().a(parse, (AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Boolean bool) {
                            if (bool.booleanValue()) {
                                DeviceMainPage.this.a(next);
                            } else {
                                SHBusinessManager.a().a(parse, true);
                            }
                        }

                        public void onFailure(Error error) {
                            if (error != null) {
                                LogUtil.a("refreshBusinessView", error.b());
                            }
                            DeviceMainPage.this.a(next);
                        }
                    });
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final SHBusinessManager.BusinessContent businessContent) {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            if (this.R == null || !this.R.isShowing()) {
                View inflate = activity.getLayoutInflater().inflate(R.layout.home_page_interstitial_popup_window, (ViewGroup) null);
                this.R = new PopupWindow(inflate, -1, -1, true);
                this.R.setTouchable(true);
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.business_image);
                Uri parse = Uri.parse(businessContent.b);
                simpleDraweeView.setHierarchy(GenericDraweeHierarchyBuilder.newInstance(getResources()).setFadeDuration(200).setRoundingParams(RoundingParams.fromCornersRadius(15.0f)).setPlaceholderImage(getResources().getDrawable(R.drawable.device_list_phone_no)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
                simpleDraweeView.setController(Fresco.newDraweeControllerBuilder().setUri(parse).build());
                inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        StatHelper.c(businessContent.f13950a);
                        if (DeviceMainPage.this.R != null && DeviceMainPage.this.R.isShowing()) {
                            DeviceMainPage.this.R.dismiss();
                        }
                    }
                });
                inflate.findViewById(R.id.click_more).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SHBusinessManager.a().b(businessContent);
                        StatHelper.b(businessContent.f13950a);
                        DeviceMainPage.this.ag.postDelayed(new Runnable() {
                            public void run() {
                                if (DeviceMainPage.this.R != null && DeviceMainPage.this.R.isShowing()) {
                                    try {
                                        DeviceMainPage.this.R.dismiss();
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        }, 1000);
                    }
                });
                try {
                    this.R.showAtLocation(this.f16923a, 17, -1, -1);
                    SHBusinessManager.a().a(1, businessContent.f13950a, System.currentTimeMillis());
                    StatHelper.a(businessContent.f13950a);
                } catch (Exception unused) {
                }
            }
        }
    }

    private void u() {
        if (!DeviceMainPageHelper.a()) {
            if (this.H == null) {
                this.H = ((ViewStub) this.f16923a.findViewById(R.id.no_cache_offline_view_vs)).inflate();
            }
            this.H.setVisibility(0);
            this.z.setVisibility(8);
            ((TextView) this.H.findViewById(R.id.no_login_tv)).setText(R.string.network_disable);
            ((ImageView) this.H.findViewById(R.id.no_login_icon)).setImageResource(R.drawable.network_disconnected_icon_2);
            this.H.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DeviceMainPage.this.getContext().startActivity(new Intent("android.settings.SETTINGS"));
                }
            });
            View findViewById = this.H.findViewById(R.id.header_view_offline_container);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
            layoutParams.height = -2;
            layoutParams.addRule(13);
            findViewById.setLayoutParams(layoutParams);
        }
    }

    private void v() {
        IntentFilter intentFilter = new IntentFilter(HomeManager.S);
        intentFilter.addAction("com.smarthome.refresh_list_view");
        intentFilter.addAction(HomeManager.t);
        intentFilter.addAction(UserMamanger.b);
        intentFilter.addAction(CommonActivity.ACTION_SPLIT_SCREEN_MODE_CHANGED);
        intentFilter.addAction(HomeManager.u);
        intentFilter.addAction(SHBusinessManager.f13942a);
        intentFilter.addAction(DeviceTagInterface.A);
        intentFilter.addAction(f);
        intentFilter.addAction(GetDeviceTask.d);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.ah, intentFilter);
    }

    private void w() {
        LogUtil.a(s, "initViews");
        this.y = (ViewGroup) this.f16923a.findViewById(R.id.root_view);
        this.z = (CoordinatorLayout) this.f16923a.findViewById(R.id.coordinator_layout_vs);
        this.A = (MainPageAppBarLayout) this.f16923a.findViewById(R.id.main_appbar);
        this.ak = this.A.findViewById(R.id.module_a_3_right_iv_setting_btn);
        this.ak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.p();
                DeviceMainPage.this.ac.setVisibility(8);
                DeviceMainPage.this.ad.setVisibility(8);
                ChooseDeviceActivity.openChooseDevice(DeviceMainPage.this.getActivity());
                STAT.d.o(AndroidCommonConfigManager.a().k());
            }
        });
        int i2 = 0;
        DarkModeCompat.a(this.ak, false);
        this.ap = (MyViewPager) this.f16923a.findViewById(R.id.viewpager);
        this.ao = (MyIndicator) this.f16923a.findViewById(R.id.top_indicator);
        this.ai = (ViewStub) this.f16923a.findViewById(R.id.top_indicator_decor_vs);
        this.aj = this.ai.inflate();
        this.aj.setVisibility(this.am ? 8 : 0);
        this.ao.attachViewPager(this.ap);
        this.D = (TextView) this.f16923a.findViewById(R.id.module_a_3_return_title);
        this.D.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DeviceMainPage.this.c(view);
            }
        });
        DarkModeCompat.a((View) this.D, false);
        if (this.am) {
            this.D.setTextColor(-1);
        }
        this.E = (ImageView) this.f16923a.findViewById(R.id.home_name_arrow);
        this.F = (TextView) this.f16923a.findViewById(R.id.device_num);
        this.ac = (TextView) this.f16923a.findViewById(R.id.new_message_text);
        this.ad = this.f16923a.findViewById(R.id.new_message_tag);
        this.B = (ViewStub) this.f16923a.findViewById(R.id.bg_mask_vs);
        this.af = ObjectAnimator.ofFloat(this.F, "alpha", new float[]{1.0f, 0.0f});
        this.af.setDuration(300);
        this.ae = ObjectAnimator.ofFloat(this.F, "alpha", new float[]{0.0f, 1.0f});
        this.ae.setDuration(300);
        this.af.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                DeviceMainPage.this.F.setVisibility(8);
            }
        });
        this.ae.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                DeviceMainPage.this.F.setVisibility(0);
            }
        });
        this.G = (CustomBannerHeaderView) this.y.findViewById(R.id.custom_header_view);
        this.G.setDeviceMainPage(this);
        this.N = this.G.getCameraSize();
        this.A.setContentHeight(getResources().getDimensionPixelSize(this.N > 0 ? R.dimen.main_app_bar_layout_height_2 : R.dimen.main_app_bar_layout_height));
        CustomBannerHeaderView customBannerHeaderView = this.G;
        if (this.N <= 0) {
            i2 = 8;
        }
        customBannerHeaderView.setCameraContainerVisibility(i2, this.N);
        this.A.setOnRefreshListener(new SimplePushToRefreshHeader.OnRefreshListener() {
            public void a() {
                LogUtil.a(DeviceMainPage.s, "start refresh");
                DeviceMainPage.this.e(true);
                DevicePushBindManager.instance.checkDeviceWifi();
            }
        });
        this.A.setDeviceMainPage(this);
        this.A.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarStateChangeListener() {
            public void a(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if (DeviceMainPage.this.i) {
                    TitleBarUtil.a((Activity) DeviceMainPage.this.getActivity());
                }
            }
        });
        this.A.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarLayout.OnOffsetChangedListener() {

            /* renamed from: a  reason: collision with root package name */
            ArgbEvaluator f20216a = new ArgbEvaluator();
            Interpolator b = new AccelerateInterpolator(3.8f);
            boolean c = false;
            boolean d = false;
            int e = 0;
            int f = 0;

            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float abs = Math.abs(((float) i) / ((float) appBarLayout.getTotalScrollRange()));
                if (!DeviceMainPage.this.i) {
                    if (Float.compare(abs, 0.3f) > 0) {
                        if (!this.c) {
                            this.c = true;
                            this.d = false;
                            TitleBarUtil.a((Activity) DeviceMainPage.this.getActivity());
                        }
                    } else if (!this.d) {
                        this.d = true;
                        this.c = false;
                        TitleBarUtil.b((Activity) DeviceMainPage.this.getActivity());
                    }
                }
                if (!DeviceMainPage.this.am) {
                    float interpolation = this.b.getInterpolation(abs);
                    if (DeviceMainPage.this.C == null) {
                        View unused = DeviceMainPage.this.C = DeviceMainPage.this.B.inflate();
                    }
                    DeviceMainPage.this.C.setAlpha(interpolation);
                    if (DeviceMainPage.this.aj == null) {
                        View unused2 = DeviceMainPage.this.aj = DeviceMainPage.this.ai.inflate();
                    }
                    DeviceMainPage.this.aj.setAlpha(0.0f);
                    if (Float.compare(interpolation, 1.0f) == 0) {
                        DeviceMainPage.this.aj.setAlpha(1.0f);
                    }
                    if (this.f == 0) {
                        this.f = 16185338;
                    }
                    if (this.e == 0) {
                        this.e = DeviceMainPage.this.getResources().getColor(R.color.main_view_pager_bg);
                    }
                    DeviceMainPage.this.ap.setBackgroundColor(((Integer) this.f20216a.evaluate(interpolation, Integer.valueOf(this.f), Integer.valueOf(this.e))).intValue());
                }
            }
        });
        this.J = (ViewStub) this.f16923a.findViewById(R.id.fab_vs);
        x();
        b(true);
        MyViewPager myViewPager = this.ap;
        RoomPagerAdapter roomPagerAdapter = new RoomPagerAdapter(getContext(), this.ap, this);
        this.aq = roomPagerAdapter;
        myViewPager.setAdapter(roomPagerAdapter);
        boolean c2 = HomeManager.a().c();
        String str = s;
        LogUtilGrey.a(str, "initViews: mIsDeviceDataReady: " + this.O + " ;isHomeManagerInited: " + c2);
        this.aq.a(this);
        if (this.O && c2) {
            this.aq.b();
        }
        z();
        a(getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        startActivity(new Intent(getActivity(), ChangeHomeActivity.class));
    }

    private void a(Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(this.as, new IntentFilter(RoomPagerAdapter.f20366a));
    }

    private void x() {
        if (this.K == null) {
            this.K = (ImageView) this.J.inflate();
        }
        if (!VoiceManager.a().b() || ServerCompact.e(getContext())) {
            this.K.setVisibility(8);
        } else {
            this.K.setVisibility(0);
        }
        this.K.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MiBrainManager.l()) {
                    MiBrainManager.m();
                    return;
                }
                PermissionHelper.b(DeviceMainPage.this.getActivity(), true, new Action() {
                    public void onAction(List<String> list) {
                        NLProcessor.b = "";
                        Intent intent = new Intent(DeviceMainPage.this.getActivity(), MiBrainActivityNew.class);
                        intent.putExtra("from", "mainpage");
                        DeviceMainPage.this.getActivity().startActivity(intent);
                        StatHelper.an();
                        STAT.d.b(1);
                    }
                });
                STAT.d.g();
            }
        });
    }

    public void c() {
        if (HomeManager.a().f() != null && HomeManager.a().f().size() != 0 && !this.i && this.G != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ap, "scaleX", new float[]{1.0f, 0.9f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.ap, "scaleY", new float[]{1.0f, 0.9f});
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f16923a, "alpha", new float[]{1.0f, 0.0f});
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.ao, "scaleX", new float[]{1.0f, 0.9f});
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.ao, "scaleY", new float[]{1.0f, 0.9f});
            ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.ao, "translationY", new float[]{0.0f, (((float) this.ap.getHeight()) * 0.100000024f) / 2.0f});
            animatorSet.setDuration(100);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat6).with(ofFloat4).with(ofFloat5);
            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    if (DeviceMainPage.this.isValid()) {
                        ((SmartHomeMainActivity) DeviceMainPage.this.getActivity()).enterHomeEnvInfoFragment();
                    }
                }
            });
        }
    }

    public void d() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ap, "scaleX", new float[]{0.9f, 1.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.ap, "scaleY", new float[]{0.9f, 1.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f16923a, "alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.ao, "scaleX", new float[]{0.9f, 1.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.ao, "scaleY", new float[]{0.9f, 1.0f});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.ao, "translationY", new float[]{(((float) this.ap.getHeight()) * 0.100000024f) / 2.0f, 0.0f});
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4).with(ofFloat5).with(ofFloat6);
        animatorSet.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        });
        animatorSet.start();
    }

    public void e() {
        if (this.A != null) {
            this.A.expand(this.z, this.A);
        }
    }

    public boolean f() {
        if (this.A != null) {
            return !this.A.isCollapsingMode();
        }
        return false;
    }

    public void g() {
        if (this.ac != null && this.ac.getVisibility() == 0) {
            this.ac.setVisibility(8);
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    Home m = HomeManager.a().m();
                    if (DeviceMainPage.this.ac != null && m != null && m.p()) {
                        DeviceMainPage.this.ac.setVisibility(0);
                    }
                }
            }, 300);
        }
    }

    private void y() {
        LogUtilGrey.a("forceUpdateAllData", "forceUpdate getCurrentState=" + SHApplication.getStateNotifier().a());
        if (SHApplication.getStateNotifier().a() != 4) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(HomeManager.S));
            return;
        }
        boolean h2 = HomeManager.a().h();
        LogUtil.a("forceUpdateAllData", "forceUpdate isTransferred=" + h2);
        if (h2) {
            LogUtilGrey.a("forceUpdateAllData", "HomeManager forceUpdateAllData start mIsDeviceDataReady=" + this.O + ",DeviceManager isInited=" + SmartHomeDeviceManager.a().u());
            HomeManager.a().b(!this.O && SmartHomeDeviceManager.a().u());
            TopWidgetDataManagerNew.b().c();
            if (this.O) {
                FragmentActivity activity = getActivity();
                activity.getClass();
                ((HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class)).b(HomeManager.a().l());
                FragmentActivity activity2 = getActivity();
                activity2.getClass();
                ((HomeEnvInfoViewModel) ViewModelProviders.a(activity2).a(HomeEnvInfoViewModel.class)).a(HomeManager.a().l());
                return;
            }
            return;
        }
        HomeManager.a().a((HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
            public void a(int i, Error error) {
            }

            public void a() {
                if (DeviceMainPage.this.isValid()) {
                    if (HomeManager.a().h()) {
                        HomeManager.a().b(false);
                        TopWidgetDataManagerNew.b().c();
                        if (DeviceMainPage.this.O) {
                            FragmentActivity activity = DeviceMainPage.this.getActivity();
                            activity.getClass();
                            ((HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class)).b(HomeManager.a().l());
                            FragmentActivity activity2 = DeviceMainPage.this.getActivity();
                            activity2.getClass();
                            ((HomeEnvInfoViewModel) ViewModelProviders.a(activity2).a(HomeEnvInfoViewModel.class)).a(HomeManager.a().l());
                        }
                        AreaInfoManager.a().a(SHApplication.getAppContext(), true);
                        return;
                    }
                    LogUtilGrey.a("forceUpdateAllData", "transfer complete, but state is wrong!!");
                }
            }
        });
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        DeviceRecycler f2;
        if (i2 == q && i3 == -1 && this.aq != null && (f2 = this.aq.f()) != null) {
            f2.forceRefreshDevice();
        }
    }

    public RecyclerView getRecyclerView() {
        if (this.aq != null) {
            return this.aq.d();
        }
        return null;
    }

    public void a(boolean z2) {
        a((String) null, z2);
        if (this.aq != null) {
            this.aq.a(z2);
        }
        if (z2) {
            this.at = STAT.c.f(0);
        } else if (this.at > 0) {
            STAT.c.f(this.at);
            this.at = 0;
        }
        this.P = z2;
        if (this.A != null && this.P) {
            this.A.refreshUI();
        }
        H();
        t();
        MiBrainManager.a().a(z2);
        if (!z2) {
            exitEditMode();
        }
        if (!z2) {
            this.k = true;
        }
        if (z2) {
            IndexNoDeviceOperation.m();
        }
    }

    public void h() {
        if (this.aq != null) {
            this.aq.a(PageBean.a(this.mContext));
        }
    }

    public void b(boolean z2) {
        if (this.A != null && this.z != null) {
            if (SHApplication.isInSplitScreenMode()) {
                this.A.setNormalScrollEnable(true, this.z);
            }
            this.A.setNormalScrollEnable(z2, this.z);
        }
    }

    public void onPause() {
        super.onPause();
        DevicePushBindManager.instance.onPause();
        this.ab = false;
        MiBrainManager.a().a((Activity) getActivity()).c();
        if (this.al != null) {
            this.al.onPause(this);
            this.al = null;
        }
    }

    public void onResume() {
        super.onResume();
        DevicePushBindManager.instance.onResume();
        z();
        if (this.K != null) {
            if (HomeManager.A() || !VoiceManager.a().b() || !this.P || SHApplication.getStateNotifier().a() != 4) {
                this.K.setVisibility(8);
            } else if (this.K.getVisibility() != 0) {
                this.K.setVisibility(0);
            }
        }
        if (!this.Q) {
            j();
        }
        this.ab = true;
        this.ag.postDelayed(new Runnable() {
            public void run() {
                if (!DeviceMainPage.this.L && DeviceMainPage.this.isValid()) {
                    if (IRDeviceUtil.c()) {
                        AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                            /* access modifiers changed from: protected */
                            public Object doInBackground(Object... objArr) {
                                try {
                                    IRDeviceUtil.a(SHApplication.getAppContext());
                                    return null;
                                } catch (Throwable th) {
                                    th.printStackTrace();
                                    return null;
                                }
                            }

                            /* access modifiers changed from: protected */
                            public void onPostExecute(Object obj) {
                                if (DeviceMainPage.this.isValid() && !DeviceMainPage.this.Q) {
                                    DeviceMainPage.this.j();
                                }
                            }
                        }, new Object[0]);
                    }
                    if (DeviceMainPage.this.X == null) {
                        VirtualDeviceManager unused = DeviceMainPage.this.X = new VirtualDeviceManager();
                    }
                    if (SHApplication.getStateNotifier().a() == 4) {
                        LogUtil.a("forceUpdateAllData", "startRefresh STATE_LOGIN_SUCCESS");
                        DeviceMainPage.this.e(false);
                    }
                    CameraInfoRefreshManager.a().f();
                }
            }
        }, 1000);
        d(false);
        Device device = DeviceRenderer.d;
        if (device != null) {
            DeviceRenderer.a(device).a();
        }
        if (this.P) {
            E();
            if (SmartHomeDeviceManager.a().t()) {
                this.S = true;
            } else if (this.ao == null) {
                this.ag.postDelayed(new Runnable() {
                    public final void run() {
                        DeviceMainPage.this.o();
                    }
                }, Constants.x);
            } else {
                o();
            }
        }
        if (this.al != null) {
            this.al.onResume(this);
        }
    }

    private void d(final boolean z2) {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                AreaInfoManager.a().a(SHApplication.getAppContext(), z2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void e(boolean z2) {
        LogUtil.a("forceUpdateAllData", "startRefresh " + z2 + ",loginstate=" + SHApplication.getStateNotifier().a());
        if (this.i) {
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(HomeManager.S));
            return;
        }
        if (SHApplication.getStateNotifier().a() == 4) {
            if (z2) {
                y();
            }
            if (this.U) {
                this.U = false;
                boolean u2 = SmartHomeDeviceManager.a().u();
                LogUtil.a("forceUpdateAllData", "startRefresh SmartHomeDeviceManager inited=" + u2);
                if (u2) {
                    this.Q = false;
                }
                FragmentActivity activity = getActivity();
                activity.getClass();
                ((HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class)).e(HomeManager.a().l());
            }
            VoiceManager.a().a((AsyncCallback) this.l);
            AndroidCommonConfigManager.a().i();
            LightGroupManager.a().b();
            DeviceListSwitchManager.a().d();
            CameraInfoRefreshManager.a().g();
            I();
            SHBusinessManager.a().b();
            CommonExtraConfigManager.a().c();
            TimerCommonConfigManager.a().b();
        } else if (SHApplication.getStateNotifier().a() == 3) {
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(new Intent(HomeManager.S));
            d(true);
        }
        if (this.l == null) {
            this.l = new BrainSettingCallback(this);
        }
    }

    public void onDestroyView() {
        if (this.F != null) {
            this.F.setVisibility(8);
        }
        this.L = true;
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.as);
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        this.U = true;
        this.ag.removeCallbacksAndMessages((Object) null);
        DeviceListSwitchManager.a().e();
        SmartHomeDeviceManager.a().c(this.p);
        try {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.ah);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.k = true;
        LiteSoundManager.a().c();
        this.ae.cancel();
        this.af.cancel();
        if (this.h != null && this.h.isShowing()) {
            this.h.dismiss();
        }
        if (this.j != null && this.j.isShowing()) {
            this.j.dismiss();
        }
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.ar);
        LoginManager.a().b(this.aw);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.ag.postDelayed(new Runnable() {
            public void run() {
                DeviceMainPage.this.e(true);
            }
        }, 1000);
        TitleBarUtil.b((Activity) getActivity());
        try {
            ((ReactiveWallpaper) getActivity().findViewById(R.id.react_wallpaper)).attach();
        } catch (Exception unused) {
        }
    }

    public void refreshTitleBar() {
        if (this.i) {
            TitleBarUtil.a((Activity) getActivity());
        } else if (this.A == null) {
        } else {
            if (this.A.isCollapsingMode()) {
                TitleBarUtil.a((Activity) getActivity());
            } else {
                TitleBarUtil.b((Activity) getActivity());
            }
        }
    }

    public void onClickCommonUseDevice(Device device, RectF rectF, String str) {
        SmartHomeMainActivity smartHomeMainActivity = (SmartHomeMainActivity) getActivity();
        if (smartHomeMainActivity != null && isValid()) {
            smartHomeMainActivity.onClickCommonUseDevice(device, rectF, str);
        }
    }

    public boolean isEditMode() {
        return this.i;
    }

    public void enterEditMode(DviceEditInterface dviceEditInterface) {
        if (!this.i && dviceEditInterface != null) {
            this.au = false;
            this.ap.onEnterEditMode();
            this.ao.onEnterEditMode();
            this.aq.h();
            SmartHomeMainActivity smartHomeMainActivity = (SmartHomeMainActivity) getActivity();
            if (smartHomeMainActivity != null && !smartHomeMainActivity.isFinishing()) {
                this.i = true;
                this.r = dviceEditInterface;
                BluetoothHelper.b();
                try {
                    View chooseDeviceTitleBar = smartHomeMainActivity.getChooseDeviceTitleBar();
                    this.Z = (ImageView) chooseDeviceTitleBar.findViewById(EditActionMode.f3057a);
                    this.Y = (ImageView) chooseDeviceTitleBar.findViewById(EditActionMode.b);
                    this.Y.setImageResource(R.drawable.title_right_tick_drawable);
                    this.n = (TextView) chooseDeviceTitleBar.findViewById(R.id.module_a_4_return_more_title);
                    this.Y.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            DeviceMainPage.this.b(view);
                        }
                    });
                    this.Z.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            DeviceMainPage.a(DviceEditInterface.this, view);
                        }
                    });
                    DeviceMainPageEditBarV2 deviceMainPageEditBarV2 = (DeviceMainPageEditBarV2) smartHomeMainActivity.getChooseDeviceMenuBar2();
                    deviceMainPageEditBarV2.setDeviceMainPage(this);
                    deviceMainPageEditBarV2.enterEditMode(dviceEditInterface);
                    deviceMainPageEditBarV2.setOnMenuBarPositionChangeListener(this.aa);
                    if (deviceMainPageEditBarV2.getHeight() == 0) {
                        deviceMainPageEditBarV2.post(new Runnable(deviceMainPageEditBarV2, chooseDeviceTitleBar) {
                            private final /* synthetic */ DeviceMainPageEditBarV2 f$1;
                            private final /* synthetic */ View f$2;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                            }

                            public final void run() {
                                DeviceMainPage.this.a(this.f$1, this.f$2);
                            }
                        });
                    } else {
                        a(deviceMainPageEditBarV2, chooseDeviceTitleBar, true);
                    }
                    refreshTitleBar();
                    if (this.f16923a != null) {
                        this.f16923a.requestLayout();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                dviceEditInterface.j();
                if (this.K != null) {
                    this.K.setVisibility(8);
                }
                STAT.c.d(dviceEditInterface.q());
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        exitEditMode();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DviceEditInterface dviceEditInterface, View view) {
        boolean z2 = dviceEditInterface.l().size() < dviceEditInterface.p();
        for (int i2 = 0; i2 < dviceEditInterface.n(); i2++) {
            dviceEditInterface.a(i2, z2);
        }
        dviceEditInterface.o();
        STAT.d.i(z2 ? 1 : 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DeviceMainPageEditBarV2 deviceMainPageEditBarV2, View view) {
        a(deviceMainPageEditBarV2, view, true);
    }

    private void a(final DeviceMainPageEditBarV2 deviceMainPageEditBarV2, final View view, final boolean z2) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        deviceMainPageEditBarV2.setVisibility(0);
        view.setVisibility(0);
        ViewGroup viewGroup = (ViewGroup) deviceMainPageEditBarV2.getParent();
        if (z2) {
            objectAnimator = ObjectAnimator.ofFloat(deviceMainPageEditBarV2, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - deviceMainPageEditBarV2.getHeight())});
            objectAnimator2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
        } else {
            objectAnimator = ObjectAnimator.ofFloat(deviceMainPageEditBarV2, View.Y, new float[]{(float) (viewGroup.getHeight() - deviceMainPageEditBarV2.getHeight()), (float) viewGroup.getHeight()});
            objectAnimator2 = ObjectAnimator.ofFloat(view, View.Y, new float[]{0.0f, (float) (-view.getHeight())});
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(objectAnimator).with(objectAnimator2);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (!z2) {
                    view.clearAnimation();
                    view.setVisibility(8);
                    deviceMainPageEditBarV2.exitEditMode();
                }
            }
        });
    }

    public void exitEditMode() {
        if (this.j != null && this.j.isShowing()) {
            this.j.dismiss();
        }
        if (this.i) {
            this.ap.onExitEditMode();
            this.ao.onExitEditMode();
            this.aq.i();
            SmartHomeMainActivity smartHomeMainActivity = (SmartHomeMainActivity) getActivity();
            if (smartHomeMainActivity != null && !smartHomeMainActivity.isFinishing()) {
                this.i = false;
                a((DeviceMainPageEditBarV2) smartHomeMainActivity.getChooseDeviceMenuBar2(), smartHomeMainActivity.getChooseDeviceTitleBar(), false);
                refreshTitleBar();
                if (this.r != null) {
                    this.r.k();
                    this.r.o();
                    this.r = null;
                }
                if (this.au) {
                    j();
                }
                H();
            }
        }
    }

    public void updateActionItems(DviceEditInterface dviceEditInterface, int i2, String str, boolean z2) {
        if (i2 > 0) {
            try {
                this.n.setText(getResources().getQuantityString(R.plurals.edit_choosed_device, i2, new Object[]{Integer.valueOf(i2)}));
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            this.n.setText(R.string.title_choose_device);
        }
        this.n.setTypeface((Typeface) null, 0);
        if (i2 >= dviceEditInterface.p()) {
            this.Z.setImageResource(R.drawable.un_select_selector);
        } else {
            this.Z.setImageResource(R.drawable.all_select_selector);
        }
        SmartHomeMainActivity smartHomeMainActivity = (SmartHomeMainActivity) getActivity();
        if (smartHomeMainActivity == null) {
            return;
        }
        if (!smartHomeMainActivity.isFinishing()) {
            ((DeviceMainPageEditBarV2) smartHomeMainActivity.getChooseDeviceMenuBar2()).updateActionItems(dviceEditInterface, str, z2);
        }
    }

    public boolean onBackPressed() {
        if (this.aq != null && this.aq.j()) {
            return true;
        }
        if (this.i) {
            exitEditMode();
            return true;
        }
        MiBrainManager.a().a((Activity) getActivity()).d();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
        return true;
    }

    public void onStateChanged() {
        LogUtil.a(s, "onStateChanged");
        j();
        exitEditMode();
        FragmentActivity activity = getActivity();
        activity.getClass();
        ((HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class)).a(HomeManager.a().l());
        if (this.h != null) {
            this.h.dismiss();
        }
        if (this.j != null) {
            this.j.dismiss();
            this.j = null;
        }
    }

    public void j() {
        this.ag.removeMessages(3);
        if (this.m) {
            this.ag.sendEmptyMessageDelayed(3, 500);
        } else {
            k();
        }
    }

    public void c(boolean z2) {
        if (z2 || !this.i) {
            this.ag.removeMessages(3);
            this.ag.sendEmptyMessage(3);
        }
    }

    public void k() {
        if (this.i) {
            this.au = true;
            return;
        }
        B();
        LogUtil.a(s, "notifyDataSetChanged");
        if (this.T) {
            m();
            z();
            l();
            if (this.I && this.O) {
                this.I = false;
                s();
            }
        }
    }

    public void l() {
        boolean z2 = true;
        List<PageBean.Classify> a2 = PageBean.a(this.mContext, HomeManager.a().e(), true);
        boolean c2 = HomeManager.a().c();
        String str = s;
        StringBuilder sb = new StringBuilder();
        sb.append("refreshPages rooms size=");
        sb.append(a2 == null ? null : Integer.valueOf(a2.size()));
        sb.append(" ;isHomeManagerInited: ");
        sb.append(c2);
        sb.append(" mRefreshAll:");
        sb.append(this.V);
        LogUtilGrey.a(str, sb.toString());
        RoomPagerAdapter roomPagerAdapter = this.aq;
        if (!this.O || !c2) {
            z2 = false;
        }
        roomPagerAdapter.a(a2, z2, this.V);
        this.V = false;
    }

    /* access modifiers changed from: private */
    public void z() {
        if (n()) {
            if (F()) {
                if (this.D != null) {
                    this.D.setOnClickListener((View.OnClickListener) null);
                    this.D.setVisibility(8);
                    this.E.setVisibility(8);
                }
                if (this.G != null) {
                    this.G.showLoginView();
                }
            } else if (this.D != null) {
                int i2 = 0;
                this.D.setVisibility(0);
                this.D.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        DeviceMainPage.this.a(view);
                    }
                });
                Home m2 = HomeManager.a().m();
                if (m2 == null || TextUtils.isEmpty(m2.k())) {
                    this.D.setText("");
                    this.E.setVisibility(8);
                    return;
                }
                this.D.setText(m2.k());
                this.E.setVisibility(0);
                boolean p2 = m2.p();
                View view = this.ak;
                if (!p2) {
                    i2 = 8;
                }
                view.setVisibility(i2);
                if (!p2) {
                    this.ad.setVisibility(8);
                    this.ac.setVisibility(8);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        startActivity(new Intent(getActivity(), ChangeHomeActivity.class));
    }

    /* access modifiers changed from: private */
    public void A() {
        this.N = HomeManager.a().B(HomeManager.a().l());
    }

    /* access modifiers changed from: private */
    public void B() {
        if (this.G != null && this.G.getVisibility() != 8) {
            if (this.A != null) {
                this.A.setCameraVisibility(this.N);
            }
            if (this.N <= 0 || this.i) {
                r();
            } else {
                b(this.N);
            }
        }
    }

    /* access modifiers changed from: private */
    public void C() {
        LogUtilGrey.a("forceUpdateAllData", "DeviceMainPage onLoginSuccess in");
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                if (DeviceMainPage.this.isValid()) {
                    SharedHomeDeviceManager.b().c();
                    DeviceMainPage.this.e(true);
                    DeviceMainPage.this.z();
                    if (!CoreApi.a().D()) {
                        ThirdAccountBindManager.a().b((AsyncCallback<Void, Error>) null);
                    }
                    ServerApi.a().a(false);
                    SceneManager.x().c((String) null);
                    try {
                        ConsumableDataManager.a().a((Context) DeviceMainPage.this.getActivity(), (String) null, false, (ConsumableDataManager.IConsumableListener) null);
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void D() {
        LogUtil.a("forceUpdateAllData", "startRefresh onLogout");
        DeviceTagManager deviceTagManager = (DeviceTagManager) SmartHomeDeviceHelper.a().b();
        if (this.D != null) {
            this.D.setText("");
            this.E.setVisibility(8);
        }
        if (this.aq != null) {
            this.aq.c();
        }
        deviceTagManager.h();
        this.M = null;
        this.O = false;
        this.m = false;
        m();
        z();
        FragmentActivity activity = getActivity();
        activity.getClass();
        ((HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class)).d();
        this.av.set(false);
    }

    public boolean m() {
        if (!this.T || SHApplication.getStateNotifier().a() != 3) {
            return false;
        }
        h();
        return true;
    }

    private void E() {
        if (!this.k && !HomeManager.A()) {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    try {
                        YoupinPopupActivity.checkAndShowIfNeeded(DeviceMainPage.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.ag.sendEmptyMessageDelayed(1, 2000);
        }
    }

    public boolean n() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
            f(activeNetworkInfo != null);
            if (activeNetworkInfo != null) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    private void f(boolean z2) {
        String str = s;
        LogUtil.a(str, "showOffline " + z2);
        if (this.H == null) {
            this.H = ((ViewStub) this.f16923a.findViewById(R.id.no_cache_offline_view_vs)).inflate();
        }
        if (this.H != null) {
            this.H.setVisibility(8);
        }
        if (this.z != null) {
            this.z.setVisibility(0);
        }
        if (this.G != null) {
            if (z2) {
                if (!F()) {
                    this.G.showContentView();
                    B();
                } else {
                    this.G.showLoginView();
                }
                if (SHApplication.getStateNotifier().a() != 4 && SHApplication.getStateNotifier().a() == 3) {
                    h();
                    return;
                }
                return;
            }
            this.Q = false;
            this.G.showOfflineView();
            this.D.setText("");
            this.E.setVisibility(8);
            r();
            u();
        }
    }

    private boolean F() {
        return SHApplication.getStateNotifier().a() == 3;
    }

    /* access modifiers changed from: private */
    public void G() {
        j();
        DeviceListSwitchManager.a().b();
    }

    /* access modifiers changed from: private */
    public void H() {
        if (this.f16923a != null && this.K != null) {
            if (HomeManager.A() || !VoiceManager.a().b() || !this.P || SHApplication.getStateNotifier().a() != 4) {
                if (isValid()) {
                    this.K.setVisibility(8);
                }
            } else if (isValid()) {
                this.K.setVisibility(0);
            }
        }
    }

    private void I() {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                if (DeviceMainPage.this.isValid()) {
                    MessageCenter a2 = MessageCenter.a();
                    a2.b();
                    a2.c();
                }
            }
        });
    }

    public void o() {
        List<Device> d2;
        String i2 = DeviceFinder.a().i();
        if (!TextUtils.isEmpty(i2) && (d2 = SmartHomeDeviceManager.a().d()) != null && !d2.isEmpty()) {
            for (int i3 = 0; i3 < d2.size(); i3++) {
                Device device = d2.get(i3);
                if (device != null && TextUtils.equals(device.did, i2)) {
                    device.isNew = true;
                    DeviceFinder.a().j();
                    LogUtil.a(s, "updateScrollState");
                    if (this.ao != null) {
                        this.ao.selectDevicePage();
                        Intent intent = new Intent(DropMenuAdapter.f20253a);
                        intent.putExtra(DropMenuAdapter.c, PageBean.a().f);
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
                        return;
                    }
                    return;
                }
            }
        }
    }

    public void a(String str) {
        DeviceFinder.a().c(str);
        o();
    }

    public void b(String str) {
        MyIndicator myIndicator = this.ao;
    }

    static class BrainSettingCallback extends AsyncCallback {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<DeviceMainPage> f20242a;

        public void onFailure(Error error) {
        }

        BrainSettingCallback(DeviceMainPage deviceMainPage) {
            this.f20242a = new WeakReference<>(deviceMainPage);
        }

        public void onSuccess(Object obj) {
            if (this.f20242a.get() != null) {
                ((DeviceMainPage) this.f20242a.get()).H();
            }
        }
    }

    public void a(int i2) {
        this.F.setText(XMStringUtils.a(this.mContext, (int) R.plurals.home_device_size, i2, (Object) Integer.valueOf(i2)));
        if (this.af.isRunning()) {
            this.af.cancel();
            this.ae.start();
        }
        if (this.F.getVisibility() == 8) {
            this.ae.start();
        }
        this.ag.removeMessages(8);
        this.ag.sendEmptyMessageDelayed(8, 1700);
    }

    /* access modifiers changed from: private */
    public void J() {
        ShareAlertDialogHelper.a((BaseActivity) getActivity(), 1);
        ShareAlertDialogHelper.a((BaseActivity) getActivity(), 8);
    }
}
