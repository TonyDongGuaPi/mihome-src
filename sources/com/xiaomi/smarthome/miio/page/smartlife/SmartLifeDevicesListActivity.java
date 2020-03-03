package com.xiaomi.smarthome.miio.page.smartlife;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
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
import com.xiaomi.smarthome.library.common.widget.CircleIndicator;
import com.xiaomi.smarthome.library.common.widget.MultiViewPager;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartLifeDevicesListActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private View f19925a;
    /* access modifiers changed from: private */
    public List<SmartLifeItemWraper> b = new ArrayList();
    /* access modifiers changed from: private */
    public Map<String, String> c = new HashMap();
    /* access modifiers changed from: private */
    public XQProgressDialog d;
    /* access modifiers changed from: private */
    public MultiViewPager e;
    /* access modifiers changed from: private */
    public MultiViewPagerAdapter f;
    private CircleIndicator g;
    private ViewPager.PageTransformer h;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smart_life_devices_list);
        TitleBarUtil.b((Activity) this);
        a();
        c();
    }

    private void a() {
        ((TextView) findViewById(R.id.module_a_3_return_transparent_title)).setText(R.string.smart_life_title_device_selected);
        findViewById(R.id.module_a_3_return_transparent_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartLifeDevicesListActivity.this.finish();
            }
        });
        this.e = (MultiViewPager) findViewById(R.id.pager);
        this.f = new MultiViewPagerAdapter(getSupportFragmentManager());
        this.e.setAdapter(this.f);
        this.h = new ViewPager.PageTransformer() {
            public void transformPage(View view, float f) {
                float abs = (Math.abs(Math.abs(f) - 1.0f) / 2.0f) + 0.55f;
                view.setScaleX(abs);
                view.setScaleY(abs);
                view.setAlpha(((double) Math.abs(f)) >= 1.0d ? 0.6f : 1.0f - (Math.abs(f) / 2.5f));
            }
        };
        this.e.setPageTransformer(false, this.h);
        this.f19925a = findViewById(R.id.common_white_empty_view);
        this.g = (CircleIndicator) findViewById(R.id.indicator);
        this.g.setViewPager(this.e);
        d();
        b();
    }

    private void b() {
        String c2 = SharePrefsManager.c(this, "smart_life", "smart_life_pictures", "");
        if (TextUtils.isEmpty(c2)) {
            SmartLifeItem.a(this, CoreApi.a().s(), new AsyncCallback<String, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(String str) {
                    SmartLifeDevicesListActivity.this.a(str);
                    SharePrefsManager.a((Context) SmartLifeDevicesListActivity.this, "smart_life", "smart_life_pictures", str);
                }
            });
        } else {
            a(c2);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.c.put(next, jSONObject.getString(next));
            }
            for (int i = 0; i < this.f.getCount(); i++) {
                ((SmartLifeDeviceIntroFragment) this.f.getItem(i)).a(this.c);
            }
        } catch (Exception unused) {
        }
    }

    private void c() {
        if (!CoreApi.a().q()) {
            String c2 = SharePrefsManager.c(this, "smart_life", "smart_life_not_login_devices", "");
            if (!TextUtils.isEmpty(c2)) {
                try {
                    JSONArray jSONArray = new JSONArray(c2);
                    int length = jSONArray.length();
                    if (length != 0) {
                        for (int i = 0; i < length; i++) {
                            SmartLifeItemWraper[] a2 = SmartLifeItemWraper.a(SmartLifeItem.a(jSONArray.optJSONObject(i)));
                            if (a2 != null) {
                                Collections.addAll(this.b, a2);
                            }
                        }
                        this.f.notifyDataSetChanged();
                        this.e.setCurrentItem(0, true);
                    }
                } catch (JSONException unused) {
                }
            }
        } else if (!this.d.isShowing()) {
            this.d.show();
            UserConfigApi.a().a((Context) this, 0, new String[]{"3"}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
                /* renamed from: a */
                public void onSuccess(ArrayList<UserConfig> arrayList) {
                    UserConfig.UserConfigAttr a2;
                    SmartLifeDevicesListActivity.this.d.dismiss();
                    SmartLifeDevicesListActivity.this.b.clear();
                    if (arrayList != null && arrayList.size() > 0) {
                        UserConfig userConfig = arrayList.get(0);
                        if (userConfig.D != null && userConfig.D.size() > 1 && (a2 = DeviceSortUtil.a(userConfig, "value")) != null) {
                            try {
                                JSONArray jSONArray = new JSONArray(a2.b);
                                if (jSONArray.length() != 0) {
                                    for (int i = 0; i < jSONArray.length(); i++) {
                                        SmartLifeItemWraper[] a3 = SmartLifeItemWraper.a(SmartLifeItem.a(jSONArray.optJSONObject(i)));
                                        if (a3 != null) {
                                            Collections.addAll(SmartLifeDevicesListActivity.this.b, a3);
                                        }
                                    }
                                    SmartLifeDevicesListActivity.this.runOnUiThread(new Runnable() {
                                        public void run() {
                                            SmartLifeDevicesListActivity.this.f.notifyDataSetChanged();
                                            SmartLifeDevicesListActivity.this.e.setCurrentItem(0, true);
                                        }
                                    });
                                }
                            } catch (JSONException unused) {
                            }
                        }
                    }
                }

                public void onFailure(Error error) {
                    SmartLifeDevicesListActivity.this.d.dismiss();
                    ToastUtil.a((int) R.string.load_data_error);
                }
            });
        }
    }

    private void d() {
        this.d = new XQProgressDialog(this);
        this.d.setMessage(getString(R.string.smart_life_changing));
        this.d.setCancelable(true);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private class MultiViewPagerAdapter extends FragmentStatePagerAdapter {
        public MultiViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            SmartLifeDeviceIntroFragment smartLifeDeviceIntroFragment = new SmartLifeDeviceIntroFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", ((SmartLifeItemWraper) SmartLifeDevicesListActivity.this.b.get(i)).f19932a);
            bundle.putInt("type", ((SmartLifeItemWraper) SmartLifeDevicesListActivity.this.b.get(i)).b);
            smartLifeDeviceIntroFragment.setArguments(bundle);
            smartLifeDeviceIntroFragment.a((Map<String, String>) SmartLifeDevicesListActivity.this.c);
            return smartLifeDeviceIntroFragment;
        }

        public int getCount() {
            return SmartLifeDevicesListActivity.this.b.size();
        }
    }

    public static class SmartLifeItemWraper {

        /* renamed from: a  reason: collision with root package name */
        SmartLifeItem f19932a;
        int b;

        private SmartLifeItemWraper() {
        }

        public static SmartLifeItemWraper[] a(SmartLifeItem smartLifeItem) {
            int[] d = SmartLifeItem.d(smartLifeItem.f19933a);
            if (d == null) {
                return null;
            }
            if (d.length == 1) {
                SmartLifeItemWraper smartLifeItemWraper = new SmartLifeItemWraper();
                smartLifeItemWraper.f19932a = smartLifeItem;
                if (d[0] == R.string.smart_life_intro_tip1) {
                    smartLifeItemWraper.b = 2;
                } else if (d[0] == R.string.smart_life_intro_tip2) {
                    smartLifeItemWraper.b = 1;
                }
                return new SmartLifeItemWraper[]{smartLifeItemWraper};
            } else if (d.length != 2) {
                return null;
            } else {
                SmartLifeItemWraper smartLifeItemWraper2 = new SmartLifeItemWraper();
                smartLifeItemWraper2.f19932a = smartLifeItem;
                smartLifeItemWraper2.b = 2;
                SmartLifeItemWraper smartLifeItemWraper3 = new SmartLifeItemWraper();
                smartLifeItemWraper3.f19932a = smartLifeItem;
                smartLifeItemWraper3.b = 1;
                return new SmartLifeItemWraper[]{smartLifeItemWraper2, smartLifeItemWraper3};
            }
        }
    }
}
