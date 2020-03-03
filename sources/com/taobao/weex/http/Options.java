package com.taobao.weex.http;

import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

class Options {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private String body;
    private Map<String, String> headers;
    private String method;
    private int timeout;
    private Type type;
    private String url;

    /* renamed from: com.taobao.weex.http.Options$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2892409795661936566L, "com/taobao/weex/http/Options$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2056840449418545231L, "com/taobao/weex/http/Options", 10);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ Options(String str, String str2, Map map, String str3, Type type2, int i, AnonymousClass1 r7) {
        this(str, str2, map, str3, type2, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[9] = true;
    }

    private Options(String str, String str2, Map<String, String> map, String str3, Type type2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.type = Type.text;
        this.timeout = 3000;
        this.method = str;
        this.url = str2;
        this.headers = map;
        this.body = str3;
        this.type = type2;
        if (i != 0) {
            $jacocoInit[0] = true;
        } else {
            $jacocoInit[1] = true;
            i = 3000;
        }
        this.timeout = i;
        $jacocoInit[2] = true;
    }

    public String getMethod() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.method;
        $jacocoInit[3] = true;
        return str;
    }

    public String getUrl() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.url;
        $jacocoInit[4] = true;
        return str;
    }

    public Map<String, String> getHeaders() {
        boolean[] $jacocoInit = $jacocoInit();
        Map<String, String> map = this.headers;
        $jacocoInit[5] = true;
        return map;
    }

    public String getBody() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.body;
        $jacocoInit[6] = true;
        return str;
    }

    public Type getType() {
        boolean[] $jacocoInit = $jacocoInit();
        Type type2 = this.type;
        $jacocoInit[7] = true;
        return type2;
    }

    public int getTimeout() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.timeout;
        $jacocoInit[8] = true;
        return i;
    }

    public enum Type {
        json,
        text,
        jsonp;

        static {
            boolean[] $jacocoInit;
            $jacocoInit[3] = true;
        }
    }

    public static class Builder {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private String body;
        private Map<String, String> headers = new HashMap();
        private String method;
        private int timeout;
        private Type type;
        private String url;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(5770907758860853562L, "com/taobao/weex/http/Options$Builder", 13);
            $jacocoData = a2;
            return a2;
        }

        public Builder() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            $jacocoInit[1] = true;
        }

        public Builder setMethod(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.method = str;
            $jacocoInit[2] = true;
            return this;
        }

        public Builder setUrl(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.url = str;
            $jacocoInit[3] = true;
            return this;
        }

        public Builder putHeader(String str, String str2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.headers.put(str, str2);
            $jacocoInit[4] = true;
            return this;
        }

        public Builder setBody(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.body = str;
            $jacocoInit[5] = true;
            return this;
        }

        public Builder setType(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            if (Type.json.name().equals(str)) {
                this.type = Type.json;
                $jacocoInit[6] = true;
            } else if (Type.jsonp.name().equals(str)) {
                this.type = Type.jsonp;
                $jacocoInit[7] = true;
            } else {
                this.type = Type.text;
                $jacocoInit[8] = true;
            }
            $jacocoInit[9] = true;
            return this;
        }

        public Builder setType(Type type2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.type = type2;
            $jacocoInit[10] = true;
            return this;
        }

        public Builder setTimeout(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.timeout = i;
            $jacocoInit[11] = true;
            return this;
        }

        public Options createOptions() {
            boolean[] $jacocoInit = $jacocoInit();
            Options options = new Options(this.method, this.url, this.headers, this.body, this.type, this.timeout, (AnonymousClass1) null);
            $jacocoInit[12] = true;
            return options;
        }
    }
}
