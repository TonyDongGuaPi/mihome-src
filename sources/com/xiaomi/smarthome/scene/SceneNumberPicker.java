package com.xiaomi.smarthome.scene;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.NumberPicker;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class SceneNumberPicker extends BaseActivity {
    public static final double EPSILON = 1.0E-4d;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public NumberPicker f21325a;
    /* access modifiers changed from: private */
    public float b;
    /* access modifiers changed from: private */
    public float c;
    private float d;
    private float e;
    /* access modifiers changed from: private */
    public String f = null;
    /* access modifiers changed from: private */
    public String g = null;
    private ArrayList<CommonSceneOnline.NumberPickerTag> h = null;
    /* access modifiers changed from: private */
    public String i;
    private int j = -1;
    /* access modifiers changed from: private */
    public ArrayList<String> k = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> l = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.scene_number_picker);
        this.b = getIntent().getFloatExtra("max_value", 0.0f);
        this.c = getIntent().getFloatExtra("min_value", 0.0f);
        this.d = getIntent().getFloatExtra(Constants.Name.INTERVAL, 1.0f);
        this.i = getIntent().getStringExtra("degree");
        this.f = getIntent().getStringExtra("json_tag");
        this.e = getIntent().getFloatExtra("default_value", 0.0f);
        this.h = getIntent().getParcelableArrayListExtra("show_tags");
        String stringExtra = getIntent().getStringExtra("title");
        this.g = getIntent().getStringExtra("formatter");
        b();
        if (getIntent().hasExtra("last_value")) {
            String stringExtra2 = getIntent().getStringExtra("last_value");
            if (!TextUtils.isEmpty(this.f)) {
                if (this.f.equalsIgnoreCase("equal")) {
                    try {
                        this.e = Float.valueOf(stringExtra2).floatValue();
                    } catch (NumberFormatException unused) {
                    }
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(stringExtra2);
                        if (this.f.equalsIgnoreCase("max")) {
                            this.e = (float) jSONObject.optDouble("min");
                        } else {
                            this.e = (float) jSONObject.optDouble("max");
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        this.f21325a = (NumberPicker) findViewById(R.id.scene_number_picker);
        if (!a()) {
            finish();
            return;
        }
        String[] strArr = new String[this.k.size()];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = this.k.get(i2);
        }
        this.f21325a.setMinValue(0);
        this.f21325a.setMaxValue(this.k.size() - 1);
        this.f21325a.setDisplayedValues(strArr);
        this.f21325a.setWrapSelectorWheel(true);
        if (this.j != -1) {
            this.f21325a.setValue(this.j);
            NumberPicker numberPicker = this.f21325a;
            numberPicker.setLabel(this.i + "  " + this.l.get(this.j));
        } else {
            this.f21325a.setValue(0);
            NumberPicker numberPicker2 = this.f21325a;
            numberPicker2.setLabel(this.i + "  " + this.l.get(0));
        }
        this.f21325a.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                NumberPicker access$200 = SceneNumberPicker.this.f21325a;
                access$200.setLabel(SceneNumberPicker.this.i + "  " + ((String) SceneNumberPicker.this.l.get(i2)));
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SceneNumberPicker.this.setResult(0);
                SceneNumberPicker.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(stringExtra);
        textView.setText(R.string.complete);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                float floatValue = Float.valueOf((String) SceneNumberPicker.this.k.get(SceneNumberPicker.this.f21325a.getValue())).floatValue();
                Object valueOf = Float.valueOf(floatValue);
                int i = (int) floatValue;
                if (((double) Math.abs(((float) i) - floatValue)) < 1.0E-4d) {
                    valueOf = Integer.valueOf(i);
                }
                Intent intent = new Intent();
                if (!TextUtils.isEmpty(SceneNumberPicker.this.f)) {
                    if (!SceneNumberPicker.this.f.equals("equal")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            if (SceneNumberPicker.this.f.equals("max")) {
                                jSONObject.put("min", valueOf);
                                jSONObject.put("max", (double) SceneNumberPicker.this.b);
                            } else {
                                jSONObject.put("min", (double) SceneNumberPicker.this.c);
                                jSONObject.put("max", valueOf);
                            }
                            intent.putExtra("value", jSONObject.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else if (valueOf instanceof Integer) {
                        intent.putExtra("value", (Integer) valueOf);
                    } else {
                        intent.putExtra("value", floatValue);
                    }
                } else if (valueOf instanceof Integer) {
                    intent.putExtra("value", (Integer) valueOf);
                } else {
                    intent.putExtra("value", floatValue);
                }
                if (!TextUtils.isEmpty(SceneNumberPicker.this.g)) {
                    try {
                        if (valueOf instanceof Integer) {
                            intent.putExtra("key_name", String.format(SceneNumberPicker.this.g, new Object[]{(Integer) valueOf}));
                        } else {
                            intent.putExtra("key_name", String.format(SceneNumberPicker.this.g, new Object[]{Float.valueOf(floatValue)}));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        intent.putExtra("key_name", valueOf + "");
                    }
                }
                SceneNumberPicker.this.setResult(-1, intent);
                SceneNumberPicker.this.finish();
            }
        });
    }

    private boolean a() {
        CommonSceneOnline.NumberPickerTag next;
        if (this.c >= this.b) {
            return false;
        }
        double d2 = (double) this.c;
        int i2 = (this.d * 100.0f) % 10.0f == 0.0f ? 1 : 2;
        while (true) {
            if (d2 > ((double) this.b) && Math.abs(a(d2, (double) this.b)) >= 1.0E-4d) {
                return true;
            }
            int i3 = (int) d2;
            double d3 = (double) i3;
            Double.isNaN(d3);
            if (Math.abs(d3 - d2) < 1.0E-4d) {
                this.k.add(String.format("%d", new Object[]{Integer.valueOf(i3)}));
            } else {
                this.k.add(String.format("%." + i2 + "f", new Object[]{Double.valueOf(d2)}));
            }
            if (Math.abs(a(d2, (double) this.e)) < 1.0E-4d) {
                this.j = this.k.size() - 1;
            }
            String str = "";
            if (this.h != null) {
                Iterator<CommonSceneOnline.NumberPickerTag> it = this.h.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    next = it.next();
                    if (Math.abs(a(d2, (double) next.f21202a)) < 1.0E-4d || Math.abs(a(d2, (double) next.b)) < 1.0E-4d || (d2 > ((double) next.f21202a) && d2 < ((double) next.b))) {
                        str = str + next.c;
                    }
                }
                str = str + next.c;
            }
            this.l.add(str);
            double d4 = (double) this.d;
            Double.isNaN(d4);
            d2 += d4;
        }
    }

    private void b() {
        if (!TextUtils.isEmpty(this.g)) {
            if (this.g.contains("d%") && !this.g.contains("d%%")) {
                this.g = this.g.replace("d%", "d%%");
            } else if (this.g.contains("f%") && !this.g.contains("f%%")) {
                this.g = this.g.replace("f%", "f%%");
            }
        }
    }

    private double a(double d2, double d3) {
        return new BigDecimal(Double.toString(d2)).subtract(new BigDecimal(Double.toString(d3))).doubleValue();
    }
}
