package miuipub.util.async.tasks;

import java.util.Map;
import miuipub.net.http.HttpSession;
import miuipub.util.async.tasks.HttpTask;
import org.json.JSONArray;

public class HttpJsonArrayTask extends HttpTextTask<JSONArray> {
    public HttpJsonArrayTask(String str) {
        super(str);
    }

    public HttpJsonArrayTask(HttpSession httpSession, String str) {
        super(httpSession, str);
    }

    public HttpJsonArrayTask(HttpSession httpSession, HttpTask.Method method, String str, Map<String, String> map) {
        super(httpSession, method, str, map);
    }

    /* renamed from: a */
    public JSONArray h() throws Exception {
        return new JSONArray(i());
    }
}
