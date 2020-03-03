package kotlin.collections;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0006\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\b\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\n\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\f\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\r0\u0001*\u00020\u000e\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u00020\u0010\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u00020\u0012\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u00020\u0014\u001aU\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001c\u001a9\u0010\u0015\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001d\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a2\u0010\u001e\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\f¢\u0006\u0004\b \u0010!\u001a\"\u0010\"\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0004\b#\u0010$\u001a\"\u0010%\u001a\u00020&\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0004\b'\u0010(\u001a0\u0010)\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\f¢\u0006\u0002\u0010!\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\f\u001a\u0015\u0010)\u001a\u00020\u0005*\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\f\u001a \u0010*\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u0010$\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0006H\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\bH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\nH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\fH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u000eH\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0010H\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0012H\b\u001a\r\u0010*\u001a\u00020\u000f*\u00020\u0014H\b\u001a \u0010+\u001a\u00020&\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u0010(\u001a\r\u0010+\u001a\u00020&*\u00020\u0006H\b\u001a\r\u0010+\u001a\u00020&*\u00020\bH\b\u001a\r\u0010+\u001a\u00020&*\u00020\nH\b\u001a\r\u0010+\u001a\u00020&*\u00020\fH\b\u001a\r\u0010+\u001a\u00020&*\u00020\u000eH\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0010H\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0012H\b\u001a\r\u0010+\u001a\u00020&*\u00020\u0014H\b\u001aQ\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00101\u001a2\u0010,\u001a\u00020\u0006*\u00020\u00062\u0006\u0010-\u001a\u00020\u00062\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\b*\u00020\b2\u0006\u0010-\u001a\u00020\b2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\n*\u00020\n2\u0006\u0010-\u001a\u00020\n2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\f*\u00020\f2\u0006\u0010-\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0010*\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0012*\u00020\u00122\u0006\u0010-\u001a\u00020\u00122\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a2\u0010,\u001a\u00020\u0014*\u00020\u00142\u0006\u0010-\u001a\u00020\u00142\b\b\u0002\u0010.\u001a\u00020\u000f2\b\b\u0002\u0010/\u001a\u00020\u000f2\b\b\u0002\u00100\u001a\u00020\u000fH\u0007\u001a$\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u00103\u001a.\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u00104\u001a\u00020\u000fH\b¢\u0006\u0002\u00105\u001a\r\u00102\u001a\u00020\u0006*\u00020\u0006H\b\u001a\u0015\u00102\u001a\u00020\u0006*\u00020\u00062\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\b*\u00020\bH\b\u001a\u0015\u00102\u001a\u00020\b*\u00020\b2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\n*\u00020\nH\b\u001a\u0015\u00102\u001a\u00020\n*\u00020\n2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\f*\u00020\fH\b\u001a\u0015\u00102\u001a\u00020\f*\u00020\f2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u000e*\u00020\u000eH\b\u001a\u0015\u00102\u001a\u00020\u000e*\u00020\u000e2\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u0010*\u00020\u0010H\b\u001a\u0015\u00102\u001a\u00020\u0010*\u00020\u00102\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u0012*\u00020\u0012H\b\u001a\u0015\u00102\u001a\u00020\u0012*\u00020\u00122\u0006\u00104\u001a\u00020\u000fH\b\u001a\r\u00102\u001a\u00020\u0014*\u00020\u0014H\b\u001a\u0015\u00102\u001a\u00020\u0014*\u00020\u00142\u0006\u00104\u001a\u00020\u000fH\b\u001a6\u00106\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0004\b7\u00108\u001a\"\u00106\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a\"\u00106\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\b¢\u0006\u0002\b7\u001a5\u00109\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0004\b6\u00108\u001a!\u00109\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a!\u00109\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b6\u001a7\u0010:\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010<\u001a&\u0010:\u001a\u00020;*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010:\u001a\u00020;*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a-\u0010=\u001a\b\u0012\u0004\u0012\u0002H>0\u0001\"\u0004\b\u0000\u0010>*\u0006\u0012\u0002\b\u00030\u00032\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H>0@¢\u0006\u0002\u0010A\u001aA\u0010B\u001a\u0002HC\"\u0010\b\u0000\u0010C*\n\u0012\u0006\b\u0000\u0012\u0002H>0D\"\u0004\b\u0001\u0010>*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010-\u001a\u0002HC2\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H>0@¢\u0006\u0002\u0010E\u001a,\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0002¢\u0006\u0002\u0010G\u001a4\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0010H\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0002¢\u0006\u0002\u0010I\u001a2\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u00020JH\u0002¢\u0006\u0002\u0010K\u001a\u0015\u0010F\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005H\u0002\u001a\u0015\u0010F\u001a\u00020\u0006*\u00020\u00062\u0006\u0010H\u001a\u00020\u0006H\u0002\u001a\u001b\u0010F\u001a\u00020\u0006*\u00020\u00062\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00050JH\u0002\u001a\u0015\u0010F\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0007H\u0002\u001a\u0015\u0010F\u001a\u00020\b*\u00020\b2\u0006\u0010H\u001a\u00020\bH\u0002\u001a\u001b\u0010F\u001a\u00020\b*\u00020\b2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00070JH\u0002\u001a\u0015\u0010F\u001a\u00020\n*\u00020\n2\u0006\u0010\u0016\u001a\u00020\tH\u0002\u001a\u0015\u0010F\u001a\u00020\n*\u00020\n2\u0006\u0010H\u001a\u00020\nH\u0002\u001a\u001b\u0010F\u001a\u00020\n*\u00020\n2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\t0JH\u0002\u001a\u0015\u0010F\u001a\u00020\f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002\u001a\u0015\u0010F\u001a\u00020\f*\u00020\f2\u0006\u0010H\u001a\u00020\fH\u0002\u001a\u001b\u0010F\u001a\u00020\f*\u00020\f2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u000b0JH\u0002\u001a\u0015\u0010F\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0002\u001a\u0015\u0010F\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010H\u001a\u00020\u000eH\u0002\u001a\u001b\u0010F\u001a\u00020\u000e*\u00020\u000e2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\r0JH\u0002\u001a\u0015\u0010F\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000fH\u0002\u001a\u0015\u0010F\u001a\u00020\u0010*\u00020\u00102\u0006\u0010H\u001a\u00020\u0010H\u0002\u001a\u001b\u0010F\u001a\u00020\u0010*\u00020\u00102\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u000f0JH\u0002\u001a\u0015\u0010F\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0011H\u0002\u001a\u0015\u0010F\u001a\u00020\u0012*\u00020\u00122\u0006\u0010H\u001a\u00020\u0012H\u0002\u001a\u001b\u0010F\u001a\u00020\u0012*\u00020\u00122\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00110JH\u0002\u001a\u0015\u0010F\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013H\u0002\u001a\u0015\u0010F\u001a\u00020\u0014*\u00020\u00142\u0006\u0010H\u001a\u00020\u0014H\u0002\u001a\u001b\u0010F\u001a\u00020\u0014*\u00020\u00142\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00130JH\u0002\u001a,\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010G\u001a\u001d\u0010M\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010N\u001a*\u0010M\u001a\u00020;\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020O*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\b¢\u0006\u0002\u0010P\u001a1\u0010M\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010Q\u001a\n\u0010M\u001a\u00020;*\u00020\b\u001a\u001e\u0010M\u001a\u00020;*\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\n\u001a\u001e\u0010M\u001a\u00020;*\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\f\u001a\u001e\u0010M\u001a\u00020;*\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u000e\u001a\u001e\u0010M\u001a\u00020;*\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u0010\u001a\u001e\u0010M\u001a\u00020;*\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u0012\u001a\u001e\u0010M\u001a\u00020;*\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010M\u001a\u00020;*\u00020\u0014\u001a\u001e\u0010M\u001a\u00020;*\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a9\u0010R\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010S\u001aM\u0010R\u001a\u00020;\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010T\u001a-\u0010U\u001a\b\u0012\u0004\u0012\u0002H\u00020V\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020O*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010W\u001a?\u0010U\u001a\b\u0012\u0004\u0012\u0002H\u00020V\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010X\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00050V*\u00020\u0006\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00070V*\u00020\b\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\t0V*\u00020\n\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000b0V*\u00020\f\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\r0V*\u00020\u000e\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000f0V*\u00020\u0010\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00110V*\u00020\u0012\u001a\u0010\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00130V*\u00020\u0014\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u0006¢\u0006\u0002\u0010Z\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\u00020\b¢\u0006\u0002\u0010[\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\n¢\u0006\u0002\u0010\\\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003*\u00020\f¢\u0006\u0002\u0010]\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\r0\u0003*\u00020\u000e¢\u0006\u0002\u0010^\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003*\u00020\u0010¢\u0006\u0002\u0010_\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003*\u00020\u0012¢\u0006\u0002\u0010`\u001a\u0015\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003*\u00020\u0014¢\u0006\u0002\u0010a¨\u0006b"}, d2 = {"asList", "", "T", "", "([Ljava/lang/Object;)Ljava/util/List;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "binarySearch", "element", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "fromIndex", "toIndex", "([Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;II)I", "([Ljava/lang/Object;Ljava/lang/Object;II)I", "contentDeepEquals", "other", "contentDeepEqualsInline", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepHashCode", "contentDeepHashCodeInline", "([Ljava/lang/Object;)I", "contentDeepToString", "", "contentDeepToStringInline", "([Ljava/lang/Object;)Ljava/lang/String;", "contentEquals", "contentHashCode", "contentToString", "copyInto", "destination", "destinationOffset", "startIndex", "endIndex", "([Ljava/lang/Object;[Ljava/lang/Object;III)[Ljava/lang/Object;", "copyOf", "([Ljava/lang/Object;)[Ljava/lang/Object;", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRange", "copyOfRangeInline", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "copyOfRangeImpl", "fill", "", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "filterIsInstance", "R", "klass", "Ljava/lang/Class;", "([Ljava/lang/Object;Ljava/lang/Class;)Ljava/util/List;", "filterIsInstanceTo", "C", "", "([Ljava/lang/Object;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "plus", "([Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "elements", "([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "", "([Ljava/lang/Object;Ljava/util/Collection;)[Ljava/lang/Object;", "plusElement", "sort", "([Ljava/lang/Object;)V", "", "([Ljava/lang/Comparable;)V", "([Ljava/lang/Object;II)V", "sortWith", "([Ljava/lang/Object;Ljava/util/Comparator;)V", "([Ljava/lang/Object;Ljava/util/Comparator;II)V", "toSortedSet", "Ljava/util/SortedSet;", "([Ljava/lang/Comparable;)Ljava/util/SortedSet;", "([Ljava/lang/Object;Ljava/util/Comparator;)Ljava/util/SortedSet;", "toTypedArray", "([Z)[Ljava/lang/Boolean;", "([B)[Ljava/lang/Byte;", "([C)[Ljava/lang/Character;", "([D)[Ljava/lang/Double;", "([F)[Ljava/lang/Float;", "([I)[Ljava/lang/Integer;", "([J)[Ljava/lang/Long;", "([S)[Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/collections/ArraysKt")
class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    @NotNull
    public static final <R> List<R> a(@NotNull Object[] objArr, @NotNull Class<R> cls) {
        Intrinsics.f(objArr, "receiver$0");
        Intrinsics.f(cls, "klass");
        return (List) ArraysKt.a(objArr, new ArrayList(), cls);
    }

    @NotNull
    public static final <C extends Collection<? super R>, R> C a(@NotNull Object[] objArr, @NotNull C c, @NotNull Class<R> cls) {
        Intrinsics.f(objArr, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(cls, "klass");
        for (Object obj : objArr) {
            if (cls.isInstance(obj)) {
                c.add(obj);
            }
        }
        return c;
    }

    @NotNull
    public static final <T> List<T> c(@NotNull T[] tArr) {
        Intrinsics.f(tArr, "receiver$0");
        List<T> a2 = ArraysUtilJVM.a(tArr);
        Intrinsics.b(a2, "ArraysUtilJVM.asList(this)");
        return a2;
    }

    @NotNull
    public static final List<Byte> a(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$1(bArr);
    }

    @NotNull
    public static final List<Short> a(@NotNull short[] sArr) {
        Intrinsics.f(sArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$2(sArr);
    }

    @NotNull
    public static final List<Integer> a(@NotNull int[] iArr) {
        Intrinsics.f(iArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$3(iArr);
    }

    @NotNull
    public static final List<Long> a(@NotNull long[] jArr) {
        Intrinsics.f(jArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$4(jArr);
    }

    @NotNull
    public static final List<Float> a(@NotNull float[] fArr) {
        Intrinsics.f(fArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$5(fArr);
    }

    @NotNull
    public static final List<Double> a(@NotNull double[] dArr) {
        Intrinsics.f(dArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$6(dArr);
    }

    @NotNull
    public static final List<Boolean> a(@NotNull boolean[] zArr) {
        Intrinsics.f(zArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$7(zArr);
    }

    @NotNull
    public static final List<Character> a(@NotNull char[] cArr) {
        Intrinsics.f(cArr, "receiver$0");
        return new ArraysKt___ArraysJvmKt$asList$8(cArr);
    }

    public static /* synthetic */ int a(Object[] objArr, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        return ArraysKt.a((T[]) objArr, obj, comparator, i, i2);
    }

    public static final <T> int a(@NotNull T[] tArr, T t, @NotNull Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(comparator, "comparator");
        return Arrays.binarySearch(tArr, i, i2, t, comparator);
    }

    public static /* synthetic */ int a(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        return ArraysKt.a((T[]) objArr, obj, i, i2);
    }

    public static final <T> int a(@NotNull T[] tArr, T t, int i, int i2) {
        Intrinsics.f(tArr, "receiver$0");
        return Arrays.binarySearch(tArr, i, i2, t);
    }

    public static /* synthetic */ int a(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ArraysKt.a(bArr, b, i, i2);
    }

    public static final int a(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.f(bArr, "receiver$0");
        return Arrays.binarySearch(bArr, i, i2, b);
    }

    public static /* synthetic */ int a(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        return ArraysKt.a(sArr, s, i, i2);
    }

    public static final int a(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.f(sArr, "receiver$0");
        return Arrays.binarySearch(sArr, i, i2, s);
    }

    public static /* synthetic */ int a(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        return ArraysKt.a(iArr, i, i2, i3);
    }

    public static final int a(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.f(iArr, "receiver$0");
        return Arrays.binarySearch(iArr, i2, i3, i);
    }

    public static /* synthetic */ int a(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        return ArraysKt.a(jArr, j, i, i2);
    }

    public static final int a(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.f(jArr, "receiver$0");
        return Arrays.binarySearch(jArr, i, i2, j);
    }

    public static /* synthetic */ int a(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return ArraysKt.a(fArr, f, i, i2);
    }

    public static final int a(@NotNull float[] fArr, float f, int i, int i2) {
        Intrinsics.f(fArr, "receiver$0");
        return Arrays.binarySearch(fArr, i, i2, f);
    }

    public static /* synthetic */ int a(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        return ArraysKt.a(dArr, d, i, i2);
    }

    public static final int a(@NotNull double[] dArr, double d, int i, int i2) {
        Intrinsics.f(dArr, "receiver$0");
        return Arrays.binarySearch(dArr, i, i2, d);
    }

    public static /* synthetic */ int a(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return ArraysKt.a(cArr, c, i, i2);
    }

    public static final int a(@NotNull char[] cArr, char c, int i, int i2) {
        Intrinsics.f(cArr, "receiver$0");
        return Arrays.binarySearch(cArr, i, i2, c);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepEqualsInline")
    private static final <T> boolean c(@NotNull T[] tArr, T[] tArr2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(tArr, tArr2);
        }
        return Arrays.deepEquals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepHashCodeInline")
    private static final <T> int e(@NotNull T[] tArr) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(tArr);
        }
        return Arrays.deepHashCode(tArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @JvmName(name = "contentDeepToStringInline")
    private static final <T> String f(@NotNull T[] tArr) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.b(tArr);
        }
        String deepToString = Arrays.deepToString(tArr);
        Intrinsics.b(deepToString, "java.util.Arrays.deepToString(this)");
        return deepToString;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> boolean d(@NotNull T[] tArr, T[] tArr2) {
        return Arrays.equals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull short[] sArr, short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull long[] jArr, long[] jArr2) {
        return Arrays.equals(jArr, jArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull float[] fArr, float[] fArr2) {
        return Arrays.equals(fArr, fArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull double[] dArr, double[] dArr2) {
        return Arrays.equals(dArr, dArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull boolean[] zArr, boolean[] zArr2) {
        return Arrays.equals(zArr, zArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final boolean b(@NotNull char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> int g(@NotNull T[] tArr) {
        return Arrays.hashCode(tArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull float[] fArr) {
        return Arrays.hashCode(fArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull double[] dArr) {
        return Arrays.hashCode(dArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int d(@NotNull boolean[] zArr) {
        return Arrays.hashCode(zArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int e(@NotNull char[] cArr) {
        return Arrays.hashCode(cArr);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> String h(@NotNull T[] tArr) {
        String arrays = Arrays.toString(tArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull byte[] bArr) {
        String arrays = Arrays.toString(bArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull short[] sArr) {
        String arrays = Arrays.toString(sArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull int[] iArr) {
        String arrays = Arrays.toString(iArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull long[] jArr) {
        String arrays = Arrays.toString(jArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull float[] fArr) {
        String arrays = Arrays.toString(fArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull double[] dArr) {
        String arrays = Arrays.toString(dArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String e(@NotNull boolean[] zArr) {
        String arrays = Arrays.toString(zArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String f(@NotNull char[] cArr) {
        String arrays = Arrays.toString(cArr);
        Intrinsics.b(arrays, "java.util.Arrays.toString(this)");
        return arrays;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ Object[] a(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        return ArraysKt.a((T[]) objArr, (T[]) objArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <T> T[] a(@NotNull T[] tArr, @NotNull T[] tArr2, int i, int i2, int i3) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(tArr2, "destination");
        System.arraycopy(tArr, i2, tArr2, i, i3 - i2);
        return tArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ byte[] a(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        return ArraysKt.a(bArr, bArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final byte[] a(@NotNull byte[] bArr, @NotNull byte[] bArr2, int i, int i2, int i3) {
        Intrinsics.f(bArr, "receiver$0");
        Intrinsics.f(bArr2, "destination");
        System.arraycopy(bArr, i2, bArr2, i, i3 - i2);
        return bArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ short[] a(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length;
        }
        return ArraysKt.a(sArr, sArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final short[] a(@NotNull short[] sArr, @NotNull short[] sArr2, int i, int i2, int i3) {
        Intrinsics.f(sArr, "receiver$0");
        Intrinsics.f(sArr2, "destination");
        System.arraycopy(sArr, i2, sArr2, i, i3 - i2);
        return sArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ int[] a(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length;
        }
        return ArraysKt.a(iArr, iArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final int[] a(@NotNull int[] iArr, @NotNull int[] iArr2, int i, int i2, int i3) {
        Intrinsics.f(iArr, "receiver$0");
        Intrinsics.f(iArr2, "destination");
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
        return iArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ long[] a(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length;
        }
        return ArraysKt.a(jArr, jArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final long[] a(@NotNull long[] jArr, @NotNull long[] jArr2, int i, int i2, int i3) {
        Intrinsics.f(jArr, "receiver$0");
        Intrinsics.f(jArr2, "destination");
        System.arraycopy(jArr, i2, jArr2, i, i3 - i2);
        return jArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ float[] a(float[] fArr, float[] fArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length;
        }
        return ArraysKt.a(fArr, fArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final float[] a(@NotNull float[] fArr, @NotNull float[] fArr2, int i, int i2, int i3) {
        Intrinsics.f(fArr, "receiver$0");
        Intrinsics.f(fArr2, "destination");
        System.arraycopy(fArr, i2, fArr2, i, i3 - i2);
        return fArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ double[] a(double[] dArr, double[] dArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length;
        }
        return ArraysKt.a(dArr, dArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final double[] a(@NotNull double[] dArr, @NotNull double[] dArr2, int i, int i2, int i3) {
        Intrinsics.f(dArr, "receiver$0");
        Intrinsics.f(dArr2, "destination");
        System.arraycopy(dArr, i2, dArr2, i, i3 - i2);
        return dArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ boolean[] a(boolean[] zArr, boolean[] zArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = zArr.length;
        }
        return ArraysKt.a(zArr, zArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final boolean[] a(@NotNull boolean[] zArr, @NotNull boolean[] zArr2, int i, int i2, int i3) {
        Intrinsics.f(zArr, "receiver$0");
        Intrinsics.f(zArr2, "destination");
        System.arraycopy(zArr, i2, zArr2, i, i3 - i2);
        return zArr2;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static /* synthetic */ char[] a(char[] cArr, char[] cArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = cArr.length;
        }
        return ArraysKt.a(cArr, cArr2, i, i2, i3);
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final char[] a(@NotNull char[] cArr, @NotNull char[] cArr2, int i, int i2, int i3) {
        Intrinsics.f(cArr, "receiver$0");
        Intrinsics.f(cArr2, "destination");
        System.arraycopy(cArr, i2, cArr2, i, i3 - i2);
        return cArr2;
    }

    @InlineOnly
    private static final <T> T[] i(@NotNull T[] tArr) {
        T[] copyOf = Arrays.copyOf(tArr, tArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final byte[] g(@NotNull byte[] bArr) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final short[] g(@NotNull short[] sArr) {
        short[] copyOf = Arrays.copyOf(sArr, sArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final int[] g(@NotNull int[] iArr) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final long[] g(@NotNull long[] jArr) {
        long[] copyOf = Arrays.copyOf(jArr, jArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final float[] g(@NotNull float[] fArr) {
        float[] copyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final double[] g(@NotNull double[] dArr) {
        double[] copyOf = Arrays.copyOf(dArr, dArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final boolean[] f(@NotNull boolean[] zArr) {
        boolean[] copyOf = Arrays.copyOf(zArr, zArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final char[] g(@NotNull char[] cArr) {
        char[] copyOf = Arrays.copyOf(cArr, cArr.length);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    @InlineOnly
    private static final byte[] a(@NotNull byte[] bArr, int i) {
        byte[] copyOf = Arrays.copyOf(bArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final short[] a(@NotNull short[] sArr, int i) {
        short[] copyOf = Arrays.copyOf(sArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final int[] b(@NotNull int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final long[] a(@NotNull long[] jArr, int i) {
        long[] copyOf = Arrays.copyOf(jArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final float[] a(@NotNull float[] fArr, int i) {
        float[] copyOf = Arrays.copyOf(fArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final double[] a(@NotNull double[] dArr, int i) {
        double[] copyOf = Arrays.copyOf(dArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final boolean[] a(@NotNull boolean[] zArr, int i) {
        boolean[] copyOf = Arrays.copyOf(zArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final char[] a(@NotNull char[] cArr, int i) {
        char[] copyOf = Arrays.copyOf(cArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    private static final <T> T[] b(@NotNull T[] tArr, int i) {
        T[] copyOf = Arrays.copyOf(tArr, i);
        Intrinsics.b(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final <T> T[] c(@NotNull T[] tArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(tArr, i, i2);
        }
        if (i2 <= tArr.length) {
            T[] copyOfRange = Arrays.copyOfRange(tArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + tArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final byte[] c(@NotNull byte[] bArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(bArr, i, i2);
        }
        if (i2 <= bArr.length) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + bArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final short[] c(@NotNull short[] sArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(sArr, i, i2);
        }
        if (i2 <= sArr.length) {
            short[] copyOfRange = Arrays.copyOfRange(sArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + sArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final int[] c(@NotNull int[] iArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(iArr, i, i2);
        }
        if (i2 <= iArr.length) {
            int[] copyOfRange = Arrays.copyOfRange(iArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + iArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final long[] c(@NotNull long[] jArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(jArr, i, i2);
        }
        if (i2 <= jArr.length) {
            long[] copyOfRange = Arrays.copyOfRange(jArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + jArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final float[] c(@NotNull float[] fArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(fArr, i, i2);
        }
        if (i2 <= fArr.length) {
            float[] copyOfRange = Arrays.copyOfRange(fArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + fArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final double[] c(@NotNull double[] dArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(dArr, i, i2);
        }
        if (i2 <= dArr.length) {
            double[] copyOfRange = Arrays.copyOfRange(dArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + dArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final boolean[] b(@NotNull boolean[] zArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(zArr, i, i2);
        }
        if (i2 <= zArr.length) {
            boolean[] copyOfRange = Arrays.copyOfRange(zArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + zArr.length);
    }

    @InlineOnly
    @JvmName(name = "copyOfRangeInline")
    private static final char[] c(@NotNull char[] cArr, int i, int i2) {
        if (PlatformImplementationsKt.a(1, 3, 0)) {
            return ArraysKt.a(cArr, i, i2);
        }
        if (i2 <= cArr.length) {
            char[] copyOfRange = Arrays.copyOfRange(cArr, i, i2);
            Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
            return copyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + cArr.length);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final <T> T[] a(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.f(tArr, "receiver$0");
        ArraysKt.a(i2, tArr.length);
        T[] copyOfRange = Arrays.copyOfRange(tArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final byte[] a(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.f(bArr, "receiver$0");
        ArraysKt.a(i2, bArr.length);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final short[] a(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.f(sArr, "receiver$0");
        ArraysKt.a(i2, sArr.length);
        short[] copyOfRange = Arrays.copyOfRange(sArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final int[] a(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.f(iArr, "receiver$0");
        ArraysKt.a(i2, iArr.length);
        int[] copyOfRange = Arrays.copyOfRange(iArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final long[] a(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.f(jArr, "receiver$0");
        ArraysKt.a(i2, jArr.length);
        long[] copyOfRange = Arrays.copyOfRange(jArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final float[] a(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.f(fArr, "receiver$0");
        ArraysKt.a(i2, fArr.length);
        float[] copyOfRange = Arrays.copyOfRange(fArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final double[] a(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.f(dArr, "receiver$0");
        ArraysKt.a(i2, dArr.length);
        double[] copyOfRange = Arrays.copyOfRange(dArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final boolean[] a(@NotNull boolean[] zArr, int i, int i2) {
        Intrinsics.f(zArr, "receiver$0");
        ArraysKt.a(i2, zArr.length);
        boolean[] copyOfRange = Arrays.copyOfRange(zArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    @JvmName(name = "copyOfRange")
    public static final char[] a(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.f(cArr, "receiver$0");
        ArraysKt.a(i2, cArr.length);
        char[] copyOfRange = Arrays.copyOfRange(cArr, i, i2);
        Intrinsics.b(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }

    public static /* synthetic */ void b(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.b((T[]) objArr, obj, i, i2);
    }

    public static final <T> void b(@NotNull T[] tArr, T t, int i, int i2) {
        Intrinsics.f(tArr, "receiver$0");
        Arrays.fill(tArr, i, i2, t);
    }

    public static /* synthetic */ void b(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        ArraysKt.b(bArr, b, i, i2);
    }

    public static final void b(@NotNull byte[] bArr, byte b, int i, int i2) {
        Intrinsics.f(bArr, "receiver$0");
        Arrays.fill(bArr, i, i2, b);
    }

    public static /* synthetic */ void b(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        ArraysKt.b(sArr, s, i, i2);
    }

    public static final void b(@NotNull short[] sArr, short s, int i, int i2) {
        Intrinsics.f(sArr, "receiver$0");
        Arrays.fill(sArr, i, i2, s);
    }

    public static /* synthetic */ void b(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        ArraysKt.b(iArr, i, i2, i3);
    }

    public static final void b(@NotNull int[] iArr, int i, int i2, int i3) {
        Intrinsics.f(iArr, "receiver$0");
        Arrays.fill(iArr, i2, i3, i);
    }

    public static /* synthetic */ void b(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        ArraysKt.b(jArr, j, i, i2);
    }

    public static final void b(@NotNull long[] jArr, long j, int i, int i2) {
        Intrinsics.f(jArr, "receiver$0");
        Arrays.fill(jArr, i, i2, j);
    }

    public static /* synthetic */ void b(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        ArraysKt.b(fArr, f, i, i2);
    }

    public static final void b(@NotNull float[] fArr, float f, int i, int i2) {
        Intrinsics.f(fArr, "receiver$0");
        Arrays.fill(fArr, i, i2, f);
    }

    public static /* synthetic */ void b(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        ArraysKt.b(dArr, d, i, i2);
    }

    public static final void b(@NotNull double[] dArr, double d, int i, int i2) {
        Intrinsics.f(dArr, "receiver$0");
        Arrays.fill(dArr, i, i2, d);
    }

    public static /* synthetic */ void a(boolean[] zArr, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = zArr.length;
        }
        ArraysKt.a(zArr, z, i, i2);
    }

    public static final void a(@NotNull boolean[] zArr, boolean z, int i, int i2) {
        Intrinsics.f(zArr, "receiver$0");
        Arrays.fill(zArr, i, i2, z);
    }

    public static /* synthetic */ void b(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        ArraysKt.b(cArr, c, i, i2);
    }

    public static final void b(@NotNull char[] cArr, char c, int i, int i2) {
        Intrinsics.f(cArr, "receiver$0");
        Arrays.fill(cArr, i, i2, c);
    }

    @NotNull
    public static final <T> T[] a(@NotNull T[] tArr, T t) {
        Intrinsics.f(tArr, "receiver$0");
        int length = tArr.length;
        T[] copyOf = Arrays.copyOf(tArr, length + 1);
        copyOf[length] = t;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final byte[] a(@NotNull byte[] bArr, byte b) {
        Intrinsics.f(bArr, "receiver$0");
        int length = bArr.length;
        byte[] copyOf = Arrays.copyOf(bArr, length + 1);
        copyOf[length] = b;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final short[] a(@NotNull short[] sArr, short s) {
        Intrinsics.f(sArr, "receiver$0");
        int length = sArr.length;
        short[] copyOf = Arrays.copyOf(sArr, length + 1);
        copyOf[length] = s;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final int[] a(@NotNull int[] iArr, int i) {
        Intrinsics.f(iArr, "receiver$0");
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, length + 1);
        copyOf[length] = i;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final long[] a(@NotNull long[] jArr, long j) {
        Intrinsics.f(jArr, "receiver$0");
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, length + 1);
        copyOf[length] = j;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final float[] a(@NotNull float[] fArr, float f) {
        Intrinsics.f(fArr, "receiver$0");
        int length = fArr.length;
        float[] copyOf = Arrays.copyOf(fArr, length + 1);
        copyOf[length] = f;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final double[] a(@NotNull double[] dArr, double d) {
        Intrinsics.f(dArr, "receiver$0");
        int length = dArr.length;
        double[] copyOf = Arrays.copyOf(dArr, length + 1);
        copyOf[length] = d;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final boolean[] a(@NotNull boolean[] zArr, boolean z) {
        Intrinsics.f(zArr, "receiver$0");
        int length = zArr.length;
        boolean[] copyOf = Arrays.copyOf(zArr, length + 1);
        copyOf[length] = z;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final char[] a(@NotNull char[] cArr, char c) {
        Intrinsics.f(cArr, "receiver$0");
        int length = cArr.length;
        char[] copyOf = Arrays.copyOf(cArr, length + 1);
        copyOf[length] = c;
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final <T> T[] a(@NotNull T[] tArr, @NotNull Collection<? extends T> collection) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = tArr.length;
        T[] copyOf = Arrays.copyOf(tArr, collection.size() + length);
        for (T t : collection) {
            copyOf[length] = t;
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final byte[] a(@NotNull byte[] bArr, @NotNull Collection<Byte> collection) {
        Intrinsics.f(bArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = bArr.length;
        byte[] copyOf = Arrays.copyOf(bArr, collection.size() + length);
        for (Byte byteValue : collection) {
            copyOf[length] = byteValue.byteValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final short[] a(@NotNull short[] sArr, @NotNull Collection<Short> collection) {
        Intrinsics.f(sArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = sArr.length;
        short[] copyOf = Arrays.copyOf(sArr, collection.size() + length);
        for (Short shortValue : collection) {
            copyOf[length] = shortValue.shortValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final int[] a(@NotNull int[] iArr, @NotNull Collection<Integer> collection) {
        Intrinsics.f(iArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, collection.size() + length);
        for (Integer intValue : collection) {
            copyOf[length] = intValue.intValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final long[] a(@NotNull long[] jArr, @NotNull Collection<Long> collection) {
        Intrinsics.f(jArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, collection.size() + length);
        for (Long longValue : collection) {
            copyOf[length] = longValue.longValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final float[] a(@NotNull float[] fArr, @NotNull Collection<Float> collection) {
        Intrinsics.f(fArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = fArr.length;
        float[] copyOf = Arrays.copyOf(fArr, collection.size() + length);
        for (Float floatValue : collection) {
            copyOf[length] = floatValue.floatValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final double[] a(@NotNull double[] dArr, @NotNull Collection<Double> collection) {
        Intrinsics.f(dArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = dArr.length;
        double[] copyOf = Arrays.copyOf(dArr, collection.size() + length);
        for (Double doubleValue : collection) {
            copyOf[length] = doubleValue.doubleValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final boolean[] a(@NotNull boolean[] zArr, @NotNull Collection<Boolean> collection) {
        Intrinsics.f(zArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = zArr.length;
        boolean[] copyOf = Arrays.copyOf(zArr, collection.size() + length);
        for (Boolean booleanValue : collection) {
            copyOf[length] = booleanValue.booleanValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final char[] a(@NotNull char[] cArr, @NotNull Collection<Character> collection) {
        Intrinsics.f(cArr, "receiver$0");
        Intrinsics.f(collection, MessengerShareContentUtility.ELEMENTS);
        int length = cArr.length;
        char[] copyOf = Arrays.copyOf(cArr, collection.size() + length);
        for (Character charValue : collection) {
            copyOf[length] = charValue.charValue();
            length++;
        }
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final <T> T[] b(@NotNull T[] tArr, @NotNull T[] tArr2) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(tArr2, MessengerShareContentUtility.ELEMENTS);
        int length = tArr.length;
        int length2 = tArr2.length;
        T[] copyOf = Arrays.copyOf(tArr, length + length2);
        System.arraycopy(tArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final byte[] a(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.f(bArr, "receiver$0");
        Intrinsics.f(bArr2, MessengerShareContentUtility.ELEMENTS);
        int length = bArr.length;
        int length2 = bArr2.length;
        byte[] copyOf = Arrays.copyOf(bArr, length + length2);
        System.arraycopy(bArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final short[] a(@NotNull short[] sArr, @NotNull short[] sArr2) {
        Intrinsics.f(sArr, "receiver$0");
        Intrinsics.f(sArr2, MessengerShareContentUtility.ELEMENTS);
        int length = sArr.length;
        int length2 = sArr2.length;
        short[] copyOf = Arrays.copyOf(sArr, length + length2);
        System.arraycopy(sArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final int[] a(@NotNull int[] iArr, @NotNull int[] iArr2) {
        Intrinsics.f(iArr, "receiver$0");
        Intrinsics.f(iArr2, MessengerShareContentUtility.ELEMENTS);
        int length = iArr.length;
        int length2 = iArr2.length;
        int[] copyOf = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(iArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final long[] a(@NotNull long[] jArr, @NotNull long[] jArr2) {
        Intrinsics.f(jArr, "receiver$0");
        Intrinsics.f(jArr2, MessengerShareContentUtility.ELEMENTS);
        int length = jArr.length;
        int length2 = jArr2.length;
        long[] copyOf = Arrays.copyOf(jArr, length + length2);
        System.arraycopy(jArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final float[] a(@NotNull float[] fArr, @NotNull float[] fArr2) {
        Intrinsics.f(fArr, "receiver$0");
        Intrinsics.f(fArr2, MessengerShareContentUtility.ELEMENTS);
        int length = fArr.length;
        int length2 = fArr2.length;
        float[] copyOf = Arrays.copyOf(fArr, length + length2);
        System.arraycopy(fArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final double[] a(@NotNull double[] dArr, @NotNull double[] dArr2) {
        Intrinsics.f(dArr, "receiver$0");
        Intrinsics.f(dArr2, MessengerShareContentUtility.ELEMENTS);
        int length = dArr.length;
        int length2 = dArr2.length;
        double[] copyOf = Arrays.copyOf(dArr, length + length2);
        System.arraycopy(dArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final boolean[] a(@NotNull boolean[] zArr, @NotNull boolean[] zArr2) {
        Intrinsics.f(zArr, "receiver$0");
        Intrinsics.f(zArr2, MessengerShareContentUtility.ELEMENTS);
        int length = zArr.length;
        int length2 = zArr2.length;
        boolean[] copyOf = Arrays.copyOf(zArr, length + length2);
        System.arraycopy(zArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @NotNull
    public static final char[] a(@NotNull char[] cArr, @NotNull char[] cArr2) {
        Intrinsics.f(cArr, "receiver$0");
        Intrinsics.f(cArr2, MessengerShareContentUtility.ELEMENTS);
        int length = cArr.length;
        int length2 = cArr2.length;
        char[] copyOf = Arrays.copyOf(cArr, length + length2);
        System.arraycopy(cArr2, 0, copyOf, length, length2);
        Intrinsics.b(copyOf, "result");
        return copyOf;
    }

    @InlineOnly
    private static final <T> T[] b(@NotNull T[] tArr, T t) {
        return ArraysKt.a(tArr, t);
    }

    public static final void b(@NotNull int[] iArr) {
        Intrinsics.f(iArr, "receiver$0");
        if (iArr.length > 1) {
            Arrays.sort(iArr);
        }
    }

    public static final void b(@NotNull long[] jArr) {
        Intrinsics.f(jArr, "receiver$0");
        if (jArr.length > 1) {
            Arrays.sort(jArr);
        }
    }

    public static final void b(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, "receiver$0");
        if (bArr.length > 1) {
            Arrays.sort(bArr);
        }
    }

    public static final void b(@NotNull short[] sArr) {
        Intrinsics.f(sArr, "receiver$0");
        if (sArr.length > 1) {
            Arrays.sort(sArr);
        }
    }

    public static final void b(@NotNull double[] dArr) {
        Intrinsics.f(dArr, "receiver$0");
        if (dArr.length > 1) {
            Arrays.sort(dArr);
        }
    }

    public static final void b(@NotNull float[] fArr) {
        Intrinsics.f(fArr, "receiver$0");
        if (fArr.length > 1) {
            Arrays.sort(fArr);
        }
    }

    public static final void b(@NotNull char[] cArr) {
        Intrinsics.f(cArr, "receiver$0");
        if (cArr.length > 1) {
            Arrays.sort(cArr);
        }
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> void b(@NotNull T[] tArr) {
        if (tArr != null) {
            ArraysKt.d((T[]) (Object[]) tArr);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
    }

    public static final <T> void d(@NotNull T[] tArr) {
        Intrinsics.f(tArr, "receiver$0");
        if (tArr.length > 1) {
            Arrays.sort(tArr);
        }
    }

    public static /* synthetic */ void a(Object[] objArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.b((T[]) objArr, i, i2);
    }

    public static final <T> void b(@NotNull T[] tArr, int i, int i2) {
        Intrinsics.f(tArr, "receiver$0");
        Arrays.sort(tArr, i, i2);
    }

    public static /* synthetic */ void a(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        ArraysKt.b(bArr, i, i2);
    }

    public static final void b(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.f(bArr, "receiver$0");
        Arrays.sort(bArr, i, i2);
    }

    public static /* synthetic */ void a(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        ArraysKt.b(sArr, i, i2);
    }

    public static final void b(@NotNull short[] sArr, int i, int i2) {
        Intrinsics.f(sArr, "receiver$0");
        Arrays.sort(sArr, i, i2);
    }

    public static /* synthetic */ void a(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        ArraysKt.b(iArr, i, i2);
    }

    public static final void b(@NotNull int[] iArr, int i, int i2) {
        Intrinsics.f(iArr, "receiver$0");
        Arrays.sort(iArr, i, i2);
    }

    public static /* synthetic */ void a(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        ArraysKt.b(jArr, i, i2);
    }

    public static final void b(@NotNull long[] jArr, int i, int i2) {
        Intrinsics.f(jArr, "receiver$0");
        Arrays.sort(jArr, i, i2);
    }

    public static /* synthetic */ void a(float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = fArr.length;
        }
        ArraysKt.b(fArr, i, i2);
    }

    public static final void b(@NotNull float[] fArr, int i, int i2) {
        Intrinsics.f(fArr, "receiver$0");
        Arrays.sort(fArr, i, i2);
    }

    public static /* synthetic */ void a(double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = dArr.length;
        }
        ArraysKt.b(dArr, i, i2);
    }

    public static final void b(@NotNull double[] dArr, int i, int i2) {
        Intrinsics.f(dArr, "receiver$0");
        Arrays.sort(dArr, i, i2);
    }

    public static /* synthetic */ void a(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        ArraysKt.b(cArr, i, i2);
    }

    public static final void b(@NotNull char[] cArr, int i, int i2) {
        Intrinsics.f(cArr, "receiver$0");
        Arrays.sort(cArr, i, i2);
    }

    public static final <T> void a(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(comparator, "comparator");
        if (tArr.length > 1) {
            Arrays.sort(tArr, comparator);
        }
    }

    public static /* synthetic */ void a(Object[] objArr, Comparator comparator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.a((T[]) objArr, comparator, i, i2);
    }

    public static final <T> void a(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator, int i, int i2) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(comparator, "comparator");
        Arrays.sort(tArr, i, i2, comparator);
    }

    @NotNull
    public static final Byte[] c(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, "receiver$0");
        Byte[] bArr2 = new Byte[bArr.length];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    @NotNull
    public static final Short[] c(@NotNull short[] sArr) {
        Intrinsics.f(sArr, "receiver$0");
        Short[] shArr = new Short[sArr.length];
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return shArr;
    }

    @NotNull
    public static final Integer[] c(@NotNull int[] iArr) {
        Intrinsics.f(iArr, "receiver$0");
        Integer[] numArr = new Integer[iArr.length];
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    @NotNull
    public static final Long[] c(@NotNull long[] jArr) {
        Intrinsics.f(jArr, "receiver$0");
        Long[] lArr = new Long[jArr.length];
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    @NotNull
    public static final Float[] c(@NotNull float[] fArr) {
        Intrinsics.f(fArr, "receiver$0");
        Float[] fArr2 = new Float[fArr.length];
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    @NotNull
    public static final Double[] c(@NotNull double[] dArr) {
        Intrinsics.f(dArr, "receiver$0");
        Double[] dArr2 = new Double[dArr.length];
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    @NotNull
    public static final Boolean[] b(@NotNull boolean[] zArr) {
        Intrinsics.f(zArr, "receiver$0");
        Boolean[] boolArr = new Boolean[zArr.length];
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    @NotNull
    public static final Character[] c(@NotNull char[] cArr) {
        Intrinsics.f(cArr, "receiver$0");
        Character[] chArr = new Character[cArr.length];
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return chArr;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> SortedSet<T> a(@NotNull T[] tArr) {
        Intrinsics.f(tArr, "receiver$0");
        return (SortedSet) ArraysKt.d(tArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Byte> d(@NotNull byte[] bArr) {
        Intrinsics.f(bArr, "receiver$0");
        return (SortedSet) ArraysKt.c(bArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Short> d(@NotNull short[] sArr) {
        Intrinsics.f(sArr, "receiver$0");
        return (SortedSet) ArraysKt.c(sArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Integer> d(@NotNull int[] iArr) {
        Intrinsics.f(iArr, "receiver$0");
        return (SortedSet) ArraysKt.c(iArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Long> d(@NotNull long[] jArr) {
        Intrinsics.f(jArr, "receiver$0");
        return (SortedSet) ArraysKt.c(jArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Float> d(@NotNull float[] fArr) {
        Intrinsics.f(fArr, "receiver$0");
        return (SortedSet) ArraysKt.c(fArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Double> d(@NotNull double[] dArr) {
        Intrinsics.f(dArr, "receiver$0");
        return (SortedSet) ArraysKt.c(dArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Boolean> c(@NotNull boolean[] zArr) {
        Intrinsics.f(zArr, "receiver$0");
        return (SortedSet) ArraysKt.c(zArr, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Character> d(@NotNull char[] cArr) {
        Intrinsics.f(cArr, "receiver$0");
        return (SortedSet) ArraysKt.c(cArr, new TreeSet());
    }

    @NotNull
    public static final <T> SortedSet<T> b(@NotNull T[] tArr, @NotNull Comparator<? super T> comparator) {
        Intrinsics.f(tArr, "receiver$0");
        Intrinsics.f(comparator, "comparator");
        return (SortedSet) ArraysKt.d(tArr, new TreeSet(comparator));
    }
}
