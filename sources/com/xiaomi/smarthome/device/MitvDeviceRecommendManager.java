package com.xiaomi.smarthome.device;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.RemoteRouterMitvApi;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MitvDeviceRecommendManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14903a = "MitvDeviceRecommend";
    public static final String b = "mitv_recommend";
    public static final String g = "pref_last_scan_time";
    public static final String h = "com.xiaomi.mitv.phone.tvassistant";
    private static MitvDeviceRecommendManager l;
    private static Object m = new Object();
    volatile boolean c = false;
    Handler d = new Handler(Looper.getMainLooper());
    HashMap<String, MiTVRecommend> e = new HashMap<>();
    HashMap<String, MiTVRecommend> f = new HashMap<>();
    long i = 0;
    String j;
    boolean k;

    private MitvDeviceRecommendManager() {
        new Thread() {
            public void run() {
                super.run();
                MitvDeviceRecommendManager.this.c = true;
                try {
                    Log.d(MitvDeviceRecommendManager.f14903a, "initial");
                    FileInputStream openFileInput = CommonApplication.getAppContext().openFileInput(MitvDeviceRecommendManager.b);
                    DataInputStream dataInputStream = new DataInputStream(openFileInput);
                    Date date = new Date();
                    if (date.getTime() - dataInputStream.readLong() > 86400000) {
                        openFileInput.close();
                        CommonApplication.getAppContext().deleteFile(MitvDeviceRecommendManager.b);
                        return;
                    }
                    int readInt = dataInputStream.readInt();
                    if (readInt > 0) {
                        byte[] bArr = new byte[readInt];
                        dataInputStream.read(bArr);
                        openFileInput.close();
                        MitvDeviceRecommendManager.this.a(new JSONObject(new String(bArr, "UTF-8")));
                        MitvDeviceRecommendManager.this.c = false;
                    }
                } catch (Exception unused) {
                    CommonApplication.getAppContext().deleteFile(MitvDeviceRecommendManager.b);
                }
            }
        }.start();
    }

    public static MitvDeviceRecommendManager a() {
        if (l == null) {
            synchronized (m) {
                if (l == null) {
                    l = new MitvDeviceRecommendManager();
                }
            }
        }
        return l;
    }

    public static class MiTVRecommend {

        /* renamed from: a  reason: collision with root package name */
        public String f14907a;
        public String b;
        public ArrayList<MiTVRecommendItem> c;

        public static MiTVRecommend a(String str, JSONObject jSONObject) {
            try {
                MiTVRecommend miTVRecommend = new MiTVRecommend();
                Iterator<String> keys = jSONObject.keys();
                if (keys.hasNext()) {
                    miTVRecommend.f14907a = str;
                    miTVRecommend.b = keys.next();
                    JSONArray jSONArray = jSONObject.getJSONArray(miTVRecommend.b);
                    miTVRecommend.c = new ArrayList<>();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        miTVRecommend.c.add(MiTVRecommendItem.a(jSONArray.getJSONObject(i)));
                    }
                }
                return miTVRecommend;
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public static class MiTVRecommendItem implements Parcelable {
        public static final Parcelable.Creator<MiTVRecommendItem> CREATOR = new Parcelable.Creator<MiTVRecommendItem>() {
            /* renamed from: a */
            public MiTVRecommendItem createFromParcel(Parcel parcel) {
                return new MiTVRecommendItem(parcel);
            }

            /* renamed from: a */
            public MiTVRecommendItem[] newArray(int i) {
                return new MiTVRecommendItem[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public String f14908a;
        public int b;
        public String[] c;
        public int d;
        public String e;

        public int describeContents() {
            return 0;
        }

        public MiTVRecommendItem() {
        }

        public MiTVRecommendItem(Parcel parcel) {
            this.f14908a = parcel.readString();
            this.b = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readString();
        }

        public static MiTVRecommendItem a(JSONObject jSONObject) throws JSONException {
            MiTVRecommendItem miTVRecommendItem = new MiTVRecommendItem();
            miTVRecommendItem.b = jSONObject.getInt("id");
            miTVRecommendItem.d = jSONObject.getInt("type");
            miTVRecommendItem.f14908a = jSONObject.getString("poster");
            miTVRecommendItem.e = jSONObject.getString("name");
            JSONArray jSONArray = jSONObject.getJSONArray("from");
            miTVRecommendItem.c = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                miTVRecommendItem.c[i] = jSONArray.getString(i);
            }
            return miTVRecommendItem;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f14908a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.d);
            parcel.writeString(this.e);
        }
    }

    /* access modifiers changed from: package-private */
    public MiTVRecommend a(MiTVDevice miTVDevice) {
        c();
        MiTVRecommend miTVRecommend = this.e.get(miTVDevice.did);
        if (miTVRecommend == null) {
            miTVRecommend = this.f.get(miTVDevice.model);
        }
        if (miTVRecommend == null) {
            b();
        }
        return miTVRecommend;
    }

    /* access modifiers changed from: package-private */
    public MiTVRecommend b(MiTVDevice miTVDevice) {
        MiTVRecommend miTVRecommend = this.e.get(miTVDevice.did);
        return miTVRecommend == null ? this.f.get(miTVDevice.model) : miTVRecommend;
    }

    public void b() {
        if (!this.c) {
            final ArrayList arrayList = new ArrayList();
            for (Device next : SmartHomeDeviceManager.a().d()) {
                if (next instanceof MiTVDevice) {
                    arrayList.add(next.did);
                }
            }
            if (arrayList.size() != 0) {
                this.c = true;
                CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        RemoteRouterMitvApi.a().a(CommonApplication.getAppContext(), (ArrayList<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                MitvDeviceRecommendManager.this.c = false;
                                if (jSONObject != null) {
                                    try {
                                        FileOutputStream openFileOutput = CommonApplication.getAppContext().openFileOutput(MitvDeviceRecommendManager.b, 0);
                                        DataOutputStream dataOutputStream = new DataOutputStream(openFileOutput);
                                        byte[] bytes = jSONObject.toString().getBytes("UTF-8");
                                        dataOutputStream.writeLong(new Date().getTime());
                                        dataOutputStream.writeInt(bytes.length);
                                        dataOutputStream.write(bytes);
                                        openFileOutput.close();
                                    } catch (Exception unused) {
                                    }
                                    MitvDeviceRecommendManager.this.a(jSONObject);
                                    LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                }
                            }

                            public void onFailure(Error error) {
                                MitvDeviceRecommendManager.this.c = false;
                            }
                        });
                    }
                });
            }
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            Log.d(f14903a, "updateRecommend:" + jSONObject.toString());
            try {
                ArrayList arrayList = new ArrayList();
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    JSONArray jSONArray = jSONObject.getJSONArray(next);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        MiTVRecommend a2 = MiTVRecommend.a(next, jSONArray.getJSONObject(i2));
                        if (a2 != null) {
                            arrayList.add(a2);
                        }
                    }
                }
                this.e.clear();
                this.f.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    MiTVRecommend miTVRecommend = (MiTVRecommend) it.next();
                    this.e.put(miTVRecommend.b, miTVRecommend);
                    this.f.put(miTVRecommend.f14907a, miTVRecommend);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void c() {
        long currentTimeMillis = System.currentTimeMillis();
        Context appContext = CommonApplication.getAppContext();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        this.i = defaultSharedPreferences.getLong(g, 0);
        if (this.i == 0) {
            this.i = System.currentTimeMillis() / 1000;
            defaultSharedPreferences.edit().putLong(g, this.i).apply();
            return;
        }
        try {
            if (appContext.getPackageManager().getApplicationInfo(h, 0) != null) {
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = CommonApplication.getAppContext().getContentResolver();
                String[] strArr = {Downloads._DATA};
                Cursor query = contentResolver.query(uri, strArr, "date_added>" + this.i, (String[]) null, "date_added DESC");
                if (query != null) {
                    if (query.getCount() != 0) {
                        query.moveToFirst();
                        this.j = query.getString(query.getColumnIndex(Downloads._DATA));
                        query.close();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Time:");
                        sb.append(System.currentTimeMillis() - currentTimeMillis);
                        sb.append(this.j == null ? " no pic" : " new pic");
                        Log.d("scanpic", sb.toString());
                        return;
                    }
                }
                if (query != null) {
                    query.close();
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void d() {
        this.j = null;
        this.i = System.currentTimeMillis() / 1000;
        PreferenceManager.getDefaultSharedPreferences(CommonApplication.getAppContext()).edit().putLong(g, this.i).apply();
    }

    public String e() {
        return this.j;
    }
}
