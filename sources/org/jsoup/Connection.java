package org.jsoup;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public interface Connection {

    public interface Base<T extends Base> {
        String a(String str);

        URL a();

        T a(String str, String str2);

        T a(URL url);

        T a(Method method);

        List<String> b(String str);

        T b(String str, String str2);

        Method b();

        Map<String, String> c();

        boolean c(String str);

        boolean c(String str, String str2);

        Map<String, List<String>> d();

        T d(String str);

        T d(String str, String str2);

        String e(String str);

        Map<String, String> e();

        boolean f(String str);

        T g(String str);
    }

    public interface KeyVal {
        String a();

        KeyVal a(InputStream inputStream);

        KeyVal a(String str);

        String b();

        KeyVal b(String str);

        InputStream c();

        KeyVal c(String str);

        boolean d();

        String e();
    }

    public interface Request extends Base<Request> {
        Request a(int i);

        Request a(String str, int i);

        Request a(Proxy proxy);

        Request a(KeyVal keyVal);

        Request a(Parser parser);

        Request a(boolean z);

        Request b(int i);

        Request b(boolean z);

        Request c(boolean z);

        void d(boolean z);

        Proxy f();

        int g();

        int h();

        Request h(String str);

        Request i(String str);

        boolean i();

        boolean j();

        boolean k();

        boolean l();

        Collection<KeyVal> m();

        String n();

        Parser o();

        String p();
    }

    public interface Response extends Base<Response> {
        int f();

        String g();

        String h();

        Response h(String str);

        String i();

        Document j() throws IOException;

        String k();

        byte[] l();

        Response m();

        BufferedInputStream n();
    }

    Connection a(int i);

    Connection a(String str);

    Connection a(String str, int i);

    Connection a(String str, String str2);

    Connection a(String str, String str2, InputStream inputStream);

    Connection a(String str, String str2, InputStream inputStream, String str3);

    Connection a(Proxy proxy);

    Connection a(URL url);

    Connection a(Collection<KeyVal> collection);

    Connection a(Map<String, String> map);

    Connection a(Method method);

    Connection a(Request request);

    Connection a(Response response);

    Connection a(Parser parser);

    Connection a(boolean z);

    Connection a(String... strArr);

    Document a() throws IOException;

    Connection b(int i);

    Connection b(String str);

    Connection b(String str, String str2);

    Connection b(Map<String, String> map);

    Connection b(boolean z);

    Document b() throws IOException;

    Response c() throws IOException;

    Connection c(String str);

    Connection c(String str, String str2);

    Connection c(Map<String, String> map);

    Connection c(boolean z);

    KeyVal d(String str);

    Request d();

    Connection d(boolean z);

    Response e();

    Connection e(String str);

    Connection f(String str);

    public enum Method {
        GET(false),
        POST(true),
        PUT(true),
        DELETE(false),
        PATCH(true),
        HEAD(false),
        OPTIONS(false),
        TRACE(false);
        
        private final boolean hasBody;

        private Method(boolean z) {
            this.hasBody = z;
        }

        public final boolean hasBody() {
            return this.hasBody;
        }
    }
}
