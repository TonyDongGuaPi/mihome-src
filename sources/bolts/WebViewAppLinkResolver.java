package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alibaba.android.bindingx.core.internal.BindingXConstants;
import com.alipay.sdk.util.i;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver implements AppLinkResolver {
    private static final String b = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    private static final String c = "Prefer-Html-Meta-Tags";
    private static final String d = "al";
    private static final String e = "value";
    private static final String f = "app_name";
    private static final String g = "class";
    private static final String h = "package";
    private static final String i = "url";
    private static final String j = "should_fallback";
    private static final String k = "url";
    private static final String l = "web";
    private static final String m = "android";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Context f526a;

    public WebViewAppLinkResolver(Context context) {
        this.f526a = context;
    }

    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final Capture capture = new Capture();
        final Capture capture2 = new Capture();
        return Task.a(new Callable<Void>() {
            /* renamed from: a */
            public Void call() throws Exception {
                URL url = new URL(uri.toString());
                URLConnection uRLConnection = null;
                while (url != null) {
                    uRLConnection = url.openConnection();
                    boolean z = uRLConnection instanceof HttpURLConnection;
                    if (z) {
                        ((HttpURLConnection) uRLConnection).setInstanceFollowRedirects(true);
                    }
                    uRLConnection.setRequestProperty(WebViewAppLinkResolver.c, WebViewAppLinkResolver.d);
                    uRLConnection.connect();
                    if (z) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
                        if (httpURLConnection.getResponseCode() >= 300 && httpURLConnection.getResponseCode() < 400) {
                            URL url2 = new URL(httpURLConnection.getHeaderField("Location"));
                            httpURLConnection.disconnect();
                            url = url2;
                        }
                    }
                    url = null;
                }
                try {
                    capture.a(WebViewAppLinkResolver.b(uRLConnection));
                    capture2.a(uRLConnection.getContentType());
                    return null;
                } finally {
                    if (uRLConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection) uRLConnection).disconnect();
                    }
                }
            }
        }).d(new Continuation<Void, Task<JSONArray>>() {
            /* renamed from: a */
            public Task<JSONArray> then(Task<Void> task) throws Exception {
                final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                WebView webView = new WebView(WebViewAppLinkResolver.this.f526a);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setNetworkAvailable(false);
                webView.setWebViewClient(new WebViewClient() {
                    private boolean b = false;

                    private void a(WebView webView) {
                        if (!this.b) {
                            this.b = true;
                            webView.loadUrl(WebViewAppLinkResolver.b);
                        }
                    }

                    public void onPageFinished(WebView webView, String str) {
                        super.onPageFinished(webView, str);
                        a(webView);
                    }

                    public void onLoadResource(WebView webView, String str) {
                        super.onLoadResource(webView, str);
                        a(webView);
                    }
                });
                webView.addJavascriptInterface(new Object() {
                    @JavascriptInterface
                    public void setValue(String str) {
                        try {
                            taskCompletionSource.a(new JSONArray(str));
                        } catch (JSONException e) {
                            taskCompletionSource.a((Exception) e);
                        }
                    }
                }, "boltsWebViewAppLinkResolverResult");
                webView.loadDataWithBaseURL(uri.toString(), (String) capture.a(), capture2.a() != null ? ((String) capture2.a()).split(i.b)[0] : null, (String) null, (String) null);
                return taskCompletionSource.a();
            }
        }, Task.b).c(new Continuation<JSONArray, AppLink>() {
            /* renamed from: a */
            public AppLink then(Task<JSONArray> task) throws Exception {
                return WebViewAppLinkResolver.b(WebViewAppLinkResolver.b(task.f()), uri);
            }
        });
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> b(JSONArray jSONArray) throws JSONException {
        Map hashMap = new HashMap();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            String[] split = jSONObject.getString(BindingXConstants.j).split(":");
            if (split[0].equals(d)) {
                Map map = hashMap;
                int i3 = 1;
                while (true) {
                    Map map2 = null;
                    if (i3 >= split.length) {
                        break;
                    }
                    List list = (List) map.get(split[i3]);
                    if (list == null) {
                        list = new ArrayList();
                        map.put(split[i3], list);
                    }
                    if (list.size() > 0) {
                        map2 = (Map) list.get(list.size() - 1);
                    }
                    if (map2 == null || i3 == split.length - 1) {
                        map = new HashMap();
                        list.add(map);
                    } else {
                        map = map2;
                    }
                    i3++;
                }
                if (jSONObject.has("content")) {
                    if (jSONObject.isNull("content")) {
                        map.put("value", (Object) null);
                    } else {
                        map.put("value", jSONObject.getString("content"));
                    }
                }
            }
        }
        return hashMap;
    }

    private static List<Map<String, Object>> a(Map<String, Object> map, String str) {
        List<Map<String, Object>> list = (List) map.get(str);
        return list == null ? Collections.emptyList() : list;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0124, code lost:
        if (java.util.Arrays.asList(new java.lang.String[]{"no", "false", "0"}).contains(((java.lang.String) ((java.util.Map) r14.get(0)).get("value")).toLowerCase()) != false) goto L_0x0128;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static bolts.AppLink b(java.util.Map<java.lang.String, java.lang.Object> r14, android.net.Uri r15) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "android"
            java.lang.Object r1 = r14.get(r1)
            java.util.List r1 = (java.util.List) r1
            if (r1 != 0) goto L_0x0013
            java.util.List r1 = java.util.Collections.emptyList()
        L_0x0013:
            java.util.Iterator r1 = r1.iterator()
        L_0x0017:
            boolean r2 = r1.hasNext()
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L_0x00c7
            java.lang.Object r2 = r1.next()
            java.util.Map r2 = (java.util.Map) r2
            java.lang.String r5 = "url"
            java.util.List r5 = a((java.util.Map<java.lang.String, java.lang.Object>) r2, (java.lang.String) r5)
            java.lang.String r6 = "package"
            java.util.List r6 = a((java.util.Map<java.lang.String, java.lang.Object>) r2, (java.lang.String) r6)
            java.lang.String r7 = "class"
            java.util.List r7 = a((java.util.Map<java.lang.String, java.lang.Object>) r2, (java.lang.String) r7)
            java.lang.String r8 = "app_name"
            java.util.List r2 = a((java.util.Map<java.lang.String, java.lang.Object>) r2, (java.lang.String) r8)
            int r8 = r5.size()
            int r9 = r6.size()
            int r10 = r7.size()
            int r11 = r2.size()
            int r10 = java.lang.Math.max(r10, r11)
            int r9 = java.lang.Math.max(r9, r10)
            int r8 = java.lang.Math.max(r8, r9)
        L_0x005a:
            if (r4 >= r8) goto L_0x0017
            int r9 = r5.size()
            if (r9 <= r4) goto L_0x0070
            java.lang.Object r9 = r5.get(r4)
            java.util.Map r9 = (java.util.Map) r9
            java.lang.String r10 = "value"
            java.lang.Object r9 = r9.get(r10)
            goto L_0x0071
        L_0x0070:
            r9 = r3
        L_0x0071:
            java.lang.String r9 = (java.lang.String) r9
            android.net.Uri r9 = a((java.lang.String) r9)
            int r10 = r6.size()
            if (r10 <= r4) goto L_0x008b
            java.lang.Object r10 = r6.get(r4)
            java.util.Map r10 = (java.util.Map) r10
            java.lang.String r11 = "value"
            java.lang.Object r10 = r10.get(r11)
            goto L_0x008c
        L_0x008b:
            r10 = r3
        L_0x008c:
            java.lang.String r10 = (java.lang.String) r10
            int r11 = r7.size()
            if (r11 <= r4) goto L_0x00a2
            java.lang.Object r11 = r7.get(r4)
            java.util.Map r11 = (java.util.Map) r11
            java.lang.String r12 = "value"
            java.lang.Object r11 = r11.get(r12)
            goto L_0x00a3
        L_0x00a2:
            r11 = r3
        L_0x00a3:
            java.lang.String r11 = (java.lang.String) r11
            int r12 = r2.size()
            if (r12 <= r4) goto L_0x00b9
            java.lang.Object r12 = r2.get(r4)
            java.util.Map r12 = (java.util.Map) r12
            java.lang.String r13 = "value"
            java.lang.Object r12 = r12.get(r13)
            goto L_0x00ba
        L_0x00b9:
            r12 = r3
        L_0x00ba:
            java.lang.String r12 = (java.lang.String) r12
            bolts.AppLink$Target r13 = new bolts.AppLink$Target
            r13.<init>(r10, r11, r9, r12)
            r0.add(r13)
            int r4 = r4 + 1
            goto L_0x005a
        L_0x00c7:
            java.lang.String r1 = "web"
            java.lang.Object r14 = r14.get(r1)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x0148
            int r1 = r14.size()
            if (r1 <= 0) goto L_0x0148
            java.lang.Object r14 = r14.get(r4)
            java.util.Map r14 = (java.util.Map) r14
            java.lang.String r1 = "url"
            java.lang.Object r1 = r14.get(r1)
            java.util.List r1 = (java.util.List) r1
            java.lang.String r2 = "should_fallback"
            java.lang.Object r14 = r14.get(r2)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x0127
            int r2 = r14.size()
            if (r2 <= 0) goto L_0x0127
            java.lang.Object r14 = r14.get(r4)
            java.util.Map r14 = (java.util.Map) r14
            java.lang.String r2 = "value"
            java.lang.Object r14 = r14.get(r2)
            java.lang.String r14 = (java.lang.String) r14
            r2 = 3
            java.lang.String[] r2 = new java.lang.String[r2]
            java.lang.String r5 = "no"
            r2[r4] = r5
            java.lang.String r5 = "false"
            r6 = 1
            r2[r6] = r5
            r5 = 2
            java.lang.String r6 = "0"
            r2[r5] = r6
            java.util.List r2 = java.util.Arrays.asList(r2)
            java.lang.String r14 = r14.toLowerCase()
            boolean r14 = r2.contains(r14)
            if (r14 == 0) goto L_0x0127
            goto L_0x0128
        L_0x0127:
            r3 = r15
        L_0x0128:
            if (r3 == 0) goto L_0x0146
            if (r1 == 0) goto L_0x0146
            int r14 = r1.size()
            if (r14 <= 0) goto L_0x0146
            java.lang.Object r14 = r1.get(r4)
            java.util.Map r14 = (java.util.Map) r14
            java.lang.String r1 = "value"
            java.lang.Object r14 = r14.get(r1)
            java.lang.String r14 = (java.lang.String) r14
            android.net.Uri r14 = a((java.lang.String) r14)
            goto L_0x0149
        L_0x0146:
            r14 = r3
            goto L_0x0149
        L_0x0148:
            r14 = r15
        L_0x0149:
            bolts.AppLink r1 = new bolts.AppLink
            r1.<init>(r15, r0, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.WebViewAppLinkResolver.b(java.util.Map, android.net.Uri):bolts.AppLink");
    }

    private static Uri a(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    /* access modifiers changed from: private */
    public static String b(URLConnection uRLConnection) throws IOException {
        InputStream inputStream;
        int i2;
        if (uRLConnection instanceof HttpURLConnection) {
            try {
                inputStream = uRLConnection.getInputStream();
            } catch (Exception unused) {
                inputStream = ((HttpURLConnection) uRLConnection).getErrorStream();
            }
        } else {
            inputStream = uRLConnection.getInputStream();
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                i2 = 0;
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String contentEncoding = uRLConnection.getContentEncoding();
            if (contentEncoding == null) {
                String[] split = uRLConnection.getContentType().split(i.b);
                int length = split.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    String trim = split[i2].trim();
                    if (trim.startsWith("charset=")) {
                        contentEncoding = trim.substring("charset=".length());
                        break;
                    }
                    i2++;
                }
                if (contentEncoding == null) {
                    contentEncoding = "UTF-8";
                }
            }
            return new String(byteArrayOutputStream.toByteArray(), contentEncoding);
        } finally {
            inputStream.close();
        }
    }
}
