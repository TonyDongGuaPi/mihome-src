package com.xiaomi.account.diagnosis;

import android.util.Pair;
import com.facebook.internal.AnalyticsEvents;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.account.diagnosis.encrypt.EncryptLog;
import com.xiaomi.accountsdk.utils.DiagnosisLogInterface;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Random;

class RealDiagnosisLogger implements DiagnosisLogInterface {
    private static final String TAG = "AccountDiagnosisLogger";
    private static final Random sRandom = new Random();

    private enum HttpMethod {
        GET,
        POST
    }

    RealDiagnosisLogger() {
    }

    public void logResponseCode(String str, int i) {
        appendLogLine("Response code=" + i + ", request id=" + str);
    }

    private static void appendLogLine(String str) {
        EncryptLog.e(TAG, str);
    }

    private static String generateShortUUID() {
        return Integer.toHexString(sRandom.nextInt());
    }

    public void logResponseDecryptedBody(String str) {
        appendLogLine("DecryptedBody: " + str);
    }

    public void logResponse(String str, String str2, Map<String, List<String>> map, Map<String, String> map2) {
        appendLogLine("request id: " + str + "\n" + "raw response body: " + str2 + "\n" + "response headers: " + map + "\n" + "response cookies: " + map2);
    }

    private static Pair<String, String> resolveHost(String str) {
        String str2;
        String str3;
        try {
            str2 = InetAddress.getByName(str).toString();
        } catch (UnknownHostException unused) {
            str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            StringBuilder sb = new StringBuilder();
            sb.append(Operators.BRACKET_START_STR);
            for (InetAddress append : allByName) {
                sb.append(append);
                sb.append(",");
            }
            sb.append(Operators.BRACKET_END_STR);
            str3 = sb.toString();
        } catch (UnknownHostException unused2) {
            str3 = "(Unknown)";
        }
        return Pair.create(str2, str3);
    }

    private static String getDomainName(String str) {
        try {
            return new URI(str).getHost();
        } catch (URISyntaxException unused) {
            return null;
        }
    }

    public void logRequestException(Exception exc) {
        String stackTraceString = getStackTraceString(exc);
        appendLogLine("RequestException: " + stackTraceString);
    }

    private static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, false);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public String logGetRequest(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3) {
        return logRequest(HttpMethod.GET, str, map, str2, (Map<String, String>) null, map3, map2);
    }

    public String logPostRequest(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) {
        return logRequest(HttpMethod.POST, str, map, str2, map2, map3, map4);
    }

    private static String logRequest(HttpMethod httpMethod, String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) {
        String generateShortUUID = generateShortUUID();
        Pair<String, String> resolveHost = resolveHost(getDomainName(str));
        StringBuilder sb = new StringBuilder();
        sb.append(httpMethod.toString());
        sb.append(" request id=");
        sb.append(generateShortUUID);
        sb.append("\n");
        sb.append("fullUrl: ");
        sb.append(str2);
        sb.append("\n");
        sb.append("addr: ");
        sb.append((String) resolveHost.first);
        sb.append(", addr list: ");
        sb.append((String) resolveHost.second);
        sb.append("\n");
        sb.append("url: ");
        sb.append(str);
        sb.append("\n");
        sb.append("urlParams: ");
        sb.append(map);
        sb.append("\n");
        if (map2 != null) {
            sb.append("postParams: ");
            sb.append(map2);
            sb.append("\n");
        }
        sb.append("headers: ");
        sb.append(map4);
        sb.append("\n");
        sb.append("cookies: ");
        sb.append(map3);
        appendLogLine(sb.toString());
        return generateShortUUID;
    }
}
