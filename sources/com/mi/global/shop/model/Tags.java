package com.mi.global.shop.model;

import org.json.JSONObject;

public class Tags {
    public static final String CODE = "code";
    public static final String DATA = "data";
    public static final String DESCRIPTION = "description";
    public static final String REASON = "reason";
    public static final String RESULT = "result";
    public static final String RESULT_OK = "ok";
    public static final String RESULT_TRUE = "true";
    public static final String STATUS = "status";

    public static final class Activity {
        public static final String STATUS = "status";
        public static final String TYPE = "type";
        public static final String TYPE_BUY = "buy";
        public static final String TYPE_RESERVE = "reserve";
        public static final String URL = "url";
        public static final String VERSION = "version";
    }

    public static final class AddressInfo {
        public static final String ADDRESS = "address";
        public static final String AREA_ID = "id";
        public static final String AREA_NAME = "name";
        public static final String CHINA_ID = "1";
        public static final String CITY = "city";
        public static final String CONSIGNEE = "consignee";
        public static final String COUNTRY = "country";
        public static final String DISTRICT = "district";
        public static final int ERROR_CODE_CITY = 2013004;
        public static final int ERROR_CODE_CONSIGNEE = 2013001;
        public static final int ERROR_CODE_DISTRICT = 2013005;
        public static final int ERROR_CODE_LOCATION = 2013006;
        public static final int ERROR_CODE_PROVINCE = 2013003;
        public static final int ERROR_CODE_TEL = 2013008;
        public static final int ERROR_CODE_ZIPCODE = 2013007;
        public static final String ID = "address_id";
        public static final String MIHOME_BUY_ADDR = "mihome_buy_addr";
        public static final String PROVINCE = "province";
        public static final String TEL = "tel";
        public static final String ZIPCODE = "zipcode";
    }

    public static final class BaiduLbs {
        public static final String ADDRTYPE = "all";
        public static final String BAIDUMAP_PACKAGE = "com.baidu.BaiduMap";
        public static final String BAIDU_LBS_URI_CONTENT = "&content=";
        public static final String BAIDU_LBS_URI_END = "&coord_type=bd09ll&zoom=&referer=mishop#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end";
        public static final String BAIDU_LBS_URI_INTENT = "intent://map/marker?location=";
        public static final String BAIDU_LBS_URI_TITLE = "&title=";
        public static final String BAIDU_LBS_URL_HTTP = "http://api.map.baidu.com/staticimage?width=360&height=560&center=";
        public static final String BAIDU_LBS_URL_LABEL = "&labelStyles=";
        public static final String BAIDU_LBS_URL_LABELSTYLES = ",1,18,0xffffff,0x2e2e2e,1&markers=";
        public static final String BAIDU_LBS_URL_MARKERSTYLES = "&markerStyles=l,";
        public static final String BAIDU_LBS_URL_ZOOM = "&zoom=15&dpiType=ph&labels=";
        public static final String COORTYPE = "bd09ll";
        public static final String LAT_LNG_SEPARATOR = ",";
        public static final String PRODNAME = "mishop";
        public static final String SERVICENAME = "com.baidu.location.service_v3.3";
        public static final int TIMEOUT = 1500;
    }

    public interface Category {
        public static final String CATEGORIES = "categroies";
        public static final String CATE_ID = "cate_id";
        public static final String CATE_NAME = "cate_name";
        public static final String IMAGE_URLS = "image_url";
        public static final String TOTAL_COUNT = "total_count";
    }

    public static final class CategoryTree {
        public static final String ADAPT_CODE = "adapt_id";
        public static final String CAT_ID = "cat_id";
        public static final String CAT_NAME = "cat_name";
        public static final String CHILDREN = "children";
        public static final String DATA_TYPE = "data_type";
        public static final String HAS_CHILDREN = "has_children";
        public static final String IMAGE_URLS = "image_url";
    }

    public static final class CheckCode {
        public static final String COOKIE_AUTHCODE = "authcode";
        public static final String GOODS = "targetGoods";
        public static final String LIST = "product_list";
        public static final String URL = "url";
    }

    public static final class CheckoutSubmit {
        public static final String ADDRESS = "address";
        public static final String ADDRESS_ID = "address_id";
        public static final String BRIEF = "brief";
        public static final String CHECKED = "checked";
        public static final String DELIVERTIME = "delivertime";
        public static final String DESC = "desc";
        public static final String HOME_ID = "home_id";
        public static final String INVOICE = "invoice";
        public static final String INVOICE_ID_COMPANY = "2";
        public static final String INVOICE_ID_PERSONAL = "1";
        public static final String INVOICE_OPEN = "invoice_open";
        public static final String INVOICE_TYPE = "type";
        public static final String INVOICE_TYPE_COMMON = "common";
        public static final String INVOICE_TYPE_ELECTRON = "electron";
        public static final String LIST = "list";
        public static final String MIHOME_BUY_ID = "client_mihome_id";
        public static final String NAME = "name";
        public static final String PAYLIST = "paylist";
        public static final String PAY_ID = "pay_id";
        public static final String PICKUP_ID_DEFAULT = "0";
        public static final String PICKUP_ID_SELF = "6";
        public static final String SHIPMENTLIST = "shipmentlist";
        public static final String SHIPMENT_ID = "shipment_id";
        public static final String SHIPMENT_ID_SELF = "25";
        public static final String VALUE = "value";
    }

    public static final class ComboList {
        public static final String IMAGE_URL = "image_url";
        public static final String IS_SALE = "is_sale";
        public static final String PRICE = "price";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_STYLE = "product_style";
        public static final String RESULT = "result";
    }

    public static final class Coupon {
        public static final String BEGIN_TIME = "beginTime";
        public static final String COUPON_CODE = "coupon_code";
        public static final String COUPON_ID = "couponId";
        public static final String COUPON_NAME = "couponName";
        public static final String COUPON_TYPE = "coupon_type";
        public static final String COUPON_TYPE_F = "1";
        public static final String COUPON_TYPE_NF = "2";
        public static final String END_TIME = "endTime";
        public static final String POSTFREE = "postfree";
        public static final String PRESENT = "present";
        public static final String REASON = "reason";
        public static final String REPLACE_MONEY = "replaceMoney";
        public static final String RESULT = "result";
        public static final String RESULT_ALLOW = "allow";
        public static final String RESULT_REFUSE = "refuse";
        public static final String STAT = "stat";
        public static final String STAT_EXPIRED = "expired";
        public static final String STAT_UNUSED = "unused";
        public static final String STAT_USED = "used";
        public static final String TYPE = "type";
        public static final String TYPE_CASH = "cash";
        public static final String TYPE_DISCOUNT = "discount";
        public static final String TYPE_PRESENT = "present";
        public static final String USABLE_RANGE = "usableRange";
        public static final String VALUE_DESC = "valueDesc";
    }

    public static final class DelCart {
        public static final String ITEM_ID = "itemId";
        public static final String MIHOME_BUY_ID = "client_mihome_id";
    }

    public static final class EditConsumption {
        public static final String CONSUMPTION = "consumption";
        public static final String ITEM_ID = "itemId";
        public static final String MIHOME_BUY_ID = "client_mihome_id";
    }

    public static final class EditOrder {
        public static final String ADDRESS = "address";
        public static final String BEST_TIME = "best_time";
        public static final String CHECKCODE = "checkcode";
        public static final String CITY = "city";
        public static final String CONSIGNEE = "consignee";
        public static final String COUNTRY = "country";
        public static final String DISTRICT = "district";
        public static final String ORDER_ID = "order_id";
        public static final String ORDER_MODIFY = "order_modify";
        public static final String PROVINCE = "province";
        public static final String TEL = "tel";
        public static final String TYPE = "type";
        public static final String USER_ID = "user_id";
        public static final String VALUE_TYPE_ADDRESS = "address";
        public static final String VALUE_TYPE_TIME = "time";
        public static final String ZIPCODE = "zipcode";
    }

    public static final class FCodeSelectProduct {
        public static final String BRIEF = "product_brief";
        public static final String IMAGE_URL = "image_url";
        public static final String IS_CHANGE_STYLE = "is_change_style";
        public static final String NAME = "product_name";
        public static final String PRICE = "price";
        public static final String PRODUCT_ID = "product_id";
        public static final String SIZE = "180";
        public static final String SIZE_SINGLE = "800";
    }

    public static final class Home {
        public static final String ACTIVITY_ICON = "activity_icon";
        public static final String ACTIVITY_URL = "activity_url";
        public static final String BIG_PHOTO_URL = "big_image";
        public static final String FULL_PRICE = "full_price";
        public static final String ITEMS = "items";
        public static final String ITEM_COUNT = "item_count";
        public static final String ITEM_TYPE = "item_type";
        public static final String PHOTO_URL = "big_image_url";
        public static final String PRODUCT = "product";
        public static final String PRODUCT_DETAIL = "product_detail";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_PRICE = "product_price";
        public static final String THUMBNAIL_URL = "image_url";
    }

    public static final class Lottery {
        public static final String IMAGE_URL = "image_url";
        public static final String ITEMS = "items";
        public static final String ITEM_TYPE = "item_type";
        public static final String KEY_NATIVE_TYPE_LIST = "nativelist";
        public static final String NATIVE_TYPE_COCACOLA = "cocacola";
        public static final String NATIVE_TYPE_KUWAN = "kuwan";
        public static final String NATIVE_TYPE_RECHARGE = "recharge";
        public static final String NATIVE_TYPE_SCANNERBUY = "scanner";
        public static final String NATIVE_TYPE_SHAKE = "shake";
        public static final String URL = "url";
    }

    public static final class LuckyShake {
        public static final String CODE = "code";
        public static final String EXPIRED = "expired";
        public static final String HIT_TIME = "hit_time";
        public static final String IMAGE_SIZE = "510";
        public static final String IMAGE_URL = "image_url";
        public static final String MARKET_PRICE = "market_price";
        public static final String MESSAGE = "mess";
        public static final String PRICE = "price";
        public static final String PRODUCT = "product";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String REMAIN = "remain";
        public static final String REMAIN_NUMBER = "number";
        public static final String SECURITY_CODE = "security_code";
        public static final String STATUS = "status";
        public static final String VALUE_FAIL_HITTED = "1003";
        public static final String VALUE_FAIL_OVER_TIME = "1002";
        public static final String VALUE_FAIL_REMAIN_CODE = "1005";
        public static final String VALUE_HIT_FAIL = "1014";
        public static final String VALUE_SUCCESS_CODE = "1000";
    }

    public static final class MiHome {
        public static final String ADDRESS = "address";
        public static final String AVAILABLE = "available";
        public static final String CANCEL_RESERVE_ID = "reserve_id";
        public static final String CITY = "city";
        public static final String CITY_NAME = "city_name";
        public static final String DESC = "desc";
        public static final String GRADE = "comment_star";
        public static final String HOME_ID = "home_id";
        public static final String LONGITUDE_LATITUDE = "longitude_latitude";
        public static final String MAN = "先生";
        public static final String MAN_ID = "1";
        public static final String MIHOME_LIST = "mihome_list";
        public static final String MIHOME_LIST_REGULAR = "市";
        public static final String MIHOME_LIST_REPLACE = "小米之家";
        public static final String NAME = "name";
        public static final String PROVINCE = "province";
        public static final String PROVINCE_NAME = "province_name";
        public static final String RESERVE = "reserve";
        public static final String RESERVE_BRUSH = "自助刷机";
        public static final String RESERVE_BRUSH_ID = "3";
        public static final String RESERVE_CONSULT = "到店咨询";
        public static final String RESERVE_CONSULT_ID = "2";
        public static final String RESERVE_DATE_AFTER_TOMORROW = "后天";
        public static final String RESERVE_DATE_FORMAT = "yyyy-MM-dd";
        public static final String RESERVE_DATE_TODAY = "今天";
        public static final String RESERVE_DATE_TOMORROW = "明天";
        public static final String RESERVE_ID = "id";
        public static final String RESERVE_SERVICE = "售后服务";
        public static final String RESERVE_SERVICE_ID = "1";
        public static final String STATUS = "status";
        public static final String TEL = "tel";
        public static final String TEL_SEPARATOR0 = "-";
        public static final String TEL_SEPARATOR1 = "/";
        public static final String TEL_SEPARATOR2 = "、";
        public static final String TEL_SEPARATOR3 = " ";
        public static final String TEL_SEPARATOR4 = "\\";
        public static final String TIME = "time";
        public static final String TIMELIST = "timelist";
        public static final String TYPE_LIST = "type_list";
        public static final String WOMAN = "女士";
        public static final String WOMAN_ID = "2";
    }

    public static final class MiPhone {
        public static final String BRIEF = "brief";
        public static final String DESCRIBE = "describe";
        public static final String DESCRIBE_TWO = "describe_two";
        public static final String DISPLAY_TYPE = "display_type";
        public static final String IMAGE_URL = "image_url";
        public static final String LEFT_BUTTON = "left_button";
        public static final String LEFT_URL = "left_url";
        public static final String PRICE = "price";
        public static final String PRODUCT_ID = "phone_type";
        public static final String PRODUCT_NAME = "phone_name";
        public static final String PRODUCT_URL = "product_url";
        public static final String RIGHT_BUTTON = "right_button";
        public static final String RIGHT_URL = "right_url";
    }

    public static final class MiPhoneDetails {
        public static final String ACTIVITY_URL = "activity_url";
        public static final String DETAILS = "details";
        public static final String FEATURES = "features";
        public static final String FEATURE_NAME = "feature_name";
        public static final String FOCUS_IMG = "focus_img";
        public static final String GALLERY = "gallery";
        public static final String IMG = "img";
        public static final String IS_AVAIL = "is_avail";
        public static final String IS_PHONE = "is_phone";
        public static final String LAST_ITEM = "last_item";
        public static final String MEDIA = "media";
        public static final String NEXT_ITEM = "next_item";
        public static final String PHONE_TYPE = "phone_type";
        public static final String PRODUCT_BRIEF = "product_brief";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_IMG = "product_img";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_PRICE = "product_price";
        public static final String TEXT = "text";
        public static final String URL = "url";
    }

    public static final class MiPhoneSalesRecord {
        public static final String ADD_TIME = "add_time";
        public static final String GOODS_NAME = "goods_name";
        public static final String IMEI = "imei";
        public static final String UNIQUECODE = "uniquecode";
    }

    public static final class MihomeBuyInfo {
        public static final String ADDRESS = "address";
        public static final String CITY = "city";
        public static final String DISTRICT = "district";
        public static final String HOME_ID = "home_id";
        public static final String NAME = "name";
        public static final String PROVINCE = "province";
        public static final String SELF = "mihome";
        public static final String STATUS = "status";
        public static final String TEL = "tel";
    }

    public static final class MihomeCheckInfo {
        public static final String CLIENT_MIHOME_ID = "mihome_id";
        public static final String COLOR = "background_color";
        public static final String DESC = "text";
        public static final String IMAGE_URL = "image_url";
        public static final String MIHOME_NAME = "mihome_name";
        public static final String SIGNIN_COUNT = "signin_count";
        public static final String SIGNS = "signs";
        public static final String TDCODE = "tdcode";
    }

    public static final class Order {
        public static final String ADDRESS = "address";
        public static final String ADD_TIME = "add_time";
        public static final String AREA_ID = "id";
        public static final String AREA_NAME = "name";
        public static final String BEST_TIME = "best_time";
        public static final String CART_PRICT = "cart_price";
        public static final String CITY = "city";
        public static final String CONSIGNEE = "consignee";
        public static final String CONSIGNEE_PHONE = "tel";
        public static final String DELIVERS = "delivers";
        public static final String DELIVER_ID = "deliver_id";
        public static final String DISTRICT = "district";
        public static final String EXPRESS = "express";
        public static final String EXPRESS_ID = "express_id";
        public static final String EXPRESS_INFO = "express_info";
        public static final String EXPRESS_NAME = "express_name";
        public static final String EXPRESS_SHOW = "is_show";
        public static final String EXPRESS_SN = "express_sn";
        public static final String EXPRESS_TRACE = "express_trace";
        public static final String EXPRESS_TRACE_TEXT = "track";
        public static final String EXPRESS_TRACE_TIME = "time";
        public static final String EXPRESS_UPDATE_TIME = "express_update_time";
        public static final String FEE = "goods_amount";
        public static final String HAS_PHONE = "hasPhone";
        public static final String ID = "order_id";
        public static final String IMAGE_SIZE = "180";
        public static final String IMAGE_URL = "image_url";
        public static final String INVOICE = "e_invoice";
        public static final String INVOICE_TITLE = "invoice_title";
        public static final String INVOICE_TYPE = "invoice_type";
        public static final String INVOICE_TYPE_NAME = "invoice_type_name";
        public static final String IS_MESSAGE_CHECK = "is_message_check";
        public static final String MIHOME_BUY_ID = "client_mihome_id";
        public static final String ORDER_NEXT_CANCEL = "CANCLE_ORDER";
        public static final String ORDER_NEXT_CONFIRM = "CONFIRM_ORDER";
        public static final String ORDER_NEXT_PAY = "PAY_MONEY";
        public static final String ORDER_STATUS_CHANGE = "3";
        public static final String ORDER_STATUS_CLOSE = "5";
        public static final String ORDER_STATUS_EXPRESS = "8";
        public static final String ORDER_STATUS_INFO = "order_status_info";
        public static final String ORDER_STATUS_OPEN = "2";
        public static final String ORDER_STATUS_REFUND = "6";
        public static final String ORDER_STATUS_WAIT_PAYMENT = "7";
        public static final String ORDER_TRACE = "trace";
        public static final String ORIGINAL_PRICE = "original_price";
        public static final int PAYMENG_STATUS_RECEIPT = 8;
        public static final int PAYMENG_STATUS_SHIPPED = 7;
        public static final int PAYMENT_STATUS_CONTRACTPHONE_WAIT_VERIFY = 52;
        public static final int PAYMENT_STATUS_NOTIFIED_WAREHOUST = 50;
        public static final int PAYMENT_STATUS_OK = 4;
        public static final int PAYMENT_STATUS_WAIT_NOTIFY_WAREHOUSE = 49;
        public static final int PAYMENT_STATUS_WAIT_PAY = 3;
        public static final String PICKUP_ADDRESS = "address";
        public static final String PICKUP_INFO = "pickup_info";
        public static final String PICKUP_LONLAT = "longitude_latitude";
        public static final String PICKUP_NAME = "name";
        public static final String PICKUP_TEL = "tel";
        public static final String PRODUCT = "product";
        public static final String PRODUCT_COUNT = "product_count";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PROVINCE = "province";
        public static final String REDUCE_PRICE = "reduce_price";
        public static final String SHIPMENT_EXPRENSE = "shipment_expense";
        public static final String STATUS = "order_status";
        public static final String STATUS_DELIVER_INFO = "delivery_info";
        public static final String STATUS_INFO = "order_status_info";
        public static final String STATUS_NEXT = "next";
        public static final String STATUS_SHOWTYPE = "showtype";
        public static final String STATUS_STRING = "info";
        public static final String TOTAL_PRICT = "subtotal";
        public static final String TRACK_INFO = "order_track_info";
        public static final String TRACK_TEXT = "text";
        public static final String TRACK_TIME = "time";
        public static final String TYPE = "type";
        public static final String ZIPCODE = "zipcode";
    }

    public static final class OrderSubmit {
        public static final String ACTIVITY_DISCOUNT_MONEY = "activityDiscountMoney";
        public static final String ADDRESS_ID = "address_id";
        public static final String AMOUNT = "amount";
        public static final String AMOUNT_DESC = "amountDesc";
        public static final String BEST_TIME = "best_time";
        public static final String CARTLIST = "cartlist";
        public static final String CHECK_CODE = "check_code";
        public static final String COUPON_CODE = "coupon_code";
        public static final String COUPON_DISCOUNT_MONEY = "couponDiscountMoney";
        public static final String COUPON_TYPE = "coupon_type";
        public static final String EXTEND_FIELD = "extend_field";
        public static final String EXTEND_FIELD_CONSIGNESS = "consignee";
        public static final String EXTEND_FIELD_TEL = "tel";
        public static final String INVOICE_TITLE = "invoice_title";
        public static final String INVOICE_TYPE = "invoice_type";
        public static final String MIHOME_BUY_ID = "client_mihome_id";
        public static final String NEEDCHECKCODE = "needcheckcode";
        public static final String PAY_ID = "pay_id";
        public static final String PICKUP_ID = "pickup_id";
        public static final String PRODUCT_MONEY = "productMoney";
        public static final String SHIPMENT = "shipment";
        public static final String SHIPMENT_ID = "shipment_id";
        public static final String TOTAL = "total";
    }

    public static final class Phone {
        public static final String ALL_PHONE = "0";
        public static final String ALL_PHONETYPE = "-1";
        public static final String M11S_PHONE = "2";
        public static final String M1_PHONE = "1";
        public static final String M22S_PHONE = "16";
        public static final String M2A_PHONE = "32";
        public static final String M2_PHONE = "4";
        public static final String M3_PHONE = "128";
        public static final String MI_BOX = "8";
        public static final String MI_TV = "256";
        public static final String MRED_PHONE = "64";
    }

    public static final class PhoneModel {
        public static final String CODE = "code";
        public static final String IMAGE_URL = "image_url";
        public static final String NAME = "name";
        public static final String SYMBOL = "symbol";
        public static final String TEXT = "text";
    }

    public static final class PrepaidRechargeInfo {
        public static final String BOOLEAN = "boolean";
        public static final String MAX = "max";
        public static final String MESSAGE = "message";
        public static final String MIN = "min";
        public static final String NAME = "name";
        public static final String ORDER_ID = "order_id";
        public static final String PERVALUE = "pervalue";
        public static final String PREVALUE = "prevalue";
        public static final String PRODUCT_NAME = "product_name";
        public static final String RECHARGE_NAME = "recharge_name";
        public static final String SELL_PRICE = "sellprice";
    }

    public interface Product {
        public static final String CATE_NAME = "cate_name";
        public static final String CURRENT_PAGE = "current_page";
        public static final String DISPLAY_BROWSER = "display_browser";
        public static final String DISPLAY_NATIVE = "display_native";
        public static final String DISPLAY_TYPE = "display_type";
        public static final String DISPLAY_WEB = "display_web";
        public static final String IMAGE_URL = "image_url";
        public static final String IS_COS = "is_cos";
        public static final String MARKET_PRICE = "market_price";
        public static final String PRICE = "price";
        public static final String PRODUCT = "product";
        public static final String PRODUCT_COUNT = "product_count";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String STYLE_NAME = "style_name";
        public static final String TOTAL_COUNT = "total_count";
        public static final String TOTAL_PAGES = "total_pages";
        public static final String URL = "url";
    }

    public static final class ProductDetails {
        public static final String ADAPT = "adapt";
        public static final String ADAPT_URL = "adapt_url";
        public static final String ATTRS = "attrs";
        public static final String BUY_LIMIT = "buy_limit";
        public static final String CANJOINACTS = "canJoinActs";
        public static final String ELEMENTS_PRODUCT = "elements_goods";
        public static final String IMAGES = "images";
        public static final String IMAGES_ONE = "1";
        public static final String IMAGE_URL = "image_url";
        public static final String IS_CHANGE_STYLE = "is_change_style";
        public static final String IS_COS = "is_cos";
        public static final String IS_PHONE = "is_phone";
        public static final String LAST_ITEM = "last_item";
        public static final String MARKET_PRICE = "market_price";
        public static final String NEXT_ITEM = "next_item";
        public static final String PRICE = "price";
        public static final String PRODUCT_BRIEF = "product_brief";
        public static final String PRODUCT_DESC_IMG = "product_desc_img";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PRODUCT_NEW_STYLE = "new_style";
        public static final String PRODUCT_STYLE = "style";
        public static final String PRODUCT_STYLE_DATA = "data";
        public static final String PRODUCT_STYLE_ID = "key";
        public static final String PRODUCT_STYLE_NAME = "value";
        public static final String PRODUCT_STYLE_TYPE = "name";
        public static final String RESULT = "result";
        public static final String STYLE_NAME = "style_name";
        public static final String TITLE = "title";
        public static final String TYPE_DESC = "typeDesc";
    }

    public static final class Purcharser {
        public static final String ACTIVITY_PURCHASER_PRODUCT = "activity_purchaser_product";
        public static final String BUY_LIMIT = "buy_limit";
        public static final String CODE = "code";
        public static final String GIFT = "gift";
        public static final String GIFT_PURCHASER = "gift_purchaser";
        public static final String IMAGE_THUMBNAIL = "180";
        public static final String IMAGE_URL = "image_url";
        public static final String IS_CHANGE_STYLE = "is_change_style";
        public static final String IS_COS = "is_cos";
        public static final String MARKET_PRICE = "market_price";
        public static final String MESSAGE = "message";
        public static final String PRICE = "price";
        public static final String PRODUCT_BRIEF = "product_brief";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PURCHARSER = "purcharser";
        public static final String PURCHASER_PRODUCT = "purchaser_product";
    }

    public static final class Push {
        public static final String DESC = "description";
        public static final String ORDER_ID = "order_id";
        public static final String PARAM_WATERMARK = "watermark";
        public static final String PRODUCT_ID = "product_id";
        public static final String TITLE = "title";
        public static final String TYPE_ID = "type_id";
        public static final String URL = "url";

        public static final class ShopWaterMarkTypes {
            public static final String ORDER_REMIND = "3";
            public static final String ORDER_STATUS_SHIPPED = "0";
            public static final String RECOMMEND_CAMPAIGN = "1";
            public static final String SALE_OUT_REFILL = "2";
        }
    }

    public static final class RemindInfo {
        public static final String JSON_KEY_EXPRESS = "express";
        public static final String JSON_KEY_REVIEW = "wait_comment";
        public static final String JSON_KEY_UNPAY = "no_pay";
    }

    public static final class RepairOrder {
        public static final String ADD_TIME = "add_time";
        public static final String CURRENT_STATUS = "currentStatus";
        public static final String EXPRESS = "express";
        public static final String GOODS_NAME = "goods_name";
        public static final String IMAGE_SIZE = "180";
        public static final String IMAGE_URL = "image_url";
        public static final String OPERATOR_TEXT = "operator_text";
        public static final String ORDER_ID = "order_id";
        public static final String PRODUCT = "product";
        public static final String PRODUCT_ID = "goods_id";
        public static final String PRODUCT_NAME = "goods_name";
        public static final String PRODUCT_PRICT = "market_price";
        public static final String SERVICE = "service";
        public static final String SERVICE_ID = "service_id";
        public static final String SERVICE_ITEMS = "items";
        public static final String SERVICE_LOG = "logs";
        public static final String SERVICE_STATUS = "service_status";
        public static final String SERVICE_STATUS_NAME = "service_status_name";
    }

    public static final class RepairProgress {
        public static final String ADD_TIME = "add_time";
        public static final String JC_REMARK = "jc_remark";
        public static final String SERVICE_ID = "service_id";
        public static final String STATUSDESC = "status_desc";
        public static final String UPDATE_TIME = "update_time";
    }

    public static final class ReserveOrder {
        public static final String DESC = "desc";
        public static final String END_TIME = "end_time";
        public static final String HOME_NAME = "name";
        public static final String MIHOME = "mihome";
        public static final String MIHOME_ID = "home_id";
        public static final String ORDER_ID = "id";
        public static final String RESERVE_DATE = "reserve_date";
        public static final String RESERVE_TIME = "reserve_time";
        public static final String RESERVE_TYPE = "reserve_type";
        public static final String ROWS = "rows";
        public static final String STATUS = "reserve_status";
        public static final String TEL = "telphone";
        public static final String USER_NAME = "user_name";
    }

    public static final class SaleOutRegister {
        public static final String MORE = "more";
    }

    public static final class ServiceStation {
        public static final String ADDRESS = "address";
        public static final String CITY = "city";
        public static final String CONTACT = "contact";
        public static final String PHONE = "phone";
    }

    public static final class ServicesInfo {
        public static final String SERVICES_INFO_DATA = "data";
        public static final String SERVICES_INFO_IMG = "img";
        public static final String SERVICES_INFO_IMG_LIST = "img_list";
        public static final String SERVICES_INFO_LINK_URL = "link_url";
        public static final String SERVICES_INFO_OPEN_TYPE = "open_type";
        public static final String SERVICES_INFO_PRODUCT_ID = "product_id";
    }

    public static final class ShakeCup {
        public static final String MY_PRIZE = "my_prize";
        public static final String PRIZES = "prizes";
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String TIME = "hit_time";
        public static final String USER_ID = "user_id";
    }

    public static final class ShoppingCartList {
        public static final String ACTIVITYS = "activitys";
        public static final String ACTNAME = "actName";
        public static final String ADAPT = "adapt";
        public static final String BALANCE_PRICE = "balance_price";
        public static final String BUY_LIMIT = "buy_limit";
        public static final String CAN_CHANGE_NUM = "can_change_num";
        public static final String CAN_DELETE = "can_delete";
        public static final String COUPONS = "coupons";
        public static final String GATHER_ORDER_INFO = "gatherorder_info";
        public static final String GIFT = "gift";
        public static final String GOOD_LIST = "goods_list";
        public static final String IMAGE_PHOTO = "800";
        public static final String IMAGE_THUMBNAIL = "180";
        public static final String IMAGE_URL = "image_url";
        public static final String ITEMS = "items";
        public static final String ITEM_ID = "itemId";
        public static final String NUM = "num";
        public static final String POSTFREE = "postFree";
        public static final String POSTFREEBALANCE = "postFreeBalance";
        public static final String PRODUCT_NAME = "product_name";
        public static final String PROPERTIES = "properties";
        public static final String REDUCTION = "reduction";
        public static final String SALE_PRICE = "salePrice";
        public static final String SHOWTYPE = "showType";
        public static final String SHOWTYPE_BARGIN = "bargain";
        public static final String SHOWTYPE_ERNIE = "ernie";
        public static final String SHOWTYPE_GIFT = "gift";
        public static final String SHOWTYPE_GIFTCARD = "giftcard";
        public static final String SHOWTYPE_SECKILL = "seckill";
        public static final String SHOWTYPE_SPECIAL = "special";
        public static final String SHOW_LIST = "show_list";
        public static final String SUB_TOTAL = "subtotal";
        public static final String TOTAL = "total";
        public static final String TOTAL_PRICE = "totalprice";
    }

    public static final class ShoppingSupply {
        public static final String ACT_ID = "actId";
        public static final String BARGAINS = "bargains";
        public static final String BARGAIN_NAME = "bargain_name";
        public static final String BOUGHT_PRODUCT_ID = "bought_product_id";
        public static final String CHECKED = "checked";
        public static final String ITEM_ID = "item_Id";
        public static final String PRODUCT_ID = "product_id";
        public static final String SELECTABLE = "selectable";
        public static final String SELECTABLE_PRODUCTS = "selectable_products";
        public static final String SELECT_INFO = "selecInfo";
    }

    public static final class UserInfo {
        public static final String JSON_KEY_DATA = "data";
        public static final String JSON_KEY_USER_ID = "userId";
        public static final String JSON_KEY_USER_NAME = "userName";
    }

    public static final class VersionUpdate {
        public static final String NEED_UPDATE = "needUpdate";
        public static final String UPDATE_NOTES = "notes";
        public static final String UPDATE_TYPE = "updateType";
        public static final String UPDATE_URL = "url";
        public static final String UPDATE_VERSION = "version";
        public static final String UPDATE_VERSION_CODE = "versionCode";
    }

    public static boolean isJSONResultOK(JSONObject jSONObject) {
        return jSONObject != null && "ok".equals(jSONObject.optString("result"));
    }

    public final class Recharge {
        public static final String ADD_TIME = "add_time";
        public static final String ORDER_ID = "order_id";
        public static final String ORDER_STATUS = "order_status";
        public static final String ORDER_STATUS_INFO = "order_status_info";
        public static final String PRODUCT_NAME = "product_name";
        public static final String RECHARGE_NAME = "recharge_name";
        public static final String SELL_PRICE = "sellprice";
        public static final int STATUS_CLOSE = 8;
        public static final int STATUS_DONE = 2;
        public static final int STATUS_FAIL = 5;
        public static final int STATUS_REFUNDED = 7;
        public static final int STATUS_REFUNDING = 6;
        public static final int STATUS_SUCCEED = 4;
        public static final int STATUS_WAIT_PAY = 1;
        public static final int STATUS_WAIT_PLATFORM = 3;

        public Recharge() {
        }
    }

    public final class CommentInfo {
        public static final String ADD_TIME = "add_time";
        public static final String AVERAGE_GRADE = "average_grade";
        public static final String COMMENTS = "comments";
        public static final String COMMENTS_BAD = "comments_bad";
        public static final String COMMENTS_GENERAL = "comments_general";
        public static final String COMMENTS_GOOD = "comments_good";
        public static final String COMMENT_CONTENT = "comment_content";
        public static final String TOTAL_COUNT = "total_count";
        public static final String USER_NAME = "user_name";

        public CommentInfo() {
        }
    }

    public final class Review {
        public static final String ARRIVE_TIME = "arrive_time";
        public static final String COMMENT_LABELS = "comment_labels";
        public static final String GOODS_ID = "goods_id";
        public static final String GOODS_IMG = "goods_img";
        public static final String GOODS_LIST = "goodsList";
        public static final String GOODS_NAME = "goods_name";
        public static final String IS_COMMENT = "isComment";
        public static final String PMS_COMMODITY_ID = "pms_commodity_id";
        public static final String PMS_PRODUCT_ID = "pms_product_id";
        public static final String SHOP_PRICE = "shop_price";

        public Review() {
        }
    }

    public final class MiHomeStorage {
        public static final String BODY = "body";
        public static final String RESULTS = "results";
        public static final String STORAGE_AMOUNT = "storage_amount";

        public MiHomeStorage() {
        }
    }

    public final class Kuwan {
        public static final String AUTHOR = "author";
        public static final String AUTHOR_TIME = "dateline";
        public static final String BBS_TID = "bbs_tid";
        public static final String CATEGORY = "category";
        public static final String COMMENT = "context";
        public static final String COMMENT_FLOOR = "floor";
        public static final String COMMENT_TIME = "time";
        public static final String COMMENT_USER = "user_name";
        public static final String COMMENT_USER_IMAGE_URL = "user_image_url";
        public static final String CONTEXT = "context";
        public static final String CONTEXT_IMAGE_URL = "image_url";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE_URL = "thumb";
        public static final String NUMS_PER_PAGE = "offset";
        public static final String PAGE_NUM = "page";
        public static final String PRODUCT_ID = "product_url";
        public static final String PRODUCT_TYPE = "product_type";
        public static final String SUBJECT = "subject";
        public static final String TID = "tid";
        public static final String TITLE = "title";
        public static final String TYPE = "p_type";
        public static final String URL = "url";
        public static final String VIEWS = "views";

        public Kuwan() {
        }
    }

    public final class Nearby {
        public static final String AGE = "age";
        public static final String DEVICE = "device";
        public static final String DEVICE_NAME = "device_name";
        public static final String DISTANCE = "distance";
        public static final String FORUM_NAME = "forumName";
        public static final String FORUM_TAGS = "forumTags";
        public static final String FORUM_TAG_NAME = "bbs_tagname";
        public static final String GOODS_IMAGE = "goods_img";
        public static final String GOODS_LIST = "goods_list";
        public static final String GOODS_PRODUCT_ID = "app_product_id";
        public static final String HELP_ID = "help_uid";
        public static final String ICON = "icon";
        public static final String LAT = "lat";
        public static final String LIST = "list";
        public static final String LON = "lon";
        public static final String MEDIA_IMAGE = "image";
        public static final String MEDIA_LIST = "media_list";
        public static final String MEDIA_PRODUCT_COUNT = "count";
        public static final String MEDIA_PRODUCT_ID = "product_id";
        public static final String MEDIA_PRODUCT_IS_ONSALE = "is_sale";
        public static final String MEDIA_PRODUCT_M = "url_product_m";
        public static final String MIUI_TAGS = "miuiTags";
        public static final String MIUI_TAG_NAME = "miui_tagname";
        public static final String NAME = "name";
        public static final String PHONE_MODEL = "phone_device";
        public static final String SEX = "gender";
        public static final String USER_ID = "userId";
        public static final String USER_SELF = "user_self";

        public Nearby() {
        }
    }

    public final class PaidService {
        public static final String ACCEPT_TIME = "acceptTime";
        public static final String BUY_DATE = "orderDate";
        public static final String IMEI = "imei";
        public static final String IMG_URL = "imgUrl";
        public static final String MATERIAL = "repairMethod";
        public static final String ORDER_ID = "orderId";
        public static final String ORG_NAME = "orgName";
        public static final String PHONE_MODEL = "phoneModel";
        public static final String REPAIRED_TIME = "returnTime";
        public static final String SERVICE_NUM = "serviceno";
        public static final String STATUS = "statusDesc";
        public static final String VALIDATE_DATE = "endDate";

        public PaidService() {
        }
    }
}
