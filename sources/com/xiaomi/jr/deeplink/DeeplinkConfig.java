package com.xiaomi.jr.deeplink;

import com.xiaomi.jr.deeplink.DeeplinkPolicy;
import java.util.HashMap;
import java.util.Map;

public class DeeplinkConfig {
    static DeeplinkPolicy.TargetProcessor sBasicTargetProcessor;
    static DeeplinkPolicy.DeeplinkProcessor sDeeplinkProcessor;
    static RouteBuilder sDefaultRouteBuilder;
    static Map<String, String> sDeprecatedDeeplinkTable;
    static Map<DeeplinkPolicy.DeeplinkMatcher, RouteBuilder> sPatternRouteBuilderTable;
    static Map<String, RouteBuilder> sSimpleRouteBuilderTable;
    static UrlTranslator sUrlTranslator;

    public interface RouteBuilder {
        DeeplinkPolicy.Target build(String str);
    }

    public interface UrlTranslator {
        String toEnv(String str);

        String toNormal(String str);
    }

    static {
        initSimpleRouteBuilderTable();
        initPatternRouteBuilderTable();
        initDeprecatedDeeplinkTable();
    }

    public static void addSimpleRouteBuilders(Map<String, RouteBuilder> map) {
        sSimpleRouteBuilderTable.putAll(map);
    }

    public static Map<String, RouteBuilder> getSimpleRouteBuilderTable() {
        return sSimpleRouteBuilderTable;
    }

    public static void addPatternRouteBuilders(Map<DeeplinkPolicy.DeeplinkMatcher, RouteBuilder> map) {
        sPatternRouteBuilderTable.putAll(map);
    }

    public static Map<DeeplinkPolicy.DeeplinkMatcher, RouteBuilder> getPatternRouteBuilderTable() {
        return sPatternRouteBuilderTable;
    }

    public static void addDeprecatedDeeplinkTable(Map<String, String> map) {
        sDeprecatedDeeplinkTable.putAll(map);
    }

    public static void setDeeplinkProcessor(DeeplinkPolicy.DeeplinkProcessor deeplinkProcessor) {
        sDeeplinkProcessor = deeplinkProcessor;
    }

    public static void setBasicTargetProcessor(DeeplinkPolicy.TargetProcessor targetProcessor) {
        sBasicTargetProcessor = targetProcessor;
    }

    public static void setUrlTranslator(UrlTranslator urlTranslator) {
        sUrlTranslator = urlTranslator;
    }

    public static void setDefaultRouteBuilder(RouteBuilder routeBuilder) {
        sDefaultRouteBuilder = routeBuilder;
    }

    private static void initSimpleRouteBuilderTable() {
        sSimpleRouteBuilderTable = new HashMap();
    }

    private static void initPatternRouteBuilderTable() {
        sPatternRouteBuilderTable = new HashMap();
    }

    private static void initDeprecatedDeeplinkTable() {
        sDeprecatedDeeplinkTable = new HashMap();
    }
}
