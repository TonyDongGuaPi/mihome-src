package com.xiaomi.mishopsdk.util;

import android.text.TextUtils;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.VolleyError;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import java.util.HashMap;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class FavUtil {
    public static HashSet<String> favIds;
    public static OnFavListener mFavListener;
    /* access modifiers changed from: private */
    public static LoadCallback sCallback;

    public interface LoadCallback {
        void onLoadFinish();
    }

    public interface OnFavListener {
        void onFavChanged();
    }

    public static void setCallback(LoadCallback loadCallback) {
        sCallback = loadCallback;
    }

    public static void unsetCallback(LoadCallback loadCallback) {
        if (sCallback == loadCallback) {
            sCallback = null;
        }
    }

    public static void loadFavIds() {
        if (LoginManager.getInstance().hasLogin()) {
            new Thread() {
                public void run() {
                    if (LoginManager.getInstance().hasLogin()) {
                        RequestQueueManager instance = RequestQueueManager.getInstance();
                        instance.postApiRequest((Object) this, HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "user/favoriteIds", (HashMap<String, String>) null, JSONObject.class, false, new Response.Listener<JSONObject>() {
                            public void onError(VolleyError volleyError) {
                            }

                            public void onFinish() {
                            }

                            public void onStart() {
                            }

                            public void onSuccess(JSONObject jSONObject, boolean z) {
                                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                                if (optJSONArray != null) {
                                    HashSet<String> hashSet = new HashSet<>();
                                    int length = optJSONArray.length();
                                    for (int i = 0; i < length; i++) {
                                        hashSet.add(optJSONArray.optString(i));
                                    }
                                    FavUtil.favIds = hashSet;
                                    if (FavUtil.sCallback != null) {
                                        FavUtil.sCallback.onLoadFinish();
                                    }
                                }
                            }
                        });
                    }
                }
            }.start();
        }
    }

    public static String productIdToCommodityId(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(JSMethod.NOT_SET);
        if (split.length < 2) {
            return null;
        }
        return split[1];
    }

    public static void changeFav(final String str, boolean z, boolean z2) {
        StringBuilder sb;
        String str2;
        if (!TextUtils.isEmpty(str)) {
            if (favIds != null && !TextUtils.isEmpty(str)) {
                if (z) {
                    favIds.add(str);
                } else {
                    favIds.remove(str);
                }
            }
            if (z) {
                sb = new StringBuilder();
                sb.append(HostManager.FORMAL_DOMAIN_APP_SHOPAPI);
                str2 = "user/favoriteAdd";
            } else {
                sb = new StringBuilder();
                sb.append(HostManager.FORMAL_DOMAIN_APP_SHOPAPI);
                str2 = "user/favoriteDrop";
            }
            sb.append(str2);
            final String sb2 = sb.toString();
            new Thread() {
                public void run() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("product_id", str);
                    RequestQueueManager.getInstance().postApiRequest((Object) this, sb2, (HashMap<String, String>) hashMap, JSONObject.class, false, new Response.Listener<JSONObject>() {
                        public void onError(VolleyError volleyError) {
                        }

                        public void onStart() {
                        }

                        public void onSuccess(JSONObject jSONObject, boolean z) {
                            JSONArray optJSONArray = jSONObject.optJSONArray("result");
                            if (optJSONArray != null) {
                                HashSet<String> hashSet = new HashSet<>();
                                int length = optJSONArray.length();
                                for (int i = 0; i < length; i++) {
                                    hashSet.add(optJSONArray.optString(i));
                                }
                                FavUtil.favIds = hashSet;
                                if (FavUtil.sCallback != null) {
                                    FavUtil.sCallback.onLoadFinish();
                                }
                            }
                        }

                        public void onFinish() {
                            if (FavUtil.mFavListener != null) {
                                FavUtil.mFavListener.onFavChanged();
                            }
                        }
                    });
                }
            }.start();
        }
    }

    public static void changeFav(String str, boolean z) {
        changeFav(str, z, false);
    }

    public static void setFavListener(OnFavListener onFavListener) {
        mFavListener = onFavListener;
    }
}
