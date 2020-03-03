package com.ximalaya.ting.android.opensdk.datatrasfer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.amap.location.common.model.AmapLoc;
import com.drew.metadata.iptc.IptcDirectory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.account.openauth.AuthorizeActivityBase;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.httputil.BaseBuilder;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.BaseResponse;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.httputil.ExecutorDelivery;
import com.ximalaya.ting.android.opensdk.httputil.HttpDNSUtilForOpenSDK;
import com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.httputil.XmSecretKeyUtil;
import com.ximalaya.ting.android.opensdk.httputil.util.BASE64Encoder;
import com.ximalaya.ting.android.opensdk.model.PostResponse;
import com.ximalaya.ting.android.opensdk.model.SercretPubKey;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.AlbumList;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import com.ximalaya.ting.android.opensdk.model.album.AnnouncerListByIds;
import com.ximalaya.ting.android.opensdk.model.album.BatchAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.CategoryRecommendAlbums;
import com.ximalaya.ting.android.opensdk.model.album.CategoryRecommendAlbumsList;
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbums;
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbumsList;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.HotAggregation;
import com.ximalaya.ting.android.opensdk.model.album.HotAggregationList;
import com.ximalaya.ting.android.opensdk.model.album.RecommendCollectAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.RelativeAlbums;
import com.ximalaya.ting.android.opensdk.model.album.SearchAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.SubscribeAlbumList;
import com.ximalaya.ting.android.opensdk.model.album.UpdateBatch;
import com.ximalaya.ting.android.opensdk.model.album.UpdateBatchList;
import com.ximalaya.ting.android.opensdk.model.album.XmAlbumTracksStatue;
import com.ximalaya.ting.android.opensdk.model.announcer.AnnouncerCategory;
import com.ximalaya.ting.android.opensdk.model.announcer.AnnouncerCategoryList;
import com.ximalaya.ting.android.opensdk.model.announcer.AnnouncerList;
import com.ximalaya.ting.android.opensdk.model.app.AppModel;
import com.ximalaya.ting.android.opensdk.model.banner.Banner;
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2;
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List;
import com.ximalaya.ting.android.opensdk.model.banner.BannersContentList;
import com.ximalaya.ting.android.opensdk.model.banner.CategoryBannerList;
import com.ximalaya.ting.android.opensdk.model.banner.DiscoveryBannerList;
import com.ximalaya.ting.android.opensdk.model.banner.RankBannerList;
import com.ximalaya.ting.android.opensdk.model.category.Category;
import com.ximalaya.ting.android.opensdk.model.category.CategoryList;
import com.ximalaya.ting.android.opensdk.model.coldboot.ColdBootDetail;
import com.ximalaya.ting.android.opensdk.model.coldboot.ColdBootTag;
import com.ximalaya.ting.android.opensdk.model.coldboot.GenreList;
import com.ximalaya.ting.android.opensdk.model.coldboot.SubGenreList;
import com.ximalaya.ting.android.opensdk.model.column.ColumnDetail;
import com.ximalaya.ting.android.opensdk.model.column.ColumnDetailAlbum;
import com.ximalaya.ting.android.opensdk.model.column.ColumnDetailTrack;
import com.ximalaya.ting.android.opensdk.model.column.ColumnList;
import com.ximalaya.ting.android.opensdk.model.customized.ColumnItems;
import com.ximalaya.ting.android.opensdk.model.customized.CustomizedAlbumColumnDetail;
import com.ximalaya.ting.android.opensdk.model.customized.CustomizedAlbumList;
import com.ximalaya.ting.android.opensdk.model.customized.CustomizedSearchList;
import com.ximalaya.ting.android.opensdk.model.customized.CustomizedTrackColumnDetail;
import com.ximalaya.ting.android.opensdk.model.customized.CustomizedTrackList;
import com.ximalaya.ting.android.opensdk.model.download.RecommendDownload;
import com.ximalaya.ting.android.opensdk.model.history.PlayHistoryList;
import com.ximalaya.ting.android.opensdk.model.live.program.Program;
import com.ximalaya.ting.android.opensdk.model.live.program.ProgramList;
import com.ximalaya.ting.android.opensdk.model.live.provinces.Province;
import com.ximalaya.ting.android.opensdk.model.live.provinces.ProvinceList;
import com.ximalaya.ting.android.opensdk.model.live.radio.City;
import com.ximalaya.ting.android.opensdk.model.live.radio.CityList;
import com.ximalaya.ting.android.opensdk.model.live.radio.Radio;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioCategory;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioCategoryList;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioList;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioListByCategory;
import com.ximalaya.ting.android.opensdk.model.live.radio.RadioListById;
import com.ximalaya.ting.android.opensdk.model.live.schedule.Schedule;
import com.ximalaya.ting.android.opensdk.model.live.schedule.ScheduleList;
import com.ximalaya.ting.android.opensdk.model.metadata.MetaData;
import com.ximalaya.ting.android.opensdk.model.metadata.MetaDataList;
import com.ximalaya.ting.android.opensdk.model.pay.BoughtStatu;
import com.ximalaya.ting.android.opensdk.model.pay.BoughtStatuList;
import com.ximalaya.ting.android.opensdk.model.pay.ObfuscatePlayInfo;
import com.ximalaya.ting.android.opensdk.model.pay.ObfuscatePlayInfoList;
import com.ximalaya.ting.android.opensdk.model.pay.OrderDetail;
import com.ximalaya.ting.android.opensdk.model.pay.PayInfo;
import com.ximalaya.ting.android.opensdk.model.pay.PayOderStatue;
import com.ximalaya.ting.android.opensdk.model.ranks.Rank;
import com.ximalaya.ting.android.opensdk.model.ranks.RankAlbumList;
import com.ximalaya.ting.android.opensdk.model.ranks.RankList;
import com.ximalaya.ting.android.opensdk.model.ranks.RankTrackList;
import com.ximalaya.ting.android.opensdk.model.search.SearchAll;
import com.ximalaya.ting.android.opensdk.model.tag.Tag;
import com.ximalaya.ting.android.opensdk.model.tag.TagList;
import com.ximalaya.ting.android.opensdk.model.track.AnnouncerTrackList;
import com.ximalaya.ting.android.opensdk.model.track.BatchTrackList;
import com.ximalaya.ting.android.opensdk.model.track.CommonTrackList;
import com.ximalaya.ting.android.opensdk.model.track.LastPlayTrackList;
import com.ximalaya.ting.android.opensdk.model.track.SearchTrackList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackBaseInfo;
import com.ximalaya.ting.android.opensdk.model.track.TrackHotList;
import com.ximalaya.ting.android.opensdk.model.track.TrackIdList;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.model.user.XmBaseUserInfo;
import com.ximalaya.ting.android.opensdk.model.user.XmUserInfo;
import com.ximalaya.ting.android.opensdk.model.word.HotWord;
import com.ximalaya.ting.android.opensdk.model.word.HotWordList;
import com.ximalaya.ting.android.opensdk.model.word.SuggestWords;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.advertis.OpenSdkUtils;
import com.ximalaya.ting.android.opensdk.player.advertis.XmAdsEvent;
import com.ximalaya.ting.android.opensdk.player.advertis.XmAdsEvents;
import com.ximalaya.ting.android.opensdk.player.advertis.XmAdsRecord;
import com.ximalaya.ting.android.opensdk.player.advertis.shopAdsEvent.XmShopEvents;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.AsyncGson;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.EncryptUtilForSDK;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.NetworkType;
import com.ximalaya.ting.android.opensdk.util.PayUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonRequest {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1866a = "XiMaLaYaSDK";
    public static Handler b = new Handler(Looper.getMainLooper());
    public static ExecutorDelivery c = new ExecutorDelivery(b);
    private static CommonRequest g = null;
    private static int i = 20;
    private static IRequestCallBack<TrackBaseInfo> v = new IRequestCallBack<TrackBaseInfo>() {
        /* renamed from: a */
        public TrackBaseInfo b(String str) throws Exception {
            TrackBaseInfo trackBaseInfo = (TrackBaseInfo) new Gson().fromJson(str, TrackBaseInfo.class);
            XmSecretKeyUtil.a().a(trackBaseInfo, trackBaseInfo != null && trackBaseInfo.e());
            return trackBaseInfo;
        }
    };
    private static String w;
    public Set<String> d = new HashSet();
    public String e;
    private Context f = null;
    private String h;
    private String j = "";
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private Config r = null;
    private final Set<String> s = new HashSet<String>() {
        {
            add(CommonRequest.a(DTransferConstants.bd));
            add(CommonRequest.a(DTransferConstants.be));
            add(CommonRequest.a(DTransferConstants.dn));
        }
    };
    private ITokenStateChangeWrapper t;
    private boolean u = false;

    public interface IRequestCallBack<T> {
        T b(String str) throws Exception;
    }

    public interface ITokenStateChange {
        boolean a();

        boolean b();

        void c();
    }

    public static class UrlConstants {

        /* renamed from: a  reason: collision with root package name */
        public static String f1979a = "http://api.ximalaya.com/openapi-gateway-app/albums/browse";
        public static String b = "http://api.ximalaya.com/openapi-gateway-app/tracks/hot";
        public static String c = "http://api.ximalaya.com/openapi-gateway-app/search/tracks";
        public static String d = "http://api.ximalaya.com/openapi-gateway-app/tracks/get_batch";
    }

    public int s() {
        return 2;
    }

    private CommonRequest() {
    }

    public static String a(String str) {
        return str.substring(str.indexOf("/", ConnectionHelper.HTTP_PREFIX.length()), str.length());
    }

    public static CommonRequest a() {
        if (g == null) {
            synchronized (CommonRequest.class) {
                if (g == null) {
                    g = new CommonRequest();
                }
            }
        }
        return g;
    }

    public ITokenStateChange b() {
        return this.t;
    }

    public void a(ITokenStateChange iTokenStateChange) {
        if (iTokenStateChange != null) {
            this.t = new ITokenStateChangeWrapper(iTokenStateChange);
            if (BaseUtil.b(this.f)) {
                XmPlayerManager.a(this.f).a((ITokenStateChange) this.t);
                return;
            }
            return;
        }
        this.t = null;
    }

    public static class ITokenStateChangeWrapper implements ITokenStateChange {

        /* renamed from: a  reason: collision with root package name */
        private ITokenStateChange f1978a;
        private long b;

        public ITokenStateChangeWrapper(ITokenStateChange iTokenStateChange) {
            this.f1978a = iTokenStateChange;
        }

        public boolean a() {
            if (this.f1978a != null) {
                return this.f1978a.a();
            }
            return false;
        }

        public boolean b() {
            if (this.f1978a != null) {
                return this.f1978a.b();
            }
            return false;
        }

        public void c() {
            if (System.currentTimeMillis() - this.b > 1000) {
                this.b = System.currentTimeMillis();
                if (this.f1978a != null) {
                    this.f1978a.c();
                }
            }
        }
    }

    public void a(Context context, String str) {
        this.f = context.getApplicationContext();
        this.h = str;
        AccessTokenManager a2 = AccessTokenManager.a();
        a2.a((ITokenStateChange) this.t);
        a2.b(context);
        if (XmPlayerConfig.a(context).j()) {
            BaseCall.a().a(HttpDNSUtilForOpenSDK.a());
        }
        EncryptUtilForSDK.b().a(this.f);
        XmSecretKeyUtil.a().a(this.f);
    }

    public Context c() {
        return this.f;
    }

    public void a(int i2) {
        Logger.a(i2);
    }

    public void b(String str) {
        this.j = str;
    }

    public void c(String str) {
        this.k = str;
    }

    public static Handler d() {
        return b;
    }

    public String e() {
        return this.h;
    }

    private Context x() throws XimalayaException {
        if (this.f != null) {
            return this.f.getApplicationContext();
        }
        throw XimalayaException.getExceptionByCode(1004);
    }

    public String f() throws XimalayaException {
        if (TextUtils.isEmpty(this.j)) {
            try {
                this.j = x().getPackageManager().getApplicationInfo(x().getPackageName(), 128).metaData.getString("app_key");
            } catch (XimalayaException e2) {
                throw e2;
            } catch (Exception e3) {
                Logger.a(e3);
                throw XimalayaException.getExceptionByCode(1005);
            }
        }
        if (!TextUtils.isEmpty(this.j)) {
            return this.j;
        }
        throw XimalayaException.getExceptionByCode(1005);
    }

    @SuppressLint({"WifiManagerLeak"})
    public String g() throws XimalayaException {
        if (TextUtils.isEmpty(this.m)) {
            this.m = ((WifiManager) x().getSystemService("wifi")).getConnectionInfo().getMacAddress();
        }
        if (!TextUtils.isEmpty(this.m)) {
            return this.m;
        }
        throw new XimalayaException(1006, "get mac address error");
    }

    public String h() {
        if (!TextUtils.isEmpty(this.e)) {
            return this.e;
        }
        try {
            String str = Build.MANUFACTURER;
            if (!TextUtils.isEmpty(str)) {
                this.e = URLEncoder.encode(str, "utf-8");
            }
        } catch (Exception unused) {
        }
        return this.e;
    }

    public void a(Config config) {
        this.r = config;
        BaseCall.a().a(this.r);
        if (this.f != null) {
            String a2 = BaseUtil.a(this.f);
            if (!TextUtils.isEmpty(a2) && !a2.contains(":player")) {
                XmPlayerManager.a(this.f).a(this.r);
            }
        }
    }

    public void b(Config config) {
        this.r = config;
    }

    public void a(boolean z) {
        this.u = z;
    }

    public boolean i() {
        return this.u;
    }

    public static String d(String str) {
        if (str == null || str.startsWith("https") || !str.startsWith("http") || BaseUtil.d() || !a().i()) {
            return str;
        }
        for (String contains : a().s) {
            if (str.contains(contains)) {
                return str;
            }
        }
        if (a().d == null) {
            return str;
        }
        for (String contains2 : a().d) {
            if (str.contains(contains2)) {
                return str;
            }
        }
        return str.replace("http", "https");
    }

    public Config j() {
        return this.r;
    }

    public String k() throws XimalayaException {
        if (TextUtils.isEmpty(this.l)) {
            this.l = Settings.Secure.getString(x().getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        }
        if (TextUtils.isEmpty(this.l)) {
            this.l = UUID.randomUUID().toString();
        }
        return this.l;
    }

    public String l() throws XimalayaException {
        if (this.k.equals("")) {
            try {
                this.k = x().getPackageManager().getApplicationInfo(x().getPackageName(), 128).metaData.getString("pack_id");
            } catch (PackageManager.NameNotFoundException unused) {
                throw new XimalayaException(600, "get packid error");
            }
        }
        return this.k;
    }

    public String m() {
        if (this.n.equals("")) {
            this.n = this.f.getPackageName();
        }
        return this.n;
    }

    public String n() {
        if (TextUtils.isEmpty(this.o)) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) this.f.getSystemService("phone");
                if (telephonyManager.getSimState() == 5) {
                    this.o = telephonyManager.getSimOperatorName();
                } else {
                    this.o = "未知";
                }
            } catch (Exception unused) {
            }
        }
        return this.o;
    }

    public String o() {
        this.p = NetworkType.a(this.f).getName();
        return this.p;
    }

    public String p() {
        return this.q;
    }

    public void b(int i2) {
        if (i != i2) {
            i = i2;
            XmPlayerManager.a(this.f).b(i2);
        }
    }

    public int q() {
        return i;
    }

    public String r() {
        return DTransferConstants.c;
    }

    public Map<String, String> t() throws XimalayaException {
        HashMap hashMap = new HashMap();
        hashMap.put("app_key", a().f());
        hashMap.put("device_id", k());
        hashMap.put("pack_id", a().l());
        hashMap.put(DTransferConstants.l, a().r());
        hashMap.put("client_os_type", String.valueOf(a().s()));
        return hashMap;
    }

    public static void u() {
        v();
    }

    public static void v() {
        if (!BaseUtil.d()) {
            EncryptUtilForSDK.a();
        }
    }

    public static Map<String, String> a(Map<String, String> map) throws XimalayaException {
        HashMap hashMap = new HashMap();
        hashMap.putAll(a().t());
        if (map != null) {
            hashMap.putAll(map);
        }
        hashMap.put("access_token", AccessTokenManager.a().d());
        String e2 = AccessTokenManager.a().e();
        if (!TextUtils.isEmpty(e2) && !AccessTokenManager.i()) {
            hashMap.put("uid", e2);
        }
        return hashMap;
    }

    public static <T> T a(String str, Map<String, String> map, IRequestCallBack<T> iRequestCallBack) throws Exception {
        try {
            try {
                String a2 = AccessTokenBaseCall.a(BaseBuilder.a(d(str), a(map), a().e()).build(), map, a().e(), str);
                if (iRequestCallBack != null) {
                    return iRequestCallBack.b(a2);
                }
                return null;
            } catch (Exception e2) {
                throw e2;
            } catch (XimalayaException e3) {
                throw e3;
            }
        } catch (XimalayaException e4) {
            throw e4;
        }
    }

    public static <T extends XimalayaResponse> void a(String str, Map<String, String> map, final IDataCallBack<T> iDataCallBack, final IRequestCallBack<T> iRequestCallBack) {
        try {
            AccessTokenBaseCall.a(BaseBuilder.a(d(str), a(map), a().e()).build(), new IHttpCallBack() {
                public void a(Response response) {
                    try {
                        String c = new BaseResponse(response).c();
                        if (iRequestCallBack != null) {
                            CommonRequest.c.a(iDataCallBack, iRequestCallBack.b(c));
                        } else {
                            CommonRequest.c.a(iDataCallBack, null);
                        }
                    } catch (Exception e) {
                        if (!TextUtils.isEmpty(e.getMessage())) {
                            CommonRequest.c.a(1007, e.getMessage(), iDataCallBack);
                        } else {
                            CommonRequest.c.a(1007, "parse response json data error", iDataCallBack);
                        }
                    }
                }

                public void a(int i, String str) {
                    CommonRequest.c.a(i, str, iDataCallBack);
                }
            }, map, a().e(), str);
        } catch (XimalayaException e2) {
            iDataCallBack.a(e2.getErrorCode(), e2.getMessage());
        }
    }

    public static <T> T b(String str, Map<String, String> map, IRequestCallBack<T> iRequestCallBack) throws Exception {
        try {
            try {
                String a2 = AccessTokenBaseCall.a(BaseBuilder.b(d(str), a(map), a().e()).build(), map, a().e(), str);
                if (iRequestCallBack != null) {
                    return iRequestCallBack.b(a2);
                }
                return null;
            } catch (Exception e2) {
                throw e2;
            } catch (XimalayaException e3) {
                throw e3;
            }
        } catch (XimalayaException e4) {
            throw e4;
        }
    }

    public static <T> void b(String str, Map<String, String> map, final IDataCallBack<T> iDataCallBack, final IRequestCallBack<T> iRequestCallBack) {
        try {
            Request.Builder b2 = BaseBuilder.b(d(str), a(map), a().e());
            if (str.contains("client_place_order")) {
                b2.header("Accept-Encoding", "danding");
            }
            AccessTokenBaseCall.a(b2.build(), new IHttpCallBack() {
                public void a(Response response) {
                    try {
                        String c = new BaseResponse(response).c();
                        if (iRequestCallBack != null) {
                            CommonRequest.c.a(iDataCallBack, iRequestCallBack.b(c));
                        } else {
                            CommonRequest.c.a(iDataCallBack, null);
                        }
                    } catch (Exception e) {
                        if (!TextUtils.isEmpty(e.getMessage())) {
                            CommonRequest.c.a(1007, e.getMessage(), iDataCallBack);
                        } else {
                            CommonRequest.c.a(1007, "parse response json data error", iDataCallBack);
                        }
                    }
                }

                public void a(int i, String str) {
                    CommonRequest.c.a(i, str, iDataCallBack);
                }
            }, map, a().e(), str);
        } catch (XimalayaException e2) {
            iDataCallBack.a(e2.getErrorCode(), e2.getMessage());
        }
    }

    public static void a(Map<String, String> map, IDataCallBack<CategoryList> iDataCallBack) {
        a(DTransferConstants.bl, map, iDataCallBack, new IRequestCallBack<CategoryList>() {
            /* renamed from: a */
            public CategoryList b(String str) throws Exception {
                CategoryList categoryList = new CategoryList();
                categoryList.a((List) BaseResponse.a(new TypeToken<List<Category>>() {
                }.getType(), str));
                return categoryList;
            }
        });
    }

    public static void b(Map<String, String> map, IDataCallBack<CategoryList> iDataCallBack) {
        a(DTransferConstants.bm, map, iDataCallBack, new IRequestCallBack<CategoryList>() {
            /* renamed from: a */
            public CategoryList b(String str) throws Exception {
                CategoryList categoryList = new CategoryList();
                categoryList.a((List) BaseResponse.a(new TypeToken<List<Category>>() {
                }.getType(), str));
                return categoryList;
            }
        });
    }

    public static void c(Map<String, String> map, IDataCallBack<UpdateBatchList> iDataCallBack) {
        a(DTransferConstants.bo, map, iDataCallBack, new IRequestCallBack<UpdateBatchList>() {
            /* renamed from: a */
            public UpdateBatchList b(String str) throws Exception {
                UpdateBatchList updateBatchList = new UpdateBatchList();
                updateBatchList.a((List) BaseResponse.a(new TypeToken<List<UpdateBatch>>() {
                }.getType(), str));
                return updateBatchList;
            }
        });
    }

    public static void d(Map<String, String> map, IDataCallBack<SearchAlbumList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bp, map, iDataCallBack, new IRequestCallBack<SearchAlbumList>() {
            /* renamed from: a */
            public SearchAlbumList b(String str) throws Exception {
                return (SearchAlbumList) BaseResponse.a(new TypeToken<SearchAlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void e(Map<String, String> map, IDataCallBack<SearchTrackList> iDataCallBack) {
        a().e(map);
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, UrlConstants.c);
        hashMap.putAll(map);
        a(DTransferConstants.bq, map, iDataCallBack, new IRequestCallBack<SearchTrackList>() {
            /* renamed from: a */
            public SearchTrackList b(String str) throws Exception {
                SearchTrackList searchTrackList = (SearchTrackList) BaseResponse.a(new TypeToken<SearchTrackList>() {
                }.getType(), str);
                hashMap.put(DTransferConstants.P, String.valueOf(searchTrackList.f()));
                searchTrackList.a((Map<String, String>) hashMap);
                return searchTrackList;
            }
        });
    }

    public static void f(Map<String, String> map, IDataCallBack<SearchAll> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bt, map, iDataCallBack, new IRequestCallBack<SearchAll>() {
            /* renamed from: a */
            public SearchAll b(String str) throws Exception {
                return (SearchAll) BaseResponse.a(new TypeToken<SearchAll>() {
                }.getType(), str);
            }
        });
    }

    public static void g(Map<String, String> map, IDataCallBack<RadioList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.br, map, iDataCallBack, new IRequestCallBack<RadioList>() {
            /* renamed from: a */
            public RadioList b(String str) throws Exception {
                return (RadioList) BaseResponse.a(new TypeToken<RadioList>() {
                }.getType(), str);
            }
        });
    }

    public static void h(Map<String, String> map, IDataCallBack<ObfuscatePlayInfo> iDataCallBack) {
        a("https://mpay.ximalaya.com/openapi-payfacade-app/open_pay/get_obfuscated_play_info/" + System.currentTimeMillis(), map, iDataCallBack, new IRequestCallBack<ObfuscatePlayInfo>() {
            /* renamed from: a */
            public ObfuscatePlayInfo b(String str) throws Exception {
                return (ObfuscatePlayInfo) BaseResponse.a(new TypeToken<ObfuscatePlayInfo>() {
                }.getType(), str);
            }
        });
    }

    public static ObfuscatePlayInfo b(Map<String, String> map) {
        try {
            return (ObfuscatePlayInfo) a("https://mpay.ximalaya.com/openapi-payfacade-app/open_pay/get_obfuscated_play_info/" + System.currentTimeMillis(), map, new IRequestCallBack<ObfuscatePlayInfo>() {
                /* renamed from: a */
                public ObfuscatePlayInfo b(String str) throws Exception {
                    return (ObfuscatePlayInfo) BaseResponse.a(new TypeToken<ObfuscatePlayInfo>() {
                    }.getType(), str);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void i(Map<String, String> map, IDataCallBack<ObfuscatePlayInfoList> iDataCallBack) {
        a("https://mpay.ximalaya.com/openapi-payfacade-app/open_pay/batch_get_obfuscated_play_info/" + System.currentTimeMillis(), map, iDataCallBack, new IRequestCallBack<ObfuscatePlayInfoList>() {
            /* renamed from: a */
            public ObfuscatePlayInfoList b(String str) throws Exception {
                ObfuscatePlayInfoList obfuscatePlayInfoList = new ObfuscatePlayInfoList();
                obfuscatePlayInfoList.a((List) BaseResponse.a(new TypeToken<List<ObfuscatePlayInfo>>() {
                }.getType(), str));
                return obfuscatePlayInfoList;
            }
        });
    }

    public static void j(Map<String, String> map, IDataCallBack<AnnouncerList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bs, map, iDataCallBack, new IRequestCallBack<AnnouncerList>() {
            /* renamed from: a */
            public AnnouncerList b(String str) throws Exception {
                return (AnnouncerList) BaseResponse.a(new TypeToken<AnnouncerList>() {
                }.getType(), str);
            }
        });
    }

    public static void k(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.ch, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) BaseResponse.a(new TypeToken<AlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void l(Map<String, String> map, IDataCallBack<BatchAlbumList> iDataCallBack) {
        a(DTransferConstants.bu, map, iDataCallBack, new IRequestCallBack<BatchAlbumList>() {
            /* renamed from: a */
            public BatchAlbumList b(String str) throws Exception {
                BatchAlbumList batchAlbumList = new BatchAlbumList();
                batchAlbumList.a((List) BaseResponse.a(new TypeToken<List<Album>>() {
                }.getType(), str));
                return batchAlbumList;
            }
        });
    }

    public static void m(Map<String, String> map, IDataCallBack<BatchTrackList> iDataCallBack) {
        a(DTransferConstants.bw, map, iDataCallBack, new IRequestCallBack<BatchTrackList>() {
            /* renamed from: a */
            public BatchTrackList b(String str) throws Exception {
                return (BatchTrackList) BaseResponse.a(new TypeToken<BatchTrackList>() {
                }.getType(), str);
            }
        });
    }

    public static void n(Map<String, String> map, IDataCallBack<TrackHotList> iDataCallBack) {
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, UrlConstants.b);
        hashMap.putAll(map);
        a().e(map);
        a(DTransferConstants.by, map, iDataCallBack, new IRequestCallBack<TrackHotList>() {
            /* renamed from: a */
            public TrackHotList b(String str) throws Exception {
                TrackHotList trackHotList = (TrackHotList) BaseResponse.a(new TypeToken<TrackHotList>() {
                }.getType(), str);
                hashMap.put(DTransferConstants.P, String.valueOf(trackHotList.f()));
                trackHotList.a((Map<String, String>) hashMap);
                return trackHotList;
            }
        });
    }

    @Deprecated
    public static void o(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        q(map, iDataCallBack);
    }

    @Deprecated
    public static void p(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        map.put(DTransferConstants.Z, "1");
        q(map, iDataCallBack);
    }

    public static void q(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.cl, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) BaseResponse.a(new TypeToken<AlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void r(Map<String, String> map, IDataCallBack<LastPlayTrackList> iDataCallBack) {
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, UrlConstants.f1979a);
        hashMap.putAll(map);
        hashMap.remove("pid");
        hashMap.remove("track_id");
        a().e(map);
        a(DTransferConstants.bA, map, iDataCallBack, new IRequestCallBack<LastPlayTrackList>() {
            /* renamed from: a */
            public LastPlayTrackList b(String str) throws Exception {
                LastPlayTrackList lastPlayTrackList = (LastPlayTrackList) BaseResponse.a(new TypeToken<LastPlayTrackList>() {
                }.getType(), str);
                hashMap.put("page", String.valueOf(lastPlayTrackList.h()));
                hashMap.put(DTransferConstants.Q, String.valueOf(lastPlayTrackList.h() - 1));
                hashMap.put(DTransferConstants.P, String.valueOf(lastPlayTrackList.f()));
                lastPlayTrackList.a((Map<String, String>) hashMap);
                return lastPlayTrackList;
            }
        });
    }

    public static void s(Map<String, String> map, IDataCallBack<TrackList> iDataCallBack) {
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, UrlConstants.f1979a);
        hashMap.putAll(map);
        a().e(map);
        a(DTransferConstants.bn, map, iDataCallBack, new IRequestCallBack<TrackList>() {
            /* renamed from: a */
            public TrackList b(String str) throws Exception {
                TrackList trackList = (TrackList) BaseResponse.a(new TypeToken<TrackList>() {
                }.getType(), str);
                hashMap.put(DTransferConstants.P, String.valueOf(trackList.f()));
                trackList.a((Map<String, String>) hashMap);
                return trackList;
            }
        });
    }

    public static void t(Map<String, String> map, IDataCallBack<TrackList> iDataCallBack) {
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, UrlConstants.f1979a);
        hashMap.putAll(map);
        a().e(map);
        a(DTransferConstants.bD, map, iDataCallBack, new IRequestCallBack<TrackList>() {
            /* renamed from: a */
            public TrackList b(String str) throws Exception {
                TrackList trackList = (TrackList) BaseResponse.a(new TypeToken<TrackList>() {
                }.getType(), str);
                hashMap.put(DTransferConstants.P, String.valueOf(trackList.f()));
                trackList.a((Map<String, String>) hashMap);
                return trackList;
            }
        });
    }

    public static void u(Map<String, String> map, IDataCallBack<TrackIdList> iDataCallBack) {
        a("http://api.ximalaya.com/openapi-gateway-app/albums/get_all_track_ids", map, iDataCallBack, new IRequestCallBack<TrackIdList>() {
            /* renamed from: a */
            public TrackIdList b(String str) throws Exception {
                return (TrackIdList) BaseResponse.a(new TypeToken<TrackIdList>() {
                }.getType(), str);
            }
        });
    }

    public static void v(Map<String, String> map, IDataCallBack<HotWordList> iDataCallBack) {
        a(DTransferConstants.bE, map, iDataCallBack, new IRequestCallBack<HotWordList>() {
            /* renamed from: a */
            public HotWordList b(String str) throws Exception {
                HotWordList hotWordList = new HotWordList();
                hotWordList.a((List) BaseResponse.a(new TypeToken<List<HotWord>>() {
                }.getType(), str));
                return hotWordList;
            }
        });
    }

    public static void w(Map<String, String> map, IDataCallBack<SuggestWords> iDataCallBack) {
        a(DTransferConstants.bF, map, iDataCallBack, new IRequestCallBack<SuggestWords>() {
            /* renamed from: a */
            public SuggestWords b(String str) throws Exception {
                return (SuggestWords) BaseResponse.a(new TypeToken<SuggestWords>() {
                }.getType(), str);
            }
        });
    }

    public static void x(Map<String, String> map, IDataCallBack<DiscoveryBannerList> iDataCallBack) {
        a(DTransferConstants.bG, map, iDataCallBack, new IRequestCallBack<DiscoveryBannerList>() {
            /* renamed from: a */
            public DiscoveryBannerList b(String str) throws Exception {
                DiscoveryBannerList discoveryBannerList = new DiscoveryBannerList();
                discoveryBannerList.a((List) BaseResponse.a(new TypeToken<List<Banner>>() {
                }.getType(), str));
                return discoveryBannerList;
            }
        });
    }

    @Deprecated
    public static void y(Map<String, String> map, IDataCallBack<CategoryBannerList> iDataCallBack) {
        a(DTransferConstants.bH, map, iDataCallBack, new IRequestCallBack<CategoryBannerList>() {
            /* renamed from: a */
            public CategoryBannerList b(String str) throws Exception {
                CategoryBannerList categoryBannerList = new CategoryBannerList();
                categoryBannerList.a((List) BaseResponse.a(new TypeToken<List<Banner>>() {
                }.getType(), str));
                return categoryBannerList;
            }
        });
    }

    public static void z(Map<String, String> map, IDataCallBack<RankBannerList> iDataCallBack) {
        a(DTransferConstants.bI, map, iDataCallBack, new IRequestCallBack<RankBannerList>() {
            /* renamed from: a */
            public RankBannerList b(String str) throws Exception {
                RankBannerList rankBannerList = new RankBannerList();
                rankBannerList.a((List) BaseResponse.a(new TypeToken<List<Banner>>() {
                }.getType(), str));
                return rankBannerList;
            }
        });
    }

    public static void A(Map<String, String> map, IDataCallBack<GussLikeAlbumList> iDataCallBack) {
        map.put("device_type", "2");
        a(DTransferConstants.ce, map, iDataCallBack, new IRequestCallBack<GussLikeAlbumList>() {
            /* renamed from: a */
            public GussLikeAlbumList b(String str) throws Exception {
                GussLikeAlbumList gussLikeAlbumList = new GussLikeAlbumList();
                gussLikeAlbumList.a((List) BaseResponse.a(new TypeToken<List<Album>>() {
                }.getType(), str));
                return gussLikeAlbumList;
            }
        });
    }

    public static void B(Map<String, String> map, IDataCallBack<ColumnList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bK, map, iDataCallBack, new IRequestCallBack<ColumnList>() {
            /* renamed from: a */
            public ColumnList b(String str) throws Exception {
                return (ColumnList) BaseResponse.a(new TypeToken<ColumnList>() {
                }.getType(), str);
            }
        });
    }

    public static void C(Map<String, String> map, IDataCallBack<ColumnDetail> iDataCallBack) {
        a(DTransferConstants.bL, map, iDataCallBack, new IRequestCallBack<ColumnDetail>() {
            /* renamed from: a */
            public ColumnDetail b(String str) throws Exception {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Gson gson = new Gson();
                    if (jSONObject.optInt("column_content_type", 0) == 1) {
                        return (ColumnDetailAlbum) gson.fromJson(str, new TypeToken<ColumnDetailAlbum>() {
                        }.getType());
                    }
                    if (jSONObject.optInt("column_content_type", 0) == 2) {
                        return (ColumnDetailTrack) gson.fromJson(str, new TypeToken<ColumnDetailTrack>() {
                        }.getType());
                    }
                    throw new XimalayaException(1007, "parse response json data error");
                } catch (JSONException e) {
                    throw e;
                }
            }
        });
    }

    public static void D(Map<String, String> map, IDataCallBack<RankList> iDataCallBack) {
        a(DTransferConstants.bM, map, iDataCallBack, new IRequestCallBack<RankList>() {
            /* renamed from: a */
            public RankList b(String str) throws Exception {
                RankList rankList = new RankList();
                rankList.a((List) BaseResponse.a(new TypeToken<List<Rank>>() {
                }.getType(), str));
                return rankList;
            }
        });
    }

    public static void E(Map<String, String> map, IDataCallBack<RankAlbumList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bN, map, iDataCallBack, new IRequestCallBack<RankAlbumList>() {
            /* renamed from: a */
            public RankAlbumList b(String str) throws Exception {
                return (RankAlbumList) BaseResponse.a(new TypeToken<RankAlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void F(Map<String, String> map, IDataCallBack<RankTrackList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bO, map, iDataCallBack, new IRequestCallBack<RankTrackList>() {
            /* renamed from: a */
            public RankTrackList b(String str) throws Exception {
                return (RankTrackList) BaseResponse.a(new TypeToken<RankTrackList>() {
                }.getType(), str);
            }
        });
    }

    public static void G(Map<String, String> map, IDataCallBack<RadioList> iDataCallBack) {
        a(DTransferConstants.bP, map, iDataCallBack, new IRequestCallBack<RadioList>() {
            /* renamed from: a */
            public RadioList b(String str) throws Exception {
                RadioList radioList = new RadioList();
                radioList.a((List<Radio>) (List) BaseResponse.a(new TypeToken<List<Radio>>() {
                }.getType(), str));
                return radioList;
            }
        });
    }

    public static void H(Map<String, String> map, IDataCallBack<RelativeAlbums> iDataCallBack) {
        a(DTransferConstants.bR, map, iDataCallBack, new IRequestCallBack<RelativeAlbums>() {
            /* renamed from: a */
            public RelativeAlbums b(String str) throws Exception {
                List list = (List) BaseResponse.a(new TypeToken<List<Album>>() {
                }.getType(), str);
                RelativeAlbums relativeAlbums = new RelativeAlbums();
                relativeAlbums.a((List<Album>) list);
                if (list != null) {
                    relativeAlbums.c(1);
                    relativeAlbums.a(1);
                    relativeAlbums.b(list.size());
                }
                return relativeAlbums;
            }
        });
    }

    public static void I(Map<String, String> map, IDataCallBack<RelativeAlbums> iDataCallBack) {
        a(DTransferConstants.bQ, map, iDataCallBack, new IRequestCallBack<RelativeAlbums>() {
            /* renamed from: a */
            public RelativeAlbums b(String str) throws Exception {
                List list = (List) BaseResponse.a(new TypeToken<List<Album>>() {
                }.getType(), str);
                RelativeAlbums relativeAlbums = new RelativeAlbums();
                relativeAlbums.a((List<Album>) list);
                if (list != null) {
                    relativeAlbums.c(1);
                    relativeAlbums.a(1);
                    relativeAlbums.b(list.size());
                }
                return relativeAlbums;
            }
        });
    }

    public static void J(Map<String, String> map, IDataCallBack<TagList> iDataCallBack) {
        a(DTransferConstants.bT, map, iDataCallBack, new IRequestCallBack<TagList>() {
            /* renamed from: a */
            public TagList b(String str) throws Exception {
                TagList tagList = new TagList();
                tagList.a((List) BaseResponse.a(new TypeToken<List<Tag>>() {
                }.getType(), str));
                return tagList;
            }
        });
    }

    public static void K(Map<String, String> map, IDataCallBack<AnnouncerListByIds> iDataCallBack) {
        a(DTransferConstants.bx, map, iDataCallBack, new IRequestCallBack<AnnouncerListByIds>() {
            /* renamed from: a */
            public AnnouncerListByIds b(String str) throws Exception {
                AnnouncerListByIds announcerListByIds = new AnnouncerListByIds();
                announcerListByIds.a((List) BaseResponse.a(new TypeToken<List<Announcer>>() {
                }.getType(), str));
                return announcerListByIds;
            }
        });
    }

    public static void L(Map<String, String> map, IDataCallBack<HotAggregationList> iDataCallBack) {
        a(DTransferConstants.bU, map, iDataCallBack, new IRequestCallBack<HotAggregationList>() {
            /* renamed from: a */
            public HotAggregationList b(String str) throws Exception {
                HotAggregationList hotAggregationList = new HotAggregationList();
                hotAggregationList.a((List) BaseResponse.a(new TypeToken<List<HotAggregation>>() {
                }.getType(), str));
                return hotAggregationList;
            }
        });
    }

    public static void M(Map<String, String> map, IDataCallBack<RecommendDownload> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bV, map, iDataCallBack, new IRequestCallBack<RecommendDownload>() {
            /* renamed from: a */
            public RecommendDownload b(String str) throws Exception {
                return (RecommendDownload) BaseResponse.a(new TypeToken<RecommendDownload>() {
                }.getType(), str);
            }
        });
    }

    public static void N(Map<String, String> map, IDataCallBack<ProvinceList> iDataCallBack) {
        a(DTransferConstants.bW, map, iDataCallBack, new IRequestCallBack<ProvinceList>() {
            /* renamed from: a */
            public ProvinceList b(String str) throws Exception {
                ProvinceList provinceList = new ProvinceList();
                provinceList.a((List) BaseResponse.a(new TypeToken<List<Province>>() {
                }.getType(), str));
                return provinceList;
            }
        });
    }

    public static void O(Map<String, String> map, IDataCallBack<RadioList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.cr, map, iDataCallBack, new IRequestCallBack<RadioList>() {
            /* renamed from: a */
            public RadioList b(String str) throws Exception {
                return (RadioList) BaseResponse.a(new TypeToken<RadioList>() {
                }.getType(), str);
            }
        });
    }

    public static void P(Map<String, String> map, IDataCallBack<RadioList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.bX, map, iDataCallBack, new IRequestCallBack<RadioList>() {
            /* renamed from: a */
            public RadioList b(String str) throws Exception {
                return (RadioList) BaseResponse.a(new TypeToken<RadioList>() {
                }.getType(), str);
            }
        });
    }

    public static void Q(Map<String, String> map, IDataCallBack<ScheduleList> iDataCallBack) {
        a(DTransferConstants.bY, map, iDataCallBack, new IRequestCallBack<ScheduleList>() {
            /* renamed from: a */
            public ScheduleList b(String str) throws Exception {
                ScheduleList scheduleList = new ScheduleList();
                scheduleList.a((List) BaseResponse.a(new TypeToken<List<Schedule>>() {
                }.getType(), str));
                return scheduleList;
            }
        });
    }

    public static void R(Map<String, String> map, IDataCallBack<ProgramList> iDataCallBack) {
        a(DTransferConstants.ca, map, iDataCallBack, new IRequestCallBack<ProgramList>() {
            /* renamed from: a */
            public ProgramList b(String str) throws Exception {
                ProgramList programList = new ProgramList();
                ArrayList arrayList = new ArrayList();
                arrayList.add((Program) BaseResponse.a(new TypeToken<Program>() {
                }.getType(), str));
                programList.a(arrayList);
                return programList;
            }
        });
    }

    public static void S(Map<String, String> map, IDataCallBack<RecommendCollectAlbumList> iDataCallBack) {
        a(DTransferConstants.cb, map, iDataCallBack, new IRequestCallBack<RecommendCollectAlbumList>() {
            /* renamed from: a */
            public RecommendCollectAlbumList b(String str) throws Exception {
                RecommendCollectAlbumList recommendCollectAlbumList = new RecommendCollectAlbumList();
                recommendCollectAlbumList.a((List) BaseResponse.a(new TypeToken<List<Album>>() {
                }.getType(), str));
                return recommendCollectAlbumList;
            }
        });
    }

    public static void T(Map<String, String> map, IDataCallBack<RadioListById> iDataCallBack) {
        a(DTransferConstants.cd, map, iDataCallBack, new IRequestCallBack<RadioListById>() {
            /* renamed from: a */
            public RadioListById b(String str) throws Exception {
                return (RadioListById) BaseResponse.a(new TypeToken<RadioListById>() {
                }.getType(), str);
            }
        });
    }

    public static void U(Map<String, String> map, IDataCallBack<BannersContentList> iDataCallBack) {
        final String str = "";
        if (map.containsKey(DTransferConstants.U)) {
            str = map.get(DTransferConstants.U);
            if (!str.equalsIgnoreCase("6") && !str.equalsIgnoreCase("7")) {
                iDataCallBack.a(0, "please add param banner_content_type 6 or 7");
            }
        } else {
            iDataCallBack.a(0, "please add param banner_content_type");
        }
        a(DTransferConstants.cc, map, iDataCallBack, new IRequestCallBack<BannersContentList>() {
            /* renamed from: a */
            public BannersContentList b(String str) throws Exception {
                if (str.equalsIgnoreCase("6")) {
                    BannersContentList bannersContentList = new BannersContentList();
                    bannersContentList.b((List) BaseResponse.a(new TypeToken<List<Album>>() {
                    }.getType(), str));
                    return bannersContentList;
                } else if (str.equalsIgnoreCase("7")) {
                    BannersContentList bannersContentList2 = new BannersContentList();
                    bannersContentList2.a((List) BaseResponse.a(new TypeToken<List<Track>>() {
                    }.getType(), str));
                    return bannersContentList2;
                } else {
                    throw new XimalayaException(1007, "parse response json data error");
                }
            }
        });
    }

    public static void V(Map<String, String> map, final IDataCallBack<AppModel> iDataCallBack) {
        try {
            BaseCall.a().a(BaseBuilder.a(d(DTransferConstants.be), map).build(), (IHttpCallBack) new IHttpCallBack() {
                public void a(Response response) {
                    Exception e;
                    String str;
                    BaseResponse baseResponse = new BaseResponse(response);
                    Type type = new TypeToken<AppModel>() {
                    }.getType();
                    try {
                        str = baseResponse.c();
                        try {
                            CommonRequest.c.a(iDataCallBack, (AppModel) BaseResponse.a(type, str));
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } catch (Exception e3) {
                        Exception exc = e3;
                        str = "";
                        e = exc;
                        Logger.b("XIMALAYASDK", "response json str:" + str);
                        if (!TextUtils.isEmpty(e.getMessage())) {
                            CommonRequest.c.a(1007, e.getMessage(), iDataCallBack);
                        } else {
                            CommonRequest.c.a(1007, "parse response json data error", iDataCallBack);
                        }
                    }
                }

                public void a(int i, String str) {
                    CommonRequest.c.a(i, str, iDataCallBack);
                }
            });
        } catch (XimalayaException e2) {
            iDataCallBack.a(e2.getErrorCode(), e2.getMessage());
        }
    }

    public static void W(Map<String, String> map, IDataCallBack<TrackBaseInfo> iDataCallBack) {
        a(DTransferConstants.cX, map, iDataCallBack, v);
    }

    public static TrackBaseInfo c(Map<String, String> map) {
        try {
            return (TrackBaseInfo) a(DTransferConstants.cX, map, v);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void X(Map<String, String> map, IDataCallBack<SercretPubKey> iDataCallBack) {
        b(DTransferConstants.cY, map, iDataCallBack, new IRequestCallBack<SercretPubKey>() {
            /* renamed from: a */
            public SercretPubKey b(String str) throws Exception {
                SercretPubKey sercretPubKey = (SercretPubKey) new Gson().fromJson(str, SercretPubKey.class);
                if (sercretPubKey == null || !sercretPubKey.c()) {
                    return sercretPubKey;
                }
                return null;
            }
        });
    }

    public static SercretPubKey d(Map<String, String> map) {
        try {
            return (SercretPubKey) b(DTransferConstants.cY, map, new IRequestCallBack<SercretPubKey>() {
                /* renamed from: a */
                public SercretPubKey b(String str) throws Exception {
                    return (SercretPubKey) new Gson().fromJson(str, SercretPubKey.class);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void Y(Map<String, String> map, IDataCallBack<AnnouncerCategoryList> iDataCallBack) {
        a(DTransferConstants.cm, map, iDataCallBack, new IRequestCallBack<AnnouncerCategoryList>() {
            /* renamed from: a */
            public AnnouncerCategoryList b(String str) throws Exception {
                AnnouncerCategoryList announcerCategoryList = new AnnouncerCategoryList();
                announcerCategoryList.a((List) BaseResponse.a(new TypeToken<List<AnnouncerCategory>>() {
                }.getType(), str));
                return announcerCategoryList;
            }
        });
    }

    public static void Z(Map<String, String> map, IDataCallBack<AnnouncerList> iDataCallBack) {
        a().e(map);
        a(DTransferConstants.f1852cn, map, iDataCallBack, new IRequestCallBack<AnnouncerList>() {
            /* renamed from: a */
            public AnnouncerList b(String str) throws Exception {
                return (AnnouncerList) BaseResponse.a(new TypeToken<AnnouncerList>() {
                }.getType(), str);
            }
        });
    }

    public static void a(Map<String, String> map, final IDataCallBack<AdvertisList> iDataCallBack, int i2) {
        int i3;
        try {
            map.put("appid", AmapLoc.q);
            map.put("version", DTransferConstants.b);
            map.put("device", "android");
            String e2 = AccessTokenManager.a().e();
            if (TextUtils.isEmpty(e2)) {
                e2 = "-1";
            }
            map.put("xt", System.currentTimeMillis() + "");
            map.put("uid", e2);
            map.put(AuthorizeActivityBase.KEY_OPERATOR, OpenSdkUtils.e(a().c()) + "");
            map.put(LogCategory.CATEGORY_NETWORK, a().o());
            map.put("deviceId", a().k());
            map.put("appKey", a().f());
            if (XmPlayerService.getPlayerSrvice() != null) {
                XmPlayListControl.PlayMode playMode = XmPlayerService.getPlayerSrvice().getPlayMode();
                if (playMode != XmPlayListControl.PlayMode.PLAY_MODEL_LIST) {
                    if (playMode == XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE_LOOP) {
                        i3 = 1;
                    } else if (playMode == XmPlayListControl.PlayMode.PLAY_MODEL_RANDOM) {
                        i3 = 2;
                    } else if (playMode == XmPlayListControl.PlayMode.PLAY_MODEL_LIST_LOOP) {
                        i3 = 3;
                    } else if (playMode == XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE) {
                        i3 = 4;
                    }
                    map.put("playMode", i3 + "");
                }
                i3 = 0;
                map.put("playMode", i3 + "");
            }
            Request.Builder a2 = BaseBuilder.a(d(DTransferConstants.bd), map);
            a2.addHeader("User-Agent", w());
            try {
                a(a2, new StringBuilder());
                BaseCall.a().a(a2.build(), (IHttpCallBack) new IHttpCallBack() {
                    public void a(Response response) {
                        Exception e;
                        String str;
                        BaseResponse baseResponse = new BaseResponse(response);
                        Type type = new TypeToken<AdvertisList>() {
                        }.getType();
                        if (baseResponse.a() == 200) {
                            try {
                                str = baseResponse.c();
                                try {
                                    CommonRequest.c.a(iDataCallBack, (AdvertisList) BaseResponse.a(type, str));
                                } catch (Exception e2) {
                                    e = e2;
                                }
                            } catch (Exception e3) {
                                Exception exc = e3;
                                str = "";
                                e = exc;
                                Logger.b("XIMALAYASDK", "response json str:" + str);
                                if (!TextUtils.isEmpty(e.getMessage())) {
                                    CommonRequest.c.a(1007, e.getMessage(), iDataCallBack);
                                } else {
                                    CommonRequest.c.a(1007, "parse response json data error", iDataCallBack);
                                }
                            }
                        } else {
                            CommonRequest.c.a(baseResponse.a(), baseResponse.b(), iDataCallBack);
                        }
                    }

                    public void a(int i, String str) {
                        CommonRequest.c.a(i, str, iDataCallBack);
                    }
                }, i2);
            } catch (UnsupportedEncodingException e3) {
                iDataCallBack.a(1008, "UnsupportedEncodingException :" + e3.getMessage());
            } catch (XimalayaException e4) {
                iDataCallBack.a(e4.getErrorCode(), e4.getErrorMessage());
            } catch (Exception e5) {
                iDataCallBack.a(0, e5.getMessage());
            }
        } catch (XimalayaException e6) {
            iDataCallBack.a(e6.getErrorCode(), e6.getMessage());
        } catch (Exception e7) {
            iDataCallBack.a(0, e7.getMessage());
        }
    }

    private static void a(Request.Builder builder, StringBuilder sb) throws UnsupportedEncodingException, XimalayaException {
        String str;
        sb.append("1&_device");
        sb.append("=");
        sb.append(URLEncoder.encode("android&" + a().k() + a.b + DTransferConstants.b, "UTF-8"));
        sb.append(i.b);
        sb.append("impl");
        sb.append("=");
        sb.append(URLEncoder.encode(a().m(), "UTF-8"));
        sb.append(i.b);
        sb.append("XUM");
        sb.append("=");
        sb.append(URLEncoder.encode(a().g(), "UTF-8"));
        sb.append(i.b);
        sb.append("c-oper");
        sb.append("=");
        sb.append(URLEncoder.encode(a().n(), "UTF-8"));
        sb.append(i.b);
        sb.append("net-mode");
        sb.append("=");
        sb.append(URLEncoder.encode(a().o(), "UTF-8"));
        sb.append(i.b);
        sb.append("manufacture");
        sb.append("=");
        sb.append(URLEncoder.encode(a().h(), "UTF-8"));
        sb.append(i.b);
        try {
            new BASE64Encoder();
            str = BASE64Encoder.a(e(OpenSdkUtils.a(a().c())));
        } catch (Exception e2) {
            e2.printStackTrace();
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append("AID");
            sb.append("=");
            sb.append(str);
            sb.append(i.b);
        }
        sb.append("osversion");
        sb.append("=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append(i.b);
        String b2 = OpenSdkUtils.b(a().c());
        if (!TextUtils.isEmpty(b2)) {
            try {
                String hexString = Long.toHexString(Long.valueOf(b2).longValue());
                sb.append("XIM");
                sb.append("=");
                sb.append(hexString);
                sb.append(i.b);
            } catch (NumberFormatException unused) {
            }
        }
        sb.append("res");
        sb.append("=");
        sb.append(URLEncoder.encode(a().p(), "UTF-8"));
        builder.addHeader("Cookie", sb.toString());
    }

    public static void a(List<XmAdsRecord> list, final IDataCallBack iDataCallBack) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (XmAdsRecord a2 : list) {
                XmAdsEvent xmAdsEvent = new XmAdsEvent();
                xmAdsEvent.a("AD");
                xmAdsEvent.a(a2);
                xmAdsEvent.a(System.currentTimeMillis());
                arrayList.add(xmAdsEvent);
            }
            XmAdsEvents xmAdsEvents = new XmAdsEvents();
            xmAdsEvents.a(arrayList);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new AsyncGson().b(xmAdsEvents, new AsyncGson.IResult<String>() {
                    public void a(Exception exc) {
                    }

                    public void a(String str) {
                        if (!TextUtils.isEmpty(str)) {
                            CommonRequest.a(str, iDataCallBack);
                        }
                    }
                });
                return;
            }
            try {
                String json = new Gson().toJson((Object) xmAdsEvents);
                if (!TextUtils.isEmpty(json)) {
                    a(json, iDataCallBack);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(XmShopEvents xmShopEvents, final IDataCallBack iDataCallBack) {
        if (xmShopEvents != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new AsyncGson().b(xmShopEvents, new AsyncGson.IResult<String>() {
                    public void a(Exception exc) {
                    }

                    public void a(String str) {
                        if (!TextUtils.isEmpty(str)) {
                            CommonRequest.b(str, iDataCallBack);
                        }
                    }
                });
                return;
            }
            try {
                String json = new Gson().toJson((Object) xmShopEvents);
                if (!TextUtils.isEmpty(json)) {
                    b(json, iDataCallBack);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r2, final com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack r3) {
        /*
            java.lang.String r0 = "http://xdcs-collector.ximalaya.com/api/v1/adRealTime"
            okhttp3.Request$Builder r2 = com.ximalaya.ting.android.opensdk.httputil.BaseBuilder.a((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ XimalayaException -> 0x0017, UnsupportedEncodingException -> 0x0013 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ XimalayaException -> 0x0017, UnsupportedEncodingException -> 0x0013 }
            r0.<init>()     // Catch:{ XimalayaException -> 0x0017, UnsupportedEncodingException -> 0x0013 }
            a((okhttp3.Request.Builder) r2, (java.lang.StringBuilder) r0)     // Catch:{ XimalayaException -> 0x0017, UnsupportedEncodingException -> 0x0013 }
            okhttp3.Request r2 = r2.build()     // Catch:{ XimalayaException -> 0x0017, UnsupportedEncodingException -> 0x0013 }
            goto L_0x0018
        L_0x0013:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0017:
            r2 = 0
        L_0x0018:
            if (r2 == 0) goto L_0x0026
            com.ximalaya.ting.android.opensdk.httputil.BaseCall r0 = com.ximalaya.ting.android.opensdk.httputil.BaseCall.a()
            com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest$59 r1 = new com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest$59
            r1.<init>(r3)
            r0.a((okhttp3.Request) r2, (com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack) r1)
        L_0x0026:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest.a(java.lang.String, com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack):void");
    }

    public static void b(String str, final IDataCallBack iDataCallBack) {
        Request request;
        try {
            request = BaseBuilder.a(DTransferConstants.bg, str).build();
        } catch (XimalayaException unused) {
            request = null;
        }
        if (request != null) {
            BaseCall.a().a(request, (IHttpCallBack) new IHttpCallBack() {
                public void a(Response response) {
                    if (iDataCallBack != null) {
                        iDataCallBack.a(null);
                    }
                }

                public void a(int i, String str) {
                    if (iDataCallBack != null) {
                        iDataCallBack.a(i, str);
                    }
                }
            });
        }
    }

    public static byte[] e(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            bArr[i3] = (byte) ((((byte) (Character.digit(str.charAt(i2), 16) & 255)) << 4) | ((byte) (Character.digit(str.charAt(i2 + 1), 16) & 255)));
            i2 += 2;
        }
        return bArr;
    }

    public static void aa(Map<String, String> map, IDataCallBack<GenreList> iDataCallBack) {
        a(DTransferConstants.ci, map, iDataCallBack, new IRequestCallBack<GenreList>() {
            /* renamed from: a */
            public GenreList b(String str) throws Exception {
                GenreList genreList = new GenreList();
                genreList.a((List) BaseResponse.a(new TypeToken<List<String>>() {
                }.getType(), str));
                return genreList;
            }
        });
    }

    public static void ab(Map<String, String> map, IDataCallBack<SubGenreList> iDataCallBack) {
        a(DTransferConstants.cj, map, iDataCallBack, new IRequestCallBack<SubGenreList>() {
            /* renamed from: a */
            public SubGenreList b(String str) throws Exception {
                SubGenreList subGenreList = new SubGenreList();
                subGenreList.a((List) BaseResponse.a(new TypeToken<List<String>>() {
                }.getType(), str));
                return subGenreList;
            }
        });
    }

    public static void ac(Map<String, String> map, IDataCallBack<ColdBootTag> iDataCallBack) {
        a(DTransferConstants.co, map, iDataCallBack, new IRequestCallBack<ColdBootTag>() {
            /* renamed from: a */
            public ColdBootTag b(String str) throws Exception {
                return (ColdBootTag) BaseResponse.a(new TypeToken<ColdBootTag>() {
                }.getType(), str);
            }
        });
    }

    public static void ad(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        map.put("device_type", "2");
        b(DTransferConstants.cp, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void ae(Map<String, String> map, IDataCallBack<ColdBootDetail> iDataCallBack) {
        try {
            map.put("device_id", a().k());
            map.put("device_type", "2");
            a(DTransferConstants.cq, map, iDataCallBack, new IRequestCallBack<ColdBootDetail>() {
                /* renamed from: a */
                public ColdBootDetail b(String str) throws Exception {
                    return (ColdBootDetail) BaseResponse.a(new TypeToken<ColdBootDetail>() {
                    }.getType(), str);
                }
            });
        } catch (XimalayaException e2) {
            iDataCallBack.a(e2.getErrorCode(), e2.getMessage());
        }
    }

    public static void af(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cu, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void ag(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cw, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void ah(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cv, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void ai(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cx, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void aj(Map<String, String> map, IDataCallBack<CommonTrackList> iDataCallBack) {
        if (!CommonRequestForMain.a(map, iDataCallBack, "getTrackListM")) {
            HashMap hashMap = new HashMap();
            hashMap.putAll(map);
            hashMap.remove(DTransferConstants.P);
            hashMap.remove(DTransferConstants.Q);
            a((String) hashMap.remove(DTransferConstants.K), hashMap, iDataCallBack, new IRequestCallBack<CommonTrackList>() {
                /* renamed from: a */
                public CommonTrackList b(String str) throws Exception {
                    return (CommonTrackList) BaseResponse.a(new TypeToken<CommonTrackList<Track>>() {
                    }.getType(), str);
                }
            });
        }
    }

    public DataErrorCategory a(BaseResponse baseResponse) {
        try {
            return (DataErrorCategory) new Gson().fromJson(baseResponse.c(), DataErrorCategory.class);
        } catch (Exception unused) {
            return null;
        }
    }

    public void e(Map<String, String> map) {
        if (!map.containsKey("count")) {
            map.put("count", String.valueOf(q()));
        }
    }

    public static void ak(Map<String, String> map, IDataCallBack<DiscoveryRecommendAlbumsList> iDataCallBack) {
        a(DTransferConstants.cf, map, iDataCallBack, new IRequestCallBack<DiscoveryRecommendAlbumsList>() {
            /* renamed from: a */
            public DiscoveryRecommendAlbumsList b(String str) throws Exception {
                DiscoveryRecommendAlbumsList discoveryRecommendAlbumsList = new DiscoveryRecommendAlbumsList();
                discoveryRecommendAlbumsList.a((List) BaseResponse.a(new TypeToken<List<DiscoveryRecommendAlbums>>() {
                }.getType(), str));
                return discoveryRecommendAlbumsList;
            }
        });
    }

    public static void al(Map<String, String> map, IDataCallBack<CategoryRecommendAlbumsList> iDataCallBack) {
        a(DTransferConstants.cg, map, iDataCallBack, new IRequestCallBack<CategoryRecommendAlbumsList>() {
            /* renamed from: a */
            public CategoryRecommendAlbumsList b(String str) throws Exception {
                CategoryRecommendAlbumsList categoryRecommendAlbumsList = new CategoryRecommendAlbumsList();
                categoryRecommendAlbumsList.a((List) BaseResponse.a(new TypeToken<List<CategoryRecommendAlbums>>() {
                }.getType(), str));
                return categoryRecommendAlbumsList;
            }
        });
    }

    public static void a(Map<String, String> map, IDataCallBack<String> iDataCallBack, Track track) {
        if (BaseUtil.d()) {
            CommonRequestForMain.a(map, iDataCallBack, track);
        } else {
            PayUtil.a(map, iDataCallBack, track);
        }
    }

    public static String w() {
        if (BaseUtil.d()) {
            return CommonRequestForMain.b();
        }
        if (TextUtils.isEmpty(w)) {
            StringBuilder sb = new StringBuilder();
            sb.append("ting_");
            sb.append(a().r());
            sb.append(Operators.BRACKET_START_STR);
            try {
                sb.append(URLEncoder.encode(Build.MODEL, "utf-8"));
            } catch (UnsupportedEncodingException unused) {
            }
            sb.append(",");
            sb.append(com.alipay.android.phone.a.a.a.f813a + Build.VERSION.SDK_INT);
            sb.append(Operators.BRACKET_END_STR);
            w = sb.toString();
        }
        return w;
    }

    public static void am(Map<String, String> map, IDataCallBack<MetaDataList> iDataCallBack) {
        a(DTransferConstants.bB, map, iDataCallBack, new IRequestCallBack<MetaDataList>() {
            /* renamed from: a */
            public MetaDataList b(String str) throws Exception {
                MetaDataList metaDataList = new MetaDataList();
                metaDataList.a((List) BaseResponse.a(new TypeToken<List<MetaData>>() {
                }.getType(), str));
                return metaDataList;
            }
        });
    }

    public static void an(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a(DTransferConstants.bC, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) BaseResponse.a(new TypeToken<AlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void ao(Map<String, String> map, IDataCallBack<RadioCategoryList> iDataCallBack) {
        a(DTransferConstants.cA, map, iDataCallBack, new IRequestCallBack<RadioCategoryList>() {
            /* renamed from: a */
            public RadioCategoryList b(String str) throws Exception {
                RadioCategoryList radioCategoryList = new RadioCategoryList();
                radioCategoryList.a((List) BaseResponse.a(new TypeToken<List<RadioCategory>>() {
                }.getType(), str));
                return radioCategoryList;
            }
        });
    }

    public static void ap(Map<String, String> map, IDataCallBack<RadioListByCategory> iDataCallBack) {
        a(DTransferConstants.cB, map, iDataCallBack, new IRequestCallBack<RadioListByCategory>() {
            /* renamed from: a */
            public RadioListByCategory b(String str) throws Exception {
                return (RadioListByCategory) BaseResponse.a(new TypeToken<RadioListByCategory>() {
                }.getType(), str);
            }
        });
    }

    public static void aq(final Map<String, String> map, IDataCallBack<AnnouncerTrackList> iDataCallBack) {
        a(DTransferConstants.cC, map, iDataCallBack, new IRequestCallBack<AnnouncerTrackList>() {
            /* renamed from: a */
            public AnnouncerTrackList b(String str) throws Exception {
                AnnouncerTrackList announcerTrackList = (AnnouncerTrackList) BaseResponse.a(new TypeToken<AnnouncerTrackList>() {
                }.getType(), str);
                try {
                    map.put("page", String.valueOf(announcerTrackList.a()));
                    map.put(DTransferConstants.Q, String.valueOf(announcerTrackList.a() - 1));
                    map.put(DTransferConstants.P, String.valueOf(announcerTrackList.f()));
                    announcerTrackList.a((Map<String, String>) map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return announcerTrackList;
            }
        });
    }

    public static void ar(Map<String, String> map, IDataCallBack<CustomizedAlbumList> iDataCallBack) {
        a(DTransferConstants.cD, map, iDataCallBack, new IRequestCallBack<CustomizedAlbumList>() {
            /* renamed from: a */
            public CustomizedAlbumList b(String str) throws Exception {
                return (CustomizedAlbumList) BaseResponse.a(new TypeToken<CustomizedAlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void as(Map<String, String> map, IDataCallBack<CustomizedTrackList> iDataCallBack) {
        a(DTransferConstants.cE, map, iDataCallBack, new IRequestCallBack<CustomizedTrackList>() {
            /* renamed from: a */
            public CustomizedTrackList b(String str) throws Exception {
                return (CustomizedTrackList) BaseResponse.a(new TypeToken<CustomizedTrackList>() {
                }.getType(), str);
            }
        });
    }

    public static void at(Map<String, String> map, IDataCallBack<CustomizedAlbumColumnDetail> iDataCallBack) {
        a(DTransferConstants.cF, map, iDataCallBack, new IRequestCallBack<CustomizedAlbumColumnDetail>() {
            /* renamed from: a */
            public CustomizedAlbumColumnDetail b(String str) throws Exception {
                return (CustomizedAlbumColumnDetail) BaseResponse.a(new TypeToken<CustomizedAlbumColumnDetail>() {
                }.getType(), str);
            }
        });
    }

    public static void au(Map<String, String> map, IDataCallBack<CustomizedTrackColumnDetail> iDataCallBack) {
        a(DTransferConstants.cG, map, iDataCallBack, new IRequestCallBack<CustomizedTrackColumnDetail>() {
            /* renamed from: a */
            public CustomizedTrackColumnDetail b(String str) throws Exception {
                return (CustomizedTrackColumnDetail) BaseResponse.a(new TypeToken<CustomizedTrackColumnDetail>() {
                }.getType(), str);
            }
        });
    }

    public static void av(Map<String, String> map, IDataCallBack<CityList> iDataCallBack) {
        a(DTransferConstants.bZ, map, iDataCallBack, new IRequestCallBack<CityList>() {
            /* renamed from: a */
            public CityList b(String str) throws Exception {
                CityList cityList = new CityList();
                cityList.a((List) BaseResponse.a(new TypeToken<List<City>>() {
                }.getType(), str));
                return cityList;
            }
        });
    }

    public static void aw(Map<String, String> map, IDataCallBack<CustomizedTrackColumnDetail> iDataCallBack) {
        a(DTransferConstants.cH, map, iDataCallBack, new IRequestCallBack<CustomizedTrackColumnDetail>() {
            /* renamed from: a */
            public CustomizedTrackColumnDetail b(String str) throws Exception {
                return (CustomizedTrackColumnDetail) BaseResponse.a(new TypeToken<CustomizedTrackColumnDetail>() {
                }.getType(), str);
            }
        });
    }

    public static void ax(Map<String, String> map, IDataCallBack<PlayHistoryList> iDataCallBack) {
        a(DTransferConstants.cK, map, iDataCallBack, new IRequestCallBack<PlayHistoryList>() {
            /* renamed from: a */
            public PlayHistoryList b(String str) throws Exception {
                return new PlayHistoryList(str);
            }
        });
    }

    public static void ay(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cL, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void az(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cM, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void aA(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cN, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void aB(Map<String, String> map, IDataCallBack<SubscribeAlbumList> iDataCallBack) {
        a(DTransferConstants.cO, map, iDataCallBack, new IRequestCallBack<SubscribeAlbumList>() {
            /* renamed from: a */
            public SubscribeAlbumList b(String str) throws Exception {
                return (SubscribeAlbumList) BaseResponse.a(new TypeToken<SubscribeAlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void aC(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cP, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void aD(Map<String, String> map, IDataCallBack<PostResponse> iDataCallBack) {
        b(DTransferConstants.cQ, map, iDataCallBack, new IRequestCallBack<PostResponse>() {
            /* renamed from: a */
            public PostResponse b(String str) throws Exception {
                return (PostResponse) new Gson().fromJson(str, PostResponse.class);
            }
        });
    }

    public static void aE(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a(DTransferConstants.da, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) new Gson().fromJson(str, AlbumList.class);
            }
        });
    }

    public static void aF(Map<String, String> map, IDataCallBack<TagList> iDataCallBack) {
        a(DTransferConstants.db, map, iDataCallBack, new IRequestCallBack<TagList>() {
            /* renamed from: a */
            public TagList b(String str) throws Exception {
                TagList tagList = new TagList();
                tagList.a((List) BaseResponse.a(new TypeToken<List<Tag>>() {
                }.getType(), str));
                return tagList;
            }
        });
    }

    public static void aG(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a(DTransferConstants.dc, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) BaseResponse.a(new TypeToken<AlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void aH(Map<String, String> map, IDataCallBack<TrackList> iDataCallBack) {
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, DTransferConstants.dd);
        hashMap.putAll(map);
        a().e(map);
        a(DTransferConstants.dd, map, iDataCallBack, new IRequestCallBack<TrackList>() {
            /* renamed from: a */
            public TrackList b(String str) throws Exception {
                TrackList trackList = (TrackList) BaseResponse.a(new TypeToken<TrackList>() {
                }.getType(), str);
                hashMap.put(DTransferConstants.P, String.valueOf(trackList.f()));
                trackList.a((Map<String, String>) hashMap);
                return trackList;
            }
        });
    }

    public static void aI(Map<String, String> map, IDataCallBack<BatchAlbumList> iDataCallBack) {
        a(DTransferConstants.f1853de, map, iDataCallBack, new IRequestCallBack<BatchAlbumList>() {
            /* renamed from: a */
            public BatchAlbumList b(String str) throws Exception {
                Type type = new TypeToken<List<Album>>() {
                }.getType();
                Gson gson = new Gson();
                BatchAlbumList batchAlbumList = new BatchAlbumList();
                batchAlbumList.a((List) gson.fromJson(str, type));
                return batchAlbumList;
            }
        });
    }

    public static void aJ(Map<String, String> map, IDataCallBack<BatchTrackList> iDataCallBack) {
        a(DTransferConstants.df, map, iDataCallBack, new IRequestCallBack<BatchTrackList>() {
            /* renamed from: a */
            public BatchTrackList b(String str) throws Exception {
                BatchTrackList batchTrackList = new BatchTrackList();
                batchTrackList.a((List) BaseResponse.a(new TypeToken<List<Track>>() {
                }.getType(), str));
                return batchTrackList;
            }
        });
    }

    public static void aK(Map<String, String> map, IDataCallBack<RankList> iDataCallBack) {
        a(DTransferConstants.dg, map, iDataCallBack, new IRequestCallBack<RankList>() {
            /* renamed from: a */
            public RankList b(String str) throws Exception {
                RankList rankList = new RankList();
                rankList.a((List) BaseResponse.a(new TypeToken<List<Rank>>() {
                }.getType(), str));
                return rankList;
            }
        });
    }

    public static void aL(Map<String, String> map, IDataCallBack<RankAlbumList> iDataCallBack) {
        a(DTransferConstants.dh, map, iDataCallBack, new IRequestCallBack<RankAlbumList>() {
            /* renamed from: a */
            public RankAlbumList b(String str) throws Exception {
                return (RankAlbumList) BaseResponse.a(new TypeToken<RankAlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void aM(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a(DTransferConstants.dh, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) BaseResponse.a(new TypeToken<AlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void aN(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a(DTransferConstants.di, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                AlbumList albumList = new AlbumList();
                albumList.a((List<Album>) (List) BaseResponse.a(new TypeToken<List<Album>>() {
                }.getType(), str));
                return albumList;
            }
        });
    }

    public static void aO(Map<String, String> map, IDataCallBack<BoughtStatuList> iDataCallBack) {
        a(DTransferConstants.dj, map, iDataCallBack, new IRequestCallBack<BoughtStatuList>() {
            /* renamed from: a */
            public BoughtStatuList b(String str) throws Exception {
                BoughtStatuList boughtStatuList = new BoughtStatuList();
                boughtStatuList.a((List) BaseResponse.a(new TypeToken<List<BoughtStatu>>() {
                }.getType(), str));
                return boughtStatuList;
            }
        });
    }

    public static void aP(Map<String, String> map, IDataCallBack<BoughtStatuList> iDataCallBack) {
        a(DTransferConstants.f1854dk, map, iDataCallBack, new IRequestCallBack<BoughtStatuList>() {
            /* renamed from: a */
            public BoughtStatuList b(String str) throws Exception {
                BoughtStatuList boughtStatuList = new BoughtStatuList();
                boughtStatuList.a((List) BaseResponse.a(new TypeToken<List<BoughtStatu>>() {
                }.getType(), str));
                return boughtStatuList;
            }
        });
    }

    public static void aQ(Map<String, String> map, IDataCallBack<AlbumList> iDataCallBack) {
        a(DTransferConstants.dl, map, iDataCallBack, new IRequestCallBack<AlbumList>() {
            /* renamed from: a */
            public AlbumList b(String str) throws Exception {
                return (AlbumList) BaseResponse.a(new TypeToken<AlbumList>() {
                }.getType(), str);
            }
        });
    }

    public static void aR(Map<String, String> map, IDataCallBack<TrackList> iDataCallBack) {
        final HashMap hashMap = new HashMap();
        hashMap.put(DTransferConstants.K, DTransferConstants.dm);
        hashMap.putAll(map);
        a().e(map);
        a(DTransferConstants.dm, map, iDataCallBack, new IRequestCallBack<TrackList>() {
            /* renamed from: a */
            public TrackList b(String str) throws Exception {
                TrackList trackList = (TrackList) BaseResponse.a(new TypeToken<TrackList>() {
                }.getType(), str);
                hashMap.put(DTransferConstants.P, String.valueOf(trackList.f()));
                trackList.a((Map<String, String>) hashMap);
                return trackList;
            }
        });
    }

    public static void aS(Map<String, String> map, IDataCallBack<PayInfo> iDataCallBack) {
        a(DTransferConstants.dp, map, iDataCallBack, new IRequestCallBack<PayInfo>() {
            /* renamed from: a */
            public PayInfo b(String str) throws Exception {
                return (PayInfo) BaseResponse.a(new TypeToken<PayInfo>() {
                }.getType(), str);
            }
        });
    }

    public static void aT(Map<String, String> map, IDataCallBack<ColumnItems> iDataCallBack) {
        a(DTransferConstants.cI, map, iDataCallBack, new IRequestCallBack<ColumnItems>() {
            /* renamed from: a */
            public ColumnItems b(String str) throws Exception {
                return (ColumnItems) BaseResponse.a(new TypeToken<ColumnItems>() {
                }.getType(), str);
            }
        });
    }

    public static void aU(Map<String, String> map, IDataCallBack<CustomizedSearchList> iDataCallBack) {
        a(DTransferConstants.cJ, map, iDataCallBack, new IRequestCallBack<CustomizedSearchList>() {
            /* renamed from: a */
            public CustomizedSearchList b(String str) throws Exception {
                return (CustomizedSearchList) BaseResponse.a(new TypeToken<CustomizedSearchList>() {
                }.getType(), str);
            }
        });
    }

    public static void aV(Map<String, String> map, final IDataCallBack<String> iDataCallBack) {
        Request.Builder builder;
        map.put("timestamp", System.currentTimeMillis() + "");
        map.put("nonce", System.currentTimeMillis() + "");
        map.put("response_type", "token");
        try {
            map.put("client_id", a().f());
        } catch (XimalayaException e2) {
            e2.printStackTrace();
        }
        try {
            builder = BaseBuilder.b(d(DTransferConstants.cU), a(map), a().e());
        } catch (XimalayaException e3) {
            e3.printStackTrace();
            builder = null;
        }
        new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS).followRedirects(false).followSslRedirects(false).build().newCall(builder.build()).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code == 302) {
                    String str = response.headers().get("Location");
                    if (!TextUtils.isEmpty(str)) {
                        CommonRequest.c.a(iDataCallBack, str);
                        return;
                    }
                    return;
                }
                CommonRequest.c.a(code, response.body().string(), iDataCallBack);
            }

            public void onFailure(Call call, IOException iOException) {
                Logger.b(CommonRequest.f1866a, "authorize, request failed, error message = " + iOException.getMessage());
                if (iDataCallBack != null) {
                    String str = BaseCall.h;
                    if (ConstantsOpenSdk.b) {
                        str = iOException.getMessage();
                        if (TextUtils.isEmpty(str)) {
                            str = BaseCall.h;
                        }
                    }
                    CommonRequest.c.a(IptcDirectory.Z, str, iDataCallBack);
                }
            }
        });
    }

    public static String f(Map<String, String> map) throws IOException {
        Request.Builder builder;
        map.put("timestamp", System.currentTimeMillis() + "");
        map.put("nonce", System.currentTimeMillis() + "");
        map.put("response_type", "token");
        try {
            map.put("client_id", a().f());
        } catch (XimalayaException e2) {
            e2.printStackTrace();
        }
        try {
            builder = BaseBuilder.b(d(DTransferConstants.cU), a(map), a().e());
        } catch (XimalayaException e3) {
            e3.printStackTrace();
            builder = null;
        }
        Response execute = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS).followRedirects(false).followSslRedirects(false).build().newCall(builder.build()).execute();
        return execute.code() == 302 ? execute.headers().get("Location") : "";
    }

    public static void aW(Map<String, String> map, IDataCallBack<XmBaseUserInfo> iDataCallBack) {
        a(DTransferConstants.cV, map, iDataCallBack, new IRequestCallBack<XmBaseUserInfo>() {
            /* renamed from: a */
            public XmBaseUserInfo b(String str) throws Exception {
                return (XmBaseUserInfo) BaseResponse.a(new TypeToken<XmBaseUserInfo>() {
                }.getType(), str);
            }
        });
    }

    public static void aX(Map<String, String> map, IDataCallBack<XmUserInfo> iDataCallBack) {
        a(DTransferConstants.cW, map, iDataCallBack, new IRequestCallBack<XmUserInfo>() {
            /* renamed from: a */
            public XmUserInfo b(String str) throws Exception {
                return (XmUserInfo) BaseResponse.a(new TypeToken<XmUserInfo>() {
                }.getType(), str);
            }
        });
    }

    public static void aY(Map<String, String> map, IDataCallBack<XmAlbumTracksStatue> iDataCallBack) {
        a("http://api.ximalaya.com/openapi-gateway-app/albums/get_all_track_ids", map, iDataCallBack, new IRequestCallBack<XmAlbumTracksStatue>() {
            /* renamed from: a */
            public XmAlbumTracksStatue b(String str) throws Exception {
                return (XmAlbumTracksStatue) BaseResponse.a(new TypeToken<XmAlbumTracksStatue>() {
                }.getType(), str);
            }
        });
    }

    public static void aZ(Map<String, String> map, IDataCallBack<PayOderStatue> iDataCallBack) {
        map.put("confirm_type", "2");
        b(DTransferConstants.ds, map, iDataCallBack, new IRequestCallBack<PayOderStatue>() {
            /* renamed from: a */
            public PayOderStatue b(String str) throws Exception {
                return (PayOderStatue) BaseResponse.a(new TypeToken<PayOderStatue>() {
                }.getType(), str);
            }
        });
    }

    public static void ba(Map<String, String> map, IDataCallBack<OrderDetail> iDataCallBack) {
        b(DTransferConstants.dt, map, iDataCallBack, new IRequestCallBack<OrderDetail>() {
            /* renamed from: a */
            public OrderDetail b(String str) throws Exception {
                return (OrderDetail) BaseResponse.a(new TypeToken<OrderDetail>() {
                }.getType(), str);
            }
        });
    }

    public static void bb(Map<String, String> map, IDataCallBack<BannerV2List> iDataCallBack) {
        a(DTransferConstants.ct, map, iDataCallBack, new IRequestCallBack<BannerV2List>() {
            /* renamed from: a */
            public BannerV2List b(String str) throws Exception {
                BannerV2List bannerV2List = new BannerV2List();
                bannerV2List.a((List) BaseResponse.a(new TypeToken<List<BannerV2>>() {
                }.getType(), str));
                return bannerV2List;
            }
        });
    }
}
