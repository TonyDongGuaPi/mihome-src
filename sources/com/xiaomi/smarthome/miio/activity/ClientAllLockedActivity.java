package com.xiaomi.smarthome.miio.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.lockscreen.ClientAllLockedDialog;
import com.xiaomi.smarthome.stat.STAT;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ClientAllLockedActivity extends FragmentActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11716a = "ClientAllLockedActivity";
    public static boolean clickOnPlugin = false;
    private long b = 0;
    public ClientAllLockedDialog mDialog = null;
    Runnable mDismissRunnable = new Runnable() {
        public void run() {
            ClientAllLockedActivity.this.mIsFinished = true;
            ClientAllLockedActivity.this.finish();
        }
    };
    public Handler mHandler = null;
    boolean mIsFinished = false;
    boolean mIsPassCTA = false;

    /* access modifiers changed from: protected */
    public boolean useActivityAsStat() {
        return true;
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public void onCreate(Bundle bundle) {
        Intent launchIntentForPackage;
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        FrescoInitial.a(false);
        Intent intent = getIntent();
        if (!(intent == null || (intent.getFlags() & 1048576) == 0)) {
            try {
                String packageName = getPackageName();
                if (!TextUtils.isEmpty(packageName) && (launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(packageName)) != null) {
                    startActivity(launchIntentForPackage);
                }
            } catch (Throwable unused) {
            }
            finish();
        }
        Window window = getWindow();
        window.addFlags(524288);
        window.addFlags(134217728);
        DisplayUtils.a(window);
        TitleBarUtil.b((Activity) this);
        this.mHandler = new Handler();
        a();
        setContentView(R.layout.client_all_locked_activity);
        final View findViewById = findViewById(R.id.fl_background);
        new AsyncTask<Void, Void, BitmapDrawable>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public BitmapDrawable doInBackground(Void... voidArr) {
                return DisplayUtils.c(ClientAllLockedActivity.this);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(BitmapDrawable bitmapDrawable) {
                super.onPostExecute(bitmapDrawable);
                findViewById.setBackground(bitmapDrawable);
            }
        }.execute(new Void[0]);
        SmartHomeDeviceManager.a().p();
        HomeKeyManager.a().a(false);
    }

    private void a() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setNavigationBarColor(getPageNavBarColor());
        }
    }

    /* access modifiers changed from: protected */
    public int getPageNavBarColor() {
        return getResources().getColor(R.color.transparent);
    }

    /* access modifiers changed from: package-private */
    public void onCTAPassed() {
        if (!this.mIsFinished) {
            showDeviceList();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        clickOnPlugin = false;
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void c() {
            }

            public void b() {
                ClientAllLockedActivity.this.mIsPassCTA = false;
                ClientAllLockedActivity.this.mIsFinished = true;
                ClientAllLockedActivity.this.finish();
            }

            public void d() {
                ClientAllLockedActivity.this.mIsPassCTA = false;
                ClientAllLockedActivity.this.mIsFinished = true;
                ClientAllLockedActivity.this.finish();
            }

            public void e() {
                ClientAllLockedActivity.this.mIsPassCTA = true;
                ClientAllLockedActivity.this.onCTAPassed();
            }
        });
        if (this.mIsPassCTA) {
            showDeviceList();
            if (useActivityAsStat()) {
                String name = getClass().getName();
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("name", name);
                    jSONObject.put("activity", name);
                    CoreApi.a().a(StatType.EVENT, "page_start", jSONObject.toString(), (String) null, false);
                    this.b = STAT.b.a(this, (String) null);
                    if (IRDeviceUtil.c()) {
                        CoreApi.a().a(StatType.EVENT, "devicelist_ir_card_locked", jSONObject.toString(), (String) null, false);
                    }
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mIsPassCTA && useActivityAsStat()) {
            CoreApi.a().a(StatType.EVENT, "page_end", (String) null, (String) null, false);
            STAT.b.a(this, this.b, (String) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mHandler.removeCallbacks(this.mDismissRunnable);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (clickOnPlugin) {
            postDelayed(this.mDismissRunnable, 1000);
        }
    }

    public void postDelayed(Runnable runnable, long j) {
        if (runnable != null && this.mHandler != null) {
            this.mHandler.postDelayed(runnable, j);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        dismissDeviceList();
        FrescoInitial.a();
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    /* access modifiers changed from: protected */
    public void showDeviceList() {
        if (this.mDialog == null) {
            this.mDialog = new ClientAllLockedDialog(this);
        }
        if (!this.mDialog.isShowing()) {
            try {
                this.mDialog.show();
            } catch (Exception unused) {
            }
        } else {
            this.mDialog.k();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissDeviceList() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            try {
                this.mDialog.dismiss();
            } catch (Exception unused) {
            }
        }
    }
}
