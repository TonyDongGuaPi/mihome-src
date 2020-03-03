package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.exoplayer2.C;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.BlankActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.ResizeLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.WifiAccount;
import com.xiaomi.smarthome.miio.activity.GDPRLicenseActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.util.ArrayList;
import java.util.List;

public class ChooseWifiStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    public static int f22500a = 200;
    /* access modifiers changed from: private */
    public BaseAdapter b = new ConnWifiAdapter();
    /* access modifiers changed from: private */
    public BaseAdapter c = new UnConnWifiAdapter();
    /* access modifiers changed from: private */
    public WifiManager d;
    /* access modifiers changed from: private */
    public ScanResult e;
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> f = new ArrayList();
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> g = new ArrayList();
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> h = new ArrayList();
    private boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public boolean l;
    @BindView(2131428310)
    CheckBox mCheckbox;
    @BindView(2131428526)
    ListView mConnectList;
    @BindView(2131429685)
    ImageView mDeviceIcon;
    @BindView(2131430975)
    TextView mDeviceInfo;
    @BindView(2131428307)
    CheckBox mLicenseCheckBox;
    @BindView(2131427637)
    TextView mLicenseTv;
    @BindView(2131431178)
    Button mNextBtn;
    @BindView(2131434005)
    EditText mPasswordEditor;
    @BindView(2131434006)
    EditText mPasswordEditorAbove;
    @BindView(2131434007)
    TextView mPasswordToggle;
    @BindView(2131428347)
    ResizeLayout mResizeLayout;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131434009)
    CustomPullDownRefreshLinearLayout mScanResultList;
    @BindView(2131432231)
    View mScanWifi;
    @BindView(2131433994)
    ScrollView mScrollWifiList;
    @BindView(2131434013)
    ImageView mShowScanResultToggle;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131428346)
    View mTopContainer;
    @BindView(2131433013)
    View mTopMask;
    @BindView(2131432641)
    View mTvSubDesc;
    @BindView(2131433607)
    ListView mUnConnectList;
    @BindView(2131430719)
    TextView mWifiChooser;
    @BindView(2131434010)
    View mWifiPassContainer;

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
            b();
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.j) {
            this.j = false;
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            final ArrayList arrayList3 = new ArrayList();
            AsyncTaskUtils.a(new AsyncTask<Void, Void, ScanResult>() {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Removed duplicated region for block: B:98:0x038d  */
                /* renamed from: a */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public android.net.wifi.ScanResult doInBackground(java.lang.Void... r18) {
                    /*
                        r17 = this;
                        r0 = r17
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r1 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.WifiManager r1 = r1.d
                        android.net.wifi.WifiInfo r1 = r1.getConnectionInfo()
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r2 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.WifiManager r2 = r2.d
                        java.util.List r2 = r2.getScanResults()
                        r3 = 0
                        if (r2 != 0) goto L_0x001a
                        return r3
                    L_0x001a:
                        java.util.HashMap r4 = new java.util.HashMap
                        r4.<init>()
                        java.util.HashMap r5 = new java.util.HashMap
                        r5.<init>()
                        r6 = 0
                        r8 = r3
                        r7 = 0
                    L_0x0027:
                        int r9 = r2.size()
                        r10 = 1
                        if (r7 >= r9) goto L_0x029c
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        boolean r9 = android.text.TextUtils.isEmpty(r9)
                        if (r9 != 0) goto L_0x0297
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        boolean r9 = com.xiaomi.smarthome.device.DeviceFactory.f((android.net.wifi.ScanResult) r9)
                        if (r9 == 0) goto L_0x004a
                        goto L_0x0297
                    L_0x004a:
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        boolean r9 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.d((android.net.wifi.ScanResult) r9)
                        com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager r11 = com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager.a()
                        java.lang.Object r12 = r2.get(r7)
                        android.net.wifi.ScanResult r12 = (android.net.wifi.ScanResult) r12
                        java.lang.String r12 = r12.BSSID
                        com.xiaomi.smarthome.miio.WifiAccount$WifiItem r11 = r11.a((java.lang.String) r12)
                        if (r11 == 0) goto L_0x0068
                        r11 = 1
                        goto L_0x0069
                    L_0x0068:
                        r11 = 0
                    L_0x0069:
                        if (r11 != 0) goto L_0x008e
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r11 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.WifiManager r11 = r11.d
                        java.lang.Object r12 = r2.get(r7)
                        android.net.wifi.ScanResult r12 = (android.net.wifi.ScanResult) r12
                        java.lang.String r12 = r12.SSID
                        boolean r11 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((android.net.wifi.WifiManager) r11, (java.lang.String) r12)
                        if (r11 == 0) goto L_0x008d
                        com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r11 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
                        java.lang.String r12 = "miui_class"
                        java.lang.Object r11 = r11.a(r12)
                        if (r11 == 0) goto L_0x008d
                        r11 = 1
                        goto L_0x008e
                    L_0x008d:
                        r11 = 0
                    L_0x008e:
                        r12 = 5000(0x1388, float:7.006E-42)
                        if (r1 == 0) goto L_0x00f2
                        java.lang.String r13 = r1.getSSID()
                        java.lang.Object r14 = r2.get(r7)
                        android.net.wifi.ScanResult r14 = (android.net.wifi.ScanResult) r14
                        java.lang.String r14 = r14.SSID
                        boolean r13 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((java.lang.String) r13, (java.lang.String) r14)
                        if (r13 == 0) goto L_0x00f2
                        if (r9 == 0) goto L_0x00f2
                        java.lang.Object r13 = r2.get(r7)
                        android.net.wifi.ScanResult r13 = (android.net.wifi.ScanResult) r13
                        int r13 = r13.frequency
                        if (r13 <= r12) goto L_0x00f2
                        java.lang.Object r8 = r2.get(r7)
                        android.net.wifi.ScanResult r8 = (android.net.wifi.ScanResult) r8
                        java.lang.String r8 = r8.BSSID
                        java.lang.String r13 = "\\:"
                        java.lang.String[] r8 = r8.split(r13)
                        java.lang.String r13 = "%1$s:%2$s:%3$s:%4$s:%5$s:%6$x"
                        r14 = 6
                        java.lang.Object[] r14 = new java.lang.Object[r14]
                        r15 = r8[r6]
                        r14[r6] = r15
                        r15 = r8[r10]
                        r14[r10] = r15
                        r15 = 2
                        r16 = r8[r15]
                        r14[r15] = r16
                        r15 = 3
                        r16 = r8[r15]
                        r14[r15] = r16
                        r15 = 4
                        r16 = r8[r15]
                        r14[r15] = r16
                        r15 = 5
                        r8 = r8[r15]
                        r3 = 16
                        java.lang.Integer r3 = java.lang.Integer.valueOf(r8, r3)
                        int r3 = r3.intValue()
                        int r3 = r3 - r10
                        java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                        r14[r15] = r3
                        java.lang.String r8 = java.lang.String.format(r13, r14)
                    L_0x00f2:
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        int r3 = r3.frequency
                        r10 = 100
                        if (r3 <= r12) goto L_0x0106
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r3 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        boolean r3 = r3.k
                        if (r3 == 0) goto L_0x0202
                    L_0x0106:
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        java.lang.String r3 = r3.capabilities
                        java.lang.String r13 = "WEP"
                        boolean r3 = r3.contains(r13)
                        if (r3 != 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        java.lang.String r3 = r3.capabilities
                        java.lang.String r13 = "EAP"
                        boolean r3 = r3.contains(r13)
                        if (r3 != 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        java.lang.String r3 = r3.capabilities
                        java.lang.String r13 = "WAPI-KEY"
                        boolean r3 = r3.contains(r13)
                        if (r3 != 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        java.lang.String r3 = r3.capabilities
                        java.lang.String r13 = "WAPI-PSK"
                        boolean r3 = r3.contains(r13)
                        if (r3 != 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        java.lang.String r3 = r3.capabilities
                        java.lang.String r13 = "WAPI-CERT"
                        boolean r3 = r3.contains(r13)
                        if (r3 != 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        int r3 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((android.net.wifi.ScanResult) r3)
                        if (r3 == 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        int r3 = r3.level
                        if (r3 == 0) goto L_0x0202
                        java.lang.Object r3 = r2.get(r7)
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        java.lang.String r3 = r3.SSID
                        boolean r3 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.d((java.lang.String) r3)
                        if (r3 == 0) goto L_0x017c
                        goto L_0x0202
                    L_0x017c:
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = new com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult
                        r3.<init>()
                        r3.c = r9
                        r3.b = r11
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        r3.f22964a = r9
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        boolean r9 = r4.containsKey(r9)
                        if (r9 == 0) goto L_0x01f0
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        int r9 = r9.level
                        int r9 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r9, (int) r10)
                        java.lang.Object r11 = r2.get(r7)
                        android.net.wifi.ScanResult r11 = (android.net.wifi.ScanResult) r11
                        java.lang.String r11 = r11.SSID
                        java.lang.Object r11 = r4.get(r11)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r11 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r11
                        android.net.wifi.ScanResult r11 = r11.f22964a
                        int r11 = r11.level
                        int r10 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r11, (int) r10)
                        if (r9 <= r10) goto L_0x0297
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        java.lang.Object r9 = r4.get(r9)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r9 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r9
                        android.net.wifi.ScanResult r10 = r9.f22964a
                        java.lang.String r10 = r10.SSID
                        r4.remove(r10)
                        java.lang.Object r10 = r2.get(r7)
                        android.net.wifi.ScanResult r10 = (android.net.wifi.ScanResult) r10
                        java.lang.String r10 = r10.SSID
                        r4.put(r10, r3)
                        java.util.List r10 = r1
                        r10.remove(r9)
                        java.util.List r10 = r3
                        r10.remove(r9)
                        java.util.List r9 = r3
                        r9.add(r3)
                        goto L_0x0297
                    L_0x01f0:
                        java.util.List r9 = r1
                        r9.add(r3)
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        r4.put(r9, r3)
                        goto L_0x0297
                    L_0x0202:
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = new com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult
                        r3.<init>()
                        r3.c = r9
                        r3.b = r11
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        r3.f22964a = r9
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        int r9 = r9.frequency
                        if (r9 <= r12) goto L_0x022a
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r9 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        boolean r9 = r9.k
                        if (r9 != 0) goto L_0x022a
                        java.util.List r9 = r2
                        r9.add(r3)
                    L_0x022a:
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        boolean r9 = r5.containsKey(r9)
                        if (r9 == 0) goto L_0x0287
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        int r9 = r9.level
                        int r9 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r9, (int) r10)
                        java.lang.Object r11 = r2.get(r7)
                        android.net.wifi.ScanResult r11 = (android.net.wifi.ScanResult) r11
                        java.lang.String r11 = r11.SSID
                        java.lang.Object r11 = r5.get(r11)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r11 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r11
                        android.net.wifi.ScanResult r11 = r11.f22964a
                        int r11 = r11.level
                        int r10 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r11, (int) r10)
                        if (r9 <= r10) goto L_0x0297
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        java.lang.Object r9 = r5.get(r9)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r9 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r9
                        android.net.wifi.ScanResult r10 = r3.f22964a
                        java.lang.String r10 = r10.SSID
                        r4.remove(r10)
                        java.lang.Object r10 = r2.get(r7)
                        android.net.wifi.ScanResult r10 = (android.net.wifi.ScanResult) r10
                        java.lang.String r10 = r10.SSID
                        r4.put(r10, r3)
                        java.util.List r3 = r3
                        r3.remove(r9)
                        java.util.List r3 = r3
                        r3.add(r9)
                        goto L_0x0297
                    L_0x0287:
                        java.util.List r9 = r3
                        r9.add(r3)
                        java.lang.Object r9 = r2.get(r7)
                        android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
                        java.lang.String r9 = r9.SSID
                        r5.put(r9, r3)
                    L_0x0297:
                        int r7 = r7 + 1
                        r3 = 0
                        goto L_0x0027
                    L_0x029c:
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep$1$1 r2 = new com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep$1$1
                        r2.<init>()
                        java.util.List r3 = r1
                        java.util.Collections.sort(r3, r2)
                        java.util.List r3 = r3
                        java.util.Collections.sort(r3, r2)
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r2 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.ScanResult r2 = r2.e
                        if (r2 == 0) goto L_0x02ec
                    L_0x02b3:
                        java.util.List r1 = r1
                        int r1 = r1.size()
                        if (r6 >= r1) goto L_0x03d0
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r1 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.ScanResult r1 = r1.e
                        java.lang.String r1 = r1.BSSID
                        java.util.List r2 = r1
                        java.lang.Object r2 = r2.get(r6)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                        android.net.wifi.ScanResult r2 = r2.f22964a
                        java.lang.String r2 = r2.BSSID
                        boolean r1 = r1.equalsIgnoreCase(r2)
                        if (r1 == 0) goto L_0x02d7
                        goto L_0x03d0
                    L_0x02d7:
                        if (r8 == 0) goto L_0x02e9
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r1 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.ScanResult r1 = r1.e
                        java.lang.String r1 = r1.BSSID
                        boolean r1 = r1.equalsIgnoreCase(r8)
                        if (r1 == 0) goto L_0x02e9
                        goto L_0x03d0
                    L_0x02e9:
                        int r6 = r6 + 1
                        goto L_0x02b3
                    L_0x02ec:
                        com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
                        java.lang.String r3 = "target_bssid"
                        java.lang.Object r2 = r2.a(r3)
                        java.lang.String r2 = (java.lang.String) r2
                        boolean r3 = android.text.TextUtils.isEmpty(r2)
                        if (r3 != 0) goto L_0x037a
                        r3 = 0
                    L_0x02ff:
                        java.util.List r4 = r1
                        int r4 = r4.size()
                        if (r3 >= r4) goto L_0x037a
                        java.util.List r4 = r1
                        java.lang.Object r4 = r4.get(r3)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r4 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r4
                        android.net.wifi.ScanResult r4 = r4.f22964a
                        java.lang.String r4 = r4.BSSID
                        boolean r4 = r2.equalsIgnoreCase(r4)
                        if (r4 == 0) goto L_0x0377
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r2 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        java.util.List r4 = r1
                        java.lang.Object r3 = r4.get(r3)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r3
                        android.net.wifi.ScanResult r3 = r3.f22964a
                        android.net.wifi.ScanResult unused = r2.e = r3
                        com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
                        java.lang.String r3 = "target_passwd"
                        java.lang.Object r2 = r2.a(r3)
                        if (r2 == 0) goto L_0x037b
                        com.xiaomi.smarthome.miio.WifiAccount$WifiItem r2 = new com.xiaomi.smarthome.miio.WifiAccount$WifiItem
                        r2.<init>()
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r3 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.ScanResult r3 = r3.e
                        java.lang.String r3 = r3.BSSID
                        r2.e = r3
                        java.lang.String r3 = r2.e
                        if (r3 != 0) goto L_0x034b
                        java.lang.String r3 = ""
                        r2.e = r3
                    L_0x034b:
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r3 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.ScanResult r3 = r3.e
                        java.lang.String r3 = r3.SSID
                        r2.c = r3
                        com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep r3 = com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.this
                        android.net.wifi.ScanResult r3 = r3.e
                        java.lang.String r3 = r3.capabilities
                        r2.f = r3
                        com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r3 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
                        java.lang.String r4 = "target_passwd"
                        java.lang.Object r3 = r3.a(r4)
                        java.lang.String r3 = (java.lang.String) r3
                        r2.d = r3
                        r2.b = r10
                        com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager r3 = com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager.a()
                        r3.a((com.xiaomi.smarthome.miio.WifiAccount.WifiItem) r2)
                        goto L_0x037b
                    L_0x0377:
                        int r3 = r3 + 1
                        goto L_0x02ff
                    L_0x037a:
                        r10 = 0
                    L_0x037b:
                        if (r10 != 0) goto L_0x03d0
                        if (r1 == 0) goto L_0x03d0
                        java.lang.String r2 = r1.getBSSID()
                        if (r2 == 0) goto L_0x03d0
                    L_0x0385:
                        java.util.List r2 = r1
                        int r2 = r2.size()
                        if (r6 >= r2) goto L_0x03d0
                        java.lang.String r2 = r1.getBSSID()
                        java.util.List r3 = r1
                        java.lang.Object r3 = r3.get(r6)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r3
                        android.net.wifi.ScanResult r3 = r3.f22964a
                        java.lang.String r3 = r3.BSSID
                        boolean r2 = r2.equalsIgnoreCase(r3)
                        if (r2 == 0) goto L_0x03ae
                        java.util.List r1 = r1
                        java.lang.Object r1 = r1.get(r6)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r1 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r1
                        android.net.wifi.ScanResult r3 = r1.f22964a
                        goto L_0x03d1
                    L_0x03ae:
                        if (r8 == 0) goto L_0x03cd
                        java.util.List r2 = r1
                        java.lang.Object r2 = r2.get(r6)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                        android.net.wifi.ScanResult r2 = r2.f22964a
                        java.lang.String r2 = r2.BSSID
                        boolean r2 = r2.equalsIgnoreCase(r8)
                        if (r2 == 0) goto L_0x03cd
                        java.util.List r1 = r1
                        java.lang.Object r1 = r1.get(r6)
                        com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r1 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r1
                        android.net.wifi.ScanResult r3 = r1.f22964a
                        goto L_0x03d1
                    L_0x03cd:
                        int r6 = r6 + 1
                        goto L_0x0385
                    L_0x03d0:
                        r3 = 0
                    L_0x03d1:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep.AnonymousClass1.doInBackground(java.lang.Void[]):android.net.wifi.ScanResult");
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(ScanResult scanResult) {
                    if (!ChooseWifiStep.this.ag) {
                        ChooseWifiStep.this.f.clear();
                        ChooseWifiStep.this.f.addAll(arrayList);
                        ChooseWifiStep.this.g.clear();
                        ChooseWifiStep.this.g.addAll(arrayList2);
                        ChooseWifiStep.this.h.clear();
                        ChooseWifiStep.this.h.addAll(arrayList3);
                        if (!ChooseWifiStep.this.g()) {
                            ScanResult unused = ChooseWifiStep.this.e = scanResult;
                            if (ChooseWifiStep.this.e == null) {
                                ChooseWifiStep.this.mWifiChooser.setText(R.string.click_to_choose_wlan);
                            } else {
                                ChooseWifiStep.this.mWifiChooser.setText(ChooseWifiStep.this.e.SSID);
                            }
                            ChooseWifiStep.this.mScanWifi.setVisibility(8);
                            boolean unused2 = ChooseWifiStep.this.j = false;
                            if (ChooseWifiStep.this.e != null && !KuailianManager.a().a(ChooseWifiStep.this.e.BSSID)) {
                                SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
                            }
                            ChooseWifiStep.this.a(ChooseWifiStep.this.e);
                            ChooseWifiStep.this.o();
                            ChooseWifiStep.this.mPasswordEditor.setEnabled(true);
                            ChooseWifiStep.this.mWifiChooser.setEnabled(true);
                            ChooseWifiStep.this.mShowScanResultToggle.setEnabled(true);
                            ChooseWifiStep.this.b.notifyDataSetChanged();
                            ChooseWifiStep.this.c.notifyDataSetChanged();
                            ChooseWifiStep.this.mScanResultList.postRefresh();
                        }
                    }
                }
            }, new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        if (this.f.size() != 0) {
            return false;
        }
        if (!SHLocationManager.f()) {
            k();
            return true;
        }
        new MLAlertDialog.Builder(this.af).b((int) R.string.get_wifi_scan_result_error).a((int) R.string.wifi_rescan_wifi, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ChooseWifiStep.this.n();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ChooseWifiStep.this.a();
            }
        }).a(false).d();
        return true;
    }

    private void k() {
        new MLAlertDialog.Builder(this.af).b((int) R.string.open_location_permission).a((int) R.string.set_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", ChooseWifiStep.this.af.getPackageName(), (String) null));
                ((SmartConfigMainActivity) ChooseWifiStep.this.af).startActivityForResult(intent, ChooseWifiStep.f22500a);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ChooseWifiStep.this.a();
            }
        }).a(false).d();
    }

    public void a(int i2, int i3, Intent intent) {
        super.a(i2, i3, intent);
        if (i2 == f22500a) {
            a();
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        this.mScanWifi.setVisibility(0);
        try {
            this.d.startScan();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mPasswordEditor.setEnabled(false);
        this.mWifiChooser.setEnabled(false);
        this.mNextBtn.setEnabled(false);
        this.mPasswordToggle.setEnabled(false);
        this.j = true;
    }

    /* access modifiers changed from: private */
    public void a(ScanResult scanResult) {
        if (scanResult == null) {
            this.mWifiPassContainer.setVisibility(8);
            this.mCheckbox.setVisibility(8);
        } else if (WifiSettingUtils.a(scanResult) == 0) {
            this.mWifiPassContainer.setVisibility(8);
            this.mCheckbox.setVisibility(8);
            this.mPasswordToggle.setEnabled(false);
        } else {
            WifiAccount.WifiItem a2 = WifiAccountManager.a().a(scanResult.BSSID);
            if (SmartConfigDataProvider.a().a(SmartConfigDataProvider.p) != null && WifiSettingUtils.a(this.d, scanResult.SSID)) {
                b(false);
            } else if (a2 != null) {
                this.l = true;
                b(false);
            } else {
                b(true);
            }
        }
    }

    public boolean a() {
        if (this.mScanResultList.getVisibility() == 0) {
            j();
            return true;
        }
        STAT.c.b(this.aj, this.ai);
        d_(false);
        return true;
    }

    private void b(boolean z) {
        this.mWifiPassContainer.setVisibility(0);
        this.mCheckbox.setVisibility(0);
        this.mCheckbox.setChecked(true);
        if (!z) {
            this.mPasswordEditorAbove.setInputType(129);
            this.mPasswordEditorAbove.setText("123456");
            this.mPasswordEditorAbove.setVisibility(0);
            this.mPasswordEditor.setVisibility(8);
            this.mPasswordToggle.setEnabled(false);
            this.mPasswordToggle.setText(R.string.smart_config_show_passwd);
            this.mPasswordToggle.setTextColor(this.af.getResources().getColor(R.color.std_list_subtitle));
            this.mNextBtn.setEnabled(true);
        } else {
            this.mPasswordEditor.setVisibility(0);
            this.mPasswordEditorAbove.setVisibility(8);
            this.mPasswordToggle.setEnabled(true);
            this.mNextBtn.setEnabled(false);
            this.mPasswordToggle.setText(R.string.smart_config_hide_passwd);
            this.mPasswordToggle.setTextColor(-11371828);
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
        }
        if (!this.i) {
            this.i = true;
            this.af.startActivity(new Intent(this.af, BlankActivity.class));
        }
    }

    /* access modifiers changed from: private */
    public void o() {
        if (this.e == null || this.j || !(this.mWifiPassContainer.getVisibility() == 8 || this.mPasswordEditorAbove.getVisibility() == 0 || !TextUtils.isEmpty(this.mPasswordEditor.getText().toString()))) {
            this.mNextBtn.setEnabled(false);
        } else {
            this.mNextBtn.setEnabled(true);
        }
    }

    private void p() {
        if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.R, false)).booleanValue()) {
            this.mDeviceIcon.setVisibility(8);
            this.mTopContainer.setVisibility(0);
            this.mDeviceInfo.setText(R.string.kuailian_main_title_2);
            this.mNextBtn.setBackgroundResource(R.drawable.camera_refresh_bg);
            this.mNextBtn.setTextColor(-1);
            this.mCheckbox.setButtonDrawable(R.drawable.camera_check_icon);
        } else {
            this.mDeviceIcon.setVisibility(0);
            this.mTopContainer.setVisibility(8);
        }
        if (this.k) {
            this.mTvSubDesc.setVisibility(0);
        } else {
            this.mTvSubDesc.setVisibility(8);
        }
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_wifi_choose_ui);
        y();
        STAT.c.b(this.aj, 0);
        TitleBarUtil.a((int) this.af.getResources().getDimension(R.dimen.title_bar_top_padding), this.mTitleBar);
        this.d = (WifiManager) context.getSystemService("wifi");
        this.mNextBtn.setText(R.string.next_button);
        this.mDeviceInfo.setText(String.format(context.getString(R.string.kuailian_main_title_2), new Object[0]));
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseWifiStep.this.a();
            }
        });
        this.mResizeLayout.setResizeListener(new ResizeLayout.ResizeListener() {
            public void a(int i, int i2) {
                Log.e("aaaa", "Resize");
            }
        });
        this.mDeviceIcon.setImageResource(R.drawable.kuailian_wifi_icon);
        this.mWifiChooser.setText("");
        this.mWifiChooser.setEnabled(false);
        this.mScanWifi.setVisibility(0);
        this.mNextBtn.setEnabled(false);
        this.mNextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ChooseWifiStep.this.mLicenseCheckBox.getVisibility() != 0 || ChooseWifiStep.this.mLicenseCheckBox.isChecked() || !(ChooseWifiStep.this.af instanceof Activity)) {
                    ChooseWifiStep.this.q();
                } else {
                    new MLAlertDialog.Builder((Activity) ChooseWifiStep.this.af).a((CharSequence) ChooseWifiStep.this.af.getString(R.string.license_title)).a(ChooseWifiStep.this.a(ChooseWifiStep.this.aj)).b((CharSequence) ChooseWifiStep.this.af.getString(R.string.license_negative_btn), (DialogInterface.OnClickListener) null).a((CharSequence) ChooseWifiStep.this.af.getString(R.string.license_positive_btn), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ChooseWifiStep.this.q();
                        }
                    }).d();
                }
            }
        });
        this.mWifiPassContainer.setVisibility(8);
        this.mCheckbox.setVisibility(8);
        if (!ApiHelper.i) {
            this.mCheckbox.setPadding(this.mCheckbox.getPaddingLeft() + DisplayUtils.a(18.0f), this.mCheckbox.getPaddingTop(), this.mCheckbox.getPaddingRight(), this.mCheckbox.getPaddingBottom());
        }
        this.mPasswordEditor.setText("");
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        ScanResult scanResult = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
        if (scanResult != null) {
            MobclickAgent.a(this.af, DeviceFactory.a(scanResult), "ap_input_wlan");
        }
        if (str != null && DeviceFactory.c(str)) {
            this.k = true;
        }
        c(str);
        p();
        this.mPasswordEditor.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                ChooseWifiStep.this.o();
                boolean unused = ChooseWifiStep.this.l = false;
            }
        });
        this.mScanResultList.setScrollView(this.mScrollWifiList);
        this.mScanResultList.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                try {
                    ChooseWifiStep.this.d.startScan();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                boolean unused = ChooseWifiStep.this.j = true;
            }
        });
        this.mWifiChooser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.e("adddevice_wlan_click.AP", ChooseWifiStep.this.aj);
                ChooseWifiStep.this.mPasswordEditorAbove.clearFocus();
                ChooseWifiStep.this.i();
            }
        });
        this.mPasswordToggle.setEnabled(false);
        this.mPasswordToggle.setSelected(true);
        this.mPasswordToggle.setText(R.string.smart_config_hide_passwd);
        this.mPasswordToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int selectionStart = ChooseWifiStep.this.mPasswordEditor.getSelectionStart();
                if (!ChooseWifiStep.this.mPasswordToggle.isSelected()) {
                    ChooseWifiStep.this.mPasswordEditor.setInputType(144);
                    ChooseWifiStep.this.mPasswordToggle.setSelected(true);
                    ChooseWifiStep.this.mPasswordToggle.setText(R.string.smart_config_hide_passwd);
                } else {
                    ChooseWifiStep.this.mPasswordEditor.setInputType(129);
                    ChooseWifiStep.this.mPasswordToggle.setSelected(false);
                    ChooseWifiStep.this.mPasswordToggle.setText(R.string.smart_config_show_passwd);
                }
                ChooseWifiStep.this.mPasswordEditor.setSelection(selectionStart);
            }
        });
        this.mPasswordEditorAbove.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return true;
                }
                new MLAlertDialog.Builder(ChooseWifiStep.this.af).b((int) R.string.smart_config_reinput_wifi_passwd).a((int) R.string.smart_config_reinput, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ChooseWifiStep.this.mPasswordEditor.setVisibility(0);
                        ChooseWifiStep.this.mPasswordEditorAbove.setVisibility(8);
                        ChooseWifiStep.this.mPasswordEditor.requestFocus();
                        ChooseWifiStep.this.mPasswordToggle.setEnabled(true);
                        ChooseWifiStep.this.mPasswordToggle.setTextColor(-11371828);
                        ChooseWifiStep.this.mPasswordToggle.setText(R.string.smart_config_hide_passwd);
                        SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
                        dialogInterface.dismiss();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).b().show();
                return true;
            }
        });
        this.mShowScanResultToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseWifiStep.this.i();
                BaseAdapter unused = ChooseWifiStep.this.b = new ConnWifiAdapter();
                ChooseWifiStep.this.mConnectList.setAdapter(ChooseWifiStep.this.b);
                BaseAdapter unused2 = ChooseWifiStep.this.c = new UnConnWifiAdapter();
                ChooseWifiStep.this.mUnConnectList.setAdapter(ChooseWifiStep.this.c);
            }
        });
        this.mShowScanResultToggle.setEnabled(false);
        this.b = new ConnWifiAdapter();
        this.mConnectList.setAdapter(this.b);
        this.c = new UnConnWifiAdapter();
        this.mUnConnectList.setAdapter(this.c);
        StatHelper.n();
        if (!h()) {
            new MLAlertDialog.Builder(this.af).b((int) R.string.open_wifi_failed).a(false).b((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ChooseWifiStep.this.a();
                }
            }).a(false).d();
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        StatClick statClick = STAT.d;
        String str = this.aj;
        int i2 = 1;
        int i3 = this.l ? 1 : 2;
        if (this.mPasswordToggle.getText().equals(this.af.getString(R.string.smart_config_hide_passwd))) {
            i2 = 2;
        }
        statClick.a(str, i3, i2);
        STAT.c.b(this.aj, this.ai);
        InputMethodManager inputMethodManager = (InputMethodManager) this.af.getSystemService("input_method");
        if (inputMethodManager.isActive() && (this.af instanceof Activity) && ((Activity) this.af).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) this.af).getCurrentFocus().getWindowToken(), 2);
        }
        String obj = this.mPasswordEditor.getText().toString();
        if (WifiSettingUtils.a(this.e) == 0) {
            b("");
        }
        if (!TextUtils.isEmpty(obj) && this.mCheckbox.isChecked()) {
            b(obj);
        }
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.c, this.e);
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.d, this.e.SSID);
        WifiAccount.WifiItem a2 = WifiAccountManager.a().a(this.e.BSSID);
        if (a2 != null && (TextUtils.equals(a2.d, obj) || TextUtils.isEmpty(obj))) {
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, a2.d);
        } else if (!TextUtils.isEmpty(obj)) {
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, obj);
        }
        D();
    }

    /* access modifiers changed from: private */
    public SpannableStringBuilder a(final String str) {
        String string = this.af.getString(R.string.license_content);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass16 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseWifiStep.this.af, GDPRLicenseActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_model", str);
                ChooseWifiStep.this.af.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#0099ff"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf > 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return spannableStringBuilder;
    }

    private void b(String str) {
        WifiAccount.WifiItem wifiItem = new WifiAccount.WifiItem();
        wifiItem.e = this.e.BSSID;
        if (wifiItem.e == null) {
            wifiItem.e = "";
        }
        wifiItem.c = this.e.SSID;
        wifiItem.f = this.e.capabilities;
        wifiItem.d = str;
        wifiItem.b = true;
        wifiItem.f11584a = this.d.getConnectionInfo().getNetworkId();
        WifiAccountManager.a().a(wifiItem);
    }

    private void c(final String str) {
        if (!ServerCompact.d(CoreApi.a().F())) {
            this.mLicenseCheckBox.setVisibility(4);
            this.mLicenseTv.setVisibility(4);
            this.mLicenseCheckBox.setChecked(true);
            return;
        }
        this.mLicenseCheckBox.setChecked(false);
        this.mLicenseCheckBox.setVisibility(0);
        String string = this.af.getString(R.string.kuailian_license);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass17 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseWifiStep.this.af, GDPRLicenseActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_model", str);
                ChooseWifiStep.this.af.startActivity(intent);
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

    public void e() {
        if (U_() != null) {
            U_().removeMessages(104);
        }
    }

    class ViewTag {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22536a;
        public ImageView b;
        public ImageView c;
        public TextView d;

        ViewTag() {
        }
    }

    /* access modifiers changed from: private */
    public void b(ScanResult scanResult) {
        STAT.d.f(this.aj, scanResult.BSSID);
        if (this.e != scanResult) {
            this.mPasswordEditor.setText("");
        }
        this.e = scanResult;
        this.mWifiChooser.setText(this.e.SSID);
        a(this.e);
        o();
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        int wifiState = this.d.getWifiState();
        if (wifiState == 3) {
            try {
                this.d.startScan();
                U_().sendEmptyMessageDelayed(104, 30000);
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } else if ((wifiState == 4 || wifiState == 1) && wifiState == 1) {
            return this.d.setWifiEnabled(true);
        }
        this.j = true;
        return true;
    }

    class ConnWifiAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        ConnWifiAdapter() {
        }

        public int getCount() {
            return ChooseWifiStep.this.f.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ChooseWifiStep.this.af).inflate(R.layout.kuailian_wifi_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f22536a = (TextView) view.findViewById(R.id.wifi_name);
                viewTag.b = (ImageView) view.findViewById(R.id.miwif_tag);
                viewTag.d = (TextView) view.findViewById(R.id.security_name);
                viewTag.c = (ImageView) view.findViewById(R.id.wifi_signal_level);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            viewTag2.f22536a.setText(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a.SSID);
            if (((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).c) {
                viewTag2.b.setVisibility(0);
            } else {
                viewTag2.b.setVisibility(8);
            }
            viewTag2.d.setText(WifiSettingUtils.a(ChooseWifiStep.this.af, (WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)));
            viewTag2.c.setImageResource(WifiSettingUtils.a(WifiSettingUtils.a(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a.level, 100)));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (i < ChooseWifiStep.this.f.size()) {
                        String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.n);
                        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a.BSSID)) {
                            new MLAlertDialog.Builder(ChooseWifiStep.this.af).b((int) R.string.kuailian_conn_error_router).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ChooseWifiStep.this.b(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a);
                                    ChooseWifiStep.this.j();
                                }
                            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).d();
                        } else if (WifiSettingUtils.a(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a) == 0) {
                            new MLAlertDialog.Builder(ChooseWifiStep.this.af).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ChooseWifiStep.this.b(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a);
                                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.p, (Object) null);
                                    ChooseWifiStep.this.j();
                                }
                            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).d();
                        } else {
                            ChooseWifiStep.this.b(((WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.f.get(i)).f22964a);
                            ChooseWifiStep.this.j();
                        }
                    }
                }
            });
            return view;
        }
    }

    class UnConnWifiAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        UnConnWifiAdapter() {
        }

        public int getCount() {
            return ChooseWifiStep.this.h.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ChooseWifiStep.this.af).inflate(R.layout.kuailian_wifi_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f22536a = (TextView) view.findViewById(R.id.wifi_name);
                viewTag.b = (ImageView) view.findViewById(R.id.miwif_tag);
                viewTag.d = (TextView) view.findViewById(R.id.security_name);
                viewTag.c = (ImageView) view.findViewById(R.id.wifi_signal_level);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            final WifiSettingUtils.KuailianScanResult kuailianScanResult = (WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.h.get(i);
            viewTag2.f22536a.setText(kuailianScanResult.f22964a.SSID);
            viewTag2.f22536a.setTextColor(ChooseWifiStep.this.af.getResources().getColor(R.color.class_text_16));
            if (kuailianScanResult.c) {
                viewTag2.b.setVisibility(0);
            } else {
                viewTag2.b.setVisibility(8);
            }
            viewTag2.c.setImageResource(WifiSettingUtils.a(WifiSettingUtils.a(kuailianScanResult.f22964a.level, 100)));
            viewTag2.d.setText(WifiSettingUtils.a(ChooseWifiStep.this.af, (WifiSettingUtils.KuailianScanResult) ChooseWifiStep.this.h.get(i)));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ScanResult scanResult = kuailianScanResult.f22964a;
                    if ((scanResult.frequency > 5000 && !ChooseWifiStep.this.k) || scanResult.capabilities.contains("WEP") || scanResult.capabilities.contains("EAP") || scanResult.capabilities.contains("WAPI-KEY") || scanResult.capabilities.contains("WAPI-PSK") || scanResult.capabilities.contains("WAPI-CERT") || scanResult.level == 0 || DeviceFactory.f(scanResult)) {
                        new MLAlertDialog.Builder(ChooseWifiStep.this.af).b((int) R.string.kuailian_unconn_reason).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) null).d();
                    } else if (WifiSettingUtils.d(kuailianScanResult.f22964a.SSID)) {
                        new MLAlertDialog.Builder(ChooseWifiStep.this.af).a((int) R.string.kuailian_contain_unsupport_char).b((int) R.string.kuailian_unsafe_wifi_content).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ChooseWifiStep.this.b(kuailianScanResult.f22964a);
                                SmartConfigDataProvider.a().b(SmartConfigDataProvider.p, (Object) null);
                                ChooseWifiStep.this.j();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).d();
                    } else {
                        new MLAlertDialog.Builder(ChooseWifiStep.this.af).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content_1).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ChooseWifiStep.this.b(kuailianScanResult.f22964a);
                                SmartConfigDataProvider.a().b(SmartConfigDataProvider.p, (Object) null);
                                ChooseWifiStep.this.j();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).d();
                    }
                }
            });
            return view;
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        if (this.mScanResultList != null && this.af != null) {
            STAT.c.c(this.aj, 0);
            this.mScanResultList.setVisibility(0);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.af, R.anim.v5_dialog_enter);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    ChooseWifiStep.this.mTopMask.setVisibility(0);
                }
            });
            this.mScanResultList.startAnimation(loadAnimation);
        }
    }

    /* access modifiers changed from: package-private */
    public void j() {
        if (this.mScanResultList != null && this.af != null) {
            STAT.c.c(this.aj, this.ai);
            this.mScanResultList.setVisibility(8);
            Animation loadAnimation = AnimationUtils.loadAnimation(this.af, R.anim.v5_dialog_exit);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    ChooseWifiStep.this.mTopMask.setVisibility(8);
                }
            });
            this.mScanResultList.startAnimation(loadAnimation);
        }
    }
}
