package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.cybergarage.upnp.RootDescription;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0002J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\rJ\u001a\u0010\n\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
public final class FileTreeWalk implements Sequence<File> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final File f2791a;
    /* access modifiers changed from: private */
    public final FileWalkDirection b;
    /* access modifiers changed from: private */
    public final Function1<File, Boolean> c;
    /* access modifiers changed from: private */
    public final Function1<File, Unit> d;
    /* access modifiers changed from: private */
    public final Function2<File, IOException, Unit> e;
    /* access modifiers changed from: private */
    public final int f;

    private FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1<? super File, Boolean> function1, Function1<? super File, Unit> function12, Function2<? super File, ? super IOException, Unit> function2, int i) {
        this.f2791a = file;
        this.b = fileWalkDirection;
        this.c = function1;
        this.d = function12;
        this.e = function2;
        this.f = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1 function1, Function1 function12, Function2 function2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i2 & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection, function1, function12, function2, (i2 & 32) != 0 ? Integer.MAX_VALUE : i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FileTreeWalk(@NotNull File file, @NotNull FileWalkDirection fileWalkDirection) {
        this(file, fileWalkDirection, (Function1) null, (Function1) null, (Function2) null, 0, 32, (DefaultConstructorMarker) null);
        Intrinsics.f(file, "start");
        Intrinsics.f(fileWalkDirection, "direction");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection);
    }

    @NotNull
    public Iterator<File> a() {
        return new FileTreeWalkIterator();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private static abstract class WalkState {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        private final File f2797a;

        @Nullable
        public abstract File a();

        public WalkState(@NotNull File file) {
            Intrinsics.f(file, RootDescription.ROOT_ELEMENT);
            this.f2797a = file;
        }

        @NotNull
        public final File b() {
            return this.f2797a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private static abstract class DirectoryState extends WalkState {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DirectoryState(@NotNull File file) {
            super(file);
            Intrinsics.f(file, "rootDir");
            if (_Assertions.f2694a) {
                boolean isDirectory = file.isDirectory();
                if (_Assertions.f2694a && !isDirectory) {
                    throw new AssertionError("rootDir must be verified to be directory beforehand.");
                }
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\r\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/Stack;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
    private final class FileTreeWalkIterator extends AbstractIterator<File> {
        private final Stack<WalkState> b = new Stack<>();

        public FileTreeWalkIterator() {
            if (FileTreeWalk.this.f2791a.isDirectory()) {
                this.b.push(a(FileTreeWalk.this.f2791a));
            } else if (FileTreeWalk.this.f2791a.isFile()) {
                this.b.push(new SingleFileState(this, FileTreeWalk.this.f2791a));
            } else {
                b();
            }
        }

        /* access modifiers changed from: protected */
        public void a() {
            File c = c();
            if (c != null) {
                a(c);
            } else {
                b();
            }
        }

        private final DirectoryState a(File file) {
            switch (FileTreeWalk.this.b) {
                case TOP_DOWN:
                    return new TopDownDirectoryState(this, file);
                case BOTTOM_UP:
                    return new BottomUpDirectoryState(this, file);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        private final File c() {
            while (!this.b.empty()) {
                WalkState peek = this.b.peek();
                if (peek == null) {
                    Intrinsics.a();
                }
                WalkState walkState = peek;
                File a2 = walkState.a();
                if (a2 == null) {
                    this.b.pop();
                } else if (Intrinsics.a((Object) a2, (Object) walkState.b()) || !a2.isDirectory() || this.b.size() >= FileTreeWalk.this.f) {
                    return a2;
                } else {
                    this.b.push(a(a2));
                }
            }
            return null;
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        private final class BottomUpDirectoryState extends DirectoryState {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f2793a;
            private boolean b;
            private File[] c;
            private int d;
            private boolean e;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public BottomUpDirectoryState(FileTreeWalkIterator fileTreeWalkIterator, @NotNull File file) {
                super(file);
                Intrinsics.f(file, "rootDir");
                this.f2793a = fileTreeWalkIterator;
            }

            @Nullable
            public File a() {
                if (!this.e && this.c == null) {
                    Function1 a2 = FileTreeWalk.this.c;
                    if (a2 != null && !((Boolean) a2.invoke(b())).booleanValue()) {
                        return null;
                    }
                    this.c = b().listFiles();
                    if (this.c == null) {
                        Function2 b2 = FileTreeWalk.this.e;
                        if (b2 != null) {
                            Unit unit = (Unit) b2.invoke(b(), new AccessDeniedException(b(), (File) null, "Cannot list files in a directory", 2, (DefaultConstructorMarker) null));
                        }
                        this.e = true;
                    }
                }
                if (this.c != null) {
                    int i = this.d;
                    File[] fileArr = this.c;
                    if (fileArr == null) {
                        Intrinsics.a();
                    }
                    if (i < fileArr.length) {
                        File[] fileArr2 = this.c;
                        if (fileArr2 == null) {
                            Intrinsics.a();
                        }
                        int i2 = this.d;
                        this.d = i2 + 1;
                        return fileArr2[i2];
                    }
                }
                if (!this.b) {
                    this.b = true;
                    return b();
                }
                Function1 c2 = FileTreeWalk.this.d;
                if (c2 != null) {
                    Unit unit2 = (Unit) c2.invoke(b());
                }
                return null;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        private final class TopDownDirectoryState extends DirectoryState {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f2795a;
            private boolean b;
            private File[] c;
            private int d;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public TopDownDirectoryState(FileTreeWalkIterator fileTreeWalkIterator, @NotNull File file) {
                super(file);
                Intrinsics.f(file, "rootDir");
                this.f2795a = fileTreeWalkIterator;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:33:0x008f, code lost:
                if (r0.length == 0) goto L_0x0091;
             */
            @org.jetbrains.annotations.Nullable
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.io.File a() {
                /*
                    r10 = this;
                    boolean r0 = r10.b
                    r1 = 0
                    if (r0 != 0) goto L_0x0028
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f2795a
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = r0.c
                    if (r0 == 0) goto L_0x0020
                    java.io.File r2 = r10.b()
                    java.lang.Object r0 = r0.invoke(r2)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 != 0) goto L_0x0020
                    return r1
                L_0x0020:
                    r0 = 1
                    r10.b = r0
                    java.io.File r0 = r10.b()
                    return r0
                L_0x0028:
                    java.io.File[] r0 = r10.c
                    if (r0 == 0) goto L_0x004e
                    int r0 = r10.d
                    java.io.File[] r2 = r10.c
                    if (r2 != 0) goto L_0x0035
                    kotlin.jvm.internal.Intrinsics.a()
                L_0x0035:
                    int r2 = r2.length
                    if (r0 >= r2) goto L_0x0039
                    goto L_0x004e
                L_0x0039:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f2795a
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = r0.d
                    if (r0 == 0) goto L_0x004d
                    java.io.File r2 = r10.b()
                    java.lang.Object r0 = r0.invoke(r2)
                    kotlin.Unit r0 = (kotlin.Unit) r0
                L_0x004d:
                    return r1
                L_0x004e:
                    java.io.File[] r0 = r10.c
                    if (r0 != 0) goto L_0x00a6
                    java.io.File r0 = r10.b()
                    java.io.File[] r0 = r0.listFiles()
                    r10.c = r0
                    java.io.File[] r0 = r10.c
                    if (r0 != 0) goto L_0x0083
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f2795a
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function2 r0 = r0.e
                    if (r0 == 0) goto L_0x0083
                    java.io.File r2 = r10.b()
                    kotlin.io.AccessDeniedException r9 = new kotlin.io.AccessDeniedException
                    java.io.File r4 = r10.b()
                    r5 = 0
                    java.lang.String r6 = "Cannot list files in a directory"
                    r7 = 2
                    r8 = 0
                    r3 = r9
                    r3.<init>(r4, r5, r6, r7, r8)
                    java.lang.Object r0 = r0.invoke(r2, r9)
                    kotlin.Unit r0 = (kotlin.Unit) r0
                L_0x0083:
                    java.io.File[] r0 = r10.c
                    if (r0 == 0) goto L_0x0091
                    java.io.File[] r0 = r10.c
                    if (r0 != 0) goto L_0x008e
                    kotlin.jvm.internal.Intrinsics.a()
                L_0x008e:
                    int r0 = r0.length
                    if (r0 != 0) goto L_0x00a6
                L_0x0091:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.f2795a
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = r0.d
                    if (r0 == 0) goto L_0x00a5
                    java.io.File r2 = r10.b()
                    java.lang.Object r0 = r0.invoke(r2)
                    kotlin.Unit r0 = (kotlin.Unit) r0
                L_0x00a5:
                    return r1
                L_0x00a6:
                    java.io.File[] r0 = r10.c
                    if (r0 != 0) goto L_0x00ad
                    kotlin.jvm.internal.Intrinsics.a()
                L_0x00ad:
                    int r1 = r10.d
                    int r2 = r1 + 1
                    r10.d = r2
                    r0 = r0[r1]
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.FileTreeWalkIterator.TopDownDirectoryState.a():java.io.File");
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 13})
        private final class SingleFileState extends WalkState {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ FileTreeWalkIterator f2794a;
            private boolean b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public SingleFileState(FileTreeWalkIterator fileTreeWalkIterator, @NotNull File file) {
                super(file);
                Intrinsics.f(file, "rootFile");
                this.f2794a = fileTreeWalkIterator;
                if (_Assertions.f2694a) {
                    boolean isFile = file.isFile();
                    if (_Assertions.f2694a && !isFile) {
                        throw new AssertionError("rootFile must be verified to be file beforehand.");
                    }
                }
            }

            @Nullable
            public File a() {
                if (this.b) {
                    return null;
                }
                this.b = true;
                return b();
            }
        }
    }

    @NotNull
    public final FileTreeWalk a(@NotNull Function1<? super File, Boolean> function1) {
        Intrinsics.f(function1, "function");
        return new FileTreeWalk(this.f2791a, this.b, function1, this.d, this.e, this.f);
    }

    @NotNull
    public final FileTreeWalk b(@NotNull Function1<? super File, Unit> function1) {
        Intrinsics.f(function1, "function");
        return new FileTreeWalk(this.f2791a, this.b, this.c, function1, this.e, this.f);
    }

    @NotNull
    public final FileTreeWalk a(@NotNull Function2<? super File, ? super IOException, Unit> function2) {
        Intrinsics.f(function2, "function");
        return new FileTreeWalk(this.f2791a, this.b, this.c, this.d, function2, this.f);
    }

    @NotNull
    public final FileTreeWalk a(int i) {
        if (i > 0) {
            return new FileTreeWalk(this.f2791a, this.b, this.c, this.d, this.e, i);
        }
        throw new IllegalArgumentException("depth must be positive, but was " + i + '.');
    }
}
