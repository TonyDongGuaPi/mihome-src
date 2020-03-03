package com.mi.global.bbs.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.DialogFactory;
import com.orhanobut.logger.Logger;
import com.taobao.weex.annotation.JSMethod;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PermissionClaimer {
    /* access modifiers changed from: private */
    public static final String DEFAULT_HINT_TEXT = BBSApplication.getInstance().getResources().getString(R.string.str_permission_tip);
    private static final int MSG_PERMISSION_DENIED = 19;
    private static final int MSG_PERMISSION_GRANTED = 18;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private static PermissionHandler sHandler = new PermissionHandler();

    public static class DefaultPermissionReqListener implements PermissionReqListener {
        public void onDenied(String... strArr) {
        }

        public void onGranted() {
        }
    }

    public interface PermissionReqListener {
        void onDenied(String... strArr);

        void onGranted();
    }

    public static void requestPermissions(Activity activity, final Runnable runnable, String... strArr) {
        requestPermissions(activity, true, (String) null, new DefaultPermissionReqListener() {
            public void onGranted() {
                if (runnable != null) {
                    runnable.run();
                }
            }

            public void onDenied(String... strArr) {
                if (runnable != null) {
                    runnable.run();
                }
            }
        }, strArr);
    }

    public static void requestPermissions(Activity activity, DefaultPermissionReqListener defaultPermissionReqListener, String... strArr) {
        requestPermissions(activity, true, defaultPermissionReqListener, strArr);
    }

    public static void requestPermissions(Activity activity, boolean z, PermissionReqListener permissionReqListener, String... strArr) {
        requestPermissions(activity, z, (String) null, permissionReqListener, strArr);
    }

    public static void requestPermissions(final Activity activity, boolean z, String str, PermissionReqListener permissionReqListener, String... strArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (permissionReqListener != null) {
                sHandler.setPermissionReqListener(permissionReqListener);
            }
            sHandler.setHandle(z);
            if (z) {
                sHandler.setActivity(activity);
            }
            final ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : strArr) {
                if (ActivityCompat.checkSelfPermission(activity, str2) != 0) {
                    arrayList.add(str2);
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, str2)) {
                        arrayList2.add(str2);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                dispatchGranted(permissionReqListener);
            } else if (!arrayList2.isEmpty()) {
                if (TextUtils.isEmpty(str)) {
                    str = getDefaultRationString(arrayList2);
                }
                showMessageOKCancel(activity, str, new DialogFactory.DefaultOnAlertClick() {
                    public void onOkClick(View view) {
                        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 123);
                    }
                });
            } else {
                ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 123);
            }
        } else {
            dispatchGranted(permissionReqListener);
        }
    }

    public static void requestPermissionsWithReasonDialog(final Activity activity, boolean z, String str, PermissionReqListener permissionReqListener, String... strArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (permissionReqListener != null) {
                sHandler.setPermissionReqListener(permissionReqListener);
            }
            sHandler.setHandle(z);
            if (z) {
                sHandler.setActivity(activity);
            }
            final ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : strArr) {
                if (ActivityCompat.checkSelfPermission(activity, str2) != 0) {
                    arrayList.add(str2);
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, str2)) {
                        arrayList2.add(str2);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                if (TextUtils.isEmpty(str)) {
                    str = getDefaultRationString(arrayList2);
                }
                DialogFactory.showAlert(activity, str, new DialogFactory.DefaultOnAlertClick() {
                    public void onOkClick(View view) {
                        super.onOkClick(view);
                        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 123);
                    }

                    public void onCancelClick(View view) {
                        super.onCancelClick(view);
                    }
                });
                return;
            }
            dispatchGranted(permissionReqListener);
            return;
        }
        dispatchGranted(permissionReqListener);
    }

    public static String getRequestPermissionReasons(Context context, int... iArr) {
        if (context == null || iArr == null) {
            return null;
        }
        if (iArr.length == 1) {
            String string = context.getResources().getString(iArr[0]);
            if (!TextUtils.isEmpty(string)) {
                return string.replaceAll("Mi Community", context.getResources().getString(R.string.app_name));
            }
            return null;
        } else if (iArr.length <= 1) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < iArr.length; i++) {
                String string2 = context.getResources().getString(iArr[i]);
                if (!TextUtils.isEmpty(string2)) {
                    string2 = string2.replaceAll("Mi Community", context.getResources().getString(R.string.app_name));
                }
                sb.append(string2);
                if (i != iArr.length - 1) {
                    sb.append("\n");
                }
            }
            return sb.toString();
        }
    }

    private static String getDefaultRationString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder(BBSApplication.getInstance().getResources().getString(R.string.str_permission_span));
        sb.append(" ");
        sb.append(getPermissionString(arrayList.get(0)));
        for (int i = 1; i < arrayList.size(); i++) {
            sb.append(", ");
            sb.append(getPermissionString(arrayList.get(i)));
        }
        sb.append(".");
        return sb.toString();
    }

    private static void dispatchGranted(PermissionReqListener permissionReqListener) {
        if (permissionReqListener != null) {
            permissionReqListener.onGranted();
        }
    }

    public static boolean checkPermissions(Context context, String... strArr) {
        for (String checkSelfPermission : strArr) {
            if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 123) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] == 0) {
                    arrayList.add(strArr[i2]);
                } else {
                    arrayList2.add(strArr[i2]);
                }
            }
            Logger.b("request_permission_result!", new Object[0]);
            if (arrayList.size() != strArr.length || iArr.length <= 0) {
                sendMsgToCallback(arrayList2);
            } else {
                sHandler.sendEmptyMessage(18);
            }
        }
    }

    private static void sendMsgToCallback(ArrayList<String> arrayList) {
        Message obtainMessage = sHandler.obtainMessage();
        obtainMessage.what = 19;
        obtainMessage.obj = arrayList;
        obtainMessage.sendToTarget();
    }

    public static void defaultHandleForPermissionDenied(final Context context, String str) {
        showMessageOKCancel(context, str, new DialogFactory.DefaultOnAlertClick() {
            public void onOkClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
            }
        });
    }

    private static void showMessageOKCancel(Context context, String str, DialogFactory.DefaultOnAlertClick defaultOnAlertClick) {
        Logger.b(str, new Object[0]);
        DialogFactory.showAlert(context, str, defaultOnAlertClick);
    }

    private static String getPermissionString(String str) {
        return str.toLowerCase().replace(".", "#").split("#")[2].replace(JSMethod.NOT_SET, " ");
    }

    private static class PermissionHandler extends Handler {
        private boolean isHandle;
        private WeakReference<Activity> mActivity;
        private WeakReference<PermissionReqListener> mListener;

        private PermissionHandler() {
        }

        public void setPermissionReqListener(PermissionReqListener permissionReqListener) {
            this.mListener = new WeakReference<>(permissionReqListener);
        }

        public void setActivity(Activity activity) {
            this.mActivity = new WeakReference<>(activity);
            setHandle(true);
        }

        public void setHandle(boolean z) {
            this.isHandle = z;
        }

        public void handleMessage(Message message) {
            PermissionReqListener permissionReqListener;
            Activity activity;
            switch (message.what) {
                case 18:
                    if (this.mListener != null && (permissionReqListener = (PermissionReqListener) this.mListener.get()) != null) {
                        permissionReqListener.onGranted();
                        return;
                    }
                    return;
                case 19:
                    if (!this.isHandle || (activity = (Activity) this.mActivity.get()) == null) {
                        ArrayList arrayList = (ArrayList) message.obj;
                        if (arrayList == null || arrayList.size() <= 0) {
                            handleDenied(new String[0]);
                            return;
                        } else {
                            handleDenied((String[]) arrayList.toArray(new String[arrayList.size()]));
                            return;
                        }
                    } else {
                        PermissionClaimer.defaultHandleForPermissionDenied((Context) this.mActivity.get(), PermissionClaimer.DEFAULT_HINT_TEXT.replaceAll("Mi Community", activity.getResources().getString(R.string.app_name)));
                        return;
                    }
                default:
                    return;
            }
        }

        private void handleDenied(String... strArr) {
            PermissionReqListener permissionReqListener;
            if (this.mListener != null && (permissionReqListener = (PermissionReqListener) this.mListener.get()) != null) {
                permissionReqListener.onDenied(strArr);
            }
        }
    }
}
