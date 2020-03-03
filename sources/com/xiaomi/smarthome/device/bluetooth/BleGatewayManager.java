package com.xiaomi.smarthome.device.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class BleGatewayManager {

    public interface BleGatewayBatchCallback {
        void a(List<BleGateway> list);
    }

    public static boolean a(String str) {
        PluginRecord d;
        Device b = SmartHomeDeviceManager.a().b(str);
        if (b == null || (d = CoreApi.a().d(b.model)) == null || d.c() == null || d.c().O() != 1) {
            return false;
        }
        return true;
    }

    public static void a(final List<String> list, final BleGatewayBatchCallback bleGatewayBatchCallback) {
        if (bleGatewayBatchCallback != null) {
            b(list, new BleGatewayBatchCallback() {
                public void a(final List<BleGateway> list) {
                    BleGatewayManager.b(new BleGatewayBatchCallback() {
                        public void a(List<BleGateway> list) {
                            ArrayList arrayList = new ArrayList();
                            for (String str : list) {
                                BleGateway bleGateway = new BleGateway(str);
                                if (list != null) {
                                    Iterator it = list.iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        BleGateway bleGateway2 = (BleGateway) it.next();
                                        if (TextUtils.equals(str, bleGateway2.f15095a)) {
                                            if (bleGateway2.b().size() > 0) {
                                                bleGateway.a((List<BleGatewayItem>) bleGateway2.b());
                                            }
                                        }
                                    }
                                }
                                if (list != null) {
                                    Iterator<BleGateway> it2 = list.iterator();
                                    while (true) {
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        BleGateway next = it2.next();
                                        if (TextUtils.equals(str, next.f15095a)) {
                                            if (next.b().size() > 0) {
                                                bleGateway.a((List<BleGatewayItem>) next.b());
                                            }
                                        }
                                    }
                                }
                                arrayList.add(bleGateway);
                            }
                            bleGatewayBatchCallback.a(arrayList);
                        }
                    });
                }
            });
        }
    }

    private static void b(final List<String> list, final BleGatewayBatchCallback bleGatewayBatchCallback) {
        if (list != null && list.size() != 0 && bleGatewayBatchCallback != null) {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("dids", new JSONArray(list));
            } catch (JSONException unused) {
            }
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/device/get_bledevice_by_gateway").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<BleGateway>>() {
                /* renamed from: a */
                public List<BleGateway> parse(JSONObject jSONObject) throws JSONException {
                    int i;
                    ArrayList arrayList = new ArrayList();
                    for (String str : list) {
                        BleGateway bleGateway = new BleGateway(str);
                        arrayList.add(bleGateway);
                        if (jSONObject != null) {
                            try {
                                JSONObject jSONObject2 = new JSONObject(jSONObject.optString(str));
                                if (jSONObject2.length() > 0) {
                                    Iterator<String> keys = jSONObject2.keys();
                                    while (keys.hasNext()) {
                                        String next = keys.next();
                                        String string = jSONObject2.getString(next);
                                        if (SmartHomeDeviceManager.a().b(next) != null) {
                                            try {
                                                i = Integer.valueOf(string).intValue();
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                i = 0;
                                            }
                                            bleGateway.a(new BleGatewayItem(next, i));
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    return arrayList;
                }
            }, Crypto.RC4, new AsyncCallback<List<BleGateway>, Error>() {
                /* renamed from: a */
                public void onSuccess(final List<BleGateway> list) {
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            bleGatewayBatchCallback.a(list);
                        }
                    });
                }

                public void onFailure(Error error) {
                    BluetoothLog.c("getBatchBleGateway error: " + error.b());
                    ArrayList arrayList = new ArrayList();
                    for (String bleGateway : list) {
                        arrayList.add(new BleGateway(bleGateway));
                    }
                    bleGatewayBatchCallback.a(arrayList);
                }
            });
        } else if (bleGatewayBatchCallback != null) {
            bleGatewayBatchCallback.a((List<BleGateway>) null);
        }
    }

    /* access modifiers changed from: private */
    public static void b(final BleGatewayBatchCallback bleGatewayBatchCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("dids", new JSONArray());
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/blemesh/dev_belong").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<BleGateway>>() {
            /* renamed from: a */
            public List<BleGateway> parse(JSONObject jSONObject) throws JSONException {
                int i;
                ArrayList arrayList = new ArrayList();
                JSONObject optJSONObject = jSONObject.optJSONObject("relation");
                if (optJSONObject != null) {
                    Iterator<String> keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        try {
                            String next = keys.next();
                            String string = optJSONObject.getString(next);
                            BleGateway bleGateway = new BleGateway(next);
                            arrayList.add(bleGateway);
                            JSONArray jSONArray = new JSONArray(string);
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject optJSONObject2 = jSONArray.optJSONObject(i2);
                                if (optJSONObject2 != null) {
                                    String optString = optJSONObject2.optString("did");
                                    String optString2 = optJSONObject2.optString("rssi");
                                    if (SmartHomeDeviceManager.a().b(optString) != null) {
                                        try {
                                            i = Integer.valueOf(optString2).intValue();
                                        } catch (NumberFormatException e) {
                                            e.printStackTrace();
                                            i = 0;
                                        }
                                        bleGateway.a(new BleGatewayItem(optString, i));
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                return arrayList;
            }
        }, Crypto.RC4, new AsyncCallback<List<BleGateway>, Error>() {
            /* renamed from: a */
            public void onSuccess(final List<BleGateway> list) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        bleGatewayBatchCallback.a(list);
                    }
                });
            }

            public void onFailure(Error error) {
                BluetoothLog.c("getBatchMeshGateway error: " + error.b());
                bleGatewayBatchCallback.a(new ArrayList());
            }
        });
    }

    public static class BleGateway {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f15095a;
        private ArrayList<BleGatewayItem> b = new ArrayList<>();

        public BleGateway(String str) {
            this.f15095a = str;
        }

        public String a() {
            return this.f15095a;
        }

        public void a(BleGatewayItem bleGatewayItem) {
            if (bleGatewayItem != null && !this.b.contains(bleGatewayItem)) {
                this.b.add(bleGatewayItem);
            }
        }

        public void a(List<BleGatewayItem> list) {
            if (list != null && list.size() > 0) {
                this.b.addAll(list);
            }
        }

        public ArrayList<BleGatewayItem> b() {
            return this.b;
        }
    }

    public static class BleGatewayItem implements Parcelable {
        public static final Parcelable.Creator<BleGatewayItem> CREATOR = new Parcelable.Creator<BleGatewayItem>() {
            /* renamed from: a */
            public BleGatewayItem[] newArray(int i) {
                return new BleGatewayItem[i];
            }

            /* renamed from: a */
            public BleGatewayItem createFromParcel(Parcel parcel) {
                return new BleGatewayItem(parcel);
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private String f15096a;
        private int b;

        public int describeContents() {
            return 0;
        }

        public BleGatewayItem(String str, int i) {
            this.f15096a = str;
            this.b = i;
        }

        protected BleGatewayItem(Parcel parcel) {
            this.f15096a = parcel.readString();
            this.b = parcel.readInt();
        }

        public String a() {
            return this.f15096a;
        }

        public void a(String str) {
            this.f15096a = str;
        }

        public int b() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f15096a);
            parcel.writeInt(this.b);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                return TextUtils.equals(this.f15096a, ((BleGatewayItem) obj).f15096a);
            }
            return false;
        }
    }
}
