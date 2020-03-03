package com.xiaomi.shopviews.model.item;

import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.HashMap;

public class HomeItemContentFactory {
    public static final int A = 11;
    public static final String B = "nav_normal";
    public static final int C = 12;
    public static final String D = "discovery_article";
    public static final int E = 13;
    public static final String F = "discovery_video";
    public static final int G = 14;
    public static final String H = "discovery_gallery";
    public static final int I = 15;
    public static final String J = "virtual_view";
    public static final int K = 100;
    public static final int L = 17;
    public static final int M = 18;
    public static final int N = 19;
    public static final int O = 20;
    public static final int P = 21;
    public static final int Q = 22;
    public static final int R = 23;
    public static final int S = 24;
    public static final int T = 25;
    public static final int U = 26;
    public static final int V = 27;
    public static final int W = 28;
    private static HashMap X = new HashMap() {
        {
            put(HomeItemContentFactory.b, 1);
            put(HomeItemContentFactory.d, 2);
            put(HomeItemContentFactory.e, 2);
            put(HomeItemContentFactory.f, 2);
            put(HomeItemContentFactory.h, 3);
            put(HomeItemContentFactory.j, 4);
            put(HomeItemContentFactory.l, 5);
            put(HomeItemContentFactory.n, 6);
            put(HomeItemContentFactory.p, 30);
            put(HomeItemContentFactory.r, 7);
            put(HomeItemContentFactory.t, 8);
            put(HomeItemContentFactory.v, 9);
            put(HomeItemContentFactory.x, 10);
            put(HomeItemContentFactory.z, 11);
            put(HomeItemContentFactory.B, 12);
            put(HomeItemContentFactory.D, 13);
            put(HomeItemContentFactory.F, 14);
            put(HomeItemContentFactory.H, 15);
            put(HomeItemContentFactory.J, 100);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13188a = 0;
    public static final String b = "slider_with_nav";
    public static final int c = 1;
    public static final String d = "slider_with_dot_small";
    public static final String e = "slider_with_dot_middle";
    public static final String f = "slider_with_dot_big";
    public static final int g = 2;
    public static final String h = "block_product_three";
    public static final int i = 3;
    public static final String j = "slider_product_noprice";
    public static final int k = 4;
    public static final String l = "title_line";
    public static final int m = 5;
    public static final String n = "slider_flash_sale_new";
    public static final int o = 6;
    public static final String p = "slider_flash_sale_cfg";
    public static final int q = 30;
    public static final String r = "slider_comments";
    public static final int s = 7;
    public static final String t = "block_discovery_list";
    public static final int u = 8;
    public static final String v = "block_golden_oil";
    public static final int w = 9;
    public static final String x = "block_product_grid";
    public static final int y = 10;
    public static final String z = "slider_no_dot";

    public static HomeItemContentBase a(ProtoReader protoReader, HomeItemData homeItemData) throws IOException {
        return null;
    }

    public static int a(PageDataBean pageDataBean) {
        if (X.containsKey(pageDataBean.b)) {
            return ((Integer) X.get(pageDataBean.b)).intValue();
        }
        return 0;
    }
}
