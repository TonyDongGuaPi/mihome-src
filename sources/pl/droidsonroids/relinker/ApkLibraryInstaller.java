package pl.droidsonroids.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import pl.droidsonroids.relinker.ReLinker;

public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11979a = 5;
    private static final int b = 4096;

    private String[] a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[(applicationInfo.splitSourceDirs.length + 1)];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    private static class ZipFileInZipEntry {

        /* renamed from: a  reason: collision with root package name */
        public ZipFile f11980a;
        public ZipEntry b;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.f11980a = zipFile;
            this.b = zipEntry;
        }
    }

    private ZipFileInZipEntry a(Context context, String[] strArr, String str, ReLinkerInstance reLinkerInstance) {
        int i;
        String[] strArr2 = strArr;
        String[] a2 = a(context);
        int length = a2.length;
        char c = 0;
        ZipFile zipFile = null;
        int i2 = 0;
        while (i2 < length) {
            String str2 = a2[i2];
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                i = 5;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile == null) {
                String str3 = str;
                ReLinkerInstance reLinkerInstance2 = reLinkerInstance;
            } else {
                int i5 = 0;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 < i) {
                        int length2 = strArr2.length;
                        int i7 = 0;
                        while (i7 < length2) {
                            String str4 = ShareConstants.o + File.separatorChar + strArr2[i7] + File.separatorChar + str;
                            Object[] objArr = new Object[2];
                            objArr[c] = str4;
                            objArr[1] = str2;
                            reLinkerInstance.a("Looking for %s in APK %s...", objArr);
                            ZipEntry entry = zipFile.getEntry(str4);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                            i7++;
                            c = 0;
                            i = 5;
                        }
                        String str5 = str;
                        ReLinkerInstance reLinkerInstance3 = reLinkerInstance;
                        i5 = i6;
                        c = 0;
                    } else {
                        String str6 = str;
                        ReLinkerInstance reLinkerInstance4 = reLinkerInstance;
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i2++;
            c = 0;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:69:0x00a5 A[SYNTHETIC, Splitter:B:69:0x00a5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r11, java.lang.String[] r12, java.lang.String r13, java.io.File r14, pl.droidsonroids.relinker.ReLinkerInstance r15) {
        /*
            r10 = this;
            r0 = 0
            pl.droidsonroids.relinker.ApkLibraryInstaller$ZipFileInZipEntry r11 = r10.a(r11, r12, r13, r15)     // Catch:{ all -> 0x00a1 }
            if (r11 == 0) goto L_0x0099
            r12 = 0
            r1 = 0
        L_0x0009:
            int r2 = r1 + 1
            r3 = 5
            if (r1 >= r3) goto L_0x0088
            java.lang.String r1 = "Found %s! Extracting..."
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x009f }
            r4[r12] = r13     // Catch:{ all -> 0x009f }
            r15.a((java.lang.String) r1, (java.lang.Object[]) r4)     // Catch:{ all -> 0x009f }
            boolean r1 = r14.exists()     // Catch:{ IOException -> 0x0086 }
            if (r1 != 0) goto L_0x0026
            boolean r1 = r14.createNewFile()     // Catch:{ IOException -> 0x0086 }
            if (r1 != 0) goto L_0x0026
            goto L_0x0086
        L_0x0026:
            java.util.zip.ZipFile r1 = r11.f11980a     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x007a, all -> 0x0071 }
            java.util.zip.ZipEntry r4 = r11.b     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x007a, all -> 0x0071 }
            java.io.InputStream r1 = r1.getInputStream(r4)     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x007a, all -> 0x0071 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006d, all -> 0x006b }
            r4.<init>(r14)     // Catch:{ FileNotFoundException -> 0x006f, IOException -> 0x006d, all -> 0x006b }
            long r5 = r10.a(r1, r4)     // Catch:{ FileNotFoundException -> 0x0082, IOException -> 0x007c, all -> 0x0068 }
            java.io.FileDescriptor r7 = r4.getFD()     // Catch:{ FileNotFoundException -> 0x0082, IOException -> 0x007c, all -> 0x0068 }
            r7.sync()     // Catch:{ FileNotFoundException -> 0x0082, IOException -> 0x007c, all -> 0x0068 }
            long r7 = r14.length()     // Catch:{ FileNotFoundException -> 0x0082, IOException -> 0x007c, all -> 0x0068 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x004d
            r10.a((java.io.Closeable) r1)     // Catch:{ all -> 0x009f }
        L_0x0049:
            r10.a((java.io.Closeable) r4)     // Catch:{ all -> 0x009f }
            goto L_0x0086
        L_0x004d:
            r10.a((java.io.Closeable) r1)     // Catch:{ all -> 0x009f }
            r10.a((java.io.Closeable) r4)     // Catch:{ all -> 0x009f }
            r14.setReadable(r3, r12)     // Catch:{ all -> 0x009f }
            r14.setExecutable(r3, r12)     // Catch:{ all -> 0x009f }
            r14.setWritable(r3)     // Catch:{ all -> 0x009f }
            if (r11 == 0) goto L_0x0067
            java.util.zip.ZipFile r12 = r11.f11980a     // Catch:{ IOException -> 0x0067 }
            if (r12 == 0) goto L_0x0067
            java.util.zip.ZipFile r11 = r11.f11980a     // Catch:{ IOException -> 0x0067 }
            r11.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0067:
            return
        L_0x0068:
            r12 = move-exception
            r0 = r4
            goto L_0x0073
        L_0x006b:
            r12 = move-exception
            goto L_0x0073
        L_0x006d:
            r4 = r0
            goto L_0x007c
        L_0x006f:
            r4 = r0
            goto L_0x0082
        L_0x0071:
            r12 = move-exception
            r1 = r0
        L_0x0073:
            r10.a((java.io.Closeable) r1)     // Catch:{ all -> 0x009f }
            r10.a((java.io.Closeable) r0)     // Catch:{ all -> 0x009f }
            throw r12     // Catch:{ all -> 0x009f }
        L_0x007a:
            r1 = r0
            r4 = r1
        L_0x007c:
            r10.a((java.io.Closeable) r1)     // Catch:{ all -> 0x009f }
            goto L_0x0049
        L_0x0080:
            r1 = r0
            r4 = r1
        L_0x0082:
            r10.a((java.io.Closeable) r1)     // Catch:{ all -> 0x009f }
            goto L_0x0049
        L_0x0086:
            r1 = r2
            goto L_0x0009
        L_0x0088:
            java.lang.String r12 = "FATAL! Couldn't extract the library from the APK!"
            r15.a((java.lang.String) r12)     // Catch:{ all -> 0x009f }
            if (r11 == 0) goto L_0x0098
            java.util.zip.ZipFile r12 = r11.f11980a     // Catch:{ IOException -> 0x0098 }
            if (r12 == 0) goto L_0x0098
            java.util.zip.ZipFile r11 = r11.f11980a     // Catch:{ IOException -> 0x0098 }
            r11.close()     // Catch:{ IOException -> 0x0098 }
        L_0x0098:
            return
        L_0x0099:
            pl.droidsonroids.relinker.MissingLibraryException r12 = new pl.droidsonroids.relinker.MissingLibraryException     // Catch:{ all -> 0x009f }
            r12.<init>(r13)     // Catch:{ all -> 0x009f }
            throw r12     // Catch:{ all -> 0x009f }
        L_0x009f:
            r12 = move-exception
            goto L_0x00a3
        L_0x00a1:
            r12 = move-exception
            r11 = r0
        L_0x00a3:
            if (r11 == 0) goto L_0x00ae
            java.util.zip.ZipFile r13 = r11.f11980a     // Catch:{ IOException -> 0x00ae }
            if (r13 == 0) goto L_0x00ae
            java.util.zip.ZipFile r11 = r11.f11980a     // Catch:{ IOException -> 0x00ae }
            r11.close()     // Catch:{ IOException -> 0x00ae }
        L_0x00ae:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.droidsonroids.relinker.ApkLibraryInstaller.a(android.content.Context, java.lang.String[], java.lang.String, java.io.File, pl.droidsonroids.relinker.ReLinkerInstance):void");
    }

    private long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
