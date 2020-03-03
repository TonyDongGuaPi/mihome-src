package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.util.Preconditions;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class DiskCacheWriteLocker {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, WriteLock> f4904a = new HashMap();
    private final WriteLockPool b = new WriteLockPool();

    DiskCacheWriteLocker() {
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = this.f4904a.get(str);
            if (writeLock == null) {
                writeLock = this.b.a();
                this.f4904a.put(str, writeLock);
            }
            writeLock.b++;
        }
        writeLock.f4905a.lock();
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = (WriteLock) Preconditions.a(this.f4904a.get(str));
            if (writeLock.b >= 1) {
                writeLock.b--;
                if (writeLock.b == 0) {
                    WriteLock remove = this.f4904a.remove(str);
                    if (remove.equals(writeLock)) {
                        this.b.a(remove);
                    } else {
                        throw new IllegalStateException("Removed the wrong lock, expected to remove: " + writeLock + ", but actually removed: " + remove + ", safeKey: " + str);
                    }
                }
            } else {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + writeLock.b);
            }
        }
        writeLock.f4905a.unlock();
    }

    private static class WriteLock {

        /* renamed from: a  reason: collision with root package name */
        final Lock f4905a = new ReentrantLock();
        int b;

        WriteLock() {
        }
    }

    private static class WriteLockPool {

        /* renamed from: a  reason: collision with root package name */
        private static final int f4906a = 10;
        private final Queue<WriteLock> b = new ArrayDeque();

        WriteLockPool() {
        }

        /* access modifiers changed from: package-private */
        public WriteLock a() {
            WriteLock poll;
            synchronized (this.b) {
                poll = this.b.poll();
            }
            return poll == null ? new WriteLock() : poll;
        }

        /* access modifiers changed from: package-private */
        public void a(WriteLock writeLock) {
            synchronized (this.b) {
                if (this.b.size() < 10) {
                    this.b.offer(writeLock);
                }
            }
        }
    }
}
