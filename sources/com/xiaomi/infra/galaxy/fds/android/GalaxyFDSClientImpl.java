package com.xiaomi.infra.galaxy.fds.android;

import android.util.Log;
import com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential;
import com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException;
import com.xiaomi.infra.galaxy.fds.android.model.FDSObject;
import com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata;
import com.xiaomi.infra.galaxy.fds.android.model.ProgressListener;
import com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult;
import com.xiaomi.infra.galaxy.fds.android.model.ThumbParam;
import com.xiaomi.infra.galaxy.fds.android.model.UserParam;
import com.xiaomi.infra.galaxy.fds.android.util.Args;
import com.xiaomi.infra.galaxy.fds.android.util.RequestFactory;
import com.xiaomi.infra.galaxy.fds.android.util.Util;
import com.xiaomi.infra.galaxy.fds.model.HttpMethod;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class GalaxyFDSClientImpl implements GalaxyFDSClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10131a = "GalaxyFDSClientImpl";
    private static final String b = "http";
    private static final String c = "https";
    private static final boolean d;
    private final FDSClientConfiguration e;
    private final HttpClient f = a(this.e);
    private ThreadPoolExecutor g;

    static {
        String property = System.getProperty("java.runtime.name");
        if (property == null || !property.equals("android runtime")) {
            d = true;
        } else {
            d = false;
        }
    }

    public GalaxyFDSClientImpl(FDSClientConfiguration fDSClientConfiguration) {
        this.e = fDSClientConfiguration;
        this.g = new ThreadPoolExecutor(fDSClientConfiguration.f(), fDSClientConfiguration.g(), (long) fDSClientConfiguration.h(), TimeUnit.SECONDS, new ArrayBlockingQueue(fDSClientConfiguration.i(), true), new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "FDS-multipart-upload-thread");
            }
        });
    }

    @Deprecated
    public GalaxyFDSClientImpl(String str, GalaxyFDSCredential galaxyFDSCredential, FDSClientConfiguration fDSClientConfiguration) {
        this.e = fDSClientConfiguration;
        this.e.a(galaxyFDSCredential);
    }

    private HttpClient a(FDSClientConfiguration fDSClientConfiguration) {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, fDSClientConfiguration.c());
        HttpConnectionParams.setSoTimeout(basicHttpParams, fDSClientConfiguration.b());
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        int i = fDSClientConfiguration.d()[0];
        int i2 = fDSClientConfiguration.d()[1];
        if (i > 0 || i2 > 0) {
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, Math.max(i, i2));
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        if (fDSClientConfiguration.o()) {
            SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            schemeRegistry.register(new Scheme("https", socketFactory, 443));
        }
        return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    private boolean a(List<UserParam> list) {
        if (list != null) {
            for (UserParam userParam : list) {
                if (userParam instanceof ThumbParam) {
                    return true;
                }
            }
        }
        return false;
    }

    public FDSObject a(String str, String str2) throws GalaxyFDSClientException {
        return a(str, str2, 0, (List<UserParam>) null);
    }

    public FDSObject a(String str, String str2, long j, List<UserParam> list) throws GalaxyFDSClientException {
        return a(str, str2, j, list, (ProgressListener) null);
    }

    public FDSObject a(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException {
        Args.a(str, "bucket name");
        Args.a(str, "bucket name");
        Args.a(str2, "object name");
        Args.a(str2, "object name");
        StringBuilder sb = new StringBuilder();
        sb.append(this.e.v());
        sb.append("/" + str + "/" + str2);
        return a(sb.toString(), j, list, progressListener);
    }

    @Deprecated
    public FDSObject a(String str, String str2, long j, List<UserParam> list, ProgressListener progressListener, boolean z) throws GalaxyFDSClientException {
        return a(str, str2, j, list, progressListener);
    }

    /* JADX WARNING: type inference failed for: r11v5, types: [java.lang.Throwable, com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException] */
    /* JADX WARNING: type inference failed for: r12v10, types: [java.lang.Throwable, com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.infra.galaxy.fds.android.model.FDSObject a(java.lang.String r9, long r10, java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam> r12, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener r13) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            r8 = this;
            java.lang.String r0 = "URI"
            com.xiaomi.infra.galaxy.fds.android.util.Args.a(r9, (java.lang.String) r0)
            java.lang.String r0 = "offset in content"
            com.xiaomi.infra.galaxy.fds.android.util.Args.b((long) r10, (java.lang.String) r0)
            r0 = -1
            if (r12 == 0) goto L_0x0041
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r9)
            java.util.Iterator r9 = r12.iterator()
        L_0x0016:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x003d
            java.lang.Object r2 = r9.next()
            com.xiaomi.infra.galaxy.fds.android.model.UserParam r2 = (com.xiaomi.infra.galaxy.fds.android.model.UserParam) r2
            java.lang.String r3 = "?"
            int r3 = r1.indexOf(r3)
            if (r3 != r0) goto L_0x0030
            r3 = 63
            r1.append(r3)
            goto L_0x0035
        L_0x0030:
            r3 = 38
            r1.append(r3)
        L_0x0035:
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            goto L_0x0016
        L_0x003d:
            java.lang.String r9 = r1.toString()
        L_0x0041:
            java.net.URI r1 = new java.net.URI     // Catch:{ URISyntaxException -> 0x015f }
            r1.<init>(r9)     // Catch:{ URISyntaxException -> 0x015f }
            java.lang.String r1 = r1.getPath()     // Catch:{ URISyntaxException -> 0x015f }
            r2 = 0
            r3 = 47
            r4 = 1
            int r3 = r1.indexOf(r3, r4)     // Catch:{ URISyntaxException -> 0x015f }
            if (r3 == r0) goto L_0x0157
            java.lang.String r0 = r1.substring(r2, r3)     // Catch:{ URISyntaxException -> 0x015f }
            int r3 = r3 + r4
            java.lang.String r1 = r1.substring(r3)     // Catch:{ URISyntaxException -> 0x015f }
            r2 = 0
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            r3.<init>()     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            r4 = 0
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x008a
            boolean r12 = r8.a((java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam>) r12)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            if (r12 != 0) goto L_0x008a
            java.lang.String r12 = "Range"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            r4.<init>()     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            java.lang.String r5 = "bytes="
            r4.append(r5)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            r4.append(r10)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            java.lang.String r10 = "-"
            r4.append(r10)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            java.lang.String r10 = r4.toString()     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            r3.put(r12, r10)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
        L_0x008a:
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r10 = r8.e     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential r10 = r10.j()     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            com.xiaomi.infra.galaxy.fds.model.HttpMethod r11 = com.xiaomi.infra.galaxy.fds.model.HttpMethod.GET     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            org.apache.http.client.methods.HttpUriRequest r10 = com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.a(r9, r10, r11, r3)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            org.apache.http.client.HttpClient r11 = r8.f     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            org.apache.http.HttpResponse r10 = r11.execute(r10)     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            org.apache.http.HttpEntity r11 = r10.getEntity()     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            java.io.InputStream r11 = r11.getContent()     // Catch:{ IOException -> 0x0112, all -> 0x010f }
            org.apache.http.StatusLine r12 = r10.getStatusLine()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            int r12 = r12.getStatusCode()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r12 == r3) goto L_0x00f0
            r3 = 206(0xce, float:2.89E-43)
            if (r12 != r3) goto L_0x00b5
            goto L_0x00f0
        L_0x00b5:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r12 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r13.<init>()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.String r3 = "Unable to get object["
            r13.append(r3)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r13.append(r0)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.String r3 = "/"
            r13.append(r3)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r13.append(r1)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.String r3 = "] from URI :"
            r13.append(r3)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r13.append(r9)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.String r3 = ". Cause:"
            r13.append(r3)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            org.apache.http.StatusLine r10 = r10.getStatusLine()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r13.append(r10)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            java.lang.String r10 = r13.toString()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r12.<init>((java.lang.String) r10)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            throw r12     // Catch:{ IOException -> 0x00ee, all -> 0x00ec }
        L_0x00ec:
            r9 = move-exception
            goto L_0x014e
        L_0x00ee:
            r10 = move-exception
            goto L_0x010d
        L_0x00f0:
            com.xiaomi.infra.galaxy.fds.android.model.FDSObject r12 = new com.xiaomi.infra.galaxy.fds.android.model.FDSObject     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r12.<init>(r0, r1)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            org.apache.http.Header[] r10 = r10.getAllHeaders()     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata r10 = com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata.a((org.apache.http.Header[]) r10)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream r3 = new com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r3.<init>(r11, r10, r13)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r12.a((java.io.InputStream) r3)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            r12.a((com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata) r10)     // Catch:{ IOException -> 0x010b, all -> 0x0109 }
            return r12
        L_0x0109:
            r9 = move-exception
            goto L_0x014f
        L_0x010b:
            r10 = move-exception
            r12 = r2
        L_0x010d:
            r2 = r11
            goto L_0x0114
        L_0x010f:
            r9 = move-exception
            r11 = r2
            goto L_0x014f
        L_0x0112:
            r10 = move-exception
            r12 = r2
        L_0x0114:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r11 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ all -> 0x014c }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x014c }
            r13.<init>()     // Catch:{ all -> 0x014c }
            java.lang.String r3 = "Unable to get object["
            r13.append(r3)     // Catch:{ all -> 0x014c }
            r13.append(r0)     // Catch:{ all -> 0x014c }
            java.lang.String r0 = "/"
            r13.append(r0)     // Catch:{ all -> 0x014c }
            r13.append(r1)     // Catch:{ all -> 0x014c }
            java.lang.String r0 = "] from URI :"
            r13.append(r0)     // Catch:{ all -> 0x014c }
            r13.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = " Exception:"
            r13.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = r10.getMessage()     // Catch:{ all -> 0x014c }
            r13.append(r9)     // Catch:{ all -> 0x014c }
            java.lang.String r9 = r13.toString()     // Catch:{ all -> 0x014c }
            r11.<init>(r9, r10)     // Catch:{ all -> 0x014c }
            throw r11     // Catch:{ all -> 0x0147 }
        L_0x0147:
            r9 = move-exception
            r7 = r2
            r2 = r11
            r11 = r7
            goto L_0x014f
        L_0x014c:
            r9 = move-exception
            r11 = r2
        L_0x014e:
            r2 = r12
        L_0x014f:
            if (r2 == 0) goto L_0x0156
            if (r11 == 0) goto L_0x0156
            r11.close()     // Catch:{ IOException -> 0x0156 }
        L_0x0156:
            throw r9
        L_0x0157:
            java.net.URISyntaxException r10 = new java.net.URISyntaxException     // Catch:{ URISyntaxException -> 0x015f }
            java.lang.String r11 = "not a valid object URI"
            r10.<init>(r9, r11)     // Catch:{ URISyntaxException -> 0x015f }
            throw r10     // Catch:{ URISyntaxException -> 0x015f }
        L_0x015f:
            r10 = move-exception
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r11 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Invalid URI, can't parse bucket nameand object name form it:"
            r12.append(r13)
            r12.append(r9)
            java.lang.String r9 = r12.toString()
            r11.<init>(r9, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.a(java.lang.String, long, java.util.List, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener):com.xiaomi.infra.galaxy.fds.android.model.FDSObject");
    }

    public ObjectMetadata a(String str, String str2, File file) throws GalaxyFDSClientException {
        return a(str, str2, file, (List<UserParam>) null);
    }

    public ObjectMetadata a(String str, String str2, File file, List<UserParam> list) throws GalaxyFDSClientException {
        return a(str, str2, file, list, (ProgressListener) null);
    }

    public ObjectMetadata a(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException {
        boolean z;
        long j;
        File file2 = file;
        Args.a(file2, "Destination file");
        boolean a2 = a(list);
        int i = 0;
        while (true) {
            z = i != 0 && !a2;
            if (!z) {
                j = 0;
                break;
            }
            try {
                j = file.length();
                break;
            } catch (GalaxyFDSClientException e2) {
                i++;
                if (i >= this.e.a()) {
                    throw e2;
                } else if (!d) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Retry the download of object:");
                    sb.append(str2);
                    sb.append(" bucket");
                    sb.append(":");
                    String str3 = str;
                    sb.append(str);
                    sb.append(" to file: ");
                    sb.append(file.getAbsolutePath());
                    sb.append(" cause:");
                    sb.append(Util.a((Exception) e2));
                    Log.i(f10131a, sb.toString());
                } else {
                    String str4 = str;
                    String str5 = str2;
                }
            }
        }
        FDSObject a3 = a(str, str2, j, list, progressListener);
        Util.a(a3, file2, z);
        return a3.c();
    }

    @Deprecated
    public ObjectMetadata a(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener, boolean z) throws GalaxyFDSClientException {
        return a(str, str2, file, list, progressListener);
    }

    public ObjectMetadata a(String str, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException {
        boolean z;
        long j;
        File file2 = file;
        Args.a(file, "Destination file");
        boolean a2 = a(list);
        int i = 0;
        while (true) {
            z = i != 0 && !a2;
            if (!z) {
                j = 0;
                break;
            }
            try {
                j = file.length();
                break;
            } catch (GalaxyFDSClientException e2) {
                i++;
                if (i >= this.e.a()) {
                    throw e2;
                } else if (!d) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Retry the download of object:");
                    String str2 = str;
                    sb.append(str);
                    sb.append(" to file: ");
                    sb.append(file.getAbsolutePath());
                    sb.append(" cause:");
                    sb.append(Util.a((Exception) e2));
                    Log.i(f10131a, sb.toString());
                } else {
                    String str3 = str;
                }
            }
        }
        FDSObject a3 = a(str, j, list, progressListener);
        Util.a(a3, file, z);
        return a3.c();
    }

    public PutObjectResult b(String str, String str2, File file) throws GalaxyFDSClientException {
        return b(str, str2, file, (List<UserParam>) null);
    }

    public PutObjectResult b(String str, String str2, File file, List<UserParam> list) throws GalaxyFDSClientException {
        return b(str, str2, file, list, (ProgressListener) null);
    }

    public PutObjectResult b(String str, String str2, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException {
        Args.a(file, "file");
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.a(file.length());
            objectMetadata.b(Util.a(file));
            objectMetadata.a(new Date(file.lastModified()));
            return a(str, str2, (InputStream) bufferedInputStream, objectMetadata, list, progressListener);
        } catch (FileNotFoundException e2) {
            throw new GalaxyFDSClientException("Unable to find the file to be uploaded:" + file.getAbsolutePath(), e2);
        }
    }

    public PutObjectResult a(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata) throws GalaxyFDSClientException {
        return a(str, str2, inputStream, objectMetadata, (List<UserParam>) null);
    }

    public PutObjectResult a(String str, String str2, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list) throws GalaxyFDSClientException {
        return a(str, str2, inputStream, objectMetadata, list, (ProgressListener) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e4, code lost:
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00e9, code lost:
        r1 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ec, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ed, code lost:
        r6 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        a(r24, r14, r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fb, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fd, code lost:
        r1 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0108, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00e8 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fd A[Catch:{ all -> 0x00fb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult a(java.lang.String r24, java.lang.String r25, java.io.InputStream r26, com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata r27, java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam> r28, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener r29) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            r23 = this;
            r10 = r23
            r11 = r24
            r0 = r26
            r12 = r27
            java.lang.String r1 = "bucket name"
            com.xiaomi.infra.galaxy.fds.android.util.Args.a(r11, (java.lang.String) r1)
            java.lang.String r1 = "bucket name"
            com.xiaomi.infra.galaxy.fds.android.util.Args.a(r11, (java.lang.String) r1)
            java.lang.String r1 = "input stream"
            com.xiaomi.infra.galaxy.fds.android.util.Args.a(r0, (java.lang.String) r1)
            java.lang.String r1 = "metadata"
            com.xiaomi.infra.galaxy.fds.android.util.Args.a(r12, (java.lang.String) r1)
            long r1 = r27.b()
            java.lang.String r3 = "content length"
            com.xiaomi.infra.galaxy.fds.android.util.Args.b((long) r1, (java.lang.String) r3)
            java.lang.String r3 = r27.d()
            if (r3 != 0) goto L_0x0030
            java.lang.String r3 = com.xiaomi.infra.galaxy.fds.android.util.Consts.g
            r12.b(r3)
        L_0x0030:
            r3 = 0
            com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream r13 = new com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream
            r4 = r29
            r13.<init>(r0, r12, r4)
            long r4 = r27.b()     // Catch:{ Exception -> 0x00ec, all -> 0x00e8 }
            r6 = r25
            com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult r0 = r10.a((java.lang.String) r11, (java.lang.String) r6, (long) r4)     // Catch:{ Exception -> 0x00e6, all -> 0x00e8 }
            java.lang.String r14 = r0.b()     // Catch:{ Exception -> 0x00e6, all -> 0x00e8 }
            java.lang.String r15 = r0.c()     // Catch:{ Exception -> 0x00e3, all -> 0x00e8 }
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r0 = r10.e     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            int r0 = r0.e()     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            long r3 = (long) r0     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            long r3 = r3 + r1
            r5 = 1
            long r3 = r3 - r5
            int r3 = (int) r3     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            int r9 = r3 / r0
            int r1 = (int) r1     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r7.<init>(r9)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r16 = 1
            r5 = r1
            r6 = 1
        L_0x0067:
            if (r6 > r9) goto L_0x00af
            int r4 = java.lang.Math.min(r0, r5)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            byte[] r3 = new byte[r4]     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r1 = 0
            r13.read(r3, r1, r4)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            java.util.concurrent.ThreadPoolExecutor r2 = r10.g     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl$2 r1 = new com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl$2     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r17 = r1
            r1 = r17
            r18 = r0
            r0 = r2
            r2 = r23
            r19 = r3
            r3 = r15
            r20 = r4
            r4 = r24
            r21 = r5
            r5 = r14
            r22 = r6
            r12 = r7
            r7 = r19
            r10 = r8
            r8 = r27
            r11 = r9
            r9 = r20
            r1.<init>(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            java.util.concurrent.Future r0 = r0.submit(r1)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r10.add(r0)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            int r5 = r21 - r20
            int r6 = r22 + 1
            r8 = r10
            r9 = r11
            r7 = r12
            r0 = r18
            r10 = r23
            r11 = r24
            r12 = r27
            goto L_0x0067
        L_0x00af:
            r12 = r7
            r10 = r8
            r11 = r9
            r0 = 1
        L_0x00b3:
            if (r0 > r11) goto L_0x00c7
            int r1 = r0 + -1
            java.lang.Object r1 = r10.get(r1)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            java.util.concurrent.Future r1 = (java.util.concurrent.Future) r1     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r12.add(r1)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            int r0 = r0 + 1
            goto L_0x00b3
        L_0x00c7:
            com.xiaomi.infra.galaxy.fds.android.model.UploadPartResultList r6 = new com.xiaomi.infra.galaxy.fds.android.model.UploadPartResultList     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r6.<init>()     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r6.a(r12)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r1 = r23
            r2 = r15
            r3 = r24
            r4 = r14
            r5 = r27
            r7 = r28
            com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult r0 = r1.a((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata) r5, (com.xiaomi.infra.galaxy.fds.android.model.UploadPartResultList) r6, (java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam>) r7)     // Catch:{ Exception -> 0x00e1, all -> 0x00e8 }
            r13.close()     // Catch:{ IOException -> 0x00e0 }
        L_0x00e0:
            return r0
        L_0x00e1:
            r0 = move-exception
            goto L_0x00f1
        L_0x00e3:
            r0 = move-exception
            r15 = r3
            goto L_0x00f1
        L_0x00e6:
            r0 = move-exception
            goto L_0x00ef
        L_0x00e8:
            r0 = move-exception
            r1 = r23
            goto L_0x0105
        L_0x00ec:
            r0 = move-exception
            r6 = r25
        L_0x00ef:
            r15 = r3
            r14 = r6
        L_0x00f1:
            if (r15 == 0) goto L_0x00fd
            r1 = r23
            r2 = r24
            r1.a((java.lang.String) r2, (java.lang.String) r14, (java.lang.String) r15)     // Catch:{ all -> 0x00fb }
            goto L_0x00ff
        L_0x00fb:
            r0 = move-exception
            goto L_0x0105
        L_0x00fd:
            r1 = r23
        L_0x00ff:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r2 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ all -> 0x00fb }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x00fb }
            throw r2     // Catch:{ all -> 0x00fb }
        L_0x0105:
            r13.close()     // Catch:{ IOException -> 0x0108 }
        L_0x0108:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.a(java.lang.String, java.lang.String, java.io.InputStream, com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata, java.util.List, com.xiaomi.infra.galaxy.fds.android.model.ProgressListener):com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x010b A[SYNTHETIC, Splitter:B:42:0x010b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult a(java.lang.String r5, java.lang.String r6, long r7) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r1 = r4.e
            java.lang.String r1 = r1.w()
            r0.append(r1)
            java.lang.String r1 = "/"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = "/"
            r0.append(r1)
            if (r6 != 0) goto L_0x0020
            java.lang.String r1 = ""
            goto L_0x0021
        L_0x0020:
            r1 = r6
        L_0x0021:
            r0.append(r1)
            java.lang.String r1 = "?uploads"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ IOException -> 0x00f1 }
            r2.<init>()     // Catch:{ IOException -> 0x00f1 }
            java.lang.String r3 = "x-xiaomi-estimated-object-size"
            java.lang.String r7 = java.lang.Long.toString(r7)     // Catch:{ IOException -> 0x00f1 }
            r2.put(r3, r7)     // Catch:{ IOException -> 0x00f1 }
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r7 = r4.e     // Catch:{ IOException -> 0x00f1 }
            com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential r7 = r7.j()     // Catch:{ IOException -> 0x00f1 }
            if (r6 != 0) goto L_0x0047
            com.xiaomi.infra.galaxy.fds.model.HttpMethod r8 = com.xiaomi.infra.galaxy.fds.model.HttpMethod.POST     // Catch:{ IOException -> 0x00f1 }
            goto L_0x0049
        L_0x0047:
            com.xiaomi.infra.galaxy.fds.model.HttpMethod r8 = com.xiaomi.infra.galaxy.fds.model.HttpMethod.PUT     // Catch:{ IOException -> 0x00f1 }
        L_0x0049:
            org.apache.http.client.methods.HttpUriRequest r7 = com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.a(r0, r7, r8, r2)     // Catch:{ IOException -> 0x00f1 }
            org.apache.http.client.HttpClient r8 = r4.f     // Catch:{ IOException -> 0x00f1 }
            org.apache.http.HttpResponse r7 = r8.execute(r7)     // Catch:{ IOException -> 0x00f1 }
            org.apache.http.HttpEntity r8 = r7.getEntity()     // Catch:{ IOException -> 0x00f1 }
            java.io.InputStream r8 = r8.getContent()     // Catch:{ IOException -> 0x00f1 }
            org.apache.http.StatusLine r1 = r7.getStatusLine()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            int r1 = r1.getStatusCode()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r1 != r2) goto L_0x00b2
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r1.<init>()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.Class<com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult> r2 = com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult.class
            java.lang.Object r7 = r1.fromJson((java.io.Reader) r7, r2)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult r7 = (com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult) r7     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            if (r7 == 0) goto L_0x0093
            java.lang.String r1 = r7.c()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            if (r1 == 0) goto L_0x0093
            java.lang.String r1 = r7.b()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            if (r1 == 0) goto L_0x0093
            java.lang.String r1 = r7.a()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            if (r1 == 0) goto L_0x0093
            if (r8 == 0) goto L_0x0092
            r8.close()     // Catch:{ IOException -> 0x0092 }
        L_0x0092:
            return r7
        L_0x0093:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r7 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r1.<init>()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r2 = "Fail to parse the result of init multipart upload. bucket name:"
            r1.append(r2)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r1.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = ", object name:"
            r1.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r1.append(r6)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = r1.toString()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r7.<init>((java.lang.String) r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            throw r7     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
        L_0x00b2:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r1 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r2.<init>()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r3 = "Unable to upload object["
            r2.append(r3)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r2.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = "/"
            r2.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r2.append(r6)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = "] to URI :"
            r2.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r2.append(r0)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = ". Fail to initiate multipart upload: "
            r2.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            org.apache.http.StatusLine r5 = r7.getStatusLine()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r2.append(r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            java.lang.String r5 = r2.toString()     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            r1.<init>((java.lang.String) r5)     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
            throw r1     // Catch:{ IOException -> 0x00eb, all -> 0x00e9 }
        L_0x00e9:
            r5 = move-exception
            goto L_0x0109
        L_0x00eb:
            r5 = move-exception
            r1 = r8
            goto L_0x00f2
        L_0x00ee:
            r5 = move-exception
            r8 = r1
            goto L_0x0109
        L_0x00f1:
            r5 = move-exception
        L_0x00f2:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r6 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ all -> 0x00ee }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ee }
            r7.<init>()     // Catch:{ all -> 0x00ee }
            java.lang.String r8 = "Fail to initiate multipart upload. URI:"
            r7.append(r8)     // Catch:{ all -> 0x00ee }
            r7.append(r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00ee }
            r6.<init>(r7, r5)     // Catch:{ all -> 0x00ee }
            throw r6     // Catch:{ all -> 0x00ee }
        L_0x0109:
            if (r8 == 0) goto L_0x010e
            r8.close()     // Catch:{ IOException -> 0x010e }
        L_0x010e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.a(java.lang.String, java.lang.String, long):com.xiaomi.infra.galaxy.fds.android.model.InitMultipartUploadResult");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:17|18|19|20|21|22|(5:24|(1:36)(3:28|29|(3:81|(2:32|33)|34))|37|38|39)(3:82|40|41)|80) */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0150, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0153, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0158, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x017c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x018b, code lost:
        if (d == false) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x018d, code lost:
        android.util.Log.i(f10131a, "Retry the upload of object:" + r4 + " bucket" + ":" + r3 + " upload id:" + r2 + " part number:" + r5 + " cause:" + com.xiaomi.infra.galaxy.fds.android.util.Util.a((java.lang.Exception) r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01cc, code lost:
        if (r9 != null) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0087 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0087 A[LOOP:1: B:17:0x0087->B:80:0x0087, LOOP_START, PHI: r9 r15 
      PHI: (r9v2 java.io.InputStream) = (r9v1 java.io.InputStream), (r9v3 java.io.InputStream) binds: [B:15:0x0085, B:80:0x0087] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r15v1 int) = (r15v0 int), (r15v2 int) binds: [B:15:0x0085, B:80:0x0087] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC, Splitter:B:17:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0150 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:21:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0189 A[Catch:{ all -> 0x0179 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01d3 A[EDGE_INSN: B:71:0x01d3->B:72:? ?: BREAK  , SYNTHETIC, Splitter:B:71:0x01d3] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01d6 A[SYNTHETIC, Splitter:B:74:0x01d6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult a(java.lang.String r17, java.lang.String r18, java.lang.String r19, int r20, com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream r21, long r22) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r22
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r8 = r1.e
            java.lang.String r8 = r8.w()
            r0.append(r8)
            java.lang.String r8 = "/"
            r0.append(r8)
            r0.append(r3)
            java.lang.String r8 = "/"
            r0.append(r8)
            r0.append(r4)
            java.lang.String r8 = "?uploadId="
            r0.append(r8)
            r0.append(r2)
            java.lang.String r8 = "&partNumber="
            r0.append(r8)
            r0.append(r5)
            java.lang.String r8 = r0.toString()
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream
            int r10 = (int) r6
            r9.<init>(r10)
            r10 = r6
        L_0x0049:
            r12 = 0
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            r15 = 0
            if (r14 == 0) goto L_0x007e
            int r14 = r0.length     // Catch:{ IOException -> 0x0066 }
            int r12 = (int) r10     // Catch:{ IOException -> 0x0066 }
            int r12 = java.lang.Math.min(r14, r12)     // Catch:{ IOException -> 0x0066 }
            r13 = r21
            int r12 = r13.read(r0, r15, r12)     // Catch:{ IOException -> 0x0066 }
            r14 = -1
            if (r12 != r14) goto L_0x0060
            goto L_0x007e
        L_0x0060:
            r9.write(r0, r15, r12)     // Catch:{ IOException -> 0x0066 }
            long r14 = (long) r12
            long r10 = r10 - r14
            goto L_0x0049
        L_0x0066:
            r0 = move-exception
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r2 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Fail to read data from input stream, size:"
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3, r0)
            throw r2
        L_0x007e:
            byte[] r6 = r9.toByteArray()
            r9.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0085:
            r7 = 0
            r9 = r7
        L_0x0087:
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r0 = r1.e     // Catch:{ GalaxyFDSClientException -> 0x017c }
            com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential r0 = r0.j()     // Catch:{ GalaxyFDSClientException -> 0x017c }
            com.xiaomi.infra.galaxy.fds.model.HttpMethod r10 = com.xiaomi.infra.galaxy.fds.model.HttpMethod.PUT     // Catch:{ GalaxyFDSClientException -> 0x017c }
            org.apache.http.client.methods.HttpUriRequest r0 = com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.a(r8, r0, r10, r7)     // Catch:{ GalaxyFDSClientException -> 0x017c }
            r10 = r0
            org.apache.http.client.methods.HttpPut r10 = (org.apache.http.client.methods.HttpPut) r10     // Catch:{ GalaxyFDSClientException -> 0x017c }
            org.apache.http.entity.ByteArrayEntity r11 = new org.apache.http.entity.ByteArrayEntity     // Catch:{ GalaxyFDSClientException -> 0x017c }
            r11.<init>(r6)     // Catch:{ GalaxyFDSClientException -> 0x017c }
            r10.setEntity(r11)     // Catch:{ GalaxyFDSClientException -> 0x017c }
            org.apache.http.client.HttpClient r10 = r1.f     // Catch:{ IOException -> 0x015d }
            org.apache.http.HttpResponse r0 = r10.execute(r0)     // Catch:{ IOException -> 0x015d }
            org.apache.http.HttpEntity r10 = r0.getEntity()     // Catch:{ IOException -> 0x015d }
            java.io.InputStream r10 = r10.getContent()     // Catch:{ IOException -> 0x015d }
            org.apache.http.StatusLine r9 = r0.getStatusLine()     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            int r9 = r9.getStatusCode()     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            r11 = 200(0xc8, float:2.8E-43)
            if (r9 != r11) goto L_0x010b
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            r0.<init>(r10)     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            com.google.gson.Gson r9 = new com.google.gson.Gson     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            r9.<init>()     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            java.lang.Class<com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult> r11 = com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult.class
            java.lang.Object r0 = r9.fromJson((java.io.Reader) r0, r11)     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult r0 = (com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult) r0     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            if (r0 == 0) goto L_0x00e2
            java.lang.String r9 = r0.b()     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            if (r9 == 0) goto L_0x00e2
            long r11 = r0.c()     // Catch:{ IOException -> 0x0158, GalaxyFDSClientException -> 0x0153, all -> 0x0150 }
            r13 = 0
            int r9 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r9 == 0) goto L_0x00e4
            if (r10 == 0) goto L_0x00e1
            r10.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x00e1:
            return r0
        L_0x00e2:
            r13 = 0
        L_0x00e4:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r0 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r9.<init>()     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r11 = "Fail to parse the result of uploading part. bucket name:"
            r9.append(r11)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r9.append(r3)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r11 = ", object name:"
            r9.append(r11)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r9.append(r4)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r11 = ", upload ID:"
            r9.append(r11)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r9.append(r2)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r0.<init>((java.lang.String) r9)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            throw r0     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
        L_0x010b:
            r13 = 0
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r9 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r11.<init>()     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r12 = "Unable to upload object["
            r11.append(r12)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r11.append(r3)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r12 = "/"
            r11.append(r12)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r11.append(r4)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r12 = "] to URI :"
            r11.append(r12)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r11.append(r8)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r12 = ". Fail to upload part "
            r11.append(r12)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r11.append(r5)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r12 = ": "
            r11.append(r12)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            org.apache.http.StatusLine r0 = r0.getStatusLine()     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r11.append(r0)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            java.lang.String r0 = r11.toString()     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            r9.<init>((java.lang.String) r0)     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
            throw r9     // Catch:{ IOException -> 0x014e, GalaxyFDSClientException -> 0x014c, all -> 0x0150 }
        L_0x014c:
            r0 = move-exception
            goto L_0x0156
        L_0x014e:
            r0 = move-exception
            goto L_0x015b
        L_0x0150:
            r0 = move-exception
            goto L_0x01d4
        L_0x0153:
            r0 = move-exception
            r13 = 0
        L_0x0156:
            r9 = r10
            goto L_0x017f
        L_0x0158:
            r0 = move-exception
            r13 = 0
        L_0x015b:
            r9 = r10
            goto L_0x0160
        L_0x015d:
            r0 = move-exception
            r13 = 0
        L_0x0160:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r10 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            r11.<init>()     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            java.lang.String r12 = "Fail to put part. URI:"
            r11.append(r12)     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            r11.append(r8)     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            java.lang.String r11 = r11.toString()     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            r10.<init>(r11, r0)     // Catch:{ GalaxyFDSClientException -> 0x0177 }
            throw r10     // Catch:{ GalaxyFDSClientException -> 0x0177 }
        L_0x0177:
            r0 = move-exception
            goto L_0x017f
        L_0x0179:
            r0 = move-exception
            r10 = r9
            goto L_0x01d4
        L_0x017c:
            r0 = move-exception
            r13 = 0
        L_0x017f:
            int r15 = r15 + 1
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r10 = r1.e     // Catch:{ all -> 0x0179 }
            int r10 = r10.a()     // Catch:{ all -> 0x0179 }
            if (r15 >= r10) goto L_0x01d3
            boolean r10 = d     // Catch:{ all -> 0x0179 }
            if (r10 != 0) goto L_0x01cc
            java.lang.String r10 = "GalaxyFDSClientImpl"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0179 }
            r11.<init>()     // Catch:{ all -> 0x0179 }
            java.lang.String r12 = "Retry the upload of object:"
            r11.append(r12)     // Catch:{ all -> 0x0179 }
            r11.append(r4)     // Catch:{ all -> 0x0179 }
            java.lang.String r12 = " bucket"
            r11.append(r12)     // Catch:{ all -> 0x0179 }
            java.lang.String r12 = ":"
            r11.append(r12)     // Catch:{ all -> 0x0179 }
            r11.append(r3)     // Catch:{ all -> 0x0179 }
            java.lang.String r12 = " upload id:"
            r11.append(r12)     // Catch:{ all -> 0x0179 }
            r11.append(r2)     // Catch:{ all -> 0x0179 }
            java.lang.String r12 = " part number:"
            r11.append(r12)     // Catch:{ all -> 0x0179 }
            r11.append(r5)     // Catch:{ all -> 0x0179 }
            java.lang.String r12 = " cause:"
            r11.append(r12)     // Catch:{ all -> 0x0179 }
            java.lang.String r0 = com.xiaomi.infra.galaxy.fds.android.util.Util.a((java.lang.Exception) r0)     // Catch:{ all -> 0x0179 }
            r11.append(r0)     // Catch:{ all -> 0x0179 }
            java.lang.String r0 = r11.toString()     // Catch:{ all -> 0x0179 }
            android.util.Log.i(r10, r0)     // Catch:{ all -> 0x0179 }
        L_0x01cc:
            if (r9 == 0) goto L_0x0087
            r9.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x0087
        L_0x01d3:
            throw r0     // Catch:{ all -> 0x0179 }
        L_0x01d4:
            if (r10 == 0) goto L_0x01d9
            r10.close()     // Catch:{ IOException -> 0x01d9 }
        L_0x01d9:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.a(java.lang.String, java.lang.String, java.lang.String, int, com.xiaomi.infra.galaxy.fds.android.util.ObjectInputStream, long):com.xiaomi.infra.galaxy.fds.android.model.UploadPartResult");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0186 A[SYNTHETIC, Splitter:B:48:0x0186] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult a(java.lang.String r6, java.lang.String r7, java.lang.String r8, com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata r9, com.xiaomi.infra.galaxy.fds.android.model.UploadPartResultList r10, java.util.List<com.xiaomi.infra.galaxy.fds.android.model.UserParam> r11) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r2 = r5.e
            java.lang.String r2 = r2.w()
            r1.append(r2)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r7)
            java.lang.String r2 = "/"
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = "?uploadId="
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r0.append(r1)
            if (r11 == 0) goto L_0x0051
            java.util.Iterator r11 = r11.iterator()
        L_0x0038:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L_0x0051
            java.lang.Object r1 = r11.next()
            com.xiaomi.infra.galaxy.fds.android.model.UserParam r1 = (com.xiaomi.infra.galaxy.fds.android.model.UserParam) r1
            r2 = 38
            r0.append(r2)
            java.lang.String r1 = r1.toString()
            r0.append(r1)
            goto L_0x0038
        L_0x0051:
            java.lang.String r11 = r0.toString()
            r0 = 0
            if (r9 == 0) goto L_0x008e
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ IOException -> 0x008b }
            r1.<init>()     // Catch:{ IOException -> 0x008b }
            java.util.Map r9 = r9.h()     // Catch:{ IOException -> 0x008b }
            java.util.Set r9 = r9.entrySet()     // Catch:{ IOException -> 0x008b }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException -> 0x008b }
        L_0x0069:
            boolean r2 = r9.hasNext()     // Catch:{ IOException -> 0x008b }
            if (r2 == 0) goto L_0x008f
            java.lang.Object r2 = r9.next()     // Catch:{ IOException -> 0x008b }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ IOException -> 0x008b }
            java.lang.Object r3 = r2.getKey()     // Catch:{ IOException -> 0x008b }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ IOException -> 0x008b }
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ IOException -> 0x008b }
            java.lang.Object r2 = r2.getValue()     // Catch:{ IOException -> 0x008b }
            r1.put(r3, r2)     // Catch:{ IOException -> 0x008b }
            goto L_0x0069
        L_0x0087:
            r6 = move-exception
            r10 = r0
            goto L_0x0184
        L_0x008b:
            r6 = move-exception
            goto L_0x016d
        L_0x008e:
            r1 = r0
        L_0x008f:
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r9 = r5.e     // Catch:{ IOException -> 0x008b }
            com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential r9 = r9.j()     // Catch:{ IOException -> 0x008b }
            com.xiaomi.infra.galaxy.fds.model.HttpMethod r2 = com.xiaomi.infra.galaxy.fds.model.HttpMethod.PUT     // Catch:{ IOException -> 0x008b }
            org.apache.http.client.methods.HttpUriRequest r9 = com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.a(r11, r9, r2, r1)     // Catch:{ IOException -> 0x008b }
            r1 = r9
            org.apache.http.client.methods.HttpPut r1 = (org.apache.http.client.methods.HttpPut) r1     // Catch:{ IOException -> 0x008b }
            org.apache.http.entity.StringEntity r2 = new org.apache.http.entity.StringEntity     // Catch:{ IOException -> 0x008b }
            com.google.gson.Gson r3 = new com.google.gson.Gson     // Catch:{ IOException -> 0x008b }
            r3.<init>()     // Catch:{ IOException -> 0x008b }
            java.lang.String r10 = r3.toJson((java.lang.Object) r10)     // Catch:{ IOException -> 0x008b }
            r2.<init>(r10)     // Catch:{ IOException -> 0x008b }
            r1.setEntity(r2)     // Catch:{ IOException -> 0x008b }
            org.apache.http.client.HttpClient r10 = r5.f     // Catch:{ IOException -> 0x008b }
            org.apache.http.HttpResponse r9 = r10.execute(r9)     // Catch:{ IOException -> 0x008b }
            org.apache.http.HttpEntity r10 = r9.getEntity()     // Catch:{ IOException -> 0x008b }
            java.io.InputStream r10 = r10.getContent()     // Catch:{ IOException -> 0x008b }
            org.apache.http.StatusLine r0 = r9.getStatusLine()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            int r0 = r0.getStatusCode()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L_0x0132
            java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r9.<init>(r10)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.<init>()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.Class<com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult> r1 = com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult.class
            java.lang.Object r9 = r0.fromJson((java.io.Reader) r9, r1)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult r9 = (com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult) r9     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            if (r9 == 0) goto L_0x010b
            java.lang.String r0 = r9.c()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            if (r0 == 0) goto L_0x010b
            java.lang.String r0 = r9.d()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            if (r0 == 0) goto L_0x010b
            long r0 = r9.e()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x010b
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r6 = r5.e     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r6 = r6.t()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r9.e(r6)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r6 = r5.e     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r6 = r6.u()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r9.f(r6)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            if (r10 == 0) goto L_0x010a
            r10.close()     // Catch:{ IOException -> 0x010a }
        L_0x010a:
            return r9
        L_0x010b:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r9 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.<init>()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r1 = "Fail to parse the result of completing multipart upload. bucket name:"
            r0.append(r1)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = ", object name:"
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r8)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = ", upload ID:"
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r6)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r6 = r0.toString()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r9.<init>((java.lang.String) r6)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            throw r9     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
        L_0x0132:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r6 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.<init>()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r1 = "Unable to upload object["
            r0.append(r1)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = "/"
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r8)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = "] to URI :"
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r11)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = ". Fail to complete multipart upload: "
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            org.apache.http.StatusLine r7 = r9.getStatusLine()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r0.append(r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            java.lang.String r7 = r0.toString()     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            r6.<init>((java.lang.String) r7)     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
            throw r6     // Catch:{ IOException -> 0x016b, all -> 0x0169 }
        L_0x0169:
            r6 = move-exception
            goto L_0x0184
        L_0x016b:
            r6 = move-exception
            r0 = r10
        L_0x016d:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r7 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ all -> 0x0087 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            r8.<init>()     // Catch:{ all -> 0x0087 }
            java.lang.String r9 = "Fail to complete multipart upload. URI:"
            r8.append(r9)     // Catch:{ all -> 0x0087 }
            r8.append(r11)     // Catch:{ all -> 0x0087 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0087 }
            r7.<init>(r8, r6)     // Catch:{ all -> 0x0087 }
            throw r7     // Catch:{ all -> 0x0087 }
        L_0x0184:
            if (r10 == 0) goto L_0x0189
            r10.close()     // Catch:{ IOException -> 0x0189 }
        L_0x0189:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.a(java.lang.String, java.lang.String, java.lang.String, com.xiaomi.infra.galaxy.fds.android.model.ObjectMetadata, com.xiaomi.infra.galaxy.fds.android.model.UploadPartResultList, java.util.List):com.xiaomi.infra.galaxy.fds.android.model.PutObjectResult");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b0 A[SYNTHETIC, Splitter:B:24:0x00b0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r6, java.lang.String r7, java.lang.String r8) throws com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r1 = r5.e
            java.lang.String r1 = r1.w()
            r0.append(r1)
            java.lang.String r1 = "/"
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = "/"
            r0.append(r1)
            r0.append(r7)
            java.lang.String r1 = "?uploadId="
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r0 = 0
            com.xiaomi.infra.galaxy.fds.android.FDSClientConfiguration r1 = r5.e     // Catch:{ IOException -> 0x0096 }
            com.xiaomi.infra.galaxy.fds.android.auth.GalaxyFDSCredential r1 = r1.j()     // Catch:{ IOException -> 0x0096 }
            com.xiaomi.infra.galaxy.fds.model.HttpMethod r2 = com.xiaomi.infra.galaxy.fds.model.HttpMethod.DELETE     // Catch:{ IOException -> 0x0096 }
            org.apache.http.client.methods.HttpUriRequest r1 = com.xiaomi.infra.galaxy.fds.android.util.RequestFactory.a(r8, r1, r2, r0)     // Catch:{ IOException -> 0x0096 }
            org.apache.http.client.HttpClient r2 = r5.f     // Catch:{ IOException -> 0x0096 }
            org.apache.http.HttpResponse r1 = r2.execute(r1)     // Catch:{ IOException -> 0x0096 }
            org.apache.http.HttpEntity r2 = r1.getEntity()     // Catch:{ IOException -> 0x0096 }
            java.io.InputStream r2 = r2.getContent()     // Catch:{ IOException -> 0x0096 }
            org.apache.http.StatusLine r0 = r1.getStatusLine()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            int r0 = r0.getStatusCode()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 != r3) goto L_0x0057
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ IOException -> 0x0056 }
        L_0x0056:
            return
        L_0x0057:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r0 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r3.<init>()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r4 = "Unable to upload object["
            r3.append(r4)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r3.append(r6)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r6 = "/"
            r3.append(r6)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r3.append(r7)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r6 = "] to URI :"
            r3.append(r6)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r3.append(r8)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r6 = ". Fail to abort multipart upload: "
            r3.append(r6)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            org.apache.http.StatusLine r6 = r1.getStatusLine()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r3.append(r6)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            java.lang.String r6 = r3.toString()     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            r0.<init>((java.lang.String) r6)     // Catch:{ IOException -> 0x0090, all -> 0x008e }
            throw r0     // Catch:{ IOException -> 0x0090, all -> 0x008e }
        L_0x008e:
            r6 = move-exception
            goto L_0x00ae
        L_0x0090:
            r6 = move-exception
            r0 = r2
            goto L_0x0097
        L_0x0093:
            r6 = move-exception
            r2 = r0
            goto L_0x00ae
        L_0x0096:
            r6 = move-exception
        L_0x0097:
            com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException r7 = new com.xiaomi.infra.galaxy.fds.android.exception.GalaxyFDSClientException     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r1.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "Fail to abort multipart upload. URI:"
            r1.append(r2)     // Catch:{ all -> 0x0093 }
            r1.append(r8)     // Catch:{ all -> 0x0093 }
            java.lang.String r8 = r1.toString()     // Catch:{ all -> 0x0093 }
            r7.<init>(r8, r6)     // Catch:{ all -> 0x0093 }
            throw r7     // Catch:{ all -> 0x0093 }
        L_0x00ae:
            if (r2 == 0) goto L_0x00b3
            r2.close()     // Catch:{ IOException -> 0x00b3 }
        L_0x00b3:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.infra.galaxy.fds.android.GalaxyFDSClientImpl.a(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public PutObjectResult a(String str, File file) throws GalaxyFDSClientException {
        return a(str, file, (List<UserParam>) null);
    }

    public PutObjectResult a(String str, File file, List<UserParam> list) throws GalaxyFDSClientException {
        return b(str, file, list, (ProgressListener) null);
    }

    public PutObjectResult b(String str, File file, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException {
        return b(str, (String) null, file, list, progressListener);
    }

    public PutObjectResult a(String str, InputStream inputStream, ObjectMetadata objectMetadata) throws GalaxyFDSClientException {
        return a(str, inputStream, objectMetadata, (List<UserParam>) null);
    }

    public PutObjectResult a(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list) throws GalaxyFDSClientException {
        return a(str, inputStream, objectMetadata, list, (ProgressListener) null);
    }

    public PutObjectResult a(String str, InputStream inputStream, ObjectMetadata objectMetadata, List<UserParam> list, ProgressListener progressListener) throws GalaxyFDSClientException {
        return a(str, (String) null, inputStream, objectMetadata, list, progressListener);
    }

    public boolean b(String str, String str2) throws GalaxyFDSClientException {
        Args.a(str, "bucket name");
        Args.a(str, "bucket name");
        Args.a(str2, "object name");
        Args.a(str2, "object name");
        String str3 = this.e.t() + "/" + str + "/" + str2;
        try {
            HttpResponse execute = this.f.execute(RequestFactory.a(str3, this.e.j(), HttpMethod.HEAD, (Map<String, String>) null));
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                return true;
            }
            if (statusCode == 404) {
                return false;
            }
            throw new GalaxyFDSClientException("Unable to head object[" + str + "/" + str2 + "] from URI :" + str3 + ". Cause:" + execute.getStatusLine().toString());
        } catch (IOException e2) {
            throw new GalaxyFDSClientException("Unable to head object[" + str + "/" + str2 + "] from URI :" + str3 + " Exception:" + e2.getMessage(), e2);
        }
    }
}
