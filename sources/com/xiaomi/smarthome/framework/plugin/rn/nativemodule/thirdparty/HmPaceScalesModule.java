package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty;

import com.chipsea.healthscale.CsAlgoBuilderEx;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.holtek.libHTBodyfat.HTPeopleGeneral;

public class HmPaceScalesModule extends ReactContextBaseJavaModule {
    private static final String TAG = "HmPaceScalesModule";

    public String getName() {
        return TAG;
    }

    public HmPaceScalesModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void computeBodyParam(double d, double d2, int i, int i2, int i3, Callback callback) {
        Callback callback2 = callback;
        HTPeopleGeneral hTPeopleGeneral = new HTPeopleGeneral(d, d2, i, i2, i3);
        int a2 = hTPeopleGeneral.a();
        try {
            CsAlgoBuilderEx csAlgoBuilderEx = new CsAlgoBuilderEx();
            csAlgoBuilderEx.a((int) d2, (byte) i, i2, (float) d, (float) i3);
            csAlgoBuilderEx.a((float) hTPeopleGeneral.l, (float) hTPeopleGeneral.n, (float) hTPeopleGeneral.k, (float) hTPeopleGeneral.m, (float) hTPeopleGeneral.i, (float) hTPeopleGeneral.j);
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("code", a2);
            createMap.putDouble("resistance", hTPeopleGeneral.e);
            createMap.putDouble("BMI", hTPeopleGeneral.h);
            createMap.putInt("basic_metabolism", hTPeopleGeneral.i);
            createMap.putInt("visceral_fat", hTPeopleGeneral.j);
            createMap.putDouble("bone_weight", hTPeopleGeneral.k);
            createMap.putDouble("fat_percentage", hTPeopleGeneral.l);
            createMap.putDouble("water_percentage", hTPeopleGeneral.m);
            createMap.putDouble("muscle_weight", hTPeopleGeneral.n);
            createMap.putInt("body_age", csAlgoBuilderEx.c());
            createMap.putDouble("protein_percentage", (double) csAlgoBuilderEx.a());
            createMap.putDouble("standard_body_weight", (double) csAlgoBuilderEx.b());
            callback2.invoke(true, createMap);
        } catch (Exception e) {
            callback2.invoke(false, e.toString());
        }
    }
}
