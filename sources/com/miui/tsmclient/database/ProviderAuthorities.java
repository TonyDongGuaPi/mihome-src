package com.miui.tsmclient.database;

import android.content.Context;
import android.net.Uri;

public class ProviderAuthorities {
    public static String AUTHORITY = String.format(AUTHORITY_FORMAT, new Object[]{"com.miui.tsmclient.common"});
    private static final String AUTHORITY_FORMAT = "%s.platform.provider";
    public static Uri CONTENT_URI = Uri.parse(String.format(CONTENT_URI_FORMAT, new Object[]{"com.miui.tsmclient.common"}));
    private static final String CONTENT_URI_FORMAT = "content://%s.platform.provider";
    private static final String PACKAGE_NAME = "com.miui.tsmclient.common";
    public static Uri PUBLIC_CONTENT_URI = Uri.parse(String.format(PUBLIC_CONTENT_URI_FORMAT, new Object[]{"com.miui.tsmclient.common"}));
    private static final String PUBLIC_CONTENT_URI_FORMAT = "content://%s.platform.provider.public";

    public static void init(Context context) {
        String packageName = context.getPackageName();
        AUTHORITY = String.format(AUTHORITY_FORMAT, new Object[]{packageName});
        CONTENT_URI = Uri.parse(String.format(CONTENT_URI_FORMAT, new Object[]{packageName}));
        PUBLIC_CONTENT_URI = Uri.parse(String.format(PUBLIC_CONTENT_URI_FORMAT, new Object[]{packageName}));
    }
}
