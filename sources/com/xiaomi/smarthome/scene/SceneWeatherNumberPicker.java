package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import com.xiaomi.smarthome.miio.areainfo.LocationData;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneWeatherNumberPicker extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21333a = "SceneWeatherNumberPicker";
    private static final double b = 1.0E-4d;
    /* access modifiers changed from: private */
    public NumberPicker c;
    /* access modifiers changed from: private */
    public float d;
    /* access modifiers changed from: private */
    public float e;
    private float f;
    private float g;
    /* access modifiers changed from: private */
    public String h = null;
    private ArrayList<CommonSceneOnline.NumberPickerTag> i = null;
    WeatherInnerCondition innerCondition;
    /* access modifiers changed from: private */
    public String j;
    private int k = -1;
    /* access modifiers changed from: private */
    public ArrayList<String> l = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> m = new ArrayList<>();
    Object result;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.scene_number_picker);
        this.innerCondition = CreateSceneManager.a().n();
        if (this.innerCondition == null) {
            finish();
        }
        this.d = getIntent().getFloatExtra("max_value", 0.0f);
        this.e = getIntent().getFloatExtra("min_value", 0.0f);
        this.f = getIntent().getFloatExtra(Constants.Name.INTERVAL, 1.0f);
        this.j = getIntent().getStringExtra("degree");
        this.h = getIntent().getStringExtra("json_tag");
        this.g = getIntent().getFloatExtra("default_value", 0.0f);
        this.i = getIntent().getParcelableArrayListExtra("show_tags");
        String stringExtra = getIntent().getStringExtra("title");
        if (getIntent().hasExtra("last_value")) {
            String stringExtra2 = getIntent().getStringExtra("last_value");
            if (!TextUtils.isEmpty(this.h)) {
                try {
                    JSONObject jSONObject = new JSONObject(stringExtra2);
                    if (this.h.equalsIgnoreCase("max")) {
                        this.g = (float) jSONObject.optDouble("min");
                    } else {
                        this.g = (float) jSONObject.optDouble("max");
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.c = (NumberPicker) findViewById(R.id.scene_number_picker);
        if (!a()) {
            finish();
            return;
        }
        String[] strArr = new String[this.l.size()];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = this.l.get(i2);
        }
        this.c.setMinValue(0);
        this.c.setMaxValue(this.l.size() - 1);
        this.c.setDisplayedValues(strArr);
        this.c.setWrapSelectorWheel(true);
        if (this.k != -1) {
            this.c.setValue(this.k);
            NumberPicker numberPicker = this.c;
            numberPicker.setLabel(this.j + "  " + this.m.get(this.k));
        } else {
            this.c.setValue(0);
            NumberPicker numberPicker2 = this.c;
            numberPicker2.setLabel(this.j + "  " + this.m.get(0));
        }
        this.c.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                NumberPicker access$200 = SceneWeatherNumberPicker.this.c;
                access$200.setLabel(SceneWeatherNumberPicker.this.j + "  " + ((String) SceneWeatherNumberPicker.this.m.get(i2)));
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SceneWeatherNumberPicker.this.setResult(0);
                SceneWeatherNumberPicker.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(stringExtra);
        textView.setText(R.string.complete);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                float f;
                String str = (String) SceneWeatherNumberPicker.this.l.get(SceneWeatherNumberPicker.this.c.getValue());
                try {
                    f = Float.valueOf(str).floatValue();
                } catch (NumberFormatException unused) {
                    try {
                        f = NumberFormat.getNumberInstance(ServerCompact.c((Context) SceneWeatherNumberPicker.this)).parse(str).floatValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                        f = 0.0f;
                    }
                }
                SceneWeatherNumberPicker.this.result = Float.valueOf(f);
                int i = (int) f;
                if (((double) Math.abs(((float) i) - f)) < 1.0E-4d) {
                    SceneWeatherNumberPicker.this.result = Integer.valueOf(i);
                }
                new Intent();
                if (!TextUtils.isEmpty(SceneWeatherNumberPicker.this.h)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (SceneWeatherNumberPicker.this.h.equals("max")) {
                            jSONObject.put("min", SceneWeatherNumberPicker.this.result);
                            jSONObject.put("max", (double) SceneWeatherNumberPicker.this.d);
                        } else {
                            jSONObject.put("min", (double) SceneWeatherNumberPicker.this.e);
                            jSONObject.put("max", SceneWeatherNumberPicker.this.result);
                        }
                        SceneWeatherNumberPicker.this.innerCondition.j = jSONObject;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    SceneWeatherNumberPicker.this.innerCondition.j = SceneWeatherNumberPicker.this.result;
                }
                if (SceneWeatherNumberPicker.this.result instanceof Integer) {
                    SceneWeatherNumberPicker.this.innerCondition.k = ((Integer) SceneWeatherNumberPicker.this.result).intValue();
                }
                ShowProvinceHelper.a((Activity) SceneWeatherNumberPicker.this, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
                    public void a(Context context, String str, String str2, String str3, String str4) {
                        if (SceneWeatherNumberPicker.this.innerCondition != null) {
                            SceneWeatherNumberPicker.this.innerCondition.h = str4;
                            SceneWeatherNumberPicker.this.innerCondition.g = str3;
                            if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.humidity_above_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result, Operators.MOD});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_HUMIDITY.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.humidity_less_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result, Operators.MOD});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.temperature_above_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_TEMPERATURE.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.temperature_less_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.aqi_above_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_AQI.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.aqi_less_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            }
                            SceneWeatherNumberPicker.this.b();
                        }
                    }

                    public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                        if (SceneWeatherNumberPicker.this.innerCondition != null) {
                            LocationData a2 = ShowProvinceHelper.a(context, address);
                            SceneWeatherNumberPicker.this.innerCondition.h = a2.f;
                            SceneWeatherNumberPicker.this.innerCondition.g = a2.e;
                            if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.humidity_above_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result, Operators.MOD});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_HUMIDITY.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.humidity_less_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result, Operators.MOD});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.temperature_above_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_TEMPERATURE.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.temperature_less_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.aqi_above_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            } else if (SceneWeatherNumberPicker.this.innerCondition.e == WeatherInnerCondition.WeatherConditionType.TYPE_LESS_AQI.conditionId) {
                                SceneWeatherNumberPicker.this.innerCondition.i = String.format(SceneWeatherNumberPicker.this.getResources().getString(R.string.aqi_less_format), new Object[]{SceneWeatherNumberPicker.this.innerCondition.g, SceneWeatherNumberPicker.this.result});
                            }
                            SceneWeatherNumberPicker.this.b();
                        }
                    }
                });
            }
        });
    }

    private boolean a() {
        CommonSceneOnline.NumberPickerTag next;
        if (this.e >= this.d) {
            return false;
        }
        float f2 = this.e;
        while (true) {
            if (f2 > this.d && ((double) Math.abs(f2 - this.d)) >= 1.0E-4d) {
                return true;
            }
            int i2 = (int) f2;
            if (((double) Math.abs(((float) i2) - f2)) < 1.0E-4d) {
                this.l.add(String.format(Locale.US, "%d", new Object[]{Integer.valueOf(i2)}));
            } else {
                this.l.add(String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(f2)}));
            }
            if (((double) Math.abs(f2 - this.g)) < 1.0E-4d) {
                this.k = this.l.size() - 1;
            }
            String str = "";
            if (this.i != null) {
                Iterator<CommonSceneOnline.NumberPickerTag> it = this.i.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    next = it.next();
                    if (((double) Math.abs(f2 - next.f21202a)) < 1.0E-4d || ((double) Math.abs(f2 - next.b)) < 1.0E-4d || (f2 > next.f21202a && f2 < next.b)) {
                        str = str + next.c;
                    }
                }
                str = str + next.c;
            }
            this.m.add(str);
            f2 += this.f;
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }
}
