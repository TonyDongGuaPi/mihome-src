package com.ximalaya.ting.android.sdkdownloader.http.app;

import com.ximalaya.ting.android.sdkdownloader.exception.HttpException;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashSet;
import org.json.JSONException;

public class HttpRetryHandler {
    protected static HashSet<Class<?>> b = new HashSet<>();

    /* renamed from: a  reason: collision with root package name */
    protected int f2362a = 2;

    static {
        b.add(HttpException.class);
        b.add(Callback.CancelledException.class);
        b.add(MalformedURLException.class);
        b.add(URISyntaxException.class);
        b.add(NoRouteToHostException.class);
        b.add(PortUnreachableException.class);
        b.add(ProtocolException.class);
        b.add(NullPointerException.class);
        b.add(FileNotFoundException.class);
        b.add(JSONException.class);
        b.add(UnknownHostException.class);
        b.add(IllegalArgumentException.class);
    }

    public void a(int i) {
        this.f2362a = i;
    }

    public boolean a(UriRequest uriRequest, Throwable th, int i) {
        if (i <= this.f2362a && !b.contains(th.getClass())) {
            return true;
        }
        return false;
    }
}
