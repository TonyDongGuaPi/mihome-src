package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG = getClass();
    private boolean mAllowNewBuckets;
    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mFree;
    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mUsed;

    /* access modifiers changed from: protected */
    public abstract V alloc(int i);

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public abstract void free(V v);

    /* access modifiers changed from: protected */
    public abstract int getBucketedSize(int i);

    /* access modifiers changed from: protected */
    public abstract int getBucketedSizeForValue(V v);

    /* access modifiers changed from: protected */
    public abstract int getSizeInBytes(int i);

    /* access modifiers changed from: protected */
    public void onParamsChanged() {
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        initBuckets(new SparseIntArray(0));
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0052, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        r0 = alloc(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0070, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r5.mUsed.decrement(r2);
        r4 = getBucket(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        if (r4 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007c, code lost:
        r4.decrementInUseCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0080, code lost:
        com.facebook.common.internal.Throwables.propagateIfPossible(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        com.facebook.common.internal.Preconditions.checkState(r5.mInUseValues.add(r0));
        trimToSoftCap();
        r5.mPoolStatsTracker.onAlloc(r2);
        logStats();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009c, code lost:
        if (com.facebook.common.logging.FLog.isLoggable(2) == false) goto L_0x00b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009e, code lost:
        com.facebook.common.logging.FLog.v(r5.TAG, "get (alloc) (object, size) = (%x, %s)", (java.lang.Object) java.lang.Integer.valueOf(java.lang.System.identityHashCode(r0)), (java.lang.Object) java.lang.Integer.valueOf(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b2, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V get(int r6) {
        /*
            r5 = this;
            r5.ensurePoolSizeInvariant()
            int r6 = r5.getBucketedSize(r6)
            monitor-enter(r5)
            com.facebook.imagepipeline.memory.Bucket r0 = r5.getBucket(r6)     // Catch:{ all -> 0x00cb }
            r1 = 2
            if (r0 == 0) goto L_0x0053
            java.lang.Object r2 = r0.get()     // Catch:{ all -> 0x00cb }
            if (r2 == 0) goto L_0x0053
            java.util.Set<V> r6 = r5.mInUseValues     // Catch:{ all -> 0x00cb }
            boolean r6 = r6.add(r2)     // Catch:{ all -> 0x00cb }
            com.facebook.common.internal.Preconditions.checkState(r6)     // Catch:{ all -> 0x00cb }
            int r6 = r5.getBucketedSizeForValue(r2)     // Catch:{ all -> 0x00cb }
            int r0 = r5.getSizeInBytes(r6)     // Catch:{ all -> 0x00cb }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r5.mUsed     // Catch:{ all -> 0x00cb }
            r3.increment(r0)     // Catch:{ all -> 0x00cb }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r5.mFree     // Catch:{ all -> 0x00cb }
            r3.decrement(r0)     // Catch:{ all -> 0x00cb }
            com.facebook.imagepipeline.memory.PoolStatsTracker r3 = r5.mPoolStatsTracker     // Catch:{ all -> 0x00cb }
            r3.onValueReuse(r0)     // Catch:{ all -> 0x00cb }
            r5.logStats()     // Catch:{ all -> 0x00cb }
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x00cb }
            if (r0 == 0) goto L_0x0051
            java.lang.Class<?> r0 = r5.TAG     // Catch:{ all -> 0x00cb }
            java.lang.String r1 = "get (reuse) (object, size) = (%x, %s)"
            int r3 = java.lang.System.identityHashCode(r2)     // Catch:{ all -> 0x00cb }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00cb }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00cb }
            com.facebook.common.logging.FLog.v((java.lang.Class<?>) r0, (java.lang.String) r1, (java.lang.Object) r3, (java.lang.Object) r6)     // Catch:{ all -> 0x00cb }
        L_0x0051:
            monitor-exit(r5)     // Catch:{ all -> 0x00cb }
            return r2
        L_0x0053:
            int r2 = r5.getSizeInBytes(r6)     // Catch:{ all -> 0x00cb }
            boolean r3 = r5.canAllocate(r2)     // Catch:{ all -> 0x00cb }
            if (r3 == 0) goto L_0x00b9
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r5.mUsed     // Catch:{ all -> 0x00cb }
            r3.increment(r2)     // Catch:{ all -> 0x00cb }
            if (r0 == 0) goto L_0x0067
            r0.incrementInUseCount()     // Catch:{ all -> 0x00cb }
        L_0x0067:
            monitor-exit(r5)     // Catch:{ all -> 0x00cb }
            r0 = 0
            java.lang.Object r3 = r5.alloc(r6)     // Catch:{ Throwable -> 0x006f }
            r0 = r3
            goto L_0x0083
        L_0x006f:
            r3 = move-exception
            monitor-enter(r5)
            com.facebook.imagepipeline.memory.BasePool$Counter r4 = r5.mUsed     // Catch:{ all -> 0x00b6 }
            r4.decrement(r2)     // Catch:{ all -> 0x00b6 }
            com.facebook.imagepipeline.memory.Bucket r4 = r5.getBucket(r6)     // Catch:{ all -> 0x00b6 }
            if (r4 == 0) goto L_0x007f
            r4.decrementInUseCount()     // Catch:{ all -> 0x00b6 }
        L_0x007f:
            monitor-exit(r5)     // Catch:{ all -> 0x00b6 }
            com.facebook.common.internal.Throwables.propagateIfPossible(r3)
        L_0x0083:
            monitor-enter(r5)
            java.util.Set<V> r3 = r5.mInUseValues     // Catch:{ all -> 0x00b3 }
            boolean r3 = r3.add(r0)     // Catch:{ all -> 0x00b3 }
            com.facebook.common.internal.Preconditions.checkState(r3)     // Catch:{ all -> 0x00b3 }
            r5.trimToSoftCap()     // Catch:{ all -> 0x00b3 }
            com.facebook.imagepipeline.memory.PoolStatsTracker r3 = r5.mPoolStatsTracker     // Catch:{ all -> 0x00b3 }
            r3.onAlloc(r2)     // Catch:{ all -> 0x00b3 }
            r5.logStats()     // Catch:{ all -> 0x00b3 }
            boolean r1 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x00b1
            java.lang.Class<?> r1 = r5.TAG     // Catch:{ all -> 0x00b3 }
            java.lang.String r2 = "get (alloc) (object, size) = (%x, %s)"
            int r3 = java.lang.System.identityHashCode(r0)     // Catch:{ all -> 0x00b3 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00b3 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00b3 }
            com.facebook.common.logging.FLog.v((java.lang.Class<?>) r1, (java.lang.String) r2, (java.lang.Object) r3, (java.lang.Object) r6)     // Catch:{ all -> 0x00b3 }
        L_0x00b1:
            monitor-exit(r5)     // Catch:{ all -> 0x00b3 }
            return r0
        L_0x00b3:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00b3 }
            throw r6
        L_0x00b6:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00b6 }
            throw r6
        L_0x00b9:
            com.facebook.imagepipeline.memory.BasePool$PoolSizeViolationException r6 = new com.facebook.imagepipeline.memory.BasePool$PoolSizeViolationException     // Catch:{ all -> 0x00cb }
            com.facebook.imagepipeline.memory.PoolParams r0 = r5.mPoolParams     // Catch:{ all -> 0x00cb }
            int r0 = r0.maxSizeHardCap     // Catch:{ all -> 0x00cb }
            com.facebook.imagepipeline.memory.BasePool$Counter r1 = r5.mUsed     // Catch:{ all -> 0x00cb }
            int r1 = r1.mNumBytes     // Catch:{ all -> 0x00cb }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r5.mFree     // Catch:{ all -> 0x00cb }
            int r3 = r3.mNumBytes     // Catch:{ all -> 0x00cb }
            r6.<init>(r0, r1, r3, r2)     // Catch:{ all -> 0x00cb }
            throw r6     // Catch:{ all -> 0x00cb }
        L_0x00cb:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00cb }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.get(int):java.lang.Object");
    }

    public void release(V v) {
        Preconditions.checkNotNull(v);
        int bucketedSizeForValue = getBucketedSizeForValue(v);
        int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
        synchronized (this) {
            Bucket bucketIfPresent = getBucketIfPresent(bucketedSizeForValue);
            if (!this.mInUseValues.remove(v)) {
                FLog.e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSizeForValue));
                free(v);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                if (bucketIfPresent != null && !bucketIfPresent.isMaxLengthExceeded() && !isMaxSizeSoftCapExceeded()) {
                    if (isReusable(v)) {
                        bucketIfPresent.release(v);
                        this.mFree.increment(sizeInBytes);
                        this.mUsed.decrement(sizeInBytes);
                        this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                        if (FLog.isLoggable(2)) {
                            FLog.v(this.TAG, "release (reuse) (object, size) = (%x, %s)", (Object) Integer.valueOf(System.identityHashCode(v)), (Object) Integer.valueOf(bucketedSizeForValue));
                        }
                    }
                }
                if (bucketIfPresent != null) {
                    bucketIfPresent.decrementInUseCount();
                }
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "release (free) (object, size) = (%x, %s)", (Object) Integer.valueOf(System.identityHashCode(v)), (Object) Integer.valueOf(bucketedSizeForValue));
                }
                free(v);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            }
            logStats();
        }
    }

    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    /* access modifiers changed from: protected */
    public boolean isReusable(V v) {
        Preconditions.checkNotNull(v);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z;
        if (isMaxSizeSoftCapExceeded()) {
            if (this.mFree.mNumBytes != 0) {
                z = false;
                Preconditions.checkState(z);
            }
        }
        z = true;
        Preconditions.checkState(z);
    }

    private synchronized void initBuckets(SparseIntArray sparseIntArray) {
        Preconditions.checkNotNull(sparseIntArray);
        this.mBuckets.clear();
        SparseIntArray sparseIntArray2 = this.mPoolParams.bucketSizes;
        if (sparseIntArray2 != null) {
            for (int i = 0; i < sparseIntArray2.size(); i++) {
                int keyAt = sparseIntArray2.keyAt(i);
                this.mBuckets.put(keyAt, new Bucket(getSizeInBytes(keyAt), sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0)));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void trimToNothing() {
        int i;
        ArrayList arrayList = new ArrayList(this.mBuckets.size());
        SparseIntArray sparseIntArray = new SparseIntArray();
        synchronized (this) {
            for (int i2 = 0; i2 < this.mBuckets.size(); i2++) {
                Bucket valueAt = this.mBuckets.valueAt(i2);
                if (valueAt.getFreeListSize() > 0) {
                    arrayList.add(valueAt);
                }
                sparseIntArray.put(this.mBuckets.keyAt(i2), valueAt.getInUseCount());
            }
            initBuckets(sparseIntArray);
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (i = 0; i < arrayList.size(); i++) {
            Bucket bucket = (Bucket) arrayList.get(i);
            while (true) {
                Object pop = bucket.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008f, code lost:
        return;
     */
    @com.facebook.common.internal.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void trimToSize(int r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.facebook.imagepipeline.memory.BasePool$Counter r0 = r7.mUsed     // Catch:{ all -> 0x0090 }
            int r0 = r0.mNumBytes     // Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.memory.BasePool$Counter r1 = r7.mFree     // Catch:{ all -> 0x0090 }
            int r1 = r1.mNumBytes     // Catch:{ all -> 0x0090 }
            int r0 = r0 + r1
            int r0 = r0 - r8
            com.facebook.imagepipeline.memory.BasePool$Counter r1 = r7.mFree     // Catch:{ all -> 0x0090 }
            int r1 = r1.mNumBytes     // Catch:{ all -> 0x0090 }
            int r0 = java.lang.Math.min(r0, r1)     // Catch:{ all -> 0x0090 }
            if (r0 > 0) goto L_0x0017
            monitor-exit(r7)
            return
        L_0x0017:
            r1 = 2
            boolean r2 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x003b
            java.lang.Class<?> r2 = r7.TAG     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.memory.BasePool$Counter r5 = r7.mUsed     // Catch:{ all -> 0x0090 }
            int r5 = r5.mNumBytes     // Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.memory.BasePool$Counter r6 = r7.mFree     // Catch:{ all -> 0x0090 }
            int r6 = r6.mNumBytes     // Catch:{ all -> 0x0090 }
            int r5 = r5 + r6
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0090 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0090 }
            com.facebook.common.logging.FLog.v((java.lang.Class<?>) r2, (java.lang.String) r3, (java.lang.Object) r4, (java.lang.Object) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x0090 }
        L_0x003b:
            r7.logStats()     // Catch:{ all -> 0x0090 }
            r2 = 0
        L_0x003f:
            android.util.SparseArray<com.facebook.imagepipeline.memory.Bucket<V>> r3 = r7.mBuckets     // Catch:{ all -> 0x0090 }
            int r3 = r3.size()     // Catch:{ all -> 0x0090 }
            if (r2 >= r3) goto L_0x006c
            if (r0 > 0) goto L_0x004a
            goto L_0x006c
        L_0x004a:
            android.util.SparseArray<com.facebook.imagepipeline.memory.Bucket<V>> r3 = r7.mBuckets     // Catch:{ all -> 0x0090 }
            java.lang.Object r3 = r3.valueAt(r2)     // Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.memory.Bucket r3 = (com.facebook.imagepipeline.memory.Bucket) r3     // Catch:{ all -> 0x0090 }
        L_0x0052:
            if (r0 <= 0) goto L_0x0069
            java.lang.Object r4 = r3.pop()     // Catch:{ all -> 0x0090 }
            if (r4 != 0) goto L_0x005b
            goto L_0x0069
        L_0x005b:
            r7.free(r4)     // Catch:{ all -> 0x0090 }
            int r4 = r3.mItemSize     // Catch:{ all -> 0x0090 }
            int r0 = r0 - r4
            com.facebook.imagepipeline.memory.BasePool$Counter r4 = r7.mFree     // Catch:{ all -> 0x0090 }
            int r5 = r3.mItemSize     // Catch:{ all -> 0x0090 }
            r4.decrement(r5)     // Catch:{ all -> 0x0090 }
            goto L_0x0052
        L_0x0069:
            int r2 = r2 + 1
            goto L_0x003f
        L_0x006c:
            r7.logStats()     // Catch:{ all -> 0x0090 }
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r1)     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008e
            java.lang.Class<?> r0 = r7.TAG     // Catch:{ all -> 0x0090 }
            java.lang.String r1 = "trimToSize: TargetSize = %d; Final Size = %d"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mUsed     // Catch:{ all -> 0x0090 }
            int r2 = r2.mNumBytes     // Catch:{ all -> 0x0090 }
            com.facebook.imagepipeline.memory.BasePool$Counter r3 = r7.mFree     // Catch:{ all -> 0x0090 }
            int r3 = r3.mNumBytes     // Catch:{ all -> 0x0090 }
            int r2 = r2 + r3
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0090 }
            com.facebook.common.logging.FLog.v((java.lang.Class<?>) r0, (java.lang.String) r1, (java.lang.Object) r8, (java.lang.Object) r2)     // Catch:{ all -> 0x0090 }
        L_0x008e:
            monitor-exit(r7)
            return
        L_0x0090:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.trimToSize(int):void");
    }

    private synchronized Bucket<V> getBucketIfPresent(int i) {
        return this.mBuckets.get(i);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        return r0;
     */
    @com.facebook.common.internal.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.memory.Bucket<V> getBucket(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            android.util.SparseArray<com.facebook.imagepipeline.memory.Bucket<V>> r0 = r3.mBuckets     // Catch:{ all -> 0x002f }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x002f }
            com.facebook.imagepipeline.memory.Bucket r0 = (com.facebook.imagepipeline.memory.Bucket) r0     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x002d
            boolean r1 = r3.mAllowNewBuckets     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0010
            goto L_0x002d
        L_0x0010:
            r0 = 2
            boolean r0 = com.facebook.common.logging.FLog.isLoggable(r0)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x0022
            java.lang.Class<?> r0 = r3.TAG     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "creating new bucket %s"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x002f }
            com.facebook.common.logging.FLog.v((java.lang.Class<?>) r0, (java.lang.String) r1, (java.lang.Object) r2)     // Catch:{ all -> 0x002f }
        L_0x0022:
            com.facebook.imagepipeline.memory.Bucket r0 = r3.newBucket(r4)     // Catch:{ all -> 0x002f }
            android.util.SparseArray<com.facebook.imagepipeline.memory.Bucket<V>> r1 = r3.mBuckets     // Catch:{ all -> 0x002f }
            r1.put(r4, r0)     // Catch:{ all -> 0x002f }
            monitor-exit(r3)
            return r0
        L_0x002d:
            monitor-exit(r3)
            return r0
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.getBucket(int):com.facebook.imagepipeline.memory.Bucket");
    }

    /* access modifiers changed from: package-private */
    public Bucket<V> newBucket(int i) {
        return new Bucket<>(getSizeInBytes(i), Integer.MAX_VALUE, 0);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized boolean canAllocate(int i) {
        int i2 = this.mPoolParams.maxSizeHardCap;
        if (i > i2 - this.mUsed.mNumBytes) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i3 = this.mPoolParams.maxSizeSoftCap;
        if (i > i3 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            trimToSize(i3 - i);
        }
        if (i <= i2 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            return true;
        }
        this.mPoolStatsTracker.onHardCapReached();
        return false;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", (Object) Integer.valueOf(this.mUsed.mCount), (Object) Integer.valueOf(this.mUsed.mNumBytes), (Object) Integer.valueOf(this.mFree.mCount), (Object) Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    public synchronized Map<String, Integer> getStats() {
        HashMap hashMap;
        hashMap = new HashMap();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            int keyAt = this.mBuckets.keyAt(i);
            hashMap.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(keyAt), Integer.valueOf(this.mBuckets.valueAt(i).getInUseCount()));
        }
        hashMap.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        hashMap.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        hashMap.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        hashMap.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        hashMap.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        hashMap.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return hashMap;
    }

    @VisibleForTesting
    @NotThreadSafe
    static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int i) {
            this.mCount++;
            this.mNumBytes += i;
        }

        public void decrement(int i) {
            if (this.mNumBytes < i || this.mCount <= 0) {
                FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount--;
            this.mNumBytes -= i;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    public static class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object obj) {
            super("Invalid value: " + obj.toString());
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object obj) {
            super(obj);
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }
}
