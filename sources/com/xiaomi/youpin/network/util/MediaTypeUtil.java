package com.xiaomi.youpin.network.util;

import android.text.TextUtils;
import com.xiaomi.youpin.network.annotation.ContentType;
import okhttp3.MediaType;

public class MediaTypeUtil {
    public static MediaType a(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str) || !str.contains(".")) {
            return null;
        }
        String substring = str.substring(str.lastIndexOf(".") + 1);
        if ("png".equals(substring)) {
            str3 = "image/png";
        } else if ("jpg".equals(substring)) {
            str3 = "image/jpg";
        } else if ("jpeg".equals(substring)) {
            str3 = "image/jpeg";
        } else if ("gif".equals(substring)) {
            str3 = "image/gif";
        } else if ("bmp".equals(substring)) {
            str3 = "image/bmp";
        } else if ("tiff".equals(substring)) {
            str3 = "image/tiff";
        } else {
            str3 = "ico".equals(substring) ? "image/ico" : ContentType.FORM_DATA;
        }
        return MediaType.parse(str3 + str2);
    }
}
