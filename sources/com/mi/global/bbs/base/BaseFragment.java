package com.mi.global.bbs.base;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.mi.account.sdk.util.Constants;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.manager.MiCommunitySdkManager;
import com.mi.global.bbs.manager.SdkListener;
import com.mi.global.bbs.ui.ShortContentDetailActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.checkin.SignDetailActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.ui.column.ColumnHomeActivity;
import com.mi.global.bbs.ui.forum.NewsForumActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DownloadPluginDialogUtil;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.dialog.LoadingDialog;
import com.mi.util.permission.PermissionCallback;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BaseFragment extends RxFragment {
    private static int sPermissionRequestCode = 528;
    private static HashMap<Integer, PermissionCallback> sPermissionRequestMap = new HashMap<>();
    public LoadingDialog mProgressDialog;

    public void toast(String str) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), str, 0).show();
        }
    }

    public void toast(@StringRes int i) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), i, 0).show();
        }
    }

    public String getQuantityString(int i, int i2) {
        if (getActivity() == null) {
            return "";
        }
        return getActivity().getResources().getQuantityString(i, i2, new Object[]{Integer.valueOf(i2)});
    }

    /* access modifiers changed from: protected */
    public void adjustStatusBar(View view) {
        int statusBarHeight = getStatusBarHeight();
        if (statusBarHeight > 0) {
            view.getLayoutParams().height = statusBarHeight;
            view.requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public int getStatusBarHeight() {
        int i = 0;
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            int parseInt = Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString());
            try {
                return getResources().getDimensionPixelSize(parseInt);
            } catch (Exception e) {
                int i2 = parseInt;
                e = e;
                i = i2;
                e.printStackTrace();
                return i;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return i;
        }
    }

    public void showProDialog(String str) {
        if (this.mProgressDialog == null || !this.mProgressDialog.isShowing()) {
            this.mProgressDialog = new LoadingDialog(getActivity());
            this.mProgressDialog.show();
        }
    }

    public void dismissProDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void gotoAccount() {
        SdkListener listener = MiCommunitySdkManager.getInstance().getListener();
        if (listener != null) {
            listener.onNeedLogin(Constants.Account.accountSid);
            return;
        }
        throw new IllegalStateException("you should call MiCommunitySdkManager.getInstance().setListener() first.");
    }

    public void refreshWebUrl(String str) {
        if (!checkJumpUrl(str)) {
            WebActivity.jump(getContext(), ConnectionHelper.getAppUrl(str));
        }
    }

    public boolean checkJumpUrl(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        String appUrl = ConnectionHelper.getAppUrl(str);
        if (appUrl.toLowerCase().contains(Constants.WebView.WEB_LOGIN_URL.toLowerCase())) {
            gotoAccount();
            return true;
        } else if (!TextUtils.isEmpty(appUrl) && appUrl.startsWith(ConnectionHelper.getPluginUrl())) {
            DownloadPluginDialogUtil.download(getContext(), appUrl);
            return true;
        } else if (appUrl.contains("space-uid-")) {
            UserCenterActivity.jump(getContext(), str.substring(str.indexOf("space-uid-") + "space-uid-".length(), str.lastIndexOf(".")));
            return true;
        } else {
            if (appUrl.startsWith("mailto:") || appUrl.startsWith("tel:")) {
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (appUrl.toLowerCase().contains("account.xiaomi.com/pass/serviceLogin".toLowerCase())) {
                LoginManager.getInstance().logout();
                SdkListener listener = MiCommunitySdkManager.getInstance().getListener();
                if (listener != null) {
                    listener.onNeedLogin(Constants.Account.accountSid);
                }
                return true;
            } else if (appUrl.endsWith("forum.php")) {
                NewsForumActivity.jump(getContext(), 0);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_COLUMN)) {
                ColumnHomeActivity.jump(getContext());
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_COLUMN_DETAIL)) {
                ColumnDetailActivity.jumpWithUrl(getContext(), appUrl);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_SIGN_DETAIL)) {
                SignDetailActivity.jumpWithUrl(getContext(), appUrl);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.PAGE_SHORT_CONTENT_DETAIL)) {
                ShortContentDetailActivity.jumpWithUrl(getContext(), appUrl);
                return true;
            } else if (appUrl.contains(Constants.WebViewURL.GO_STORE_RN) || appUrl.contains(Constants.WebViewURL.GO_STORE_RN_SECOND) || appUrl.contains(Constants.WebViewURL.GO_STORE_RN_TEST) || appUrl.contains(Constants.WebViewURL.GO_STORE_RN_SECOND_TEST)) {
                String str3 = "bbs_" + Utils.getTopActivity();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("goreactnative://mi.com"));
                if (appUrl.contains("?")) {
                    str2 = appUrl + "&origin=" + str3;
                } else {
                    str2 = appUrl + "?origin=" + str3;
                }
                intent.putExtra("url", str2);
                startActivity(intent);
                return true;
            } else {
                try {
                    if (ConnectionHelper.needOpenInBrowser(appUrl)) {
                        Uri parse = Uri.parse(appUrl);
                        Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.setData(parse);
                        startActivity(intent2);
                        return true;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return false;
            }
        }
    }

    public void requestPermissions(Activity activity, PermissionCallback permissionCallback, @NonNull String... strArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> filterPermissions = filterPermissions(activity, strArr);
            if (filterPermissions.size() != 0) {
                requestPermissions((String[]) filterPermissions.toArray(new String[filterPermissions.size()]), sPermissionRequestCode);
                sPermissionRequestMap.put(Integer.valueOf(sPermissionRequestCode), permissionCallback);
                sPermissionRequestCode++;
            } else if (permissionCallback != null) {
                permissionCallback.onResult();
                permissionCallback.onGranted();
            }
        } else if (permissionCallback != null) {
            permissionCallback.onResult();
            permissionCallback.onGranted();
        }
    }

    private static List<String> filterPermissions(Activity activity, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionCallback permissionCallback = sPermissionRequestMap.get(Integer.valueOf(i));
        if (permissionCallback != null) {
            permissionCallback.onResult();
            if (checkGrantResults(iArr)) {
                permissionCallback.onGranted();
            } else {
                permissionCallback.onDenied();
            }
            sPermissionRequestMap.remove(Integer.valueOf(i));
        }
    }

    private static boolean checkGrantResults(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
