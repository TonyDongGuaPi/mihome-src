package com.xiaomi.youpin.log;

import android.text.TextUtils;
import java.util.Map;

public class NetResponse {
    public int code = 0;
    public String content = "";
    public long contentLength = 0;
    public String contentType = "";
    public Map<String, String> heads;
    public long time = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response    ");
        sb.append(this.code);
        sb.append("\n");
        if (this.heads != null) {
            for (String next : this.heads.keySet()) {
                sb.append(next);
                sb.append(":");
                sb.append(this.heads.get(next));
                sb.append("\n");
            }
        }
        if (!TextUtils.isEmpty(this.content)) {
            sb.append(this.content);
            sb.append("\n");
        }
        return sb.toString();
    }
}
