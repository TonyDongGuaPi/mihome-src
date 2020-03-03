package com.mijia.model.sdcard;

import android.text.TextUtils;
import android.util.Log;
import com.mijia.app.AppConfig;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.BaseFileLoadManager;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.camera.P2pResponse;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SdcardManager extends BaseFileLoadManager {
    private static final int k = 6;
    public SDCardInfo i;
    /* access modifiers changed from: private */
    public volatile List<TimeItem> j = new ArrayList();

    public String f() {
        return "com.mijia.camera.SdcardManager";
    }

    public SdcardManager(CameraDevice cameraDevice) {
        super(cameraDevice);
    }

    public void a(int i2) {
        this.h.removeMessages(1);
        this.h.removeMessages(2);
        this.f = i2;
        this.e = false;
        this.h.sendEmptyMessageDelayed(1, 0);
    }

    /* access modifiers changed from: protected */
    public void a(Callback<Void> callback) {
        d(callback);
    }

    public synchronized List<TimeItemDay> g() {
        return TimeItemDay.a(this.j);
    }

    private synchronized TimeItem c(long j2) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            if (this.j.get(size).a(j2)) {
                return this.j.get(size);
            }
        }
        return null;
    }

    public synchronized TimeItem a(long j2) {
        if (j2 == 0) {
            return null;
        }
        if (this.j.size() == 0) {
            return null;
        }
        if (this.j.get(this.j.size() - 1).c <= j2) {
            return null;
        }
        int size = this.j.size() - 1;
        while (size >= 0) {
            if (this.j.get(size).c > j2 || size == this.j.size() - 1) {
                size--;
            } else {
                return this.j.get(size + 1);
            }
        }
        return this.j.get(0);
    }

    public synchronized int b(long j2) {
        TimeItem c = c(j2);
        if (c == null) {
            return 0;
        }
        return (int) (c.f8098a / 1000);
    }

    public synchronized List<TimeItem> a(long j2, long j3) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (j3 == 0) {
            TimeItem c = c(j2);
            if (c != null) {
                arrayList.add(c);
            }
        } else {
            long j4 = j3 + j2;
            for (int size = this.j.size() - 1; size >= 0; size--) {
                if (this.j.get(size).f8098a <= j4 && this.j.get(size).b() >= j2) {
                    arrayList.add(this.j.get(size));
                    Log.d("deleteTime", "get " + this.j.get(size).f8098a);
                }
            }
        }
        return arrayList;
    }

    public synchronized void h() {
        this.j.clear();
        this.h.post(new Runnable() {
            public void run() {
                SdcardManager.this.e();
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void a(List<TimeItem> list) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                TimeItem timeItem = list.get(size);
                int size2 = this.j.size() - 1;
                while (true) {
                    if (size2 < 0) {
                        break;
                    } else if (timeItem.equals(this.j.get(size2))) {
                        Log.d("deleteTime", "delete " + timeItem.f8098a);
                        this.j.remove(size2);
                        break;
                    } else {
                        size2--;
                    }
                }
            }
            this.h.post(new Runnable() {
                public void run() {
                    SdcardManager.this.d();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public synchronized void b(List<TimeItem> list) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                list.get(size).d = 1;
            }
            this.h.post(new Runnable() {
                public void run() {
                    SdcardManager.this.d();
                }
            });
        }
    }

    public synchronized List<TimeItem> i() {
        return this.j;
    }

    public void j() {
        a(-2, "SdcardManager release");
        this.e = false;
        this.h.removeMessages(1);
        this.h.removeMessages(2);
    }

    private void d(final Callback<Void> callback) {
        SDKLog.b(Tag.d, "updateHistoryTime");
        if (this.g.u() == null) {
            a(-1, "SdcardManager updateTime");
            if (callback != null) {
                callback.onFailure(-1, "");
                return;
            }
            return;
        }
        final ArrayList arrayList = new ArrayList();
        this.g.u().sendMsg(6, 6, (byte[]) null, new P2pResponse() {
            public void onResponse(int i, byte[] bArr) {
                SdcardManager.this.a((List<TimeItem>) arrayList, bArr);
                if (i == 0) {
                    SDKLog.e(Tag.d, "update save updateRecordTime onSuccess");
                    Collections.sort(arrayList, new TimeItemComparator());
                    List unused = SdcardManager.this.j = arrayList;
                    SdcardManager.this.e();
                    if (callback != null) {
                        callback.onSuccess(null);
                    }
                }
            }

            public void onError(int i) {
                SDKLog.b(Tag.d, "update save updateRecordTime onResult:" + i);
                SdcardManager sdcardManager = SdcardManager.this;
                sdcardManager.a(i, "SdcardManager onError:" + i);
                if (callback != null) {
                    callback.onFailure(-1, "");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<TimeItem> list, byte[] bArr) {
        if (bArr != null) {
            for (int i2 = 0; i2 < bArr.length; i2 += TimeItem.a()) {
                TimeItem a2 = TimeItem.a(bArr, i2, AppConfig.b());
                if (a2.f8098a != 0 && a2.f8098a <= a2.c) {
                    list.add(a2);
                }
            }
        }
    }

    public void b(final Callback<SDCardInfo> callback, boolean z) {
        if (this.i != null && !z) {
            callback.onSuccess(this.i);
        }
        this.g.callMethod("sd_storge", new JSONArray(), new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!TextUtils.isEmpty(jSONObject.optString("error"))) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("error");
                        callback.onFailure(jSONObject2.optInt("code"), jSONObject2.optString("message"));
                        return;
                    }
                    JSONArray jSONArray = jSONObject.getJSONArray("result");
                    long j = 0;
                    long j2 = !jSONArray.isNull(0) ? jSONArray.getLong(0) : 0;
                    long j3 = !jSONArray.isNull(1) ? jSONArray.getLong(1) : 0;
                    if (!jSONArray.isNull(2)) {
                        j = jSONArray.getLong(2);
                    }
                    SDCardInfo sDCardInfo = new SDCardInfo(j2, j3, j, !jSONArray.isNull(3) ? jSONArray.getInt(3) : -1);
                    SdcardManager.this.i = sDCardInfo;
                    callback.onSuccess(sDCardInfo);
                } catch (JSONException e) {
                    callback.onFailure(-1, e.toString());
                }
            }

            public void onFailure(int i, String str) {
                if (i == -2003) {
                    SdcardManager.this.i = null;
                }
                callback.onFailure(i, str);
            }
        }, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return str;
            }
        });
    }

    public void a(final List<TimeItem> list, final Callback<JSONObject> callback) {
        if (list != null && list.size() != 0) {
            final ArrayList arrayList = new ArrayList();
            new Thread(new Runnable() {
                public void run() {
                    int i;
                    int i2;
                    int size = list.size();
                    if (size < 50) {
                        i = size;
                        i2 = 0;
                    } else {
                        i2 = 0;
                        i = 50;
                    }
                    while (i2 < size) {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        final List subList = list.subList(i2, i);
                        JSONArray jSONArray = new JSONArray();
                        for (int i3 = 0; i3 < subList.size(); i3++) {
                            jSONArray.put((int) (((TimeItem) subList.get(i3)).f8098a / 1000));
                        }
                        String str = "deleteVideo";
                        if (((TimeItem) subList.get(0)).d != 0) {
                            str = "deleteSaveVideo";
                        }
                        SdcardManager.this.g.callMethod(str, jSONArray, new Callback<JSONObject>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                SdcardManager.this.a((List<TimeItem>) subList);
                                SdcardManager.this.g.h().a((List<TimeItem>) subList);
                                arrayList.add(true);
                                countDownLatch.countDown();
                            }

                            public void onFailure(int i, String str) {
                                arrayList.add(false);
                                countDownLatch.countDown();
                                SDKLog.e(Tag.d, "delete video fail " + str);
                            }
                        }, Parser.DEFAULT_PARSER);
                        try {
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        int i4 = i + 50;
                        if (i4 >= size) {
                            i4 = size;
                        }
                        if (arrayList.isEmpty() || ((Boolean) arrayList.remove(0)).booleanValue()) {
                            int i5 = i;
                            i = i4;
                            i2 = i5;
                        } else if (callback != null) {
                            callback.onFailure(-1, "");
                            return;
                        } else {
                            return;
                        }
                    }
                    if (callback != null) {
                        callback.onSuccess(new JSONObject());
                    }
                }
            }).start();
        } else if (callback != null) {
            callback.onFailure(-1, "");
        }
    }

    public void b(Callback<Object> callback) {
        this.g.a("sd_format", (Object) null, callback);
    }

    public void c(final Callback<Object> callback) {
        this.g.callMethod("sd_umount", new JSONArray(), new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                String str;
                int i;
                if (jSONObject == null) {
                    callback.onSuccess(null);
                } else if (TextUtils.isEmpty(jSONObject.optString("error"))) {
                    callback.onSuccess(jSONObject);
                } else {
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("error");
                        i = jSONObject2.optInt("code");
                        try {
                            str = jSONObject2.optString("message");
                        } catch (JSONException e) {
                            e = e;
                            e.printStackTrace();
                            str = "";
                            callback.onFailure(i, str);
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        i = -1;
                        e.printStackTrace();
                        str = "";
                        callback.onFailure(i, str);
                    }
                    callback.onFailure(i, str);
                }
            }

            public void onFailure(int i, String str) {
                callback.onFailure(i, str);
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void b(final List<TimeItem> list, final Callback<JSONObject> callback) {
        if (list != null && list.size() != 0) {
            final ArrayList arrayList = new ArrayList();
            new Thread(new Runnable() {
                public void run() {
                    int i;
                    int i2;
                    int size = list.size();
                    if (size < 50) {
                        i = size;
                        i2 = 0;
                    } else {
                        i2 = 0;
                        i = 50;
                    }
                    while (i2 < size) {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        final List subList = list.subList(i2, i);
                        JSONArray jSONArray = new JSONArray();
                        for (int i3 = 0; i3 < subList.size(); i3++) {
                            if (((TimeItem) subList.get(i3)).d == 0) {
                                jSONArray.put((int) (((TimeItem) subList.get(i3)).f8098a / 1000));
                            }
                        }
                        if (jSONArray.length() != 0) {
                            SdcardManager.this.g.callMethod("saveVideo", jSONArray, new Callback<JSONObject>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    SdcardManager.this.b((List<TimeItem>) subList);
                                    arrayList.add(true);
                                    countDownLatch.countDown();
                                }

                                public void onFailure(int i, String str) {
                                    arrayList.add(false);
                                    countDownLatch.countDown();
                                    SDKLog.e(Tag.d, "save video fail " + str);
                                }
                            }, Parser.DEFAULT_PARSER);
                            try {
                                countDownLatch.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            int i4 = i + 50;
                            if (i4 >= size) {
                                i4 = size;
                            }
                            if (arrayList.isEmpty() || ((Boolean) arrayList.remove(0)).booleanValue()) {
                                int i5 = i;
                                i = i4;
                                i2 = i5;
                            } else if (callback != null) {
                                callback.onFailure(-1, "");
                                return;
                            } else {
                                return;
                            }
                        } else if (callback != null) {
                            callback.onFailure(-1, "");
                            return;
                        } else {
                            return;
                        }
                    }
                    if (callback != null) {
                        callback.onSuccess(new JSONObject());
                    }
                }
            }).start();
        } else if (callback != null) {
            callback.onFailure(-1, "");
        }
    }
}
