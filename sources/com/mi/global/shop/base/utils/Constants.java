package com.mi.global.shop.base.utils;

import android.os.Environment;
import com.mi.account.util.Constants;

public class Constants {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5651a = 86400000;
    public static final int b = -1;
    public static final String c = "com.mi.global.shop.web";

    public static final class AddShoppingCartStatus {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5652a = "add_success";
        public static final String b = "add_fail";
        public static final String c = "add_fail_already_max";
    }

    public static final class Address {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5653a = "address_manage";
        public static final String b = "address_choose";
        public static final String c = "address_list";
        public static final String d = "region_list";
        public static final String e = "address_id";
        public static final String f = "name";
        public static final String g = "tel";
        public static final String h = "address";
        public static final String i = "city";
        public static final String j = "city_id";
        public static final String k = "landmark";
        public static final String l = "state";
        public static final String m = "zipcode";
        public static final String n = "is_invalid";
        public static final String o = "can_cod";
        public static final String p = "limit";
        public static final String q = "limit_cod";
        public static final String r = "dealer";
        public static final String s = "address_jsonarray";
        public static final String t = "pref_address";
        public static final String u = "gstin_prefix";
    }

    public static final class App {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5654a = "OTA";
        public static final String b = "MSITE";
        public static final String c = "GOOGLEPLAY";
        public static final String d = "pref_channel";
        public static final String e = "pref_version";
        public static final String f = "pref_locale";
        public static final String g = "pref_lang";
        public static final String h = "jsontag";
        public static final String i = "ISAPP";
    }

    public static class AppLink {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5655a = "pref_applink_haslink";
        public static final String b = "pref_applink_url";
    }

    public static final class AppUpdate {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5656a = "file://";
        public static final String b = ".apk";
        public static final String c = "application/vnd.android.package-archive";
        public static final String d = "pref_last_update_is_ok";
        public static final String e = "pref_last_check_update";
        public static final int f = 3600000;
        public static final int g = 3600000;
        public static final int h = 30000;
        public static final String i = "pref_download_id";
        public static final String j = "wifiForce";
        public static final String k = "force";
    }

    public static final class CancelOrder {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5657a = "cancel_reason";
    }

    public static final class Cookie {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5658a = "xiaomi.com";
        public static final String b = "m.buy.mi.com";
        public static final String c = "sg.support.kefu.mi.com";
        public static final String d = "pref_flash_sale_cookie";
        public static final String e = "pref_flash_sale_cookie_expries_";
    }

    public static final class Coupon {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5659a = "coupon_list";
        public static final String b = "coupon_manage";
        public static final String c = "coupon_choose";
        public static final String d = "coupon_id";
        public static final String e = "exchange_coupon_id";
        public static final String f = "card_coupon_id";
        public static final String g = "name";
        public static final String h = "couponDiscountMoney";
        public static final String i = "cardDiscountMoney";
        public static final String j = "shipment";
        public static final String k = "amount";
        public static final String l = "type";
    }

    public static final class ExternalStorage {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5660a = (Environment.getExternalStorageDirectory() + "/mishop");
        public static final String b = (f5660a + "/save/");
    }

    public static final class Intent {
        public static final String A = "com.mi.global.shop.extra_payment_security_key";
        public static final String B = "com.mi.global.shop.extra_payment_order_type";
        public static final String C = "com.mi.global.shop.extra_payment_service_order";
        public static final String D = "com.mi.global.shop.extra_payment_fee";
        public static final String E = "com.mi.global.shop.extra_online_payment_type";
        public static final String F = "com.mi.global.shop.extra_payment_user_confirm_result";
        public static final String G = "com.mi.global.shop.extra_address_consignee";
        public static final String H = "com.mi.global.shop.extra_address_province";
        public static final String I = "com.mi.global.shop.extra_address_city";
        public static final String J = "com.mi.global.shop.extra_address_district";
        public static final String K = "com.mi.global.shop.extra_address_area";
        public static final String L = "com.mi.global.shop.extra_address_location";
        public static final String M = "com.mi.global.shop.extra_address_zipcode";
        public static final String N = "com.mi.global.shop.extra_address_tel";
        public static final String O = "com.mi.global.shop.extra_del_address_result";
        public static final String P = "com.mi.global.shop.extra_add_address_new_id";
        public static final String Q = "com.mi.global.shop.extra_del_address_result_msg";
        public static final String R = "com.mi.global.shop.extra_address_result_code";
        public static final String S = "com.mi.global.shop.extra_address_tv_allowadd";
        public static final String T = "com.mi.global.shop.extra_is_tv_address";
        public static final String U = "com.mi.global.shop.extra_address_match";
        public static final String V = "com.mi.global.shop.extra_cart_has_tv";
        public static final String W = "com.mi.global.shop.extra_address_is_event";
        public static final String X = "com.mi.global.shop.extra_order_list_type";
        public static final String Y = "com.mi.global.shop.extra_order_list_from";
        public static final String Z = "com.mi.global.shop.extra_order_view_type";

        /* renamed from: a  reason: collision with root package name */
        public static final String f5661a = "com.mi.global.shop.extra_categoryid";
        public static final String aA = "com.mi.global.shop.extra_checkcode_id";
        public static final String aB = "com.mi.global.shop.extra_checkcode_list";
        public static final String aC = "com.mi.global.shop.extra_coupon_id";
        public static final String aD = "com.mi.global.shop.extra_coupon_type";
        public static final String aE = "com.mi.global.shop.extra_combo_type";
        public static final String aF = "com.mi.global.shop.extra_product_info";
        public static final String aG = "com.mi.global.shop.extra_product_name";
        public static final String aH = "com.mi.global.shop.extra_security_code";
        public static final String aI = "com.mi.global.shop.extra_remain_number";
        public static final String aJ = "com.mi.global.shop.extra_price";
        public static final String aK = "com.mi.global.shop.extra_market_price";
        public static final String aL = "com.mi.global.shop.extra_image_url";
        public static final String aM = "com.mi.global.shop.extra_shake_time";
        public static final String aN = "com.mi.global.shop.extra_map_url";
        public static final String aO = "com.mi.global.shop.extra_mihome_isgpscity";
        public static final String aP = "com.mi.global.shop.extra_mihome_isfrommap";
        public static final String aQ = "com.mi.global.shop.extra_mihome_id";
        public static final String aR = "com.mi.global.shop.extra_mihome_buy";
        public static final String aS = "com.mi.global.shop.extra_mihome_name";
        public static final String aT = "com.mi.global.shop.extra_mihome_date_day";
        public static final String aU = "com.mi.global.shop.extra_mistation_isfrommap";
        public static final String aV = "com.mi.global.shop.extra_mihome_date_hours";
        public static final String aW = "com.mi.global.shop.extra_mistation_id";
        public static final String aX = "com.mi.global.shop.extra_station_province_name_en";
        public static final String aY = "com.mi.global.shop.extra_station_province_name_cn";
        public static final String aZ = "com.mi.global.shop.extra_order_id";
        public static final String aa = "com.mi.global.shop.extra_order_list_type_is_show_non_payment";
        public static final String ab = "com.mi.global.shop.extra_order_list_type_express";
        public static final String ac = "com.mi.global.shop.extra_del_add_shopping_cart_result_msg";
        public static final String ad = "com.mi.global.shop.extra_activity_url";
        public static final String ae = "com.mi.global.shop.extra_activity_version";
        public static final String af = "com.mi.global.shop.extra_activity_type";
        public static final String ag = "com.mi.global.shop.extra_update_url";
        public static final String ah = "com.mi.global.shop.extra_update_version";
        public static final String ai = "com.mi.global.shop.extra_update_type";
        public static final String aj = "com.mi.global.shop.result";
        public static final String ak = "com.mi.global.shop.extra_go_to_fragment";
        public static final String al = "com.mi.global.shop.extra_is_closed";
        public static final String am = "com.mi.global.shop.extra_is_closed_url";
        public static final String an = "com.mi.global.shop.extra_shop_intent_service_action";
        public static final String ao = "com.mi.global.shop.extra_submit_result";
        public static final String ap = "com.mi.global.shop.extra_submit_json";
        public static final String aq = "com.mi.global.shop.extra_order_submit_order_id";
        public static final String ar = "com.mi.global.shop.extra_full_screen_start_index";
        public static final String as = "com.mi.global.shop.extra_full_screen_is_gallery";
        public static final String at = "com.mi.global.shop.extra_order_express";
        public static final String au = "com.mi.global.shop.extra_checkcode_url";
        public static final String av = "com.mi.global.shop.extra_checkcode_vcode";
        public static final String aw = "com.mi.global.shop.extra_checkcode_result";
        public static final String ax = "com.mi.global.shop.extra_checkcode_message";
        public static final String ay = "com.mi.global.shop.extra_checkcode_fcode";
        public static final String az = "com.mi.global.shop.extra_checkcode_authcode";
        public static final String b = "com.mi.global.shop.extra_category_name";
        public static final String bA = "com.mi.global.shop.extra_review_goods_id";
        public static final String bB = "com.mi.global.shop.extra_review_pms_product_id";
        public static final String bC = "com.mi.global.shop.extra_review_quality_rate";
        public static final String bD = "com.mi.global.shop.extra_review_service_rate";
        public static final String bE = "com.mi.global.shop.extra_review_deliver_rate";
        public static final String bF = "com.mi.global.shop.extra_review_content";
        public static final String bG = "com.mi.global.shop.extra_mihome_sign_count";
        public static final String bH = "com.mi.global.shop.extra_jsondata_from_checkout";
        public static final String bI = "com.mi.global.shop.extra_invoice_type_tag_result";
        public static final String bJ = "com.mi.global.shop.extra_invoice_type_text_result";
        public static final String bK = "com.mi.global.shop.extra_invoice_desc_tag_result";
        public static final String bL = "com.mi.global.shop.extra_invoice_desc_text_result";
        public static final String bM = "com.mi.global.shop.extra_pickup_id_result";
        public static final String bN = "com.mi.global.shop.extra_payment_id_result";
        public static final String bO = "com.mi.global.shop.extra_payment_desc_result";
        public static final String bP = "com.mi.global.shop.extra_shipment_id_result";
        public static final String bQ = "com.mi.global.shop.extra_shipment_desc_result";
        public static final String bR = "com.mi.global.shop.extra_shipment_amount_result";
        public static final String bS = "com.mi.global.shop.extra_deliver_id_result";
        public static final String bT = "com.mi.global.shop.extra_express_id_result";
        public static final String bU = "com.mi.global.shop.extra_deliver_id";
        public static final String bV = "com.mi.global.shop.extra_deliver_products";
        public static final String bW = "com.mi.global.shop.extra_deliver_products_num";
        public static final String bX = "com.mi.global.shop.extra_deliver_desc_result";
        public static final String bY = "com.mi.global.shop.extra_checkout_region_id";
        public static final String bZ = "com.mi.global.shop.extra_kuwan_bbs_tid";
        public static final String ba = "com.mi.global.shop.extra_miphone_sales_record_imei";
        public static final String bb = "com.mi.global.shop.extra_miphone_sales_record_uniq";
        public static final String bc = "com.mi.global.shop.extra_repair_progress_tel";
        public static final String bd = "com.mi.global.shop.extra_repair_progress_imei";
        public static final String be = "com.mi.global.shop.extra_order_deliver_time";
        public static final String bf = "com.mi.global.shop.extra_order_has_phone";
        public static final String bg = "com.mi.global.shop.extra_order_edit_type";
        public static final String bh = "com.mi.global.shop.extra_order_edit_oldtel";
        public static final String bi = "com.mi.global.shop.extra_mihome_reserve_id";
        public static final String bj = "com.mi.global.shop.extra_mihome_reserve_use_name";
        public static final String bk = "com.mi.global.shop.extra_mihome_reserve_use_tel";
        public static final String bl = "com.mi.global.shop.extra_mihome_reserve_use_sex";
        public static final String bm = "com.mi.global.shop.extra_mihome_reserve_accept_type";
        public static final String bn = "com.mi.global.shop.extra_mihome_reserve_accept_desc";
        public static final String bo = "com.mi.global.shop.incast_products";
        public static final String bp = "com.mi.global.shop.extra_campaign_show_title";
        public static final String bq = "com.mi.global.shop.extra_campaign_show_bottom";
        public static final String br = "com.mi.global.shop.extra_campaign_finish_anim";
        public static final String bs = "com.mi.global.shop.extra_campaign_from_push";
        public static final String bt = "com.mi.global.shop.extra_mihome_error_result";
        public static final String bu = "com.mi.global.shop.extra_recharge_recharge_name";
        public static final String bv = "com.mi.global.shop.extra_recharge_pervalue";
        public static final String bw = "com.mi.global.shop.extra_michat_visibility";
        public static final String bx = "com.mi.global.shop.extra_search_result_keyword";
        public static final String by = "com.mi.global.shop.extra_review_goods_name";
        public static final String bz = "com.mi.global.shop.extra_review_goods_obj";
        public static final String c = "com.mi.global.shop.extra_cartlist_item_id";
        public static final String cA = "orderview_orderid";
        public static final String cB = "expresssn";
        public static final String cC = "delivered";
        public static final String cD = "order_status";
        public static final String cE = "order_haspay";
        public static final String cF = "order_hastrace";
        public static final String cG = "order_hascancel";
        public static final String cH = "order_hasrefund";
        public static final String cI = "order_hasnot_delivered";
        public static final String cJ = "order_has_delivered";
        public static final String cK = "order_has_delivered_time";
        public static final String cL = "cart_bargain";
        public static final String cM = "cart_bargain_item_id";
        public static final String cN = "cart_insurance_goodsid";
        public static final String cO = "cart_insurance_parentid";
        public static final String cP = "cart_insurance_url";
        public static final String cQ = "cart_webview";
        public static final String cR = "com.mi.global.shop.action_update_user_info";
        public static final String cS = "com.mi.global.shop.action_delete_cartitem";
        public static final String cT = "com.mi.global.shop.action_update_shopping_count";
        public static final String cU = "com.mi.global.shop.action_update_mihome_shopping_count";
        public static final String cV = "com.mi.global.shop.action_checkout_submit";
        public static final String cW = "com.mi.global.shop.action_update_product_detail";
        public static final String cX = "com.mi.global.shop.del_address";
        public static final String cY = "com.mi.global.shop.use_address";
        public static final String cZ = "com.mi.global.shop.edit_address";
        public static final String ca = "com.mi.global.shop.extra_kuwan_title";
        public static final String cb = "com.mi.global.shop.extra_kuwan_content";
        public static final String cc = "com.mi.global.shop.extra_kuwan_url";
        public static final String cd = "com.mi.global.shop.extra_kuwan_top_image";
        public static final String ce = "com.mi.global.shop.extra_nearby_user_id";
        public static final String cf = "com.mi.global.shop.extra_nearby_user_name";
        public static final String cg = "com.mi.global.shop.extra_nearby_user_icon";
        public static final String ch = "com.mi.global.shop.extra_nearby_user_forumName";
        public static final String ci = "com.mi.global.shop.extra_nearby_user_sex";
        public static final String cj = "com.mi.global.shop.extra_nearby_user_age";
        public static final String ck = "com.mi.global.shop.extra_nearby_user_tagBbs";
        public static final String cl = "com.mi.global.shop.extra_nearby_user_tagMiui";
        public static final String cm = "com.mi.global.shop.extra_nearby_user_tagBbsName";

        /* renamed from: cn  reason: collision with root package name */
        public static final String f5662cn = "com.mi.global.shop.extra_nearby_user_tagMiuiName";
        public static final String co = "com.mi.global.shop.extra_paid_service_order_id";
        public static final String cp = "com.mi.global.shop.extra_paid_service_device_img_url";
        public static final String cq = "com.mi.global.shop.extra_paid_service_device_type";
        public static final String cr = "com.mi.global.shop.extra_paid_service_device_imei";
        public static final String cs = "com.mi.global.shop.extra_paid_service_status";
        public static final String ct = "com.mi.global.shop.extra_paid_service_validate_date";
        public static final String cu = "com.mi.global.shop.extra_buy_confirm_orderid";
        public static final String cv = "com.mi.global.shop.extra_user_address_type";
        public static final String cw = "com.mi.global.shop.extra_user_address_list_from";
        public static final String cx = "com.mi.global.shop.extra_user_address_edit_from";
        public static final String cy = "com.mi.global.shop.extra_user_coupon_type";
        public static final String cz = "orderid";
        public static final String d = "com.mi.global.shop.extra_category_data_type";
        public static final String dA = "com.mi.global.shop.action_show_m_site";
        public static final String dB = "com.mi.global.shop.action_show_open_purchase";
        public static final String dC = "com.mi.global.shop.action_show_product_details";
        public static final String dD = "com.mi.global.shop.action_show_combo";
        public static final String dE = "com.mi.global.shop.action_fetch_vcode";
        public static final String dF = "com.mi.global.shop.action_verify_vcode";
        public static final String dG = "com.mi.global.shop.action_verify_fcode";
        public static final String dH = "com.mi.global.shop.action_use_coupon";
        public static final String dI = "com.mi.global.shop.action_adapt_phone_list";
        public static final String dJ = "com.mi.global.shop.action_shake";
        public static final String dK = "com.mi.global.shop.action_fetch_defense_hacker_vcode";
        public static final String dL = "com.mi.global.shop.action_mihome_list";
        public static final String dM = "com.mi.global.shop.action_mihome_detail";
        public static final String dN = "com.mi.global.shop.action_mihome_reserve";
        public static final String dO = "com.mi.global.shop.action_mihome_comment_list";
        public static final String dP = "com.mi.global.shop.action_mihome_scanner";
        public static final String dQ = "com.mi.global.shop.action_product_scanner";
        public static final String dR = "com.mi.global.shop.action_mihome_check";
        public static final String dS = "com.mi.global.shop.action_mihome_product_detail";
        public static final String dT = "com.mi.global.shop.action_direct";
        public static final String dU = "com.mi.global.shop.action_mihome_signin";
        public static final String dV = "com.mi.global.shop.action_nearby_details";
        public static final String dW = "com.mi.global.shop.action_nearby_my_details";
        public static final String dX = "com.mi.global.shop.action_nearby_radar";
        public static final String dY = "com.mi.global.shop.action_nearby_list";
        public static final String dZ = "com.mi.global.shop.action_mistation_detail";
        public static final String da = "com.mi.global.shop.add_address";
        public static final String db = "com.mi.global.shop.add_tv_address";
        public static final String dc = "com.mi.global.shop.action_order_submit";
        public static final String dd = "com.mi.global.shop.action_edit_consumption";

        /* renamed from: de  reason: collision with root package name */
        public static final String f5663de = "com.mi.global.shop.my_order_list";
        public static final String df = "com.mi.global.shop.my_express_list";
        public static final String dg = "com.mi.global.shop.action_add_shopping_cart";
        public static final String dh = "com.mi.global.shop.action_muti_add_shopping_cart";
        public static final String di = "com.mi.global.shop.my_repair_list";
        public static final String dj = "com.mi.global.shop.view_order";

        /* renamed from: dk  reason: collision with root package name */
        public static final String f5664dk = "com.mi.global.shop.recharge_list";
        public static final String dl = "com.mi.global.shop.view.recharge";
        public static final String dm = "com.mi.global.shop.view.deliverorder";
        public static final String dn = "com.mi.global.shop.view_after_sale_order";

        /* renamed from: do  reason: not valid java name */
        public static final String f29do = "com.mi.global.shop.action_check_activity";
        public static final String dp = "com.mi.global.shop.action_check_update";
        public static final String dq = "com.mi.global.shop.action_download_completed";
        public static final String dr = "com.mi.global.shop.action_cancel_order";
        public static final String ds = "com.mi.global.shop.action_cancel_recharge";
        public static final String dt = "com.mi.global.shop.action_show_activity";
        public static final String du = "com.mi.global.shop.action_review_submit";
        public static final String dv = "com.mi.global.shop.action_paid_service_list";
        public static final String dw = "com.mi.global.shop.action_show_home";
        public static final String dx = "com.mi.global.shop.action_show_usercenter";
        public static final String dy = "com.mi.global.shop.action_show_shipping_order_list";
        public static final String dz = "com.mi.global.shop.action_show_mihome";
        public static final String e = "com.mi.global.shop.extra_category_adapt_code";
        public static final String ea = "com.mi.global.shop.action_mistation_comment_list";
        public static final String eb = "com.mi.global.shop.action_switch_main";
        public static final String ec = "switch_home_page";
        public static final String f = "com.mi.global.shop.extra_product_id";
        public static final String g = "com.mi.global.shop.extra_product_type";
        public static final String h = "com.mi.global.shop.extra_productview_postion";
        public static final String i = "com.mi.global.shop.extra_commodity_id";
        public static final String j = "com.mi.global.shop.extra_key_list";
        public static final String k = "com.mi.global.shop.extra_id_list";
        public static final String l = "com.mi.global.shop.extra_compaign_url";
        public static final String m = "com.mi.global.shop.extra_about_url";
        public static final String n = "com.mi.global.shop.extra_is_miphone";
        public static final String o = "com.mi.global.shop.extra_miphone_name";
        public static final String p = "com.mi.global.shop.extra_shopping_count";
        public static final String q = "com.mi.global.shop.extra_mihome_shopping_count";
        public static final String r = "com.mi.global.shop.extra_address_id";
        public static final String s = "com.mi.global.shop.extra_address_id_from_checkout";
        public static final String t = "com.mi.global.shop.extra_del_address_result";
        public static final String u = "com.mi.global.shop.extra_del_address_result_msg";
        public static final String v = "com.xiaomi.ship.extra_payment_url";
        public static final String w = "com.mi.global.shop.extra_payment_order_id";
        public static final String x = "com.mi.global.shop.extra_payment_mode_key";
        public static final String y = "com.mi.global.shop.extra_payment_need_encrypt_key";
        public static final String z = "com.mi.global.shop.extra_edit_order_message_check";
    }

    public static final class MobileWebUri {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5665a = "?";
        public static final String b = "#";
        public static final String c = "ac";
        public static final String d = "product";
        public static final String e = "op";
        public static final String f = "list";
        public static final String g = "view";
        public static final String h = "cate_id";
        public static final String i = "product_id";
    }

    public static final class OrderConfirmed {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5666a = "payment_type";
        public static final String b = "cod_by_img";
        public static final String c = "cod_by_sms";
        public static final String d = "2";
        public static final String e = "1";
    }

    public static final class OrderExpressType {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5667a = "express_list_type_head";
        public static final String b = "express_list_type_default";
    }

    public static final class OrderList {

        /* renamed from: a  reason: collision with root package name */
        public static final int f5668a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final int d = 3;
        public static final int e = 4;
        public static final int f = 5;
        public static final int g = 6;
        public static final String h = "type";
    }

    public static final class Pay {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5669a = "/in/buy/confirm?id=";
        public static final String b = "/in/user/orderview?order_id=";
    }

    public static final class Prefence extends Constants.Prefence {
        public static final String A = "pref_user_central_header_bg";
        public static final String B = "pref_user_central_title_color";
        public static final String C = "pref_key_push_classify_data";
        public static final String D = "pref_key_push_classify_key";
        public static final String E = "pref_key_use_new_model";
        public static final String F = "pref_key_user_center_list";
        public static final String G = "pref_key_user_gift_card";
        public static final String H = "pref_key_home_entrance_viewid";
        public static final String I = "pref_key_using_go_mifile_host_swtich";
        public static final String J = "pref_key_private_dialog";
        public static final String K = "pref_key_private_again_dialog";
        public static final String L = "pref_key_https_request";
        public static final String M = "pref_key_https_image_request";
        public static final String N = "pref_key_https_webview_url_request";
        public static final String O = "pref_key_search_history_list";
        public static final String P = "pref_key_show_gst_address_dialog";
        public static final String Q = "pref_key_home_new_list";
        public static final String R = "pref_key_has_agreen_conditions_";
        public static final String S = "pref_key_ga_id";
        public static final String T = "pref_key_home_two_floor";
        public static final String U = "pref_key_switch_region";

        /* renamed from: a  reason: collision with root package name */
        public static final String f5670a = "pref_key_in_show_ui";
        public static final String b = "pref_key_custom_cookies";
        public static final String c = "pref_key_shoppingcart_number";
        public static final String d = "pref_key_cache_config";
        public static final String e = "pref_key_activity_config";
        public static final String f = "pref_key_pin_code_switch";
        public static final String g = "pref_key_zip_code";
        public static final String h = "pref_key_city_name";
        public static final String i = "pref_key_state_id";
        public static final String j = "pref_key_warehouse_id";
        public static final String k = "pref_key_home_notice_closed_type";
        public static final String l = "pref_key_home_notice_closed_time";
        public static final String m = "pref_update_user_info_time";
        public static final String n = "pref_isfirstrun";
        public static final String o = "pref_no_chance";
        public static final String p = "pref_activity_url";
        public static final String q = "pref_activity_version";
        public static final String r = "pref_key_enable_push";
        public static final String s = "pref_key_enable_push_time";
        public static final String t = "pref_key_enable_push_time_from";
        public static final String u = "pref_key_enable_push_time_to";
        public static final String v = "pref_key_mihome_info";
        public static final String w = "pref_key_recharge_history";
        public static final String x = "pref_key_search_history";
        public static final String y = "pref_key_default_payment";
        public static final String z = "pref_user_nearby";
    }

    public static final class Push {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5671a = "watermark";
        public static final String b = "type_id";
        public static final String c = "url";
        public static final String d = "title";
        public static final String e = "description";

        public static final class WaterMarkTypes {

            /* renamed from: a  reason: collision with root package name */
            public static final String f5672a = "1";
        }
    }

    public static final class RequestCode {
        public static final int A = 25;
        public static final int B = 26;
        public static final int C = 27;
        public static final int D = 28;
        public static final int E = 29;
        public static final int F = 30;

        /* renamed from: a  reason: collision with root package name */
        public static final int f5673a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
        public static final int g = 7;
        public static final int h = 8;
        public static final int i = 9;
        public static final int j = 10;
        public static final int k = 11;
        public static final int l = 12;
        public static final int m = 13;
        public static final int n = 14;
        public static final int o = 15;
        public static final int p = 16;
        public static final int q = 100;
        public static final int r = 101;
        public static final int s = 17;
        public static final int t = 18;
        public static final int u = 19;
        public static final int v = 20;
        public static final int w = 21;
        public static final int x = 22;
        public static final int y = 23;
        public static final int z = 24;
    }

    public static final class Schema {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5674a = "tel:";
        public static final String b = "mailto:";
    }

    public static final class ShoppingCart {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5675a = "seckill";
        public static final String b = "buy";
        public static final String c = "insurance";
        public static final String d = "bigtap";
        public static final String e = "presales";
        public static final String f = "fcode";
        public static final String g = "bargain";
    }

    public static final class Stat {
        public static final String A = "category_click";
        public static final String B = "popularity_click";
        public static final String C = "filter_click";
        public static final String D = "%s_click";
        public static final String E = "%s_click";
        public static final String F = "message1_click";
        public static final String G = "cart_click";
        public static final String[] H = {"tab1_click", "tab2_click", "tab3_click", "tab4_click"};
        public static final String I = "category%s_click";
        public static final String J = "category%s_group%s_product%s_click";
        public static final String K = "account_settings1_click";
        public static final String L = "account_setting2_click";
        public static final String M = "account_tier_click";
        public static final String N = "account_mi_tokens_click";
        public static final String O = "account_all_orders_click";
        public static final String P = "account_unpaid_click";
        public static final String Q = "account_shipping_click";
        public static final String R = "account_%s_click";
        public static final String S = "account_returns_click";
        public static final String T = "account_reviews_click";
        public static final String U = "account_shipping_status_click";
        public static final String V = "santa_create_activity_click";
        public static final String W = "santa_take_photo_click";
        public static final String X = "santa_upload_photo_number";
        public static final String Y = "santa_identification_success_number";
        public static final String Z = "santa_identification_fail_number";

        /* renamed from: a  reason: collision with root package name */
        public static final String f5676a = "title_back";
        public static final String aa = "santa_upload_fail_number";
        public static final String ab = "santa_scan_fail_number";
        public static final String ac = "santa_page";
        public static final String ad = "search_engine";
        public static final String ae = "search_jump";
        public static final String af = "search_landing";
        public static final String ag = "search_filter";
        public static final String ah = "search_associative";
        public static final String ai = "navigation_message";
        public static final String aj = "navigation_cart";
        public static final String ak = "mistore_tab";
        public static final String al = "category_tab";
        public static final String am = "discover_tab";
        public static final String an = "account_tab";
        public static final String ao = "category";
        public static String ap = "flash_";
        public static String aq = "flash_";
        public static final String ar = "devicelist_click";
        public static final String as = "guideline_click";
        public static final String at = "selectprotect_click";
        public static final String au = "protectdetail_click";
        public static final String av = "accessorycart_%s_click";
        public static final String aw = "accessorydetail_%s_click";
        public static final String ax = "buynow_click";
        public static final String ay = "joinwaitlist_click";
        public static final String az = "appdownload_click";
        public static final String b = "title_close";
        public static final String c = "title_cart";
        public static final String d = "title_refresh";
        public static final String e = "store_card";
        public static final String f = "pay_promotion(%s)";
        public static final String g = "pay_method(%s)";
        public static final String h = "review_banner";
        public static final String i = "start_download_plugin";
        public static final String j = "download_plugin_finish";
        public static final String k = "install_plugin_finish";
        public static final String l = "update_channel";
        public static final String m = "click_facebook";
        public static final String n = "click_twitter";
        public static final String o = "click_enter_market";
        public static final String p = "share";
        public static final String q = "order";
        public static final String r = "ad_skip";
        public static final String s = "ad_click";
        public static final String t = "privilege_enter_click";
        public static final String u = "search_click";
        public static final String v = "%s_click";
        public static final String w = "%s_click";
        public static final String x = "cancel_click";
        public static final String y = "delete_click";
        public static final String z = "%s_click";
    }

    public static class WebServiceStatic {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5677a = "content://";
        public static final String b = "content://com.mi.global.shop.web/web/static";
        public static final String c = "content://com.mi.global.shop.web/web/static/index.html";
        public static final String d = "content://com.mi.global.shop.web/web/static/category.html";
        public static final String e = "content://com.mi.global.shop.web/web/static/service.html";
        public static final String f = "content://com.mi.global.shop.web/web/static/user.html";
        public static final String g = "content://com.mi.global.shop.web/web/static/un.html";
        public static final String h = "content://com.mi.global.shop.web/web/static/cart-insurance.html";
    }

    public static final class WebView {
        public static final String A = "hide_actionbar_exit_icon";
        public static final String B = "show_actionbar_exit_icon";
        public static final String C = "get_deviceid";
        public static final String D = "get_hash_deviceid";
        public static final String E = "hide_actionbar_fav_icon";
        public static final String F = "show_actionbar_fav_icon";
        public static final String G = "hide_actionbar_refresh_icon";
        public static final String H = "show_actionbar_refresh_icon";
        public static final String I = "send_product_id";
        public static final String J = "clear_web_cache";
        public static final String K = "login_callback";
        public static final String L = "start_another_app";
        public static final String M = "open_new_campaign";
        public static final String N = "null";
        public static final int O = 0;
        public static final int P = 1;
        public static final int Q = 2;
        public static final int R = 3;
        public static final String S = "account.xiaomi.com/pass/serviceLogin";
        public static final String T = "/pass/serviceLogin";

        /* renamed from: a  reason: collision with root package name */
        public static final String f5678a = "login";
        public static final String b = "product";
        public static final String c = "shopping";
        public static final String d = "opennew";
        public static final String e = "open_browser";
        public static final String f = "gohome";
        public static final String g = "fcode";
        public static final String h = "orderlist";
        public static final String i = "installed";
        public static final String j = "checkupdate";
        public static final String k = "iswifi";
        public static final String l = "bottomstyle";
        public static final String m = "productlist";
        public static final String n = "weibo";
        public static final String o = "miphonedetail";
        public static final String p = "showtitlebar";
        public static final String q = "hidetitlebar";
        public static final String r = "callsrecharge";
        public static final String s = "gocoupon";
        public static final String t = "sendwx";
        public static final String u = "send_wxtimeline";
        public static final String v = "send_qzone";
        public static final String w = "do_search";
        public static final String x = "fit_android_version";
        public static final String y = "close";
        public static final String z = "bought_insurance";
    }

    public static final class Account extends Constants.Account {
        public static final String T = "xm_in_sid";
        public static final String U = "pref_user_icons";
        public static final String V = "pref_cache_service_token_list";
        public static final String W = "pref_data_saver_mode";
        public static final String X = "pref_data_saver_toast_count";
        public static final String Y = "facebook_connect_success";
        public static final String Z = "facebook_connect_uid";
        public static final String aa = "facebook_connect_servicetoken";
        public static final String ab = "not_paid_order";
        public static final String ac = "not_used_coupon";
        public static String ad = null;
        public static final String ae = "pref_kefu_token";
        public static final String af = "pref_mipay_token";
        public static final String ag = "pref_mipay_slh";
        public static final String ah = "pref_mipay_ph";
        public static final String ai = "pref_mipay_cuid";

        public static void d() {
            if (b == null) {
                b = new Account();
            }
        }

        public static Account e() {
            return (Account) b;
        }
    }
}
