package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.net.SocketException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ConnectionPool {
    private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000;
    private static final ConnectionPool systemDefault;
    private final Deque<Connection> connections = new ArrayDeque();
    private final Runnable connectionsCleanupRunnable = new Runnable() {
        public void run() {
            ConnectionPool.this.runCleanupUntilPoolIsEmpty();
        }
    };
    private Executor executor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    static {
        String property = System.getProperty("http.keepAlive");
        String property2 = System.getProperty("http.keepAliveDuration");
        String property3 = System.getProperty("http.maxConnections");
        long parseLong = property2 != null ? Long.parseLong(property2) : 300000;
        if (property != null && !Boolean.parseBoolean(property)) {
            systemDefault = new ConnectionPool(0, parseLong);
        } else if (property3 != null) {
            systemDefault = new ConnectionPool(Integer.parseInt(property3), parseLong);
        } else {
            systemDefault = new ConnectionPool(5, parseLong);
        }
    }

    public ConnectionPool(int i, long j) {
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = j * 1000 * 1000;
    }

    public static ConnectionPool getDefault() {
        return systemDefault;
    }

    public synchronized int getConnectionCount() {
        return this.connections.size();
    }

    @Deprecated
    public synchronized int getSpdyConnectionCount() {
        return getMultiplexedConnectionCount();
    }

    public synchronized int getMultiplexedConnectionCount() {
        int i;
        i = 0;
        for (Connection isFramed : this.connections) {
            if (isFramed.isFramed()) {
                i++;
            }
        }
        return i;
    }

    public synchronized int getHttpConnectionCount() {
        return this.connections.size() - getMultiplexedConnectionCount();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006f, code lost:
        r0 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.squareup.okhttp.Connection get(com.squareup.okhttp.Address r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            r0 = 0
            java.util.Deque<com.squareup.okhttp.Connection> r1 = r8.connections     // Catch:{ all -> 0x007f }
            java.util.Iterator r1 = r1.descendingIterator()     // Catch:{ all -> 0x007f }
        L_0x0008:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0070
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x007f }
            com.squareup.okhttp.Connection r2 = (com.squareup.okhttp.Connection) r2     // Catch:{ all -> 0x007f }
            com.squareup.okhttp.Route r3 = r2.getRoute()     // Catch:{ all -> 0x007f }
            com.squareup.okhttp.Address r3 = r3.getAddress()     // Catch:{ all -> 0x007f }
            boolean r3 = r3.equals(r9)     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x0008
            boolean r3 = r2.isAlive()     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x0008
            long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x007f }
            long r5 = r2.getIdleStartTimeNs()     // Catch:{ all -> 0x007f }
            r7 = 0
            long r3 = r3 - r5
            long r5 = r8.keepAliveDurationNs     // Catch:{ all -> 0x007f }
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x0039
            goto L_0x0008
        L_0x0039:
            r1.remove()     // Catch:{ all -> 0x007f }
            boolean r3 = r2.isFramed()     // Catch:{ all -> 0x007f }
            if (r3 != 0) goto L_0x006f
            com.squareup.okhttp.internal.Platform r3 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ SocketException -> 0x004e }
            java.net.Socket r4 = r2.getSocket()     // Catch:{ SocketException -> 0x004e }
            r3.tagSocket(r4)     // Catch:{ SocketException -> 0x004e }
            goto L_0x006f
        L_0x004e:
            r3 = move-exception
            java.net.Socket r2 = r2.getSocket()     // Catch:{ all -> 0x007f }
            com.squareup.okhttp.internal.Util.closeQuietly((java.net.Socket) r2)     // Catch:{ all -> 0x007f }
            com.squareup.okhttp.internal.Platform r2 = com.squareup.okhttp.internal.Platform.get()     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            r4.<init>()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "Unable to tagSocket(): "
            r4.append(r5)     // Catch:{ all -> 0x007f }
            r4.append(r3)     // Catch:{ all -> 0x007f }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x007f }
            r2.logW(r3)     // Catch:{ all -> 0x007f }
            goto L_0x0008
        L_0x006f:
            r0 = r2
        L_0x0070:
            if (r0 == 0) goto L_0x007d
            boolean r9 = r0.isFramed()     // Catch:{ all -> 0x007f }
            if (r9 == 0) goto L_0x007d
            java.util.Deque<com.squareup.okhttp.Connection> r9 = r8.connections     // Catch:{ all -> 0x007f }
            r9.addFirst(r0)     // Catch:{ all -> 0x007f }
        L_0x007d:
            monitor-exit(r8)
            return r0
        L_0x007f:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.ConnectionPool.get(com.squareup.okhttp.Address):com.squareup.okhttp.Connection");
    }

    /* access modifiers changed from: package-private */
    public void recycle(Connection connection) {
        if (connection.isFramed() || !connection.clearOwner()) {
            return;
        }
        if (!connection.isAlive()) {
            Util.closeQuietly(connection.getSocket());
            return;
        }
        try {
            Platform.get().untagSocket(connection.getSocket());
            synchronized (this) {
                addConnection(connection);
                connection.incrementRecycleCount();
                connection.resetIdleStartTime();
            }
        } catch (SocketException e) {
            Platform platform = Platform.get();
            platform.logW("Unable to untagSocket(): " + e);
            Util.closeQuietly(connection.getSocket());
        }
    }

    private void addConnection(Connection connection) {
        boolean isEmpty = this.connections.isEmpty();
        this.connections.addFirst(connection);
        if (isEmpty) {
            this.executor.execute(this.connectionsCleanupRunnable);
        } else {
            notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void share(Connection connection) {
        if (!connection.isFramed()) {
            throw new IllegalArgumentException();
        } else if (connection.isAlive()) {
            synchronized (this) {
                addConnection(connection);
            }
        }
    }

    public void evictAll() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.connections);
            this.connections.clear();
            notifyAll();
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Util.closeQuietly(((Connection) arrayList.get(i)).getSocket());
        }
    }

    /* access modifiers changed from: private */
    public void runCleanupUntilPoolIsEmpty() {
        do {
        } while (performCleanup());
    }

    /* access modifiers changed from: package-private */
    public boolean performCleanup() {
        synchronized (this) {
            if (this.connections.isEmpty()) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            long nanoTime = System.nanoTime();
            long j = this.keepAliveDurationNs;
            Iterator<Connection> descendingIterator = this.connections.descendingIterator();
            long j2 = j;
            int i = 0;
            while (descendingIterator.hasNext()) {
                Connection next = descendingIterator.next();
                long idleStartTimeNs = (next.getIdleStartTimeNs() + this.keepAliveDurationNs) - nanoTime;
                if (idleStartTimeNs > 0) {
                    if (next.isAlive()) {
                        if (next.isIdle()) {
                            i++;
                            j2 = Math.min(j2, idleStartTimeNs);
                        }
                    }
                }
                descendingIterator.remove();
                arrayList.add(next);
            }
            Iterator<Connection> descendingIterator2 = this.connections.descendingIterator();
            while (descendingIterator2.hasNext() && i > this.maxIdleConnections) {
                Connection next2 = descendingIterator2.next();
                if (next2.isIdle()) {
                    arrayList.add(next2);
                    descendingIterator2.remove();
                    i--;
                }
            }
            if (arrayList.isEmpty()) {
                try {
                    long j3 = j2 / 1000000;
                    wait(j3, (int) (j2 - (1000000 * j3)));
                    return true;
                } catch (InterruptedException unused) {
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Util.closeQuietly(((Connection) arrayList.get(i2)).getSocket());
                    }
                    return true;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void replaceCleanupExecutorForTests(Executor executor2) {
        this.executor = executor2;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<Connection> getConnections() {
        return new ArrayList(this.connections);
    }
}
