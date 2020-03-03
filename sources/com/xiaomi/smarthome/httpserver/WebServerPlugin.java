package com.xiaomi.smarthome.httpserver;

import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.io.File;
import java.util.Map;

public interface WebServerPlugin {
    NanoHTTPD.Response a(String str, Map<String, String> map, NanoHTTPD.IHTTPSession iHTTPSession, File file, String str2);

    void a(Map<String, String> map);

    boolean a(String str, File file);
}
