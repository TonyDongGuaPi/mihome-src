package com.xiaomi.jr.appbase.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.xiaomi.jr.common.utils.MifiHostsUtils;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonAdapter(GsonDeserializer.class)
public class Configuration {
    static Gson d = new GsonBuilder().create();
    @SerializedName("web_resource_route")
    @JsonAdapter(WebResourceRouteTable.GsonDeserializer.class)

    /* renamed from: a  reason: collision with root package name */
    public WebResourceRouteTable f1401a;
    @SerializedName("xiaomi_services")
    public List<Service> b;
    @SerializedName("web_features")
    public Map<String, List<String>> c;
    private String e;

    public static class Service {
        @SerializedName("sid")

        /* renamed from: a  reason: collision with root package name */
        public String f1402a;
        @SerializedName("base")
        public String b;
        @SerializedName("weblogin")
        public String c;
    }

    public static class WebResourceRouteTable extends HashMap<String, String> {

        public static class GsonDeserializer implements JsonDeserializer<WebResourceRouteTable> {
            /* renamed from: a */
            public WebResourceRouteTable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                WebResourceRouteTable webResourceRouteTable = new WebResourceRouteTable();
                WebResourceRoute webResourceRoute = (WebResourceRoute) jsonDeserializationContext.deserialize(jsonElement, WebResourceRoute.class);
                if (webResourceRoute.f10318a != null) {
                    for (String next : webResourceRoute.f10318a) {
                        String substring = next.substring(next.indexOf("/"));
                        String str = MifiHostsUtils.f(next.substring(0, next.indexOf("/"))) + substring;
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(str.endsWith("/") ? "" : "/");
                        sb.append("index.html");
                        webResourceRouteTable.put(str, sb.toString());
                    }
                }
                if (webResourceRoute.b != null) {
                    for (WebResourceRoute.SpecialItem next2 : webResourceRoute.b) {
                        String str2 = null;
                        String f = MifiHostsUtils.f(next2.f10319a.substring(0, next2.f10319a.indexOf("/")));
                        for (String next3 : next2.b) {
                            if (next3.startsWith(f)) {
                                str2 = next3;
                            }
                        }
                        if (str2 != null) {
                            webResourceRouteTable.put(f + next2.f10319a.substring(next2.f10319a.indexOf("/")), str2);
                        }
                    }
                }
                return webResourceRouteTable;
            }
        }

        private static class WebResourceRoute {
            @SerializedName("default")

            /* renamed from: a  reason: collision with root package name */
            public List<String> f10318a;
            @SerializedName("special")
            public List<SpecialItem> b;

            public static class SpecialItem {
                @SerializedName("src")

                /* renamed from: a  reason: collision with root package name */
                public String f10319a;
                @SerializedName("dests")
                public List<String> b;
            }

            private WebResourceRoute() {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.e = str;
    }

    public String a() {
        return this.e;
    }

    public static class GsonDeserializer implements JsonDeserializer<Configuration> {

        private static class ConfigurationAlias extends Configuration {
            private ConfigurationAlias() {
            }
        }

        /* renamed from: a */
        public Configuration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                ConfigurationAlias configurationAlias = (ConfigurationAlias) jsonDeserializationContext.deserialize(jsonElement, ConfigurationAlias.class);
                configurationAlias.a(jsonElement.toString());
                return configurationAlias;
            } catch (JsonParseException e) {
                throw e;
            }
        }
    }
}
