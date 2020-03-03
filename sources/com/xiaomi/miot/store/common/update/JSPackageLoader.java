package com.xiaomi.miot.store.common.update;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.xiaomi.miot.store.utils.IOUtils;
import com.xiaomi.youpin.cookie.YouPinCookieHandler;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.log.NetMonitor;
import com.xiaomi.youpin.youpin_common.UserAgent;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSPackageLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11400a = "JSPackageLoader";
    private static final String b = "Content-Type";
    private static final String c = "Etag";
    private static final String d = "Ctag";
    private static final String e = "application/bspatch";

    public interface INetworkHandler {
        void a(OkHttpClient okHttpClient);

        void a(Response response);
    }

    public static IPackageLoader a(final String str, final Map<String, String> map, final INetworkHandler iNetworkHandler) {
        return new IPackageLoader() {
            public UpdateResponse a(String str, String str2) {
                FileOutputStream fileOutputStream;
                InputStream inputStream;
                InputStream inputStream2 = null;
                try {
                    OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(new YouPinCookieHandler())).addNetworkInterceptor(new Interceptor() {
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", UserAgent.d()).addHeader("Connection", "close").build());
                        }
                    });
                    NetMonitor.addNetworkInterceptor(addNetworkInterceptor);
                    OkHttpClient build = addNetworkInterceptor.build();
                    iNetworkHandler.a(build);
                    Request.Builder url = new Request.Builder().url(str);
                    for (String str3 : map.keySet()) {
                        url.header(str3, (String) map.get(str3));
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        url.header(JSPackageLoader.c, str2);
                    }
                    Request build2 = url.build();
                    Response execute = build.newCall(build2).execute();
                    iNetworkHandler.a(execute);
                    LogUtils.d(JSPackageLoader.f11400a, "request headers:" + build2.headers().toString() + ",response headers:" + execute.headers());
                    if (execute.code() == 304) {
                        LogUtils.d(JSPackageLoader.f11400a, "content does not modified:" + str2);
                        UpdateResponse updateResponse = new UpdateResponse(str2);
                        IOUtils.a((Closeable) null);
                        IOUtils.a((Closeable) null);
                        return updateResponse;
                    } else if (!execute.isSuccessful()) {
                        LogUtils.d(JSPackageLoader.f11400a, "request not success:" + execute.code());
                        UpdateResponse updateResponse2 = new UpdateResponse(str2);
                        IOUtils.a((Closeable) null);
                        IOUtils.a((Closeable) null);
                        return updateResponse2;
                    } else {
                        String header = execute.header(JSPackageLoader.c);
                        if (TextUtils.isEmpty(header)) {
                            LogUtils.d(JSPackageLoader.f11400a, "cannot get newEtag");
                            UpdateResponse updateResponse3 = new UpdateResponse(str2);
                            IOUtils.a((Closeable) null);
                            IOUtils.a((Closeable) null);
                            return updateResponse3;
                        }
                        if (header.startsWith("\"")) {
                            header = header.substring(1, header.length() - 1);
                        }
                        if (TextUtils.equals(str2, header)) {
                            LogUtils.d(JSPackageLoader.f11400a, "newEtag equals oldEtag:" + str2);
                            UpdateResponse updateResponse4 = new UpdateResponse(str2);
                            IOUtils.a((Closeable) null);
                            IOUtils.a((Closeable) null);
                            return updateResponse4;
                        }
                        File file = new File(str, header);
                        if (file.exists()) {
                            file.delete();
                        }
                        InputStream byteStream = execute.body().byteStream();
                        try {
                            fileOutputStream = new FileOutputStream(file);
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = null;
                            Exception exc = e;
                            inputStream = byteStream;
                            e = exc;
                            try {
                                LogUtils.d(JSPackageLoader.f11400a, "download js package failed" + e.toString());
                                IOUtils.a((Closeable) inputStream);
                                IOUtils.a((Closeable) fileOutputStream);
                                return null;
                            } catch (Throwable th) {
                                th = th;
                                inputStream2 = inputStream;
                                IOUtils.a((Closeable) inputStream2);
                                IOUtils.a((Closeable) fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = null;
                            inputStream2 = byteStream;
                            th = th;
                            IOUtils.a((Closeable) inputStream2);
                            IOUtils.a((Closeable) fileOutputStream);
                            throw th;
                        }
                        try {
                            boolean equals = TextUtils.equals(execute.header("Content-Type"), JSPackageLoader.e);
                            IOUtils.a(byteStream, (OutputStream) fileOutputStream);
                            fileOutputStream.flush();
                            LogUtils.d(JSPackageLoader.f11400a, String.format("download file: %s", new Object[]{file.getAbsolutePath()}));
                            IOUtils.a((Closeable) byteStream);
                            IOUtils.a((Closeable) fileOutputStream);
                            return new UpdateResponse(header, header, equals);
                        } catch (Exception e2) {
                            e = e2;
                            Exception exc2 = e;
                            inputStream = byteStream;
                            e = exc2;
                            LogUtils.d(JSPackageLoader.f11400a, "download js package failed" + e.toString());
                            IOUtils.a((Closeable) inputStream);
                            IOUtils.a((Closeable) fileOutputStream);
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStream2 = byteStream;
                            th = th;
                            IOUtils.a((Closeable) inputStream2);
                            IOUtils.a((Closeable) fileOutputStream);
                            throw th;
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    inputStream = null;
                    fileOutputStream = null;
                    LogUtils.d(JSPackageLoader.f11400a, "download js package failed" + e.toString());
                    IOUtils.a((Closeable) inputStream);
                    IOUtils.a((Closeable) fileOutputStream);
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    IOUtils.a((Closeable) inputStream2);
                    IOUtils.a((Closeable) fileOutputStream);
                    throw th;
                }
            }
        };
    }

    public static IPackageLoader a(final Context context, final String str) {
        return new IPackageLoader() {
            public UpdateResponse a(String str, String str2) {
                FileOutputStream fileOutputStream;
                InputStream inputStream;
                InputStream inputStream2 = null;
                try {
                    AssetManager assets = context.getApplicationContext().getAssets();
                    String[] list = assets.list(str);
                    if (list != null) {
                        if (list.length != 0) {
                            String str3 = list[0];
                            String str4 = str + File.separator + str3;
                            inputStream = assets.open(str + File.separator + str3);
                            try {
                                File unused = JSPackageLoader.b(str4);
                                fileOutputStream = new FileOutputStream(str4);
                            } catch (Exception e) {
                                e = e;
                                fileOutputStream = null;
                                try {
                                    LogUtils.d(JSPackageLoader.f11400a, "download js package from assets failed" + e.toString());
                                    IOUtils.a((Closeable) inputStream);
                                    IOUtils.a((Closeable) fileOutputStream);
                                    return null;
                                } catch (Throwable th) {
                                    th = th;
                                    inputStream2 = inputStream;
                                    IOUtils.a((Closeable) inputStream2);
                                    IOUtils.a((Closeable) fileOutputStream);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = null;
                                inputStream2 = inputStream;
                                IOUtils.a((Closeable) inputStream2);
                                IOUtils.a((Closeable) fileOutputStream);
                                throw th;
                            }
                            try {
                                IOUtils.a(inputStream, (OutputStream) fileOutputStream);
                                UpdateResponse updateResponse = new UpdateResponse(str3, str3, false);
                                IOUtils.a((Closeable) inputStream);
                                IOUtils.a((Closeable) fileOutputStream);
                                return updateResponse;
                            } catch (Exception e2) {
                                e = e2;
                                LogUtils.d(JSPackageLoader.f11400a, "download js package from assets failed" + e.toString());
                                IOUtils.a((Closeable) inputStream);
                                IOUtils.a((Closeable) fileOutputStream);
                                return null;
                            }
                        }
                    }
                    IOUtils.a((Closeable) null);
                    IOUtils.a((Closeable) null);
                    return null;
                } catch (Exception e3) {
                    e = e3;
                    inputStream = null;
                    fileOutputStream = null;
                    LogUtils.d(JSPackageLoader.f11400a, "download js package from assets failed" + e.toString());
                    IOUtils.a((Closeable) inputStream);
                    IOUtils.a((Closeable) fileOutputStream);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    IOUtils.a((Closeable) inputStream2);
                    IOUtils.a((Closeable) fileOutputStream);
                    throw th;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static File b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e2) {
            LogUtils.d(f11400a, "create file failed:" + str + "," + e2.toString());
        }
        return file;
    }
}
