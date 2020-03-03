package org.xutils.cache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ProcessLock;

public final class DiskCacheFile extends File implements Closeable {
    DiskCacheEntity cacheEntity;
    ProcessLock lock;

    DiskCacheFile(DiskCacheEntity diskCacheEntity, String str, ProcessLock processLock) {
        super(str);
        this.cacheEntity = diskCacheEntity;
        this.lock = processLock;
    }

    public void close() throws IOException {
        IOUtil.a((Closeable) this.lock);
    }

    public DiskCacheFile commit() throws IOException {
        return getDiskCache().a(this);
    }

    public LruDiskCache getDiskCache() {
        return LruDiskCache.a(getParentFile().getName());
    }

    public DiskCacheEntity getCacheEntity() {
        return this.cacheEntity;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        close();
    }
}
