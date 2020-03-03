package com.mishopsdk.volley.toolbox;

import android.os.Build;
import android.text.TextUtils;
import com.mishopsdk.volley.AuthFailureError;
import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.Request;
import com.mishopsdk.volley.Response;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.ExtendedAuthToken;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.io.http.RequestConstants;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.FileUtil;
import com.xiaomi.mishopsdk.util.NetworkUtil;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import com.xiaomi.mishopsdk.util.ScreenInfo;
import com.xiaomi.shop2.io.http.ShopSigned;
import com.xiaomi.shop2.util.Device;
import com.xiaomi.shop2.util.DeviceUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;

public abstract class BaseRequest<T> extends Request<T> {
    protected static final String COOKIE = "Cookie";
    protected static final String ENCODING_GZIP = "gzip";
    protected static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    protected static final String HEADER_ENCODING = "Content-Encoding";
    protected static final String HEADER_ENCODING_LOWER = "content-encoding";
    protected static final String HEADER_USER_AGENT = "User-Agent";
    protected static final String TAG = "BaseRequest";
    private static String sCookie;
    private static final CookieManager sCookieManager = new CookieManager((CookieStore) null, CookiePolicy.ACCEPT_ALL);
    private String mCUserId;
    protected boolean mGzipEnabled = true;
    protected ConcurrentHashMap<String, String> mHeaders = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<String, String> mParams = new ConcurrentHashMap<>();
    protected ConcurrentHashMap<String, String> mParamsForCacheKey = new ConcurrentHashMap<>();
    protected Request.Priority mPriority;
    private byte[] mRequestBody;
    private ExtendedAuthToken mToken;
    private String mUserId;

    /* access modifiers changed from: protected */
    public boolean addCustomParams(Map<String, String> map) {
        return true;
    }

    public BaseRequest(Builder<?> builder) {
        super(builder.mMethod, builder.mUrl, builder.mListener);
        this.mGzipEnabled = builder.mGzipEnabled;
        this.mPriority = builder.mPriority;
        this.mParams.putAll(builder.mParams);
        this.mHeaders.putAll(builder.mHeaders);
        this.mParamsForCacheKey.putAll(builder.mParams);
        this.mParamsForCacheKey.putAll(builder.mHeaders);
        handleIgnoreCacheKeys(builder.mIgnoreCachekeys, this.mParamsForCacheKey);
        addCustomParams(this.mParams);
        LoginManager instance = LoginManager.getInstance();
        if (instance != null && instance.hasLogin()) {
            String accountId = LoginManager.getInstance().getAccountId();
            if (!TextUtils.isEmpty(accountId)) {
                this.mParamsForCacheKey.put(RequestConstants.Keys.USER_ID, accountId);
            }
        }
        setTag(builder.mTag);
        setShouldHttps(builder.mHttpsEnabled);
        setShouldCache(builder.mShouldCache);
        this.mRequestBody = builder.mRequestBody;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        String str;
        if (this.mGzipEnabled) {
            this.mHeaders.put("Accept-Encoding", "gzip");
        }
        this.mHeaders.put(RequestConstants.Keys.ClientID, RequestConstants.Values.CLIENT_ID);
        this.mHeaders.put(RequestConstants.Keys.NETWORK_STAT, NetworkUtil.getnetworkTypeStr());
        this.mHeaders.put(RequestConstants.Keys.SCREEN_WIDTH_PX, String.valueOf(ScreenInfo.getInstance().getScreenWidth()));
        this.mHeaders.put(RequestConstants.Keys.SCREEN_DENSITYDPI, String.valueOf(ScreenInfo.getInstance().getScreenDensitydpi()));
        this.mHeaders.put(RequestConstants.Keys.VERSION_CDOE, String.valueOf(Device.MISHOP_SDK_VERSION));
        ConcurrentHashMap<String, String> concurrentHashMap = this.mHeaders;
        if (TextUtils.isEmpty(Device.MISHOP_SDK_VERSION_STRING)) {
            str = "";
        } else {
            str = String.valueOf(Device.MISHOP_SDK_VERSION_STRING);
        }
        concurrentHashMap.put(RequestConstants.Keys.VERSION_NAME, str);
        this.mHeaders.put(RequestConstants.Keys.DEVICE_ID, DeviceUtil.getInstance().getUniqueId());
        this.mHeaders.put(RequestConstants.Keys.IS_PAD, RequestConstants.Values.IS_PAD ? "1" : "0");
        this.mHeaders.put(RequestConstants.Keys.ChANNEL_ID, RequestConstants.SDK_Channel.channel_id);
        this.mHeaders.put(RequestConstants.Keys.MODEL, Device.MODEL);
        this.mHeaders.put(RequestConstants.Keys.ANDROID_VERSION, String.valueOf(Build.VERSION.SDK_INT));
        String cookies = getCookies();
        if (!TextUtils.isEmpty(cookies)) {
            this.mHeaders.put("Cookie", cookies);
        }
        if (this.mParams != null) {
            this.mHeaders.put(RequestConstants.Keys.Auth, ShopSigned.getSignedStr(this.mParams));
        }
        String uuid = getUuid();
        if (!TextUtils.isEmpty(uuid)) {
            this.mHeaders.put(RequestConstants.Keys.uuid, uuid);
        }
        return this.mHeaders;
    }

    public byte[] getBody() throws AuthFailureError {
        if (this.mRequestBody != null) {
            return this.mRequestBody;
        }
        return super.getBody();
    }

    /* access modifiers changed from: protected */
    public void handleIgnoreCacheKeys(ArrayList<String> arrayList, ConcurrentHashMap<String, String> concurrentHashMap) {
        if (arrayList != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                concurrentHashMap.remove(it.next());
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Map<String, String> getParams() throws AuthFailureError {
        return this.mParams;
    }

    public String getCacheKey() {
        TreeSet<String> treeSet = new TreeSet<>(this.mParamsForCacheKey.keySet());
        String str = "";
        StringBuilder sb = new StringBuilder(getUrl());
        sb.append(Operators.BLOCK_START_STR);
        for (String str2 : treeSet) {
            sb.append(str);
            str = ",";
            sb.append(str2);
            sb.append("=");
            sb.append(this.mParamsForCacheKey.get(str2));
        }
        sb.append("}");
        return sb.toString();
    }

    public Request.Priority getPriority() {
        return this.mPriority != null ? this.mPriority : super.getPriority();
    }

    /* access modifiers changed from: protected */
    public boolean isGzipped(NetworkResponse networkResponse) {
        Map<String, String> map = networkResponse.headers;
        return map != null && !map.isEmpty() && ((map.containsKey("Content-Encoding") && map.get("Content-Encoding").equalsIgnoreCase("gzip")) || (map.containsKey("content-encoding") && map.get("content-encoding").equalsIgnoreCase("gzip")));
    }

    /* access modifiers changed from: protected */
    public byte[] decompressResponse(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream;
        GZIPInputStream gZIPInputStream2;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream2);
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = byteArrayInputStream2;
                gZIPInputStream = null;
                FileUtil.closeQuietly(byteArrayOutputStream);
                FileUtil.closeQuietly(gZIPInputStream);
                FileUtil.closeQuietly(byteArrayInputStream);
                throw th;
            }
            try {
                byte[] bArr2 = new byte[8192];
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = gZIPInputStream2.read(bArr2, 0, 8192);
                        if (read != -1) {
                            byteArrayOutputStream2.write(bArr2, 0, read);
                        } else {
                            byte[] byteArray = byteArrayOutputStream2.toByteArray();
                            FileUtil.closeQuietly(byteArrayOutputStream2);
                            FileUtil.closeQuietly(gZIPInputStream2);
                            FileUtil.closeQuietly(byteArrayInputStream2);
                            return byteArray;
                        }
                    } catch (Throwable th2) {
                        byteArrayInputStream = byteArrayInputStream2;
                        gZIPInputStream = gZIPInputStream2;
                        th = th2;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        FileUtil.closeQuietly(byteArrayOutputStream);
                        FileUtil.closeQuietly(gZIPInputStream);
                        FileUtil.closeQuietly(byteArrayInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th3) {
                ByteArrayInputStream byteArrayInputStream3 = byteArrayInputStream2;
                gZIPInputStream = gZIPInputStream2;
                th = th3;
                byteArrayInputStream = byteArrayInputStream3;
                FileUtil.closeQuietly(byteArrayOutputStream);
                FileUtil.closeQuietly(gZIPInputStream);
                FileUtil.closeQuietly(byteArrayInputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            gZIPInputStream = null;
            byteArrayInputStream = null;
            FileUtil.closeQuietly(byteArrayOutputStream);
            FileUtil.closeQuietly(gZIPInputStream);
            FileUtil.closeQuietly(byteArrayInputStream);
            throw th;
        }
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> {
        /* access modifiers changed from: private */
        public boolean mGzipEnabled = true;
        /* access modifiers changed from: private */
        public ConcurrentHashMap<String, String> mHeaders = new ConcurrentHashMap<>();
        /* access modifiers changed from: private */
        public boolean mHttpsEnabled = false;
        /* access modifiers changed from: private */
        public ArrayList<String> mIgnoreCachekeys = new ArrayList<>();
        /* access modifiers changed from: private */
        public Response.Listener mListener;
        /* access modifiers changed from: private */
        public int mMethod = 1;
        /* access modifiers changed from: private */
        public ConcurrentHashMap<String, String> mParams = new ConcurrentHashMap<>();
        /* access modifiers changed from: private */
        public Request.Priority mPriority = Request.Priority.NORMAL;
        protected byte[] mRequestBody;
        /* access modifiers changed from: private */
        public boolean mShouldCache = true;
        /* access modifiers changed from: private */
        public Object mTag;
        /* access modifiers changed from: private */
        public String mUrl;

        /* access modifiers changed from: protected */
        public B self() {
            return this;
        }

        public B addHeader(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                this.mHeaders.put(str, str2);
            }
            return self();
        }

        public B addParams(String str, String str2) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    this.mParams.put(str, str2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return self();
        }

        public B addParams(String str, long j) {
            if (!TextUtils.isEmpty(str) && j >= 0) {
                this.mParams.put(str, String.valueOf(j));
            }
            return self();
        }

        public B addParams(String str, int i) {
            if (!TextUtils.isEmpty(str) && i >= 0) {
                this.mParams.put(str, String.valueOf(i));
            }
            return self();
        }

        public B addParams(HashMap<String, String> hashMap) {
            if (hashMap != null && hashMap.size() > 0) {
                for (Map.Entry next : hashMap.entrySet()) {
                    addParams((String) next.getKey(), (String) next.getValue());
                }
            }
            return self();
        }

        public B setUrl(String str) {
            this.mUrl = str;
            if (!TextUtils.isEmpty(str) && str.toLowerCase().startsWith("https")) {
                setShouldHttps(true);
            }
            return self();
        }

        public B setMethod(int i) {
            this.mMethod = i;
            return self();
        }

        public B setGzipEnabled(boolean z) {
            this.mGzipEnabled = z;
            return self();
        }

        public B setShouldHttps(boolean z) {
            this.mHttpsEnabled = z;
            return self();
        }

        public B setShouldCache(boolean z) {
            this.mShouldCache = z;
            return self();
        }

        public B setPriority(Request.Priority priority) {
            this.mPriority = priority;
            return self();
        }

        public B setTag(Object obj) {
            this.mTag = obj;
            return self();
        }

        public B setListner(Response.Listener listener) {
            setListner(listener, true);
            return self();
        }

        @Deprecated
        public B setListner(Response.Listener listener, boolean z) {
            if (z) {
                this.mListener = new SoftRefListener(listener);
            } else {
                this.mListener = listener;
            }
            return self();
        }

        public B setRequestBody(byte[] bArr) {
            this.mRequestBody = bArr;
            return self();
        }

        public B addIgnoreCacheKey(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mIgnoreCachekeys.add(str);
            }
            return self();
        }
    }

    private String getCookies() {
        ExtendedAuthToken extendedAuthToken;
        String str = "";
        String str2 = "";
        LoginManager instance = LoginManager.getInstance();
        if (instance == null || !instance.hasLogin()) {
            extendedAuthToken = null;
        } else {
            str = LoginManager.getInstance().getAccountId();
            str2 = LoginManager.getInstance().getPrefEncryptedUserId();
            extendedAuthToken = LoginManager.getInstance().getExtendedAuthToken("eshopmobile");
        }
        if (extendedAuthToken == null) {
            extendedAuthToken = ExtendedAuthToken.build("", "");
        }
        if (!TextUtils.equals(str, this.mUserId) || !extendedAuthToken.equals(this.mToken) || !TextUtils.equals(str2, this.mCUserId)) {
            this.mUserId = str;
            this.mCUserId = str2;
            if (TextUtils.isEmpty(extendedAuthToken.authToken)) {
                this.mToken = null;
            } else {
                this.mToken = extendedAuthToken;
            }
            sCookie = null;
        }
        if (this.mToken != null && !TextUtils.isEmpty(this.mToken.authToken) && sCookie == null) {
            sCookie = "serviceToken=" + this.mToken.authToken;
        }
        List<HttpCookie> cookies = sCookieManager.getCookieStore().getCookies();
        if (cookies == null || cookies.size() == 0) {
            return sCookie;
        }
        StringBuilder sb = new StringBuilder();
        for (HttpCookie next : cookies) {
            if (TextUtils.indexOf(URI.create(getUrl()).getHost(), next.getDomain()) > 0) {
                sb.append(next.getName());
                sb.append("=");
                sb.append(next.getValue());
                sb.append("; ");
            }
        }
        return sb.toString() + sCookie;
    }

    private String getUuid() {
        return PreferenceUtil.getStringPref(ShopApp.instance, Constants.Preference.PREF_KEY_HEADER_UUID, "");
    }
}
