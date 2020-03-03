package com.xiaomi.smarthome.homeroom;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.constants.URLConstant;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.DeviceType;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.family.api.HomeListApi;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.plugin.ZipFileUtils;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.device_order.CateOrder;
import com.xiaomi.smarthome.homeroom.device_order.FrontOrder;
import com.xiaomi.smarthome.homeroom.device_order.HomeOrder;
import com.xiaomi.smarthome.homeroom.device_order.OrderCompat;
import com.xiaomi.smarthome.homeroom.device_order.RoomOrder;
import com.xiaomi.smarthome.homeroom.device_order.UtilKt;
import com.xiaomi.smarthome.homeroom.homedevicelist.DeviceCountManager;
import com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.DeviceTagRoom;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.model.RoomConfig;
import com.xiaomi.smarthome.homeroom.model.UnduplicateList;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.newui.DeviceMainPageHelper;
import com.xiaomi.smarthome.newui.roomenv.RoomEnvManager;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvData;
import com.xiaomi.smarthome.newui.topwidget.TopWidgetDataManagerNew;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Triple;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeManager {
    public static final String A = "home_room_room_edit";
    public static final String B = "home_room_room_sort";
    public static final String C = "home_room_home_added";
    static String D = "DeviceOrderDebug";
    public static final String E = "homeid";
    public static final String F = "homeid_server";
    public static final String G = "first_transfer_guide";
    public static final String H = "multihome_first_guide";
    public static Device I = null;
    public static final String J = "room_id";
    public static final String K = "device_tag_selected_action";
    public static final String L = "device_tag_updated_action";
    public static final String M = "tag_selected_param";
    public static final String N = "device_tag_param";
    public static final String O = "router_bssid_param";
    public static final String P = "device_tag_shared_prefs";
    public static final Set<String> Q = new HashSet();
    public static volatile long R = 0;
    public static final String S = "force_update_data_completed";
    /* access modifiers changed from: private */
    public static final String T = "HomeManager";
    private static final int U = 3000;
    private static final int V = 2;
    private static HomeManager W = null;

    /* renamed from: a  reason: collision with root package name */
    public static final String f1548a = "home_room_transfer_state";
    private static final String ad = "home_room_content";
    private static int ag = 8;
    private static final String[] ah = {"小米", "米家", "米兔", "小蚁", "青米", "飞利浦", "Yeelight", "智米", "90分", "美的", "奥克斯", "金兴", "iHealth", "小吉", "云米", "Aqara", "素士", "创米", "夏洛克"};
    private static final int ai = 5;
    private static final String az = "device_updated";
    public static final int b = 20;
    public static final int c = 50;
    public static final String d = "mijia.roomid.default";
    public static final String e = "mijia.roomid.share";
    public static final String f = "mijia.roomid.nearby";
    public static final String g = "mijia.roomid.favorite";
    public static final String h = "mijia.roomid.all";
    public static final String i = "mijia.roomid.category";
    public static final int j = -1;
    public static final int k = 0;
    public static final int l = 1;
    public static final int m = 2;
    public static final int n = 3;
    public static final int o = 4;
    public static final int p = 5;
    public static final int q = 6;
    public static final int r = 7;
    public static final String s = "home_room_manager_sp_";
    public static final String t = "home_room_updated";
    public static final String u = "home_room_home_changed";
    public static final String v = "operation";
    public static final String w = "result_code";
    public static final String x = "home_room_sync";
    public static final String y = "home_room_room_deleted";
    public static final String z = "home_room_room_added";
    /* access modifiers changed from: private */
    public boolean X = false;
    /* access modifiers changed from: private */
    public String Y = null;
    private Room Z;
    private BroadcastReceiver aA = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Integer num;
            Integer num2;
            String action = intent.getAction();
            LogUtil.a("forceUpdateAllData", "received:" + action);
            if (TextUtils.equals(action, HomeManager.az)) {
                LogUtilGrey.a("forceUpdateAllData", "mUpdateACTION_DEVICE_UPDATED received", true);
                SmartHomeDeviceManager.a().c(HomeManager.this.au);
                HomeManager.this.av.set(false);
            } else if (TextUtils.equals(action, HomeManager.t)) {
                LogUtilGrey.a("forceUpdateAllData", "ACTION_HOME_ROOM_UPDATED received", true);
                AreaInfoManager.a().a(SHApplication.getAppContext(), true);
                TopWidgetDataManagerNew.b().e();
                HomeManager.this.aw.set(false);
            } else if (TextUtils.equals(action, CommonUseDeviceDataManager.b)) {
                LogUtilGrey.a("forceUpdateAllData", "ACTION_DATA_UPDATED received", true);
                HomeManager.this.ax.set(false);
            }
            if (!HomeManager.this.av.get() && !HomeManager.this.aw.get() && !HomeManager.this.ax.get()) {
                SharedHomeDeviceManager.b().d();
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                HomeManager.this.ay.set(false);
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(HomeManager.S));
                Integer num3 = null;
                if (GlobalSetting.u || GlobalSetting.q) {
                    List<PluginRecord> O = CoreApi.a().O();
                    StringBuilder sb = new StringBuilder();
                    sb.append("ACTION_FORCE_UPDATE_COMPLETED sent,device count=");
                    sb.append(SmartHomeDeviceManager.a().d().size());
                    sb.append(",pluginrecord size=");
                    if (O == null) {
                        num = null;
                    } else {
                        num = Integer.valueOf(O.size());
                    }
                    sb.append(num);
                    LogUtilGrey.a("forceUpdateAllData", sb.toString());
                    Home m = HomeManager.this.m();
                    List<Room> e = HomeManager.this.e();
                    ServerBean F = CoreApi.a().F();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("currentHome is =");
                    sb2.append(m == null ? null : m.q().toString());
                    sb2.append(",current server=");
                    sb2.append(F == null ? "" : F.f1530a);
                    sb2.append(",getDefaultRoomList=");
                    if (e != null) {
                        num3 = Integer.valueOf(e.size());
                    }
                    sb2.append(num3);
                    LogUtilGrey.a("forceUpdateAllData", sb2.toString());
                    return;
                }
                List<PluginRecord> O2 = CoreApi.a().O();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ACTION_FORCE_UPDATE_COMPLETED sent,device count=");
                sb3.append(SmartHomeDeviceManager.a().d().size());
                sb3.append(",pluginrecord size=");
                if (O2 == null) {
                    num2 = null;
                } else {
                    num2 = Integer.valueOf(O2.size());
                }
                sb3.append(num2);
                LogUtilGrey.a("forceUpdateAllData", sb3.toString(), true);
                Home m2 = HomeManager.this.m();
                List<Room> e2 = HomeManager.this.e();
                ServerBean F2 = CoreApi.a().F();
                StringBuilder sb4 = new StringBuilder();
                sb4.append("currentHome=");
                sb4.append(m2);
                sb4.append(",current server=");
                sb4.append(F2 == null ? "" : F2.f1530a);
                sb4.append(",getDefaultRoomList=");
                if (e2 != null) {
                    num3 = Integer.valueOf(e2.size());
                }
                sb4.append(num3);
                LogUtilGrey.a("forceUpdateAllData", sb4.toString(), true);
            }
        }
    };
    private String aa;
    private String ab;
    private int ac = -1;
    private List<ITransferCheckListener> ae = new ArrayList();
    /* access modifiers changed from: private */
    public List<ITransferListener> af = new ArrayList();
    /* access modifiers changed from: private */
    public HomeListApi aj = new HomeListApi();
    private BroadcastReceiver ak = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(HomeManager.T, intent.getAction());
            if ("action_on_login_success".equals(intent.getAction())) {
                HomeManager.this.Q();
            } else if ("action_on_logout".equals(intent.getAction())) {
                HomeManager.this.R();
            }
        }
    };
    private Handler al = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
        }
    };
    /* access modifiers changed from: private */
    public HomeDataManager am;
    private RoomIconManager an;
    private long ao = 0;
    /* access modifiers changed from: private */
    public AtomicBoolean ap = new AtomicBoolean(false);
    private Room aq;
    private List<Device> ar;
    /* access modifiers changed from: private */
    public List<DeviceTagInterface.IDeviceTagListener> as = new ArrayList();
    private PublishSubject<Boolean> at = PublishSubject.create();
    /* access modifiers changed from: private */
    public SmartHomeDeviceManager.IClientDeviceListener au = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                HomeManager.R = System.currentTimeMillis();
                DeviceMainPageHelper.a(true);
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(HomeManager.az));
            }
        }
    };
    /* access modifiers changed from: private */
    public AtomicBoolean av = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean aw = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean ax = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean ay = new AtomicBoolean(false);

    public interface IHomeOperationCallback {
        public static final int d = 1;
        public static final int e = 2;
        public static final int f = 3;

        void a();

        void a(int i, Error error);
    }

    public interface ITransferCheckListener {
        void a();
    }

    public interface ITransferListener {
        void a();

        void b();
    }

    public interface UpdateHomeCallback {
        void a();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String D(String str) {
        return str;
    }

    /* access modifiers changed from: private */
    public void Q() {
    }

    static {
        for (String add : DeviceFactory.q) {
            Q.add(add);
        }
    }

    public static HomeManager a() {
        if (W == null) {
            synchronized (HomeManager.class) {
                if (W == null) {
                    W = new HomeManager();
                }
            }
        }
        return W;
    }

    private HomeManager() {
        LogUtilGrey.a(T, "HomeManager <init>");
        this.am = new HomeDataManager();
        O();
        this.an = new RoomIconManager();
        MultiHomeDeviceManager.a();
        SmartHomeDeviceManager.a().b((SmartHomeDeviceManager.IClientDeviceListener) this.am);
        IntentFilter intentFilter = new IntentFilter("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.ak, intentFilter);
        if (!TextUtils.isEmpty(this.Y)) {
            this.am.c();
        }
    }

    public void b() {
        this.am.h();
    }

    public boolean c() {
        return this.am.b();
    }

    public void a(final UpdateHomeCallback updateHomeCallback) {
        if (SHApplication.getStateNotifier().a() == 4) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (updateHomeCallback != null) {
                        updateHomeCallback.a();
                    }
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                }
            }, new IntentFilter(t));
            this.am.h();
        } else if (updateHomeCallback != null) {
            updateHomeCallback.a();
        }
    }

    public List<Room> a(String str) {
        return this.am.g(str);
    }

    public StateListDrawable b(String str) {
        return this.an.a(str);
    }

    public List<String> d() {
        return this.an.a();
    }

    public List<String> c(String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList();
        }
        int indexOf = str.indexOf(JSMethod.NOT_SET);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        return this.an.b(str);
    }

    public Bitmap d(String str) {
        return this.an.a(str, true);
    }

    public Bitmap e(String str) {
        return this.an.a(str, false);
    }

    public String f(String str) {
        return this.an.b(str, true);
    }

    public String a(String str, boolean z2) {
        List<Room> a2;
        List<Home> f2 = f();
        if (f2 == null || f2.isEmpty()) {
            return this.an.b(RoomIconManager.f18022a, true);
        }
        for (int i2 = 0; i2 < f2.size(); i2++) {
            Home home = f2.get(i2);
            if (!(home == null || (a2 = a(home.j())) == null || a2.isEmpty())) {
                for (int i3 = 0; i3 < a2.size(); i3++) {
                    Room room = a2.get(i3);
                    if (room != null && TextUtils.equals(str, room.e())) {
                        return this.an.b(room.a(), z2);
                    }
                }
                continue;
            }
        }
        RoomConfig c2 = this.an.c(str);
        if (c2 == null) {
            return this.an.b(RoomIconManager.f18022a, z2);
        }
        RoomIconManager roomIconManager = this.an;
        return roomIconManager.b(c2.a() + "_1", z2);
    }

    public String g(String str) {
        return this.an.b(str, false);
    }

    public void a(final IHomeOperationCallback iHomeOperationCallback) {
        O();
        if (!this.X || TextUtils.isEmpty(this.Y) || this.am.b.size() <= 0) {
            if (!this.ap.getAndSet(true)) {
                UserMamanger.a().a(CoreApi.a().s(), (AsyncResponseCallback<ShareUserRecord>) null, false);
                Observable.create(new ObservableOnSubscribe<Boolean>() {
                    public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                        RemoteFamilyApi.a().i(SHApplication.getAppContext(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                if (!observableEmitter.isDisposed()) {
                                    if (jSONObject == null) {
                                        observableEmitter.onComplete();
                                        return;
                                    }
                                    String optString = jSONObject.optString(HomeManager.E);
                                    if (TextUtils.isEmpty(optString)) {
                                        observableEmitter.onNext(false);
                                        observableEmitter.onComplete();
                                        return;
                                    }
                                    if (HomeManager.this.f().size() > 0 && HomeManager.this.am.f(HomeManager.this.Y) == null) {
                                        String unused = HomeManager.this.Y = optString;
                                        HomeManager.this.a(SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0), HomeManager.this.Y);
                                    }
                                    boolean unused2 = HomeManager.this.X = true;
                                    observableEmitter.onNext(true);
                                    observableEmitter.onComplete();
                                }
                            }

                            public void onFailure(Error error) {
                                observableEmitter.onComplete();
                            }
                        });
                    }
                }).flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    /* renamed from: a */
                    public ObservableSource<Boolean> apply(final Boolean bool) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Boolean>() {
                            public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                                if (bool.booleanValue()) {
                                    observableEmitter.onNext(true);
                                    observableEmitter.onComplete();
                                    return;
                                }
                                ArrayList arrayList = new ArrayList();
                                Map<String, Set<String>> a2 = SmartHomeDeviceHelper.a().b().a(4);
                                if (!a2.isEmpty()) {
                                    arrayList.clear();
                                    arrayList.addAll(a2.keySet());
                                }
                                RemoteFamilyApi.a().a(SHApplication.getAppContext(), (ArrayList<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                                    /* renamed from: a */
                                    public void onSuccess(JSONObject jSONObject) {
                                        if (!observableEmitter.isDisposed()) {
                                            if (jSONObject == null) {
                                                observableEmitter.onComplete();
                                                return;
                                            }
                                            String unused = HomeManager.this.Y = jSONObject.optString(HomeManager.E);
                                            if (!TextUtils.isEmpty(HomeManager.this.Y)) {
                                                HomeManager.this.a(SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0), HomeManager.this.Y);
                                                boolean unused2 = HomeManager.this.X = true;
                                                observableEmitter.onNext(true);
                                                observableEmitter.onComplete();
                                                return;
                                            }
                                            observableEmitter.onComplete();
                                        }
                                    }

                                    public void onFailure(Error error) {
                                        observableEmitter.onComplete();
                                    }
                                });
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io()).subscribe(new Observer<Boolean>() {
                    public void onError(Throwable th) {
                    }

                    public void onSubscribe(Disposable disposable) {
                    }

                    /* renamed from: a */
                    public void onNext(Boolean bool) {
                        if (bool.booleanValue()) {
                            if (iHomeOperationCallback != null) {
                                iHomeOperationCallback.a();
                            }
                            HomeManager.this.am.h();
                        }
                    }

                    public void onComplete() {
                        HomeManager.this.ap.set(false);
                    }
                });
            }
        } else if (iHomeOperationCallback != null) {
            iHomeOperationCallback.a();
        }
    }

    public List<Room> e() {
        if (SHApplication.getStateNotifier().a() != 4) {
            return new ArrayList();
        }
        Map a2 = this.am.b;
        if (a2 != null && a2.size() == 1) {
            Iterator it = a2.keySet().iterator();
            if (it.hasNext()) {
                return (List) a2.get(it.next());
            }
        }
        return a(this.Y);
    }

    public Room h(String str) {
        if (SHApplication.getStateNotifier().a() != 4 || TextUtils.isEmpty(str)) {
            return null;
        }
        List list = (List) HomeRoomSortUtil.f.get(this.Y);
        if (list == null) {
            list = new ArrayList();
            HomeRoomSortUtil.f.put(this.Y, list);
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Room room = (Room) list.get(i2);
            if (room.d().equals(str)) {
                return room;
            }
        }
        Room room2 = new Room();
        room2.d(str);
        room2.a((List<String>) new ArrayList());
        list.add(room2);
        return room2;
    }

    public Room i(String str) {
        return this.am.e(str);
    }

    public Home j(String str) {
        return this.am.f(str);
    }

    public List<Home> f() {
        return this.am.f();
    }

    public Home g() {
        return this.am.g();
    }

    public List<String> a(String str, boolean... zArr) {
        List<Device> l2;
        ArrayList arrayList = new ArrayList();
        Home j2 = j(str);
        if (j2 == null) {
            return arrayList;
        }
        if (!j2.p()) {
            return SharedHomeDeviceManager.b().b(str);
        }
        List<Room> a2 = a(str);
        if (a2 == null) {
            return arrayList;
        }
        int i2 = 0;
        while (i2 < a2.size()) {
            try {
                Room room = a2.get(i2);
                if (room != null) {
                    List<String> h2 = room.h();
                    for (int i3 = 0; i3 < h2.size(); i3++) {
                        String str2 = h2.get(i3);
                        Device b2 = SmartHomeDeviceManager.a().b(str2);
                        if (b2 != null && !arrayList.contains(b2.did)) {
                            arrayList.add(str2);
                        }
                    }
                }
                i2++;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if ((zArr.length == 0 || zArr[0]) && (l2 = l(str)) != null) {
            for (int i4 = 0; i4 < l2.size(); i4++) {
                Device device = l2.get(i4);
                if (device != null && !arrayList.contains(device.did)) {
                    arrayList.add(device.did);
                }
            }
        }
        return arrayList;
    }

    public void a(String str, final AsyncCallback asyncCallback) {
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, this.Y) && this.am.f(str) != null) {
            if (j(str).p()) {
                this.Y = str;
                a(SHApplication.getAppContext().getSharedPreferences(f1548a, 0), str);
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(u));
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(null);
                    return;
                }
                return;
            }
            LogUtilGrey.a(T, "changeHome shared home in");
            this.Y = str;
            a(SHApplication.getAppContext().getSharedPreferences(f1548a, 0), str);
            SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) new SmartHomeDeviceManager.IClientDeviceListener() {
                public void a(int i, Device device) {
                }

                public void a(int i) {
                    LogUtilGrey.a(HomeManager.T, "changeHome onRefreshClientDeviceSuccess in");
                    SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(HomeManager.u));
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(null);
                    }
                }

                public void b(int i) {
                    String M = HomeManager.T;
                    LogUtilGrey.a(M, "changeHome onRefreshClientDeviceFailed in " + i);
                    SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(HomeManager.u));
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(null);
                    }
                }
            });
            LogUtilGrey.a(T, "changeHome updateDeviceRemote start");
            SmartHomeDeviceManager.a().p();
        }
    }

    /* access modifiers changed from: private */
    public String N() {
        if (SHApplication.getAppContext() == null) {
            return "";
        }
        SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(f1548a, 0);
        String string = sharedPreferences.getString(F, "");
        ServerBean a2 = ServerCompact.a(SHApplication.getAppContext());
        if (!TextUtils.isEmpty(string)) {
            if (a2 != null && !TextUtils.equals(a2.f1530a, string)) {
                return "";
            }
        } else if (a2 != null) {
            sharedPreferences.edit().putString(F, a2.f1530a).apply();
        }
        return sharedPreferences.getString(E, "");
    }

    /* access modifiers changed from: private */
    public void a(SharedPreferences sharedPreferences, String str) {
        sharedPreferences.edit().putString(E, str).apply();
        if (TextUtils.isEmpty(str)) {
            sharedPreferences.edit().putString(F, "").apply();
        } else {
            ServerBean a2 = ServerCompact.a(SHApplication.getAppContext());
            if (a2 != null) {
                sharedPreferences.edit().putString(F, a2.f1530a).apply();
            }
        }
        String str2 = T;
        LogUtilGrey.a(str2, "syncHomeToCore from saveHomeIdToSP " + str);
        C(str);
    }

    /* access modifiers changed from: private */
    public void C(final String str) {
        String str2 = T;
        LogUtilGrey.a(str2, "syncHomeToCore " + str);
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                LogUtilGrey.a(HomeManager.T, "syncHomeToCore onCoreReady in");
                SHApplication.getThreadExecutor().submit(new Runnable() {
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.xiaomi.smarthome.core.server.internal.homeroom.Home} */
                    /* JADX WARNING: type inference failed for: r1v1 */
                    /* JADX WARNING: type inference failed for: r1v5 */
                    /* JADX WARNING: type inference failed for: r1v7 */
                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r4 = this;
                            com.xiaomi.smarthome.homeroom.HomeManager$8 r0 = com.xiaomi.smarthome.homeroom.HomeManager.AnonymousClass8.this     // Catch:{ Exception -> 0x0043 }
                            java.lang.String r0 = r4     // Catch:{ Exception -> 0x0043 }
                            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0043 }
                            r1 = 0
                            if (r0 != 0) goto L_0x0032
                            com.xiaomi.smarthome.homeroom.HomeManager$8 r0 = com.xiaomi.smarthome.homeroom.HomeManager.AnonymousClass8.this     // Catch:{ Exception -> 0x0043 }
                            com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.this     // Catch:{ Exception -> 0x0043 }
                            com.xiaomi.smarthome.homeroom.HomeManager$8 r2 = com.xiaomi.smarthome.homeroom.HomeManager.AnonymousClass8.this     // Catch:{ Exception -> 0x0043 }
                            java.lang.String r2 = r4     // Catch:{ Exception -> 0x0043 }
                            com.xiaomi.smarthome.homeroom.model.Home r0 = r0.j((java.lang.String) r2)     // Catch:{ Exception -> 0x0043 }
                            com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo r2 = new com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo     // Catch:{ Exception -> 0x0043 }
                            r2.<init>()     // Catch:{ Exception -> 0x0043 }
                            if (r0 != 0) goto L_0x001f
                            goto L_0x0023
                        L_0x001f:
                            com.xiaomi.smarthome.core.server.internal.homeroom.Home r1 = r0.r()     // Catch:{ Exception -> 0x0043 }
                        L_0x0023:
                            r2.a((com.xiaomi.smarthome.core.server.internal.homeroom.Home) r1)     // Catch:{ Exception -> 0x0043 }
                            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ Exception -> 0x0043 }
                            java.lang.String r0 = r0.s()     // Catch:{ Exception -> 0x0043 }
                            r2.a((java.lang.String) r0)     // Catch:{ Exception -> 0x0043 }
                            r1 = r2
                        L_0x0032:
                            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ Exception -> 0x0043 }
                            r0.a((com.xiaomi.smarthome.core.server.internal.homeroom.HomeDeviceInfo) r1)     // Catch:{ Exception -> 0x0043 }
                            java.lang.String r0 = com.xiaomi.smarthome.homeroom.HomeManager.T     // Catch:{ Exception -> 0x0043 }
                            java.lang.String r1 = "syncHomeToCore setCurrentHome sent"
                            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)     // Catch:{ Exception -> 0x0043 }
                            goto L_0x0063
                        L_0x0043:
                            r0 = move-exception
                            r0.printStackTrace()
                            java.lang.String r1 = com.xiaomi.smarthome.homeroom.HomeManager.T
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            r2.<init>()
                            java.lang.String r3 = "syncHomeToCore setCurrentHome exception:"
                            r2.append(r3)
                            java.lang.String r0 = r0.getMessage()
                            r2.append(r0)
                            java.lang.String r0 = r2.toString()
                            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r1, r0)
                        L_0x0063:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.AnonymousClass8.AnonymousClass1.run():void");
                    }
                });
            }
        });
    }

    public boolean h() {
        return this.X || !TextUtils.isEmpty(this.Y);
    }

    public void a(boolean z2) {
        this.X = z2;
    }

    public int i() {
        return this.am.e();
    }

    public synchronized void a(ArrayList<String> arrayList) {
        if (arrayList != null) {
            try {
                RemoteFamilyApi.a().a(SHApplication.getAppContext(), arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        String unused = HomeManager.this.Y = HomeManager.this.N();
                        if (!TextUtils.isEmpty(HomeManager.this.Y)) {
                            HomeManager.this.a(SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0), HomeManager.this.Y);
                            boolean unused2 = HomeManager.this.X = true;
                        }
                        for (int i = 0; i < HomeManager.this.af.size(); i++) {
                            if (HomeManager.this.af.get(i) != null) {
                                if (!TextUtils.isEmpty(HomeManager.this.Y)) {
                                    ((ITransferListener) HomeManager.this.af.get(i)).a();
                                } else {
                                    ((ITransferListener) HomeManager.this.af.get(i)).b();
                                }
                            }
                        }
                    }

                    public void onFailure(Error error) {
                        for (int i = 0; i < HomeManager.this.af.size(); i++) {
                            if (HomeManager.this.af.get(i) != null) {
                                ((ITransferListener) HomeManager.this.af.get(i)).b();
                            }
                        }
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            return;
        }
    }

    public static JSONObject k(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", str);
        ArrayList arrayList = new ArrayList();
        Set<String> b2 = SmartHomeDeviceHelper.a().b().b(4, str);
        if (b2 != null) {
            for (String next : b2) {
                Device b3 = SmartHomeDeviceManager.a().b(next);
                if (b3 != null && a(b3)) {
                    arrayList.add(next);
                }
            }
        }
        jSONObject.put("dids", new JSONArray(arrayList));
        return jSONObject;
    }

    public List<Device> j() {
        return m(l());
    }

    public List<Device> l(String str) {
        return m(str);
    }

    public List<Device> m(String str) {
        Home f2;
        UnduplicateList unduplicateList = new UnduplicateList();
        if (TextUtils.isEmpty(str) || (f2 = this.am.f(str)) == null || f2.m() == null) {
            return unduplicateList;
        }
        ArrayList arrayList = new ArrayList(f2.m());
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b((String) arrayList.get(i2));
            if (b2 != null) {
                if (b2.isNew) {
                    arrayList2.add(b2);
                } else {
                    unduplicateList.add(b2);
                }
            }
        }
        unduplicateList.addAll(arrayList2);
        return unduplicateList;
    }

    public List<Device> k() {
        Set set;
        ArrayList<Device> arrayList = new ArrayList<>();
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        if (d2 != null && !d2.isEmpty()) {
            arrayList.addAll(d2);
        }
        ArrayList arrayList2 = new ArrayList();
        Map<String, Set<String>> q2 = SmartHomeDeviceHelper.a().b().q();
        if (q2 == null || q2.isEmpty() || (set = q2.get(this.ab)) == null || set.isEmpty()) {
            return arrayList2;
        }
        for (Device device : arrayList) {
            if (set.contains(device.did)) {
                arrayList2.add(device);
            }
        }
        return arrayList2;
    }

    public void a(ITransferCheckListener iTransferCheckListener) {
        this.ae.add(iTransferCheckListener);
    }

    public void b(ITransferCheckListener iTransferCheckListener) {
        this.ae.remove(iTransferCheckListener);
    }

    public void a(ITransferListener iTransferListener) {
        this.af.add(iTransferListener);
    }

    public void b(ITransferListener iTransferListener) {
        this.af.remove(iTransferListener);
    }

    private void O() {
        if (TextUtils.isEmpty(this.Y)) {
            this.Y = N();
            String str = T;
            LogUtilGrey.a(str, "syncHomeToCore from initCurrentHomeIdIfEmpty " + this.Y);
            C(this.Y);
        }
    }

    public String l() {
        Home home;
        O();
        if ((TextUtils.isEmpty(this.Y) || j(this.Y) == null) && f().size() > 0 && (home = (Home) this.am.c.get(0)) != null) {
            this.Y = home.j();
            a(SHApplication.getAppContext().getSharedPreferences(f1548a, 0), this.Y);
        }
        return this.Y;
    }

    public Home m() {
        O();
        return j(this.Y);
    }

    public boolean n() {
        return SHApplication.getAppContext().getSharedPreferences(f1548a, 0).getBoolean(G, true);
    }

    public boolean o() {
        if (m() == null || m().d().size() > 0) {
            return false;
        }
        SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(f1548a, 0);
        return sharedPreferences.getBoolean(H + l(), false);
    }

    public void a(Device device, boolean z2) {
        String str;
        if (device != null) {
            Home q2 = q(device.did);
            try {
                DeviceTagInterface.Category c2 = SmartHomeDeviceHelper.a().b().c(device.model);
                if (c2 != null) {
                    String str2 = null;
                    if (q2 == null) {
                        str = null;
                    } else {
                        str = q2.j();
                    }
                    List a2 = a(str, c2.f15435a);
                    if (a2 == null) {
                        a2 = new ArrayList();
                    }
                    a2.remove(device.did);
                    if (z2) {
                        a2.add(0, device.did);
                    } else {
                        a2.add(device.did);
                    }
                    if (q2 != null) {
                        str2 = q2.j();
                    }
                    a(str2, c2.f15435a, (List<String>) new ArrayList(a2));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(String str, String str2, List<String> list) {
        if (!TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str)) {
                str = this.Y;
            }
            this.am.a(str, str2, list);
        }
    }

    public void a(String str, List<Room> list) {
        if (TextUtils.isEmpty(str)) {
            str = this.Y;
        }
        this.am.a(str, list);
    }

    public void a(String str, List<String> list, String str2, IHomeOperationCallback iHomeOperationCallback) {
        if (TextUtils.isEmpty(str2)) {
            this.am.a(l(), str, list, RoomIconManager.f18022a, iHomeOperationCallback);
        } else {
            this.am.a(l(), str, list, str2, iHomeOperationCallback);
        }
    }

    public void a(String str, String str2, List<String> list, String str3, IHomeOperationCallback iHomeOperationCallback) {
        String str4 = TextUtils.isEmpty(str3) ? RoomIconManager.f18022a : str3;
        if (TextUtils.isEmpty(str)) {
            this.am.a(l(), str2, list, str4, iHomeOperationCallback);
        } else {
            this.am.a(str, str2, list, str3, iHomeOperationCallback);
        }
    }

    public void a(Home home, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(home, iHomeOperationCallback);
    }

    public static boolean n(String str) {
        return !TextUtils.isEmpty(str);
    }

    public int a(Room room) {
        if (room == null) {
            List<Device> r2 = r();
            if (r2 == null) {
                return 0;
            }
            return r2.size();
        }
        List<String> h2 = room.h();
        if (h2 == null || h2.isEmpty()) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < h2.size(); i3++) {
            Device b2 = SmartHomeDeviceManager.a().b(h2.get(i3));
            if (b2 != null && a(b2)) {
                i2++;
            }
        }
        return i2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2, types: [int] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r3, int r4, java.lang.String r5, java.lang.String r6) {
        /*
            r2 = this;
            r2.ac = r4
            int r4 = r2.ac
            r0 = 0
            r1 = 2
            if (r4 == r1) goto L_0x003b
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            java.util.List r4 = r2.e()
            if (r4 == 0) goto L_0x003f
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x0019
            goto L_0x003f
        L_0x0019:
            r5 = 0
        L_0x001a:
            int r6 = r4.size()
            if (r0 >= r6) goto L_0x0039
            java.lang.Object r6 = r4.get(r0)
            com.xiaomi.smarthome.homeroom.model.Room r6 = (com.xiaomi.smarthome.homeroom.model.Room) r6
            if (r6 != 0) goto L_0x0029
            goto L_0x0036
        L_0x0029:
            java.lang.String r1 = r6.d()
            boolean r1 = android.text.TextUtils.equals(r3, r1)
            if (r1 == 0) goto L_0x0036
            r2.Z = r6
            r5 = 1
        L_0x0036:
            int r0 = r0 + 1
            goto L_0x001a
        L_0x0039:
            r0 = r5
            goto L_0x003f
        L_0x003b:
            r2.aa = r5
            r2.ab = r6
        L_0x003f:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r3 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.util.List r3 = r3.d()
            r2.a((java.util.List<com.xiaomi.smarthome.device.Device>) r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.a(java.lang.String, int, java.lang.String, java.lang.String):boolean");
    }

    public Room p() {
        return this.Z;
    }

    public void b(Room room) {
        this.aq = room;
    }

    public Room q() {
        return this.aq;
    }

    public List<Device> r() {
        if (this.ar != null) {
            return this.ar;
        }
        ArrayList arrayList = new ArrayList();
        if (this.ac == -1) {
            List<Room> e2 = e();
            if (e2 == null || e2.isEmpty()) {
                return arrayList;
            }
            for (int i2 = 0; i2 < e2.size(); i2++) {
                Room room = e2.get(i2);
                if (room != null) {
                    List<String> h2 = room.h();
                    for (int i3 = 0; i3 < h2.size(); i3++) {
                        Device b2 = SmartHomeDeviceManager.a().b(h2.get(i3));
                        if (b2 != null) {
                            arrayList.add(b2);
                        }
                    }
                }
            }
        } else if (this.ac == 6) {
            return j();
        } else {
            if (this.ac == 7) {
                List<Room> e3 = e();
                if (e3 == null || e3.isEmpty()) {
                    return arrayList;
                }
                for (int i4 = 0; i4 < e3.size(); i4++) {
                    Room room2 = e3.get(i4);
                    if (room2 != null) {
                        List<String> h3 = room2.h();
                        for (int i5 = 0; i5 < h3.size(); i5++) {
                            Device b3 = SmartHomeDeviceManager.a().b(h3.get(i5));
                            if (b3 != null && b3.isOwner()) {
                                arrayList.add(b3);
                            }
                        }
                    }
                }
            } else if (this.Z != null) {
                return c(this.Z);
            } else {
                List<Room> e4 = e();
                if (e4 == null || e4.isEmpty()) {
                    return arrayList;
                }
                for (int i6 = 0; i6 < e4.size(); i6++) {
                    Room room3 = e4.get(i6);
                    if (room3 != null) {
                        List<String> h4 = room3.h();
                        for (int i7 = 0; i7 < h4.size(); i7++) {
                            Device b4 = SmartHomeDeviceManager.a().b(h4.get(i7));
                            if (b4 != null) {
                                arrayList.add(b4);
                            }
                        }
                    }
                }
            }
        }
        this.ar = arrayList;
        return arrayList;
    }

    public List<Device> c(Room room) {
        ArrayList arrayList = new ArrayList();
        if (room == null) {
            return r();
        }
        List<String> h2 = room.h();
        if (h2 == null || h2.isEmpty()) {
            return arrayList;
        }
        for (int i2 = 0; i2 < h2.size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b(h2.get(i2));
            if (a(b2)) {
                arrayList.add(b2);
            }
        }
        return arrayList;
    }

    public List<Device> o(String str) {
        Home j2;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || (j2 = j(str)) == null) {
            return arrayList;
        }
        if (!j2.p()) {
            return SharedHomeDeviceManager.b().a(str);
        }
        List<Room> a2 = a(str);
        if (a2 == null) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(a2);
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            Room room = (Room) arrayList2.get(i2);
            if (room != null) {
                List<String> h2 = room.h();
                for (int i3 = 0; i3 < h2.size(); i3++) {
                    Device b2 = SmartHomeDeviceManager.a().b(h2.get(i3));
                    if (b2 != null) {
                        arrayList.add(b2);
                    }
                }
            }
        }
        List<Device> l2 = l(str);
        if (l2 != null) {
            arrayList.addAll(l2);
        }
        return arrayList;
    }

    public List<DeviceTagRoom> d(final Room room) {
        ArrayList arrayList = new ArrayList();
        List<Device> a2 = SmartHomeDeviceHelper.a().a(SmartHomeDeviceManager.a().d());
        if (a2 == null || a2.isEmpty()) {
            return arrayList;
        }
        for (Device next : a2) {
            if (a(next)) {
                arrayList.add(a(next, room.h()));
            }
        }
        Collections.sort(arrayList, new Comparator<DeviceTagRoom>() {
            /* renamed from: a */
            public int compare(DeviceTagRoom deviceTagRoom, DeviceTagRoom deviceTagRoom2) {
                if (room != null && room.h() != null && room.h().size() > 0) {
                    Room room = deviceTagRoom.j;
                    Room room2 = deviceTagRoom2.j;
                    if (!(room == null || room2 == null)) {
                        if (TextUtils.equals(room.d(), room.d()) && TextUtils.equals(room.d(), room2.d())) {
                            return 0;
                        }
                        if (TextUtils.equals(room.d(), room.d())) {
                            return -1;
                        }
                        if (TextUtils.equals(room.d(), room2.d())) {
                            return 1;
                        }
                    }
                    if (room != null && room2 == null && TextUtils.equals(room.d(), room.d())) {
                        return -1;
                    }
                    if (room == null && room2 != null && TextUtils.equals(room.d(), room2.d())) {
                        return 1;
                    }
                    if (deviceTagRoom.k == 1 && deviceTagRoom2.k == 1) {
                        return 0;
                    }
                    if (deviceTagRoom.k == 1) {
                        return -1;
                    }
                    if (deviceTagRoom2.k == 1) {
                        return 1;
                    }
                    if (deviceTagRoom.k < deviceTagRoom2.k) {
                        return -1;
                    }
                    return deviceTagRoom.k > deviceTagRoom2.k ? 1 : 0;
                } else if (deviceTagRoom.k < deviceTagRoom2.k) {
                    return -1;
                } else {
                    return deviceTagRoom.k > deviceTagRoom2.k ? 1 : 0;
                }
            }
        });
        return arrayList;
    }

    private DeviceTagRoom a(Device device, List<String> list) {
        DeviceTagRoom deviceTagRoom = new DeviceTagRoom();
        deviceTagRoom.d = device.name;
        deviceTagRoom.g = device;
        deviceTagRoom.j = p(device.did);
        if (deviceTagRoom.j == null) {
            deviceTagRoom.k = 0;
            deviceTagRoom.h = false;
        } else if (list == null || !list.contains(device.did)) {
            deviceTagRoom.k = 2;
            deviceTagRoom.h = false;
        } else {
            deviceTagRoom.k = 1;
            deviceTagRoom.h = true;
        }
        return deviceTagRoom;
    }

    public List<String> a(String str, String str2) {
        Set<String> e2;
        List<String> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            str = l();
        }
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        List list = (List) HomeRoomSortUtil.g.get(str);
        if (list == null) {
            list = new ArrayList();
        }
        if (DeviceTagManager.W) {
            str2 = str2 + JSMethod.NOT_SET;
        }
        int i2 = 0;
        while (true) {
            if (i2 < list.size()) {
                Pair pair = (Pair) list.get(i2);
                if (pair != null && !TextUtils.isEmpty((CharSequence) pair.first) && TextUtils.equals((CharSequence) pair.first, str2)) {
                    arrayList = (List) pair.second;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        DeviceTagInterface.Category h2 = b2.h(str2);
        if (h2 == null || (e2 = b2.e(str, h2.d)) == null || e2.isEmpty()) {
            return arrayList;
        }
        HashSet hashSet = new HashSet(e2);
        for (String remove : arrayList) {
            hashSet.remove(remove);
        }
        arrayList.addAll(0, hashSet);
        return arrayList;
    }

    public List<DeviceTagRoom> a(String str, Room room) {
        ArrayList arrayList = new ArrayList();
        if (room == null || TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            Home j2 = j(str);
            List<Room> d2 = j2.d();
            for (int i2 = 0; i2 < d2.size(); i2++) {
                Room room2 = d2.get(i2);
                if (TextUtils.equals(room.d(), room2.d())) {
                    arrayList.addAll(0, b(room.d(), room));
                } else {
                    arrayList.addAll(b(room.d(), room2));
                }
            }
            if (j2.m().size() > 0) {
                Room room3 = new Room();
                room3.d(d);
                room3.a(j2.m());
                room3.f(j2.j());
                room3.e(SHApplication.getAppContext().getResources().getString(R.string.tag_recommend_defaultroom));
                if (room.h() == null || !TextUtils.equals(str, room.f()) || arrayList.size() < room.h().size()) {
                    arrayList.addAll(0, b(room.d(), room3));
                } else {
                    arrayList.addAll(room.h().size(), b(room.d(), room3));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private List<DeviceTagRoom> b(String str, Room room) {
        ArrayList arrayList = new ArrayList();
        if (room == null || room.h() == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < room.h().size(); i2++) {
            Device b2 = SmartHomeDeviceManager.a().b(room.h().get(i2));
            if (b2 != null) {
                DeviceTagRoom deviceTagRoom = new DeviceTagRoom();
                deviceTagRoom.d = b2.name;
                deviceTagRoom.g = b2;
                deviceTagRoom.j = room;
                if (TextUtils.equals(str, room.d())) {
                    deviceTagRoom.k = 1;
                    deviceTagRoom.h = true;
                } else if (TextUtils.equals(room.d(), d)) {
                    deviceTagRoom.k = 0;
                    deviceTagRoom.h = false;
                } else {
                    deviceTagRoom.k = 2;
                    deviceTagRoom.h = false;
                }
                arrayList.add(deviceTagRoom);
            }
        }
        return arrayList;
    }

    public Room p(String str) {
        return this.am.a(str);
    }

    public Home q(String str) {
        return this.am.b(str);
    }

    public Map<String, Home> s() {
        return this.am.d();
    }

    public String r(String str) {
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 == null) {
            b2 = SmartHomeDeviceManager.a().c(str);
        }
        if (b2 == null) {
            return "";
        }
        ServerCompact.c(SHApplication.getAppContext());
        if (SmartHomeDeviceManager.e(b2) && !b2.isHomeShared()) {
            return SHApplication.getAppContext().getResources().getString(R.string.tag_share);
        }
        if (SmartHomeDeviceManager.f(b2) && !b2.isHomeShared()) {
            return SHApplication.getAppContext().getResources().getString(R.string.nearby_device);
        }
        if (HomeVirtualDeviceHelper.a(b2.model)) {
            return SHApplication.getAppContext().getResources().getString(R.string.virtual_device_room_name);
        }
        Room a2 = this.am.a(str);
        if (a2 != null) {
            return a2.e();
        }
        return SHApplication.getAppContext().getResources().getString(R.string.tag_recommend_defaultroom);
    }

    public boolean a(Room room, String str) {
        List<Room> list;
        if (room == null) {
            list = e();
        } else {
            list = a(room.f());
        }
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Room room2 = list.get(i2);
            if ((room == null || (!TextUtils.equals(room2.d(), room.d()) && !TextUtils.equals(room2.e(), room.e()))) && TextUtils.equals(room2.e(), str)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(Home home, String str) {
        List<Home> f2 = f();
        if (f2 == null || f2.isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < f2.size(); i2++) {
            Home home2 = f2.get(i2);
            if ((home == null || (!TextUtils.equals(home2.j(), home.j()) && !TextUtils.equals(home2.k(), home.k()))) && TextUtils.equals(home2.k(), str)) {
                return true;
            }
        }
        return false;
    }

    public static class HomeRoomSortUtil {

        /* renamed from: a  reason: collision with root package name */
        public static List<String> f18020a = new ArrayList();
        private static final String b = "home_room_sort_timestamp";
        private static final String c = "home_room_sort_value";
        private static final String d = "home_cate_device_sort_timestamp";
        private static final String e = "home_cate_device_sort_value";
        /* access modifiers changed from: private */
        public static Map<String, List<Room>> f = new ConcurrentHashMap();
        /* access modifiers changed from: private */
        public static Map<String, List<Pair<String, List<String>>>> g = new ConcurrentHashMap();

        /* access modifiers changed from: private */
        public static void c(Home home, List<Room> list) {
            if (list != null && home != null) {
                try {
                    boolean z = true;
                    boolean z2 = true;
                    for (Room next : list) {
                        if (next != null) {
                            if (TextUtils.equals(next.d(), HomeManager.d)) {
                                z = false;
                            } else if (TextUtils.equals(next.d(), HomeManager.e)) {
                                z2 = false;
                            }
                        }
                    }
                    if (z && home.m().size() > 0) {
                        Room room = new Room();
                        room.d(HomeManager.d);
                        room.a(home.m());
                        list.add(room);
                    }
                    Room h = HomeManager.a().h(HomeManager.e);
                    if (z2 && h.h().size() > 0) {
                        Room room2 = new Room();
                        room2.d(HomeManager.e);
                        room2.a((List<String>) new ArrayList(h.h()));
                        list.add(room2);
                    }
                } catch (Exception e2) {
                    String M = HomeManager.T;
                    Log.e(M, "appendSpecialRoomOrderIfNeeded: " + e2.getLocalizedMessage());
                }
            }
        }

        public static List<Device> a(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                str = HomeManager.a().l();
            }
            ArrayList arrayList = new ArrayList();
            List list = f.get(str);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Room room = (Room) list.get(i);
                    if (room.d().equals(str2)) {
                        List<String> h = room.h();
                        for (int i2 = 0; i2 < h.size(); i2++) {
                            Device b2 = SmartHomeDeviceManager.a().b(h.get(i2));
                            if (b2 != null) {
                                arrayList.add(b2);
                            }
                        }
                    }
                }
            }
            return arrayList;
        }

        public static List<String> b(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                str = HomeManager.a().l();
            }
            List<Room> list = f.get(str);
            if (list != null) {
                for (Room room : list) {
                    if (TextUtils.equals(str2, room.d())) {
                        return new ArrayList(room.h());
                    }
                }
            }
            return new ArrayList();
        }

        public static <T> void a(List<T> list, final List<T> list2) {
            Collections.sort(list, new Comparator<T>() {
                public int compare(T t, T t2) {
                    int indexOf = list2.indexOf(t);
                    int indexOf2 = list2.indexOf(t2);
                    if (indexOf < 0 && indexOf2 > 0) {
                        return -1;
                    }
                    if (indexOf > 0 && indexOf2 < 0) {
                        return 1;
                    }
                    if (indexOf >= 0 || indexOf2 >= 0) {
                        return indexOf - indexOf2;
                    }
                    return 0;
                }
            });
        }

        public static JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                Map a2 = HomeManager.a().am.b;
                JSONArray jSONArray = new JSONArray();
                for (String str : a2.keySet()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("home_id", str);
                    List list = (List) a2.get(str);
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = 0; i < list.size(); i++) {
                        Room room = (Room) list.get(i);
                        if (room != null) {
                            if (!TextUtils.isEmpty(room.d())) {
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("room_id", room.d());
                                jSONArray2.put(jSONObject3);
                                jSONObject3.put("did_order", new JSONArray(room.h()));
                            }
                        }
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("room_id", HomeManager.d);
                    jSONArray2.put(jSONObject4);
                    List<Device> m = HomeManager.a().m(str);
                    ArrayList arrayList = new ArrayList(m.size());
                    for (int i2 = 0; i2 < m.size(); i2++) {
                        arrayList.add(m.get(i2).did);
                    }
                    jSONObject4.put("did_order", new JSONArray(arrayList));
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("room_id", HomeManager.e);
                    jSONArray2.put(jSONObject5);
                    jSONObject5.put("did_order", new JSONArray(HomeManager.a().h(HomeManager.e).h()));
                    jSONObject2.put("room_order", jSONArray2);
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("home_order", jSONArray);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }

        public static JSONObject b() {
            DeviceTagInterface.Category i;
            JSONObject jSONObject = new JSONObject();
            try {
                List b2 = HomeManager.a().am.c;
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < b2.size(); i2++) {
                    Home home = (Home) b2.get(i2);
                    if (home != null) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("home_id", home.j());
                        List list = g.get(home.j());
                        if (list != null) {
                            if (!list.isEmpty()) {
                                JSONArray jSONArray2 = new JSONArray();
                                for (int i3 = 0; i3 < list.size(); i3++) {
                                    Pair pair = (Pair) list.get(i3);
                                    if (!(pair == null || TextUtils.isEmpty((CharSequence) pair.first) || pair.second == null)) {
                                        if (!((List) pair.second).isEmpty()) {
                                            JSONObject jSONObject3 = new JSONObject();
                                            jSONObject3.put("cate_id", pair.first);
                                            jSONArray2.put(jSONObject3);
                                            jSONObject3.put("did_order", new JSONArray((Collection) pair.second));
                                            String str = "";
                                            if (((String) pair.first).contains(JSMethod.NOT_SET) && (i = SmartHomeDeviceHelper.a().b().i(((String) pair.first).replace(JSMethod.NOT_SET, ""))) != null) {
                                                str = i.b;
                                            }
                                            jSONObject3.put("parent_id", str);
                                        }
                                    }
                                }
                                jSONObject2.put("cate_device_order", jSONArray2);
                                jSONArray.put(jSONObject2);
                            }
                        }
                    }
                }
                jSONObject.put("category_order", jSONArray);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (GlobalSetting.q) {
                String M = HomeManager.T;
                LogUtil.a(M, "generateHomeCategoryOrder:" + jSONObject.toString());
            }
            return jSONObject;
        }

        /* access modifiers changed from: private */
        public static void d(Home home, List<Room> list) {
            if (home != null && list != null && !list.isEmpty() && home.p()) {
                ArrayList arrayList = new ArrayList(list);
                c(home, arrayList);
                OrderCompat.f18244a.a(home.j(), (List<? extends Room>) arrayList);
            }
        }

        public static LinkedHashMap<String, LinkedHashMap<String, List<String>>> a(List<String> list) {
            String str;
            LinkedHashMap<String, LinkedHashMap<String, List<String>>> linkedHashMap = new LinkedHashMap<>();
            if (list == null || list.isEmpty()) {
                return linkedHashMap;
            }
            for (int i = 0; i < list.size(); i++) {
                String str2 = list.get(i);
                if (SmartHomeDeviceManager.a().b(str2) != null) {
                    Home q = HomeManager.a().q(str2);
                    Room p = HomeManager.a().p(str2);
                    if (q != null) {
                        LinkedHashMap linkedHashMap2 = linkedHashMap.get(q.j());
                        if (linkedHashMap2 == null) {
                            linkedHashMap2 = new LinkedHashMap();
                            linkedHashMap.put(q.j(), linkedHashMap2);
                        }
                        if (p == null) {
                            str = "";
                        } else {
                            str = p.d();
                        }
                        List list2 = (List) linkedHashMap2.get(str);
                        if (list2 == null) {
                            list2 = new ArrayList();
                            linkedHashMap2.put(str, list2);
                        }
                        list2.add(str2);
                    }
                }
            }
            return linkedHashMap;
        }
    }

    public void a(DeviceTagInterface.IDeviceTagListener iDeviceTagListener) {
        this.as.add(iDeviceTagListener);
    }

    public void b(DeviceTagInterface.IDeviceTagListener iDeviceTagListener) {
        this.as.remove(iDeviceTagListener);
    }

    public Room s(String str) {
        return this.am.c(str);
    }

    public Room t(String str) {
        return this.am.d(str);
    }

    public void a(Room room, List<String> list, List<String> list2, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(room, list, list2, iHomeOperationCallback);
    }

    public void a(Room room, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(room, iHomeOperationCallback);
    }

    public void a(List<String> list, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(list, iHomeOperationCallback);
    }

    public void b(Home home, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(home, "set", iHomeOperationCallback);
    }

    public void c(Home home, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(home, "delete", iHomeOperationCallback);
    }

    public void a(Home home, Room room, Device device, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(home, room, device, iHomeOperationCallback);
    }

    public void a(Home home, Room room, List<String> list, IHomeOperationCallback iHomeOperationCallback) {
        this.am.a(home, room, list, iHomeOperationCallback);
    }

    public void a(Room room, List<String> list) {
        this.am.a(room, (List<String>) null, list);
    }

    public Map<String, Set<String>> t() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList(e());
        if (!arrayList.isEmpty()) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Room room = (Room) arrayList.get(i2);
                HashSet hashSet = new HashSet();
                List<String> h2 = room.h();
                if (h2 != null && !h2.isEmpty()) {
                    hashSet.addAll(h2);
                }
                hashMap.put(room.e(), hashSet);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.util.Set<java.lang.String> r3, java.lang.String r4, boolean r5, com.xiaomi.smarthome.homeroom.HomeManager.IHomeOperationCallback r6) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 == 0) goto L_0x005a
            boolean r5 = r3.isEmpty()     // Catch:{ all -> 0x0057 }
            if (r5 == 0) goto L_0x000a
            goto L_0x005a
        L_0x000a:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0057 }
            java.lang.Object r3 = r3.next()     // Catch:{ all -> 0x0057 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0057 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0057 }
            if (r5 == 0) goto L_0x001c
            monitor-exit(r2)
            return
        L_0x001c:
            com.xiaomi.smarthome.homeroom.model.Room r5 = r2.t(r3)     // Catch:{ all -> 0x0057 }
            r0 = 0
            if (r5 != 0) goto L_0x0035
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0057 }
            r5.<init>()     // Catch:{ all -> 0x0057 }
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0057 }
            if (r1 != 0) goto L_0x0031
            r5.add(r4)     // Catch:{ all -> 0x0057 }
        L_0x0031:
            r2.a((java.lang.String) r3, (java.util.List<java.lang.String>) r5, (java.lang.String) r0, (com.xiaomi.smarthome.homeroom.HomeManager.IHomeOperationCallback) r6)     // Catch:{ all -> 0x0057 }
            goto L_0x0055
        L_0x0035:
            java.util.List r3 = r5.h()     // Catch:{ all -> 0x0057 }
            if (r3 != 0) goto L_0x0041
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0057 }
            r3.<init>()     // Catch:{ all -> 0x0057 }
            goto L_0x0047
        L_0x0041:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0057 }
            r1.<init>(r3)     // Catch:{ all -> 0x0057 }
            r3 = r1
        L_0x0047:
            boolean r1 = r3.contains(r4)     // Catch:{ all -> 0x0057 }
            if (r1 != 0) goto L_0x0050
            r3.add(r4)     // Catch:{ all -> 0x0057 }
        L_0x0050:
            com.xiaomi.smarthome.homeroom.HomeManager$HomeDataManager r4 = r2.am     // Catch:{ all -> 0x0057 }
            r4.a((com.xiaomi.smarthome.homeroom.model.Room) r5, (java.util.List<java.lang.String>) r3, (java.util.List<java.lang.String>) r0, (com.xiaomi.smarthome.homeroom.HomeManager.IHomeOperationCallback) r6)     // Catch:{ all -> 0x0057 }
        L_0x0055:
            monitor-exit(r2)
            return
        L_0x0057:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x005a:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.a(java.util.Set, java.lang.String, boolean, com.xiaomi.smarthome.homeroom.HomeManager$IHomeOperationCallback):void");
    }

    public synchronized void u(String str) {
        Room s2 = s(str);
        if (s2 != null) {
            this.am.a(s2, (IHomeOperationCallback) null);
        }
    }

    public synchronized List<String> v(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Room p2 = p(str);
        if (p2 != null) {
            arrayList.add(p2.e());
        }
        return arrayList;
    }

    public boolean u() {
        return this.ac != -1;
    }

    public synchronized void v() {
        this.am.a();
        this.Y = "";
        this.Z = null;
        this.ac = -1;
        HomeRoomSortUtil.f18020a = new ArrayList();
        Map unused = HomeRoomSortUtil.f = new ConcurrentHashMap();
        Map unused2 = HomeRoomSortUtil.g = new ConcurrentHashMap();
        this.X = false;
        a(SHApplication.getAppContext().getSharedPreferences(f1548a, 0), "");
        SharePrefsManager.a(SHApplication.getAppContext(), s, ad, new String("{}"));
        CommonUseDeviceDataManager.a().b();
    }

    public void a(List<Device> list) {
        if (this.ac == -1) {
            this.ar = list;
        } else if (this.ac == 6) {
            this.ar = j();
        } else if (this.ac == 7) {
            this.ar = MultiHomeDeviceManager.a().e();
        } else if (this.ac == 2) {
            this.ar = k();
        } else if (this.Z != null) {
            this.ar = c(this.Z);
        } else {
            this.ar = list;
        }
    }

    public String w() {
        if (this.ac == -1) {
            return SHApplication.getAppContext().getString(R.string.tag_all_devices);
        }
        if (this.ac == 7) {
            return SHApplication.getAppContext().getString(R.string.smarthome_device_device);
        }
        if (this.ac == 2) {
            return this.aa;
        }
        if (this.ac == 6) {
            return SHApplication.getAppContext().getString(R.string.tag_recommend_defaultroom);
        }
        if (this.Z != null) {
            return this.Z.e();
        }
        return SHApplication.getAppContext().getString(R.string.tag_all_devices);
    }

    public String x() {
        if (this.ac == -1) {
            return "";
        }
        if (this.ac == 2) {
            return this.ab;
        }
        return this.aa;
    }

    public List<Device> y() {
        return this.ar;
    }

    public int z() {
        List<Device> list;
        if (this.ac == -1 || (list = this.ar) == null) {
            return 0;
        }
        return list.size();
    }

    public String b(String str, String str2) {
        List<Room> list;
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str2)) {
            list = e();
        } else {
            list = a(str2);
        }
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                String e2 = list.get(i2).e();
                if (!TextUtils.isEmpty(e2) && e2.contains(str)) {
                    arrayList.add(e2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return str;
        }
        int i3 = 1;
        while (true) {
            String str3 = str + (arrayList.size() + i3);
            Iterator it = arrayList.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                if (((String) it.next()).equals(str3)) {
                    z2 = true;
                }
            }
            if (!z2) {
                return str3;
            }
            i3++;
        }
    }

    public class HomeDataManager implements SmartHomeDeviceManager.IClientDeviceListener {
        /* access modifiers changed from: private */
        public Map<String, List<Room>> b = new ConcurrentHashMap();
        /* access modifiers changed from: private */
        public List<Home> c = new ArrayList();
        /* access modifiers changed from: private */
        public AtomicBoolean d = new AtomicBoolean(false);
        /* access modifiers changed from: private */
        public AtomicBoolean e = new AtomicBoolean(false);
        private AtomicBoolean f = new AtomicBoolean(true);
        /* access modifiers changed from: private */
        public volatile boolean g = false;
        /* access modifiers changed from: private */
        public long h = 0;
        private final Consumer<Triple<List<Home>, List<HomeOrder>, Boolean>> i = new Consumer<Triple<List<Home>, List<HomeOrder>, Boolean>>() {
            @SuppressLint({"CheckResult"})
            /* renamed from: a */
            public void accept(Triple<List<Home>, List<HomeOrder>, Boolean> triple) throws Exception {
                LogUtil.a(HomeManager.T, "new order data received.");
                List<HomeOrder> second = triple.getSecond();
                Boolean third = triple.getThird();
                if (GlobalSetting.u) {
                    StringBuilder sb = new StringBuilder();
                    for (Home q : HomeDataManager.this.c) {
                        sb.append(q.q());
                        sb.append("    ");
                    }
                    String str = HomeManager.D;
                    LogUtilGrey.a(str, "Consumer:homeOrder before " + sb.toString());
                }
                if (!HomeDataManager.this.c.isEmpty() && HomeDataManager.this.f(HomeManager.a().Y) == null) {
                    String unused = HomeManager.a().Y = ((Home) HomeDataManager.this.c.get(0)).j();
                    HomeManager.this.a(SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0), HomeManager.this.Y);
                }
                for (HomeOrder homeOrder : second) {
                    FrontOrder d = homeOrder.d();
                    String a2 = homeOrder.a();
                    if (!TextUtils.isEmpty(a2)) {
                        CommonUseDeviceDataManager.a().a(a2, (List<String>) d.a());
                    }
                }
                ArrayList arrayList = new ArrayList();
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
                for (HomeOrder homeOrder2 : second) {
                    String a3 = homeOrder2.a();
                    if (!TextUtils.isEmpty(a3)) {
                        arrayList.add(a3);
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<RoomOrder> it = homeOrder2.e().iterator();
                        while (it.hasNext()) {
                            RoomOrder next = it.next();
                            Room room = new Room();
                            room.d(next.a());
                            room.a((List<String>) new ArrayList(next.b()));
                            arrayList2.add(room);
                        }
                        concurrentHashMap.put(a3, arrayList2);
                        ArrayList arrayList3 = new ArrayList();
                        concurrentHashMap2.put(a3, arrayList3);
                        Iterator<CateOrder> it2 = homeOrder2.f().iterator();
                        while (it2.hasNext()) {
                            CateOrder next2 = it2.next();
                            arrayList3.add(Pair.create(next2.a(), new ArrayList(next2.b())));
                        }
                    }
                }
                HomeRoomSortUtil.f18020a = arrayList;
                Map unused2 = HomeRoomSortUtil.f = concurrentHashMap;
                Map unused3 = HomeRoomSortUtil.g = concurrentHashMap2;
                for (DeviceTagInterface.IDeviceTagListener iDeviceTagListener : HomeManager.this.as) {
                    if (iDeviceTagListener != null) {
                        iDeviceTagListener.a();
                    }
                }
                HomeDataManager.this.d.set(false);
                HomeDataManager.this.e.set(true);
                Set f = HomeDataManager.this.k();
                if (!f.isEmpty()) {
                    int i = 0;
                    while (true) {
                        if (i >= HomeDataManager.this.c.size()) {
                            break;
                        }
                        Home home = (Home) HomeDataManager.this.c.get(i);
                        if (home.p() && home.i() != null) {
                            home.i().clear();
                            home.i().addAll(f);
                            break;
                        }
                        i++;
                    }
                }
                HomeDataManager.this.j();
                a(second);
                if (GlobalSetting.u) {
                    StringBuilder sb2 = new StringBuilder();
                    for (Home q2 : HomeDataManager.this.c) {
                        sb2.append(q2.q());
                        sb2.append("    ");
                    }
                    String str2 = HomeManager.D;
                    LogUtilGrey.a(str2, "Consumer:homeOrder after " + sb2.toString());
                }
                HomeDataManager.this.a(HomeManager.x, ErrorCode.SUCCESS.getCode());
                long unused4 = HomeDataManager.this.h = System.currentTimeMillis();
                if (third.booleanValue()) {
                    Observable.combineLatest(Observable.just(HomeDataManager.this.c), OrderCompat.f18244a.a((List<? extends Home>) HomeDataManager.this.c, false), new BiFunction<List<Home>, List<HomeOrder>, Triple<List<Home>, List<HomeOrder>, Boolean>>() {
                        /* renamed from: a */
                        public Triple<List<Home>, List<HomeOrder>, Boolean> apply(List<Home> list, List<HomeOrder> list2) throws Exception {
                            return new Triple<>(HomeDataManager.this.a(list, list2), list2, false);
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
            }

            private void a(List<HomeOrder> list) {
                for (HomeOrder next : list) {
                    Home f = HomeDataManager.this.f(next.a());
                    if (f != null && f.p()) {
                        boolean z = false;
                        if (!next.b()) {
                            ArrayList<String> a2 = next.d().a();
                            List<GridViewData> y = HomeManager.this.y(next.a());
                            ArrayList<String> arrayList = new ArrayList<>();
                            for (GridViewData next2 : y) {
                                if (next2 != null) {
                                    arrayList.add(next2.b.did);
                                }
                            }
                            ArrayList arrayList2 = null;
                            if (a2.isEmpty() || (a2.size() == 1 && TextUtils.equals("-", a2.get(0)))) {
                                LogUtil.a(HomeManager.T, "setDefaultOrderIfNeeded: never sort");
                                if (arrayList.size() > 1) {
                                    arrayList2 = new ArrayList();
                                    for (String str : arrayList) {
                                        if (TextUtils.equals(str, PhoneIRDevice.f13562a)) {
                                            str = CommonUseDeviceDataManager.g;
                                        }
                                        arrayList2.add(str);
                                    }
                                }
                            } else {
                                HashSet hashSet = new HashSet(a2);
                                if (!hashSet.containsAll(arrayList)) {
                                    LogUtil.a(HomeManager.T, "setDefaultOrderIfNeeded: serverOrderInsufficient sort");
                                    arrayList2 = new ArrayList(a2);
                                    for (String str2 : arrayList) {
                                        if (TextUtils.equals(str2, PhoneIRDevice.f13562a)) {
                                            str2 = CommonUseDeviceDataManager.g;
                                        }
                                        if (!hashSet.contains(str2)) {
                                            arrayList2.add(str2);
                                        }
                                    }
                                }
                            }
                            if (arrayList2 != null) {
                                if (GlobalSetting.u) {
                                    String str3 = HomeManager.D;
                                    LogUtilGrey.a(str3, "setDefaultOrderIfNeeded: front, homeId: " + next.a() + " ; orderOnServer: " + Arrays.deepToString(a2.toArray()) + " ; upload: " + Arrays.deepToString(arrayList2.toArray()));
                                }
                                CommonUseDeviceDataManager.a().a((List<String>) arrayList2, HomeDataManager.this.f(next.a()));
                            }
                        }
                        if (next.c() || !next.b()) {
                            ArrayList<RoomOrder> e = next.e();
                            List<Room> d = f.d();
                            if (d != null && !d.isEmpty()) {
                                Iterator<RoomOrder> it = e.iterator();
                                boolean z2 = false;
                                while (it.hasNext()) {
                                    RoomOrder next3 = it.next();
                                    if (z2) {
                                        break;
                                    }
                                    Iterator<Room> it2 = d.iterator();
                                    while (true) {
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        Room next4 = it2.next();
                                        if (next4 != null && TextUtils.equals(next3.a(), next4.d())) {
                                            HashSet hashSet2 = new HashSet(next3.b());
                                            ArrayList arrayList3 = new ArrayList(next4.h());
                                            HashSet hashSet3 = new HashSet(arrayList3);
                                            if (hashSet3.size() > 1 && !hashSet2.containsAll(hashSet3)) {
                                                if (GlobalSetting.u) {
                                                    String str4 = HomeManager.D;
                                                    LogUtilGrey.a(str4, "setDefaultOrderIfNeeded: existNoOrderedRoom, homeId: " + next.a() + " ;orderOnServer: " + Arrays.deepToString(next3.b().toArray()) + " ;dids: " + Arrays.deepToString(arrayList3.toArray()));
                                                }
                                                z2 = true;
                                            }
                                        }
                                    }
                                }
                                List<Device> e2 = MultiHomeDeviceManager.a().e();
                                if (e2.size() > 1) {
                                    List<String> b = HomeRoomSortUtil.b(next.a(), HomeManager.e);
                                    ArrayList arrayList4 = new ArrayList();
                                    for (Device next5 : e2) {
                                        if (next5 != null) {
                                            arrayList4.add(next5.did);
                                        }
                                    }
                                    if (arrayList4.size() > 1 && !new HashSet(b).containsAll(arrayList4)) {
                                        Room h = HomeManager.a().h(HomeManager.e);
                                        ArrayList arrayList5 = new ArrayList();
                                        for (Device next6 : e2) {
                                            if (next6 != null) {
                                                arrayList5.add(next6.did);
                                            }
                                        }
                                        h.a((List<String>) arrayList5);
                                        if (GlobalSetting.u) {
                                            String str5 = HomeManager.D;
                                            LogUtilGrey.a(str5, "setDefaultOrderIfNeeded: share, homeId: " + next.a() + " ;orderOnServer: " + Arrays.deepToString(b.toArray()) + " ;dids: " + Arrays.deepToString(arrayList5.toArray()));
                                        }
                                        z = true;
                                    }
                                }
                                if (z2 || z) {
                                    HomeDataManager.this.a(next.a(), d);
                                    if (GlobalSetting.u) {
                                        String str6 = HomeManager.D;
                                        LogUtilGrey.a(str6, "setDefaultOrderIfNeeded: room, homeId: " + next.a());
                                    }
                                }
                                Iterator<CateOrder> it3 = next.f().iterator();
                                while (it3.hasNext()) {
                                    CateOrder next7 = it3.next();
                                    ArrayList arrayList6 = new ArrayList(SmartHomeDeviceHelper.a().b().e(next.a(), next7.a()));
                                    HashSet hashSet4 = new HashSet(next7.b());
                                    if (arrayList6.size() > 1 && !hashSet4.containsAll(arrayList6)) {
                                        HomeDataManager.this.a(next.a(), next7.a(), (List<String>) arrayList6);
                                        if (GlobalSetting.u) {
                                            String str7 = HomeManager.D;
                                            LogUtilGrey.a(str7, "setDefaultOrderIfNeeded: cate, homeId: " + next.a() + " ;orderOnServer: " + Arrays.deepToString(next7.b().toArray()) + " ;dids: " + Arrays.deepToString(arrayList6.toArray()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        private boolean j = true;
        /* access modifiers changed from: private */
        public boolean k = true;

        /* access modifiers changed from: private */
        public static /* synthetic */ String h(String str) {
            return str;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String i(String str) {
            return str;
        }

        public void a(int i2, Device device) {
        }

        public void b(int i2) {
        }

        public HomeDataManager() {
        }

        public void a() {
            this.b = new HashMap();
            this.c = new ArrayList();
            this.d.set(false);
            this.e.set(false);
            this.g = false;
            this.f.set(true);
        }

        public boolean b() {
            return this.g;
        }

        public void c() {
            LogUtilGrey.a(HomeManager.T, "startSyncIfNeeded: load local: ");
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object... objArr) {
                    try {
                        String c = SharePrefsManager.c(SHApplication.getAppContext(), HomeManager.s, HomeManager.ad, "{}");
                        if (GlobalSetting.u) {
                            LogUtilGrey.a("zxtzxt", "loadLocalHomeCache: " + c);
                        }
                        HomeDataManager.this.a(new JSONObject(c), !HomeDataManager.this.e.get());
                        HomeManager.this.C(HomeManager.this.Y);
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }, new Object[0]);
            String M = HomeManager.T;
            LogUtilGrey.a(M, "startSyncIfNeeded: load start: mSynchronized: " + this.e.get() + " ;mSynchronizing: " + this.d.get());
            if (!this.e.get() && !this.d.get()) {
                LogUtilGrey.a(HomeManager.T, "startSyncIfNeeded: load net: ");
                h();
            }
        }

        private List<List<Room>> i() {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.b.values());
            return arrayList;
        }

        public Room a(String str) {
            List<String> h2;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            for (String str2 : this.b.keySet()) {
                List list = this.b.get(str2);
                if (list != null) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Room room = (Room) list.get(i2);
                        if (!(room == null || (h2 = room.h()) == null || h2.size() == 0)) {
                            if (GlobalSetting.q) {
                                String M = HomeManager.T;
                                LogUtil.a(M, "getRoomByDid:" + room.i().toString());
                            }
                            if (h2.contains(str)) {
                                return room;
                            }
                        }
                    }
                    continue;
                }
            }
            return null;
        }

        public Map<String, Home> d() {
            List<String> h2;
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.b.entrySet()) {
                List list = (List) next.getValue();
                Home f2 = f((String) next.getKey());
                if (!(list == null || f2 == null)) {
                    List<String> m = f2.m();
                    if (m != null) {
                        for (String put : m) {
                            hashMap.put(put, f2);
                        }
                    }
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Room room = (Room) list.get(i2);
                        if (!(room == null || (h2 = room.h()) == null)) {
                            for (String put2 : h2) {
                                hashMap.put(put2, f2);
                            }
                        }
                    }
                }
            }
            return hashMap;
        }

        public Home b(String str) {
            List<String> h2;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            for (Map.Entry next : this.b.entrySet()) {
                List list = (List) next.getValue();
                Home f2 = f((String) next.getKey());
                if (!(list == null || f2 == null)) {
                    List<String> m = f2.m();
                    if (m != null && m.contains(str)) {
                        return f2;
                    }
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Room room = (Room) list.get(i2);
                        if (!(room == null || (h2 = room.h()) == null || h2.size() == 0)) {
                            if (GlobalSetting.q) {
                                String M = HomeManager.T;
                                LogUtil.a(M, "getRoomByDid:" + room.i().toString());
                            }
                            if (h2.contains(str)) {
                                return f2;
                            }
                        }
                    }
                    continue;
                }
            }
            return null;
        }

        public Room c(String str) {
            ArrayList<List> arrayList = new ArrayList<>(this.b.values());
            if (arrayList.isEmpty()) {
                return null;
            }
            for (List list : arrayList) {
                if (list != null) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        Room room = (Room) list.get(i2);
                        if (room != null && TextUtils.equals(str, room.e())) {
                            return room;
                        }
                    }
                    continue;
                }
            }
            return null;
        }

        public Room d(String str) {
            Home m = HomeManager.this.m();
            if (m == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(m.d());
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Room room = (Room) arrayList.get(i2);
                if (room != null && TextUtils.equals(str, room.e())) {
                    return room;
                }
            }
            return null;
        }

        public void a(String str, String str2, List<String> list) {
            Home m;
            if (TextUtils.isEmpty(str)) {
                Log.e(HomeManager.T, "saveOrders: homeID==null mCurrentHomeId==" + HomeManager.a().Y + " mHomeMapSize==" + this.b.size());
            } else if (!TextUtils.isEmpty(str2) && (m = HomeManager.this.m()) != null && m.p()) {
                String str3 = "";
                if (DeviceTagManager.W) {
                    DeviceTagInterface.Category i2 = SmartHomeDeviceHelper.a().b().i(str2);
                    if (i2 != null) {
                        str3 = i2.b;
                    }
                    str2 = str2 + JSMethod.NOT_SET;
                }
                try {
                    List list2 = (List) HomeRoomSortUtil.g.get(str);
                    if (list2 == null) {
                        list2 = new ArrayList();
                        HomeRoomSortUtil.g.put(str, list2);
                    }
                    Pair pair = null;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= list2.size()) {
                            break;
                        }
                        Pair pair2 = (Pair) list2.get(i3);
                        if (pair2 != null) {
                            if (TextUtils.equals((CharSequence) pair2.first, str2)) {
                                pair = pair2;
                                break;
                            }
                        }
                        i3++;
                    }
                    if (pair == null) {
                        list2.add(new Pair(str2, list));
                    } else {
                        ((List) pair.second).clear();
                        ((List) pair.second).addAll(list);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                OrderCompat.f18244a.a(str, str2, str3, list);
                a(HomeManager.B, ErrorCode.SUCCESS.getCode());
            }
        }

        public void a(String str, List<Room> list) {
            Home m = HomeManager.a().m();
            if (m != null && m.p()) {
                if (TextUtils.isEmpty(str)) {
                    if (!this.b.isEmpty() || TextUtils.isEmpty(HomeManager.a().Y)) {
                        Iterator<String> it = this.b.keySet().iterator();
                        if (it.hasNext()) {
                            str = it.next();
                        }
                    } else {
                        this.b.put(HomeManager.a().Y, list);
                        return;
                    }
                }
                if (TextUtils.isEmpty(str) || list == null) {
                    String M = HomeManager.T;
                    Log.e(M, "saveOrders: homeID==null mCurrentHomeId==" + HomeManager.a().Y + " mHomeMapSize==" + this.b.size());
                    return;
                }
                try {
                    ArrayList arrayList = new ArrayList(list);
                    this.b.put(str, list);
                    Home f2 = f(str);
                    List d2 = f2.d();
                    if (d2 == null) {
                        d2 = new ArrayList();
                        f2.a((List<Room>) d2);
                    }
                    d2.clear();
                    d2.addAll(arrayList);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                ArrayList arrayList2 = new ArrayList(list);
                HomeRoomSortUtil.c(f(str), arrayList2);
                OrderCompat.f18244a.a(str, (List<? extends Room>) arrayList2);
                a(HomeManager.B, ErrorCode.SUCCESS.getCode());
            }
        }

        /* access modifiers changed from: private */
        public void a(Room room, List<String> list, List<String> list2) {
            Room e2;
            List<String> h2;
            if (room != null && (e2 = e(room.d())) != null) {
                Home f2 = f(room.f());
                if (f2 != null) {
                    if (list != null) {
                        List<Home> f3 = f();
                        for (int i2 = 0; i2 < f3.size(); i2++) {
                            f3.get(i2).n().removeAll(list);
                            if (f3.get(i2).i() != null) {
                                f3.get(i2).i().removeAll(list);
                            }
                        }
                    }
                    if (list2 != null) {
                        f2.n().addAll(list2);
                    }
                }
                e2.a(room);
                List h3 = e2.h();
                if (h3 == null) {
                    h3 = new ArrayList();
                }
                if (list != null && !list.isEmpty()) {
                    for (String next : list) {
                        if (!h3.contains(next)) {
                            h3.add(0, next);
                        }
                    }
                }
                if (list2 != null) {
                    h3.removeAll(list2);
                }
                e2.a((List<String>) h3);
                if (list != null && !list.isEmpty()) {
                    for (List next2 : i()) {
                        for (int i3 = 0; i3 < next2.size(); i3++) {
                            Room room2 = (Room) next2.get(i3);
                            if (!(room2 == null || e2 == room2 || TextUtils.equals(e2.d(), room2.d()) || (h2 = room2.h()) == null || h2.isEmpty())) {
                                h2.removeAll(list);
                            }
                        }
                    }
                }
                if (GlobalSetting.q) {
                    String M = HomeManager.T;
                    Log.d(M, "addAndDeleteRoomDevice:" + e2.i().toString());
                }
            }
        }

        public void a(Home home, Room room, Device device, IHomeOperationCallback iHomeOperationCallback) {
            try {
                Room a2 = a(device.did);
                if (a2 != null) {
                    a2.h().remove(device.did);
                }
                Home b2 = b(device.did);
                if (b2 != null) {
                    b2.m().remove(device.did);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            final Room room2 = room;
            final Device device2 = device;
            final Home home2 = home;
            final IHomeOperationCallback iHomeOperationCallback2 = iHomeOperationCallback;
            RemoteFamilyApi.a().a(home, room, device, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        if (room2 != null) {
                            List h = room2.h();
                            if (h == null) {
                                h = new ArrayList();
                                room2.a((List<String>) h);
                            }
                            h.add(0, device2.did);
                        } else if (home2 != null) {
                            List m = home2.m();
                            if (m == null) {
                                m = new ArrayList();
                                home2.b((List<String>) m);
                            }
                            m.add(0, device2.did);
                        }
                        HomeDataManager.this.j();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (iHomeOperationCallback2 != null) {
                        iHomeOperationCallback2.a();
                    }
                }

                public void onFailure(Error error) {
                    if (iHomeOperationCallback2 != null) {
                        iHomeOperationCallback2.a(2, error);
                    }
                }
            });
        }

        public void a(Home home, Room room, List<String> list, IHomeOperationCallback iHomeOperationCallback) {
            if ((home != null || room != null) && list != null && !list.isEmpty()) {
                final Room room2 = room;
                final List<String> list2 = list;
                final Home home2 = home;
                final IHomeOperationCallback iHomeOperationCallback2 = iHomeOperationCallback;
                RemoteFamilyApi.a().a(home, room, list, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        try {
                            if (room2 != null) {
                                List h = room2.h();
                                if (h == null) {
                                    h = new ArrayList();
                                    room2.a((List<String>) h);
                                }
                                h.addAll(0, list2);
                            } else {
                                List m = home2.m();
                                if (m == null) {
                                    m = new ArrayList();
                                    home2.b((List<String>) m);
                                }
                                m.addAll(0, list2);
                            }
                            HomeDataManager.this.j();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (iHomeOperationCallback2 != null) {
                            iHomeOperationCallback2.a();
                        }
                    }

                    public void onFailure(Error error) {
                        if (iHomeOperationCallback2 != null) {
                            iHomeOperationCallback2.a(2, error);
                        }
                    }
                });
            }
        }

        public void a(final Home home, final String str, final IHomeOperationCallback iHomeOperationCallback) {
            RemoteFamilyApi.a().a(home, str, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        if (TextUtils.equals(str, "delete") && jSONObject.getInt("affect") == 1) {
                            HomeDataManager.this.b.remove(home.j());
                            HomeDataManager.this.c.remove(home);
                            HomeDataManager.this.j();
                            if (TextUtils.equals(home.j(), HomeManager.this.Y)) {
                                HomeManager.this.a(((Home) HomeDataManager.this.c.get(0)).j(), (AsyncCallback) null);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (iHomeOperationCallback != null) {
                        iHomeOperationCallback.a();
                    }
                }

                public void onFailure(Error error) {
                    if (iHomeOperationCallback != null) {
                        iHomeOperationCallback.a(2, error);
                    }
                }
            });
        }

        public void a(Room room, List<String> list, List<String> list2, IHomeOperationCallback iHomeOperationCallback) {
            a(room, list, list2);
            final Room room2 = room;
            final List<String> list3 = list;
            final List<String> list4 = list2;
            final IHomeOperationCallback iHomeOperationCallback2 = iHomeOperationCallback;
            Observable.create(new ObservableOnSubscribe<JSONObject>() {
                public void subscribe(final ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                    RemoteFamilyApi.a().a(SHApplication.getAppContext(), room2, (List<String>) list3, (List<String>) list4, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (!observableEmitter.isDisposed()) {
                                HomeDataManager.this.a(room2, (List<String>) list3);
                                if (iHomeOperationCallback2 != null) {
                                    iHomeOperationCallback2.a();
                                }
                                HomeDataManager.this.j();
                                HomeDataManager.this.a(HomeManager.A, ErrorCode.SUCCESS.getCode());
                                observableEmitter.onComplete();
                            }
                        }

                        public void onFailure(Error error) {
                            if (!observableEmitter.isDisposed()) {
                                if (iHomeOperationCallback2 != null) {
                                    iHomeOperationCallback2.a(2, error);
                                } else if (error == null || error.a() != -35) {
                                    ToastUtil.a((int) R.string.add_failed);
                                } else {
                                    ToastUtil.a((int) R.string.name_repeat);
                                }
                                observableEmitter.onComplete();
                            }
                        }
                    });
                }
            }).subscribeOn(Schedulers.io()).subscribe(new Observer<JSONObject>() {
                /* renamed from: a */
                public void onNext(JSONObject jSONObject) {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                }

                public void onComplete() {
                    HomeRoomSortUtil.d(HomeManager.this.m(), (List) HomeDataManager.this.b.get(HomeManager.this.Y));
                }
            });
        }

        /* access modifiers changed from: private */
        public void a(Room room, List<String> list) {
            Home f2;
            if (list != null && !list.isEmpty() && (f2 = f(room.f())) != null) {
                CommonUseDeviceDataManager.a().b(f2.j(), list);
            }
        }

        public void a(final Room room, final IHomeOperationCallback iHomeOperationCallback) {
            Observable.create(new ObservableOnSubscribe<JSONObject>() {
                public void subscribe(final ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                    RemoteFamilyApi.a().a(SHApplication.getAppContext(), room, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (!observableEmitter.isDisposed()) {
                                HomeDataManager.this.g(room.f()).remove(room);
                                try {
                                    Home f = HomeDataManager.this.f(room.f());
                                    f.m().addAll(room.h());
                                    f.d().remove(room);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                HomeDataManager.this.j();
                                HomeDataManager.this.a(HomeManager.y, ErrorCode.SUCCESS.getCode());
                                if (iHomeOperationCallback != null) {
                                    iHomeOperationCallback.a();
                                }
                                observableEmitter.onComplete();
                            }
                        }

                        public void onFailure(Error error) {
                            if (!observableEmitter.isDisposed()) {
                                int i = 0;
                                if (error.a() != -1 || !NetworkUtils.c()) {
                                    if (iHomeOperationCallback != null) {
                                        iHomeOperationCallback.a(0, error);
                                    }
                                    observableEmitter.onComplete();
                                    return;
                                }
                                List<Room> g = HomeDataManager.this.g(room.f());
                                if (g != null) {
                                    while (true) {
                                        if (i >= g.size()) {
                                            break;
                                        } else if (TextUtils.equals(g.get(i).d(), room.d())) {
                                            g.remove(i);
                                            break;
                                        } else {
                                            i++;
                                        }
                                    }
                                }
                                HomeDataManager.this.j();
                                HomeDataManager.this.a(HomeManager.y, ErrorCode.SUCCESS.getCode());
                                if (iHomeOperationCallback != null) {
                                    iHomeOperationCallback.a();
                                }
                                observableEmitter.onComplete();
                            }
                        }
                    });
                }
            }).subscribeOn(Schedulers.io()).subscribe(new Observer<JSONObject>() {
                /* renamed from: a */
                public void onNext(JSONObject jSONObject) {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                }

                public void onComplete() {
                    HomeRoomSortUtil.d(HomeManager.this.m(), (List) HomeDataManager.this.b.get(HomeManager.this.Y));
                }
            });
        }

        public void a(final List<String> list, final IHomeOperationCallback iHomeOperationCallback) {
            Observable.create(new ObservableOnSubscribe<JSONObject>() {
                public void subscribe(final ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                    RemoteFamilyApi.a().a((List<String>) list, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            List<Room> g;
                            if (!observableEmitter.isDisposed()) {
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < list.size(); i++) {
                                    Room e = HomeDataManager.this.e((String) list.get(i));
                                    if (e != null) {
                                        arrayList.add(e);
                                        try {
                                            Home f = HomeDataManager.this.f(e.f());
                                            f.m().addAll(e.h());
                                            f.d().remove(e);
                                        } catch (Exception e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                }
                                if (arrayList.size() > 0 && (g = HomeDataManager.this.g(((Room) arrayList.get(0)).f())) != null) {
                                    g.removeAll(arrayList);
                                }
                                HomeDataManager.this.j();
                                HomeDataManager.this.a(HomeManager.y, ErrorCode.SUCCESS.getCode());
                                if (iHomeOperationCallback != null) {
                                    iHomeOperationCallback.a();
                                }
                                observableEmitter.onComplete();
                            }
                        }

                        public void onFailure(Error error) {
                            if (!observableEmitter.isDisposed()) {
                                if (error.a() != -1 || !NetworkUtils.c()) {
                                    if (iHomeOperationCallback != null) {
                                        iHomeOperationCallback.a(0, error);
                                    }
                                    observableEmitter.onComplete();
                                    return;
                                }
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < list.size(); i++) {
                                    Room e = HomeDataManager.this.e((String) list.get(i));
                                    if (e != null) {
                                        arrayList.add(e);
                                    }
                                }
                                List<Room> e2 = HomeManager.a().e();
                                if (e2 != null) {
                                    e2.removeAll(arrayList);
                                }
                                HomeDataManager.this.j();
                                HomeDataManager.this.a(HomeManager.y, ErrorCode.SUCCESS.getCode());
                                if (iHomeOperationCallback != null) {
                                    iHomeOperationCallback.a();
                                }
                                observableEmitter.onComplete();
                            }
                        }
                    });
                }
            }).subscribeOn(Schedulers.io()).subscribe(new Observer<JSONObject>() {
                /* renamed from: a */
                public void onNext(JSONObject jSONObject) {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(Disposable disposable) {
                }

                public void onComplete() {
                    HomeRoomSortUtil.d(HomeManager.this.m(), (List) HomeDataManager.this.b.get(HomeManager.this.Y));
                }
            });
        }

        public void a(final Home home, final IHomeOperationCallback iHomeOperationCallback) {
            if (HomeManager.n(home.k())) {
                Observable.create(new ObservableOnSubscribe<JSONObject>() {
                    public void subscribe(final ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                        RemoteFamilyApi.a().a(home, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                if (!observableEmitter.isDisposed()) {
                                    if (jSONObject != null && !jSONObject.isNull("result")) {
                                        home.h(jSONObject.optString("result"));
                                        for (int i = 0; i < home.d().size(); i++) {
                                            Room room = home.d().get(i);
                                            String f = room.f();
                                            ((List) HomeDataManager.this.b.get(f)).remove(room);
                                            for (int i2 = 0; i2 < HomeDataManager.this.c.size(); i2++) {
                                                if (TextUtils.equals(((Home) HomeDataManager.this.c.get(i2)).j(), f)) {
                                                    ((Home) HomeDataManager.this.c.get(i2)).d().remove(room);
                                                }
                                            }
                                            room.f(home.j());
                                        }
                                        HomeDataManager.this.b.put(home.j(), home.d());
                                        if (!HomeDataManager.this.c.contains(home)) {
                                            HomeDataManager.this.c.add(home);
                                        }
                                        HomeDataManager.this.j();
                                        if (iHomeOperationCallback != null) {
                                            iHomeOperationCallback.a();
                                        }
                                        HomeDataManager.this.a(HomeManager.C, ErrorCode.SUCCESS.getCode());
                                    }
                                    observableEmitter.onComplete();
                                }
                            }

                            public void onFailure(Error error) {
                                if (!observableEmitter.isDisposed()) {
                                    if (iHomeOperationCallback != null) {
                                        iHomeOperationCallback.a(2, error);
                                    }
                                    observableEmitter.onComplete();
                                }
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io()).subscribe(new Observer<Object>() {
                    public void onError(Throwable th) {
                    }

                    public void onNext(Object obj) {
                    }

                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onComplete() {
                        HomeRoomSortUtil.d(home, home.d());
                    }
                });
            } else if (iHomeOperationCallback != null) {
                iHomeOperationCallback.a(1, (Error) null);
            }
        }

        public void a(String str, String str2, List<String> list, String str3, IHomeOperationCallback iHomeOperationCallback) {
            if (HomeManager.n(str2)) {
                final String str4 = str2;
                final String str5 = str;
                final List<String> list2 = list;
                final String str6 = str3;
                final IHomeOperationCallback iHomeOperationCallback2 = iHomeOperationCallback;
                Observable.create(new ObservableOnSubscribe<JSONObject>() {
                    public void subscribe(final ObservableEmitter<JSONObject> observableEmitter) throws Exception {
                        RemoteFamilyApi.a().a(SHApplication.getAppContext(), str4, str5, (List<String>) list2, str6, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                            /* renamed from: a */
                            public void onSuccess(JSONObject jSONObject) {
                                if (!observableEmitter.isDisposed()) {
                                    if (jSONObject != null) {
                                        Room room = new Room(str4);
                                        room.d(jSONObject.optString("roomid"));
                                        room.a((List<String>) list2);
                                        room.f(str5);
                                        room.a(str6);
                                        List list = (List) HomeDataManager.this.b.get(str5);
                                        if (list == null) {
                                            list = new ArrayList();
                                        }
                                        list.add(0, room);
                                        HomeDataManager.this.b.put(str5, list);
                                        try {
                                            HomeDataManager.this.f(room.f()).a((List<Room>) list);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        HomeDataManager.this.j();
                                        HomeDataManager.this.a(room, (List<String>) list2, (List<String>) null);
                                        HomeDataManager.this.a(room, (List<String>) list2);
                                    }
                                    HomeDataManager.this.a(HomeManager.z, ErrorCode.SUCCESS.getCode());
                                    if (iHomeOperationCallback2 != null) {
                                        iHomeOperationCallback2.a();
                                    }
                                    observableEmitter.onComplete();
                                }
                            }

                            public void onFailure(Error error) {
                                if (!observableEmitter.isDisposed()) {
                                    if (iHomeOperationCallback2 != null) {
                                        iHomeOperationCallback2.a(2, error);
                                    } else if (error == null || error.a() != -35) {
                                        ToastUtil.a((int) R.string.add_failed);
                                    } else {
                                        ToastUtil.a((int) R.string.name_repeat);
                                    }
                                    observableEmitter.onComplete();
                                }
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io()).subscribe(new Observer<Object>() {
                    public void onError(Throwable th) {
                    }

                    public void onNext(Object obj) {
                    }

                    public void onSubscribe(Disposable disposable) {
                    }

                    public void onComplete() {
                        HomeRoomSortUtil.d(HomeManager.this.m(), (List) HomeDataManager.this.b.get(HomeManager.this.Y));
                    }
                });
            } else if (iHomeOperationCallback != null) {
                iHomeOperationCallback.a(1, (Error) null);
            }
        }

        public Room e(String str) {
            Map<String, List<Room>> map = this.b;
            Room room = null;
            if (map != null && !map.isEmpty()) {
                for (List next : map.values()) {
                    if (next != null && !next.isEmpty()) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= next.size()) {
                                break;
                            }
                            Room room2 = (Room) next.get(i2);
                            if (room2 != null && TextUtils.equals(room2.d(), str)) {
                                room = room2;
                                break;
                            }
                            i2++;
                        }
                    }
                }
            }
            return room;
        }

        public int e() {
            return this.c.size();
        }

        public Home f(String str) {
            List<Home> list = this.c;
            if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
                return null;
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                Home home = list.get(i2);
                if (home != null && TextUtils.equals(home.j(), str)) {
                    return home;
                }
            }
            return null;
        }

        public List<Home> f() {
            return this.c;
        }

        /* JADX WARNING: Removed duplicated region for block: B:20:0x0068 A[SYNTHETIC, Splitter:B:20:0x0068] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x007a A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.xiaomi.smarthome.homeroom.model.Home g() {
            /*
                r8 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r1 = 2147483647(0x7fffffff, float:NaN)
                r0.append(r1)
                java.lang.String r2 = ""
                r0.append(r2)
                java.lang.String r0 = r0.toString()
                r2 = 0
                r3 = 0
            L_0x0016:
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r4 = r8.c
                int r4 = r4.size()
                if (r3 >= r4) goto L_0x007d
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r4 = r8.c
                java.lang.Object r4 = r4.get(r3)
                com.xiaomi.smarthome.homeroom.model.Home r4 = (com.xiaomi.smarthome.homeroom.model.Home) r4
                java.lang.String r5 = r4.j()
                boolean r5 = android.text.TextUtils.isEmpty(r5)
                if (r5 == 0) goto L_0x0031
                goto L_0x007a
            L_0x0031:
                java.lang.String r5 = r4.j()     // Catch:{ Exception -> 0x005e }
                int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Exception -> 0x005e }
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x005e }
                int r6 = r5.intValue()     // Catch:{ Exception -> 0x005e }
                if (r6 >= r1) goto L_0x007a
                int r2 = r5.intValue()     // Catch:{ Exception -> 0x005d }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005c }
                r1.<init>()     // Catch:{ Exception -> 0x005c }
                r1.append(r2)     // Catch:{ Exception -> 0x005c }
                java.lang.String r5 = ""
                r1.append(r5)     // Catch:{ Exception -> 0x005c }
                java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x005c }
                r0 = r1
                r1 = r2
                r2 = r4
                goto L_0x007a
            L_0x005c:
                r1 = r2
            L_0x005d:
                r2 = r4
            L_0x005e:
                java.lang.String r5 = r4.j()
                int r5 = r0.compareTo(r5)
                if (r5 <= 0) goto L_0x007a
                java.lang.String r0 = r4.j()     // Catch:{ Exception -> 0x0071 }
                int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0071 }
                goto L_0x0072
            L_0x0071:
                r0 = r1
            L_0x0072:
                java.lang.String r1 = r4.j()
                r2 = r4
                r7 = r1
                r1 = r0
                r0 = r7
            L_0x007a:
                int r3 = r3 + 1
                goto L_0x0016
            L_0x007d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.HomeDataManager.g():com.xiaomi.smarthome.homeroom.model.Home");
        }

        /* access modifiers changed from: private */
        public void j() {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Home next : this.c) {
                if (next != null && !TextUtils.isEmpty(next.j())) {
                    jSONArray.put(next.q());
                }
            }
            try {
                jSONObject.put("homelist", jSONArray);
                if (GlobalSetting.u) {
                    LogUtilGrey.a("zxtzxt", "dumpHomeRoomToLocal: " + jSONArray);
                    LogUtilGrey.a("zxtzxt", "dumpHomeRoomToLocal: " + Log.getStackTraceString(new Exception()));
                }
                SharePrefsManager.b(SHApplication.getAppContext(), HomeManager.s, HomeManager.ad, jSONObject.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public List<Room> g(String str) {
            if (SHApplication.getStateNotifier().a() != 4) {
                return new ArrayList();
            }
            if (!this.e.get()) {
                this.d.get();
            }
            if (str == null) {
                return null;
            }
            return this.b.get(str) == null ? new ArrayList() : this.b.get(str);
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x014b, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void a(org.json.JSONObject r7, boolean r8) {
            /*
                r6 = this;
                monitor-enter(r6)
                if (r7 != 0) goto L_0x0005
                monitor-exit(r6)
                return
            L_0x0005:
                java.lang.String r0 = "homelist"
                boolean r0 = r7.isNull(r0)     // Catch:{ all -> 0x014c }
                r1 = 0
                if (r0 != 0) goto L_0x003b
                java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x014c }
                r0.<init>()     // Catch:{ all -> 0x014c }
                java.lang.String r2 = "homelist"
                org.json.JSONArray r2 = r7.optJSONArray(r2)     // Catch:{ all -> 0x014c }
                r3 = 0
            L_0x001a:
                int r4 = r2.length()     // Catch:{ all -> 0x014c }
                if (r3 >= r4) goto L_0x0036
                org.json.JSONObject r4 = r2.optJSONObject(r3)     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.homeroom.model.Home r4 = com.xiaomi.smarthome.homeroom.model.Home.a((org.json.JSONObject) r4)     // Catch:{ all -> 0x014c }
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r5 = r6.c     // Catch:{ all -> 0x014c }
                boolean r5 = r5.contains(r4)     // Catch:{ all -> 0x014c }
                if (r5 != 0) goto L_0x0033
                r0.add(r4)     // Catch:{ all -> 0x014c }
            L_0x0033:
                int r3 = r3 + 1
                goto L_0x001a
            L_0x0036:
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r2 = r6.c     // Catch:{ all -> 0x014c }
                r2.addAll(r0)     // Catch:{ all -> 0x014c }
            L_0x003b:
                r0 = 0
            L_0x003c:
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r2 = r6.c     // Catch:{ all -> 0x014c }
                int r2 = r2.size()     // Catch:{ all -> 0x014c }
                if (r0 >= r2) goto L_0x006f
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r2 = r6.c     // Catch:{ all -> 0x014c }
                java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.homeroom.model.Home r2 = (com.xiaomi.smarthome.homeroom.model.Home) r2     // Catch:{ all -> 0x014c }
                if (r2 == 0) goto L_0x006c
                java.lang.String r3 = r2.j()     // Catch:{ all -> 0x014c }
                boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x014c }
                if (r3 != 0) goto L_0x006c
                java.util.List r3 = r2.d()     // Catch:{ all -> 0x014c }
                if (r3 != 0) goto L_0x005f
                goto L_0x006c
            L_0x005f:
                java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.homeroom.model.Room>> r3 = r6.b     // Catch:{ all -> 0x014c }
                java.lang.String r4 = r2.j()     // Catch:{ all -> 0x014c }
                java.util.List r2 = r2.d()     // Catch:{ all -> 0x014c }
                r3.put(r4, r2)     // Catch:{ all -> 0x014c }
            L_0x006c:
                int r0 = r0 + 1
                goto L_0x003c
            L_0x006f:
                boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ all -> 0x014c }
                if (r0 != 0) goto L_0x0077
                boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.q     // Catch:{ all -> 0x014c }
                if (r0 == 0) goto L_0x00c3
            L_0x0077:
                java.lang.String r0 = com.xiaomi.smarthome.homeroom.HomeManager.T     // Catch:{ all -> 0x014c }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
                r2.<init>()     // Catch:{ all -> 0x014c }
                java.lang.String r3 = "processHomeRoomContent result="
                r2.append(r3)     // Catch:{ all -> 0x014c }
                r3 = 0
                if (r7 != 0) goto L_0x008a
                r7 = r3
                goto L_0x0092
            L_0x008a:
                int r7 = r7.length()     // Catch:{ all -> 0x014c }
                java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x014c }
            L_0x0092:
                r2.append(r7)     // Catch:{ all -> 0x014c }
                java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r7)     // Catch:{ all -> 0x014c }
                java.lang.String r7 = com.xiaomi.smarthome.homeroom.HomeManager.T     // Catch:{ all -> 0x014c }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
                r0.<init>()     // Catch:{ all -> 0x014c }
                java.lang.String r2 = "mHomeMap result="
                r0.append(r2)     // Catch:{ all -> 0x014c }
                java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.homeroom.model.Room>> r2 = r6.b     // Catch:{ all -> 0x014c }
                if (r2 != 0) goto L_0x00af
                goto L_0x00b9
            L_0x00af:
                java.util.Map<java.lang.String, java.util.List<com.xiaomi.smarthome.homeroom.model.Room>> r2 = r6.b     // Catch:{ all -> 0x014c }
                int r2 = r2.size()     // Catch:{ all -> 0x014c }
                java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x014c }
            L_0x00b9:
                r0.append(r3)     // Catch:{ all -> 0x014c }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r7, r0)     // Catch:{ all -> 0x014c }
            L_0x00c3:
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r7 = r6.c     // Catch:{ all -> 0x014c }
                boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x014c }
                if (r7 != 0) goto L_0x0101
                com.xiaomi.smarthome.homeroom.HomeManager r7 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x014c }
                java.lang.String r7 = r7.Y     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.homeroom.model.Home r7 = r6.f((java.lang.String) r7)     // Catch:{ all -> 0x014c }
                if (r7 != 0) goto L_0x0101
                com.xiaomi.smarthome.homeroom.HomeManager r7 = com.xiaomi.smarthome.homeroom.HomeManager.a()     // Catch:{ all -> 0x014c }
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r0 = r6.c     // Catch:{ all -> 0x014c }
                java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.homeroom.model.Home r0 = (com.xiaomi.smarthome.homeroom.model.Home) r0     // Catch:{ all -> 0x014c }
                java.lang.String r0 = r0.j()     // Catch:{ all -> 0x014c }
                java.lang.String unused = r7.Y = r0     // Catch:{ all -> 0x014c }
                android.content.Context r7 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ all -> 0x014c }
                java.lang.String r0 = "home_room_transfer_state"
                android.content.SharedPreferences r7 = r7.getSharedPreferences(r0, r1)     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.homeroom.HomeManager r0 = com.xiaomi.smarthome.homeroom.HomeManager.this     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.homeroom.HomeManager r1 = com.xiaomi.smarthome.homeroom.HomeManager.this     // Catch:{ all -> 0x014c }
                java.lang.String r1 = r1.Y     // Catch:{ all -> 0x014c }
                r0.a((android.content.SharedPreferences) r7, (java.lang.String) r1)     // Catch:{ all -> 0x014c }
            L_0x0101:
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r7 = r6.c     // Catch:{ all -> 0x014c }
                boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x014c }
                r7 = r7 ^ 1
                r6.g = r7     // Catch:{ all -> 0x014c }
                java.lang.String r7 = com.xiaomi.smarthome.homeroom.HomeManager.T     // Catch:{ all -> 0x014c }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
                r0.<init>()     // Catch:{ all -> 0x014c }
                java.lang.String r1 = "loadLocalDeviceData: mIsInited: "
                r0.append(r1)     // Catch:{ all -> 0x014c }
                boolean r1 = r6.g     // Catch:{ all -> 0x014c }
                r0.append(r1)     // Catch:{ all -> 0x014c }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r7, r0)     // Catch:{ all -> 0x014c }
                java.lang.String r7 = com.xiaomi.smarthome.homeroom.HomeManager.T     // Catch:{ all -> 0x014c }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
                r0.<init>()     // Catch:{ all -> 0x014c }
                java.lang.String r1 = "loadLocalDeviceData: "
                r0.append(r1)     // Catch:{ all -> 0x014c }
                r0.append(r8)     // Catch:{ all -> 0x014c }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x014c }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r7, r0)     // Catch:{ all -> 0x014c }
                if (r8 == 0) goto L_0x014a
                java.lang.String r7 = "home_room_sync"
                com.xiaomi.smarthome.frame.ErrorCode r8 = com.xiaomi.smarthome.frame.ErrorCode.SUCCESS     // Catch:{ all -> 0x014c }
                int r8 = r8.getCode()     // Catch:{ all -> 0x014c }
                r6.a((java.lang.String) r7, (int) r8)     // Catch:{ all -> 0x014c }
            L_0x014a:
                monitor-exit(r6)
                return
            L_0x014c:
                r7 = move-exception
                monitor-exit(r6)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.HomeDataManager.a(org.json.JSONObject, boolean):void");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x017e, code lost:
            return r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized java.util.Set<java.lang.String> k() {
            /*
                r9 = this;
                monitor-enter(r9)
                java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r1 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ Exception -> 0x0188 }
                java.util.Map r1 = r1.g()     // Catch:{ Exception -> 0x0188 }
                r0.<init>(r1)     // Catch:{ Exception -> 0x0188 }
                boolean r1 = r0.isEmpty()     // Catch:{ Exception -> 0x0188 }
                if (r1 != 0) goto L_0x017f
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r1 = r9.c     // Catch:{ Exception -> 0x0188 }
                if (r1 == 0) goto L_0x017f
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r1 = r9.c     // Catch:{ Exception -> 0x0188 }
                boolean r1 = r1.isEmpty()     // Catch:{ Exception -> 0x0188 }
                if (r1 != 0) goto L_0x017f
                boolean r1 = r9.b()     // Catch:{ Exception -> 0x0188 }
                if (r1 != 0) goto L_0x0028
                goto L_0x017f
            L_0x0028:
                java.util.HashSet r1 = new java.util.HashSet     // Catch:{ Exception -> 0x0188 }
                java.util.Set r2 = r0.keySet()     // Catch:{ Exception -> 0x0188 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0188 }
                boolean r2 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x0188 }
                if (r2 == 0) goto L_0x005f
                java.lang.String r2 = "zxtzxt"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0188 }
                r3.<init>()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r4 = "findFreeDevicesList1: "
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                java.lang.Object[] r4 = r1.toArray()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r4 = java.util.Arrays.deepToString(r4)     // Catch:{ Exception -> 0x0188 }
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r4 = " freeDids:"
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                int r4 = r1.size()     // Catch:{ Exception -> 0x0188 }
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)     // Catch:{ Exception -> 0x0188 }
            L_0x005f:
                java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x0188 }
                java.util.List<com.xiaomi.smarthome.homeroom.model.Home> r3 = r9.c     // Catch:{ Exception -> 0x0188 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0188 }
                r3 = 0
                r4 = 0
            L_0x0068:
                int r5 = r2.size()     // Catch:{ Exception -> 0x0188 }
                if (r4 >= r5) goto L_0x00e3
                java.lang.Object r5 = r2.get(r4)     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.homeroom.model.Home r5 = (com.xiaomi.smarthome.homeroom.model.Home) r5     // Catch:{ Exception -> 0x0188 }
                boolean r6 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x0188 }
                if (r6 == 0) goto L_0x0092
                java.lang.String r6 = "zxtzxt"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0188 }
                r7.<init>()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r8 = "findFreeDevicesList1.1: "
                r7.append(r8)     // Catch:{ Exception -> 0x0188 }
                org.json.JSONObject r8 = r5.q()     // Catch:{ Exception -> 0x0188 }
                r7.append(r8)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r6, r7)     // Catch:{ Exception -> 0x0188 }
            L_0x0092:
                java.util.List r6 = r5.n()     // Catch:{ Exception -> 0x0188 }
                r1.removeAll(r6)     // Catch:{ Exception -> 0x0188 }
                boolean r6 = r5.p()     // Catch:{ Exception -> 0x0188 }
                if (r6 != 0) goto L_0x00af
                com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager r6 = com.xiaomi.smarthome.homeroom.homedevicelist.SharedHomeDeviceManager.b()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r5 = r5.j()     // Catch:{ Exception -> 0x0188 }
                java.util.List r5 = r6.b(r5)     // Catch:{ Exception -> 0x0188 }
                r1.removeAll(r5)     // Catch:{ Exception -> 0x0188 }
                goto L_0x00e0
            L_0x00af:
                java.util.List r5 = r5.d()     // Catch:{ Exception -> 0x0188 }
                if (r5 != 0) goto L_0x00b6
                goto L_0x00e0
            L_0x00b6:
                r6 = 0
            L_0x00b7:
                int r7 = r5.size()     // Catch:{ Exception -> 0x0188 }
                if (r6 >= r7) goto L_0x00e0
                java.lang.Object r7 = r5.get(r6)     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.homeroom.model.Room r7 = (com.xiaomi.smarthome.homeroom.model.Room) r7     // Catch:{ Exception -> 0x0188 }
                if (r7 == 0) goto L_0x00dd
                java.util.List r8 = r7.h()     // Catch:{ Exception -> 0x0188 }
                if (r8 == 0) goto L_0x00dd
                java.util.List r8 = r7.h()     // Catch:{ Exception -> 0x0188 }
                boolean r8 = r8.isEmpty()     // Catch:{ Exception -> 0x0188 }
                if (r8 == 0) goto L_0x00d6
                goto L_0x00dd
            L_0x00d6:
                java.util.List r7 = r7.h()     // Catch:{ Exception -> 0x0188 }
                r1.removeAll(r7)     // Catch:{ Exception -> 0x0188 }
            L_0x00dd:
                int r6 = r6 + 1
                goto L_0x00b7
            L_0x00e0:
                int r4 = r4 + 1
                goto L_0x0068
            L_0x00e3:
                boolean r2 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x0188 }
                if (r2 == 0) goto L_0x0111
                java.lang.String r2 = "zxtzxt"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0188 }
                r3.<init>()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r4 = "findFreeDevicesList2: "
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                java.lang.Object[] r4 = r1.toArray()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r4 = java.util.Arrays.deepToString(r4)     // Catch:{ Exception -> 0x0188 }
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r4 = " freeDids:"
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                int r4 = r1.size()     // Catch:{ Exception -> 0x0188 }
                r3.append(r4)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r2, r3)     // Catch:{ Exception -> 0x0188 }
            L_0x0111:
                boolean r2 = r1.isEmpty()     // Catch:{ Exception -> 0x0188 }
                if (r2 != 0) goto L_0x014f
                java.util.Iterator r2 = r1.iterator()     // Catch:{ Exception -> 0x0188 }
            L_0x011b:
                boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x0188 }
                if (r3 == 0) goto L_0x014f
                java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x0188 }
                java.lang.Object r3 = r0.get(r3)     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3     // Catch:{ Exception -> 0x0188 }
                if (r3 == 0) goto L_0x014b
                boolean r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.e((com.xiaomi.smarthome.device.Device) r3)     // Catch:{ Exception -> 0x0188 }
                if (r4 != 0) goto L_0x014b
                boolean r4 = com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager.a((com.xiaomi.smarthome.device.Device) r3)     // Catch:{ Exception -> 0x0188 }
                if (r4 != 0) goto L_0x014b
                java.util.List<java.lang.String> r4 = com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper.f18123a     // Catch:{ Exception -> 0x0188 }
                java.lang.String r5 = r3.model     // Catch:{ Exception -> 0x0188 }
                boolean r4 = r4.contains(r5)     // Catch:{ Exception -> 0x0188 }
                if (r4 != 0) goto L_0x014b
                java.lang.String r3 = r3.did     // Catch:{ Exception -> 0x0188 }
                boolean r3 = com.xiaomi.smarthome.device.utils.IRDeviceUtil.a((java.lang.String) r3)     // Catch:{ Exception -> 0x0188 }
                if (r3 == 0) goto L_0x011b
            L_0x014b:
                r2.remove()     // Catch:{ Exception -> 0x0188 }
                goto L_0x011b
            L_0x014f:
                boolean r0 = com.xiaomi.smarthome.globalsetting.GlobalSetting.u     // Catch:{ Exception -> 0x0188 }
                if (r0 == 0) goto L_0x017d
                java.lang.String r0 = "zxtzxt"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0188 }
                r2.<init>()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r3 = "findFreeDevicesList3: "
                r2.append(r3)     // Catch:{ Exception -> 0x0188 }
                java.lang.Object[] r3 = r1.toArray()     // Catch:{ Exception -> 0x0188 }
                java.lang.String r3 = java.util.Arrays.deepToString(r3)     // Catch:{ Exception -> 0x0188 }
                r2.append(r3)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r3 = " freeDids:"
                r2.append(r3)     // Catch:{ Exception -> 0x0188 }
                int r3 = r1.size()     // Catch:{ Exception -> 0x0188 }
                r2.append(r3)     // Catch:{ Exception -> 0x0188 }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0188 }
                com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ Exception -> 0x0188 }
            L_0x017d:
                monitor-exit(r9)
                return r1
            L_0x017f:
                java.util.HashSet r0 = new java.util.HashSet     // Catch:{ Exception -> 0x0188 }
                r0.<init>()     // Catch:{ Exception -> 0x0188 }
                monitor-exit(r9)
                return r0
            L_0x0186:
                r0 = move-exception
                goto L_0x0193
            L_0x0188:
                r0 = move-exception
                r0.printStackTrace()     // Catch:{ all -> 0x0186 }
                java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x0186 }
                r0.<init>()     // Catch:{ all -> 0x0186 }
                monitor-exit(r9)
                return r0
            L_0x0193:
                monitor-exit(r9)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.HomeDataManager.k():java.util.Set");
        }

        public void a(int i2) {
            Home home;
            if (i2 == 3) {
                Set<String> k2 = k();
                if (k2 != null && !k2.isEmpty()) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= this.c.size()) {
                            home = null;
                            break;
                        }
                        home = this.c.get(i3);
                        if (home.p() && home.i() != null) {
                            home.i().clear();
                            home.i().addAll(k2);
                            break;
                        }
                        i3++;
                    }
                    if (this.f.get() && this.e.get() && !this.d.get()) {
                        this.f.set(false);
                        j();
                        a(home, (Room) null, (List<String>) new ArrayList(k2), (IHomeOperationCallback) null);
                    }
                } else if (this.f.get() && this.e.get()) {
                    this.f.set(false);
                }
            }
        }

        private Observable<List<Home>> l() {
            return Observable.create(new ObservableOnSubscribe<List<Home>>() {
                public void subscribe(final ObservableEmitter<List<Home>> observableEmitter) throws Exception {
                    HomeManager.this.aj.a((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            String M = HomeManager.T;
                            StringBuilder sb = new StringBuilder();
                            sb.append("getHomeFromServer onSuccess result=");
                            sb.append(jSONObject == null ? null : Integer.valueOf(jSONObject.length()));
                            LogUtilGrey.a(M, sb.toString(), true);
                            if (!observableEmitter.isDisposed()) {
                                ArrayList arrayList = new ArrayList();
                                if (jSONObject != null && !jSONObject.isNull("homelist")) {
                                    JSONArray optJSONArray = jSONObject.optJSONArray("homelist");
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        Home a2 = Home.a(optJSONArray.optJSONObject(i));
                                        if (!arrayList.contains(a2)) {
                                            arrayList.add(a2);
                                        }
                                    }
                                }
                                if (jSONObject != null && !jSONObject.isNull("share_home_list")) {
                                    JSONArray optJSONArray2 = jSONObject.optJSONArray("share_home_list");
                                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                        Home a3 = Home.a(optJSONArray2.optJSONObject(i2));
                                        if (!arrayList.contains(a3)) {
                                            arrayList.add(a3);
                                        }
                                    }
                                }
                                observableEmitter.onNext(arrayList);
                                observableEmitter.onComplete();
                                DeviceMainPageHelper.a(true);
                                if (GlobalSetting.u && arrayList.isEmpty()) {
                                    String M2 = HomeManager.T;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("getHomeFromServer onSuccess homes is empty");
                                    sb2.append(jSONObject);
                                    LogUtilGrey.a(M2, sb2.toString() == null ? "null" : jSONObject.toString());
                                }
                            }
                        }

                        public void onFailure(Error error) {
                            LogUtilGrey.a(HomeManager.T, "getHomeFromServer onFailure", true);
                            if (!observableEmitter.isDisposed()) {
                                try {
                                    observableEmitter.onError(new Exception(error.b()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
            }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends List<Home>>>() {
                /* renamed from: a */
                public ObservableSource<? extends List<Home>> apply(final Throwable th) throws Exception {
                    return new Observable<List<Home>>() {
                        /* access modifiers changed from: protected */
                        public void subscribeActual(Observer<? super List<Home>> observer) {
                            try {
                                JSONObject jSONObject = new JSONObject(SharePrefsManager.c(SHApplication.getAppContext(), HomeManager.s, HomeManager.ad, "{}"));
                                ArrayList arrayList = new ArrayList();
                                if (!jSONObject.isNull("homelist")) {
                                    JSONArray optJSONArray = jSONObject.optJSONArray("homelist");
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        Home a2 = Home.a(optJSONArray.optJSONObject(i));
                                        if (!arrayList.contains(a2)) {
                                            arrayList.add(a2);
                                        }
                                    }
                                }
                                if (!jSONObject.isNull("share_home_list")) {
                                    JSONArray optJSONArray2 = jSONObject.optJSONArray("share_home_list");
                                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                        Home a3 = Home.a(optJSONArray2.optJSONObject(i2));
                                        if (!arrayList.contains(a3)) {
                                            arrayList.add(a3);
                                        }
                                    }
                                }
                                observer.onNext(arrayList);
                                observer.onComplete();
                            } catch (Exception e) {
                                e.printStackTrace();
                                observer.onError(th);
                            }
                        }
                    };
                }
            });
        }

        /* access modifiers changed from: private */
        public void a(String str, int i2) {
            Intent intent = new Intent(HomeManager.t);
            intent.putExtra(HomeManager.v, str);
            intent.putExtra("result_code", i2);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
        }

        @SuppressLint({"CheckResult"})
        public boolean h() {
            Observable<R> observable;
            LogUtilGrey.a("forceUpdateAllData", "startSyncFromServer loginstate=" + SHApplication.getStateNotifier().a() + ",update interval=" + Math.abs(System.currentTimeMillis() - this.h) + ",mSynchronizing=" + this.d.get());
            if (SHApplication.getStateNotifier().a() != 4) {
                return false;
            }
            if (Math.abs(System.currentTimeMillis() - this.h) < 3000) {
                a(HomeManager.x, ErrorCode.SUCCESS.getCode());
                return false;
            } else if (this.d.getAndSet(true)) {
                return false;
            } else {
                final boolean z = this.j;
                if (this.j) {
                    this.j = false;
                }
                if (HomeManager.this.m() != null) {
                    observable = Observable.combineLatest(l(), OrderCompat.f18244a.a((List<? extends Home>) Collections.singletonList(HomeManager.this.m()), z), new BiFunction<List<Home>, List<HomeOrder>, Triple<List<Home>, List<HomeOrder>, Boolean>>() {
                        /* renamed from: a */
                        public Triple<List<Home>, List<HomeOrder>, Boolean> apply(List<Home> list, List<HomeOrder> list2) throws Exception {
                            boolean g = HomeDataManager.this.k;
                            List a2 = HomeDataManager.this.a(list, list2);
                            boolean z = true;
                            if (list.size() == 1 || !g) {
                                z = false;
                            }
                            return new Triple<>(a2, list2, Boolean.valueOf(z));
                        }
                    });
                } else {
                    observable = l().flatMap(new Function<List<Home>, ObservableSource<Triple<List<Home>, List<HomeOrder>, Boolean>>>() {
                        /* renamed from: a */
                        public ObservableSource<Triple<List<Home>, List<HomeOrder>, Boolean>> apply(List<Home> list) throws Exception {
                            new ArrayList();
                            return Observable.combineLatest(Observable.just(list), OrderCompat.f18244a.a((List<? extends Home>) list, z), new BiFunction<List<Home>, List<HomeOrder>, Triple<List<Home>, List<HomeOrder>, Boolean>>() {
                                /* renamed from: a */
                                public Triple<List<Home>, List<HomeOrder>, Boolean> apply(List<Home> list, List<HomeOrder> list2) throws Exception {
                                    return new Triple<>(HomeDataManager.this.a(list, list2), list2, false);
                                }
                            });
                        }
                    });
                }
                LogUtilGrey.a(HomeManager.D, "startSyncFromServer 1");
                observable.doOnNext(new Consumer<Triple<List<Home>, List<HomeOrder>, Boolean>>() {
                    /* renamed from: a */
                    public void accept(Triple<List<Home>, List<HomeOrder>, Boolean> triple) throws Exception {
                        List unused = HomeDataManager.this.c = triple.getFirst();
                        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                        for (int i = 0; i < HomeDataManager.this.c.size(); i++) {
                            Home home = (Home) HomeDataManager.this.c.get(i);
                            if (!TextUtils.isEmpty(home.j()) && home.d() != null) {
                                concurrentHashMap.put(home.j(), home.d());
                            }
                        }
                        Map unused2 = HomeDataManager.this.b = concurrentHashMap;
                        HomeDataManager.this.a(2);
                        boolean unused3 = HomeDataManager.this.g = !HomeDataManager.this.c.isEmpty();
                        String M = HomeManager.T;
                        LogUtilGrey.a(M, "accept: mIsInited: " + HomeDataManager.this.g);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this.i, new Consumer<Throwable>() {
                    /* renamed from: a */
                    public void accept(Throwable th) throws Exception {
                        String M = HomeManager.T;
                        LogUtilGrey.a(M, "accept: " + Log.getStackTraceString(th));
                        HomeDataManager.this.d.set(false);
                        HomeDataManager.this.e.set(true);
                        HomeDataManager.this.a(HomeManager.x, ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                        CommonUseDeviceDataManager.a().a(th);
                        long unused = HomeDataManager.this.h = System.currentTimeMillis();
                    }
                }, new Action() {
                    public void run() throws Exception {
                        LogUtilGrey.a(HomeManager.T, "startSyncFromServer: onComplete");
                        HomeDataManager.this.d.set(false);
                        HomeDataManager.this.e.set(true);
                    }
                });
                return true;
            }
        }

        /* access modifiers changed from: private */
        public List<Home> a(List<Home> list, List<HomeOrder> list2) {
            for (Home next : list) {
                if (GlobalSetting.u) {
                    String str = HomeManager.D;
                    LogUtilGrey.a(str, "reorder: before: " + next.q());
                }
                Iterator<HomeOrder> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HomeOrder next2 = it.next();
                    if (next2.a().equals(next.j())) {
                        for (Room next3 : next.d()) {
                            RoomOrder roomOrder = null;
                            Iterator<RoomOrder> it2 = next2.e().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                RoomOrder next4 = it2.next();
                                if (next3.d().equals(next4.a())) {
                                    roomOrder = next4;
                                    break;
                                }
                            }
                            if (roomOrder != null) {
                                UtilKt.a(roomOrder.b(), next3.h(), $$Lambda$HomeManager$HomeDataManager$yvUa7sILhhqZUi2oaOOoPaLcNFU.INSTANCE, $$Lambda$GTGskhkA0zkxgoMCP8oZc33uZeI.INSTANCE);
                            }
                        }
                        UtilKt.a(next2.e(), next.d(), $$Lambda$o2TZ1YG__7oQf8dZ_J3A6Bmuv50.INSTANCE, $$Lambda$HomeManager$HomeDataManager$jBL0mFysg1g8l_2uttjYOEIPzfw.INSTANCE);
                        List<String> m = next.m();
                        if (m != null && m.size() > 0) {
                            Iterator<RoomOrder> it3 = next2.e().iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    break;
                                }
                                RoomOrder next5 = it3.next();
                                if (TextUtils.equals(HomeManager.d, next5.a())) {
                                    next.b((List<String>) UtilKt.a(next5.b(), m, $$Lambda$HomeManager$HomeDataManager$bOYSIzd_YrvTlVDdOyraQnopDM.INSTANCE, $$Lambda$GTGskhkA0zkxgoMCP8oZc33uZeI.INSTANCE));
                                    break;
                                }
                            }
                        }
                    }
                }
                if (GlobalSetting.u) {
                    String str2 = HomeManager.D;
                    LogUtilGrey.a(str2, "reorder: after: " + next.q());
                }
            }
            return list;
        }
    }

    public static class RoomIconManager {

        /* renamed from: a  reason: collision with root package name */
        public static final String f18022a = "more_1";
        /* access modifiers changed from: private */
        public String b = "";
        private String c = URLConstant.f13961a.substring(URLConstant.f13961a.lastIndexOf("/") + 1);
        /* access modifiers changed from: private */
        public File d = SHApplication.getAppContext().getDir("images", 0);
        /* access modifiers changed from: private */
        public File e = new File(this.d + "/" + this.c);
        /* access modifiers changed from: private */
        public File f;
        /* access modifiers changed from: private */
        public Map<String, List<String>> g;
        /* access modifiers changed from: private */
        public List<RoomConfig> h;
        /* access modifiers changed from: private */
        public AtomicBoolean i;
        /* access modifiers changed from: private */
        public BroadcastReceiver j;

        public RoomIconManager() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.d);
            sb.append("/roomicon");
            this.f = new File(sb.toString());
            this.g = new HashMap();
            this.h = new ArrayList();
            this.i = new AtomicBoolean(false);
            this.j = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (TextUtils.equals(intent.getAction(), HomeManager.S)) {
                        Log.d(HomeManager.T, "DownloadAndUnZipRoomIconRes: Retry");
                        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(RoomIconManager.this.j);
                        RoomIconManager.this.c();
                        if (!RoomIconManager.this.i.get()) {
                            RoomIconManager.this.b();
                        }
                    }
                }
            };
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                /* access modifiers changed from: protected */
                public Object doInBackground(Object... objArr) {
                    CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            ServerBean F = CoreApi.a().F();
                            if (ServerCompact.c(F) || ServerCompact.l(F)) {
                                String unused = RoomIconManager.this.b = URLConstant.f13961a;
                            } else if (ServerCompact.d(F)) {
                                String unused2 = RoomIconManager.this.b = URLConstant.d;
                            } else {
                                String unused3 = RoomIconManager.this.b = URLConstant.b;
                            }
                            if (!RoomIconManager.this.e.exists() || !RoomIconManager.this.f.exists()) {
                                RoomIconManager.this.c();
                            }
                        }
                    });
                    RoomIconManager.this.b();
                    return null;
                }
            }, new Object[0]);
        }

        /* access modifiers changed from: private */
        public void b() {
            SmartHomeDeviceHelper.a().b().a((DeviceTagInterface.IRoomConfigListener) new DeviceTagInterface.IRoomConfigListener() {
                public void a() {
                    List unused = RoomIconManager.this.h = ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).A();
                    Locale I = CoreApi.a().I();
                    if (I == null) {
                        I = Locale.getDefault();
                    }
                    for (int i = 0; i < RoomIconManager.this.h.size(); i++) {
                        RoomConfig roomConfig = (RoomConfig) RoomIconManager.this.h.get(i);
                        if (!TextUtils.isEmpty(roomConfig.a(I)) && !TextUtils.isEmpty(roomConfig.a())) {
                            ArrayList arrayList = new ArrayList();
                            String a2 = roomConfig.a();
                            int b = roomConfig.b();
                            int i2 = 0;
                            while (i2 < b) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(a2);
                                sb.append(JSMethod.NOT_SET);
                                i2++;
                                sb.append(i2);
                                arrayList.add(sb.toString());
                            }
                            RoomIconManager.this.g.put(a2, arrayList);
                        }
                        RoomIconManager.this.i.set(true);
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        public void c() {
            Observable.create(new ObservableOnSubscribe<Boolean>() {
                public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                    if (!observableEmitter.isDisposed()) {
                        AsyncTaskUtils.a(new NetworkUtils.DownloadTask(SHApplication.getAppContext(), RoomIconManager.this.b, RoomIconManager.this.e, new NetworkUtils.DownloadTask.DownloadListener() {
                            public void a() {
                                Log.d(HomeManager.T, "DownloadAndUnZipRoomIconRes: Load Success");
                                FileUtils.e(RoomIconManager.this.f.getAbsolutePath());
                                observableEmitter.onNext(true);
                                observableEmitter.onComplete();
                            }

                            public void b() {
                                Log.d(HomeManager.T, "DownloadAndUnZipRoomIconRes: Load Failed");
                                RoomIconManager.this.e.delete();
                                observableEmitter.onNext(false);
                            }
                        }), new Object[0]);
                    }
                }
            }).flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                /* renamed from: a */
                public ObservableSource<Boolean> apply(final Boolean bool) throws Exception {
                    return Observable.create(new ObservableOnSubscribe<Boolean>() {
                        public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                            if (!observableEmitter.isDisposed()) {
                                if (bool.booleanValue()) {
                                    AsyncTaskUtils.a(new AsyncTask<Object, Object, Boolean>() {
                                        /* access modifiers changed from: protected */
                                        /* renamed from: a */
                                        public Boolean doInBackground(Object... objArr) {
                                            Thread.currentThread().setName("UnzipRoomIcon");
                                            boolean a2 = ZipFileUtils.a(RoomIconManager.this.e.getAbsolutePath(), RoomIconManager.this.d.getAbsolutePath());
                                            String M = HomeManager.T;
                                            Log.d(M, "DownloadAndUnZipRoomIconRes: UnZip " + a2);
                                            observableEmitter.onNext(Boolean.valueOf(a2));
                                            if (a2) {
                                                observableEmitter.onComplete();
                                            }
                                            return Boolean.valueOf(a2);
                                        }
                                    }, new Object[0]);
                                    return;
                                }
                                observableEmitter.onNext(bool);
                                observableEmitter.onError(new Throwable());
                            }
                        }
                    });
                }
            }).subscribeOn(Schedulers.io()).subscribe(new Observer<Boolean>() {
                /* renamed from: a */
                public void onNext(Boolean bool) {
                }

                public void onComplete() {
                }

                public void onSubscribe(Disposable disposable) {
                }

                public void onError(Throwable th) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(RoomIconManager.this.j, new IntentFilter(HomeManager.S));
                }
            });
        }

        public StateListDrawable a(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            try {
                File file = new File(this.f + "/" + str + "_pres.png");
                File file2 = new File(this.f + "/" + str + "_normal.png");
                if (file.exists()) {
                    if (file2.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                        BitmapFactory.decodeFile(file2.getAbsolutePath(), options);
                        options.inSampleSize = options.outWidth / DisplayUtils.a(38.0f);
                        options.outWidth = DisplayUtils.a(38.0f);
                        options.outHeight = DisplayUtils.a(38.0f);
                        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                        options.inJustDecodeBounds = false;
                        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                        Bitmap decodeFile2 = BitmapFactory.decodeFile(file2.getAbsolutePath(), options);
                        BitmapDrawable bitmapDrawable = new BitmapDrawable(SHApplication.getAppContext().getResources(), decodeFile);
                        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(SHApplication.getAppContext().getResources(), decodeFile2);
                        StateListDrawable stateListDrawable = new StateListDrawable();
                        stateListDrawable.addState(new int[]{16842919}, bitmapDrawable);
                        stateListDrawable.addState(new int[]{16842913}, bitmapDrawable);
                        stateListDrawable.addState(new int[]{16842912}, bitmapDrawable);
                        stateListDrawable.addState(new int[0], bitmapDrawable2);
                        return stateListDrawable;
                    }
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public Bitmap a(String str, boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f);
            sb.append("/");
            sb.append(str);
            sb.append(z ? "_normal.png" : "_pres.png");
            File file = new File(sb.toString());
            if (file.exists()) {
                return BitmapFactory.decodeFile(file.getAbsolutePath());
            }
            return BitmapFactory.decodeResource(SHApplication.getAppContext().getResources(), R.drawable.more_6_normal);
        }

        public List<String> a() {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                arrayList.addAll(arrayList.size(), this.g.get(this.h.get(i2).a()));
            }
            return arrayList;
        }

        public List<String> b(String str) {
            return this.g.get(str);
        }

        public String b(String str, boolean z) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f);
            sb.append("/");
            sb.append(str);
            sb.append(z ? "_normal.png" : "_pres.png");
            return new File(sb.toString()).getAbsolutePath();
        }

        public RoomConfig c(String str) {
            List<RoomConfig> list = this.h;
            if (list == null || list.isEmpty()) {
                return null;
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                RoomConfig roomConfig = list.get(i2);
                if (roomConfig != null && TextUtils.equals(str, roomConfig.a(ServerCompact.c(SHApplication.getAppContext())))) {
                    return roomConfig;
                }
            }
            return null;
        }
    }

    public static boolean A() {
        if (CoreApi.a().l()) {
            return CoreApi.a().D();
        }
        return ServerCompact.e(SHApplication.getAppContext());
    }

    public static boolean B() {
        return ServerCompact.g(SHApplication.getAppContext());
    }

    public static boolean a(Device device) {
        if (device == null) {
            return false;
        }
        if ((device.isOwner() || device.isHomeShared()) && !IRDeviceUtil.a(device.did) && !device.isVirtualDevice() && DeviceType.a(device.model) == 2 && !Q.contains(device.model)) {
            return true;
        }
        return false;
    }

    public void w(String str) {
        CommonUseDeviceDataManager.a().a(str);
    }

    public boolean b(List<String> list) {
        return CommonUseDeviceDataManager.a().b(list);
    }

    public void x(String str) {
        CommonUseDeviceDataManager.a().b(str);
    }

    public List<String> C() {
        Home m2 = m();
        if (m2 == null) {
            return null;
        }
        return CommonUseDeviceDataManager.a().d(m2.j());
    }

    private void a(List<Device> list, int i2) {
        MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
        miioDeviceV2.name = SHApplication.getAppContext().getString(R.string.group_type_phoneir);
        miioDeviceV2.model = IRDeviceUtil.a();
        miioDeviceV2.did = DeviceUtils.a();
        if (i2 < 0 || i2 > list.size()) {
            list.add(miioDeviceV2);
        } else {
            list.add(i2, miioDeviceV2);
        }
    }

    @Deprecated
    public List<GridViewData> D() {
        Home m2 = m();
        if (m2 == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(MultiHomeDeviceManager.a().d());
        arrayList2.addAll(MultiHomeDeviceManager.a().e());
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            Device device = (Device) arrayList2.get(i2);
            if (device != null) {
                arrayList3.add(device.did);
            }
        }
        List d2 = CommonUseDeviceDataManager.a().d(m2.j());
        if (d2 == null) {
            d2 = new ArrayList();
        }
        for (int i3 = 0; i3 < d2.size(); i3++) {
            String str = (String) d2.get(i3);
            if (!TextUtils.isEmpty(str) && !arrayList.contains(str) && arrayList3.contains(str)) {
                arrayList.add(str);
            } else if (TextUtils.equals(CommonUseDeviceDataManager.g, str)) {
                arrayList.add(str);
            } else {
                Device b2 = SmartHomeDeviceManager.a().b(str);
                if (b2 != null && HomeVirtualDeviceHelper.a(b2.model)) {
                    arrayList.add(str);
                }
            }
        }
        ArrayList arrayList4 = new ArrayList();
        if (arrayList.isEmpty()) {
            if (IRDeviceUtil.c()) {
                GridViewData gridViewData = new GridViewData();
                gridViewData.f18311a = GridViewData.GridType.TYPE_IR;
                gridViewData.b = P();
                arrayList4.add(gridViewData);
            }
            return arrayList4;
        }
        boolean z2 = false;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            String str2 = (String) arrayList.get(i4);
            Device b3 = SmartHomeDeviceManager.a().b(str2);
            if (b3 != null) {
                GridViewData gridViewData2 = new GridViewData();
                gridViewData2.b = b3;
                if (IRDeviceUtil.a(str2)) {
                    gridViewData2.f18311a = GridViewData.GridType.TYPE_IR;
                    gridViewData2.b = P();
                    z2 = true;
                } else {
                    gridViewData2.f18311a = GridViewData.GridType.TYPE_NORMAL;
                }
                arrayList4.add(gridViewData2);
            } else if (TextUtils.equals(str2, CommonUseDeviceDataManager.g) && !z2 && IRDeviceUtil.c()) {
                GridViewData gridViewData3 = new GridViewData();
                gridViewData3.f18311a = GridViewData.GridType.TYPE_IR;
                gridViewData3.b = P();
                arrayList4.add(gridViewData3);
                z2 = true;
            }
        }
        if (!z2 && IRDeviceUtil.c()) {
            GridViewData gridViewData4 = new GridViewData();
            gridViewData4.f18311a = GridViewData.GridType.TYPE_IR;
            gridViewData4.b = P();
            arrayList4.add(0, gridViewData4);
        }
        for (int i5 = 0; i5 < arrayList4.size(); i5++) {
            GridViewData gridViewData5 = (GridViewData) arrayList4.get(i5);
            if (gridViewData5.b != null && !TextUtils.isEmpty(gridViewData5.b.getName())) {
                gridViewData5.c = z(gridViewData5.b.getName().toString());
            }
        }
        return arrayList4;
    }

    public void b(String str, boolean z2) {
        ArrayList arrayList;
        if (!TextUtils.isEmpty(str)) {
            Home q2 = q(str);
            if (q2 != null || (q2 = a().m()) != null) {
                Map map = CommonUseDeviceDataManager.a().f17934a;
                if (map == null) {
                    map = new HashMap();
                }
                List list = (List) map.get(q2.j());
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    list.remove(str);
                    arrayList = new ArrayList(list);
                }
                if (z2) {
                    arrayList.add(0, str);
                } else {
                    arrayList.add(str);
                }
                CommonUseDeviceDataManager.a().a((List<String>) arrayList, q2);
            }
        }
    }

    public void a(List<String> list, boolean z2) {
        ArrayList arrayList;
        if (list != null && !list.isEmpty()) {
            Home q2 = q(list.get(0));
            if (q2 != null || (q2 = a().m()) != null) {
                Map map = CommonUseDeviceDataManager.a().f17934a;
                if (map == null) {
                    map = new HashMap();
                }
                List list2 = (List) map.get(q2.j());
                if (list2 == null) {
                    arrayList = new ArrayList();
                } else {
                    list2.removeAll(list);
                    arrayList = new ArrayList(list2);
                }
                if (z2) {
                    arrayList.addAll(0, list);
                } else {
                    arrayList.addAll(list);
                }
                CommonUseDeviceDataManager.a().a((List<String>) arrayList, q2);
            }
        }
    }

    public boolean E() {
        String l2 = l();
        Home j2 = j(l2);
        String str = T;
        LogUtilGrey.a(str, "isInEmptyHome: homeId " + l2);
        String str2 = T;
        LogUtilGrey.a(str2, "isInEmptyHome: home " + j2);
        if (j2 == null) {
            return true;
        }
        if (IRDeviceUtil.c()) {
            LogUtilGrey.a(T, "isInEmptyHome: hasIrEmitter ");
            return false;
        } else if (!o(l2).isEmpty()) {
            LogUtilGrey.a(T, "isInEmptyHome: getDeviceByHomeId is not empty");
            return false;
        } else if (!MultiHomeDeviceManager.a().e().isEmpty()) {
            LogUtilGrey.a(T, "isInEmptyHome: getShareDeviceList is not empty ");
            return false;
        } else if (!MultiHomeDeviceManager.a().h().isEmpty()) {
            LogUtilGrey.a(T, "isInEmptyHome: getVirtualGroupDeviceList is not empty ");
            return false;
        } else {
            LogUtilGrey.a(T, "isInEmptyHome: true ");
            return true;
        }
    }

    public List<GridViewData> F() {
        return y(l());
    }

    public List<GridViewData> y(String str) {
        Home j2 = j(str);
        if (j2 == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a(str, new boolean[0]));
        int size = arrayList.size();
        String str2 = T;
        LogUtilGrey.a(str2, "getCommonUseDevices getHomeDids size=" + size);
        if (j2.p()) {
            List<Device> e2 = MultiHomeDeviceManager.a().e();
            if (e2 != null && !e2.isEmpty()) {
                for (int i2 = 0; i2 < e2.size(); i2++) {
                    Device device = e2.get(i2);
                    if (device != null) {
                        arrayList.add(device.did);
                    }
                }
            }
            String str3 = T;
            LogUtilGrey.a(str3, "getCommonUseDevices getShareDidList size=" + (arrayList.size() - size));
        }
        List<Device> h2 = MultiHomeDeviceManager.a().h();
        if (h2 != null && !h2.isEmpty()) {
            for (int i3 = 0; i3 < h2.size(); i3++) {
                Device device2 = h2.get(i3);
                if (device2 != null) {
                    arrayList.add(device2.did);
                }
            }
        }
        String str4 = T;
        LogUtilGrey.a(str4, "getCommonUseDevices alldid size=" + arrayList.size());
        ArrayList arrayList2 = new ArrayList();
        String i4 = DeviceFinder.a().i();
        if (!TextUtils.isEmpty(i4)) {
            arrayList2.add(i4);
        }
        String str5 = T;
        LogUtilGrey.a(str5, "getCommonUseDevices newDids: " + Arrays.deepToString(arrayList2.toArray()));
        HashSet hashSet = new HashSet(arrayList);
        LinkedHashSet linkedHashSet = new LinkedHashSet(arrayList.size() * 2, 0.75f);
        linkedHashSet.addAll(arrayList2);
        linkedHashSet.addAll(arrayList);
        LinkedHashSet<String> linkedHashSet2 = new LinkedHashSet<>(linkedHashSet.size() * 2, 0.75f);
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            String str6 = (String) it.next();
            if (!TextUtils.isEmpty(str6) && !linkedHashSet2.contains(str6) && hashSet.contains(str6)) {
                linkedHashSet2.add(str6);
            } else if (TextUtils.equals(CommonUseDeviceDataManager.g, str6)) {
                linkedHashSet2.add(str6);
            } else {
                Device b2 = SmartHomeDeviceManager.a().b(str6);
                if (b2 != null && HomeVirtualDeviceHelper.a(b2.model)) {
                    linkedHashSet2.add(str6);
                }
            }
        }
        ArrayList<GridViewData> arrayList3 = new ArrayList<>();
        if (linkedHashSet2.isEmpty()) {
            if (IRDeviceUtil.c()) {
                GridViewData gridViewData = new GridViewData();
                gridViewData.f18311a = GridViewData.GridType.TYPE_IR;
                gridViewData.b = P();
                arrayList3.add(gridViewData);
            }
            return arrayList3;
        }
        boolean z2 = false;
        for (String str7 : linkedHashSet2) {
            Device b3 = SmartHomeDeviceManager.a().b(str7);
            if (b3 != null) {
                GridViewData gridViewData2 = new GridViewData();
                gridViewData2.b = b3;
                if (IRDeviceUtil.a(str7)) {
                    gridViewData2.f18311a = GridViewData.GridType.TYPE_IR;
                    gridViewData2.b = P();
                    z2 = true;
                } else {
                    gridViewData2.f18311a = GridViewData.GridType.TYPE_NORMAL;
                }
                gridViewData2.c = z((String) gridViewData2.b.getName());
                arrayList3.add(gridViewData2);
            } else if (TextUtils.equals(str7, CommonUseDeviceDataManager.g) && !z2 && IRDeviceUtil.c()) {
                GridViewData gridViewData3 = new GridViewData();
                gridViewData3.f18311a = GridViewData.GridType.TYPE_IR;
                gridViewData3.b = P();
                arrayList3.add(gridViewData3);
                z2 = true;
            }
        }
        if (!z2 && IRDeviceUtil.c()) {
            GridViewData gridViewData4 = new GridViewData();
            gridViewData4.f18311a = GridViewData.GridType.TYPE_IR;
            gridViewData4.b = P();
            arrayList3.add(0, gridViewData4);
        }
        String str8 = T;
        LogUtilGrey.a(str8, "getCommonUseDevices return size=" + arrayList3.size());
        List<String> d2 = CommonUseDeviceDataManager.a().d(j2.j());
        if (GlobalSetting.u) {
            LogUtilGrey.a("DeviceOrderDebug", "getCommonUseDevices did order=" + Arrays.deepToString(d2.toArray()));
        }
        if (GlobalSetting.u) {
            ArrayList arrayList4 = new ArrayList();
            for (GridViewData gridViewData5 : arrayList3) {
                arrayList4.add(gridViewData5.b.did);
            }
            LogUtilGrey.a("DeviceOrderDebug", "getCommonUseDevices did order before=" + Arrays.deepToString(arrayList4.toArray()));
        }
        UtilKt.a(d2, arrayList3, $$Lambda$HomeManager$NeU1GPVIv1UwGJaiLMi8fXk4zE.INSTANCE, $$Lambda$HomeManager$vb6FLj275Y7cDwC1NoWVsl3iw.INSTANCE);
        if (GlobalSetting.u) {
            ArrayList arrayList5 = new ArrayList();
            for (GridViewData gridViewData6 : arrayList3) {
                arrayList5.add(gridViewData6.b.did);
            }
            LogUtilGrey.a("DeviceOrderDebug", "getCommonUseDevices did order after=" + Arrays.deepToString(arrayList5.toArray()));
        }
        return arrayList3;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean a(String str, GridViewData gridViewData) {
        String str2 = gridViewData.b.did;
        if (gridViewData.f18311a == GridViewData.GridType.TYPE_IR) {
            str2 = CommonUseDeviceDataManager.g;
        }
        return Boolean.valueOf(TextUtils.equals(str, str2));
    }

    public List<String> G() {
        HashSet hashSet = new HashSet();
        List<Room> e2 = e();
        if (e2 == null || e2.isEmpty()) {
            return new ArrayList(hashSet);
        }
        for (int i2 = 0; i2 < e2.size(); i2++) {
            Room room = e2.get(i2);
            if (room != null) {
                List<String> h2 = room.h();
                for (int i3 = 0; i3 < h2.size(); i3++) {
                    Device b2 = SmartHomeDeviceManager.a().b(h2.get(i3));
                    if (b2 != null) {
                        hashSet.add(b2.did);
                    }
                }
            }
        }
        for (Device device : j()) {
            hashSet.add(device.did);
        }
        return new ArrayList(hashSet);
    }

    private static Device P() {
        PhoneIRDevice phoneIRDevice = new PhoneIRDevice();
        phoneIRDevice.name = SHApplication.getAppContext().getString(R.string.group_type_phoneir);
        return phoneIRDevice;
    }

    public static String z(String str) {
        if (TextUtils.isEmpty(str) || StringUtil.a((CharSequence) str) <= ag) {
            return str;
        }
        String[] strArr = ah;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str2 = strArr[i2];
            if (!str.startsWith(str2)) {
                i2++;
            } else if (str.trim().length() > str2.length()) {
                return str.substring(str2.length()).trim();
            }
        }
        return str;
    }

    public void H() {
        if (this.am != null) {
            this.am.j();
        }
    }

    public void I() {
        W = null;
        SmartHomeDeviceManager.a().c(this.au);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.aA);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.ak);
        this.ay.set(false);
        this.al.removeCallbacksAndMessages((Object) null);
        CommonUseDeviceDataManager.a().b();
    }

    public static void J() {
        if (W != null) {
            try {
                a().I();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public Observable<Boolean> K() {
        return this.at;
    }

    public void b(boolean z2) {
        Integer num;
        LogUtil.a(T, "forceUpdateAllData " + this.ay.get());
        LogUtilGrey.a("forceUpdateAllData", "mIsForcingRefreshing=" + this.ay.get());
        if (!this.ay.getAndSet(true)) {
            this.at.onNext(true);
            this.al.postDelayed(new Runnable() {
                public void run() {
                    HomeManager.this.ay.set(false);
                }
            }, 5000);
            IntentFilter intentFilter = new IntentFilter(az);
            this.av.set(true);
            LogUtil.a("forceUpdateAllData", "add ACTION_DEVICE_UPDATED");
            if (this.am.h()) {
                intentFilter.addAction(t);
                this.aw.set(true);
                LogUtil.a("forceUpdateAllData", "add ACTION_HOME_ROOM_UPDATED");
            }
            SmartHomeDeviceManager.a().a(this.au);
            SmartHomeDeviceManager.a().p();
            List<PluginRecord> O2 = CoreApi.a().O();
            StringBuilder sb = new StringBuilder();
            sb.append("mHomeUpdating=");
            sb.append(this.aw.get());
            sb.append(",mCommonDeviceUpdating=");
            sb.append(this.ax.get());
            sb.append(",device count=");
            sb.append(SmartHomeDeviceManager.a().d().size());
            sb.append(",pluginrecord size=");
            if (O2 == null) {
                num = null;
            } else {
                num = Integer.valueOf(O2.size());
            }
            sb.append(num);
            sb.append(",SmartHomeDeviceManager isRefreshing=");
            sb.append(SmartHomeDeviceManager.a().t());
            LogUtilGrey.a("forceUpdateAllData", sb.toString(), true);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.aA, intentFilter);
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    SmartHomeDeviceHelper.a().b().e();
                }
            });
            RoomEnvManager.a().a((String) null, (AsyncCallback<List<RoomEnvData>, Error>) null);
            DeviceCountManager.a().c();
            if (z2) {
                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(az));
            }
        }
    }

    public void L() {
        Integer num;
        String str = T;
        LogUtil.a(str, "forceUpdateAllData " + this.ay.get());
        if (!this.ay.getAndSet(true)) {
            this.al.postDelayed(new Runnable() {
                public void run() {
                    HomeManager.this.ay.set(false);
                }
            }, 5000);
            IntentFilter intentFilter = new IntentFilter(az);
            this.av.set(true);
            LogUtil.a("forceUpdateAllData", "add ACTION_DEVICE_UPDATED");
            if (this.am.h()) {
                intentFilter.addAction(t);
                this.aw.set(true);
                LogUtil.a("forceUpdateAllData", "add ACTION_HOME_ROOM_UPDATED");
            }
            SmartHomeDeviceManager.a().a(this.au);
            SmartHomeDeviceManager.a().p();
            List<PluginRecord> O2 = CoreApi.a().O();
            StringBuilder sb = new StringBuilder();
            sb.append("mHomeUpdating=");
            sb.append(this.aw.get());
            sb.append(",mCommonDeviceUpdating=");
            sb.append(this.ax.get());
            sb.append(",device count=");
            sb.append(SmartHomeDeviceManager.a().d().size());
            sb.append(",getHomeSize size=");
            sb.append(i());
            sb.append(",pluginrecord size=");
            if (O2 == null) {
                num = null;
            } else {
                num = Integer.valueOf(O2.size());
            }
            sb.append(num);
            sb.append(",SmartHomeDeviceManager isRefreshing=");
            sb.append(SmartHomeDeviceManager.a().t());
            LogUtilGrey.a("forceUpdateAllData", sb.toString(), true);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.aA, intentFilter);
        }
    }

    public static boolean A(String str) {
        if ((TextUtils.isEmpty(str) || str.length() <= 40) && StringUtil.a((CharSequence) str) <= 40) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void R() {
        v();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01e3, code lost:
        if (java.lang.Math.abs(r11 - java.lang.Double.parseDouble(r1.c())) < 0.001d) goto L_0x00f7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(com.xiaomi.smarthome.framework.location.SHLocationManager.LocationCallback r16, boolean r17, com.xiaomi.smarthome.homeroom.model.Home r18) {
        /*
            r15 = this;
            if (r18 != 0) goto L_0x0008
            com.xiaomi.smarthome.homeroom.model.Home r0 = r15.m()
            r1 = r0
            goto L_0x000a
        L_0x0008:
            r1 = r18
        L_0x000a:
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x0010
            goto L_0x01ea
        L_0x0010:
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "home_room_manager_sp_"
            r3.append(r4)
            com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r4 = r4.s()
            java.lang.String r4 = com.xiaomi.smarthome.library.crypto.MD5Util.a((java.lang.String) r4)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "home_location"
            r4.append(r5)
            java.lang.String r5 = r1.j()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = ""
            java.lang.String r0 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.c(r0, r3, r4, r5)
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L_0x008a
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "home_room_manager_sp_"
            r3.append(r4)
            com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r4 = r4.s()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "home_location"
            r4.append(r5)
            java.lang.String r5 = r1.j()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = ""
            java.lang.String r0 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.c(r0, r3, r4, r5)
        L_0x008a:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x01f8
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            r3.<init>(r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r0 = "latitude"
            java.lang.String r6 = "0"
            java.lang.String r0 = r3.optString(r0, r6)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r6 = "longitude"
            java.lang.String r7 = "0"
            java.lang.String r6 = r3.optString(r6, r7)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r7 = "city_id"
            java.lang.String r7 = r3.optString(r7)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r8 == 0) goto L_0x00d1
            boolean r8 = android.text.TextUtils.isEmpty(r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r8 != 0) goto L_0x01ea
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r8 != 0) goto L_0x01ea
            java.lang.String r8 = "0"
            boolean r8 = android.text.TextUtils.equals(r8, r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r8 != 0) goto L_0x01ea
            java.lang.String r8 = "0"
            boolean r8 = android.text.TextUtils.equals(r8, r6)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r8 == 0) goto L_0x00d1
            goto L_0x01ea
        L_0x00d1:
            java.lang.String r8 = r1.b()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r9 = r1.c()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r10 = "loc"
            java.lang.String r10 = r3.optString(r10)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            boolean r11 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r11 != 0) goto L_0x00fb
            java.lang.String r11 = "0"
            boolean r11 = android.text.TextUtils.equals(r7, r11)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r11 != 0) goto L_0x00fb
            java.lang.String r11 = r1.a()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            boolean r7 = android.text.TextUtils.equals(r7, r11)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r7 == 0) goto L_0x00fb
        L_0x00f7:
            r2 = r10
        L_0x00f8:
            r4 = 0
            goto L_0x01e7
        L_0x00fb:
            java.lang.String r7 = r1.a()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r7 != 0) goto L_0x0180
            java.lang.String r7 = r1.a()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r11 = "0"
            boolean r7 = android.text.TextUtils.equals(r7, r11)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r7 != 0) goto L_0x0180
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r4 = r1.a()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r4 = com.xiaomi.smarthome.miio.areainfo.MainlandAreaInfo.a((android.content.Context) r0, (java.lang.String) r4)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            java.lang.String r0 = "latitude"
            java.lang.String r2 = "0"
            r3.put(r0, r2)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r0 = "longitude"
            java.lang.String r2 = "0"
            r3.put(r0, r2)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r0 = "city_id"
            java.lang.String r2 = r1.a()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            r3.put(r0, r2)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r0 = "loc"
            r3.put(r0, r4)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            r2.<init>()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r6 = "home_room_manager_sp_"
            r2.append(r6)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r6 = r6.s()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r6 = com.xiaomi.smarthome.library.crypto.MD5Util.a((java.lang.String) r6)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            r2.append(r6)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            r6.<init>()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r7 = "home_location"
            r6.append(r7)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r7 = r1.j()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            r6.append(r7)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r6 = r6.toString()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            java.lang.String r3 = r3.toString()     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            com.xiaomi.smarthome.library.common.util.SharePrefsManager.b((android.content.Context) r0, (java.lang.String) r2, (java.lang.String) r6, (java.lang.String) r3)     // Catch:{ JSONException -> 0x017c, Exception -> 0x0178 }
            r2 = r4
            goto L_0x00f8
        L_0x0178:
            r0 = move-exception
            r2 = r4
            goto L_0x01ee
        L_0x017c:
            r0 = move-exception
            r2 = r4
            goto L_0x01f3
        L_0x0180:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            r11 = 0
            if (r3 == 0) goto L_0x018a
            r13 = r11
            goto L_0x018e
        L_0x018a:
            double r13 = java.lang.Double.parseDouble(r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
        L_0x018e:
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r0 == 0) goto L_0x0195
            goto L_0x0199
        L_0x0195:
            double r11 = java.lang.Double.parseDouble(r6)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
        L_0x0199:
            boolean r0 = android.text.TextUtils.isEmpty(r8)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r0 != 0) goto L_0x01ea
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r0 == 0) goto L_0x01a6
            goto L_0x01ea
        L_0x01a6:
            java.lang.String r0 = "0"
            boolean r0 = android.text.TextUtils.equals(r8, r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r0 != 0) goto L_0x01ea
            java.lang.String r0 = "0"
            boolean r0 = android.text.TextUtils.equals(r9, r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            if (r0 == 0) goto L_0x01b7
            goto L_0x01ea
        L_0x01b7:
            java.lang.String r0 = r1.b()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            double r6 = java.lang.Double.parseDouble(r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            r0 = 0
            double r13 = r13 - r6
            double r6 = java.lang.Math.abs(r13)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            r8 = 4572414629676717179(0x3f747ae147ae147b, double:0.005)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x01e7
            java.lang.String r0 = r1.c()     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            double r6 = java.lang.Double.parseDouble(r0)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            r0 = 0
            double r11 = r11 - r6
            double r6 = java.lang.Math.abs(r11)     // Catch:{ JSONException -> 0x01f2, Exception -> 0x01ed }
            r8 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x01e7
            goto L_0x00f7
        L_0x01e7:
            r0 = r2
            r5 = r4
            goto L_0x021d
        L_0x01ea:
            r0 = r2
        L_0x01eb:
            r2 = r15
            goto L_0x024c
        L_0x01ed:
            r0 = move-exception
        L_0x01ee:
            r0.printStackTrace()
            goto L_0x01f6
        L_0x01f2:
            r0 = move-exception
        L_0x01f3:
            r0.printStackTrace()
        L_0x01f6:
            r0 = r2
            goto L_0x021d
        L_0x01f8:
            java.lang.String r0 = r1.a()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x021b
            java.lang.String r0 = r1.a()
            java.lang.String r3 = "0"
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 != 0) goto L_0x021b
            android.content.Context r0 = com.xiaomi.smarthome.application.SHApplication.getAppContext()
            java.lang.String r2 = r1.a()
            java.lang.String r2 = com.xiaomi.smarthome.miio.areainfo.MainlandAreaInfo.a((android.content.Context) r0, (java.lang.String) r2)
            goto L_0x01f6
        L_0x021b:
            r0 = r2
            r5 = 1
        L_0x021d:
            if (r17 == 0) goto L_0x01eb
            if (r5 == 0) goto L_0x01eb
            java.lang.String r2 = r1.b()
            if (r2 == 0) goto L_0x01eb
            java.lang.String r2 = r1.c()
            if (r2 == 0) goto L_0x01eb
            com.xiaomi.smarthome.framework.location.SHLocationManager r3 = com.xiaomi.smarthome.framework.location.SHLocationManager.a()
            java.lang.String r2 = r1.b()
            double r4 = java.lang.Double.parseDouble(r2)
            java.lang.String r2 = r1.c()
            double r6 = java.lang.Double.parseDouble(r2)
            com.xiaomi.smarthome.homeroom.HomeManager$16 r8 = new com.xiaomi.smarthome.homeroom.HomeManager$16
            r2 = r15
            r9 = r16
            r8.<init>(r1, r9)
            r3.a((double) r4, (double) r6, (com.xiaomi.smarthome.framework.location.SHLocationManager.LocationCallback) r8)
        L_0x024c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.HomeManager.a(com.xiaomi.smarthome.framework.location.SHLocationManager$LocationCallback, boolean, com.xiaomi.smarthome.homeroom.model.Home):java.lang.String");
    }

    public void b(Device device) {
        a((String) null, e());
        if (device != null) {
            a(device, true);
            b(device.did, true);
        }
    }

    public int B(String str) {
        List<Device> o2 = o(str);
        List<Device> e2 = MultiHomeDeviceManager.a().e();
        if (!(o2 == null || e2 == null)) {
            o2.addAll(MultiHomeDeviceManager.a().e());
        }
        if (o2 == null || o2.isEmpty()) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < o2.size(); i3++) {
            if (TextUtils.equals(UserAvatarUpdateActivity.CAMERA, DeviceUtils.e(o2.get(i3)))) {
                i2++;
            }
        }
        return i2;
    }
}
