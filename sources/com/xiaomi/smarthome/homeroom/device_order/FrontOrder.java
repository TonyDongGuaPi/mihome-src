package com.xiaomi.smarthome.homeroom.device_order;

import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\n\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/xiaomi/smarthome/homeroom/device_order/FrontOrder;", "Lcom/xiaomi/smarthome/homeroom/device_order/Order;", "orders", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getOrders", "()Ljava/util/ArrayList;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class FrontOrder implements Order {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<String> f18242a;

    public FrontOrder() {
        this((ArrayList) null, 1, (DefaultConstructorMarker) null);
    }

    @NotNull
    public static /* synthetic */ FrontOrder a(FrontOrder frontOrder, ArrayList<String> arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = frontOrder.f18242a;
        }
        return frontOrder.a(arrayList);
    }

    @NotNull
    public final FrontOrder a(@NotNull ArrayList<String> arrayList) {
        Intrinsics.f(arrayList, "orders");
        return new FrontOrder(arrayList);
    }

    @NotNull
    public final ArrayList<String> b() {
        return this.f18242a;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof FrontOrder) && Intrinsics.a((Object) this.f18242a, (Object) ((FrontOrder) obj).f18242a);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<String> arrayList = this.f18242a;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "FrontOrder(orders=" + this.f18242a + Operators.BRACKET_END_STR;
    }

    public FrontOrder(@NotNull ArrayList<String> arrayList) {
        Intrinsics.f(arrayList, "orders");
        this.f18242a = arrayList;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FrontOrder(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    @NotNull
    public final ArrayList<String> a() {
        return this.f18242a;
    }
}
