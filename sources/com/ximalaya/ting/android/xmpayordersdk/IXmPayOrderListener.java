package com.ximalaya.ting.android.xmpayordersdk;

import com.amap.api.services.core.AMapException;
import java.util.HashMap;
import java.util.Map;

public interface IXmPayOrderListener {
    public static final int A = 432;
    public static final int B = 433;
    public static final Map<Integer, String> C = new HashMap<Integer, String>() {
        {
            put(2, "取消购买");
            put(4, "用户未登录");
            put(110, AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
            put(101, "网络错误");
            put(102, "下单接口 json提取失败");
            put(200, "下单成功");
            put(500, "下单失败");
            put(100, "参数常规校验失败");
            put(600, "待购买内容已下架");
            put(601, "待购买声音来自不同专辑");
            put(602, "单价校验失败");
            put(Integer.valueOf(IXmPayOrderListener.l), "已存在未支付订单包含重叠付费音频");
            put(3, "购买类型不支持");
            put(Integer.valueOf(IXmPayOrderListener.n), "应用没有付费音频接入资格");
            put(615, "已经购买");
            put(1014, "需要登录");
            put(Integer.valueOf(IXmPayOrderListener.o), "应用IP不在白名单中");
            put(Integer.valueOf(IXmPayOrderListener.p), "查询价格信息的专辑不存在");
            put(1015, "获取价格信息出错");
            put(401, "订单超时");
            put(Integer.valueOf(IXmPayOrderListener.y), "订单处理中");
            put(Integer.valueOf(IXmPayOrderListener.z), "支付宝支付失败");
            put(Integer.valueOf(IXmPayOrderListener.A), "签名错误");
            put(Integer.valueOf(IXmPayOrderListener.B), "支付渠道错误");
            put(Integer.valueOf(IXmPayOrderListener.r), "已经购买");
            put(Integer.valueOf(IXmPayOrderListener.s), "声音资源是免费试听的，无需购买");
            put(Integer.valueOf(IXmPayOrderListener.t), "购买的资源是单集购买类型，不能当做整张类型购买");
            put(Integer.valueOf(IXmPayOrderListener.u), "购买的商品不存在");
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f2378a = 2;
    public static final int b = 4;
    public static final int c = 110;
    public static final int d = 101;
    public static final int e = 102;
    public static final int f = 200;
    public static final int g = 500;
    public static final int h = 100;
    public static final int i = 600;
    public static final int j = 601;
    public static final int k = 602;
    public static final int l = 603;
    public static final int m = 3;
    public static final int n = 609;
    public static final int o = 610;
    public static final int p = 614;
    public static final int q = 615;
    public static final int r = 618;
    public static final int s = 619;
    public static final int t = 620;
    public static final int u = 621;
    public static final int v = 1014;
    public static final int w = 1015;
    public static final int x = 401;
    public static final int y = 430;
    public static final int z = 431;

    void a(PayFinishModel payFinishModel);
}
