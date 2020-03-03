package com.xiaomi.mishopsdk.io.http;

import android.util.Log;
import com.mishopsdk.volley.Response;
import java.util.HashMap;
import org.json.JSONObject;

public class VolleyTest {
    protected static final String TAG = "VolleyTest";

    public void testGetUserInfo() {
        RequestQueueManager instance = RequestQueueManager.getInstance();
        instance.postApiRequest((Object) this, HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "user/contactInfo", (HashMap<String, String>) null, String.class, new Response.SimpleListener<String>() {
            public void onSuccess(String str, boolean z) {
                Log.d(VolleyTest.TAG, "onSuccess >> " + str);
            }
        });
        RequestQueueManager instance2 = RequestQueueManager.getInstance();
        instance2.postApiRequest((Object) this, HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "user/contactInfo", (HashMap<String, String>) null, String.class, new Response.SimpleListener<JSONObject>() {
            public void onSuccess(JSONObject jSONObject, boolean z) {
                Log.d(VolleyTest.TAG, "onSuccess >> " + jSONObject);
            }
        });
    }

    public void testVoidClass() {
        RequestQueueManager instance = RequestQueueManager.getInstance();
        instance.postApiRequest((Object) this, HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "user/contactInfo", (HashMap<String, String>) null, Void.class, new Response.SimpleListener<Void>() {
            public void onSuccess(Void voidR, boolean z) {
                Log.d(VolleyTest.TAG, "onSuccess >> " + voidR);
            }
        });
    }
}
