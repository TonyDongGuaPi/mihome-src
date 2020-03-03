package com.xiaomi.smarthome.framework.location;

import android.content.Context;
import android.location.Location;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;

public class LocationSdkApi {
    public static AsyncHandle a(Context context, String str, JsonParser<Location> jsonParser, AsyncCallback<Location, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/location/get").b((List<KeyValuePair>) arrayList).a(), jsonParser, Crypto.RC4, asyncCallback);
    }
}
