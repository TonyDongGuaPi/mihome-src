package com.mi.util;

import android.net.Uri;

public class PathUtils {
    public static String a(String str) {
        Uri parse = Uri.parse(Uri.decode(str));
        StringBuilder sb = new StringBuilder();
        if (parse.getScheme() != null) {
            sb.append(parse.getScheme());
            sb.append("://");
        } else {
            sb.append("/");
        }
        if (parse.getAuthority() != null) {
            sb.append(Uri.encode(parse.getAuthority(), ":@"));
        }
        if (parse.getPath() != null) {
            sb.append(Uri.encode(parse.getPath(), "/"));
        }
        if (parse.getQuery() != null) {
            sb.append("?");
            sb.append(Uri.encode(parse.getQuery(), "=&:/"));
        }
        if (parse.getFragment() != null) {
            sb.append("#");
            sb.append(Uri.encode(parse.getFragment()));
        }
        return sb.toString();
    }
}
