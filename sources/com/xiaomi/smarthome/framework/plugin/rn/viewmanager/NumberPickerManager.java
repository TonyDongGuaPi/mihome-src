package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.libra.Color;
import com.xiaomi.mistatistic.sdk.BaseService;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.view.StringPicker;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.Map;

public class NumberPickerManager extends SimpleViewManager {
    private static String REACT_CLASS = "MIOTNumberPicker";
    private static final String TAG = "NumberPickerManager";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public MiotNumberPicker createViewInstance(ThemedReactContext themedReactContext) {
        MiotNumberPicker miotNumberPicker = new MiotNumberPicker(themedReactContext);
        miotNumberPicker.setPickerType(2);
        return miotNumberPicker;
    }

    @ReactProp(name = "maxValue")
    public void setMaxValue(MiotNumberPicker miotNumberPicker, @Nullable float f) {
        float unused = miotNumberPicker.d = f;
    }

    @ReactProp(defaultFloat = 0.0f, name = "minValue")
    public void setMinValue(MiotNumberPicker miotNumberPicker, @Nullable float f) {
        float unused = miotNumberPicker.e = f;
    }

    @ReactProp(defaultFloat = 1.0f, name = "interval")
    public void setInterval(MiotNumberPicker miotNumberPicker, @Nullable float f) {
        float unused = miotNumberPicker.f = f;
    }

    @ReactProp(name = "defaultValue")
    public void setDefaultValue(MiotNumberPicker miotNumberPicker, @Nullable float f) {
        float unused = miotNumberPicker.g = f;
    }

    @ReactProp(name = "valueFormat")
    public void setValueFormat(MiotNumberPicker miotNumberPicker, @Nullable String str) {
        String unused = miotNumberPicker.h = str;
    }

    @ReactProp(customType = "Color", name = "lineColor")
    public void setLineColor(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        String unused = miotNumberPicker.c = "" + i;
    }

    @ReactProp(customType = "Color", name = "textColor")
    public void setTextColor(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setUnSelectTextColor(i);
    }

    @ReactProp(customType = "Color", name = "selectTextColor")
    public void setSelectTextColor(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setSelectTextColor(i);
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setUnSelectTextSize(DisplayUtils.c(miotNumberPicker.getContext(), (float) i));
    }

    @ReactProp(name = "selectFontSize")
    public void setSelectFontSize(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setSelectTextSize(DisplayUtils.c(miotNumberPicker.getContext(), (float) i));
    }

    @ReactProp(customType = "Color", name = "selectBgColor")
    public void setSelectBgColor(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setSelectItemBgColor(i);
    }

    @ReactProp(name = "unit")
    public void setUnit(MiotNumberPicker miotNumberPicker, @Nullable String str) {
        String unused = miotNumberPicker.i = str;
    }

    @ReactProp(name = "unitFontSize")
    public void setUnitFontSize(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setUnitFontSize(DisplayUtils.c(miotNumberPicker.getContext(), (float) i));
    }

    @ReactProp(customType = "Color", name = "unitTextColor")
    public void setUnitTextColor(MiotNumberPicker miotNumberPicker, @Nullable int i) {
        miotNumberPicker.setUnitTextColor(i);
    }

    @ReactProp(name = "lineStyle")
    @Deprecated
    public void setLineStyle(MiotNumberPicker miotNumberPicker, @Nullable String str) {
        if (TextUtils.isEmpty(str) || "none".equalsIgnoreCase(str)) {
            miotNumberPicker.a(0.0f, 0);
            return;
        }
        float f = 0.5f;
        if ("default".equalsIgnoreCase(str)) {
            miotNumberPicker.a(0.5f, (int) Color.c);
            return;
        }
        int indexOf = str.indexOf(44);
        if (indexOf > 0) {
            try {
                f = Float.parseFloat(str.substring(0, indexOf).trim());
                str = str.substring(indexOf + 1).trim();
            } catch (Exception unused) {
                return;
            }
        }
        miotNumberPicker.a(f, android.graphics.Color.parseColor(str));
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onValueChanged", MapBuilder.of("registrationName", "onValueChanged")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view instanceof MiotNumberPicker) {
            ((MiotNumberPicker) view).setOnValueChangedListener(new StringPicker.OnValueChangeListener() {
                public void a(StringPicker stringPicker, int i, int i2) {
                }

                public void b(StringPicker stringPicker, int i, int i2) {
                    MiotNumberPicker miotNumberPicker = (MiotNumberPicker) stringPicker;
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("oldValue", Float.toString(miotNumberPicker.a(i)));
                    createMap.putString(BaseService.NEW_VALUE, Float.toString(miotNumberPicker.a(i2)));
                    ((RCTEventEmitter) ((ReactContext) stringPicker.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(stringPicker.getId(), "onValueChanged", createMap);
                }
            });
        }
        super.addEventEmitters(themedReactContext, view);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(View view) {
        if (view != null && (view instanceof MiotNumberPicker)) {
            ((MiotNumberPicker) view).a();
        }
        super.onAfterUpdateTransaction(view);
    }

    private class MiotNumberPicker extends StringPicker {
        private Paint b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public float d = 100.0f;
        /* access modifiers changed from: private */
        public float e = 0.0f;
        /* access modifiers changed from: private */
        public float f = 1.0f;
        /* access modifiers changed from: private */
        public float g = 0.0f;
        /* access modifiers changed from: private */
        public String h = null;
        /* access modifiers changed from: private */
        public String i = null;
        private boolean j = false;

        public MiotNumberPicker(Context context) {
            super(context);
        }

        /* access modifiers changed from: private */
        public void a() {
            a(this.c);
            if (this.f == 0.0f) {
                this.f = 1.0f;
            }
            if (TextUtils.isEmpty(this.h)) {
                this.h = MainPageOpManager.f13390a;
            }
            this.mUnit = this.i;
            this.mUnitPos = Math.max(Math.max(Math.max(Math.max(Math.max(a(this.e), a(this.d)), a(this.g)), a(this.e + this.f)), a(this.d + this.f)), a(this.g + this.f));
            b();
        }

        private void b() {
            float f2 = (this.d - this.e) / this.f;
            this.j = f2 > 0.0f;
            int abs = Math.abs((int) f2);
            setMinValue(0);
            setMaxValue(abs);
            setValue(Math.min(abs, Math.max((int) Math.abs((this.g - this.e) / this.f), 0)));
            setFormatter(new StringPicker.Formatter() {
                public String a(int i) {
                    return String.format(MiotNumberPicker.this.h, new Object[]{Float.valueOf(MiotNumberPicker.this.a(i))});
                }
            });
        }

        /* access modifiers changed from: private */
        public float a(int i2) {
            if (!this.j) {
                i2 *= -1;
            }
            return this.e + (((float) i2) * this.f);
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            int i2;
            if (!TextUtils.isEmpty(str)) {
                try {
                    i2 = Integer.parseInt(str);
                } catch (Exception e2) {
                    RnPluginLog.b("MHStringPicker  " + e2.toString());
                    i2 = Color.c;
                }
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(i2);
                paint.setStrokeWidth(0.5f);
                this.mLinePaint = paint;
            } else if (this.b != null) {
                this.mLinePaint = this.b;
            }
        }

        /* access modifiers changed from: package-private */
        @Deprecated
        public void a(float f2, int i2) {
            this.b = null;
            if (f2 > 1.0E-5f) {
                this.b = new Paint();
                this.b.setAntiAlias(true);
                this.b.setColor(i2);
                this.b.setStrokeWidth(f2);
            }
        }

        private float a(float f2) {
            return measureLabelSize(String.format(this.h, new Object[]{Float.valueOf(f2)}));
        }
    }
}
