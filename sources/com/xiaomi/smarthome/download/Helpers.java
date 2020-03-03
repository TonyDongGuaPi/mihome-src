package com.xiaomi.smarthome.download;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.j256.ormlite.stmt.query.ManyClause;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.io.File;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    /* renamed from: a  reason: collision with root package name */
    public static Random f15566a = new Random(SystemClock.uptimeMillis());
    private static final Pattern b = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");

    private Helpers() {
    }

    private static String c(String str) {
        try {
            Matcher matcher = b.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public static class GenerateSaveFileError extends Exception {
        private static final long serialVersionUID = 4293675292408637112L;
        String mMessage;
        int mStatus;

        public GenerateSaveFileError(int i, String str) {
            this.mStatus = i;
            this.mMessage = str;
        }
    }

    public static String a(Context context, String str, String str2, String str3, String str4, String str5, int i, long j, boolean z) throws GenerateSaveFileError {
        a(context, str5, i, z);
        if (i == 4) {
            return a(str, str2, str3, str4, str5, i, j);
        }
        return a(context, str, str2, str3, str4, str5, i, j);
    }

    private static String a(String str, String str2, String str3, String str4, String str5, int i, long j) throws GenerateSaveFileError {
        if (a()) {
            String path = Uri.parse(str2).getPath();
            if (path.endsWith("/")) {
                path = b(path.substring(0, path.length() - 1), str, str3, str4, str5, i, j);
            } else if (new File(path).exists()) {
                Log.d(Constants.f15548a, "File already exists: " + path + ",delete");
                new File(path).delete();
            }
            if (a(a(path)) >= j) {
                return path;
            }
            throw new GenerateSaveFileError(Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, "insufficient space on external storage");
        }
        throw new GenerateSaveFileError(Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, "external media not mounted");
    }

    public static File a(String str) {
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        if (str.startsWith(downloadCacheDirectory.getPath())) {
            return downloadCacheDirectory;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (str.startsWith(externalStorageDirectory.getPath())) {
            return externalStorageDirectory;
        }
        throw new IllegalArgumentException("Cannot determine filesystem root for " + str);
    }

    private static String b(String str, String str2, String str3, String str4, String str5, int i, long j) throws GenerateSaveFileError {
        String str6;
        String a2 = a(str2, (String) null, str3, str4, i);
        int indexOf = a2.indexOf(46);
        if (indexOf < 0) {
            str6 = a(str5, true);
        } else {
            String a3 = a(str5, i, a2, indexOf);
            a2 = a2.substring(0, indexOf);
            str6 = a3;
        }
        boolean equalsIgnoreCase = Constants.s.equalsIgnoreCase(a2 + str6);
        String str7 = str + File.separator + a2;
        Log.v(Constants.f15548a, "target file: " + str7 + str6);
        return a(i, str7, str6, equalsIgnoreCase);
    }

    private static String a(Context context, String str, String str2, String str3, String str4, String str5, int i, long j) throws GenerateSaveFileError {
        Context context2 = context;
        long j2 = j;
        return b(a(context, str5, i, j2).getPath(), str, str3, str4, str5, i, j2);
    }

    private static void a(Context context, String str, int i, boolean z) throws GenerateSaveFileError {
        if (z || i != 0) {
            return;
        }
        if (str != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            PackageManager packageManager = context.getPackageManager();
            intent.setDataAndType(Uri.fromParts("file", "", (String) null), str);
            if (packageManager.resolveActivity(intent, 65536) == null) {
                throw new GenerateSaveFileError(406, "no handler found for this download type");
            }
            return;
        }
        throw new GenerateSaveFileError(406, "external download with no mime type not allowed");
    }

    private static File a(Context context, String str, int i, long j) throws GenerateSaveFileError {
        return a(j);
    }

    private static File a(long j) throws GenerateSaveFileError {
        if (a()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (a(externalStorageDirectory) >= j) {
                File file = new File(externalStorageDirectory.getPath() + Constants.q);
                if (file.isDirectory() || file.mkdir()) {
                    return file;
                }
                throw new GenerateSaveFileError(Downloads.STATUS_FILE_ERROR, "unable to create external downloads directory " + file.getPath());
            }
            Log.d(Constants.f15548a, "download aborted - not enough free space");
            throw new GenerateSaveFileError(Downloads.STATUS_INSUFFICIENT_SPACE_ERROR, "insufficient space on external media");
        }
        throw new GenerateSaveFileError(Downloads.STATUS_DEVICE_NOT_FOUND_ERROR, "external media not mounted");
    }

    public static boolean a() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        Log.d(Constants.f15548a, "no external storage");
        return false;
    }

    public static long a(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }

    private static String a(String str, String str2, String str3, String str4, int i) {
        String decode;
        int lastIndexOf;
        String decode2;
        if (str2 == null || str2.endsWith("/")) {
            str2 = null;
        } else {
            Log.v(Constants.f15548a, "getting filename from hint");
            int lastIndexOf2 = str2.lastIndexOf(47) + 1;
            if (lastIndexOf2 > 0) {
                str2 = str2.substring(lastIndexOf2);
            }
        }
        if (!(str2 != null || str3 == null || (str2 = c(str3)) == null)) {
            Log.v(Constants.f15548a, "getting filename from content-disposition");
            int lastIndexOf3 = str2.lastIndexOf(47) + 1;
            if (lastIndexOf3 > 0) {
                str2 = str2.substring(lastIndexOf3);
            }
        }
        if (str2 == null && str4 != null && (decode2 = Uri.decode(str4)) != null && !decode2.endsWith("/") && decode2.indexOf(63) < 0) {
            Log.v(Constants.f15548a, "getting filename from content-location");
            int lastIndexOf4 = decode2.lastIndexOf(47) + 1;
            str2 = lastIndexOf4 > 0 ? decode2.substring(lastIndexOf4) : decode2;
        }
        if (str2 == null && (decode = Uri.decode(str)) != null && !decode.endsWith("/") && decode.indexOf(63) < 0 && (lastIndexOf = decode.lastIndexOf(47) + 1) > 0) {
            Log.v(Constants.f15548a, "getting filename from uri");
            str2 = decode.substring(lastIndexOf);
        }
        if (str2 == null) {
            Log.v(Constants.f15548a, "using default filename");
            str2 = Constants.l;
        }
        return str2.replaceAll("[^a-zA-Z0-9\\.\\-_]+", JSMethod.NOT_SET);
    }

    private static String a(String str, boolean z) {
        String str2;
        if (str != null) {
            str2 = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
            if (str2 != null) {
                Log.v(Constants.f15548a, "adding extension from type");
                str2 = "." + str2;
            } else {
                Log.v(Constants.f15548a, "couldn't find extension for " + str);
            }
        } else {
            str2 = null;
        }
        if (str2 != null) {
            return str2;
        }
        if (str == null || !str.toLowerCase().startsWith("text/")) {
            if (!z) {
                return str2;
            }
            Log.v(Constants.f15548a, "adding default binary extension");
            return Constants.o;
        } else if (str.equalsIgnoreCase(NanoHTTPD.c)) {
            Log.v(Constants.f15548a, "adding default html extension");
            return Constants.m;
        } else if (!z) {
            return str2;
        } else {
            Log.v(Constants.f15548a, "adding default text extension");
            return Constants.n;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r3, int r4, java.lang.String r5, int r6) {
        /*
            if (r3 == 0) goto L_0x0044
            r4 = 46
            int r4 = r5.lastIndexOf(r4)
            android.webkit.MimeTypeMap r0 = android.webkit.MimeTypeMap.getSingleton()
            int r4 = r4 + 1
            java.lang.String r4 = r5.substring(r4)
            java.lang.String r4 = r0.getMimeTypeFromExtension(r4)
            if (r4 == 0) goto L_0x001e
            boolean r4 = r4.equalsIgnoreCase(r3)
            if (r4 != 0) goto L_0x0044
        L_0x001e:
            r4 = 0
            java.lang.String r4 = a((java.lang.String) r3, (boolean) r4)
            if (r4 == 0) goto L_0x002d
            java.lang.String r3 = "DownloadManager"
            java.lang.String r0 = "substituting extension from type"
            android.util.Log.v(r3, r0)
            goto L_0x0045
        L_0x002d:
            java.lang.String r0 = "DownloadManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "couldn't find extension for "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            android.util.Log.v(r0, r3)
            goto L_0x0045
        L_0x0044:
            r4 = 0
        L_0x0045:
            if (r4 != 0) goto L_0x0052
            java.lang.String r3 = "DownloadManager"
            java.lang.String r4 = "keeping extension"
            android.util.Log.v(r3, r4)
            java.lang.String r4 = r5.substring(r6)
        L_0x0052:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.download.Helpers.a(java.lang.String, int, java.lang.String, int):java.lang.String");
    }

    private static String a(int i, String str, String str2, boolean z) throws GenerateSaveFileError {
        String str3 = str + str2;
        if (!new File(str3).exists() && !z) {
            return str3;
        }
        String str4 = str + "-";
        int i2 = 1;
        for (int i3 = 1; i3 < 1000000000; i3 *= 10) {
            for (int i4 = 0; i4 < 9; i4++) {
                String str5 = str4 + i2 + str2;
                if (!new File(str5).exists()) {
                    return str5;
                }
                Log.v(Constants.f15548a, "file with sequence number " + i2 + " exists");
                i2 += f15566a.nextInt(i3) + 1;
            }
        }
        throw new GenerateSaveFileError(Downloads.STATUS_FILE_ERROR, "failed to generate an unused filename on internal download storage");
    }

    public static boolean a(SystemFacade systemFacade) {
        return systemFacade.b() != null;
    }

    public static boolean b(String str) {
        String replaceFirst = str.replaceFirst("/+", "/");
        return replaceFirst.startsWith(Environment.getDownloadCacheDirectory().toString()) || replaceFirst.startsWith(Environment.getExternalStorageDirectory().toString());
    }

    public static void a(String str, Set<String> set) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    Lexer lexer = new Lexer(str, set);
                    a(lexer);
                    if (lexer.a() != 9) {
                        throw new IllegalArgumentException("syntax error");
                    }
                }
            } catch (RuntimeException e) {
                Log.d(Constants.f15548a, "invalid selection triggered " + e);
                throw e;
            }
        }
    }

    private static void a(Lexer lexer) {
        while (true) {
            if (lexer.a() == 1) {
                lexer.b();
                a(lexer);
                if (lexer.a() == 2) {
                    lexer.b();
                } else {
                    throw new IllegalArgumentException("syntax error, unmatched parenthese");
                }
            } else {
                b(lexer);
            }
            if (lexer.a() == 3) {
                lexer.b();
            } else {
                return;
            }
        }
    }

    private static void b(Lexer lexer) {
        if (lexer.a() == 4) {
            lexer.b();
            if (lexer.a() == 5) {
                lexer.b();
                if (lexer.a() == 6) {
                    lexer.b();
                    return;
                }
                throw new IllegalArgumentException("syntax error, expected quoted string");
            } else if (lexer.a() == 7) {
                lexer.b();
                if (lexer.a() == 8) {
                    lexer.b();
                    return;
                }
                throw new IllegalArgumentException("syntax error, expected NULL");
            } else {
                throw new IllegalArgumentException("syntax error after column name");
            }
        } else {
            throw new IllegalArgumentException("syntax error, expected column name");
        }
    }

    private static class Lexer {

        /* renamed from: a  reason: collision with root package name */
        public static final int f15567a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final int h = 7;
        public static final int i = 8;
        public static final int j = 9;
        private final String k;
        private final Set<String> l;
        private int m = 0;
        private int n = 0;
        private final char[] o;

        private static final boolean a(char c2) {
            return c2 == '_' || (c2 >= 'A' && c2 <= 'Z') || (c2 >= 'a' && c2 <= 'z');
        }

        private static final boolean b(char c2) {
            return c2 == '_' || (c2 >= 'A' && c2 <= 'Z') || ((c2 >= 'a' && c2 <= 'z') || (c2 >= '0' && c2 <= '9'));
        }

        public Lexer(String str, Set<String> set) {
            this.k = str;
            this.l = set;
            this.o = new char[this.k.length()];
            this.k.getChars(0, this.o.length, this.o, 0);
            b();
        }

        public int a() {
            return this.n;
        }

        public void b() {
            char[] cArr = this.o;
            while (this.m < cArr.length && cArr[this.m] == ' ') {
                this.m++;
            }
            if (this.m == cArr.length) {
                this.n = 9;
            } else if (cArr[this.m] == '(') {
                this.m++;
                this.n = 1;
            } else if (cArr[this.m] == ')') {
                this.m++;
                this.n = 2;
            } else if (cArr[this.m] == '?') {
                this.m++;
                this.n = 6;
            } else if (cArr[this.m] == '=') {
                this.m++;
                this.n = 5;
                if (this.m < cArr.length && cArr[this.m] == '=') {
                    this.m++;
                }
            } else if (cArr[this.m] == '>') {
                this.m++;
                this.n = 5;
                if (this.m < cArr.length && cArr[this.m] == '=') {
                    this.m++;
                }
            } else if (cArr[this.m] == '<') {
                this.m++;
                this.n = 5;
                if (this.m >= cArr.length) {
                    return;
                }
                if (cArr[this.m] == '=' || cArr[this.m] == '>') {
                    this.m++;
                }
            } else if (cArr[this.m] == '!') {
                this.m++;
                this.n = 5;
                if (this.m >= cArr.length || cArr[this.m] != '=') {
                    throw new IllegalArgumentException("Unexpected character after !");
                }
                this.m++;
            } else if (a(cArr[this.m])) {
                int i2 = this.m;
                this.m++;
                while (this.m < cArr.length && b(cArr[this.m])) {
                    this.m++;
                }
                String substring = this.k.substring(i2, this.m);
                if (this.m - i2 <= 4) {
                    if (substring.equals("IS")) {
                        this.n = 7;
                        return;
                    } else if (substring.equals(ManyClause.OR_OPERATION) || substring.equals(ManyClause.AND_OPERATION)) {
                        this.n = 3;
                        return;
                    } else if (substring.equals("NULL")) {
                        this.n = 8;
                        return;
                    }
                }
                if (this.l.contains(substring)) {
                    this.n = 4;
                    return;
                }
                throw new IllegalArgumentException("unrecognized column or keyword");
            } else if (cArr[this.m] == '\'') {
                this.m++;
                while (this.m < cArr.length) {
                    if (cArr[this.m] == '\'') {
                        if (this.m + 1 >= cArr.length || cArr[this.m + 1] != '\'') {
                            break;
                        }
                        this.m++;
                    }
                    this.m++;
                }
                if (this.m != cArr.length) {
                    this.m++;
                    this.n = 6;
                    return;
                }
                throw new IllegalArgumentException("unterminated string");
            } else {
                throw new IllegalArgumentException("illegal character: " + cArr[this.m]);
            }
        }
    }

    public static void a(ContentResolver contentResolver, long j, String str, String str2) {
        try {
            new File(str).delete();
        } catch (Exception e) {
            Log.w(Constants.f15548a, "file: '" + str + "' couldn't be deleted", e);
        }
        contentResolver.delete(Downloads.ALL_DOWNLOADS_CONTENT_URI, "_id = ? ", new String[]{String.valueOf(j)});
    }
}
