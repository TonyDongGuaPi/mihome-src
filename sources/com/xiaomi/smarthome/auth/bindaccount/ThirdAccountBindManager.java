package com.xiaomi.smarthome.auth.bindaccount;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.bindaccount.model.ThirdAccount;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdAccountBindManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13881a = "third_cloud_device_icon";
    public static final String b = "third_cloud_device_group_id";
    public static final String c = "account_id";
    public static final String d = "filter_sync_dev";
    public static final String e = "sync_dev_groupid";
    public static final String f = "sync_dev_code";
    private static final String g = "ThirdAccountBindManager";
    /* access modifiers changed from: private */
    public static volatile ThirdAccountBindManager h = null;
    private static final String l = "third_cloud_account_sp_";
    private static final String m = "sp_content";
    /* access modifiers changed from: private */
    public volatile List<ThirdAccount> i = new ArrayList();
    /* access modifiers changed from: private */
    public volatile boolean j = false;
    /* access modifiers changed from: private */
    public List<AsyncCallback> k = new ArrayList();

    private void h() {
    }

    public static ThirdAccountBindManager a() {
        if (h == null) {
            synchronized (ThirdAccountBindManager.class) {
                if (h == null) {
                    h = new ThirdAccountBindManager();
                }
            }
        }
        return h;
    }

    public List<ThirdAccount> b() {
        return this.i;
    }

    public List<ThirdAccount> c() {
        if (this.i == null || this.i.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            ThirdAccount thirdAccount = this.i.get(i2);
            if (!(thirdAccount == null || thirdAccount.d() == -1)) {
                arrayList.add(thirdAccount);
            }
        }
        return arrayList;
    }

    public ThirdAccount a(String str) {
        List<ThirdAccount> list = this.i;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ThirdAccount thirdAccount = list.get(i2);
            if (thirdAccount != null && TextUtils.equals(thirdAccount.b(), str)) {
                return thirdAccount;
            }
        }
        return null;
    }

    public Pair<Integer, ThirdAccount> b(String str) {
        List<ThirdAccount> list = this.i;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ThirdAccount thirdAccount = list.get(i2);
            if (thirdAccount != null && TextUtils.equals(thirdAccount.b(), str)) {
                return new Pair<>(Integer.valueOf(i2), thirdAccount);
            }
        }
        return null;
    }

    private void i() {
        e();
        b((AsyncCallback<Void, Error>) null);
    }

    public void a(AsyncCallback<Void, Error> asyncCallback) {
        e();
        b(asyncCallback);
    }

    public List<Device> d() {
        List<Device> f2;
        ArrayList arrayList = new ArrayList();
        List<ThirdAccount> list = this.i;
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ThirdAccount thirdAccount = list.get(i2);
            if (!(thirdAccount == null || thirdAccount.f() == null || (f2 = thirdAccount.f()) == null || f2.isEmpty())) {
                arrayList.addAll(f2);
            }
        }
        return arrayList;
    }

    public Device c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Device next : d()) {
            if (next.did != null && next.did.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public void b(AsyncCallback<Void, Error> asyncCallback) {
        if (h != null) {
            if (asyncCallback != null) {
                this.k.add(asyncCallback);
            }
            if (!this.j) {
                this.j = true;
                c((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (ThirdAccountBindManager.h != null) {
                            ArrayList arrayList = new ArrayList();
                            List a2 = ThirdAccountBindManager.this.i;
                            for (int i = 0; i < a2.size(); i++) {
                                ThirdAccount thirdAccount = (ThirdAccount) a2.get(i);
                                if (thirdAccount != null) {
                                    arrayList.add(thirdAccount.b());
                                }
                            }
                            ThirdAccountBindManager.this.a((List<String>) arrayList, (AsyncCallback<Map<String, List<Device>>, Error>) new AsyncCallback<Map<String, List<Device>>, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Map<String, List<Device>> map) {
                                    List list;
                                    if (ThirdAccountBindManager.h != null) {
                                        List a2 = ThirdAccountBindManager.this.i;
                                        for (int i = 0; i < a2.size(); i++) {
                                            ThirdAccount thirdAccount = (ThirdAccount) a2.get(i);
                                            if (!(thirdAccount == null || TextUtils.isEmpty(thirdAccount.b()) || (list = map.get(thirdAccount.b())) == null)) {
                                                thirdAccount.a((List<Device>) list);
                                            }
                                        }
                                        boolean unused = ThirdAccountBindManager.this.j = false;
                                        for (int i2 = 0; i2 < ThirdAccountBindManager.this.k.size(); i2++) {
                                            AsyncCallback asyncCallback = (AsyncCallback) ThirdAccountBindManager.this.k.get(i2);
                                            if (asyncCallback != null) {
                                                asyncCallback.onSuccess(null);
                                            }
                                        }
                                        ThirdAccountBindManager.this.k.clear();
                                        ThirdAccountBindManager.this.j();
                                    }
                                }

                                public void onFailure(Error error) {
                                    if (ThirdAccountBindManager.h != null) {
                                        boolean unused = ThirdAccountBindManager.this.j = false;
                                        for (int i = 0; i < ThirdAccountBindManager.this.k.size(); i++) {
                                            AsyncCallback asyncCallback = (AsyncCallback) ThirdAccountBindManager.this.k.get(i);
                                            if (asyncCallback != null) {
                                                asyncCallback.onFailure(error);
                                            }
                                        }
                                        ThirdAccountBindManager.this.k.clear();
                                    }
                                }
                            });
                        }
                    }

                    public void onFailure(Error error) {
                        if (ThirdAccountBindManager.h != null) {
                            boolean unused = ThirdAccountBindManager.this.j = false;
                            for (int i = 0; i < ThirdAccountBindManager.this.k.size(); i++) {
                                AsyncCallback asyncCallback = (AsyncCallback) ThirdAccountBindManager.this.k.get(i);
                                if (asyncCallback != null) {
                                    asyncCallback.onFailure(error);
                                }
                            }
                            ThirdAccountBindManager.this.k.clear();
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        AppStateNotifier stateNotifier;
        List<ThirdAccount> list = this.i;
        if (list != null && (stateNotifier = SHApplication.getStateNotifier()) != null && stateNotifier.a() == 4) {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < list.size(); i2++) {
                ThirdAccount thirdAccount = list.get(i2);
                if (thirdAccount != null) {
                    jSONArray.put(thirdAccount.g());
                }
            }
            Context appContext = SHApplication.getAppContext();
            appContext.getSharedPreferences(l + MD5Util.a(CoreApi.a().s()), 0).edit().putString(m, jSONArray.toString()).apply();
        }
    }

    public void e() {
        AppStateNotifier stateNotifier = SHApplication.getStateNotifier();
        if (stateNotifier != null && stateNotifier.a() == 4) {
            Context appContext = SHApplication.getAppContext();
            String string = appContext.getSharedPreferences(l + MD5Util.a(CoreApi.a().s()), 0).getString(m, "");
            if (!TextUtils.isEmpty(string)) {
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                        if (optJSONObject != null) {
                            arrayList.add(ThirdAccount.a(optJSONObject));
                        }
                    }
                    this.i = arrayList;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void a(final List<String> list, final AsyncCallback<Map<String, List<Device>>, Error> asyncCallback) {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                RemoteFamilyApi.a().b((List<String>) list, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (ThirdAccountBindManager.h != null && jSONObject != null) {
                            try {
                                if (!jSONObject.isNull("list")) {
                                    Object obj = jSONObject.get("list");
                                    if (obj == null) {
                                        return;
                                    }
                                    if (obj instanceof JSONArray) {
                                        JSONArray jSONArray = (JSONArray) obj;
                                        HashMap hashMap = new HashMap();
                                        if (jSONArray.length() != 0) {
                                            for (int i = 0; i < jSONArray.length(); i++) {
                                                Object obj2 = jSONArray.get(i);
                                                if (obj2 != null) {
                                                    if (obj2 instanceof JSONObject) {
                                                        if (!((JSONObject) obj2).isNull("dev_list")) {
                                                            JSONArray optJSONArray = ((JSONObject) obj2).optJSONArray("dev_list");
                                                            if (optJSONArray.length() != 0) {
                                                                String optString = ((JSONObject) obj2).optString(FirebaseAnalytics.Param.GROUP_ID);
                                                                ArrayList arrayList = new ArrayList();
                                                                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                                                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                                                                    Device device = new Device();
                                                                    device.did = optJSONObject.optString("did");
                                                                    device.name = optJSONObject.optString("name");
                                                                    device.model = optJSONObject.optString("model");
                                                                    if (device.propInfo == null) {
                                                                        device.propInfo = new JSONObject();
                                                                    }
                                                                    device.propInfo.put(ThirdAccountBindManager.f13881a, optJSONObject.opt("icon_url"));
                                                                    device.propInfo.put(ThirdAccountBindManager.b, optString);
                                                                    arrayList.add(device);
                                                                }
                                                                ThirdAccount a2 = ThirdAccountBindManager.this.a(optString);
                                                                if (a2 != null) {
                                                                    a2.a((List<Device>) arrayList);
                                                                    if (!((JSONObject) obj2).isNull("bind_status")) {
                                                                        a2.a(((JSONObject) obj2).optInt("bind_status", -1));
                                                                    }
                                                                    ThirdAccountBindManager.this.j();
                                                                }
                                                                hashMap.put(optString, arrayList);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (asyncCallback != null) {
                                            asyncCallback.onSuccess(hashMap);
                                        }
                                    }
                                } else if (asyncCallback != null) {
                                    asyncCallback.onFailure(new Error(Integer.MIN_VALUE, jSONObject.toString()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (asyncCallback != null) {
                                    asyncCallback.onFailure(new Error(Integer.MIN_VALUE, e.getMessage()));
                                }
                            }
                        }
                    }

                    public void onFailure(Error error) {
                        if (asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
            }
        });
    }

    public void c(final AsyncCallback<Void, Error> asyncCallback) {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                RemoteFamilyApi.a().b((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (ThirdAccountBindManager.h != null && jSONObject != null) {
                            try {
                                if (!jSONObject.isNull("group_list")) {
                                    Object obj = jSONObject.get("group_list");
                                    if (obj == null) {
                                        return;
                                    }
                                    if (obj instanceof JSONArray) {
                                        JSONArray jSONArray = (JSONArray) obj;
                                        final ArrayList arrayList = new ArrayList();
                                        ArrayList arrayList2 = new ArrayList();
                                        if (jSONArray.length() != 0) {
                                            for (int i = 0; i < jSONArray.length(); i++) {
                                                Object obj2 = jSONArray.get(i);
                                                if (obj2 != null) {
                                                    if (obj2 instanceof JSONObject) {
                                                        ThirdAccount a2 = ThirdAccount.a((JSONObject) obj2);
                                                        arrayList.add(a2);
                                                        arrayList2.add(a2.b());
                                                    }
                                                }
                                            }
                                        }
                                        ThirdAccountBindManager.this.a((List<String>) arrayList2, (AsyncCallback<Map<String, List<Device>>, Error>) new AsyncCallback<Map<String, List<Device>>, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(Map<String, List<Device>> map) {
                                                if (ThirdAccountBindManager.h != null) {
                                                    if (map == null) {
                                                        List unused = ThirdAccountBindManager.this.i = arrayList;
                                                        if (asyncCallback != null) {
                                                            asyncCallback.onSuccess(null);
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    for (int i = 0; i < arrayList.size(); i++) {
                                                        ThirdAccount thirdAccount = (ThirdAccount) arrayList.get(i);
                                                        if (thirdAccount != null) {
                                                            thirdAccount.a(map.get(thirdAccount.b()));
                                                        }
                                                    }
                                                    List unused2 = ThirdAccountBindManager.this.i = arrayList;
                                                    if (asyncCallback != null) {
                                                        asyncCallback.onSuccess(null);
                                                    }
                                                }
                                            }

                                            public void onFailure(Error error) {
                                                if (ThirdAccountBindManager.h != null && asyncCallback != null) {
                                                    asyncCallback.onFailure(error);
                                                }
                                            }
                                        });
                                    }
                                } else if (asyncCallback != null) {
                                    asyncCallback.onFailure(new Error(Integer.MIN_VALUE, jSONObject.toString()));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                if (asyncCallback != null) {
                                    asyncCallback.onFailure(new Error(Integer.MIN_VALUE, e.getMessage()));
                                }
                            }
                        }
                    }

                    public void onFailure(Error error) {
                        if (ThirdAccountBindManager.h != null && asyncCallback != null) {
                            asyncCallback.onFailure(error);
                        }
                    }
                });
            }
        });
    }

    private static class SyncManager {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static Map<String, String> f13890a = new ConcurrentHashMap();
        /* access modifiers changed from: private */
        public static Object b = new Object();
        /* access modifiers changed from: private */
        public static final int c = ((int) ((Math.random() * 1.0E7d) + 1.0E7d));
        /* access modifiers changed from: private */
        public static Handler d = new Handler(SHApplication.getGlobalWorkerHandler().getLooper()) {
            public void handleMessage(Message message) {
                if (message.obj != null && (message.obj instanceof String)) {
                    String str = (String) message.obj;
                    synchronized (SyncManager.b) {
                        SyncManager.f(str);
                        SyncManager.f13890a.remove(str);
                    }
                }
            }
        };

        private SyncManager() {
        }

        public static void a() {
            for (String hashCode : new ArrayList(f13890a.keySet())) {
                d.removeMessages(c + hashCode.hashCode());
            }
            f13890a.clear();
        }

        public static boolean a(final String str) {
            synchronized (b) {
                if (f13890a.containsKey(str)) {
                    return false;
                }
                f13890a.put(str, str);
                d.sendMessageDelayed(d.obtainMessage(c + str.hashCode(), str), 30000);
                RemoteFamilyApi.a().d(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (ThirdAccountBindManager.h != null) {
                            SyncManager.d.removeMessages(SyncManager.c + str.hashCode());
                            SyncManager.e(str);
                            synchronized (SyncManager.b) {
                                SyncManager.f13890a.remove(str);
                            }
                        }
                    }

                    public void onFailure(Error error) {
                        String str;
                        if (ThirdAccountBindManager.h != null) {
                            if (("sync_dev failed:" + error) == null) {
                                str = null;
                            } else {
                                str = error.a() + "," + error.b();
                            }
                            LogUtil.a(ThirdAccountBindManager.g, str);
                            SyncManager.d.removeMessages(SyncManager.c + str.hashCode());
                            SyncManager.b(str, error);
                            synchronized (SyncManager.b) {
                                SyncManager.f13890a.remove(str);
                            }
                        }
                    }
                });
                return true;
            }
        }

        public static boolean b(String str) {
            return f13890a.containsKey(str);
        }

        /* access modifiers changed from: private */
        public static void e(String str) {
            Intent intent = new Intent(ThirdAccountBindManager.d);
            intent.putExtra(ThirdAccountBindManager.f, 0);
            intent.putExtra(ThirdAccountBindManager.e, str);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        }

        /* access modifiers changed from: private */
        public static void b(String str, Error error) {
            int i;
            Intent intent = new Intent(ThirdAccountBindManager.d);
            if (error == null) {
                i = -2147483647;
            } else {
                i = error.a();
            }
            intent.putExtra(ThirdAccountBindManager.f, i);
            intent.putExtra(ThirdAccountBindManager.e, str);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        }

        /* access modifiers changed from: private */
        public static void f(String str) {
            Intent intent = new Intent(ThirdAccountBindManager.d);
            intent.putExtra(ThirdAccountBindManager.f, Integer.MIN_VALUE);
            intent.putExtra(ThirdAccountBindManager.e, str);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        }
    }

    public boolean d(String str) {
        if (SyncManager.b(str)) {
            return false;
        }
        return SyncManager.a(str);
    }

    public boolean e(String str) {
        return SyncManager.b(str);
    }

    public void a(String str, final AsyncCallback<Void, Error> asyncCallback) {
        RemoteFamilyApi.a().e(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (ThirdAccountBindManager.h != null) {
                    if (jSONObject != null) {
                        int optInt = jSONObject.optInt("code");
                        if (optInt != 0) {
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(new Error(optInt, jSONObject.optString("message", "")));
                            }
                        } else if (asyncCallback != null) {
                            asyncCallback.onSuccess(null);
                        }
                    } else if (asyncCallback != null) {
                        asyncCallback.onFailure(new Error(Integer.MIN_VALUE, "result is null"));
                    }
                }
            }

            public void onFailure(Error error) {
                if (ThirdAccountBindManager.h != null && asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void f() {
        if (h != null) {
            h = null;
            try {
                SyncManager.a();
                this.k.clear();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(SimpleDraweeView simpleDraweeView, String str) {
        Uri uri;
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            uri = CommonUtils.c((int) R.drawable.third_account_default_icon);
        } else {
            uri = Uri.parse(str);
        }
        if (Fresco.getDraweeControllerBuilderSupplier() != null) {
            if (simpleDraweeView.getHierarchy() == null) {
                simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.user_default)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
            }
            simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setPostprocessor(new CircleAvatarProcessor()).build())).setOldController(simpleDraweeView.getController())).build());
        }
    }
}
