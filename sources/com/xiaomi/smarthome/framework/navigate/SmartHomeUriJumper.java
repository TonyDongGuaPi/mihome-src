package com.xiaomi.smarthome.framework.navigate;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.xiaomi.loan.sdk.MiFiSdk;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginMiuiActivity;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.imagecache.ImageCacheUtils;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.operation.js_sdk.linker.DeepLinkDelegate;
import java.io.File;
import java.net.URLDecoder;

public class SmartHomeUriJumper extends SmartHomeJumper {
    public SmartHomeUriJumper(Activity activity) {
        super(activity);
    }

    public void a(Intent intent) {
        Uri data = intent.getData();
        if (DeepLinkDelegate.a().a(data)) {
            this.f16637a.finish();
            return;
        }
        String uri = data.toString();
        if (TextUtils.isEmpty(uri) || !uri.startsWith("mihome://home.mi.com/miloan?url=")) {
            final Uri b = PageUrl.b(data);
            PageUrl.UrlConfigInfo c = PageUrl.c(b);
            if (c == null) {
                if (!PageUrl.a(b)) {
                    this.f16637a.finish();
                } else if (!b.toString().equalsIgnoreCase("https://home.mi.com/download/")) {
                    try {
                        UrlResolver.a(this.f16637a, b, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.f16637a.finish();
                }
            } else if (c.b()) {
                if (!NavigateUtil.a()) {
                    SmartHomeDeviceManager.a().p();
                    Intent intent2 = new Intent(this.f16637a, SmartHomeMainActivity.class);
                    intent2.putExtras(intent);
                    intent2.putExtra("source", 7);
                    intent2.putExtra(ApiConst.N, intent);
                    intent2.setFlags(268468224);
                    this.f16637a.startActivity(intent2);
                    this.f16637a.finish();
                } else if (!c.c()) {
                    UrlResolver.a(this.f16637a, b, false);
                    this.f16637a.finish();
                } else if (CoreApi.a().q()) {
                    UrlResolver.a(this.f16637a, b, false);
                    this.f16637a.finish();
                } else {
                    new MLAlertDialog.Builder(this.f16637a).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SmartHomeUriJumper.this.b();
                            dialogInterface.dismiss();
                            SmartHomeUriJumper.this.f16637a.finish();
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            SmartHomeUriJumper.this.f16637a.finish();
                        }
                    }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            SmartHomeUriJumper.this.f16637a.finish();
                        }
                    }).b((int) R.string.loing_helper_title).d();
                }
            } else if (!c.c()) {
                UrlResolver.a(this.f16637a, b, false);
                this.f16637a.finish();
            } else if (SHApplication.getStateNotifier().a() != 3) {
                SHApplication.getStateNotifier().a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
                    public void a() {
                        if (UrlResolver.a(SmartHomeUriJumper.this.f16637a, b, false)) {
                            SmartHomeUriJumper.this.f16637a.finish();
                        }
                    }

                    public void b() {
                        new MLAlertDialog.Builder(SmartHomeUriJumper.this.f16637a).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmartHomeUriJumper.this.b();
                                dialogInterface.dismiss();
                                SmartHomeUriJumper.this.f16637a.finish();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                SmartHomeUriJumper.this.f16637a.finish();
                            }
                        }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                                SmartHomeUriJumper.this.f16637a.finish();
                            }
                        }).b((int) R.string.loing_helper_title).d();
                    }
                });
            } else {
                new MLAlertDialog.Builder(this.f16637a).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SmartHomeUriJumper.this.b();
                        dialogInterface.dismiss();
                        SmartHomeUriJumper.this.f16637a.finish();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SmartHomeUriJumper.this.f16637a.finish();
                    }
                }).a(true).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        SmartHomeUriJumper.this.f16637a.finish();
                    }
                }).b((int) R.string.loing_helper_title).d();
            }
        } else {
            String decode = URLDecoder.decode(uri.substring("mihome://home.mi.com/miloan?url=".length()));
            this.f16637a.overridePendingTransition(0, 0);
            MiFiSdk.a(this.f16637a, decode);
            XmPluginHostApi.instance().addTouchRecord("mi_fenqi", decode);
            this.f16637a.finish();
        }
    }

    private String a() {
        String str;
        if ("mounted".equals(Environment.getExternalStorageState()) || !ImageCacheUtils.b()) {
            str = ImageCacheUtils.a(SHApplication.getAppContext()).getPath();
        } else {
            str = SHApplication.getAppContext().getCacheDir().getPath() + File.separator + "app";
        }
        return str + File.separator + "SmartHomeLauncher/SmartHome.apk";
    }

    /* access modifiers changed from: private */
    public void b() {
        MultiHomeDeviceManager.a().b();
        SmartHomeDeviceManager.a().v();
        SceneManager.x().y();
        SmartHomeDeviceManager.a().p();
        Intent intent = new Intent(this.f16637a, LoginMiuiActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        this.f16637a.startActivity(intent);
        this.f16637a.finish();
    }

    private class AsyncHandleWrapper {

        /* renamed from: a  reason: collision with root package name */
        HttpAsyncHandle f16666a;

        private AsyncHandleWrapper() {
        }
    }
}
