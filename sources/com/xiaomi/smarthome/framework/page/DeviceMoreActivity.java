package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.IBleUpgradeController;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.model.SupportBleGatewayFirmwareVersion;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.BleMeshDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.framework.page.scene.DeviceSceneActivityNew;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.framework.update.ui.MiioUpgradeActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.light.group.LightGroupManageActivity;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.light.group.LightGroupSettingActivity;
import com.xiaomi.smarthome.miio.activity.BleUpgradeActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceMoreActivity extends BaseActivity implements Device.StateChangedListener {
    public static final String ARGS_BLUETOOTH_GATEWAY = "bluetooth_gateway";
    public static final String ARGS_DELETE_ENABLE = "delete_enable";
    public static final String ARGS_DEVICE_UNBIND_MESSAGE_DETAIL = "device_unbind_message_detail";
    public static final String ARGS_ENABLE_PRIVACY_SETTING = "enable_privacy_setting";
    public static final String ARGS_ENABLE_REMOVE_LICENSE = "enableRemoveLicense";
    public static final String ARGS_FIRMWARE_ENABLE = "firmware_enable";
    public static final String ARGS_HELP_ENABLE = "help_enable";
    public static final String ARGS_KEY_ABOUT_CLICK = "about_click";
    public static final String ARGS_KEY_DID = "did";
    public static final String ARGS_KEY_HELP_CLICK = "help_click";
    public static final String ARGS_KEY_SETTING_CLICK = "setting_click";
    public static final String ARGS_LICENSE_CONTENT = "licenseContent";
    public static final String ARGS_LICENSE_HTML = "licenseContentHtml";
    public static final String ARGS_LICENSE_HTML_RES = "licenseContentHtmlRes";
    public static final String ARGS_LICENSE_URI = "licenseContentUri";
    public static final String ARGS_MULTIKEY_SETTING_ENABLE = "multikey_setting_enable";
    public static final String ARGS_NEED_DELETE_SERVER_DATA = "need_delete_server_data";
    public static final String ARGS_PRIVACY_CONTENT = "privacyContent";
    public static final String ARGS_PRIVACY_HTML = "privacyContentHtml";
    public static final String ARGS_PRIVACY_HTML_RES = "privacyContentHtmlRes";
    public static final String ARGS_PRIVACY_URI = "privacyContentUri";
    public static final String ARGS_RESULT_REMOVE_LICENSE = "removedLicense";
    public static final String ARGS_SCENCE_SETTING_ENABLE = "scence_enable";
    public static final String ARGS_SECURITY_SETTING_ENABLE = "security_setting_enable";
    public static final String ARGS_SHARE_EBABLE = "share_enable";
    public static final String ARGS_SHORTCUT_ENABLE = "shortcut_enable";
    public static final String ARGS_TIMEZONE_ENABLE = "timezone_enable";
    public static final String ARGS_TITLE_NAME = "title_name";
    public static final String ARGS_UNBIND_ENABLE = "unbind_enable";
    public static final String ARGS_USE_DEFAULT_LICENSE = "useDefaultLicense";
    public static final String ARGS_USR_EXP_PLAN_CONTENT = "usrExpPlanContent";
    public static final String ARGS_USR_EXP_PLAN_RN_URI = "usrExpPlanContentRnUri";
    public static final String ARGS_USR_EXP_PLAN_URI = "usrExpPlanContentUri";
    public static final String MENUS = "menus";
    public static final String MENU_ITEMS = "menusItems";
    public static final String MENU_ITEM_NAME_VOICE_CONTROL = "voice_control";
    public static final String RESULT_KEY_FINISH = "result_data";
    public static final String RESULT_MENU_FINISH = "menu";
    public static final int RESULT_VAL_FINISH = 1;
    public static final String USE_DEAULT_MENUS = "useDefaultMenus";

    /* renamed from: a  reason: collision with root package name */
    private static final String f16765a = "device_more_activity_red_point";
    private static final String b = "ble_gateway";
    private static final int c = 1;
    private Map<String, Set<String>> d = new HashMap();
    String did;
    View mAbout;
    RelativeLayout mAuthManagerRL;
    View mBluetoothGateway;
    protected Context mContext;
    View mDefaultMenuContainer;
    View mDelete;
    protected Device mDevice;
    View mEmpty;
    boolean mFinishing;
    View mFirmware;
    View mFirmwareVersionInfo;
    boolean mHasNewFirmware = false;
    View mHelp;
    ArrayList<Intent> mIntents;
    View mLicenseAndPrivacy;
    View mLightGroup;
    LinearLayout mMenuContainer;
    ArrayList<IXmPluginHostActivity.MenuItemBase> mMenuItemList = new ArrayList<>();
    ArrayList<String> mMenus;
    TextView mNameView;
    String mNewFirmwareVer = "";
    View mNotificationQuickOpView;
    View mRename;
    View mReset;
    View mSetting;
    boolean mUseDefaultMenus = true;
    View mUsingHelp;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x055a  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0561  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0599  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x05a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r22) {
        /*
            r21 = this;
            r15 = r21
            super.onCreate(r22)
            r0 = 2130968622(0x7f04002e, float:1.7545903E38)
            r1 = 2130968625(0x7f040031, float:1.7545909E38)
            r15.overridePendingTransition(r0, r1)
            r15.mContext = r15
            r14 = 0
            r15.mFinishing = r14
            com.xiaomi.smarthome.miio.TitleBarUtil.a((android.app.Activity) r21)
            r0 = 2130903948(0x7f03038c, float:1.7414728E38)
            r15.setContentView(r0)
            android.content.Intent r0 = r21.getIntent()
            java.lang.String r1 = "did"
            java.lang.String r1 = r0.getStringExtra(r1)
            r15.did = r1
            java.lang.String r1 = "setting_click"
            android.os.Parcelable r1 = r0.getParcelableExtra(r1)
            android.content.Intent r1 = (android.content.Intent) r1
            java.lang.String r2 = "about_click"
            android.os.Parcelable r2 = r0.getParcelableExtra(r2)
            android.content.Intent r2 = (android.content.Intent) r2
            java.lang.String r3 = "help_click"
            android.os.Parcelable r3 = r0.getParcelableExtra(r3)
            android.content.Intent r3 = (android.content.Intent) r3
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r5 = r15.did
            com.xiaomi.smarthome.device.Device r4 = r4.b((java.lang.String) r5)
            r15.mDevice = r4
            com.xiaomi.smarthome.device.Device r4 = r15.mDevice
            if (r4 != 0) goto L_0x0064
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r5 = r15.did
            com.xiaomi.smarthome.device.Device r4 = r4.l((java.lang.String) r5)
            r15.mDevice = r4
            com.xiaomi.smarthome.device.Device r4 = r15.mDevice
            if (r4 != 0) goto L_0x0064
            r21.e()
            return
        L_0x0064:
            r21.a()
            r4 = 2131430969(0x7f0b0e39, float:1.8483654E38)
            android.view.View r4 = r15.findViewById(r4)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$1 r5 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$1
            r5.<init>()
            r4.setOnClickListener(r5)
            r4 = 2131430822(0x7f0b0da6, float:1.8483356E38)
            android.view.View r4 = r15.findViewById(r4)
            android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
            r15.mMenuContainer = r4
            android.widget.LinearLayout r4 = r15.mMenuContainer
            r13 = 8
            r4.setVisibility(r13)
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r21)
            r5 = 2131430828(0x7f0b0dac, float:1.8483368E38)
            r6 = 0
            r7 = 2130903843(0x7f030323, float:1.7414515E38)
            r8 = -1
            if (r1 == 0) goto L_0x00d0
            android.widget.LinearLayout r9 = r15.mMenuContainer
            r9.setVisibility(r14)
            android.view.View r9 = r4.inflate(r7, r6)
            r15.mSetting = r9
            android.view.View r9 = r15.mSetting
            android.view.View r9 = r9.findViewById(r5)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r10 = 2131494562(0x7f0c06a2, float:1.8612636E38)
            r9.setText(r10)
            android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
            android.content.res.Resources r10 = r21.getResources()
            r11 = 2131362571(0x7f0a030b, float:1.8344926E38)
            int r10 = r10.getDimensionPixelOffset(r11)
            r9.<init>(r8, r10)
            android.widget.LinearLayout r10 = r15.mMenuContainer
            android.view.View r11 = r15.mSetting
            r10.addView(r11, r9)
            android.view.View r9 = r15.mSetting
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$2 r10 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$2
            r10.<init>(r1)
            r9.setOnClickListener(r10)
        L_0x00d0:
            r1 = 2131363166(0x7f0a055e, float:1.8346133E38)
            if (r3 == 0) goto L_0x0135
            android.widget.LinearLayout r9 = r15.mMenuContainer
            r9.setVisibility(r14)
            android.view.View r9 = r4.inflate(r7, r6)
            r15.mHelp = r9
            com.xiaomi.smarthome.framework.page.SharedConfig r9 = new com.xiaomi.smarthome.framework.page.SharedConfig
            android.content.Context r10 = r15.mContext
            r9.<init>(r10)
            android.content.SharedPreferences r9 = r9.a()
            java.lang.String r10 = "is_watch_movie"
            boolean r9 = r9.getBoolean(r10, r14)
            r10 = 2131430973(0x7f0b0e3d, float:1.8483662E38)
            if (r9 != 0) goto L_0x0100
            android.view.View r9 = r15.mHelp
            android.view.View r9 = r9.findViewById(r10)
            r9.setVisibility(r14)
            goto L_0x0109
        L_0x0100:
            android.view.View r9 = r15.mHelp
            android.view.View r9 = r9.findViewById(r10)
            r9.setVisibility(r13)
        L_0x0109:
            android.view.View r9 = r15.mHelp
            android.view.View r9 = r9.findViewById(r5)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r10 = 2131494551(0x7f0c0697, float:1.8612614E38)
            r9.setText(r10)
            android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
            android.content.res.Resources r10 = r21.getResources()
            int r10 = r10.getDimensionPixelOffset(r1)
            r9.<init>(r8, r10)
            android.widget.LinearLayout r10 = r15.mMenuContainer
            android.view.View r11 = r15.mHelp
            r10.addView(r11, r9)
            android.view.View r9 = r15.mHelp
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$3 r10 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$3
            r10.<init>(r3)
            r9.setOnClickListener(r10)
        L_0x0135:
            if (r2 == 0) goto L_0x016e
            android.widget.LinearLayout r3 = r15.mMenuContainer
            r3.setVisibility(r14)
            android.view.View r3 = r4.inflate(r7, r6)
            r15.mAbout = r3
            android.view.View r3 = r15.mAbout
            android.view.View r3 = r3.findViewById(r5)
            android.widget.TextView r3 = (android.widget.TextView) r3
            r4 = 2131494543(0x7f0c068f, float:1.8612597E38)
            r3.setText(r4)
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams
            android.content.res.Resources r4 = r21.getResources()
            int r1 = r4.getDimensionPixelOffset(r1)
            r3.<init>(r8, r1)
            android.widget.LinearLayout r1 = r15.mMenuContainer
            android.view.View r4 = r15.mAbout
            r1.addView(r4, r3)
            android.view.View r1 = r15.mAbout
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$4 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$4
            r3.<init>(r2)
            r1.setOnClickListener(r3)
        L_0x016e:
            java.lang.String r1 = "useDefaultMenus"
            r2 = 1
            boolean r1 = r0.getBooleanExtra(r1, r2)
            r15.mUseDefaultMenus = r1
            r1 = 2131430824(0x7f0b0da8, float:1.848336E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mDefaultMenuContainer = r1
            java.lang.String r1 = "%s device.permit = %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Class r4 = r21.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r3[r14] = r4
            com.xiaomi.smarthome.device.Device r4 = r15.mDevice
            int r4 = r4.permitLevel
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            java.lang.String r1 = java.lang.String.format(r1, r3)
            com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog.c((java.lang.String) r1)
            boolean r1 = r15.mUseDefaultMenus
            if (r1 != 0) goto L_0x01a9
            android.view.View r1 = r15.mDefaultMenuContainer
            r1.setVisibility(r13)
        L_0x01a9:
            r1 = 2131431844(0x7f0b11a4, float:1.8485429E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mRename = r1
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            boolean r1 = r1.isOwner()
            if (r1 != 0) goto L_0x01bf
            android.view.View r1 = r15.mRename
            r1.setVisibility(r13)
        L_0x01bf:
            android.view.View r1 = r15.mRename
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$5 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$5
            r3.<init>()
            r1.setOnClickListener(r3)
            r1 = 2131431126(0x7f0b0ed6, float:1.8483972E38)
            android.view.View r1 = r15.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            r15.mNameView = r1
            r1 = 2131431019(0x7f0b0e6b, float:1.8483755E38)
            android.view.View r1 = r15.findViewById(r1)
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = com.xiaomi.smarthome.framework.page.DeviceMoreInnterActivity.visableFeedback(r3)
            if (r3 != 0) goto L_0x0202
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = com.xiaomi.smarthome.framework.page.DeviceMoreInnterActivity.visableNetwork(r3)
            if (r3 != 0) goto L_0x0202
            boolean r3 = com.xiaomi.smarthome.framework.page.DeviceMoreInnterActivity.visableSecurity(r0)
            if (r3 != 0) goto L_0x0202
            boolean r3 = com.xiaomi.smarthome.framework.page.DeviceMoreInnterActivity.visableTimezone(r0)
            if (r3 != 0) goto L_0x0202
            boolean r3 = com.xiaomi.smarthome.framework.page.DeviceMoreInnterActivity.visableShortcut(r0)
            if (r3 == 0) goto L_0x01fe
            goto L_0x0202
        L_0x01fe:
            r1.setVisibility(r13)
            goto L_0x020d
        L_0x0202:
            r1.setVisibility(r14)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$6 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$6
            r3.<init>(r0)
            r1.setOnClickListener(r3)
        L_0x020d:
            r1 = 2131432137(0x7f0b12c9, float:1.8486023E38)
            android.view.View r1 = r15.findViewById(r1)
            java.lang.String r3 = "scence_enable"
            boolean r3 = r0.getBooleanExtra(r3, r14)
            if (r3 == 0) goto L_0x0238
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isOwner()
            if (r3 == 0) goto L_0x0238
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isSubDevice()
            if (r3 != 0) goto L_0x0238
            r1.setVisibility(r14)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$7 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$7
            r3.<init>(r0)
            r1.setOnClickListener(r3)
            goto L_0x023b
        L_0x0238:
            r1.setVisibility(r13)
        L_0x023b:
            r1 = 2131431041(0x7f0b0e81, float:1.84838E38)
            android.view.View r1 = r15.findViewById(r1)
            java.lang.String r3 = "multikey_setting_enable"
            boolean r3 = r0.getBooleanExtra(r3, r14)
            if (r3 == 0) goto L_0x026a
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isOwner()
            if (r3 == 0) goto L_0x026a
            boolean r3 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.f((android.content.Context) r21)
            if (r3 != 0) goto L_0x025e
            boolean r3 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.o(r21)
            if (r3 == 0) goto L_0x026a
        L_0x025e:
            r1.setVisibility(r14)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$8 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$8
            r3.<init>()
            r1.setOnClickListener(r3)
            goto L_0x026d
        L_0x026a:
            r1.setVisibility(r13)
        L_0x026d:
            r1 = 2131428824(0x7f0b05d8, float:1.8479303E38)
            android.view.View r1 = r15.findViewById(r1)
            r1.setVisibility(r13)
            com.xiaomi.smarthome.frame.core.CoreApi r3 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.device.Device r4 = r15.mDevice
            java.lang.String r4 = r4.model
            com.xiaomi.smarthome.core.entity.plugin.PluginRecord r3 = r3.d((java.lang.String) r4)
            if (r3 == 0) goto L_0x029c
            com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo r3 = r3.c()
            if (r3 == 0) goto L_0x029c
            int r3 = r3.F()
            if (r3 != r2) goto L_0x029c
            r1.setVisibility(r14)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$9 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$9
            r3.<init>()
            r1.setOnClickListener(r3)
        L_0x029c:
            r1 = 2131432393(0x7f0b13c9, float:1.8486542E38)
            android.view.View r1 = r15.findViewById(r1)
            java.lang.String r3 = "share_enable"
            boolean r3 = r0.getBooleanExtra(r3, r2)
            com.xiaomi.smarthome.device.Device r4 = r15.mDevice
            boolean r4 = r4.canBeShared()
            com.xiaomi.smarthome.device.Device r5 = r15.mDevice
            boolean r5 = r5.isOwner()
            if (r5 == 0) goto L_0x02c7
            com.xiaomi.smarthome.device.Device r5 = r15.mDevice
            boolean r5 = r5.isSubDevice()
            if (r5 != 0) goto L_0x02c7
            if (r3 == 0) goto L_0x02c7
            if (r4 == 0) goto L_0x02c7
            r1.setVisibility(r14)
            goto L_0x02ca
        L_0x02c7:
            r1.setVisibility(r13)
        L_0x02ca:
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$10 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$10
            r3.<init>()
            r1.setOnClickListener(r3)
            r1 = 2131428845(0x7f0b05ed, float:1.8479346E38)
            android.view.View r1 = r15.findViewById(r1)
            com.xiaomi.smarthome.frame.core.CoreApi r3 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r3 = r3.D()
            if (r3 != 0) goto L_0x031c
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isOwner()
            if (r3 == 0) goto L_0x031c
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            java.lang.String r3 = r3.model
            boolean r3 = com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper.a(r3)
            if (r3 == 0) goto L_0x02f6
            goto L_0x031c
        L_0x02f6:
            r3 = 2131429623(0x7f0b08f7, float:1.8480924E38)
            android.view.View r3 = r1.findViewById(r3)
            android.widget.TextView r3 = (android.widget.TextView) r3
            com.xiaomi.smarthome.homeroom.HomeManager r4 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            boolean r4 = r4.h()
            if (r4 == 0) goto L_0x030d
            r4 = 2131498189(0x7f0c14cd, float:1.8619992E38)
            goto L_0x0310
        L_0x030d:
            r4 = 2131494573(0x7f0c06ad, float:1.8612658E38)
        L_0x0310:
            r3.setText(r4)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$11 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$11
            r3.<init>()
            r1.setOnClickListener(r3)
            goto L_0x031f
        L_0x031c:
            r1.setVisibility(r13)
        L_0x031f:
            r1 = 2131429243(0x7f0b077b, float:1.8480153E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mFirmware = r1
            android.view.View r1 = r15.mFirmware
            r1.setVisibility(r13)
            boolean r1 = r21.c()
            if (r1 == 0) goto L_0x0342
            android.view.View r1 = r15.mFirmware
            r1.setVisibility(r14)
            android.view.View r1 = r15.mFirmware
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$12 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$12
            r3.<init>()
            r1.setOnClickListener(r3)
        L_0x0342:
            r1 = 2131433792(0x7f0b1940, float:1.848938E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mFirmwareVersionInfo = r1
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            boolean r1 = r1 instanceof com.xiaomi.smarthome.device.MiTVDevice
            if (r1 != 0) goto L_0x0357
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            boolean r1 = r1 instanceof com.xiaomi.smarthome.miio.camera.match.CameraDevice
            if (r1 == 0) goto L_0x035c
        L_0x0357:
            android.view.View r1 = r15.mFirmware
            r1.setVisibility(r13)
        L_0x035c:
            r1 = 2131433115(0x7f0b169b, float:1.8488007E38)
            android.view.View r3 = r15.findViewById(r1)
            r15.mDelete = r3
            android.view.View r3 = r15.mDelete
            r3.setVisibility(r13)
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isOwner()
            if (r3 == 0) goto L_0x0392
            java.lang.String r3 = "unbind_enable"
            boolean r3 = r0.getBooleanExtra(r3, r2)
            if (r3 == 0) goto L_0x0392
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isSubDevice()
            if (r3 != 0) goto L_0x0392
            android.view.View r1 = r15.mDelete
            r1.setVisibility(r14)
            android.view.View r1 = r15.mDelete
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$13 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$13
            r3.<init>(r0)
            r1.setOnClickListener(r3)
            goto L_0x03c3
        L_0x0392:
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isFamily()
            if (r3 != 0) goto L_0x03a2
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = r3.isShared()
            if (r3 == 0) goto L_0x03c3
        L_0x03a2:
            java.lang.String r3 = "delete_enable"
            boolean r3 = r0.getBooleanExtra(r3, r2)
            if (r3 == 0) goto L_0x03c3
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            int r3 = r3.pid
            int r4 = com.xiaomi.smarthome.device.Device.PID_VIRTUAL_DEVICE
            if (r3 == r4) goto L_0x03c3
            android.view.View r3 = r15.mDelete
            r3.setVisibility(r14)
            android.view.View r1 = r15.findViewById(r1)
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$14 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$14
            r3.<init>()
            r1.setOnClickListener(r3)
        L_0x03c3:
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            boolean r1 = r1.isHomeShared()
            if (r1 == 0) goto L_0x03d0
            android.view.View r1 = r15.mDelete
            r1.setVisibility(r13)
        L_0x03d0:
            r1 = 2131431867(0x7f0b11bb, float:1.8485475E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mReset = r1
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            boolean r1 = r1 instanceof com.xiaomi.smarthome.device.MiioDeviceV2
            if (r1 == 0) goto L_0x03ec
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            com.xiaomi.smarthome.device.MiioDeviceV2 r1 = (com.xiaomi.smarthome.device.MiioDeviceV2) r1
            boolean r1 = r1.B
            if (r1 == 0) goto L_0x03ec
            android.view.View r1 = r15.mReset
            r1.setVisibility(r14)
        L_0x03ec:
            android.view.View r1 = r15.mReset
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$15 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$15
            r3.<init>()
            r1.setOnClickListener(r3)
            r1 = 2131433739(0x7f0b190b, float:1.8489272E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mUsingHelp = r1
            android.view.View r1 = r15.mUsingHelp
            r1.setVisibility(r13)
            java.lang.String r1 = "help_enable"
            boolean r1 = r0.getBooleanExtra(r1, r14)
            if (r1 == 0) goto L_0x041b
            android.view.View r1 = r15.mUsingHelp
            r1.setVisibility(r14)
            android.view.View r1 = r15.mUsingHelp
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$16 r3 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$16
            r3.<init>()
            r1.setOnClickListener(r3)
        L_0x041b:
            r1 = 2131427865(0x7f0b0219, float:1.8477358E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mBluetoothGateway = r1
            android.view.View r1 = r15.mBluetoothGateway
            r1.setVisibility(r13)
            com.xiaomi.smarthome.device.Device r1 = r15.mDevice
            boolean r1 = isSupportBleGateway(r1)
            if (r1 == 0) goto L_0x0460
            java.lang.String r1 = "bluetooth_gateway"
            boolean r1 = r0.getBooleanExtra(r1, r2)
            if (r1 == 0) goto L_0x0460
            r1 = 2131427854(0x7f0b020e, float:1.8477336E38)
            android.view.View r1 = r15.findViewById(r1)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            com.xiaomi.smarthome.device.Device r3 = r15.mDevice
            boolean r3 = getBleGatewayRedPointStatus(r3)
            if (r3 == 0) goto L_0x044e
            r1.setVisibility(r14)
            goto L_0x0451
        L_0x044e:
            r1.setVisibility(r13)
        L_0x0451:
            android.view.View r3 = r15.mBluetoothGateway
            r3.setVisibility(r14)
            android.view.View r3 = r15.mBluetoothGateway
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$17 r4 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$17
            r4.<init>(r1)
            r3.setOnClickListener(r4)
        L_0x0460:
            java.lang.String r1 = "enable_privacy_setting"
            boolean r3 = r0.getBooleanExtra(r1, r14)
            r1 = 2131430426(0x7f0b0c1a, float:1.8482553E38)
            android.view.View r1 = r15.findViewById(r1)
            r15.mLicenseAndPrivacy = r1
            java.lang.String r1 = "enableRemoveLicense"
            boolean r1 = r0.getBooleanExtra(r1, r14)
            java.lang.String r4 = "useDefaultLicense"
            boolean r6 = r0.getBooleanExtra(r4, r14)
            java.lang.String r4 = "need_delete_server_data"
            boolean r5 = r0.getBooleanExtra(r4, r2)
            java.lang.String r2 = "licenseContent"
            java.lang.CharSequence r2 = r0.getCharSequenceExtra(r2)
            r11 = r2
            android.text.Spanned r11 = (android.text.Spanned) r11
            java.lang.String r2 = "privacyContent"
            java.lang.CharSequence r2 = r0.getCharSequenceExtra(r2)
            r12 = r2
            android.text.Spanned r12 = (android.text.Spanned) r12
            java.lang.String r2 = "usrExpPlanContent"
            java.lang.CharSequence r2 = r0.getCharSequenceExtra(r2)
            r4 = r2
            android.text.Spanned r4 = (android.text.Spanned) r4
            java.lang.String r2 = "licenseContentUri"
            java.lang.String r9 = r0.getStringExtra(r2)
            java.lang.String r2 = "privacyContentUri"
            java.lang.String r10 = r0.getStringExtra(r2)
            java.lang.String r2 = "usrExpPlanContentUri"
            java.lang.String r7 = r0.getStringExtra(r2)
            java.lang.String r2 = "licenseContentHtml"
            java.lang.String r16 = r0.getStringExtra(r2)
            java.lang.String r2 = "privacyContentHtml"
            java.lang.String r17 = r0.getStringExtra(r2)
            java.lang.String r2 = "licenseContentHtmlRes"
            int r18 = r0.getIntExtra(r2, r8)
            java.lang.String r2 = "privacyContentHtmlRes"
            int r19 = r0.getIntExtra(r2, r8)
            if (r1 == 0) goto L_0x0525
            if (r9 == 0) goto L_0x04cc
            if (r10 != 0) goto L_0x04da
        L_0x04cc:
            if (r16 == 0) goto L_0x04d0
            if (r17 != 0) goto L_0x04da
        L_0x04d0:
            if (r11 == 0) goto L_0x04d4
            if (r12 != 0) goto L_0x04da
        L_0x04d4:
            if (r18 <= 0) goto L_0x04d8
            if (r19 > 0) goto L_0x04da
        L_0x04d8:
            if (r6 == 0) goto L_0x0525
        L_0x04da:
            com.xiaomi.smarthome.device.Device r0 = r15.mDevice
            boolean r0 = r0.isOwner()
            if (r0 != 0) goto L_0x04ed
            com.xiaomi.smarthome.device.Device r0 = r15.mDevice
            boolean r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.f((com.xiaomi.smarthome.device.Device) r0)
            if (r0 == 0) goto L_0x04eb
            goto L_0x04ed
        L_0x04eb:
            r0 = r15
            goto L_0x0527
        L_0x04ed:
            if (r6 == 0) goto L_0x04fe
            r0 = 2131431595(0x7f0b10ab, float:1.8484924E38)
            android.view.View r0 = r15.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 2131494557(0x7f0c069d, float:1.8612626E38)
            r0.setText(r1)
        L_0x04fe:
            android.view.View r0 = r15.mLicenseAndPrivacy
            r0.setVisibility(r14)
            android.view.View r8 = r15.mLicenseAndPrivacy
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$18 r2 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$18
            r0 = r2
            r1 = r21
            r15 = r2
            r2 = r3
            r3 = r7
            r7 = r16
            r20 = r15
            r15 = r8
            r8 = r17
            r13 = r18
            r14 = r19
            r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r0 = r20
            r15.setOnClickListener(r0)
            r0 = r21
            r2 = 8
            goto L_0x052e
        L_0x0525:
            r0 = r21
        L_0x0527:
            android.view.View r1 = r0.mLicenseAndPrivacy
            r2 = 8
            r1.setVisibility(r2)
        L_0x052e:
            r21.intialUserMenus()
            r1 = 2131428767(0x7f0b059f, float:1.8479188E38)
            android.view.View r1 = r0.findViewById(r1)
            android.widget.RelativeLayout r1 = (android.widget.RelativeLayout) r1
            r0.mAuthManagerRL = r1
            java.util.Map<java.lang.String, java.util.Set<java.lang.String>> r1 = r0.d
            java.lang.String r3 = "voice_control"
            java.lang.Object r1 = r1.get(r3)
            if (r1 == 0) goto L_0x0561
            java.util.Map<java.lang.String, java.util.Set<java.lang.String>> r1 = r0.d
            java.lang.String r3 = "voice_control"
            java.lang.Object r1 = r1.get(r3)
            java.util.Set r1 = (java.util.Set) r1
            com.xiaomi.smarthome.device.Device r3 = r0.mDevice
            java.lang.String r3 = r3.model
            boolean r1 = r1.contains(r3)
            if (r1 == 0) goto L_0x0561
            android.widget.RelativeLayout r1 = r0.mAuthManagerRL
            r1.setVisibility(r2)
            r3 = 0
            goto L_0x0584
        L_0x0561:
            com.xiaomi.smarthome.device.authorization.DeviceAuthManager r1 = com.xiaomi.smarthome.device.authorization.DeviceAuthManager.a()
            java.lang.String r3 = r0.did
            boolean r1 = r1.b(r3)
            if (r1 == 0) goto L_0x057e
            android.widget.RelativeLayout r1 = r0.mAuthManagerRL
            r3 = 0
            r1.setVisibility(r3)
            android.widget.RelativeLayout r1 = r0.mAuthManagerRL
            com.xiaomi.smarthome.framework.page.DeviceMoreActivity$19 r2 = new com.xiaomi.smarthome.framework.page.DeviceMoreActivity$19
            r2.<init>()
            r1.setOnClickListener(r2)
            goto L_0x0584
        L_0x057e:
            r3 = 0
            android.widget.RelativeLayout r1 = r0.mAuthManagerRL
            r1.setVisibility(r2)
        L_0x0584:
            r1 = 2131430431(0x7f0b0c1f, float:1.8482563E38)
            android.view.View r1 = r0.findViewById(r1)
            r0.mLightGroup = r1
            com.xiaomi.smarthome.light.group.LightGroupManager r1 = com.xiaomi.smarthome.light.group.LightGroupManager.a()
            com.xiaomi.smarthome.device.Device r2 = r0.mDevice
            boolean r1 = r1.a((com.xiaomi.smarthome.device.Device) r2)
            if (r1 == 0) goto L_0x05a9
            android.view.View r1 = r0.mLightGroup
            r1.setVisibility(r3)
            android.view.View r1 = r0.mLightGroup
            com.xiaomi.smarthome.framework.page.-$$Lambda$DeviceMoreActivity$G2QUgb7rU3XlRelDc1MAX1Q6toE r2 = new com.xiaomi.smarthome.framework.page.-$$Lambda$DeviceMoreActivity$G2QUgb7rU3XlRelDc1MAX1Q6toE
            r2.<init>()
            r1.setOnClickListener(r2)
            goto L_0x05d3
        L_0x05a9:
            com.xiaomi.smarthome.light.group.LightGroupManager r1 = com.xiaomi.smarthome.light.group.LightGroupManager.a()
            com.xiaomi.smarthome.device.Device r2 = r0.mDevice
            boolean r1 = r1.b((com.xiaomi.smarthome.device.Device) r2)
            if (r1 == 0) goto L_0x05d3
            android.view.View r1 = r0.mLightGroup
            r1.setVisibility(r3)
            r1 = 2131433370(0x7f0b179a, float:1.8488524E38)
            android.view.View r1 = r0.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            r2 = 2131496112(0x7f0c0cb0, float:1.861578E38)
            r1.setText(r2)
            android.view.View r1 = r0.mLightGroup
            com.xiaomi.smarthome.framework.page.-$$Lambda$DeviceMoreActivity$_aQvQVjScQHvdguIim3X712zKGQ r2 = new com.xiaomi.smarthome.framework.page.-$$Lambda$DeviceMoreActivity$_aQvQVjScQHvdguIim3X712zKGQ
            r2.<init>()
            r1.setOnClickListener(r2)
        L_0x05d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.page.DeviceMoreActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        Intent intent = new Intent(this, LightGroupSettingActivity.class);
        intent.putExtra(LightGroupSettingActivity.ARGS_KEY_GROUP_MODEL, LightGroupManager.a().b(this.mDevice.model));
        intent.putExtra("did", this.did);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        Intent intent = new Intent(this, LightGroupManageActivity.class);
        intent.putExtra("did", this.did);
        startActivity(intent);
    }

    private void a() {
        HashSet hashSet = new HashSet();
        hashSet.add("chuangmi.radio.v1");
        hashSet.add("chuangmi.radio.v2");
        this.d.put(MENU_ITEM_NAME_VOICE_CONTROL, hashSet);
    }

    public static boolean isSupportBleGateway(Device device) {
        if (device == null) {
            return false;
        }
        PluginRecord d2 = CoreApi.a().d(device.model);
        if (!device.isOwner() || d2 == null || d2.c() == null || d2.c().J() != 1) {
            return false;
        }
        if (device instanceof MiioDeviceV2) {
            String str = ((MiioDeviceV2) device).D;
            List<SupportBleGatewayFirmwareVersion> f = AndroidCommonConfigManager.a().f();
            if (f != null && f.size() > 0 && !TextUtils.isEmpty(str)) {
                Iterator<SupportBleGatewayFirmwareVersion> it = f.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SupportBleGatewayFirmwareVersion next = it.next();
                    if (TextUtils.equals(next.f13958a, device.model)) {
                        if (BluetoothHelper.a(str, next.b) < 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static String a(String str, String str2) {
        return "ble_gateway_" + str + JSMethod.NOT_SET + str2;
    }

    /* access modifiers changed from: private */
    public static void a(Device device) {
        if (device != null) {
            String s = CoreApi.a().s();
            if (!TextUtils.isEmpty(s) && !TextUtils.equals(s, "0") && !TextUtils.isEmpty(device.did)) {
                SharePrefsManager.a(SHApplication.getAppContext(), f16765a).edit().putBoolean(a(s, device.did), false).apply();
            }
        }
    }

    public static boolean getBleGatewayRedPointStatus(Device device) {
        if (device == null) {
            return false;
        }
        String s = CoreApi.a().s();
        if (TextUtils.isEmpty(s) || TextUtils.equals(s, "0") || TextUtils.isEmpty(device.did)) {
            return false;
        }
        return SharePrefsManager.a(SHApplication.getAppContext(), f16765a).getBoolean(a(s, device.did), true);
    }

    /* access modifiers changed from: private */
    public void a(String str, int i) {
        Intent intent = new Intent(XmBluetoothManager.ACTION_RENAME_NOTIFY);
        intent.putExtra("did", this.did);
        intent.putExtra(XmBluetoothManager.EXTRA_NAME, str);
        intent.putExtra("extra.result", i);
        sendBroadcast(intent);
    }

    /* access modifiers changed from: package-private */
    public void intialUserMenus() {
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("menusItems");
        if (parcelableArrayListExtra != null && parcelableArrayListExtra.size() > 0) {
            this.mMenuItemList.addAll(parcelableArrayListExtra);
        }
        this.mMenus = getIntent().getStringArrayListExtra("menus");
        this.mIntents = getIntent().getParcelableArrayListExtra("intents");
        if (this.mMenus != null && this.mMenus.size() > 0) {
            Iterator<String> it = this.mMenus.iterator();
            while (it.hasNext()) {
                IXmPluginHostActivity.StringMenuItem stringMenuItem = new IXmPluginHostActivity.StringMenuItem();
                stringMenuItem.name = it.next();
                this.mMenuItemList.add(stringMenuItem);
            }
        }
        if (this.mIntents != null && this.mIntents.size() > 0) {
            Iterator<Intent> it2 = this.mIntents.iterator();
            while (it2.hasNext()) {
                Intent next = it2.next();
                IXmPluginHostActivity.IntentMenuItem intentMenuItem = new IXmPluginHostActivity.IntentMenuItem();
                intentMenuItem.name = next.getStringExtra("menu");
                intentMenuItem.intent = next;
                this.mMenuItemList.add(intentMenuItem);
            }
        }
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        String stringExtra = getIntent().getStringExtra("title_name");
        if (TextUtils.isEmpty(stringExtra)) {
            textView.setText(R.string.setting);
        } else {
            textView.setText(stringExtra);
        }
        LayoutInflater from = LayoutInflater.from(this);
        if (this.mMenuItemList == null || this.mMenuItemList.size() <= 0) {
            findViewById(R.id.menu_default_title).setVisibility(8);
            findViewById(R.id.list_space).setVisibility(8);
            return;
        }
        this.mMenuContainer.setVisibility(0);
        for (int i = 0; i < this.mMenuItemList.size(); i++) {
            final IXmPluginHostActivity.MenuItemBase menuItemBase = this.mMenuItemList.get(i);
            View view = null;
            if (menuItemBase instanceof IXmPluginHostActivity.StringMenuItem) {
                view = from.inflate(R.layout.common_device_more_menu_item, (ViewGroup) null);
                view.findViewById(R.id.menu_arrorw).setVisibility(0);
                ((TextView) view.findViewById(R.id.menu_item)).setText(((IXmPluginHostActivity.StringMenuItem) menuItemBase).name);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        DeviceMoreActivity.this.selectMenu(((IXmPluginHostActivity.StringMenuItem) menuItemBase).name);
                    }
                });
            } else if (menuItemBase instanceof IXmPluginHostActivity.IntentMenuItem) {
                view = from.inflate(R.layout.common_device_more_menu_item, (ViewGroup) null);
                IXmPluginHostActivity.IntentMenuItem intentMenuItem2 = (IXmPluginHostActivity.IntentMenuItem) menuItemBase;
                ((TextView) view.findViewById(R.id.menu_item)).setText(intentMenuItem2.name);
                if (intentMenuItem2.intent == null) {
                    view.findViewById(R.id.menu_arrorw).setVisibility(4);
                } else {
                    view.findViewById(R.id.menu_arrorw).setVisibility(0);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = ((IXmPluginHostActivity.IntentMenuItem) menuItemBase).intent;
                            switch (intent.getIntExtra("target_activity", -1)) {
                                case 1:
                                    Intent intent2 = new Intent(DeviceMoreActivity.this, DeviceSceneActivityNew.class);
                                    intent2.putExtras(intent);
                                    DeviceMoreActivity.this.startActivity(intent2);
                                    return;
                                case 2:
                                    FrameManager.b().k().openOpHistoryActivity(DeviceMoreActivity.this, DeviceMoreActivity.this.mDevice.did);
                                    return;
                                default:
                                    intent.putExtra(PluginHostActivity.JUMP_FORM_PLUGIN_HOST, true);
                                    DeviceMoreActivity.this.startActivity(intent);
                                    return;
                            }
                        }
                    });
                }
            } else if (menuItemBase instanceof IXmPluginHostActivity.SlideBtnMenuItem) {
                final IXmPluginHostActivity.SlideBtnMenuItem slideBtnMenuItem = (IXmPluginHostActivity.SlideBtnMenuItem) menuItemBase;
                view = from.inflate(R.layout.common_device_more_menu_slidebtn_item, (ViewGroup) null);
                TextView textView2 = (TextView) view.findViewById(R.id.menu_item);
                if (!TextUtils.isEmpty(slideBtnMenuItem.subName)) {
                    TextView textView3 = (TextView) view.findViewById(R.id.menu_subname);
                    textView3.setVisibility(0);
                    textView3.setText(slideBtnMenuItem.subName);
                }
                textView2.setText(slideBtnMenuItem.name);
                final SwitchButton switchButton = (SwitchButton) view.findViewById(R.id.menu_slide_btn);
                switchButton.setChecked(slideBtnMenuItem.isOn);
                switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                        String str;
                        String str2;
                        JSONArray jSONArray;
                        if (!z || !slideBtnMenuItem.isOn) {
                            if (!z) {
                                boolean z2 = slideBtnMenuItem.isOn;
                            }
                            if (z) {
                                str = slideBtnMenuItem.onParams;
                                str2 = slideBtnMenuItem.onMethod;
                            } else {
                                str = slideBtnMenuItem.offParams;
                                str2 = slideBtnMenuItem.offMethod;
                            }
                            String str3 = str2;
                            try {
                                jSONArray = new JSONArray(str);
                            } catch (JSONException unused) {
                                jSONArray = new JSONArray();
                                jSONArray.put(str);
                            }
                            final XQProgressDialog xQProgressDialog = new XQProgressDialog(DeviceMoreActivity.this);
                            xQProgressDialog.setMessage(DeviceMoreActivity.this.getString(R.string.processing));
                            xQProgressDialog.show();
                            XmPluginHostApi.instance().callMethod(DeviceMoreActivity.this.mDevice.did, str3, jSONArray, new Callback<Void>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    xQProgressDialog.dismiss();
                                    slideBtnMenuItem.isOn = z;
                                }

                                public void onFailure(int i, String str) {
                                    xQProgressDialog.dismiss();
                                    switchButton.setChecked(slideBtnMenuItem.isOn);
                                    Toast.makeText(DeviceMoreActivity.this, R.string.handle_error, 0).show();
                                }
                            }, (Parser) null);
                        }
                    }
                });
            } else if (menuItemBase instanceof IXmPluginHostActivity.BleMenuItem) {
                if (this.mMenuItemList.size() == 1) {
                    this.mMenuContainer.setVisibility(8);
                    findViewById(R.id.menu_default_title).setVisibility(8);
                }
                final IXmPluginHostActivity.BleMenuItem bleMenuItem = (IXmPluginHostActivity.BleMenuItem) menuItemBase;
                if (XmBluetoothManager.KEY_FIRMWARE_CLICK.equals(bleMenuItem.key)) {
                    this.mFirmware.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            IBleUpgradeController bleUpgrader = bleMenuItem.getBleUpgrader();
                            Intent intent = bleMenuItem.intent;
                            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_DID, DeviceMoreActivity.this.mDevice.did);
                            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_PID, DeviceMoreActivity.this.mDevice.pid);
                            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_NAME, DeviceMoreActivity.this.mDevice.name);
                            try {
                                if (bleUpgrader.onPreEnterActivity(intent.getExtras())) {
                                    return;
                                }
                            } catch (Throwable th) {
                                BluetoothLog.a(th);
                            }
                            Intent intent2 = new Intent(DeviceMoreActivity.this.mContext, BleUpgradeActivity.class);
                            intent2.putExtras(intent);
                            DeviceMoreActivity.this.startActivity(intent2);
                        }
                    });
                }
            } else if (menuItemBase instanceof IXmPluginHostActivity.InfoMenuItem) {
                view = from.inflate(R.layout.common_device_more_menu_item, (ViewGroup) null);
                ((TextView) view.findViewById(R.id.menu_item)).setText(((IXmPluginHostActivity.InfoMenuItem) menuItemBase).name);
                view.findViewById(R.id.menu_arrorw).setVisibility(4);
            }
            if (view != null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelOffset(R.dimen.menu_item_height));
                if (i == this.mMenuItemList.size() - 1) {
                    view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
                } else {
                    view.setBackgroundResource(R.drawable.common_white_list_padding);
                }
                this.mMenuContainer.addView(view, layoutParams);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void selectMenu(String str) {
        Intent intent = new Intent();
        intent.putExtra("menu", str);
        setResult(-1, intent);
        e();
    }

    private void b() {
        if (!ListUtils.a(this.mMenuItemList)) {
            IBleUpgradeController iBleUpgradeController = null;
            Iterator<IXmPluginHostActivity.MenuItemBase> it = this.mMenuItemList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                IXmPluginHostActivity.MenuItemBase next = it.next();
                if (next instanceof IXmPluginHostActivity.BleMenuItem) {
                    IXmPluginHostActivity.BleMenuItem bleMenuItem = (IXmPluginHostActivity.BleMenuItem) next;
                    if (XmBluetoothManager.KEY_FIRMWARE_CLICK.equals(bleMenuItem.key)) {
                        iBleUpgradeController = bleMenuItem.getBleUpgrader();
                        break;
                    }
                }
            }
            if (iBleUpgradeController != null) {
                try {
                    String currentVersion = iBleUpgradeController.getCurrentVersion();
                    if (TextUtils.isEmpty(currentVersion) && (this.mDevice instanceof BleMeshDevice)) {
                        currentVersion = ((BleMeshDevice) this.mDevice).D;
                    }
                    this.mHasNewFirmware = BluetoothHelper.a(iBleUpgradeController.getLatestVersion(), currentVersion) > 0;
                } catch (Exception e) {
                    BluetoothLog.a((Throwable) e);
                }
            }
        }
    }

    private boolean c() {
        if (!getIntent().getBooleanExtra(ARGS_FIRMWARE_ENABLE, true) || !this.mDevice.isOwner() || this.mDevice.isSubDevice() || this.mDevice.pid == Device.PID_VIRTUAL_DEVICE || LightGroupManager.a().b(this.mDevice)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mDevice.addStateChangedListener(this);
        if (c()) {
            if (this.mDevice.pid == Device.PID_BLUETOOTH || this.mDevice.pid == Device.PID_BLE_MESH) {
                b();
            } else if (this.mDevice.pid != Device.PID_VIRTUAL_DEVICE) {
                checkFirmwareVersion();
            }
        }
        refreshUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mDevice.removeStateChangedListener(this);
    }

    public void onBackPressed() {
        e();
    }

    public void onStateChanged(Device device) {
        refreshUI();
    }

    /* access modifiers changed from: package-private */
    public void checkFirmwareVersion() {
        if (this.mUseDefaultMenus) {
            UpdateApi.a().f(this, this.mDevice.did, this.mDevice.pid, new AsyncCallback<JSONObject, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    DeviceMoreActivity.this.mHasNewFirmware = !jSONObject.optBoolean("updating") && !jSONObject.optBoolean("isLatest");
                    jSONObject.optString("curr");
                    String optString = jSONObject.optString(Constants.PageFragment.PAGE_LATEST);
                    jSONObject.optString("description");
                    DeviceMoreActivity.this.mNewFirmwareVer = optString;
                    DeviceMoreActivity.this.refreshUI();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.mFinishing = true;
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }

    /* access modifiers changed from: private */
    public void e() {
        this.mFinishing = true;
        d();
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        this.mNameView.setText(this.mDevice.name);
        if (this.mHelp != null) {
            if (!new SharedConfig(this.mContext).a().getBoolean("is_watch_movie", false)) {
                this.mHelp.findViewById(R.id.module_a_3_return_more_new_btn).setVisibility(0);
            } else {
                this.mHelp.findViewById(R.id.module_a_3_return_more_new_btn).setVisibility(8);
            }
        }
        if (!this.mUseDefaultMenus || this.mFirmwareVersionInfo == null) {
            return;
        }
        if (this.mHasNewFirmware) {
            this.mFirmwareVersionInfo.setVisibility(0);
        } else {
            this.mFirmwareVersionInfo.setVisibility(8);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public static void openMoreMenu(Activity activity, String str, ArrayList<String> arrayList, ArrayList<Intent> arrayList2, boolean z, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, DeviceMoreActivity.class);
        intent.putExtra("did", str);
        if (arrayList != null) {
            intent.putStringArrayListExtra("menus", arrayList);
        }
        intent.putExtra("useDefaultMenus", z);
        if (arrayList2 != null) {
            intent.putParcelableArrayListExtra("intents", arrayList2);
        }
        activity.startActivityForResult(intent, i);
    }

    public static void openMoreMenu(Activity activity, String str, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, DeviceMoreActivity.class);
        intent.putExtra("did", str);
        if (arrayList != null) {
            intent.putParcelableArrayListExtra("menusItems", arrayList);
        }
        intent.putExtra("useDefaultMenus", z);
        activity.startActivityForResult(intent, i);
    }

    public static void openMoreMenu(Activity activity, String str, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        Intent intent2 = new Intent();
        intent2.setClass(activity, DeviceMoreActivity.class);
        intent2.putExtra("did", str);
        if (arrayList != null) {
            intent2.putParcelableArrayListExtra("menusItems", arrayList);
        }
        intent2.putExtra("useDefaultMenus", z);
        if (intent != null) {
            intent2.putExtras(intent);
        }
        activity.startActivityForResult(intent2, i);
    }

    public static void openMoreMenu(Activity activity, String str, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, String str2) {
        Intent intent2 = new Intent();
        intent2.setClass(activity, DeviceMoreActivity.class);
        intent2.putExtra("did", str);
        if (arrayList != null) {
            intent2.putParcelableArrayListExtra("menusItems", arrayList);
        }
        intent2.putExtra("useDefaultMenus", z);
        if (intent != null) {
            intent2.putExtras(intent);
        }
        intent2.putExtra(ARGS_DEVICE_UNBIND_MESSAGE_DETAIL, str2);
        activity.startActivityForResult(intent2, i);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(Constants.Event.FINISH, false)) {
            setResult(-1, intent);
            e();
        }
    }
}
