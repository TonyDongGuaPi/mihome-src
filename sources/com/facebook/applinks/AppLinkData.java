package com.facebook.applinks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppLinkData {
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_EXTRAS_KEY = "extras";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String AUTO_APPLINK_FLAG_KEY = "is_auto_applink";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    private static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String EXTRAS_DEEPLINK_CONTEXT_KEY = "deeplink_context";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String PROMOTION_CODE_KEY = "promo_code";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG = AppLinkData.class.getCanonicalName();
    @Nullable
    private JSONObject appLinkData;
    @Nullable
    private Bundle argumentBundle;
    @Nullable
    private JSONObject arguments;
    @Nullable
    private String promotionCode;
    @Nullable
    private String ref;
    @Nullable
    private Uri targetUri;

    public interface CompletionHandler {
        void onDeferredAppLinkDataFetched(@Nullable AppLinkData appLinkData);
    }

    public static void fetchDeferredAppLinkData(Context context, CompletionHandler completionHandler) {
        fetchDeferredAppLinkData(context, (String) null, completionHandler);
    }

    public static void fetchDeferredAppLinkData(Context context, final String str, final CompletionHandler completionHandler) {
        Validate.notNull(context, "context");
        Validate.notNull(completionHandler, "completionHandler");
        if (str == null) {
            str = Utility.getMetadataApplicationId(context);
        }
        Validate.notNull(str, "applicationId");
        final Context applicationContext = context.getApplicationContext();
        FacebookSdk.getExecutor().execute(new Runnable() {
            public void run() {
                AppLinkData.fetchDeferredAppLinkFromServer(applicationContext, str, completionHandler);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to put tap time in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to put app link class name in AppLinkData.arguments");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        com.facebook.internal.Utility.logd(TAG, "Unable to put app link URL in AppLinkData.arguments");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x008d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x00ad */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x00cd */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void fetchDeferredAppLinkFromServer(android.content.Context r7, java.lang.String r8, com.facebook.applinks.AppLinkData.CompletionHandler r9) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "event"
            java.lang.String r2 = "DEFERRED_APP_LINK"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e1 }
            com.facebook.internal.AttributionIdentifiers r1 = com.facebook.internal.AttributionIdentifiers.getAttributionIdentifiers(r7)     // Catch:{ JSONException -> 0x00e1 }
            java.lang.String r2 = com.facebook.appevents.AppEventsLogger.getAnonymousAppDeviceGUID(r7)     // Catch:{ JSONException -> 0x00e1 }
            boolean r3 = com.facebook.FacebookSdk.getLimitEventAndDataUsage(r7)     // Catch:{ JSONException -> 0x00e1 }
            com.facebook.internal.Utility.setAppEventAttributionParameters(r0, r1, r2, r3)     // Catch:{ JSONException -> 0x00e1 }
            android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ JSONException -> 0x00e1 }
            com.facebook.internal.Utility.setAppEventExtendedDeviceInfoParameters(r0, r1)     // Catch:{ JSONException -> 0x00e1 }
            java.lang.String r1 = "application_package_name"
            java.lang.String r7 = r7.getPackageName()     // Catch:{ JSONException -> 0x00e1 }
            r0.put(r1, r7)     // Catch:{ JSONException -> 0x00e1 }
            java.lang.String r7 = "%s/activities"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r1[r2] = r8
            java.lang.String r7 = java.lang.String.format(r7, r1)
            r8 = 0
            com.facebook.GraphRequest r7 = com.facebook.GraphRequest.newPostRequest(r8, r7, r0, r8)     // Catch:{ Exception -> 0x00d6 }
            com.facebook.GraphResponse r7 = r7.executeAndWait()     // Catch:{ Exception -> 0x00d6 }
            org.json.JSONObject r7 = r7.getJSONObject()     // Catch:{ Exception -> 0x00d6 }
            if (r7 == 0) goto L_0x00dd
            java.lang.String r0 = "applink_args"
            java.lang.String r0 = r7.optString(r0)     // Catch:{ Exception -> 0x00d6 }
            java.lang.String r1 = "click_time"
            r2 = -1
            long r4 = r7.optLong(r1, r2)     // Catch:{ Exception -> 0x00d6 }
            java.lang.String r1 = "applink_class"
            java.lang.String r1 = r7.optString(r1)     // Catch:{ Exception -> 0x00d6 }
            java.lang.String r6 = "applink_url"
            java.lang.String r7 = r7.optString(r6)     // Catch:{ Exception -> 0x00d6 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00d6 }
            if (r6 != 0) goto L_0x00dd
            com.facebook.applinks.AppLinkData r0 = createFromJson(r0)     // Catch:{ Exception -> 0x00d6 }
            if (r0 == 0) goto L_0x00d4
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x0094
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x008d }
            if (r8 == 0) goto L_0x007b
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x008d }
            java.lang.String r2 = "com.facebook.platform.APPLINK_TAP_TIME_UTC"
            r8.put(r2, r4)     // Catch:{ JSONException -> 0x008d }
        L_0x007b:
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x008d }
            if (r8 == 0) goto L_0x0094
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x008d }
            java.lang.String r2 = "com.facebook.platform.APPLINK_TAP_TIME_UTC"
            java.lang.String r3 = java.lang.Long.toString(r4)     // Catch:{ JSONException -> 0x008d }
            r8.putString(r2, r3)     // Catch:{ JSONException -> 0x008d }
            goto L_0x0094
        L_0x008b:
            r8 = r0
            goto L_0x00d6
        L_0x008d:
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x008b }
            java.lang.String r2 = "Unable to put tap time in AppLinkData.arguments"
            com.facebook.internal.Utility.logd((java.lang.String) r8, (java.lang.String) r2)     // Catch:{ Exception -> 0x008b }
        L_0x0094:
            if (r1 == 0) goto L_0x00b4
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00ad }
            if (r8 == 0) goto L_0x00a1
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00ad }
            java.lang.String r2 = "com.facebook.platform.APPLINK_NATIVE_CLASS"
            r8.put(r2, r1)     // Catch:{ JSONException -> 0x00ad }
        L_0x00a1:
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00ad }
            if (r8 == 0) goto L_0x00b4
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00ad }
            java.lang.String r2 = "com.facebook.platform.APPLINK_NATIVE_CLASS"
            r8.putString(r2, r1)     // Catch:{ JSONException -> 0x00ad }
            goto L_0x00b4
        L_0x00ad:
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x008b }
            java.lang.String r1 = "Unable to put app link class name in AppLinkData.arguments"
            com.facebook.internal.Utility.logd((java.lang.String) r8, (java.lang.String) r1)     // Catch:{ Exception -> 0x008b }
        L_0x00b4:
            if (r7 == 0) goto L_0x00d4
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00cd }
            if (r8 == 0) goto L_0x00c1
            org.json.JSONObject r8 = r0.arguments     // Catch:{ JSONException -> 0x00cd }
            java.lang.String r1 = "com.facebook.platform.APPLINK_NATIVE_URL"
            r8.put(r1, r7)     // Catch:{ JSONException -> 0x00cd }
        L_0x00c1:
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00cd }
            if (r8 == 0) goto L_0x00d4
            android.os.Bundle r8 = r0.argumentBundle     // Catch:{ JSONException -> 0x00cd }
            java.lang.String r1 = "com.facebook.platform.APPLINK_NATIVE_URL"
            r8.putString(r1, r7)     // Catch:{ JSONException -> 0x00cd }
            goto L_0x00d4
        L_0x00cd:
            java.lang.String r7 = TAG     // Catch:{ Exception -> 0x008b }
            java.lang.String r8 = "Unable to put app link URL in AppLinkData.arguments"
            com.facebook.internal.Utility.logd((java.lang.String) r7, (java.lang.String) r8)     // Catch:{ Exception -> 0x008b }
        L_0x00d4:
            r8 = r0
            goto L_0x00dd
        L_0x00d6:
            java.lang.String r7 = TAG
            java.lang.String r0 = "Unable to fetch deferred applink from server"
            com.facebook.internal.Utility.logd((java.lang.String) r7, (java.lang.String) r0)
        L_0x00dd:
            r9.onDeferredAppLinkDataFetched(r8)
            return
        L_0x00e1:
            r7 = move-exception
            com.facebook.FacebookException r8 = new com.facebook.FacebookException
            java.lang.String r9 = "An error occurred while preparing deferred app link"
            r8.<init>((java.lang.String) r9, (java.lang.Throwable) r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.applinks.AppLinkData.fetchDeferredAppLinkFromServer(android.content.Context, java.lang.String, com.facebook.applinks.AppLinkData$CompletionHandler):void");
    }

    @Nullable
    public static AppLinkData createFromActivity(Activity activity) {
        Validate.notNull(activity, "activity");
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        AppLinkData createFromAlApplinkData = createFromAlApplinkData(intent);
        if (createFromAlApplinkData == null) {
            createFromAlApplinkData = createFromJson(intent.getStringExtra(BUNDLE_APPLINK_ARGS_KEY));
        }
        return createFromAlApplinkData == null ? createFromUri(intent.getData()) : createFromAlApplinkData;
    }

    @Nullable
    public static AppLinkData createFromAlApplinkData(Intent intent) {
        Bundle bundleExtra;
        String string;
        String string2;
        if (intent == null || (bundleExtra = intent.getBundleExtra(BUNDLE_AL_APPLINK_DATA_KEY)) == null) {
            return null;
        }
        AppLinkData appLinkData2 = new AppLinkData();
        appLinkData2.targetUri = intent.getData();
        appLinkData2.appLinkData = getAppLinkData(appLinkData2.targetUri);
        if (appLinkData2.targetUri == null && (string2 = bundleExtra.getString(METHOD_ARGS_TARGET_URL_KEY)) != null) {
            appLinkData2.targetUri = Uri.parse(string2);
        }
        appLinkData2.argumentBundle = bundleExtra;
        appLinkData2.arguments = null;
        Bundle bundle = bundleExtra.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        if (bundle != null) {
            appLinkData2.ref = bundle.getString(REFERER_DATA_REF_KEY);
        }
        Bundle bundle2 = bundleExtra.getBundle("extras");
        if (!(bundle2 == null || (string = bundle2.getString("deeplink_context")) == null)) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("promo_code")) {
                    appLinkData2.promotionCode = jSONObject.getString("promo_code");
                }
            } catch (JSONException e) {
                Utility.logd(TAG, "Unable to parse deeplink_context JSON", e);
            }
        }
        return appLinkData2;
    }

    @Nullable
    private static AppLinkData createFromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("version");
            if (jSONObject.getJSONObject("bridge_args").getString("method").equals("applink") && string.equals("2")) {
                AppLinkData appLinkData2 = new AppLinkData();
                appLinkData2.arguments = jSONObject.getJSONObject("method_args");
                if (appLinkData2.arguments.has("ref")) {
                    appLinkData2.ref = appLinkData2.arguments.getString("ref");
                } else if (appLinkData2.arguments.has(ARGUMENTS_REFERER_DATA_KEY)) {
                    JSONObject jSONObject2 = appLinkData2.arguments.getJSONObject(ARGUMENTS_REFERER_DATA_KEY);
                    if (jSONObject2.has(REFERER_DATA_REF_KEY)) {
                        appLinkData2.ref = jSONObject2.getString(REFERER_DATA_REF_KEY);
                    }
                }
                if (appLinkData2.arguments.has(METHOD_ARGS_TARGET_URL_KEY)) {
                    appLinkData2.targetUri = Uri.parse(appLinkData2.arguments.getString(METHOD_ARGS_TARGET_URL_KEY));
                    appLinkData2.appLinkData = getAppLinkData(appLinkData2.targetUri);
                }
                if (appLinkData2.arguments.has("extras")) {
                    JSONObject jSONObject3 = appLinkData2.arguments.getJSONObject("extras");
                    if (jSONObject3.has("deeplink_context")) {
                        JSONObject jSONObject4 = jSONObject3.getJSONObject("deeplink_context");
                        if (jSONObject4.has("promo_code")) {
                            appLinkData2.promotionCode = jSONObject4.getString("promo_code");
                        }
                    }
                }
                appLinkData2.argumentBundle = toBundle(appLinkData2.arguments);
                return appLinkData2;
            }
        } catch (JSONException e) {
            Utility.logd(TAG, "Unable to parse AppLink JSON", e);
        } catch (FacebookException e2) {
            Utility.logd(TAG, "Unable to parse AppLink JSON", e2);
        }
        return null;
    }

    @Nullable
    private static AppLinkData createFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        AppLinkData appLinkData2 = new AppLinkData();
        appLinkData2.targetUri = uri;
        appLinkData2.appLinkData = getAppLinkData(appLinkData2.targetUri);
        return appLinkData2;
    }

    private static Bundle toBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONObject) {
                bundle.putBundle(next, toBundle((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                int i = 0;
                if (jSONArray.length() == 0) {
                    bundle.putStringArray(next, new String[0]);
                } else {
                    Object obj2 = jSONArray.get(0);
                    if (obj2 instanceof JSONObject) {
                        Bundle[] bundleArr = new Bundle[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            bundleArr[i] = toBundle(jSONArray.getJSONObject(i));
                            i++;
                        }
                        bundle.putParcelableArray(next, bundleArr);
                    } else if (!(obj2 instanceof JSONArray)) {
                        String[] strArr = new String[jSONArray.length()];
                        while (i < jSONArray.length()) {
                            strArr[i] = jSONArray.get(i).toString();
                            i++;
                        }
                        bundle.putStringArray(next, strArr);
                    } else {
                        throw new FacebookException("Nested arrays are not supported.");
                    }
                }
            } else {
                bundle.putString(next, obj.toString());
            }
        }
        return bundle;
    }

    @Nullable
    private static JSONObject getAppLinkData(@Nullable Uri uri) {
        String queryParameter;
        if (uri == null || (queryParameter = uri.getQueryParameter(BUNDLE_AL_APPLINK_DATA_KEY)) == null) {
            return null;
        }
        try {
            return new JSONObject(queryParameter);
        } catch (JSONException unused) {
            return null;
        }
    }

    private AppLinkData() {
    }

    public boolean isAutoAppLink() {
        if (this.targetUri == null) {
            return false;
        }
        String host = this.targetUri.getHost();
        String scheme = this.targetUri.getScheme();
        String format = String.format("fb%s", new Object[]{FacebookSdk.getApplicationId()});
        if (!(this.appLinkData != null && this.appLinkData.optBoolean(AUTO_APPLINK_FLAG_KEY)) || !"applinks".equals(host) || !format.equals(scheme)) {
            return false;
        }
        return true;
    }

    @Nullable
    public Uri getTargetUri() {
        return this.targetUri;
    }

    @Nullable
    public String getRef() {
        return this.ref;
    }

    @Nullable
    public String getPromotionCode() {
        return this.promotionCode;
    }

    @Nullable
    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }

    @Nullable
    public Bundle getRefererData() {
        if (this.argumentBundle != null) {
            return this.argumentBundle.getBundle(ARGUMENTS_REFERER_DATA_KEY);
        }
        return null;
    }

    public JSONObject getAppLinkData() {
        return this.appLinkData != null ? this.appLinkData : new JSONObject();
    }
}
