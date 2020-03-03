package com.xiaomi.smarthome.httpserver;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.code.microlog4android.appender.DatagramAppender;
import com.google.common.net.HttpHeaders;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import com.xiaomi.stat.d;
import com.xiaomi.youpin.network.annotation.ContentType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.StringTokenizer;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.cybergarage.http.HTTP;

public class SimpleWebServer extends NanoHTTPD {
    public static final String d = "application/octet-stream";
    public static final List<String> e = new ArrayList<String>() {
        {
            add("index.html");
            add("index.htm");
        }
    };
    private static final Map<String, String> f = new HashMap<String, String>() {
        {
            put("css", "text/css");
            put("htm", NanoHTTPD.c);
            put("html", NanoHTTPD.c);
            put("xml", ContentType.XML);
            put("java", "text/x-java-source, text/java");
            put(d.G, "text/plain");
            put("txt", "text/plain");
            put("asc", "text/plain");
            put("gif", "image/gif");
            put("jpg", "image/jpeg");
            put("jpeg", "image/jpeg");
            put("png", "image/png");
            put("mp3", MimeTypes.AUDIO_MPEG);
            put("m3u", "audio/mpeg-url");
            put("mp4", MimeTypes.VIDEO_MP4);
            put("ogv", "video/ogg");
            put("flv", "video/x-flv");
            put("mov", "video/quicktime");
            put("swf", "application/x-shockwave-flash");
            put("js", "application/javascript");
            put("pdf", "application/pdf");
            put("doc", "application/msword");
            put("ogg", "application/x-ogg");
            put(ArchiveStreamFactory.g, "application/octet-stream");
            put("exe", "application/octet-stream");
            put(AppConstants.x, "application/octet-stream");
        }
    };
    private static final String g = "Copyright (c) 2012-2013 by Paul S. Hawke, 2001,2005-2013 by Jarno Elonen, 2010 by Konstantinos Togias\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.";
    private static Map<String, WebServerPlugin> h = new HashMap();
    private final List<File> i;
    private final boolean j;

    public void g() {
    }

    public SimpleWebServer(String str, int i2, File file, boolean z) {
        super(str, i2);
        this.j = z;
        this.i = new ArrayList();
        this.i.add(file);
        g();
    }

    public SimpleWebServer(String str, int i2, List<File> list, boolean z) {
        super(str, i2);
        this.j = z;
        this.i = new ArrayList(list);
        g();
    }

    public static void a(String[] strArr) {
        Iterator<S> it;
        int indexOf;
        String[] strArr2 = strArr;
        ArrayList<File> arrayList = new ArrayList<>();
        HashMap hashMap = new HashMap();
        String str = DatagramAppender.DEFAULT_HOST;
        int i2 = 8080;
        boolean z = false;
        for (int i3 = 0; i3 < strArr2.length; i3++) {
            if (strArr2[i3].equalsIgnoreCase("-h") || strArr2[i3].equalsIgnoreCase("--host")) {
                str = strArr2[i3 + 1];
            } else if (strArr2[i3].equalsIgnoreCase("-p") || strArr2[i3].equalsIgnoreCase("--port")) {
                i2 = Integer.parseInt(strArr2[i3 + 1]);
            } else if (strArr2[i3].equalsIgnoreCase("-q") || strArr2[i3].equalsIgnoreCase("--quiet")) {
                z = true;
            } else if (strArr2[i3].equalsIgnoreCase("-d") || strArr2[i3].equalsIgnoreCase("--dir")) {
                arrayList.add(new File(strArr2[i3 + 1]).getAbsoluteFile());
            } else if (strArr2[i3].equalsIgnoreCase("--licence")) {
                System.out.println("Copyright (c) 2012-2013 by Paul S. Hawke, 2001,2005-2013 by Jarno Elonen, 2010 by Konstantinos Togias\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
            } else if (strArr2[i3].startsWith("-X:") && (indexOf = strArr2[i3].indexOf(61)) > 0) {
                hashMap.put(strArr2[i3].substring(0, indexOf), strArr2[i3].substring(indexOf + 1, strArr2[i3].length()));
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new File(".").getAbsoluteFile());
        }
        hashMap.put("host", str);
        hashMap.put("port", "" + i2);
        hashMap.put("quiet", String.valueOf(z));
        StringBuilder sb = new StringBuilder();
        for (File file : arrayList) {
            if (sb.length() > 0) {
                sb.append(":");
            }
            try {
                sb.append(file.getCanonicalPath());
            } catch (IOException unused) {
            }
        }
        hashMap.put("home", sb.toString());
        Iterator<S> it2 = ServiceLoader.load(WebServerPluginInfo.class).iterator();
        while (it2.hasNext()) {
            WebServerPluginInfo webServerPluginInfo = (WebServerPluginInfo) it2.next();
            String[] a2 = webServerPluginInfo.a();
            int length = a2.length;
            int i4 = 0;
            while (i4 < length) {
                String str2 = a2[i4];
                String[] a3 = webServerPluginInfo.a(str2);
                if (!z) {
                    PrintStream printStream = System.out;
                    printStream.print("# Found plugin for Mime type: \"" + str2 + "\"");
                    if (a3 != null) {
                        System.out.print(" (serving index files: ");
                        int length2 = a3.length;
                        int i5 = 0;
                        while (i5 < length2) {
                            String str3 = a3[i5];
                            PrintStream printStream2 = System.out;
                            Iterator<S> it3 = it2;
                            printStream2.print(str3 + " ");
                            i5++;
                            it2 = it3;
                        }
                    }
                    it = it2;
                    System.out.println(").");
                } else {
                    it = it2;
                }
                a(a3, str2, webServerPluginInfo.b(str2), (Map<String, String>) hashMap);
                i4++;
                it2 = it;
            }
        }
        ServerRunner.a((NanoHTTPD) new SimpleWebServer(str, i2, (List<File>) arrayList, z));
    }

    protected static void a(String[] strArr, String str, WebServerPlugin webServerPlugin, Map<String, String> map) {
        if (str != null && webServerPlugin != null) {
            if (strArr != null) {
                for (String str2 : strArr) {
                    int lastIndexOf = str2.lastIndexOf(46);
                    if (lastIndexOf >= 0) {
                        f.put(str2.substring(lastIndexOf + 1).toLowerCase(), str);
                    }
                }
                e.addAll(Arrays.asList(strArr));
            }
            h.put(str, webServerPlugin);
            webServerPlugin.a(map);
        }
    }

    private File i() {
        return this.i.get(0);
    }

    private List<File> j() {
        return this.i;
    }

    private void a(File file) {
        this.i.add(file);
    }

    private String e(String str) {
        String str2;
        String str3 = "";
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/ ", true);
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("/")) {
                str2 = str3 + "/";
            } else if (nextToken.equals(" ")) {
                str2 = str3 + "%20";
            } else {
                try {
                    str2 = str3 + URLEncoder.encode(nextToken, "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                }
            }
            str3 = str2;
        }
        return str3;
    }

    public NanoHTTPD.Response a(NanoHTTPD.IHTTPSession iHTTPSession) {
        Map<String, String> d2 = iHTTPSession.d();
        Map<String, String> b = iHTTPSession.b();
        String e2 = iHTTPSession.e();
        if (!this.j) {
            PrintStream printStream = System.out;
            printStream.println(iHTTPSession.f() + " '" + e2 + "' ");
            for (String next : d2.keySet()) {
                PrintStream printStream2 = System.out;
                printStream2.println("  HDR: '" + next + "' = '" + d2.get(next) + "'");
            }
            for (String next2 : b.keySet()) {
                PrintStream printStream3 = System.out;
                printStream3.println("  PRM: '" + next2 + "' = '" + b.get(next2) + "'");
            }
        }
        for (File next3 : j()) {
            if (!next3.isDirectory()) {
                return d("given path is not a directory (" + next3 + ").");
            }
        }
        return a(Collections.unmodifiableMap(d2), iHTTPSession, e2);
    }

    private NanoHTTPD.Response a(Map<String, String> map, NanoHTTPD.IHTTPSession iHTTPSession, String str) {
        NanoHTTPD.Response response;
        String replace = str.trim().replace(File.separatorChar, IOUtils.f15883a);
        boolean z = false;
        if (replace.indexOf(63) >= 0) {
            replace = replace.substring(0, replace.indexOf(63));
        }
        String str2 = replace;
        if (str2.startsWith("src/main") || str2.endsWith("src/main") || str2.contains("../")) {
            return c("Won't serve ../ for security reasons.");
        }
        List<File> j2 = j();
        File file = null;
        int i2 = 0;
        while (!z && i2 < j2.size()) {
            file = j2.get(i2);
            z = b(str2, file);
            i2++;
        }
        if (!z) {
            return h();
        }
        File file2 = new File(file, str2);
        if (file2.isDirectory() && !str2.endsWith("/")) {
            String str3 = str2 + "/";
            NanoHTTPD.Response a2 = a(NanoHTTPD.Response.Status.REDIRECT, NanoHTTPD.c, "<html><body>Redirected: <a href=\"" + str3 + "\">" + str3 + "</a></body></html>");
            a2.a("Location", str3);
            return a2;
        } else if (file2.isDirectory()) {
            String b = b(file2);
            if (b != null) {
                return a(map, iHTTPSession, str2 + b);
            } else if (file2.canRead()) {
                return a(NanoHTTPD.Response.Status.OK, NanoHTTPD.c, a(str2, file2));
            } else {
                return c("No directory listing.");
            }
        } else {
            String f2 = f(str2);
            WebServerPlugin webServerPlugin = h.get(f2);
            if (webServerPlugin != null) {
                response = webServerPlugin.a(str2, map, iHTTPSession, file2, f2);
                if (response != null && (response instanceof InternalRewrite)) {
                    InternalRewrite internalRewrite = (InternalRewrite) response;
                    return a(internalRewrite.b(), iHTTPSession, internalRewrite.a());
                }
            } else {
                response = a(str2, map, file2, f2);
            }
            if (response != null) {
                return response;
            }
            return h();
        }
    }

    /* access modifiers changed from: protected */
    public NanoHTTPD.Response h() {
        return a(NanoHTTPD.Response.Status.NOT_FOUND, "text/plain", "Error 404, file not found.");
    }

    /* access modifiers changed from: protected */
    public NanoHTTPD.Response c(String str) {
        NanoHTTPD.Response.Status status = NanoHTTPD.Response.Status.FORBIDDEN;
        return a(status, "text/plain", "FORBIDDEN: " + str);
    }

    /* access modifiers changed from: protected */
    public NanoHTTPD.Response d(String str) {
        NanoHTTPD.Response.Status status = NanoHTTPD.Response.Status.INTERNAL_ERROR;
        return a(status, "text/plain", "INTERNAL ERRROR: " + str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r1 = h.get(f(r4));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(java.lang.String r4, java.io.File r5) {
        /*
            r3 = this;
            java.io.File r0 = new java.io.File
            r0.<init>(r5, r4)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x001d
            java.lang.String r1 = r3.f(r4)
            java.util.Map<java.lang.String, com.xiaomi.smarthome.httpserver.WebServerPlugin> r2 = h
            java.lang.Object r1 = r2.get(r1)
            com.xiaomi.smarthome.httpserver.WebServerPlugin r1 = (com.xiaomi.smarthome.httpserver.WebServerPlugin) r1
            if (r1 == 0) goto L_0x001d
            boolean r0 = r1.a(r4, r5)
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.httpserver.SimpleWebServer.b(java.lang.String, java.io.File):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:1|2|(10:8|9|10|11|12|13|14|17|18|(2:33|(2:35|42)(2:36|43))(2:22|(2:24|40)(6:25|(1:27)|28|(1:30)(1:31)|32|41)))|15|17|18|(0)(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0049, code lost:
        r7 = r7.substring("bytes=".length());
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0071 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007b A[Catch:{ IOException -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0109 A[Catch:{ IOException -> 0x0145 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.httpserver.NanoHTTPD.Response a(java.lang.String r20, java.util.Map<java.lang.String, java.lang.String> r21, java.io.File r22, java.lang.String r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            r2 = r22
            r3 = r23
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0145 }
            r4.<init>()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r5 = r22.getAbsolutePath()     // Catch:{ IOException -> 0x0145 }
            r4.append(r5)     // Catch:{ IOException -> 0x0145 }
            long r5 = r22.lastModified()     // Catch:{ IOException -> 0x0145 }
            r4.append(r5)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r5 = ""
            r4.append(r5)     // Catch:{ IOException -> 0x0145 }
            long r5 = r22.length()     // Catch:{ IOException -> 0x0145 }
            r4.append(r5)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0145 }
            int r4 = r4.hashCode()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ IOException -> 0x0145 }
            r5 = -1
            java.lang.String r7 = "range"
            java.lang.Object r7 = r1.get(r7)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x0145 }
            r8 = 0
            if (r7 == 0) goto L_0x0070
            java.lang.String r10 = "bytes="
            boolean r10 = r7.startsWith(r10)     // Catch:{ IOException -> 0x0145 }
            if (r10 == 0) goto L_0x0070
            java.lang.String r10 = "bytes="
            int r10 = r10.length()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r7 = r7.substring(r10)     // Catch:{ IOException -> 0x0145 }
            r10 = 45
            int r10 = r7.indexOf(r10)     // Catch:{ IOException -> 0x0145 }
            if (r10 <= 0) goto L_0x0070
            r11 = 0
            java.lang.String r11 = r7.substring(r11, r10)     // Catch:{ NumberFormatException -> 0x0070 }
            long r11 = java.lang.Long.parseLong(r11)     // Catch:{ NumberFormatException -> 0x0070 }
            int r10 = r10 + 1
            java.lang.String r10 = r7.substring(r10)     // Catch:{ NumberFormatException -> 0x0071 }
            long r13 = java.lang.Long.parseLong(r10)     // Catch:{ NumberFormatException -> 0x0071 }
            r5 = r13
            goto L_0x0071
        L_0x0070:
            r11 = r8
        L_0x0071:
            long r13 = r22.length()     // Catch:{ IOException -> 0x0145 }
            if (r7 == 0) goto L_0x0109
            int r7 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r7 < 0) goto L_0x0109
            int r1 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r1 < 0) goto L_0x00a6
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response$Status r1 = com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status.RANGE_NOT_SATISFIABLE     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "text/plain"
            java.lang.String r3 = ""
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response r1 = r0.a((com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status) r1, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "Content-Range"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0145 }
            r3.<init>()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r5 = "bytes 0-0/"
            r3.append(r5)     // Catch:{ IOException -> 0x0145 }
            r3.append(r13)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0145 }
            r1.a((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "ETag"
            r1.a((java.lang.String) r2, (java.lang.String) r4)     // Catch:{ IOException -> 0x0145 }
            goto L_0x014b
        L_0x00a6:
            int r1 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            r15 = 1
            if (r1 >= 0) goto L_0x00ae
            long r5 = r13 - r15
        L_0x00ae:
            r1 = 0
            long r17 = r5 - r11
            long r15 = r17 + r15
            int r1 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b8
            goto L_0x00b9
        L_0x00b8:
            r8 = r15
        L_0x00b9:
            com.xiaomi.smarthome.httpserver.SimpleWebServer$3 r1 = new com.xiaomi.smarthome.httpserver.SimpleWebServer$3     // Catch:{ IOException -> 0x0145 }
            r1.<init>(r2, r8)     // Catch:{ IOException -> 0x0145 }
            r1.skip(r11)     // Catch:{ IOException -> 0x0145 }
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response$Status r2 = com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status.PARTIAL_CONTENT     // Catch:{ IOException -> 0x0145 }
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response r1 = r0.a((com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status) r2, (java.lang.String) r3, (java.io.InputStream) r1)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "Content-Length"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0145 }
            r3.<init>()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r7 = ""
            r3.append(r7)     // Catch:{ IOException -> 0x0145 }
            r3.append(r8)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0145 }
            r1.a((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "Content-Range"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0145 }
            r3.<init>()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r7 = "bytes "
            r3.append(r7)     // Catch:{ IOException -> 0x0145 }
            r3.append(r11)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r7 = "-"
            r3.append(r7)     // Catch:{ IOException -> 0x0145 }
            r3.append(r5)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r5 = "/"
            r3.append(r5)     // Catch:{ IOException -> 0x0145 }
            r3.append(r13)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0145 }
            r1.a((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "ETag"
            r1.a((java.lang.String) r2, (java.lang.String) r4)     // Catch:{ IOException -> 0x0145 }
            goto L_0x014b
        L_0x0109:
            java.lang.String r5 = "if-none-match"
            java.lang.Object r1 = r1.get(r5)     // Catch:{ IOException -> 0x0145 }
            boolean r1 = r4.equals(r1)     // Catch:{ IOException -> 0x0145 }
            if (r1 == 0) goto L_0x011e
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response$Status r1 = com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status.NOT_MODIFIED     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = ""
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response r1 = r0.a((com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status) r1, (java.lang.String) r3, (java.lang.String) r2)     // Catch:{ IOException -> 0x0145 }
            goto L_0x014b
        L_0x011e:
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response$Status r1 = com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status.OK     // Catch:{ IOException -> 0x0145 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0145 }
            r5.<init>(r2)     // Catch:{ IOException -> 0x0145 }
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response r1 = r0.a((com.xiaomi.smarthome.httpserver.NanoHTTPD.Response.Status) r1, (java.lang.String) r3, (java.io.InputStream) r5)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "Content-Length"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0145 }
            r3.<init>()     // Catch:{ IOException -> 0x0145 }
            java.lang.String r5 = ""
            r3.append(r5)     // Catch:{ IOException -> 0x0145 }
            r3.append(r13)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0145 }
            r1.a((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ IOException -> 0x0145 }
            java.lang.String r2 = "ETag"
            r1.a((java.lang.String) r2, (java.lang.String) r4)     // Catch:{ IOException -> 0x0145 }
            goto L_0x014b
        L_0x0145:
            java.lang.String r1 = "Reading file failed."
            com.xiaomi.smarthome.httpserver.NanoHTTPD$Response r1 = r0.c(r1)
        L_0x014b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.httpserver.SimpleWebServer.a(java.lang.String, java.util.Map, java.io.File, java.lang.String):com.xiaomi.smarthome.httpserver.NanoHTTPD$Response");
    }

    private String f(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        String str2 = lastIndexOf >= 0 ? f.get(str.substring(lastIndexOf + 1).toLowerCase()) : null;
        return str2 == null ? "application/octet-stream" : str2;
    }

    private NanoHTTPD.Response a(NanoHTTPD.Response.Status status, String str, InputStream inputStream) {
        NanoHTTPD.Response response = new NanoHTTPD.Response((NanoHTTPD.Response.IStatus) status, str, inputStream);
        response.a(HttpHeaders.ACCEPT_RANGES, HTTP.CONTENT_RANGE_BYTES);
        return response;
    }

    private NanoHTTPD.Response a(NanoHTTPD.Response.Status status, String str, String str2) {
        NanoHTTPD.Response response = new NanoHTTPD.Response((NanoHTTPD.Response.IStatus) status, str, str2);
        response.a(HttpHeaders.ACCEPT_RANGES, HTTP.CONTENT_RANGE_BYTES);
        return response;
    }

    private String b(File file) {
        for (String next : e) {
            if (new File(file, next).exists()) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x003b, code lost:
        r0 = r14.substring(0, r14.length() - 1);
        r4 = r0.lastIndexOf(47);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r14, java.io.File r15) {
        /*
            r13 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Directory "
            r0.append(r1)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "<html><head><title>"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = "</title><style><!--\nspan.dirname { font-weight: bold; }\nspan.filesize { font-size: 75%; }\n// -->\n</style></head><body><h1>"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = "</h1>"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            int r0 = r14.length()
            r2 = 1
            if (r0 <= r2) goto L_0x0059
            int r0 = r14.length()
            int r0 = r0 - r2
            r3 = 0
            java.lang.String r0 = r14.substring(r3, r0)
            r4 = 47
            int r4 = r0.lastIndexOf(r4)
            if (r4 < 0) goto L_0x0059
            int r0 = r0.length()
            if (r4 >= r0) goto L_0x0059
            int r4 = r4 + r2
            java.lang.String r0 = r14.substring(r3, r4)
            goto L_0x005a
        L_0x0059:
            r0 = 0
        L_0x005a:
            com.xiaomi.smarthome.httpserver.SimpleWebServer$4 r2 = new com.xiaomi.smarthome.httpserver.SimpleWebServer$4
            r2.<init>()
            java.lang.String[] r2 = r15.list(r2)
            java.util.List r2 = java.util.Arrays.asList(r2)
            java.util.Collections.sort(r2)
            com.xiaomi.smarthome.httpserver.SimpleWebServer$5 r3 = new com.xiaomi.smarthome.httpserver.SimpleWebServer$5
            r3.<init>()
            java.lang.String[] r3 = r15.list(r3)
            java.util.List r3 = java.util.Arrays.asList(r3)
            java.util.Collections.sort(r3)
            if (r0 != 0) goto L_0x0087
            int r4 = r3.size()
            int r5 = r2.size()
            int r4 = r4 + r5
            if (r4 <= 0) goto L_0x019e
        L_0x0087:
            java.lang.String r4 = "<ul>"
            r1.append(r4)
            if (r0 != 0) goto L_0x0094
            int r4 = r3.size()
            if (r4 <= 0) goto L_0x00f7
        L_0x0094:
            java.lang.String r4 = "<section class=\"directories\">"
            r1.append(r4)
            if (r0 == 0) goto L_0x00a8
            java.lang.String r4 = "<li><a rel=\"directory\" href=\""
            r1.append(r4)
            r1.append(r0)
            java.lang.String r0 = "\"><span class=\"dirname\">..</span></a></b></li>"
            r1.append(r0)
        L_0x00a8:
            java.util.Iterator r0 = r3.iterator()
        L_0x00ac:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00f2
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            java.lang.String r3 = "/"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.lang.String r4 = "<li><a rel=\"directory\" href=\""
            r1.append(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r14)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r13.e(r4)
            r1.append(r4)
            java.lang.String r4 = "\"><span class=\"dirname\">"
            r1.append(r4)
            r1.append(r3)
            java.lang.String r3 = "</span></a></b></li>"
            r1.append(r3)
            goto L_0x00ac
        L_0x00f2:
            java.lang.String r0 = "</section>"
            r1.append(r0)
        L_0x00f7:
            int r0 = r2.size()
            if (r0 <= 0) goto L_0x0199
            java.lang.String r0 = "<section class=\"files\">"
            r1.append(r0)
            java.util.Iterator r0 = r2.iterator()
        L_0x0106:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0194
            java.lang.Object r2 = r0.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "<li><a href=\""
            r1.append(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r14)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = r13.e(r3)
            r1.append(r3)
            java.lang.String r3 = "\"><span class=\"filename\">"
            r1.append(r3)
            r1.append(r2)
            java.lang.String r3 = "</span></a>"
            r1.append(r3)
            java.io.File r3 = new java.io.File
            r3.<init>(r15, r2)
            long r2 = r3.length()
            java.lang.String r4 = "&nbsp;<span class=\"filesize\">("
            r1.append(r4)
            r4 = 1024(0x400, double:5.06E-321)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0157
            r1.append(r2)
            java.lang.String r2 = " bytes"
            r1.append(r2)
            goto L_0x018d
        L_0x0157:
            r6 = 1048576(0x100000, double:5.180654E-318)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            r9 = 100
            r11 = 10
            if (r8 >= 0) goto L_0x0178
            long r6 = r2 / r4
            r1.append(r6)
            java.lang.String r6 = "."
            r1.append(r6)
            long r2 = r2 % r4
            long r2 = r2 / r11
            long r2 = r2 % r9
            r1.append(r2)
            java.lang.String r2 = " KB"
            r1.append(r2)
            goto L_0x018d
        L_0x0178:
            long r4 = r2 / r6
            r1.append(r4)
            java.lang.String r4 = "."
            r1.append(r4)
            long r2 = r2 % r6
            long r2 = r2 / r11
            long r2 = r2 % r9
            r1.append(r2)
            java.lang.String r2 = " MB"
            r1.append(r2)
        L_0x018d:
            java.lang.String r2 = ")</span></li>"
            r1.append(r2)
            goto L_0x0106
        L_0x0194:
            java.lang.String r14 = "</section>"
            r1.append(r14)
        L_0x0199:
            java.lang.String r14 = "</ul>"
            r1.append(r14)
        L_0x019e:
            java.lang.String r14 = "</body></html>"
            r1.append(r14)
            java.lang.String r14 = r1.toString()
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.httpserver.SimpleWebServer.a(java.lang.String, java.io.File):java.lang.String");
    }
}
