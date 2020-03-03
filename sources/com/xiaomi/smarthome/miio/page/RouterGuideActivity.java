package com.xiaomi.smarthome.miio.page;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.library.common.util.DownloadManagerPro;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.Iterator;
import org.cybergarage.upnp.DeviceList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterGuideActivity extends BaseWhiteActivity implements Device.StateChangedListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19633a = "http://bigota.miwifi.com/xiaoqiang/client/xqapp.apk";
    private static final String b = "http://bigota.miwifi.com/xiaoqiang/client/xqapp_dev.apk";
    private static final String c = "http://bigota.miwifi.com/xiaoqiang/client/xqapp_v2.apk";
    private static final String d = "http://bigota.miwifi.com/xiaoqiang/client/xqapp_v2_dev.apk";
    private static final String e = "mac";
    private static final int l = 0;
    private static final int m = 1;
    private static final int n = 2;
    /* access modifiers changed from: private */
    public TextView f;
    private ProgressBar g;
    private TextView h;
    private DownloadManagerPro i;
    private long j = -1;
    private ContentObserver k = null;
    /* access modifiers changed from: private */
    public int o = 0;
    /* access modifiers changed from: private */
    public RouterDevice p;

    public void onStateChanged(Device device) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.router_guide_activity);
        String stringExtra = getIntent().getStringExtra("mac");
        if (SmartHomeDeviceManager.a().d() != null && !TextUtils.isEmpty(stringExtra)) {
            Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Device next = it.next();
                if (stringExtra.equalsIgnoreCase(next.did) && (next instanceof RouterDevice)) {
                    this.p = (RouterDevice) next;
                    break;
                }
            }
        }
        if (this.p == null) {
            finish();
            return;
        }
        TitleBarUtil.c(this);
        ImageView imageView = (ImageView) findViewById(R.id.module_a_3_return_transparent_btn);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    RouterGuideActivity.this.finish();
                }
            });
        }
        ((TextView) findViewById(R.id.module_a_3_return_transparent_title)).setText(R.string.router_guide_title);
        this.g = (ProgressBar) findViewById(R.id.progress);
        this.f = (TextView) findViewById(R.id.install_button);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (RouterGuideActivity.this.o == 0) {
                    RouterGuideActivity.this.f.setEnabled(false);
                    RouterRemoteApi.a().e(RouterGuideActivity.this, "", new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            RouterGuideActivity.this.f.setEnabled(true);
                            ArrayList arrayList = new ArrayList();
                            try {
                                JSONArray jSONArray = jSONObject.getJSONArray(DeviceList.ELEM_NAME);
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                                    RouterInfo routerInfo = new RouterInfo();
                                    routerInfo.f19638a = jSONObject2.getString("id");
                                    routerInfo.b = jSONObject2.getString("serial");
                                    routerInfo.c = jSONObject2.getString("name");
                                    routerInfo.d = jSONObject2.getString(MipayConstants.S);
                                    routerInfo.e = jSONObject2.optString("channel");
                                    routerInfo.f = jSONObject2.optString("rom");
                                    arrayList.add(routerInfo);
                                }
                            } catch (JSONException unused) {
                            }
                            String str = "";
                            String str2 = "release";
                            String str3 = RouterGuideActivity.this.p.did;
                            if (str3.startsWith("miwifi.")) {
                                str3 = str3.substring("miwifi.".length());
                            }
                            Iterator it = arrayList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                RouterInfo routerInfo2 = (RouterInfo) it.next();
                                if (str3.equalsIgnoreCase(routerInfo2.f19638a)) {
                                    str = routerInfo2.f;
                                    str2 = routerInfo2.e;
                                    break;
                                }
                            }
                            int unused2 = RouterGuideActivity.this.o = 1;
                            RouterGuideActivity.this.a(RouterGuideActivity.this.a(str, str2));
                        }

                        public void onFailure(Error error) {
                            if (!RouterGuideActivity.this.isFinishing()) {
                                RouterGuideActivity.this.f.setEnabled(true);
                            }
                        }
                    });
                } else if (RouterGuideActivity.this.o == 2) {
                    RouterGuideActivity.this.c();
                }
            }
        });
        this.h = (TextView) findViewById(R.id.progress_title);
        a();
        b();
        if (this.o == 1) {
            d();
            e();
        }
    }

    /* access modifiers changed from: private */
    public String a(String str, String str2) {
        boolean startsWith = str.startsWith("2");
        boolean equalsIgnoreCase = str2.equalsIgnoreCase("release");
        if (startsWith && equalsIgnoreCase) {
            return c;
        }
        if (!startsWith || equalsIgnoreCase) {
            return (startsWith || equalsIgnoreCase) ? f19633a : b;
        }
        return d;
    }

    private void a() {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        this.i = new DownloadManagerPro(downloadManager);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        long j2 = defaultSharedPreferences.getLong(DownloadBroadcastReceiver.DOWNLOAD_MIWIFI_ASSEST_APK_ID, -2);
        if (j2 >= 0) {
            int a2 = this.i.a(j2);
            if (a2 == 8) {
                this.j = j2;
                this.o = 2;
            } else if (a2 == 1 || a2 == 2) {
                this.j = j2;
                this.o = 1;
            } else {
                downloadManager.remove(new long[]{j2});
                defaultSharedPreferences.edit().remove(DownloadBroadcastReceiver.DOWNLOAD_MIWIFI_ASSEST_APK_ID).apply();
                this.j = -1;
                this.o = 0;
            }
        } else {
            this.j = -1;
            this.o = 0;
        }
    }

    private void b() {
        this.f.setVisibility(4);
        this.g.setVisibility(4);
        this.h.setVisibility(4);
        if (this.o == 0) {
            this.f.setVisibility(0);
            this.f.setText(R.string.router_button_to_download);
        } else if (this.o == 1) {
            this.g.setVisibility(0);
            this.h.setVisibility(0);
        } else if (this.o == 2) {
            this.f.setVisibility(0);
            this.f.setText(R.string.router_button_to_install);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setTitle(getString(R.string.router_download_title));
        request.setDescription(getString(R.string.router_download_des));
        request.setNotificationVisibility(0);
        this.j = this.i.a().enqueue(request);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putLong(DownloadBroadcastReceiver.DOWNLOAD_MIWIFI_ASSEST_APK_ID, this.j).apply();
        d();
    }

    /* access modifiers changed from: private */
    public void c() {
        DownloadBroadcastReceiver.installDownloadId(this, this.j);
        PreferenceManager.getDefaultSharedPreferences(this).edit().remove(DownloadBroadcastReceiver.DOWNLOAD_MIWIFI_ASSEST_APK_ID).apply();
        this.j = -1;
        this.o = 0;
        finish();
    }

    private void d() {
        this.k = new ContentObserver(this.mHandler) {
            public void onChange(boolean z) {
                super.onChange(z);
                RouterGuideActivity.this.e();
            }
        };
        getContentResolver().registerContentObserver(DownloadManagerPro.f18670a, true, this.k);
        this.o = 1;
        b();
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.j != -1) {
            int[] c2 = this.i.c(this.j);
            if (c2[2] == 2 || c2[2] == 1) {
                a(c2[1], c2[0]);
            } else if (c2[2] == 8) {
                this.o = 2;
                b();
            } else {
                Toast.makeText(getApplicationContext(), R.string.download_error, 0).show();
                f();
            }
        }
    }

    private void a(int i2, int i3) {
        if (this.g != null) {
            this.g.setMax(i2);
            this.g.setProgress(i3);
        }
        if (this.h != null) {
            TextView textView = this.h;
            Resources resources = getResources();
            double d2 = (double) i3;
            Double.isNaN(d2);
            double d3 = (double) i2;
            Double.isNaN(d3);
            double d4 = (d2 * 100.0d) / d3;
            textView.setText(resources.getQuantityString(R.plurals.router_download_progress, (int) Math.round(d4), new Object[]{Long.valueOf(Math.round(d4))}));
        }
    }

    private void f() {
        ((DownloadManager) getSystemService(Constants.TitleMenu.MENU_DOWNLOAD)).remove(new long[]{this.j});
        this.j = -1;
        PreferenceManager.getDefaultSharedPreferences(this).edit().remove(DownloadBroadcastReceiver.DOWNLOAD_MIWIFI_ASSEST_APK_ID).apply();
        release();
        this.o = 0;
        b();
    }

    public void release() {
        this.j = -1;
        if (this.k != null) {
            getContentResolver().unregisterContentObserver(this.k);
            this.k = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        release();
        super.onDestroy();
    }

    private static class RouterInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f19638a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;

        private RouterInfo() {
        }
    }
}
