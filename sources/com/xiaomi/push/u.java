package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.cybergarage.http.HTTP;

public final class u {
    private static final Set<String> e = Collections.synchronizedSet(new HashSet());

    /* renamed from: a  reason: collision with root package name */
    private Context f12947a;
    private FileLock b;
    private String c;
    private RandomAccessFile d;

    private u(Context context) {
        this.f12947a = context;
    }

    /* JADX INFO: finally extract failed */
    public static u a(Context context, File file) {
        b.c("Locking: " + file.getAbsolutePath());
        String str = file.getAbsolutePath() + ".LOCK";
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.getParentFile().mkdirs();
            file2.createNewFile();
        }
        if (e.add(str)) {
            u uVar = new u(context);
            uVar.c = str;
            try {
                uVar.d = new RandomAccessFile(file2, "rw");
                uVar.b = uVar.d.getChannel().lock();
                b.c("Locked: " + str + HTTP.HEADER_LINE_DELIM + uVar.b);
                if (uVar.b == null) {
                    if (uVar.d != null) {
                        y.a((Closeable) uVar.d);
                    }
                    e.remove(uVar.c);
                }
                return uVar;
            } catch (Throwable th) {
                if (uVar.b == null) {
                    if (uVar.d != null) {
                        y.a((Closeable) uVar.d);
                    }
                    e.remove(uVar.c);
                }
                throw th;
            }
        } else {
            throw new IOException("abtain lock failure");
        }
    }

    public void a() {
        b.c("unLock: " + this.b);
        if (this.b != null && this.b.isValid()) {
            try {
                this.b.release();
            } catch (IOException unused) {
            }
            this.b = null;
        }
        if (this.d != null) {
            y.a((Closeable) this.d);
        }
        e.remove(this.c);
    }
}
