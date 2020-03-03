package com.xiaomi.jr.web.staticresource;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class UpdateInfo {
    @SerializedName("update")

    /* renamed from: a  reason: collision with root package name */
    public boolean f1462a;
    @SerializedName("type")
    public int b;
    @SerializedName("timestamp")
    public long c;
    @SerializedName("info")
    public Info d;

    public static class Info {
        @SerializedName("lastModified")

        /* renamed from: a  reason: collision with root package name */
        public Map<String, String> f1463a;
        @SerializedName("path")
        public String b;
        @SerializedName("md5")
        public String c;
        @SerializedName("baseMd5")
        public String d;
        @SerializedName("fullMd5")
        public String e;
        public BaseType f;

        public enum BaseType {
            LAST,
            BUILDIN
        }
    }
}
