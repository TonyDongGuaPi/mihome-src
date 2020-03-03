package com.xiaomi.smarthome.framework.navigate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginMiuiActivity;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.frame.plugin.runtime.service.PluginServiceConst;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.imagecache.ImageCacheUtils;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.io.File;

public class SmartHomeLauncher extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void c() {
            }

            public void b() {
                SmartHomeLauncher.this.finish();
            }

            public void d() {
                SmartHomeLauncher.this.finish();
            }

            public void e() {
                CoreApi.a().a((Context) SmartHomeLauncher.this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        SmartHomeLauncher.this.a();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        HomeKeyManager.a().a(false);
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data == null) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.equalsIgnoreCase(PluginServiceConst.ACTION_STARTFOREGROUND_NOTIFICATION_PENDING_INTENT)) {
                a(intent);
            }
            finish();
            return;
        }
        Uri b = PageUrl.b(data);
        PageUrl.UrlConfigInfo c = PageUrl.c(b);
        if (c == null) {
            try {
                if (XmPluginHostApi.instance().isYoupinHost(b.toString())) {
                    XmPluginHostApi.instance().openUrl(b.toString());
                    finish();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!PageUrl.a(b)) {
                finish();
            } else if (!b.toString().equalsIgnoreCase("https://home.mi.com/download/")) {
                UrlResolver.a(this, b, false);
                finish();
            }
        } else if (c.b()) {
            if (!NavigateUtil.a()) {
                SmartHomeDeviceManager.a().p();
                Intent intent2 = new Intent(this, SmartHomeMainActivity.class);
                intent2.putExtras(getIntent());
                intent2.putExtra("source", 7);
                intent2.putExtra(ApiConst.N, getIntent());
                intent2.setFlags(268468224);
                startActivity(intent2);
                finish();
            } else if (!c.c()) {
                UrlResolver.a(this, b, false);
                finish();
            } else if (CoreApi.a().q()) {
                UrlResolver.a(this, b, false);
                finish();
            } else {
                new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SmartHomeLauncher.this.c();
                        dialogInterface.dismiss();
                        SmartHomeLauncher.this.finish();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SmartHomeLauncher.this.finish();
                    }
                }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SmartHomeLauncher.this.finish();
                    }
                }).b((int) R.string.loing_helper_title).d();
            }
        } else if (!c.c()) {
            UrlResolver.a(this, b, false);
            finish();
        } else if (CoreApi.a().q()) {
            UrlResolver.a(this, b, false);
            finish();
        } else {
            new MLAlertDialog.Builder(this).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartHomeLauncher.this.c();
                    dialogInterface.dismiss();
                    SmartHomeLauncher.this.finish();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    SmartHomeLauncher.this.finish();
                }
            }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SmartHomeLauncher.this.finish();
                }
            }).b((int) R.string.loing_helper_title).d();
        }
    }

    private void a(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("model");
            Intent intent2 = (Intent) intent.getParcelableExtra("params");
            if (TextUtils.isEmpty(stringExtra)) {
                Log.d("", "model is null");
                return;
            }
            PluginRecord d = CoreApi.a().d(stringExtra);
            if (d == null) {
                Log.d("", "PluginRecord is null");
            } else {
                PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, 17, intent2, (DeviceStat) null, (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
            }
        }
    }

    private String b() {
        String str;
        if ("mounted".equals(Environment.getExternalStorageState()) || !ImageCacheUtils.b()) {
            str = ImageCacheUtils.a(SHApplication.getAppContext()).getPath();
        } else {
            str = SHApplication.getAppContext().getCacheDir().getPath() + File.separator + "app";
        }
        return str + File.separator + "SmartHomeLauncher/SmartHome.apk";
    }

    /* access modifiers changed from: private */
    public void c() {
        MultiHomeDeviceManager.a().b();
        SmartHomeDeviceManager.a().v();
        SceneManager.x().y();
        SmartHomeDeviceManager.a().p();
        sendBroadcast(new Intent(WifiScanHomelog.c));
        Intent intent = new Intent(this, LoginMiuiActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        startActivity(intent);
    }

    private class AsyncHandleWrapper {

        /* renamed from: a  reason: collision with root package name */
        HttpAsyncHandle f16655a;

        private AsyncHandleWrapper() {
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Miio.b("SmartHomeLauncher", "onActivityResult");
        setResult(i2, intent);
        finish();
    }
}
