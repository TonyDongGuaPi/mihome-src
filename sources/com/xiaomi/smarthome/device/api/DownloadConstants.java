package com.xiaomi.smarthome.device.api;

import android.net.Uri;

public class DownloadConstants {
    public static final Uri ALL_DOWNLOADS_CONTENT_URI = Uri.parse("content://com.xiaomi.smarthome.downloads/all_downloads");
    public static final String AUTHORITY = "com.xiaomi.smarthome.downloads";
    public static final String COLUMN_BYTES_DOWNLOADED_SO_FAR = "bytes_so_far";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LAST_MODIFIED_TIMESTAMP = "last_modified_timestamp";
    public static final String COLUMN_LOCAL_URI = "local_uri";
    public static final String COLUMN_MEDIA_TYPE = "media_type";
    public static final String COLUMN_REASON = "reason";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TOTAL_SIZE_BYTES = "total_size";
    public static final String COLUMN_URI = "uri";
    public static final Uri CONTENT_URI = Uri.parse("content://com.xiaomi.smarthome.downloads/my_downloads");
    public static final int STATUS_FAILED = 16;
    public static final int STATUS_PAUSED = 4;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_RUNNING = 2;
    public static final int STATUS_SUCCESSFUL = 8;
}
