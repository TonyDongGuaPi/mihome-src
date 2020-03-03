package com.xiaomi.smarthome.download;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Downloads implements BaseColumns {
    public static final String ACTION_DOWNLOAD_COMPLETED = "android.intent.action.DOWNLOAD_COMPLETED";
    public static final String ACTION_NOTIFICATION_CLICKED = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED";
    public static final Uri ALL_DOWNLOADS_CONTENT_URI = Uri.parse("content://com.xiaomi.smarthome.downloads/all_downloads");
    public static final String AUTHORITY = "com.xiaomi.smarthome.downloads";
    public static final String COLUMN_ALLOWED_NETWORK_TYPES = "allowed_network_types";
    public static final String COLUMN_ALLOW_ROAMING = "allow_roaming";
    public static final String COLUMN_APP_DATA = "entity";
    public static final String COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT = "bypass_recommended_size_limit";
    public static final String COLUMN_CONTROL = "control";
    public static final String COLUMN_COOKIE_DATA = "cookiedata";
    public static final String COLUMN_CURRENT_BYTES = "current_bytes";
    public static final String COLUMN_DELETED = "deleted";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DESTINATION = "destination";
    public static final String COLUMN_FILE_NAME_HINT = "hint";
    public static final String COLUMN_IS_PUBLIC_API = "is_public_api";
    public static final String COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI = "is_visible_in_downloads_ui";
    public static final String COLUMN_LAST_MODIFICATION = "lastmod";
    public static final String COLUMN_MIME_TYPE = "mimetype";
    public static final String COLUMN_NOTIFICATION_CLASS = "notificationclass";
    public static final String COLUMN_NOTIFICATION_EXTRAS = "notificationextras";
    public static final String COLUMN_NOTIFICATION_PACKAGE = "notificationpackage";
    public static final String COLUMN_NO_INTEGRITY = "no_integrity";
    public static final String COLUMN_OTHER_UID = "otheruid";
    public static final String COLUMN_REFERER = "referer";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TOTAL_BYTES = "total_bytes";
    public static final String COLUMN_UDN = "udn";
    public static final String COLUMN_URI = "uri";
    public static final String COLUMN_USER_AGENT = "useragent";
    public static final String COLUMN_VISIBILITY = "visibility";
    public static final Uri CONTENT_URI = Uri.parse("content://com.xiaomi.smarthome.downloads/my_downloads");
    public static final int CONTROL_PAUSED = 1;
    public static final int CONTROL_RUN = 0;
    public static final int DESTINATION_EXTERNAL = 0;
    public static final int DESTINATION_FILE_URI = 4;
    public static final int MIN_ARTIFICIAL_ERROR_STATUS = 488;
    public static final String PERMISSION_ACCESS = "com.xiaomi.smarthome.permission.ACCESS_DOWNLOAD_MANAGER";
    public static final String PERMISSION_ACCESS_ADVANCED = "com.xiaomi.smarthome.permission.ACCESS_DOWNLOAD_MANAGER_ADVANCED";
    public static final String PERMISSION_ACCESS_ALL = "com.xiaomi.smarthome.permission.ACCESS_ALL_DOWNLOADS";
    public static final String PERMISSION_NO_NOTIFICATION = "com.xiaomi.smarthome.permission.DOWNLOAD_WITHOUT_NOTIFICATION";
    public static final String PERMISSION_SEND_INTENTS = "com.xiaomi.smarthome.permission.SEND_DOWNLOAD_COMPLETED_INTENTS";
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_CANCELED = 490;
    public static final int STATUS_CANNOT_RESUME = 489;
    public static final int STATUS_DEVICE_NOT_FOUND_ERROR = 499;
    public static final int STATUS_FILE_ALREADY_EXISTS_ERROR = 488;
    public static final int STATUS_FILE_ERROR = 492;
    public static final int STATUS_HTTP_DATA_ERROR = 495;
    public static final int STATUS_HTTP_EXCEPTION = 496;
    public static final int STATUS_INSUFFICIENT_SPACE_ERROR = 498;
    public static final int STATUS_LENGTH_REQUIRED = 411;
    public static final int STATUS_NOT_ACCEPTABLE = 406;
    public static final int STATUS_PAUSED_BY_APP = 193;
    public static final int STATUS_PENDING = 190;
    public static final int STATUS_PRECONDITION_FAILED = 412;
    public static final int STATUS_QUEUED_FOR_WIFI = 196;
    public static final int STATUS_RUNNING = 192;
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_TOO_MANY_REDIRECTS = 497;
    public static final int STATUS_UNHANDLED_HTTP_CODE = 494;
    public static final int STATUS_UNHANDLED_REDIRECT = 493;
    public static final int STATUS_UNKNOWN_ERROR = 491;
    public static final int STATUS_WAITING_FOR_NETWORK = 195;
    public static final int STATUS_WAITING_TO_RETRY = 194;
    public static final int VISIBILITY_HIDDEN = 2;
    public static final int VISIBILITY_VISIBLE = 0;
    public static final int VISIBILITY_VISIBLE_NOTIFY_COMPLETED = 1;
    public static final String _DATA = "_data";

    public static class RequestHeaders {

        /* renamed from: a  reason: collision with root package name */
        public static final String f15565a = "request_headers";
        public static final String b = "download_id";
        public static final String c = "header";
        public static final String d = "value";
        public static final String e = "headers";
        public static final String f = "http_header_";
    }

    public static boolean isStatusClientError(int i) {
        return i >= 400 && i < 500;
    }

    public static boolean isStatusCompleted(int i) {
        return (i >= 200 && i < 300) || (i >= 400 && i < 600);
    }

    public static boolean isStatusError(int i) {
        return i >= 400 && i < 600;
    }

    public static boolean isStatusInformational(int i) {
        return i >= 100 && i < 200;
    }

    public static boolean isStatusServerError(int i) {
        return i >= 500 && i < 600;
    }

    public static boolean isStatusSuccess(int i) {
        return i >= 200 && i < 300;
    }

    private Downloads() {
    }
}
