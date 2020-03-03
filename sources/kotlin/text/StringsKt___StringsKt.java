package kotlin.text;

import com.xiaomi.smarthome.homeroom.HomeManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.collections.SlidingWindowKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ü\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b*\u00020\u0002\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002\u001aE\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\b\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00050\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001aM\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aN\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u00020\u00050\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\b¢\u0006\u0002\u0010\u0018\u001a3\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aN\u0010\u001d\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u000e\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0005\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0018\u001a\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0007\u001a4\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020 0\n*\u00020\u00022\u0006\u0010!\u001a\u00020\"H\u0007\u001a4\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0\n\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\r\u0010%\u001a\u00020\"*\u00020\u0002H\b\u001a!\u0010%\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010&\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010(\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0012\u0010(\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a!\u0010)\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010)\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010*\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010*\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0015\u0010+\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"H\b\u001a)\u0010-\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u001c\u0010/\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"H\b¢\u0006\u0002\u00100\u001a!\u00101\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u00101\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a6\u00102\u001a\u00020\u0002*\u00020\u00022'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000103H\b\u001a6\u00102\u001a\u00020 *\u00020 2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000103H\b\u001aQ\u00106\u001a\u0002H7\"\f\b\u0000\u00107*\u000608j\u0002`9*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000103H\b¢\u0006\u0002\u0010:\u001a!\u0010;\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010;\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a<\u0010<\u001a\u0002H7\"\f\b\u0000\u00107*\u000608j\u0002`9*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010=\u001a<\u0010>\u001a\u0002H7\"\f\b\u0000\u00107*\u000608j\u0002`9*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010=\u001a(\u0010?\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a(\u0010A\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a\n\u0010B\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010B\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010C\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a(\u0010C\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a3\u0010E\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\b0\u0004H\b\u001aL\u0010F\u001a\u0002H7\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\b0\u0004H\b¢\u0006\u0002\u0010H\u001aI\u0010I\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2'\u0010K\u001a#\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#03H\b¢\u0006\u0002\u0010M\u001a^\u0010N\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0OH\b¢\u0006\u0002\u0010P\u001aI\u0010Q\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2'\u0010K\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H#03H\b¢\u0006\u0002\u0010M\u001a^\u0010R\u001a\u0002H#\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010J\u001a\u0002H#2<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H#¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u0002H#0OH\b¢\u0006\u0002\u0010P\u001a!\u0010S\u001a\u00020T*\u00020\u00022\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020T0\u0004H\b\u001a6\u0010V\u001a\u00020T*\u00020\u00022'\u0010U\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020T03H\b\u001a)\u0010W\u001a\u00020\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a\u0019\u0010X\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010,\u001a\u00020\"¢\u0006\u0002\u00100\u001a9\u0010Y\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001f0\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001aS\u0010Y\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u001f0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b\u001aR\u0010Z\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u001c\b\u0001\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050[0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b¢\u0006\u0002\u0010\u0018\u001al\u0010Z\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u001c\b\u0002\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0[0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\b¢\u0006\u0002\u0010\u0019\u001a5\u0010\\\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0]\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\b\u001a!\u0010^\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a!\u0010_\u001a\u00020\"*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\n\u0010`\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010`\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0011\u0010a\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a(\u0010a\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a-\u0010b\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b\u001aB\u0010c\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#03H\b\u001aH\u0010d\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\b\b\u0000\u0010#*\u00020e*\u00020\u00022)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#03H\b\u001aa\u0010f\u001a\u0002H7\"\b\b\u0000\u0010#*\u00020e\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#03H\b¢\u0006\u0002\u0010g\u001a[\u0010h\u001a\u0002H7\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#03H\b¢\u0006\u0002\u0010g\u001a3\u0010i\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\b\b\u0000\u0010#*\u00020e*\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#0\u0004H\b\u001aL\u0010j\u001a\u0002H7\"\b\b\u0000\u0010#*\u00020e\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H#0\u0004H\b¢\u0006\u0002\u0010H\u001aF\u0010k\u001a\u0002H7\"\u0004\b\u0000\u0010#\"\u0010\b\u0001\u00107*\n\u0012\u0006\b\u0000\u0012\u0002H#0G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H72\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b¢\u0006\u0002\u0010H\u001a\u0011\u0010l\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a8\u0010m\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u0002H#0n*\u00020\u00022\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b¢\u0006\u0002\u0010@\u001a-\u0010p\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010q\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050rj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`s¢\u0006\u0002\u0010t\u001a\u0011\u0010u\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a8\u0010v\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010#*\b\u0012\u0004\u0012\u0002H#0n*\u00020\u00022\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H#0\u0004H\b¢\u0006\u0002\u0010@\u001a-\u0010w\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010q\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050rj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`s¢\u0006\u0002\u0010t\u001a\n\u0010x\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010x\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a0\u0010y\u001a\u0002Hz\"\b\b\u0000\u0010z*\u00020\u0002*\u0002Hz2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020T0\u0004H\b¢\u0006\u0002\u0010{\u001a-\u0010|\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a-\u0010|\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u0010*\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\r\u0010}\u001a\u00020\u0005*\u00020\u0002H\b\u001a\u0014\u0010}\u001a\u00020\u0005*\u00020\u00022\u0006\u0010}\u001a\u00020~H\u0007\u001a6\u0010\u001a\u00020\u0005*\u00020\u00022'\u0010K\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000503H\b\u001aL\u0010\u0001\u001a\u00020\u0005*\u00020\u00022<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050OH\b\u001a7\u0010\u0001\u001a\u00020\u0005*\u00020\u00022'\u0010K\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u000503H\b\u001aL\u0010\u0001\u001a\u00020\u0005*\u00020\u00022<\u0010K\u001a8\u0012\u0013\u0012\u00110\"¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u00050OH\b\u001a\u000b\u0010\u0001\u001a\u00020\u0002*\u00020\u0002\u001a\u000e\u0010\u0001\u001a\u00020 *\u00020 H\b\u001a\u000b\u0010\u0001\u001a\u00020\u0005*\u00020\u0002\u001a\"\u0010\u0001\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\u0012\u0010\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010D\u001a)\u0010\u0001\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b¢\u0006\u0002\u0010@\u001a\u001a\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\b\u001a\u0015\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\b\u0010\u0001\u001a\u00030\u0001\u001a\u001d\u0010\u0001\u001a\u00020 *\u00020 2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\bH\b\u001a\u0015\u0010\u0001\u001a\u00020 *\u00020 2\b\u0010\u0001\u001a\u00030\u0001\u001a\"\u0010\u0001\u001a\u00020\"*\u00020\u00022\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004H\b\u001a$\u0010\u0001\u001a\u00030\u0001*\u00020\u00022\u0013\u0010o\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u00010\u0004H\b\u001a\u0013\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0001\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010'\u001a\u00020\"\u001a\u0013\u0010\u0001\u001a\u00020 *\u00020 2\u0006\u0010'\u001a\u00020\"\u001a\"\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a\"\u0010\u0001\u001a\u00020 *\u00020 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\b\u001a+\u0010\u0001\u001a\u0002H7\"\u0010\b\u0000\u00107*\n\u0012\u0006\b\u0000\u0012\u00020\u00050G*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H7¢\u0006\u0003\u0010\u0001\u001a\u001d\u0010\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u0001*\u00020\u0002\u001a\u0011\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u001f*\u00020\u0002\u001a\u0011\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050[*\u00020\u0002\u001a\u0012\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0002\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020 0\u001f*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a1\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020 0\n*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u0001H\u0007\u001aK\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\n\"\u0004\b\u0000\u0010#*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\"2\t\b\u0002\u0010\u0001\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H#0\u0004H\u0007\u001a\u0018\u0010\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00050\u00010\b*\u00020\u0002\u001a)\u0010\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001f*\u00020\u00022\u0007\u0010 \u0001\u001a\u00020\u0002H\u0004\u001a]\u0010\u0001\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u001f\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0007\u0010 \u0001\u001a\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¢\u0001\u0012\u0004\u0012\u0002H\u000e03H\b\u001a\u001f\u0010£\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100\u001f*\u00020\u0002H\u0007\u001aT\u0010£\u0001\u001a\b\u0012\u0004\u0012\u0002H#0\u001f\"\u0004\b\u0000\u0010#*\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¡\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b4\u0012\t\b5\u0012\u0005\b\b(¢\u0001\u0012\u0004\u0012\u0002H#03H\b¨\u0006¤\u0001"}, d2 = {"all", "", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", "destination", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "associateWith", "valueSelector", "associateWithTo", "chunked", "", "", "size", "", "R", "chunkedSequence", "count", "drop", "n", "dropLast", "dropLastWhile", "dropWhile", "elementAt", "index", "elementAtOrElse", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", "first", "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", "initial", "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "none", "onEach", "S", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "random", "Lkotlin/random/Random;", "reduce", "reduceIndexed", "reduceRight", "reduceRightIndexed", "reversed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "windowed", "step", "partialWindows", "windowedSequence", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "zipWithNext", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
    @InlineOnly
    private static final char j(@NotNull CharSequence charSequence, int i) {
        return charSequence.charAt(i);
    }

    @InlineOnly
    private static final char c(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt.g(charSequence)) ? function1.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @InlineOnly
    private static final Character k(@NotNull CharSequence charSequence, int i) {
        return StringsKt.c(charSequence, i);
    }

    public static final char k(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (!(charSequence.length() == 0)) {
            return charSequence.charAt(0);
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final char d(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return charAt;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @Nullable
    public static final Character l(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(0));
    }

    @Nullable
    public static final Character e(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final char d(@NotNull CharSequence charSequence, int i, Function1<? super Integer, Character> function1) {
        return (i < 0 || i > StringsKt.g(charSequence)) ? function1.invoke(Integer.valueOf(i)).charValue() : charSequence.charAt(i);
    }

    @Nullable
    public static final Character c(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i < 0 || i > StringsKt.g(charSequence)) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(i));
    }

    public static final int f(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return i;
            }
        }
        return -1;
    }

    public static final int g(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(length))).booleanValue()) {
                return length;
            }
        }
        return -1;
    }

    public static final char m(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (!(charSequence.length() == 0)) {
            return charSequence.charAt(StringsKt.g(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final char h(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        char charAt;
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length >= 0) {
                charAt = charSequence.charAt(length);
            } else {
                throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
            }
        } while (!function1.invoke(Character.valueOf(charAt)).booleanValue());
        return charAt;
    }

    @Nullable
    public static final Character n(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (charSequence.length() == 0) {
            return null;
        }
        return Character.valueOf(charSequence.charAt(charSequence.length() - 1));
    }

    @Nullable
    public static final Character i(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        char charAt;
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = charSequence.charAt(length);
        } while (!function1.invoke(Character.valueOf(charAt)).booleanValue());
        return Character.valueOf(charAt);
    }

    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final char D(@NotNull CharSequence charSequence) {
        return StringsKt.a(charSequence, (Random) Random.b);
    }

    @SinceKotlin(version = "1.3")
    public static final char a(@NotNull CharSequence charSequence, @NotNull Random random) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(random, "random");
        if (!(charSequence.length() == 0)) {
            return charSequence.charAt(random.b(charSequence.length()));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static final char o(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        switch (charSequence.length()) {
            case 0:
                throw new NoSuchElementException("Char sequence is empty.");
            case 1:
                return charSequence.charAt(0);
            default:
                throw new IllegalArgumentException("Char sequence has more than one element.");
        }
    }

    public static final char j(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                if (!z) {
                    ch = Character.valueOf(charAt);
                    z = true;
                } else {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
            }
        }
        if (!z) {
            throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
        } else if (ch != null) {
            return ch.charValue();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
        }
    }

    @Nullable
    public static final Character p(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (charSequence.length() == 1) {
            return Character.valueOf(charSequence.charAt(0));
        }
        return null;
    }

    @Nullable
    public static final Character k(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        Character ch = null;
        boolean z = false;
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                if (z) {
                    return null;
                }
                ch = Character.valueOf(charAt);
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        return ch;
    }

    @NotNull
    public static final CharSequence d(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i >= 0) {
            return charSequence.subSequence(RangesKt.d(i, charSequence.length()), charSequence.length());
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String f(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        if (i >= 0) {
            String substring = str.substring(RangesKt.d(i, str.length()));
            Intrinsics.b(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence e(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i >= 0) {
            return StringsKt.f(charSequence, RangesKt.c(charSequence.length() - i, 0));
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String g(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        if (i >= 0) {
            return StringsKt.h(str, RangesKt.c(str.length() - i, 0));
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence l(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int g = StringsKt.g(charSequence); g >= 0; g--) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(g))).booleanValue()) {
                return charSequence.subSequence(0, g + 1);
            }
        }
        return "";
    }

    @NotNull
    public static final String d(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int g = StringsKt.g(str); g >= 0; g--) {
            if (!function1.invoke(Character.valueOf(str.charAt(g))).booleanValue()) {
                String substring = str.substring(0, g + 1);
                Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence m(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String e(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                String substring = str.substring(i);
                Intrinsics.b(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence n(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        Appendable sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return (CharSequence) sb;
    }

    @NotNull
    public static final String f(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        CharSequence charSequence = str;
        Appendable sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = ((StringBuilder) sb).toString();
        Intrinsics.b(sb2, "filterTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, "predicate");
        Appendable sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return (CharSequence) sb;
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function2, "predicate");
        CharSequence charSequence = str;
        Appendable sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
            i++;
            i2 = i3;
        }
        String sb2 = ((StringBuilder) sb).toString();
        Intrinsics.b(sb2, "filterIndexedTo(StringBu…(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final CharSequence o(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        Appendable sb = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        return (CharSequence) sb;
    }

    @NotNull
    public static final String g(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        CharSequence charSequence = str;
        Appendable sb = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            }
        }
        String sb2 = ((StringBuilder) sb).toString();
        Intrinsics.b(sb2, "filterNotTo(StringBuilder(), predicate).toString()");
        return sb2;
    }

    @NotNull
    public static final <C extends Appendable> C a(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (!function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                c.append(charAt);
            }
        }
        return c;
    }

    @NotNull
    public static final <C extends Appendable> C b(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                c.append(charAt);
            }
        }
        return c;
    }

    @NotNull
    public static final CharSequence d(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(intRange, "indices");
        if (intRange.e()) {
            return "";
        }
        return StringsKt.a(charSequence, intRange);
    }

    @NotNull
    public static final String b(@NotNull String str, @NotNull IntRange intRange) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(intRange, "indices");
        if (intRange.e()) {
            return "";
        }
        return StringsKt.a(str, intRange);
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull Iterable<Integer> iterable) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(iterable, "indices");
        int a2 = CollectionsKt.a(iterable, 10);
        if (a2 == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(a2);
        for (Integer intValue : iterable) {
            sb.append(charSequence.charAt(intValue.intValue()));
        }
        return sb;
    }

    @InlineOnly
    private static final String a(@NotNull String str, Iterable<Integer> iterable) {
        if (str != null) {
            return StringsKt.a((CharSequence) str, iterable).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence f(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i >= 0) {
            return charSequence.subSequence(0, RangesKt.d(i, charSequence.length()));
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String h(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        if (i >= 0) {
            String substring = str.substring(0, RangesKt.d(i, str.length()));
            Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence g(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i >= 0) {
            int length = charSequence.length();
            return charSequence.subSequence(length - RangesKt.d(i, length), length);
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final String i(@NotNull String str, int i) {
        Intrinsics.f(str, "receiver$0");
        if (i >= 0) {
            int length = str.length();
            String substring = str.substring(length - RangesKt.d(i, length));
            Intrinsics.b(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
    }

    @NotNull
    public static final CharSequence p(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int g = StringsKt.g(charSequence); g >= 0; g--) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(g))).booleanValue()) {
                return charSequence.subSequence(g + 1, charSequence.length());
            }
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String h(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int g = StringsKt.g(str); g >= 0; g--) {
            if (!function1.invoke(Character.valueOf(str.charAt(g))).booleanValue()) {
                String substring = str.substring(g + 1);
                Intrinsics.b(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
        }
        return str;
    }

    @NotNull
    public static final CharSequence q(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return charSequence.subSequence(0, i);
            }
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String i(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!function1.invoke(Character.valueOf(str.charAt(i))).booleanValue()) {
                String substring = str.substring(0, i);
                Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return str;
    }

    @NotNull
    public static final CharSequence q(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        StringBuilder reverse = new StringBuilder(charSequence).reverse();
        Intrinsics.b(reverse, "StringBuilder(this).reverse()");
        return reverse;
    }

    @InlineOnly
    private static final String m(@NotNull String str) {
        if (str != null) {
            return StringsKt.q(str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final <K, V> Map<K, V> r(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        Map<K, V> linkedHashMap = new LinkedHashMap<>(RangesKt.c(MapsKt.a(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) function1.invoke(Character.valueOf(charSequence.charAt(i)));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K> Map<K, Character> s(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "keySelector");
        Map<K, Character> linkedHashMap = new LinkedHashMap<>(RangesKt.c(MapsKt.a(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(function1.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, V> a(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "keySelector");
        Intrinsics.f(function12, "valueTransform");
        Map<K, V> linkedHashMap = new LinkedHashMap<>(RangesKt.c(MapsKt.a(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(function1.invoke(Character.valueOf(charAt)), function12.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M a(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(m, "destination");
        Intrinsics.f(function1, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(function1.invoke(Character.valueOf(charAt)), Character.valueOf(charAt));
        }
        return m;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M a(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(m, "destination");
        Intrinsics.f(function1, "keySelector");
        Intrinsics.f(function12, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(function1.invoke(Character.valueOf(charAt)), function12.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M b(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(m, "destination");
        Intrinsics.f(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Pair pair = (Pair) function1.invoke(Character.valueOf(charSequence.charAt(i)));
            m.put(pair.getFirst(), pair.getSecond());
        }
        return m;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <V> Map<Character, V> t(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends V> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "valueSelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.c(MapsKt.a(charSequence.length()), 16));
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            linkedHashMap.put(Character.valueOf(charAt), function1.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    @SinceKotlin(version = "1.3")
    public static final <V, M extends Map<? super Character, ? super V>> M c(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends V> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(m, "destination");
        Intrinsics.f(function1, "valueSelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            m.put(Character.valueOf(charAt), function1.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C a(@NotNull CharSequence charSequence, @NotNull C c) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        for (int i = 0; i < charSequence.length(); i++) {
            c.add(Character.valueOf(charSequence.charAt(i)));
        }
        return c;
    }

    @NotNull
    public static final HashSet<Character> r(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return (HashSet) StringsKt.a(charSequence, new HashSet(MapsKt.a(charSequence.length())));
    }

    @NotNull
    public static final List<Character> s(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        switch (charSequence.length()) {
            case 0:
                return CollectionsKt.a();
            case 1:
                return CollectionsKt.a(Character.valueOf(charSequence.charAt(0)));
            default:
                return StringsKt.t(charSequence);
        }
    }

    @NotNull
    public static final List<Character> t(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return (List) StringsKt.a(charSequence, new ArrayList(charSequence.length()));
    }

    @NotNull
    public static final Set<Character> u(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        switch (charSequence.length()) {
            case 0:
                return SetsKt.a();
            case 1:
                return SetsKt.a(Character.valueOf(charSequence.charAt(0)));
            default:
                return (Set) StringsKt.a(charSequence, new LinkedHashSet(MapsKt.a(charSequence.length())));
        }
    }

    @NotNull
    public static final <R> List<R> u(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        Collection arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt.a(arrayList, (Iterable) function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C a(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            CollectionsKt.a(c, (Iterable) function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return c;
    }

    @NotNull
    public static final <K> Map<K, List<Character>> v(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "keySelector");
        Map<K, List<Character>> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            List<Character> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(Character.valueOf(charAt));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> b(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "keySelector");
        Intrinsics.f(function12, "valueTransform");
        Map<K, List<V>> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            List<V> list = linkedHashMap.get(invoke);
            if (list == null) {
                list = new ArrayList<>();
                linkedHashMap.put(invoke, list);
            }
            list.add(function12.invoke(Character.valueOf(charAt)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M d(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(m, "destination");
        Intrinsics.f(function1, "keySelector");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            Object obj = m.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                m.put(invoke, obj);
            }
            ((List) obj).add(Character.valueOf(charAt));
        }
        return m;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M b(@NotNull CharSequence charSequence, @NotNull M m, @NotNull Function1<? super Character, ? extends K> function1, @NotNull Function1<? super Character, ? extends V> function12) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(m, "destination");
        Intrinsics.f(function1, "keySelector");
        Intrinsics.f(function12, "valueTransform");
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Object invoke = function1.invoke(Character.valueOf(charAt));
            Object obj = m.get(invoke);
            if (obj == null) {
                obj = new ArrayList();
                m.put(invoke, obj);
            }
            ((List) obj).add(function12.invoke(Character.valueOf(charAt)));
        }
        return m;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K> Grouping<Character, K> w(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends K> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "keySelector");
        return new StringsKt___StringsKt$groupingBy$1(charSequence, function1);
    }

    @NotNull
    public static final <R> List<R> x(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        Collection arrayList = new ArrayList(charSequence.length());
        for (int i = 0; i < charSequence.length(); i++) {
            arrayList.add(function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R> List<R> b(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, "transform");
        Collection arrayList = new ArrayList(charSequence.length());
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            arrayList.add(function2.invoke(valueOf, Character.valueOf(charAt)));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R> List<R> c(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, "transform");
        Collection arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            Object invoke = function2.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C b(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function2, "transform");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            c.add(function2.invoke(valueOf, Character.valueOf(charAt)));
        }
        return c;
    }

    @NotNull
    public static final <R> List<R> y(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        Collection arrayList = new ArrayList();
        for (int i = 0; i < charSequence.length(); i++) {
            Object invoke = function1.invoke(Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                arrayList.add(invoke);
            }
        }
        return (List) arrayList;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C c(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            c.add(function1.invoke(Character.valueOf(charSequence.charAt(i))));
        }
        return c;
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> v(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return new IndexingIterable<>(new StringsKt___StringsKt$withIndex$1(charSequence));
    }

    public static final boolean z(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (!function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean w(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return !(charSequence.length() == 0);
    }

    public static final boolean A(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    private static final int E(@NotNull CharSequence charSequence) {
        return charSequence.length();
    }

    public static final int B(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i2))).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2<? super R, ? super java.lang.Character, ? extends R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R> R a(@org.jetbrains.annotations.NotNull java.lang.CharSequence r2, R r3, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super R, ? super java.lang.Character, ? extends R> r4) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r2, r0)
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.f(r4, r0)
            r0 = 0
        L_0x000b:
            int r1 = r2.length()
            if (r0 >= r1) goto L_0x0020
            char r1 = r2.charAt(r0)
            java.lang.Character r1 = java.lang.Character.valueOf(r1)
            java.lang.Object r3 = r4.invoke(r3, r1)
            int r0 = r0 + 1
            goto L_0x000b
        L_0x0020:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt___StringsKt.a(java.lang.CharSequence, java.lang.Object, kotlin.jvm.functions.Function2):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super R, ? super java.lang.Character, ? extends R>, kotlin.jvm.functions.Function3, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R> R a(@org.jetbrains.annotations.NotNull java.lang.CharSequence r4, R r5, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super R, ? super java.lang.Character, ? extends R> r6) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r4, r0)
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.f(r6, r0)
            r0 = 0
            r1 = r5
            r5 = 0
        L_0x000d:
            int r2 = r4.length()
            if (r0 >= r2) goto L_0x0028
            char r2 = r4.charAt(r0)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            int r5 = r5 + 1
            java.lang.Character r2 = java.lang.Character.valueOf(r2)
            java.lang.Object r1 = r6.invoke(r3, r1, r2)
            int r0 = r0 + 1
            goto L_0x000d
        L_0x0028:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt___StringsKt.a(java.lang.CharSequence, java.lang.Object, kotlin.jvm.functions.Function3):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2<? super java.lang.Character, ? super R, ? extends R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R> R b(@org.jetbrains.annotations.NotNull java.lang.CharSequence r2, R r3, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.lang.Character, ? super R, ? extends R> r4) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r2, r0)
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.f(r4, r0)
            int r0 = kotlin.text.StringsKt.g(r2)
        L_0x000e:
            if (r0 < 0) goto L_0x0020
            int r1 = r0 + -1
            char r0 = r2.charAt(r0)
            java.lang.Character r0 = java.lang.Character.valueOf(r0)
            java.lang.Object r3 = r4.invoke(r0, r3)
            r0 = r1
            goto L_0x000e
        L_0x0020:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt___StringsKt.b(java.lang.CharSequence, java.lang.Object, kotlin.jvm.functions.Function2):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super java.lang.Character, ? super R, ? extends R>, kotlin.jvm.functions.Function3, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <R> R b(@org.jetbrains.annotations.NotNull java.lang.CharSequence r3, R r4, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super java.lang.Character, ? super R, ? extends R> r5) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r3, r0)
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.f(r5, r0)
            int r0 = kotlin.text.StringsKt.g(r3)
        L_0x000e:
            if (r0 < 0) goto L_0x0023
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            char r2 = r3.charAt(r0)
            java.lang.Character r2 = java.lang.Character.valueOf(r2)
            java.lang.Object r4 = r5.invoke(r1, r2, r4)
            int r0 = r0 + -1
            goto L_0x000e
        L_0x0023:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt___StringsKt.b(java.lang.CharSequence, java.lang.Object, kotlin.jvm.functions.Function3):java.lang.Object");
    }

    public static final void C(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Unit> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "action");
        for (int i = 0; i < charSequence.length(); i++) {
            function1.invoke(Character.valueOf(charSequence.charAt(i)));
        }
    }

    public static final void d(@NotNull CharSequence charSequence, @NotNull Function2<? super Integer, ? super Character, Unit> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, "action");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            char charAt = charSequence.charAt(i2);
            Integer valueOf = Integer.valueOf(i);
            i++;
            function2.invoke(valueOf, Character.valueOf(charAt));
        }
    }

    @Nullable
    public static final Character x(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int g = StringsKt.g(charSequence);
        if (1 <= g) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (charAt < charAt2) {
                    charAt = charAt2;
                }
                if (i == g) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character D(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "selector");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        Comparable comparable = (Comparable) function1.invoke(Character.valueOf(charAt));
        int g = StringsKt.g(charSequence);
        if (1 <= g) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                Comparable comparable2 = (Comparable) function1.invoke(Character.valueOf(charAt2));
                if (comparable.compareTo(comparable2) < 0) {
                    charAt = charAt2;
                    comparable = comparable2;
                }
                if (i == g) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character a(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(comparator, "comparator");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int g = StringsKt.g(charSequence);
        if (1 <= g) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) < 0) {
                    charAt = charAt2;
                }
                if (i == g) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character y(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int g = StringsKt.g(charSequence);
        if (1 <= g) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (charAt > charAt2) {
                    charAt = charAt2;
                }
                if (i == g) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character E(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "selector");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        Comparable comparable = (Comparable) function1.invoke(Character.valueOf(charAt));
        int g = StringsKt.g(charSequence);
        if (1 <= g) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                Comparable comparable2 = (Comparable) function1.invoke(Character.valueOf(charAt2));
                if (comparable.compareTo(comparable2) > 0) {
                    charAt = charAt2;
                    comparable = comparable2;
                }
                if (i == g) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    @Nullable
    public static final Character b(@NotNull CharSequence charSequence, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(comparator, "comparator");
        int i = 1;
        if (charSequence.length() == 0) {
            return null;
        }
        char charAt = charSequence.charAt(0);
        int g = StringsKt.g(charSequence);
        if (1 <= g) {
            while (true) {
                char charAt2 = charSequence.charAt(i);
                if (comparator.compare(Character.valueOf(charAt), Character.valueOf(charAt2)) > 0) {
                    charAt = charAt2;
                }
                if (i == g) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(charAt);
    }

    public static final boolean z(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return charSequence.length() == 0;
    }

    public static final boolean F(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        for (int i = 0; i < charSequence.length(); i++) {
            if (function1.invoke(Character.valueOf(charSequence.charAt(i))).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <S extends CharSequence> S G(@NotNull S s, @NotNull Function1<? super Character, Unit> function1) {
        Intrinsics.f(s, "receiver$0");
        Intrinsics.f(function1, "action");
        for (int i = 0; i < s.length(); i++) {
            function1.invoke(Character.valueOf(s.charAt(i)));
        }
        return s;
    }

    public static final char e(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, HomeManager.v);
        int i = 1;
        if (!(charSequence.length() == 0)) {
            char charAt = charSequence.charAt(0);
            int g = StringsKt.g(charSequence);
            if (1 <= g) {
                while (true) {
                    charAt = function2.invoke(Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i))).charValue();
                    if (i == g) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char a(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> function3) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function3, HomeManager.v);
        int i = 1;
        if (!(charSequence.length() == 0)) {
            char charAt = charSequence.charAt(0);
            int g = StringsKt.g(charSequence);
            if (1 <= g) {
                while (true) {
                    charAt = function3.invoke(Integer.valueOf(i), Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i))).charValue();
                    if (i == g) {
                        break;
                    }
                    i++;
                }
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char f(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, Character> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, HomeManager.v);
        int g = StringsKt.g(charSequence);
        if (g >= 0) {
            char charAt = charSequence.charAt(g);
            for (int i = g - 1; i >= 0; i--) {
                charAt = function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charAt)).charValue();
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final char b(@NotNull CharSequence charSequence, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> function3) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function3, HomeManager.v);
        int g = StringsKt.g(charSequence);
        if (g >= 0) {
            char charAt = charSequence.charAt(g);
            for (int i = g - 1; i >= 0; i--) {
                charAt = function3.invoke(Integer.valueOf(i), Character.valueOf(charSequence.charAt(i)), Character.valueOf(charAt)).charValue();
            }
            return charAt;
        }
        throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
    }

    public static final int H(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Integer> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "selector");
        int i = 0;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            i += function1.invoke(Character.valueOf(charSequence.charAt(i2))).intValue();
        }
        return i;
    }

    public static final double I(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Double> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "selector");
        double d = 0.0d;
        for (int i = 0; i < charSequence.length(); i++) {
            d += function1.invoke(Character.valueOf(charSequence.charAt(i))).doubleValue();
        }
        return d;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<String> h(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        return StringsKt.a(charSequence, i, i, true);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> List<R> a(@NotNull CharSequence charSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        return StringsKt.a(charSequence, i, i, true, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<String> i(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        return StringsKt.b(charSequence, i, StringsKt___StringsKt$chunkedSequence$1.INSTANCE);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> Sequence<R> b(@NotNull CharSequence charSequence, int i, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        return StringsKt.b(charSequence, i, i, true, function1);
    }

    @NotNull
    public static final Pair<CharSequence, CharSequence> J(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb, sb2);
    }

    @NotNull
    public static final Pair<String, String> j(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                sb.append(charAt);
            } else {
                sb2.append(charAt);
            }
        }
        return new Pair<>(sb.toString(), sb2.toString());
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ List a(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, i, i2, z);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<String> a(@NotNull CharSequence charSequence, int i, int i2, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        return StringsKt.a(charSequence, i, i2, z, StringsKt___StringsKt$windowed$1.INSTANCE);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ List a(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, i, i2, z, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> List<R> a(@NotNull CharSequence charSequence, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        SlidingWindowKt.a(i, i2);
        int length = charSequence.length();
        ArrayList arrayList = new ArrayList(((length + i2) - 1) / i2);
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + i;
            if (i4 > length) {
                if (!z) {
                    break;
                }
                i4 = length;
            }
            arrayList.add(function1.invoke(charSequence.subSequence(i3, i4)));
            i3 += i2;
        }
        return arrayList;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ Sequence b(CharSequence charSequence, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, i, i2, z);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final Sequence<String> b(@NotNull CharSequence charSequence, int i, int i2, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        return StringsKt.b(charSequence, i, i2, z, StringsKt___StringsKt$windowedSequence$1.INSTANCE);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static /* synthetic */ Sequence b(CharSequence charSequence, int i, int i2, boolean z, Function1 function1, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, i, i2, z, function1);
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> Sequence<R> b(@NotNull CharSequence charSequence, int i, int i2, boolean z, @NotNull Function1<? super CharSequence, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "transform");
        SlidingWindowKt.a(i, i2);
        return SequencesKt.u(CollectionsKt.I(RangesKt.a((IntProgression) z ? StringsKt.f(charSequence) : RangesKt.b(0, (charSequence.length() - i) + 1), i2)), new StringsKt___StringsKt$windowedSequence$2(charSequence, function1, i));
    }

    @NotNull
    public static final <V> List<V> a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull Function2<? super Character, ? super Character, ? extends V> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "other");
        Intrinsics.f(function2, "transform");
        int min = Math.min(charSequence.length(), charSequence2.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charSequence2.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final <R> List<R> g(@NotNull CharSequence charSequence, @NotNull Function2<? super Character, ? super Character, ? extends R> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function2, "transform");
        int length = charSequence.length() - 1;
        if (length < 1) {
            return CollectionsKt.a();
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            i++;
            arrayList.add(function2.invoke(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    public static final Iterable<Character> B(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (charSequence instanceof String) {
            if (charSequence.length() == 0) {
                return CollectionsKt.a();
            }
        }
        return new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(charSequence);
    }

    @NotNull
    public static final Sequence<Character> C(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        if (charSequence instanceof String) {
            if (charSequence.length() == 0) {
                return SequencesKt.b();
            }
        }
        return new StringsKt___StringsKt$asSequence$$inlined$Sequence$1(charSequence);
    }

    @InlineOnly
    private static final Character K(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            if (function1.invoke(Character.valueOf(charAt)).booleanValue()) {
                return Character.valueOf(charAt);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character L(@NotNull CharSequence charSequence, Function1<? super Character, Boolean> function1) {
        char charAt;
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return null;
            }
            charAt = charSequence.charAt(length);
        } while (!function1.invoke(Character.valueOf(charAt)).booleanValue());
        return Character.valueOf(charAt);
    }

    @NotNull
    public static final <C extends Appendable> C a(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, Boolean> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function2, "predicate");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            int i3 = i2 + 1;
            if (function2.invoke(Integer.valueOf(i2), Character.valueOf(charAt)).booleanValue()) {
                c.append(charAt);
            }
            i++;
            i2 = i3;
        }
        return c;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C a(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function2<? super Integer, ? super Character, ? extends R> function2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function2, "transform");
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i2 + 1;
            Object invoke = function2.invoke(Integer.valueOf(i2), Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                c.add(invoke);
            }
            i++;
            i2 = i3;
        }
        return c;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C b(@NotNull CharSequence charSequence, @NotNull C c, @NotNull Function1<? super Character, ? extends R> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(c, "destination");
        Intrinsics.f(function1, "transform");
        for (int i = 0; i < charSequence.length(); i++) {
            Object invoke = function1.invoke(Character.valueOf(charSequence.charAt(i)));
            if (invoke != null) {
                c.add(invoke);
            }
        }
        return c;
    }

    @NotNull
    public static final List<Pair<Character, Character>> d(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "other");
        int min = Math.min(charSequence.length(), charSequence2.length());
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(TuplesKt.a(Character.valueOf(charSequence.charAt(i)), Character.valueOf(charSequence2.charAt(i))));
        }
        return arrayList;
    }

    @NotNull
    @SinceKotlin(version = "1.2")
    public static final List<Pair<Character, Character>> A(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        int length = charSequence.length() - 1;
        if (length < 1) {
            return CollectionsKt.a();
        }
        ArrayList arrayList = new ArrayList(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            i++;
            arrayList.add(TuplesKt.a(Character.valueOf(charAt), Character.valueOf(charSequence.charAt(i))));
        }
        return arrayList;
    }
}
