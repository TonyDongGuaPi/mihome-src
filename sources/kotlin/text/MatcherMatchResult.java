package kotlin.text;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.MatchResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lkotlin/text/MatcherMatchResult;", "Lkotlin/text/MatchResult;", "matcher", "Ljava/util/regex/Matcher;", "input", "", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)V", "groupValues", "", "", "getGroupValues", "()Ljava/util/List;", "groupValues_", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "matchResult", "Ljava/util/regex/MatchResult;", "kotlin.jvm.PlatformType", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", "value", "getValue", "()Ljava/lang/String;", "next", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
final class MatcherMatchResult implements MatchResult {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final MatchResult f2905a = this.d.toMatchResult();
    @NotNull
    private final MatchGroupCollection b = new MatcherMatchResult$groups$1(this);
    private List<String> c;
    private final Matcher d;
    private final CharSequence e;

    public MatcherMatchResult(@NotNull Matcher matcher, @NotNull CharSequence charSequence) {
        Intrinsics.f(matcher, "matcher");
        Intrinsics.f(charSequence, "input");
        this.d = matcher;
        this.e = charSequence;
    }

    @NotNull
    public MatchResult.Destructured e() {
        return MatchResult.DefaultImpls.a(this);
    }

    @NotNull
    public IntRange a() {
        java.util.regex.MatchResult matchResult = this.f2905a;
        Intrinsics.b(matchResult, "matchResult");
        return RegexKt.b(matchResult);
    }

    @NotNull
    public String b() {
        String group = this.f2905a.group();
        Intrinsics.b(group, "matchResult.group()");
        return group;
    }

    @NotNull
    public MatchGroupCollection c() {
        return this.b;
    }

    @NotNull
    public List<String> d() {
        if (this.c == null) {
            this.c = new MatcherMatchResult$groupValues$1(this);
        }
        List<String> list = this.c;
        if (list == null) {
            Intrinsics.a();
        }
        return list;
    }

    @Nullable
    public MatchResult f() {
        int end = this.f2905a.end() + (this.f2905a.end() == this.f2905a.start() ? 1 : 0);
        if (end <= this.e.length()) {
            return RegexKt.b(this.d, end, this.e);
        }
        return null;
    }
}