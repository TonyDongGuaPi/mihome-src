package com.xiaomi.youpin.youpin_constants;

import android.text.TextUtils;
import android.util.Pair;
import com.alipay.sdk.sys.a;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlConstants {
    public static final String NDA = "NDA";
    public static final String SHOP_URL = "http://home.mi.com/shop";
    public static final String address = "address";
    public static final String addressdetail = "addressdetail";
    public static final String aftersaleslist = "aftersalelist";
    public static final String afterservice = "afterservice";
    public static final String allCommentList = "allCommentList";
    public static final String appCommentPrompt = "appcommentprompt";
    public static final String appCriticise = "appcriticise";
    public static final String bargains = "bargains";
    public static final String bundleabout = "bundleabout";
    public static final String call = "call";
    public static final String cart = "cart";
    public static final String check = "check";
    public static final String checkoutnew = "checkoutnew";
    public static final String coldplay = "coldplay";
    public static final String coldplaydetail = "coldplaydetail";
    public static final String commentCenter = "commentCenter";
    public static final String convertreport = "convertreport";
    public static final String coupon = "coupon";
    public static final String couponAndPrivilege = "couponAndPrivilege";
    public static final String couponintro = "couponintro";
    public static final String couponselect = "couponselect";
    public static final String crowdfunding = "crowdfunding";
    public static final String crowdfundinglist = "crowdfundinglist";
    public static final String csPushHeader = "csPushHeader";
    public static final String customerService = "customerService";
    public static final String detail = "detail";
    public static final String express = "express";
    public static final String favor = "favor";
    public static final String fcode = "fcode";
    public static final String feedback_list = "feedback_list";
    public static final String flagshipstore = "flagshipstore";
    public static final String goodsbycategory = "goodsbycategory";
    public static final String goodscategory = "goodscategory";
    public static final String home = "home";
    public static final String imagePicker = "imagePicker";
    public static final String insurance = "insurance";
    public static final String joinmoregoods = "joinmoregoods";
    public static final String lotteryaddress = "lotteryaddress";
    public static final String lottory = "lottory";
    public static final String main = "main";
    public static final String main_land_tab = "main_land_tab";
    public static final String mainpreview = "preview";
    public static final String maintab = "maintab";
    public static final String mishopsdk = "mishopsdk=true";
    public static final String mishopsdk_activity_url = "http://m.mi.com/sdk?pid=15102&root=com.xiaomi.shop2.plugin.hdchannel.RootFragment&cid=20118.00007&extra_cart=true&show_title=true";
    public static final String mishopsdk_fragment_url = "http://m.mi.com/sdk?pid=15102&root=com.xiaomi.shop2.plugin.hdchannel.RootFragment&cid=20118.00007&show_title=false&embedded=true";
    public static final String morelist = "morelist";
    public static final String msglist = "msgcenter";
    public static final String newpayment = "newpayment";
    public static final String orderdetail = "orderdetail";
    public static final String orderlist = "orderlist";
    public static final String pay = "pay";
    public static final String payment = "payment";
    public static final String payorders = "payorders";
    public static final String picture_pick = "picture_pick";
    public static final String pinwei = "pinwei";
    public static final String privilege = "privilege";
    public static final String profile = "profile";
    public static final String red_envelope_rain = "red_envelope_rain";
    public static final String red_envelope_rain_checkout = "red_envelope_rain_checkout";
    public static final String red_envelope_rain_gaming = "red_envelope_rain_gaming";
    public static final String red_envelope_rain_web_gaming = "red_envelope_rain_web_gaming";
    public static final String salereport = "salereport";
    public static final String scan = "qrcodescan";
    public static final String scanbar = "scanbar";
    public static final String score = "score";
    public static final String scorehistory = "scorehistory";
    public static final String search = "searchfilter";
    public static final String searchBar = "searchBar";
    public static final String second_floor = "second_floor";
    public static final String selectsku = "selectsku";
    public static final String serviceCenter = "serviceCenter";
    public static final String share = "share";
    public static final String shop3d = "shop3d";
    public static final String shopshortcut = "shopshortcut";
    public static final String statreport = "statreport";
    public static final String tab_brand = "tab_brand";
    public static final String tab_recommend = "tab_recommend";
    public static final String topic = "topic";
    public static final String virtual3d = "virtual3d";
    public static final String web = "web";
    public static final String writeComment = "writeComment";
    public static final String yp_imagebrowser = "yp-imagebrowser";

    public static String generateUrl(String str) {
        if (str.startsWith("http")) {
            return str;
        }
        if (!str.startsWith("/")) {
            return "http://home.mi.com/shop/" + str;
        }
        return SHOP_URL + str;
    }

    public static String generateUrlParams(String str, String[] strArr, Object[] objArr) {
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < strArr.length && i < objArr.length) {
            hashMap.put(strArr[i], objArr[i]);
            i++;
        }
        return generateUrlParams(str, hashMap);
    }

    public static String generateUrlParams(String str, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(generateUrl(str));
        sb.append("?");
        if (map != null) {
            Set<String> keySet = map.keySet();
            if (keySet.size() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                boolean z = true;
                for (String next : keySet) {
                    if (map.get(next) != null) {
                        if (!z) {
                            stringBuffer.append(a.b);
                        }
                        z = false;
                        stringBuffer.append(next);
                        stringBuffer.append("=");
                        stringBuffer.append(URLEncoder.encode(map.get(next).toString()));
                    }
                }
                sb.append(stringBuffer.toString());
            }
        }
        return sb.toString();
    }

    public static Pair<String, HashMap<String, String>> parseUrlAndParams(String str) {
        Matcher matcher = Pattern.compile("(\\w+)=([\\-_.%\\w+]+)").matcher(str);
        HashMap hashMap = new HashMap();
        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                hashMap.put(matcher.group(1), URLDecoder.decode(matcher.group(2)));
            }
        }
        return new Pair<>(parseShortPath(str), hashMap);
    }

    public static String parseShortPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1, str.length()) : str;
    }
}
