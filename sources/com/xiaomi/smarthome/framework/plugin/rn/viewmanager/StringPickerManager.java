package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.libra.Color;
import com.xiaomi.mistatistic.sdk.BaseService;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.view.StringPicker;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.Map;

public class StringPickerManager extends SimpleViewManager {
    private static String REACT_CLASS = "MHStringPicker";
    private static final String TAG = "StringPickerManager";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public MiotStringPicker createViewInstance(ThemedReactContext themedReactContext) {
        MiotStringPicker miotStringPicker = new MiotStringPicker(themedReactContext);
        miotStringPicker.setPickerType(1);
        return miotStringPicker;
    }

    @ReactProp(name = "defaultValue")
    public void setDefaultValue(MiotStringPicker miotStringPicker, @Nullable String str) {
        String unused = miotStringPicker.b = str;
    }

    @ReactProp(name = "dataSource")
    public void setDataSource(MiotStringPicker miotStringPicker, @Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            int size = readableArray.size();
            String[] unused = miotStringPicker.c = new String[size];
            for (int i = 0; i < size; i++) {
                miotStringPicker.c[i] = readableArray.getString(i);
            }
        }
    }

    @ReactProp(customType = "Color", name = "lineColor")
    public void setLineColor(MiotStringPicker miotStringPicker, @Nullable int i) {
        String unused = miotStringPicker.d = "" + i;
    }

    @ReactProp(customType = "Color", name = "textColor")
    public void setTextColor(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setUnSelectTextColor(i);
    }

    @ReactProp(customType = "Color", name = "selectTextColor")
    public void setSelectTextColor(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setSelectTextColor(i);
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setUnSelectTextSize(DisplayUtils.c(miotStringPicker.getContext(), (float) i));
    }

    @ReactProp(name = "selectFontSize")
    public void setSelectFontSize(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setSelectTextSize(DisplayUtils.c(miotStringPicker.getContext(), (float) i));
    }

    @ReactProp(customType = "Color", name = "selectBgColor")
    public void setSelectBgColor(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setSelectItemBgColor(i);
    }

    @ReactProp(name = "unit")
    public void setUnit(MiotStringPicker miotStringPicker, @Nullable String str) {
        miotStringPicker.setUnit(str);
    }

    @ReactProp(name = "unitFontSize")
    public void setUnitFontSize(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setUnitFontSize(DisplayUtils.c(miotStringPicker.getContext(), (float) i));
    }

    @ReactProp(customType = "Color", name = "unitTextColor")
    public void setUnitTextColor(MiotStringPicker miotStringPicker, @Nullable int i) {
        miotStringPicker.setUnitTextColor(i);
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onValueChanged", MapBuilder.of("registrationName", "onValueChanged")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view instanceof MiotStringPicker) {
            RnPluginLog.a("MHStringPicker addEventEmitters...");
            MiotStringPicker miotStringPicker = (MiotStringPicker) view;
            miotStringPicker.setOnValueChangedListener(miotStringPicker.e);
        }
        super.addEventEmitters(themedReactContext, view);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(View view) {
        RnPluginLog.a("MHStringPicker  onAfterUpdateTransaction...");
        if (view != null && (view instanceof MiotStringPicker)) {
            ((MiotStringPicker) view).a();
        }
        super.onAfterUpdateTransaction(view);
    }

    private class MiotStringPicker extends StringPicker {
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String[] c = null;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public StringPicker.OnValueChangeListener e = new StringPicker.OnValueChangeListener() {
            public void a(StringPicker stringPicker, int i, int i2) {
                RnPluginLog.a("MHStringPicker  onValueChange...   newIndex=" + i2);
            }

            public void b(StringPicker stringPicker, int i, int i2) {
                MiotStringPicker miotStringPicker = (MiotStringPicker) stringPicker;
                String a2 = miotStringPicker.a(i);
                String a3 = miotStringPicker.a(i2);
                RnPluginLog.a("MHStringPicker  onLastValueChange...  oldValue=" + a2 + "  newIndex=" + i2 + "  newValue=" + a3);
                WritableMap createMap = Arguments.createMap();
                createMap.putString("oldValue", a2);
                createMap.putString(BaseService.NEW_VALUE, a3);
                ((RCTEventEmitter) ((ReactContext) miotStringPicker.getContext()).getJSModule(RCTEventEmitter.class)).receiveEvent(miotStringPicker.getId(), "onValueChanged", createMap);
            }
        };

        /* access modifiers changed from: private */
        public String a(int i) {
            if (i < 0) {
                RnPluginLog.b("StringPickerNanager   index is smaller than 0");
                return "";
            } else if (this.c != null && this.c.length > 0 && this.c.length > i) {
                return this.c[i];
            } else {
                RnPluginLog.b("StringPickerNanager  dataSource is null");
                return "";
            }
        }

        /* access modifiers changed from: private */
        public void a() {
            if (this.c != null && this.c.length != 0) {
                b();
                a(this.d);
                updateValues(0, this.c.length - 1, 0);
                setDisplayedValues(this.c);
                setMinValue(0);
                setMaxValue(this.c.length - 1);
                int i = 0;
                while (true) {
                    if (i >= this.c.length) {
                        i = 0;
                        break;
                    } else if (this.b.equalsIgnoreCase(this.c[i])) {
                        break;
                    } else {
                        i++;
                    }
                }
                setValue(i);
                c();
                setWrapSelectorWheel(true);
            }
        }

        private void b() {
            this.mWrapSelectorWheel = false;
        }

        private void c() {
            String str = "";
            for (int i = 0; i < this.c.length; i++) {
                if (this.c[i].length() > str.length()) {
                    str = this.c[i];
                }
            }
            this.mUnitPos = measureLabelSize(str);
        }

        /* access modifiers changed from: package-private */
        public void a(String str) {
            int i;
            if (!TextUtils.isEmpty(str)) {
                try {
                    i = Integer.parseInt(str);
                } catch (Exception e2) {
                    RnPluginLog.b("MHStringPicker  " + e2.toString());
                    i = Color.c;
                }
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(i);
                paint.setStrokeWidth(0.5f);
                this.mLinePaint = paint;
            }
        }

        public MiotStringPicker(Context context) {
            super(context);
        }
    }
}
