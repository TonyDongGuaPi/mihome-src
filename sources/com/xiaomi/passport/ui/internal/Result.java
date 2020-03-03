package com.xiaomi.passport.ui.internal;

import com.xiaomi.miot.support.monitor.core.BaseInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0010\u0011B\u001b\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lcom/xiaomi/passport/ui/internal/Result;", "V", "", "value", "tr", "", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "getTr", "()Ljava/lang/Throwable;", "setTr", "(Ljava/lang/Throwable;)V", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "Failure", "Success", "Lcom/xiaomi/passport/ui/internal/Result$Success;", "Lcom/xiaomi/passport/ui/internal/Result$Failure;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public abstract class Result<V> {
    @Nullable
    private Throwable tr;
    @Nullable
    private V value;

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/xiaomi/passport/ui/internal/Result$Success;", "V", "Lcom/xiaomi/passport/ui/internal/Result;", "value", "(Ljava/lang/Object;)V", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Success<V> extends Result<V> {
        public Success(V v) {
            super(v, (Throwable) null, (DefaultConstructorMarker) null);
        }
    }

    private Result(V v, Throwable th) {
        this.value = v;
        this.tr = th;
    }

    public /* synthetic */ Result(@Nullable Object obj, @Nullable Throwable th, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, th);
    }

    @Nullable
    public Throwable getTr() {
        return this.tr;
    }

    @Nullable
    public V getValue() {
        return this.value;
    }

    public void setTr(@Nullable Throwable th) {
        this.tr = th;
    }

    public void setValue(@Nullable V v) {
        this.value = v;
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/xiaomi/passport/ui/internal/Result$Failure;", "V", "Lcom/xiaomi/passport/ui/internal/Result;", "tr", "", "(Ljava/lang/Throwable;)V", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Failure<V> extends Result<V> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Failure(@NotNull Throwable th) {
            super((Object) null, th, (DefaultConstructorMarker) null);
            Intrinsics.f(th, BaseInfo.KEY_TIME_RECORD);
        }
    }
}
