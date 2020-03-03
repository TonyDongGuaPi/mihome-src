package com.squareup.okhttp.internal.allocations;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.internal.Internal;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public final class Connection {
    private int allocationLimit = 1;
    private final List<StreamAllocationReference> allocations = new ArrayList();
    long idleAt = Long.MAX_VALUE;
    private boolean noNewAllocations;
    /* access modifiers changed from: private */
    public final ConnectionPool pool;

    public Connection(ConnectionPool connectionPool) {
        this.pool = connectionPool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.okhttp.internal.allocations.Connection.StreamAllocation reserve(java.lang.String r5) {
        /*
            r4 = this;
            com.squareup.okhttp.ConnectionPool r0 = r4.pool
            monitor-enter(r0)
            boolean r1 = r4.noNewAllocations     // Catch:{ all -> 0x0026 }
            r2 = 0
            if (r1 != 0) goto L_0x0024
            java.util.List<com.squareup.okhttp.internal.allocations.Connection$StreamAllocationReference> r1 = r4.allocations     // Catch:{ all -> 0x0026 }
            int r1 = r1.size()     // Catch:{ all -> 0x0026 }
            int r3 = r4.allocationLimit     // Catch:{ all -> 0x0026 }
            if (r1 < r3) goto L_0x0013
            goto L_0x0024
        L_0x0013:
            com.squareup.okhttp.internal.allocations.Connection$StreamAllocation r1 = new com.squareup.okhttp.internal.allocations.Connection$StreamAllocation     // Catch:{ all -> 0x0026 }
            r1.<init>()     // Catch:{ all -> 0x0026 }
            java.util.List<com.squareup.okhttp.internal.allocations.Connection$StreamAllocationReference> r2 = r4.allocations     // Catch:{ all -> 0x0026 }
            com.squareup.okhttp.internal.allocations.Connection$StreamAllocationReference r3 = new com.squareup.okhttp.internal.allocations.Connection$StreamAllocationReference     // Catch:{ all -> 0x0026 }
            r3.<init>(r1, r5)     // Catch:{ all -> 0x0026 }
            r2.add(r3)     // Catch:{ all -> 0x0026 }
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return r1
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return r2
        L_0x0026:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.allocations.Connection.reserve(java.lang.String):com.squareup.okhttp.internal.allocations.Connection$StreamAllocation");
    }

    public void release(StreamAllocation streamAllocation) {
        synchronized (this.pool) {
            if (!streamAllocation.released) {
                boolean unused = streamAllocation.released = true;
                if (streamAllocation.stream == null) {
                    remove(streamAllocation);
                }
            } else {
                throw new IllegalStateException("already released");
            }
        }
    }

    /* access modifiers changed from: private */
    public void remove(StreamAllocation streamAllocation) {
        int size = this.allocations.size();
        for (int i = 0; i < size; i++) {
            if (this.allocations.get(i).get() == streamAllocation) {
                this.allocations.remove(i);
                if (this.allocations.isEmpty()) {
                    this.idleAt = System.nanoTime();
                    return;
                }
                return;
            }
        }
        throw new IllegalArgumentException("unexpected allocation: " + streamAllocation);
    }

    public void noNewStreams() {
        synchronized (this.pool) {
            this.noNewAllocations = true;
            for (int i = 0; i < this.allocations.size(); i++) {
                this.allocations.get(i).rescind();
            }
        }
    }

    public void setAllocationLimit(int i) {
        synchronized (this.pool) {
            if (i >= 0) {
                this.allocationLimit = i;
                while (i < this.allocations.size()) {
                    this.allocations.get(i).rescind();
                    i++;
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public void pruneLeakedAllocations() {
        synchronized (this.pool) {
            Iterator<StreamAllocationReference> it = this.allocations.iterator();
            while (it.hasNext()) {
                StreamAllocationReference next = it.next();
                if (next.get() == null) {
                    Logger logger = Internal.logger;
                    logger.warning("Call " + next.name + " leaked a connection. Did you forget to close a response body?");
                    this.noNewAllocations = true;
                    it.remove();
                    if (this.allocations.isEmpty()) {
                        this.idleAt = System.nanoTime();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int size() {
        int size;
        synchronized (this.pool) {
            size = this.allocations.size();
        }
        return size;
    }

    public final class StreamAllocation {
        /* access modifiers changed from: private */
        public boolean released;
        /* access modifiers changed from: private */
        public boolean rescinded;
        /* access modifiers changed from: private */
        public Stream stream;

        private StreamAllocation() {
        }

        public Stream newStream(String str) {
            synchronized (Connection.this.pool) {
                if (this.stream != null || this.released) {
                    throw new IllegalStateException();
                } else if (this.rescinded) {
                    return null;
                } else {
                    this.stream = new Stream(str);
                    Stream stream2 = this.stream;
                    return stream2;
                }
            }
        }

        public void streamComplete(Stream stream2) {
            synchronized (Connection.this.pool) {
                if (stream2 != null) {
                    try {
                        if (stream2 == this.stream) {
                            this.stream = null;
                            if (this.released) {
                                Connection.this.remove(this);
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                throw new IllegalArgumentException();
            }
        }
    }

    private static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        /* access modifiers changed from: private */
        public final String name;

        public StreamAllocationReference(StreamAllocation streamAllocation, String str) {
            super(streamAllocation);
            this.name = str;
        }

        public void rescind() {
            StreamAllocation streamAllocation = (StreamAllocation) get();
            if (streamAllocation != null) {
                boolean unused = streamAllocation.rescinded = true;
            }
        }
    }

    public static class Stream {
        public final String name;

        public Stream(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }
}
