package com.xiaomi.smarthome.newui.widget.banner;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.application.ServiceApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.file.FileUtil;
import com.xiaomi.smarthome.listcamera.AllCameraActivity;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.HomeEnvInfoViewModel;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetInfo;
import com.xiaomi.smarthome.stat.STAT;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomBannerHeaderView extends FrameLayout {
    protected static final String TAG = "CustomBannerHeaderView";

    /* renamed from: a  reason: collision with root package name */
    private HomeEnvInfoViewModel f20892a;
    private TextView b;
    private TextView c;
    private TextView d;
    /* access modifiers changed from: private */
    public View e;
    /* access modifiers changed from: private */
    public WeakReference<DeviceMainPage> f;
    private TextView g;
    private int h = 0;
    private String i = "";
    private String j = "";
    private String k = "";
    private int l = 0;
    protected View mContentView;
    protected View mOfflineView;

    public void notifyDataSetChanged() {
    }

    public CustomBannerHeaderView(Context context) {
        super(context);
        init();
    }

    public CustomBannerHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomBannerHeaderView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* access modifiers changed from: protected */
    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.customized_banner_header_view, this);
        boolean a2 = DarkModeCompat.a(getContext());
        this.mOfflineView = findViewById(R.id.header_view_offline);
        this.b = (TextView) findViewById(R.id.env_info_temp_tv);
        this.c = (TextView) findViewById(R.id.env_info_hum_tv);
        this.d = (TextView) findViewById(R.id.env_info_aqi_tv);
        this.e = findViewById(R.id.camera_num_container);
        this.g = (TextView) this.e.findViewById(R.id.camera_num);
        ImageView imageView = (ImageView) findViewById(R.id.camera_icon);
        int i2 = 0;
        DarkModeCompat.a(this.e, false);
        if (a2) {
            this.e.setBackgroundResource(R.drawable.top_camera_bg_dark);
            this.g.setTextColor(-1);
            DarkModeCompat.a((View) this.b, false);
            DarkModeCompat.a((View) this.c, false);
            DarkModeCompat.a((View) this.d, false);
            int argb = Color.argb(178, 255, 255, 255);
            this.b.setTextColor(argb);
            this.c.setTextColor(argb);
            this.d.setTextColor(argb);
            imageView.setImageResource(R.drawable.top_icon_camera_v2_dark);
        }
        this.mContentView = findViewById(R.id.banner_content_container);
        c();
        if (this.h <= 0) {
            i2 = 8;
        }
        a(i2, this.h);
    }

    public FragmentActivity getActivity() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof FragmentActivity) {
                return (FragmentActivity) context;
            }
        }
        return null;
    }

    public void setDeviceMainPage(DeviceMainPage deviceMainPage) {
        this.f = new WeakReference<>(deviceMainPage);
        FragmentActivity activity = deviceMainPage.getActivity();
        activity.getClass();
        this.f20892a = (HomeEnvInfoViewModel) ViewModelProviders.a(activity).a(HomeEnvInfoViewModel.class);
        MutableLiveData<Map<String, TopWidgetInfo>> b2 = this.f20892a.b();
        FragmentActivity activity2 = deviceMainPage.getActivity();
        activity2.getClass();
        b2.observe(activity2, new Observer() {
            public final void onChanged(Object obj) {
                CustomBannerHeaderView.this.a((Map) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Map map) {
        a();
    }

    private void a() {
        if (this.b != null) {
            try {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.mContentView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        DeviceMainPage deviceMainPage;
                        if (CustomBannerHeaderView.this.f != null && (deviceMainPage = (DeviceMainPage) CustomBannerHeaderView.this.f.get()) != null) {
                            deviceMainPage.c();
                            STAT.d.Q();
                        }
                    }
                });
                this.i = "";
                this.j = "";
                this.k = "";
                TopWidgetInfo topWidgetInfo = (TopWidgetInfo) this.f20892a.b().getValue().get(HomeManager.a().l());
                if (topWidgetInfo != null) {
                    this.i = topWidgetInfo.g();
                    this.j = topWidgetInfo.f();
                    this.k = topWidgetInfo.e();
                    b();
                    a(this.h, this.i, this.j, this.k);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void b() {
        if (TextUtils.isEmpty(this.i + this.j + this.k)) {
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            this.b.setVisibility(0);
            this.b.setText(R.string.home_env_info_top_desc_empty);
            return;
        }
        if (!TextUtils.isEmpty(this.i)) {
            this.b.setVisibility(0);
            if (!TextUtils.isEmpty(this.j) || !TextUtils.isEmpty(this.k)) {
                TextView textView = this.b;
                textView.setText(this.i + " | ");
            } else {
                this.b.setText(this.i);
            }
        }
        if (!TextUtils.isEmpty(this.j)) {
            this.c.setVisibility(0);
            if (TextUtils.isEmpty(this.k)) {
                this.c.setText(this.j);
            } else {
                TextView textView2 = this.c;
                textView2.setText(this.j + " | ");
            }
        }
        if (!TextUtils.isEmpty(this.k)) {
            this.d.setVisibility(0);
            this.d.setText(this.k);
        }
    }

    public void setCameraContainerVisibility(int i2, int i3) {
        this.h = i3;
        a(this.h, this.i, this.j, this.k);
        a(i2, i3);
    }

    public int getCameraSize() {
        return this.h;
    }

    private void a(int i2, final int i3) {
        if (this.e != null) {
            this.e.setVisibility(i2);
            if (i2 == 0) {
                this.g.setText(XMStringUtils.a(SHApplication.getAppContext(), (int) R.plurals.top_widget_camera_num, i3, (Object) Integer.valueOf(i3)));
            }
            this.e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (i3 > 0 && CustomBannerHeaderView.this.e.getAlpha() >= 1.0f) {
                        Intent intent = new Intent(SHApplication.getAppContext(), AllCameraActivity.class);
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                        SHApplication.getAppContext().startActivity(intent);
                    }
                    STAT.d.ak();
                }
            });
        }
    }

    private void c() {
        try {
            byte[] d2 = FileUtil.d(SHApplication.getAppContext(), Uri.fromFile(new File(a(CoreApi.a().s()))));
            if (d2 == null) {
                return;
            }
            if (d2.length != 0) {
                this.h = new JSONObject(new String(d2)).optInt("c_s");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(int i2, String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("c_s", i2);
            jSONObject.put("t_t", str);
            jSONObject.put("h_t", str2);
            jSONObject.put("a_t", str3);
            File file = new File(a(CoreApi.a().s()));
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileUtil.a(SHApplication.getAppContext(), Uri.fromFile(file), jSONObject.toString().getBytes());
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private static String a(String str) {
        return ServiceApplication.getAppContext().getFilesDir() + File.separator + "main" + File.separator + "main_page_banner_data_" + MD5.a(str);
    }

    public void offset(int i2) {
        if (this.l == 0) {
            this.l = getHeight();
        }
    }

    public void showOfflineView() {
        this.mOfflineView.setVisibility(0);
        this.mContentView.setVisibility(8);
        TextView textView = (TextView) this.mOfflineView.findViewById(R.id.no_login_tv);
        textView.setText(R.string.network_disable);
        ((ImageView) this.mOfflineView.findViewById(R.id.no_login_icon)).setImageResource(R.drawable.network_disconnected_icon_v2);
        textView.setTextColor(getResources().getColor(R.color.main_banner_text_color_1));
        this.mOfflineView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    CustomBannerHeaderView.this.getContext().startActivity(new Intent("android.settings.SETTINGS"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showLoginView() {
        this.mOfflineView.setVisibility(8);
        this.mContentView.setVisibility(8);
    }

    public void showContentView() {
        this.mOfflineView.setVisibility(8);
        this.mContentView.setVisibility(0);
    }
}
