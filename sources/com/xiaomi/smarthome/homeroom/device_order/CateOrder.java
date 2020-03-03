package com.xiaomi.smarthome.homeroom.device_order;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J7\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/CateOrder;", "Lcom/xiaomi/smarthome/homeroom/device_order/Order;", "cateId", "", "orders", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "parentId", "(Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;)V", "getCateId", "()Ljava/lang/String;", "getOrders", "()Ljava/util/ArrayList;", "getParentId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class CateOrder implements Order {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final String f18226a;
    @NotNull
    private final ArrayList<String> b;
    @NotNull
    private final String c;

    @NotNull
    public static /* synthetic */ CateOrder a(CateOrder cateOrder, String str, ArrayList<String> arrayList, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cateOrder.f18226a;
        }
        if ((i & 2) != 0) {
            arrayList = cateOrder.b;
        }
        if ((i & 4) != 0) {
            str2 = cateOrder.c;
        }
        return cateOrder.a(str, arrayList, str2);
    }

    @NotNull
    public final CateOrder a(@NotNull String str, @NotNull ArrayList<String> arrayList, @NotNull String str2) {
        Intrinsics.f(str, "cateId");
        Intrinsics.f(arrayList, "orders");
        Intrinsics.f(str2, "parentId");
        return new CateOrder(str, arrayList, str2);
    }

    @NotNull
    public final String d() {
        return this.f18226a;
    }

    @NotNull
    public final ArrayList<String> e() {
        return this.b;
    }

    @NotNull
    public final String f() {
        return this.c;
    }

    @NotNull
    public String toString() {
        return "CateOrder(cateId=" + this.f18226a + ", orders=" + this.b + ", parentId=" + this.c + Operators.BRACKET_END_STR;
    }

    public CateOrder(@NotNull String str, @NotNull ArrayList<String> arrayList, @NotNull String str2) {
        Intrinsics.f(str, "cateId");
        Intrinsics.f(arrayList, "orders");
        Intrinsics.f(str2, "parentId");
        this.f18226a = str;
        this.b = arrayList;
        this.c = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CateOrder(String str, ArrayList arrayList, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new ArrayList() : arrayList, str2);
    }

    @NotNull
    public final String a() {
        return this.f18226a;
    }

    @NotNull
    public final ArrayList<String> b() {
        return this.b;
    }

    @NotNull
    public final String c() {
        return this.c;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.a((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            CateOrder cateOrder = (CateOrder) obj;
            if (!(!Intrinsics.a((Object) this.f18226a, (Object) cateOrder.f18226a)) && !(!Intrinsics.a((Object) this.c, (Object) cateOrder.c))) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.smarthome.homeroom.device_order.CateOrder");
    }

    public int hashCode() {
        return (this.f18226a.hashCode() * 31) + this.c.hashCode();
    }
}
