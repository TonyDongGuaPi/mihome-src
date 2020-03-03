package com.xiaomi.smarthome.framework.plugin.web.command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    /* renamed from: a  reason: collision with root package name */
    static Map<String, Class<?>> f17652a = new HashMap();

    static {
        f17652a.put("PluginBridgeReady", PluginBridgeReadyCommand.class);
        f17652a.put("rpc", RpcCommand.class);
    }

    public static BaseCommand a(String str) {
        try {
            Class cls = f17652a.get(str);
            if (cls == null) {
                return null;
            }
            return (BaseCommand) cls.newInstance();
        } catch (IllegalAccessException | InstantiationException unused) {
            throw new IllegalArgumentException("no command found for name: " + str);
        }
    }
}
