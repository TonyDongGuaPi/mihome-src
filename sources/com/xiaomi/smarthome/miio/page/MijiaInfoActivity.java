package com.xiaomi.smarthome.miio.page;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.util.Locale;

public class MijiaInfoActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Dialog f19619a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mijia_info_layout);
        a();
    }

    private void a() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_info);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        findViewById(R.id.btn_wx).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MijiaInfoActivity.this.b();
            }
        });
        findViewById(R.id.btn_pinwei).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.p("品味");
                if (CoreApi.a().q()) {
                    UrlDispatchManger.a().c("https://home.mi.com/shop/pinwei");
                    return;
                }
                if (MijiaInfoActivity.this.f19619a != null && MijiaInfoActivity.this.f19619a.isShowing()) {
                    MijiaInfoActivity.this.f19619a.dismiss();
                }
                Dialog unused = MijiaInfoActivity.this.f19619a = SHApplication.showLoginDialog(MijiaInfoActivity.this, false);
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MijiaInfoActivity.this.finish();
            }
        });
        findViewById(R.id.btn_forum).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri;
                MIOTStat.Log(MIOTStat.TOUCH, "forum");
                STAT.d.C();
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Locale locale = Locale.getDefault();
                    if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                        uri = Uri.parse("https://en.miui.com/forum.php");
                    } else {
                        if (!locale.equals(Locale.TAIWAN)) {
                            if (!locale.equals(Locale.TRADITIONAL_CHINESE)) {
                                uri = Uri.parse("https://home.mi.com/redirect/forum");
                            }
                        }
                        uri = Uri.parse("https://tw.miui.com/forum.php?mobile=2");
                    }
                    if (!OpenExternalBrowserCompat.a(MijiaInfoActivity.this.getContext(), uri.toString())) {
                        intent.setClass(MijiaInfoActivity.this.getContext(), WebShellActivity.class);
                        intent.putExtra("url", uri.toString());
                        intent.setData(uri);
                        MijiaInfoActivity.this.startActivity(intent);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!SHApplication.getIWXAPI().isWXAppInstalled()) {
            ToastUtil.a((int) R.string.wx_not_installed);
        } else {
            c();
        }
    }

    private void c() {
        try {
            ((ClipboardManager) getSystemService(ShareChannel.d)).setPrimaryClip(ClipData.newPlainText("text", "mijia0329"));
            if (this.f19619a != null && this.f19619a.isShowing()) {
                this.f19619a.dismiss();
            }
            this.f19619a = new MLAlertDialog.Builder(this).a((int) R.string.tips).b((int) R.string.miio_setting_info_wx_msg).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    IWXAPI iwxapi = SHApplication.getIWXAPI();
                    if (!iwxapi.isWXAppInstalled()) {
                        ToastUtil.a((int) R.string.wx_not_installed);
                    } else {
                        iwxapi.openWXApp();
                    }
                }
            }).d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f19619a != null && this.f19619a.isShowing()) {
            this.f19619a.dismiss();
        }
    }

    private boolean a(String str) {
        try {
            getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
