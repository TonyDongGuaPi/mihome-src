package com.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import android.view.View;
import com.google.android.exoplayer2.C;
import com.mijia.model.band.CameraBand;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.regex.Pattern;
import org.apache.http.conn.ConnectTimeoutException;

public class Util {

    /* renamed from: a  reason: collision with root package name */
    private static final String f671a = "MI";

    public static void a(Object obj, StringBuilder sb) {
        String str;
        boolean z = obj instanceof Class;
        Class<?> cls = z ? (Class) obj : obj.getClass();
        if (z) {
            obj = null;
        }
        for (Field field : cls.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                sb.append(field.getName());
                sb.append("=");
                Object obj2 = field.get(obj);
                if (obj2 == null) {
                    str = "null";
                } else {
                    str = obj2.toString();
                }
                sb.append(str);
                sb.append("\n");
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "WTF: Excpetion is null!!!";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        if (th instanceof ConnectException) {
            printStream.println("  " + th.getMessage());
            if (th.getCause() != null) {
                printStream.println("  " + th.getCause().getMessage());
            }
        } else if ((th instanceof SocketException) || (th instanceof SocketTimeoutException) || (th instanceof ConnectTimeoutException) || (th instanceof HttpRetryException) || (th instanceof UnknownHostException)) {
            if (th.getMessage() != null) {
                printStream.print("  " + th.getMessage());
            } else {
                th.printStackTrace(printStream);
            }
            printStream.println("");
        } else {
            th.printStackTrace(printStream);
            printStream.println("");
        }
        printStream.close();
        return byteArrayOutputStream.toString();
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern compile = Pattern.compile("[._]+");
        String[] split = compile.split(str);
        String[] split2 = compile.split(str2);
        int min = Math.min(split.length, split2.length);
        for (int i = 0; i < min; i++) {
            if (split[i].compareTo(split2[i]) > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context) {
        Method method;
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(context);
        }
        try {
            Object systemService = context.getApplicationContext().getSystemService("appops");
            if (systemService == null || (method = systemService.getClass().getMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class})) == null) {
                return false;
            }
            if (((Integer) method.invoke(systemService, new Object[]{24, Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue() == 0) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String a(long j) {
        if (j <= 0) {
            return "0 KB";
        }
        if (j < 1024) {
            return "1 KB";
        }
        if (j < 1048576) {
            DecimalFormat decimalFormat = new DecimalFormat("##0.0");
            StringBuilder sb = new StringBuilder();
            double d = (double) j;
            Double.isNaN(d);
            sb.append(decimalFormat.format(FileUtil.a(d / 1024.0d)));
            sb.append(" KB");
            return sb.toString();
        }
        DecimalFormat decimalFormat2 = new DecimalFormat("##0.0");
        StringBuilder sb2 = new StringBuilder();
        double d2 = (double) j;
        Double.isNaN(d2);
        sb2.append(decimalFormat2.format(FileUtil.a(d2 / 1048576.0d)));
        sb2.append(" MB");
        return sb2.toString();
    }

    public static boolean a(int i, View view) {
        if (view == null || view.getVisibility() == i) {
            return false;
        }
        view.setVisibility(i);
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: org.mp4parser.muxer.FileRandomAccessSourceImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: org.mp4parser.muxer.FileRandomAccessSourceImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: org.mp4parser.muxer.FileRandomAccessSourceImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: org.mp4parser.muxer.FileRandomAccessSourceImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v22, resolved type: org.mp4parser.muxer.FileRandomAccessSourceImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: org.mp4parser.muxer.FileRandomAccessSourceImpl} */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.io.Closeable, org.mp4parser.muxer.tracks.AACTrackImpl, org.mp4parser.muxer.Track] */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x016d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x016e, code lost:
        r4 = r6;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0172, code lost:
        r4 = r6;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01aa A[Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01c2 A[Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01d7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:10:0x0063] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r16, java.io.File r17, java.io.File r18) {
        /*
            r0 = r16
            r1 = r17
            boolean r2 = r17.exists()
            r3 = 0
            if (r2 == 0) goto L_0x023d
            long r4 = r17.length()
            r6 = 100
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 > 0) goto L_0x0017
            goto L_0x023d
        L_0x0017:
            r2 = 1
            r4 = 0
            java.lang.String r5 = "g711"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            r6.<init>()     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            java.lang.String r7 = "start build "
            r6.append(r7)     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            r6.append(r0)     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            java.lang.String r6 = r6.toString()     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            com.mijia.debug.SDKLog.e(r5, r6)     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            r5.<init>(r1)     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            org.mp4parser.muxer.FileRandomAccessSourceImpl r6 = new org.mp4parser.muxer.FileRandomAccessSourceImpl     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            java.lang.String r8 = "r"
            r7.<init>(r1, r8)     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            r6.<init>(r7)     // Catch:{ OutOfMemoryError -> 0x020b, Exception -> 0x01ed, all -> 0x01e8 }
            java.nio.channels.FileChannel r7 = r5.getChannel()     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            java.lang.String r8 = r17.getName()     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            org.mp4parser.muxer.Movie r7 = org.mp4parser.muxer.container.mp4.MovieCreator.a(r7, r6, r8)     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            r5.close()     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            java.util.List r5 = r7.a()     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            java.lang.String r7 = "g711"
            java.lang.String r8 = "start check "
            com.mijia.debug.SDKLog.e(r7, r8)     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ OutOfMemoryError -> 0x01e4, Exception -> 0x01df, all -> 0x01dc }
            r8 = r4
            r9 = r8
            r7 = 0
        L_0x0063:
            boolean r10 = r5.hasNext()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r10 == 0) goto L_0x009b
            java.lang.Object r10 = r5.next()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            org.mp4parser.muxer.Track r10 = (org.mp4parser.muxer.Track) r10     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.String r11 = r10.p()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.String r12 = "soun"
            boolean r11 = r11.equals(r12)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r11 == 0) goto L_0x0099
            org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox r11 = r10.n()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.util.List r11 = r11.a()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.Object r11 = r11.get(r3)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            org.mp4parser.Box r11 = (org.mp4parser.Box) r11     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.String r11 = r11.ae_()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.String r12 = "alaw"
            boolean r11 = r11.equals(r12)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r11 == 0) goto L_0x0097
            r7 = 1
        L_0x0097:
            r9 = r10
            goto L_0x0063
        L_0x0099:
            r8 = r10
            goto L_0x0063
        L_0x009b:
            if (r7 == 0) goto L_0x01cc
            if (r8 == 0) goto L_0x01cc
            java.lang.String r5 = "g711"
            java.lang.String r7 = "start change "
            com.mijia.debug.SDKLog.e(r5, r7)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r5.<init>()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r5.append(r0)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.String r0 = ".aac"
            r5.append(r0)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.String r5 = r5.toString()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r0 = 10240(0x2800, float:1.4349E-41)
            byte[] r0 = new byte[r0]     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            com.xiaomi.aaccodec.AACEncodeEx r7 = new com.xiaomi.aaccodec.AACEncodeEx     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r7.<init>()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r10 = 8000(0x1f40, float:1.121E-41)
            r11 = 16000(0x3e80, float:2.2421E-41)
            r7.initial(r10, r2, r11)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.io.BufferedOutputStream r10 = new java.io.BufferedOutputStream     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r11.<init>(r5)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r10.<init>(r11)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.util.List r11 = r9.l()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r12 = 2048(0x800, float:2.87E-42)
            byte[] r12 = new byte[r12]     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            com.xiaomi.smarthome.audioprocess.ByteDataBuffer r13 = new com.xiaomi.smarthome.audioprocess.ByteDataBuffer     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r13.<init>()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
        L_0x00e3:
            boolean r14 = r11.hasNext()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r14 == 0) goto L_0x011c
            java.lang.Object r14 = r11.next()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            org.mp4parser.muxer.Sample r14 = (org.mp4parser.muxer.Sample) r14     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.nio.ByteBuffer r14 = r14.b()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            int r15 = r14.remaining()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            byte[] r15 = new byte[r15]     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            int r2 = r15.length     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r14.get(r15, r3, r2)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            int r2 = r15.length     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            int r2 = com.xiaomi.aaccodec.G711.decode(r15, r3, r2, r0)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            byte[] r14 = new byte[r2]     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            java.lang.System.arraycopy(r0, r3, r14, r3, r2)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r13.b(r14)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
        L_0x010a:
            boolean r2 = r13.a((byte[]) r12)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r2 == 0) goto L_0x011a
            int r2 = r12.length     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            byte[] r2 = r7.encode(r12, r3, r2)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            int r14 = r2.length     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r10.write(r2, r3, r14)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            goto L_0x010a
        L_0x011a:
            r2 = 1
            goto L_0x00e3
        L_0x011c:
            r7.release()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r10.flush()     // Catch:{ IOException -> 0x0126 }
            r10.close()     // Catch:{ IOException -> 0x0126 }
            goto L_0x012a
        L_0x0126:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
        L_0x012a:
            org.mp4parser.muxer.tracks.AACTrackImpl r2 = new org.mp4parser.muxer.tracks.AACTrackImpl     // Catch:{ Exception -> 0x0183, all -> 0x0180 }
            org.mp4parser.muxer.FileDataSourceImpl r0 = new org.mp4parser.muxer.FileDataSourceImpl     // Catch:{ Exception -> 0x0183, all -> 0x0180 }
            r0.<init>((java.lang.String) r5)     // Catch:{ Exception -> 0x0183, all -> 0x0180 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0183, all -> 0x0180 }
            org.mp4parser.muxer.Movie r0 = new org.mp4parser.muxer.Movie     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            r0.<init>()     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            r0.a((org.mp4parser.muxer.Track) r8)     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            r0.a((org.mp4parser.muxer.Track) r2)     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            org.mp4parser.muxer.builder.DefaultMp4Builder r7 = new org.mp4parser.muxer.builder.DefaultMp4Builder     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            r7.<init>()     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            org.mp4parser.Container r0 = r7.a((org.mp4parser.muxer.Movie) r0)     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            r10 = r18
            r7.<init>(r10)     // Catch:{ Exception -> 0x017c, all -> 0x017a }
            java.nio.channels.FileChannel r4 = r7.getChannel()     // Catch:{ Exception -> 0x0178, all -> 0x0176 }
            r0.a((java.nio.channels.WritableByteChannel) r4)     // Catch:{ Exception -> 0x0178, all -> 0x0176 }
            java.io.File r0 = new java.io.File     // Catch:{ OutOfMemoryError -> 0x0172, Exception -> 0x016d, all -> 0x01d7 }
            r0.<init>(r5)     // Catch:{ OutOfMemoryError -> 0x0172, Exception -> 0x016d, all -> 0x01d7 }
            boolean r3 = r0.exists()     // Catch:{ OutOfMemoryError -> 0x0172, Exception -> 0x016d, all -> 0x01d7 }
            if (r3 == 0) goto L_0x0164
            r0.delete()     // Catch:{ OutOfMemoryError -> 0x0172, Exception -> 0x016d, all -> 0x01d7 }
        L_0x0164:
            a((java.io.Closeable) r7)     // Catch:{ OutOfMemoryError -> 0x0172, Exception -> 0x016d, all -> 0x01d7 }
            a((java.io.Closeable) r2)     // Catch:{ OutOfMemoryError -> 0x0172, Exception -> 0x016d, all -> 0x01d7 }
            r3 = 1
            goto L_0x01cc
        L_0x016d:
            r0 = move-exception
            r4 = r6
            r3 = 1
            goto L_0x01f0
        L_0x0172:
            r4 = r6
            r3 = 1
            goto L_0x020d
        L_0x0176:
            r0 = move-exception
            goto L_0x01b6
        L_0x0178:
            r0 = move-exception
            goto L_0x017e
        L_0x017a:
            r0 = move-exception
            goto L_0x01b7
        L_0x017c:
            r0 = move-exception
            r7 = r4
        L_0x017e:
            r4 = r2
            goto L_0x0185
        L_0x0180:
            r0 = move-exception
            r2 = r4
            goto L_0x01b7
        L_0x0183:
            r0 = move-exception
            r7 = r4
        L_0x0185:
            java.lang.String r2 = "g711"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b4 }
            r10.<init>()     // Catch:{ all -> 0x01b4 }
            java.lang.String r11 = "Error AACTrack"
            r10.append(r11)     // Catch:{ all -> 0x01b4 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01b4 }
            r10.append(r0)     // Catch:{ all -> 0x01b4 }
            java.lang.String r0 = r10.toString()     // Catch:{ all -> 0x01b4 }
            com.mijia.debug.SDKLog.e(r2, r0)     // Catch:{ all -> 0x01b4 }
            java.io.File r0 = new java.io.File     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r0.<init>(r5)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            boolean r2 = r0.exists()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r2 == 0) goto L_0x01ad
            r0.delete()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
        L_0x01ad:
            a((java.io.Closeable) r7)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            a((java.io.Closeable) r4)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            goto L_0x01cc
        L_0x01b4:
            r0 = move-exception
            r2 = r4
        L_0x01b6:
            r4 = r7
        L_0x01b7:
            java.io.File r7 = new java.io.File     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            r7.<init>(r5)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            boolean r5 = r7.exists()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            if (r5 == 0) goto L_0x01c5
            r7.delete()     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
        L_0x01c5:
            a((java.io.Closeable) r4)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            a((java.io.Closeable) r2)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            throw r0     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
        L_0x01cc:
            java.lang.String r0 = "g711"
            java.lang.String r2 = "Success"
            com.mijia.debug.SDKLog.e(r0, r2)     // Catch:{ OutOfMemoryError -> 0x01e6, Exception -> 0x01da, all -> 0x01d7 }
            a((java.io.Closeable) r6)
            goto L_0x022a
        L_0x01d7:
            r0 = move-exception
            goto L_0x0233
        L_0x01da:
            r0 = move-exception
            goto L_0x01e2
        L_0x01dc:
            r0 = move-exception
            r8 = r4
            goto L_0x01eb
        L_0x01df:
            r0 = move-exception
            r8 = r4
            r9 = r8
        L_0x01e2:
            r4 = r6
            goto L_0x01f0
        L_0x01e4:
            r8 = r4
            r9 = r8
        L_0x01e6:
            r4 = r6
            goto L_0x020d
        L_0x01e8:
            r0 = move-exception
            r6 = r4
            r8 = r6
        L_0x01eb:
            r9 = r8
            goto L_0x0233
        L_0x01ed:
            r0 = move-exception
            r8 = r4
            r9 = r8
        L_0x01f0:
            java.lang.String r1 = "g711"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0231 }
            r2.<init>()     // Catch:{ all -> 0x0231 }
            java.lang.String r5 = "Error Exception "
            r2.append(r5)     // Catch:{ all -> 0x0231 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0231 }
            r2.append(r0)     // Catch:{ all -> 0x0231 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0231 }
            com.mijia.debug.SDKLog.e(r1, r0)     // Catch:{ all -> 0x0231 }
            goto L_0x0227
        L_0x020b:
            r8 = r4
            r9 = r8
        L_0x020d:
            java.lang.String r0 = "g711"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0231 }
            r2.<init>()     // Catch:{ all -> 0x0231 }
            java.lang.String r5 = "Error OOM "
            r2.append(r5)     // Catch:{ all -> 0x0231 }
            long r5 = r17.length()     // Catch:{ all -> 0x0231 }
            r2.append(r5)     // Catch:{ all -> 0x0231 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0231 }
            com.mijia.debug.SDKLog.e(r0, r1)     // Catch:{ all -> 0x0231 }
        L_0x0227:
            a((java.io.Closeable) r4)
        L_0x022a:
            a((java.io.Closeable) r8)
            a((java.io.Closeable) r9)
            return r3
        L_0x0231:
            r0 = move-exception
            r6 = r4
        L_0x0233:
            a((java.io.Closeable) r6)
            a((java.io.Closeable) r8)
            a((java.io.Closeable) r9)
            throw r0
        L_0x023d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Utils.Util.a(java.lang.String, java.io.File, java.io.File):boolean");
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent();
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", str, (String) null));
        context.startActivity(intent);
    }

    public static String a(CameraBand cameraBand) {
        String a2 = cameraBand.a();
        if (TextUtils.isEmpty(a2)) {
            a2 = f671a;
        }
        StringBuilder sb = new StringBuilder(a2);
        String b = cameraBand.b();
        sb.append(Operators.BRACKET_START_STR);
        sb.append(b.substring(b.length() - 5, b.length() - 3));
        sb.append(b.substring(b.length() - 2));
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public static String a(String str) {
        return f671a + Operators.BRACKET_START_STR + str.substring(str.length() - 5, str.length() - 3) + str.substring(str.length() - 2) + Operators.BRACKET_END_STR;
    }

    public static boolean b(Context context, String str) {
        return PermissionChecker.checkSelfPermission(context, str) == 0;
    }

    public static String b(long j) {
        return String.format("%d.%d.%d.%d", new Object[]{Long.valueOf((j >> 24) & 255), Long.valueOf((j >> 16) & 255), Long.valueOf((j >> 8) & 255), Long.valueOf(j & 255)});
    }
}
