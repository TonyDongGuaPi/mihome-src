package com.xiaomi.smarthome.miio.page;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.places.model.PlaceFields;
import com.google.android.exoplayer2.C;
import com.libra.Color;
import com.xiaomi.smarthome.CommentInternationalHelper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountGroupListActivity;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.SHBusinessManager;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.family.FamilyActivity;
import com.xiaomi.smarthome.feedback.FeedBackMainActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.login.ui.LogoutActivity;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.page.TabFragment;
import com.xiaomi.smarthome.framework.page.rndebug.DevSettingRnDebugListActivity;
import com.xiaomi.smarthome.framework.permission.PermissionRequestActivity;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.webview.CommonWebViewActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.homedevicelist.DeviceCountManager;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.activity.AboutActivity;
import com.xiaomi.smarthome.miio.activity.BleGatewayListActivity;
import com.xiaomi.smarthome.miio.activity.ConsumablesActivity;
import com.xiaomi.smarthome.miio.activity.LaboratoryActivity;
import com.xiaomi.smarthome.miio.activity.LicenseChooseActivity;
import com.xiaomi.smarthome.miio.consumables.ConsumableDataManager;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.newui.MyScaleAnimation;
import com.xiaomi.smarthome.operation.OperationCollector;
import com.xiaomi.smarthome.operation.my.MyOperation;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.voice.VoiceManager;
import com.xiaomi.smarthome.voice.VoiceSettingActivity;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class SettingMainPageV2 extends TabFragment {
    public static final String b = "message_center_red_dot_clicked";
    public static final String c = "new_message_count";
    public static final String d = "new_feedback_count";
    public static final int e = 1001;
    /* access modifiers changed from: private */
    public static final String g = "SettingMainPageV2";
    boolean f = false;
    private List<ViewGroup> h = new ArrayList();
    private boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    private long k;
    /* access modifiers changed from: private */
    public boolean l;
    @BindView(2131431998)
    View layoutRnDebug;
    private MyIClientDeviceListener m = null;
    @BindView(2131427688)
    AppBarLayout mAppBarLayout;
    @BindView(2131428402)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(2131428537)
    View mConsumableRedDot;
    @BindView(2131431965)
    RelativeLayout mConsumables;
    @BindView(2131433748)
    TextView mDevCountView;
    @BindView(2131431974)
    RelativeLayout mFamily;
    @BindView(2131432308)
    ViewGroup mGroup1;
    @BindView(2131432309)
    ViewGroup mGroup2;
    @BindView(2131432310)
    ViewGroup mGroup3;
    @BindView(2131427442)
    View mImgAboutDot;
    @BindView(2131429212)
    View mImgFeedbackDot;
    @BindView(2131431983)
    RelativeLayout mLaboratory;
    @BindView(2131430292)
    View mLaboratoryRedDot;
    @BindView(2131428360)
    View mLoginFrame;
    @BindView(2131432306)
    View mLoginFrameBottom;
    @BindView(2131432004)
    RelativeLayout mMyShopBtn;
    @BindView(2131430838)
    View mNewMsgTag;
    @BindView(2131433757)
    TextView mNickView;
    @BindView(2131431990)
    ViewGroup mPermissionBtn;
    @BindView(2131432001)
    RelativeLayout mScore;
    @BindView(2131432007)
    RelativeLayout mThirdAccountBtn;
    @BindView(2131433521)
    TextView mTitle;
    @BindView(2131432919)
    View mToolbar;
    @BindView(2131433756)
    SimpleDraweeView mUsrIcon;
    @BindView(2131433822)
    CoordinatorLayout mViewContainer;
    @BindView(2131432015)
    RelativeLayout mVoiceAssistant;
    @BindView(2131432016)
    RelativeLayout mVoiceControl;
    @BindView(2131433887)
    View mVoiceRedDot;
    private BroadcastReceiver n = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("action_on_logout".equals(intent.getAction())) {
                SettingMainPageV2.this.h();
            }
        }
    };
    private String o;
    private Handler p = new Handler(Looper.getMainLooper());
    private boolean q = true;
    private long r = 0;
    /* access modifiers changed from: private */
    public boolean s = false;
    private BroadcastReceiver t = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            boolean unused = SettingMainPageV2.this.s = true;
        }
    };
    @BindView(2131433464)
    TextView tvRnDebugLeftInfo2;

    /* access modifiers changed from: private */
    public /* synthetic */ boolean c(View view) {
        return false;
    }

    private static class MyIClientDeviceListener implements SmartHomeDeviceManager.IClientDeviceListener {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<SettingMainPageV2> f19648a;

        public void a(int i, Device device) {
        }

        public MyIClientDeviceListener(SettingMainPageV2 settingMainPageV2) {
            this.f19648a = new WeakReference<>(settingMainPageV2);
        }

        public void a(int i) {
            SettingMainPageV2 settingMainPageV2;
            if (i == 3 && (settingMainPageV2 = (SettingMainPageV2) this.f19648a.get()) != null && settingMainPageV2.isValid()) {
                settingMainPageV2.a();
            }
        }

        public void b(int i) {
            SettingMainPageV2 settingMainPageV2;
            if (i == 3 && (settingMainPageV2 = (SettingMainPageV2) this.f19648a.get()) != null && settingMainPageV2.isValid()) {
                settingMainPageV2.a();
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        SHBusinessManager.a().d();
    }

    private void a(View view) {
        if (!HomeManager.A()) {
            OperationCollector.b((Fragment) this).a(view);
        }
    }

    public void a() {
        if (this.mDevCountView != null) {
            if (SHApplication.getStateNotifier().a() != 4) {
                this.mDevCountView.setText("");
            } else {
                DeviceCountManager.a().b().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
                    /* renamed from: a */
                    public void accept(Integer num) throws Exception {
                        String str;
                        if (SettingMainPageV2.this.isValid()) {
                            try {
                                if (num.intValue() == 0) {
                                    str = SettingMainPageV2.this.getResources().getString(R.string.setting_total_device_none);
                                } else {
                                    str = SettingMainPageV2.this.getResources().getQuantityString(R.plurals.setting_total_device, num.intValue(), new Object[]{num});
                                }
                                MyLog.d(str);
                                SettingMainPageV2.this.mDevCountView.setText(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str = g;
        LogUtil.a(str, g + "onCreateView");
        this.f = false;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.f16923a == null) {
            this.f16923a = layoutInflater.inflate(R.layout.setting_main_page_v2, (ViewGroup) null);
            this.q = !SHApplication.isCurrentHotStart();
            if (this.q) {
                SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                    public final void run() {
                        SettingMainPageV2.this.i();
                    }
                }, 1000);
            } else {
                i();
            }
        }
        String str2 = g;
        Log.d(str2, "" + (System.currentTimeMillis() - currentTimeMillis));
        return this.f16923a;
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 100 && i3 == 1001) {
            FragmentActivity activity = getActivity();
            if (activity instanceof SmartHomeMainActivity) {
                ((SmartHomeMainActivity) activity).gotoDevicePage();
            }
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        if (!this.f && isValid()) {
            ViewStub viewStub = (ViewStub) this.f16923a.findViewById(R.id.setting_main_page_content_view_stub);
            if (viewStub != null) {
                viewStub.inflate();
            }
            ButterKnife.bind((Object) this, this.f16923a);
            if (this.l) {
                this.mLoginFrame.setBackgroundColor(Color.d);
                this.mLoginFrameBottom.setBackgroundColor(Color.d);
                this.mToolbar.setBackgroundColor(Color.c);
            }
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.q) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable(view) {
                private final /* synthetic */ View f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SettingMainPageV2.this.d(this.f$1);
                }
            }, 1000);
        } else {
            d(view);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void d(View view) {
        if (!this.f && isValid()) {
            f();
            this.h.add(this.mGroup1);
            this.h.add(this.mGroup2);
            this.h.add(this.mGroup3);
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public final void onCoreReady() {
                    SettingMainPageV2.this.l();
                }
            });
            View findViewById = view.findViewById(R.id.rl_about);
            if (findViewById != null) {
                findViewById.setOnLongClickListener(new View.OnLongClickListener() {
                    public final boolean onLongClick(View view) {
                        return SettingMainPageV2.this.c(view);
                    }
                });
            }
            c();
            MessageCenter a2 = MessageCenter.a();
            a2.b();
            a2.c();
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.t, new IntentFilter(UserMamanger.f19980a));
            this.mAppBarLayout.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarLayout.OnOffsetChangedListener() {

                /* renamed from: a  reason: collision with root package name */
                Interpolator f19641a = new DecelerateInterpolator(2.0f);

                public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                    float abs = Math.abs(((float) i) / ((float) appBarLayout.getTotalScrollRange()));
                    int i2 = 0;
                    if (((double) abs) < 0.64d) {
                        SettingMainPageV2.this.mTitle.setVisibility(8);
                    } else {
                        SettingMainPageV2.this.mTitle.setVisibility(0);
                    }
                    View view = SettingMainPageV2.this.mToolbar;
                    if (abs == 0.0f) {
                        i2 = 8;
                    }
                    view.setVisibility(i2);
                    SettingMainPageV2.this.mToolbar.setAlpha(this.f19641a.getInterpolation(abs));
                }
            });
            this.mFamily.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.layoutRnDebug != null) {
            if (this.j) {
                this.layoutRnDebug.setVisibility(0);
                this.i = DevelopSharePreManager.a().g();
                this.tvRnDebugLeftInfo2.setText(this.i ? "开发模式已打开" : "开发模式已关闭");
                RnPluginLog.a("debug build type: false  sdk build type: false  is rn debug enable: " + this.i);
            } else {
                this.layoutRnDebug.setVisibility(8);
            }
            ViewParent parent = this.layoutRnDebug.getParent();
            if (parent instanceof ViewGroup) {
                a((ViewGroup) parent);
            }
        }
    }

    private void k() {
        XmPluginHostApi.instance().callSmartHomeApi((String) null, "/v2/developer/check_developer", "", new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    LogUtil.c("rn-debug", jSONObject.toString());
                    boolean unused = SettingMainPageV2.this.j = jSONObject.optBoolean("result", false);
                } else {
                    LogUtil.c("rn-debug", "requestUserDedbugAuthority is null...");
                    boolean unused2 = SettingMainPageV2.this.j = false;
                }
                SettingMainPageV2.this.j();
            }

            public void onFailure(int i, String str) {
                LogUtil.c("rn-debug", str);
                boolean unused = SettingMainPageV2.this.j = false;
                SettingMainPageV2.this.j();
            }
        }, Parser.DEFAULT_PARSER);
    }

    /* access modifiers changed from: private */
    public void l() {
        try {
            if (!this.f) {
                if (CoreApi.a().D()) {
                    this.mVoiceAssistant.setVisibility(0);
                    this.mConsumables.setVisibility(8);
                    this.mThirdAccountBtn.setVisibility(8);
                    this.mMyShopBtn.setVisibility(8);
                    this.mLaboratory.setVisibility(8);
                    if (!TextUtils.isEmpty(CommonUtils.t(getContext()))) {
                        this.mScore.setVisibility(0);
                    }
                    this.mFamily.setVisibility(8);
                    this.mVoiceControl.setVisibility(8);
                } else {
                    this.mVoiceAssistant.setVisibility(8);
                    this.mConsumables.setVisibility(0);
                    this.mMyShopBtn.setVisibility(0);
                    if (AndroidCommonConfigManager.a().g()) {
                        this.mThirdAccountBtn.setVisibility(0);
                    }
                    this.mLaboratory.setVisibility(0);
                    this.mScore.setVisibility(8);
                    if (!PreferenceUtils.a("lab_red_dot_shown", false)) {
                        this.mLaboratoryRedDot.setVisibility(8);
                    } else {
                        this.mLaboratoryRedDot.setVisibility(8);
                    }
                    this.mFamily.setVisibility(0);
                    this.mVoiceControl.setVisibility(0);
                }
                for (int i2 = 0; i2 < this.h.size(); i2++) {
                    a(this.h.get(i2));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
        Miio.b(g, "onResume");
        this.k = System.currentTimeMillis();
        if (this.q) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public final void run() {
                    SettingMainPageV2.this.o();
                }
            }, 1000);
        } else {
            o();
        }
    }

    public void refreshTitleBar() {
        TitleBarUtil.b((Activity) getActivity());
    }

    private void m() {
        e();
        this.mNickView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SettingMainPageV2.this.mLoginFrame.performClick();
            }
        });
        this.mNickView.setText(R.string.not_logged_in_desc);
        this.mTitle.setText(R.string.not_logged_in);
        this.mToolbar.setBackgroundColor(android.graphics.Color.parseColor("#B4B9C0"));
        this.mLoginFrame.setBackgroundResource(R.drawable.setting_main_page_appbar_top_logout_bg);
        this.mLoginFrameBottom.setBackgroundResource(R.drawable.setting_main_page_appbar_bottom_logout_bg);
    }

    /* access modifiers changed from: private */
    public void n() {
        e();
        this.mNickView.setText(R.string.already_logged_in);
        this.mTitle.setText(R.string.already_logged_in);
        this.mNickView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SettingMainPageV2.this.r();
            }
        });
        this.mToolbar.setBackgroundColor(android.graphics.Color.parseColor("#B4B9C0"));
        this.mLoginFrame.setBackgroundResource(R.drawable.setting_main_page_appbar_top_logout_bg);
        this.mLoginFrameBottom.setBackgroundResource(R.drawable.setting_main_page_appbar_bottom_logout_bg);
    }

    /* access modifiers changed from: private */
    public void o() {
        if (!this.f) {
            p();
            k();
            if (TextUtils.isEmpty(this.o) && SHApplication.getStateNotifier().a() != 4) {
                m();
            } else if (TextUtils.isEmpty(this.o) || SHApplication.getStateNotifier().a() != 4 || !this.o.equals(CoreApi.a().s()) || this.s) {
                this.s = false;
                this.o = CoreApi.a().s();
                c();
                a();
            }
        }
    }

    private void p() {
        if (getActivity() != null) {
            this.mPermissionBtn.setVisibility(PermissionRequestActivity.Companion.b(getActivity()) ? 8 : 0);
        }
    }

    private void q() {
        RedPointManagerNew.a().a(RedPointManagerNew.f, (RedPointManagerNew.RedPointAction) new RedPointManagerNew.RedPointAction() {
            public final void showRedPoint(boolean z) {
                SettingMainPageV2.this.h(z);
            }
        });
        RedPointManagerNew.a().a(RedPointManagerNew.h, (RedPointManagerNew.RedPointAction) new RedPointManagerNew.RedPointAction() {
            public final void showRedPoint(boolean z) {
                SettingMainPageV2.this.f(z);
            }
        });
        RedPointManagerNew.a().a(RedPointManagerNew.i, (RedPointManagerNew.RedPointAction) new RedPointManagerNew.RedPointAction() {
            public final void showRedPoint(boolean z) {
                SettingMainPageV2.this.d(z);
            }
        });
        RedPointManagerNew.a().a(RedPointManagerNew.g, (RedPointManagerNew.RedPointAction) new RedPointManagerNew.RedPointAction() {
            public final void showRedPoint(boolean z) {
                SettingMainPageV2.this.b(z);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void h(boolean z) {
        Miio.b(OpenApi.e, "newMsg  showRedPoint" + z);
        if (this.mNewMsgTag != null) {
            this.p.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SettingMainPageV2.this.i(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void i(boolean z) {
        this.mNewMsgTag.setVisibility(z ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(boolean z) {
        if (this.mImgFeedbackDot != null) {
            this.p.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SettingMainPageV2.this.g(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g(boolean z) {
        this.mImgFeedbackDot.setVisibility(z ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(boolean z) {
        if (this.mImgAboutDot != null) {
            this.p.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SettingMainPageV2.this.e(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(boolean z) {
        this.mImgAboutDot.setVisibility(z ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(boolean z) {
        if (this.mConsumableRedDot != null) {
            if (z) {
                z = SHApplication.getStateNotifier().a() == 4;
            }
            this.p.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    SettingMainPageV2.this.c(this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(boolean z) {
        this.mConsumableRedDot.setVisibility(z ? 0 : 8);
    }

    public void b() {
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public final void run() {
                SettingMainPageV2.this.c();
            }
        }, 1000);
    }

    public void c() {
        e();
        if (SHApplication.getStateNotifier().a() != 4) {
            try {
                this.mNickView.setText(R.string.not_logged_in_desc);
                this.mTitle.setText(R.string.not_logged_in);
                this.mLoginFrame.setBackgroundResource(R.drawable.setting_main_page_appbar_top_logout_bg);
                this.mLoginFrameBottom.setBackgroundResource(R.drawable.setting_main_page_appbar_bottom_logout_bg);
                this.mToolbar.setBackgroundColor(android.graphics.Color.parseColor("#B4B9C0"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            r();
        }
    }

    /* access modifiers changed from: private */
    public void r() {
        UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
            public void a(ShareUserRecord shareUserRecord) {
                if (SettingMainPageV2.this.isValid()) {
                    try {
                        SettingMainPageV2.this.mNickView.setText(shareUserRecord.nickName);
                        SettingMainPageV2.this.mTitle.setText(shareUserRecord.nickName);
                        SettingMainPageV2.this.mNickView.setVisibility(0);
                        if (SettingMainPageV2.this.l) {
                            SettingMainPageV2.this.mLoginFrame.setBackgroundColor(Color.d);
                            SettingMainPageV2.this.mLoginFrameBottom.setBackgroundColor(Color.d);
                            SettingMainPageV2.this.mToolbar.setBackgroundColor(Color.c);
                        } else {
                            SettingMainPageV2.this.mLoginFrame.setBackgroundResource(R.drawable.setting_main_page_appbar_top_normal_bg);
                            SettingMainPageV2.this.mLoginFrameBottom.setBackgroundResource(R.drawable.setting_main_page_appbar_bottom_normal_bg);
                            SettingMainPageV2.this.mToolbar.setBackgroundColor(android.graphics.Color.parseColor("#FF15B2B9"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    UserMamanger.a().b(shareUserRecord);
                    String g = SettingMainPageV2.g;
                    LogUtilGrey.a(g, "refreshUIByReloadingUserInfo onSuccess " + shareUserRecord);
                    UserMamanger.a().b(shareUserRecord.url, SettingMainPageV2.this.mUsrIcon, new CircleAvatarProcessor());
                }
            }

            public void a(int i) {
                if (SettingMainPageV2.this.isValid()) {
                    String g = SettingMainPageV2.g;
                    LogUtilGrey.a(g, "refreshUIByReloadingUserInfo onFailure " + i);
                    SettingMainPageV2.this.n();
                }
            }

            public void a(int i, Object obj) {
                if (SettingMainPageV2.this.isValid()) {
                    String g = SettingMainPageV2.g;
                    LogUtilGrey.a(g, "refreshUIByReloadingUserInfo onFailure " + i + "," + obj);
                    SettingMainPageV2.this.n();
                }
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = DarkModeCompat.a(getContext());
        this.m = new MyIClientDeviceListener(this);
        SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) this.m);
        IntentFilter intentFilter = new IntentFilter(HomeManager.S);
        intentFilter.addAction(SHBusinessManager.f13942a);
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.n, intentFilter);
        String str = g;
        LogUtil.a(str, g + "onCreate");
    }

    public void onPause() {
        super.onPause();
        RedPointManagerNew.a().a(RedPointManagerNew.f);
        RedPointManagerNew.a().a(RedPointManagerNew.h);
        RedPointManagerNew.a().a(RedPointManagerNew.i);
    }

    public void onDestroyView() {
        this.f = true;
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this.m);
        try {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.t);
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.n);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(boolean z) {
        a((String) null, z);
        if (z) {
            this.r = STAT.c.h(0);
        } else if (this.r > 0) {
            STAT.c.h(this.r);
            this.r = 0;
        }
        String str = g;
        Miio.b(str, "onSwitchtoPage  " + z);
        if (z) {
            q();
            a(this.f16923a);
            new Thread(new Runnable() {
                public final void run() {
                    SettingMainPageV2.this.J();
                }
            }).start();
            SHApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                public final void run() {
                    SettingMainPageV2.this.I();
                }
            }, 100);
            if (CoreApi.a().D() || !AndroidCommonConfigManager.a().g()) {
                this.mThirdAccountBtn.setVisibility(8);
            } else {
                this.mThirdAccountBtn.setVisibility(0);
            }
            MyOperation.a();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void J() {
        if (isValid()) {
            FragmentActivity activity = getActivity();
            PreferenceUtils.a((Context) activity, ProfileRedPointManager.d + CoreApi.a().s(), (System.currentTimeMillis() + ProfileRedPointManager.a().c()) / 1000);
            ProfileRedPointManager.a().d();
            ProfileRedPointManager.a().e();
            ProfileRedPointManager.a().g();
            VoiceManager a2 = VoiceManager.a();
            if (a2 != null) {
                a2.i();
            }
            ConsumableDataManager.a().a(getContext());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void I() {
        if (isValid()) {
            MessageCenter a2 = MessageCenter.a();
            a2.b(getActivity());
            a2.a(getActivity());
            Context appContext = SHApplication.getAppContext();
            a2.a(PreferenceUtils.b(appContext, ProfileRedPointManager.d + CoreApi.a().s(), System.currentTimeMillis()), 2);
            Context appContext2 = SHApplication.getAppContext();
            a2.a(PreferenceUtils.b(appContext2, ProfileRedPointManager.e + CoreApi.a().s(), System.currentTimeMillis()), 1);
            a2.f();
            a2.b();
            a2.c();
            a2.g();
            a2.i();
        }
    }

    private void s() {
        FragmentActivity activity = getActivity();
        PreferenceUtils.a((Context) activity, ProfileRedPointManager.e + CoreApi.a().s(), (System.currentTimeMillis() + ProfileRedPointManager.a().c()) / 1000);
        if (SHApplication.getStateNotifier().a() == 4) {
            MIOTStat.Log(MIOTStat.TOUCH, UrlConstants.msglist);
            this.mNewMsgTag.setVisibility(8);
            PreferenceUtils.b(b, true);
            PreferenceUtils.b("my_home_red_dot_clicked", true);
            PreferenceUtils.a(SHApplication.getAppContext(), c, 0);
            MessageCenter.a().a(MessageCenter.f10454a, 0, false);
            Intent intent = new Intent();
            intent.setClass(getActivity(), MessageCenterActivity.class);
            startActivity(intent);
        } else {
            SHApplication.showLoginDialog(getActivity(), false);
        }
        STAT.d.r();
        RedPointManagerNew.a().c(RedPointManagerNew.f);
    }

    private void t() {
        RedPointManagerNew.a().c(RedPointManagerNew.i);
        MIOTStat.Log(MIOTStat.TOUCH, PlaceFields.ABOUT);
        STAT.d.F();
        Intent intent = new Intent();
        intent.setClass(getActivity(), AboutActivity.class);
        startActivityForResult(intent, 100);
    }

    private void u() {
        startActivity(new Intent(getActivity(), LaboratoryActivity.class));
        PreferenceUtils.c(this.mContext.getApplicationContext(), "lab_red_dot_shown", true);
        this.mLaboratoryRedDot.setVisibility(8);
        STAT.d.z();
    }

    private void v() {
        STAT.d.A();
        if (SHApplication.getStateNotifier().a() == 4) {
            startActivity(new Intent(getActivity(), ThirdAccountGroupListActivity.class));
        } else {
            SHApplication.showLoginDialog(getActivity(), false);
        }
    }

    private void w() {
        MIOTStat.Log(MIOTStat.TOUCH, LogStrategyManager.ACTION_TYPE_FEEDBACK);
        STAT.d.D();
        RedPointManagerNew.a().c(RedPointManagerNew.h);
        if (SHApplication.getStateNotifier().a() == 4) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), FeedBackMainActivity.class);
            startActivity(intent);
            return;
        }
        SHApplication.showLoginDialog(getActivity(), false);
    }

    private void x() {
        STAT.d.t();
        Intent intent = new Intent();
        intent.setClass(getActivity(), ConsumablesActivity.class);
        startActivity(intent);
        if (this.mConsumableRedDot.getVisibility() == 0) {
            ConsumableDataManager.a().b();
        }
        RedPointManagerNew.a().c(RedPointManagerNew.g);
    }

    private void y() {
        MIOTStat.Log(MIOTStat.TOUCH, "shop");
        STAT.d.x();
        if (SHApplication.getStateNotifier().a() == 4) {
            UrlDispatchManger.a().c("https://home.mi.com/shop/shopshortcut");
        } else {
            SHApplication.showLoginDialog(getActivity(), false);
        }
    }

    private void z() {
        MIOTStat.Log(MIOTStat.TOUCH, IjkMediaPlayer.OnNativeInvokeListener.ARG_FAMILIY);
        STAT.d.v();
        if (SHApplication.getStateNotifier().a() == 4) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), FamilyActivity.class);
            startActivity(intent);
            return;
        }
        SHApplication.showLoginDialog(getActivity(), false);
    }

    private void A() {
        MIOTStat.Log(MIOTStat.TOUCH, "share");
        if (SHApplication.getStateNotifier().a() == 4) {
            startActivity(new Intent(getActivity(), SharePolymerizationActivity.class));
        } else {
            SHApplication.showLoginDialog(getActivity(), false);
        }
    }

    private void B() {
        STAT.d.u();
        if (SHApplication.getStateNotifier().a() == 4) {
            Intent intent = new Intent(getActivity(), VoiceSettingActivity.class);
            intent.putExtra(VoiceSettingActivity.FROM_SETTINGMAIN, true);
            startActivity(intent);
            VoiceManager.a().f();
            MessageCenter.a().i();
            StatHelper.am();
            return;
        }
        VoiceManager.a().f();
        SHApplication.showLoginDialog(getActivity(), false);
    }

    private void C() {
        if (SHApplication.getStateNotifier().a() != 4) {
            SHApplication.showLoginDialog(getActivity(), false);
            return;
        }
        STAT.d.y();
        startActivity(new Intent(getActivity(), BleGatewayListActivity.class));
    }

    private void D() {
        DevSettingRnDebugListActivity.startActivity(getActivity());
    }

    private void E() {
        STAT.d.E();
        CommentInternationalHelper.a((BaseActivity) getActivity());
    }

    private void F() {
        STAT.d.B();
        startActivity(new Intent(getActivity(), LicenseChooseActivity.class));
    }

    private void G() {
        if (getActivity() != null) {
            PermissionRequestActivity.Companion.a((Activity) getActivity());
        }
        STAT.d.bd();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        MIOTStat.Log(MIOTStat.TOUCH, "voice_service");
        STAT.d.p("语音助手");
        Intent intent = new Intent(SHApplication.getAppContext(), CommonWebViewActivity.class);
        intent.putExtra("url", H());
        intent.putExtra("title", getString(R.string.miio_setting_voice_service));
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        startActivity(intent);
    }

    private String H() {
        String str;
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(CommonUtils.l(PageUrl.f));
        sb.append("/views/voice_assistant.html");
        if (I == null) {
            str = "";
        } else {
            str = "?locale=" + LocaleUtil.a(I);
        }
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        if (this.mUsrIcon != null) {
            this.mUsrIcon.setImageResource(R.drawable.user_not_log_in_v2);
        }
    }

    public void f() {
        if (TitleBarUtil.f11582a && this.mLoginFrame != null) {
            int a2 = TitleBarUtil.a();
            if (this.mLoginFrame.getLayoutParams().height > 0) {
                this.mLoginFrame.getLayoutParams().height += a2;
            }
            this.mLoginFrame.setPadding(0, a2, 0, 0);
            this.mLoginFrame.setLayoutParams(this.mLoginFrame.getLayoutParams());
        }
    }

    @OnClick({2131428360, 2131433756, 2131433757})
    public void onClickLoginFrame() {
        MIOTStat.Log(MIOTStat.TOUCH, "login");
        STAT.d.q();
        if (SHApplication.getStateNotifier().a() != 4) {
            LoginApi.a().a((Context) getActivity(), 1, (LoginApi.LoginCallback) null);
            return;
        }
        Intent intent = new Intent(getActivity(), LogoutActivity.class);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.startActivity(intent);
        }
    }

    @OnClick({2131430208, 2131431984, 2131431965, 2131432002, 2131432016, 2131431954, 2131432004, 2131431974, 2131431983, 2131432007, 2131431975, 2131431948, 2131431998, 2131432015, 2131432001, 2131431994, 2131431990})
    public void onClickDispatcher(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.iv_qrcode:
                    ToastUtil.a((CharSequence) "iv_qrcode");
                    return;
                case R.id.rl_about:
                    t();
                    return;
                case R.id.rl_bluetooth_gateway:
                    C();
                    return;
                case R.id.rl_consumables:
                    x();
                    return;
                case R.id.rl_family:
                    z();
                    return;
                case R.id.rl_feedback:
                    w();
                    return;
                case R.id.rl_laboratory:
                    u();
                    return;
                case R.id.rl_message_center:
                    s();
                    return;
                case R.id.rl_permission:
                    G();
                    return;
                case R.id.rl_privacy:
                    F();
                    return;
                case R.id.rl_rn_debug:
                    D();
                    return;
                case R.id.rl_score:
                    E();
                    return;
                case R.id.rl_share:
                    A();
                    return;
                case R.id.rl_shop:
                    y();
                    return;
                case R.id.rl_third_account:
                    v();
                    return;
                case R.id.rl_voice_assistant:
                    d();
                    return;
                case R.id.rl_voice_control:
                    B();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(ViewGroup viewGroup) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= childCount) {
                    z = true;
                    break;
                } else if (viewGroup.getChildAt(i2).getVisibility() == 0) {
                    break;
                } else {
                    i2++;
                }
            }
            if (viewGroup.getParent() != null) {
                ((ViewGroup) viewGroup.getParent()).setVisibility(z ? 8 : viewGroup.getVisibility());
            }
        }
    }

    private void a(View view, final Runnable runnable) {
        MyScaleAnimation myScaleAnimation = new MyScaleAnimation(0.9f, 1, 0.5f, 1, 0.5f);
        myScaleAnimation.setDuration(360);
        myScaleAnimation.setInterpolator(new LinearInterpolator());
        myScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                runnable.run();
            }
        });
        view.startAnimation(myScaleAnimation);
    }
}
