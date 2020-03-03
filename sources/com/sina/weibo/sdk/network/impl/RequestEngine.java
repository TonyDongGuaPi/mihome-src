package com.sina.weibo.sdk.network.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.sina.weibo.sdk.net.NetStateManager;
import com.sina.weibo.sdk.network.IRequestParam;
import com.sina.weibo.sdk.network.base.RequestBodyHelper;
import com.sina.weibo.sdk.network.base.UriUtils;
import com.sina.weibo.sdk.network.base.WbResponse;
import com.sina.weibo.sdk.network.base.WbResponseBody;
import com.sina.weibo.sdk.network.exception.RequestException;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

public class RequestEngine {
    public static WbResponse request(IRequestParam iRequestParam) throws RequestException {
        HttpURLConnection httpURLConnection;
        String url = iRequestParam.getUrl();
        if (TextUtils.isEmpty(url) || (!url.startsWith("http") && !url.startsWith("https"))) {
            throw new RequestException("非法的请求地址");
        }
        String buildCompleteUri = UriUtils.buildCompleteUri(url, iRequestParam.getGetBundle());
        Pair<String, Integer> apn = NetStateManager.getAPN();
        Proxy proxy = null;
        if (apn != null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress((String) apn.first, ((Integer) apn.second).intValue()));
        }
        try {
            final URL url2 = new URL(buildCompleteUri);
            if (proxy == null) {
                httpURLConnection = (HttpURLConnection) url2.openConnection();
            } else {
                httpURLConnection = (HttpsURLConnection) url2.openConnection(proxy);
            }
            if ("https".equalsIgnoreCase(url2.getProtocol())) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String str, SSLSession sSLSession) {
                        String host = url2.getHost();
                        if (TextUtils.isEmpty(host) || !host.equals(str)) {
                            return false;
                        }
                        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
                    }
                });
                try {
                    httpsURLConnection.setSSLSocketFactory(SSLContext.getDefault().getSocketFactory());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    httpsURLConnection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
                }
            }
            setRequestHeader(httpURLConnection, iRequestParam.getHeader());
            Bundle bundle = new Bundle();
            String str = "------------" + RequestBodyHelper.getBoundry();
            if (iRequestParam.getMethod() == IRequestParam.RequestType.POST) {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setUseCaches(false);
                if (iRequestParam.getPostBundle().getByteArray(RequestParam.KEY_PARAM_BODY_BYTE_ARRAY) != null) {
                    bundle.putString("Content-Type", "application/octet-stream");
                } else if (RequestBodyHelper.isMultipartRequest(iRequestParam)) {
                    bundle.putString("Content-Type", "multipart/form-data;boundary=" + str);
                } else {
                    bundle.putString("Content-Type", "application/x-www-form-urlencoded");
                }
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
            } else if (iRequestParam.getMethod() == IRequestParam.RequestType.GET) {
                httpURLConnection.setRequestMethod("GET");
            } else if (iRequestParam.getMethod() == IRequestParam.RequestType.PATCH) {
                httpURLConnection.setRequestMethod("PATCH");
            }
            httpURLConnection.setReadTimeout(iRequestParam.getResponseTimeout());
            httpURLConnection.setConnectTimeout(iRequestParam.getRequestTimeout());
            setRequestHeader(httpURLConnection, bundle);
            httpURLConnection.connect();
            if (iRequestParam.getMethod() != IRequestParam.RequestType.GET) {
                RequestBodyHelper.fillRequestBody(iRequestParam, httpURLConnection, str);
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                return new WbResponse(new WbResponseBody(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength()));
            }
            if (responseCode != 302) {
                if (responseCode != 301) {
                    WbResponseBody wbResponseBody = new WbResponseBody(httpURLConnection.getErrorStream(), (long) httpURLConnection.getContentLength());
                    throw new RequestException("服务器异常" + wbResponseBody.string());
                }
            }
            iRequestParam.setUrl(httpURLConnection.getHeaderField("Location"));
            return request(iRequestParam);
        } catch (MalformedURLException e2) {
            LogUtil.e("weibosdk", e2.toString());
            throw new RequestException("请求异常" + e2.toString());
        } catch (IOException e3) {
            LogUtil.e("weibosdk", e3.toString());
            throw new RequestException("请求异常" + e3.toString());
        }
    }

    private static void setRequestHeader(HttpURLConnection httpURLConnection, Bundle bundle) {
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                httpURLConnection.addRequestProperty(str, String.valueOf(bundle.get(str)));
            }
        }
    }
}
