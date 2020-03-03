package com.xiaomi.smarthome.homeroom.device_order;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005j\b\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/RoomOrder;", "Lcom/xiaomi/smarthome/homeroom/device_order/Order;", "roomId", "", "orders", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;Ljava/util/ArrayList;)V", "getOrders", "()Ljava/util/ArrayList;", "getRoomId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class RoomOrder implements Order {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final String f18246a;
    @NotNull
    private final ArrayList<String> b;

    @NotNull
    public static /* synthetic */ RoomOrder a(RoomOrder roomOrder, String str, ArrayList<String> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = roomOrder.f18246a;
        }
        if ((i & 2) != 0) {
            arrayList = roomOrder.b;
        }
        return roomOrder.a(str, arrayList);
    }

    @NotNull
    public final RoomOrder a(@NotNull String str, @NotNull ArrayList<String> arrayList) {
        Intrinsics.f(str, "roomId");
        Intrinsics.f(arrayList, "orders");
        return new RoomOrder(str, arrayList);
    }

    @NotNull
    public final String c() {
        return this.f18246a;
    }

    @NotNull
    public final ArrayList<String> d() {
        return this.b;
    }

    @NotNull
    public String toString() {
        return "RoomOrder(roomId=" + this.f18246a + ", orders=" + this.b + Operators.BRACKET_END_STR;
    }

    public RoomOrder(@NotNull String str, @NotNull ArrayList<String> arrayList) {
        Intrinsics.f(str, "roomId");
        Intrinsics.f(arrayList, "orders");
        this.f18246a = str;
        this.b = arrayList;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RoomOrder(String str, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new ArrayList() : arrayList);
    }

    @NotNull
    public final String a() {
        return this.f18246a;
    }

    @NotNull
    public final ArrayList<String> b() {
        return this.b;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.a((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.smarthome.homeroom.device_order.RoomOrder");
        } else if (!Intrinsics.a((Object) this.f18246a, (Object) ((RoomOrder) obj).f18246a)) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        return this.f18246a.hashCode();
    }
}
