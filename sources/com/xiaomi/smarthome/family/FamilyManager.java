package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15700a = "family.cache.key";
    private static final String b = "family.list.key";
    private static final String c = "family.data.key";
    private static FamilyManager d;
    /* access modifiers changed from: private */
    public static final Byte[] e = new Byte[0];
    /* access modifiers changed from: private */
    public List<Listener> f = new ArrayList();
    /* access modifiers changed from: private */
    public List<FamilyItemData> g = new ArrayList();
    /* access modifiers changed from: private */
    public HashMap<String, FamilyData> h = new HashMap<>();
    /* access modifiers changed from: private */
    public String i;
    private Handler j = new Handler(Looper.getMainLooper());

    interface Listener {
        void a();

        void a(FamilyData familyData);
    }

    public void a(Context context, final AsyncResponseCallback<List<FamilyItemData>> asyncResponseCallback) {
        String s = CoreApi.a().s();
        if (!s.equals(this.i)) {
            synchronized (e) {
                d();
                this.i = s;
            }
        }
        RemoteFamilyApi.a().d(context, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                List<FamilyItemData> a2 = FamilyItemData.a(jSONObject.optJSONArray("list"));
                synchronized (FamilyManager.e) {
                    List unused = FamilyManager.this.g = a2;
                }
                FamilyManager.this.f();
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(FamilyManager.this.g);
                }
            }

            public void onFailure(Error error) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(error.a());
                }
            }
        });
    }

    public void a(Context context, final String str, final AsyncResponseCallback<FamilyData> asyncResponseCallback) {
        String s = CoreApi.a().s();
        if (!s.equals(this.i)) {
            synchronized (e) {
                d();
                this.i = s;
            }
        }
        RemoteFamilyApi.a().j(context, str, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                FamilyData a2 = FamilyData.a(jSONObject);
                if (a2 != null) {
                    synchronized (FamilyManager.e) {
                        FamilyManager.this.h.put(str, a2);
                        FamilyManager.this.a(a2);
                    }
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(a2);
                }
            }

            public void onFailure(Error error) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(error.a());
                }
            }
        });
    }

    private FamilyManager() {
    }

    public void a(Listener listener) {
        if (listener != null) {
            for (Listener equals : this.f) {
                if (equals.equals(listener)) {
                    return;
                }
            }
            synchronized (e) {
                this.f.add(listener);
            }
        }
    }

    public void b(Listener listener) {
        if (listener != null) {
            synchronized (e) {
                this.f.remove(listener);
            }
        }
    }

    public static FamilyManager a() {
        if (d == null) {
            synchronized (FamilyManager.class) {
                if (d == null) {
                    d = new FamilyManager();
                    d.g();
                }
            }
        }
        return d;
    }

    public List<FamilyItemData> b() {
        if (c()) {
            return this.g;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void f() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                Context appContext = SHApplication.getAppContext();
                SharedPreferences sharedPreferences = appContext.getSharedPreferences(FamilyManager.f15700a + FamilyManager.this.i, 0);
                if (sharedPreferences == null) {
                    return null;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(FamilyManager.b);
                JSONArray jSONArray = new JSONArray();
                for (FamilyItemData a2 : FamilyManager.this.g) {
                    jSONArray.put(a2.a());
                }
                edit.putString(FamilyManager.b, jSONArray.toString());
                edit.apply();
                return null;
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void a(final FamilyData familyData) {
        if (familyData != null) {
            AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Void doInBackground(Void... voidArr) {
                    Context appContext = SHApplication.getAppContext();
                    SharedPreferences sharedPreferences = appContext.getSharedPreferences(FamilyManager.f15700a + FamilyManager.this.i, 0);
                    if (sharedPreferences == null) {
                        return null;
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.remove(FamilyManager.c + familyData.d.f);
                    edit.putString(FamilyManager.c + familyData.d.f, familyData.a().toString());
                    edit.apply();
                    return null;
                }
            }, new Void[0]);
        }
    }

    private void g() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                FamilyData a2;
                String s = CoreApi.a().s();
                Context appContext = SHApplication.getAppContext();
                SharedPreferences sharedPreferences = appContext.getSharedPreferences(FamilyManager.f15700a + s, 0);
                if (sharedPreferences != null) {
                    synchronized (FamilyManager.e) {
                        FamilyManager.this.d();
                        String unused = FamilyManager.this.i = s;
                        String string = sharedPreferences.getString(FamilyManager.b, (String) null);
                        if (string == null) {
                            return null;
                        }
                        try {
                            List unused2 = FamilyManager.this.g = FamilyItemData.a(new JSONArray(string));
                            for (FamilyItemData familyItemData : FamilyManager.this.g) {
                                String string2 = sharedPreferences.getString(FamilyManager.c + familyItemData.f, (String) null);
                                if (!(string2 == null || (a2 = FamilyData.a(new JSONObject(string2))) == null)) {
                                    FamilyManager.this.h.put(familyItemData.f, a2);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                FamilyManager.this.h();
                return null;
                FamilyManager.this.h();
                return null;
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        String s = CoreApi.a().s();
        return s != null && !s.isEmpty() && s.equals(this.i);
    }

    public FamilyData a(String str) {
        if (c()) {
            return this.h.get(str);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        synchronized (e) {
            if (this.g != null) {
                this.g.clear();
            }
            if (this.h != null) {
                this.h.clear();
            }
            if (this.i != null) {
                this.i = null;
            }
        }
    }

    private void b(final FamilyData familyData) {
        if (this.f != null && this.f.size() > 0) {
            this.j.post(new Runnable() {
                public void run() {
                    for (Listener a2 : FamilyManager.this.f) {
                        a2.a(familyData);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.f != null && this.f.size() > 0) {
            this.j.post(new Runnable() {
                public void run() {
                    for (Listener a2 : FamilyManager.this.f) {
                        a2.a();
                    }
                }
            });
        }
    }
}
