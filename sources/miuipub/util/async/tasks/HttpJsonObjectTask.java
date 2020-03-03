package miuipub.util.async.tasks;

import java.util.Map;
import miuipub.net.http.HttpSession;
import miuipub.util.async.tasks.HttpTask;
import org.json.JSONObject;

public class HttpJsonObjectTask extends HttpTextTask<JSONObject> {
    public HttpJsonObjectTask(String str) {
        super(str);
    }

    public HttpJsonObjectTask(HttpSession httpSession, String str) {
        super(httpSession, str);
    }

    public HttpJsonObjectTask(HttpSession httpSession, HttpTask.Method method, String str, Map<String, String> map) {
        super(httpSession, method, str, map);
    }

    /* renamed from: a */
    public JSONObject h() throws Exception {
        return new JSONObject(i());
    }
}
