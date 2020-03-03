package com.mi.global.bbs.model;

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

    public static boolean isJSONResultOK(JSONObject jSONObject) {
        return jSONObject != null && "ok".equals(jSONObject.optString("result"));
    }
}
