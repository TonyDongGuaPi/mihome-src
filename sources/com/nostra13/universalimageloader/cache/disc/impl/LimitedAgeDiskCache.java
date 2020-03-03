package com.nostra13.universalimageloader.cache.disc.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LimitedAgeDiskCache extends BaseDiskCache {
    private final long j;
    private final Map<File, Long> k;

    public LimitedAgeDiskCache(File file, long j2) {
        this(file, (File) null, DefaultConfigurationFactory.b(), j2);
    }

    public LimitedAgeDiskCache(File file, File file2, long j2) {
        this(file, file2, DefaultConfigurationFactory.b(), j2);
    }

    public LimitedAgeDiskCache(File file, File file2, FileNameGenerator fileNameGenerator, long j2) {
        super(file, file2, fileNameGenerator);
        this.k = Collections.synchronizedMap(new HashMap());
        this.j = j2 * 1000;
    }

    public File a(String str) {
        boolean z;
        File a2 = super.a(str);
        if (a2 != null && a2.exists()) {
            Long l = this.k.get(a2);
            if (l == null) {
                l = Long.valueOf(a2.lastModified());
                z = false;
            } else {
                z = true;
            }
            if (System.currentTimeMillis() - l.longValue() > this.j) {
                a2.delete();
                this.k.remove(a2);
            } else if (!z) {
                this.k.put(a2, l);
            }
        }
        return a2;
    }

    public boolean a(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException {
        boolean a2 = super.a(str, inputStream, copyListener);
        d(str);
        return a2;
    }

    public boolean a(String str, Bitmap bitmap) throws IOException {
        boolean a2 = super.a(str, bitmap);
        d(str);
        return a2;
    }

    public boolean b(String str) {
        this.k.remove(c(str));
        return super.b(str);
    }

    public void c() {
        super.c();
        this.k.clear();
    }

    private void d(String str) {
        File c = c(str);
        long currentTimeMillis = System.currentTimeMillis();
        c.setLastModified(currentTimeMillis);
        this.k.put(c, Long.valueOf(currentTimeMillis));
    }
}
