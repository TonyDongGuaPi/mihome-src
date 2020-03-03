package kotlin.concurrent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001aJ\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001aL\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a\u001a\u0010\u0010\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001aJ\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001aL\u0010\u0010\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a$\u0010\u0011\u001a\u00020\f2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a0\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a0\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0012\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b\u001a8\u0010\u0015\u001a\u00020\f*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0004\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\b¨\u0006\u0016"}, d2 = {"fixedRateTimer", "Ljava/util/Timer;", "name", "", "daemon", "", "startAt", "Ljava/util/Date;", "period", "", "action", "Lkotlin/Function1;", "Ljava/util/TimerTask;", "", "Lkotlin/ExtensionFunctionType;", "initialDelay", "timer", "timerTask", "schedule", "time", "delay", "scheduleAtFixedRate", "kotlin-stdlib"}, k = 2, mv = {1, 1, 13})
@JvmName(name = "TimersKt")
public final class TimersKt {
    @InlineOnly
    private static final TimerTask a(@NotNull Timer timer, long j, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, j);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask a(@NotNull Timer timer, Date date, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, date);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask a(@NotNull Timer timer, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, j, j2);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask a(@NotNull Timer timer, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.schedule(timersKt$timerTask$1, date, j);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask b(@NotNull Timer timer, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.scheduleAtFixedRate(timersKt$timerTask$1, j, j2);
        return timersKt$timerTask$1;
    }

    @InlineOnly
    private static final TimerTask b(@NotNull Timer timer, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        TimerTask timersKt$timerTask$1 = new TimersKt$timerTask$1(function1);
        timer.scheduleAtFixedRate(timersKt$timerTask$1, date, j);
        return timersKt$timerTask$1;
    }

    @NotNull
    @PublishedApi
    public static final Timer a(@Nullable String str, boolean z) {
        return str == null ? new Timer(z) : new Timer(str, z);
    }

    @InlineOnly
    static /* synthetic */ Timer a(String str, boolean z, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        Timer a2 = a(str, z);
        a2.schedule(new TimersKt$timerTask$1(function1), j, j2);
        return a2;
    }

    @InlineOnly
    private static final Timer a(String str, boolean z, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        Timer a2 = a(str, z);
        a2.schedule(new TimersKt$timerTask$1(function1), j, j2);
        return a2;
    }

    @InlineOnly
    static /* synthetic */ Timer a(String str, boolean z, Date date, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        Timer a2 = a(str, z);
        a2.schedule(new TimersKt$timerTask$1(function1), date, j);
        return a2;
    }

    @InlineOnly
    private static final Timer a(String str, boolean z, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        Timer a2 = a(str, z);
        a2.schedule(new TimersKt$timerTask$1(function1), date, j);
        return a2;
    }

    @InlineOnly
    static /* synthetic */ Timer b(String str, boolean z, long j, long j2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        Timer a2 = a(str, z);
        a2.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), j, j2);
        return a2;
    }

    @InlineOnly
    private static final Timer b(String str, boolean z, long j, long j2, Function1<? super TimerTask, Unit> function1) {
        Timer a2 = a(str, z);
        a2.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), j, j2);
        return a2;
    }

    @InlineOnly
    static /* synthetic */ Timer b(String str, boolean z, Date date, long j, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        Timer a2 = a(str, z);
        a2.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), date, j);
        return a2;
    }

    @InlineOnly
    private static final Timer b(String str, boolean z, Date date, long j, Function1<? super TimerTask, Unit> function1) {
        Timer a2 = a(str, z);
        a2.scheduleAtFixedRate(new TimersKt$timerTask$1(function1), date, j);
        return a2;
    }

    @InlineOnly
    private static final TimerTask a(Function1<? super TimerTask, Unit> function1) {
        return new TimersKt$timerTask$1(function1);
    }
}
