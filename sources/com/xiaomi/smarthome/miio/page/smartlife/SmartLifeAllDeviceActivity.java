package com.xiaomi.smarthome.miio.page.smartlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.utils.DeviceSortUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeCircleView;
import com.xiaomi.smarthome.miio.page.smartlife.view.SmartLifeRLContainer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class SmartLifeAllDeviceActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private SmartLifeRLContainer f19911a;
    private TextView b;
    private TextView c;
    /* access modifiers changed from: private */
    public Map<Integer, SmartLifeItem> d = new HashMap();
    /* access modifiers changed from: private */
    public XQProgressDialog e;
    /* access modifiers changed from: private */
    public boolean f = false;
    private int g = 1;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smart_life_all_devices);
        TitleBarUtil.b((Activity) this);
        Intent intent = getIntent();
        if (intent != null) {
            this.g = intent.getIntExtra("source", 1);
        }
        b();
    }

    private void a() {
        if (!CoreApi.a().q()) {
            String c2 = SharePrefsManager.c(this, "smart_life", "smart_life_not_login_devices", "");
            if (!TextUtils.isEmpty(c2)) {
                try {
                    JSONArray jSONArray = new JSONArray(c2);
                    int length = jSONArray.length();
                    if (length != 0) {
                        for (int i = 0; i < length; i++) {
                            SmartLifeItem a2 = SmartLifeItem.a(jSONArray.optJSONObject(i));
                            if (a2 != null) {
                                this.d.put(Integer.valueOf(a2.f19933a), a2);
                            }
                        }
                    }
                } catch (JSONException unused) {
                }
            }
        } else if (!this.e.isShowing()) {
            this.e.show();
            UserConfigApi.a().a((Context) this, 0, new String[]{"3"}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
                /* renamed from: a */
                public void onSuccess(ArrayList<UserConfig> arrayList) {
                    UserConfig.UserConfigAttr a2;
                    SmartLifeAllDeviceActivity.this.e.dismiss();
                    if (arrayList != null && arrayList.size() > 0) {
                        UserConfig userConfig = arrayList.get(0);
                        if (userConfig.D != null && userConfig.D.size() > 1 && (a2 = DeviceSortUtil.a(userConfig, "value")) != null) {
                            try {
                                JSONArray jSONArray = new JSONArray(a2.b);
                                if (jSONArray.length() != 0) {
                                    final ArrayList arrayList2 = new ArrayList();
                                    for (int i = 0; i < jSONArray.length(); i++) {
                                        arrayList2.add(SmartLifeItem.a(jSONArray.optJSONObject(i)));
                                    }
                                    SmartLifeAllDeviceActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            SmartLifeAllDeviceActivity.this.d.clear();
                                            for (SmartLifeItem smartLifeItem : arrayList2) {
                                                if (smartLifeItem != null) {
                                                    SmartLifeAllDeviceActivity.this.d.put(Integer.valueOf(smartLifeItem.f19933a), smartLifeItem);
                                                }
                                            }
                                            SmartLifeAllDeviceActivity.this.c();
                                        }
                                    });
                                }
                            } catch (JSONException unused) {
                            }
                        }
                    }
                }

                public void onFailure(Error error) {
                    SmartLifeAllDeviceActivity.this.e.dismiss();
                    ToastUtil.a((int) R.string.update_failure);
                }
            });
        }
    }

    private void b() {
        d();
        this.b = (TextView) findViewById(R.id.next_btn);
        this.c = (TextView) findViewById(R.id.button_right);
        this.f19911a = (SmartLifeRLContainer) findViewById(R.id.smart_view_circle_container);
        this.f19911a.setOnSelectListener(new SmartLifeCircleView.SelectListener() {
            public void a(boolean z, SmartLifeItem smartLifeItem) {
                boolean unused = SmartLifeAllDeviceActivity.this.f = true;
                if (z) {
                    SmartLifeAllDeviceActivity.this.d.put(Integer.valueOf(smartLifeItem.f19933a), smartLifeItem);
                } else {
                    SmartLifeItem smartLifeItem2 = null;
                    Iterator it = SmartLifeAllDeviceActivity.this.d.keySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        SmartLifeItem smartLifeItem3 = (SmartLifeItem) SmartLifeAllDeviceActivity.this.d.get((Integer) it.next());
                        if (smartLifeItem3.f19933a == smartLifeItem.f19933a) {
                            smartLifeItem2 = smartLifeItem3;
                            break;
                        }
                    }
                    if (smartLifeItem2 != null) {
                        SmartLifeAllDeviceActivity.this.d.remove(Integer.valueOf(smartLifeItem2.f19933a));
                    }
                }
                SmartLifeAllDeviceActivity.this.e();
            }
        });
        findViewById(R.id.button_left).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartLifeAllDeviceActivity.this.finish();
            }
        });
        c();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f19911a.startAnim();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f19911a.stopAnim();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.g == 0) {
            findViewById(R.id.buttonPanel).setVisibility(8);
            this.b.setVisibility(0);
        } else {
            findViewById(R.id.buttonPanel).setVisibility(0);
            this.b.setVisibility(8);
        }
        e();
        ArrayList<SmartLifeItem> arrayList = new ArrayList<>();
        arrayList.addAll(this.d.values());
        for (SmartLifeItem updateState : arrayList) {
            this.f19911a.updateState(updateState, true);
        }
    }

    private void d() {
        this.e = new XQProgressDialog(this);
        this.e.setMessage(getString(R.string.smart_life_changing));
        this.e.setCancelable(true);
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.d.size() > 0) {
            this.b.setEnabled(true);
            this.b.setAlpha(1.0f);
            AnonymousClass4 r0 = new View.OnClickListener() {
                public void onClick(View view) {
                    if (SmartLifeAllDeviceActivity.this.f) {
                        SmartLifeAllDeviceActivity.this.f();
                        return;
                    }
                    SmartLifeAllDeviceActivity.this.startActivity(new Intent(SmartLifeAllDeviceActivity.this, SmartLifeDevicesChangedActivity.class));
                }
            };
            this.b.setOnClickListener(r0);
            this.c.setOnClickListener(r0);
            this.c.setEnabled(true);
            this.b.setTextColor(getResources().getColor(R.color.white));
            this.c.setTextColor(getResources().getColor(R.color.white));
            return;
        }
        this.b.setEnabled(false);
        this.b.setTextColor(getResources().getColor(R.color.white_50_transparent));
        this.c.setEnabled(false);
        this.c.setTextColor(getResources().getColor(R.color.white_50_transparent));
    }

    /* access modifiers changed from: private */
    public void f() {
        if (CoreApi.a().q()) {
            UserConfig userConfig = new UserConfig();
            userConfig.B = 0;
            userConfig.C = "3";
            userConfig.D = new ArrayList<>();
            long currentTimeMillis = System.currentTimeMillis();
            ArrayList<UserConfig.UserConfigAttr> arrayList = userConfig.D;
            arrayList.add(new UserConfig.UserConfigAttr("time", "" + currentTimeMillis));
            JSONArray jSONArray = new JSONArray();
            for (Integer num : this.d.keySet()) {
                SmartLifeItem smartLifeItem = this.d.get(num);
                if (smartLifeItem != null) {
                    jSONArray.put(smartLifeItem.a());
                }
            }
            userConfig.D.add(new UserConfig.UserConfigAttr("value", jSONArray.toString()));
            UserConfigApi.a().a(SHApplication.getAppContext(), userConfig, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    SmartLifeAllDeviceActivity.this.e.dismiss();
                    SharePrefsManager.a(SHApplication.getAppContext(), "smart_life", "smart_life_page_shown", true);
                    if (SmartLifeAllDeviceActivity.this.isValid()) {
                        SmartLifeAllDeviceActivity.this.startActivity(new Intent(SmartLifeAllDeviceActivity.this, SmartLifeDevicesChangedActivity.class));
                    }
                }

                public void onFailure(Error error) {
                    SmartLifeAllDeviceActivity.this.e.dismiss();
                    ToastUtil.a((int) R.string.change_fail);
                }
            });
            this.e.show();
            return;
        }
        SharePrefsManager.a(SHApplication.getAppContext(), "smart_life", "smart_life_page_shown", true);
        JSONArray jSONArray2 = new JSONArray();
        for (Integer num2 : this.d.keySet()) {
            SmartLifeItem smartLifeItem2 = this.d.get(num2);
            if (smartLifeItem2 != null) {
                jSONArray2.put(smartLifeItem2.a());
            }
        }
        SharePrefsManager.a((Context) this, "smart_life", "smart_life_not_login_devices", jSONArray2.toString());
        startActivity(new Intent(this, SmartLifeDevicesChangedActivity.class));
    }
}
