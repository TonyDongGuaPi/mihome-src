package com.mijia.model.property;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.mijia.debug.SDKLog;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PropertyManger {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f8068a = "PropertyManger";
    private static final int c = 1;
    private static final int d = 4;
    private static final int e = 2;
    private static final int f = 180000;
    private Handler b = null;
    /* access modifiers changed from: private */
    public List<OnPropertyChangeListener> g = new ArrayList();
    /* access modifiers changed from: private */
    public CameraDevice h;

    public interface OnPropertyChangeListener {
        void onPropertyChanged(CameraDevice cameraDevice, HashSet<String> hashSet);
    }

    public PropertyManger(CameraDevice cameraDevice) {
        this.h = cameraDevice;
        HandlerThread handlerThread = new HandlerThread("subscribe_thread");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper()) {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 4) {
                    switch (i) {
                        case 1:
                            PropertyManger.this.a();
                            return;
                        case 2:
                            Intent intent = (Intent) message.obj;
                            if (intent != null) {
                                String stringExtra = intent.getStringExtra("data");
                                SDKLog.b(PropertyManger.f8068a, stringExtra);
                                CameraPropertyBase f = PropertyManger.this.h.f();
                                try {
                                    JSONArray jSONArray = new JSONArray(stringExtra);
                                    if (jSONArray.length() != 0) {
                                        HashSet hashSet = new HashSet();
                                        int length = jSONArray.length();
                                        for (int i2 = 0; i2 < length; i2++) {
                                            JSONObject jSONObject = jSONArray.getJSONObject(i2);
                                            String string = jSONObject.getString("key");
                                            if (!TextUtils.isEmpty(string)) {
                                                if (string.startsWith("prop.")) {
                                                    string = string.substring("prop.".length());
                                                }
                                                Object obj = jSONObject.get("value");
                                                if (obj instanceof JSONArray) {
                                                    obj = ((JSONArray) obj).getString(0);
                                                }
                                                f.b(string, obj);
                                                hashSet.add(string);
                                            }
                                        }
                                        if (PropertyManger.this.g != null && !PropertyManger.this.g.isEmpty()) {
                                            for (int i3 = 0; i3 < PropertyManger.this.g.size(); i3++) {
                                                OnPropertyChangeListener onPropertyChangeListener = (OnPropertyChangeListener) PropertyManger.this.g.get(i3);
                                                if (onPropertyChangeListener != null) {
                                                    onPropertyChangeListener.onPropertyChanged(PropertyManger.this.h, hashSet);
                                                }
                                            }
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            } else {
                                return;
                            }
                        default:
                            return;
                    }
                } else {
                    PropertyManger.this.b();
                }
            }
        };
    }

    public void a(OnPropertyChangeListener onPropertyChangeListener) {
        if (onPropertyChangeListener != null) {
            if (this.g == null) {
                this.g = new ArrayList();
            }
            if (!this.g.contains(onPropertyChangeListener)) {
                this.g.add(onPropertyChangeListener);
            }
        }
    }

    public void b(OnPropertyChangeListener onPropertyChangeListener) {
        if (onPropertyChangeListener != null && this.g != null) {
            this.g.remove(onPropertyChangeListener);
        }
    }

    public void a(String str) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        if (this.g != null) {
            for (int i = 0; i < this.g.size(); i++) {
                OnPropertyChangeListener onPropertyChangeListener = this.g.get(i);
                if (onPropertyChangeListener != null) {
                    onPropertyChangeListener.onPropertyChanged(this.h, hashSet);
                }
            }
        }
    }

    public void a() {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance != null && this.h != null && this.h.getDid() != null) {
            this.b.sendEmptyMessageDelayed(1, 180000);
            instance.subscribeDevice(this.h.getDid(), 0, this.h.f().a(), 3, (Callback<Void>) null);
        }
    }

    public void b() {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        if (instance != null && this.h != null && this.h.getDid() != null) {
            this.b.sendEmptyMessageDelayed(4, 180000);
            instance.subscribeDevice(this.h.getDid(), 0, this.h.f().b(), 3, (Callback<Void>) null);
        }
    }

    public void a(Intent intent) {
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = intent;
        this.b.removeMessages(2);
        this.b.sendMessage(obtainMessage);
    }
}
