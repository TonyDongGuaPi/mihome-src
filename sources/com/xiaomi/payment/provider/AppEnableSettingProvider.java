package com.xiaomi.payment.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.Utils;
import com.miui.tsmclient.util.Constants;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.payment.ui.MibiLicenseActivity;

public class AppEnableSettingProvider extends ContentProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12401a = "AppEnableSettingProvider";
    private static final String b = "com.xiaomi.payment.provider";
    private static final UriMatcher c = new UriMatcher(-1);
    private static final int d = 0;
    private static final String[] e = {Constants.PACKAGE_NAME_WALLET, AccountIntent.PACKAGE_XIAOMI_ACCOUNT};
    private static final String f = "appEnable";
    private static final String[] g = {f};

    public boolean onCreate() {
        return true;
    }

    static {
        c.addURI(b, "app_enable", 0);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (a(uri) == 0 && a(getContext())) {
            return a(Utils.a(getContext(), MibiLicenseActivity.KEY_APP_ENABLE, true) ? 1 : 0);
        }
        return null;
    }

    public String getType(Uri uri) {
        throw new UnsupportedOperationException("No default type");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external delete");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (a(uri) == 0) {
            if (!a(getContext())) {
                return -1;
            }
            Utils.b(getContext(), MibiLicenseActivity.KEY_APP_ENABLE, ((Integer) contentValues.get(f)).intValue() != 0);
        }
        return 0;
    }

    private boolean a(Context context) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        if (packagesForUid == null) {
            return false;
        }
        for (String str : packagesForUid) {
            for (String equals : e) {
                if (TextUtils.equals(str, equals)) {
                    return true;
                }
            }
        }
        return false;
    }

    private MatrixCursor a(int i) {
        MatrixCursor matrixCursor = new MatrixCursor(g);
        matrixCursor.addRow(new Object[]{Integer.valueOf(i)});
        return matrixCursor;
    }

    private static int a(Uri uri) {
        int match = c.match(uri);
        if (match >= 0) {
            Log.v(f12401a, "uri=" + uri + ", match is " + match);
            return match;
        }
        throw new IllegalArgumentException("Unknown uri: " + uri);
    }
}
