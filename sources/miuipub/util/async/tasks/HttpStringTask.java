package miuipub.util.async.tasks;

import java.util.Map;
import miuipub.net.http.HttpSession;
import miuipub.util.async.tasks.HttpTask;

public class HttpStringTask extends HttpTextTask<String> {
    public HttpStringTask(String str) {
        this((HttpSession) null, HttpTask.Method.Get, str, (Map<String, String>) null);
    }

    public HttpStringTask(HttpSession httpSession, String str) {
        this(httpSession, HttpTask.Method.Get, str, (Map<String, String>) null);
    }

    public HttpStringTask(HttpSession httpSession, HttpTask.Method method, String str, Map<String, String> map) {
        super(httpSession, method, str, map);
    }

    /* renamed from: a */
    public String h() throws Exception {
        return i();
    }
}
