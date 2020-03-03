package com.tiqiaa.icontrol.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.icontrol.dev.LibLoader;
import com.imi.fastjson.JSON;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.parser.Feature;
import com.tiqiaa.database.DataBaseManager;
import com.tiqiaa.icontrol.util.DTOUtil;
import com.tiqiaa.irdnasdk.IrDnaSdkHelper;
import com.tiqiaa.local.LocalIrDb;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

public class TiqiaaService {
    public static final int CHECK_REQUST_NOT_SUPPORTED_APP_VERSION = -9999;
    public static final int CHECK_REQUST_OK = 1;
    public static final String REMOTE_DIY_URL_ROOT = "http://112.124.46.149:8080/icontrol";
    public static final boolean REMOTE_IOS_TEST = false;
    public static final String REMOTE_OFFICIAL_URL_ROOT = "http://115.29.222.198:8080/icontrolofficial";
    public static final boolean REMOTE_TESTMODE = false;
    public static final String REQUEST_CLIENT_PARAMS = "android";
    private static final int SDK_FLAG = 3;
    private static final int SDK_VERSION = 4;
    public static String TAG = "TiqiaaService";
    protected static boolean TEST_MODE = false;
    private static final boolean TEST_OFFICIAL_SERVICE = false;
    private static final String TQ_KEY = "TIQIAA_APPKEY";
    public static final boolean TV_TESTMODE = false;
    public static final String TV_URL_ROOT = "http://112.124.46.149:8080/tv_service/services/tv";
    public static final String URL_ACRA_REPORT = "http://112.124.46.149:8080/icontrol/services/general/acra";
    public static final String URL_COMMENT = "http://112.124.46.149:8080/icontrol/services/general/comment";
    public static final String URL_DELETE_REMOTE = "http://112.124.46.149:8080/icontrol/services/remote/delete_remote";
    public static final String URL_DIY_REMOTE_SERVICE_APP_USED = "http://112.124.46.149:8080/icontrol/services/remote/app_used";
    public static final String URL_DOWNLOAD_CLOUD_REMOTES_USED_REC = "http://112.124.46.149:8080/icontrol/services/remote/download_cloud_remotes_used_rec";
    public static final String URL_DOWNLOAD_REMOTE = "http://112.124.46.149:8080/icontrol/services/remote/download_remote";
    public static final String URL_DOWNLOAD_TEST_KEYS = "http://112.124.46.149:8080/icontrol/services/remote/load_test_keys";
    public static final String URL_DOWNLOAD_USER_SCENE_REMOTE_SETTINGS = "http://112.124.46.149:8080/icontrol/services/remote/download_scene_remote_settings";
    public static final String URL_GET_MY_REMOTE_IDS_SETTINGS = "http://112.124.46.149:8080/icontrol/services/remote/get_my_remotes";
    public static final String URL_LOAD_NOTICE = "http://112.124.46.149:8080/icontrol/services/general/load_newest_notice";
    public static final String URL_LOAD_PROVIDER_CHANNEL_NUM_SETTINGS = "http://112.124.46.149:8080/tv_service/services/tv/load_provider_channel_num_settings";
    public static final String URL_LOAD_PROVIDER_REMOTES = "http://112.124.46.149:8080/tv_service/services/tv/load_provider_remotes";
    public static final String URL_MATCH_REMOTE = "http://112.124.46.149:8080/icontrol/services/remote/match_remote";
    public static final String URL_MISSING_MODEL = "http://112.124.46.149:8080/icontrol/services/general/missing_mode";
    public static final String URL_OFFICIAL_SEVICES_APP_USED = "http://115.29.222.198:8080/icontrolofficial/services/remote/app_used";
    public static final String URL_OFFICIAL_SEVICES_DOWNLOAD_REMOTE = "http://115.29.222.198:8080/icontrolofficial/services/remote/download_remote";
    public static final String URL_OFFICIAL_SEVICES_DOWNLOAD_TEST_KEYS = "http://115.29.222.198:8080/icontrolofficial/services/remote/load_test_keys";
    public static final String URL_OFFICIAL_SEVICES_EXACT_MATCH = "http://115.29.222.198:8080/icontrolofficial/services/remote/exact_match_remote";
    public static final String URL_OFFICIAL_SEVICES_LOAD_TIQIAA_NOTICE = "http://115.29.222.198:8080/icontrolofficial/services/remote/load_tiqiaa_notice";
    public static final String URL_OFFICIAL_SEVICES_MATCH_REMOTES = "http://115.29.222.198:8080/icontrolofficial/services/remote/match_remote";
    public static final String URL_OFFICIAL_SEVICES_PHYSICAL_REMOTE_MATCH = "http://115.29.222.198:8080/icontrolofficial/services/remote/physical_remote_match";
    public static final String URL_OFFICIAL_SEVICES_SEARCH_REMOTES = "http://115.29.222.198:8080/icontrolofficial/services/remote/search_remote";
    public static final String URL_PAY_SERVICE_BUY_OR_EARN_COINS = "http://112.124.46.149:8080/icontrol/services/pay/buy_or_earn_coins";
    public static final String URL_PAY_SERVICE_BUY_REMOTE = "http://112.124.46.149:8080/icontrol/services/pay/buy_remote";
    public static final String URL_PAY_SERVICE_CANCEL_DEAL = "http://112.124.46.149:8080/icontrol/services/pay/cancel_deal";
    public static final String URL_PAY_SERVICE_GET_USER_ASSETS = "http://112.124.46.149:8080/icontrol/services/pay/get_user_assets";
    public static final String URL_PAY_SERVICE_LOAD_PAID_REMOTE_IDS = "http://112.124.46.149:8080/icontrol/services/pay/load_paid_remotes";
    public static final String URL_PAY_SERVICE_REFERRER_GOT_COINS = "http://112.124.46.149:8080/icontrol/services/pay/referrer_got_coins";
    public static final String URL_PAY_SERVICE_SHARE_COINS = "http://112.124.46.149:8080/icontrol/services/pay/share_coins";
    public static final String URL_SEARCH_REMOTE = "http://112.124.46.149:8080/icontrol/services/remote/search_remote";
    public static final String URL_SUGGEST = "http://112.124.46.149:8080/icontrol/services/general/suggest";
    public static final String URL_SYNC_CHANNEL_SETTINGS_DOWNLOAD = "http://112.124.46.149:8080/tv_service/services/tv/sync_channel_settings_download";
    public static final String URL_SYNC_CHANNEL_SETTINGS_UPLOAD = "http://112.124.46.149:8080/tv_service/services/tv/sync_channel_settings_upload";
    public static final String URL_TOP_BRANDS = "http://112.124.46.149:8080/icontrol/services/remote/update_top_brands";
    public static final String URL_TV_CHANNEL = "http://112.124.46.149:8080/tv_service/services/tv/load_channels";
    public static final String URL_TV_FORENOTICES = "http://112.124.46.149:8080/tv_service/services/tv/load_tv_forenotices";
    public static final String URL_TV_RATINGS = "http://112.124.46.149:8080/tv_service/services/tv/upload_tv_watch_records";
    public static final String URL_TV_SHOW = "http://112.124.46.149:8080/tv_service/services/tv/load_tvshow";
    public static final String URL_TV_STAR = "http://112.124.46.149:8080/tv_service/services/tv/load_star";
    public static final String URL_UPLOAD_LOCAL_CLOUD_REMOTES_REC = "http://112.124.46.149:8080/icontrol/services/remote/upload_local_cloud_remotes_used_rec";
    public static final String URL_UPLOAD_PROVIDER_SETTING = "http://112.124.46.149:8080/tv_service/services/tv/upload_provider_setting";
    public static final String URL_UPLOAD_REMOTE = "http://112.124.46.149:8080/icontrol/services/remote/upload_remote";
    public static final String URL_UPLOAD_USER_REAL_SERIALNUMBER = "http://112.124.46.149:8080/icontrol/services/remote/upload_user_real_serialnumber";
    public static final String URL_UPLOAD_USER_SCENE_REMOTE_SETTINGS = "http://112.124.46.149:8080/icontrol/services/remote/upload_scene_remote_settings";
    public static final String URL_USER_FORGET = "http://112.124.46.149:8080/icontrol/services/users/forgot_password";
    public static final String URL_USER_LOGIN = "http://112.124.46.149:8080/icontrol/services/users/login";
    public static final String URL_USER_REGISTER = "http://112.124.46.149:8080/icontrol/services/users/register";
    public static final String URL_USER_RETRIEVE_PASSWORD = "http://112.124.46.149:8080/icontrol/services/users/retrieve_password";
    public static final String URL_USER_UPDATE = "http://112.124.46.149:8080/icontrol/services/users/update";
    public static final String URL_VERIFY_DEV = "http://112.124.46.149:8080/icontrol/services/general/verify_dev";
    public static final String URL_ZAZA_VOLUME = "http://112.124.46.149:8080/icontrol/services/general/zaza_maxvolume";
    private static boolean isLocalServer = false;
    public static String license;
    protected static Context mContext;
    public static boolean working;
    protected boolean closed;
    protected ConnectivityManager connectivity;
    protected HttpClient mHttpClient;
    /* access modifiers changed from: protected */
    public ResponseDTO response_dto;

    interface BaseCallBack {
        public static final int ERROR_CODE_FAILURE = 10003;
        public static final int ERROR_CODE_NET_ERROR = 12001;
        public static final int ERROR_CODE_NO_NET = 12000;
        public static final int ERROR_CODE_SUCCESS = 10000;
    }

    public boolean isNidu() {
        return false;
    }

    public boolean isSupportDiyDb() {
        return true;
    }

    public boolean isSupportLocalDb() {
        return true;
    }

    public boolean isSupportOfficialDb() {
        return true;
    }

    public static Context getAppContext() throws NotInitTiqiaaServiceException {
        if (mContext != null) {
            return mContext;
        }
        throw new NotInitTiqiaaServiceException();
    }

    public static void init(Context context, String str) {
        if (context != null) {
            LibLoader.b(context);
            IrDnaSdkHelper.init(context, str, 4, 3);
            LocalIrDb.getIrDb(context).initSdk(str, 4, 3);
            DataBaseManager.init(context);
            mContext = context;
            license = str;
            if (TEST_MODE) {
                LogUtil.setAllLogOn();
                return;
            }
            return;
        }
        throw new NullPointerException("the method param : context is null");
    }

    public static boolean isInitialized() {
        return (mContext == null || RequestDTO.getTiqiaaKey() == null) ? false : true;
    }

    public static void enableTestMode() {
        TEST_MODE = true;
        LogUtil.setAllLogOn();
    }

    public static boolean isTestModeEnable() {
        return TEST_MODE;
    }

    protected static class NotInitTiqiaaServiceException extends RuntimeException {
        private static final long serialVersionUID = -3556245221673264128L;

        public NotInitTiqiaaServiceException() {
            super("The application context did not initialized !!! call TiqiaaService.init(context) while your app starting...");
        }

        public NotInitTiqiaaServiceException(String str) {
            super(str);
        }
    }

    public static class NotFoundTiqiaaKeyException extends RuntimeException {
        private static final long serialVersionUID = -3556245221673264128L;

        public NotFoundTiqiaaKeyException() {
            super("not found 'TIQIAA_APPKEY' in manifest xml file !!! please add the metadata in AndroidManifest.xml for using Tiqiaa web service..");
        }

        public NotFoundTiqiaaKeyException(String str) {
            super(str);
        }
    }

    public TiqiaaService(Context context) {
        mContext = context;
        initHttpParams();
    }

    private void initHttpParams() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, true);
        ConnManagerParams.setTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 35000);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        this.mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        this.mHttpClient = new DefaultHttpClient(basicHttpParams);
        this.closed = false;
    }

    public static boolean isWorking() {
        return working;
    }

    public ResponseDTO getResponseDTO() {
        return this.response_dto;
    }

    /* JADX INFO: finally extract failed */
    private String getResponseJson(InputStream inputStream, String str) throws IOException {
        if (inputStream == null) {
            return "";
        }
        if (str == null) {
            str = "ISO-8859-1";
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    inputStreamReader.close();
                    LogUtil.d(TAG, "getResponseJson.............reader.close");
                    return stringBuffer.toString();
                }
                stringBuffer.append(cArr, 0, read);
            }
        } catch (Throwable th) {
            inputStreamReader.close();
            LogUtil.d(TAG, "getResponseJson.............reader.close");
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e5 A[SYNTHETIC, Splitter:B:52:0x00e5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.lang.String doPost(org.apache.http.client.methods.HttpPost r9) throws java.lang.Exception, java.lang.Error {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
            java.lang.String r2 = "request.url="
            r1.<init>(r2)     // Catch:{ all -> 0x00f8 }
            java.net.URI r2 = r9.getURI()     // Catch:{ all -> 0x00f8 }
            r1.append(r2)     // Catch:{ all -> 0x00f8 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f8 }
            com.tiqiaa.icontrol.util.LogUtil.v(r0, r1)     // Catch:{ all -> 0x00f8 }
            boolean r0 = r8.closed     // Catch:{ all -> 0x00f8 }
            if (r0 == 0) goto L_0x001f
            r8.initHttpParams()     // Catch:{ all -> 0x00f8 }
        L_0x001f:
            r0 = 0
            r8.response_dto = r0     // Catch:{ all -> 0x00f8 }
            r1 = 0
            org.apache.http.client.HttpClient r2 = r8.mHttpClient     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            org.apache.http.HttpResponse r2 = r2.execute(r9)     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            org.apache.http.HttpEntity r3 = r2.getEntity()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            java.lang.String r4 = TAG     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            java.lang.String r6 = "response....StatusCode = "
            r5.<init>(r6)     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            org.apache.http.StatusLine r6 = r2.getStatusLine()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            int r6 = r6.getStatusCode()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            r5.append(r6)     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            java.lang.String r5 = r5.toString()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            com.tiqiaa.icontrol.util.LogUtil.w(r4, r5)     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            org.apache.http.StatusLine r2 = r2.getStatusLine()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            int r2 = r2.getStatusCode()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 == r4) goto L_0x005e
            r9.abort()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            r8.close()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            working = r1     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            monitor-exit(r8)
            return r0
        L_0x005e:
            java.io.InputStream r2 = r3.getContent()     // Catch:{ ClientProtocolException -> 0x00d6, IOException -> 0x00c9, Exception -> 0x00bc, Error -> 0x00af }
            java.lang.String r0 = "UTF-8"
            java.lang.String r0 = r8.getResponseJson(r2, r0)     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            java.lang.String r3 = TAG     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            java.lang.String r5 = "response....rsJsonStr = "
            r4.<init>(r5)     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            r4.append(r0)     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            java.lang.String r4 = r4.toString()     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            com.tiqiaa.icontrol.util.LogUtil.w(r3, r4)     // Catch:{ ClientProtocolException -> 0x00a8, IOException -> 0x00a3, Exception -> 0x009e, Error -> 0x0099, all -> 0x0096 }
            if (r2 == 0) goto L_0x008f
            r2.close()     // Catch:{ IOException -> 0x0088 }
            java.lang.String r9 = TAG     // Catch:{ IOException -> 0x0088 }
            java.lang.String r2 = "doPost.............in.close"
            com.tiqiaa.icontrol.util.LogUtil.d(r9, r2)     // Catch:{ IOException -> 0x0088 }
            goto L_0x008f
        L_0x0088:
            r9 = move-exception
            r8.close()     // Catch:{ all -> 0x00f8 }
            r9.printStackTrace()     // Catch:{ all -> 0x00f8 }
        L_0x008f:
            r8.close()     // Catch:{ all -> 0x00f8 }
            working = r1     // Catch:{ all -> 0x00f8 }
            monitor-exit(r8)
            return r0
        L_0x0096:
            r9 = move-exception
            r0 = r2
            goto L_0x00e3
        L_0x0099:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L_0x00b0
        L_0x009e:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L_0x00bd
        L_0x00a3:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L_0x00ca
        L_0x00a8:
            r0 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L_0x00d7
        L_0x00ad:
            r9 = move-exception
            goto L_0x00e3
        L_0x00af:
            r2 = move-exception
        L_0x00b0:
            r2.printStackTrace()     // Catch:{ all -> 0x00ad }
            r9.abort()     // Catch:{ all -> 0x00ad }
            r8.close()     // Catch:{ all -> 0x00ad }
            working = r1     // Catch:{ all -> 0x00ad }
            throw r2     // Catch:{ all -> 0x00ad }
        L_0x00bc:
            r2 = move-exception
        L_0x00bd:
            r2.printStackTrace()     // Catch:{ all -> 0x00ad }
            r9.abort()     // Catch:{ all -> 0x00ad }
            r8.close()     // Catch:{ all -> 0x00ad }
            working = r1     // Catch:{ all -> 0x00ad }
            throw r2     // Catch:{ all -> 0x00ad }
        L_0x00c9:
            r2 = move-exception
        L_0x00ca:
            r2.printStackTrace()     // Catch:{ all -> 0x00ad }
            r9.abort()     // Catch:{ all -> 0x00ad }
            r8.close()     // Catch:{ all -> 0x00ad }
            working = r1     // Catch:{ all -> 0x00ad }
            throw r2     // Catch:{ all -> 0x00ad }
        L_0x00d6:
            r2 = move-exception
        L_0x00d7:
            r2.printStackTrace()     // Catch:{ all -> 0x00ad }
            r9.abort()     // Catch:{ all -> 0x00ad }
            r8.close()     // Catch:{ all -> 0x00ad }
            working = r1     // Catch:{ all -> 0x00ad }
            throw r2     // Catch:{ all -> 0x00ad }
        L_0x00e3:
            if (r0 == 0) goto L_0x00f7
            r0.close()     // Catch:{ IOException -> 0x00f0 }
            java.lang.String r0 = TAG     // Catch:{ IOException -> 0x00f0 }
            java.lang.String r1 = "doPost.............in.close"
            com.tiqiaa.icontrol.util.LogUtil.d(r0, r1)     // Catch:{ IOException -> 0x00f0 }
            goto L_0x00f7
        L_0x00f0:
            r0 = move-exception
            r8.close()     // Catch:{ all -> 0x00f8 }
            r0.printStackTrace()     // Catch:{ all -> 0x00f8 }
        L_0x00f7:
            throw r9     // Catch:{ all -> 0x00f8 }
        L_0x00f8:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.icontrol.util.TiqiaaService.doPost(org.apache.http.client.methods.HttpPost):java.lang.String");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.tiqiaa.icontrol.util.ResponseDTO postRequest(com.tiqiaa.icontrol.util.RequestDTO r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest--->>>>>>>>>>>>> url = "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r2 = ",request_dto = "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.d(r0, r1)
            r0 = 0
            if (r6 == 0) goto L_0x01cf
            if (r7 != 0) goto L_0x0022
            goto L_0x01cf
        L_0x0022:
            java.lang.String r1 = TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "postRequest---..###....request.PartnerNumber = "
            r2.<init>(r3)
            java.lang.String r3 = r6.getPartnerNumber()
            r2.append(r3)
            java.lang.String r3 = "request.SdkVersion = "
            r2.append(r3)
            int r3 = r6.getSdkVersion()
            r2.append(r3)
            java.lang.String r3 = ",request.TiqiaaKey = "
            r2.append(r3)
            java.lang.String r3 = com.tiqiaa.icontrol.util.RequestDTO.getTiqiaaKey()
            r2.append(r3)
            java.lang.String r3 = ",request.data = "
            r2.append(r3)
            java.lang.Object r3 = r6.getData()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.tiqiaa.icontrol.util.LogUtil.w(r1, r2)
            r1 = -4
            org.apache.http.client.methods.HttpPost r2 = new org.apache.http.client.methods.HttpPost     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            r2.<init>((java.lang.String) r7)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r7 = TAG     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r3 = "postRequest..............01"
            com.tiqiaa.icontrol.util.LogUtil.d(r7, r3)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r6 = com.tiqiaa.icontrol.util.DTOUtil.getRequestJson(r6)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r7 = TAG     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r4 = "json_str="
            r3.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            r3.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r3 = r3.toString()     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            com.tiqiaa.icontrol.util.LogUtil.d(r7, r3)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            r7.<init>()     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            org.apache.http.message.BasicNameValuePair r3 = new org.apache.http.message.BasicNameValuePair     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r4 = "client_request_params"
            r3.<init>(r4, r6)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            r7.add(r3)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            org.apache.http.client.entity.UrlEncodedFormEntity r6 = new org.apache.http.client.entity.UrlEncodedFormEntity     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            r6.<init>(r7)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            r2.setEntity(r6)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            java.lang.String r6 = r5.doPost(r2)     // Catch:{ UnsupportedEncodingException -> 0x01b6, ClientProtocolException -> 0x019d, ConnectTimeoutException -> 0x0184, IOException -> 0x016b, DataProcessException -> 0x0152, Exception -> 0x0129, Error -> 0x0100 }
            if (r6 == 0) goto L_0x00ec
            java.lang.String r7 = ""
            boolean r7 = r6.equals(r7)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            if (r7 == 0) goto L_0x00a7
            goto L_0x00ec
        L_0x00a7:
            java.lang.String r7 = "<html><head>"
            boolean r7 = r6.contains(r7)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            if (r7 == 0) goto L_0x00c3
            java.lang.String r7 = TAG     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.String r3 = "postRequest..............response_json="
            r2.<init>(r3)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            r2.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.String r2 = r2.toString()     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            com.tiqiaa.icontrol.util.LogUtil.w(r7, r2)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            return r0
        L_0x00c3:
            boolean r7 = r5.verifyResponse(r6)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            if (r7 != 0) goto L_0x00d2
            java.lang.String r7 = TAG     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.String r2 = "postRequest......校验MD5未通过"
            com.tiqiaa.icontrol.util.LogUtil.w(r7, r2)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            goto L_0x01ce
        L_0x00d2:
            com.tiqiaa.icontrol.util.ResponseDTO r7 = r5.getResponseDTO()     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            r0 = r7
            goto L_0x01ce
        L_0x00d9:
            r7 = move-exception
            goto L_0x0102
        L_0x00db:
            r7 = move-exception
            goto L_0x012b
        L_0x00dd:
            r7 = move-exception
            goto L_0x0154
        L_0x00e0:
            r7 = move-exception
            goto L_0x016d
        L_0x00e3:
            r7 = move-exception
            goto L_0x0186
        L_0x00e6:
            r7 = move-exception
            goto L_0x019f
        L_0x00e9:
            r7 = move-exception
            goto L_0x01b8
        L_0x00ec:
            java.lang.String r7 = TAG     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.String r3 = "postRequest..........response_json="
            r2.<init>(r3)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            r2.append(r6)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            java.lang.String r2 = r2.toString()     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            com.tiqiaa.icontrol.util.LogUtil.w(r7, r2)     // Catch:{ UnsupportedEncodingException -> 0x00e9, ClientProtocolException -> 0x00e6, ConnectTimeoutException -> 0x00e3, IOException -> 0x00e0, DataProcessException -> 0x00dd, Exception -> 0x00db, Error -> 0x00d9 }
            return r0
        L_0x0100:
            r7 = move-exception
            r6 = r0
        L_0x0102:
            r7.printStackTrace()
            com.tiqiaa.icontrol.util.ResponseDTO r7 = r5.getResponseDTO()
            if (r7 != 0) goto L_0x0110
            com.tiqiaa.icontrol.util.ResponseDTO r7 = new com.tiqiaa.icontrol.util.ResponseDTO
            r7.<init>()
        L_0x0110:
            r0 = r7
            r0.setResponseType(r1)
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............Error..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
            goto L_0x01ce
        L_0x0129:
            r7 = move-exception
            r6 = r0
        L_0x012b:
            r7.printStackTrace()
            com.tiqiaa.icontrol.util.ResponseDTO r7 = r5.getResponseDTO()
            if (r7 != 0) goto L_0x0139
            com.tiqiaa.icontrol.util.ResponseDTO r7 = new com.tiqiaa.icontrol.util.ResponseDTO
            r7.<init>()
        L_0x0139:
            r0 = r7
            r0.setResponseType(r1)
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............Exception..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
            goto L_0x01ce
        L_0x0152:
            r7 = move-exception
            r6 = r0
        L_0x0154:
            r7.printStackTrace()
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............DataProcessException..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
            goto L_0x01ce
        L_0x016b:
            r7 = move-exception
            r6 = r0
        L_0x016d:
            r7.printStackTrace()
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............IOException..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
            goto L_0x01ce
        L_0x0184:
            r7 = move-exception
            r6 = r0
        L_0x0186:
            r7.printStackTrace()
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............ConnectTimeoutException..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
            goto L_0x01ce
        L_0x019d:
            r7 = move-exception
            r6 = r0
        L_0x019f:
            r7.printStackTrace()
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............ClientProtocolException..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
            goto L_0x01ce
        L_0x01b6:
            r7 = move-exception
            r6 = r0
        L_0x01b8:
            r7.printStackTrace()
            java.lang.String r7 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "postRequest...............UnsupportedEncodingException..........response_json="
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r7, r6)
        L_0x01ce:
            return r0
        L_0x01cf:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.icontrol.util.TiqiaaService.postRequest(com.tiqiaa.icontrol.util.RequestDTO, java.lang.String):com.tiqiaa.icontrol.util.ResponseDTO");
    }

    public class IControlWebException extends Exception {
        private static final String msg = "网络服务操作出错!";
        private static final long serialVersionUID = -4488540373718890759L;

        public String getMessage() {
            return msg;
        }

        public IControlWebException() {
        }

        public void printStackTrace() {
            LogUtil.e(TiqiaaService.TAG, msg);
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkNet() {
        return PhoneHelper.checkNet();
    }

    public static final boolean networkIsAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public String getPhoneIMEI() {
        String deviceId = ((TelephonyManager) mContext.getSystemService("phone")).getDeviceId();
        if (deviceId != null && !deviceId.equals("")) {
            return deviceId;
        }
        String macAddress = ((WifiManager) mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        return macAddress == null || macAddress.equals("") ? "device_havnt_imei_or_mac_address" : macAddress;
    }

    public boolean verifyResponse(String str) throws Exception {
        try {
            Map map = (Map) JSON.parseObject(str, new TypeReference<Map<String, String>>() {
            }, new Feature[0]);
            String str2 = (String) map.get(ResponseDTO.RESPONSE_PARAMS);
            if (!DTOUtil.verifyMD5(str2, (String) map.get("md5"))) {
                return false;
            }
            byte[] decoder = DTOUtil.Base64Helper.decoder(str2);
            LogUtil.d(TAG, "--------------------解密解压数据--------------------");
            byte[] decodeAndUnzip = DTOUtil.decodeAndUnzip(decoder);
            String str3 = new String(decodeAndUnzip, "utf-8");
            String str4 = TAG;
            LogUtil.d(str4, "明文----> length = " + str3.length());
            this.response_dto = (ResponseDTO) JSON.parseObject(str3, ResponseDTO.class);
            LogUtil.d(TAG, "解析response完成....");
            WeakRefHandler.add(str2);
            WeakRefHandler.add(decodeAndUnzip);
            WeakRefHandler.add(str3);
            WeakRefHandler.add(decoder);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, " @@@@@@@@@@@@@@@@@@@ verifyResponse ERROR ");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }

    public void close() {
        LogUtil.w(TAG, "BASEWEBSERVICE.......................关闭网络连接.....");
        if (this.mHttpClient != null) {
            this.closed = true;
            this.mHttpClient.getConnectionManager().shutdown();
        }
    }

    public static boolean isLocalServer() {
        return isLocalServer;
    }

    public static void setLocalServer(boolean z) {
        isLocalServer = z;
    }
}
