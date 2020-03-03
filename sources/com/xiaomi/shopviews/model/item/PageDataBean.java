package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okio.Buffer;

public class PageDataBean {
    @SerializedName("COUNTSTATUSPRE")
    @Expose
    public static int r = 0;
    @SerializedName("COUNTSTATUSSTART")
    @Expose
    public static int s = 1;
    @SerializedName("COUNTSTATUSEND")
    @Expose
    public static int t = 2;
    @SerializedName("primaryid")
    @Expose
    public long A;
    @SerializedName("assembly_id")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    public String f13216a;
    @SerializedName("assembly_key")
    @Expose
    public String b;
    @SerializedName("background_color")
    @Expose
    public String c;
    @SerializedName("title")
    @Expose
    public String d;
    @SerializedName("count_down")
    @Expose
    public String e;
    @SerializedName("extended")
    @Expose
    public String f;
    @SerializedName("background_img")
    @Expose
    public String g;
    @SerializedName("more_url")
    @Expose
    public String h;
    @SerializedName("title_color")
    @Expose
    public String i;
    @SerializedName("count_down_color")
    @Expose
    public String j;
    @SerializedName("copy_color")
    @Expose
    public String k;
    @SerializedName("number_color")
    @Expose
    public String l;
    @SerializedName("number_background_colr")
    @Expose
    public String m;
    @SerializedName("timestamp")
    @Expose
    public long n;
    @SerializedName("virtual_name")
    @Expose
    public String o;
    @SerializedName("virtual_item_name")
    @Expose
    public String p;
    @SerializedName("auto_play_interval")
    @Expose
    public int q;
    @SerializedName("countStatus")
    @Expose
    public int u = r;
    @SerializedName("assembly_info")
    @Expose
    public List<AssemblyInfoBean> v = new ArrayList();
    @SerializedName("hasShowMore")
    @Expose
    public boolean w = false;
    @SerializedName("itemHeight")
    @Expose
    public int x = 0;
    @SerializedName("end_time")
    @Expose
    public long y;
    @SerializedName("has_remind")
    @Expose
    public boolean z;

    public static PageDataBean a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static PageDataBean a(ProtoReader protoReader) throws IOException {
        PageDataBean pageDataBean = new PageDataBean();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        pageDataBean.f13216a = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        pageDataBean.b = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        pageDataBean.c = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        pageDataBean.d = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        pageDataBean.e = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 6:
                        pageDataBean.f = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 7:
                        pageDataBean.g = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 8:
                        pageDataBean.h = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 9:
                        pageDataBean.i = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 10:
                        pageDataBean.j = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 11:
                        pageDataBean.k = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 12:
                        pageDataBean.l = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 13:
                        pageDataBean.m = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 14:
                        pageDataBean.v.add(AssemblyInfoBean.a(protoReader));
                        break;
                    case 15:
                        pageDataBean.n = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    case 16:
                        pageDataBean.o = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 17:
                        pageDataBean.p = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 18:
                        pageDataBean.q = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 19:
                        pageDataBean.y = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    case 20:
                        pageDataBean.A = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return pageDataBean;
            }
        }
    }

    public static class AssemblyInfoBean {
        @SerializedName("icon_text")
        @Expose
        public String A;
        @SerializedName("icon_head")
        @Expose
        public String B;
        @SerializedName("type")
        @Expose
        public String C;
        @SerializedName("page_id")
        @Expose
        public String D;
        @SerializedName("hide_currency_label")
        @Expose
        public boolean E;
        @SerializedName("isLight")
        @Expose
        public int F = -1;
        @SerializedName("view_id")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        public String f13217a;
        @SerializedName("image_url")
        @Expose
        public String b;
        @SerializedName("go_to_url")
        @Expose
        public String c;
        @SerializedName("title")
        @Expose
        public String d;
        @SerializedName("price")
        @Expose
        public String e;
        @SerializedName("origin_prince")
        @Expose
        public String f;
        @SerializedName("icon")
        @Expose
        public String g;
        @SerializedName("name")
        @Expose
        public String h;
        @SerializedName("description")
        @Expose
        public String i;
        @SerializedName("video_url")
        @Expose
        public String j;
        @SerializedName("star")
        @Expose
        public String k;
        @SerializedName("funded")
        @Expose
        public String l;
        @SerializedName("funded_ratio")
        @Expose
        public String m;
        @SerializedName("supporters_btn_text")
        @Expose
        public String n;
        @SerializedName("supporters_number")
        @Expose
        public String o;
        @SerializedName("days")
        @Expose
        public String p;
        @SerializedName("days_value")
        @Expose
        public String q;
        @SerializedName("extended")
        @Expose
        public String r;
        @SerializedName("discount")
        @Expose
        public String s;
        @SerializedName("background_color")
        @Expose
        public String t;
        @SerializedName("background_img")
        @Expose
        public String u;
        @SerializedName("title_color")
        @Expose
        public String v;
        @SerializedName("discovery_number")
        @Expose
        public String w;
        @SerializedName("button_title")
        @Expose
        public String x;
        @SerializedName("button_color")
        @Expose
        public String y;
        @SerializedName("description_color")
        @Expose
        public String z;

        public AssemblyInfoBean(String str, String str2, String str3, String str4) {
            this.b = str;
            this.h = str2;
            this.e = str3;
            this.f = str4;
        }

        public AssemblyInfoBean(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.b = str;
            this.d = str2;
            this.h = str3;
            this.k = str6;
            this.e = str4;
            this.p = str7;
            this.i = str8;
            this.f = str5;
        }

        public AssemblyInfoBean(String str, String str2) {
            this.b = str;
            this.h = str2;
        }

        public AssemblyInfoBean(String str, String str2, String str3, String str4, String str5) {
            this.b = str;
            this.d = str2;
            this.c = str4;
            this.C = str3;
        }

        public AssemblyInfoBean(String str, String str2, String str3) {
            this.b = str;
            this.h = str2;
            this.r = str3;
        }

        public AssemblyInfoBean() {
        }

        public static AssemblyInfoBean a(byte[] bArr) throws IOException {
            return a(new ProtoReader(new Buffer().write(bArr)));
        }

        public static AssemblyInfoBean a(ProtoReader protoReader) throws IOException {
            AssemblyInfoBean assemblyInfoBean = new AssemblyInfoBean();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            assemblyInfoBean.f13217a = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            assemblyInfoBean.b = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 3:
                            assemblyInfoBean.c = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 4:
                            assemblyInfoBean.d = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 5:
                            assemblyInfoBean.e = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 6:
                            assemblyInfoBean.f = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 7:
                            assemblyInfoBean.g = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 8:
                            assemblyInfoBean.h = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 9:
                            assemblyInfoBean.i = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 10:
                            assemblyInfoBean.j = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 11:
                            assemblyInfoBean.k = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 12:
                            assemblyInfoBean.l = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 13:
                            assemblyInfoBean.m = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 14:
                            assemblyInfoBean.n = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 15:
                            assemblyInfoBean.o = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 16:
                            assemblyInfoBean.p = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 17:
                            assemblyInfoBean.q = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 18:
                            assemblyInfoBean.r = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 19:
                            assemblyInfoBean.s = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 20:
                            assemblyInfoBean.t = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 21:
                            assemblyInfoBean.u = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 22:
                            assemblyInfoBean.v = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 23:
                            assemblyInfoBean.w = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 24:
                            assemblyInfoBean.x = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 25:
                            assemblyInfoBean.y = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 26:
                            assemblyInfoBean.z = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 27:
                            assemblyInfoBean.A = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 28:
                            assemblyInfoBean.B = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 29:
                            assemblyInfoBean.C = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 30:
                            assemblyInfoBean.D = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 31:
                            assemblyInfoBean.E = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return assemblyInfoBean;
                }
            }
        }
    }
}
