package com.xiaomi.youpin.log;

import android.text.TextUtils;
import java.util.Map;

public class NetRequest {
    public String body = "";
    public long contentLength;
    public String contentType;
    public Map<String, String> heads;
    public String method = "";
    public String source;
    public long time;
    public String url = "";

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request    ");
        sb.append(this.source);
        sb.append("    ");
        sb.append(this.method);
        sb.append("    ");
        sb.append(this.url);
        sb.append("\n");
        if (this.heads != null) {
            for (String next : this.heads.keySet()) {
                sb.append(next);
                sb.append(":");
                sb.append(this.heads.get(next));
                sb.append("\n");
            }
        }
        if (!TextUtils.isEmpty(this.body)) {
            sb.append(this.body);
            sb.append("\n");
        }
        return sb.toString();
    }
}
