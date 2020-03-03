package com.drew.tools;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.ExifThumbnailDirectory;
import com.xiaomi.jr.stats.SensorsDataManager;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProcessAllImagesInFolderUtility {

    interface FileHandler {
        void a(@NotNull File file);

        void a(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream);

        void a(@NotNull File file, @NotNull PrintStream printStream, @NotNull String str);

        void a(@NotNull File file, @NotNull Throwable th, @NotNull PrintStream printStream);

        void a(@NotNull PrintStream printStream);

        boolean b(@NotNull File file);
    }

    public static void a(String[] strArr) throws IOException, JpegProcessingException {
        ArrayList<String> arrayList = new ArrayList<>();
        FileHandler fileHandler = null;
        PrintStream printStream = System.out;
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (str.equalsIgnoreCase("--text")) {
                fileHandler = new TextFileOutputHandler();
            } else if (str.equalsIgnoreCase("--markdown")) {
                fileHandler = new MarkdownTableOutputHandler();
            } else if (str.equalsIgnoreCase("--unknown")) {
                fileHandler = new UnknownTagHandler();
            } else if (str.equalsIgnoreCase("--log-file")) {
                if (i == strArr.length - 1) {
                    a();
                    System.exit(1);
                }
                i++;
                printStream = new PrintStream(new FileOutputStream(strArr[i], false), true);
            } else {
                arrayList.add(str);
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            System.err.println("Expects one or more directories as arguments.");
            a();
            System.exit(1);
        }
        if (fileHandler == null) {
            fileHandler = new BasicFileHandler();
        }
        long nanoTime = System.nanoTime();
        for (String file : arrayList) {
            a(new File(file), fileHandler, "", printStream);
        }
        fileHandler.a(printStream);
        System.out.println(String.format("Completed in %d ms", new Object[]{Long.valueOf((System.nanoTime() - nanoTime) / 1000000)}));
        if (printStream != System.out) {
            printStream.close();
        }
    }

    private static void a() {
        System.out.println("Usage:");
        System.out.println();
        System.out.println("  java com.drew.tools.ProcessAllImagesInFolderUtility [--text|--markdown|--unknown] [--log-file <file-name>]");
    }

    private static void a(@NotNull File file, @NotNull FileHandler fileHandler, @NotNull String str, PrintStream printStream) {
        fileHandler.a(file);
        String[] list = file.list();
        if (list != null) {
            Arrays.sort(list);
            for (String str2 : list) {
                File file2 = new File(file, str2);
                if (file2.isDirectory()) {
                    if (str.length() != 0) {
                        str2 = str + "/" + str2;
                    }
                    a(file2, fileHandler, str2, printStream);
                } else if (fileHandler.b(file2)) {
                    fileHandler.a(file2, printStream, str);
                    try {
                        fileHandler.a(file2, ImageMetadataReader.a(file2), str, printStream);
                    } catch (Throwable th) {
                        fileHandler.a(file2, th, printStream);
                    }
                }
            }
        }
    }

    static abstract class FileHandlerBase implements FileHandler {

        /* renamed from: a  reason: collision with root package name */
        private final Set<String> f5271a = new HashSet(Arrays.asList(new String[]{"jpg", "jpeg", "png", "gif", "bmp", "ico", "webp", "pcx", "ai", "eps", "nef", "crw", "cr2", "orf", "arw", "raf", "srw", "x3f", "rw2", "rwl", "tif", "tiff", "psd", "dng", "3g2", "3gp", "m4v", "mov", "mp4", "pbm", "pnm", "pgm"}));
        private int b = 0;
        private int c = 0;
        private int d = 0;
        private long e = 0;

        public void a(@NotNull File file) {
        }

        FileHandlerBase() {
        }

        public boolean b(@NotNull File file) {
            String c2 = c(file);
            return c2 != null && this.f5271a.contains(c2.toLowerCase());
        }

        public void a(@NotNull File file, @NotNull PrintStream printStream, @NotNull String str) {
            this.b++;
            this.e += file.length();
        }

        public void a(@NotNull File file, @NotNull Throwable th, @NotNull PrintStream printStream) {
            this.c++;
            printStream.printf("\t[%s] %s\n", new Object[]{th.getClass().getName(), th.getMessage()});
        }

        public void a(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            if (metadata.c()) {
                printStream.print(file);
                printStream.print(10);
                for (Directory next : metadata.a()) {
                    if (next.f()) {
                        for (String str2 : next.g()) {
                            printStream.printf("\t[%s] %s\n", new Object[]{next.a(), str2});
                            this.d++;
                        }
                    }
                }
            }
        }

        public void a(@NotNull PrintStream printStream) {
            if (this.b > 0) {
                printStream.print(String.format("Processed %,d files (%,d bytes) with %,d exceptions and %,d file errors\n", new Object[]{Integer.valueOf(this.b), Long.valueOf(this.e), Integer.valueOf(this.c), Integer.valueOf(this.d)}));
            }
        }

        /* access modifiers changed from: protected */
        @Nullable
        public String c(@NotNull File file) {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf == -1 || lastIndexOf == name.length() - 1) {
                return null;
            }
            return name.substring(lastIndexOf + 1);
        }
    }

    static class TextFileOutputHandler extends FileHandlerBase {

        /* renamed from: a  reason: collision with root package name */
        private static final String f5275a = "\n";

        TextFileOutputHandler() {
        }

        public void a(@NotNull File file) {
            super.a(file);
            File file2 = new File(file + "/metadata");
            if (file2.exists()) {
                d(file2);
            }
        }

        private static void d(@NotNull File file) {
            String[] list;
            if (file.isDirectory()) {
                if (file.exists() && (list = file.list()) != null) {
                    for (String file2 : list) {
                        File file3 = new File(file2);
                        if (file3.isDirectory()) {
                            d(file3);
                        } else {
                            file3.delete();
                        }
                    }
                }
                file.delete();
                return;
            }
            throw new IllegalArgumentException("Must be a directory.");
        }

        public void a(@NotNull File file, @NotNull PrintStream printStream, @NotNull String str) {
            super.a(file, printStream, str);
            printStream.print(file.getAbsoluteFile());
            printStream.print("\n");
        }

        /* JADX WARNING: Removed duplicated region for block: B:62:0x0133 A[Catch:{ all -> 0x0148 }] */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x0062 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(@com.drew.lang.annotations.NotNull java.io.File r17, @com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r18, @com.drew.lang.annotations.NotNull java.lang.String r19, @com.drew.lang.annotations.NotNull java.io.PrintStream r20) {
            /*
                r16 = this;
                super.a(r17, r18, r19, r20)
                r1 = 0
                java.io.PrintWriter r2 = e(r17)     // Catch:{ all -> 0x014a }
                boolean r0 = r18.c()     // Catch:{ all -> 0x0148 }
                r3 = 2
                r4 = 3
                r5 = 1
                r6 = 0
                if (r0 == 0) goto L_0x005a
                java.lang.Iterable r0 = r18.a()     // Catch:{ all -> 0x0148 }
                java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0148 }
            L_0x001a:
                boolean r7 = r0.hasNext()     // Catch:{ all -> 0x0148 }
                if (r7 == 0) goto L_0x0055
                java.lang.Object r7 = r0.next()     // Catch:{ all -> 0x0148 }
                com.drew.metadata.Directory r7 = (com.drew.metadata.Directory) r7     // Catch:{ all -> 0x0148 }
                boolean r8 = r7.f()     // Catch:{ all -> 0x0148 }
                if (r8 != 0) goto L_0x002d
                goto L_0x001a
            L_0x002d:
                java.lang.Iterable r8 = r7.g()     // Catch:{ all -> 0x0148 }
                java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x0148 }
            L_0x0035:
                boolean r9 = r8.hasNext()     // Catch:{ all -> 0x0148 }
                if (r9 == 0) goto L_0x001a
                java.lang.Object r9 = r8.next()     // Catch:{ all -> 0x0148 }
                java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0148 }
                java.lang.String r10 = "[ERROR: %s] %s%s"
                java.lang.Object[] r11 = new java.lang.Object[r4]     // Catch:{ all -> 0x0148 }
                java.lang.String r12 = r7.a()     // Catch:{ all -> 0x0148 }
                r11[r6] = r12     // Catch:{ all -> 0x0148 }
                r11[r5] = r9     // Catch:{ all -> 0x0148 }
                java.lang.String r9 = "\n"
                r11[r3] = r9     // Catch:{ all -> 0x0148 }
                r2.format(r10, r11)     // Catch:{ all -> 0x0148 }
                goto L_0x0035
            L_0x0055:
                java.lang.String r0 = "\n"
                r2.write(r0)     // Catch:{ all -> 0x0148 }
            L_0x005a:
                java.lang.Iterable r0 = r18.a()     // Catch:{ all -> 0x0148 }
                java.util.Iterator r7 = r0.iterator()     // Catch:{ all -> 0x0148 }
            L_0x0062:
                boolean r0 = r7.hasNext()     // Catch:{ all -> 0x0148 }
                if (r0 == 0) goto L_0x013a
                java.lang.Object r0 = r7.next()     // Catch:{ all -> 0x0148 }
                com.drew.metadata.Directory r0 = (com.drew.metadata.Directory) r0     // Catch:{ all -> 0x0148 }
                java.lang.String r8 = r0.a()     // Catch:{ all -> 0x0148 }
                java.util.Collection r9 = r0.d()     // Catch:{ all -> 0x0148 }
                java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0148 }
            L_0x007a:
                boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0148 }
                r11 = 4
                if (r10 == 0) goto L_0x00b8
                java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0148 }
                com.drew.metadata.Tag r10 = (com.drew.metadata.Tag) r10     // Catch:{ all -> 0x0148 }
                java.lang.String r12 = r10.e()     // Catch:{ all -> 0x0148 }
                java.lang.String r13 = r10.c()     // Catch:{ all -> 0x0148 }
                if (r13 != 0) goto L_0x0093
                java.lang.String r13 = ""
            L_0x0093:
                boolean r14 = r0 instanceof com.drew.metadata.file.FileSystemDirectory     // Catch:{ all -> 0x0148 }
                if (r14 == 0) goto L_0x009f
                int r14 = r10.a()     // Catch:{ all -> 0x0148 }
                if (r14 != r4) goto L_0x009f
                java.lang.String r13 = "<omitted for regression testing as checkout dependent>"
            L_0x009f:
                java.lang.String r14 = "[%s - %s] %s = %s%s"
                r15 = 5
                java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ all -> 0x0148 }
                r15[r6] = r8     // Catch:{ all -> 0x0148 }
                java.lang.String r10 = r10.b()     // Catch:{ all -> 0x0148 }
                r15[r5] = r10     // Catch:{ all -> 0x0148 }
                r15[r3] = r12     // Catch:{ all -> 0x0148 }
                r15[r4] = r13     // Catch:{ all -> 0x0148 }
                java.lang.String r10 = "\n"
                r15[r11] = r10     // Catch:{ all -> 0x0148 }
                r2.format(r14, r15)     // Catch:{ all -> 0x0148 }
                goto L_0x007a
            L_0x00b8:
                int r8 = r0.e()     // Catch:{ all -> 0x0148 }
                if (r8 == 0) goto L_0x00c3
                java.lang.String r8 = "\n"
                r2.write(r8)     // Catch:{ all -> 0x0148 }
            L_0x00c3:
                boolean r8 = r0 instanceof com.drew.metadata.xmp.XmpDirectory     // Catch:{ all -> 0x0148 }
                if (r8 == 0) goto L_0x0062
                com.drew.metadata.xmp.XmpDirectory r0 = (com.drew.metadata.xmp.XmpDirectory) r0     // Catch:{ all -> 0x0148 }
                com.adobe.xmp.XMPMeta r0 = r0.k()     // Catch:{ all -> 0x0148 }
                com.adobe.xmp.XMPIterator r0 = r0.a()     // Catch:{ XMPException -> 0x012c }
                r8 = 0
            L_0x00d2:
                boolean r9 = r0.hasNext()     // Catch:{ XMPException -> 0x012a }
                if (r9 == 0) goto L_0x0131
                java.lang.Object r9 = r0.next()     // Catch:{ XMPException -> 0x012a }
                com.adobe.xmp.properties.XMPPropertyInfo r9 = (com.adobe.xmp.properties.XMPPropertyInfo) r9     // Catch:{ XMPException -> 0x012a }
                java.lang.String r10 = r9.a()     // Catch:{ XMPException -> 0x012a }
                java.lang.String r12 = r9.b()     // Catch:{ XMPException -> 0x012a }
                java.lang.String r9 = r9.c()     // Catch:{ XMPException -> 0x012a }
                if (r10 != 0) goto L_0x00ee
                java.lang.String r10 = ""
            L_0x00ee:
                if (r12 != 0) goto L_0x00f2
                java.lang.String r12 = ""
            L_0x00f2:
                if (r9 != 0) goto L_0x00f7
                java.lang.String r9 = ""
                goto L_0x0117
            L_0x00f7:
                int r13 = r9.length()     // Catch:{ XMPException -> 0x012a }
                r14 = 512(0x200, float:7.175E-43)
                if (r13 <= r14) goto L_0x0117
                java.lang.String r13 = "%s <truncated from %d characters>"
                java.lang.Object[] r15 = new java.lang.Object[r3]     // Catch:{ XMPException -> 0x012a }
                java.lang.String r14 = r9.substring(r6, r14)     // Catch:{ XMPException -> 0x012a }
                r15[r6] = r14     // Catch:{ XMPException -> 0x012a }
                int r9 = r9.length()     // Catch:{ XMPException -> 0x012a }
                java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ XMPException -> 0x012a }
                r15[r5] = r9     // Catch:{ XMPException -> 0x012a }
                java.lang.String r9 = java.lang.String.format(r13, r15)     // Catch:{ XMPException -> 0x012a }
            L_0x0117:
                java.lang.String r13 = "[XMPMeta - %s] %s = %s%s"
                java.lang.Object[] r14 = new java.lang.Object[r11]     // Catch:{ XMPException -> 0x012a }
                r14[r6] = r10     // Catch:{ XMPException -> 0x012a }
                r14[r5] = r12     // Catch:{ XMPException -> 0x012a }
                r14[r3] = r9     // Catch:{ XMPException -> 0x012a }
                java.lang.String r9 = "\n"
                r14[r4] = r9     // Catch:{ XMPException -> 0x012a }
                r2.format(r13, r14)     // Catch:{ XMPException -> 0x012a }
                r8 = 1
                goto L_0x00d2
            L_0x012a:
                r0 = move-exception
                goto L_0x012e
            L_0x012c:
                r0 = move-exception
                r8 = 0
            L_0x012e:
                r0.printStackTrace()     // Catch:{ all -> 0x0148 }
            L_0x0131:
                if (r8 == 0) goto L_0x0062
                java.lang.String r0 = "\n"
                r2.write(r0)     // Catch:{ all -> 0x0148 }
                goto L_0x0062
            L_0x013a:
                r3 = r18
                a((com.drew.metadata.Metadata) r3, (java.io.PrintWriter) r2, (com.drew.metadata.Directory) r1, (int) r6)     // Catch:{ all -> 0x0148 }
                java.lang.String r0 = "\n"
                r2.write(r0)     // Catch:{ all -> 0x0148 }
                a((java.io.Writer) r2)     // Catch:{ IOException -> 0x0150 }
                goto L_0x0154
            L_0x0148:
                r0 = move-exception
                goto L_0x014c
            L_0x014a:
                r0 = move-exception
                r2 = r1
            L_0x014c:
                a((java.io.Writer) r2)     // Catch:{ IOException -> 0x0150 }
                throw r0     // Catch:{ IOException -> 0x0150 }
            L_0x0150:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0154:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.TextFileOutputHandler.a(java.io.File, com.drew.metadata.Metadata, java.lang.String, java.io.PrintStream):void");
        }

        private static void a(@NotNull Metadata metadata, @NotNull PrintWriter printWriter, @Nullable Directory directory, int i) {
            for (Directory next : metadata.a()) {
                if (directory == null) {
                    if (next.i() != null) {
                    }
                } else if (!directory.equals(next.i())) {
                }
                for (int i2 = 0; i2 < i * 4; i2++) {
                    printWriter.write(32);
                }
                printWriter.write("- ");
                printWriter.write(next.a());
                printWriter.write("\n");
                a(metadata, printWriter, next, i + 1);
            }
        }

        public void a(@NotNull File file, @NotNull Throwable th, @NotNull PrintStream printStream) {
            PrintWriter printWriter;
            super.a(file, th, printStream);
            try {
                printWriter = e(file);
                try {
                    printWriter.write("EXCEPTION: " + th.getMessage() + "\n");
                    printWriter.write("\n");
                    try {
                        a((Writer) printWriter);
                    } catch (IOException e) {
                        printStream.printf("IO exception writing metadata file: %s%s", new Object[]{e.getMessage(), "\n"});
                    }
                } catch (Throwable th2) {
                    th = th2;
                    a((Writer) printWriter);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                printWriter = null;
                a((Writer) printWriter);
                throw th;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0094  */
        @com.drew.lang.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static java.io.PrintWriter e(@com.drew.lang.annotations.NotNull java.io.File r6) throws java.io.IOException {
            /*
                java.io.File r0 = new java.io.File
                java.lang.String r1 = "%s/metadata"
                r2 = 1
                java.lang.Object[] r3 = new java.lang.Object[r2]
                java.lang.String r4 = r6.getParent()
                r5 = 0
                r3[r5] = r4
                java.lang.String r1 = java.lang.String.format(r1, r3)
                r0.<init>(r1)
                boolean r1 = r0.exists()
                if (r1 != 0) goto L_0x001e
                r0.mkdir()
            L_0x001e:
                java.lang.String r0 = "%s/metadata/%s.txt"
                r1 = 2
                java.lang.Object[] r1 = new java.lang.Object[r1]
                java.lang.String r3 = r6.getParent()
                r1[r5] = r3
                java.lang.String r3 = r6.getName()
                r1[r2] = r3
                java.lang.String r0 = java.lang.String.format(r0, r1)
                java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter
                java.io.FileOutputStream r3 = new java.io.FileOutputStream
                r3.<init>(r0)
                java.lang.String r0 = "UTF-8"
                r1.<init>(r3, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r3 = "FILE: "
                r0.append(r3)
                java.lang.String r3 = r6.getName()
                r0.append(r3)
                java.lang.String r3 = "\n"
                r0.append(r3)
                java.lang.String r0 = r0.toString()
                r1.write(r0)
                r0 = 0
                java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0090 }
                java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0090 }
                r4.<init>(r6)     // Catch:{ all -> 0x0090 }
                r3.<init>(r4)     // Catch:{ all -> 0x0090 }
                com.drew.imaging.FileType r6 = com.drew.imaging.FileTypeDetector.a(r3)     // Catch:{ all -> 0x008e }
                java.lang.String r0 = "TYPE: %s\n"
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x008e }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x008e }
                java.lang.String r6 = r6.toUpperCase()     // Catch:{ all -> 0x008e }
                r2[r5] = r6     // Catch:{ all -> 0x008e }
                java.lang.String r6 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x008e }
                r1.write(r6)     // Catch:{ all -> 0x008e }
                java.lang.String r6 = "\n"
                r1.write(r6)     // Catch:{ all -> 0x008e }
                r3.close()
                java.io.PrintWriter r6 = new java.io.PrintWriter
                r6.<init>(r1)
                return r6
            L_0x008e:
                r6 = move-exception
                goto L_0x0092
            L_0x0090:
                r6 = move-exception
                r3 = r0
            L_0x0092:
                if (r3 == 0) goto L_0x0097
                r3.close()
            L_0x0097:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.TextFileOutputHandler.e(java.io.File):java.io.PrintWriter");
        }

        private static void a(@Nullable Writer writer) throws IOException {
            if (writer != null) {
                writer.write("Generated using metadata-extractor\n");
                writer.write("https://drewnoakes.com/code/exif/\n");
                writer.flush();
                writer.close();
            }
        }
    }

    static class MarkdownTableOutputHandler extends FileHandlerBase {

        /* renamed from: a  reason: collision with root package name */
        private final Map<String, String> f5272a = new HashMap();
        private final Map<String, List<Row>> b = new HashMap();

        static class Row {

            /* renamed from: a  reason: collision with root package name */
            final File f5274a;
            final Metadata b;
            @NotNull
            final String c;
            /* access modifiers changed from: private */
            @Nullable
            public String d;
            /* access modifiers changed from: private */
            @Nullable
            public String e;
            /* access modifiers changed from: private */
            @Nullable
            public String f;
            /* access modifiers changed from: private */
            @Nullable
            public String g;
            /* access modifiers changed from: private */
            @Nullable
            public String h;

            Row(@NotNull File file, @NotNull Metadata metadata, @NotNull String str) {
                boolean z;
                this.f5274a = file;
                this.b = metadata;
                this.c = str;
                ExifIFD0Directory exifIFD0Directory = (ExifIFD0Directory) metadata.b(ExifIFD0Directory.class);
                ExifSubIFDDirectory exifSubIFDDirectory = (ExifSubIFDDirectory) metadata.b(ExifSubIFDDirectory.class);
                ExifThumbnailDirectory exifThumbnailDirectory = (ExifThumbnailDirectory) metadata.b(ExifThumbnailDirectory.class);
                if (exifIFD0Directory != null) {
                    this.d = exifIFD0Directory.x(271);
                    this.e = exifIFD0Directory.x(272);
                }
                if (exifSubIFDDirectory != null) {
                    this.f = exifSubIFDDirectory.x(36864);
                    z = exifSubIFDDirectory.a((int) ExifDirectoryBase.bh);
                } else {
                    z = false;
                }
                if (exifThumbnailDirectory != null) {
                    Integer c2 = exifThumbnailDirectory.c(256);
                    Integer c3 = exifThumbnailDirectory.c(257);
                    this.g = (c2 == null || c3 == null) ? IntentIntegrator.d : String.format("Yes (%s x %s)", new Object[]{c2, c3});
                }
                Iterator<Directory> it = metadata.a().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Directory next = it.next();
                    if (next.getClass().getName().contains("Makernote")) {
                        this.h = next.a().replace("Makernote", "").trim();
                        break;
                    }
                }
                if (this.h == null) {
                    this.h = z ? "(Unknown)" : SensorsDataManager.u;
                }
            }
        }

        public MarkdownTableOutputHandler() {
            this.f5272a.put("jpeg", "jpg");
        }

        public void a(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            super.a(file, metadata, str, printStream);
            String c = c(file);
            if (c != null) {
                String lowerCase = c.toLowerCase();
                if (this.f5272a.containsKey(lowerCase)) {
                    lowerCase = this.f5272a.get(lowerCase);
                }
                List list = this.b.get(lowerCase);
                if (list == null) {
                    list = new ArrayList();
                    this.b.put(lowerCase, list);
                }
                list.add(new Row(file, metadata, str));
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x003e  */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0043 A[SYNTHETIC, Splitter:B:27:0x0043] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x004f  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0054 A[SYNTHETIC, Splitter:B:35:0x0054] */
        /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(@com.drew.lang.annotations.NotNull java.io.PrintStream r5) {
            /*
                r4 = this;
                super.a((java.io.PrintStream) r5)
                r5 = 0
                java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0037, all -> 0x0034 }
                java.lang.String r1 = "../wiki/ImageDatabaseSummary.md"
                r2 = 0
                r0.<init>(r1, r2)     // Catch:{ IOException -> 0x0037, all -> 0x0034 }
                java.io.PrintStream r1 = new java.io.PrintStream     // Catch:{ IOException -> 0x002f, all -> 0x002a }
                r1.<init>(r0, r2)     // Catch:{ IOException -> 0x002f, all -> 0x002a }
                r4.b(r1)     // Catch:{ IOException -> 0x0024, all -> 0x001e }
                r1.flush()     // Catch:{ IOException -> 0x0024, all -> 0x001e }
                r1.close()
                r0.close()     // Catch:{ IOException -> 0x0047 }
                goto L_0x004b
            L_0x001e:
                r5 = move-exception
                r3 = r0
                r0 = r5
                r5 = r1
                r1 = r3
                goto L_0x004d
            L_0x0024:
                r5 = move-exception
                r3 = r0
                r0 = r5
                r5 = r1
                r1 = r3
                goto L_0x0039
            L_0x002a:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
                goto L_0x004d
            L_0x002f:
                r1 = move-exception
                r3 = r1
                r1 = r0
                r0 = r3
                goto L_0x0039
            L_0x0034:
                r0 = move-exception
                r1 = r5
                goto L_0x004d
            L_0x0037:
                r0 = move-exception
                r1 = r5
            L_0x0039:
                r0.printStackTrace()     // Catch:{ all -> 0x004c }
                if (r5 == 0) goto L_0x0041
                r5.close()
            L_0x0041:
                if (r1 == 0) goto L_0x004b
                r1.close()     // Catch:{ IOException -> 0x0047 }
                goto L_0x004b
            L_0x0047:
                r5 = move-exception
                r5.printStackTrace()
            L_0x004b:
                return
            L_0x004c:
                r0 = move-exception
            L_0x004d:
                if (r5 == 0) goto L_0x0052
                r5.close()
            L_0x0052:
                if (r1 == 0) goto L_0x005c
                r1.close()     // Catch:{ IOException -> 0x0058 }
                goto L_0x005c
            L_0x0058:
                r5 = move-exception
                r5.printStackTrace()
            L_0x005c:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.ProcessAllImagesInFolderUtility.MarkdownTableOutputHandler.a(java.io.PrintStream):void");
        }

        private void b(@NotNull PrintStream printStream) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(printStream);
            outputStreamWriter.write("# Image Database Summary\n\n");
            for (Map.Entry next : this.b.entrySet()) {
                outputStreamWriter.write("## " + ((String) next.getKey()).toUpperCase() + " Files\n\n");
                outputStreamWriter.write("File|Manufacturer|Model|Dir Count|Exif?|Makernote|Thumbnail|All Data\n");
                outputStreamWriter.write("----|------------|-----|---------|-----|---------|---------|--------\n");
                List<Row> list = (List) next.getValue();
                Collections.sort(list, new Comparator<Row>() {
                    /* renamed from: a */
                    public int compare(Row row, Row row2) {
                        int a2 = StringUtil.a(row.d, row2.d);
                        return a2 != 0 ? a2 : StringUtil.a(row.e, row2.e);
                    }
                });
                for (Row row : list) {
                    Object[] objArr = new Object[11];
                    objArr[0] = row.f5274a.getName();
                    objArr[1] = row.c;
                    objArr[2] = StringUtil.a(row.f5274a.getName());
                    objArr[3] = row.d == null ? "" : row.d;
                    objArr[4] = row.e == null ? "" : row.e;
                    objArr[5] = Integer.valueOf(row.b.b());
                    objArr[6] = row.f == null ? "" : row.f;
                    objArr[7] = row.h == null ? "" : row.h;
                    objArr[8] = row.g == null ? "" : row.g;
                    objArr[9] = row.c;
                    objArr[10] = StringUtil.a(row.f5274a.getName()).toLowerCase();
                    outputStreamWriter.write(String.format("[%s](https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s/%s)|%s|%s|%d|%s|%s|%s|[metadata](https://raw.githubusercontent.com/drewnoakes/metadata-extractor-images/master/%s/metadata/%s.txt)\n", objArr));
                }
                outputStreamWriter.write(10);
            }
            outputStreamWriter.flush();
        }
    }

    static class UnknownTagHandler extends FileHandlerBase {

        /* renamed from: a  reason: collision with root package name */
        private HashMap<String, HashMap<Integer, Integer>> f5276a = new HashMap<>();

        UnknownTagHandler() {
        }

        public void a(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            super.a(file, metadata, str, printStream);
            for (Directory next : metadata.a()) {
                for (Tag next2 : next.d()) {
                    if (!next2.d()) {
                        HashMap hashMap = this.f5276a.get(next.a());
                        if (hashMap == null) {
                            hashMap = new HashMap();
                            this.f5276a.put(next.a(), hashMap);
                        }
                        Integer num = (Integer) hashMap.get(Integer.valueOf(next2.a()));
                        if (num == null) {
                            hashMap.put(Integer.valueOf(next2.a()), 0);
                            num = 0;
                        }
                        hashMap.put(Integer.valueOf(next2.a()), Integer.valueOf(num.intValue() + 1));
                    }
                }
            }
        }

        public void a(@NotNull PrintStream printStream) {
            super.a(printStream);
            for (Map.Entry next : this.f5276a.entrySet()) {
                String str = (String) next.getKey();
                ArrayList<Map.Entry> arrayList = new ArrayList<>(((HashMap) next.getValue()).entrySet());
                Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Integer>>() {
                    /* renamed from: a */
                    public int compare(Map.Entry<Integer, Integer> entry, Map.Entry<Integer, Integer> entry2) {
                        return entry2.getValue().compareTo(entry.getValue());
                    }
                });
                for (Map.Entry entry : arrayList) {
                    printStream.format("%s, 0x%04X, %d\n", new Object[]{str, (Integer) entry.getKey(), (Integer) entry.getValue()});
                }
            }
        }
    }

    static class BasicFileHandler extends FileHandlerBase {
        BasicFileHandler() {
        }

        public void a(@NotNull File file, @NotNull Metadata metadata, @NotNull String str, @NotNull PrintStream printStream) {
            super.a(file, metadata, str, printStream);
            for (Directory next : metadata.a()) {
                next.a();
                for (Tag next2 : next.d()) {
                    next2.e();
                    next2.c();
                }
            }
        }
    }
}
