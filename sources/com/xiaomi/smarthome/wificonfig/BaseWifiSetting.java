package com.xiaomi.smarthome.wificonfig;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.BlankActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.KuailianManager;
import com.xiaomi.smarthome.device.LocalRouterDeviceInfo;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.KeyboardUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.ExpandListView;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.library.common.widget.ResizeLayout;
import com.xiaomi.smarthome.library.common.widget.SlideImageView;
import com.xiaomi.smarthome.miio.WifiAccount;
import com.xiaomi.smarthome.wificonfig.NetworkTest;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseWifiSetting extends CommonActivity {
    protected static final int CONNECTING_TIME_OUT = 103;
    protected static final int CONNECTION_RESULT = 106;
    public static final int KUAILIAN_CAMERA_TIME = 120000;
    public static final int KUAILIAN_TIME = 70000;
    protected static final int MSG_DISCONNECT_TIME_OUT = 115;
    protected static final int MSG_GET_ROUTER_INFO_TIME_OUT = 116;
    protected static final int MSG_SEND_DEVICE_MSG = 114;
    protected static final int MSG_START_KUAILIAN = 110;
    protected static final int MSG_START_NETWORK_TEST = 109;
    protected static final int MSG_UPDATE_CONFIRM_STATE = 113;
    protected static final int MSG_UPDATE_DEVICE_STATE = 112;
    protected static final int MSG_UPDATE_DOWNLOADING_STATE = 118;
    protected static final int MSG_UPDATE_KUAILIAN_PROGRESS = 111;
    protected static final int MSG_UPDATE_RESCAN_DEVICE_STATE = 119;
    protected static final int MSG_UPDATE_ROUTER_TEST_STATE = 117;
    protected static final int NETWORK_STATE_CHANGED = 101;
    protected static final int REQUEST_LAYOUT = 107;
    protected static final int SCAN_RESULT_AVAILABLE = 100;
    protected static final int SCAN_TIME_OUT = 104;
    protected static final int WIFI_CONNECTION_DELAY = 108;
    protected static final int WIFI_STATE_CHANGED = 102;

    /* renamed from: a  reason: collision with root package name */
    private static final int f22830a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final String g = "http://v.youku.com/v_show/id_XODYwNTMxMjIw.html";
    private static final String h = "http://v.youku.com/v_show/id_XODU2NDA2MjA0.html";
    private static final String i = "http://v.youku.com/v_show/id_XODU2NDA2OTIw.html​";
    /* access modifiers changed from: private */
    public EditText A;
    /* access modifiers changed from: private */
    public EditText B;
    private CheckBox C;
    /* access modifiers changed from: private */
    public View D;
    private View E;
    /* access modifiers changed from: private */
    public CustomPullDownRefreshLinearLayout F;
    /* access modifiers changed from: private */
    public ListView G;
    /* access modifiers changed from: private */
    public ListView H;
    /* access modifiers changed from: private */
    public BaseAdapter I;
    /* access modifiers changed from: private */
    public BaseAdapter J;
    /* access modifiers changed from: private */
    public ImageView K;
    /* access modifiers changed from: private */
    public int L = -1;
    private Dialog M;
    /* access modifiers changed from: private */
    public boolean N = false;
    /* access modifiers changed from: private */
    public int O = 0;
    /* access modifiers changed from: private */
    public ResizeLayout P;
    private View Q;
    private ImageView R;
    private ImageView S;
    private SlideImageView T;
    private ImageView U;
    private TextView V;
    private TextView W;
    private TextView X;
    /* access modifiers changed from: private */
    public TextView Y;
    /* access modifiers changed from: private */
    public PieProgressBar Z;
    private TextView aa;
    private TextView ab;
    private TextView ac;
    /* access modifiers changed from: private */
    public PieProgressBar ad;
    private View ae;
    private boolean af = false;
    /* access modifiers changed from: private */
    public View ag;
    private View ah;
    /* access modifiers changed from: private */
    public CheckBox ai;
    private TextView aj;
    private TextView ak;
    /* access modifiers changed from: private */
    public View al;
    /* access modifiers changed from: private */
    public PieProgressBar am;
    private TextView an;
    /* access modifiers changed from: private */
    public TextView ao;
    private TextView ap;
    /* access modifiers changed from: private */
    public View aq;
    /* access modifiers changed from: private */
    public PieProgressBar ar;
    /* access modifiers changed from: private */
    public List<Device> as = new ArrayList();
    private View at;
    /* access modifiers changed from: private */
    public PieProgressBar au;
    private boolean av = false;
    private BroadcastReceiver aw = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                BaseWifiSetting.this.mHandler.sendEmptyMessage(100);
            } else if (action.equals("android.net.wifi.STATE_CHANGE")) {
                Parcelable parcelableExtra = intent.getParcelableExtra(StatType.NETWORKINFO);
                if (parcelableExtra != null) {
                    Message obtainMessage = BaseWifiSetting.this.mHandler.obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.obj = (NetworkInfo) parcelableExtra;
                    BaseWifiSetting.this.mHandler.sendMessage(obtainMessage);
                }
            } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                BaseWifiSetting.this.mHandler.sendEmptyMessage(102);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean j = false;
    private boolean k;
    /* access modifiers changed from: private */
    public boolean l = false;
    /* access modifiers changed from: private */
    public int m;
    protected boolean mChooseNopasswdWifi = false;
    protected boolean mChooseUnconnWifi = false;
    protected ConnectivityManager mConnectivityManager;
    String mDeviceModel;
    protected Handler mHandler = new Handler() {
        /* JADX WARNING: Removed duplicated region for block: B:147:0x0413  */
        /* JADX WARNING: Removed duplicated region for block: B:199:0x0688 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:200:0x0689  */
        /* JADX WARNING: Removed duplicated region for block: B:217:0x06ff  */
        /* JADX WARNING: Removed duplicated region for block: B:236:0x0797  */
        /* JADX WARNING: Removed duplicated region for block: B:245:0x07c2  */
        /* JADX WARNING: Removed duplicated region for block: B:251:0x0825  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r19) {
            /*
                r18 = this;
                r0 = r18
                r1 = r19
                int r2 = r1.what
                r3 = 8
                r4 = 1189765120(0x46ea6000, float:30000.0)
                r5 = 3
                r6 = 100
                r9 = 1120534528(0x42ca0000, float:101.0)
                r10 = 1120403456(0x42c80000, float:100.0)
                r11 = 0
                r12 = 1
                switch(r2) {
                    case 100: goto L_0x0358;
                    case 101: goto L_0x020f;
                    case 102: goto L_0x01de;
                    case 103: goto L_0x01c1;
                    case 104: goto L_0x0358;
                    case 105: goto L_0x0017;
                    case 106: goto L_0x019c;
                    case 107: goto L_0x0191;
                    case 108: goto L_0x0186;
                    case 109: goto L_0x017f;
                    case 110: goto L_0x0178;
                    case 111: goto L_0x00e6;
                    case 112: goto L_0x0017;
                    case 113: goto L_0x00bb;
                    case 114: goto L_0x0017;
                    case 115: goto L_0x0017;
                    case 116: goto L_0x008b;
                    case 117: goto L_0x0065;
                    case 118: goto L_0x0050;
                    case 119: goto L_0x001e;
                    default: goto L_0x0017;
                }
            L_0x0017:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.onHandleMessage(r1)
                goto L_0x08e7
            L_0x001e:
                long r1 = java.lang.System.currentTimeMillis()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                long r12 = r3.mStartTime
                long r1 = r1 - r12
                float r1 = (float) r1
                float r1 = r1 * r10
                float r1 = r1 / r4
                int r2 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
                if (r2 <= 0) goto L_0x003c
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.a((boolean) r11)
                com.xiaomi.smarthome.device.KuailianManager r1 = com.xiaomi.smarthome.device.KuailianManager.a()
                r1.b()
                return
            L_0x003c:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.PieProgressBar r2 = r2.au
                r2.setPercent(r1)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                r2 = 119(0x77, float:1.67E-43)
                r1.sendEmptyMessageDelayed(r2, r6)
                goto L_0x08e7
            L_0x0050:
                java.lang.Object r1 = r1.obj
                java.lang.Float r1 = (java.lang.Float) r1
                float r1 = r1.floatValue()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.PieProgressBar r2 = r2.ar
                float r1 = r1 * r10
                r2.setPercent(r1)
                goto L_0x08e7
            L_0x0065:
                long r1 = java.lang.System.currentTimeMillis()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                long r11 = r3.mStartTime
                long r1 = r1 - r11
                float r1 = (float) r1
                float r1 = r1 * r10
                float r1 = r1 / r4
                int r2 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
                if (r2 <= 0) goto L_0x0077
                return
            L_0x0077:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.PieProgressBar r2 = r2.am
                r2.setPercent(r1)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                r2 = 117(0x75, float:1.64E-43)
                r1.sendEmptyMessageDelayed(r2, r6)
                goto L_0x08e7
            L_0x008b:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.view.View r1 = r1.al
                r1.setVisibility(r3)
                java.lang.String r1 = "yunyi.camera.v1"
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mDeviceModel
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00b4
                java.lang.String r1 = "yunyi.camera.v2"
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mDeviceModel
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x00ad
                goto L_0x00b4
            L_0x00ad:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.c()
                goto L_0x08e7
            L_0x00b4:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.d()
                goto L_0x08e7
            L_0x00bb:
                long r1 = java.lang.System.currentTimeMillis()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                long r12 = r3.mStartTime
                long r1 = r1 - r12
                float r1 = (float) r1
                float r1 = r1 * r10
                float r1 = r1 / r4
                int r2 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
                if (r2 <= 0) goto L_0x00d2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.a((boolean) r11)
                return
            L_0x00d2:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.PieProgressBar r2 = r2.ad
                r2.setPercent(r1)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                r2 = 113(0x71, float:1.58E-43)
                r1.sendEmptyMessageDelayed(r2, r6)
                goto L_0x08e7
            L_0x00e6:
                java.lang.String r1 = "yunyi.camera.v1"
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mDeviceModel
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x011c
                java.lang.String r1 = "yunyi.camera.v2"
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mDeviceModel
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x011c
                java.lang.String r1 = "chuangmi.camera.xiaobai"
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mDeviceModel
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x010b
                goto L_0x011c
            L_0x010b:
                long r1 = java.lang.System.currentTimeMillis()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                long r3 = r3.mStartTime
                long r1 = r1 - r3
                float r1 = (float) r1
                float r1 = r1 * r10
                r2 = 1200142336(0x4788b800, float:70000.0)
                float r1 = r1 / r2
                goto L_0x012c
            L_0x011c:
                long r1 = java.lang.System.currentTimeMillis()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                long r3 = r3.mStartTime
                long r1 = r1 - r3
                float r1 = (float) r1
                float r1 = r1 * r10
                r2 = 1206542336(0x47ea6000, float:120000.0)
                float r1 = r1 / r2
            L_0x012c:
                int r2 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
                if (r2 <= 0) goto L_0x0150
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r1 = r1.O
                if (r1 <= 0) goto L_0x013e
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.n()
                goto L_0x014f
            L_0x013e:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                boolean r1 = r1.mSwitchToRescanPage
                if (r1 == 0) goto L_0x014a
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.l()
                goto L_0x014f
            L_0x014a:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.a((boolean) r11)
            L_0x014f:
                return
            L_0x0150:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.PieProgressBar r2 = r2.Z
                r2.setPercent(r1)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r1 = r1.Y
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.getConnStatusText()
                r1.setText(r2)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.checkOkBtn()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                r2 = 111(0x6f, float:1.56E-43)
                r1.sendEmptyMessageDelayed(r2, r6)
                goto L_0x08e7
            L_0x0178:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.j()
                goto L_0x08e7
            L_0x017f:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.f()
                goto L_0x08e7
            L_0x0186:
                int r1 = r1.arg1
                if (r1 != 0) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.g()
                goto L_0x08e7
            L_0x0191:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.ResizeLayout r1 = r1.P
                r1.requestLayout()
                goto L_0x08e7
            L_0x019c:
                java.lang.Object r1 = r1.obj
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                boolean r1 = r1.booleanValue()
                if (r1 == 0) goto L_0x01ad
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.n()
                goto L_0x08e7
            L_0x01ad:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                boolean r1 = r1.mSwitchToRescanPage
                if (r1 == 0) goto L_0x01ba
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.l()
                goto L_0x08e7
            L_0x01ba:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.a((boolean) r11)
                goto L_0x08e7
            L_0x01c1:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_CONNECTING
                if (r2 != r3) goto L_0x01cf
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.onHandleMessage(r1)
                return
            L_0x01cf:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r1 = r1.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.CONNECTTING
                if (r1 != r2) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.i()
                goto L_0x08e7
            L_0x01de:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.net.wifi.WifiManager r1 = r1.mWifiManager
                int r1 = r1.getWifiState()
                java.lang.String r2 = "WifiStateChanged"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = ""
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)
                if (r1 != r5) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r1 = r1.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.SCANNING
                if (r1 != r2) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.net.wifi.WifiManager r1 = r1.mWifiManager
                r1.startScan()
                goto L_0x08e7
            L_0x020f:
                java.lang.Object r2 = r1.obj
                android.net.NetworkInfo r2 = (android.net.NetworkInfo) r2
                android.net.NetworkInfo$DetailedState r3 = r2.getDetailedState()
                java.lang.String r4 = "WifiState"
                java.lang.String r5 = r3.toString()
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r4, r5)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.net.wifi.WifiManager r4 = r4.mWifiManager
                android.net.wifi.WifiInfo r4 = r4.getConnectionInfo()
                android.net.NetworkInfo$DetailedState r5 = android.net.NetworkInfo.DetailedState.DISCONNECTED
                if (r3 != r5) goto L_0x023b
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r5 = r5.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_SEARCHING
                if (r5 != r6) goto L_0x023b
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.onHandleMessage(r1)
                goto L_0x08e7
            L_0x023b:
                if (r4 == 0) goto L_0x08e7
                java.lang.String r5 = r4.getSSID()
                boolean r5 = android.text.TextUtils.isEmpty(r5)
                if (r5 != 0) goto L_0x08e7
                java.lang.String r5 = r4.getSSID()
                java.lang.String r6 = "<unknown ssid>"
                boolean r5 = r5.contains(r6)
                if (r5 == 0) goto L_0x0255
                goto L_0x08e7
            L_0x0255:
                android.net.NetworkInfo$DetailedState r5 = android.net.NetworkInfo.DetailedState.CONNECTED
                if (r3 != r5) goto L_0x08e7
                boolean r2 = r2.isConnected()
                if (r2 == 0) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.CONNECTTING
                if (r2 == r3) goto L_0x0277
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_CONNECTING
                if (r2 == r3) goto L_0x0277
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_SEARCHING
                if (r2 != r3) goto L_0x08e7
            L_0x0277:
                java.lang.String r2 = "MSG"
                java.lang.String r3 = "Wifi Connected"
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)
                java.lang.String r2 = "MSG"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r5 = "state = "
                r3.append(r5)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r5 = r5.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_CONNECTING
                if (r5 != r6) goto L_0x0293
                r11 = 1
            L_0x0293:
                r3.append(r11)
                java.lang.String r3 = r3.toString()
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_CONNECTING
                r5 = 103(0x67, float:1.44E-43)
                if (r2 == r3) goto L_0x034b
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.DEVICE_SEARCHING
                if (r2 != r3) goto L_0x02b1
                goto L_0x034b
            L_0x02b1:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.framework.api.RouterRemoteApi$WifiDetail r1 = r1.mRouterInfo
                if (r1 == 0) goto L_0x02dd
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.framework.api.RouterRemoteApi$WifiDetail r1 = r1.mRouterInfo
                java.util.ArrayList<com.xiaomi.smarthome.framework.api.RouterRemoteApi$WifiInfo> r1 = r1.f16425a
                java.util.Iterator r1 = r1.iterator()
                r8 = 0
            L_0x02c2:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L_0x02da
                java.lang.Object r2 = r1.next()
                com.xiaomi.smarthome.framework.api.RouterRemoteApi$WifiInfo r2 = (com.xiaomi.smarthome.framework.api.RouterRemoteApi.WifiInfo) r2
                int r3 = r2.f16426a
                if (r3 <= 0) goto L_0x02c2
                int r3 = r2.f16426a
                r6 = 20
                if (r3 >= r6) goto L_0x02c2
                r8 = r2
                goto L_0x02c2
            L_0x02da:
                java.lang.String r1 = r8.c
                goto L_0x0306
            L_0x02dd:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                boolean r1 = r1.mChooseUnconnWifi
                if (r1 == 0) goto L_0x02f4
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r1 = r1.mUnconnectResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.mPosition
                java.lang.Object r1 = r1.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r1 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r1
                android.net.wifi.ScanResult r1 = r1.f22964a
                goto L_0x0304
            L_0x02f4:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r1 = r1.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.mPosition
                java.lang.Object r1 = r1.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r1 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r1
                android.net.wifi.ScanResult r1 = r1.f22964a
            L_0x0304:
                java.lang.String r1 = r1.SSID
            L_0x0306:
                if (r4 == 0) goto L_0x0332
                java.lang.String r2 = r4.getSSID()
                boolean r1 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((java.lang.String) r2, (java.lang.String) r1)
                if (r1 == 0) goto L_0x0332
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.r()
                com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.L
                r1.a((int) r2)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                r1.removeMessages(r5)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.g()
                goto L_0x08e7
            L_0x0332:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                boolean r1 = r1.hasMessages(r5)
                if (r1 != 0) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r1 = r1.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.CONNECTTING
                if (r1 != r2) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.i()
                goto L_0x08e7
            L_0x034b:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r2 = r2.mHandler
                r2.removeMessages(r5)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.onHandleMessage(r1)
                return
            L_0x0358:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                boolean r1 = r1.N
                if (r1 == 0) goto L_0x0361
                return
            L_0x0361:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.net.wifi.WifiManager r1 = r1.mWifiManager
                android.net.wifi.WifiInfo r1 = r1.getConnectionInfo()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.SCANNING
                if (r2 == r4) goto L_0x038d
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = r2.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.RECANNINGINPUT
                if (r2 != r4) goto L_0x037a
                goto L_0x038d
            L_0x037a:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r1 = r1.mState
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.RESCANNING
                if (r1 != r2) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mPasswd
                r1.connectToWifi(r2)
                goto L_0x08e7
            L_0x038d:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.mPosition
                r4 = -1
                if (r2 == r4) goto L_0x03e1
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                boolean r2 = r2.mChooseUnconnWifi
                if (r2 == 0) goto L_0x03be
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.mPosition
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r6 = r6.mUnconnectResult
                int r6 = r6.size()
                if (r2 >= r6) goto L_0x03b9
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mUnconnectResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r6 = r6.mPosition
                java.lang.Object r2 = r2.get(r6)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                goto L_0x03e2
            L_0x03b9:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.mPosition = r4
                goto L_0x03e1
            L_0x03be:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.mPosition
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r6 = r6.mScanResult
                int r6 = r6.size()
                if (r2 >= r6) goto L_0x03dd
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r6 = r6.mPosition
                java.lang.Object r2 = r2.get(r6)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                goto L_0x03e2
            L_0x03dd:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.mPosition = r4
            L_0x03e1:
                r2 = 0
            L_0x03e2:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r6 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.net.wifi.WifiManager r6 = r6.mWifiManager
                java.util.List r6 = r6.getScanResults()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r7 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r7 = r7.mScanResult
                r7.clear()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r7 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List r7 = r7.y
                r7.clear()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r7 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r7 = r7.mUnconnectResult
                r7.clear()
                java.util.HashMap r7 = new java.util.HashMap
                r7.<init>()
                java.util.HashMap r9 = new java.util.HashMap
                r9.<init>()
                r10 = 0
                r13 = 0
            L_0x040d:
                int r14 = r6.size()
                if (r10 >= r14) goto L_0x066d
                java.lang.Object r14 = r6.get(r10)
                android.net.wifi.ScanResult r14 = (android.net.wifi.ScanResult) r14
                java.lang.String r14 = r14.SSID
                boolean r14 = android.text.TextUtils.isEmpty(r14)
                if (r14 != 0) goto L_0x0665
                java.lang.Object r14 = r6.get(r10)
                android.net.wifi.ScanResult r14 = (android.net.wifi.ScanResult) r14
                boolean r14 = com.xiaomi.smarthome.device.DeviceFactory.e((android.net.wifi.ScanResult) r14)
                if (r14 == 0) goto L_0x042f
                goto L_0x0665
            L_0x042f:
                java.lang.Object r14 = r6.get(r10)
                android.net.wifi.ScanResult r14 = (android.net.wifi.ScanResult) r14
                boolean r14 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.d((android.net.wifi.ScanResult) r14)
                com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager r15 = com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager.a()
                java.lang.Object r16 = r6.get(r10)
                r8 = r16
                android.net.wifi.ScanResult r8 = (android.net.wifi.ScanResult) r8
                java.lang.String r8 = r8.BSSID
                com.xiaomi.smarthome.miio.WifiAccount$WifiItem r8 = r15.a((java.lang.String) r8)
                if (r8 == 0) goto L_0x044f
                r8 = 1
                goto L_0x0450
            L_0x044f:
                r8 = 0
            L_0x0450:
                r15 = 5000(0x1388, float:7.006E-42)
                if (r1 == 0) goto L_0x04bc
                java.lang.String r3 = r1.getSSID()
                java.lang.Object r16 = r6.get(r10)
                r4 = r16
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                boolean r3 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((java.lang.String) r3, (java.lang.String) r4)
                if (r3 == 0) goto L_0x04bc
                if (r14 == 0) goto L_0x04bc
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = r3.frequency
                if (r3 <= r15) goto L_0x04bc
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.BSSID
                java.lang.String r4 = "\\:"
                java.lang.String[] r3 = r3.split(r4)
                java.lang.String r4 = "%1$s:%2$s:%3$s:%4$s:%5$s:%6$x"
                r13 = 6
                java.lang.Object[] r13 = new java.lang.Object[r13]
                r16 = r3[r11]
                r13[r11] = r16
                r16 = r3[r12]
                r13[r12] = r16
                r16 = 2
                r17 = r3[r16]
                r13[r16] = r17
                r16 = r3[r5]
                r13[r5] = r16
                r16 = 4
                r17 = 4
                r17 = r3[r17]
                r13[r16] = r17
                r16 = 5
                r17 = 5
                r3 = r3[r17]
                r5 = 16
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3, r5)
                int r3 = r3.intValue()
                int r3 = r3 - r12
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r13[r16] = r3
                java.lang.String r13 = java.lang.String.format(r4, r13)
            L_0x04bc:
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = r3.frequency
                r4 = 100
                if (r3 > r15) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WEP"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "EAP"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WAPI-KEY"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WAPI-PSK"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.lang.String r3 = r3.capabilities
                java.lang.String r5 = "WAPI-CERT"
                boolean r3 = r3.contains(r5)
                if (r3 != 0) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((android.net.wifi.ScanResult) r3)
                if (r3 == 0) goto L_0x05ce
                java.lang.Object r3 = r6.get(r10)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                int r3 = r3.level
                if (r3 == 0) goto L_0x05ce
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                boolean r3 = r3.a((java.lang.String) r5)
                if (r3 == 0) goto L_0x0540
                goto L_0x05ce
            L_0x0540:
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = new com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult
                r3.<init>()
                r3.c = r14
                r3.b = r8
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                r3.f22964a = r5
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                boolean r5 = r7.containsKey(r5)
                if (r5 == 0) goto L_0x05ba
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.level
                int r5 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r5, (int) r4)
                java.lang.Object r8 = r6.get(r10)
                android.net.wifi.ScanResult r8 = (android.net.wifi.ScanResult) r8
                java.lang.String r8 = r8.SSID
                java.lang.Object r8 = r7.get(r8)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r8 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r8
                android.net.wifi.ScanResult r8 = r8.f22964a
                int r8 = r8.level
                int r4 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r8, (int) r4)
                if (r5 <= r4) goto L_0x0665
                java.lang.Object r4 = r6.get(r10)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r7.get(r4)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r4 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r4
                android.net.wifi.ScanResult r5 = r4.f22964a
                java.lang.String r5 = r5.SSID
                r7.remove(r5)
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                r7.put(r5, r3)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r5 = r5.mScanResult
                r5.remove(r4)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r5 = r5.mUnconnectResult
                r5.remove(r4)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r4 = r4.mUnconnectResult
                r4.add(r3)
                goto L_0x0665
            L_0x05ba:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r4 = r4.mScanResult
                r4.add(r3)
                java.lang.Object r4 = r6.get(r10)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                r7.put(r4, r3)
                goto L_0x0665
            L_0x05ce:
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = new com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult
                r3.<init>()
                r3.c = r14
                r3.b = r8
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                r3.f22964a = r5
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.frequency
                if (r5 <= r15) goto L_0x05f2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List r5 = r5.y
                r5.add(r3)
            L_0x05f2:
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                boolean r5 = r9.containsKey(r5)
                if (r5 == 0) goto L_0x0653
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                int r5 = r5.level
                int r5 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r5, (int) r4)
                java.lang.Object r8 = r6.get(r10)
                android.net.wifi.ScanResult r8 = (android.net.wifi.ScanResult) r8
                java.lang.String r8 = r8.SSID
                java.lang.Object r8 = r9.get(r8)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r8 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r8
                android.net.wifi.ScanResult r8 = r8.f22964a
                int r8 = r8.level
                int r4 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a((int) r8, (int) r4)
                if (r5 <= r4) goto L_0x0665
                java.lang.Object r4 = r6.get(r10)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                java.lang.Object r4 = r9.get(r4)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r4 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r4
                android.net.wifi.ScanResult r5 = r3.f22964a
                java.lang.String r5 = r5.SSID
                r7.remove(r5)
                java.lang.Object r5 = r6.get(r10)
                android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
                java.lang.String r5 = r5.SSID
                r7.put(r5, r3)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mUnconnectResult
                r3.remove(r4)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mUnconnectResult
                r3.add(r4)
                goto L_0x0665
            L_0x0653:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r4 = r4.mUnconnectResult
                r4.add(r3)
                java.lang.Object r4 = r6.get(r10)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                java.lang.String r4 = r4.SSID
                r9.put(r4, r3)
            L_0x0665:
                int r10 = r10 + 1
                r3 = 8
                r4 = -1
                r5 = 3
                goto L_0x040d
            L_0x066d:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$2$1 r3 = new com.xiaomi.smarthome.wificonfig.BaseWifiSetting$2$1
                r3.<init>()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r4 = r4.mScanResult
                java.util.Collections.sort(r4, r3)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r4 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r4 = r4.mUnconnectResult
                java.util.Collections.sort(r4, r3)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                boolean r3 = r3.checkWifiList()
                if (r3 == 0) goto L_0x0689
                return
            L_0x0689:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r3 = r3.mPosition
                r4 = 2131500781(0x7f0c1eed, float:1.862525E38)
                r5 = -1
                if (r3 == r5) goto L_0x070f
                if (r2 == 0) goto L_0x06fc
                r1 = 0
            L_0x0696:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                int r3 = r3.size()
                if (r1 >= r3) goto L_0x06fc
                java.lang.String r3 = r2.BSSID
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r5 = r5.mScanResult
                java.lang.Object r5 = r5.get(r1)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r5 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r5
                android.net.wifi.ScanResult r5 = r5.f22964a
                java.lang.String r5 = r5.BSSID
                boolean r3 = r3.equalsIgnoreCase(r5)
                if (r3 == 0) goto L_0x06d3
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.mPosition = r1
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r2 = r2.z
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                java.lang.Object r1 = r3.get(r1)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r1 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r1
                android.net.wifi.ScanResult r1 = r1.f22964a
                java.lang.String r1 = r1.SSID
                r2.setText(r1)
            L_0x06d1:
                r1 = 1
                goto L_0x06fd
            L_0x06d3:
                if (r13 == 0) goto L_0x06f9
                java.lang.String r3 = r2.BSSID
                boolean r3 = r3.equalsIgnoreCase(r13)
                if (r3 == 0) goto L_0x06f9
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2.mPosition = r1
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r2 = r2.z
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                java.lang.Object r1 = r3.get(r1)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r1 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r1
                android.net.wifi.ScanResult r1 = r1.f22964a
                java.lang.String r1 = r1.SSID
                r2.setText(r1)
                goto L_0x06d1
            L_0x06f9:
                int r1 = r1 + 1
                goto L_0x0696
            L_0x06fc:
                r1 = 0
            L_0x06fd:
                if (r1 != 0) goto L_0x0835
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2 = -1
                r1.mPosition = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r1 = r1.z
                r1.setText(r4)
                goto L_0x0835
            L_0x070f:
                if (r1 == 0) goto L_0x078c
                java.lang.String r2 = r1.getBSSID()
                if (r2 == 0) goto L_0x078c
                r2 = 0
            L_0x0718:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                int r3 = r3.size()
                if (r2 >= r3) goto L_0x078c
                java.lang.String r3 = r1.getBSSID()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r5 = r5.mScanResult
                java.lang.Object r5 = r5.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r5 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r5
                android.net.wifi.ScanResult r5 = r5.f22964a
                java.lang.String r5 = r5.BSSID
                boolean r3 = r3.equalsIgnoreCase(r5)
                if (r3 == 0) goto L_0x0757
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.mPosition = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r1 = r1.z
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                java.lang.Object r2 = r3.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                java.lang.String r2 = r2.SSID
                r1.setText(r2)
            L_0x0755:
                r1 = 1
                goto L_0x078d
            L_0x0757:
                if (r13 == 0) goto L_0x0789
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                java.lang.Object r3 = r3.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r3 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r3
                android.net.wifi.ScanResult r3 = r3.f22964a
                java.lang.String r3 = r3.BSSID
                boolean r3 = r3.equalsIgnoreCase(r13)
                if (r3 == 0) goto L_0x0789
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.mPosition = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r1 = r1.z
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                java.lang.Object r2 = r3.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                java.lang.String r2 = r2.SSID
                r1.setText(r2)
                goto L_0x0755
            L_0x0789:
                int r2 = r2 + 1
                goto L_0x0718
            L_0x078c:
                r1 = 0
            L_0x078d:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mTargetBssid
                boolean r2 = android.text.TextUtils.isEmpty(r2)
                if (r2 != 0) goto L_0x07be
                r2 = 0
            L_0x0798:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r3 = r3.mScanResult
                int r3 = r3.size()
                if (r2 >= r3) goto L_0x07be
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r3 = r3.mTargetBssid
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r5 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r5 = r5.mScanResult
                java.lang.Object r5 = r5.get(r2)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r5 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r5
                android.net.wifi.ScanResult r5 = r5.f22964a
                java.lang.String r5 = r5.BSSID
                boolean r3 = r3.equalsIgnoreCase(r5)
                if (r3 == 0) goto L_0x07bb
                goto L_0x07bf
            L_0x07bb:
                int r2 = r2 + 1
                goto L_0x0798
            L_0x07be:
                r2 = -1
            L_0x07bf:
                r3 = -1
                if (r2 == r3) goto L_0x0825
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.mPosition = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r1 = r1.mTargetPasswd
                if (r1 == 0) goto L_0x0835
                com.xiaomi.smarthome.miio.WifiAccount$WifiItem r1 = new com.xiaomi.smarthome.miio.WifiAccount$WifiItem
                r1.<init>()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r3 = r3.mPosition
                java.lang.Object r2 = r2.get(r3)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                java.lang.String r2 = r2.BSSID
                r1.e = r2
                java.lang.String r2 = r1.e
                if (r2 != 0) goto L_0x07ed
                java.lang.String r2 = ""
                r1.e = r2
            L_0x07ed:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r3 = r3.mPosition
                java.lang.Object r2 = r2.get(r3)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                java.lang.String r2 = r2.SSID
                r1.c = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r3 = r3.mPosition
                java.lang.Object r2 = r2.get(r3)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                java.lang.String r2 = r2.capabilities
                r1.f = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.lang.String r2 = r2.mTargetPasswd
                r1.d = r2
                r1.b = r12
                com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager r2 = com.xiaomi.smarthome.framework.wifiaccount.WifiAccountManager.a()
                r2.a((com.xiaomi.smarthome.miio.WifiAccount.WifiItem) r1)
                goto L_0x0835
            L_0x0825:
                if (r1 != 0) goto L_0x0835
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2 = -1
                r1.mPosition = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r1 = r1.z
                r1.setText(r4)
            L_0x0835:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.view.View r1 = r1.D
                r2 = 8
                r1.setVisibility(r2)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting$State r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.State.INPUT
                r1.mState = r2
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r1 = r1.mPosition
                r2 = -1
                if (r1 == r2) goto L_0x086d
                com.xiaomi.smarthome.device.KuailianManager r1 = com.xiaomi.smarthome.device.KuailianManager.a()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r3 = r3.mPosition
                java.lang.Object r2 = r2.get(r3)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r2 = r2.f22964a
                java.lang.String r2 = r2.BSSID
                boolean r1 = r1.a((java.lang.String) r2)
                if (r1 != 0) goto L_0x086d
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.mIsMiui = r11
            L_0x086d:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r2 = r2.mPosition
                r3 = -1
                if (r2 != r3) goto L_0x0878
                r8 = 0
                goto L_0x0888
            L_0x0878:
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r2 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                java.util.List<com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult> r2 = r2.mScanResult
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r3 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                int r3 = r3.mPosition
                java.lang.Object r2 = r2.get(r3)
                com.xiaomi.smarthome.wificonfig.WifiSettingUtils$KuailianScanResult r2 = (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.KuailianScanResult) r2
                android.net.wifi.ScanResult r8 = r2.f22964a
            L_0x0888:
                r1.a((android.net.wifi.ScanResult) r8)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r1.o()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.EditText r1 = r1.A
                r1.setEnabled(r12)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.TextView r1 = r1.z
                r1.setEnabled(r12)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                r2 = 2131434013(0x7f0b1a1d, float:1.8489828E38)
                android.view.View r1 = r1.findViewById(r2)
                r1.setEnabled(r12)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.ImageView r1 = r1.K
                r1.setEnabled(r12)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.os.Handler r1 = r1.mHandler
                r2 = 104(0x68, float:1.46E-43)
                r1.removeMessages(r2)
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout r1 = r1.F
                int r1 = r1.getVisibility()
                if (r1 != 0) goto L_0x08e7
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.BaseAdapter r1 = r1.I
                r1.notifyDataSetChanged()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                android.widget.BaseAdapter r1 = r1.J
                r1.notifyDataSetChanged()
                com.xiaomi.smarthome.wificonfig.BaseWifiSetting r1 = com.xiaomi.smarthome.wificonfig.BaseWifiSetting.this
                com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout r1 = r1.F
                r1.postRefresh()
            L_0x08e7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wificonfig.BaseWifiSetting.AnonymousClass2.handleMessage(android.os.Message):void");
        }
    };
    boolean mIsBarcodeConnection = false;
    protected boolean mIsMiui = false;
    protected boolean mIsShowPasswd;
    ScanResult mKuailianScanResult;
    protected String mPasswd;
    protected int mPosition = -1;
    protected RouterRemoteApi.WifiDetail mRouterInfo;
    protected List<WifiSettingUtils.KuailianScanResult> mScanResult = new ArrayList();
    protected long mStartTime;
    protected State mState;
    protected boolean mSwitchToNetworkTest = true;
    protected boolean mSwitchToRescanPage = true;
    String mTargetBssid;
    String mTargetPasswd;
    protected List<WifiSettingUtils.KuailianScanResult> mUnconnectResult = new ArrayList();
    protected Class<?> mWhetstoneClass = null;
    protected WifiManager mWifiManager;
    /* access modifiers changed from: private */
    public TextView n;
    /* access modifiers changed from: private */
    public TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    /* access modifiers changed from: private */
    public View s;
    /* access modifiers changed from: private */
    public View t;
    private SimpleDraweeView u;
    private View[] v = new View[6];
    /* access modifiers changed from: private */
    public Button w;
    private Button x;
    /* access modifiers changed from: private */
    public List<WifiSettingUtils.KuailianScanResult> y = new ArrayList();
    /* access modifiers changed from: private */
    public TextView z;

    enum State {
        SCANNING,
        INPUT,
        RECANNINGINPUT,
        RESCANNING,
        CONNECTTING,
        CONNECTTING_AP,
        DEVICE_CONNECTING,
        DEVICE_SEARCHING,
        STOP_CONNECTION,
        RESCAN_DEVICE
    }

    public String getConnStatusText() {
        return null;
    }

    public String getErrorResultStr() {
        return "";
    }

    public void onHandleMessage(Message message) {
    }

    public void onStopConnection() {
    }

    public boolean setFailTextBtn(TextView textView) {
        return false;
    }

    public void startConnection() {
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        byte[] bytes = str.getBytes();
        for (byte b2 : bytes) {
            if ((b2 & 128) != 0) {
                return true;
            }
        }
        return false;
    }

    public void onBackPressed() {
        stop();
        if (this.mState == State.INPUT && this.F.getVisibility() == 0) {
            hideWifiList();
        } else if (this.mState == State.CONNECTTING) {
            this.mState = State.INPUT;
            d();
            onStopConnection();
        } else if (this.mState == State.INPUT) {
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
        this.l = true;
    }

    public void onDeviceConnectionSuccess() {
        if (!this.mHandler.hasMessages(111) || this.mKuailianScanResult != null) {
            stop();
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 106;
            obtainMessage.obj = true;
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    public void onDeviceConnectionSuccess(List<Device> list, boolean z2) {
        if (this.mHandler.hasMessages(111) || this.mHandler.hasMessages(119)) {
            if (z2) {
                stop();
                Message obtainMessage = this.mHandler.obtainMessage();
                obtainMessage.what = 106;
                obtainMessage.obj = true;
                this.mHandler.sendMessage(obtainMessage);
            }
            this.as.clear();
            this.as.addAll(list);
        }
    }

    public void onDeviceConnectionSuccessBind() {
        stop();
        k();
    }

    public void removeAllMsg() {
        this.mHandler.removeMessages(111);
        this.mHandler.removeMessages(113);
        this.mHandler.removeMessages(104);
        this.mHandler.removeMessages(103);
        this.mHandler.removeMessages(109);
        this.mHandler.removeMessages(110);
        this.mHandler.removeMessages(111);
        this.mHandler.removeMessages(112);
        this.mHandler.removeMessages(114);
        this.mHandler.removeMessages(111);
        NetworkTest.c().g();
        NetworkTest.c().a();
    }

    public void onDeviceConnectionError() {
        if (!this.mHandler.hasMessages(111)) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 106;
            obtainMessage.obj = false;
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    public void onDeviceFindDevices(int i2) {
        this.O = i2;
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        removeAllMsg();
        WifiDeviceFinder.j.clear();
        WifiDeviceFinder.m.clear();
        KuailianManager.a().b();
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x012f A[EDGE_INSN: B:58:0x012f->B:42:0x012f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            r6 = 0
            r5.N = r6
            r0 = 2130904739(0x7f0306a3, float:1.7416333E38)
            r5.setContentView(r0)
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "gotoBindView"
            boolean r0 = r0.getBooleanExtra(r1, r6)
            r5.af = r0
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r5.getSystemService(r0)
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0
            r5.mWifiManager = r0
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r5.getSystemService(r0)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            r5.mConnectivityManager = r0
            android.content.BroadcastReceiver r0 = r5.aw
            android.content.IntentFilter r1 = new android.content.IntentFilter
            java.lang.String r2 = "android.net.wifi.SCAN_RESULTS"
            r1.<init>(r2)
            r5.registerReceiver(r0, r1)
            android.content.BroadcastReceiver r0 = r5.aw
            android.content.IntentFilter r1 = new android.content.IntentFilter
            java.lang.String r2 = "android.net.wifi.STATE_CHANGE"
            r1.<init>(r2)
            r5.registerReceiver(r0, r1)
            android.content.BroadcastReceiver r0 = r5.aw
            android.content.IntentFilter r1 = new android.content.IntentFilter
            java.lang.String r2 = "android.net.wifi.WIFI_STATE_CHANGED"
            r1.<init>(r2)
            r5.registerReceiver(r0, r1)
            android.content.Intent r0 = r5.getIntent()
            if (r0 == 0) goto L_0x0070
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "scanResult"
            android.os.Parcelable r0 = r0.getParcelableExtra(r1)
            if (r0 == 0) goto L_0x0070
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "scanResult"
            android.os.Parcelable r0 = r0.getParcelableExtra(r1)
            android.net.wifi.ScanResult r0 = (android.net.wifi.ScanResult) r0
            r5.mKuailianScanResult = r0
        L_0x0070:
            android.content.Intent r0 = r5.getIntent()
            if (r0 == 0) goto L_0x0082
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "model"
            java.lang.String r0 = r0.getStringExtra(r1)
            r5.mDeviceModel = r0
        L_0x0082:
            android.content.Intent r0 = r5.getIntent()
            if (r0 == 0) goto L_0x0094
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "bssid"
            java.lang.String r0 = r0.getStringExtra(r1)
            r5.mTargetBssid = r0
        L_0x0094:
            android.content.Intent r0 = r5.getIntent()
            if (r0 == 0) goto L_0x00a6
            android.content.Intent r0 = r5.getIntent()
            java.lang.String r1 = "password"
            java.lang.String r0 = r0.getStringExtra(r1)
            r5.mTargetPasswd = r0
        L_0x00a6:
            boolean r0 = r5.q()     // Catch:{ Error -> 0x00b4 }
            r5.mIsMiui = r0     // Catch:{ Error -> 0x00b4 }
            boolean r0 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a()     // Catch:{ Error -> 0x00b4 }
            if (r0 == 0) goto L_0x00b4
            r5.mIsMiui = r6     // Catch:{ Error -> 0x00b4 }
        L_0x00b4:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.D()
            if (r0 == 0) goto L_0x00d2
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = r0.F()
            if (r0 != 0) goto L_0x00cb
            java.lang.String r0 = ""
            goto L_0x00cd
        L_0x00cb:
            java.lang.String r0 = r0.f1530a
        L_0x00cd:
            com.xiaomi.miio.MiioLocalAPI.a((java.lang.String) r0)
            r5.mIsMiui = r6
        L_0x00d2:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r0 = r0.s()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            long r0 = r0.longValue()
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x00eb
            r5.mIsMiui = r6
        L_0x00eb:
            boolean r0 = com.xiaomi.smarthome.library.common.ApiHelper.e
            if (r0 == 0) goto L_0x00f1
            r5.mIsMiui = r6
        L_0x00f1:
            r5.initViews()
            r5.k = r6
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r6 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r6 = r6.d()
            java.util.Iterator r6 = r6.iterator()
        L_0x0102:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x012f
            java.lang.Object r0 = r6.next()
            com.xiaomi.smarthome.device.Device r0 = (com.xiaomi.smarthome.device.Device) r0
            java.lang.String r1 = "xiaomi.router.v1"
            java.lang.String r2 = r0.model
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x012c
            java.lang.String r1 = "xiaomi.router.v2"
            java.lang.String r2 = r0.model
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x012c
            java.lang.String r1 = "xiaomi.router.mv1"
            java.lang.String r0 = r0.model
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0102
        L_0x012c:
            r6 = 1
            r5.k = r6
        L_0x012f:
            java.lang.String r6 = "yunyi.camera.v1"
            java.lang.String r0 = r5.mDeviceModel
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0150
            java.lang.String r6 = "yunyi.camera.v2"
            java.lang.String r0 = r5.mDeviceModel
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0144
            goto L_0x0150
        L_0x0144:
            boolean r6 = r5.k
            if (r6 == 0) goto L_0x014c
            r5.b()
            goto L_0x0160
        L_0x014c:
            r5.d()
            goto L_0x0160
        L_0x0150:
            r5.c()
            com.xiaomi.smarthome.config.SHConfig r6 = com.xiaomi.smarthome.config.SHConfig.a()
            java.lang.String r0 = "set_use_tag_time"
            long r1 = java.lang.System.currentTimeMillis()
            r6.a((java.lang.String) r0, (long) r1)
        L_0x0160:
            java.lang.String r6 = "yunyi.camera.v1"
            java.lang.String r0 = r5.mDeviceModel
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L_0x0174
            java.lang.String r6 = "yunyi.camera.v2"
            java.lang.String r0 = r5.mDeviceModel
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x017b
        L_0x0174:
            java.lang.String r6 = "yunyi"
            java.lang.String r0 = "wificonfig"
            com.umeng.analytics.MobclickAgent.a((android.content.Context) r5, (java.lang.String) r6, (java.lang.String) r0)
        L_0x017b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wificonfig.BaseWifiSetting.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (CoreApi.a().q()) {
            return;
        }
        if (this.M == null) {
            this.M = SHApplication.showLoginDialog(this, true);
            this.M.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    BaseWifiSetting.this.finish();
                }
            });
        } else if (!this.M.isShowing()) {
            this.M.show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.aw);
        this.N = true;
        ViewUtils.b((Activity) this);
        ViewUtils.a((Activity) this);
    }

    private void a(int i2, boolean z2) {
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        for (int i3 = 0; i3 < 6; i3++) {
            if (i3 == i2) {
                if (this.v[i3] != null) {
                    this.v[i3].setVisibility(0);
                    if (z2) {
                        this.v[i3].startAnimation(AnimationUtils.loadAnimation(this, R.anim.show));
                    }
                }
            } else if (this.v[i3] != null) {
                if (z2 && this.v[i3].getVisibility() == 0) {
                    this.v[i3].startAnimation(AnimationUtils.loadAnimation(this, R.anim.disappear));
                }
                this.v[i3].setVisibility(8);
            }
        }
    }

    private void a() {
        this.n.setText("");
        this.o.setText("");
        this.u.setVisibility(8);
        this.w.setVisibility(8);
        this.an.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void b() {
        a(-1, false);
        this.x.setVisibility(8);
        this.w.setText(R.string.next_button);
        this.j = true;
        a();
        this.al.setVisibility(0);
        this.am.setPercentView((TextView) findViewById(R.id.router_test_progress_test));
        this.am.setDuration(30);
        this.am.startPercentAnim();
        this.mStartTime = System.currentTimeMillis();
        this.mHandler.sendEmptyMessageDelayed(116, 30000);
        this.mHandler.sendEmptyMessageDelayed(117, 100);
        LocalRouterDeviceInfo.a().a((AsyncResponseCallback<RouterRemoteApi.WifiDetail>) new AsyncResponseCallback<RouterRemoteApi.WifiDetail>() {
            public void a(int i, Object obj) {
            }

            public void a(RouterRemoteApi.WifiDetail wifiDetail) {
                boolean z;
                Iterator<RouterRemoteApi.WifiInfo> it = wifiDetail.f16425a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    RouterRemoteApi.WifiInfo next = it.next();
                    if (next.f16426a > 0 && next.f16426a < 20 && next.b && !TextUtils.isEmpty(next.c)) {
                        z = true;
                        break;
                    }
                }
                if (!BaseWifiSetting.this.l) {
                    if (z) {
                        BaseWifiSetting.this.mRouterInfo = wifiDetail;
                    }
                    BaseWifiSetting.this.mHandler.removeMessages(116);
                    BaseWifiSetting.this.mHandler.removeMessages(117);
                    BaseWifiSetting.this.al.setVisibility(8);
                    BaseWifiSetting.this.w.setVisibility(0);
                    BaseWifiSetting.this.c();
                }
            }

            public void a(int i) {
                if (BaseWifiSetting.this.mHandler.hasMessages(116)) {
                    BaseWifiSetting.this.mHandler.removeMessages(116);
                    BaseWifiSetting.this.al.setVisibility(8);
                    BaseWifiSetting.this.w.setVisibility(0);
                    BaseWifiSetting.this.d();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        String str;
        a(0, false);
        this.al.setVisibility(8);
        this.m = 0;
        this.u.setVisibility(0);
        this.w.setVisibility(0);
        if (this.mDeviceModel == null) {
            str = getString(R.string.terminal_feedback);
            DeviceFactory.a(this.mDeviceModel, this.u, (int) R.drawable.kuailian_common_icon);
        } else {
            Device k2 = DeviceFactory.k(this.mDeviceModel);
            if (k2 != null) {
                str = k2.name;
            } else {
                str = SHApplication.getAppContext().getString(R.string.other_device);
            }
            DeviceFactory.a(this.mDeviceModel, this.u, (int) R.drawable.kuailian_common_icon);
            this.x.setVisibility(8);
            this.w.setText(R.string.next_button);
            if (this.mRouterInfo == null && this.mDeviceModel.equals("yunyi.camera.v1")) {
                this.w.setText(R.string.use_normal_wificonfig);
                this.w.setVisibility(8);
                this.an.setVisibility(8);
                this.x.setVisibility(0);
                this.x.setText(R.string.next_button);
                this.x.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        BaseWifiSetting.this.mIsBarcodeConnection = true;
                        BaseWifiSetting.this.mIsMiui = false;
                        BaseWifiSetting.this.b();
                    }
                });
            }
        }
        this.n.setText(String.format(getString(R.string.kuailian_main_title_3), new Object[]{str}));
        this.o.setText(R.string.kuailian_sub_main_title_1);
        if (this.mRouterInfo != null) {
            this.ao.setVisibility(0);
            this.n.setText(String.format(getString(R.string.router_test_info), new Object[]{str}));
            Iterator<RouterRemoteApi.WifiInfo> it = this.mRouterInfo.f16425a.iterator();
            RouterRemoteApi.WifiInfo wifiInfo = null;
            while (it.hasNext()) {
                RouterRemoteApi.WifiInfo next = it.next();
                if (next.f16426a > 0 && next.f16426a < 20) {
                    wifiInfo = next;
                }
            }
            if (wifiInfo == null) {
                this.mRouterInfo = null;
                d();
                return;
            }
            this.o.setText(String.format(getString(R.string.router_test_sub_info), new Object[]{wifiInfo.c}));
            SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(getString(R.string.router_test_switch));
            valueOf.setSpan(new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(BaseWifiSetting.this.getResources().getColor(R.color.class_text_27));
                    textPaint.setUnderlineText(true);
                }

                public void onClick(View view) {
                    BaseWifiSetting.this.mRouterInfo = null;
                    BaseWifiSetting.this.ao.setVisibility(8);
                    BaseWifiSetting.this.d();
                }
            }, 0, valueOf.length(), 33);
            this.ao.setHighlightColor(0);
            this.ao.setText(valueOf);
            this.ao.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.al.setVisibility(8);
        a(1, false);
        this.m = 1;
        this.x.setVisibility(8);
        this.w.setText(R.string.next_button);
        this.n.setVisibility(0);
        this.n.setText(String.format(getString(R.string.kuailian_main_title_2), new Object[0]));
        this.u.setVisibility(0);
        this.u.setImageURI(CommonUtils.c((int) R.drawable.kuailian_wifi_icon));
        this.an.setVisibility(8);
        this.o.setVisibility(0);
        this.o.setText(R.string.kuailian_sub_main_title_2);
        this.v[5].setVisibility(8);
        this.ag.setVisibility(8);
        checkWifiState();
        this.z.setText("");
        this.D.setVisibility(0);
        this.w.setEnabled(false);
        this.E.setVisibility(8);
        this.C.setVisibility(8);
        this.w.setVisibility(0);
        this.A.setText("");
        this.A.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                BaseWifiSetting.this.o();
            }
        });
        this.K.setSelected(false);
    }

    /* access modifiers changed from: private */
    public void e() {
        this.n.setVisibility(8);
        this.o.setVisibility(8);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        this.u.setVisibility(8);
        a(2, true);
        this.m = 2;
        KeyboardUtils.b(this.A);
        this.R.setImageResource(R.drawable.kuailian_phone_icon);
        this.S.setImageResource(R.drawable.kuailian_router_icon);
        this.T.setVisibility(0);
        this.U.setVisibility(8);
        if (this.mRouterInfo != null) {
            RouterRemoteApi.WifiInfo wifiInfo = null;
            Iterator<RouterRemoteApi.WifiInfo> it = this.mRouterInfo.f16425a.iterator();
            while (it.hasNext()) {
                RouterRemoteApi.WifiInfo next = it.next();
                if (next.f16426a > 0 && next.f16426a < 20) {
                    wifiInfo = next;
                }
            }
            WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
            if (connectionInfo != null && connectionInfo.getSSID() != null) {
                if (WifiSettingUtils.a(connectionInfo.getSSID(), wifiInfo.c)) {
                    this.mHandler.sendEmptyMessageDelayed(108, 1000);
                    r();
                } else {
                    this.mState = State.CONNECTTING;
                    connectXiaomiRouter(wifiInfo.c, wifiInfo.e, wifiInfo.g, true);
                }
            } else {
                return;
            }
        } else {
            connectToWifi(this.mPasswd);
        }
        this.w.setVisibility(8);
        this.an.setVisibility(0);
        this.an.setText(R.string.cancel);
        this.V.setText(R.string.kuailian_connectting_router);
        this.W.setText("");
        this.T.startConnection();
    }

    /* access modifiers changed from: private */
    public void f() {
        this.R.setImageResource(R.drawable.kuailian_router_icon);
        this.S.setImageResource(R.drawable.kuailian_icon_cloud);
        this.V.setText(R.string.kuailian_connectting_cloud);
        this.W.setText("");
        this.T.setVisibility(0);
        this.U.setVisibility(8);
        NetworkTest.c().a((NetworkTest.Listener) new NetworkTest.Listener() {
            public void a() {
                NetworkTest.c().g();
                NetworkTest.c().a();
                if (BaseWifiSetting.this.m != 1) {
                    BaseWifiSetting.this.h();
                }
            }

            public void a(int i) {
                NetworkTest.c().g();
                NetworkTest.c().a();
                if (i == 1) {
                    BaseWifiSetting.this.a((int) R.string.kuailian_network_dns_error);
                } else {
                    BaseWifiSetting.this.a((int) R.string.kuailian_network_slow);
                }
            }
        });
        NetworkTest.c().b();
        this.T.startConnection();
    }

    /* access modifiers changed from: private */
    public void g() {
        this.T.stopConnection();
        this.T.setVisibility(8);
        this.U.setVisibility(0);
        this.U.setImageResource(R.drawable.connection_success);
        this.V.setText(R.string.kuailian_success);
        this.W.setText(R.string.kuailian_success_phone_router);
        this.mHandler.sendEmptyMessageDelayed(109, 1000);
    }

    /* access modifiers changed from: private */
    public void h() {
        this.T.stopConnection();
        this.T.setVisibility(8);
        this.an.setVisibility(8);
        this.U.setVisibility(0);
        this.U.setImageResource(R.drawable.connection_success);
        this.V.setText(R.string.kuailian_success);
        this.W.setText(R.string.kuailian_success_phone_cloud);
        this.mHandler.sendEmptyMessageDelayed(110, 1000);
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.T.stopConnection();
        this.T.setVisibility(8);
        this.U.setVisibility(0);
        this.U.setImageResource(R.drawable.connection_failed);
        this.V.setText(R.string.kuailian_failed);
        this.W.setText(i2);
        this.v[5].setVisibility(0);
        this.ag.setVisibility(0);
        this.ah.setVisibility(8);
        this.ak.setText(R.string.kuailian_cancel);
        this.aj.setText(R.string.kuailian_retry_network);
        this.ak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseWifiSetting.this.goBack(false, false);
            }
        });
        this.aj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseWifiSetting.this.d();
            }
        });
        this.an.setVisibility(0);
        this.an.setText(R.string.kuailian_still_connect);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.v[5] != null) {
            this.T.stopConnection();
            this.T.setVisibility(8);
            this.mState = State.STOP_CONNECTION;
            this.U.setVisibility(0);
            this.U.setImageResource(R.drawable.connection_failed);
            this.V.setText(R.string.kuailian_falied_router);
            this.W.setText(R.string.kuailian_failed_phone_router);
            this.an.setVisibility(8);
            this.v[5].setVisibility(0);
            this.ag.setVisibility(0);
            this.ai.setVisibility(8);
            this.ah.setVisibility(8);
            this.ak.setText(R.string.kuailian_cancel);
            this.aj.setText(R.string.kuailian_retry_network);
            this.ak.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseWifiSetting.this.goBack(false, false);
                }
            });
            this.aj.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseWifiSetting.this.d();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.mIsBarcodeConnection) {
            goCameraBarcodeConnection();
            return;
        }
        a(3, false);
        this.m = 3;
        this.Z.setPercentView(this.aa);
        this.mStartTime = System.currentTimeMillis();
        this.n.setVisibility(8);
        this.o.setVisibility(8);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        this.u.setVisibility(8);
        this.mHandler.sendEmptyMessageDelayed(111, 100);
        if (this.mDeviceModel == null) {
            this.X.setText(String.format(getString(R.string.kuailian_main_title_3), new Object[]{getString(R.string.terminal_feedback)}));
        } else {
            Device k2 = DeviceFactory.k(this.mDeviceModel);
            if (k2 != null) {
                this.X.setText(String.format(getString(R.string.kuailian_main_title_3), new Object[]{k2.name}));
            } else {
                this.X.setText(String.format(getString(R.string.kuailian_main_title_3), new Object[]{SHApplication.getAppContext().getString(R.string.other_device)}));
            }
        }
        r();
        startConnection();
        this.mState = State.DEVICE_CONNECTING;
        this.an.setVisibility(0);
        this.an.setText(R.string.cancel);
    }

    private void k() {
        a(4, false);
        this.ab.setText(String.format(getString(R.string.kuailian_main_title_4), new Object[0]));
        if (this.mKuailianScanResult == null || DeviceFactory.a(this.mKuailianScanResult) != "zhimi.airpurifier.v1") {
            this.ac.setText(R.string.kuailian_sub_main_title_4);
        } else {
            this.ac.setText(R.string.kuailian_sub_main_title_4_zhimi);
        }
        this.ad.setPercentView((TextView) findViewById(R.id.confirm_progress_text));
        this.ad.setOri(false);
        this.mStartTime = System.currentTimeMillis();
        this.mHandler.sendEmptyMessageDelayed(113, 100);
    }

    /* access modifiers changed from: private */
    public void l() {
        a(5, false);
        this.an.setVisibility(4);
        this.w.setVisibility(8);
        this.o.setVisibility(8);
        this.ag.setVisibility(8);
        a();
        if (this.mState != State.STOP_CONNECTION) {
            this.mState = State.STOP_CONNECTION;
            KuailianManager.a().b();
            this.ai.setVisibility(8);
            this.u.setVisibility(0);
            this.n.setVisibility(0);
            this.n.setText(R.string.connect_timeout);
            this.u.setImageURI(CommonUtils.c((int) R.drawable.kuailian_failed_icon));
            this.ap.setVisibility(0);
            this.ap.setText(R.string.wifi_rescan_wifi);
            this.ap.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    KuailianManager.a().a(BaseWifiSetting.this, true, BaseWifiSetting.this.mKuailianScanResult, (String) null);
                    BaseWifiSetting.this.m();
                }
            });
            KuailianManager.a().d();
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        a(-1, false);
        this.u.setVisibility(8);
        this.n.setVisibility(8);
        this.o.setVisibility(8);
        this.at.setVisibility(0);
        this.au.setPercentView((TextView) findViewById(R.id.rescan_device_progress_text));
        this.an.setVisibility(0);
        this.an.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseWifiSetting.this.goBack(false, false);
            }
        });
        this.mStartTime = System.currentTimeMillis();
        this.mState = State.RESCAN_DEVICE;
        this.mHandler.sendEmptyMessage(119);
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        a(5, false);
        this.an.setVisibility(4);
        this.w.setVisibility(8);
        this.aq.setVisibility(8);
        this.at.setVisibility(8);
        if (this.mState != State.STOP_CONNECTION) {
            this.mState = State.STOP_CONNECTION;
            KuailianManager.a().b();
            if (z2) {
                this.ai.setVisibility(0);
                this.ai.setChecked(true);
                this.n.setText(String.format(getString(R.string.kuailian_success), new Object[0]));
                this.u.setVisibility(0);
                this.n.setVisibility(0);
                this.o.setVisibility(8);
                this.p.setVisibility(8);
                this.q.setVisibility(8);
                this.r.setVisibility(8);
                this.u.setImageURI(CommonUtils.c((int) R.drawable.kuailian_success_icon));
                this.ag.setVisibility(8);
                this.ap.setVisibility(0);
                this.ap.setText(R.string.miio_sub_device_study_complete);
                this.ap.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (BaseWifiSetting.this.ai.isChecked()) {
                            final XQProgressDialog a2 = XQProgressDialog.a(BaseWifiSetting.this.getContext(), (CharSequence) null, BaseWifiSetting.this.getString(R.string.creating, new Object[]{true, false}));
                            final boolean[] zArr = new boolean[BaseWifiSetting.this.as.size()];
                            for (int i = 0; i < zArr.length; i++) {
                                zArr[i] = false;
                            }
                            for (final int i2 = 0; i2 < BaseWifiSetting.this.as.size(); i2++) {
                                DeviceShortcutUtils.a(false, (Device) BaseWifiSetting.this.as.get(i2), (Intent) null, "smart_config", (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                                    public void a(Void voidR) {
                                        zArr[i2] = true;
                                        int i = 0;
                                        while (i < BaseWifiSetting.this.as.size()) {
                                            if (zArr[i]) {
                                                i++;
                                            } else {
                                                return;
                                            }
                                        }
                                        a2.dismiss();
                                        BaseWifiSetting.this.goBack(false, false);
                                    }

                                    public void a(int i) {
                                        if (i != -1) {
                                            zArr[i2] = true;
                                            int i2 = 0;
                                            while (i2 < BaseWifiSetting.this.as.size()) {
                                                if (zArr[i2]) {
                                                    i2++;
                                                } else {
                                                    return;
                                                }
                                            }
                                            a2.dismiss();
                                            BaseWifiSetting.this.goBack(false, false);
                                        } else if (Build.VERSION.SDK_INT < 23 || !BaseWifiSetting.this.shouldShowRequestPermissionRationale("com.android.launcher.permission.INSTALL_SHORTCUT")) {
                                            ToastUtil.a((int) R.string.permission_tips_denied_msg);
                                        } else {
                                            BaseWifiSetting.this.requestPermissions(new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, 1);
                                        }
                                    }

                                    public void a(int i, Object obj) {
                                        zArr[i2] = true;
                                        int i2 = 0;
                                        while (i2 < BaseWifiSetting.this.as.size()) {
                                            if (zArr[i2]) {
                                                i2++;
                                            } else {
                                                return;
                                            }
                                        }
                                        a2.dismiss();
                                        BaseWifiSetting.this.goBack(false, false);
                                    }
                                });
                            }
                            return;
                        }
                        BaseWifiSetting.this.goBack(false, false);
                    }
                });
                if ("yunyi.camera.v1".equals(this.mDeviceModel) || "yunyi.camera.v2".equals(this.mDeviceModel)) {
                    MobclickAgent.a((Context) this, StatUtil.b, "wificonfig suscess");
                    return;
                }
                return;
            }
            if ("yunyi.camera.v1".equals(this.mDeviceModel) || "yunyi.camera.v2".equals(this.mDeviceModel)) {
                MobclickAgent.a((Context) this, StatUtil.b, "wificonfig failed");
            }
            this.ai.setVisibility(8);
            this.n.setText(String.format(getString(R.string.kuailian_failed), new Object[0]));
            onStopConnection();
            this.o.setVisibility(0);
            this.o.setText(getErrorResultStr());
            WifiAccountManager.a().b(WifiUtil.b(this));
            this.u.setVisibility(0);
            this.n.setVisibility(0);
            this.u.setImageURI(CommonUtils.c((int) R.drawable.kuailian_failed_icon));
            this.ag.setVisibility(0);
            this.ah.setVisibility(8);
            this.ak.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseWifiSetting.this.goBack(false, false);
                }
            });
            if (!setFailTextBtn(this.aj)) {
                this.aj.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        BaseWifiSetting.this.goBack(false, false);
                        Intent intent = new Intent(BaseWifiSetting.this, ChooseDeviceActivity.class);
                        if (BaseWifiSetting.this.mDeviceModel != null) {
                            intent.putExtra("model", BaseWifiSetting.this.mDeviceModel);
                        }
                        if (BaseWifiSetting.this.mKuailianScanResult != null) {
                            intent.putExtra("model", DeviceFactory.a(BaseWifiSetting.this.mKuailianScanResult));
                        }
                        BaseWifiSetting.this.startActivity(intent);
                        BaseWifiSetting.this.finish();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        a(-1, false);
        this.u.setVisibility(8);
        this.n.setVisibility(8);
        this.at.setVisibility(8);
        this.aq.setVisibility(0);
        this.mHandler.removeMessages(119);
        this.ar.setPercentView((TextView) findViewById(R.id.download_progress_text));
        if (this.as.size() <= 0) {
            this.aq.setVisibility(8);
            a(true);
        } else if (CoreApi.a().d(this.as.get(0).model).l()) {
            this.aq.setVisibility(8);
            a(true);
        } else {
            CoreApi.a().a(this.as.get(0).model, (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
                public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                }

                public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                }

                public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                }

                public void onProgress(PluginRecord pluginRecord, float f) {
                    Message obtainMessage = BaseWifiSetting.this.mHandler.obtainMessage();
                    obtainMessage.obj = Float.valueOf(f);
                    obtainMessage.what = 118;
                    BaseWifiSetting.this.mHandler.sendMessage(obtainMessage);
                }

                public void onSuccess(PluginRecord pluginRecord) {
                    BaseWifiSetting.this.a(true);
                }

                public void onFailure(PluginError pluginError) {
                    BaseWifiSetting.this.a(true);
                }

                public void onCancel() {
                    BaseWifiSetting.this.a(true);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void checkOkBtn() {
        if (this.O > 0) {
            this.Y.setText(getResources().getQuantityString(R.plurals.kuailian_sub_main_device_complete, this.O, new Object[]{Integer.valueOf(this.O)}));
            this.o.setText(R.string.kuailian_sub_main_device_count);
            this.o.setOnClickListener((View.OnClickListener) null);
            this.m = 3;
            this.an.setVisibility(8);
            this.w.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.an = (TextView) findViewById(R.id.finish_title);
        this.n = (TextView) findViewById(R.id.kuailian_common_main_title);
        this.o = (TextView) findViewById(R.id.kuailian_common_main_sub_title);
        this.p = (TextView) findViewById(R.id.kuailian_common_main_sub_title_0);
        this.q = (TextView) findViewById(R.id.kuailian_common_main_sub_title_1);
        this.r = (TextView) findViewById(R.id.kuailian_common_main_sub_title_2);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        this.P = (ResizeLayout) findViewById(R.id.top_view);
        this.P.setResizeListener(new ResizeLayout.ResizeListener() {
            public void a(int i, int i2) {
                int i3 = i - i2;
                if (i3 > 0) {
                    ViewGroup.LayoutParams layoutParams = BaseWifiSetting.this.s.getLayoutParams();
                    layoutParams.height = DisplayUtils.a(167.0f);
                    BaseWifiSetting.this.s.setLayoutParams(layoutParams);
                    ViewGroup.LayoutParams layoutParams2 = BaseWifiSetting.this.t.getLayoutParams();
                    layoutParams2.height = DisplayUtils.a(60.3f);
                    BaseWifiSetting.this.t.setLayoutParams(layoutParams2);
                    if (BaseWifiSetting.this.mState == State.INPUT) {
                        BaseWifiSetting.this.o.setVisibility(0);
                        BaseWifiSetting.this.n.setVisibility(0);
                    }
                } else if (i3 < 0) {
                    ViewGroup.LayoutParams layoutParams3 = BaseWifiSetting.this.s.getLayoutParams();
                    layoutParams3.height = DisplayUtils.a(10.0f);
                    BaseWifiSetting.this.s.setLayoutParams(layoutParams3);
                    ViewGroup.LayoutParams layoutParams4 = BaseWifiSetting.this.t.getLayoutParams();
                    layoutParams4.height = DisplayUtils.a(10.0f);
                    BaseWifiSetting.this.t.setLayoutParams(layoutParams4);
                    BaseWifiSetting.this.o.setVisibility(8);
                    BaseWifiSetting.this.n.setVisibility(8);
                }
                BaseWifiSetting.this.mHandler.sendEmptyMessage(107);
            }
        });
        this.s = findViewById(R.id.top_view_margin);
        this.t = findViewById(R.id.input_view_margin);
        this.u = (SimpleDraweeView) findViewById(R.id.kuailian_common_icon);
        this.u.setHierarchy(new GenericDraweeHierarchyBuilder(this.u.getResources()).setFadeDuration(200).setPlaceholderImage(this.u.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
        this.w = (Button) findViewById(R.id.next_btn);
        this.x = (Button) findViewById(R.id.next_btn_2);
        this.an.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BaseWifiSetting.this.m == 2 && BaseWifiSetting.this.ag.getVisibility() == 0) {
                    BaseWifiSetting.this.j();
                } else if (BaseWifiSetting.this.aq.getVisibility() == 0) {
                    BaseWifiSetting.this.goBack(true, false);
                } else {
                    BaseWifiSetting.this.d();
                    BaseWifiSetting.this.onStopConnection();
                    KuailianManager.a().b();
                }
            }
        });
        this.w.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BaseWifiSetting.this.m == 0) {
                    BaseWifiSetting.this.ao.setVisibility(8);
                    if (BaseWifiSetting.this.mRouterInfo != null) {
                        BaseWifiSetting.this.mIsMiui = false;
                        if (BaseWifiSetting.this.mSwitchToNetworkTest) {
                            BaseWifiSetting.this.e();
                        } else {
                            BaseWifiSetting.this.j();
                        }
                    } else if (BaseWifiSetting.this.j) {
                        BaseWifiSetting.this.d();
                    } else {
                        BaseWifiSetting.this.d();
                    }
                } else if (BaseWifiSetting.this.m == 1) {
                    if (BaseWifiSetting.this.mIsShowPasswd) {
                        BaseWifiSetting.this.mPasswd = BaseWifiSetting.this.A.getText().toString();
                    }
                    if (BaseWifiSetting.this.mSwitchToNetworkTest) {
                        BaseWifiSetting.this.e();
                    } else {
                        BaseWifiSetting.this.j();
                    }
                } else if (BaseWifiSetting.this.m != 5 && BaseWifiSetting.this.m == 3) {
                    BaseWifiSetting.this.goBack(false, false);
                }
            }
        });
        this.v[0] = findViewById(R.id.first_step);
        this.ao = (TextView) findViewById(R.id.router_switch_btn);
        this.ao.setVisibility(4);
        this.v[1] = findViewById(R.id.second_step);
        this.K = (ImageView) findViewById(R.id.wifi_password_toggle);
        this.A = (EditText) findViewById(R.id.wifi_password_editor);
        this.E = findViewById(R.id.wifi_setting_pass_container);
        this.F = (CustomPullDownRefreshLinearLayout) findViewById(R.id.wifi_refresh_container);
        this.F.setScrollView((ScrollView) findViewById(R.id.wifi_list_scroll_view));
        this.F.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                BaseWifiSetting.this.mWifiManager.startScan();
                BaseWifiSetting.this.mState = State.RECANNINGINPUT;
            }
        });
        this.G = (ExpandListView) findViewById(R.id.conn_wifi_list);
        this.H = (ExpandListView) findViewById(R.id.unconn_wifi_list);
        ScanResult scanResult = this.mKuailianScanResult;
        this.B = (EditText) findViewById(R.id.wifi_password_editor_above);
        this.A.setVisibility(8);
        if (this.mKuailianScanResult != null) {
            this.mDeviceModel = DeviceFactory.a(this.mKuailianScanResult);
        }
        this.D = findViewById(R.id.searching_text);
        this.K.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BaseWifiSetting.this.mIsShowPasswd) {
                    int selectionStart = BaseWifiSetting.this.A.getSelectionStart();
                    if (!BaseWifiSetting.this.K.isSelected()) {
                        BaseWifiSetting.this.A.setInputType(144);
                        BaseWifiSetting.this.K.setSelected(true);
                    } else {
                        BaseWifiSetting.this.A.setInputType(129);
                        BaseWifiSetting.this.K.setSelected(false);
                    }
                    BaseWifiSetting.this.A.setSelection(selectionStart);
                }
            }
        });
        this.K.setSelected(false);
        this.A.setInputType(129);
        this.B.setInputType(129);
        this.B.setFocusable(false);
        this.B.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseWifiSetting.this.mIsShowPasswd = true;
                BaseWifiSetting.this.A.setVisibility(0);
                BaseWifiSetting.this.B.setVisibility(8);
                BaseWifiSetting.this.A.requestFocus();
                BaseWifiSetting.this.K.setEnabled(true);
                BaseWifiSetting.this.w.setEnabled(false);
                return false;
            }
        });
        this.z = (TextView) findViewById(R.id.login_wifi_ssid_chooser);
        this.z.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseWifiSetting.this.B.clearFocus();
                BaseWifiSetting.this.showWifiList();
                BaseAdapter unused = BaseWifiSetting.this.I = new ConnWifiAdapter();
                BaseWifiSetting.this.G.setAdapter(BaseWifiSetting.this.I);
                BaseAdapter unused2 = BaseWifiSetting.this.J = new UnConnWifiAdapter();
                BaseWifiSetting.this.H.setAdapter(BaseWifiSetting.this.J);
            }
        });
        findViewById(R.id.wifi_ssid_toggle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseWifiSetting.this.showWifiList();
                BaseAdapter unused = BaseWifiSetting.this.I = new ConnWifiAdapter();
                BaseWifiSetting.this.G.setAdapter(BaseWifiSetting.this.I);
                BaseAdapter unused2 = BaseWifiSetting.this.J = new UnConnWifiAdapter();
                BaseWifiSetting.this.H.setAdapter(BaseWifiSetting.this.J);
            }
        });
        findViewById(R.id.wifi_ssid_toggle).setEnabled(false);
        this.C = (CheckBox) findViewById(R.id.check_box_save_passwd);
        this.C.setChecked(true);
        this.v[2] = findViewById(R.id.third_step);
        this.R = (ImageView) findViewById(R.id.left_icon_view);
        this.S = (ImageView) findViewById(R.id.right_icon_view);
        this.T = (SlideImageView) findViewById(R.id.center_icon_view);
        this.U = (ImageView) findViewById(R.id.center_result_view);
        this.V = (TextView) findViewById(R.id.connecting_main_title);
        this.W = (TextView) findViewById(R.id.connecting_main_sub_title);
        this.v[3] = findViewById(R.id.fouth_step);
        this.Z = (PieProgressBar) findViewById(R.id.kuailian_progress);
        this.X = (TextView) findViewById(R.id.kuailianing_main_title);
        this.Y = (TextView) findViewById(R.id.kuailianing_main_sub_title);
        this.aa = (TextView) findViewById(R.id.connecting_progress);
        this.v[4] = findViewById(R.id.fifth_step);
        this.ab = (TextView) findViewById(R.id.confirm_main_title);
        this.ac = (TextView) findViewById(R.id.confirm_main_sub_title);
        this.ad = (PieProgressBar) findViewById(R.id.confirm_progress);
        this.v[5] = findViewById(R.id.final_step);
        this.ag = findViewById(R.id.finish_error_btn_container);
        this.ah = findViewById(R.id.finish_success_btn);
        this.aj = (TextView) findViewById(R.id.retry_btn);
        this.ak = (TextView) findViewById(R.id.cancel_btn);
        this.ap = (TextView) findViewById(R.id.finish_success_btn);
        this.ai = (CheckBox) findViewById(R.id.check_box_create_launcher);
        this.al = findViewById(R.id.router_test_step);
        this.am = (PieProgressBar) findViewById(R.id.router_test_progress);
        this.aq = findViewById(R.id.start_download_plugin_step);
        this.ar = (PieProgressBar) findViewById(R.id.download_progress);
        this.at = findViewById(R.id.rescan_device_step);
        this.au = (PieProgressBar) findViewById(R.id.rescan_device_progress);
    }

    /* access modifiers changed from: package-private */
    public boolean checkWifiList() {
        if (this.mScanResult.size() != 0) {
            return false;
        }
        new MLAlertDialog.Builder(this).b((int) R.string.get_wifi_scan_result_error).a((int) R.string.wifi_rescan_wifi, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseWifiSetting.this.D.setVisibility(0);
                BaseWifiSetting.this.mWifiManager.startScan();
                BaseWifiSetting.this.A.setEnabled(false);
                BaseWifiSetting.this.z.setEnabled(false);
                BaseWifiSetting.this.w.setEnabled(false);
                BaseWifiSetting.this.K.setEnabled(false);
                BaseWifiSetting.this.mState = State.SCANNING;
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BaseWifiSetting.this.goBack(false, true);
            }
        }).a(false).d();
        this.mState = State.INPUT;
        return true;
    }

    /* access modifiers changed from: package-private */
    public void checkWifiState() {
        int wifiState = this.mWifiManager.getWifiState();
        if (wifiState == 3) {
            this.mWifiManager.startScan();
            this.mHandler.sendEmptyMessageDelayed(104, 10000);
        } else if ((wifiState == 4 || wifiState == 1) && wifiState == 1) {
            this.mWifiManager.setWifiEnabled(true);
        }
        this.mState = State.SCANNING;
    }

    /* access modifiers changed from: private */
    public void o() {
        if (this.mPosition != -1 && this.mState == State.INPUT && (this.E.getVisibility() == 8 || this.B.getVisibility() == 0 || !TextUtils.isEmpty(this.A.getText().toString()))) {
            this.w.setEnabled(true);
        } else {
            this.w.setEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public void a(ScanResult scanResult) {
        if (scanResult == null) {
            this.E.setVisibility(8);
            this.C.setVisibility(8);
        } else if (WifiSettingUtils.a(scanResult) == 0) {
            this.E.setVisibility(8);
            this.C.setVisibility(8);
            this.mChooseNopasswdWifi = true;
        } else {
            this.mChooseNopasswdWifi = false;
            WifiAccount.WifiItem a2 = WifiAccountManager.a().a(scanResult.BSSID);
            if (this.mIsMiui && queryWifiPasswd(scanResult.SSID)) {
                b(false);
            } else if (a2 != null) {
                this.mPasswd = a2.d;
                b(false);
            } else {
                b(true);
            }
        }
    }

    private void b(boolean z2) {
        this.E.setVisibility(0);
        this.C.setVisibility(0);
        this.mIsShowPasswd = z2;
        if (!z2) {
            this.B.setText("123456");
            this.B.setVisibility(0);
            this.A.setVisibility(8);
            this.K.setEnabled(false);
            this.w.setEnabled(true);
        } else {
            this.A.setVisibility(0);
            this.B.setVisibility(8);
            this.K.setEnabled(true);
            this.w.setEnabled(false);
        }
        if (!this.av) {
            this.av = true;
            startActivity(new Intent(this, BlankActivity.class));
        }
    }

    /* access modifiers changed from: protected */
    public void connectXiaomiRouter(String str, String str2, String str3, boolean z2) {
        WifiConfiguration wifiConfiguration;
        boolean z3;
        boolean z4;
        if (this.mWifiManager.isWifiEnabled()) {
            Iterator<WifiConfiguration> it = this.mWifiManager.getConfiguredNetworks().iterator();
            while (true) {
                if (!it.hasNext()) {
                    wifiConfiguration = null;
                    break;
                }
                wifiConfiguration = it.next();
                if (wifiConfiguration.SSID != null && wifiConfiguration.SSID.equals(WifiSettingUtils.c(str)) && WifiSettingUtils.a(wifiConfiguration) == WifiSettingUtils.a(str3)) {
                    break;
                }
            }
            if (wifiConfiguration == null) {
                WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
                WifiSettingUtils.a(wifiConfiguration2, str, str2, str3);
                int addNetwork = this.mWifiManager.addNetwork(wifiConfiguration2);
                if (Build.VERSION.SDK_INT > 9) {
                    Class<?> cls = Class.forName(WifiManager.class.getName());
                    Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls.getName());
                    Method[] methods = cls.getMethods();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= methods.length) {
                            z3 = false;
                            break;
                        } else if ((methods[i2].getName().equalsIgnoreCase("connect") || methods[i2].getName().equalsIgnoreCase("connectNetwork")) && methods[i2].getParameterTypes()[0].getName().equals("int")) {
                            z3 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (z3) {
                        methods[i2].setAccessible(true);
                        if (!methods[i2].getName().equalsIgnoreCase("connect")) {
                            methods[i2].invoke(this.mWifiManager, new Object[]{Integer.valueOf(addNetwork)});
                        } else if (methods[i2].getParameterTypes().length > 2) {
                            methods[i2].invoke(this.mWifiManager, new Object[]{null, Integer.valueOf(addNetwork), null});
                        } else {
                            methods[i2].invoke(this.mWifiManager, new Object[]{Integer.valueOf(addNetwork), null});
                        }
                    } else {
                        this.mWifiManager.enableNetwork(addNetwork, true);
                    }
                } else {
                    this.mWifiManager.enableNetwork(addNetwork, true);
                }
            } else if (Build.VERSION.SDK_INT > 9) {
                try {
                    Class<?> cls2 = Class.forName(WifiManager.class.getName());
                    Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls2.getName());
                    Method[] methods2 = cls2.getMethods();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= methods2.length) {
                            z4 = false;
                            break;
                        } else if ((methods2[i3].getName().equalsIgnoreCase("connect") || methods2[i3].getName().equalsIgnoreCase("connectNetwork")) && methods2[i3].getParameterTypes()[0].getName().equals("int")) {
                            z4 = true;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (z4) {
                        methods2[i3].setAccessible(true);
                        if (!methods2[i3].getName().equalsIgnoreCase("connect")) {
                            methods2[i3].invoke(this.mWifiManager, new Object[]{Integer.valueOf(wifiConfiguration.networkId)});
                        } else if (methods2[i3].getParameterTypes().length > 2) {
                            methods2[i3].invoke(this.mWifiManager, new Object[]{null, Integer.valueOf(wifiConfiguration.networkId), null});
                        } else {
                            methods2[i3].invoke(this.mWifiManager, new Object[]{Integer.valueOf(wifiConfiguration.networkId), null});
                        }
                    } else {
                        this.mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                    }
                } catch (Exception unused) {
                }
            } else {
                this.mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
            }
            this.mHandler.sendEmptyMessageDelayed(103, 30000);
        }
    }

    /* access modifiers changed from: protected */
    public void connectToWifi(String str) {
        ScanResult scanResult;
        WifiConfiguration wifiConfiguration;
        int i2;
        boolean z2;
        boolean z3;
        if (this.mWifiManager.isWifiEnabled()) {
            InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService("input_method");
            if (inputMethodManager.isActive() && getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            }
            this.mState = State.CONNECTTING;
            this.A.setEnabled(false);
            this.z.setEnabled(false);
            findViewById(R.id.wifi_ssid_toggle).setEnabled(false);
            this.w.setEnabled(false);
            this.K.setEnabled(false);
            if (this.mPosition != -1) {
                if (this.mChooseUnconnWifi) {
                    scanResult = this.mUnconnectResult.get(this.mPosition).f22964a;
                } else {
                    scanResult = this.mScanResult.get(this.mPosition).f22964a;
                }
                if (!this.mWifiManager.isWifiEnabled()) {
                    this.mWifiManager.setWifiEnabled(true);
                    this.mState = State.RESCANNING;
                    return;
                }
                Iterator<WifiConfiguration> it = this.mWifiManager.getConfiguredNetworks().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        wifiConfiguration = null;
                        break;
                    }
                    wifiConfiguration = it.next();
                    if (wifiConfiguration.SSID != null && wifiConfiguration.SSID.equals(WifiSettingUtils.c(scanResult.SSID)) && WifiSettingUtils.a(wifiConfiguration) == WifiSettingUtils.a(scanResult)) {
                        break;
                    }
                }
                if (this.mWifiManager.getConnectionInfo() == null || this.mWifiManager.getConnectionInfo().getBSSID() == null) {
                    i2 = -1;
                } else {
                    i2 = -1;
                    for (int i3 = 0; i3 < this.y.size(); i3++) {
                        if (this.mWifiManager.getConnectionInfo().getBSSID().equalsIgnoreCase(this.y.get(i3).f22964a.BSSID)) {
                            i2 = i3;
                        }
                    }
                }
                if (wifiConfiguration != null) {
                    WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
                    if (connectionInfo != null && WifiSettingUtils.a(connectionInfo.getSSID(), scanResult.SSID) && connectionInfo.getNetworkId() != -1) {
                        r();
                        this.mHandler.sendEmptyMessageDelayed(108, 1000);
                        return;
                    } else if (Build.VERSION.SDK_INT > 9) {
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
                                if (!methods[i4].getName().equalsIgnoreCase("connect")) {
                                    methods[i4].invoke(this.mWifiManager, new Object[]{Integer.valueOf(wifiConfiguration.networkId)});
                                } else if (methods[i4].getParameterTypes().length > 2) {
                                    methods[i4].invoke(this.mWifiManager, new Object[]{null, Integer.valueOf(wifiConfiguration.networkId), null});
                                } else {
                                    methods[i4].invoke(this.mWifiManager, new Object[]{Integer.valueOf(wifiConfiguration.networkId), null});
                                }
                            } else {
                                this.mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                            }
                        } catch (Exception unused) {
                        }
                    } else {
                        this.mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                    }
                } else {
                    WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
                    a(wifiConfiguration2, scanResult, str);
                    int addNetwork = this.mWifiManager.addNetwork(wifiConfiguration2);
                    if (Build.VERSION.SDK_INT > 9) {
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
                            if (!methods2[i5].getName().equalsIgnoreCase("connect")) {
                                methods2[i5].invoke(this.mWifiManager, new Object[]{Integer.valueOf(addNetwork)});
                            } else if (methods2[i5].getParameterTypes().length > 2) {
                                methods2[i5].invoke(this.mWifiManager, new Object[]{null, Integer.valueOf(addNetwork), null});
                            } else {
                                methods2[i5].invoke(this.mWifiManager, new Object[]{Integer.valueOf(addNetwork), null});
                            }
                        } else {
                            this.mWifiManager.enableNetwork(addNetwork, true);
                        }
                    } else {
                        this.mWifiManager.enableNetwork(addNetwork, true);
                    }
                }
                if (i2 != -1) {
                    this.L = this.mWifiManager.getConnectionInfo().getNetworkId();
                }
                if (TextUtils.isEmpty(str)) {
                    this.mIsShowPasswd = false;
                } else {
                    this.mIsShowPasswd = true;
                }
                this.mHandler.sendEmptyMessageDelayed(103, 30000);
            }
        }
    }

    public void disableCurrentNetwork() {
        this.mWifiManager.disconnect();
    }

    public boolean connectToWifi(ScanResult scanResult, String str) {
        WifiConfiguration wifiConfiguration;
        boolean z2;
        boolean z3;
        if (!this.mWifiManager.isWifiEnabled()) {
            return false;
        }
        Iterator<WifiConfiguration> it = this.mWifiManager.getConfiguredNetworks().iterator();
        while (true) {
            if (!it.hasNext()) {
                wifiConfiguration = null;
                break;
            }
            wifiConfiguration = it.next();
            if (wifiConfiguration.SSID != null && wifiConfiguration.SSID.equals(WifiSettingUtils.c(scanResult.SSID)) && WifiSettingUtils.a(wifiConfiguration) == WifiSettingUtils.a(scanResult)) {
                break;
            }
        }
        if (wifiConfiguration == null) {
            WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
            a(wifiConfiguration2, scanResult, str);
            int addNetwork = this.mWifiManager.addNetwork(wifiConfiguration2);
            if (Build.VERSION.SDK_INT <= 9 || Build.VERSION.SDK_INT >= 23) {
                this.mWifiManager.enableNetwork(addNetwork, true);
            } else {
                Class<?> cls = Class.forName(WifiManager.class.getName());
                Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls.getName());
                Method[] methods = cls.getMethods();
                int i2 = 0;
                while (true) {
                    if (i2 >= methods.length) {
                        z2 = false;
                        break;
                    } else if ((methods[i2].getName().equalsIgnoreCase("connect") || methods[i2].getName().equalsIgnoreCase("connectNetwork")) && methods[i2].getParameterTypes()[0].getName().equals("int")) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    methods[i2].setAccessible(true);
                    if (!methods[i2].getName().equalsIgnoreCase("connect")) {
                        methods[i2].invoke(this.mWifiManager, new Object[]{Integer.valueOf(addNetwork)});
                    } else if (methods[i2].getParameterTypes().length > 2) {
                        methods[i2].invoke(this.mWifiManager, new Object[]{null, Integer.valueOf(addNetwork), null});
                    } else {
                        methods[i2].invoke(this.mWifiManager, new Object[]{Integer.valueOf(addNetwork), null});
                    }
                } else {
                    this.mWifiManager.enableNetwork(addNetwork, true);
                }
            }
        } else if (Build.VERSION.SDK_INT <= 9 || Build.VERSION.SDK_INT >= 23) {
            this.mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
        } else {
            try {
                Class<?> cls2 = Class.forName(WifiManager.class.getName());
                Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls2.getName());
                Method[] methods2 = cls2.getMethods();
                int i3 = 0;
                while (true) {
                    if (i3 >= methods2.length) {
                        z3 = false;
                        break;
                    } else if ((methods2[i3].getName().equalsIgnoreCase("connect") || methods2[i3].getName().equalsIgnoreCase("connectNetwork")) && methods2[i3].getParameterTypes()[0].getName().equals("int")) {
                        z3 = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (z3) {
                    methods2[i3].setAccessible(true);
                    if (!methods2[i3].getName().equalsIgnoreCase("connect")) {
                        methods2[i3].invoke(this.mWifiManager, new Object[]{Integer.valueOf(wifiConfiguration.networkId)});
                    } else if (methods2[i3].getParameterTypes().length > 2) {
                        methods2[i3].invoke(this.mWifiManager, new Object[]{null, Integer.valueOf(wifiConfiguration.networkId), null});
                    } else {
                        methods2[i3].invoke(this.mWifiManager, new Object[]{Integer.valueOf(wifiConfiguration.networkId), null});
                    }
                } else {
                    this.mWifiManager.enableNetwork(wifiConfiguration.networkId, true);
                }
            } catch (Exception unused) {
            }
        }
        this.mHandler.sendEmptyMessageDelayed(103, 30000);
        return false;
    }

    private void p() {
        startConnection();
    }

    private boolean q() {
        try {
            Class<?> cls = Class.forName("com.miui.whetstone.WhetstoneManager");
            if (((Integer) cls.getDeclaredMethod("getSmartConfigLevel", new Class[0]).invoke((Object) null, new Object[0])).intValue() == 2) {
                this.mWhetstoneClass = cls;
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void r() {
        ScanResult scanResult;
        if (this.mIsMiui && !this.mIsShowPasswd && !this.mChooseNopasswdWifi && this.mRouterInfo == null) {
            return;
        }
        if (this.mRouterInfo != null) {
            RouterRemoteApi.WifiInfo wifiInfo = null;
            Iterator<RouterRemoteApi.WifiInfo> it = this.mRouterInfo.f16425a.iterator();
            while (it.hasNext()) {
                RouterRemoteApi.WifiInfo next = it.next();
                if (next.f16426a > 0 && next.f16426a < 20) {
                    wifiInfo = next;
                }
            }
            WifiAccount.WifiItem wifiItem = new WifiAccount.WifiItem();
            wifiItem.e = WifiUtil.b(this);
            if (wifiItem.e == null) {
                wifiItem.e = "";
            }
            wifiItem.c = wifiInfo.c;
            if (wifiInfo.g.equalsIgnoreCase("psk2")) {
                wifiItem.f = "[WPA2-PSK-CCMP]";
            } else if (wifiInfo.g.equalsIgnoreCase("mixed-psk")) {
                wifiItem.f = "[WPA-PSK-CCMP+TKIP]";
            } else {
                wifiItem.f = "";
            }
            wifiItem.d = wifiInfo.e;
            wifiItem.b = true;
            wifiItem.f11584a = this.mWifiManager.getConnectionInfo().getNetworkId();
            WifiAccountManager.a().a(wifiItem);
            return;
        }
        if (this.mChooseUnconnWifi) {
            scanResult = this.mUnconnectResult.get(this.mPosition).f22964a;
        } else {
            scanResult = this.mScanResult.get(this.mPosition).f22964a;
        }
        WifiAccount.WifiItem wifiItem2 = new WifiAccount.WifiItem();
        wifiItem2.e = scanResult.BSSID;
        if (wifiItem2.e == null) {
            wifiItem2.e = "";
        }
        wifiItem2.c = scanResult.SSID;
        wifiItem2.f = scanResult.capabilities;
        wifiItem2.d = this.mPasswd;
        wifiItem2.b = true;
        wifiItem2.f11584a = this.mWifiManager.getConnectionInfo().getNetworkId();
        WifiAccountManager.a().a(wifiItem2);
    }

    private void a(WifiConfiguration wifiConfiguration, ScanResult scanResult, String str) {
        int a2 = WifiSettingUtils.a(scanResult);
        wifiConfiguration.SSID = WifiSettingUtils.c(scanResult.SSID);
        wifiConfiguration.BSSID = scanResult.BSSID;
        switch (a2) {
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
        WifiSettingUtils.TKIPType b2 = WifiSettingUtils.b(scanResult);
        if (b2 == WifiSettingUtils.TKIPType.TKIP_CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (b2 == WifiSettingUtils.TKIPType.TKIP) {
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (b2 == WifiSettingUtils.TKIPType.CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
        }
        wifiConfiguration.status = 0;
    }

    /* access modifiers changed from: package-private */
    public boolean queryWifiPasswd(String str) {
        WifiConfiguration wifiConfiguration;
        Iterator<WifiConfiguration> it = this.mWifiManager.getConfiguredNetworks().iterator();
        while (true) {
            if (!it.hasNext()) {
                wifiConfiguration = null;
                break;
            }
            wifiConfiguration = it.next();
            if (wifiConfiguration.SSID.equalsIgnoreCase(WifiSettingUtils.c(str))) {
                break;
            }
        }
        return wifiConfiguration != null;
    }

    public static void startConnectWifi(int i2, WifiManager wifiManager) {
        boolean z2;
        if (Build.VERSION.SDK_INT > 10) {
            try {
                Class<?> cls = Class.forName(WifiManager.class.getName());
                Log.i(Constants.Plugin.PLUGINID_WEBVIEW, "class3:" + cls.getName());
                Method[] methods = cls.getMethods();
                int i3 = 0;
                while (true) {
                    if (i3 >= methods.length) {
                        z2 = false;
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
                    if (methods[i3].getName().equalsIgnoreCase("connect")) {
                        methods[i3].invoke(wifiManager, new Object[]{Integer.valueOf(i2), null});
                        return;
                    }
                    methods[i3].invoke(wifiManager, new Object[]{Integer.valueOf(i2)});
                }
            } catch (Exception unused) {
            }
        } else {
            wifiManager.enableNetwork(i2, true);
        }
    }

    class ViewTag {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22873a;
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
            return BaseWifiSetting.this.mScanResult.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(BaseWifiSetting.this).inflate(R.layout.kuailian_wifi_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f22873a = (TextView) view.findViewById(R.id.wifi_name);
                viewTag.b = (ImageView) view.findViewById(R.id.miwif_tag);
                viewTag.d = (TextView) view.findViewById(R.id.security_name);
                viewTag.c = (ImageView) view.findViewById(R.id.wifi_signal_level);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            viewTag2.f22873a.setText(BaseWifiSetting.this.mScanResult.get(i).f22964a.SSID);
            if (BaseWifiSetting.this.mScanResult.get(i).c) {
                viewTag2.b.setVisibility(0);
            } else {
                viewTag2.b.setVisibility(8);
            }
            viewTag2.d.setText(WifiSettingUtils.a((Context) BaseWifiSetting.this, BaseWifiSetting.this.mScanResult.get(i)));
            viewTag2.c.setImageResource(WifiSettingUtils.a(WifiSettingUtils.a(BaseWifiSetting.this.mScanResult.get(i).f22964a.level, 100)));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(BaseWifiSetting.this.mTargetBssid) && !BaseWifiSetting.this.mTargetBssid.equalsIgnoreCase(BaseWifiSetting.this.mScanResult.get(i).f22964a.BSSID)) {
                        new MLAlertDialog.Builder(BaseWifiSetting.this).b((int) R.string.kuailian_conn_error_router).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (BaseWifiSetting.this.mPosition != i) {
                                    BaseWifiSetting.this.A.setText("");
                                }
                                BaseWifiSetting.this.mPosition = i;
                                BaseWifiSetting.this.z.setText(BaseWifiSetting.this.mScanResult.get(i).f22964a.SSID);
                                BaseWifiSetting.this.a(BaseWifiSetting.this.mScanResult.get(BaseWifiSetting.this.mPosition).f22964a);
                                BaseWifiSetting.this.o();
                                BaseWifiSetting.this.mChooseUnconnWifi = false;
                                BaseWifiSetting.this.hideWifiList();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).d();
                    } else if (WifiSettingUtils.a(BaseWifiSetting.this.mScanResult.get(i).f22964a) == 0) {
                        new MLAlertDialog.Builder(BaseWifiSetting.this).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (BaseWifiSetting.this.mPosition != i) {
                                    BaseWifiSetting.this.A.setText("");
                                }
                                BaseWifiSetting.this.mPosition = i;
                                BaseWifiSetting.this.z.setText(BaseWifiSetting.this.mScanResult.get(i).f22964a.SSID);
                                BaseWifiSetting.this.a(BaseWifiSetting.this.mScanResult.get(BaseWifiSetting.this.mPosition).f22964a);
                                BaseWifiSetting.this.o();
                                BaseWifiSetting.this.hideWifiList();
                                BaseWifiSetting.this.mChooseUnconnWifi = false;
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).d();
                    } else {
                        if (BaseWifiSetting.this.mPosition != i) {
                            BaseWifiSetting.this.A.setText("");
                        }
                        BaseWifiSetting.this.mPosition = i;
                        BaseWifiSetting.this.z.setText(BaseWifiSetting.this.mScanResult.get(i).f22964a.SSID);
                        BaseWifiSetting.this.a(BaseWifiSetting.this.mScanResult.get(BaseWifiSetting.this.mPosition).f22964a);
                        BaseWifiSetting.this.o();
                        BaseWifiSetting.this.mChooseUnconnWifi = false;
                        BaseWifiSetting.this.hideWifiList();
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
            return BaseWifiSetting.this.mUnconnectResult.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(BaseWifiSetting.this).inflate(R.layout.kuailian_wifi_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f22873a = (TextView) view.findViewById(R.id.wifi_name);
                viewTag.b = (ImageView) view.findViewById(R.id.miwif_tag);
                viewTag.d = (TextView) view.findViewById(R.id.security_name);
                viewTag.c = (ImageView) view.findViewById(R.id.wifi_signal_level);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            viewTag2.f22873a.setText(BaseWifiSetting.this.mUnconnectResult.get(i).f22964a.SSID);
            viewTag2.f22873a.setTextColor(BaseWifiSetting.this.getResources().getColor(R.color.class_text_16));
            if (BaseWifiSetting.this.mUnconnectResult.get(i).c) {
                viewTag2.b.setVisibility(0);
            } else {
                viewTag2.b.setVisibility(8);
            }
            viewTag2.c.setImageResource(WifiSettingUtils.a(WifiSettingUtils.a(BaseWifiSetting.this.mUnconnectResult.get(i).f22964a.level, 100)));
            viewTag2.d.setText(WifiSettingUtils.a((Context) BaseWifiSetting.this, BaseWifiSetting.this.mUnconnectResult.get(i)));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ScanResult scanResult = BaseWifiSetting.this.mUnconnectResult.get(i).f22964a;
                    if (scanResult.frequency > 5000 || scanResult.capabilities.contains("WEP") || scanResult.capabilities.contains("EAP") || scanResult.capabilities.contains("WAPI-KEY") || scanResult.capabilities.contains("WAPI-PSK") || scanResult.capabilities.contains("WAPI-CERT") || scanResult.level == 0 || DeviceFactory.e(scanResult)) {
                        new MLAlertDialog.Builder(BaseWifiSetting.this).b((int) R.string.kuailian_unconn_reason).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) null).d();
                    } else if (BaseWifiSetting.this.a(BaseWifiSetting.this.mUnconnectResult.get(i).f22964a.SSID)) {
                        new MLAlertDialog.Builder(BaseWifiSetting.this).a((int) R.string.kuailian_contain_unsupport_char).b((int) R.string.kuailian_unsafe_wifi_content).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (BaseWifiSetting.this.mPosition != i) {
                                    BaseWifiSetting.this.A.setText("");
                                }
                                BaseWifiSetting.this.mPosition = i;
                                BaseWifiSetting.this.z.setText(BaseWifiSetting.this.mUnconnectResult.get(i).f22964a.SSID);
                                BaseWifiSetting.this.a(BaseWifiSetting.this.mUnconnectResult.get(BaseWifiSetting.this.mPosition).f22964a);
                                BaseWifiSetting.this.o();
                                BaseWifiSetting.this.hideWifiList();
                                BaseWifiSetting.this.mChooseUnconnWifi = true;
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).d();
                    } else {
                        new MLAlertDialog.Builder(BaseWifiSetting.this).a((int) R.string.kuailian_unsafe_wifi).b((int) R.string.kuailian_unsafe_wifi_content_1).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (BaseWifiSetting.this.mPosition != i) {
                                    BaseWifiSetting.this.A.setText("");
                                }
                                BaseWifiSetting.this.mPosition = i;
                                BaseWifiSetting.this.z.setText(BaseWifiSetting.this.mUnconnectResult.get(i).f22964a.SSID);
                                BaseWifiSetting.this.a(BaseWifiSetting.this.mUnconnectResult.get(BaseWifiSetting.this.mPosition).f22964a);
                                BaseWifiSetting.this.o();
                                BaseWifiSetting.this.hideWifiList();
                                BaseWifiSetting.this.mChooseUnconnWifi = true;
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
        this.F.setVisibility(0);
        this.F.startAnimation(AnimationUtils.loadAnimation(this, R.anim.v5_dialog_enter));
    }

    /* access modifiers changed from: package-private */
    public void hideWifiList() {
        this.F.setVisibility(8);
        this.F.startAnimation(AnimationUtils.loadAnimation(this, R.anim.v5_dialog_exit));
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public String getConnDeviceModel() {
        return this.mDeviceModel;
    }

    public void goCameraBarcodeConnection() {
        String str;
        String str2;
        ScanResult scanResult;
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager.isWifiEnabled() || this.mPosition != -1) {
            this.mStartTime = System.currentTimeMillis();
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null) {
                WifiAccount.WifiItem a2 = WifiAccountManager.a().a(connectionInfo.getBSSID());
                if (a2 != null) {
                    str = a2.c;
                    str2 = a2.d;
                } else {
                    if (this.mChooseUnconnWifi) {
                        scanResult = this.mUnconnectResult.get(this.mPosition).f22964a;
                    } else {
                        scanResult = this.mScanResult.get(this.mPosition).f22964a;
                    }
                    str = scanResult.SSID;
                    str2 = this.mPasswd;
                }
                Intent intent = new Intent(this, CameraBarcodeGenActivity.class);
                intent.putExtra(DeviceTagInterface.e, str);
                intent.putExtra("password", str2);
                startActivityForResult(intent, 1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 1) {
            return;
        }
        if (i3 != -1) {
            goBack(true, true);
        } else if (intent == null) {
            goBack(true, false);
        } else if ("retry".equals(intent.getStringExtra("ret"))) {
            this.mIsBarcodeConnection = true;
            this.mIsMiui = false;
            d();
        } else {
            goBack(true, false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean useMiuiKuailian() {
        return this.mIsMiui && !this.mIsShowPasswd && !this.mChooseNopasswdWifi;
    }

    public void getSelectSSIDAndPasswd(Map<String, String> map) {
        ScanResult scanResult;
        if (this.mRouterInfo != null) {
            RouterRemoteApi.WifiInfo wifiInfo = null;
            Iterator<RouterRemoteApi.WifiInfo> it = this.mRouterInfo.f16425a.iterator();
            while (it.hasNext()) {
                RouterRemoteApi.WifiInfo next = it.next();
                if (next.f16426a > 0 && next.f16426a < 20) {
                    wifiInfo = next;
                }
            }
            if (wifiInfo != null) {
                map.put(DeviceTagInterface.e, wifiInfo.c);
                map.put("passwd", wifiInfo.e);
                return;
            }
            return;
        }
        if (this.mChooseUnconnWifi) {
            scanResult = this.mUnconnectResult.get(this.mPosition).f22964a;
        } else {
            scanResult = this.mScanResult.get(this.mPosition).f22964a;
        }
        WifiAccount.WifiItem a2 = WifiAccountManager.a().a(scanResult.BSSID);
        if (a2 != null) {
            map.put(DeviceTagInterface.e, a2.c);
            map.put("passwd", a2.d);
            return;
        }
        map.put(DeviceTagInterface.e, scanResult.SSID);
        map.put("passwd", this.mPasswd);
    }
}
