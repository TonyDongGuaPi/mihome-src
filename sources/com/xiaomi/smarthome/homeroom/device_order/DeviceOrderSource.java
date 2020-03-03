package com.xiaomi.smarthome.homeroom.device_order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.adobe.xmp.XMPConst;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.device_order.OrderCompat;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.international.RxApi;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u00042\b\b\u0002\u0010%\u001a\u00020\u0004H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0012\u0010(\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010*\u001a\u00020\u0004H\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004H\u0002J\u0013\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00120-H\u0000¢\u0006\u0002\b.J/\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120-002\f\u00101\u001a\b\u0012\u0004\u0012\u0002020-2\u0006\u00103\u001a\u000204H\u0000¢\u0006\u0002\b5J\u0018\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u000204H\u0002J\u0016\u0010:\u001a\u00020#2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020#0<H\u0002J\u0016\u0010=\u001a\u00020#2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00040-H\u0002J\u0010\u0010?\u001a\u00020#2\u0006\u0010)\u001a\u00020\u0004H\u0002JJ\u0010@\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00120\nj\b\u0012\u0004\u0012\u00020\u0012`\u000b002\f\u00101\u001a\b\u0012\u0004\u0012\u0002020-2\b\b\u0002\u0010A\u001a\u0002042\b\b\u0002\u0010B\u001a\u0002042\b\b\u0002\u0010C\u001a\u000204H\u0002J\u0018\u0010D\u001a\u00020#2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010E\u001a\u00020FH\u0007JD\u0010G\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00120\nj\b\u0012\u0004\u0012\u00020\u0012`\u000b002\u0006\u00101\u001a\u0002022\b\b\u0002\u0010A\u001a\u0002042\b\b\u0002\u0010B\u001a\u0002042\b\b\u0002\u0010C\u001a\u000204H\u0002J,\u0010H\u001a\u00020#2\u0006\u0010)\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040-J\u001c\u0010L\u001a\u00020#2\u0006\u0010)\u001a\u00020\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040-J\u001c\u0010M\u001a\u00020#2\u0006\u0010)\u001a\u00020\u00042\f\u0010K\u001a\b\u0012\u0004\u0012\u00020N0-R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\nj\b\u0012\u0004\u0012\u00020\u0004`\u000b8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR7\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0012`\u00138BX\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R>\u0010\u001d\u001a2\u0012.\u0012,\u0012\u0004\u0012\u00020\u0012 \u001f*\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u000b0\nj\b\u0012\u0004\u0012\u00020\u0012`\u000b0\u001eX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n \u001f*\u0004\u0018\u00010!0!X\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/DeviceOrderSource;", "", "()V", "GET_ORDER_PATH", "", "HOME_ORDER_KEY_PREFIX", "PREFS_HOME_ORDER", "SET_ORDER_PATH", "TAG", "mHomesListOrder", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMHomesListOrder", "()Ljava/util/ArrayList;", "mHomesListOrder$delegate", "Lkotlin/Lazy;", "mHomesOrdersMap", "Ljava/util/HashMap;", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lkotlin/collections/HashMap;", "getMHomesOrdersMap", "()Ljava/util/HashMap;", "mHomesOrdersMap$delegate", "mOrderExecutor", "Ljava/util/concurrent/ExecutorService;", "mOrderSchedule", "Lio/reactivex/Scheduler;", "mOrderThreadId", "", "mParser", "Lcom/xiaomi/smarthome/frame/JsonParser;", "kotlin.jvm.PlatformType", "mUpgradeOrderCompat", "Lcom/xiaomi/smarthome/homeroom/device_order/UpgradeOrderCompat;", "Log", "", "enter", "message", "assertThread", "name", "dumpHomeOrderJson", "homeId", "getCachedHomeListOrder", "getCachedHomeOrder", "getCachedHomeOrders", "", "getCachedHomeOrders$app_GooglePlayRelease", "getHomeOrders", "Lio/reactivex/Observable;", "homeParams", "Lcom/xiaomi/smarthome/homeroom/device_order/OrderCompat$HomeParam;", "needCache", "", "getHomeOrders$app_GooglePlayRelease", "parseHomeOrder", "json", "Lorg/json/JSONObject;", "isCached", "runOnOrderThread", "block", "Lkotlin/Function0;", "saveHomeListOrderToCache", "homeIds", "saveHomeOrderCache", "syncOrderFromServer", "needRoomOrder", "needFrontOrder", "needCateOrder", "syncOrderToServer", "orderData", "Lcom/xiaomi/smarthome/homeroom/device_order/Order;", "syncSingleOwnerOrderFromServer", "updateCategoryOrder", "cateId", "parent_id", "orderList", "updateFrontOrder", "updateRoomOrder", "Lcom/xiaomi/smarthome/homeroom/model/Room;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class DeviceOrderSource {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ KProperty[] f18227a = {Reflection.a((PropertyReference1) new PropertyReference1Impl(Reflection.b(DeviceOrderSource.class), "mHomesListOrder", "getMHomesListOrder()Ljava/util/ArrayList;")), Reflection.a((PropertyReference1) new PropertyReference1Impl(Reflection.b(DeviceOrderSource.class), "mHomesOrdersMap", "getMHomesOrdersMap()Ljava/util/HashMap;"))};
    public static final DeviceOrderSource b = new DeviceOrderSource();
    private static final String c = "DeviceOrderDebug";
    private static final String d = "/v2/home/get_device_order";
    private static final String e = "/v2/home/set_device_order";
    private static final String f = "prefs_home_order";
    private static final String g = "home_order_key_prefix_";
    /* access modifiers changed from: private */
    public static long h = -10;
    private static final ExecutorService i;
    /* access modifiers changed from: private */
    public static final Scheduler j;
    /* access modifiers changed from: private */
    public static final UpgradeOrderCompat k = UpgradeOrderCompat.a();
    private static final Lazy l = LazyKt.a(LazyThreadSafetyMode.SYNCHRONIZED, DeviceOrderSource$mHomesListOrder$2.INSTANCE);
    private static final Lazy m = LazyKt.a(LazyThreadSafetyMode.SYNCHRONIZED, DeviceOrderSource$mHomesOrdersMap$2.INSTANCE);
    /* access modifiers changed from: private */
    public static final JsonParser<ArrayList<HomeOrder>> n = DeviceOrderSource$mParser$1.f18235a;

    /* access modifiers changed from: private */
    public final void a(String str, String str2) {
    }

    /* access modifiers changed from: private */
    public final ArrayList<String> b() {
        Lazy lazy = l;
        KProperty kProperty = f18227a[0];
        return (ArrayList) lazy.getValue();
    }

    /* access modifiers changed from: private */
    public final HashMap<String, HomeOrder> c() {
        Lazy lazy = m;
        KProperty kProperty = f18227a[1];
        return (HashMap) lazy.getValue();
    }

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(DeviceOrderSource$mOrderExecutor$1.f18234a);
        Intrinsics.b(newSingleThreadExecutor, "Executors.newSingleThrea…G;mOrderThreadId = id } }");
        i = newSingleThreadExecutor;
        Scheduler from = Schedulers.from(i);
        Intrinsics.b(from, "Schedulers.from(mOrderExecutor)");
        j = from;
    }

    private DeviceOrderSource() {
    }

    @NotNull
    public final List<HomeOrder> a() {
        Collection<HomeOrder> values = c().values();
        Intrinsics.b(values, "mHomesOrdersMap.values");
        Iterable<HomeOrder> r = CollectionsKt.r(values);
        Collection arrayList = new ArrayList(CollectionsKt.a(r, 10));
        for (HomeOrder a2 : r) {
            arrayList.add(HomeOrder.a(a2, (String) null, false, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 63, (Object) null));
        }
        return (List) arrayList;
    }

    @NotNull
    public final Observable<List<HomeOrder>> a(@NotNull List<OrderCompat.HomeParam> list, boolean z) {
        Intrinsics.f(list, "homeParams");
        Observable<List<HomeOrder>> subscribeOn = Observable.just(1).observeOn(j).flatMap(new DeviceOrderSource$getHomeOrders$1(z, list)).map(DeviceOrderSource$getHomeOrders$2.f18232a).map(new DeviceOrderSource$getHomeOrders$3(list, z)).subscribeOn(Schedulers.io());
        Intrinsics.b(subscribeOn, "Observable.just(1)\n     …scribeOn(Schedulers.io())");
        return subscribeOn;
    }

    /* access modifiers changed from: private */
    public final void a(String str) {
        Thread currentThread = Thread.currentThread();
        Intrinsics.b(currentThread, "Thread.currentThread()");
        int i2 = (currentThread.getId() > h ? 1 : (currentThread.getId() == h ? 0 : -1));
    }

    static /* synthetic */ Observable a(DeviceOrderSource deviceOrderSource, List list, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = true;
        }
        return deviceOrderSource.a((List<OrderCompat.HomeParam>) list, z, z2, z3);
    }

    private final Observable<ArrayList<HomeOrder>> a(List<OrderCompat.HomeParam> list, boolean z, boolean z2, boolean z3) {
        if (list.isEmpty()) {
            Observable<ArrayList<HomeOrder>> empty = Observable.empty();
            Intrinsics.b(empty, "Observable.empty()");
            return empty;
        }
        Iterable<OrderCompat.HomeParam> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.a(iterable, 10));
        for (OrderCompat.HomeParam a2 : iterable) {
            arrayList.add(b.a(a2, z, z2, z3));
        }
        Observable<ArrayList<HomeOrder>> zip = Observable.zip((List) arrayList, new DeviceOrderSource$syncOrderFromServer$1());
        Intrinsics.b(zip, "Observable.zip(homeOrder…            }\n\n        })");
        return zip;
    }

    static /* synthetic */ Observable a(DeviceOrderSource deviceOrderSource, OrderCompat.HomeParam homeParam, boolean z, boolean z2, boolean z3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        if ((i2 & 8) != 0) {
            z3 = true;
        }
        return deviceOrderSource.a(homeParam, z, z2, z3);
    }

    private final Observable<ArrayList<HomeOrder>> a(OrderCompat.HomeParam homeParam, boolean z, boolean z2, boolean z3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("owner_uid", homeParam.a());
        jSONObject.put("home_id", UtilKt.a(homeParam.b(), DeviceOrderSource$syncSingleOwnerOrderFromServer$param$1$1.INSTANCE));
        jSONObject.put("platform", "android");
        jSONObject.put("get_room_order", z);
        jSONObject.put("get_front_order", z2);
        jSONObject.put("get_cate_order", z3);
        Observable<ArrayList<HomeOrder>> onErrorReturn = RxApi.a(new NetRequest.Builder().a("GET").b(d).b((List<KeyValuePair>) CollectionsKt.a(new KeyValuePair("data", jSONObject.toString()))).a(), DeviceOrderSource$syncSingleOwnerOrderFromServer$1.f18240a).onErrorReturn(new DeviceOrderSource$syncSingleOwnerOrderFromServer$2(homeParam));
        Intrinsics.b(onErrorReturn, "RxApi.fetch(request) {\n …ached = true) }\n        }");
        return onErrorReturn;
    }

    @SuppressLint({"CheckResult"})
    public final void a(@NotNull String str, @NotNull Order order) {
        JSONArray jSONArray;
        String str2;
        HomeOrder homeOrder;
        Intrinsics.f(str, "homeId");
        Intrinsics.f(order, "orderData");
        a("syncOrderToServer");
        boolean z = order instanceof FrontOrder;
        if (z) {
            HomeOrder homeOrder2 = c().get(str);
            if (homeOrder2 != null) {
                Intrinsics.b(homeOrder2, "mHomesOrdersMap[homeId] ?: return");
                jSONArray = UtilKt.a(homeOrder2.d().a(), DeviceOrderSource$syncOrderToServer$orders$1.INSTANCE);
            } else {
                return;
            }
        } else if (order instanceof HomeOrder) {
            HomeOrder homeOrder3 = c().get(str);
            if (homeOrder3 != null) {
                Intrinsics.b(homeOrder3, "mHomesOrdersMap[homeId] ?: return");
                jSONArray = UtilKt.a(homeOrder3.e(), DeviceOrderSource$syncOrderToServer$orders$2.INSTANCE);
            } else {
                return;
            }
        } else if ((order instanceof CateOrder) && (homeOrder = c().get(str)) != null) {
            Intrinsics.b(homeOrder, "mHomesOrdersMap[homeId] ?: return");
            jSONArray = UtilKt.a(CollectionsKt.r(homeOrder.f()), DeviceOrderSource$syncOrderToServer$orders$3.INSTANCE);
        } else {
            return;
        }
        if (z) {
            str2 = "front";
        } else if (order instanceof HomeOrder) {
            str2 = "room";
        } else if (order instanceof CateOrder) {
            str2 = "category";
        } else {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("home_id", StringsKt.i(str));
        jSONObject.put("platform", "android");
        jSONObject.put("type", str2);
        jSONObject.put("order_data", jSONArray);
        String str3 = "syncOrderToServer: homeId: " + str + " , type: " + str2 + ": ";
        String jSONArray2 = jSONArray.toString();
        Intrinsics.b(jSONArray2, "orders.toString()");
        a(str3, jSONArray2);
        RxApi.a(new NetRequest.Builder().a("POST").b(e).b((List<KeyValuePair>) CollectionsKt.a(new KeyValuePair("data", jSONObject.toString()))).a(), DeviceOrderSource$syncOrderToServer$1.f18237a).subscribe(DeviceOrderSource$syncOrderToServer$2.f18238a, DeviceOrderSource$syncOrderToServer$3.f18239a);
    }

    /* access modifiers changed from: private */
    public final String b(String str) {
        Context appContext = SHApplication.getAppContext();
        String c2 = SharePrefsManager.c(appContext, f, g + str, "{}");
        Intrinsics.b(c2, "SharePrefsManager.getSet…EY_PREFIX + homeId, \"{}\")");
        return c2;
    }

    /* access modifiers changed from: private */
    public final void c(String str) {
        String d2 = d(str);
        if (!TextUtils.isEmpty(d2)) {
            a("saveHomeOrderCache", d2);
            Context appContext = SHApplication.getAppContext();
            SharePrefsManager.a(appContext, f, g + str, d2);
        }
    }

    /* access modifiers changed from: private */
    public final String d() {
        Context appContext = SHApplication.getAppContext();
        StringBuilder sb = new StringBuilder();
        sb.append(g);
        CoreApi a2 = CoreApi.a();
        Intrinsics.b(a2, "CoreApi.getInstance()");
        sb.append(MD5.d(a2.s()));
        String c2 = SharePrefsManager.c(appContext, f, sb.toString(), XMPConst.ai);
        if (TextUtils.equals(c2, XMPConst.ai)) {
            Context appContext2 = SHApplication.getAppContext();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(g);
            CoreApi a3 = CoreApi.a();
            Intrinsics.b(a3, "CoreApi.getInstance()");
            sb2.append(a3.s());
            c2 = SharePrefsManager.c(appContext2, f, sb2.toString(), XMPConst.ai);
        }
        Intrinsics.b(c2, "cache");
        return c2;
    }

    /* access modifiers changed from: private */
    public final void a(List<String> list) {
        if (!list.isEmpty()) {
            Context appContext = SHApplication.getAppContext();
            StringBuilder sb = new StringBuilder();
            sb.append(g);
            CoreApi a2 = CoreApi.a();
            Intrinsics.b(a2, "CoreApi.getInstance()");
            sb.append(MD5.d(a2.s()));
            SharePrefsManager.a(appContext, f, sb.toString(), UtilKt.a(list, DeviceOrderSource$saveHomeListOrderToCache$1.INSTANCE).toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0085, code lost:
        r5 = r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String d(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "dumpHomeOrderJson"
            r4.a((java.lang.String) r0)
            r0 = r5
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0021
            com.xiaomi.smarthome.homeroom.HomeManager r5 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.lang.String r0 = "HomeManager.getInstance()"
            kotlin.jvm.internal.Intrinsics.b(r5, r0)
            java.lang.String r5 = r5.l()
            java.lang.String r0 = "HomeManager.getInstance().currentHomeId"
            kotlin.jvm.internal.Intrinsics.b(r5, r0)
            goto L_0x0026
        L_0x0021:
            if (r5 != 0) goto L_0x0026
            kotlin.jvm.internal.Intrinsics.a()
        L_0x0026:
            java.util.HashMap r0 = r4.c()
            java.lang.Object r5 = r0.get(r5)
            com.xiaomi.smarthome.homeroom.device_order.HomeOrder r5 = (com.xiaomi.smarthome.homeroom.device_order.HomeOrder) r5
            if (r5 == 0) goto L_0x0082
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "home_id"
            java.lang.String r2 = r5.a()
            r0.put(r1, r2)
            java.lang.String r1 = "front_order"
            com.xiaomi.smarthome.homeroom.device_order.FrontOrder r2 = r5.d()
            java.util.ArrayList r2 = r2.a()
            java.util.List r2 = (java.util.List) r2
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$dumpHomeOrderJson$orderJson$1$1 r3 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$dumpHomeOrderJson$orderJson$1$1.INSTANCE
            kotlin.jvm.functions.Function1 r3 = (kotlin.jvm.functions.Function1) r3
            org.json.JSONArray r2 = com.xiaomi.smarthome.homeroom.device_order.UtilKt.a(r2, r3)
            r0.put(r1, r2)
            java.util.ArrayList r1 = r5.e()
            java.util.List r1 = (java.util.List) r1
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$dumpHomeOrderJson$orderJson$1$roomOrderJson$1 r2 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$dumpHomeOrderJson$orderJson$1$roomOrderJson$1.INSTANCE
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.json.JSONArray r1 = com.xiaomi.smarthome.homeroom.device_order.UtilKt.a(r1, r2)
            java.lang.String r2 = "room_order"
            r0.put(r2, r1)
            java.util.HashSet r5 = r5.f()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.CollectionsKt.r(r5)
            com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1 r1 = com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource$dumpHomeOrderJson$orderJson$1$cateOrderJson$1.INSTANCE
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            org.json.JSONArray r5 = com.xiaomi.smarthome.homeroom.device_order.UtilKt.a(r5, r1)
            java.lang.String r1 = "cate_order"
            r0.put(r1, r5)
            goto L_0x0083
        L_0x0082:
            r0 = 0
        L_0x0083:
            if (r0 == 0) goto L_0x008c
            java.lang.String r5 = r0.toString()
            if (r5 == 0) goto L_0x008c
            goto L_0x008e
        L_0x008c:
            java.lang.String r5 = ""
        L_0x008e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.device_order.DeviceOrderSource.d(java.lang.String):java.lang.String");
    }

    private final void a(Function0<Unit> function0) {
        Thread currentThread = Thread.currentThread();
        Intrinsics.b(currentThread, "Thread.currentThread()");
        if (currentThread.getId() == h) {
            try {
                function0.invoke();
            } catch (Exception unused) {
            }
        } else {
            i.execute(new DeviceOrderSource$runOnOrderThread$1(function0));
        }
    }

    /* access modifiers changed from: private */
    public final HomeOrder a(JSONObject jSONObject, boolean z) {
        FrontOrder frontOrder = new FrontOrder((ArrayList) null, 1, (DefaultConstructorMarker) null);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("front_order");
        if (optJSONArray != null) {
            UtilKt.a(optJSONArray, frontOrder.a(), DeviceOrderSource$parseHomeOrder$1.INSTANCE);
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("room_order");
        if (optJSONArray2 != null) {
            UtilKt.a(optJSONArray2, arrayList, DeviceOrderSource$parseHomeOrder$2.INSTANCE);
        }
        JSONArray optJSONArray3 = jSONObject.optJSONArray("cate_order");
        if (optJSONArray3 != null) {
            UtilKt.a(optJSONArray3, arrayList2, DeviceOrderSource$parseHomeOrder$3.INSTANCE);
        }
        String optString = jSONObject.optString("home_id");
        Intrinsics.b(optString, "json.optString(\"home_id\")");
        return new HomeOrder(optString, z, false, frontOrder, arrayList, CollectionsKt.q(arrayList2));
    }

    public final void a(@NotNull String str, @NotNull List<String> list) {
        Intrinsics.f(str, "homeId");
        Intrinsics.f(list, "orderList");
        a(this, "updateFrontOrder", (String) null, 2, (Object) null);
        a((Function0<Unit>) new DeviceOrderSource$updateFrontOrder$1(str, list));
    }

    public final void b(@NotNull String str, @NotNull List<? extends Room> list) {
        Intrinsics.f(str, "homeId");
        Intrinsics.f(list, "orderList");
        a(this, "updateRoomOrder", (String) null, 2, (Object) null);
        a((Function0<Unit>) new DeviceOrderSource$updateRoomOrder$1(str, list));
    }

    public final void a(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list) {
        Intrinsics.f(str, "homeId");
        Intrinsics.f(str2, "cateId");
        Intrinsics.f(str3, "parent_id");
        Intrinsics.f(list, "orderList");
        a(this, "updateCategoryOrder", (String) null, 2, (Object) null);
        a((Function0<Unit>) new DeviceOrderSource$updateCategoryOrder$1(str, str2, str3, list));
    }

    static /* synthetic */ void a(DeviceOrderSource deviceOrderSource, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "";
        }
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        deviceOrderSource.a(str, str2);
    }
}
