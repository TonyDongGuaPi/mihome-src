package com.mi.global.shop.util;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.buy.BFLVerifyOTPFragment;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.SyncModel;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.Utils;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.controller.RemoteDataUploadManager;
import com.mi.mistatistic.sdk.controller.asyncjobs.CorrectingServerTimeJob;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class ConnectionHelper {
    public static String A = "";
    public static String B = "";
    public static String C = "";
    public static String D = "http://sg.appeye.appmifile.com";
    public static String E = "mi.com";
    public static final String F = "mi.co.id";
    public static String G = (E + "/" + LocaleHelper.b);
    public static String H = null;
    public static final String I = "/app";
    public static String J = LocaleHelper.b;
    public static final String K = "/";
    public static final String L = "/service/";
    public static final String M = "/user";
    public static final String N = "/category/";
    public static final String O = "/discover/";
    public static final String P = "/cart";
    public static final String Q = "/cart/add";
    public static final String R = "/cart/delete";
    public static final String S = "/cart/deletebatch";
    public static final String T = "/cart/update";
    public static final String U = "/cart-insurance/";
    public static final String V = "/accessories/";
    public static final String W = "/item/";
    public static final String X = "/user/orderlist?type=0";
    public static final String Y = "/user/orderlist?type=2";
    public static final String Z = "/user/orderlist?type=5";

    /* renamed from: a  reason: collision with root package name */
    public static String f7052a = "http://mobile.mi.com/";
    public static final String aA = "/order/refundapply";
    public static final String aB = "/user/expressinfo/express_sn/";
    public static final String aC = "/cod/sendcodcode/";
    public static final String aD = "/cod/codconfirmbysms";
    public static final String aE = "/cod/codconfirmbymanual";
    public static final String aF = "/cod/codconfirmbynoverify";
    public static final String aG = "/cod/aftercodconfirm";
    public static final String aH = "/cod/codimagecode?order_id=";
    public static final String aI = "/order/getauthcode?";
    public static final String aJ = "/captcha?type=ordercancel";
    public static final String aK = "/buy/payinfo";
    public static final String aL = "/user/ordercancel";
    public static final String aM = "/app/sync";
    public static final String aN = "/app/private";
    public static final String aO = "/app/block";
    public static final String aP = "/page/home";
    public static final String aQ = "/page/discovery";
    public static final String aR = "/discovery/view";
    public static final String aS = "/user/up";
    public static final String aT = "/buy/paywallet";
    public static final String aU = "/app/logincallback";
    public static final String aV = "/app/refreshuserinfo";
    public static final String aW = "/user";
    public static final String aX = "/loyalty/updatebirthday";
    public static final String aY = "/buy/changeaddress";
    public static final String aZ = "/app/plugin";
    public static final String aa = "/user/orderlist?type=6";
    public static final String ab = "/user/orderlist?type=7";
    public static final String ac = "/user/usercentral/orderlist/review";
    public static final String ad = "/user/orderlist?type=8";
    public static final String ae = "/user/orderlist?type=17";
    public static final String af = "/user/address";
    public static final String ag = "/user/cards/";
    public static final String ah = "/user/coupon";
    public static final String ai = "/user/notifymessage";
    public static final String aj = "/user/message/";
    public static final String ak = "/arrivalnotice/list/";
    public static final String al = "/address/checkzipcode";
    public static final String am = "/address/save";
    public static final String an = "/address/del";
    public static final String ao = "/address/setdefault";
    public static final String ap = "/fcode";
    public static final String aq = "com.mi.global.shop.web/web/static/service.html";
    public static final String ar = "/user/reward/";
    public static final String as = "/service/authorized_stores/";
    public static final String at = "/service/mihome/";
    public static final String au = "/mihome/getdata";
    public static final String av = "/user/orderview";
    public static final String aw = "/buy/paygoapp/";
    public static final String ax = "/buy/checkout/";
    public static final String ay = "/buy/checkoutoneclick/";
    public static final String az = "/buy/submit/";
    public static final String b = "http://m.buy.mi.com/";
    public static final String bA = "/find";
    public static final String bB = "/user/up";
    public static final String bC = "/comment/getgoodslist";
    public static final String bD = "/comment/getgoodscount";
    public static final String bE = "/comment/writeevaluate";
    public static final String bF = "^https*://m*event.c.mi.com.*";
    public static final String bG = "/user/cards";
    public static final String bH = "/buy/paygo";
    public static final String bI = "/buy/paygo";
    public static final String bJ = "/buy/paygo";
    public static final String bK = "/feedback/add";
    public static final String bL = "/mifinance/getemiplan";
    public static final String bM = "/mifinance/createloanapplication";
    public static final String bN = "/mifinance/getloanagreement";
    public static final String bO = "/mifinance/sendotp";
    public static final String bP = "/buy/sendotp";
    public static final String bQ = "/buy/paygo";
    public static final String bR = "http://push.buy.mi.com/in/client/pushtypes";
    public static final String bS = "http://push.buy.test.mi.com/in/client/pushtypes";
    public static String bT = "http://push.buy.mi.com/";
    public static String bU = "http://push.buy.test.mi.com/";
    public static final String bV = "/client/register";
    public static final String bW = "/loyalty/share";
    public static final String bX = "/buy/checkgstin";
    public static final String bY = "/buy/payment";
    public static final String bZ = "/in/user/miexchange";
    public static final String ba = "/app/register";
    public static final String bb = "https://twitter.com/XiaomiIndia";
    public static final String bc = "https://www.facebook.com/XiaomiIndia";
    public static final String bd = "1495988390615727";
    public static final String be = "https://www.facebook.com/xiaomitaiwan";
    public static final String bf = "360450250740751";
    public static final String bg = "https://www.facebook.com/Xiaomihongkong/";
    public static final String bh = "509277899107138";
    public static final String bi = "https://www.facebook.com/XiaomiEspana/";
    public static final String bj = "513539172312954";
    public static final String bk = "https://www.facebook.com/xiaomifrance/";
    public static final String bl = "2022323384452365";
    public static final String bm = "https://facebook.com/XiaomiIndonesia";
    public static final String bn = "123539107849000";
    public static final String bo = "https://www.facebook.com/XiaomiItalia";
    public static final String bp = "1841811919163992";
    public static final String bq = "https://www.facebook.com/XiaomiUK";
    public static final String br = "679717119077508";
    public static final String bs = "https://www.facebook.com/XiaomiRussia";
    public static final String bt = "1683369018657863";
    public static final String bu = "https://account.xiaomi.com/";
    public static final String bv = "pass/auth/security/home";
    public static final String bw = "pass/sns/login/load/token";
    public static final String bx = "/service/privacy";
    public static final String by = "/service/agreement";
    public static final String bz = "/service/terms";
    public static final String c = "http://app.buy.mi.com/";
    public static final String cA = "/recommend";
    public static final String cB = "/search?type=android&word=";
    public static final String cC = "/searchresult";
    public static final String cD = "/couponcode";
    public static final String cE = "/openbuy/info";
    public static final String cF = "/openbuy/status";
    public static final String cG = "/openbuy/showjoin";
    public static final String cH = "/openbuy/add";
    public static final String cI = "gettimestamp";
    public static final String cJ = "https://tp.test.mi.com/gettimestamp/in?version=2.0";
    public static final String cK = "hdinfo/in";
    public static final String cL = "https://tp.test.mi.com/hdinfo/in?version=2.0";
    public static final String cM = "hdget/in";
    public static final String cN = "https://tp.test.mi.com/hdget/in";
    public static final String cO = "https://www.facebook.com/notes/mi-india/registration-guide/1617763071771591";
    public static final String cP = "https://mobile.mi.com/in/service/preorder_info/";
    public static final String cQ = "/flashsale/condition/";
    public static String cR = null;
    public static String cS = ("https://go-buy.test.mi.com/" + LocaleHelper.b + "/httpdns");
    public static String cT = (ShopApp.j() ? "staging.m.id.mipay.com" : "m.id.mipay.com");
    public static final String cU = "/app/assemblyinfo";
    public static final String cV = "/eventapi/api/caigame/agreetc";
    public static final String cW = "/eventapi/api/caigame/scanphoto";
    public static final String cX = "/user/invoice/";
    public static final String cY = "/user/qrcode?channel=app";
    public static final String cZ = "/eventapi/api/warmup/follow";
    public static final String ca = "in/exchange/couponlist";
    public static final String cb = "/user/exchangerecord";
    public static final String cc = "/miexchange/selectdevice/";
    public static final String cd = "v1/get-device-quote/automate/register";
    public static final String ce = "v1/get-device-quote/automate/quote";
    public static final String cf = "/miexchange/enterimei/?";
    public static final String cg = "in/miexchange/diagnosetool";
    public static final String ch = "mistore://mobile.mi.com/in/?diagnosetool=1";
    public static final String ci = "http://xiaomi.api.stage.cashify.in/";
    public static final String cj = "http://xiaomi.api.cashify.in/";
    public static String ck = cj;
    public static String cl = (f7052a + "in/service/smartboxdelivery/");
    public static String cm = "in/mifinance/upload";

    /* renamed from: cn  reason: collision with root package name */
    public static final String f7053cn = "/giftcard/resend/";
    public static final String co = "/giftcard/list?";
    public static final String cp = "/giftcard/bind";
    public static final String cq = "/app/giftcard";
    public static final String cr = "/giftcard";
    public static final String cs = "/user/giftcard";
    public static final String ct = "/user/giftcard#expense";
    public static final String cu = "/giftcard/imgcode";
    public static String cv = ("https://www.miui.com/res/doc/eula/" + LocaleHelper.b + Constants.m);
    public static String cw = ("https://mobile.mi.com/" + LocaleHelper.b + "/app/service/terms/");
    public static final String cx = "/user/privilege";
    public static final String cy = "/user/expresslist";
    public static final String cz = "/group/list";
    public static final String d = "https://m.buy.mi.com/";
    public static final String da = "/eventapi/api/warmup/unfollow";
    private static final String db = "ConnectionHelper";
    private static Random dc = null;
    public static String e = "http://mobile.test.mi.com/";
    public static final String f = "http://m-buy.test.mi.com/";
    public static final String g = "http://m-buy.test.mi.com/";
    public static final String h = "http://hd-c.test.mi.com/";
    public static final String i = "http://hd.c.mi.com/";
    public static final String j = "mobile.mi.com/";
    public static String k = null;
    public static String l = "http://go-buy.test.mi.com/";
    public static final String m = "https://go.buy.mi.com/";
    public static final String n = "http://go.appmifile.com/";
    public static final String o = "http://sg.support.kefu.mi.com";
    public static final String p = "https://sg.support.kefu.mi.com";
    public static String q = "http://in-fs.buy.mi.com/";
    public static String r = "http://fs-haiwai.test.mi.com/";
    public static String s = "https://in-d2s.buy.mi.com/";
    public static String t = "https://in-tp.mi.com/";
    public static String u = "";
    public static String v = "";
    public static String w = "";
    public static String x = "";
    public static String y = "";
    public static String z = "";

    public static String ak() {
        return bb;
    }

    public static String aw() {
        return "https://account.xiaomi.com/pass/auth/security/home";
    }

    static {
        String str;
        String str2;
        StringBuilder sb;
        String str3;
        if (LocaleHelper.g()) {
            str = "https://in-go.buy.mi.com/";
        } else {
            str = LocaleHelper.n() ? "https://ru-go.buy.mi.com/" : m;
        }
        k = str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("/");
        sb2.append(LocaleHelper.b);
        H = sb2.toString();
        if (LocaleHelper.g()) {
            str2 = "https://in-go.buy.mi.com/" + LocaleHelper.b + "/httpdns";
        } else {
            if (LocaleHelper.n()) {
                sb = new StringBuilder();
                str3 = "https://ru-go.buy.mi.com/";
            } else {
                sb = new StringBuilder();
                str3 = m;
            }
            sb.append(str3);
            sb.append(LocaleHelper.b);
            sb.append("/httpdns");
            str2 = sb.toString();
        }
        cR = str2;
        c();
        a();
    }

    public static void a() {
        if (ShopApp.c) {
            u = UrlUtil.b(u);
            v = UrlUtil.b(v);
            l = UrlUtil.b(l);
            k = UrlUtil.b(k);
            z = UrlUtil.b(z);
            bT = UrlUtil.b(bT);
            bU = UrlUtil.b(bU);
            y = UrlUtil.b(y);
            w = UrlUtil.b(w);
            f7052a = UrlUtil.d(f7052a);
            e = UrlUtil.d(e);
            r = UrlUtil.b(r);
            q = UrlUtil.b(q);
            C = UrlUtil.b(C);
            s = UrlUtil.b(s);
        } else {
            c();
        }
        b();
    }

    public static void b() {
        ArrayList<DomainModel> bJ2 = bJ();
        u = a(bJ2, u);
        v = a(bJ2, v);
        l = a(bJ2, l);
        k = a(bJ2, k);
        z = a(bJ2, z);
        bT = a(bJ2, bT);
        bU = a(bJ2, bU);
        y = a(bJ2, y);
        w = a(bJ2, w);
        f7052a = a(bJ2, f7052a);
        e = a(bJ2, e);
        cT = a(bJ2, cT);
        x = a(bJ2, x);
        com.mi.mistatistic.sdk.Constants.f7315a = a(bJ2, com.mi.mistatistic.sdk.Constants.f7315a);
        bK();
        if (ShopApp.n()) {
            A = z;
            B = z;
            return;
        }
        bL();
    }

    private static String r(String str) {
        return a(bJ(), str);
    }

    private static ArrayList<DomainModel> bJ() {
        ArrayList<DomainModel> arrayList = new ArrayList<>();
        try {
            return (ArrayList) new Gson().fromJson(Utils.Preference.getStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a), new TypeToken<ArrayList<DomainModel>>() {
            }.getType());
        } catch (Exception e2) {
            e2.printStackTrace();
            return arrayList;
        }
    }

    private static String a(ArrayList<DomainModel> arrayList, String str) {
        if (arrayList == null || arrayList.isEmpty()) {
            return str;
        }
        Iterator<DomainModel> it = arrayList.iterator();
        while (it.hasNext()) {
            DomainModel next = it.next();
            if (next.local.equals(LocaleHelper.b)) {
                if (System.currentTimeMillis() < next.launchTime) {
                    return str;
                }
                if (!TextUtils.isEmpty(next.replaceMatchedDomain(str))) {
                    return next.replaceMatchedDomain(str);
                }
            }
        }
        return str;
    }

    public static void c() {
        if (ShopApp.j()) {
            u = e + J;
            v = "http://m-buy.test.mi.com/" + J;
            z = l + J;
            y = "http://m-buy.test.mi.com/" + J;
            w = "http://m-buy.test.mi.com/" + J;
            C = r + J;
            x = h + J;
            return;
        }
        u = f7052a + J;
        v = b + J;
        if (ShopApp.l()) {
            z = n + J;
        } else {
            z = k + J;
        }
        y = d + J;
        w = c + J;
        ck = cj;
        C = q + J;
        x = "http://hd.c.mi.com/" + J;
        if (LocaleHelper.o()) {
            cv = "https://www.miui.com/res/doc/eula/en.html";
        }
    }

    private static void bK() {
        RemoteDataUploadManager.b = com.mi.mistatistic.sdk.Constants.f7315a + "micra/crash";
        RemoteDataUploadManager.d = com.mi.mistatistic.sdk.Constants.f7315a + "app/stat";
        com.mi.mistatistic.sdk.controller.Utils.f7351a = com.mi.mistatistic.sdk.Constants.f7315a + "micra/crash";
        CorrectingServerTimeJob.b = com.mi.mistatistic.sdk.Constants.f7315a + "micra/state/server";
    }

    public static void d() {
        if (!ShopApp.j()) {
            c();
            z = k + J;
        }
        if (ShopApp.n()) {
            A = z;
            B = z;
        }
    }

    public static String e() {
        if (ShopApp.j()) {
            return e;
        }
        return f7052a;
    }

    private static void bL() {
        A = v;
        B = y;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (SyncModel.inHardAccelerUrls != null) {
            for (String contains : SyncModel.inHardAccelerUrls) {
                if (str.contains(contains)) {
                    return true;
                }
            }
        }
        try {
            if ("1".equals(Uri.parse(str).getQueryParameter("usehardware"))) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (SyncModel.inBrowserUrls != null) {
            for (String contains : SyncModel.inBrowserUrls) {
                if (str.contains(contains)) {
                    return true;
                }
            }
        }
        if (SyncModel.inAppUrls != null) {
            for (String contains2 : SyncModel.inAppUrls) {
                if (str.contains(contains2)) {
                    return false;
                }
            }
        }
        if ((str.contains("facebook.com") && !str.contains("oauth")) || str.contains("twitter.com") || str.contains("youtube.com")) {
            return true;
        }
        try {
            if ("1".equals(Uri.parse(str).getQueryParameter("outbrowser"))) {
                return true;
            }
            return false;
        } catch (Exception unused) {
        }
    }

    public static boolean c(String str) {
        return str != null && str.contains(E);
    }

    public static String f() {
        return A + aC;
    }

    public static String d(String str) {
        return v + W + str;
    }

    public static String g() {
        return A + aD;
    }

    public static String h() {
        return A + aE;
    }

    public static String i() {
        return A + aF;
    }

    public static String e(String str) {
        if (dc == null) {
            dc = new Random();
        }
        return A + aH + str + "&v=" + dc.nextDouble();
    }

    public static String j() {
        return v + aG;
    }

    public static String k() {
        if (dc == null) {
            dc = new Random();
        }
        if (ShopApp.n()) {
            return z + aJ + "&v=" + dc.nextDouble();
        }
        return v + aI + "&v=" + dc.nextDouble();
    }

    public static String l() {
        return A + aA;
    }

    public static String m() {
        return v + aw;
    }

    public static String[] n() {
        return f(aK);
    }

    public static String[] o() {
        return f(aL);
    }

    public static String[] p() {
        return f(ax);
    }

    public static String[] q() {
        return f(az);
    }

    public static String[] a(int i2) {
        if (i2 == 0) {
            return f(X);
        }
        if (i2 == 1) {
            return f(Y);
        }
        if (i2 == 2) {
            return f(Z);
        }
        if (i2 == 3) {
            return f(aa);
        }
        if (i2 == 4) {
            return f(ab);
        }
        if (i2 == 5) {
            return f(ad);
        }
        return f(Y);
    }

    public static String b(int i2) {
        String str = z;
        if (i2 == 0) {
            return str + X + "&ot=5";
        } else if (i2 == 1) {
            return str + Y + "&ot=5";
        } else if (i2 == 2) {
            return str + Z + "&ot=5";
        } else if (i2 == 3) {
            return str + aa + "&ot=5";
        } else if (i2 == 4) {
            return str + ab + "&ot=5";
        } else if (i2 == 5) {
            return str + ad + "&ot=5";
        } else {
            return str + X + "&ot=5";
        }
    }

    public static String r() {
        return z + bX;
    }

    public static String s() {
        return z + aL + "?ot=5";
    }

    public static String t() {
        return y + Y;
    }

    public static String u() {
        return y + ae;
    }

    public static String v() {
        return x + cV;
    }

    public static String w() {
        return x + cW;
    }

    public static String x() {
        return y + Z;
    }

    public static String y() {
        return ShopApp.j() ? bS : bR;
    }

    public static String z() {
        return y + aj;
    }

    public static String A() {
        return y + ak;
    }

    public static String B() {
        return y + ab;
    }

    public static String C() {
        return y + ad;
    }

    public static String D() {
        return y + aa;
    }

    public static String[] E() {
        return f(aB);
    }

    public static String F() {
        return z + aB;
    }

    public static String[] f(String str) {
        if (ShopApp.n()) {
            if (ShopApp.j()) {
                return new String[]{l + J + str};
            }
            String[] strArr = new String[2];
            StringBuilder sb = new StringBuilder();
            sb.append(ShopApp.l() ? n : SyncModel.useHttps ? m : k);
            sb.append(J);
            sb.append(str);
            strArr[0] = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(ShopApp.l() ? n : !SyncModel.useHttps ? m : k);
            sb2.append(J);
            sb2.append(str);
            strArr[1] = sb2.toString();
            return strArr;
        } else if (ShopApp.j()) {
            return new String[]{"http://m-buy.test.mi.com/" + J + str};
        } else {
            String[] strArr2 = new String[2];
            StringBuilder sb3 = new StringBuilder();
            sb3.append(SyncModel.useHttps ? d : b);
            sb3.append(J);
            sb3.append(str);
            strArr2[0] = sb3.toString();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(!SyncModel.useHttps ? d : b);
            sb4.append(J);
            sb4.append(str);
            strArr2[1] = sb4.toString();
            return strArr2;
        }
    }

    public static String[] a(String[] strArr) {
        return a(strArr, (HashMap<String, String>) null);
    }

    public static String[] a(String[] strArr, HashMap<String, String> hashMap) {
        String[] strArr2 = new String[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            Uri.Builder buildUpon = Uri.parse(strArr[i2]).buildUpon();
            buildUpon.appendQueryParameter("jsontag", "true");
            if (ShopApp.n()) {
                buildUpon.appendQueryParameter("ot", "5");
            }
            if (hashMap != null) {
                for (Map.Entry next : hashMap.entrySet()) {
                    buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
                }
            }
            if (Setting.a()) {
                buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
            }
            strArr2[i2] = buildUpon.toString();
        }
        return strArr2;
    }

    public static String[] G() {
        return f(af);
    }

    public static String[] H() {
        return f("/user/orderview");
    }

    public static String[] I() {
        return f(P);
    }

    public static String[] J() {
        return f(Q);
    }

    public static String K() {
        return B + Q;
    }

    public static String a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("jsontag", "true");
        buildUpon.appendQueryParameter("security", "true");
        if (!TextUtils.isEmpty(str2)) {
            buildUpon.appendQueryParameter("id", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            buildUpon.appendQueryParameter("item_id", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            buildUpon.appendQueryParameter("parent_itemId", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            buildUpon.appendQueryParameter("count", str5);
        }
        if (ShopApp.n()) {
            buildUpon.appendQueryParameter("ot", "5");
        }
        if (!TextUtils.isEmpty(str6)) {
            buildUpon.appendQueryParameter("source", str6);
        }
        if (!TextUtils.isEmpty(str7)) {
            buildUpon.appendQueryParameter("token", str7);
        }
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        return buildUpon.toString();
    }

    public static String a(String str, String str2, String str3, String str4, String str5) {
        return a(str, str2, str3, str4, str5, "", "");
    }

    public static String a(String str, String str2) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("jsontag", "true");
        buildUpon.appendQueryParameter("security", "true");
        if (!TextUtils.isEmpty(str2)) {
            buildUpon.appendQueryParameter("item_ids", str2);
        }
        if (ShopApp.n()) {
            buildUpon.appendQueryParameter("ot", "5");
        }
        if (Setting.a()) {
            buildUpon.appendQueryParameter("_network_type", NetworkUtil.a());
        }
        return buildUpon.toString();
    }

    public static String[] L() {
        return f(R);
    }

    public static String M() {
        return B + R;
    }

    public static String N() {
        return B + S;
    }

    public static String[] O() {
        return f(T);
    }

    public static String P() {
        return B + T;
    }

    public static String Q() {
        return B + af + "?ot=5";
    }

    public static String R() {
        return v + ar;
    }

    public static String[] S() {
        return f(am);
    }

    public static String T() {
        return B + am;
    }

    public static String[] U() {
        return f(an);
    }

    public static String V() {
        return B + an;
    }

    public static String W() {
        return B + ao;
    }

    public static String[] X() {
        return f(al);
    }

    public static String Y() {
        return B + al;
    }

    public static String[] Z() {
        return f("/user/coupon");
    }

    public static String[] aa() {
        return f(aT);
    }

    public static String ab() {
        return y + aT;
    }

    public static String ac() {
        return z + "/user/orderview" + "?ot=5";
    }

    public static String ad() {
        return B + "/user/coupon";
    }

    public static String ae() {
        return v + ap;
    }

    public static String af() {
        return u + as;
    }

    public static String ag() {
        return u + at;
    }

    public static String ah() {
        return v + au;
    }

    public static String ai() {
        return B + ai;
    }

    public static String aj() {
        return y + ag;
    }

    public static String al() {
        if (LocaleHelper.g()) {
            return bc;
        }
        if (LocaleHelper.h()) {
            return be;
        }
        if (LocaleHelper.i()) {
            return bg;
        }
        if (LocaleHelper.j()) {
            return bm;
        }
        if (LocaleHelper.k()) {
            return bi;
        }
        if (LocaleHelper.l()) {
            return bk;
        }
        if (LocaleHelper.m()) {
            return bo;
        }
        if (LocaleHelper.n()) {
            return bs;
        }
        if (LocaleHelper.o()) {
            return bq;
        }
        return null;
    }

    public static String am() {
        if (LocaleHelper.g()) {
            return bd;
        }
        if (LocaleHelper.h()) {
            return bf;
        }
        if (LocaleHelper.i()) {
            return bh;
        }
        if (LocaleHelper.j()) {
            return bn;
        }
        if (LocaleHelper.k()) {
            return bj;
        }
        if (LocaleHelper.l()) {
            return bl;
        }
        if (LocaleHelper.m()) {
            return bp;
        }
        if (LocaleHelper.n()) {
            return bt;
        }
        if (LocaleHelper.o()) {
            return br;
        }
        return null;
    }

    public static String an() {
        return u + I + "/";
    }

    public static String ao() {
        return u + I + L;
    }

    public static String ap() {
        return u + I + O;
    }

    public static String aq() {
        if (LocaleHelper.g()) {
            return A + aQ;
        }
        return z + aQ;
    }

    public static String ar() {
        if (LocaleHelper.g()) {
            return A + aR;
        }
        return z + aR + "?ot=5";
    }

    public static String as() {
        return A + "/user/up";
    }

    public static String at() {
        return u + I + U;
    }

    public static String au() {
        return v + "/user";
    }

    public static String av() {
        if (ShopApp.j()) {
            return e + J + I + N;
        }
        return f7052a + J + I + N;
    }

    public static String ax() {
        return "/" + J + P;
    }

    public static String ay() {
        if (LocaleHelper.j()) {
            return A + P + "?ot=1";
        }
        return A + P + "?ot=5";
    }

    public static Boolean g(String str) {
        if (TextUtils.isEmpty(str) || !LocaleHelper.g()) {
            return false;
        }
        String ax2 = ax();
        if (str.indexOf(ax2) < 0) {
            return false;
        }
        if (str.contains("?")) {
            str = str.substring(0, str.indexOf(63));
        }
        if (str.toLowerCase().contains(ax2.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static Boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.toLowerCase().contains("oneclick=1")) {
            return true;
        }
        return false;
    }

    public static String az() {
        return v + V;
    }

    public static String aA() {
        return w + aM;
    }

    public static String aB() {
        return w + aN;
    }

    public static String aC() {
        return A + aM;
    }

    public static String aD() {
        if (ShopApp.j()) {
            return cS;
        }
        return cR;
    }

    public static String aE() {
        return z + cU;
    }

    public static String aF() {
        return A + aO;
    }

    public static String aG() {
        return z + aP;
    }

    public static String aH() {
        return A + aU + "?ot=5";
    }

    public static String aI() {
        return A + aY;
    }

    public static String aJ() {
        return A + aV;
    }

    public static String aK() {
        return A + "/user";
    }

    public static String aL() {
        return A + aX;
    }

    public static String aM() {
        return A + cy;
    }

    public static String i(String str) {
        String str2;
        Exception e2;
        String str3 = f7052a;
        if (str.startsWith("http:")) {
            str3 = "http://mobile.mi.com/";
        }
        if (str.startsWith("https:")) {
            str3 = "https://mobile.mi.com/";
        }
        if (ShopApp.j()) {
            str3 = str3.replace(Constants.WebViewURL.GO_STORE_RN_SECOND, Constants.WebViewURL.GO_STORE_RN_SECOND_TEST);
            str = str.replace(Constants.WebViewURL.GO_STORE_RN_SECOND, Constants.WebViewURL.GO_STORE_RN_SECOND_TEST).replace("m.buy.mi.com", "m-buy.test.mi.com");
        }
        String r2 = r(str);
        String r3 = r(str3);
        String str4 = "";
        if (r2.contains("#")) {
            String[] split = r2.split("#");
            if (split.length > 1) {
                str4 = split[1];
            }
            if (split.length > 0) {
                r2 = split[0];
            }
        }
        if (r2.startsWith(r3)) {
            if (!r2.startsWith(r3 + J + "/app/")) {
                String[] split2 = r2.split(r3, 2);
                if (split2.length == 2) {
                    split2 = split2[1].split("/", 2);
                }
                if (split2.length == 1) {
                    return b(r3 + split2[0] + I + "/", str4);
                } else if (split2.length == 2) {
                    return b(r3 + split2[0] + I + "/" + split2[1], str4);
                }
            }
        }
        if (TextUtils.isEmpty(r2) || !r2.matches(bF)) {
            str2 = r2;
        } else {
            LogUtil.b(db, "getAppUrl matches regex :" + r2);
            try {
                str2 = r2.replace(".event.c.mi.com", ".mevent.c.mi.com");
                try {
                    String[] split3 = str2.split("/");
                    if (split3.length > 4 && !"app".equals(split3[4])) {
                        String str5 = split3[0] + "/" + split3[1] + "/" + split3[2] + "/" + split3[3] + "/app/";
                        for (int i2 = 4; i2 < split3.length; i2++) {
                            str5 = str5 + split3[i2] + "/";
                        }
                        return b(str5.substring(0, str5.length() - 1), str4);
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    LogUtil.b(db, "getAppUrl erro url :" + str2 + " exception  = " + e2.toString());
                    return b(str2, str4);
                }
            } catch (Exception e4) {
                Exception exc = e4;
                str2 = r2;
                e2 = exc;
                LogUtil.b(db, "getAppUrl erro url :" + str2 + " exception  = " + e2.toString());
                return b(str2, str4);
            }
        }
        return b(str2, str4);
    }

    public static String j(String str) {
        String str2;
        if (str == null) {
            return null;
        }
        if (!Setting.a()) {
            return str;
        }
        String str3 = "";
        if (str.contains("#")) {
            str3 = str.split("#")[1];
            str = str.split("#")[0];
        }
        String a2 = NetworkUtil.a();
        if (str.contains("?")) {
            str2 = str + "&_network_type=" + a2;
        } else {
            str2 = str + "?_network_type=" + a2;
        }
        return b(str2, str3);
    }

    public static String b(String str, String str2) {
        if (str2.equals("")) {
            return str;
        }
        return str + "#" + str2;
    }

    public static boolean k(String str) {
        if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase(UrlConstants.cart)) {
            return true;
        }
        return false;
    }

    public static boolean l(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String lowerCase = str.toLowerCase();
        if (LocaleHelper.g()) {
            if (lowerCase.equals(UrlConstants.cart) || lowerCase.equals("place order") || lowerCase.equals("pay") || lowerCase.equals("privacy policy") || lowerCase.equals("exchange coupon")) {
                return false;
            }
        } else if (LocaleHelper.h() || LocaleHelper.i()) {
            if (lowerCase.equals("購物車") || lowerCase.equals("成立訂單") || lowerCase.equals("付款")) {
                return false;
            }
        } else if (LocaleHelper.j()) {
            if (lowerCase.equalsIgnoreCase("Keranjang") || lowerCase.equalsIgnoreCase("Pesan sekarang") || lowerCase.equalsIgnoreCase("Bayar")) {
                return false;
            }
        } else if (LocaleHelper.k()) {
            if (lowerCase.equalsIgnoreCase("carrito") || lowerCase.equalsIgnoreCase("envío en camino") || lowerCase.equalsIgnoreCase("pagar") || lowerCase.contains("docdata") || lowerCase.contains("contrato del usuario") || lowerCase.contains(BFLVerifyOTPFragment.c)) {
                return false;
            }
        } else if (LocaleHelper.l()) {
            if (lowerCase.equalsIgnoreCase("mon panier") || lowerCase.equalsIgnoreCase("commander") || lowerCase.equalsIgnoreCase("Paiement") || lowerCase.contains("docdata") || lowerCase.contains("accord utilisateur") || lowerCase.contains(BFLVerifyOTPFragment.c)) {
                return false;
            }
        } else if (LocaleHelper.n()) {
            if (lowerCase.equalsIgnoreCase("Корзина") || lowerCase.equalsIgnoreCase("Сделать заказ") || lowerCase.equalsIgnoreCase("Оплата") || lowerCase.contains("марвел кт") || lowerCase.contains(BFLVerifyOTPFragment.c)) {
                return false;
            }
        } else if (LocaleHelper.m()) {
            if (lowerCase.equalsIgnoreCase("carrello") || lowerCase.equalsIgnoreCase("Effettua l‘ordine") || lowerCase.equalsIgnoreCase("Dati della carta") || lowerCase.contains("Condizion generali di") || lowerCase.contains("Termini")) {
                return false;
            }
        } else if (!LocaleHelper.o() || (!lowerCase.equalsIgnoreCase(UrlConstants.cart) && !lowerCase.equalsIgnoreCase("Place Order") && !lowerCase.equalsIgnoreCase("Card Details") && !lowerCase.contains("user agreement") && !lowerCase.contains(BFLVerifyOTPFragment.c))) {
            return true;
        } else {
            return false;
        }
        return true;
    }

    public static String aN() {
        if (LocaleHelper.j()) {
            return u + I + "/about/privacy";
        }
        return u + I + bx;
    }

    public static String aO() {
        if (LocaleHelper.q()) {
            return u + I + bz;
        }
        return u + I + by;
    }

    public static String[] aP() {
        if (ShopApp.j()) {
            return new String[]{e + "in/hj.html", "http://m-buy.test.mi.com/in/misc/hj"};
        }
        return new String[]{f7052a + "in/hj.html", "http://m.buy.mi.com/in/misc/hj"};
    }

    public static String aQ() {
        return v + aZ;
    }

    public static String aR() {
        if (ShopApp.j()) {
            return bU + LocaleHelper.b + bV;
        }
        return bT + LocaleHelper.b + bV;
    }

    public static String m(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            Log.wtf(db, "UTF-8 should always be supported", e2);
            throw new RuntimeException("URLEncoder.encode() failed for " + str);
        }
    }

    public static String aS() {
        return A + I + bA;
    }

    public static String aT() {
        return A + "/user/up";
    }

    public static String aU() {
        return v + bC;
    }

    public static String aV() {
        return v + bD;
    }

    public static String aW() {
        return v + bE;
    }

    public static String aX() {
        return A + bG;
    }

    public static String aY() {
        return A + "/buy/paygo";
    }

    public static String aZ() {
        return A + "/buy/paygo";
    }

    public static String ba() {
        return D + bK;
    }

    public static String bb() {
        return z + bW;
    }

    public static boolean n(String str) {
        return !TextUtils.isEmpty(str) && str.contains("youtube.com") && str.contains("v=");
    }

    public static String bc() {
        return z + bL + "?ot=5";
    }

    public static String bd() {
        return z + bM + "?ot=5";
    }

    public static String be() {
        return z + bN + "?ot=5";
    }

    public static String bf() {
        return z + bO + "?ot=5";
    }

    public static String bg() {
        return z + bP;
    }

    public static String bh() {
        return z + "/buy/paygo" + "?ot=5";
    }

    public static String bi() {
        return z + "/buy/paygo";
    }

    public static String bj() {
        return ck + cd;
    }

    public static String bk() {
        return ck + ce;
    }

    public static String bl() {
        return u + cf;
    }

    public static String bm() {
        return v + cb;
    }

    public static String bn() {
        return u + cc;
    }

    public static String bo() {
        return z + bY + "?ot=5";
    }

    public static String o(String str) {
        return v + f7053cn + str;
    }

    public static String bp() {
        return z + co;
    }

    public static String bq() {
        return z + cp;
    }

    public static boolean p(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.contains(u + cq)) {
            if (str.contains(v + cr)) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static String br() {
        return v + cs;
    }

    public static String bs() {
        return v + ct;
    }

    public static String bt() {
        return z + cu;
    }

    public static String bu() {
        return z + cz;
    }

    public static String bv() {
        return z + "/recommend";
    }

    public static String q(String str) {
        return z + cB + str;
    }

    public static String b(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        return z + cC + "/" + str + "/" + str2 + "/" + str3 + "/" + str4 + "/" + str5 + "/" + str6 + "/" + str7;
    }

    public static String bw() {
        return v + cD;
    }

    public static String bx() {
        if (ShopApp.j()) {
            return C + cE;
        }
        return s + J + cE;
    }

    public static String by() {
        return C + cF;
    }

    public static String bz() {
        return C + cG;
    }

    public static String bA() {
        return C + cH;
    }

    public static String bB() {
        if (ShopApp.j()) {
            return cJ;
        }
        return t + cI;
    }

    public static String bC() {
        if (ShopApp.j()) {
            return cL;
        }
        return t + cK;
    }

    public static String bD() {
        if (ShopApp.j()) {
            return cN;
        }
        return t + cM;
    }

    public static String bE() {
        return v + cX;
    }

    public static String bF() {
        return u + cQ;
    }

    public static String bG() {
        return z + cY;
    }

    public static String bH() {
        return x + cZ;
    }

    public static String bI() {
        return x + da;
    }
}
