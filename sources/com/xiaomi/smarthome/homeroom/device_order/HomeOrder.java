package com.xiaomi.smarthome.homeroom.device_order;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Ba\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010¢\u0006\u0002\u0010\u0011J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\u0019\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fHÆ\u0003J\u0019\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010HÆ\u0003Je\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010HÆ\u0001J\u0013\u0010&\u001a\u00020\u00052\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006,"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/HomeOrder;", "Lcom/xiaomi/smarthome/homeroom/device_order/Order;", "homeId", "", "isCached", "", "onlyFrontIsCached", "frontOrder", "Lcom/xiaomi/smarthome/homeroom/device_order/FrontOrder;", "roomOrders", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/homeroom/device_order/RoomOrder;", "Lkotlin/collections/ArrayList;", "cateOrders", "Ljava/util/HashSet;", "Lcom/xiaomi/smarthome/homeroom/device_order/CateOrder;", "Lkotlin/collections/HashSet;", "(Ljava/lang/String;ZZLcom/xiaomi/smarthome/homeroom/device_order/FrontOrder;Ljava/util/ArrayList;Ljava/util/HashSet;)V", "getCateOrders", "()Ljava/util/HashSet;", "getFrontOrder", "()Lcom/xiaomi/smarthome/homeroom/device_order/FrontOrder;", "getHomeId", "()Ljava/lang/String;", "()Z", "setCached", "(Z)V", "getOnlyFrontIsCached", "setOnlyFrontIsCached", "getRoomOrders", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class HomeOrder implements Order {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final String f18243a;
    private boolean b;
    private boolean c;
    @NotNull
    private final FrontOrder d;
    @NotNull
    private final ArrayList<RoomOrder> e;
    @NotNull
    private final HashSet<CateOrder> f;

    public HomeOrder() {
        this((String) null, false, false, (FrontOrder) null, (ArrayList) null, (HashSet) null, 63, (DefaultConstructorMarker) null);
    }

    @NotNull
    public static /* synthetic */ HomeOrder a(HomeOrder homeOrder, String str, boolean z, boolean z2, FrontOrder frontOrder, ArrayList<RoomOrder> arrayList, HashSet<CateOrder> hashSet, int i, Object obj) {
        if ((i & 1) != 0) {
            str = homeOrder.f18243a;
        }
        if ((i & 2) != 0) {
            z = homeOrder.b;
        }
        boolean z3 = z;
        if ((i & 4) != 0) {
            z2 = homeOrder.c;
        }
        boolean z4 = z2;
        if ((i & 8) != 0) {
            frontOrder = homeOrder.d;
        }
        FrontOrder frontOrder2 = frontOrder;
        if ((i & 16) != 0) {
            arrayList = homeOrder.e;
        }
        ArrayList<RoomOrder> arrayList2 = arrayList;
        if ((i & 32) != 0) {
            hashSet = homeOrder.f;
        }
        return homeOrder.a(str, z3, z4, frontOrder2, arrayList2, hashSet);
    }

    @NotNull
    public final HomeOrder a(@NotNull String str, boolean z, boolean z2, @NotNull FrontOrder frontOrder, @NotNull ArrayList<RoomOrder> arrayList, @NotNull HashSet<CateOrder> hashSet) {
        Intrinsics.f(str, "homeId");
        Intrinsics.f(frontOrder, "frontOrder");
        Intrinsics.f(arrayList, "roomOrders");
        Intrinsics.f(hashSet, "cateOrders");
        return new HomeOrder(str, z, z2, frontOrder, arrayList, hashSet);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof HomeOrder) {
                HomeOrder homeOrder = (HomeOrder) obj;
                if (Intrinsics.a((Object) this.f18243a, (Object) homeOrder.f18243a)) {
                    if (this.b == homeOrder.b) {
                        if (!(this.c == homeOrder.c) || !Intrinsics.a((Object) this.d, (Object) homeOrder.d) || !Intrinsics.a((Object) this.e, (Object) homeOrder.e) || !Intrinsics.a((Object) this.f, (Object) homeOrder.f)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String g() {
        return this.f18243a;
    }

    public final boolean h() {
        return this.b;
    }

    public int hashCode() {
        String str = this.f18243a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.b;
        if (z) {
            z = true;
        }
        int i2 = (hashCode + (z ? 1 : 0)) * 31;
        boolean z2 = this.c;
        if (z2) {
            z2 = true;
        }
        int i3 = (i2 + (z2 ? 1 : 0)) * 31;
        FrontOrder frontOrder = this.d;
        int hashCode2 = (i3 + (frontOrder != null ? frontOrder.hashCode() : 0)) * 31;
        ArrayList<RoomOrder> arrayList = this.e;
        int hashCode3 = (hashCode2 + (arrayList != null ? arrayList.hashCode() : 0)) * 31;
        HashSet<CateOrder> hashSet = this.f;
        if (hashSet != null) {
            i = hashSet.hashCode();
        }
        return hashCode3 + i;
    }

    public final boolean i() {
        return this.c;
    }

    @NotNull
    public final FrontOrder j() {
        return this.d;
    }

    @NotNull
    public final ArrayList<RoomOrder> k() {
        return this.e;
    }

    @NotNull
    public final HashSet<CateOrder> l() {
        return this.f;
    }

    @NotNull
    public String toString() {
        return "HomeOrder(homeId=" + this.f18243a + ", isCached=" + this.b + ", onlyFrontIsCached=" + this.c + ", frontOrder=" + this.d + ", roomOrders=" + this.e + ", cateOrders=" + this.f + Operators.BRACKET_END_STR;
    }

    public HomeOrder(@NotNull String str, boolean z, boolean z2, @NotNull FrontOrder frontOrder, @NotNull ArrayList<RoomOrder> arrayList, @NotNull HashSet<CateOrder> hashSet) {
        Intrinsics.f(str, "homeId");
        Intrinsics.f(frontOrder, "frontOrder");
        Intrinsics.f(arrayList, "roomOrders");
        Intrinsics.f(hashSet, "cateOrders");
        this.f18243a = str;
        this.b = z;
        this.c = z2;
        this.d = frontOrder;
        this.e = arrayList;
        this.f = hashSet;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ HomeOrder(java.lang.String r5, boolean r6, boolean r7, com.xiaomi.smarthome.homeroom.device_order.FrontOrder r8, java.util.ArrayList r9, java.util.HashSet r10, int r11, kotlin.jvm.internal.DefaultConstructorMarker r12) {
        /*
            r4 = this;
            r12 = r11 & 1
            if (r12 == 0) goto L_0x0006
            java.lang.String r5 = ""
        L_0x0006:
            r12 = r11 & 2
            r0 = 1
            if (r12 == 0) goto L_0x000d
            r12 = 1
            goto L_0x000e
        L_0x000d:
            r12 = r6
        L_0x000e:
            r6 = r11 & 4
            if (r6 == 0) goto L_0x0015
            r7 = 0
            r1 = 0
            goto L_0x0016
        L_0x0015:
            r1 = r7
        L_0x0016:
            r6 = r11 & 8
            if (r6 == 0) goto L_0x0020
            com.xiaomi.smarthome.homeroom.device_order.FrontOrder r8 = new com.xiaomi.smarthome.homeroom.device_order.FrontOrder
            r6 = 0
            r8.<init>(r6, r0, r6)
        L_0x0020:
            r0 = r8
            r6 = r11 & 16
            if (r6 == 0) goto L_0x002a
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x002a:
            r2 = r9
            r6 = r11 & 32
            if (r6 == 0) goto L_0x0034
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
        L_0x0034:
            r3 = r10
            r6 = r4
            r7 = r5
            r8 = r12
            r9 = r1
            r10 = r0
            r11 = r2
            r12 = r3
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.device_order.HomeOrder.<init>(java.lang.String, boolean, boolean, com.xiaomi.smarthome.homeroom.device_order.FrontOrder, java.util.ArrayList, java.util.HashSet, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @NotNull
    public final String a() {
        return this.f18243a;
    }

    public final void a(boolean z) {
        this.b = z;
    }

    public final void b(boolean z) {
        this.c = z;
    }

    public final boolean b() {
        return this.b;
    }

    public final boolean c() {
        return this.c;
    }

    @NotNull
    public final FrontOrder d() {
        return this.d;
    }

    @NotNull
    public final ArrayList<RoomOrder> e() {
        return this.e;
    }

    @NotNull
    public final HashSet<CateOrder> f() {
        return this.f;
    }
}
