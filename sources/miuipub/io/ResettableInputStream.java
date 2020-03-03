package miuipub.io;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResettableInputStream extends InputStream {

    /* renamed from: a  reason: collision with root package name */
    private final Object f2968a;
    private final Type b;
    private final Context c;
    private final Uri d;
    private final String e;
    private final AssetManager f;
    private final String g;
    private final byte[] h;
    private InputStream i;
    private IOException j;
    /* access modifiers changed from: private */
    public Throwable k;

    private enum Type {
        File,
        Uri,
        Asset,
        ByteArray
    }

    public ResettableInputStream(String str) {
        this.f2968a = new Object() {
            /* access modifiers changed from: protected */
            public void finalize() throws Throwable {
                try {
                    if (ResettableInputStream.this.k != null) {
                        Log.e("ResettableInputStream", "InputStream is opened but never closed here", ResettableInputStream.this.k);
                    }
                    ResettableInputStream.this.close();
                } finally {
                    super.finalize();
                }
            }
        };
        this.b = Type.File;
        this.e = str;
        this.c = null;
        this.d = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    public ResettableInputStream(Context context, Uri uri) {
        this.f2968a = new Object() {
            /* access modifiers changed from: protected */
            public void finalize() throws Throwable {
                try {
                    if (ResettableInputStream.this.k != null) {
                        Log.e("ResettableInputStream", "InputStream is opened but never closed here", ResettableInputStream.this.k);
                    }
                    ResettableInputStream.this.close();
                } finally {
                    super.finalize();
                }
            }
        };
        if ("file".equals(uri.getScheme())) {
            this.b = Type.File;
            this.e = uri.getPath();
            this.c = null;
            this.d = null;
            this.f = null;
            this.g = null;
            this.h = null;
            return;
        }
        this.b = Type.Uri;
        this.c = context;
        this.d = uri;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    public ResettableInputStream(AssetManager assetManager, String str) {
        this.f2968a = new Object() {
            /* access modifiers changed from: protected */
            public void finalize() throws Throwable {
                try {
                    if (ResettableInputStream.this.k != null) {
                        Log.e("ResettableInputStream", "InputStream is opened but never closed here", ResettableInputStream.this.k);
                    }
                    ResettableInputStream.this.close();
                } finally {
                    super.finalize();
                }
            }
        };
        this.b = Type.Asset;
        this.f = assetManager;
        this.g = str;
        this.e = null;
        this.c = null;
        this.d = null;
        this.h = null;
    }

    public ResettableInputStream(byte[] bArr) {
        this.f2968a = new Object() {
            /* access modifiers changed from: protected */
            public void finalize() throws Throwable {
                try {
                    if (ResettableInputStream.this.k != null) {
                        Log.e("ResettableInputStream", "InputStream is opened but never closed here", ResettableInputStream.this.k);
                    }
                    ResettableInputStream.this.close();
                } finally {
                    super.finalize();
                }
            }
        };
        this.b = Type.ByteArray;
        this.h = bArr;
        this.e = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.g = null;
    }

    private void a() throws IOException {
        if (this.j != null) {
            throw this.j;
        } else if (this.i == null) {
            synchronized (this.f2968a) {
                if (this.i == null) {
                    switch (this.b) {
                        case Uri:
                            this.i = this.c.getContentResolver().openInputStream(this.d);
                            break;
                        case File:
                            this.i = new FileInputStream(this.e);
                            break;
                        case Asset:
                            this.i = this.f.open(this.g);
                            break;
                        case ByteArray:
                            this.i = new ByteArrayInputStream(this.h);
                            break;
                        default:
                            throw new IllegalStateException("Unkown type " + this.b);
                    }
                    this.k = new Throwable();
                }
            }
        }
    }

    public int available() throws IOException {
        a();
        return this.i.available();
    }

    public void close() throws IOException {
        if (this.i != null) {
            synchronized (this.f2968a) {
                if (this.i != null) {
                    try {
                        this.i.close();
                    } finally {
                        this.k = null;
                        this.i = null;
                        this.j = null;
                    }
                }
            }
        }
    }

    public void mark(int i2) {
        try {
            a();
            this.i.mark(i2);
        } catch (IOException e2) {
            this.j = e2;
        }
    }

    public boolean markSupported() {
        try {
            a();
            return this.i.markSupported();
        } catch (IOException e2) {
            this.j = e2;
            return super.markSupported();
        }
    }

    public int read() throws IOException {
        a();
        return this.i.read();
    }

    public int read(byte[] bArr) throws IOException {
        a();
        return this.i.read(bArr);
    }

    public int read(byte[] bArr, int i2, int i3) throws IOException {
        a();
        return this.i.read(bArr, i2, i3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void reset() throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            java.io.InputStream r0 = r3.i     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0032
            java.io.InputStream r0 = r3.i     // Catch:{ all -> 0x0034 }
            boolean r0 = r0 instanceof java.io.FileInputStream     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x001a
            java.io.InputStream r0 = r3.i     // Catch:{ all -> 0x0034 }
            java.io.FileInputStream r0 = (java.io.FileInputStream) r0     // Catch:{ all -> 0x0034 }
            java.nio.channels.FileChannel r0 = r0.getChannel()     // Catch:{ all -> 0x0034 }
            r1 = 0
            r0.position(r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r3)
            return
        L_0x001a:
            java.io.InputStream r0 = r3.i     // Catch:{ all -> 0x0034 }
            boolean r0 = r0 instanceof android.content.res.AssetManager.AssetInputStream     // Catch:{ all -> 0x0034 }
            if (r0 != 0) goto L_0x002b
            java.io.InputStream r0 = r3.i     // Catch:{ all -> 0x0034 }
            boolean r0 = r0 instanceof java.io.ByteArrayInputStream     // Catch:{ all -> 0x0034 }
            if (r0 == 0) goto L_0x0027
            goto L_0x002b
        L_0x0027:
            r3.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0032
        L_0x002b:
            java.io.InputStream r0 = r3.i     // Catch:{ all -> 0x0034 }
            r0.reset()     // Catch:{ all -> 0x0034 }
            monitor-exit(r3)
            return
        L_0x0032:
            monitor-exit(r3)
            return
        L_0x0034:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.io.ResettableInputStream.reset():void");
    }

    public long skip(long j2) throws IOException {
        a();
        return this.i.skip(j2);
    }
}
