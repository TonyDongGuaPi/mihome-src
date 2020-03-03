package com.xiaomi.smarthome.homeroom;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.UserInfoManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.HomeInviteInfo;
import com.xiaomi.smarthome.homeroom.model.HomeMember;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomeMemberManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18033a = 1;
    public static final int b = 2;
    /* access modifiers changed from: private */
    public static final String c = "HomeMemberManager";
    private static HomeMemberManager d;
    /* access modifiers changed from: private */
    public Map<String, LongSparseArray<HomeMember>> e = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, List<Long>> f = new HashMap();
    /* access modifiers changed from: private */
    public SparseArray<List<HomeInviteInfo>> g = new SparseArray<>();
    /* access modifiers changed from: private */
    public SparseArray<List<HomeInviteInfo>> h = new SparseArray<>();

    public static HomeMemberManager a() {
        if (d == null) {
            synchronized (HomeMemberManager.class) {
                if (d == null) {
                    d = new HomeMemberManager();
                }
            }
        }
        return d;
    }

    private HomeMemberManager() {
    }

    public LongSparseArray<HomeMember> a(String str) {
        return this.e.get(str);
    }

    public List<Long> b(String str) {
        List<Long> list = this.f.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.f.put(str, arrayList);
        return arrayList;
    }

    public SparseArray<List<HomeInviteInfo>> b() {
        return this.g;
    }

    public SparseArray<List<HomeInviteInfo>> c() {
        return this.h;
    }

    public List<HomeInviteInfo> a(int i) {
        List<HomeInviteInfo> list = this.g.get(i);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.g.put(i, arrayList);
        return arrayList;
    }

    public List<HomeInviteInfo> b(int i) {
        List<HomeInviteInfo> list = this.h.get(i);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.h.put(i, arrayList);
        return arrayList;
    }

    public void a(final Home home, final AsyncCallback<LongSparseArray<HomeMember>, Error> asyncCallback) {
        LogUtil.a(c, "startSyncHomeMemberFromServer ");
        if (SHApplication.getStateNotifier().a() == 4 && home != null) {
            Observable.create(new ObservableOnSubscribe<LongSparseArray<HomeMember>>() {
                public void subscribe(final ObservableEmitter<LongSparseArray<HomeMember>> observableEmitter) throws Exception {
                    RemoteFamilyApi.a().b(home, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (!observableEmitter.isDisposed()) {
                                if (jSONObject == null || jSONObject.optJSONArray("list").length() == 0) {
                                    observableEmitter.onComplete();
                                    return;
                                }
                                LongSparseArray longSparseArray = new LongSparseArray();
                                List list = (List) HomeMemberManager.this.f.get(home.j());
                                if (list == null) {
                                    list = new ArrayList();
                                    HomeMemberManager.this.f.put(home.j(), list);
                                }
                                list.clear();
                                JSONArray optJSONArray = jSONObject.optJSONArray("list");
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    HomeMember a2 = HomeMember.a(optJSONArray.optJSONObject(i));
                                    longSparseArray.put(a2.a(), a2);
                                    list.add(Long.valueOf(a2.a()));
                                }
                                observableEmitter.onNext(longSparseArray);
                                observableEmitter.onComplete();
                            }
                        }

                        public void onFailure(Error error) {
                            observableEmitter.onComplete();
                        }
                    });
                }
            }).flatMap(new Function<LongSparseArray<HomeMember>, ObservableSource<LongSparseArray<HomeMember>>>() {
                /* renamed from: a */
                public ObservableSource<LongSparseArray<HomeMember>> apply(final LongSparseArray<HomeMember> longSparseArray) throws Exception {
                    return Observable.create(new ObservableOnSubscribe<LongSparseArray<HomeMember>>() {
                        public void subscribe(final ObservableEmitter<LongSparseArray<HomeMember>> observableEmitter) throws Exception {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < longSparseArray.size(); i++) {
                                arrayList.add(Long.valueOf(longSparseArray.keyAt(i)));
                            }
                            RemoteFamilyApi.a().a(SHApplication.getAppContext(), (List<Long>) arrayList, (AsyncCallback<List<UserInfo>, Error>) new AsyncCallback<List<UserInfo>, Error>() {
                                /* renamed from: a */
                                public void onSuccess(List<UserInfo> list) {
                                    int i = 0;
                                    while (i < list.size()) {
                                        try {
                                            UserInfo userInfo = list.get(i);
                                            HomeMember homeMember = (HomeMember) longSparseArray.get(Long.parseLong(userInfo.f16462a));
                                            if (homeMember != null) {
                                                homeMember.a(userInfo);
                                            }
                                            longSparseArray.put(Long.parseLong(userInfo.f16462a), homeMember);
                                            i++;
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (longSparseArray.size() > 0) {
                                        observableEmitter.onNext(longSparseArray);
                                    }
                                    observableEmitter.onComplete();
                                }

                                public void onFailure(Error error) {
                                    observableEmitter.onComplete();
                                }
                            });
                        }
                    });
                }
            }).subscribe(new Observer<LongSparseArray<HomeMember>>() {

                /* renamed from: a  reason: collision with root package name */
                boolean f18034a = false;

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                    LogUtil.a(HomeMemberManager.c, "onSubscribe: ");
                }

                /* renamed from: a */
                public void onNext(LongSparseArray<HomeMember> longSparseArray) {
                    LogUtil.a(HomeMemberManager.c, "onNext: ");
                    this.f18034a = true;
                    HomeMemberManager.this.e.put(home.j(), longSparseArray);
                }

                public void onComplete() {
                    LogUtil.a(HomeMemberManager.c, "onComplete: ");
                    if (asyncCallback != null) {
                        LongSparseArray longSparseArray = (LongSparseArray) HomeMemberManager.this.e.get(home.j());
                        if (!this.f18034a || longSparseArray == null || longSparseArray.size() <= 0) {
                            asyncCallback.onFailure(new Error(-1, "get homemember err!"));
                        } else {
                            asyncCallback.onSuccess(longSparseArray);
                        }
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "get homemember err!"));
        }
    }

    public void a(UserInfo userInfo, String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        LogUtil.a(c, "homeMemberInvite");
        if (userInfo != null) {
            long parseLong = Long.parseLong(userInfo.f16462a);
            final String str2 = str;
            final long j = parseLong;
            final UserInfo userInfo2 = userInfo;
            final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
            RemoteFamilyApi.a().a(parseLong, str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    LongSparseArray<HomeMember> a2 = HomeMemberManager.a().a(str2);
                    if (a2 == null) {
                        a2 = new LongSparseArray<>();
                    }
                    HomeMember homeMember = new HomeMember();
                    homeMember.a(j);
                    homeMember.a(0);
                    homeMember.a(userInfo2);
                    a2.put(j, homeMember);
                    if (asyncCallback2 != null) {
                        asyncCallback2.onSuccess(jSONObject);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(error);
                    }
                    LogUtil.a(HomeMemberManager.c, error.toString());
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "userinfo == null"));
        }
    }

    public void a(List<Long> list, final String str, final AsyncCallback<Void, Error> asyncCallback) {
        LogUtil.a(c, "homeMemberInviteBatch");
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str) && HomeManager.a().j(str) != null) {
            RemoteFamilyApi.a().a(list, str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    HomeMemberManager.this.a(HomeManager.a().j(str), (AsyncCallback<LongSparseArray<HomeMember>, Error>) new AsyncCallback<LongSparseArray<HomeMember>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(LongSparseArray<HomeMember> longSparseArray) {
                            if (asyncCallback != null) {
                                asyncCallback.onSuccess(null);
                            }
                        }

                        public void onFailure(Error error) {
                            if (asyncCallback != null) {
                                asyncCallback.onFailure(null);
                            }
                        }
                    });
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                    LogUtil.a(HomeMemberManager.c, error.toString());
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "params err!"));
        }
    }

    public void a(long j, Home home, AsyncCallback<JSONObject, Error> asyncCallback) {
        LogUtil.a(c, "homeMemberInviteRemove");
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(j));
            final Home home2 = home;
            final long j2 = j;
            final AsyncCallback<JSONObject, Error> asyncCallback2 = asyncCallback;
            RemoteFamilyApi.a().a((List<Long>) arrayList, home.o(), Long.parseLong(home.j()), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    LongSparseArray<HomeMember> a2 = HomeMemberManager.a().a(home2.j());
                    if (a2 != null) {
                        a2.remove(j2);
                    }
                    if (!home2.p()) {
                        List<Home> f = HomeManager.a().f();
                        if (f != null) {
                            f.remove(home2);
                        }
                        HomeManager.a().b();
                    }
                    if (asyncCallback2 != null) {
                        asyncCallback2.onSuccess(jSONObject);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback2 != null) {
                        asyncCallback2.onFailure(error);
                    }
                    LogUtil.a(HomeMemberManager.c, error.toString());
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(final AsyncCallback<Void, Error> asyncCallback) {
        LogUtil.a(c, "homeMemberInviteHistory");
        RemoteFamilyApi.a().a((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                HomeMemberManager.this.g.clear();
                HomeMemberManager.this.h.clear();
                if (jSONObject != null) {
                    HashSet hashSet = new HashSet();
                    if (!jSONObject.isNull("received")) {
                        SparseArray unused = HomeMemberManager.this.g = HomeInviteInfo.a(jSONObject.optJSONArray("received"), hashSet);
                    }
                    if (!jSONObject.isNull("sended")) {
                        SparseArray unused2 = HomeMemberManager.this.h = HomeInviteInfo.a(jSONObject.optJSONArray("sended"), hashSet);
                    }
                    UserInfoManager.a().a(hashSet, new UserInfoManager.UpdateCompleteCallBack() {
                        public void a() {
                            if (asyncCallback != null) {
                                asyncCallback.onSuccess(null);
                            }
                        }
                    });
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, "InviteHistory == null"));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
                LogUtil.a(HomeMemberManager.c, error.toString());
            }
        });
    }

    public void a(final HomeInviteInfo homeInviteInfo, final int i, final AsyncCallback<JSONObject, Error> asyncCallback) {
        LogUtil.a(c, "homeMemberInviteResponse");
        if (homeInviteInfo != null) {
            RemoteFamilyApi.a().a(homeInviteInfo.e(), homeInviteInfo.g(), i, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    List<HomeInviteInfo> a2 = HomeMemberManager.this.a(homeInviteInfo.i());
                    if (a2 != null) {
                        a2.remove(homeInviteInfo);
                    }
                    int i = i == 1 ? HomeInviteInfo.b : HomeInviteInfo.c;
                    homeInviteInfo.a(i);
                    HomeMemberManager.this.a(i).add(homeInviteInfo);
                    if (i == 1) {
                        HomeManager.a().b();
                    }
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(jSONObject);
                    }
                }

                public void onFailure(Error error) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(error);
                    }
                    LogUtil.a(HomeMemberManager.c, error.toString());
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(-1, "homeInviteInfo == null"));
        }
    }

    public void a(String str, final AsyncCallback<String, Error> asyncCallback) {
        RemoteFamilyApi.a().a(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                String optString = jSONObject.optString(ApiConst.l);
                if (!TextUtils.isEmpty(optString)) {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(optString);
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.onFailure(new Error(-1, SHApplication.getAppContext().getString(R.string.share_wx_error)));
                }
            }

            public void onFailure(Error error) {
                if (error != null) {
                    int a2 = error.a();
                    if (!(a2 == -4001004 || a2 == -6)) {
                        switch (a2) {
                            case -12:
                            case -11:
                                break;
                        }
                    }
                    error = new Error(-1, SHApplication.getAppContext().getString(R.string.share_wx_error));
                }
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    public void a(String str, String str2) {
        Home j = HomeManager.a().j(str2);
        if (j == null) {
            ToastUtil.a((int) R.string.share_wx_error);
            return;
        }
        int size = j.d().size();
        int size2 = j.m().size();
        String k = j.k();
        String str3 = "";
        String str4 = "";
        try {
            ShareUserRecord b2 = UserMamanger.a().b();
            if (b2 != null) {
                String str5 = b2.nickName;
                try {
                    str3 = b2.url;
                } catch (Exception unused) {
                }
                str4 = str5;
            }
        } catch (Exception unused2) {
        }
        IWXAPI iwxapi = SHApplication.getIWXAPI();
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.miniprogramType = 0;
        wXMiniProgramObject.webpageUrl = AppConstants.K;
        wXMiniProgramObject.userName = AppConstants.H;
        wXMiniProgramObject.path = AppConstants.J + "?type=" + "share_home" + "&roomCount=" + size + "&deviceCount=" + size2 + "&userName=" + str4 + "&avatar=" + str3 + "&homeName=" + k + "&shareKey=" + str;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
        wXMediaMessage.title = SHApplication.getAppContext().getString(R.string.home_share_wx_title);
        wXMediaMessage.description = "";
        wXMediaMessage.setThumbImage(BitmapFactory.decodeResource(SHApplication.getAppContext().getResources(), R.drawable.accept_home_defualt));
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = 0;
        iwxapi.sendReq(req);
    }

    public void b(String str, final AsyncCallback<JSONObject, Error> asyncCallback) {
        RemoteFamilyApi.a().b(str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                HomeManager.a().b();
                SHApplication.getGlobalHandler().postDelayed(new Runnable(jSONObject) {
                    private final /* synthetic */ JSONObject f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        HomeMemberManager.AnonymousClass10.a(AsyncCallback.this, this.f$1);
                    }
                }, 1000);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ void a(AsyncCallback asyncCallback, JSONObject jSONObject) {
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(jSONObject);
                }
            }

            public void onFailure(Error error) {
                String str;
                if (error != null) {
                    switch (error.a()) {
                        case -4001005:
                        case -4001004:
                        case -12:
                        case -6:
                            str = SHApplication.getAppContext().getString(R.string.accept_wx_share_error_known);
                            break;
                        case -11:
                            str = SHApplication.getAppContext().getString(R.string.accept_wx_share_error_11);
                            break;
                        default:
                            str = SHApplication.getAppContext().getString(R.string.accept_wx_share_error);
                            break;
                    }
                    error = new Error(error.a(), str);
                }
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }
}
