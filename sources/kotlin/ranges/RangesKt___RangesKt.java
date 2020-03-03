package kotlin.ranges;

import com.xiaomi.infra.galaxy.fds.Common;
import java.util.NoSuchElementException;
import javax.jmdns.impl.constants.DNSRecordClass;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.CharProgression;
import kotlin.ranges.IntProgression;
import kotlin.ranges.LongProgression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a'\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005\u001a\u0012\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0006\u001a\u0012\u0010\u0000\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007\u001a\u0012\u0010\u0000\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b\u001a\u0012\u0010\u0000\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t\u001a\u0012\u0010\u0000\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n\u001a'\u0010\u000b\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001¢\u0006\u0002\u0010\u0004\u001a\u0012\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u0012\u0010\u000b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u0012\u0010\u000b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u0012\u0010\u000b\u001a\u00020\b*\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0012\u0010\u000b\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0012\u0010\u000b\u001a\u00020\n*\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a3\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\b\u0010\u0003\u001a\u0004\u0018\u0001H\u00012\b\u0010\f\u001a\u0004\u0018\u0001H\u0001¢\u0006\u0002\u0010\u000e\u001a/\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a-\u0010\r\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0002H\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a\u001a\u0010\r\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005\u001a\u001a\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006\u001a\u001a\u0010\r\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007\u001a\u001a\u0010\r\u001a\u00020\b*\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b\u001a\u0018\u0010\r\u001a\u00020\b*\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0012\u001a\u001a\u0010\r\u001a\u00020\t*\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t\u001a\u0018\u0010\r\u001a\u00020\t*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0012\u001a\u001a\u0010\r\u001a\u00020\n*\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\n¢\u0006\u0002\u0010\u0019\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001b\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00060\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001c\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001d\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001e\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0002¢\u0006\u0002\b\u001f\u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0005H\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0006H\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\u0007H\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0002\b \u001a \u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\n0\u00122\u0006\u0010\u001a\u001a\u00020\tH\u0002¢\u0006\u0002\b \u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020!2\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\u0010\"\u001a\u001c\u0010\u0014\u001a\u00020\u0015*\u00020#2\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\n¢\u0006\u0002\u0010$\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0004\u001a\u0015\u0010%\u001a\u00020)*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010%\u001a\u00020(*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010%\u001a\u00020&*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0004\u001a\r\u0010*\u001a\u00020\u0018*\u00020\u0016H\b\u001a\u0014\u0010*\u001a\u00020\u0018*\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0007\u001a\r\u0010*\u001a\u00020\b*\u00020!H\b\u001a\u0014\u0010*\u001a\u00020\b*\u00020!2\u0006\u0010*\u001a\u00020+H\u0007\u001a\r\u0010*\u001a\u00020\t*\u00020#H\b\u001a\u0014\u0010*\u001a\u00020\t*\u00020#2\u0006\u0010*\u001a\u00020+H\u0007\u001a\n\u0010,\u001a\u00020)*\u00020)\u001a\n\u0010,\u001a\u00020&*\u00020&\u001a\n\u0010,\u001a\u00020(*\u00020(\u001a\u0015\u0010-\u001a\u00020)*\u00020)2\u0006\u0010-\u001a\u00020\bH\u0004\u001a\u0015\u0010-\u001a\u00020&*\u00020&2\u0006\u0010-\u001a\u00020\bH\u0004\u001a\u0015\u0010-\u001a\u00020(*\u00020(2\u0006\u0010-\u001a\u00020\tH\u0004\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0000¢\u0006\u0002\u0010/\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H\u0000¢\u0006\u0002\u00100\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\bH\u0000¢\u0006\u0002\u00101\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\tH\u0000¢\u0006\u0002\u00102\u001a\u0013\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020\nH\u0000¢\u0006\u0002\u00103\u001a\u0013\u00104\u001a\u0004\u0018\u00010\b*\u00020\u0006H\u0000¢\u0006\u0002\u00105\u001a\u0013\u00104\u001a\u0004\u0018\u00010\b*\u00020\u0007H\u0000¢\u0006\u0002\u00106\u001a\u0013\u00104\u001a\u0004\u0018\u00010\b*\u00020\tH\u0000¢\u0006\u0002\u00107\u001a\u0013\u00108\u001a\u0004\u0018\u00010\t*\u00020\u0006H\u0000¢\u0006\u0002\u00109\u001a\u0013\u00108\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0000¢\u0006\u0002\u0010:\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\u0006H\u0000¢\u0006\u0002\u0010<\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\u0007H\u0000¢\u0006\u0002\u0010=\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\bH\u0000¢\u0006\u0002\u0010>\u001a\u0013\u0010;\u001a\u0004\u0018\u00010\n*\u00020\tH\u0000¢\u0006\u0002\u0010?\u001a\u0015\u0010@\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\u00052\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\u00052\u0006\u0010'\u001a\u00020\nH\u0004\u001a\u0015\u0010@\u001a\u00020\u0016*\u00020\u00182\u0006\u0010'\u001a\u00020\u0018H\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\b2\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\b2\u0006\u0010'\u001a\u00020\nH\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\t2\u0006\u0010'\u001a\u00020\nH\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\u0005H\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\bH\u0004\u001a\u0015\u0010@\u001a\u00020#*\u00020\n2\u0006\u0010'\u001a\u00020\tH\u0004\u001a\u0015\u0010@\u001a\u00020!*\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0004¨\u0006A"}, d2 = {"coerceAtLeast", "T", "", "minimumValue", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "coerceAtMost", "maximumValue", "coerceIn", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "range", "Lkotlin/ranges/ClosedFloatingPointRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedFloatingPointRange;)Ljava/lang/Comparable;", "Lkotlin/ranges/ClosedRange;", "(Ljava/lang/Comparable;Lkotlin/ranges/ClosedRange;)Ljava/lang/Comparable;", "contains", "", "Lkotlin/ranges/CharRange;", "element", "", "(Lkotlin/ranges/CharRange;Ljava/lang/Character;)Z", "value", "byteRangeContains", "doubleRangeContains", "floatRangeContains", "intRangeContains", "longRangeContains", "shortRangeContains", "Lkotlin/ranges/IntRange;", "(Lkotlin/ranges/IntRange;Ljava/lang/Integer;)Z", "Lkotlin/ranges/LongRange;", "(Lkotlin/ranges/LongRange;Ljava/lang/Long;)Z", "downTo", "Lkotlin/ranges/IntProgression;", "to", "Lkotlin/ranges/LongProgression;", "Lkotlin/ranges/CharProgression;", "random", "Lkotlin/random/Random;", "reversed", "step", "toByteExactOrNull", "(D)Ljava/lang/Byte;", "(F)Ljava/lang/Byte;", "(I)Ljava/lang/Byte;", "(J)Ljava/lang/Byte;", "(S)Ljava/lang/Byte;", "toIntExactOrNull", "(D)Ljava/lang/Integer;", "(F)Ljava/lang/Integer;", "(J)Ljava/lang/Integer;", "toLongExactOrNull", "(D)Ljava/lang/Long;", "(F)Ljava/lang/Long;", "toShortExactOrNull", "(D)Ljava/lang/Short;", "(F)Ljava/lang/Short;", "(I)Ljava/lang/Short;", "(J)Ljava/lang/Short;", "until", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/ranges/RangesKt")
class RangesKt___RangesKt extends RangesKt__RangesKt {
    public static final double b(double d, double d2) {
        return d < d2 ? d2 : d;
    }

    public static final float b(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    public static final byte c(byte b, byte b2) {
        return b < b2 ? b2 : b;
    }

    public static final double c(double d, double d2) {
        return d > d2 ? d2 : d;
    }

    public static final float c(float f, float f2) {
        return f > f2 ? f2 : f;
    }

    public static final int c(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static final long c(long j, long j2) {
        return j < j2 ? j2 : j;
    }

    public static final short c(short s, short s2) {
        return s < s2 ? s2 : s;
    }

    public static final byte d(byte b, byte b2) {
        return b > b2 ? b2 : b;
    }

    public static final int d(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    public static final long d(long j, long j2) {
        return j > j2 ? j2 : j;
    }

    public static final short d(short s, short s2) {
        return s > s2 ? s2 : s;
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int a(@NotNull IntRange intRange) {
        return RangesKt.a(intRange, (Random) Random.b);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final long a(@NotNull LongRange longRange) {
        return RangesKt.a(longRange, (Random) Random.b);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char a(@NotNull CharRange charRange) {
        return RangesKt.a(charRange, (Random) Random.b);
    }

    @SinceKotlin(version = "1.3")
    public static final int a(@NotNull IntRange intRange, @NotNull Random random) {
        Intrinsics.f(intRange, "receiver$0");
        Intrinsics.f(random, "random");
        try {
            return RandomKt.a(random, intRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    public static final long a(@NotNull LongRange longRange, @NotNull Random random) {
        Intrinsics.f(longRange, "receiver$0");
        Intrinsics.f(random, "random");
        try {
            return RandomKt.a(random, longRange);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    public static final char a(@NotNull CharRange charRange, @NotNull Random random) {
        Intrinsics.f(charRange, "receiver$0");
        Intrinsics.f(random, "random");
        try {
            return (char) random.a((int) charRange.a(), charRange.b() + 1);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean a(@NotNull IntRange intRange, Integer num) {
        Intrinsics.f(intRange, "receiver$0");
        return num != null && intRange.a(num.intValue());
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean a(@NotNull LongRange longRange, Long l) {
        Intrinsics.f(longRange, "receiver$0");
        return l != null && longRange.a(l.longValue());
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final boolean a(@NotNull CharRange charRange, Character ch) {
        Intrinsics.f(charRange, "receiver$0");
        return ch != null && charRange.a(ch.charValue());
    }

    @JvmName(name = "intRangeContains")
    public static final boolean a(@NotNull ClosedRange<Integer> closedRange, byte b) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Integer.valueOf(b));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean b(@NotNull ClosedRange<Long> closedRange, byte b) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Long.valueOf((long) b));
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean c(@NotNull ClosedRange<Short> closedRange, byte b) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Short.valueOf((short) b));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean d(@NotNull ClosedRange<Double> closedRange, byte b) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Double.valueOf((double) b));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean e(@NotNull ClosedRange<Float> closedRange, byte b) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Float.valueOf((float) b));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "intRangeContains")
    public static final boolean a(@NotNull ClosedRange<Integer> closedRange, double d) {
        Intrinsics.f(closedRange, "receiver$0");
        Integer b = RangesKt.b(d);
        if (b != null) {
            return closedRange.a(b);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "longRangeContains")
    public static final boolean b(@NotNull ClosedRange<Long> closedRange, double d) {
        Intrinsics.f(closedRange, "receiver$0");
        Long c = RangesKt.c(d);
        if (c != null) {
            return closedRange.a(c);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "byteRangeContains")
    public static final boolean c(@NotNull ClosedRange<Byte> closedRange, double d) {
        Intrinsics.f(closedRange, "receiver$0");
        Byte a2 = RangesKt.a(d);
        if (a2 != null) {
            return closedRange.a(a2);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "shortRangeContains")
    public static final boolean d(@NotNull ClosedRange<Short> closedRange, double d) {
        Intrinsics.f(closedRange, "receiver$0");
        Short d2 = RangesKt.d(d);
        if (d2 != null) {
            return closedRange.a(d2);
        }
        return false;
    }

    @JvmName(name = "floatRangeContains")
    public static final boolean e(@NotNull ClosedRange<Float> closedRange, double d) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Float.valueOf((float) d));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "intRangeContains")
    public static final boolean a(@NotNull ClosedRange<Integer> closedRange, float f) {
        Intrinsics.f(closedRange, "receiver$0");
        Integer b = RangesKt.b(f);
        if (b != null) {
            return closedRange.a(b);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "longRangeContains")
    public static final boolean b(@NotNull ClosedRange<Long> closedRange, float f) {
        Intrinsics.f(closedRange, "receiver$0");
        Long c = RangesKt.c(f);
        if (c != null) {
            return closedRange.a(c);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "byteRangeContains")
    public static final boolean c(@NotNull ClosedRange<Byte> closedRange, float f) {
        Intrinsics.f(closedRange, "receiver$0");
        Byte a2 = RangesKt.a(f);
        if (a2 != null) {
            return closedRange.a(a2);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "shortRangeContains")
    public static final boolean d(@NotNull ClosedRange<Short> closedRange, float f) {
        Intrinsics.f(closedRange, "receiver$0");
        Short d = RangesKt.d(f);
        if (d != null) {
            return closedRange.a(d);
        }
        return false;
    }

    @JvmName(name = "doubleRangeContains")
    public static final boolean e(@NotNull ClosedRange<Double> closedRange, float f) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Double.valueOf((double) f));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean a(@NotNull ClosedRange<Long> closedRange, int i) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Long.valueOf((long) i));
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean b(@NotNull ClosedRange<Byte> closedRange, int i) {
        Intrinsics.f(closedRange, "receiver$0");
        Byte a2 = RangesKt.a(i);
        if (a2 != null) {
            return closedRange.a(a2);
        }
        return false;
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean c(@NotNull ClosedRange<Short> closedRange, int i) {
        Intrinsics.f(closedRange, "receiver$0");
        Short b = RangesKt.b(i);
        if (b != null) {
            return closedRange.a(b);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean d(@NotNull ClosedRange<Double> closedRange, int i) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Double.valueOf((double) i));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean e(@NotNull ClosedRange<Float> closedRange, int i) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Float.valueOf((float) i));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean a(@NotNull ClosedRange<Integer> closedRange, long j) {
        Intrinsics.f(closedRange, "receiver$0");
        Integer b = RangesKt.b(j);
        if (b != null) {
            return closedRange.a(b);
        }
        return false;
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean b(@NotNull ClosedRange<Byte> closedRange, long j) {
        Intrinsics.f(closedRange, "receiver$0");
        Byte a2 = RangesKt.a(j);
        if (a2 != null) {
            return closedRange.a(a2);
        }
        return false;
    }

    @JvmName(name = "shortRangeContains")
    public static final boolean c(@NotNull ClosedRange<Short> closedRange, long j) {
        Intrinsics.f(closedRange, "receiver$0");
        Short c = RangesKt.c(j);
        if (c != null) {
            return closedRange.a(c);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean d(@NotNull ClosedRange<Double> closedRange, long j) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Double.valueOf((double) j));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean e(@NotNull ClosedRange<Float> closedRange, long j) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Float.valueOf((float) j));
    }

    @JvmName(name = "intRangeContains")
    public static final boolean a(@NotNull ClosedRange<Integer> closedRange, short s) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Integer.valueOf(s));
    }

    @JvmName(name = "longRangeContains")
    public static final boolean b(@NotNull ClosedRange<Long> closedRange, short s) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Long.valueOf((long) s));
    }

    @JvmName(name = "byteRangeContains")
    public static final boolean c(@NotNull ClosedRange<Byte> closedRange, short s) {
        Intrinsics.f(closedRange, "receiver$0");
        Byte a2 = RangesKt.a(s);
        if (a2 != null) {
            return closedRange.a(a2);
        }
        return false;
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "doubleRangeContains")
    public static final boolean d(@NotNull ClosedRange<Double> closedRange, short s) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Double.valueOf((double) s));
    }

    @Deprecated(message = "This `contains` operation mixing integer and floating point arguments has ambiguous semantics and is going to be removed.")
    @JvmName(name = "floatRangeContains")
    public static final boolean e(@NotNull ClosedRange<Float> closedRange, short s) {
        Intrinsics.f(closedRange, "receiver$0");
        return closedRange.a(Float.valueOf((float) s));
    }

    @NotNull
    public static final IntProgression a(int i, byte b) {
        return IntProgression.f2848a.a(i, b, -1);
    }

    @NotNull
    public static final LongProgression a(long j, byte b) {
        return LongProgression.f2850a.a(j, (long) b, -1);
    }

    @NotNull
    public static final IntProgression a(byte b, byte b2) {
        return IntProgression.f2848a.a(b, b2, -1);
    }

    @NotNull
    public static final IntProgression a(short s, byte b) {
        return IntProgression.f2848a.a(s, b, -1);
    }

    @NotNull
    public static final CharProgression a(char c, char c2) {
        return CharProgression.f2843a.a(c, c2, -1);
    }

    @NotNull
    public static final IntProgression a(int i, int i2) {
        return IntProgression.f2848a.a(i, i2, -1);
    }

    @NotNull
    public static final LongProgression a(long j, int i) {
        return LongProgression.f2850a.a(j, (long) i, -1);
    }

    @NotNull
    public static final IntProgression a(byte b, int i) {
        return IntProgression.f2848a.a(b, i, -1);
    }

    @NotNull
    public static final IntProgression a(short s, int i) {
        return IntProgression.f2848a.a(s, i, -1);
    }

    @NotNull
    public static final LongProgression a(int i, long j) {
        return LongProgression.f2850a.a((long) i, j, -1);
    }

    @NotNull
    public static final LongProgression a(long j, long j2) {
        return LongProgression.f2850a.a(j, j2, -1);
    }

    @NotNull
    public static final LongProgression a(byte b, long j) {
        return LongProgression.f2850a.a((long) b, j, -1);
    }

    @NotNull
    public static final LongProgression a(short s, long j) {
        return LongProgression.f2850a.a((long) s, j, -1);
    }

    @NotNull
    public static final IntProgression a(int i, short s) {
        return IntProgression.f2848a.a(i, s, -1);
    }

    @NotNull
    public static final LongProgression a(long j, short s) {
        return LongProgression.f2850a.a(j, (long) s, -1);
    }

    @NotNull
    public static final IntProgression a(byte b, short s) {
        return IntProgression.f2848a.a(b, s, -1);
    }

    @NotNull
    public static final IntProgression a(short s, short s2) {
        return IntProgression.f2848a.a(s, s2, -1);
    }

    @NotNull
    public static final IntProgression a(@NotNull IntProgression intProgression) {
        Intrinsics.f(intProgression, "receiver$0");
        return IntProgression.f2848a.a(intProgression.b(), intProgression.a(), -intProgression.c());
    }

    @NotNull
    public static final LongProgression a(@NotNull LongProgression longProgression) {
        Intrinsics.f(longProgression, "receiver$0");
        return LongProgression.f2850a.a(longProgression.b(), longProgression.a(), -longProgression.c());
    }

    @NotNull
    public static final CharProgression a(@NotNull CharProgression charProgression) {
        Intrinsics.f(charProgression, "receiver$0");
        return CharProgression.f2843a.a(charProgression.b(), charProgression.a(), -charProgression.c());
    }

    @NotNull
    public static final IntProgression a(@NotNull IntProgression intProgression, int i) {
        Intrinsics.f(intProgression, "receiver$0");
        RangesKt.a(i > 0, (Number) Integer.valueOf(i));
        IntProgression.Companion companion = IntProgression.f2848a;
        int a2 = intProgression.a();
        int b = intProgression.b();
        if (intProgression.c() <= 0) {
            i = -i;
        }
        return companion.a(a2, b, i);
    }

    @NotNull
    public static final LongProgression a(@NotNull LongProgression longProgression, long j) {
        Intrinsics.f(longProgression, "receiver$0");
        RangesKt.a(j > 0, (Number) Long.valueOf(j));
        LongProgression.Companion companion = LongProgression.f2850a;
        long a2 = longProgression.a();
        long b = longProgression.b();
        if (longProgression.c() <= 0) {
            j = -j;
        }
        return companion.a(a2, b, j);
    }

    @NotNull
    public static final CharProgression a(@NotNull CharProgression charProgression, int i) {
        Intrinsics.f(charProgression, "receiver$0");
        RangesKt.a(i > 0, (Number) Integer.valueOf(i));
        CharProgression.Companion companion = CharProgression.f2843a;
        char a2 = charProgression.a();
        char b = charProgression.b();
        if (charProgression.c() <= 0) {
            i = -i;
        }
        return companion.a(a2, b, i);
    }

    @Nullable
    public static final Byte a(int i) {
        if (-128 <= i && 127 >= i) {
            return Byte.valueOf((byte) i);
        }
        return null;
    }

    @Nullable
    public static final Byte a(long j) {
        long j2 = (long) 127;
        if (((long) -128) <= j && j2 >= j) {
            return Byte.valueOf((byte) ((int) j));
        }
        return null;
    }

    @Nullable
    public static final Byte a(short s) {
        short s2 = (short) 127;
        if (((short) -128) <= s && s2 >= s) {
            return Byte.valueOf((byte) s);
        }
        return null;
    }

    @Nullable
    public static final Byte a(double d) {
        double d2 = (double) 127;
        if (d < ((double) -128) || d > d2) {
            return null;
        }
        return Byte.valueOf((byte) ((int) d));
    }

    @Nullable
    public static final Byte a(float f) {
        float f2 = (float) 127;
        if (f < ((float) -128) || f > f2) {
            return null;
        }
        return Byte.valueOf((byte) ((int) f));
    }

    @Nullable
    public static final Integer b(long j) {
        long j2 = (long) Integer.MAX_VALUE;
        if (((long) Integer.MIN_VALUE) <= j && j2 >= j) {
            return Integer.valueOf((int) j);
        }
        return null;
    }

    @Nullable
    public static final Integer b(double d) {
        double d2 = (double) Integer.MAX_VALUE;
        if (d < ((double) Integer.MIN_VALUE) || d > d2) {
            return null;
        }
        return Integer.valueOf((int) d);
    }

    @Nullable
    public static final Integer b(float f) {
        float f2 = (float) Integer.MAX_VALUE;
        if (f < ((float) Integer.MIN_VALUE) || f > f2) {
            return null;
        }
        return Integer.valueOf((int) f);
    }

    @Nullable
    public static final Long c(double d) {
        double d2 = (double) Long.MAX_VALUE;
        if (d < ((double) Long.MIN_VALUE) || d > d2) {
            return null;
        }
        return Long.valueOf((long) d);
    }

    @Nullable
    public static final Long c(float f) {
        float f2 = (float) Long.MAX_VALUE;
        if (f < ((float) Long.MIN_VALUE) || f > f2) {
            return null;
        }
        return Long.valueOf((long) f);
    }

    @Nullable
    public static final Short b(int i) {
        if (-32768 <= i && 32767 >= i) {
            return Short.valueOf((short) i);
        }
        return null;
    }

    @Nullable
    public static final Short c(long j) {
        long j2 = (long) DNSRecordClass.CLASS_MASK;
        if (((long) -32768) <= j && j2 >= j) {
            return Short.valueOf((short) ((int) j));
        }
        return null;
    }

    @Nullable
    public static final Short d(double d) {
        double d2 = (double) DNSRecordClass.CLASS_MASK;
        if (d < ((double) -32768) || d > d2) {
            return null;
        }
        return Short.valueOf((short) ((int) d));
    }

    @Nullable
    public static final Short d(float f) {
        float f2 = (float) DNSRecordClass.CLASS_MASK;
        if (f < ((float) -32768) || f > f2) {
            return null;
        }
        return Short.valueOf((short) ((int) f));
    }

    @NotNull
    public static final IntRange b(int i, byte b) {
        return new IntRange(i, b - 1);
    }

    @NotNull
    public static final LongRange b(long j, byte b) {
        return new LongRange(j, ((long) b) - 1);
    }

    @NotNull
    public static final IntRange b(byte b, byte b2) {
        return new IntRange(b, b2 - 1);
    }

    @NotNull
    public static final IntRange b(short s, byte b) {
        return new IntRange(s, b - 1);
    }

    @NotNull
    public static final CharRange b(char c, char c2) {
        if (c2 <= 0) {
            return CharRange.b.a();
        }
        return new CharRange(c, (char) (c2 - 1));
    }

    @NotNull
    public static final IntRange b(int i, int i2) {
        if (i2 <= Integer.MIN_VALUE) {
            return IntRange.b.a();
        }
        return new IntRange(i, i2 - 1);
    }

    @NotNull
    public static final LongRange b(long j, int i) {
        return new LongRange(j, ((long) i) - 1);
    }

    @NotNull
    public static final IntRange b(byte b, int i) {
        if (i <= Integer.MIN_VALUE) {
            return IntRange.b.a();
        }
        return new IntRange(b, i - 1);
    }

    @NotNull
    public static final IntRange b(short s, int i) {
        if (i <= Integer.MIN_VALUE) {
            return IntRange.b.a();
        }
        return new IntRange(s, i - 1);
    }

    @NotNull
    public static final LongRange b(int i, long j) {
        if (j <= Long.MIN_VALUE) {
            return LongRange.b.a();
        }
        return new LongRange((long) i, j - 1);
    }

    @NotNull
    public static final LongRange b(long j, long j2) {
        if (j2 <= Long.MIN_VALUE) {
            return LongRange.b.a();
        }
        return new LongRange(j, j2 - 1);
    }

    @NotNull
    public static final LongRange b(byte b, long j) {
        if (j <= Long.MIN_VALUE) {
            return LongRange.b.a();
        }
        return new LongRange((long) b, j - 1);
    }

    @NotNull
    public static final LongRange b(short s, long j) {
        if (j <= Long.MIN_VALUE) {
            return LongRange.b.a();
        }
        return new LongRange((long) s, j - 1);
    }

    @NotNull
    public static final IntRange b(int i, short s) {
        return new IntRange(i, s - 1);
    }

    @NotNull
    public static final LongRange b(long j, short s) {
        return new LongRange(j, ((long) s) - 1);
    }

    @NotNull
    public static final IntRange b(byte b, short s) {
        return new IntRange(b, s - 1);
    }

    @NotNull
    public static final IntRange b(short s, short s2) {
        return new IntRange(s, s2 - 1);
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T b(@NotNull T t, @NotNull T t2) {
        Intrinsics.f(t, "receiver$0");
        Intrinsics.f(t2, "minimumValue");
        return t.compareTo(t2) < 0 ? t2 : t;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T c(@NotNull T t, @NotNull T t2) {
        Intrinsics.f(t, "receiver$0");
        Intrinsics.f(t2, "maximumValue");
        return t.compareTo(t2) > 0 ? t2 : t;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T a(@NotNull T t, @Nullable T t2, @Nullable T t3) {
        Intrinsics.f(t, "receiver$0");
        if (t2 == null || t3 == null) {
            if (t2 == null || t.compareTo(t2) >= 0) {
                return (t3 == null || t.compareTo(t3) <= 0) ? t : t3;
            }
            return t2;
        } else if (t2.compareTo(t3) > 0) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + t3 + " is less than minimum " + t2 + '.');
        } else if (t.compareTo(t2) < 0) {
            return t2;
        } else {
            if (t.compareTo(t3) > 0) {
                return t3;
            }
        }
    }

    public static final byte a(byte b, byte b2, byte b3) {
        if (b2 > b3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + b3 + " is less than minimum " + b2 + '.');
        } else if (b < b2) {
            return b2;
        } else {
            return b > b3 ? b3 : b;
        }
    }

    public static final short a(short s, short s2, short s3) {
        if (s2 > s3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + s3 + " is less than minimum " + s2 + '.');
        } else if (s < s2) {
            return s2;
        } else {
            return s > s3 ? s3 : s;
        }
    }

    public static final int a(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + '.');
        } else if (i < i2) {
            return i2;
        } else {
            return i > i3 ? i3 : i;
        }
    }

    public static final long a(long j, long j2, long j3) {
        if (j2 > j3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j3 + " is less than minimum " + j2 + '.');
        } else if (j < j2) {
            return j2;
        } else {
            return j > j3 ? j3 : j;
        }
    }

    public static final float a(float f, float f2, float f3) {
        if (f2 > f3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f3 + " is less than minimum " + f2 + '.');
        } else if (f < f2) {
            return f2;
        } else {
            return f > f3 ? f3 : f;
        }
    }

    public static final double a(double d, double d2, double d3) {
        if (d2 > d3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d3 + " is less than minimum " + d2 + '.');
        } else if (d < d2) {
            return d2;
        } else {
            return d > d3 ? d3 : d;
        }
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T extends Comparable<? super T>> T a(@NotNull T t, @NotNull ClosedFloatingPointRange<T> closedFloatingPointRange) {
        Intrinsics.f(t, "receiver$0");
        Intrinsics.f(closedFloatingPointRange, Common.v);
        if (closedFloatingPointRange.e()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedFloatingPointRange + '.');
        } else if (!closedFloatingPointRange.a(t, closedFloatingPointRange.g()) || closedFloatingPointRange.a(closedFloatingPointRange.g(), t)) {
            return (!closedFloatingPointRange.a(closedFloatingPointRange.i(), t) || closedFloatingPointRange.a(t, closedFloatingPointRange.i())) ? t : closedFloatingPointRange.i();
        } else {
            return closedFloatingPointRange.g();
        }
    }

    @NotNull
    public static final <T extends Comparable<? super T>> T a(@NotNull T t, @NotNull ClosedRange<T> closedRange) {
        Intrinsics.f(t, "receiver$0");
        Intrinsics.f(closedRange, Common.v);
        if (closedRange instanceof ClosedFloatingPointRange) {
            return RangesKt.a(t, (ClosedFloatingPointRange) closedRange);
        }
        if (closedRange.e()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        } else if (t.compareTo(closedRange.g()) < 0) {
            return closedRange.g();
        } else {
            return t.compareTo(closedRange.i()) > 0 ? closedRange.i() : t;
        }
    }

    public static final int a(int i, @NotNull ClosedRange<Integer> closedRange) {
        Intrinsics.f(closedRange, Common.v);
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((Number) RangesKt.a(Integer.valueOf(i), (ClosedFloatingPointRange) closedRange)).intValue();
        }
        if (closedRange.e()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        } else if (i < closedRange.g().intValue()) {
            return closedRange.g().intValue();
        } else {
            return i > closedRange.i().intValue() ? closedRange.i().intValue() : i;
        }
    }

    public static final long a(long j, @NotNull ClosedRange<Long> closedRange) {
        Intrinsics.f(closedRange, Common.v);
        if (closedRange instanceof ClosedFloatingPointRange) {
            return ((Number) RangesKt.a(Long.valueOf(j), (ClosedFloatingPointRange) closedRange)).longValue();
        }
        if (closedRange.e()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + closedRange + '.');
        } else if (j < closedRange.g().longValue()) {
            return closedRange.g().longValue();
        } else {
            return j > closedRange.i().longValue() ? closedRange.i().longValue() : j;
        }
    }
}
