package kotlin.text;

import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.common.Constants;
import com.xiaomi.infra.galaxy.fds.Common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\n\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a:\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010\u001e\u001a\u00020\r*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0006\u001a4\u0010 \u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\b¢\u0006\u0002\u0010%\u001a4\u0010&\u001a\u0002H!\"\f\b\u0000\u0010\"*\u00020\u0002*\u0002H!\"\u0004\b\u0001\u0010!*\u0002H\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\b¢\u0006\u0002\u0010%\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b)\u001a&\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010+\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010+\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u0010.\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u0010/\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u00100\u001a\u00020\r*\u00020\u0002H\b\u001a \u00101\u001a\u00020\r*\u0004\u0018\u00010\u0002H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a \u00102\u001a\u00020\r*\u0004\u0018\u00010\u0002H\b\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000\u001a\r\u00103\u001a\u000204*\u00020\u0002H\u0002\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00105\u001a\u00020\u0006*\u00020\u00022\u0006\u0010*\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00106\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u00106\u001a\u00020\u0006*\u00020\u00022\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00107\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u0002\u001a\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u0002\u001a\u0015\u0010;\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\f\u001a\u000f\u0010<\u001a\u00020\n*\u0004\u0018\u00010\nH\b\u001a\u001c\u0010=\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010=\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\u0002*\u00020\u00022\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001a\u001c\u0010@\u001a\u00020\n*\u00020\n2\u0006\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0011\u001aG\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u000e\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0004\bE\u0010F\u001a=\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000108*\u00020\u00022\u0006\u0010B\u001a\u00020-2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0002\bE\u001a4\u0010G\u001a\u00020\r*\u00020\u00022\u0006\u0010H\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010J\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u0002\u001a\u0012\u0010J\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u0002\u001a\u001a\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006\u001a\u0012\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\b\u001a\u0015\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001H\b\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\u0002*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u0002\u001a\u001a\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a+\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010R\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020\u00020SH\b\u001a\u001d\u0010Q\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\b\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010V\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Y\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\u0006\u0010U\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001d\u0010[\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\nH\b\u001a\"\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002\u001a\u001a\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002\u001a%\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0002H\b\u001a\u001d\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010U\u001a\u00020\u0002H\b\u001a=\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006¢\u0006\u0002\u0010^\u001a0\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a/\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010P\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010D\u001a\u00020\u0006H\u0002¢\u0006\u0002\b_\u001a%\u0010]\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010D\u001a\u00020\u0006H\b\u001a=\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0C\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006¢\u0006\u0002\u0010a\u001a0\u0010`\u001a\b\u0012\u0004\u0012\u00020\n08*\u00020\u00022\n\u0010B\u001a\u00020-\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010D\u001a\u00020\u0006\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010b\u001a\u00020\r*\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010c\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u001d\u0010c\u001a\u00020\u0002*\u00020\n2\u0006\u0010d\u001a\u00020\u00062\u0006\u0010e\u001a\u00020\u0006H\b\u001a\u001f\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010(\u001a\u00020\u0006H\b\u001a\u0012\u0010f\u001a\u00020\n*\u00020\u00022\u0006\u0010M\u001a\u00020\u0001\u001a\u0012\u0010f\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0001\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010g\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010h\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010i\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020\n\u001a\u001c\u0010j\u001a\u00020\n*\u00020\n2\u0006\u0010P\u001a\u00020\n2\b\b\u0002\u0010W\u001a\u00020\n\u001a\n\u0010k\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010k\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010k\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010k\u001a\u00020\n*\u00020\nH\b\u001a!\u0010k\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010k\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010m\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010m\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010m\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010m\u001a\u00020\n*\u00020\nH\b\u001a!\u0010m\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010m\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\n\u0010n\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010n\u001a\u00020\u0002*\u00020\u00022\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010n\u001a\u00020\u0002*\u00020\u00022\n\u0010,\u001a\u00020-\"\u00020\u0011\u001a\r\u0010n\u001a\u00020\n*\u00020\nH\b\u001a!\u0010n\u001a\u00020\n*\u00020\n2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0SH\b\u001a\u0016\u0010n\u001a\u00020\n*\u00020\n2\n\u0010,\u001a\u00020-\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006o"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @InlineOnly
    private static final String p(@Nullable String str) {
        return str != null ? str : "";
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean booleanValue = function1.invoke(Character.valueOf(charSequence.charAt(!z ? i : length))).booleanValue();
            if (!z) {
                if (!booleanValue) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!booleanValue) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        CharSequence charSequence = str;
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean booleanValue = function1.invoke(Character.valueOf(charSequence.charAt(!z ? i : length))).booleanValue();
            if (!z) {
                if (!booleanValue) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!booleanValue) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1).toString();
    }

    @NotNull
    public static final CharSequence b(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
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
    public static final String b(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        CharSequence charSequence;
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        CharSequence charSequence2 = str;
        int length = charSequence2.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (!function1.invoke(Character.valueOf(charSequence2.charAt(i))).booleanValue()) {
                charSequence = charSequence2.subSequence(i, charSequence2.length());
                break;
            } else {
                i++;
            }
        }
        return charSequence.toString();
    }

    @NotNull
    public static final CharSequence c(@NotNull CharSequence charSequence, @NotNull Function1<? super Character, Boolean> function1) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(function1, "predicate");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (function1.invoke(Character.valueOf(charSequence.charAt(length))).booleanValue());
        return charSequence.subSequence(0, length + 1);
    }

    @NotNull
    public static final String c(@NotNull String str, @NotNull Function1<? super Character, Boolean> function1) {
        CharSequence charSequence;
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(function1, "predicate");
        CharSequence charSequence2 = str;
        int length = charSequence2.length();
        while (true) {
            length--;
            if (length >= 0) {
                if (!function1.invoke(Character.valueOf(charSequence2.charAt(length))).booleanValue()) {
                    charSequence = charSequence2.subSequence(0, length + 1);
                    break;
                }
            } else {
                break;
            }
        }
        return charSequence.toString();
    }

    @InlineOnly
    private static final String m(@NotNull String str) {
        if (str != null) {
            return StringsKt.b(str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @InlineOnly
    private static final String n(@NotNull String str) {
        if (str != null) {
            return StringsKt.c(str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @InlineOnly
    private static final String o(@NotNull String str) {
        if (str != null) {
            return StringsKt.d(str).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static /* synthetic */ CharSequence a(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.a(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, int i, char c) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i < 0) {
            throw new IllegalArgumentException("Desired length " + i + " is less than zero.");
        } else if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(i);
            int length = i - charSequence.length();
            int i2 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append(c);
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            sb.append(charSequence);
            return sb;
        }
    }

    @NotNull
    public static /* synthetic */ String a(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.a(str, i, c);
    }

    @NotNull
    public static final String a(@NotNull String str, int i, char c) {
        Intrinsics.f(str, "receiver$0");
        return StringsKt.a((CharSequence) str, i, c).toString();
    }

    @NotNull
    public static /* synthetic */ CharSequence b(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.b(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence b(@NotNull CharSequence charSequence, int i, char c) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i < 0) {
            throw new IllegalArgumentException("Desired length " + i + " is less than zero.");
        } else if (i <= charSequence.length()) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(i);
            sb.append(charSequence);
            int length = i - charSequence.length();
            int i2 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append(c);
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                }
            }
            return sb;
        }
    }

    @NotNull
    public static /* synthetic */ String b(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.b(str, i, c);
    }

    @NotNull
    public static final String b(@NotNull String str, int i, char c) {
        Intrinsics.f(str, "receiver$0");
        return StringsKt.b((CharSequence) str, i, c).toString();
    }

    @InlineOnly
    private static final boolean j(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    @InlineOnly
    private static final boolean k(@NotNull CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    @InlineOnly
    private static final boolean l(@NotNull CharSequence charSequence) {
        return charSequence.length() > 0;
    }

    @InlineOnly
    private static final boolean m(@NotNull CharSequence charSequence) {
        return !StringsKt.a(charSequence);
    }

    @InlineOnly
    private static final boolean n(@Nullable CharSequence charSequence) {
        return charSequence == null || StringsKt.a(charSequence);
    }

    @NotNull
    public static final CharIterator e(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return new StringsKt__StringsKt$iterator$1(charSequence);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [R, C, java.lang.CharSequence] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @kotlin.SinceKotlin(version = "1.3")
    @kotlin.internal.InlineOnly
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <C extends java.lang.CharSequence & R, R> R a(C r1, kotlin.jvm.functions.Function0<? extends R> r2) {
        /*
            int r0 = r1.length()
            if (r0 != 0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            if (r0 == 0) goto L_0x000f
            java.lang.Object r1 = r2.invoke()
        L_0x000f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.a(java.lang.CharSequence, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [R, C, java.lang.CharSequence] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @kotlin.SinceKotlin(version = "1.3")
    @kotlin.internal.InlineOnly
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <C extends java.lang.CharSequence & R, R> R b(C r1, kotlin.jvm.functions.Function0<? extends R> r2) {
        /*
            boolean r0 = kotlin.text.StringsKt.a((java.lang.CharSequence) r1)
            if (r0 == 0) goto L_0x000a
            java.lang.Object r1 = r2.invoke()
        L_0x000a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.b(java.lang.CharSequence, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    @NotNull
    public static final IntRange f(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return new IntRange(0, charSequence.length() - 1);
    }

    public static final int g(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return charSequence.length() - 1;
    }

    public static final boolean b(@NotNull CharSequence charSequence, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        int length = charSequence.length() - 2;
        if (i >= 0 && length >= i && Character.isHighSurrogate(charSequence.charAt(i)) && Character.isLowSurrogate(charSequence.charAt(i + 1))) {
            return true;
        }
        return false;
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull IntRange intRange) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(intRange, Common.v);
        String substring = str.substring(intRange.g().intValue(), intRange.i().intValue() + 1);
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(intRange, Common.v);
        return charSequence.subSequence(intRange.g().intValue(), intRange.i().intValue() + 1);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @InlineOnly
    private static final CharSequence a(@NotNull String str, int i, int i2) {
        return str.subSequence(i, i2);
    }

    @InlineOnly
    static /* synthetic */ String a(CharSequence charSequence, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = charSequence.length();
        }
        return charSequence.subSequence(i, i2).toString();
    }

    @InlineOnly
    private static final String b(@NotNull CharSequence charSequence, int i, int i2) {
        return charSequence.subSequence(i, i2).toString();
    }

    @NotNull
    public static final String b(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(intRange, Common.v);
        return charSequence.subSequence(intRange.g().intValue(), intRange.i().intValue() + 1).toString();
    }

    @NotNull
    public static /* synthetic */ String a(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.a(str, c, str2);
    }

    @NotNull
    public static final String a(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "missingDelimiterValue");
        int a2 = StringsKt.a((CharSequence) str, c, 0, false, 6, (Object) null);
        if (a2 == -1) {
            return str2;
        }
        String substring = str.substring(0, a2);
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String b(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.b(str, str2, str3);
    }

    @NotNull
    public static final String b(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "missingDelimiterValue");
        int a2 = StringsKt.a((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (a2 == -1) {
            return str3;
        }
        String substring = str.substring(0, a2);
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String b(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.b(str, c, str2);
    }

    @NotNull
    public static final String b(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "missingDelimiterValue");
        int a2 = StringsKt.a((CharSequence) str, c, 0, false, 6, (Object) null);
        if (a2 == -1) {
            return str2;
        }
        String substring = str.substring(a2 + 1, str.length());
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String c(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.c(str, str2, str3);
    }

    @NotNull
    public static final String c(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "missingDelimiterValue");
        int a2 = StringsKt.a((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (a2 == -1) {
            return str3;
        }
        String substring = str.substring(a2 + str2.length(), str.length());
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String c(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.c(str, c, str2);
    }

    @NotNull
    public static final String c(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "missingDelimiterValue");
        int b = StringsKt.b((CharSequence) str, c, 0, false, 6, (Object) null);
        if (b == -1) {
            return str2;
        }
        String substring = str.substring(0, b);
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String d(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.d(str, str2, str3);
    }

    @NotNull
    public static final String d(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "missingDelimiterValue");
        int b = StringsKt.b((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (b == -1) {
            return str3;
        }
        String substring = str.substring(0, b);
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String d(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.d(str, c, str2);
    }

    @NotNull
    public static final String d(@NotNull String str, char c, @NotNull String str2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "missingDelimiterValue");
        int b = StringsKt.b((CharSequence) str, c, 0, false, 6, (Object) null);
        if (b == -1) {
            return str2;
        }
        String substring = str.substring(b + 1, str.length());
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static /* synthetic */ String e(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.e(str, str2, str3);
    }

    @NotNull
    public static final String e(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "missingDelimiterValue");
        int b = StringsKt.b((CharSequence) str, str2, 0, false, 6, (Object) null);
        if (b == -1) {
            return str3;
        }
        String substring = str.substring(b + str2.length(), str.length());
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, int i, int i2, @NotNull CharSequence charSequence2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "replacement");
        if (i2 >= i) {
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence, 0, i);
            sb.append(charSequence2);
            sb.append(charSequence, i2, charSequence.length());
            return sb;
        }
        throw new IndexOutOfBoundsException("End index (" + i2 + ") is less than start index (" + i + ").");
    }

    @InlineOnly
    private static final String a(@NotNull String str, int i, int i2, CharSequence charSequence) {
        if (str != null) {
            return StringsKt.a((CharSequence) str, i, i2, charSequence).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull IntRange intRange, @NotNull CharSequence charSequence2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(intRange, Common.v);
        Intrinsics.f(charSequence2, "replacement");
        return StringsKt.a(charSequence, intRange.g().intValue(), intRange.i().intValue() + 1, charSequence2);
    }

    @InlineOnly
    private static final String a(@NotNull String str, IntRange intRange, CharSequence charSequence) {
        if (str != null) {
            return StringsKt.a((CharSequence) str, intRange, charSequence).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, int i, int i2) {
        Intrinsics.f(charSequence, "receiver$0");
        if (i2 < i) {
            throw new IndexOutOfBoundsException("End index (" + i2 + ") is less than start index (" + i + ").");
        } else if (i2 == i) {
            return charSequence.subSequence(0, charSequence.length());
        } else {
            StringBuilder sb = new StringBuilder(charSequence.length() - (i2 - i));
            sb.append(charSequence, 0, i);
            sb.append(charSequence, i2, charSequence.length());
            return sb;
        }
    }

    @InlineOnly
    private static final String b(@NotNull String str, int i, int i2) {
        if (str != null) {
            return StringsKt.a((CharSequence) str, i, i2).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence c(@NotNull CharSequence charSequence, @NotNull IntRange intRange) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(intRange, Common.v);
        return StringsKt.a(charSequence, intRange.g().intValue(), intRange.i().intValue() + 1);
    }

    @InlineOnly
    private static final String b(@NotNull String str, IntRange intRange) {
        if (str != null) {
            return StringsKt.c((CharSequence) str, intRange).toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, Constants.Name.PREFIX);
        if (StringsKt.a(charSequence, charSequence2, false, 2, (Object) null)) {
            return charSequence.subSequence(charSequence2.length(), charSequence.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(charSequence, Constants.Name.PREFIX);
        if (!StringsKt.a((CharSequence) str, charSequence, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(charSequence.length());
        Intrinsics.b(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
    }

    @NotNull
    public static final CharSequence b(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, Constants.Name.SUFFIX);
        if (StringsKt.b(charSequence, charSequence2, false, 2, (Object) null)) {
            return charSequence.subSequence(0, charSequence.length() - charSequence2.length());
        }
        return charSequence.subSequence(0, charSequence.length());
    }

    @NotNull
    public static final String b(@NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(charSequence, Constants.Name.SUFFIX);
        if (!StringsKt.b((CharSequence) str, charSequence, false, 2, (Object) null)) {
            return str;
        }
        String substring = str.substring(0, str.length() - charSequence.length());
        Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, @NotNull CharSequence charSequence3) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, Constants.Name.PREFIX);
        Intrinsics.f(charSequence3, Constants.Name.SUFFIX);
        if (charSequence.length() < charSequence2.length() + charSequence3.length() || !StringsKt.a(charSequence, charSequence2, false, 2, (Object) null) || !StringsKt.b(charSequence, charSequence3, false, 2, (Object) null)) {
            return charSequence.subSequence(0, charSequence.length());
        }
        return charSequence.subSequence(charSequence2.length(), charSequence.length() - charSequence3.length());
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(charSequence, Constants.Name.PREFIX);
        Intrinsics.f(charSequence2, Constants.Name.SUFFIX);
        if (str.length() >= charSequence.length() + charSequence2.length()) {
            CharSequence charSequence3 = str;
            if (StringsKt.a(charSequence3, charSequence, false, 2, (Object) null) && StringsKt.b(charSequence3, charSequence2, false, 2, (Object) null)) {
                String substring = str.substring(charSequence.length(), str.length() - charSequence2.length());
                Intrinsics.b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return str;
    }

    @NotNull
    public static final CharSequence c(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "delimiter");
        return StringsKt.a(charSequence, charSequence2, charSequence2);
    }

    @NotNull
    public static final String c(@NotNull String str, @NotNull CharSequence charSequence) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(charSequence, "delimiter");
        return StringsKt.a(str, charSequence, charSequence);
    }

    @NotNull
    public static /* synthetic */ String a(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.a(str, c, str2, str3);
    }

    @NotNull
    public static final String a(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "replacement");
        Intrinsics.f(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        int a2 = StringsKt.a(charSequence, c, 0, false, 6, (Object) null);
        return a2 == -1 ? str3 : StringsKt.a(charSequence, 0, a2, (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String a(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.a(str, str2, str3, str4);
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "replacement");
        Intrinsics.f(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        int a2 = StringsKt.a(charSequence, str2, 0, false, 6, (Object) null);
        return a2 == -1 ? str4 : StringsKt.a(charSequence, 0, a2, (CharSequence) str3).toString();
    }

    @NotNull
    public static /* synthetic */ String b(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.b(str, c, str2, str3);
    }

    @NotNull
    public static final String b(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "replacement");
        Intrinsics.f(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        int a2 = StringsKt.a(charSequence, c, 0, false, 6, (Object) null);
        return a2 == -1 ? str3 : StringsKt.a(charSequence, a2 + 1, str.length(), (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String b(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.b(str, str2, str3, str4);
    }

    @NotNull
    public static final String b(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "replacement");
        Intrinsics.f(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        int a2 = StringsKt.a(charSequence, str2, 0, false, 6, (Object) null);
        return a2 == -1 ? str4 : StringsKt.a(charSequence, a2 + str2.length(), str.length(), (CharSequence) str3).toString();
    }

    @NotNull
    public static /* synthetic */ String c(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.c(str, str2, str3, str4);
    }

    @NotNull
    public static final String c(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "replacement");
        Intrinsics.f(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        int b = StringsKt.b(charSequence, str2, 0, false, 6, (Object) null);
        return b == -1 ? str4 : StringsKt.a(charSequence, b + str2.length(), str.length(), (CharSequence) str3).toString();
    }

    @NotNull
    public static /* synthetic */ String c(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.c(str, c, str2, str3);
    }

    @NotNull
    public static final String c(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "replacement");
        Intrinsics.f(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        int b = StringsKt.b(charSequence, c, 0, false, 6, (Object) null);
        return b == -1 ? str3 : StringsKt.a(charSequence, b + 1, str.length(), (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String d(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.d(str, c, str2, str3);
    }

    @NotNull
    public static final String d(@NotNull String str, char c, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "replacement");
        Intrinsics.f(str3, "missingDelimiterValue");
        CharSequence charSequence = str;
        int b = StringsKt.b(charSequence, c, 0, false, 6, (Object) null);
        return b == -1 ? str3 : StringsKt.a(charSequence, 0, b, (CharSequence) str2).toString();
    }

    @NotNull
    public static /* synthetic */ String d(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.d(str, str2, str3, str4);
    }

    @NotNull
    public static final String d(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(str2, "delimiter");
        Intrinsics.f(str3, "replacement");
        Intrinsics.f(str4, "missingDelimiterValue");
        CharSequence charSequence = str;
        int b = StringsKt.b(charSequence, str2, 0, false, 6, (Object) null);
        return b == -1 ? str4 : StringsKt.a(charSequence, 0, b, (CharSequence) str3).toString();
    }

    @InlineOnly
    private static final String a(@NotNull CharSequence charSequence, Regex regex, String str) {
        return regex.replace(charSequence, str);
    }

    @InlineOnly
    private static final String a(@NotNull CharSequence charSequence, Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
        return regex.replace(charSequence, function1);
    }

    @InlineOnly
    private static final String b(@NotNull CharSequence charSequence, Regex regex, String str) {
        return regex.replaceFirst(charSequence, str);
    }

    @InlineOnly
    private static final boolean a(@NotNull CharSequence charSequence, Regex regex) {
        return regex.matches(charSequence);
    }

    public static final boolean b(@NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2, int i2, int i3, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!CharsKt.a(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean a(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, c, z);
    }

    public static final boolean a(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        return charSequence.length() > 0 && CharsKt.a(charSequence.charAt(0), c, z);
    }

    public static /* synthetic */ boolean b(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, c, z);
    }

    public static final boolean b(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        return charSequence.length() > 0 && CharsKt.a(charSequence.charAt(StringsKt.g(charSequence)), c, z);
    }

    public static /* synthetic */ boolean a(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, charSequence2, z);
    }

    public static final boolean a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, Constants.Name.PREFIX);
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return StringsKt.b((String) charSequence, (String) charSequence2, false, 2, (Object) null);
        }
        return StringsKt.b(charSequence, 0, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean a(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, charSequence2, i, z);
    }

    public static final boolean a(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, Constants.Name.PREFIX);
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return StringsKt.a((String) charSequence, (String) charSequence2, i, false, 4, (Object) null);
        }
        return StringsKt.b(charSequence, i, charSequence2, 0, charSequence2.length(), z);
    }

    public static /* synthetic */ boolean b(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, charSequence2, z);
    }

    public static final boolean b(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, Constants.Name.SUFFIX);
        if (!z && (charSequence instanceof String) && (charSequence2 instanceof String)) {
            return StringsKt.c((String) charSequence, (String) charSequence2, false, 2, (Object) null);
        }
        return StringsKt.b(charSequence, charSequence.length() - charSequence2.length(), charSequence2, 0, charSequence2.length(), z);
    }

    @NotNull
    public static /* synthetic */ String c(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.c(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String c(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "other");
        int min = Math.min(charSequence.length(), charSequence2.length());
        int i = 0;
        while (i < min && CharsKt.a(charSequence.charAt(i), charSequence2.charAt(i), z)) {
            i++;
        }
        int i2 = i - 1;
        if (StringsKt.b(charSequence, i2) || StringsKt.b(charSequence2, i2)) {
            i--;
        }
        return charSequence.subSequence(0, i).toString();
    }

    @NotNull
    public static /* synthetic */ String d(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.d(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String d(@NotNull CharSequence charSequence, @NotNull CharSequence charSequence2, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(charSequence2, "other");
        int length = charSequence.length();
        int length2 = charSequence2.length();
        int min = Math.min(length, length2);
        int i = 0;
        while (i < min && CharsKt.a(charSequence.charAt((length - i) - 1), charSequence2.charAt((length2 - i) - 1), z)) {
            i++;
        }
        if (StringsKt.b(charSequence, (length - i) - 1) || StringsKt.b(charSequence2, (length2 - i) - 1)) {
            i--;
        }
        return charSequence.subSequence(length - i, length).toString();
    }

    public static /* synthetic */ int a(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, cArr, i, z);
    }

    public static final int a(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        boolean z2;
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "chars");
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            int c = RangesKt.c(i, 0);
            int g = StringsKt.g(charSequence);
            if (c > g) {
                return -1;
            }
            while (true) {
                char charAt = charSequence.charAt(c);
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z2 = false;
                        break;
                    } else if (CharsKt.a(cArr[i2], charAt, z)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    return c;
                }
                if (c == g) {
                    return -1;
                }
                c++;
            }
        } else {
            return ((String) charSequence).indexOf(ArraysKt.i(cArr), i);
        }
    }

    public static /* synthetic */ int b(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, cArr, i, z);
    }

    public static final int b(@NotNull CharSequence charSequence, @NotNull char[] cArr, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "chars");
        if (z || cArr.length != 1 || !(charSequence instanceof String)) {
            for (int d = RangesKt.d(i, StringsKt.g(charSequence)); d >= 0; d--) {
                char charAt = charSequence.charAt(d);
                int length = cArr.length;
                boolean z2 = false;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (CharsKt.a(cArr[i2], charAt, z)) {
                        z2 = true;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z2) {
                    return d;
                }
            }
            return -1;
        }
        return ((String) charSequence).lastIndexOf(ArraysKt.i(cArr), i);
    }

    static /* synthetic */ int a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        return a(charSequence, charSequence2, i, i2, z, (i3 & 16) != 0 ? false : z2);
    }

    private static final int a(@NotNull CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        IntProgression intProgression;
        if (!z2) {
            intProgression = new IntRange(RangesKt.c(i, 0), RangesKt.d(i2, charSequence.length()));
        } else {
            intProgression = RangesKt.a(RangesKt.d(i, StringsKt.g(charSequence)), RangesKt.c(i2, 0));
        }
        if (!(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            int a2 = intProgression.a();
            int b = intProgression.b();
            int c = intProgression.c();
            if (c > 0) {
                if (a2 > b) {
                    return -1;
                }
            } else if (a2 < b) {
                return -1;
            }
            while (true) {
                if (StringsKt.b(charSequence2, 0, charSequence, a2, charSequence2.length(), z)) {
                    return a2;
                }
                if (a2 == b) {
                    return -1;
                }
                a2 += c;
            }
        } else {
            int a3 = intProgression.a();
            int b2 = intProgression.b();
            int c2 = intProgression.c();
            if (c2 > 0) {
                if (a3 > b2) {
                    return -1;
                }
            } else if (a3 < b2) {
                return -1;
            }
            while (true) {
                if (StringsKt.a((String) charSequence2, 0, (String) charSequence, a3, charSequence2.length(), z)) {
                    return a3;
                }
                if (a3 == b2) {
                    return -1;
                }
                a3 += c2;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Pair<Integer, String> b(@NotNull CharSequence charSequence, Collection<String> collection, int i, boolean z, boolean z2) {
        Object obj;
        Object obj2;
        if (z || collection.size() != 1) {
            IntProgression intRange = !z2 ? new IntRange(RangesKt.c(i, 0), charSequence.length()) : RangesKt.a(RangesKt.d(i, StringsKt.g(charSequence)), 0);
            if (charSequence instanceof String) {
                int a2 = intRange.a();
                int b = intRange.b();
                int c = intRange.c();
                if (c <= 0 ? a2 >= b : a2 <= b) {
                    while (true) {
                        Iterator it = collection.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it.next();
                            String str = (String) obj2;
                            if (StringsKt.a(str, 0, (String) charSequence, a2, str.length(), z)) {
                                break;
                            }
                        }
                        String str2 = (String) obj2;
                        if (str2 == null) {
                            if (a2 == b) {
                                break;
                            }
                            a2 += c;
                        } else {
                            return TuplesKt.a(Integer.valueOf(a2), str2);
                        }
                    }
                }
            } else {
                int a3 = intRange.a();
                int b2 = intRange.b();
                int c2 = intRange.c();
                if (c2 <= 0 ? a3 >= b2 : a3 <= b2) {
                    while (true) {
                        Iterator it2 = collection.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it2.next();
                            String str3 = (String) obj;
                            if (StringsKt.b((CharSequence) str3, 0, charSequence, a3, str3.length(), z)) {
                                break;
                            }
                        }
                        String str4 = (String) obj;
                        if (str4 == null) {
                            if (a3 == b2) {
                                break;
                            }
                            a3 += c2;
                        } else {
                            return TuplesKt.a(Integer.valueOf(a3), str4);
                        }
                    }
                }
            }
            return null;
        }
        String str5 = (String) CollectionsKt.k(collection);
        int a4 = !z2 ? StringsKt.a(charSequence, str5, i, false, 4, (Object) null) : StringsKt.b(charSequence, str5, i, false, 4, (Object) null);
        if (a4 < 0) {
            return null;
        }
        return TuplesKt.a(Integer.valueOf(a4), str5);
    }

    @Nullable
    public static /* synthetic */ Pair a(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, (Collection<String>) collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> a(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(collection, "strings");
        return b(charSequence, collection, i, z, false);
    }

    @Nullable
    public static /* synthetic */ Pair b(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, (Collection<String>) collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> b(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(collection, "strings");
        return b(charSequence, collection, i, z, true);
    }

    public static /* synthetic */ int c(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.c(charSequence, (Collection<String>) collection, i, z);
    }

    public static final int c(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Integer first;
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(collection, "strings");
        Pair<Integer, String> b = b(charSequence, collection, i, z, false);
        if (b == null || (first = b.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    public static /* synthetic */ int d(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.d(charSequence, (Collection<String>) collection, i, z);
    }

    public static final int d(@NotNull CharSequence charSequence, @NotNull Collection<String> collection, int i, boolean z) {
        Integer first;
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(collection, "strings");
        Pair<Integer, String> b = b(charSequence, collection, i, z, true);
        if (b == null || (first = b.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    public static /* synthetic */ int a(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, c, i, z);
    }

    public static final int a(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c, i);
        }
        return StringsKt.a(charSequence, new char[]{c}, i, z);
    }

    public static /* synthetic */ int a(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.a(charSequence, str, i, z);
    }

    public static final int a(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(str, EventData.b);
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(str, i);
        }
        return a(charSequence, str, i, charSequence.length(), z, false, 16, (Object) null);
    }

    public static /* synthetic */ int b(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, c, i, z);
    }

    public static final int b(@NotNull CharSequence charSequence, char c, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(c, i);
        }
        return StringsKt.b(charSequence, new char[]{c}, i, z);
    }

    public static /* synthetic */ int b(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.g(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.b(charSequence, str, i, z);
    }

    public static final int b(@NotNull CharSequence charSequence, @NotNull String str, int i, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(str, EventData.b);
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(str, i);
        }
        return a(charSequence, (CharSequence) str, i, 0, z, true);
    }

    public static /* synthetic */ boolean e(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.e(charSequence, charSequence2, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x001e A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean e(@org.jetbrains.annotations.NotNull java.lang.CharSequence r11, @org.jetbrains.annotations.NotNull java.lang.CharSequence r12, boolean r13) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r11, r0)
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.f(r12, r0)
            boolean r0 = r12 instanceof java.lang.String
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0020
            r4 = r12
            java.lang.String r4 = (java.lang.String) r4
            r5 = 0
            r7 = 2
            r8 = 0
            r3 = r11
            r6 = r13
            int r11 = kotlin.text.StringsKt.a((java.lang.CharSequence) r3, (java.lang.String) r4, (int) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)
            if (r11 < 0) goto L_0x0033
        L_0x001e:
            r1 = 1
            goto L_0x0033
        L_0x0020:
            r5 = 0
            int r6 = r11.length()
            r8 = 0
            r9 = 16
            r10 = 0
            r3 = r11
            r4 = r12
            r7 = r13
            int r11 = a(r3, r4, r5, r6, r7, r8, r9, r10)
            if (r11 < 0) goto L_0x0033
            goto L_0x001e
        L_0x0033:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.e(java.lang.CharSequence, java.lang.CharSequence, boolean):boolean");
    }

    public static /* synthetic */ boolean c(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.c(charSequence, c, z);
    }

    public static final boolean c(@NotNull CharSequence charSequence, char c, boolean z) {
        Intrinsics.f(charSequence, "receiver$0");
        return StringsKt.a(charSequence, c, 0, z, 2, (Object) null) >= 0;
    }

    @InlineOnly
    private static final boolean b(@NotNull CharSequence charSequence, Regex regex) {
        Intrinsics.f(charSequence, "receiver$0");
        return regex.containsMatchIn(charSequence);
    }

    static /* synthetic */ Sequence a(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return a(charSequence, cArr, i, z, i2);
    }

    private static final Sequence<IntRange> a(@NotNull CharSequence charSequence, char[] cArr, int i, boolean z, int i2) {
        if (i2 >= 0) {
            return new DelimitedRangesSequence(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$2(cArr, z));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + '.').toString());
    }

    static /* synthetic */ Sequence a(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return a(charSequence, strArr, i, z, i2);
    }

    private static final Sequence<IntRange> a(@NotNull CharSequence charSequence, String[] strArr, int i, boolean z, int i2) {
        if (i2 >= 0) {
            return new DelimitedRangesSequence(charSequence, i, i2, new StringsKt__StringsKt$rangesDelimitedBy$4(ArraysKt.c((T[]) strArr), z));
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2 + '.').toString());
    }

    @NotNull
    public static /* synthetic */ Sequence a(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.a(charSequence, strArr, z, i);
    }

    @NotNull
    public static final Sequence<String> a(@NotNull CharSequence charSequence, @NotNull String[] strArr, boolean z, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(strArr, "delimiters");
        return SequencesKt.u(a(charSequence, strArr, 0, z, i, 2, (Object) null), new StringsKt__StringsKt$splitToSequence$1(charSequence));
    }

    @NotNull
    public static /* synthetic */ List b(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.b(charSequence, strArr, z, i);
    }

    @NotNull
    public static final List<String> b(@NotNull CharSequence charSequence, @NotNull String[] strArr, boolean z, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(strArr, "delimiters");
        if (strArr.length == 1) {
            boolean z2 = false;
            String str = strArr[0];
            if (str.length() == 0) {
                z2 = true;
            }
            if (!z2) {
                return a(charSequence, str, z, i);
            }
        }
        Iterable<IntRange> G = SequencesKt.G(a(charSequence, strArr, 0, z, i, 2, (Object) null));
        Collection arrayList = new ArrayList(CollectionsKt.a(G, 10));
        for (IntRange b : G) {
            arrayList.add(StringsKt.b(charSequence, b));
        }
        return (List) arrayList;
    }

    @NotNull
    public static /* synthetic */ Sequence a(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.a(charSequence, cArr, z, i);
    }

    @NotNull
    public static final Sequence<String> a(@NotNull CharSequence charSequence, @NotNull char[] cArr, boolean z, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "delimiters");
        return SequencesKt.u(a(charSequence, cArr, 0, z, i, 2, (Object) null), new StringsKt__StringsKt$splitToSequence$2(charSequence));
    }

    @NotNull
    public static /* synthetic */ List b(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.b(charSequence, cArr, z, i);
    }

    @NotNull
    public static final List<String> b(@NotNull CharSequence charSequence, @NotNull char[] cArr, boolean z, int i) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "delimiters");
        if (cArr.length == 1) {
            return a(charSequence, String.valueOf(cArr[0]), z, i);
        }
        Iterable<IntRange> G = SequencesKt.G(a(charSequence, cArr, 0, z, i, 2, (Object) null));
        Collection arrayList = new ArrayList(CollectionsKt.a(G, 10));
        for (IntRange b : G) {
            arrayList.add(StringsKt.b(charSequence, b));
        }
        return (List) arrayList;
    }

    private static final List<String> a(@NotNull CharSequence charSequence, String str, boolean z, int i) {
        int i2 = 0;
        if (i >= 0) {
            int a2 = StringsKt.a(charSequence, str, 0, z);
            if (a2 == -1 || i == 1) {
                return CollectionsKt.a(charSequence.toString());
            }
            boolean z2 = i > 0;
            int i3 = 10;
            if (z2) {
                i3 = RangesKt.d(i, 10);
            }
            ArrayList arrayList = new ArrayList(i3);
            do {
                arrayList.add(charSequence.subSequence(i2, a2).toString());
                i2 = str.length() + a2;
                if ((z2 && arrayList.size() == i - 1) || (a2 = StringsKt.a(charSequence, str, i2, z)) == -1) {
                    arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
                }
                arrayList.add(charSequence.subSequence(i2, a2).toString());
                i2 = str.length() + a2;
                break;
            } while ((a2 = StringsKt.a(charSequence, str, i2, z)) == -1);
            arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
            return arrayList;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + '.').toString());
    }

    @InlineOnly
    private static final List<String> a(@NotNull CharSequence charSequence, Regex regex, int i) {
        return regex.split(charSequence, i);
    }

    @NotNull
    public static final Sequence<String> h(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return StringsKt.a(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, (Object) null);
    }

    @NotNull
    public static final List<String> i(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        return SequencesKt.p(StringsKt.h(charSequence));
    }

    @NotNull
    public static final CharSequence a(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "chars");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean b = ArraysKt.b(cArr, charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!b) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!b) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final String a(@NotNull String str, @NotNull char... cArr) {
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(cArr, "chars");
        CharSequence charSequence = str;
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean b = ArraysKt.b(cArr, charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!b) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!b) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1).toString();
    }

    @NotNull
    public static final CharSequence b(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "chars");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!ArraysKt.b(cArr, charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String b(@NotNull String str, @NotNull char... cArr) {
        CharSequence charSequence;
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(cArr, "chars");
        CharSequence charSequence2 = str;
        int length = charSequence2.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (!ArraysKt.b(cArr, charSequence2.charAt(i))) {
                charSequence = charSequence2.subSequence(i, charSequence2.length());
                break;
            } else {
                i++;
            }
        }
        return charSequence.toString();
    }

    @NotNull
    public static final CharSequence c(@NotNull CharSequence charSequence, @NotNull char... cArr) {
        Intrinsics.f(charSequence, "receiver$0");
        Intrinsics.f(cArr, "chars");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (ArraysKt.b(cArr, charSequence.charAt(length)));
        return charSequence.subSequence(0, length + 1);
    }

    @NotNull
    public static final String c(@NotNull String str, @NotNull char... cArr) {
        CharSequence charSequence;
        Intrinsics.f(str, "receiver$0");
        Intrinsics.f(cArr, "chars");
        CharSequence charSequence2 = str;
        int length = charSequence2.length();
        while (true) {
            length--;
            if (length >= 0) {
                if (!ArraysKt.b(cArr, charSequence2.charAt(length))) {
                    charSequence = charSequence2.subSequence(0, length + 1);
                    break;
                }
            } else {
                break;
            }
        }
        return charSequence.toString();
    }

    @NotNull
    public static final CharSequence b(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean a2 = CharsKt.a(charSequence.charAt(!z ? i : length));
            if (!z) {
                if (!a2) {
                    z = true;
                } else {
                    i++;
                }
            } else if (!a2) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    @NotNull
    public static final CharSequence c(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt.a(charSequence.charAt(i))) {
                return charSequence.subSequence(i, charSequence.length());
            }
        }
        return "";
    }

    @NotNull
    public static final CharSequence d(@NotNull CharSequence charSequence) {
        Intrinsics.f(charSequence, "receiver$0");
        int length = charSequence.length();
        do {
            length--;
            if (length < 0) {
                return "";
            }
        } while (CharsKt.a(charSequence.charAt(length)));
        return charSequence.subSequence(0, length + 1);
    }

    @InlineOnly
    static /* synthetic */ List a(CharSequence charSequence, Regex regex, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return regex.split(charSequence, i);
    }
}
