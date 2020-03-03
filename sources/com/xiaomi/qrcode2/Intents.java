package com.xiaomi.qrcode2;

public final class Intents {
    private Intents() {
    }

    public static final class Scan {

        /* renamed from: a  reason: collision with root package name */
        public static final String f13012a = "com.google.zxing.client.android.SCAN";
        public static final String b = "SCAN_MODE";
        public static final String c = "PRODUCT_MODE";
        public static final String d = "ONE_D_MODE";
        public static final String e = "QR_CODE_MODE";
        public static final String f = "DATA_MATRIX_MODE";
        public static final String g = "AZTEC_MODE";
        public static final String h = "PDF417_MODE";
        public static final String i = "SCAN_FORMATS";
        public static final String j = "SCAN_CAMERA_ID";
        public static final String k = "CHARACTER_SET";
        public static final String l = "SCAN_WIDTH";
        public static final String m = "SCAN_HEIGHT";
        public static final String n = "RESULT_DISPLAY_DURATION_MS";
        public static final String o = "PROMPT_MESSAGE";
        public static final String p = "SCAN_RESULT";
        public static final String q = "SCAN_RESULT_FORMAT";
        public static final String r = "SCAN_RESULT_UPC_EAN_EXTENSION";
        public static final String s = "SCAN_RESULT_BYTES";
        public static final String t = "SCAN_RESULT_ORIENTATION";
        public static final String u = "SCAN_RESULT_ERROR_CORRECTION_LEVEL";
        public static final String v = "SCAN_RESULT_BYTE_SEGMENTS_";
        public static final String w = "SAVE_HISTORY";

        private Scan() {
        }
    }

    public static final class History {

        /* renamed from: a  reason: collision with root package name */
        public static final String f13011a = "ITEM_NUMBER";

        private History() {
        }
    }

    public static final class Encode {

        /* renamed from: a  reason: collision with root package name */
        public static final String f13010a = "com.google.zxing.client.android.ENCODE";
        public static final String b = "ENCODE_DATA";
        public static final String c = "ENCODE_TYPE";
        public static final String d = "ENCODE_FORMAT";
        public static final String e = "ENCODE_SHOW_CONTENTS";

        private Encode() {
        }
    }

    public static final class SearchBookContents {

        /* renamed from: a  reason: collision with root package name */
        public static final String f13013a = "com.google.zxing.client.android.SEARCH_BOOK_CONTENTS";
        public static final String b = "ISBN";
        public static final String c = "QUERY";

        private SearchBookContents() {
        }
    }

    public static final class WifiConnect {

        /* renamed from: a  reason: collision with root package name */
        public static final String f13015a = "com.google.zxing.client.android.WIFI_CONNECT";
        public static final String b = "SSID";
        public static final String c = "TYPE";
        public static final String d = "PASSWORD";

        private WifiConnect() {
        }
    }

    public static final class Share {

        /* renamed from: a  reason: collision with root package name */
        public static final String f13014a = "com.google.zxing.client.android.SHARE";

        private Share() {
        }
    }
}
