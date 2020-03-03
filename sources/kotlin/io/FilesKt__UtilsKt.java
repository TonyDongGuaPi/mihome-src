package kotlin.io;

import com.facebook.react.uimanager.events.TouchesHelper;
import com.taobao.weex.common.Constants;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 13}, xi = 1, xs = "kotlin/io/FilesKt")
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    @NotNull
    public static /* synthetic */ File a(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.a(str, str2, file);
    }

    @NotNull
    public static final File a(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.f(str, Constants.Name.PREFIX);
        File createTempFile = File.createTempFile(str, str2, file);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            Intrinsics.b(createTempFile, SharePatchInfo.g);
            return createTempFile;
        }
        throw new IOException("Unable to create temporary directory " + createTempFile + '.');
    }

    @NotNull
    public static /* synthetic */ File b(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.b(str, str2, file);
    }

    @NotNull
    public static final File b(@NotNull String str, @Nullable String str2, @Nullable File file) {
        Intrinsics.f(str, Constants.Name.PREFIX);
        File createTempFile = File.createTempFile(str, str2, file);
        Intrinsics.b(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    @NotNull
    public static final String h(@NotNull File file) {
        Intrinsics.f(file, "receiver$0");
        String name = file.getName();
        Intrinsics.b(name, "name");
        return StringsKt.d(name, '.', "");
    }

    @NotNull
    public static final String i(@NotNull File file) {
        Intrinsics.f(file, "receiver$0");
        if (File.separatorChar != '/') {
            String path = file.getPath();
            Intrinsics.b(path, "path");
            return StringsKt.a(path, File.separatorChar, (char) IOUtils.f15883a, false, 4, (Object) null);
        }
        String path2 = file.getPath();
        Intrinsics.b(path2, "path");
        return path2;
    }

    @NotNull
    public static final String j(@NotNull File file) {
        Intrinsics.f(file, "receiver$0");
        String name = file.getName();
        Intrinsics.b(name, "name");
        return StringsKt.d(name, ".", (String) null, 2, (Object) null);
    }

    @NotNull
    public static final String a(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, "base");
        String i = i(file, file2);
        if (i != null) {
            return i;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + '.');
    }

    @NotNull
    public static final File b(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, "base");
        return new File(FilesKt.a(file, file2));
    }

    @NotNull
    public static final File c(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, "base");
        String i = i(file, file2);
        return i != null ? new File(i) : file;
    }

    @Nullable
    public static final File d(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, "base");
        String i = i(file, file2);
        if (i != null) {
            return new File(i);
        }
        return null;
    }

    private static final String i(@NotNull File file, File file2) {
        FilePathComponents a2 = a(FilesKt.d(file));
        FilePathComponents a3 = a(FilesKt.d(file2));
        if (!Intrinsics.a((Object) a2.d(), (Object) a3.d())) {
            return null;
        }
        int c = a3.c();
        int c2 = a2.c();
        int i = 0;
        int min = Math.min(c2, c);
        while (i < min && Intrinsics.a((Object) a2.e().get(i), (Object) a3.e().get(i))) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = c - 1;
        if (i2 >= i) {
            while (!Intrinsics.a((Object) a3.e().get(i2).getName(), (Object) "..")) {
                sb.append("..");
                if (i2 != i) {
                    sb.append(File.separatorChar);
                }
                if (i2 != i) {
                    i2--;
                }
            }
            return null;
        }
        if (i < c2) {
            if (i < c) {
                sb.append(File.separatorChar);
            }
            String str = File.separator;
            Intrinsics.b(str, "File.separator");
            CollectionsKt.a(CollectionsKt.d(a2.e(), i), sb, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 124, (Object) null);
        }
        return sb.toString();
    }

    @NotNull
    public static /* synthetic */ File a(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return FilesKt.a(file, file2, z, i);
    }

    @NotNull
    public static final File a(@NotNull File file, @NotNull File file2, boolean z, int i) {
        Closeable fileOutputStream;
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, TouchesHelper.TARGET_KEY);
        if (file.exists()) {
            if (file2.exists()) {
                boolean z2 = true;
                if (z && file2.delete()) {
                    z2 = false;
                }
                if (z2) {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                Closeable fileInputStream = new FileInputStream(file);
                Throwable th = null;
                try {
                    FileInputStream fileInputStream2 = (FileInputStream) fileInputStream;
                    fileOutputStream = new FileOutputStream(file2);
                    Throwable th2 = null;
                    ByteStreamsKt.a((InputStream) fileInputStream2, (OutputStream) (FileOutputStream) fileOutputStream, i);
                    CloseableKt.a(fileOutputStream, th2);
                    CloseableKt.a(fileInputStream, th);
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        CloseableKt.a(fileInputStream, th4);
                        throw th5;
                    }
                }
            } else if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        throw new NoSuchFileException(file, (File) null, "The source file doesn't exist.", 2, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ boolean a(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = FilesKt__UtilsKt$copyRecursively$1.INSTANCE;
        }
        return FilesKt.a(file, file2, z, (Function2<? super File, ? super IOException, ? extends OnErrorAction>) function2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a3 A[Catch:{ TerminateException -> 0x00e7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean a(@org.jetbrains.annotations.NotNull java.io.File r11, @org.jetbrains.annotations.NotNull java.io.File r12, boolean r13, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super java.io.File, ? super java.io.IOException, ? extends kotlin.io.OnErrorAction> r14) {
        /*
            java.lang.String r0 = "receiver$0"
            kotlin.jvm.internal.Intrinsics.f(r11, r0)
            java.lang.String r0 = "target"
            kotlin.jvm.internal.Intrinsics.f(r12, r0)
            java.lang.String r0 = "onError"
            kotlin.jvm.internal.Intrinsics.f(r14, r0)
            boolean r0 = r11.exists()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0030
            kotlin.io.NoSuchFileException r12 = new kotlin.io.NoSuchFileException
            r5 = 0
            java.lang.String r6 = "The source file doesn't exist."
            r7 = 2
            r8 = 0
            r3 = r12
            r4 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            java.lang.Object r11 = r14.invoke(r11, r12)
            kotlin.io.OnErrorAction r11 = (kotlin.io.OnErrorAction) r11
            kotlin.io.OnErrorAction r12 = kotlin.io.OnErrorAction.TERMINATE
            if (r11 == r12) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            return r1
        L_0x0030:
            kotlin.io.FileTreeWalk r0 = kotlin.io.FilesKt.f(r11)     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.FilesKt__UtilsKt$copyRecursively$2 r3 = new kotlin.io.FilesKt__UtilsKt$copyRecursively$2     // Catch:{ TerminateException -> 0x00e7 }
            r3.<init>(r14)     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.FileTreeWalk r0 = r0.a((kotlin.jvm.functions.Function2<? super java.io.File, ? super java.io.IOException, kotlin.Unit>) r3)     // Catch:{ TerminateException -> 0x00e7 }
            java.util.Iterator r0 = r0.a()     // Catch:{ TerminateException -> 0x00e7 }
        L_0x0043:
            boolean r3 = r0.hasNext()     // Catch:{ TerminateException -> 0x00e7 }
            if (r3 == 0) goto L_0x00e6
            java.lang.Object r3 = r0.next()     // Catch:{ TerminateException -> 0x00e7 }
            java.io.File r3 = (java.io.File) r3     // Catch:{ TerminateException -> 0x00e7 }
            boolean r4 = r3.exists()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 != 0) goto L_0x006c
            kotlin.io.NoSuchFileException r10 = new kotlin.io.NoSuchFileException     // Catch:{ TerminateException -> 0x00e7 }
            r6 = 0
            java.lang.String r7 = "The source file doesn't exist."
            r8 = 2
            r9 = 0
            r4 = r10
            r5 = r3
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ TerminateException -> 0x00e7 }
            java.lang.Object r3 = r14.invoke(r3, r10)     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.OnErrorAction r3 = (kotlin.io.OnErrorAction) r3     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.OnErrorAction r4 = kotlin.io.OnErrorAction.TERMINATE     // Catch:{ TerminateException -> 0x00e7 }
            if (r3 != r4) goto L_0x0043
            return r2
        L_0x006c:
            java.lang.String r4 = kotlin.io.FilesKt.a((java.io.File) r3, (java.io.File) r11)     // Catch:{ TerminateException -> 0x00e7 }
            java.io.File r5 = new java.io.File     // Catch:{ TerminateException -> 0x00e7 }
            r5.<init>(r12, r4)     // Catch:{ TerminateException -> 0x00e7 }
            boolean r4 = r5.exists()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 == 0) goto L_0x00b5
            boolean r4 = r3.isDirectory()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 == 0) goto L_0x0087
            boolean r4 = r5.isDirectory()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 != 0) goto L_0x00b5
        L_0x0087:
            if (r13 != 0) goto L_0x008b
        L_0x0089:
            r4 = 1
            goto L_0x00a1
        L_0x008b:
            boolean r4 = r5.isDirectory()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 == 0) goto L_0x009a
            boolean r4 = kotlin.io.FilesKt.k(r5)     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 != 0) goto L_0x0098
            goto L_0x0089
        L_0x0098:
            r4 = 0
            goto L_0x00a1
        L_0x009a:
            boolean r4 = r5.delete()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 != 0) goto L_0x0098
            goto L_0x0089
        L_0x00a1:
            if (r4 == 0) goto L_0x00b5
            kotlin.io.FileAlreadyExistsException r4 = new kotlin.io.FileAlreadyExistsException     // Catch:{ TerminateException -> 0x00e7 }
            java.lang.String r6 = "The destination file already exists."
            r4.<init>(r3, r5, r6)     // Catch:{ TerminateException -> 0x00e7 }
            java.lang.Object r3 = r14.invoke(r5, r4)     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.OnErrorAction r3 = (kotlin.io.OnErrorAction) r3     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.OnErrorAction r4 = kotlin.io.OnErrorAction.TERMINATE     // Catch:{ TerminateException -> 0x00e7 }
            if (r3 != r4) goto L_0x0043
            return r2
        L_0x00b5:
            boolean r4 = r3.isDirectory()     // Catch:{ TerminateException -> 0x00e7 }
            if (r4 == 0) goto L_0x00bf
            r5.mkdirs()     // Catch:{ TerminateException -> 0x00e7 }
            goto L_0x0043
        L_0x00bf:
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r3
            r6 = r13
            java.io.File r4 = kotlin.io.FilesKt.a((java.io.File) r4, (java.io.File) r5, (boolean) r6, (int) r7, (int) r8, (java.lang.Object) r9)     // Catch:{ TerminateException -> 0x00e7 }
            long r4 = r4.length()     // Catch:{ TerminateException -> 0x00e7 }
            long r6 = r3.length()     // Catch:{ TerminateException -> 0x00e7 }
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0043
            java.io.IOException r4 = new java.io.IOException     // Catch:{ TerminateException -> 0x00e7 }
            java.lang.String r5 = "Source file wasn't copied completely, length of destination file differs."
            r4.<init>(r5)     // Catch:{ TerminateException -> 0x00e7 }
            java.lang.Object r3 = r14.invoke(r3, r4)     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.OnErrorAction r3 = (kotlin.io.OnErrorAction) r3     // Catch:{ TerminateException -> 0x00e7 }
            kotlin.io.OnErrorAction r4 = kotlin.io.OnErrorAction.TERMINATE     // Catch:{ TerminateException -> 0x00e7 }
            if (r3 != r4) goto L_0x0043
            return r2
        L_0x00e6:
            return r1
        L_0x00e7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.a(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2):boolean");
    }

    public static final boolean k(@NotNull File file) {
        Intrinsics.f(file, "receiver$0");
        Iterator a2 = FilesKt.g(file).a();
        while (true) {
            boolean z = true;
            while (true) {
                if (!a2.hasNext()) {
                    return z;
                }
                File file2 = (File) a2.next();
                if (file2.delete() || !file2.exists()) {
                    if (z) {
                    }
                }
                z = false;
            }
        }
    }

    public static final boolean e(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, "other");
        FilePathComponents d = FilesKt.d(file);
        FilePathComponents d2 = FilesKt.d(file2);
        if (!(!Intrinsics.a((Object) d.d(), (Object) d2.d())) && d.c() >= d2.c()) {
            return d.e().subList(0, d2.c()).equals(d2.e());
        }
        return false;
    }

    public static final boolean a(@NotNull File file, @NotNull String str) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(str, "other");
        return FilesKt.e(file, new File(str));
    }

    public static final boolean f(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, "other");
        FilePathComponents d = FilesKt.d(file);
        FilePathComponents d2 = FilesKt.d(file2);
        if (d2.b()) {
            return Intrinsics.a((Object) file, (Object) file2);
        }
        int c = d.c() - d2.c();
        if (c < 0) {
            return false;
        }
        return d.e().subList(c, d.c()).equals(d2.e());
    }

    public static final boolean b(@NotNull File file, @NotNull String str) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(str, "other");
        return FilesKt.f(file, new File(str));
    }

    @NotNull
    public static final File l(@NotNull File file) {
        Intrinsics.f(file, "receiver$0");
        FilePathComponents d = FilesKt.d(file);
        File d2 = d.d();
        String str = File.separator;
        Intrinsics.b(str, "File.separator");
        return FilesKt.c(d2, CollectionsKt.a(a((List<? extends File>) d.e()), str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    private static final FilePathComponents a(@NotNull FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.d(), a((List<? extends File>) filePathComponents.e()));
    }

    private static final List<File> a(@NotNull List<? extends File> list) {
        List<File> arrayList = new ArrayList<>(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472 && name.equals("..")) {
                        if (arrayList.isEmpty() || !(!Intrinsics.a((Object) ((File) CollectionsKt.i(arrayList)).getName(), (Object) ".."))) {
                            arrayList.add(file);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                } else if (name.equals(".")) {
                }
            }
            arrayList.add(file);
        }
        return arrayList;
    }

    @NotNull
    public static final File g(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, com.facebook.appevents.codeless.internal.Constants.PATH_TYPE_RELATIVE);
        if (FilesKt.c(file2)) {
            return file2;
        }
        String file3 = file.toString();
        Intrinsics.b(file3, "this.toString()");
        CharSequence charSequence = file3;
        if ((charSequence.length() == 0) || StringsKt.b(charSequence, File.separatorChar, false, 2, (Object) null)) {
            return new File(file3 + file2);
        }
        return new File(file3 + File.separatorChar + file2);
    }

    @NotNull
    public static final File c(@NotNull File file, @NotNull String str) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(str, com.facebook.appevents.codeless.internal.Constants.PATH_TYPE_RELATIVE);
        return FilesKt.g(file, new File(str));
    }

    @NotNull
    public static final File h(@NotNull File file, @NotNull File file2) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(file2, com.facebook.appevents.codeless.internal.Constants.PATH_TYPE_RELATIVE);
        FilePathComponents d = FilesKt.d(file);
        return FilesKt.g(FilesKt.g(d.d(), d.c() == 0 ? new File("..") : d.a(0, d.c() - 1)), file2);
    }

    @NotNull
    public static final File d(@NotNull File file, @NotNull String str) {
        Intrinsics.f(file, "receiver$0");
        Intrinsics.f(str, com.facebook.appevents.codeless.internal.Constants.PATH_TYPE_RELATIVE);
        return FilesKt.h(file, new File(str));
    }
}
