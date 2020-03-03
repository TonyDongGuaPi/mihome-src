package com.mi.global.bbs.utils;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.mobikwik.sdk.lib.Constants;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class WebViewHelper {
    public static boolean isFileUrlType(String str) {
        String lowerCase = str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase();
        if (!lowerCase.equals("pdf") && !lowerCase.equals("m4a") && !lowerCase.equals("mp3") && !lowerCase.equals(Constants.MID) && !lowerCase.equals("xmf") && !lowerCase.equals("ogg") && !lowerCase.equals("wav") && !lowerCase.equals("3gp") && !lowerCase.equals("mp4") && !lowerCase.equals("jpg") && !lowerCase.equals("gif") && !lowerCase.equals("png") && !lowerCase.equals("jpeg") && !lowerCase.equals("bmp") && !lowerCase.equals("apk") && !lowerCase.equals(ArchiveStreamFactory.g) && !str.contains(MessengerShareContentUtility.ATTACHMENT)) {
            return false;
        }
        return true;
    }
}
