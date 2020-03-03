package com.loopj.android.http;

import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpRequestRetryHandler;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.impl.client.AbstractHttpClient;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncHttpRequest implements Runnable {
    private boolean cancelIsNotified;
    private final AbstractHttpClient client;
    private final HttpContext context;
    private int executionCount;
    private final AtomicBoolean isCancelled = new AtomicBoolean();
    private volatile boolean isFinished;
    private boolean isRequestPreProcessed;
    private final HttpUriRequest request;
    private final ResponseHandlerInterface responseHandler;

    public void onPostProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public void onPreProcessRequest(AsyncHttpRequest asyncHttpRequest) {
    }

    public AsyncHttpRequest(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, ResponseHandlerInterface responseHandlerInterface) {
        this.client = (AbstractHttpClient) Utils.notNull(abstractHttpClient, OpenSdkPlayStatisticUpload.r);
        this.context = (HttpContext) Utils.notNull(httpContext, "context");
        this.request = (HttpUriRequest) Utils.notNull(httpUriRequest, "request");
        this.responseHandler = (ResponseHandlerInterface) Utils.notNull(responseHandlerInterface, "responseHandler");
    }

    public void run() {
        if (!isCancelled()) {
            if (!this.isRequestPreProcessed) {
                this.isRequestPreProcessed = true;
                onPreProcessRequest(this);
            }
            if (!isCancelled()) {
                this.responseHandler.sendStartMessage();
                if (!isCancelled()) {
                    try {
                        makeRequestWithRetries();
                    } catch (IOException e) {
                        if (!isCancelled()) {
                            this.responseHandler.sendFailureMessage(0, (Header[]) null, (byte[]) null, e);
                        } else {
                            AsyncHttpClient.log.e("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
                        }
                    }
                    if (!isCancelled()) {
                        this.responseHandler.sendFinishMessage();
                        if (!isCancelled()) {
                            onPostProcessRequest(this);
                            this.isFinished = true;
                        }
                    }
                }
            }
        }
    }

    private void makeRequest() throws IOException {
        if (!isCancelled()) {
            if (this.request.getURI().getScheme() != null) {
                if (this.responseHandler instanceof RangeFileAsyncHttpResponseHandler) {
                    ((RangeFileAsyncHttpResponseHandler) this.responseHandler).updateRequestHeaders(this.request);
                }
                CloseableHttpResponse execute = this.client.execute(this.request, this.context);
                if (!isCancelled()) {
                    this.responseHandler.onPreProcessResponse(this.responseHandler, execute);
                    if (!isCancelled()) {
                        this.responseHandler.sendResponseMessage(execute);
                        if (!isCancelled()) {
                            this.responseHandler.onPostProcessResponse(this.responseHandler, execute);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new MalformedURLException("No valid URI scheme was provided");
        }
    }

    private void makeRequestWithRetries() throws IOException {
        HttpRequestRetryHandler httpRequestRetryHandler = this.client.getHttpRequestRetryHandler();
        IOException iOException = null;
        boolean z = true;
        while (z) {
            try {
                makeRequest();
                return;
            } catch (UnknownHostException e) {
                iOException = new IOException("UnknownHostException exception: " + e.getMessage());
                if (this.executionCount > 0) {
                    int i = this.executionCount + 1;
                    this.executionCount = i;
                    if (httpRequestRetryHandler.retryRequest(e, i, this.context)) {
                        z = true;
                    }
                }
                z = false;
            } catch (NullPointerException e2) {
                iOException = new IOException("NPE in HttpClient: " + e2.getMessage());
                int i2 = this.executionCount + 1;
                this.executionCount = i2;
                z = httpRequestRetryHandler.retryRequest(iOException, i2, this.context);
            } catch (IOException e3) {
                try {
                    if (!isCancelled()) {
                        int i3 = this.executionCount + 1;
                        this.executionCount = i3;
                        boolean retryRequest = httpRequestRetryHandler.retryRequest(e3, i3, this.context);
                        iOException = e3;
                        z = retryRequest;
                    } else {
                        return;
                    }
                } catch (Exception e4) {
                    AsyncHttpClient.log.e("AsyncHttpRequest", "Unhandled exception origin cause", e4);
                    iOException = new IOException("Unhandled exception: " + e4.getMessage());
                }
            }
        }
        throw iOException;
        if (z) {
            this.responseHandler.sendRetryMessage(this.executionCount);
        }
    }

    public boolean isCancelled() {
        boolean z = this.isCancelled.get();
        if (z) {
            sendCancelNotification();
        }
        return z;
    }

    private synchronized void sendCancelNotification() {
        if (!this.isFinished && this.isCancelled.get() && !this.cancelIsNotified) {
            this.cancelIsNotified = true;
            this.responseHandler.sendCancelMessage();
        }
    }

    public boolean isDone() {
        return isCancelled() || this.isFinished;
    }

    public boolean cancel(boolean z) {
        this.isCancelled.set(true);
        this.request.abort();
        return isCancelled();
    }

    public AsyncHttpRequest setRequestTag(Object obj) {
        this.responseHandler.setTag(obj);
        return this;
    }

    public Object getTag() {
        return this.responseHandler.getTag();
    }
}
