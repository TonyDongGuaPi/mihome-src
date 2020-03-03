package com.xiaomi.smarthome.httpserver;

import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpFileServer extends NanoHTTPD {
    public HttpFileServer() {
        super(8080);
    }

    public NanoHTTPD.Response a(NanoHTTPD.IHTTPSession iHTTPSession) {
        Map<String, List<String>> b = b(iHTTPSession.c());
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>Debug Server</title></head>");
        sb.append("<body>");
        sb.append("<h1>Debug Server</h1>");
        sb.append("<p><blockquote><b>URI</b> = ");
        sb.append(String.valueOf(iHTTPSession.e()));
        sb.append("<br />");
        sb.append("<b>Method</b> = ");
        sb.append(String.valueOf(iHTTPSession.f()));
        sb.append("</blockquote></p>");
        sb.append("<h3>Headers</h3><p><blockquote>");
        sb.append(b(iHTTPSession.d()));
        sb.append("</blockquote></p>");
        sb.append("<h3>Parms</h3><p><blockquote>");
        sb.append(b(iHTTPSession.b()));
        sb.append("</blockquote></p>");
        sb.append("<h3>Parms (multi values?)</h3><p><blockquote>");
        sb.append(b(b));
        sb.append("</blockquote></p>");
        try {
            HashMap hashMap = new HashMap();
            iHTTPSession.a(hashMap);
            sb.append("<h3>Files</h3><p><blockquote>");
            sb.append(b(hashMap));
            sb.append("</blockquote></p>");
        } catch (Exception unused) {
        }
        sb.append("</body>");
        sb.append("</html>");
        return new NanoHTTPD.Response(sb.toString());
    }

    private String b(Map<String, ?> map) {
        if (map.size() == 0) {
            return "";
        }
        return c(map);
    }

    private String c(Map<String, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        for (Map.Entry<String, ?> a2 : map.entrySet()) {
            a(sb, a2);
        }
        sb.append("</ul>");
        return sb.toString();
    }

    private void a(StringBuilder sb, Map.Entry entry) {
        sb.append("<li><code><b>");
        sb.append(entry.getKey());
        sb.append("</b> = ");
        sb.append(entry.getValue());
        sb.append("</code></li>");
    }

    private static class ExampleManagerFactory implements NanoHTTPD.TempFileManagerFactory {
        private ExampleManagerFactory() {
        }

        public NanoHTTPD.TempFileManager a() {
            return new ExampleManager();
        }
    }

    private static class ExampleManager implements NanoHTTPD.TempFileManager {

        /* renamed from: a  reason: collision with root package name */
        private final String f18378a;
        private final List<NanoHTTPD.TempFile> b;

        private ExampleManager() {
            this.f18378a = System.getProperty("java.io.tmpdir");
            this.b = new ArrayList();
        }

        public NanoHTTPD.TempFile a() throws Exception {
            NanoHTTPD.DefaultTempFile defaultTempFile = new NanoHTTPD.DefaultTempFile(this.f18378a);
            this.b.add(defaultTempFile);
            PrintStream printStream = System.out;
            printStream.println("Created tempFile: " + defaultTempFile.c());
            return defaultTempFile;
        }

        public void b() {
            if (!this.b.isEmpty()) {
                System.out.println("Cleaning up:");
            }
            for (NanoHTTPD.TempFile next : this.b) {
                try {
                    PrintStream printStream = System.out;
                    printStream.println("   " + next.c());
                    next.b();
                } catch (Exception unused) {
                }
            }
            this.b.clear();
        }
    }
}
