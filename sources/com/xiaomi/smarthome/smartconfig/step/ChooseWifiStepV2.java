package com.xiaomi.smarthome.smartconfig.step;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.device.LocalRouterDeviceInfo;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.location.LocationPermissionDialogHelper;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.WifiAccount;
import com.xiaomi.smarthome.miio.activity.GDPRLicenseActivity;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.step.ChooseWifiStepV2;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChooseWifiStepV2 extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f22537a = false;
    public static int b = 200;
    /* access modifiers changed from: private */
    public View c;
    /* access modifiers changed from: private */
    public View d;
    /* access modifiers changed from: private */
    public WifiManager e;
    /* access modifiers changed from: private */
    public WifiSettingUtils.KuailianScanResult f;
    /* access modifiers changed from: private */
    public WifiSettingUtils.KuailianScanResult g;
    /* access modifiers changed from: private */
    public WifiSettingUtils.KuailianScanResult h;
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> i = new ArrayList();
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> j = new ArrayList();
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> k = new ArrayList();
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> l = new ArrayList();
    /* access modifiers changed from: private */
    public RouterRemoteApi.MiRouterPwdInfo m;
    @BindView(2131428161)
    TextView mCannotFindTips;
    @BindView(2131428296)
    View mChangePasswordView;
    @BindView(2131428307)
    CheckBox mCheckBox;
    @BindView(2131428732)
    View mDeleteWifiView;
    @BindView(2131428970)
    View mEditWifiViewRoot;
    @BindView(2131429002)
    View mEmptyView;
    @BindView(2131427637)
    TextView mLicenseTv;
    @BindView(2131431178)
    Button mNextButton;
    @BindView(2131431371)
    ListView mOtherListView;
    @BindView(2131430969)
    View mReturnBt;
    @BindView(2131432107)
    View mSaveWifiViewRoot;
    @BindView(2131432106)
    ListView mSavedListView;
    @BindView(2131434009)
    CustomPullDownRefreshLinearLayout mScanResultListRoot;
    @BindView(2131433994)
    ScrollView mScrollWifiList;
    @BindView(2131430974)
    TextView mSubTitle;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitleTv;
    /* access modifiers changed from: private */
    public boolean n = false;
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public boolean p = false;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public BaseAdapter r = new BaseAdapter() {
        public long getItemId(int i) {
            return (long) i;
        }

        public int getCount() {
            return ChooseWifiStepV2.this.k.size() + ChooseWifiStepV2.this.l.size();
        }

        public Object getItem(int i) {
            if (i < ChooseWifiStepV2.this.k.size()) {
                return ChooseWifiStepV2.this.k.get(i);
            }
            return ChooseWifiStepV2.this.l.get(i - ChooseWifiStepV2.this.k.size());
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final WifiSettingUtils.KuailianScanResult kuailianScanResult;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_choose_wifi_step, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.f22569a = view.findViewById(R.id.wifi_item);
                viewHolder.b = (TextView) view.findViewById(R.id.wifi_name);
                viewHolder.c = (TextView) view.findViewById(R.id.wifi_des);
                viewHolder.d = (ImageView) view.findViewById(R.id.wifi_pointer);
                viewHolder.e = (ImageView) view.findViewById(R.id.edit_wifi);
                viewHolder.f = (ImageView) view.findViewById(R.id.img_wifi_signal);
                view.setTag(viewHolder);
            }
            ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            if (i < ChooseWifiStepV2.this.k.size()) {
                kuailianScanResult = (WifiSettingUtils.KuailianScanResult) ChooseWifiStepV2.this.k.get(i);
            } else {
                kuailianScanResult = (WifiSettingUtils.KuailianScanResult) ChooseWifiStepV2.this.l.get(i - ChooseWifiStepV2.this.k.size());
            }
            if (kuailianScanResult.b) {
                if (!TextUtils.isEmpty(kuailianScanResult.d)) {
                    viewHolder2.c.setVisibility(0);
                    viewHolder2.c.setText(kuailianScanResult.d);
                    viewHolder2.c.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
                } else {
                    viewHolder2.c.setVisibility(8);
                }
                viewHolder2.b.setText(kuailianScanResult.f22964a.SSID);
                if (i < ChooseWifiStepV2.this.k.size()) {
                    viewHolder2.e.setVisibility(0);
                    viewHolder2.b.setTextColor(viewGroup.getContext().getResources().getColor(R.color.black));
                    viewHolder2.c.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
                    viewHolder2.f22569a.setEnabled(true);
                    viewHolder2.c.setVisibility(8);
                    viewHolder2.e.setImageResource(R.drawable.edit_wifi_icon);
                    viewHolder2.e.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            STAT.d.w(ChooseWifiStepV2.this.q);
                            WifiSettingUtils.KuailianScanResult unused = ChooseWifiStepV2.this.h = kuailianScanResult;
                            ChooseWifiStepV2.this.q();
                        }
                    });
                    viewHolder2.f22569a.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            WifiSettingUtils.KuailianScanResult unused = ChooseWifiStepV2.this.g = kuailianScanResult;
                            AnonymousClass12.this.notifyDataSetChanged();
                        }
                    });
                    if (ChooseWifiStepV2.this.g == null || !kuailianScanResult.f22964a.BSSID.equalsIgnoreCase(ChooseWifiStepV2.this.g.f22964a.BSSID)) {
                        viewHolder2.d.setVisibility(8);
                    } else {
                        viewHolder2.b.setTextColor(viewGroup.getContext().getResources().getColor(R.color.miui_blue));
                        viewHolder2.c.setTextColor(viewGroup.getContext().getResources().getColor(R.color.miui_blue));
                        viewHolder2.d.setVisibility(0);
                    }
                } else {
                    viewHolder2.e.setVisibility(8);
                    viewHolder2.d.setVisibility(8);
                    viewHolder2.b.setTextColor(Color.parseColor("#d7d7d7"));
                    viewHolder2.c.setTextColor(Color.parseColor("#d7d7d7"));
                    viewHolder2.f22569a.setEnabled(false);
                }
                if (kuailianScanResult.c) {
                    viewHolder2.e.setVisibility(8);
                    viewHolder2.c.setVisibility(0);
                } else if (WifiSettingUtils.a(kuailianScanResult.f22964a.level, 100) < 60) {
                    viewHolder2.c.setVisibility(0);
                    viewHolder2.c.setText(viewGroup.getContext().getString(R.string.wifi_signal_weak_tip));
                }
                if ((DeviceFactory.d(kuailianScanResult.f22964a.SSID) || ChooseWifiStepV2.this.a(kuailianScanResult.f22964a.frequency)) && !ChooseWifiStepV2.this.o) {
                    viewHolder2.c.setText(R.string.wifi_may_not_24G);
                    viewHolder2.c.setVisibility(0);
                }
            } else {
                viewHolder2.e.setVisibility(0);
                viewHolder2.b.setTextColor(viewGroup.getContext().getResources().getColor(R.color.black));
                viewHolder2.c.setTextColor(viewGroup.getContext().getResources().getColor(R.color.miui_blue));
                viewHolder2.f22569a.setEnabled(true);
                viewHolder2.b.setText(kuailianScanResult.f22964a.SSID);
                viewHolder2.c.setVisibility(0);
                viewHolder2.c.setText(viewGroup.getContext().getString(R.string.click_input_password));
                viewHolder2.e.setOnClickListener((View.OnClickListener) null);
                viewHolder2.f22569a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ChooseWifiStepV2.this.a(kuailianScanResult, false, (DialogListener) new DialogListener() {
                            public void a(String str) {
                                ChooseWifiStepV2.this.k.clear();
                                ChooseWifiStepV2.this.a(kuailianScanResult, str);
                                WifiSettingUtils.KuailianScanResult unused = ChooseWifiStepV2.this.g = kuailianScanResult;
                                ChooseWifiStepV2.this.k.add(0, kuailianScanResult);
                                ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) ChooseWifiStepV2.this.i);
                                ChooseWifiStepV2.this.s.notifyDataSetChanged();
                                ChooseWifiStepV2.this.r.notifyDataSetChanged();
                                ChooseWifiStepV2.this.k();
                                ((TextView) ChooseWifiStepV2.this.c.findViewById(R.id.list_title)).setText(ChooseWifiStepV2.this.af.getString(R.string.kuailian_save_passwd));
                            }
                        });
                    }
                });
            }
            viewHolder2.f.setVisibility(0);
            viewHolder2.f.setImageResource(WifiSettingUtils.a(WifiSettingUtils.a(kuailianScanResult.f22964a.level, 100)));
            return view;
        }
    };
    /* access modifiers changed from: private */
    public BaseAdapter s = new BaseAdapter() {
        public long getItemId(int i) {
            return (long) i;
        }

        public int getCount() {
            return ChooseWifiStepV2.this.i.size() + ChooseWifiStepV2.this.j.size();
        }

        public Object getItem(int i) {
            if (i < ChooseWifiStepV2.this.i.size()) {
                return ChooseWifiStepV2.this.i.get(i);
            }
            return ChooseWifiStepV2.this.j.get(i - ChooseWifiStepV2.this.i.size());
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            WifiSettingUtils.KuailianScanResult kuailianScanResult;
            if (ChooseWifiStepV2.this.af == null) {
                return view;
            }
            if (view == null) {
                view = LayoutInflater.from(ChooseWifiStepV2.this.af).inflate(R.layout.item_choose_other_wifi_step, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.f22569a = view.findViewById(R.id.wifi_item);
                viewHolder.b = (TextView) view.findViewById(R.id.wifi_name);
                viewHolder.c = (TextView) view.findViewById(R.id.wifi_des);
                viewHolder.e = (ImageView) view.findViewById(R.id.wifi_signal);
                view.setTag(viewHolder);
            }
            ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            if (i < ChooseWifiStepV2.this.i.size()) {
                kuailianScanResult = (WifiSettingUtils.KuailianScanResult) ChooseWifiStepV2.this.i.get(i);
            } else {
                kuailianScanResult = (WifiSettingUtils.KuailianScanResult) ChooseWifiStepV2.this.j.get(i - ChooseWifiStepV2.this.i.size());
            }
            viewHolder2.b.setText(kuailianScanResult.f22964a.SSID);
            if (!TextUtils.isEmpty(kuailianScanResult.d)) {
                viewHolder2.c.setVisibility(0);
                viewHolder2.c.setText(kuailianScanResult.d);
            } else {
                viewHolder2.c.setVisibility(8);
            }
            if (i < ChooseWifiStepV2.this.i.size()) {
                viewHolder2.e.setVisibility(0);
                viewHolder2.b.setTextColor(ChooseWifiStepV2.this.af.getResources().getColor(R.color.black));
                viewHolder2.c.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
                viewHolder2.f22569a.setEnabled(true);
                viewHolder2.e.setImageResource(WifiSettingUtils.a(WifiSettingUtils.a(kuailianScanResult.f22964a.level, 100)));
                viewHolder2.f22569a.setEnabled(true);
                viewHolder2.f22569a.setOnClickListener(new View.OnClickListener(kuailianScanResult) {
                    private final /* synthetic */ WifiSettingUtils.KuailianScanResult f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ChooseWifiStepV2.AnonymousClass17.this.b(this.f$1, view);
                    }
                });
            } else {
                viewHolder2.e.setVisibility(8);
                viewHolder2.b.setTextColor(Color.parseColor("#d7d7d7"));
                viewHolder2.c.setTextColor(Color.parseColor("#d7d7d7"));
                viewHolder2.f22569a.setOnClickListener(new View.OnClickListener(kuailianScanResult) {
                    private final /* synthetic */ WifiSettingUtils.KuailianScanResult f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        ChooseWifiStepV2.AnonymousClass17.this.a(this.f$1, view);
                    }
                });
            }
            return view;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(WifiSettingUtils.KuailianScanResult kuailianScanResult, View view) {
            if (WifiSettingUtils.d(kuailianScanResult.f22964a.SSID)) {
                new MLAlertDialog.Builder(ChooseWifiStepV2.this.af).a((int) R.string.kuailian_contain_unsupport_char).b((int) R.string.kuailian_unsafe_wifi_content).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(kuailianScanResult) {
                    private final /* synthetic */ WifiSettingUtils.KuailianScanResult f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        ChooseWifiStepV2.AnonymousClass17.this.b(this.f$1, dialogInterface, i);
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
            } else {
                ChooseWifiStepV2.this.a(kuailianScanResult);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(WifiSettingUtils.KuailianScanResult kuailianScanResult, DialogInterface dialogInterface, int i) {
            ChooseWifiStepV2.this.a(kuailianScanResult);
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.p, (Object) null);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(WifiSettingUtils.KuailianScanResult kuailianScanResult, View view) {
            if ((ChooseWifiStepV2.this.a(kuailianScanResult.f22964a.frequency) && !ChooseWifiStepV2.this.o) || ChooseWifiStepV2.this.d(kuailianScanResult.f22964a.capabilities) || kuailianScanResult.f22964a.level == 0 || DeviceFactory.f(kuailianScanResult.f22964a)) {
                new MLAlertDialog.Builder(ChooseWifiStepV2.this.af).b((int) R.string.kuailian_unconn_reason).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) null).d();
            } else if (WifiSettingUtils.a(kuailianScanResult.f22964a) == 0) {
                new MLAlertDialog.Builder(ChooseWifiStepV2.this.af).b((int) R.string.not_support_no_password_wifi).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) null).d();
            } else {
                new MLAlertDialog.Builder(ChooseWifiStepV2.this.af).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content_1).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(kuailianScanResult) {
                    private final /* synthetic */ WifiSettingUtils.KuailianScanResult f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        ChooseWifiStepV2.AnonymousClass17.this.a(this.f$1, dialogInterface, i);
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(WifiSettingUtils.KuailianScanResult kuailianScanResult, DialogInterface dialogInterface, int i) {
            ChooseWifiStepV2.this.a(kuailianScanResult);
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.p, (Object) null);
        }
    };

    private interface DialogListener {
        void a(String str);
    }

    /* access modifiers changed from: private */
    public boolean a(int i2) {
        return i2 > 4900;
    }

    public void c() {
    }

    public void d() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_CHOOSE_WIFI;
    }

    public void a(Message message) {
        int i2 = message.what;
        if (i2 == 100 || i2 == 104) {
            U_().removeMessages(100);
            U_().removeMessages(104);
            g();
        }
    }

    public void a(Context context) {
        int i2;
        a(context, R.layout.smart_config_choose_wifi);
        TitleBarUtil.a(this.mTitleBar);
        this.mTitleTv.setText(R.string.device_choose_wifi);
        this.q = (String) SmartConfigDataProvider.a().a("device_model");
        y();
        LocalRouterDeviceInfo.a().a((AsyncResponseCallback<RouterRemoteApi.WifiDetail>) new AsyncResponseCallback<RouterRemoteApi.WifiDetail>() {
            public void a(int i) {
            }

            public void a(int i, Object obj) {
            }

            public void a(RouterRemoteApi.WifiDetail wifiDetail) {
                RouterRemoteApi.MiRouterPwdInfo unused = ChooseWifiStepV2.this.m = new RouterRemoteApi.MiRouterPwdInfo();
                ChooseWifiStepV2.this.m.f16423a = wifiDetail.f16425a.get(0).c;
                ChooseWifiStepV2.this.m.b = wifiDetail.f16425a.get(0).e;
                ChooseWifiStepV2.this.m.c = wifiDetail.f16425a.get(1).c;
                ChooseWifiStepV2.this.m.d = wifiDetail.f16425a.get(1).e;
            }
        });
        STAT.c.b(this.q, 0);
        if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.N, false)).booleanValue()) {
            i2 = 4;
        } else if (SmartConfigMainActivity.DEVICE_FROM == 5) {
            i2 = 3;
        } else {
            i2 = SmartConfigMainActivity.DEVICE_FROM_APP_PLUS_TYPE == 6 ? 2 : 1;
        }
        STAT.c.c(this.q, i2);
        if (this.q != null && DeviceFactory.c(this.q)) {
            this.o = true;
        }
        if (this.q != null) {
            PluginRecord d2 = CoreApi.a().d(this.q);
            if (d2 != null) {
                this.mSubTitle.setText(d2.p());
            } else {
                this.mSubTitle.setVisibility(8);
            }
        } else {
            this.mSubTitle.setVisibility(8);
        }
        this.mNextButton.setEnabled(false);
        this.mNextButton.setTextColor(this.af.getResources().getColor(R.color.blue_btn_text_disable));
        c(this.q);
        this.mReturnBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseWifiStepV2.this.a();
            }
        });
        this.e = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (!b()) {
            new MLAlertDialog.Builder(this.af).b((int) R.string.open_wifi_failed).a(false).b((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ChooseWifiStepV2.this.d_(false);
                }
            }).a(false).d();
        }
        this.mScanResultListRoot.setScrollView(this.mScrollWifiList);
        this.mScanResultListRoot.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                ChooseWifiStepV2.this.e.startScan();
                boolean unused = ChooseWifiStepV2.this.n = true;
            }
        });
        LayoutInflater from = LayoutInflater.from(this.af);
        View inflate = from.inflate(R.layout.header_choose_wifi_step, this.mSavedListView, false);
        this.c = inflate.findViewById(R.id.header_wrapper);
        this.mSavedListView.addHeaderView(inflate);
        this.c.setVisibility(8);
        View inflate2 = from.inflate(R.layout.footer_choose_wifi_step, this.mSavedListView, false);
        this.mSavedListView.addFooterView(inflate2);
        this.mCannotFindTips.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseWifiStepV2.this.i();
            }
        });
        inflate2.findViewById(R.id.other_wifi).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.v(ChooseWifiStepV2.this.q);
                STAT.d.aR(ChooseWifiStepV2.this.q);
                ChooseWifiStepV2.this.r();
            }
        });
        this.d = from.inflate(R.layout.header_choose_wifi_step, this.mOtherListView, false);
        this.mOtherListView.addHeaderView(this.d);
        ((TextView) this.d.findViewById(R.id.list_title)).setText(context.getString(R.string.near_wifi));
        this.d.setVisibility(8);
        this.mSavedListView.setAdapter(this.r);
        this.mOtherListView.setAdapter(this.s);
        this.mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aQ(ChooseWifiStepV2.this.q);
                if (ChooseWifiStepV2.this.mCheckBox.getVisibility() != 0 || ChooseWifiStepV2.this.mCheckBox.isChecked() || !(ChooseWifiStepV2.this.af instanceof Activity)) {
                    ChooseWifiStepV2.this.b(false);
                } else {
                    new MLAlertDialog.Builder((Activity) ChooseWifiStepV2.this.af).a((CharSequence) ChooseWifiStepV2.this.af.getString(R.string.license_title)).a(ChooseWifiStepV2.this.a(ChooseWifiStepV2.this.q)).b((CharSequence) ChooseWifiStepV2.this.af.getString(R.string.license_negative_btn), (DialogInterface.OnClickListener) null).a((CharSequence) ChooseWifiStepV2.this.af.getString(R.string.license_positive_btn), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ChooseWifiStepV2.this.b(false);
                        }
                    }).d();
                }
            }
        });
        this.mChangePasswordView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aS(ChooseWifiStepV2.this.q);
                if (ChooseWifiStepV2.this.h != null) {
                    ChooseWifiStepV2.this.a(ChooseWifiStepV2.this.h, false, (DialogListener) new DialogListener() {
                        public void a(String str) {
                            ChooseWifiStepV2.this.a(ChooseWifiStepV2.this.h, str);
                            ChooseWifiStepV2.this.p();
                        }
                    });
                }
            }
        });
        this.mDeleteWifiView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aT(ChooseWifiStepV2.this.q);
                ChooseWifiStepV2.this.o();
            }
        });
        this.mNextButton.post(new Runnable() {
            public void run() {
                if (SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.n, false) && ChooseWifiStepV2.this.j()) {
                    ChooseWifiStepV2.this.mNextButton.performClick();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public SpannableStringBuilder a(final String str) {
        String string = this.af.getString(R.string.license_content);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass11 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseWifiStepV2.this.af, GDPRLicenseActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_model", str);
                ChooseWifiStepV2.this.af.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#0099ff"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return spannableStringBuilder;
    }

    /* access modifiers changed from: private */
    public void i() {
        String str;
        Intent intent = new Intent(this.af, WebShellActivity.class);
        Bundle bundle = new Bundle();
        if (CoreApi.a().D()) {
            ServerBean F = CoreApi.a().F();
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            if (F != null) {
                str = F.f1530a + ".";
            } else {
                str = "";
            }
            sb.append(str);
            sb.append("home.mi.com/views/faqDetail.html?question=");
            sb.append(this.af.getString(R.string.param_question_choose_wifi_tips));
            bundle.putString("url", sb.toString());
        } else {
            bundle.putString("url", "https://home.mi.com/views/faqDetail.html?question=" + this.af.getString(R.string.param_question_choose_wifi_tips));
        }
        intent.putExtras(bundle);
        this.af.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public boolean j() {
        WifiInfo connectionInfo = this.e.getConnectionInfo();
        try {
            if (LocationPermissionDialogHelper.a((SmartConfigMainActivity) this.af, false, R.string.open_location_permission)) {
                List<ScanResult> scanResults = this.e.getScanResults();
                if (!(connectionInfo == null || scanResults == null)) {
                    if (scanResults.size() != 0) {
                        for (ScanResult next : scanResults) {
                            if (next.BSSID.equalsIgnoreCase(connectionInfo.getBSSID())) {
                                if (b(next) && !TextUtils.isEmpty(next.SSID)) {
                                    if (!DeviceFactory.f(next)) {
                                        if (WifiAccountManager.a().a(next.BSSID) != null) {
                                            WifiSettingUtils.KuailianScanResult kuailianScanResult = new WifiSettingUtils.KuailianScanResult();
                                            kuailianScanResult.f22964a = next;
                                            kuailianScanResult.b = true;
                                            this.g = kuailianScanResult;
                                            return true;
                                        }
                                    }
                                }
                                return false;
                            }
                        }
                    }
                }
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    private int b(String str) {
        try {
            if (WifiSettingUtils.a(this.g.f22964a) == 0) {
                return -1;
            }
            Class<?> cls = Class.forName("android.net.wifi.MiuiWifiManager");
            Method method = cls.getMethod("verifyPreSharedKey", new Class[]{WifiConfiguration.class, String.class});
            WifiConfiguration wifiConfiguration = null;
            String str2 = "\"" + this.g.f22964a.SSID + "\"";
            for (WifiConfiguration next : this.e.getConfiguredNetworks()) {
                if (next != null) {
                    if (!TextUtils.isEmpty(next.SSID)) {
                        if (next.SSID.equals(str2)) {
                            wifiConfiguration = next;
                        }
                    }
                }
            }
            if (wifiConfiguration == null) {
                return 1;
            }
            return ((Boolean) method.invoke(cls, new Object[]{wifiConfiguration, str})).booleanValue() ? 1 : 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private void c(final String str) {
        if (!ServerCompact.d(CoreApi.a().F())) {
            this.mCheckBox.setVisibility(4);
            this.mLicenseTv.setVisibility(4);
            this.mCheckBox.setChecked(true);
            return;
        }
        this.mCheckBox.setChecked(false);
        this.mCheckBox.setVisibility(0);
        String string = this.af.getString(R.string.kuailian_license);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass13 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseWifiStepV2.this.af, GDPRLicenseActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_model", str);
                ChooseWifiStepV2.this.af.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#0099ff"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception unused) {
            }
        }
        this.mLicenseTv.setText(spannableStringBuilder);
        this.mLicenseTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public boolean a() {
        if (this.mScanResultListRoot.getVisibility() == 0 || this.mEditWifiViewRoot.getVisibility() == 0) {
            p();
        } else {
            d_(false);
        }
        STAT.c.b(this.q, this.ai);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        int wifiState = this.e.getWifiState();
        if (wifiState == 3) {
            this.n = true;
            g();
        } else if ((wifiState == 4 || wifiState == 1) && wifiState == 1) {
            boolean wifiEnabled = this.e.setWifiEnabled(true);
            if (wifiEnabled) {
                this.n = true;
                g();
            } else {
                this.mCannotFindTips.setVisibility(0);
            }
            return wifiEnabled;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    @SuppressLint({"StaticFieldLeak"})
    public void g() {
        if (this.n && this.af != null) {
            this.n = false;
            if (LocationPermissionDialogHelper.a((SmartConfigMainActivity) this.af, true, R.string.open_location_permission)) {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                final ArrayList arrayList3 = new ArrayList();
                final ArrayList arrayList4 = new ArrayList();
                AsyncTaskUtils.a(new AsyncTask<Void, Void, ScanResult>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public ScanResult doInBackground(Void... voidArr) {
                        List<ScanResult> list;
                        boolean z;
                        Context context = ChooseWifiStepV2.this.af;
                        if (context == null) {
                            return null;
                        }
                        WifiInfo connectionInfo = ChooseWifiStepV2.this.e.getConnectionInfo();
                        try {
                            list = ChooseWifiStepV2.this.e.getScanResults();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            list = null;
                        }
                        if (list == null) {
                            ChooseWifiStepV2.this.mCannotFindTips.post(new Runnable() {
                                public final void run() {
                                    ChooseWifiStepV2.AnonymousClass14.this.a();
                                }
                            });
                            return null;
                        }
                        HashMap hashMap = new HashMap();
                        HashMap hashMap2 = new HashMap();
                        PushBindEntity pushBindEntity = (PushBindEntity) SmartConfigDataProvider.a().a(SmartConfigDataProvider.r);
                        char c2 = 0;
                        String str = null;
                        int i = 0;
                        while (true) {
                            boolean z2 = true;
                            if (i < list.size()) {
                                ScanResult scanResult = list.get(i);
                                if (!TextUtils.isEmpty(scanResult.SSID) && scanResult.BSSID != null && !DeviceFactory.f(scanResult) && (pushBindEntity == null || pushBindEntity.f == null || !pushBindEntity.f.equalsIgnoreCase(scanResult.BSSID))) {
                                    boolean d2 = WifiSettingUtils.d(scanResult);
                                    boolean z3 = WifiAccountManager.a().a(scanResult.BSSID) != null;
                                    if (connectionInfo != null && WifiSettingUtils.a(connectionInfo.getSSID(), scanResult.SSID) && d2 && ChooseWifiStepV2.this.a(scanResult.frequency)) {
                                        String[] split = scanResult.BSSID.split("\\:");
                                        Object[] objArr = new Object[6];
                                        objArr[c2] = split[c2];
                                        objArr[1] = split[1];
                                        objArr[2] = split[2];
                                        objArr[3] = split[3];
                                        objArr[4] = split[4];
                                        objArr[5] = Integer.valueOf(Integer.valueOf(split[5], 16).intValue() - 1);
                                        str = String.format("%1$s:%2$s:%3$s:%4$s:%5$s:%6$x", objArr);
                                    }
                                    WifiSettingUtils.KuailianScanResult kuailianScanResult = new WifiSettingUtils.KuailianScanResult();
                                    kuailianScanResult.b = z3;
                                    kuailianScanResult.f22964a = scanResult;
                                    if (!ChooseWifiStepV2.this.b(scanResult)) {
                                        if (WifiSettingUtils.a(scanResult) == 0) {
                                            kuailianScanResult.d = context.getString(R.string.kuailian_no_passwd);
                                        } else if (ChooseWifiStepV2.this.d(scanResult.capabilities)) {
                                            if (ChooseWifiStepV2.this.ag) {
                                                return null;
                                            }
                                            kuailianScanResult.d = context.getString(R.string.kuailian_unconn_reason);
                                            if (z3) {
                                                arrayList4.add(kuailianScanResult);
                                            }
                                        } else if (!ChooseWifiStepV2.this.a(scanResult.frequency) || ChooseWifiStepV2.this.o) {
                                            if (WifiSettingUtils.d(scanResult.SSID)) {
                                                if (ChooseWifiStepV2.this.ag) {
                                                    return null;
                                                }
                                                kuailianScanResult.d = context.getString(R.string.kuailian_contain_unsupport_char);
                                                if (z3) {
                                                    arrayList3.add(kuailianScanResult);
                                                }
                                            } else if (ChooseWifiStepV2.this.ag) {
                                                return null;
                                            } else {
                                                kuailianScanResult.d = context.getString(R.string.kuailian_unsafe_wifi);
                                                if (z3) {
                                                    arrayList3.add(kuailianScanResult);
                                                }
                                            }
                                        } else if (ChooseWifiStepV2.this.ag) {
                                            return null;
                                        } else {
                                            kuailianScanResult.d = context.getString(R.string.not_support_5g);
                                            if (z3) {
                                                arrayList4.add(kuailianScanResult);
                                            }
                                        }
                                        if (!hashMap2.containsKey(scanResult.SSID)) {
                                            arrayList2.add(kuailianScanResult);
                                            hashMap2.put(scanResult.SSID, kuailianScanResult);
                                        } else if (WifiSettingUtils.a(scanResult.level, 100) > WifiSettingUtils.a(((WifiSettingUtils.KuailianScanResult) hashMap2.get(scanResult.SSID)).f22964a.level, 100)) {
                                            WifiSettingUtils.KuailianScanResult kuailianScanResult2 = (WifiSettingUtils.KuailianScanResult) hashMap2.get(scanResult.SSID);
                                            arrayList2.remove(kuailianScanResult2);
                                            arrayList2.add(kuailianScanResult2);
                                        }
                                    } else {
                                        if (z3) {
                                            arrayList3.add(kuailianScanResult);
                                            if (ChooseWifiStepV2.this.ag) {
                                                return null;
                                            }
                                            kuailianScanResult.d = context.getString(R.string.kuailian_save_passwd);
                                        }
                                        if (!hashMap.containsKey(scanResult.SSID)) {
                                            arrayList.add(kuailianScanResult);
                                            hashMap.put(scanResult.SSID, kuailianScanResult);
                                        } else if (WifiSettingUtils.a(scanResult.level, 100) > WifiSettingUtils.a(((WifiSettingUtils.KuailianScanResult) hashMap.get(scanResult.SSID)).f22964a.level, 100)) {
                                            WifiSettingUtils.KuailianScanResult kuailianScanResult3 = (WifiSettingUtils.KuailianScanResult) hashMap.get(scanResult.SSID);
                                            hashMap.remove(kuailianScanResult3.f22964a.SSID);
                                            hashMap.put(scanResult.SSID, kuailianScanResult);
                                            arrayList.remove(kuailianScanResult3);
                                            arrayList.add(kuailianScanResult3);
                                        }
                                    }
                                }
                                i++;
                                c2 = 0;
                            } else {
                                ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) arrayList);
                                ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) arrayList2);
                                ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) arrayList3);
                                ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) arrayList4);
                                if (ChooseWifiStepV2.this.g != null) {
                                    int i2 = 0;
                                    while (i2 < arrayList.size()) {
                                        if (ChooseWifiStepV2.this.g.f22964a.BSSID.equalsIgnoreCase(((WifiSettingUtils.KuailianScanResult) arrayList.get(i2)).f22964a.BSSID)) {
                                            WifiSettingUtils.KuailianScanResult unused = ChooseWifiStepV2.this.g = (WifiSettingUtils.KuailianScanResult) arrayList.get(i2);
                                        } else if (ChooseWifiStepV2.this.g.f22964a.BSSID.equalsIgnoreCase(str)) {
                                            WifiSettingUtils.KuailianScanResult unused2 = ChooseWifiStepV2.this.g = (WifiSettingUtils.KuailianScanResult) arrayList.get(i2);
                                        } else {
                                            i2++;
                                        }
                                        z = true;
                                    }
                                    z = false;
                                    if (!z) {
                                        WifiSettingUtils.KuailianScanResult unused3 = ChooseWifiStepV2.this.g = null;
                                    }
                                } else {
                                    String str2 = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.n);
                                    if (!TextUtils.isEmpty(str2)) {
                                        int i3 = 0;
                                        while (true) {
                                            if (i3 >= arrayList.size()) {
                                                break;
                                            } else if (str2.equalsIgnoreCase(((WifiSettingUtils.KuailianScanResult) arrayList.get(i3)).f22964a.BSSID)) {
                                                WifiSettingUtils.KuailianScanResult unused4 = ChooseWifiStepV2.this.g = (WifiSettingUtils.KuailianScanResult) arrayList.get(i3);
                                                if (SmartConfigDataProvider.a().a(SmartConfigDataProvider.o) != null) {
                                                    WifiAccount.WifiItem wifiItem = new WifiAccount.WifiItem();
                                                    wifiItem.e = ChooseWifiStepV2.this.g.f22964a.BSSID;
                                                    if (wifiItem.e == null) {
                                                        wifiItem.e = "";
                                                    }
                                                    wifiItem.c = ChooseWifiStepV2.this.g.f22964a.SSID;
                                                    wifiItem.f = ChooseWifiStepV2.this.g.f22964a.capabilities;
                                                    wifiItem.d = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.o);
                                                    wifiItem.b = true;
                                                    WifiAccountManager.a().a(wifiItem);
                                                }
                                            } else {
                                                i3++;
                                            }
                                        }
                                    }
                                }
                                if (!(connectionInfo == null || connectionInfo.getSSID() == null)) {
                                    int i4 = 0;
                                    while (true) {
                                        if (i4 >= arrayList.size()) {
                                            z2 = false;
                                            break;
                                        } else if (WifiSettingUtils.a(connectionInfo.getSSID(), ((WifiSettingUtils.KuailianScanResult) arrayList.get(i4)).f22964a.SSID)) {
                                            WifiSettingUtils.KuailianScanResult unused5 = ChooseWifiStepV2.this.f = (WifiSettingUtils.KuailianScanResult) arrayList.get(i4);
                                            break;
                                        } else if (((WifiSettingUtils.KuailianScanResult) arrayList.get(i4)).f22964a.BSSID.equalsIgnoreCase(str)) {
                                            WifiSettingUtils.KuailianScanResult unused6 = ChooseWifiStepV2.this.f = (WifiSettingUtils.KuailianScanResult) arrayList.get(i4);
                                            break;
                                        } else {
                                            i4++;
                                        }
                                    }
                                    if (!z2) {
                                        WifiSettingUtils.KuailianScanResult unused7 = ChooseWifiStepV2.this.f = null;
                                    }
                                }
                                if (ChooseWifiStepV2.this.g == null && connectionInfo != null) {
                                    for (WifiSettingUtils.KuailianScanResult kuailianScanResult4 : arrayList3) {
                                        if (kuailianScanResult4.f22964a.BSSID.equalsIgnoreCase(connectionInfo.getBSSID())) {
                                            arrayList3.remove(kuailianScanResult4);
                                            arrayList3.add(0, kuailianScanResult4);
                                            return null;
                                        } else if (kuailianScanResult4.f22964a.BSSID.equalsIgnoreCase(str)) {
                                            arrayList3.remove(kuailianScanResult4);
                                            arrayList3.add(0, kuailianScanResult4);
                                            return null;
                                        }
                                    }
                                    return null;
                                } else if (ChooseWifiStepV2.this.g == null || !arrayList3.contains(ChooseWifiStepV2.this.g)) {
                                    return null;
                                } else {
                                    arrayList3.remove(ChooseWifiStepV2.this.g);
                                    arrayList3.add(0, ChooseWifiStepV2.this.g);
                                    return null;
                                }
                            }
                        }
                    }

                    /* access modifiers changed from: private */
                    public /* synthetic */ void a() {
                        ChooseWifiStepV2.this.mCannotFindTips.setVisibility(0);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(ScanResult scanResult) {
                        if (!ChooseWifiStepV2.this.ag) {
                            ChooseWifiStepV2.this.i.clear();
                            ChooseWifiStepV2.this.i.addAll(arrayList);
                            ChooseWifiStepV2.this.j.clear();
                            ChooseWifiStepV2.this.j.addAll(arrayList2);
                            ChooseWifiStepV2.this.k.clear();
                            ChooseWifiStepV2.this.k.addAll(arrayList3);
                            ChooseWifiStepV2.this.l.clear();
                            ChooseWifiStepV2.this.l.addAll(arrayList4);
                            if (ChooseWifiStepV2.this.h()) {
                                ChooseWifiStepV2.this.mScanResultListRoot.postRefresh();
                                return;
                            }
                            if (ChooseWifiStepV2.this.k.size() > 0 || ChooseWifiStepV2.this.j.size() > 0) {
                                ChooseWifiStepV2.this.c.setVisibility(0);
                            }
                            if (ChooseWifiStepV2.this.i.size() > 0 || ChooseWifiStepV2.this.j.size() > 0) {
                                ChooseWifiStepV2.this.mOtherListView.setVisibility(0);
                                ChooseWifiStepV2.this.d.setVisibility(0);
                                ChooseWifiStepV2.this.mScrollWifiList.setBackgroundColor(Color.parseColor("#f7f7f7"));
                                ChooseWifiStepV2.this.mEmptyView.setVisibility(8);
                            } else if (ChooseWifiStepV2.this.p) {
                                ChooseWifiStepV2.this.mOtherListView.setVisibility(8);
                                ChooseWifiStepV2.this.mEmptyView.setVisibility(0);
                                ChooseWifiStepV2.this.mScrollWifiList.setBackgroundColor(-1);
                            }
                            boolean unused = ChooseWifiStepV2.this.n = false;
                            if (ChooseWifiStepV2.this.g != null && !KuailianManager.a().a(ChooseWifiStepV2.this.g.f22964a.BSSID)) {
                                SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
                            }
                            ChooseWifiStepV2.this.mCannotFindTips.setVisibility(8);
                            if (ChooseWifiStepV2.this.k.size() == 0 && (ChooseWifiStepV2.this.f == null || (!ChooseWifiStepV2.this.o && ChooseWifiStepV2.this.a(ChooseWifiStepV2.this.f.f22964a.frequency)))) {
                                ChooseWifiStepV2.this.c.setVisibility(8);
                                ChooseWifiStepV2.this.mCannotFindTips.setVisibility(0);
                            } else if (ChooseWifiStepV2.this.k.size() == 0) {
                                if (!ChooseWifiStepV2.this.ag) {
                                    ((TextView) ChooseWifiStepV2.this.c.findViewById(R.id.list_title)).setText(ChooseWifiStepV2.this.af.getString(R.string.current_phone_wifi));
                                    ChooseWifiStepV2.this.k.add(ChooseWifiStepV2.this.f);
                                } else {
                                    return;
                                }
                            } else if (ChooseWifiStepV2.this.g == null) {
                                WifiSettingUtils.KuailianScanResult unused2 = ChooseWifiStepV2.this.g = (WifiSettingUtils.KuailianScanResult) ChooseWifiStepV2.this.k.get(0);
                            }
                            if (ChooseWifiStepV2.this.m != null) {
                                String str = ChooseWifiStepV2.this.m.f16423a;
                                String str2 = ChooseWifiStepV2.this.m.c;
                                WifiInfo connectionInfo = ((WifiManager) SHApplication.getAppContext().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
                                if (connectionInfo != null && (WifiSettingUtils.a(str, connectionInfo.getSSID()) || WifiSettingUtils.a(str2, connectionInfo.getSSID()))) {
                                    ArrayList<WifiSettingUtils.KuailianScanResult> arrayList = new ArrayList<>();
                                    arrayList.addAll(ChooseWifiStepV2.this.i);
                                    arrayList.addAll(ChooseWifiStepV2.this.j);
                                    for (WifiSettingUtils.KuailianScanResult kuailianScanResult : arrayList) {
                                        if ((WifiSettingUtils.a(kuailianScanResult.f22964a.SSID, str) || WifiSettingUtils.a(kuailianScanResult.f22964a.SSID, str2)) && ((!TextUtils.isEmpty(str) && str.equals(str2) && ChooseWifiStepV2.this.a(kuailianScanResult.f22964a)) || ChooseWifiStepV2.this.b(kuailianScanResult.f22964a))) {
                                            kuailianScanResult.b = true;
                                            kuailianScanResult.c = true;
                                            kuailianScanResult.d = SHApplication.getAppContext().getResources().getString(R.string.choose_wifi_mirouter);
                                            ChooseWifiStepV2.this.e(kuailianScanResult.f22964a.SSID);
                                            ChooseWifiStepV2.this.i.add(0, kuailianScanResult);
                                            ChooseWifiStepV2.this.k.add(0, kuailianScanResult);
                                            WifiSettingUtils.KuailianScanResult unused3 = ChooseWifiStepV2.this.g = kuailianScanResult;
                                        }
                                    }
                                }
                            }
                            ChooseWifiStepV2.this.k();
                            ChooseWifiStepV2.this.r.notifyDataSetChanged();
                            ChooseWifiStepV2.this.s.notifyDataSetChanged();
                            ChooseWifiStepV2.this.mScanResultListRoot.postRefresh();
                        }
                    }
                }, new Void[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean d(String str) {
        return str.contains("WEP") || str.contains("EAP") || str.contains("WAPI-KEY") || str.contains("WAPI-PSK") || str.contains("WAPI-CERT");
    }

    /* access modifiers changed from: private */
    public boolean a(ScanResult scanResult) {
        return (d(scanResult.capabilities) || WifiSettingUtils.a(scanResult) == 0 || scanResult.level == 0) ? false : true;
    }

    /* access modifiers changed from: private */
    public void e(String str) {
        Iterator<WifiSettingUtils.KuailianScanResult> it = this.i.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WifiSettingUtils.KuailianScanResult next = it.next();
            if (next.f22964a.SSID.equals(str)) {
                this.i.remove(next);
                break;
            }
        }
        for (WifiSettingUtils.KuailianScanResult next2 : this.k) {
            if (next2.f22964a.SSID.equals(str)) {
                this.k.remove(next2);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean b(ScanResult scanResult) {
        return (!a(scanResult.frequency) || this.o) && !d(scanResult.capabilities) && WifiSettingUtils.a(scanResult) != 0 && scanResult.level != 0;
    }

    /* access modifiers changed from: private */
    public void a(List<WifiSettingUtils.KuailianScanResult> list) {
        Collections.sort(list, new Comparator<WifiSettingUtils.KuailianScanResult>() {
            /* renamed from: a */
            public int compare(WifiSettingUtils.KuailianScanResult kuailianScanResult, WifiSettingUtils.KuailianScanResult kuailianScanResult2) {
                if ((!kuailianScanResult.b || !kuailianScanResult2.b) && (kuailianScanResult.b || kuailianScanResult2.b)) {
                    return kuailianScanResult.b ? -1 : 1;
                }
                return kuailianScanResult2.f22964a.level - kuailianScanResult.f22964a.level;
            }
        });
    }

    /* access modifiers changed from: private */
    public void k() {
        if (this.g != null) {
            this.mNextButton.setEnabled(true);
            this.mNextButton.setTextColor(this.af.getResources().getColor(R.color.blue_btn_text_enable));
            return;
        }
        this.mNextButton.setEnabled(false);
        this.mNextButton.setTextColor(this.af.getResources().getColor(R.color.blue_btn_text_disable));
    }

    private void n() {
        this.e.startScan();
        this.n = true;
    }

    /* access modifiers changed from: private */
    public void b(boolean z) {
        if (this.g != null && this.g.f22964a != null) {
            final WifiSettingUtils.KuailianScanResult kuailianScanResult = this.g;
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.c, kuailianScanResult.f22964a);
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.d, kuailianScanResult.f22964a.SSID);
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.g, Integer.valueOf(kuailianScanResult.f22964a.level));
            STAT.c.b(this.q, this.ai);
            WifiAccount.WifiItem a2 = WifiAccountManager.a().a(kuailianScanResult.f22964a.BSSID);
            String str = null;
            if (a2 != null) {
                str = a2.d;
            }
            if (TextUtils.isEmpty(str) && this.g.c && this.m != null) {
                if (WifiSettingUtils.a(this.g.f22964a.SSID, this.m.f16423a)) {
                    str = this.m.b;
                } else if (WifiSettingUtils.a(this.g.f22964a.SSID, this.m.c)) {
                    str = this.m.d;
                }
            }
            if (!TextUtils.isEmpty(str)) {
                switch (b(str)) {
                    case -1:
                        if (!z) {
                            SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, str);
                            D();
                            return;
                        }
                        return;
                    case 0:
                        a(kuailianScanResult, true, (DialogListener) new DialogListener() {
                            public void a(String str) {
                                ChooseWifiStepV2.this.a(kuailianScanResult, str);
                                SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, str);
                                ChooseWifiStepV2.this.D();
                            }
                        });
                        return;
                    case 1:
                        SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, str);
                        D();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final WifiSettingUtils.KuailianScanResult kuailianScanResult) {
        STAT.d.N();
        if (this.k.contains(kuailianScanResult)) {
            this.g = kuailianScanResult;
            this.r.notifyDataSetChanged();
            p();
            return;
        }
        a(kuailianScanResult, false, (DialogListener) new DialogListener() {
            public void a(String str) {
                ChooseWifiStepV2.this.a(kuailianScanResult, str);
                if (!ChooseWifiStepV2.this.k.contains(kuailianScanResult)) {
                    WifiSettingUtils.KuailianScanResult unused = ChooseWifiStepV2.this.g = kuailianScanResult;
                    ChooseWifiStepV2.this.k.add(0, kuailianScanResult);
                    if (!ChooseWifiStepV2.this.i.contains(kuailianScanResult)) {
                        ChooseWifiStepV2.this.i.add(kuailianScanResult);
                    }
                    if (ChooseWifiStepV2.this.j.contains(kuailianScanResult)) {
                        ChooseWifiStepV2.this.j.remove(kuailianScanResult);
                    }
                    ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) ChooseWifiStepV2.this.i);
                    ChooseWifiStepV2.this.s.notifyDataSetChanged();
                    ChooseWifiStepV2.this.r.notifyDataSetChanged();
                }
                ChooseWifiStepV2.this.p();
            }
        });
    }

    private static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f22569a;
        TextView b;
        TextView c;
        ImageView d;
        ImageView e;
        ImageView f;

        private ViewHolder() {
        }
    }

    /* access modifiers changed from: private */
    public void a(WifiSettingUtils.KuailianScanResult kuailianScanResult, boolean z, final DialogListener dialogListener) {
        if (kuailianScanResult != null && kuailianScanResult.f22964a != null) {
            View inflate = LayoutInflater.from(this.af).inflate(R.layout.dialog_choose_wifi_input, this.mSavedListView, false);
            int i2 = 8;
            inflate.findViewById(R.id.input_again_root_view).setVisibility(z ? 0 : 8);
            View findViewById = inflate.findViewById(R.id.wifi_name);
            if (!z) {
                i2 = 0;
            }
            findViewById.setVisibility(i2);
            ((TextView) inflate.findViewById(R.id.wifi_name)).setText(kuailianScanResult.f22964a.SSID);
            ((TextView) inflate.findViewById(R.id.input_again_wifi_name)).setText(kuailianScanResult.f22964a.SSID);
            CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.change_password_state);
            final EditText editText = (EditText) inflate.findViewById(R.id.password_input_et);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    editText.setInputType(z ? 144 : 129);
                    editText.setSelection(editText.getText().toString().length());
                }
            });
            checkBox.setChecked(true);
            final Button button = (Button) inflate.findViewById(R.id.right_button);
            button.setEnabled(false);
            final MLAlertDialog b2 = new MLAlertDialog.Builder(this.af).b(inflate).b();
            b2.setCanceledOnTouchOutside(false);
            ((Button) inflate.findViewById(R.id.left_button)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (b2.isShowing()) {
                        b2.dismiss();
                    }
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (dialogListener != null) {
                        dialogListener.a(editText.getText().toString());
                    }
                    if (b2.isShowing()) {
                        b2.dismiss();
                    }
                }
            });
            if (WifiAccountManager.a().a(kuailianScanResult.f22964a.BSSID) != null) {
                checkBox.setChecked(false);
                editText.setText("");
                editText.setSelection(editText.getText().toString().length());
            }
            editText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    if (ChooseWifiStepV2.this.af != null) {
                        if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                            button.setEnabled(false);
                            button.setTextColor(Color.parseColor("#4d000000"));
                            return;
                        }
                        button.setEnabled(true);
                        button.setTextColor(ChooseWifiStepV2.this.af.getResources().getColor(R.color.miui_blue));
                    }
                }
            });
            b2.show();
            inflate.postDelayed(new Runnable() {
                public void run() {
                    InputMethodManager inputMethodManager;
                    if (ChooseWifiStepV2.this.af != null && (inputMethodManager = (InputMethodManager) ChooseWifiStepV2.this.af.getSystemService("input_method")) != null) {
                        inputMethodManager.showSoftInput(editText, 0);
                    }
                }
            }, 100);
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        if (this.af != null && this.h != null) {
            new MLAlertDialog.Builder(this.af).b((CharSequence) this.af.getResources().getString(R.string.sure_delete_wifi)).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (ChooseWifiStepV2.this.k.contains(ChooseWifiStepV2.this.h)) {
                        ChooseWifiStepV2.this.k.remove(ChooseWifiStepV2.this.h);
                        ChooseWifiStepV2.this.b(ChooseWifiStepV2.this.h);
                        if (ChooseWifiStepV2.this.g != null && ChooseWifiStepV2.this.h.f22964a.BSSID.equalsIgnoreCase(ChooseWifiStepV2.this.g.f22964a.BSSID)) {
                            if (ChooseWifiStepV2.this.k.size() > 0) {
                                WifiSettingUtils.KuailianScanResult unused = ChooseWifiStepV2.this.g = (WifiSettingUtils.KuailianScanResult) ChooseWifiStepV2.this.k.get(0);
                            } else if (ChooseWifiStepV2.this.f == null || (!ChooseWifiStepV2.this.o && ChooseWifiStepV2.this.a(ChooseWifiStepV2.this.f.f22964a.frequency))) {
                                WifiSettingUtils.KuailianScanResult unused2 = ChooseWifiStepV2.this.g = null;
                            } else {
                                ChooseWifiStepV2.this.k.add(ChooseWifiStepV2.this.f);
                                WifiSettingUtils.KuailianScanResult unused3 = ChooseWifiStepV2.this.g = null;
                            }
                        }
                        ChooseWifiStepV2.this.a((List<WifiSettingUtils.KuailianScanResult>) ChooseWifiStepV2.this.i);
                        ChooseWifiStepV2.this.p();
                        ChooseWifiStepV2.this.s.notifyDataSetChanged();
                        ChooseWifiStepV2.this.r.notifyDataSetChanged();
                    }
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).a(this.af.getResources().getColor(R.color.miui_blue), -1).b().show();
        }
    }

    /* access modifiers changed from: private */
    public void p() {
        this.mScanResultListRoot.setVisibility(8);
        this.mEditWifiViewRoot.setVisibility(8);
        this.mSaveWifiViewRoot.setVisibility(0);
        k();
        if (this.k.size() > 0) {
            if (this.g != null || this.k.size() != 1 || this.f == null || !this.f.f22964a.BSSID.equalsIgnoreCase(this.k.get(0).f22964a.BSSID)) {
                ((TextView) this.c.findViewById(R.id.list_title)).setText(this.af.getString(R.string.kuailian_save_passwd));
            } else {
                ((TextView) this.c.findViewById(R.id.list_title)).setText(this.af.getString(R.string.current_phone_wifi));
            }
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        this.mTitleTv.setText(R.string.device_choose_wifi);
        this.mSubTitle.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void q() {
        this.mScanResultListRoot.setVisibility(8);
        this.mSaveWifiViewRoot.setVisibility(8);
        this.mEditWifiViewRoot.setVisibility(0);
        this.mTitleTv.setText(R.string.edit_choose_wifi_title);
        this.mSubTitle.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void r() {
        if (!this.p) {
            this.p = true;
            this.mScanResultListRoot.doRefresh();
        }
        this.mSaveWifiViewRoot.setVisibility(8);
        this.mEditWifiViewRoot.setVisibility(8);
        this.mScanResultListRoot.setVisibility(0);
        this.mTitleTv.setText(R.string.choose_other_wifi_title);
        this.mSubTitle.setVisibility(8);
        this.s.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void a(WifiSettingUtils.KuailianScanResult kuailianScanResult, String str) {
        if (this.af != null) {
            kuailianScanResult.b = true;
            kuailianScanResult.d = this.af.getString(R.string.kuailian_save_passwd);
            ScanResult scanResult = kuailianScanResult.f22964a;
            WifiAccount.WifiItem wifiItem = new WifiAccount.WifiItem();
            wifiItem.e = scanResult.BSSID;
            if (wifiItem.e == null) {
                wifiItem.e = "";
            }
            wifiItem.c = scanResult.SSID;
            wifiItem.f = scanResult.capabilities;
            wifiItem.d = str;
            wifiItem.b = true;
            wifiItem.f11584a = this.e.getConnectionInfo().getNetworkId();
            WifiAccountManager.a().a(wifiItem);
        }
    }

    /* access modifiers changed from: private */
    public void b(WifiSettingUtils.KuailianScanResult kuailianScanResult) {
        kuailianScanResult.d = null;
        kuailianScanResult.b = false;
        WifiAccountManager.a().b(kuailianScanResult.f22964a.BSSID);
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        if (this.i.size() == 0) {
            return LocationPermissionDialogHelper.a((SmartConfigMainActivity) this.af, false, R.string.open_location_permission);
        }
        return false;
    }

    public void a(int i2, int i3, Intent intent) {
        super.a(i2, i3, intent);
        if (i2 == b) {
            d_(true);
        }
    }

    public void e() {
        if (U_() != null) {
            U_().removeMessages(104);
        }
    }
}
