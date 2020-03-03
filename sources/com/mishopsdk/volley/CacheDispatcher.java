package com.mishopsdk.volley;

import android.os.Process;
import android.os.SystemClock;
import com.mishopsdk.volley.Cache;
import java.util.concurrent.BlockingQueue;

public class CacheDispatcher extends Thread {
    private static final boolean DEBUG = VolleyLog.DEBUG;
    private final Cache mCache;
    private final BlockingQueue<Request<?>> mCacheQueue;
    private final ResponseDelivery mDelivery;
    private final BlockingQueue<Request<?>> mNetworkQueue;
    private volatile boolean mQuit = false;

    public CacheDispatcher(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, Cache cache, ResponseDelivery responseDelivery) {
        this.mCacheQueue = blockingQueue;
        this.mNetworkQueue = blockingQueue2;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    public void run() {
        if (DEBUG) {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.mCache.initialize();
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request take = this.mCacheQueue.take();
                try {
                    take.addMarker("cache-queue-take");
                    if (take.isCanceled()) {
                        take.finish("cache-discard-canceled");
                    } else {
                        this.mDelivery.postStart(take);
                        Cache.Entry entry = this.mCache.get(take.getCacheKey());
                        if (entry == null) {
                            take.addMarker("cache-miss");
                            try {
                                this.mNetworkQueue.put(take);
                            } catch (InterruptedException unused) {
                            }
                        } else {
                            take.addMarker("cache-hit");
                            Response parseNetworkResponse = take.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                            take.addMarker("cache-hit-parsed");
                            parseNetworkResponse.isResponseFromCache = true;
                            if (!entry.refreshNeeded()) {
                                this.mDelivery.postResponse(take, parseNetworkResponse);
                            } else {
                                take.addMarker("cache-hit-refresh-needed");
                                take.setCacheEntry(entry);
                                parseNetworkResponse.intermediate = true;
                                this.mDelivery.postResponse(take, parseNetworkResponse, new AddQueueRunnable(this.mNetworkQueue, take));
                            }
                        }
                    }
                } catch (Exception e) {
                    VolleyLog.e(e, "Unhandled exception %s", e.toString());
                    VolleyError volleyError = new VolleyError((Throwable) e);
                    volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    parseAndDeliverNetworkError(take, volleyError);
                } catch (Throwable th) {
                    this.mDelivery.postFinish(take);
                    throw th;
                }
                this.mDelivery.postFinish(take);
            } catch (InterruptedException unused2) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }

    private static class AddQueueRunnable implements Runnable {
        BlockingQueue<Request<?>> mNetworkQueue;
        Request<?> mRequest;

        public AddQueueRunnable(BlockingQueue<Request<?>> blockingQueue, Request<?> request) {
            this.mNetworkQueue = blockingQueue;
            this.mRequest = request;
        }

        public void run() {
            try {
                if (!(this.mNetworkQueue == null || this.mRequest == null)) {
                    this.mNetworkQueue.put(this.mRequest);
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                this.mNetworkQueue = null;
                this.mRequest = null;
                throw th;
            }
            this.mNetworkQueue = null;
            this.mRequest = null;
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }
}
