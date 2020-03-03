package com.mishopsdk.volley;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.mishopsdk.volley.toolbox.HttpHeaderParser;
import com.xiaomi.mishopsdk.event.NetworkCompletedEvent;
import de.greenrobot.event.EventBus;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit = false;

    public NetworkDispatcher(BlockingQueue<Request<?>> blockingQueue, Network network, Cache cache, ResponseDelivery responseDelivery) {
        this.mQueue = blockingQueue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    @TargetApi(14)
    private void addTrafficStatsTag(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                Request take = this.mQueue.take();
                take.setWaitTime((int) (SystemClock.elapsedRealtime() - take.getQueuedTime()));
                try {
                    take.addMarker("network-queue-take");
                    if (take.isCanceled()) {
                        take.finish("network-discard-cancelled");
                    } else {
                        this.mDelivery.postStart(take);
                        addTrafficStatsTag(take);
                        NetworkResponse performRequest = this.mNetwork.performRequest(take);
                        take.addMarker("network-http-complete");
                        if (HttpHeaderParser.isContentLengthEmpty(performRequest.headers)) {
                            performRequest.headers.put("Content-Length", String.valueOf(performRequest.data.length));
                        }
                        EventBus.getDefault().post(new NetworkCompletedEvent(performRequest, take));
                        if (!performRequest.notModified || !take.hasHadResponseDelivered()) {
                            Response parseNetworkResponse = take.parseNetworkResponse(performRequest);
                            take.addMarker("network-parse-complete");
                            parseNetworkResponse.isResponseFromCache = false;
                            if (take.shouldCache() && parseNetworkResponse.cacheEntry != null) {
                                this.mCache.put(take.getCacheKey(), parseNetworkResponse.cacheEntry);
                                take.addMarker("network-cache-written");
                            }
                            take.markDelivered();
                            this.mDelivery.postResponse(take, parseNetworkResponse);
                        } else {
                            take.finish("not-modified");
                        }
                    }
                } catch (VolleyError e) {
                    e.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    parseAndDeliverNetworkError(take, e);
                    EventBus.getDefault().post(new NetworkCompletedEvent(e.networkResponse, take, e));
                } catch (Exception e2) {
                    VolleyLog.e(e2, "Unhandled exception %s", e2.toString());
                    VolleyError volleyError = new VolleyError((Throwable) e2);
                    volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - elapsedRealtime);
                    parseAndDeliverNetworkError(take, volleyError);
                    EventBus.getDefault().post(new NetworkCompletedEvent((Request<?>) take, volleyError));
                } catch (Throwable th) {
                    this.mDelivery.postFinish(take);
                    throw th;
                }
                this.mDelivery.postFinish(take);
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    return;
                }
            }
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError volleyError) {
        this.mDelivery.postError(request, request.parseNetworkError(volleyError));
    }
}
