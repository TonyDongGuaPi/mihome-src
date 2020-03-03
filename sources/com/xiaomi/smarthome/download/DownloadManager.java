package com.xiaomi.smarthome.download;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.miio.Miio;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DownloadManager {
    public static final int A = 2;
    public static final int B = 3;
    public static final int C = 4;
    public static final String D = "android.intent.action.DOWNLOAD_COMPLETE";
    public static final String E = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
    public static final String F = "android.intent.action.VIEW_DOWNLOADS";
    public static final String G = "extra_download_id";
    private static final String H = "DownloadManager";
    /* access modifiers changed from: private */
    public static final String[] I = {"_id", "title", "description", "uri", "media_type", "total_size", "local_uri", "status", "reason", "bytes_so_far", "last_modified_timestamp"};
    private static final String[] J = {"_id", "title", "description", "uri", Downloads.COLUMN_MIME_TYPE, Downloads.COLUMN_TOTAL_BYTES, "status", Downloads.COLUMN_CURRENT_BYTES, Downloads.COLUMN_LAST_MODIFICATION, "destination", "hint", Downloads._DATA};
    /* access modifiers changed from: private */
    public static final Set<String> K = new HashSet(Arrays.asList(new String[]{"_id", "total_size", "status", "reason", "bytes_so_far", "last_modified_timestamp"}));

    /* renamed from: a  reason: collision with root package name */
    public static final String f15551a = "_id";
    public static final String b = "title";
    public static final String c = "description";
    public static final String d = "uri";
    public static final String e = "media_type";
    public static final String f = "total_size";
    public static final String g = "local_uri";
    public static final String h = "status";
    public static final String i = "reason";
    public static final String j = "bytes_so_far";
    public static final String k = "last_modified_timestamp";
    public static final int l = 1;
    public static final int m = 2;
    public static final int n = 4;
    public static final int o = 8;
    public static final int p = 16;
    public static final int q = 1000;
    public static final int r = 1001;
    public static final int s = 1002;
    public static final int t = 1004;
    public static final int u = 1005;
    public static final int v = 1006;
    public static final int w = 1007;
    public static final int x = 1008;
    public static final int y = 1009;
    public static final int z = 1;
    private ContentResolver L;
    private String M;
    private Uri N = Downloads.CONTENT_URI;

    public static class Request {

        /* renamed from: a  reason: collision with root package name */
        public static final int f15554a = 1;
        public static final int b = 2;
        static final /* synthetic */ boolean c = (!DownloadManager.class.desiredAssertionStatus());
        private Uri d;
        private Uri e;
        private List<Pair<String, String>> f = new ArrayList();
        private CharSequence g;
        private CharSequence h;
        private boolean i = true;
        private String j;
        private boolean k = true;
        private int l = -1;
        private boolean m = true;
        private String n;

        public Request(Uri uri) {
            if (uri != null) {
                String scheme = uri.getScheme();
                if (scheme == null || !scheme.equals("http")) {
                    throw new IllegalArgumentException("Can only download HTTP URIs: " + uri);
                }
                this.d = uri;
                this.n = null;
                return;
            }
            throw new NullPointerException();
        }

        public Request(Uri uri, String str) {
            if (uri != null) {
                String scheme = uri.getScheme();
                if (scheme == null || !scheme.equals("http")) {
                    throw new IllegalArgumentException("Can only download HTTP URIs: " + uri);
                }
                this.d = uri;
                this.n = str;
                return;
            }
            throw new NullPointerException();
        }

        public Request a(Uri uri) {
            this.e = uri;
            return this;
        }

        public Request a(Context context, String str, String str2) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + str + "/");
            if (!file.exists()) {
                file.mkdir();
            }
            a(file, str2);
            return this;
        }

        public Request b(Context context, String str, String str2) {
            a(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str + "/"), str2);
            return this;
        }

        public Request a(String str, String str2) {
            a(Environment.getExternalStoragePublicDirectory(str), str2);
            return this;
        }

        private void a(File file, String str) {
            if (str != null) {
                this.e = Uri.withAppendedPath(Uri.fromFile(file), str);
                Miio.b("DownloadManager", "mDestinationUri=" + this.e.toString());
                return;
            }
            throw new NullPointerException("subPath cannot be null");
        }

        public Request b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("header cannot be null");
            } else if (!str.contains(":")) {
                if (str2 == null) {
                    str2 = "";
                }
                this.f.add(Pair.create(str, str2));
                return this;
            } else {
                throw new IllegalArgumentException("header may not contain ':'");
            }
        }

        public Request a(CharSequence charSequence) {
            this.g = charSequence;
            return this;
        }

        public Request b(CharSequence charSequence) {
            this.h = charSequence;
            return this;
        }

        public Request a(String str) {
            this.j = str;
            return this;
        }

        public Request a(boolean z) {
            this.i = z;
            return this;
        }

        public Request a(int i2) {
            this.l = i2;
            return this;
        }

        public Request b(boolean z) {
            this.k = z;
            return this;
        }

        public Request c(boolean z) {
            this.m = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ContentValues b(String str) {
            ContentValues contentValues = new ContentValues();
            if (c || this.d != null) {
                contentValues.put("uri", this.d.toString());
                contentValues.put(Downloads.COLUMN_IS_PUBLIC_API, true);
                contentValues.put(Downloads.COLUMN_NOTIFICATION_PACKAGE, str);
                int i2 = 0;
                if (this.e != null) {
                    contentValues.put("destination", 4);
                    contentValues.put("hint", this.e.toString());
                } else {
                    contentValues.put("destination", 0);
                }
                if (!this.f.isEmpty()) {
                    a(contentValues);
                }
                a(contentValues, "title", (Object) this.g);
                a(contentValues, "description", (Object) this.h);
                a(contentValues, Downloads.COLUMN_MIME_TYPE, (Object) this.j);
                if (!this.i) {
                    i2 = 2;
                }
                contentValues.put("visibility", Integer.valueOf(i2));
                contentValues.put(Downloads.COLUMN_ALLOWED_NETWORK_TYPES, Integer.valueOf(this.l));
                contentValues.put(Downloads.COLUMN_ALLOW_ROAMING, Boolean.valueOf(this.k));
                contentValues.put(Downloads.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, Boolean.valueOf(this.m));
                contentValues.put(Downloads.COLUMN_NO_INTEGRITY, true);
                if (this.n != null) {
                    contentValues.put(Downloads.COLUMN_UDN, this.n);
                }
                return contentValues;
            }
            throw new AssertionError();
        }

        private void a(ContentValues contentValues) {
            int i2 = 0;
            for (Pair next : this.f) {
                contentValues.put(Downloads.RequestHeaders.f + i2, ((String) next.first) + ": " + ((String) next.second));
                i2++;
            }
        }

        private void a(ContentValues contentValues, String str, Object obj) {
            if (obj != null) {
                contentValues.put(str, obj.toString());
            }
        }
    }

    public static class Query {

        /* renamed from: a  reason: collision with root package name */
        public static final int f15553a = 1;
        public static final int b = 2;
        private long[] c = null;
        private Integer d = null;
        private String e = Downloads.COLUMN_LAST_MODIFICATION;
        private int f = 2;
        private boolean g = false;

        public Query a(long... jArr) {
            this.c = jArr;
            return this;
        }

        public Query a(int i) {
            this.d = Integer.valueOf(i);
            return this;
        }

        public Query a(boolean z) {
            this.g = z;
            return this;
        }

        public Query a(String str, int i) {
            if (i == 1 || i == 2) {
                if (str.equals("last_modified_timestamp")) {
                    this.e = Downloads.COLUMN_LAST_MODIFICATION;
                } else if (str.equals("total_size")) {
                    this.e = Downloads.COLUMN_TOTAL_BYTES;
                } else {
                    throw new IllegalArgumentException("Cannot order by " + str);
                }
                this.f = i;
                return this;
            }
            throw new IllegalArgumentException("Invalid direction: " + i);
        }

        /* access modifiers changed from: package-private */
        public Cursor a(ContentResolver contentResolver, String[] strArr, Uri uri) {
            String[] strArr2;
            ArrayList arrayList = new ArrayList();
            if (this.c != null) {
                arrayList.add(DownloadManager.f(this.c));
                strArr2 = DownloadManager.g(this.c);
            } else {
                strArr2 = null;
            }
            String[] strArr3 = strArr2;
            if (this.d != null) {
                ArrayList arrayList2 = new ArrayList();
                if ((this.d.intValue() & 1) != 0) {
                    arrayList2.add(b("=", 190));
                }
                if ((this.d.intValue() & 2) != 0) {
                    arrayList2.add(b("=", 192));
                }
                if ((this.d.intValue() & 4) != 0) {
                    arrayList2.add(b("=", 193));
                    arrayList2.add(b("=", 194));
                    arrayList2.add(b("=", 195));
                    arrayList2.add(b("=", Downloads.STATUS_QUEUED_FOR_WIFI));
                }
                if ((this.d.intValue() & 8) != 0) {
                    arrayList2.add(b("=", 200));
                }
                if ((this.d.intValue() & 16) != 0) {
                    arrayList2.add(Operators.BRACKET_START_STR + b(">=", 400) + " AND " + b("<", 600) + Operators.BRACKET_END_STR);
                }
                arrayList.add(a(" OR ", (Iterable<String>) arrayList2));
            }
            if (this.g) {
                arrayList.add("is_visible_in_downloads_ui != '0'");
            }
            arrayList.add("deleted != '1'");
            String a2 = a(" AND ", (Iterable<String>) arrayList);
            String str = this.f == 1 ? "ASC" : "DESC";
            return contentResolver.query(uri, strArr, a2, strArr3, this.e + " " + str);
        }

        private String a(String str, Iterable<String> iterable) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (String next : iterable) {
                if (!z) {
                    sb.append(str);
                }
                sb.append(next);
                z = false;
            }
            return sb.toString();
        }

        private String b(String str, int i) {
            return "status" + str + "'" + i + "'";
        }
    }

    public DownloadManager(ContentResolver contentResolver, String str) {
        this.L = contentResolver;
        this.M = str;
    }

    public void a(boolean z2) {
        if (z2) {
            this.N = Downloads.ALL_DOWNLOADS_CONTENT_URI;
        } else {
            this.N = Downloads.CONTENT_URI;
        }
    }

    public long a(Request request) {
        return Long.parseLong(this.L.insert(Downloads.CONTENT_URI, request.b(this.M)).getLastPathSegment());
    }

    public int a(long... jArr) {
        if (jArr == null || jArr.length == 0) {
            throw new IllegalArgumentException("input param 'ids' can't be null");
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Downloads.COLUMN_DELETED, 1);
        return this.L.update(this.N, contentValues, f(jArr), g(jArr));
    }

    public int b(long... jArr) {
        if (jArr != null && jArr.length != 0) {
            return this.L.delete(this.N, f(jArr), g(jArr));
        }
        throw new IllegalArgumentException("input param 'ids' can't be null");
    }

    public Cursor a(Query query) {
        Cursor a2 = query.a(this.L, J, this.N);
        if (a2 == null) {
            return null;
        }
        return new CursorTranslator(a2, this.N);
    }

    public ParcelFileDescriptor a(long j2) throws FileNotFoundException {
        return this.L.openFileDescriptor(b(j2), "r");
    }

    /* JADX INFO: finally extract failed */
    public void c(long... jArr) {
        Cursor a2 = a(new Query().a(jArr));
        try {
            a2.moveToFirst();
            while (!a2.isAfterLast()) {
                int i2 = a2.getInt(a2.getColumnIndex("status"));
                if (i2 != 2) {
                    if (i2 != 1) {
                        throw new IllegalArgumentException("Can only pause a running download: " + a2.getLong(a2.getColumnIndex("_id")));
                    }
                }
                a2.moveToNext();
            }
            a2.close();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads.COLUMN_CONTROL, 1);
            contentValues.put(Downloads.COLUMN_NO_INTEGRITY, 1);
            this.L.update(this.N, contentValues, f(jArr), g(jArr));
        } catch (Throwable th) {
            a2.close();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void d(long... jArr) {
        Cursor a2 = a(new Query().a(jArr));
        try {
            a2.moveToFirst();
            while (!a2.isAfterLast()) {
                if (a2.getInt(a2.getColumnIndex("status")) == 4) {
                    a2.moveToNext();
                } else {
                    throw new IllegalArgumentException("Cann only resume a paused download: " + a2.getLong(a2.getColumnIndex("_id")));
                }
            }
            a2.close();
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", 190);
            contentValues.put(Downloads.COLUMN_CONTROL, 0);
            this.L.update(this.N, contentValues, f(jArr), g(jArr));
        } catch (Throwable th) {
            a2.close();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void e(long... jArr) {
        Cursor a2 = a(new Query().a(jArr));
        try {
            a2.moveToFirst();
            while (!a2.isAfterLast()) {
                int i2 = a2.getInt(a2.getColumnIndex("status"));
                if (i2 != 8) {
                    if (i2 != 16) {
                        throw new IllegalArgumentException("Cannot restart incomplete download: " + a2.getLong(a2.getColumnIndex("_id")));
                    }
                }
                a2.moveToNext();
            }
            a2.close();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Downloads.COLUMN_CURRENT_BYTES, 0);
            contentValues.put(Downloads.COLUMN_TOTAL_BYTES, -1);
            contentValues.putNull(Downloads._DATA);
            contentValues.put("status", 190);
            this.L.update(this.N, contentValues, f(jArr), g(jArr));
        } catch (Throwable th) {
            a2.close();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public Uri b(long j2) {
        return ContentUris.withAppendedId(this.N, j2);
    }

    static String f(long[] jArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.BRACKET_START_STR);
        for (int i2 = 0; i2 < jArr.length; i2++) {
            if (i2 > 0) {
                sb.append("OR ");
            }
            sb.append("_id");
            sb.append(" = ? ");
        }
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    static String[] g(long[] jArr) {
        String[] strArr = new String[jArr.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            strArr[i2] = Long.toString(jArr[i2]);
        }
        return strArr;
    }

    private static class CursorTranslator extends CursorWrapper {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ boolean f15552a = (!DownloadManager.class.desiredAssertionStatus());

        private long b(int i) {
            switch (i) {
                case 194:
                    return 1;
                case 195:
                    return 2;
                case Downloads.STATUS_QUEUED_FOR_WIFI /*196*/:
                    return 3;
                default:
                    return 4;
            }
        }

        private long c(int i) {
            if ((400 <= i && i < 488) || (500 <= i && i < 600)) {
                return (long) i;
            }
            switch (i) {
                case 488:
                    return 1009;
                case Downloads.STATUS_CANNOT_RESUME /*489*/:
                    return 1008;
                case Downloads.STATUS_FILE_ERROR /*492*/:
                    return 1001;
                case 493:
                case Downloads.STATUS_UNHANDLED_HTTP_CODE /*494*/:
                    return 1002;
                case Downloads.STATUS_HTTP_DATA_ERROR /*495*/:
                    return 1004;
                case Downloads.STATUS_TOO_MANY_REDIRECTS /*497*/:
                    return 1005;
                case Downloads.STATUS_INSUFFICIENT_SPACE_ERROR /*498*/:
                    return 1006;
                case Downloads.STATUS_DEVICE_NOT_FOUND_ERROR /*499*/:
                    return 1007;
                default:
                    return 1000;
            }
        }

        public CursorTranslator(Cursor cursor, Uri uri) {
            super(cursor);
        }

        public int getColumnIndex(String str) {
            return Arrays.asList(DownloadManager.I).indexOf(str);
        }

        public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
            int columnIndex = getColumnIndex(str);
            if (columnIndex != -1) {
                return columnIndex;
            }
            throw new IllegalArgumentException("No such column: " + str);
        }

        public String getColumnName(int i) {
            int length = DownloadManager.I.length;
            if (i >= 0 && i < length) {
                return DownloadManager.I[i];
            }
            throw new IllegalArgumentException("Invalid column index " + i + ", " + length + " columns exist");
        }

        public String[] getColumnNames() {
            String[] strArr = new String[DownloadManager.I.length];
            System.arraycopy(DownloadManager.I, 0, strArr, 0, DownloadManager.I.length);
            return strArr;
        }

        public int getColumnCount() {
            return DownloadManager.I.length;
        }

        public byte[] getBlob(int i) {
            throw new UnsupportedOperationException();
        }

        public double getDouble(int i) {
            return (double) getLong(i);
        }

        private boolean a(String str) {
            return DownloadManager.K.contains(str);
        }

        public float getFloat(int i) {
            return (float) getDouble(i);
        }

        public int getInt(int i) {
            return (int) getLong(i);
        }

        public long getLong(int i) {
            return c(getColumnName(i));
        }

        public short getShort(int i) {
            return (short) ((int) getLong(i));
        }

        public String getString(int i) {
            return b(getColumnName(i));
        }

        private String b(String str) {
            if (a(str)) {
                return Long.toString(c(str));
            }
            if (str.equals("title")) {
                return e("title");
            }
            if (str.equals("description")) {
                return e("description");
            }
            if (str.equals("uri")) {
                return e("uri");
            }
            if (str.equals("media_type")) {
                return e(Downloads.COLUMN_MIME_TYPE);
            }
            if (f15552a || str.equals("local_uri")) {
                return a();
            }
            throw new AssertionError();
        }

        private String a() {
            String e = e(Downloads._DATA);
            if (e == null) {
                return null;
            }
            return Uri.fromFile(new File(e)).toString();
        }

        private long c(String str) {
            if (!a(str)) {
                return Long.valueOf(b(str)).longValue();
            }
            if (str.equals("_id")) {
                return d("_id");
            }
            if (str.equals("total_size")) {
                return d(Downloads.COLUMN_TOTAL_BYTES);
            }
            if (str.equals("status")) {
                return (long) d((int) d("status"));
            }
            if (str.equals("reason")) {
                return a((int) d("status"));
            }
            if (str.equals("bytes_so_far")) {
                return d(Downloads.COLUMN_CURRENT_BYTES);
            }
            if (f15552a || str.equals("last_modified_timestamp")) {
                return d(Downloads.COLUMN_LAST_MODIFICATION);
            }
            throw new AssertionError();
        }

        private long a(int i) {
            int d = d(i);
            if (d == 4) {
                return b(i);
            }
            if (d != 16) {
                return 0;
            }
            return c(i);
        }

        private long d(String str) {
            return super.getLong(super.getColumnIndex(str));
        }

        private String e(String str) {
            return super.getString(super.getColumnIndex(str));
        }

        private int d(int i) {
            if (i == 190) {
                return 1;
            }
            if (i == 200) {
                return 8;
            }
            switch (i) {
                case 192:
                    return 2;
                case 193:
                case 194:
                case 195:
                case Downloads.STATUS_QUEUED_FOR_WIFI /*196*/:
                    return 4;
                default:
                    if (f15552a || Downloads.isStatusError(i)) {
                        return 16;
                    }
                    throw new AssertionError();
            }
        }
    }
}
