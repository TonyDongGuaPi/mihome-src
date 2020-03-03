package com.xiaomi.mimc.example;

import com.coloros.mcssdk.mode.CommandMessage;
import com.xiaomi.mimc.MIMCTokenFetcher;
import com.xiaomi.mimc.json.JSONObject;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TokenFetcher implements MIMCTokenFetcher {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11198a = "TokenFetcher";
    private String b;
    private long c;
    private String d;
    private String e;
    private String f;
    private String g;

    public TokenFetcher(long j, String str, String str2, String str3, String str4, String str5) {
        this.b = str3;
        this.c = j;
        this.d = str;
        this.e = str2;
        this.f = str4;
        this.g = str5;
    }

    public String a() throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.addRequestProperty("Content-Type", "application/json");
        JSONObject jSONObject = new JSONObject();
        jSONObject.b("appId", this.c);
        jSONObject.c("appKey", this.d);
        jSONObject.c(CommandMessage.APP_SECRET, this.e);
        jSONObject.c("appAccount", this.f);
        jSONObject.c("regionKey", this.g);
        httpURLConnection.getOutputStream().write(jSONObject.toString().getBytes("utf-8"));
        if (200 != httpURLConnection.getResponseCode()) {
            MIMCLog.d(f11198a, "con.getResponseCode()!=200");
            System.exit(0);
        }
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(a(readLine));
            } else {
                bufferedReader.close();
                MIMCLog.b(f11198a, stringBuffer.toString());
                return stringBuffer.toString();
            }
        }
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }
}
