package kotlin.io;

import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.cybergarage.upnp.RootDescription;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\b\b\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0013HÖ\u0001J\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013J\t\u0010\u001f\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lkotlin/io/FilePathComponents;", "", "root", "Ljava/io/File;", "segments", "", "(Ljava/io/File;Ljava/util/List;)V", "isRooted", "", "()Z", "getRoot", "()Ljava/io/File;", "rootName", "", "getRootName", "()Ljava/lang/String;", "getSegments", "()Ljava/util/List;", "size", "", "getSize", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "subPath", "beginIndex", "endIndex", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class FilePathComponents {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final File f2790a;
    @NotNull
    private final List<File> b;

    @NotNull
    public static /* synthetic */ FilePathComponents a(FilePathComponents filePathComponents, File file, List<File> list, int i, Object obj) {
        if ((i & 1) != 0) {
            file = filePathComponents.f2790a;
        }
        if ((i & 2) != 0) {
            list = filePathComponents.b;
        }
        return filePathComponents.a(file, (List<? extends File>) list);
    }

    @NotNull
    public final FilePathComponents a(@NotNull File file, @NotNull List<? extends File> list) {
        Intrinsics.f(file, RootDescription.ROOT_ELEMENT);
        Intrinsics.f(list, "segments");
        return new FilePathComponents(file, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilePathComponents)) {
            return false;
        }
        FilePathComponents filePathComponents = (FilePathComponents) obj;
        return Intrinsics.a((Object) this.f2790a, (Object) filePathComponents.f2790a) && Intrinsics.a((Object) this.b, (Object) filePathComponents.b);
    }

    @NotNull
    public final File f() {
        return this.f2790a;
    }

    @NotNull
    public final List<File> g() {
        return this.b;
    }

    public int hashCode() {
        File file = this.f2790a;
        int i = 0;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        List<File> list = this.b;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "FilePathComponents(root=" + this.f2790a + ", segments=" + this.b + Operators.BRACKET_END_STR;
    }

    public FilePathComponents(@NotNull File file, @NotNull List<? extends File> list) {
        Intrinsics.f(file, RootDescription.ROOT_ELEMENT);
        Intrinsics.f(list, "segments");
        this.f2790a = file;
        this.b = list;
    }

    @NotNull
    public final File d() {
        return this.f2790a;
    }

    @NotNull
    public final List<File> e() {
        return this.b;
    }

    @NotNull
    public final String a() {
        String path = this.f2790a.getPath();
        Intrinsics.b(path, "root.path");
        return path;
    }

    public final boolean b() {
        String path = this.f2790a.getPath();
        Intrinsics.b(path, "root.path");
        return path.length() > 0;
    }

    public final int c() {
        return this.b.size();
    }

    @NotNull
    public final File a(int i, int i2) {
        if (i < 0 || i > i2 || i2 > c()) {
            throw new IllegalArgumentException();
        }
        String str = File.separator;
        Intrinsics.b(str, "File.separator");
        return new File(CollectionsKt.a(this.b.subList(i, i2), str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }
}
