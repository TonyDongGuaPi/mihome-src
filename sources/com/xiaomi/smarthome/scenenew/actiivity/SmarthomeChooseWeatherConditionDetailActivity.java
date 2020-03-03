package com.xiaomi.smarthome.scenenew.actiivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.miio.areainfo.LocationData;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.SceneWeatherNumberPicker;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeChooseWeatherConditionActivity;
import java.util.ArrayList;

public class SmarthomeChooseWeatherConditionDetailActivity extends BaseActivity {
    public static final String WEATHER_DATA = "weather_data";

    /* renamed from: a  reason: collision with root package name */
    private static final String f21837a = "SmarthomeChooseWeatherConditionDetailActivity";
    /* access modifiers changed from: private */
    public boolean b = false;
    SceneApi.Condition condition;
    WeatherInnerCondition innerCondition;
    Adapter mAdapter;
    Context mContext;
    SmarthomeChooseWeatherConditionActivity.WeatherData mData;
    LayoutInflater mInflater;
    @BindView(2131430478)
    ListView mListView;
    @BindView(2131430969)
    ImageView mReturnIV;
    @BindView(2131430975)
    TextView mTitleTV;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_weather_condition_layout);
        ButterKnife.bind((Activity) this);
        this.mInflater = LayoutInflater.from(this);
        this.mContext = this;
        a();
        if (this.mData == null || TextUtils.isEmpty(this.mData.f21835a)) {
            finish();
            return;
        }
        this.mTitleTV.setText(this.mData.f21835a);
        b();
    }

    private void a() {
        this.condition = CreateSceneManager.a().i();
        if (this.condition == null || !this.condition.b()) {
            this.b = false;
            Intent intent = getIntent();
            if (intent == null) {
                finish();
                return;
            }
            this.mData = (SmarthomeChooseWeatherConditionActivity.WeatherData) intent.getParcelableExtra(WEATHER_DATA);
            if (this.mData == null || this.mData.c == null) {
                finish();
                return;
            }
            this.innerCondition = CreateSceneManager.a().n();
            if (this.innerCondition == null) {
                finish();
                return;
            }
            return;
        }
        this.b = true;
        this.mData = a(this.condition.j.d);
        if (this.mData == null || this.mData.c == null) {
            finish();
            return;
        }
        this.innerCondition = new WeatherInnerCondition((Device) null);
        this.innerCondition.f = this.condition.j.b;
        this.innerCondition.e = this.condition.j.d;
    }

    private void b() {
        this.mReturnIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmarthomeChooseWeatherConditionDetailActivity.this.c();
            }
        });
        this.mAdapter = new Adapter();
        this.mListView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    public void c() {
        setResult(0);
        finish();
    }

    public void onBackPressed() {
        c();
    }

    public class Adapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        public Adapter() {
        }

        public int getCount() {
            return SmarthomeChooseWeatherConditionDetailActivity.this.mData.c.size();
        }

        public Object getItem(int i) {
            return SmarthomeChooseWeatherConditionDetailActivity.this.mData.c.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(SmarthomeChooseWeatherConditionDetailActivity.this.mContext).inflate(R.layout.smarthome_action_item, (ViewGroup) null);
            }
            final WeatherInnerCondition.WeatherConditionType weatherConditionType = SmarthomeChooseWeatherConditionDetailActivity.this.mData.c.get(i);
            TextView textView = (TextView) view.findViewById(R.id.description_text);
            ImageView imageView = (ImageView) view.findViewById(R.id.lock_item);
            textView.setText(weatherConditionType.conditionNameId);
            final boolean access$100 = SmarthomeChooseWeatherConditionDetailActivity.this.b(weatherConditionType);
            if (SmarthomeChooseWeatherConditionDetailActivity.this.b) {
                if (SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.d == weatherConditionType.conditionId) {
                    textView.setTextColor(SmarthomeChooseWeatherConditionDetailActivity.this.getResources().getColor(R.color.class_text_17));
                } else {
                    textView.setTextColor(SmarthomeChooseWeatherConditionDetailActivity.this.getResources().getColor(R.color.std_list_title));
                }
                imageView.setVisibility(8);
            } else {
                textView.setTextColor(SmarthomeChooseWeatherConditionDetailActivity.this.getResources().getColor(R.color.std_list_title));
                if (access$100) {
                    imageView.setVisibility(8);
                } else {
                    imageView.setVisibility(0);
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SmarthomeChooseWeatherConditionDetailActivity.this.b || access$100) {
                        SmarthomeChooseWeatherConditionDetailActivity.this.a(weatherConditionType);
                    } else {
                        Toast.makeText(SmarthomeChooseWeatherConditionDetailActivity.this, R.string.scene_unclickable_toast_2, 0).show();
                    }
                }
            });
            return view;
        }
    }

    /* access modifiers changed from: private */
    public void a(WeatherInnerCondition.WeatherConditionType weatherConditionType) {
        if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_SUNRISE || weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_SUNSET) {
            this.innerCondition.e = weatherConditionType.conditionId;
            this.innerCondition.i = getString(weatherConditionType.conditionNameId);
            this.innerCondition.f = getString(weatherConditionType.conditionNameId);
            ShowProvinceHelper.a((Activity) this, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
                public void a(Context context, String str, String str2, String str3, String str4) {
                    SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.h = str4;
                    SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.g = str3;
                    WeatherInnerCondition weatherInnerCondition = SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition;
                    weatherInnerCondition.i = SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.g + " " + SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.i;
                    LogUtil.a(SmarthomeChooseWeatherConditionDetailActivity.f21837a, "cityId" + SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.h + "   cityname" + SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.g);
                    if (SmarthomeChooseWeatherConditionDetailActivity.this.b) {
                        SceneApi.Condition a2 = SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.a(SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.e, (Intent) null);
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.d = a2.j.d;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.c = a2.j.c;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.f = a2.j.f;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.e = a2.j.e;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.g = a2.j.g;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.f21522a = a2.f21522a;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.k = a2.k;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.f21525a = a2.j.f21525a;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.b = a2.j.b;
                    }
                    SmarthomeChooseWeatherConditionDetailActivity.this.d();
                }

                public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                    LocationData a2 = ShowProvinceHelper.a(context, address);
                    SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.h = a2.f;
                    SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.g = a2.e;
                    WeatherInnerCondition weatherInnerCondition = SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition;
                    weatherInnerCondition.i = SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.g + " " + SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.i;
                    LogUtil.a(SmarthomeChooseWeatherConditionDetailActivity.f21837a, "cityId" + SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.h + "   cityname" + SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.g);
                    if (SmarthomeChooseWeatherConditionDetailActivity.this.b) {
                        SceneApi.Condition a3 = SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.a(SmarthomeChooseWeatherConditionDetailActivity.this.innerCondition.e, (Intent) null);
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.d = a3.j.d;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.c = a3.j.c;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.f = a3.j.f;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.e = a3.j.e;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.g = a3.j.g;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.f21522a = a3.f21522a;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.k = a3.k;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.f21525a = a3.j.f21525a;
                        SmarthomeChooseWeatherConditionDetailActivity.this.condition.j.b = a3.j.b;
                    }
                    SmarthomeChooseWeatherConditionDetailActivity.this.d();
                }
            });
        } else if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE) {
            this.innerCondition.e = weatherConditionType.conditionId;
            a(100.0f, -40.0f, "℃", 29.0f, getString(weatherConditionType.conditionNameId), "", 1.0f, "max");
        } else if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_TEMPERATURE) {
            this.innerCondition.e = weatherConditionType.conditionId;
            a(100.0f, -40.0f, "℃", 26.0f, getString(weatherConditionType.conditionNameId), "", 1.0f, "min");
        } else if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY) {
            this.innerCondition.e = weatherConditionType.conditionId;
            a(100.0f, 0.0f, Operators.MOD, 70.0f, getString(weatherConditionType.conditionNameId), "", 1.0f, "max");
        } else if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_HUMIDITY) {
            this.innerCondition.e = weatherConditionType.conditionId;
            a(100.0f, 0.0f, Operators.MOD, 30.0f, getString(weatherConditionType.conditionNameId), "", 1.0f, "min");
        } else if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI) {
            this.innerCondition.e = weatherConditionType.conditionId;
            a(2000.0f, 5.0f, "μg/m³", 75.0f, getString(weatherConditionType.conditionNameId), "", 5.0f, "max");
        } else if (weatherConditionType == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_AQI) {
            this.innerCondition.e = weatherConditionType.conditionId;
            a(2000.0f, 5.0f, "μg/m³", 35.0f, getString(weatherConditionType.conditionNameId), "", 5.0f, "min");
        }
    }

    /* access modifiers changed from: private */
    public boolean b(WeatherInnerCondition.WeatherConditionType weatherConditionType) {
        SceneApi.SmartHomeScene g = CreateSceneManager.a().g();
        if (g == null || g.l == null || g.l.size() == 0) {
            return true;
        }
        for (int i = 0; i < g.l.size(); i++) {
            SceneApi.Condition condition2 = g.l.get(i);
            if (condition2.j != null && (condition2.j.d == weatherConditionType.conditionId || !CreateSceneManager.a().a(Integer.valueOf(weatherConditionType.compatibleId)))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void d() {
        setResult(-1);
        finish();
    }

    private void a(float f, float f2, String str, float f3, String str2, String str3, float f4, String str4) {
        Intent intent = new Intent(this.mContext, SceneWeatherNumberPicker.class);
        intent.putExtra("max_value", f);
        intent.putExtra("min_value", f2);
        intent.putExtra(Constants.Name.INTERVAL, f4);
        intent.putExtra("degree", str);
        intent.putExtra("json_tag", str4);
        intent.putExtra("default_value", f3);
        intent.putExtra("title", str2);
        intent.putExtra("formatter", str3);
        if (this.b) {
            CreateSceneManager.a().a(this.innerCondition);
        }
        startActivityForResult(intent, 106);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 106 || i2 != -1) {
            return;
        }
        if (!this.b) {
            setResult(-1);
            finish();
            return;
        }
        SceneApi.Condition a2 = this.innerCondition.a(this.innerCondition.e, (Intent) null);
        this.condition.j.d = a2.j.d;
        this.condition.j.c = a2.j.c;
        this.condition.j.g = a2.j.g;
        this.condition.j.f = a2.j.f;
        this.condition.j.e = a2.j.e;
        this.condition.f21522a = a2.f21522a;
        this.condition.k = a2.k;
        this.condition.j.f21525a = a2.j.f21525a;
        this.condition.j.b = a2.j.b;
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private SmarthomeChooseWeatherConditionActivity.WeatherData a(int i) {
        if (i == WeatherInnerCondition.WeatherConditionType.TYPE_SUNRISE.conditionId || i == WeatherInnerCondition.WeatherConditionType.TYPE_SUNSET.conditionId) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(WeatherInnerCondition.WeatherConditionType.TYPE_SUNRISE);
            arrayList.add(WeatherInnerCondition.WeatherConditionType.TYPE_SUNSET);
            return new SmarthomeChooseWeatherConditionActivity.WeatherData(getString(R.string.condition_weather_sunrise), R.drawable.scene_sun_icon, arrayList);
        } else if (i == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_TEMPERATURE.conditionId || i == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE.conditionId) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE);
            arrayList2.add(WeatherInnerCondition.WeatherConditionType.TYPE_LESS_TEMPERATURE);
            return new SmarthomeChooseWeatherConditionActivity.WeatherData(getString(R.string.condition_weather_temperature), R.drawable.scene_temperature_icon, arrayList2);
        } else if (i == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY.conditionId || i == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_HUMIDITY.conditionId) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY);
            arrayList3.add(WeatherInnerCondition.WeatherConditionType.TYPE_LESS_HUMIDITY);
            return new SmarthomeChooseWeatherConditionActivity.WeatherData(getString(R.string.condition_weather_humidity), R.drawable.scene_humidity_icon, arrayList3);
        } else if (i != WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI.conditionId && i != WeatherInnerCondition.WeatherConditionType.TYPE_LESS_AQI.conditionId) {
            return null;
        } else {
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI);
            arrayList4.add(WeatherInnerCondition.WeatherConditionType.TYPE_LESS_AQI);
            return new SmarthomeChooseWeatherConditionActivity.WeatherData(getString(R.string.condition_weather_aqi), R.drawable.scene_pm25_icon, arrayList4);
        }
    }
}
