package com.xiaomi.mishopsdk.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.mishopsdk.volley.RequestQueue;
import com.mishopsdk.volley.toolbox.InputStreamRequest;
import com.mishopsdk.volley.toolbox.RequestFuture;
import com.squareup.picasso.mishop.Downloader;
import com.xiaomi.mishopsdk.io.http.PicassoHurlStack;
import com.xiaomi.mishopsdk.io.http.PicassoVolley;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class VolleyDownloader implements Downloader {
    private PicassoHurlStack mPicassoHurlStack = new PicassoHurlStack();
    private RequestQueue mRequestQueue;

    public VolleyDownloader(Context context) {
        this.mRequestQueue = PicassoVolley.newRequestQueue(context, this.mPicassoHurlStack);
    }

    public Downloader.Response load(Uri uri, int i) throws IOException {
        InputStream inputStream;
        RequestFuture newFuture = RequestFuture.newFuture();
        this.mRequestQueue.add(((InputStreamRequest.Builder) ((InputStreamRequest.Builder) ((InputStreamRequest.Builder) ((InputStreamRequest.Builder) ((InputStreamRequest.Builder) InputStreamRequest.builder().setMethod(0)).setUrl(uri.toString())).setGzipEnabled(true)).setShouldCache(false)).setListner(newFuture)).build());
        try {
            inputStream = (InputStream) newFuture.get();
        } catch (InterruptedException unused) {
            inputStream = null;
        } catch (ExecutionException e) {
            throw new Downloader.ResponseException(e.toString(), i, 0);
        }
        return new Downloader.Response(inputStream, false);
    }

    public void shutdown() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.mPicassoHurlStack.shutdown();
        }
    }
}
