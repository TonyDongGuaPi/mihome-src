package com.xiaomi.smarthome.homeroom.device_order;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006J,\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00060\f2\u0010\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010J,\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u001e\u0010\u0014\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0006¨\u0006\u0018"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/OrderCompat;", "", "()V", "changeOrder", "", "orderList", "", "", "homeId", "getCachedHomeOrders", "Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "getHomeOrders", "Lio/reactivex/Observable;", "homes", "Lcom/xiaomi/smarthome/homeroom/model/Home;", "needCache", "", "saveCategoryDeviceOrders", "cateId", "parent_id", "saveOrders", "rooms", "Lcom/xiaomi/smarthome/homeroom/model/Room;", "HomeParam", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class OrderCompat {

    /* renamed from: a  reason: collision with root package name */
    public static final OrderCompat f18244a = new OrderCompat();

    private OrderCompat() {
    }

    public final void a(@Nullable String str, @NotNull List<? extends Room> list) {
        Intrinsics.f(list, "rooms");
        if (str != null) {
            DeviceOrderSource.b.b(str, list);
        }
    }

    public final void a(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull List<String> list) {
        Intrinsics.f(str, "homeId");
        Intrinsics.f(str2, "cateId");
        Intrinsics.f(str3, "parent_id");
        Intrinsics.f(list, "orderList");
        DeviceOrderSource.b.a(str, str2, str3, list);
    }

    public final void a(@NotNull List<String> list, @Nullable String str) {
        Intrinsics.f(list, "orderList");
        if (str != null) {
            DeviceOrderSource.b.a(str, list);
        }
    }

    @NotNull
    public final Observable<List<HomeOrder>> a(@Nullable List<? extends Home> list, boolean z) {
        if (list != null) {
            Iterable iterable = list;
            if (!CollectionsKt.m(iterable).isEmpty()) {
                Iterable m = CollectionsKt.m(iterable);
                Collection arrayList = new ArrayList();
                for (Object next : m) {
                    if (((Home) next).p()) {
                        arrayList.add(next);
                    }
                }
                List list2 = (List) arrayList;
                Collection arrayList2 = new ArrayList();
                for (Object next2 : m) {
                    if (!((Home) next2).p()) {
                        arrayList2.add(next2);
                    }
                }
                List<Home> list3 = (List) arrayList2;
                ArrayList arrayList3 = new ArrayList();
                if (!list2.isEmpty()) {
                    CoreApi a2 = CoreApi.a();
                    Intrinsics.b(a2, "CoreApi.getInstance()");
                    String s = a2.s();
                    Intrinsics.b(s, "CoreApi.getInstance().miId");
                    Long i = StringsKt.i(s);
                    Iterable<Home> iterable2 = list2;
                    Collection arrayList4 = new ArrayList(CollectionsKt.a(iterable2, 10));
                    for (Home j : iterable2) {
                        arrayList4.add(j.j());
                    }
                    arrayList3.add(new HomeParam(i, (List) arrayList4, false, 4, (DefaultConstructorMarker) null));
                }
                if (!list3.isEmpty()) {
                    HashMap hashMap = new HashMap();
                    for (Home home : list3) {
                        Map map = hashMap;
                        Long valueOf = Long.valueOf(home.o());
                        Object obj = map.get(valueOf);
                        if (obj == null) {
                            obj = new ArrayList();
                            map.put(valueOf, obj);
                        }
                        ((ArrayList) obj).add(home);
                    }
                    Map map2 = hashMap;
                    Collection arrayList5 = new ArrayList(map2.size());
                    for (Map.Entry entry : map2.entrySet()) {
                        arrayList5.add(TuplesKt.a(entry.getKey(), entry.getValue()));
                    }
                    Iterable<Pair> iterable3 = (List) arrayList5;
                    Collection arrayList6 = new ArrayList(CollectionsKt.a(iterable3, 10));
                    for (Pair pair : iterable3) {
                        Long l = (Long) pair.getFirst();
                        Collection arrayList7 = new ArrayList();
                        for (Home j2 : (Iterable) pair.getSecond()) {
                            String j3 = j2.j();
                            if (j3 != null) {
                                arrayList7.add(j3);
                            }
                        }
                        arrayList6.add(new HomeParam(l, (List) arrayList7, false));
                    }
                    arrayList3.addAll((List) arrayList6);
                }
                return DeviceOrderSource.b.a((List<HomeParam>) arrayList3, z);
            }
        }
        Observable<List<HomeOrder>> just = Observable.just(CollectionsKt.a());
        Intrinsics.b(just, "Observable.just(emptyList())");
        return just;
    }

    @NotNull
    public final List<HomeOrder> a() {
        return DeviceOrderSource.b.a();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J4\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\f\"\u0004\b\r\u0010\u000eR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/OrderCompat$HomeParam;", "", "ownId", "", "homeIds", "", "", "isOwner", "", "(Ljava/lang/Long;Ljava/util/List;Z)V", "getHomeIds", "()Ljava/util/List;", "()Z", "setOwner", "(Z)V", "getOwnId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Ljava/util/List;Z)Lcom/xiaomi/smarthome/homeroom/device_order/OrderCompat$HomeParam;", "equals", "other", "hashCode", "", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class HomeParam {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        private final Long f18245a;
        @NotNull
        private final List<String> b;
        private boolean c;

        @NotNull
        public static /* synthetic */ HomeParam a(HomeParam homeParam, Long l, List<String> list, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                l = homeParam.f18245a;
            }
            if ((i & 2) != 0) {
                list = homeParam.b;
            }
            if ((i & 4) != 0) {
                z = homeParam.c;
            }
            return homeParam.a(l, list, z);
        }

        @NotNull
        public final HomeParam a(@Nullable Long l, @NotNull List<String> list, boolean z) {
            Intrinsics.f(list, "homeIds");
            return new HomeParam(l, list, z);
        }

        @Nullable
        public final Long d() {
            return this.f18245a;
        }

        @NotNull
        public final List<String> e() {
            return this.b;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof HomeParam) {
                    HomeParam homeParam = (HomeParam) obj;
                    if (Intrinsics.a((Object) this.f18245a, (Object) homeParam.f18245a) && Intrinsics.a((Object) this.b, (Object) homeParam.b)) {
                        if (this.c == homeParam.c) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public final boolean f() {
            return this.c;
        }

        public int hashCode() {
            Long l = this.f18245a;
            int i = 0;
            int hashCode = (l != null ? l.hashCode() : 0) * 31;
            List<String> list = this.b;
            if (list != null) {
                i = list.hashCode();
            }
            int i2 = (hashCode + i) * 31;
            boolean z = this.c;
            if (z) {
                z = true;
            }
            return i2 + (z ? 1 : 0);
        }

        @NotNull
        public String toString() {
            return "HomeParam(ownId=" + this.f18245a + ", homeIds=" + this.b + ", isOwner=" + this.c + Operators.BRACKET_END_STR;
        }

        public HomeParam(@Nullable Long l, @NotNull List<String> list, boolean z) {
            Intrinsics.f(list, "homeIds");
            this.f18245a = l;
            this.b = list;
            this.c = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ HomeParam(Long l, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(l, list, (i & 4) != 0 ? true : z);
        }

        @Nullable
        public final Long a() {
            return this.f18245a;
        }

        public final void a(boolean z) {
            this.c = z;
        }

        @NotNull
        public final List<String> b() {
            return this.b;
        }

        public final boolean c() {
            return this.c;
        }
    }
}
