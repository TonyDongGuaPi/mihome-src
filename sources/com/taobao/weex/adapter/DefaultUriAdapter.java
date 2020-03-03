package com.taobao.weex.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.taobao.weex.WXSDKInstance;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class DefaultUriAdapter implements URIAdapter {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-3024114718972377512L, "com/taobao/weex/adapter/DefaultUriAdapter", 31);
        $jacocoData = a2;
        return a2;
    }

    public DefaultUriAdapter() {
        $jacocoInit()[0] = true;
    }

    @NonNull
    public Uri rewrite(WXSDKInstance wXSDKInstance, String str, Uri uri) {
        boolean[] $jacocoInit = $jacocoInit();
        Uri rewrite = rewrite(wXSDKInstance.getBundleUrl(), str, uri);
        $jacocoInit[1] = true;
        return rewrite;
    }

    @NonNull
    public Uri rewrite(String str, String str2, Uri uri) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[2] = true;
            return uri;
        }
        Uri parse = Uri.parse(str);
        $jacocoInit[3] = true;
        Uri.Builder buildUpon = uri.buildUpon();
        $jacocoInit[4] = true;
        if (uri.isRelative()) {
            $jacocoInit[5] = true;
            if (uri.getEncodedPath().length() == 0) {
                $jacocoInit[6] = true;
                if (!"image".equals(str2)) {
                    $jacocoInit[7] = true;
                } else {
                    $jacocoInit[8] = true;
                    if (!TextUtils.isEmpty(uri.toString())) {
                        $jacocoInit[9] = true;
                    } else {
                        $jacocoInit[10] = true;
                        return uri;
                    }
                }
                $jacocoInit[11] = true;
                return parse;
            }
            Uri.Builder buildRelativeURI = buildRelativeURI(buildUpon, parse, uri);
            $jacocoInit[12] = true;
            Uri build = buildRelativeURI.build();
            $jacocoInit[13] = true;
            return build;
        }
        $jacocoInit[14] = true;
        return uri;
    }

    private Uri.Builder buildRelativeURI(Uri.Builder builder, Uri uri, Uri uri2) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (uri2.getAuthority() != null) {
            $jacocoInit[15] = true;
            Uri.Builder scheme = builder.scheme(uri.getScheme());
            $jacocoInit[16] = true;
            return scheme;
        }
        $jacocoInit[17] = true;
        Uri.Builder encodedAuthority = builder.encodedAuthority(uri.getEncodedAuthority());
        $jacocoInit[18] = true;
        Uri.Builder scheme2 = encodedAuthority.scheme(uri.getScheme());
        $jacocoInit[19] = true;
        scheme2.path((String) null);
        $jacocoInit[20] = true;
        if (uri2.getPath().startsWith("/")) {
            $jacocoInit[21] = true;
            builder.appendEncodedPath(uri2.getEncodedPath().substring(1));
            $jacocoInit[22] = true;
        } else {
            List<String> pathSegments = uri.getPathSegments();
            $jacocoInit[23] = true;
            int i2 = 0;
            if (!uri.getPath().endsWith("/")) {
                $jacocoInit[24] = true;
                i = 1;
            } else {
                $jacocoInit[25] = true;
                i = 0;
            }
            int size = pathSegments.size() - i;
            $jacocoInit[26] = true;
            while (i2 < size) {
                $jacocoInit[27] = true;
                builder.appendEncodedPath(pathSegments.get(i2));
                i2++;
                $jacocoInit[28] = true;
            }
            builder.appendEncodedPath(uri2.getEncodedPath());
            $jacocoInit[29] = true;
        }
        $jacocoInit[30] = true;
        return builder;
    }
}
