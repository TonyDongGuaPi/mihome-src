package com.xiaomi.smarthome.framework.page;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.app.Event;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.IBleUpgradeController;
import com.xiaomi.smarthome.bluetooth.ISlideBtnViewer;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.local.AlbumActivity;
import com.xiaomi.smarthome.camera.activity.setting.FileManagerSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.MoreCameraSettingActivity;
import com.xiaomi.smarthome.camera.activity.setting.VoiceBroadCastActivity;
import com.xiaomi.smarthome.device.BleMeshDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.feedback.FeedbackCommonProblemActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.scene.DeviceSceneActivityNew;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoWebActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceMoreNewActivity extends BaseActivity {
    public static final String ARGS_KEY_DID = "did";
    public static final String AUTO_DISMISS = "auto_dissmiss";
    public static final String COMMON_SETTING_PARAMS = "commonSettingParams";
    public static final String HAS_NEW_FIRM = "menusItems";
    public static final String MENUS = "menus";
    public static final String MENU_ITEMS = "menusItems";
    public static final String RESULT_MENU_FINISH = "menu";
    public static final String USE_DEAULT_MENUS = "useDefaultMenus";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static boolean f16812a = false;
    private final ISlideBtnViewer.Stub b = new ISlideBtnViewer.Stub() {
        public void setChecked(final String str, final boolean z) throws RemoteException {
            DeviceMoreNewActivity.this.mHandler.post(new Runnable() {
                public void run() {
                    Iterator<IXmPluginHostActivity.MenuItemBase> it = DeviceMoreNewActivity.this.mMenuItemList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        IXmPluginHostActivity.MenuItemBase next = it.next();
                        if (next instanceof IXmPluginHostActivity.SlideBtnMenuItem) {
                            IXmPluginHostActivity.SlideBtnMenuItem slideBtnMenuItem = (IXmPluginHostActivity.SlideBtnMenuItem) next;
                            BluetoothLog.c(String.format(">>> item %s, name %s", new Object[]{slideBtnMenuItem.name, str}));
                            if (slideBtnMenuItem.name.equals(str)) {
                                slideBtnMenuItem.isOn = z;
                                break;
                            }
                        }
                    }
                    DeviceMoreNewActivity.this.mListViewAdapter.notifyDataSetChanged();
                }
            });
        }
    };
    boolean mAutoDismiss;
    protected Context mContext;
    protected Device mDevice;
    View mEmpty;
    boolean mFinishing;
    boolean mHasCommonSettings = false;
    boolean mHasNewFirmware = false;
    LayoutInflater mLayoutInflater;
    ListView mListView;
    BaseAdapter mListViewAdapter;
    View mMainview = null;
    View mMainviewFrame = null;
    ArrayList<IXmPluginHostActivity.MenuItemBase> mMenuItemList = new ArrayList<>();
    Intent mPropIntent = new Intent();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ArrayList parcelableArrayListExtra;
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        this.mContext = this;
        this.mFinishing = false;
        setContentView(R.layout.device_more_activity_new);
        this.mLayoutInflater = LayoutInflater.from(this);
        this.mMainviewFrame = findViewById(R.id.device_more_frame);
        this.mMainview = findViewById(R.id.device_more);
        this.mMainview.setVisibility(4);
        TitleBarUtil.a(this.mMainview);
        final String stringExtra = getIntent().getStringExtra("did");
        this.mDevice = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.mDevice == null) {
            finish();
            return;
        }
        this.mAutoDismiss = getIntent().getBooleanExtra(AUTO_DISMISS, true);
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceMoreNewActivity.this.onBackPressed();
            }
        });
        this.mEmpty = findViewById(R.id.empty);
        this.mEmpty.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceMoreNewActivity.this.onBackPressed();
            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("menusItems") && (parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("menusItems")) != null && parcelableArrayListExtra.size() > 0) {
            this.mMenuItemList.addAll(parcelableArrayListExtra);
        }
        if (intent.getBooleanExtra("useDefaultMenus", true)) {
            if (intent.getBooleanExtra(DeviceMoreActivity.ARGS_SCENCE_SETTING_ENABLE, true) && this.mDevice.isOwner()) {
                IXmPluginHostActivity.IntentMenuItem intentMenuItem = new IXmPluginHostActivity.IntentMenuItem();
                intentMenuItem.name = getString(R.string.device_more_activity_scence);
                intentMenuItem.intent = new Intent(this, DeviceSceneActivityNew.class);
                intentMenuItem.intent.putExtra("device_id", stringExtra);
                this.mMenuItemList.add(intentMenuItem);
            }
            if (intent.getBooleanExtra(DeviceMoreActivity.ARGS_SHARE_EBABLE, true) && this.mDevice.isOwner() && !this.mDevice.isSubDevice() && this.mDevice.canBeShared()) {
                IXmPluginHostActivity.IntentMenuItem intentMenuItem2 = new IXmPluginHostActivity.IntentMenuItem();
                intentMenuItem2.name = getString(R.string.device_more_share);
                intentMenuItem2.intent = new Intent(this, ShareDeviceActivity.class);
                intentMenuItem2.intent.putExtra("user_id", CoreApi.a().s());
                intentMenuItem2.intent.putExtra("did", this.mDevice.did);
                this.mMenuItemList.add(intentMenuItem2);
            }
            if (!CoreApi.a().D() && intent.getBooleanExtra("cloud_storage", false) && !this.mDevice.isShared()) {
                IXmPluginHostActivity.IntentMenuItem intentMenuItem3 = new IXmPluginHostActivity.IntentMenuItem();
                intentMenuItem3.name = getString(R.string.cs_service_setting);
                intentMenuItem3.intent = new Intent(this, CloudVideoWebActivity.class);
                intentMenuItem3.intent.putExtra("title", getString(R.string.cs_my_service));
                intentMenuItem3.intent.putExtra("url", CloudVideoWebActivity.REQUEST_CLOUD_MANAGEMENT);
                intentMenuItem3.intent.putExtra("did", stringExtra);
                this.mMenuItemList.add(intentMenuItem3);
            }
            if (intent.getBooleanExtra("common_setting_enable", true)) {
                this.mHasCommonSettings = true;
                IXmPluginHostActivity.IntentMenuItem intentMenuItem4 = new IXmPluginHostActivity.IntentMenuItem();
                intentMenuItem4.name = getString(R.string.device_more_activity_common_setting);
                intentMenuItem4.intent = new Intent(this, DeviceMoreActivity.class);
                intentMenuItem4.intent.putExtra("did", stringExtra);
                intentMenuItem4.intent.putExtra("title_name", intentMenuItem4.name);
                intentMenuItem4.intent.putExtra("useDefaultMenus", true);
                intentMenuItem4.intent.putExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, intent.getBooleanExtra(DeviceMoreActivity.ARGS_SECURITY_SETTING_ENABLE, false));
                Intent intent2 = (Intent) intent.getParcelableExtra(COMMON_SETTING_PARAMS);
                if (intent2 == null) {
                    intent2 = new Intent();
                }
                intent2.putExtra(DeviceMoreActivity.ARGS_SHARE_EBABLE, false);
                intentMenuItem4.intent.putExtras(intent2);
                Iterator<IXmPluginHostActivity.MenuItemBase> it = this.mMenuItemList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    IXmPluginHostActivity.MenuItemBase next = it.next();
                    if (next instanceof IXmPluginHostActivity.BleMenuItem) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(next);
                        intentMenuItem4.intent.putExtra("menusItems", arrayList);
                        break;
                    }
                }
                this.mMenuItemList.add(intentMenuItem4);
            }
            if (intent.getBooleanExtra("help_feedback_enable", true)) {
                IXmPluginHostActivity.IntentMenuItem intentMenuItem5 = new IXmPluginHostActivity.IntentMenuItem();
                intentMenuItem5.name = getString(R.string.device_more_activity_help_feedback);
                intentMenuItem5.intent = new Intent(this, FeedbackCommonProblemActivity.class);
                intentMenuItem5.intent.putExtra("did", stringExtra);
                intentMenuItem5.intent.putExtra("extra_model", this.mDevice.model);
                this.mMenuItemList.add(intentMenuItem5);
            }
        }
        Iterator<IXmPluginHostActivity.MenuItemBase> it2 = this.mMenuItemList.iterator();
        while (it2.hasNext()) {
            if (it2.next() instanceof IXmPluginHostActivity.BleMenuItem) {
                it2.remove();
            }
        }
        this.mListView = (ListView) findViewById(R.id.select_dialog_listview);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            }
        });
        this.mListViewAdapter = new BaseAdapter() {
            public long getItemId(int i) {
                return (long) i;
            }

            public int getViewTypeCount() {
                return 2;
            }

            public int getCount() {
                return DeviceMoreNewActivity.this.mMenuItemList.size();
            }

            public Object getItem(int i) {
                return DeviceMoreNewActivity.this.mMenuItemList.get(i);
            }

            public int getItemViewType(int i) {
                return DeviceMoreNewActivity.this.mMenuItemList.get(i) instanceof IXmPluginHostActivity.SlideBtnMenuItem ? 0 : 1;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                IXmPluginHostActivity.MenuItemBase menuItemBase = DeviceMoreNewActivity.this.mMenuItemList.get(i);
                if (menuItemBase instanceof IXmPluginHostActivity.SlideBtnMenuItem) {
                    final IXmPluginHostActivity.SlideBtnMenuItem slideBtnMenuItem = (IXmPluginHostActivity.SlideBtnMenuItem) menuItemBase;
                    if (view == null) {
                        view = DeviceMoreNewActivity.this.mLayoutInflater.inflate(R.layout.menu_dialog_slidebtn_item, (ViewGroup) null);
                    }
                    TextView textView = (TextView) view.findViewById(R.id.text1);
                    textView.setText(slideBtnMenuItem.name);
                    final SwitchButton switchButton = (SwitchButton) view.findViewById(R.id.slide_btn);
                    if (!(DeviceMoreNewActivity.this.mDevice.pid == Device.PID_BLUETOOTH || DeviceMoreNewActivity.this.mDevice.pid == Device.PID_BLE_MESH || DeviceMoreNewActivity.this.mDevice.isOnline)) {
                        switchButton.setEnabled(false);
                        textView.setTextColor(DeviceMoreNewActivity.this.getResources().getColor(R.color.class_text_4));
                    }
                    switchButton.setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() != 0) {
                                return false;
                            }
                            slideBtnMenuItem.isClicked = true;
                            return false;
                        }
                    });
                    if (DeviceMoreNewActivity.this.mDevice.pid == Device.PID_BLUETOOTH || DeviceMoreNewActivity.this.mDevice.pid == Device.PID_BLE_MESH) {
                        switchButton.setOnCheckedChangeListener(new SlideListener(slideBtnMenuItem));
                    } else {
                        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                                if (slideBtnMenuItem.isClicked) {
                                    switchButton.setEnabled(false);
                                    JSONObject jSONObject = new JSONObject();
                                    try {
                                        jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
                                        if (z) {
                                            jSONObject.put("method", slideBtnMenuItem.onMethod);
                                            jSONObject.put("params", new JSONArray(slideBtnMenuItem.onParams));
                                        } else {
                                            jSONObject.put("method", slideBtnMenuItem.offMethod);
                                            jSONObject.put("params", new JSONArray(slideBtnMenuItem.offParams));
                                        }
                                    } catch (JSONException unused) {
                                    }
                                    CoreApi.a().b(DeviceMoreNewActivity.this.mDevice.did, DeviceMoreNewActivity.this.mDevice.token, jSONObject.toString(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                                        /* renamed from: a */
                                        public void onSuccess(JSONObject jSONObject) {
                                            switchButton.setEnabled(true);
                                            DeviceMoreNewActivity.this.mPropIntent.putExtra(slideBtnMenuItem.name, z);
                                        }

                                        public void onFailure(Error error) {
                                            switchButton.setEnabled(true);
                                            slideBtnMenuItem.isClicked = false;
                                            switchButton.setChecked(true ^ switchButton.isChecked());
                                            Toast.makeText(DeviceMoreNewActivity.this, R.string.device_more_activity_failed, 0).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                    switchButton.setChecked(slideBtnMenuItem.isOn);
                } else if (menuItemBase instanceof IXmPluginHostActivity.IntentMenuItem) {
                    final IXmPluginHostActivity.IntentMenuItem intentMenuItem = (IXmPluginHostActivity.IntentMenuItem) menuItemBase;
                    if (view == null) {
                        view = DeviceMoreNewActivity.this.mLayoutInflater.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                    }
                    ((TextView) view.findViewById(R.id.text1)).setText(intentMenuItem.name);
                    ImageView imageView = (ImageView) view.findViewById(R.id.red_point);
                    imageView.setVisibility(8);
                    if (intentMenuItem.intent != null) {
                        view.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                if (!(intentMenuItem == null || intentMenuItem.intent == null || intentMenuItem.intent.getComponent() == null || intentMenuItem.intent.getComponent().getClassName() == null)) {
                                    String className = intentMenuItem.intent.getComponent().getClassName();
                                    if (className.equalsIgnoreCase(AlbumActivity.class.getName())) {
                                        Event.a(Event.bd);
                                    } else if (className.equalsIgnoreCase(MoreCameraSettingActivity.class.getName()) || className.equalsIgnoreCase(com.xiaomi.smarthome.camera.v4.activity.setting.MoreCameraSettingActivity.class.getName())) {
                                        Event.a(Event.aZ);
                                    } else if (className.equalsIgnoreCase(AlarmSettingV2Activity.class.getName()) || className.equalsIgnoreCase(com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmSettingV2Activity.class.getName())) {
                                        Event.a(Event.ba);
                                    } else if (className.equalsIgnoreCase(FileManagerSettingActivity.class.getName()) || className.equalsIgnoreCase(com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.class.getName())) {
                                        Event.a(Event.bb);
                                    } else if (className.equalsIgnoreCase(DeviceSceneActivityNew.class.getName())) {
                                        Event.a(Event.bc);
                                    } else if (className.equalsIgnoreCase(ShareDeviceActivity.class.getName())) {
                                        Event.a(Event.be);
                                    } else if (className.equalsIgnoreCase(DeviceMoreActivity.class.getName())) {
                                        Event.a(Event.bg);
                                    } else if (className.equalsIgnoreCase(FeedbackCommonProblemActivity.class.getName())) {
                                        Event.a(Event.bh);
                                    } else if (className.equalsIgnoreCase(CloudVideoWebActivity.class.getName())) {
                                        Event.a(Event.bf);
                                    } else if (className.equalsIgnoreCase(VoiceBroadCastActivity.class.getName())) {
                                        Event.a(Event.bw);
                                    }
                                }
                                if (intentMenuItem.goBuyVip) {
                                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(DeviceMoreNewActivity.this);
                                    builder.b((int) R.string.face_need_open_cloud);
                                    builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    });
                                    builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            CloudVideoNetUtils.getInstance().openCloudVideoBuyPage(DeviceMoreNewActivity.this, stringExtra);
                                        }
                                    });
                                    builder.d();
                                    return;
                                }
                                DeviceMoreNewActivity.this.startActivityForResult(intentMenuItem.intent, 100);
                            }
                        });
                        if ((DeviceMoreNewActivity.this.mHasNewFirmware || DeviceMoreNewActivity.this.shouldShowBleGatewayRedPoint(DeviceMoreNewActivity.this.mDevice)) && intentMenuItem.intent.getComponent().getClassName().equals(DeviceMoreActivity.class.getName())) {
                            imageView.setVisibility(0);
                        }
                    }
                } else if (menuItemBase instanceof IXmPluginHostActivity.StringMenuItem) {
                    final IXmPluginHostActivity.StringMenuItem stringMenuItem = (IXmPluginHostActivity.StringMenuItem) menuItemBase;
                    if (view == null) {
                        view = DeviceMoreNewActivity.this.mLayoutInflater.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                    }
                    ((ImageView) view.findViewById(R.id.red_point)).setVisibility(8);
                    ((TextView) view.findViewById(R.id.text1)).setText(stringMenuItem.name);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.putExtra("menu", stringMenuItem.name);
                            intent.putExtras(DeviceMoreNewActivity.this.mPropIntent);
                            DeviceMoreNewActivity.this.setResult(-1, intent);
                            DeviceMoreNewActivity.this.d();
                        }
                    });
                } else if (menuItemBase instanceof IXmPluginHostActivity.InfoMenuItem) {
                    IXmPluginHostActivity.InfoMenuItem infoMenuItem = (IXmPluginHostActivity.InfoMenuItem) menuItemBase;
                    if (view == null) {
                        view = DeviceMoreNewActivity.this.mLayoutInflater.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                    }
                    ((ImageView) view.findViewById(R.id.red_point)).setVisibility(8);
                    ((TextView) view.findViewById(R.id.text1)).setText(infoMenuItem.name);
                }
                return view;
            }
        };
        this.mListView.setAdapter(this.mListViewAdapter);
        a(true);
    }

    public boolean shouldShowBleGatewayRedPoint(Device device) {
        if (device == null) {
            return false;
        }
        Intent intent = (Intent) getIntent().getParcelableExtra(COMMON_SETTING_PARAMS);
        if ((intent == null || intent.getBooleanExtra(DeviceMoreActivity.ARGS_BLUETOOTH_GATEWAY, true)) && DeviceMoreActivity.isSupportBleGateway(device) && DeviceMoreActivity.getBleGatewayRedPointStatus(device)) {
            return true;
        }
        return false;
    }

    private boolean a() {
        Intent intent = (Intent) getIntent().getParcelableExtra(COMMON_SETTING_PARAMS);
        if (!(intent != null ? intent.getBooleanExtra(DeviceMoreActivity.ARGS_FIRMWARE_ENABLE, true) : true) || !this.mDevice.isOwner() || this.mDevice.isSubDevice() || this.mDevice.pid == Device.PID_VIRTUAL_DEVICE) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (a()) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (DeviceMoreNewActivity.this.mDevice.pid == Device.PID_BLUETOOTH || DeviceMoreNewActivity.this.mDevice.pid == Device.PID_BLE_MESH) {
                        DeviceMoreNewActivity.this.b();
                    } else if (DeviceMoreNewActivity.this.mDevice.pid != Device.PID_VIRTUAL_DEVICE) {
                        DeviceMoreNewActivity.this.checkFirmwareVersion();
                    }
                }
            }, 100);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        Intent intent = getIntent();
        IBleUpgradeController iBleUpgradeController = null;
        ArrayList parcelableArrayListExtra = intent.hasExtra("menusItems") ? intent.getParcelableArrayListExtra("menusItems") : null;
        if (!ListUtils.a(parcelableArrayListExtra)) {
            Iterator it = parcelableArrayListExtra.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                IXmPluginHostActivity.MenuItemBase menuItemBase = (IXmPluginHostActivity.MenuItemBase) it.next();
                if (menuItemBase instanceof IXmPluginHostActivity.BleMenuItem) {
                    IXmPluginHostActivity.BleMenuItem bleMenuItem = (IXmPluginHostActivity.BleMenuItem) menuItemBase;
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
            this.mListViewAdapter.notifyDataSetChanged();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkFirmwareVersion() {
        if (this.mHasCommonSettings && this.mDevice.isOwner()) {
            UpdateApi.a().f(this, this.mDevice.did, this.mDevice.pid, new AsyncCallback<JSONObject, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    DeviceMoreNewActivity.this.mHasNewFirmware = !jSONObject.optBoolean("updating") && !jSONObject.optBoolean("isLatest");
                    DeviceMoreNewActivity.this.mListViewAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        a(false);
        super.onDestroy();
    }

    public void onBackPressed() {
        setResult(0, this.mPropIntent);
        d();
    }

    /* access modifiers changed from: private */
    public void c() {
        this.mFinishing = true;
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.mFinishing = true;
        if (ApiHelper.f18555a) {
            ObjectAnimator ofObject = ObjectAnimator.ofObject(this.mMainviewFrame, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_30_transparent)), Integer.valueOf(getResources().getColor(R.color.black_00_transparent))});
            ofObject.setDuration(300);
            ofObject.start();
        } else {
            this.mMainviewFrame.setBackgroundColor(getResources().getColor(R.color.black_00_transparent));
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_top);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                DeviceMoreNewActivity.this.c();
            }
        });
        this.mMainview.startAnimation(loadAnimation);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !this.mFinishing && !this.mMainview.isShown()) {
            if (ApiHelper.f18555a) {
                ObjectAnimator ofObject = ObjectAnimator.ofObject(this.mMainviewFrame, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(getResources().getColor(R.color.black_00_transparent)), Integer.valueOf(getResources().getColor(R.color.black_30_transparent))});
                ofObject.setDuration(300);
                ofObject.start();
            } else {
                this.mMainviewFrame.setBackgroundColor(getResources().getColor(R.color.black_30_transparent));
            }
            this.mMainview.setVisibility(0);
            this.mMainview.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_top));
        }
    }

    public static void openMoreMenu(Activity activity, String str, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        if (!f16812a) {
            f16812a = true;
            Intent intent2 = new Intent();
            intent2.setClass(activity, DeviceMoreNewActivity.class);
            intent2.putExtra("did", str);
            if (arrayList != null) {
                intent2.putParcelableArrayListExtra("menusItems", arrayList);
            }
            intent2.putExtra("useDefaultMenus", z);
            if (intent != null) {
                intent2.putExtras(intent);
            }
            activity.startActivityForResult(intent2, i);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    boolean unused = DeviceMoreNewActivity.f16812a = false;
                }
            }, 500);
        }
    }

    public static void openMoreMenu(Activity activity, String str, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, Intent intent2) {
        if (!f16812a) {
            f16812a = true;
            Intent intent3 = new Intent();
            intent3.setClass(activity, DeviceMoreNewActivity.class);
            intent3.putExtra("did", str);
            if (arrayList != null) {
                intent3.putParcelableArrayListExtra("menusItems", arrayList);
            }
            intent3.putExtra("useDefaultMenus", z);
            if (intent != null) {
                intent3.putExtras(intent);
            }
            if (intent2 != null) {
                intent3.putExtra(COMMON_SETTING_PARAMS, intent2);
            }
            activity.startActivityForResult(intent3, i);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    boolean unused = DeviceMoreNewActivity.f16812a = false;
                }
            }, 500);
        }
    }

    private void a(boolean z) {
        if (!ListUtils.a(this.mMenuItemList)) {
            Iterator<IXmPluginHostActivity.MenuItemBase> it = this.mMenuItemList.iterator();
            while (it.hasNext()) {
                IXmPluginHostActivity.MenuItemBase next = it.next();
                if (next instanceof IXmPluginHostActivity.SlideBtnMenuItem) {
                    IXmPluginHostActivity.SlideBtnMenuItem slideBtnMenuItem = (IXmPluginHostActivity.SlideBtnMenuItem) next;
                    if (slideBtnMenuItem.controller != null) {
                        if (z) {
                            try {
                                slideBtnMenuItem.controller.attachSlideBtnViewer(this.b);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            slideBtnMenuItem.controller.detachSlideBtnViewer();
                        }
                    }
                }
            }
        }
    }

    class SlideListener implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: a  reason: collision with root package name */
        IXmPluginHostActivity.SlideBtnMenuItem f16828a;

        SlideListener(IXmPluginHostActivity.SlideBtnMenuItem slideBtnMenuItem) {
            this.f16828a = slideBtnMenuItem;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.f16828a.controller != null) {
                try {
                    this.f16828a.isOn = z;
                    this.f16828a.controller.onCheckedChanged(this.f16828a.name, z);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(Constants.Event.FINISH, false)) {
            setResult(-1, intent);
            finish();
        } else if (this.mAutoDismiss) {
            finish();
        }
    }
}
