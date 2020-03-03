package com.xiaomi.smarthome.homeroom.homedevicelist;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.youpin.hawkeye.upload.UploadConstants;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceCountManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f18248a = "DeviceCountManager";
    private static volatile DeviceCountManager b;
    /* access modifiers changed from: private */
    public volatile DeviceCountModel c;
    private BehaviorSubject<Integer> d;

    private DeviceCountManager() {
    }

    public static DeviceCountManager a() {
        if (b == null) {
            synchronized (DeviceCountManager.class) {
                if (b == null) {
                    b = new DeviceCountManager();
                }
            }
        }
        return b;
    }

    public Observable<Integer> b() {
        if (this.d == null) {
            this.d = BehaviorSubject.createDefault(Integer.valueOf(d()));
        }
        return this.d.hide();
    }

    private BehaviorSubject f() {
        if (this.d == null) {
            this.d = BehaviorSubject.createDefault(Integer.valueOf(d()));
        }
        return this.d;
    }

    /* access modifiers changed from: private */
    public void g() {
        f().onNext(Integer.valueOf(d()));
    }

    public void c() {
        RemoteFamilyApi.a().h((List<Home>) null, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                String str;
                String e = DeviceCountManager.f18248a;
                if (jSONObject == null) {
                    str = "fetchDeviceCountFromServer null";
                } else {
                    str = "fetchDeviceCountFromServer: " + jSONObject.toString();
                }
                LogUtilGrey.a(e, str);
                DeviceCountModel unused = DeviceCountManager.this.c = DeviceCountModel.a(jSONObject);
                DeviceCountManager.this.g();
            }

            public void onFailure(Error error) {
                LogUtil.a(DeviceCountManager.f18248a, error == null ? null : error.toString());
                DeviceCountManager.this.g();
            }
        });
    }

    public int d() {
        int w = SmartHomeDeviceManager.a().w();
        DeviceCountModel deviceCountModel = this.c;
        if (deviceCountModel == null) {
            return w;
        }
        int c2 = deviceCountModel.c();
        List<Device> g = MultiHomeDeviceManager.a().g();
        if (g != null && !g.isEmpty()) {
            c2 += g.size();
        }
        return c2 + deviceCountModel.b() + deviceCountModel.e();
    }

    public static class DeviceCountModel implements Parcelable {
        public static final Parcelable.Creator<DeviceCountModel> CREATOR = new Parcelable.Creator<DeviceCountModel>() {
            /* renamed from: a */
            public DeviceCountModel createFromParcel(Parcel parcel) {
                return new DeviceCountModel(parcel);
            }

            /* renamed from: a */
            public DeviceCountModel[] newArray(int i) {
                return new DeviceCountModel[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        private int f18250a;
        private int b;
        private int c;
        private int d;
        private List<SharedHomeDeviceCountModel> e;

        public int describeContents() {
            return 0;
        }

        public static DeviceCountModel a(JSONObject jSONObject) {
            JSONObject optJSONObject;
            JSONArray optJSONArray;
            if (jSONObject == null) {
                return null;
            }
            DeviceCountModel deviceCountModel = new DeviceCountModel();
            JSONObject optJSONObject2 = jSONObject.optJSONObject("owned");
            if (optJSONObject2 != null) {
                deviceCountModel.b(optJSONObject2.optInt(UploadConstants.i));
                deviceCountModel.a(optJSONObject2.optInt("nd"));
            }
            if (jSONObject.isNull("share") || (optJSONObject = jSONObject.optJSONObject("share")) == null) {
                return deviceCountModel;
            }
            deviceCountModel.c(optJSONObject.optInt("nd"));
            deviceCountModel.d(optJSONObject.optInt(UploadConstants.i));
            if (optJSONObject.isNull("share_family") || (optJSONArray = optJSONObject.optJSONArray("share_family")) == null || optJSONArray.length() == 0) {
                return deviceCountModel;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                SharedHomeDeviceCountModel a2 = SharedHomeDeviceCountModel.a(optJSONArray.optJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            deviceCountModel.a((List<SharedHomeDeviceCountModel>) arrayList);
            return deviceCountModel;
        }

        public int a() {
            return this.f18250a;
        }

        public int b() {
            List<SharedHomeDeviceCountModel> g = g();
            if (g == null || g.isEmpty()) {
                return 0;
            }
            int i = 0;
            for (int i2 = 0; i2 < g.size(); i2++) {
                SharedHomeDeviceCountModel sharedHomeDeviceCountModel = g.get(i2);
                if (sharedHomeDeviceCountModel != null) {
                    i += sharedHomeDeviceCountModel.a();
                }
            }
            return i;
        }

        public int c() {
            return this.f18250a;
        }

        public void a(int i) {
            this.f18250a = i;
        }

        public int d() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public int e() {
            return this.c;
        }

        public void c(int i) {
            this.c = i;
        }

        public int f() {
            return this.d;
        }

        public void d(int i) {
            this.d = i;
        }

        public List<SharedHomeDeviceCountModel> g() {
            return this.e;
        }

        public void a(List<SharedHomeDeviceCountModel> list) {
            this.e = list;
        }

        public static class SharedHomeDeviceCountModel implements Parcelable {
            public static final Parcelable.Creator<SharedHomeDeviceCountModel> CREATOR = new Parcelable.Creator<SharedHomeDeviceCountModel>() {
                /* renamed from: a */
                public SharedHomeDeviceCountModel createFromParcel(Parcel parcel) {
                    return new SharedHomeDeviceCountModel(parcel);
                }

                /* renamed from: a */
                public SharedHomeDeviceCountModel[] newArray(int i) {
                    return new SharedHomeDeviceCountModel[i];
                }
            };

            /* renamed from: a  reason: collision with root package name */
            private int f18251a;
            private int b;
            private long c;
            private long d;

            public int describeContents() {
                return 0;
            }

            public static SharedHomeDeviceCountModel a(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return null;
                }
                SharedHomeDeviceCountModel sharedHomeDeviceCountModel = new SharedHomeDeviceCountModel();
                sharedHomeDeviceCountModel.b(jSONObject.optInt(UploadConstants.i));
                sharedHomeDeviceCountModel.a(jSONObject.optInt("nd"));
                sharedHomeDeviceCountModel.b(jSONObject.optLong("home_id"));
                sharedHomeDeviceCountModel.a(jSONObject.optLong("home_owner"));
                return sharedHomeDeviceCountModel;
            }

            public int a() {
                return this.f18251a;
            }

            public int b() {
                return this.f18251a;
            }

            public void a(int i) {
                this.f18251a = i;
            }

            public int c() {
                return this.b;
            }

            public void b(int i) {
                this.b = i;
            }

            public long d() {
                return this.c;
            }

            public void a(long j) {
                this.c = j;
            }

            public long e() {
                return this.d;
            }

            public void b(long j) {
                this.d = j;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f18251a);
                parcel.writeInt(this.b);
                parcel.writeLong(this.c);
                parcel.writeLong(this.d);
            }

            public SharedHomeDeviceCountModel() {
            }

            protected SharedHomeDeviceCountModel(Parcel parcel) {
                this.f18251a = parcel.readInt();
                this.b = parcel.readInt();
                this.c = parcel.readLong();
                this.d = parcel.readLong();
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f18250a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeTypedList(this.e);
        }

        public DeviceCountModel() {
        }

        protected DeviceCountModel(Parcel parcel) {
            this.f18250a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.createTypedArrayList(SharedHomeDeviceCountModel.CREATOR);
        }
    }
}
