package com.xiaomi.smarthome.download;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.mobikwik.sdk.lib.Constants;
import com.xiaomi.smarthome.download.Downloads;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DownloadInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f15549a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final String g = "isWifiRequired";
    public long A;
    public long B;
    public String C;
    public boolean D;
    public boolean E;
    public int F;
    public boolean G;
    public String H;
    public String I;
    public int J;
    public int K;
    public volatile boolean L;
    /* access modifiers changed from: private */
    public List<Pair<String, String>> M;
    private SystemFacade N;
    private Context O;
    public long h;
    public String i;
    public boolean j;
    public String k;
    public String l;
    public String m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public long t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    private int c(int i2) {
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return 2;
            default:
                return 0;
        }
    }

    public String a(int i2) {
        switch (i2) {
            case 2:
                return "no network connection available";
            case 3:
                return "download size exceeds limit for mobile network";
            case 4:
                return "download size exceeds recommended limit for mobile network";
            case 5:
                return "download cannot use the current network connection because it is roaming";
            case 6:
                return "download was requested to not use the current network type";
            default:
                return "unknown error with network connectivity";
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
    }

    public static class Reader {

        /* renamed from: a  reason: collision with root package name */
        private ContentResolver f15550a;
        private Cursor b;
        private CharArrayBuffer c;
        private CharArrayBuffer d;

        public Reader(ContentResolver contentResolver, Cursor cursor) {
            this.f15550a = contentResolver;
            this.b = cursor;
        }

        public DownloadInfo a(Context context, SystemFacade systemFacade) {
            DownloadInfo downloadInfo = new DownloadInfo(context, systemFacade);
            a(downloadInfo);
            b(downloadInfo);
            return downloadInfo;
        }

        public void a(DownloadInfo downloadInfo) {
            downloadInfo.h = b("_id").longValue();
            downloadInfo.i = a(downloadInfo.i, "uri");
            boolean z = false;
            downloadInfo.j = a(Downloads.COLUMN_NO_INTEGRITY).intValue() == 1;
            downloadInfo.k = a(downloadInfo.k, "hint");
            downloadInfo.l = a(downloadInfo.l, Downloads._DATA);
            downloadInfo.m = a(downloadInfo.m, Downloads.COLUMN_MIME_TYPE);
            downloadInfo.n = a("destination").intValue();
            downloadInfo.o = a("visibility").intValue();
            downloadInfo.q = a("status").intValue();
            downloadInfo.r = a(Constants.g).intValue();
            downloadInfo.s = a("method").intValue() & 268435455;
            downloadInfo.t = b(Downloads.COLUMN_LAST_MODIFICATION).longValue();
            downloadInfo.u = a(downloadInfo.u, Downloads.COLUMN_NOTIFICATION_PACKAGE);
            downloadInfo.v = a(downloadInfo.v, Downloads.COLUMN_NOTIFICATION_CLASS);
            downloadInfo.w = a(downloadInfo.w, Downloads.COLUMN_NOTIFICATION_EXTRAS);
            downloadInfo.x = a(downloadInfo.x, Downloads.COLUMN_COOKIE_DATA);
            downloadInfo.y = a(downloadInfo.y, Downloads.COLUMN_USER_AGENT);
            downloadInfo.z = a(downloadInfo.z, Downloads.COLUMN_REFERER);
            downloadInfo.A = b(Downloads.COLUMN_TOTAL_BYTES).longValue();
            downloadInfo.B = b(Downloads.COLUMN_CURRENT_BYTES).longValue();
            downloadInfo.C = a(downloadInfo.C, "etag");
            downloadInfo.D = a(Downloads.COLUMN_DELETED).intValue() == 1;
            downloadInfo.E = a(Downloads.COLUMN_IS_PUBLIC_API).intValue() != 0;
            downloadInfo.F = a(Downloads.COLUMN_ALLOWED_NETWORK_TYPES).intValue();
            if (a(Downloads.COLUMN_ALLOW_ROAMING).intValue() != 0) {
                z = true;
            }
            downloadInfo.G = z;
            downloadInfo.H = a(downloadInfo.H, "title");
            downloadInfo.I = a(downloadInfo.I, "description");
            downloadInfo.J = a(Downloads.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT).intValue();
            synchronized (this) {
                downloadInfo.p = a(Downloads.COLUMN_CONTROL).intValue();
            }
        }

        /* JADX INFO: finally extract failed */
        private void b(DownloadInfo downloadInfo) {
            downloadInfo.M.clear();
            Cursor query = this.f15550a.query(Uri.withAppendedPath(downloadInfo.f(), Downloads.RequestHeaders.e), (String[]) null, (String) null, (String[]) null, (String) null);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("header");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("value");
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    a(downloadInfo, query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    query.moveToNext();
                }
                query.close();
                if (downloadInfo.x != null) {
                    a(downloadInfo, "Cookie", downloadInfo.x);
                }
                if (downloadInfo.z != null) {
                    a(downloadInfo, HttpHeaders.REFERER, downloadInfo.z);
                }
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }

        private void a(DownloadInfo downloadInfo, String str, String str2) {
            downloadInfo.M.add(Pair.create(str, str2));
        }

        private String a(String str, String str2) {
            int columnIndexOrThrow = this.b.getColumnIndexOrThrow(str2);
            if (str == null) {
                return this.b.getString(columnIndexOrThrow);
            }
            if (this.d == null) {
                this.d = new CharArrayBuffer(128);
            }
            this.b.copyStringToBuffer(columnIndexOrThrow, this.d);
            int i = this.d.sizeCopied;
            if (i != str.length()) {
                return new String(this.d.data, 0, i);
            }
            if (this.c == null || this.c.sizeCopied < i) {
                this.c = new CharArrayBuffer(i);
            }
            char[] cArr = this.c.data;
            char[] cArr2 = this.d.data;
            str.getChars(0, i, cArr, 0);
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (cArr[i2] != cArr2[i2]) {
                    return new String(cArr2, 0, i);
                }
            }
            return str;
        }

        private Integer a(String str) {
            return Integer.valueOf(this.b.getInt(this.b.getColumnIndexOrThrow(str)));
        }

        private Long b(String str) {
            return Long.valueOf(this.b.getLong(this.b.getColumnIndexOrThrow(str)));
        }
    }

    private DownloadInfo(Context context, SystemFacade systemFacade) {
        this.M = new ArrayList();
        this.O = context;
        this.N = systemFacade;
        this.K = Helpers.f15566a.nextInt(1001);
    }

    public Collection<Pair<String, String>> a() {
        return Collections.unmodifiableList(this.M);
    }

    public void b() {
        Intent intent;
        if (this.u != null) {
            if (this.E) {
                intent = new Intent(DownloadManager.D);
                intent.setPackage(this.u);
                intent.putExtra(DownloadManager.G, this.h);
            } else if (this.v != null) {
                intent = new Intent(Downloads.ACTION_DOWNLOAD_COMPLETED);
                intent.setClassName(this.u, this.v);
                if (this.w != null) {
                    intent.putExtra(Downloads.COLUMN_NOTIFICATION_EXTRAS, this.w);
                }
                intent.setData(e());
            } else {
                return;
            }
            this.N.a(intent);
        }
    }

    public long a(long j2) {
        if (this.r == 0) {
            return j2;
        }
        if (this.s > 0) {
            return this.t + ((long) this.s);
        }
        return this.t + ((long) ((this.K + 1000) * 30 * (1 << (this.r - 1))));
    }

    private boolean d(long j2) {
        if (this.L || this.p == 1) {
            return false;
        }
        int i2 = this.q;
        if (i2 == 0 || i2 == 190 || i2 == 192) {
            return true;
        }
        switch (i2) {
            case 194:
                if (a(j2) <= j2) {
                    return true;
                }
                return false;
            case 195:
            case Downloads.STATUS_QUEUED_FOR_WIFI /*196*/:
                if (d() == 1) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public boolean c() {
        if (Downloads.isStatusCompleted(this.q) && this.o == 1) {
            return true;
        }
        return false;
    }

    public int d() {
        Integer b2 = this.N.b();
        if (b2 == null) {
            return 2;
        }
        if (h() || !this.N.c()) {
            return b(b2.intValue());
        }
        return 5;
    }

    private boolean h() {
        if (this.E) {
            return this.G;
        }
        return true;
    }

    private int b(int i2) {
        if (!this.E || (c(i2) & this.F) != 0) {
            return d(i2);
        }
        return 6;
    }

    private int d(int i2) {
        Long e2;
        if (this.A <= 0 || i2 == 1) {
            return 1;
        }
        Long d2 = this.N.d();
        if (d2 != null && this.A > d2.longValue()) {
            return 3;
        }
        if (this.J != 0 || (e2 = this.N.e()) == null || this.A <= e2.longValue()) {
            return 1;
        }
        return 4;
    }

    public void b(long j2) {
        if (d(j2)) {
            if (this.L) {
                throw new IllegalStateException("Multiple threads on same download");
            } else if (this.q != 192) {
                this.q = 192;
                ContentValues contentValues = new ContentValues();
                contentValues.put("status", Integer.valueOf(this.q));
                this.O.getContentResolver().update(f(), contentValues, (String) null, (String[]) null);
            } else {
                DownloadThread downloadThread = new DownloadThread(this.O, this.N, this);
                this.L = true;
                this.N.a((Thread) downloadThread);
            }
        }
    }

    public Uri e() {
        return ContentUris.withAppendedId(Downloads.CONTENT_URI, this.h);
    }

    public Uri f() {
        return ContentUris.withAppendedId(Downloads.ALL_DOWNLOADS_CONTENT_URI, this.h);
    }

    public void g() {
        Log.v(Constants.f15548a, "Service adding new entry");
        Log.v(Constants.f15548a, "ID      : " + this.h);
        StringBuilder sb = new StringBuilder();
        sb.append("URI     : ");
        sb.append(this.i != null ? Constants.YES : "no");
        Log.v(Constants.f15548a, sb.toString());
        Log.v(Constants.f15548a, "NO_INTEG: " + this.j);
        Log.v(Constants.f15548a, "HINT    : " + this.k);
        Log.v(Constants.f15548a, "FILENAME: " + this.l);
        Log.v(Constants.f15548a, "MIMETYPE: " + this.m);
        Log.v(Constants.f15548a, "DESTINAT: " + this.n);
        Log.v(Constants.f15548a, "VISIBILI: " + this.o);
        Log.v(Constants.f15548a, "CONTROL : " + this.p);
        Log.v(Constants.f15548a, "STATUS  : " + this.q);
        Log.v(Constants.f15548a, "FAILED_C: " + this.r);
        Log.v(Constants.f15548a, "RETRY_AF: " + this.s);
        Log.v(Constants.f15548a, "LAST_MOD: " + this.t);
        Log.v(Constants.f15548a, "PACKAGE : " + this.u);
        Log.v(Constants.f15548a, "CLASS   : " + this.v);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("COOKIES : ");
        sb2.append(this.x != null ? Constants.YES : "no");
        Log.v(Constants.f15548a, sb2.toString());
        Log.v(Constants.f15548a, "AGENT   : " + this.y);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("REFERER : ");
        sb3.append(this.z != null ? Constants.YES : "no");
        Log.v(Constants.f15548a, sb3.toString());
        Log.v(Constants.f15548a, "TOTAL   : " + this.A);
        Log.v(Constants.f15548a, "CURRENT : " + this.B);
        Log.v(Constants.f15548a, "ETAG    : " + this.C);
        Log.v(Constants.f15548a, "DELETED : " + this.D);
    }

    public long c(long j2) {
        if (Downloads.isStatusCompleted(this.q)) {
            return -1;
        }
        if (this.q != 194) {
            return 0;
        }
        long a2 = a(j2);
        if (a2 <= j2) {
            return 0;
        }
        return a2 - j2;
    }
}
