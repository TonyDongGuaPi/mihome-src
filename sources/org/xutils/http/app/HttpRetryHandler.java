package org.xutils.http.app;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.HashSet;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;
import org.xutils.http.HttpMethod;
import org.xutils.http.request.UriRequest;

public class HttpRetryHandler {
    protected static HashSet<Class<?>> b = new HashSet<>();

    /* renamed from: a  reason: collision with root package name */
    protected int f10774a = 2;

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
        this.f10774a = i;
    }

    public boolean a(UriRequest uriRequest, Throwable th, int i) {
        LogUtil.e(th.getMessage(), th);
        if (i > this.f10774a) {
            LogUtil.e(uriRequest.toString());
            LogUtil.e("The Max Retry times has been reached!");
            return false;
        } else if (!HttpMethod.permitsRetry(uriRequest.q().b())) {
            LogUtil.e(uriRequest.toString());
            LogUtil.e("The Request Method can not be retried.");
            return false;
        } else if (!b.contains(th.getClass())) {
            return true;
        } else {
            LogUtil.e(uriRequest.toString());
            LogUtil.e("The Exception can not be retried.");
            return false;
        }
    }
}
