package com.xiaomi.smarthome.miio;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalResponse;
import com.xiaomi.miio.MiioLocalResult;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.FaqActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.device.api.LocalDeviceApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.SyncCallback;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.BtnAndProgressView;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.ExpandListView;
import com.xiaomi.smarthome.library.common.widget.WaveView;
import com.xiaomi.smarthome.miio.WifiAccount;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiSetting extends BaseWhiteActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11585a = 100;
    private static final int b = 101;
    private static final int c = 102;
    private static final int d = 103;
    private static final int e = 106;
    private static final int f = 105;
    private static final int g = 104;
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final int k = 3;
    private static final int l = 4;
    private static final int m = 0;
    private static final int n = 1;
    private static final int o = 2;
    private static final int p = 3;
    private static final int q = 4;
    private static final int r = 5;
    private static final String s = "http://v.youku.com/v_show/id_XODYwNTMxMjIw.html";
    private static final String t = "http://v.youku.com/v_show/id_XODU2NDA2MjA0.html";
    private static final String u = "http://v.youku.com/v_show/id_XODU2NDA2OTIw.html​";
    private View[] A = new View[6];
    /* access modifiers changed from: private */
    public BtnAndProgressView B;
    /* access modifiers changed from: private */
    public View C;
    /* access modifiers changed from: private */
    public List<KuailianScanResult> D = new ArrayList();
    /* access modifiers changed from: private */
    public List<KuailianScanResult> E = new ArrayList();
    /* access modifiers changed from: private */
    public List<KuailianScanResult> F = new ArrayList();
    /* access modifiers changed from: private */
    public TextView G;
    /* access modifiers changed from: private */
    public EditText H;
    /* access modifiers changed from: private */
    public EditText I;
    private CheckBox J;
    /* access modifiers changed from: private */
    public View K;
    private View L;
    /* access modifiers changed from: private */
    public CustomPullDownRefreshLinearLayout M;
    /* access modifiers changed from: private */
    public ListView N;
    /* access modifiers changed from: private */
    public ListView O;
    /* access modifiers changed from: private */
    public BaseAdapter P;
    /* access modifiers changed from: private */
    public BaseAdapter Q;
    /* access modifiers changed from: private */
    public int R = -1;
    /* access modifiers changed from: private */
    public ToggleButton S;
    /* access modifiers changed from: private */
    public boolean T = false;
    private boolean U = false;
    /* access modifiers changed from: private */
    public int V;
    /* access modifiers changed from: private */
    public String W;
    /* access modifiers changed from: private */
    public int X = -1;
    /* access modifiers changed from: private */
    public State Y;
    private Dialog Z;
    /* access modifiers changed from: private */
    public boolean aa = false;
    /* access modifiers changed from: private */
    public int ab = 0;
    /* access modifiers changed from: private */
    public boolean ac;
    private View ad;
    /* access modifiers changed from: private */
    public WaveView ae;
    private View af;
    private boolean ag = false;
    private View ah;
    private BroadcastReceiver ai = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                WifiSetting.this.aj.sendEmptyMessage(100);
            } else if (action.equals("android.net.wifi.STATE_CHANGE")) {
                Parcelable parcelableExtra = intent.getParcelableExtra(StatType.NETWORKINFO);
                if (parcelableExtra != null) {
                    Message obtainMessage = WifiSetting.this.aj.obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.obj = (NetworkInfo) parcelableExtra;
                    WifiSetting.this.aj.sendMessage(obtainMessage);
                }
            } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                WifiSetting.this.aj.sendEmptyMessage(102);
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler aj = new Handler() {
        /* JADX WARNING: Removed duplicated region for block: B:109:0x04e0 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:110:0x04e1  */
        /* JADX WARNING: Removed duplicated region for block: B:127:0x0565  */
        /* JADX WARNING: Removed duplicated region for block: B:145:0x0605  */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x021d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r20) {
            /*
                r19 = this;
                r0 = r19
                r1 = r20
                int r2 = r1.what
                r3 = 2131434013(0x7f0b1a1d, float:1.8489828E38)
                r4 = 3
                r5 = 0
                r6 = 1
                switch(r2) {
                    case 100: goto L_0x013c;
                    case 101: goto L_0x0095;
                    case 102: goto L_0x0074;
                    case 103: goto L_0x0029;
                    case 104: goto L_0x013c;
                    case 105: goto L_0x000f;
                    case 106: goto L_0x0011;
                    default: goto L_0x000f;
                }
            L_0x000f:
                goto L_0x06a8
            L_0x0011:
                java.lang.Object r1 = r1.obj
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                boolean r1 = r1.booleanValue()
                if (r1 == 0) goto L_0x0022
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r1.a((boolean) r6)
                goto L_0x06a8
            L_0x0022:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r1.a((boolean) r5)
                goto L_0x06a8
            L_0x0029:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = com.xiaomi.smarthome.miio.WifiSetting.State.INPUT
                com.xiaomi.smarthome.miio.WifiSetting.State unused = r1.Y = r2
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.EditText r1 = r1.H
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r1 = r1.G
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.view.View r1 = r1.findViewById(r3)
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.library.common.widget.BtnAndProgressView r1 = r1.B
                r1.reset()
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.library.common.widget.BtnAndProgressView r1 = r1.B
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.ToggleButton r1 = r1.S
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r2 = 2131500782(0x7f0c1eee, float:1.8625252E38)
                android.widget.Toast r1 = android.widget.Toast.makeText(r1, r2, r5)
                r1.show()
                goto L_0x06a8
            L_0x0074:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.net.wifi.WifiManager r1 = r1.w
                int r1 = r1.getWifiState()
                if (r1 != r4) goto L_0x06a8
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r1 = r1.Y
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = com.xiaomi.smarthome.miio.WifiSetting.State.SCANNING
                if (r1 != r2) goto L_0x06a8
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.net.wifi.WifiManager r1 = r1.w
                r1.startScan()
                goto L_0x06a8
            L_0x0095:
                java.lang.Object r1 = r1.obj
                android.net.NetworkInfo r1 = (android.net.NetworkInfo) r1
                android.net.NetworkInfo$DetailedState r1 = r1.getDetailedState()
                android.net.NetworkInfo$DetailedState r2 = android.net.NetworkInfo.DetailedState.OBTAINING_IPADDR
                r3 = 103(0x67, float:1.44E-43)
                if (r1 != r2) goto L_0x00b6
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = r2.Y
                com.xiaomi.smarthome.miio.WifiSetting$State r4 = com.xiaomi.smarthome.miio.WifiSetting.State.CONNECTTING
                if (r2 != r4) goto L_0x00b6
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.os.Handler r2 = r2.aj
                r2.removeMessages(r3)
            L_0x00b6:
                android.net.NetworkInfo$DetailedState r2 = android.net.NetworkInfo.DetailedState.CONNECTED
                if (r1 != r2) goto L_0x06a8
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r1 = r1.Y
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = com.xiaomi.smarthome.miio.WifiSetting.State.CONNECTTING
                if (r1 != r2) goto L_0x06a8
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.net.wifi.WifiManager r1 = r1.w
                android.net.wifi.WifiInfo r1 = r1.getConnectionInfo()
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                boolean r2 = r2.T
                if (r2 == 0) goto L_0x00eb
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r2 = r2.F
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r4 = r4.R
                java.lang.Object r2 = r2.get(r4)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f11622a
                goto L_0x00ff
            L_0x00eb:
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r2 = r2.D
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r4 = r4.R
                java.lang.Object r2 = r2.get(r4)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f11622a
            L_0x00ff:
                if (r1 == 0) goto L_0x0131
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.lang.String r1 = r1.getSSID()
                java.lang.String r2 = r2.SSID
                boolean r1 = r4.isEqualWifi(r1, r2)
                if (r1 == 0) goto L_0x0131
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r1.i()
                com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r2 = r2.X
                r1.a((int) r2)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.os.Handler r1 = r1.aj
                r1.removeMessages(r3)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r1.c()
                goto L_0x06a8
            L_0x0131:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.os.Handler r1 = r1.aj
                r1.sendEmptyMessage(r3)
                goto L_0x06a8
            L_0x013c:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                boolean r1 = r1.aa
                if (r1 == 0) goto L_0x0145
                return
            L_0x0145:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.net.wifi.WifiManager r1 = r1.w
                android.net.wifi.WifiInfo r1 = r1.getConnectionInfo()
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = r2.Y
                com.xiaomi.smarthome.miio.WifiSetting$State r7 = com.xiaomi.smarthome.miio.WifiSetting.State.SCANNING
                if (r2 == r7) goto L_0x017b
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = r2.Y
                com.xiaomi.smarthome.miio.WifiSetting$State r7 = com.xiaomi.smarthome.miio.WifiSetting.State.RECANNINGINPUT
                if (r2 != r7) goto L_0x0164
                goto L_0x017b
            L_0x0164:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r1 = r1.Y
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = com.xiaomi.smarthome.miio.WifiSetting.State.RESCANNING
                if (r1 != r2) goto L_0x06a8
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.lang.String r2 = r2.W
                r1.a((java.lang.String) r2)
                goto L_0x06a8
            L_0x017b:
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r2 = r2.R
                r8 = -1
                if (r2 == r8) goto L_0x01e5
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                boolean r2 = r2.T
                if (r2 == 0) goto L_0x01b9
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r2 = r2.R
                com.xiaomi.smarthome.miio.WifiSetting r9 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r9 = r9.F
                int r9 = r9.size()
                if (r2 >= r9) goto L_0x01b3
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r2 = r2.F
                com.xiaomi.smarthome.miio.WifiSetting r9 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r9 = r9.R
                java.lang.Object r2 = r2.get(r9)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f11622a
                goto L_0x01e6
            L_0x01b3:
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int unused = r2.R = r8
                goto L_0x01e5
            L_0x01b9:
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r2 = r2.R
                com.xiaomi.smarthome.miio.WifiSetting r9 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r9 = r9.D
                int r9 = r9.size()
                if (r2 >= r9) goto L_0x01e0
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r2 = r2.D
                com.xiaomi.smarthome.miio.WifiSetting r9 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r9 = r9.R
                java.lang.Object r2 = r2.get(r9)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f11622a
                goto L_0x01e6
            L_0x01e0:
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int unused = r2.R = r8
            L_0x01e5:
                r2 = 0
            L_0x01e6:
                com.xiaomi.smarthome.miio.WifiSetting r9 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.net.wifi.WifiManager r9 = r9.w
                java.util.List r9 = r9.getScanResults()
                com.xiaomi.smarthome.miio.WifiSetting r10 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r10 = r10.D
                r10.clear()
                com.xiaomi.smarthome.miio.WifiSetting r10 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r10 = r10.E
                r10.clear()
                com.xiaomi.smarthome.miio.WifiSetting r10 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r10 = r10.F
                r10.clear()
                java.util.HashMap r10 = new java.util.HashMap
                r10.<init>()
                java.util.HashMap r11 = new java.util.HashMap
                r11.<init>()
                r12 = 0
                r13 = 0
            L_0x0217:
                int r14 = r9.size()
                if (r12 >= r14) goto L_0x04c1
                java.lang.Object r14 = r9.get(r12)
                android.net.wifi.ScanResult r14 = (android.net.wifi.ScanResult) r14
                java.lang.String r14 = r14.SSID
                boolean r14 = android.text.TextUtils.isEmpty(r14)
                if (r14 != 0) goto L_0x04b5
                java.lang.Object r14 = r9.get(r12)
                android.net.wifi.ScanResult r14 = (android.net.wifi.ScanResult) r14
                boolean r14 = com.xiaomi.smarthome.device.DeviceFactory.e((android.net.wifi.ScanResult) r14)
                if (r14 == 0) goto L_0x0239
                goto L_0x04b5
            L_0x0239:
                com.xiaomi.smarthome.miio.WifiSetting r14 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.lang.Object r15 = r9.get(r12)
                android.net.wifi.ScanResult r15 = (android.net.wifi.ScanResult) r15
                boolean r14 = r14.isMiwifi(r15)
                com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager r15 = com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager.a()
                java.lang.Object r16 = r9.get(r12)
                r7 = r16
                android.net.wifi.ScanResult r7 = (android.net.wifi.ScanResult) r7
                java.lang.String r7 = r7.BSSID
                com.xiaomi.smarthome.miio.WifiAccount$WifiItem r7 = r15.a((java.lang.String) r7)
                if (r7 == 0) goto L_0x025b
                r7 = 1
                goto L_0x025c
            L_0x025b:
                r7 = 0
            L_0x025c:
                r15 = 5000(0x1388, float:7.006E-42)
                if (r1 == 0) goto L_0x02c6
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.lang.String r8 = r1.getSSID()
                java.lang.Object r16 = r9.get(r12)
                r4 = r16
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                boolean r3 = r3.isEqualWifi(r8, r4)
                if (r3 == 0) goto L_0x02c6
                if (r14 == 0) goto L_0x02c6
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = r3.frequency
                if (r3 <= r15) goto L_0x02c6
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.BSSID
                java.lang.String r4 = "\\:"
                java.lang.String[] r3 = r3.split(r4)
                java.lang.String r4 = "%1$s:%2$s:%3$s:%4$s:%5$s:%6$x"
                r8 = 6
                java.lang.Object[] r8 = new java.lang.Object[r8]
                r13 = r3[r5]
                r8[r5] = r13
                r13 = r3[r6]
                r8[r6] = r13
                r13 = 2
                r16 = r3[r13]
                r8[r13] = r16
                r16 = 3
                r13 = r3[r16]
                r8[r16] = r13
                r13 = 4
                r17 = r3[r13]
                r8[r13] = r17
                r13 = 5
                r3 = r3[r13]
                r5 = 16
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3, r5)
                int r3 = r3.intValue()
                int r3 = r3 - r6
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r8[r13] = r3
                java.lang.String r13 = java.lang.String.format(r4, r8)
                goto L_0x02c8
            L_0x02c6:
                r16 = 3
            L_0x02c8:
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = r3.frequency
                r4 = 100
                if (r3 > r15) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WEP"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "EAP"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WAPI-KEY"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WAPI-PSK"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WAPI-CERT"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = com.xiaomi.smarthome.miio.WifiSetting.b((android.net.wifi.ScanResult) r3)
                if (r3 == 0) goto L_0x0415
                java.lang.Object r3 = r9.get(r12)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = r3.level
                if (r3 != 0) goto L_0x033c
                goto L_0x0415
            L_0x033c:
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r3 = new com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult
                com.xiaomi.smarthome.miio.WifiSetting r5 = com.xiaomi.smarthome.miio.WifiSetting.this
                r3.<init>()
                r3.c = r14
                r3.b = r7
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                r3.f11622a = r5
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                boolean r5 = r10.containsKey(r5)
                if (r5 == 0) goto L_0x03ff
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.level
                int r5 = com.xiaomi.smarthome.miio.WifiSetting.calculateSignalLevel(r5, r4)
                java.lang.Object r7 = r9.get(r12)
                android.net.wifi.ScanResult r7 = (android.net.wifi.ScanResult) r7
                java.lang.String r7 = r7.SSID
                java.lang.Object r7 = r10.get(r7)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r7 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r7
                android.net.wifi.ScanResult r7 = r7.f11622a
                int r7 = r7.level
                int r4 = com.xiaomi.smarthome.miio.WifiSetting.calculateSignalLevel(r7, r4)
                if (r5 <= r4) goto L_0x03be
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r10.get(r4)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r4 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r4
                android.net.wifi.ScanResult r5 = r4.f11622a
                java.lang.String r5 = r5.SSID
                r10.remove(r5)
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                r10.put(r5, r3)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                r3.remove(r4)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.F
                r3.remove(r4)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.F
                r3.add(r4)
                goto L_0x04b7
            L_0x03be:
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r10.get(r4)
                r3.remove(r4)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.F
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r10.get(r4)
                r3.remove(r4)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.F
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r10.get(r4)
                r3.add(r4)
                goto L_0x04b7
            L_0x03ff:
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r4 = r4.D
                r4.add(r3)
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                r10.put(r4, r3)
                goto L_0x04b7
            L_0x0415:
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r3 = new com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult
                com.xiaomi.smarthome.miio.WifiSetting r5 = com.xiaomi.smarthome.miio.WifiSetting.this
                r3.<init>()
                r3.c = r14
                r3.b = r7
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                r3.f11622a = r5
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.frequency
                if (r5 <= r15) goto L_0x043b
                com.xiaomi.smarthome.miio.WifiSetting r5 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r5 = r5.E
                r5.add(r3)
            L_0x043b:
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                boolean r5 = r11.containsKey(r5)
                if (r5 == 0) goto L_0x04a0
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.level
                int r5 = com.xiaomi.smarthome.miio.WifiSetting.calculateSignalLevel(r5, r4)
                java.lang.Object r7 = r9.get(r12)
                android.net.wifi.ScanResult r7 = (android.net.wifi.ScanResult) r7
                java.lang.String r7 = r7.SSID
                java.lang.Object r7 = r11.get(r7)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r7 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r7
                android.net.wifi.ScanResult r7 = r7.f11622a
                int r7 = r7.level
                int r4 = com.xiaomi.smarthome.miio.WifiSetting.calculateSignalLevel(r7, r4)
                if (r5 <= r4) goto L_0x04b7
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r11.get(r4)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r4 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r4
                android.net.wifi.ScanResult r5 = r3.f11622a
                java.lang.String r5 = r5.SSID
                r10.remove(r5)
                java.lang.Object r5 = r9.get(r12)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                r10.put(r5, r3)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.F
                r3.remove(r4)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.F
                r3.add(r4)
                goto L_0x04b7
            L_0x04a0:
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r4 = r4.F
                r4.add(r3)
                java.lang.Object r4 = r9.get(r12)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                r11.put(r4, r3)
                goto L_0x04b7
            L_0x04b5:
                r16 = 3
            L_0x04b7:
                int r12 = r12 + 1
                r3 = 2131434013(0x7f0b1a1d, float:1.8489828E38)
                r4 = 3
                r5 = 0
                r8 = -1
                goto L_0x0217
            L_0x04c1:
                com.xiaomi.smarthome.miio.WifiSetting$2$1 r3 = new com.xiaomi.smarthome.miio.WifiSetting$2$1
                r3.<init>()
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r4 = r4.D
                java.util.Collections.sort(r4, r3)
                com.xiaomi.smarthome.miio.WifiSetting r4 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r4 = r4.F
                java.util.Collections.sort(r4, r3)
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                boolean r3 = r3.checkWifiList()
                if (r3 == 0) goto L_0x04e1
                return
            L_0x04e1:
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r3 = r3.R
                r4 = 2131500781(0x7f0c1eed, float:1.862525E38)
                r5 = -1
                if (r3 == r5) goto L_0x0576
                if (r2 == 0) goto L_0x0561
                r1 = 0
            L_0x04f0:
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x0561
                java.lang.String r3 = r2.BSSID
                com.xiaomi.smarthome.miio.WifiSetting r5 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r5 = r5.D
                java.lang.Object r5 = r5.get(r1)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r5 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r5
                android.net.wifi.ScanResult r5 = r5.f11622a
                java.lang.String r5 = r5.BSSID
                boolean r3 = r3.equalsIgnoreCase(r5)
                if (r3 == 0) goto L_0x0535
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int unused = r2.R = r1
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r2 = r2.G
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                java.lang.Object r1 = r3.get(r1)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r1 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r1
                android.net.wifi.ScanResult r1 = r1.f11622a
                java.lang.String r1 = r1.SSID
                r2.setText(r1)
            L_0x0532:
                r18 = 1
                goto L_0x0563
            L_0x0535:
                if (r13 == 0) goto L_0x055e
                java.lang.String r3 = r2.BSSID
                boolean r3 = r3.equalsIgnoreCase(r13)
                if (r3 == 0) goto L_0x055e
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int unused = r2.R = r1
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r2 = r2.G
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                java.lang.Object r1 = r3.get(r1)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r1 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r1
                android.net.wifi.ScanResult r1 = r1.f11622a
                java.lang.String r1 = r1.SSID
                r2.setText(r1)
                goto L_0x0532
            L_0x055e:
                int r1 = r1 + 1
                goto L_0x04f0
            L_0x0561:
                r18 = 0
            L_0x0563:
                if (r18 != 0) goto L_0x0614
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r2 = -1
                int unused = r1.R = r2
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r1 = r1.G
                r1.setText(r4)
                goto L_0x0614
            L_0x0576:
                if (r1 == 0) goto L_0x0601
                java.lang.String r2 = r1.getBSSID()
                if (r2 == 0) goto L_0x0601
                r2 = 0
            L_0x057f:
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                int r3 = r3.size()
                if (r2 >= r3) goto L_0x0601
                java.lang.String r3 = r1.getBSSID()
                com.xiaomi.smarthome.miio.WifiSetting r5 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r5 = r5.D
                java.lang.Object r5 = r5.get(r2)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r5 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r5
                android.net.wifi.ScanResult r5 = r5.f11622a
                java.lang.String r5 = r5.BSSID
                boolean r3 = r3.equalsIgnoreCase(r5)
                if (r3 == 0) goto L_0x05c6
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                int unused = r1.R = r2
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r1 = r1.G
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                java.lang.Object r2 = r3.get(r2)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f11622a
                java.lang.String r2 = r2.SSID
                r1.setText(r2)
            L_0x05c3:
                r18 = 1
                goto L_0x0603
            L_0x05c6:
                if (r13 == 0) goto L_0x05fd
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                java.lang.Object r3 = r3.get(r2)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r3 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r3
                android.net.wifi.ScanResult r3 = r3.f11622a
                java.lang.String r3 = r3.BSSID
                boolean r3 = r3.equalsIgnoreCase(r13)
                if (r3 == 0) goto L_0x05fd
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                int unused = r1.R = r2
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r1 = r1.G
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r3 = r3.D
                java.lang.Object r2 = r3.get(r2)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f11622a
                java.lang.String r2 = r2.SSID
                r1.setText(r2)
                goto L_0x05c3
            L_0x05fd:
                int r2 = r2 + 1
                goto L_0x057f
            L_0x0601:
                r18 = 0
            L_0x0603:
                if (r18 != 0) goto L_0x0614
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r2 = -1
                int unused = r1.R = r2
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r1 = r1.G
                r1.setText(r4)
            L_0x0614:
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.view.View r1 = r1.K
                r2 = 8
                r1.setVisibility(r2)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting$State r2 = com.xiaomi.smarthome.miio.WifiSetting.State.INPUT
                com.xiaomi.smarthome.miio.WifiSetting.State unused = r1.Y = r2
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r2 = r2.R
                r3 = -1
                if (r2 != r3) goto L_0x0633
                r7 = 0
                goto L_0x0647
            L_0x0633:
                com.xiaomi.smarthome.miio.WifiSetting r2 = com.xiaomi.smarthome.miio.WifiSetting.this
                java.util.List r2 = r2.D
                com.xiaomi.smarthome.miio.WifiSetting r3 = com.xiaomi.smarthome.miio.WifiSetting.this
                int r3 = r3.R
                java.lang.Object r2 = r2.get(r3)
                com.xiaomi.smarthome.miio.WifiSetting$KuailianScanResult r2 = (com.xiaomi.smarthome.miio.WifiSetting.KuailianScanResult) r2
                android.net.wifi.ScanResult r7 = r2.f11622a
            L_0x0647:
                r1.a((android.net.wifi.ScanResult) r7)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r1.g()
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.EditText r1 = r1.H
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.TextView r1 = r1.G
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                r2 = 2131434013(0x7f0b1a1d, float:1.8489828E38)
                android.view.View r1 = r1.findViewById(r2)
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.ToggleButton r1 = r1.S
                r1.setEnabled(r6)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.os.Handler r1 = r1.aj
                r2 = 104(0x68, float:1.46E-43)
                r1.removeMessages(r2)
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout r1 = r1.M
                int r1 = r1.getVisibility()
                if (r1 != 0) goto L_0x06a8
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.BaseAdapter r1 = r1.P
                r1.notifyDataSetChanged()
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                android.widget.BaseAdapter r1 = r1.Q
                r1.notifyDataSetChanged()
                com.xiaomi.smarthome.miio.WifiSetting r1 = com.xiaomi.smarthome.miio.WifiSetting.this
                com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout r1 = r1.M
                r1.postRefresh()
            L_0x06a8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.WifiSetting.AnonymousClass2.handleMessage(android.os.Message):void");
        }
    };
    String mDeviceModel;
    ScanResult mKuailianScanResult;
    /* access modifiers changed from: private */
    public int v;
    /* access modifiers changed from: private */
    public WifiManager w;
    /* access modifiers changed from: private */
    public TextView x;
    /* access modifiers changed from: private */
    public TextView y;
    private ImageView z;

    enum PskType {
        UNKNOWN,
        WPA,
        WPA2,
        WPA_WPA2
    }

    enum State {
        SCANNING,
        INPUT,
        RECANNINGINPUT,
        RESCANNING,
        CONNECTTING,
        CONNECTTING_AP
    }

    enum TKIPType {
        TKIP_CCMP,
        TKIP,
        CCMP,
        NONE
    }

    public static int calculateSignalLevel(int i2, int i3) {
        if (i2 <= -100) {
            return 0;
        }
        if (i2 >= -55) {
            return 100;
        }
        return (int) ((((float) (i2 - -100)) * ((float) 100)) / ((float) 45));
    }

    public int getSignalLevel(int i2) {
        return i2 > 80 ? R.drawable.wifi_strength_5 : i2 > 60 ? R.drawable.wifi_strength_4 : i2 > 40 ? R.drawable.wifi_strength_3 : i2 > 20 ? R.drawable.wifi_strength_2 : R.drawable.wifi_strength_1;
    }

    class KuailianScanResult {

        /* renamed from: a  reason: collision with root package name */
        public ScanResult f11622a;
        public boolean b;
        public boolean c;

        KuailianScanResult() {
        }
    }

    public void onBackPressed() {
        this.aj.removeMessages(104);
        this.aj.removeMessages(103);
        if (this.Y == State.INPUT && this.M.getVisibility() == 0) {
            hideWifiList();
        } else if (this.Y == State.CONNECTTING) {
            this.Y = State.INPUT;
            b();
        } else if (this.Y == State.INPUT) {
            goBack(false, false);
        } else {
            goBack(false, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void goBack(boolean z2, boolean z3) {
        stop();
        if (z3) {
            WifiAccountManager.a().b(WifiUtil.b(this));
        }
        setResult(z2 ? -1 : 0, getIntent());
        finish();
    }

    public void onDeviceConnectionSuccess() {
        Message obtainMessage = this.aj.obtainMessage();
        obtainMessage.what = 106;
        obtainMessage.obj = true;
        this.aj.sendMessage(obtainMessage);
    }

    public void onDeviceConnectionSuccessBind() {
        e();
    }

    public void onDeviceConnectionError() {
        Message obtainMessage = this.aj.obtainMessage();
        obtainMessage.what = 106;
        obtainMessage.obj = false;
        this.aj.sendMessage(obtainMessage);
    }

    public void onDeviceFindDevices(int i2) {
        this.ab = i2;
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        this.aj.removeMessages(103);
        KuailianManager.a().b();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aa = false;
        setContentView(R.layout.wifi_setting_layout);
        this.ag = getIntent().getBooleanExtra("gotoBindView", false);
        this.w = (WifiManager) getSystemService("wifi");
        registerReceiver(this.ai, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        registerReceiver(this.ai, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        registerReceiver(this.ai, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        if (!(getIntent() == null || getIntent().getParcelableExtra("scanResult") == null)) {
            this.mKuailianScanResult = (ScanResult) getIntent().getParcelableExtra("scanResult");
        }
        if (getIntent() != null) {
            this.mDeviceModel = getIntent().getStringExtra("model");
        }
        try {
            this.U = false;
        } catch (Exception unused) {
        }
        initViews();
        if (this.ag) {
            b();
        } else {
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (CoreApi.a().q()) {
            return;
        }
        if (this.Z == null) {
            this.Z = SHApplication.showLoginDialog(this, true);
            this.Z.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    WifiSetting.this.finish();
                }
            });
        } else if (!this.Z.isShowing()) {
            this.Z.show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.ai);
        this.aa = true;
    }

    /* access modifiers changed from: private */
    public void a(int i2, boolean z2) {
        for (int i3 = 0; i3 < 6; i3++) {
            if (i3 == i2) {
                if (this.A[i3] != null) {
                    this.A[i3].setVisibility(0);
                    if (z2) {
                        this.A[i3].startAnimation(AnimationUtils.loadAnimation(this, R.anim.show));
                    }
                }
            } else if (this.A[i3] != null) {
                if (z2 && this.A[i3].getVisibility() == 0) {
                    this.A[i3].startAnimation(AnimationUtils.loadAnimation(this, R.anim.disappear));
                }
                this.A[i3].setVisibility(8);
            }
        }
    }

    private void a() {
        String str;
        a(0, false);
        this.v = 0;
        if (this.mDeviceModel == null) {
            str = getString(R.string.terminal_feedback);
        } else {
            Device k2 = DeviceFactory.k(this.mDeviceModel);
            if (k2 != null) {
                str = k2.name;
            } else {
                str = SHApplication.getAppContext().getString(R.string.other_device);
            }
        }
        this.x.setText(String.format(getString(R.string.kuailian_main_title_1), new Object[]{str}));
        this.y.setText(R.string.kuailian_sub_main_title_1);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_fast);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                WifiSetting.this.C.startAnimation(animation);
            }
        });
        this.C.startAnimation(loadAnimation);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    NetworkInterface networkInterface = null;
                    while (networkInterfaces.hasMoreElements()) {
                        networkInterface = networkInterfaces.nextElement();
                        if (networkInterface.getName().contains("wlan0")) {
                            break;
                        }
                    }
                    if (networkInterface.supportsMulticast()) {
                        Log.e("Support", "Support Multicast");
                    }
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    String.format("%02x:%02x:%02x:%02x:%02x:%02x", new Object[]{Byte.valueOf(hardwareAddress[0]), Byte.valueOf(hardwareAddress[1]), Byte.valueOf(hardwareAddress[2]), Byte.valueOf(hardwareAddress[3]), Byte.valueOf(hardwareAddress[4]), Byte.valueOf(hardwareAddress[5])});
                    networkInterface.getInetAddresses();
                    DatagramSocket datagramSocket = new DatagramSocket();
                    datagramSocket.setReuseAddress(true);
                    byte[] bArr = {1, 1, 1};
                    DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, new InetSocketAddress("192.168.1.1", 10000));
                    WifiSetting.setSocketNetworkInterface(datagramSocket, networkInterface);
                    datagramSocket.send(datagramPacket);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException unused) {
                }
            }
        }).start();
        startConnectionAsso();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.V = this.C.getWidth();
        a(1, false);
        this.v = 1;
        this.x.setText(String.format(getString(R.string.kuailian_main_title_2), new Object[0]));
        this.y.setText(R.string.kuailian_sub_main_title_2);
        this.B.reset();
        checkWifiState();
        this.G.setText("");
        this.K.setVisibility(0);
        this.B.setEnabled(false);
        this.L.setVisibility(8);
        this.J.setVisibility(8);
        this.B.setVisibility(0);
        this.H.setText("");
        this.H.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                WifiSetting.this.g();
            }
        });
        this.S.setChecked(false);
    }

    /* access modifiers changed from: private */
    public void c() {
        a(5, true);
        this.x.setText(String.format(getString(R.string.kuailian_network_test), new Object[0]));
        this.y.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.B.switchToProgressBar();
    }

    private void e() {
        a(3, false);
        this.x.setText(String.format(getString(R.string.kuailian_main_title_4), new Object[0]));
        ((AnimationDrawable) findViewById(R.id.no_device_title).getBackground()).start();
        this.y.setText(R.string.kuailian_sub_main_title_4);
        this.B.switchBack();
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        a(4, false);
        findViewById(R.id.finish_title).setVisibility(4);
        this.B.setVisibility(8);
        this.B.reset();
        if (z2) {
            this.x.setText(String.format(getString(R.string.kuailian_success), new Object[0]));
            this.y.setVisibility(8);
            ((ImageView) findViewById(R.id.connection_success_icon)).setImageResource(R.drawable.kuailian_success_icon);
            findViewById(R.id.finish_error_btn_container).setVisibility(8);
            findViewById(R.id.finish_success_btn).setVisibility(0);
            findViewById(R.id.finish_success_btn).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    WifiSetting.this.goBack(false, false);
                }
            });
            return;
        }
        this.x.setText(String.format(getString(R.string.kuailian_failed), new Object[0]));
        this.y.setVisibility(0);
        this.y.setText(R.string.kuailian_get_error_help);
        WifiAccountManager.a().b(WifiUtil.b(this));
        ((ImageView) findViewById(R.id.connection_success_icon)).setImageResource(R.drawable.kuailian_failed_icon);
        this.y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = null;
                if (WifiSetting.this.mKuailianScanResult != null) {
                    String a2 = DeviceFactory.a(WifiSetting.this.mKuailianScanResult);
                    if (a2.equals("yunyi.camera.v1")) {
                        str = WifiSetting.t;
                    } else if (a2.equals("chuangmi.plug.v1") || a2.equals("chuangmi.plug.v2")) {
                        str = WifiSetting.u;
                    } else if (a2.equals("zhimi.airpurifier.v1") || a2.equals("zhimi.airpurifier.v2") || a2.equals("zhimi.airpurifier.v3")) {
                        str = WifiSetting.s;
                    }
                } else if (WifiSetting.this.mDeviceModel != null) {
                    if (WifiSetting.this.mDeviceModel.equals("yunyi.camera.v1")) {
                        str = WifiSetting.t;
                    } else if (WifiSetting.this.mDeviceModel.equals("chuangmi.plug.v1") || WifiSetting.this.mDeviceModel.equals("chuangmi.plug.v2")) {
                        str = WifiSetting.u;
                    } else if (WifiSetting.this.mDeviceModel.equals("zhimi.airpurifier.v1") || WifiSetting.this.mDeviceModel.equals("zhimi.airpurifier.v2") || WifiSetting.this.mDeviceModel.equals("zhimi.airpurifier.v3")) {
                        str = WifiSetting.s;
                    }
                }
                if (str != null) {
                    WifiSetting.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return;
                }
                WifiSetting.this.startActivity(new Intent(WifiSetting.this, FaqActivity.class));
            }
        });
        findViewById(R.id.finish_error_btn_container).setVisibility(0);
        findViewById(R.id.finish_success_btn).setVisibility(8);
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiSetting.this.goBack(false, false);
            }
        });
        findViewById(R.id.retry_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiSetting.this.b();
            }
        });
    }

    private void f() {
        a(4, false);
        final TextView textView = (TextView) findViewById(R.id.finish_title);
        textView.setVisibility(0);
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(getString(R.string.kuailian_still_connect));
        valueOf.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(WifiSetting.this.getResources().getColor(R.color.white_60_transparent));
                textPaint.setUnderlineText(true);
            }

            public void onClick(View view) {
                textView.setVisibility(8);
                WifiSetting.this.d();
            }
        }, 0, valueOf.length(), 33);
        textView.setHighlightColor(0);
        textView.setText(valueOf);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.B.setVisibility(8);
        this.x.setText(R.string.kuailian_network_error);
        this.y.setVisibility(0);
        this.y.setText(R.string.kuailian_network_slow);
        WifiAccountManager.a().b(WifiUtil.b(this));
        ((ImageView) findViewById(R.id.connection_success_icon)).setImageResource(R.drawable.kuailian_failed_icon);
        findViewById(R.id.finish_error_btn_container).setVisibility(0);
        findViewById(R.id.finish_success_btn).setVisibility(8);
        ((TextView) findViewById(R.id.cancel_btn)).setText(R.string.kuailian_cancel);
        ((TextView) findViewById(R.id.retry_btn)).setText(R.string.kuailian_retry_network);
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiSetting.this.goBack(false, false);
            }
        });
        findViewById(R.id.retry_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiSetting.this.b();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void checkOkBtn() {
        if (this.ab > 0) {
            this.y.setText(R.string.kuailian_sub_main_device_count);
            this.y.setOnClickListener((View.OnClickListener) null);
            TextView textView = (TextView) findViewById(R.id.finish_title);
            textView.setVisibility(0);
            SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(getResources().getQuantityString(R.plurals.kuailian_sub_main_device_finish, this.ab, new Object[]{Integer.valueOf(this.ab)}));
            valueOf.setSpan(new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(WifiSetting.this.getResources().getColor(R.color.white_60_transparent));
                    textPaint.setUnderlineText(true);
                }

                public void onClick(View view) {
                    WifiSetting.this.goBack(false, false);
                }
            }, 0, valueOf.length(), 33);
            textView.setHighlightColor(0);
            textView.setText(valueOf);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.x = (TextView) findViewById(R.id.main_title);
        this.y = (TextView) findViewById(R.id.main_sub_title);
        this.B = (BtnAndProgressView) findViewById(R.id.next_btn);
        this.B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (WifiSetting.this.v == 0) {
                    WifiSetting.this.b();
                } else if (WifiSetting.this.v == 1) {
                    if (WifiSetting.this.ac) {
                        String unused = WifiSetting.this.W = WifiSetting.this.H.getText().toString();
                    }
                    WifiSetting.this.a(WifiSetting.this.W);
                    WifiSetting.this.B.showProgressBar();
                } else {
                    int unused2 = WifiSetting.this.v;
                }
            }
        });
        this.B.setListener(new BtnAndProgressView.ProgressCallBack() {
            public void c() {
                WifiSetting.this.y.setText(R.string.kuailian_sub_main_title_3_2);
                WifiSetting.this.checkOkBtn();
            }

            public void d() {
                WifiSetting.this.y.setText(R.string.kuailian_sub_main_title_3_3);
                WifiSetting.this.checkOkBtn();
            }

            public void b() {
                WifiSetting.this.y.setText(R.string.kuailian_sub_main_title_3_1);
                WifiSetting.this.checkOkBtn();
            }

            public void e() {
                WifiSetting.this.y.setText(R.string.kuailian_sub_main_title_3_4);
                WifiSetting.this.checkOkBtn();
            }

            public void g() {
                WifiSetting.this.a(false);
            }

            public void f() {
                Message obtainMessage = WifiSetting.this.aj.obtainMessage();
                obtainMessage.what = 106;
                if (WifiSetting.this.ab > 0) {
                    obtainMessage.obj = true;
                    WifiSetting.this.aj.sendMessage(obtainMessage);
                    return;
                }
                obtainMessage.obj = false;
                WifiSetting.this.aj.sendMessage(obtainMessage);
            }

            public void a() {
                WifiSetting.this.a(2, true);
                int unused = WifiSetting.this.v = 2;
                if (WifiSetting.this.mDeviceModel == null) {
                    WifiSetting.this.x.setText(String.format(WifiSetting.this.getString(R.string.kuailian_main_title_3), new Object[]{WifiSetting.this.getString(R.string.terminal_feedback)}));
                } else {
                    Device k = DeviceFactory.k(WifiSetting.this.mDeviceModel);
                    if (k != null) {
                        WifiSetting.this.x.setText(String.format(WifiSetting.this.getString(R.string.kuailian_main_title_3), new Object[]{k.name}));
                    } else {
                        WifiSetting.this.x.setText(String.format(WifiSetting.this.getString(R.string.kuailian_main_title_3), new Object[]{SHApplication.getAppContext().getString(R.string.other_device)}));
                    }
                }
                int a2 = DisplayUtils.a(220.0f);
                WifiSetting.this.ae.start((WifiSetting.this.V / 2) + ((int) ((((((float) a2) / 2.0f) - (((float) WifiSetting.this.V) / 2.0f)) * 3.0f) / 11.0f)), a2 / 2, 16777215);
                WifiSetting.this.startConnection();
            }
        });
        this.A[0] = findViewById(R.id.first_step);
        this.C = findViewById(R.id.first_anim_view);
        this.A[1] = findViewById(R.id.second_step);
        this.S = (ToggleButton) findViewById(R.id.wifi_password_toggle);
        this.H = (EditText) findViewById(R.id.wifi_password_editor);
        this.L = findViewById(R.id.wifi_setting_pass_container);
        this.M = (CustomPullDownRefreshLinearLayout) findViewById(R.id.wifi_refresh_container);
        this.M.setScrollView((ScrollView) findViewById(R.id.wifi_list_scroll_view));
        this.M.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                WifiSetting.this.w.startScan();
                State unused = WifiSetting.this.Y = State.RECANNINGINPUT;
            }
        });
        this.N = (ExpandListView) findViewById(R.id.conn_wifi_list);
        this.O = (ExpandListView) findViewById(R.id.unconn_wifi_list);
        ScanResult scanResult = this.mKuailianScanResult;
        this.I = (EditText) findViewById(R.id.wifi_password_editor_above);
        this.H.setVisibility(8);
        if (this.mKuailianScanResult != null) {
            this.mDeviceModel = DeviceFactory.a(this.mKuailianScanResult);
        }
        this.K = findViewById(R.id.searching_text);
        this.S.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (WifiSetting.this.ac) {
                    int selectionStart = WifiSetting.this.H.getSelectionStart();
                    if (z) {
                        WifiSetting.this.H.setInputType(144);
                    } else {
                        WifiSetting.this.H.setInputType(129);
                    }
                    WifiSetting.this.H.setSelection(selectionStart);
                }
            }
        });
        this.S.setChecked(false);
        this.H.setInputType(129);
        this.I.setInputType(129);
        this.I.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    boolean unused = WifiSetting.this.ac = true;
                    WifiSetting.this.H.setVisibility(0);
                    WifiSetting.this.I.setVisibility(8);
                    WifiSetting.this.H.requestFocus();
                    WifiSetting.this.S.setEnabled(true);
                }
            }
        });
        this.G = (TextView) findViewById(R.id.login_wifi_ssid_chooser);
        this.G.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiSetting.this.showWifiList();
                BaseAdapter unused = WifiSetting.this.P = new ConnWifiAdapter();
                WifiSetting.this.N.setAdapter(WifiSetting.this.P);
                BaseAdapter unused2 = WifiSetting.this.Q = new UnConnWifiAdapter();
                WifiSetting.this.O.setAdapter(WifiSetting.this.Q);
            }
        });
        findViewById(R.id.wifi_ssid_toggle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiSetting.this.showWifiList();
                BaseAdapter unused = WifiSetting.this.P = new ConnWifiAdapter();
                WifiSetting.this.N.setAdapter(WifiSetting.this.P);
                BaseAdapter unused2 = WifiSetting.this.Q = new UnConnWifiAdapter();
                WifiSetting.this.O.setAdapter(WifiSetting.this.Q);
            }
        });
        findViewById(R.id.wifi_ssid_toggle).setEnabled(false);
        this.J = (CheckBox) findViewById(R.id.check_box_save_passwd);
        this.J.setChecked(true);
        this.A[5] = findViewById(R.id.network_test_step);
        this.A[2] = findViewById(R.id.third_step);
        this.ae = (WaveView) findViewById(R.id.wave_icon);
        this.A[3] = findViewById(R.id.fouth_step);
        this.A[4] = findViewById(R.id.final_step);
    }

    /* access modifiers changed from: package-private */
    public boolean checkWifiList() {
        if (this.D.size() != 0) {
            return false;
        }
        new MLAlertDialog.Builder(this).b((int) R.string.get_wifi_scan_result_error).a((int) R.string.wifi_rescan_wifi, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WifiSetting.this.K.setVisibility(0);
                WifiSetting.this.w.startScan();
                WifiSetting.this.H.setEnabled(false);
                WifiSetting.this.G.setEnabled(false);
                WifiSetting.this.B.setEnabled(false);
                WifiSetting.this.S.setEnabled(false);
                State unused = WifiSetting.this.Y = State.SCANNING;
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WifiSetting.this.goBack(false, true);
            }
        }).a(false).d();
        this.Y = State.INPUT;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void checkWifiState() {
        int wifiState = this.w.getWifiState();
        if (wifiState == 3) {
            this.w.startScan();
            this.aj.sendEmptyMessageDelayed(104, 10000);
        } else if ((wifiState == 4 || wifiState == 1) && wifiState == 1) {
            this.w.setWifiEnabled(true);
        }
        this.Y = State.SCANNING;
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.R != -1 && this.Y == State.INPUT && (this.L.getVisibility() == 8 || this.I.getVisibility() == 0 || !TextUtils.isEmpty(this.H.getText().toString()))) {
            this.B.setEnabled(true);
        } else {
            this.B.setEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public void a(ScanResult scanResult) {
        if (scanResult == null) {
            this.L.setVisibility(8);
            this.J.setVisibility(8);
        } else if (b(scanResult) == 0) {
            this.L.setVisibility(8);
            this.J.setVisibility(8);
        } else {
            WifiAccount.WifiItem a2 = WifiAccountManager.a().a(scanResult.BSSID);
            if (a2 != null && !TextUtils.isEmpty(a2.d)) {
                this.W = a2.d;
                b(false);
            } else if (!this.U) {
                b(true);
            } else if (!queryWifiPasswd(scanResult.SSID)) {
                b(true);
            } else {
                b(false);
            }
        }
    }

    private void b(boolean z2) {
        this.L.setVisibility(0);
        this.J.setVisibility(0);
        this.ac = z2;
        if (!z2) {
            this.I.setText("123456");
            this.I.setVisibility(0);
            this.H.setVisibility(8);
            this.S.setEnabled(false);
            return;
        }
        this.H.setVisibility(0);
        this.I.setVisibility(8);
        this.S.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        ScanResult scanResult;
        WifiConfiguration wifiConfiguration;
        int i2;
        boolean z2;
        boolean z3;
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService("input_method");
        if (inputMethodManager.isActive() && getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
        this.Y = State.CONNECTTING;
        this.H.setEnabled(false);
        this.G.setEnabled(false);
        findViewById(R.id.wifi_ssid_toggle).setEnabled(false);
        this.B.setEnabled(false);
        this.B.showProgressBar();
        this.S.setEnabled(false);
        if (this.T) {
            scanResult = this.F.get(this.R).f11622a;
        } else {
            scanResult = this.D.get(this.R).f11622a;
        }
        if (!this.w.isWifiEnabled()) {
            this.w.setWifiEnabled(true);
            this.Y = State.RESCANNING;
            return;
        }
        Iterator<WifiConfiguration> it = this.w.getConfiguredNetworks().iterator();
        while (true) {
            if (!it.hasNext()) {
                wifiConfiguration = null;
                break;
            }
            wifiConfiguration = it.next();
            if (wifiConfiguration.SSID != null && wifiConfiguration.SSID.equalsIgnoreCase(convertToQuotedString(scanResult.SSID)) && getAuthType(wifiConfiguration) == b(scanResult)) {
                break;
            }
        }
        if (this.w.getConnectionInfo() == null || this.w.getConnectionInfo().getBSSID() == null) {
            i2 = -1;
        } else {
            i2 = -1;
            for (int i3 = 0; i3 < this.E.size(); i3++) {
                if (this.w.getConnectionInfo().getBSSID().equalsIgnoreCase(this.E.get(i3).f11622a.BSSID)) {
                    i2 = i3;
                }
            }
        }
        if (wifiConfiguration != null) {
            WifiInfo connectionInfo = this.w.getConnectionInfo();
            if (connectionInfo != null && isEqualWifi(connectionInfo.getSSID(), scanResult.SSID) && connectionInfo.getNetworkId() != -1) {
                i();
                this.aj.removeMessages(103);
                c();
                return;
            } else if (Build.VERSION.SDK_INT > 10) {
                try {
                    Class<?> cls = Class.forName(WifiManager.class.getName());
                    Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls.getName());
                    Method[] methods = cls.getMethods();
                    int i4 = 0;
                    while (true) {
                        if (i4 >= methods.length) {
                            z3 = false;
                            break;
                        } else if ((methods[i4].getName().equalsIgnoreCase("connect") || methods[i4].getName().equalsIgnoreCase("connectNetwork")) && methods[i4].getParameterTypes()[0].getName().equals("int")) {
                            z3 = true;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (z3) {
                        methods[i4].setAccessible(true);
                        if (methods[i4].getName().equalsIgnoreCase("connect")) {
                            methods[i4].invoke(this.w, new Object[]{Integer.valueOf(wifiConfiguration.networkId), null});
                        } else {
                            methods[i4].invoke(this.w, new Object[]{Integer.valueOf(wifiConfiguration.networkId)});
                        }
                    }
                } catch (Exception unused) {
                }
            } else {
                this.w.enableNetwork(wifiConfiguration.networkId, true);
            }
        } else {
            WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
            a(wifiConfiguration2, scanResult, str);
            int addNetwork = this.w.addNetwork(wifiConfiguration2);
            Class<?> cls2 = Class.forName(WifiManager.class.getName());
            Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls2.getName());
            Method[] methods2 = cls2.getMethods();
            int i5 = 0;
            while (true) {
                if (i5 >= methods2.length) {
                    z2 = false;
                    break;
                } else if ((methods2[i5].getName().equalsIgnoreCase("connect") || methods2[i5].getName().equalsIgnoreCase("connectNetwork")) && methods2[i5].getParameterTypes()[0].getName().equals("int")) {
                    z2 = true;
                    break;
                } else {
                    i5++;
                }
            }
            if (z2) {
                methods2[i5].setAccessible(true);
                if (methods2[i5].getName().equalsIgnoreCase("connect")) {
                    methods2[i5].invoke(this.w, new Object[]{Integer.valueOf(addNetwork), null});
                } else {
                    methods2[i5].invoke(this.w, new Object[]{Integer.valueOf(addNetwork)});
                }
            }
        }
        if (i2 != -1) {
            this.X = this.w.getConnectionInfo().getNetworkId();
        }
        this.aj.sendEmptyMessageDelayed(103, 40000);
    }

    private boolean h() {
        boolean z2;
        this.w.setWifiEnabled(false);
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "YRCCONNECTION";
        wifiConfiguration.preSharedKey = "\"12345678\"";
        wifiConfiguration.allowedKeyManagement.set(1);
        wifiConfiguration.allowedAuthAlgorithms.set(0);
        wifiConfiguration.allowedGroupCiphers.set(3);
        wifiConfiguration.allowedPairwiseCiphers.set(2);
        try {
            Class<?> cls = Class.forName(WifiManager.class.getName());
            Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls.getName());
            Method[] methods = cls.getMethods();
            int i2 = 0;
            while (true) {
                if (i2 >= methods.length) {
                    z2 = false;
                    break;
                } else if (methods[i2].getName().equalsIgnoreCase("setWifiApEnabled")) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2) {
                methods[i2].setAccessible(true);
                if (methods[i2].getName().equalsIgnoreCase("setWifiApEnabled")) {
                    Log.e("result", "" + ((Boolean) methods[i2].invoke(this.w, new Object[]{wifiConfiguration, true})));
                    MiioLocalAPI.a("renqiao", "12345678", (String) null, "[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][WPS][ESS]", (MiioLocalResponse) new MiioLocalResponse() {
                        public void a(MiioLocalResult miioLocalResult) {
                        }
                    });
                }
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public static void setSocketNetworkInterface(DatagramSocket datagramSocket, NetworkInterface networkInterface) {
        boolean z2;
        try {
            Method[] methods = Class.forName(DatagramSocket.class.getName()).getMethods();
            int i2 = 0;
            while (true) {
                if (i2 >= methods.length) {
                    z2 = false;
                    break;
                } else if (methods[i2].getName().equalsIgnoreCase("setNetworkInterface")) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z2) {
                methods[i2].setAccessible(true);
                if (methods[i2].getName().equalsIgnoreCase("setNetworkInterface")) {
                    methods[i2].invoke(datagramSocket, new Object[]{networkInterface});
                }
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        ScanResult scanResult;
        if (this.T) {
            scanResult = this.F.get(this.R).f11622a;
        } else {
            scanResult = this.D.get(this.R).f11622a;
        }
        WifiAccount.WifiItem wifiItem = new WifiAccount.WifiItem();
        wifiItem.e = scanResult.BSSID;
        if (wifiItem.e == null) {
            wifiItem.e = "";
        }
        wifiItem.c = scanResult.SSID;
        wifiItem.f = scanResult.capabilities;
        wifiItem.d = this.W;
        wifiItem.b = true;
        wifiItem.f11584a = this.w.getConnectionInfo().getNetworkId();
        WifiAccountManager.a().a(wifiItem);
    }

    private void a(WifiConfiguration wifiConfiguration, ScanResult scanResult, String str) {
        int b2 = b(scanResult);
        wifiConfiguration.SSID = convertToQuotedString(scanResult.SSID);
        switch (b2) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                return;
            case 1:
                wifiConfiguration.hiddenSSID = true;
                String[] strArr = wifiConfiguration.wepKeys;
                strArr[0] = "\"" + str + "\"";
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.wepTxKeyIndex = 0;
                break;
            case 2:
                break;
            default:
                return;
        }
        wifiConfiguration.preSharedKey = "\"" + str + "\"";
        wifiConfiguration.allowedKeyManagement.set(1);
        TKIPType c2 = c(scanResult);
        if (c2 == TKIPType.TKIP_CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (c2 == TKIPType.TKIP) {
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (c2 == TKIPType.CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
        }
        wifiConfiguration.status = 0;
    }

    /* access modifiers changed from: private */
    public static int b(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return 1;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return 2;
        }
        if (scanResult.capabilities.contains("EAP")) {
            return 3;
        }
        return scanResult.capabilities.contains("WAPI") ? 4 : 0;
    }

    private static TKIPType c(ScanResult scanResult) {
        boolean contains = scanResult.capabilities.contains("TKIP");
        boolean contains2 = scanResult.capabilities.contains("CCMP");
        if (contains && contains2) {
            return TKIPType.TKIP_CCMP;
        }
        if (contains) {
            return TKIPType.TKIP;
        }
        if (contains2) {
            return TKIPType.CCMP;
        }
        return TKIPType.NONE;
    }

    private static PskType d(ScanResult scanResult) {
        boolean contains = scanResult.capabilities.contains("WPA-PSK");
        boolean contains2 = scanResult.capabilities.contains("WPA2-PSK");
        if (contains2 && contains) {
            return PskType.WPA_WPA2;
        }
        if (contains2) {
            return PskType.WPA2;
        }
        if (contains) {
            return PskType.WPA;
        }
        return PskType.UNKNOWN;
    }

    /* access modifiers changed from: package-private */
    public String convertToQuotedString(String str) {
        return "\"" + str + "\"";
    }

    /* access modifiers changed from: package-private */
    public boolean isEqualWifi(String str, String str2) {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length() - 1);
        }
        if (str2.startsWith("\"")) {
            str2 = str2.substring(1, str2.length() - 1);
        }
        return str.equalsIgnoreCase(str2);
    }

    /* access modifiers changed from: package-private */
    public boolean queryWifiPasswd(String str) {
        WifiConfiguration wifiConfiguration;
        Iterator<WifiConfiguration> it = this.w.getConfiguredNetworks().iterator();
        while (true) {
            if (!it.hasNext()) {
                wifiConfiguration = null;
                break;
            }
            wifiConfiguration = it.next();
            if (wifiConfiguration.SSID.equalsIgnoreCase(convertToQuotedString(str))) {
                break;
            }
        }
        return wifiConfiguration != null;
    }

    public static void startConnectWifi(int i2, WifiManager wifiManager) {
        if (Build.VERSION.SDK_INT > 10) {
            try {
                Class<?> cls = Class.forName(WifiManager.class.getName());
                Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls.getName());
                Method[] methods = cls.getMethods();
                boolean z2 = false;
                int i3 = 0;
                while (true) {
                    if (i3 >= methods.length) {
                        break;
                    } else if ((methods[i3].getName().equalsIgnoreCase("connect") || methods[i3].getName().equalsIgnoreCase("connectNetwork")) && methods[i3].getParameterTypes()[0].getName().equals("int")) {
                        z2 = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (z2) {
                    methods[i3].setAccessible(true);
                }
            } catch (Exception unused) {
            }
        }
    }

    public String long2Ip(long j2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j2 & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j2 >> 24) & 255)));
        return stringBuffer.toString();
    }

    public String getGatewayAddr() {
        return long2Ip((long) ((WifiManager) getSystemService("wifi")).getDhcpInfo().gateway);
    }

    public void startConnectionAsso() {
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        setMiioRouter("renqiao", "12345678");
    }

    public void setMiioRouter(String str, String str2) {
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.config_router");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DeviceTagInterface.e, str);
            jSONObject2.put("passwd", str2);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        MiioLocalAPI.a(getGatewayAddr(), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
            public void onResponse(String str) {
                LocalDeviceApi.parseRpcResponse(str, new SyncCallback<JSONObject, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        String optString = jSONObject.optString("did");
                        MiioLocalAPI.a(WifiSetting.this.getGatewayAddr(), jSONObject.toString(), Long.valueOf(optString).longValue(), jSONObject.optString("token"), (MiioLocalRpcResponse) new MiioLocalRpcResponse() {
                            public void onResponse(String str) {
                            }
                        });
                    }
                });
            }
        }, 9);
    }

    public void getConnectionState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.wifi_assoc_state");
            new JSONObject();
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        MiioLocalAPI.b(getGatewayAddr(), jSONObject.toString(), new MiioLocalRpcResponse() {
            public void onResponse(String str) {
            }
        });
    }

    public void stopConnection() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.stop_diag_mode");
            new JSONObject();
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        MiioLocalAPI.b(getGatewayAddr(), jSONObject.toString(), new MiioLocalRpcResponse() {
            public void onResponse(String str) {
            }
        });
    }

    public void startConnection() {
        WifiInfo connectionInfo;
        WifiAccount.WifiItem a2;
        int i2;
        int i3;
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager.isWifiEnabled() && (connectionInfo = wifiManager.getConnectionInfo()) != null && (a2 = WifiAccountManager.a().a(connectionInfo.getBSSID())) != null) {
            if (this.mKuailianScanResult == null) {
                try {
                    i3 = Integer.valueOf(CoreApi.a().s()).intValue();
                } catch (Exception unused) {
                    i3 = 0;
                }
                if (i3 > 0) {
                    MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, a2.e, a2.f, (long) i3, (MiioLocalResponse) new MiioLocalResponse() {
                        public void a(MiioLocalResult miioLocalResult) {
                        }
                    });
                } else {
                    MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, a2.e, a2.f, (MiioLocalResponse) new MiioLocalResponse() {
                        public void a(MiioLocalResult miioLocalResult) {
                        }
                    });
                }
            } else {
                String h2 = DeviceFactory.h(this.mKuailianScanResult);
                try {
                    i2 = Integer.valueOf(CoreApi.a().s()).intValue();
                } catch (Exception unused2) {
                    i2 = 0;
                }
                if (i2 > 0) {
                    MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, a2.e, a2.f, h2, (long) i2, (MiioLocalResponse) new MiioLocalResponse() {
                        public void a(MiioLocalResult miioLocalResult) {
                        }
                    });
                } else {
                    MiioLocalAPI.a(a2.c, a2.d == null ? "" : a2.d, a2.e, a2.f, h2, (MiioLocalResponse) new MiioLocalResponse() {
                        public void a(MiioLocalResult miioLocalResult) {
                        }
                    });
                }
            }
            WifiDeviceFinder.m.clear();
        }
    }

    public boolean isMiwifi(ScanResult scanResult) {
        try {
            Field declaredField = Class.forName(ScanResult.class.getName()).getDeclaredField("isXiaomiRouter");
            if (declaredField != null) {
                return declaredField.getBoolean(scanResult);
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException unused) {
            return false;
        }
    }

    class ViewTag {

        /* renamed from: a  reason: collision with root package name */
        public TextView f11629a;
        public ImageView b;
        public ImageView c;
        public TextView d;

        ViewTag() {
        }
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
            return WifiSetting.this.D.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(WifiSetting.this).inflate(R.layout.kuailian_wifi_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f11629a = (TextView) view.findViewById(R.id.wifi_name);
                viewTag.b = (ImageView) view.findViewById(R.id.miwif_tag);
                viewTag.d = (TextView) view.findViewById(R.id.security_name);
                viewTag.c = (ImageView) view.findViewById(R.id.wifi_signal_level);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            viewTag2.f11629a.setText(((KuailianScanResult) WifiSetting.this.D.get(i)).f11622a.SSID);
            if (((KuailianScanResult) WifiSetting.this.D.get(i)).c) {
                viewTag2.b.setVisibility(0);
            } else {
                viewTag2.b.setVisibility(8);
            }
            viewTag2.d.setText(WifiSetting.this.getSecurityString((KuailianScanResult) WifiSetting.this.D.get(i)));
            viewTag2.c.setImageResource(WifiSetting.this.getSignalLevel(WifiSetting.calculateSignalLevel(((KuailianScanResult) WifiSetting.this.D.get(i)).f11622a.level, 100)));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (WifiSetting.this.R != i) {
                        WifiSetting.this.H.setText("");
                    }
                    int unused = WifiSetting.this.R = i;
                    WifiSetting.this.G.setText(((KuailianScanResult) WifiSetting.this.D.get(i)).f11622a.SSID);
                    WifiSetting.this.a(((KuailianScanResult) WifiSetting.this.D.get(WifiSetting.this.R)).f11622a);
                    WifiSetting.this.g();
                    boolean unused2 = WifiSetting.this.T = false;
                    WifiSetting.this.hideWifiList();
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
            return WifiSetting.this.F.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(WifiSetting.this).inflate(R.layout.kuailian_wifi_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f11629a = (TextView) view.findViewById(R.id.wifi_name);
                viewTag.b = (ImageView) view.findViewById(R.id.miwif_tag);
                viewTag.d = (TextView) view.findViewById(R.id.security_name);
                viewTag.c = (ImageView) view.findViewById(R.id.wifi_signal_level);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            viewTag2.f11629a.setText(((KuailianScanResult) WifiSetting.this.F.get(i)).f11622a.SSID);
            viewTag2.f11629a.setTextColor(WifiSetting.this.getResources().getColor(R.color.class_text_16));
            if (((KuailianScanResult) WifiSetting.this.F.get(i)).c) {
                viewTag2.b.setVisibility(0);
            } else {
                viewTag2.b.setVisibility(8);
            }
            viewTag2.c.setImageResource(WifiSetting.this.getSignalLevel(WifiSetting.calculateSignalLevel(((KuailianScanResult) WifiSetting.this.F.get(i)).f11622a.level, 100)));
            viewTag2.d.setText(WifiSetting.this.getSecurityString((KuailianScanResult) WifiSetting.this.F.get(i)));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ScanResult scanResult = ((KuailianScanResult) WifiSetting.this.F.get(i)).f11622a;
                    if (scanResult.frequency > 5000 || scanResult.capabilities.contains("WEP") || scanResult.capabilities.contains("EAP") || scanResult.capabilities.contains("WAPI-KEY") || scanResult.capabilities.contains("WAPI-PSK") || scanResult.capabilities.contains("WAPI-CERT") || scanResult.level == 0 || DeviceFactory.e(scanResult)) {
                        new MLAlertDialog.Builder(WifiSetting.this).b((int) R.string.kuailian_unconn_reason).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) null).d();
                    } else if (WifiSetting.b(((KuailianScanResult) WifiSetting.this.F.get(i)).f11622a) == 0) {
                        new MLAlertDialog.Builder(WifiSetting.this).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (WifiSetting.this.R != i) {
                                    WifiSetting.this.H.setText("");
                                }
                                int unused = WifiSetting.this.R = i;
                                WifiSetting.this.G.setText(((KuailianScanResult) WifiSetting.this.F.get(i)).f11622a.SSID);
                                WifiSetting.this.a(((KuailianScanResult) WifiSetting.this.F.get(WifiSetting.this.R)).f11622a);
                                WifiSetting.this.g();
                                WifiSetting.this.hideWifiList();
                                boolean unused2 = WifiSetting.this.T = true;
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).d();
                    } else {
                        new MLAlertDialog.Builder(WifiSetting.this).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content_1).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (WifiSetting.this.R != i) {
                                    WifiSetting.this.H.setText("");
                                }
                                int unused = WifiSetting.this.R = i;
                                WifiSetting.this.G.setText(((KuailianScanResult) WifiSetting.this.F.get(i)).f11622a.SSID);
                                WifiSetting.this.a(((KuailianScanResult) WifiSetting.this.F.get(WifiSetting.this.R)).f11622a);
                                WifiSetting.this.g();
                                WifiSetting.this.hideWifiList();
                                boolean unused2 = WifiSetting.this.T = true;
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
    public void showWifiList() {
        this.M.setVisibility(0);
        this.M.startAnimation(AnimationUtils.loadAnimation(this, R.anim.v5_dialog_enter));
    }

    /* access modifiers changed from: package-private */
    public void hideWifiList() {
        this.M.setVisibility(8);
        this.M.startAnimation(AnimationUtils.loadAnimation(this, R.anim.v5_dialog_exit));
    }

    /* access modifiers changed from: package-private */
    public String getSecurityString(KuailianScanResult kuailianScanResult) {
        String str = "";
        int b2 = b(kuailianScanResult.f11622a);
        if (kuailianScanResult.b && b2 != 0) {
            str = str + getString(R.string.kuailian_save_passwd) + "，";
        }
        if (b2 == 0) {
            return str + getString(R.string.kuailian_no_passwd);
        } else if (b2 == 2) {
            PskType d2 = d(kuailianScanResult.f11622a);
            if (d2 == PskType.WPA) {
                return str + String.format(getString(R.string.kuailian_wifi_security_type), new Object[]{"WPA"});
            } else if (d2 == PskType.WPA2) {
                return str + String.format(getString(R.string.kuailian_wifi_security_type), new Object[]{"WPA2"});
            } else if (d2 != PskType.WPA_WPA2) {
                return str;
            } else {
                return str + String.format(getString(R.string.kuailian_wifi_security_type), new Object[]{"WPA_WPA2"});
            }
        } else if (b2 == 1) {
            return str + String.format(getString(R.string.kuailian_wifi_security_type), new Object[]{"WEP"});
        } else if (b2 == 3) {
            return str + String.format(getString(R.string.kuailian_wifi_security_type), new Object[]{"802.1X"});
        } else if (b2 != 4) {
            return str;
        } else {
            return str + String.format(getString(R.string.kuailian_wifi_security_type), new Object[]{"WAPI"});
        }
    }

    public int getAuthType(WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.allowedKeyManagement.get(1) ? 2 : 0;
    }
}
