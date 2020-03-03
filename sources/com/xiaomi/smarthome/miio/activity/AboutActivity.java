package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.AuthManagerListActivity;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthMasterListActivity;
import com.xiaomi.smarthome.download.PluginAutoDownloadTask;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.redpoint.RedPointManagerNew;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.update.ui.UpdateActivity;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.international.ServerApi;
import com.xiaomi.smarthome.international.ServerHelper;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.messagecenter.MessageCenter;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyManagerActivity;
import com.xiaomi.smarthome.miio.page.deviceophistory.DeviceOpHistoryListActivity;
import com.xiaomi.smarthome.miio.page.devicetag.DeviceTagManagerActivity;
import com.xiaomi.smarthome.miio.page.msgcentersetting.MessageCenterSettingActivity;
import com.xiaomi.smarthome.miio.page.usrexpplan.PrivacySettingActivity;
import com.xiaomi.smarthome.miio.update.AppUpdateManger;
import com.xiaomi.smarthome.miio.update.ModelUpdateManager;
import com.xiaomi.smarthome.newui.HomeEditorActivity;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.notificationquickop.QuickOpDeviceSettingActivity;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.voice.VoiceManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AboutActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11630a = "AboutActivity";
    /* access modifiers changed from: private */
    public PopupWindow b;
    private View c;
    private TextView d;
    /* access modifiers changed from: private */
    public Dialog e;
    private Disposable f;
    /* access modifiers changed from: private */
    public SwitchButton g;
    boolean mAutoUpdate = true;
    SwitchButton mAutoUpdateBtn;
    boolean mAutoUpdateClicked = false;
    View mAutoUpdateLayer;
    ImageView mImgDotNew;
    TextView mLanguageTitle;
    ProductStat mProductStat;
    View mProductStatLayer;

    /* access modifiers changed from: private */
    public static /* synthetic */ void h() throws Exception {
    }

    private static class MyInternationalCallback implements ServerHelper.InternationalCallback {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<AboutActivity> f11661a;

        public void b() {
        }

        private MyInternationalCallback(AboutActivity aboutActivity) {
            this.f11661a = new WeakReference<>(aboutActivity);
        }

        public void a() {
            AboutActivity aboutActivity;
            if (this.f11661a != null && (aboutActivity = (AboutActivity) this.f11661a.get()) != null && aboutActivity.isValid()) {
                aboutActivity.c();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.about_layout);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.device_more_activity_setting);
        View findViewById = findViewById(R.id.region);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.al();
                ServerHelper.a(SHApplication.getAppContext(), 2, new MyInternationalCallback(), AboutActivity.class.getSimpleName());
            }
        });
        c();
        findViewById(R.id.check_update_new).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.startActivity(new Intent(AboutActivity.this, UpdateActivity.class));
            }
        });
        this.mAutoUpdateLayer = findViewById(R.id.auto_update);
        this.mAutoUpdateLayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!AboutActivity.this.mAutoUpdateClicked) {
                    AboutActivity.this.mAutoUpdateClicked = true;
                    final boolean z = !AboutActivity.this.mAutoUpdate;
                    CoreApi.a().a(z, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            AboutActivity.this.mAutoUpdate = z;
                            PluginAutoDownloadTask.a().a(AboutActivity.this.mAutoUpdate);
                            AboutActivity.this.mAutoUpdateClicked = false;
                            AboutActivity.this.b();
                        }

                        public void onFailure(Error error) {
                            AboutActivity.this.mAutoUpdateClicked = false;
                        }
                    });
                    StatHelper.h(z);
                }
            }
        });
        this.mAutoUpdateBtn = (SwitchButton) findViewById(R.id.auto_update_btn);
        this.mAutoUpdateBtn.setOnTouchEnable(false);
        View findViewById2 = findViewById(R.id.click_sound);
        findViewById2.setVisibility(0);
        final SwitchButton switchButton = (SwitchButton) findViewById(R.id.lite_click_sound_btn);
        switchButton.setOnTouchEnable(false);
        switchButton.setChecked(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.l, true));
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchButton.setChecked(!switchButton.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.l, switchButton.isChecked());
                StatHelper.i(switchButton.isChecked());
            }
        });
        View findViewById3 = findViewById(R.id.click_miui_auto_discovery_item);
        findViewById3.setVisibility(0);
        final SwitchButton switchButton2 = (SwitchButton) findViewById(R.id.miui_auto_discovery_btn);
        switchButton2.setOnTouchEnable(false);
        switchButton2.setChecked(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.m, false));
        findViewById3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.a(!switchButton2.isChecked() ? 1 : 2);
                PreferenceUtils.b("open_find_device_tips", false);
                switchButton2.setChecked(!switchButton2.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.m, switchButton2.isChecked());
            }
        });
        STAT.c.i(switchButton2.isChecked() ? 1 : 2);
        View findViewById4 = findViewById(R.id.auto_connect_setting);
        findViewById4.setVisibility(0);
        final SwitchButton switchButton3 = (SwitchButton) findViewById(R.id.auto_connect_toggle);
        switchButton3.setOnTouchEnable(false);
        switchButton3.setChecked(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.n, false));
        findViewById4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchButton3.setChecked(!switchButton3.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.n, switchButton3.isChecked());
            }
        });
        if (!CommonUtils.p()) {
            findViewById3.setVisibility(8);
            findViewById4.setVisibility(8);
        }
        this.d = (TextView) findViewById(R.id.device_card_shortcut_info);
        this.d.setText(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.r, false) ? R.string.open : R.string.close);
        this.c = findViewById(R.id.device_card_shortcut);
        if (SHApplication.getStateNotifier().a() == 4) {
            this.c.setVisibility(0);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AboutActivity.this.startActivityForResult(new Intent(AboutActivity.this, DeviceCardSettingActivity.class), 200);
                    STAT.d.S();
                }
            });
        } else {
            this.c.setVisibility(8);
        }
        findViewById(R.id.developer).setVisibility(8);
        findViewById(R.id.server_env).setVisibility(8);
        d();
        findViewById(R.id.user_liscense_text).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.startActivity(new Intent(AboutActivity.this, LicenseChooseActivity.class));
            }
        });
        findViewById(R.id.user_liscense_text).setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                return false;
            }
        });
        if (HomeManager.a().h()) {
            findViewById(R.id.device_tag).setVisibility(8);
            findViewById(R.id.manage_home_container).setVisibility(0);
            findViewById(R.id.manage_home_container).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CoreApi.a().q()) {
                        List<Home> f = HomeManager.a().f();
                        if (f == null || f.size() <= 1) {
                            AboutActivity.this.startActivity(new Intent(AboutActivity.this, HomeEditorActivity.class));
                            return;
                        }
                        Intent intent = new Intent(AboutActivity.this, MultiHomeManagerActivity.class);
                        intent.putExtra("from", 2);
                        AboutActivity.this.startActivity(intent);
                        return;
                    }
                    if (AboutActivity.this.e != null && AboutActivity.this.e.isShowing()) {
                        AboutActivity.this.e.dismiss();
                    }
                    Dialog unused = AboutActivity.this.e = SHApplication.showLoginDialog(AboutActivity.this, false);
                }
            });
        } else {
            findViewById(R.id.device_tag).setVisibility(0);
            findViewById(R.id.room_manage_container).setVisibility(8);
            findViewById(R.id.manage_home_container).setVisibility(8);
            findViewById(R.id.device_tag).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CoreApi.a().q()) {
                        AboutActivity.this.startActivity(new Intent(AboutActivity.this, DeviceTagManagerActivity.class));
                        return;
                    }
                    if (AboutActivity.this.e != null && AboutActivity.this.e.isShowing()) {
                        AboutActivity.this.e.dismiss();
                    }
                    Dialog unused = AboutActivity.this.e = SHApplication.showLoginDialog(AboutActivity.this, false);
                }
            });
        }
        if (CoreApi.a().D()) {
            findViewById(R.id.client_all_columns).setVisibility(8);
        } else {
            findViewById(R.id.client_all_columns).setVisibility(8);
        }
        View findViewById5 = findViewById(R.id.face_privacy);
        findViewById5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FacePrivacyManagerActivity.Companion.a(AboutActivity.this.getContext());
            }
        });
        findViewById5.setVisibility(ServerCompact.e((Context) this) ? 8 : 0);
        findViewById(R.id.message_setting_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CoreApi.a().q()) {
                    AboutActivity.this.startActivity(new Intent(AboutActivity.this, MessageCenterSettingActivity.class));
                    return;
                }
                if (AboutActivity.this.e != null && AboutActivity.this.e.isShowing()) {
                    AboutActivity.this.e.dismiss();
                }
                Dialog unused = AboutActivity.this.e = SHApplication.showLoginDialog(AboutActivity.this, false);
            }
        });
        findViewById(R.id.setting_auth_manager).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.startActivity(new Intent(AboutActivity.this, AuthManagerListActivity.class));
            }
        });
        this.mImgDotNew = (ImageView) findViewById(R.id.img_about_dot_new);
        RedPointManagerNew.a().a(RedPointManagerNew.j, (RedPointManagerNew.RedPointAction) new RedPointManagerNew.RedPointAction() {
            public void showRedPoint(boolean z) {
                Miio.b(OpenApi.e, "AboutActivity  isShow  " + z);
                AboutActivity.this.mImgDotNew.setVisibility(z ? 0 : 8);
            }
        });
        try {
            ((TextView) findViewById(R.id.app_info)).setText(String.format(getString(R.string.version_name_string), new Object[]{getPackageManager().getPackageInfo(getPackageName(), 0).versionName}));
            if (CoreApi.a().D()) {
                findViewById(R.id.international_icon).setVisibility(0);
            }
        } catch (Exception unused) {
        }
        new Thread(new Runnable() {
            public void run() {
                ModelUpdateManager.d().b();
                ModelUpdateManager.d().c();
                AppUpdateManger.c();
            }
        }).start();
        this.mProductStatLayer = findViewById(R.id.product_stat);
        this.mProductStatLayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AboutActivity.this.mProductStat != null) {
                    Intent intent = new Intent(AboutActivity.this, SmartHomeWebActivity.class);
                    intent.putExtra("title", AboutActivity.this.mProductStat.b);
                    intent.putExtra("url", AboutActivity.this.mProductStat.f11662a);
                    AboutActivity.this.startActivity(intent);
                }
            }
        });
        UserApi.a().a((Context) this, (AsyncCallback<JSONArray, Error>) new AsyncCallback<JSONArray, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                if (jSONArray != null && jSONArray.length() > 0) {
                    try {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(0);
                        String optString = jSONObject.optString("url");
                        String optString2 = jSONObject.optString("title");
                        String optString3 = jSONObject.optString("type");
                        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3)) {
                            ProductStat productStat = new ProductStat();
                            productStat.f11662a = optString;
                            productStat.b = optString2;
                            productStat.c = optString3;
                            AboutActivity.this.mProductStat = productStat;
                            AboutActivity.this.e();
                        }
                    } catch (JSONException unused) {
                    }
                }
            }
        });
        CoreApi.a().c((AsyncCallback<Boolean, Error>) new AsyncCallback<Boolean, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                AboutActivity.this.mAutoUpdate = bool.booleanValue();
                AboutActivity.this.b();
            }
        });
        b();
        e();
        findViewById(R.id.language).setVisibility(0);
        this.mLanguageTitle = (TextView) findViewById(R.id.language_title_info);
        this.mLanguageTitle.setText(LanguageUtil.a());
        findViewById(R.id.language).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.am();
                AboutActivity.this.startActivityForResult(new Intent(AboutActivity.this, LanguageSettingActivity.class), 100);
            }
        });
        if (CoreApi.a().D() || !CoreApi.a().q()) {
            findViewById(R.id.notification_operation).setVisibility(8);
        } else {
            findViewById(R.id.notification_operation).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.aq();
                    AboutActivity.this.startActivity(new Intent(AboutActivity.this, QuickOpDeviceSettingActivity.class));
                }
            });
        }
        ServerBean F = CoreApi.a().F();
        if (!CoreApi.a().q() || !ServerCompact.f(F)) {
            findViewById(R.id.device_auth).setVisibility(8);
        } else {
            findViewById(R.id.device_auth).setVisibility(0);
            findViewById(R.id.device_auth).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AboutActivity.this.startActivity(new Intent(AboutActivity.this, DeviceAuthMasterListActivity.class));
                }
            });
        }
        if (a()) {
            View findViewById6 = findViewById(R.id.op_history_layout);
            findViewById6.setVisibility(0);
            findViewById6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CoreApi.a().q()) {
                        AboutActivity.this.startActivity(new Intent(AboutActivity.this, DeviceOpHistoryListActivity.class));
                        return;
                    }
                    if (AboutActivity.this.e != null && AboutActivity.this.e.isShowing()) {
                        AboutActivity.this.e.dismiss();
                    }
                    Dialog unused = AboutActivity.this.e = SHApplication.showLoginDialog(AboutActivity.this, false);
                }
            });
        } else {
            findViewById(R.id.op_history_layout).setVisibility(8);
        }
        ListItemView listItemView = (ListItemView) findViewById(R.id.laboratory_setting);
        if (CoreApi.a().D() || !CoreApi.a().q()) {
            listItemView.setVisibility(8);
        } else {
            listItemView.setVisibility(8);
            listItemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AboutActivity.this.startActivity(new Intent(AboutActivity.this, LaboratoryActivity.class));
                    StatHelper.al();
                }
            });
        }
        findViewById(R.id.privacy_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.startActivity(new Intent(AboutActivity.this, PrivacySettingActivity.class));
            }
        });
        View findViewById7 = findViewById(R.id.open_mi_brain_item);
        if (CoreApi.a().D()) {
            findViewById7.setVisibility(8);
        } else {
            findViewById7.setVisibility(0);
        }
        this.g = (SwitchButton) findViewById(R.id.voice_open_permission);
        this.g.setOnTouchEnable(false);
        findViewById7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AboutActivity.this.g.setChecked(!AboutActivity.this.g.isChecked());
                AboutActivity.this.a(AboutActivity.this.g.isChecked(), VoiceManager.a().c());
                StatHelper.j(AboutActivity.this.g.isChecked());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str) {
        VoiceManager.a().a(z, str);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public static Point getNavigationBarSize(Activity activity) {
        if (!CommonUtils.d(activity)) {
            return new Point(0, 0);
        }
        Point appUsableScreenSize = getAppUsableScreenSize(activity);
        Point realScreenSize = getRealScreenSize(activity);
        if (appUsableScreenSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableScreenSize.x, appUsableScreenSize.y);
        }
        if (appUsableScreenSize.y < realScreenSize.y) {
            return new Point(appUsableScreenSize.x, realScreenSize.y - appUsableScreenSize.y);
        }
        return new Point();
    }

    public static Point getAppUsableScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static Point getRealScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                point.x = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                point.y = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
        return point;
    }

    private boolean a() {
        PluginRecord d2;
        PluginDeviceInfo c2;
        ArrayList arrayList = new ArrayList();
        List<Device> d3 = SmartHomeDeviceManager.a().d();
        List<Device> k = SmartHomeDeviceManager.a().k();
        if (d3 != null) {
            arrayList.addAll(d3);
        }
        if (k != null) {
            arrayList.addAll(k);
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            Device device = (Device) arrayList.get(i);
            if (device != null && (d2 = CoreApi.a().d(device.model)) != null && (c2 = d2.c()) != null && c2.F() == 1) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 100 && i2 == -1) {
            finish();
        } else if (i == 200 && i2 == -1) {
            this.c.setVisibility(0);
            this.d.setText(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.r, false) ? R.string.open : R.string.close);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.mAutoUpdate) {
            this.mAutoUpdateBtn.setChecked(true);
        } else {
            this.mAutoUpdateBtn.setChecked(false);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        ServerBean F;
        final TextView textView = (TextView) findViewById(R.id.selected_region);
        if (textView != null && (F = CoreApi.a().F()) != null) {
            this.f = ServerApi.a().a(F).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                /* renamed from: a */
                public void accept(String str) throws Exception {
                    textView.setVisibility(0);
                    textView.setText(str);
                }
            }, new Consumer(textView) {
                private final /* synthetic */ TextView f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj) {
                    AboutActivity.a(this.f$0, (Throwable) obj);
                }
            }, $$Lambda$AboutActivity$dbU5lLkSY5o9wsTQ76X5n4XfIk.INSTANCE);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(TextView textView, Throwable th) throws Exception {
        th.printStackTrace();
        textView.setVisibility(4);
    }

    private void d() {
        TextView textView = (TextView) findViewById(R.id.selected_server_env);
        if (textView != null) {
            String H = CoreApi.a().H();
            if (!TextUtils.isEmpty(H)) {
                textView.setText(H);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.mProductStat != null) {
            this.mProductStatLayer.setVisibility(0);
            TextView textView = (TextView) this.mProductStatLayer.findViewById(R.id.product_stat_title);
            if (textView != null) {
                textView.setText(this.mProductStat.b);
                return;
            }
            return;
        }
        this.mProductStatLayer.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MessageCenter.a().a(this);
        MessageCenter.a().b(this);
        MessageCenter.a().g();
        this.g.setChecked(VoiceManager.a().b());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        RedPointManagerNew.a().a(RedPointManagerNew.j);
        if (this.f != null) {
            this.f.dispose();
        }
    }

    private void f() {
        final View decorView = getWindow().getDecorView();
        this.b = new PopupWindow(LayoutInflater.from(this).inflate(R.layout.sh_popup_window_check_upgrade, (ViewGroup) null), -1, -1, true);
        this.b.setBackgroundDrawable(new ColorDrawable(0));
        decorView.post(new Runnable() {
            public void run() {
                if (AboutActivity.this.b != null) {
                    AboutActivity.this.b.showAtLocation(decorView, 51, 0, 0);
                }
            }
        });
    }

    private void g() {
        if (this.b != null) {
            this.b.dismiss();
            this.b = null;
        }
    }

    class ProductStat {

        /* renamed from: a  reason: collision with root package name */
        String f11662a;
        String b;
        String c;

        ProductStat() {
        }
    }
}
