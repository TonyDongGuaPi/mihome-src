package com.huawei.hms.update.a.a;

import com.amap.api.services.core.AMapException;
import com.xiaomi.youpin.network.annotation.DownloadStatus;

public final class d {
    public static String a(int i) {
        if (i == 1000) {
            return "CHECK_OK";
        }
        if (i == 1101) {
            return "CHECK_CANCELED";
        }
        if (i == 2000) {
            return "DOWNLOAD_SUCCESS";
        }
        switch (i) {
            case 1201:
                return "CHECK_FAILURE";
            case 1202:
                return "CHECK_NO_UPDATE";
            case 1203:
                return "CHECK_NO_SUPPORTED";
            default:
                switch (i) {
                    case AMapException.CODE_AMAP_NEARBY_INVALID_USERID:
                        return DownloadStatus.DOWNLOADING;
                    case 2101:
                        return "DOWNLOAD_CANCELED";
                    default:
                        switch (i) {
                            case 2201:
                                return "DOWNLOAD_FAILURE";
                            case AMapException.CODE_AMAP_CLIENT_NEARBY_NULL_RESULT:
                                return "DOWNLOAD_HASH_ERROR";
                            case AMapException.CODE_AMAP_CLIENT_UPLOAD_TOO_FREQUENT:
                                return "DOWNLOAD_NO_SPACE";
                            case AMapException.CODE_AMAP_CLIENT_UPLOAD_LOCATION_ERROR:
                                return "DOWNLOAD_NO_STORAGE";
                            default:
                                return "UNKNOWN - " + Integer.toString(i);
                        }
                }
        }
    }
}
