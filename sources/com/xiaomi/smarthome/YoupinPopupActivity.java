package com.xiaomi.smarthome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.YoupinPopupTypeTwoActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.youpin.XmpluginHostApiImp;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.youpin_network.util.YouPinParamsUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

public class YoupinPopupActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13531a = "https://home.mi.com/shop/promotion";

    public static boolean shouldCheckYoupinShowReq() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youpin_popup);
        overridePendingTransition(0, 0);
        StatHelper.aD();
        findViewById(R.id.experience).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b("yp_popup_has_shown", true);
                UrlDispatchManger.a().c(YoupinPopupActivity.f13531a);
                StatHelper.aE();
                YoupinPopupActivity.this.a();
            }
        });
        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b("yp_popup_has_shown", true);
                StatHelper.aG();
                YoupinPopupActivity.this.a();
            }
        });
        findViewById(R.id.background_bg).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b("yp_popup_has_shown", true);
                UrlDispatchManger.a().c(YoupinPopupActivity.f13531a);
                StatHelper.aE();
                YoupinPopupActivity.this.a();
            }
        });
    }

    private class YPCloseImageViewTouchListener implements View.OnTouchListener {
        private static final long d = 1000;
        private long b = 0;
        private boolean c = true;
        private int e;
        private float f = 0.74f;
        private float g = 0.83f;
        private int h;
        private int i;
        private int j;

        private YPCloseImageViewTouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                a();
            } else if (motionEvent.getAction() == 2) {
                a(motionEvent);
            } else if (motionEvent.getAction() == 1) {
                b();
            }
            return true;
        }

        private void a() {
            this.b = System.currentTimeMillis();
            this.e = YoupinPopupActivity.this.findViewById(R.id.background_bg).getWidth();
            this.h = (int) (((float) this.e) * this.f);
            this.i = (int) (((float) this.e) * this.g);
            this.j = (int) (((float) this.e) * (this.g - this.f));
            this.c = true;
        }

        private void a(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (x < ((float) this.h) || x > ((float) this.i) || y > ((float) this.j)) {
                this.c = false;
            }
        }

        private void b() {
            if (this.c && System.currentTimeMillis() - this.b <= 1000) {
                StatHelper.aG();
                YoupinPopupActivity.this.a();
            }
        }
    }

    public void onBackPressed() {
        PreferenceUtils.b("yp_popup_has_shown", true);
        StatHelper.aI();
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        finish();
        overridePendingTransition(0, 0);
    }

    public static boolean copyApkFromAssets(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str);
            File file = new File(str2);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    open.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void checkAndShowIfNeeded(DeviceMainPage deviceMainPage) {
        final WeakReference weakReference = new WeakReference(deviceMainPage);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", CoreApi.a().s());
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("model", "Notice");
        jsonObject2.addProperty("action", "MijiaDeviceMask");
        jsonObject2.add("parameters", jsonObject);
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.add("result", jsonObject2);
        XmPluginHostApi instance = XmpluginHostApiImp.instance();
        if (instance != null) {
            instance.sendMijiaShopRequest(YouPinParamsUtil.f23868a, jsonObject3, new Callback<Object>() {
                public void onCache(Object obj) {
                }

                public void onSuccess(Object obj, boolean z) {
                    JsonArray asJsonArray;
                    if (obj != null) {
                        try {
                            if (obj instanceof JsonObject) {
                                JsonObject jsonObject = (JsonObject) obj;
                                if (!jsonObject.has("result")) {
                                    return;
                                }
                                if (jsonObject.get("result").isJsonObject()) {
                                    JsonObject asJsonObject = jsonObject.getAsJsonObject("result");
                                    if (!asJsonObject.has("data")) {
                                        return;
                                    }
                                    if (asJsonObject.get("data").isJsonArray()) {
                                        JsonArray asJsonArray2 = asJsonObject.getAsJsonArray("data");
                                        if (asJsonArray2 == null) {
                                            return;
                                        }
                                        if (asJsonArray2.size() != 0) {
                                            JsonObject asJsonObject2 = asJsonArray2.get(0).getAsJsonObject();
                                            if (asJsonObject2 == null) {
                                                return;
                                            }
                                            if (asJsonObject2.has("show_type")) {
                                                LogUtil.a("YoupinPopupTypeTwoActivity", "onsuccess" + asJsonObject2.toString());
                                                if (asJsonObject2.getAsJsonPrimitive("show_type").getAsInt() == 1) {
                                                    if (YoupinPopupActivity.shouldCheckYoupinShowReq()) {
                                                        Intent intent = new Intent(SHApplication.getAppContext(), YoupinPopupActivity.class);
                                                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                                                        SHApplication.getAppContext().startActivity(intent);
                                                    }
                                                } else if (asJsonObject2.getAsJsonPrimitive("show_type").getAsInt() == 2) {
                                                    YoupinPopupTypeTwoActivity.PopupInfo popupInfo = new YoupinPopupTypeTwoActivity.PopupInfo();
                                                    JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("mask");
                                                    if (asJsonObject3 != null && (asJsonArray = asJsonObject3.getAsJsonArray("mask")) != null) {
                                                        if (asJsonArray.size() > 0) {
                                                            popupInfo.a(asJsonArray.get(0).getAsJsonObject());
                                                            popupInfo.g = asJsonObject2.getAsJsonPrimitive("etag").getAsString();
                                                            LogUtil.a("YoupinPopupTypeTwoActivity", popupInfo.toString());
                                                            if (!YoupinPopupTypeTwoActivity.checkCanShow(popupInfo)) {
                                                                LogUtil.a("YoupinPopupTypeTwoActivity", YoupinPopupTypeTwoActivity.checkCanShow(popupInfo) + "");
                                                                return;
                                                            }
                                                            DeviceMainPage deviceMainPage = (DeviceMainPage) weakReference.get();
                                                            if (deviceMainPage != null && deviceMainPage.isValid()) {
                                                                if (!deviceMainPage.k) {
                                                                    Intent intent2 = new Intent(SHApplication.getAppContext(), YoupinPopupTypeTwoActivity.class);
                                                                    intent2.putExtra("info", popupInfo);
                                                                    intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                                                                    SHApplication.getAppContext().startActivity(intent2);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    LogUtil.a("YoupinPopupActivity", "error=" + i + ",errorinfo=" + str);
                }
            }, new Parser<Object>() {
                public Object parse(JsonElement jsonElement) {
                    return jsonElement;
                }
            }, false);
        }
    }
}
