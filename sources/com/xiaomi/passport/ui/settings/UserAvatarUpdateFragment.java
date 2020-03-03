package com.xiaomi.passport.ui.settings;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import com.xiaomi.smarthome.library.common.widget.crop.CropImageActivity;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class UserAvatarUpdateFragment extends Fragment {
    private static final String AVATAR_FILE_NAME = "xiaomi_user_avatar_file";
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final String FILE_PROVIDER_AUTHORITY_SUFFIX = ".passport.fileprovider";
    private static final int REQUEST_CROP_PHOTO = 1004;
    private static final int REQUEST_PICK_PHOTO_FROM_GALLERY = 1003;
    private static final int REQUEST_TAKE_PHOTO = 1002;
    private static final String TAG = "UserAvatarUpdateFragment";
    private File mAvatarCachedFile;
    private Uri mFileProviderUri;
    private UploadUserAvatarTask mUploadAvatarTask;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getActivity().getPackageManager().hasSystemFeature("android.hardware.camera")) {
            AccountLog.i(TAG, "has camera");
        } else {
            AccountLog.i(TAG, "no camera");
        }
        String string = getArguments().getString(UserAvatarUpdateActivity.EXTRA_UPDATE_AVATAR_TYPE);
        if (UserAvatarUpdateActivity.CAMERA.equals(string)) {
            checkCameraPermissionAndTakePhoto();
        } else if ("gallery".equals(string)) {
            startPickPhotoFromGallery();
        } else {
            finishActivity();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (AuthenticatorUtil.getXiaomiAccount(getActivity()) == null) {
            AccountLog.w(TAG, "no xiaomi account");
            finishActivity();
        }
        View findViewById = getActivity().findViewById(16908290);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserAvatarUpdateFragment.this.finishActivity();
                }
            });
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 100 && iArr[0] == 0) {
            startTakePhoto();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            super.onActivityResult(r4, r5, r6)
            r0 = 0
            r1 = 0
            r2 = -1
            switch(r4) {
                case 1002: goto L_0x002c;
                case 1003: goto L_0x002c;
                case 1004: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x003d
        L_0x000a:
            if (r6 == 0) goto L_0x0026
            android.os.Bundle r4 = r6.getExtras()
            if (r4 == 0) goto L_0x0026
            android.os.Bundle r4 = r6.getExtras()
            java.lang.String r5 = "data"
            java.lang.Object r4 = r4.get(r5)
            boolean r5 = r4 instanceof android.graphics.Bitmap
            if (r5 == 0) goto L_0x003d
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            r3.startUploadAvatar(r4)
            goto L_0x003d
        L_0x0026:
            if (r5 != r2) goto L_0x003e
            r3.startUploadAvatar(r1)
            goto L_0x003d
        L_0x002c:
            if (r5 != r2) goto L_0x003e
            if (r6 == 0) goto L_0x0034
            android.net.Uri r1 = r6.getData()
        L_0x0034:
            if (r1 != 0) goto L_0x003a
            android.net.Uri r1 = r3.getFileProviderUri()
        L_0x003a:
            r3.doCropPhoto(r1)
        L_0x003d:
            r0 = 1
        L_0x003e:
            if (r0 != 0) goto L_0x0043
            r3.finishActivity()
        L_0x0043:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onDestroy() {
        if (this.mUploadAvatarTask != null) {
            this.mUploadAvatarTask.cancel(true);
            this.mUploadAvatarTask = null;
        }
        super.onDestroy();
    }

    private void checkCameraPermissionAndTakePhoto() {
        Activity activity = getActivity();
        if (ContextCompat.checkSelfPermission(activity, "android.permission.CAMERA") == 0) {
            startTakePhoto();
        } else if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.CAMERA"}, 100);
        }
    }

    private void startTakePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(AgentOptions.k, getFileProviderUri());
        intent.addFlags(2);
        startActivityForResult(intent, 1002);
    }

    private void startPickPhotoFromGallery() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType(ShareObject.d);
        startActivityForResult(intent, 1003);
    }

    private void doCropPhoto(Uri uri) {
        if (uri == null) {
            AccountLog.i(TAG, "inputUri is null");
            return;
        }
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            Uri fileProviderUri = getFileProviderUri();
            intent.setDataAndType(uri, ShareObject.d);
            intent.putExtra(AgentOptions.k, fileProviderUri);
            intent.addFlags(1);
            intent.putExtra(CropImageActivity.RETURN_DATA, true);
            Activity activity = getActivity();
            for (ResolveInfo resolveInfo : activity.getPackageManager().queryIntentActivities(intent, 0)) {
                String str = resolveInfo.activityInfo.packageName;
                activity.grantUriPermission(str, uri, 1);
                activity.grantUriPermission(str, fileProviderUri, 2);
            }
            intent.putExtra("tips", getString(R.string.account_crop_user_avatar));
            addCropExtras(intent, getPhotoPickSize());
            startActivityForResult(intent, 1004);
        } catch (Exception e) {
            AccountLog.e(TAG, "Cannot crop image", e);
            Toast.makeText(getActivity(), R.string.photoPickerNotFoundText, 1).show();
        }
    }

    private static void addCropExtras(Intent intent, int i) {
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra(CropImageActivity.SCALE_UP_IF_NEEDED, true);
        intent.putExtra(CropImageActivity.ASPECT_X, 1);
        intent.putExtra(CropImageActivity.ASPECT_Y, 1);
        intent.putExtra(CropImageActivity.OUTPUT_X, i);
        intent.putExtra(CropImageActivity.OUTPUT_Y, i);
    }

    private int getPhotoPickSize() {
        return getResources().getDimensionPixelSize(R.dimen.upload_user_avatar_size);
    }

    private Uri getFileProviderUri() {
        if (this.mFileProviderUri == null) {
            this.mFileProviderUri = FileProvider.getUriForFile(getActivity(), getCurrentPackageAuthority(), getFileProviderFile());
        }
        return this.mFileProviderUri;
    }

    private String getCurrentPackageAuthority() {
        return getActivity().getPackageName() + FILE_PROVIDER_AUTHORITY_SUFFIX;
    }

    /* access modifiers changed from: private */
    public File getFileProviderFile() {
        if (this.mAvatarCachedFile == null) {
            this.mAvatarCachedFile = new File(getActivity().getCacheDir(), AVATAR_FILE_NAME);
        }
        return this.mAvatarCachedFile;
    }

    private void startUploadAvatar(Bitmap bitmap) {
        if (this.mUploadAvatarTask != null) {
            this.mUploadAvatarTask.cancel(true);
            this.mUploadAvatarTask = null;
        }
        this.mUploadAvatarTask = new UploadUserAvatarTask(getActivity(), bitmap);
        this.mUploadAvatarTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
    }

    private class UploadUserAvatarTask extends AsyncTask<Void, Void, UploadAvatarTaskResult> {
        private final Bitmap mBitmap;
        private Context mContext;
        private ProgressDialog mProgressDialog;

        UploadUserAvatarTask(Context context, Bitmap bitmap) {
            this.mBitmap = bitmap;
            this.mContext = context.getApplicationContext();
            this.mProgressDialog = new ProgressDialog(context);
            this.mProgressDialog.setMessage(UserAvatarUpdateFragment.this.getString(R.string.user_avatar_uploading));
            this.mProgressDialog.setCanceledOnTouchOutside(false);
            this.mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener(UserAvatarUpdateFragment.this) {
                public void onDismiss(DialogInterface dialogInterface) {
                    UploadUserAvatarTask.this.cancel(true);
                }
            });
            this.mProgressDialog.show();
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x016e A[SYNTHETIC, Splitter:B:102:0x016e] */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x0185 A[SYNTHETIC, Splitter:B:108:0x0185] */
        /* JADX WARNING: Removed duplicated region for block: B:118:0x0151 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00ec A[SYNTHETIC, Splitter:B:57:0x00ec] */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x0100 A[SYNTHETIC, Splitter:B:65:0x0100] */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x0114 A[SYNTHETIC, Splitter:B:73:0x0114] */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x0127 A[SYNTHETIC, Splitter:B:81:0x0127] */
        /* JADX WARNING: Removed duplicated region for block: B:90:0x0145 A[SYNTHETIC, Splitter:B:90:0x0145] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.UploadAvatarTaskResult doInBackground(java.lang.Void... r13) {
            /*
                r12 = this;
                android.content.Context r13 = r12.mContext
                android.accounts.Account r13 = com.xiaomi.passport.utils.AuthenticatorUtil.getXiaomiAccount(r13)
                r0 = 0
                if (r13 != 0) goto L_0x0011
                java.lang.String r13 = "UserAvatarUpdateFragment"
                java.lang.String r1 = "no xiaomi account"
                com.xiaomi.accountsdk.utils.AccountLog.w((java.lang.String) r13, (java.lang.String) r1)
                return r0
            L_0x0011:
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r1 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                java.io.File r1 = r1.getFileProviderFile()
                android.graphics.Bitmap r2 = r12.mBitmap
                if (r2 == 0) goto L_0x002d
                android.graphics.Bitmap r2 = r12.mBitmap     // Catch:{ IOException -> 0x0027 }
                java.lang.String r3 = r1.getAbsolutePath()     // Catch:{ IOException -> 0x0027 }
                com.xiaomi.passport.ui.internal.util.BitmapFactory.saveToFile(r2, r3)     // Catch:{ IOException -> 0x0027 }
                android.graphics.Bitmap r2 = r12.mBitmap     // Catch:{ IOException -> 0x0027 }
                goto L_0x0035
            L_0x0027:
                r2 = move-exception
                r2.printStackTrace()
                r2 = r0
                goto L_0x0035
            L_0x002d:
                java.lang.String r2 = r1.getPath()
                android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeFile(r2)
            L_0x0035:
                if (r2 != 0) goto L_0x003d
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r13 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                r13.deleteFile(r1)
                return r0
            L_0x003d:
                android.content.Context r3 = r12.mContext
                com.xiaomi.accounts.AccountManager r3 = com.xiaomi.accounts.AccountManager.get(r3)
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r4 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                android.app.Activity r4 = r4.getActivity()
                java.lang.String r5 = "passportapi"
                com.xiaomi.passport.data.XMPassportInfo r4 = com.xiaomi.passport.data.XMPassportInfo.build(r4, r5)
                r5 = -1
                r6 = 0
                r5 = r0
                r7 = -1
            L_0x0053:
                r8 = 2
                if (r6 >= r8) goto L_0x019a
                java.lang.String r8 = com.xiaomi.accountsdk.account.XMPassport.uploadXiaomiUserIcon(r4, r2)     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                java.lang.String r9 = "acc_avatar_url"
                r3.setUserData(r13, r9, r8)     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r8 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                android.app.Activity r8 = r8.getActivity()     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r9 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                android.app.Activity r9 = r9.getActivity()     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                android.content.res.Resources r9 = r9.getResources()     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                int r10 = com.xiaomi.passport.ui.R.dimen.passport_head_icon_size     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                int r9 = r9.getDimensionPixelSize(r10)     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                android.graphics.Bitmap r8 = com.xiaomi.passport.ui.internal.util.BitmapFactory.createPhoto(r8, r2, r9)     // Catch:{ IOException -> 0x0162, AuthenticationFailureException -> 0x012d, AccessDeniedException -> 0x011b, InvalidResponseException -> 0x0108, CipherException -> 0x00f4, InvalidParameterException -> 0x00e0 }
                java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00d9, AuthenticationFailureException -> 0x00d4, AccessDeniedException -> 0x00d1, InvalidResponseException -> 0x00ce, CipherException -> 0x00cb, InvalidParameterException -> 0x00c8 }
                r5.<init>(r1)     // Catch:{ IOException -> 0x00d9, AuthenticationFailureException -> 0x00d4, AccessDeniedException -> 0x00d1, InvalidResponseException -> 0x00ce, CipherException -> 0x00cb, InvalidParameterException -> 0x00c8 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                r0.<init>()     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                java.lang.String r9 = "xiaomi_user_avatar_"
                r0.append(r9)     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                java.lang.String r9 = r13.name     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                r0.append(r9)     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r9 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                android.app.Activity r9 = r9.getActivity()     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                com.xiaomi.passport.ui.internal.util.BitmapUtils.saveAsFile(r9, r5, r0)     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                java.lang.String r9 = "acc_avatar_file_name"
                r3.setUserData(r13, r9, r0)     // Catch:{ IOException -> 0x00c5, AuthenticationFailureException -> 0x00c2, AccessDeniedException -> 0x00bf, InvalidResponseException -> 0x00bc, CipherException -> 0x00b9, InvalidParameterException -> 0x00b6 }
                r5.close()     // Catch:{ IOException -> 0x00a3 }
                goto L_0x00ab
            L_0x00a3:
                r13 = move-exception
                java.lang.String r0 = "UserAvatarUpdateFragment"
                java.lang.String r3 = "fileInputStream"
                com.xiaomi.accountsdk.utils.AccountLog.i(r0, r3, r13)
            L_0x00ab:
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r13 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                r13.deleteFile(r1)
                r2.recycle()
                r5 = r8
                goto L_0x019a
            L_0x00b6:
                r13 = move-exception
                r0 = r5
                goto L_0x00c9
            L_0x00b9:
                r13 = move-exception
                r0 = r5
                goto L_0x00cc
            L_0x00bc:
                r13 = move-exception
                r0 = r5
                goto L_0x00cf
            L_0x00bf:
                r13 = move-exception
                r0 = r5
                goto L_0x00d2
            L_0x00c2:
                r0 = move-exception
                goto L_0x0131
            L_0x00c5:
                r13 = move-exception
                r0 = r5
                goto L_0x00da
            L_0x00c8:
                r13 = move-exception
            L_0x00c9:
                r5 = r8
                goto L_0x00e1
            L_0x00cb:
                r13 = move-exception
            L_0x00cc:
                r5 = r8
                goto L_0x00f5
            L_0x00ce:
                r13 = move-exception
            L_0x00cf:
                r5 = r8
                goto L_0x0109
            L_0x00d1:
                r13 = move-exception
            L_0x00d2:
                r5 = r8
                goto L_0x011c
            L_0x00d4:
                r5 = move-exception
                r11 = r5
                r5 = r0
                r0 = r11
                goto L_0x0131
            L_0x00d9:
                r13 = move-exception
            L_0x00da:
                r5 = r8
                goto L_0x0163
            L_0x00dd:
                r13 = move-exception
                goto L_0x0183
            L_0x00e0:
                r13 = move-exception
            L_0x00e1:
                java.lang.String r3 = "UserAvatarUpdateFragment"
                java.lang.String r4 = "uploadInfoToServer error"
                com.xiaomi.accountsdk.utils.AccountLog.e(r3, r4, r13)     // Catch:{ all -> 0x00dd }
                int r7 = com.xiaomi.passport.ui.R.string.account_invalid_user_avatar     // Catch:{ all -> 0x00dd }
                if (r0 == 0) goto L_0x017a
                r0.close()     // Catch:{ IOException -> 0x00f1 }
                goto L_0x017a
            L_0x00f1:
                r13 = move-exception
                goto L_0x0173
            L_0x00f4:
                r13 = move-exception
            L_0x00f5:
                java.lang.String r3 = "UserAvatarUpdateFragment"
                java.lang.String r4 = "uploadInfoToServer error"
                com.xiaomi.accountsdk.utils.AccountLog.e(r3, r4, r13)     // Catch:{ all -> 0x00dd }
                int r7 = com.xiaomi.passport.ui.R.string.passport_error_server     // Catch:{ all -> 0x00dd }
                if (r0 == 0) goto L_0x017a
                r0.close()     // Catch:{ IOException -> 0x0105 }
                goto L_0x017a
            L_0x0105:
                r13 = move-exception
                goto L_0x0173
            L_0x0108:
                r13 = move-exception
            L_0x0109:
                java.lang.String r3 = "UserAvatarUpdateFragment"
                java.lang.String r4 = "uploadInfoToServer error"
                com.xiaomi.accountsdk.utils.AccountLog.e(r3, r4, r13)     // Catch:{ all -> 0x00dd }
                int r7 = com.xiaomi.passport.ui.R.string.passport_error_server     // Catch:{ all -> 0x00dd }
                if (r0 == 0) goto L_0x017a
                r0.close()     // Catch:{ IOException -> 0x0119 }
                goto L_0x017a
            L_0x0119:
                r13 = move-exception
                goto L_0x0173
            L_0x011b:
                r13 = move-exception
            L_0x011c:
                java.lang.String r3 = "UserAvatarUpdateFragment"
                java.lang.String r4 = "uploadInfoToServer error"
                com.xiaomi.accountsdk.utils.AccountLog.e(r3, r4, r13)     // Catch:{ all -> 0x00dd }
                int r7 = com.xiaomi.passport.ui.R.string.passport_access_denied     // Catch:{ all -> 0x00dd }
                if (r0 == 0) goto L_0x017a
                r0.close()     // Catch:{ IOException -> 0x012b }
                goto L_0x017a
            L_0x012b:
                r13 = move-exception
                goto L_0x0173
            L_0x012d:
                r7 = move-exception
                r8 = r5
                r5 = r0
                r0 = r7
            L_0x0131:
                java.lang.String r7 = "UserAvatarUpdateFragment"
                java.lang.String r9 = "uploadInfoToServer error"
                com.xiaomi.accountsdk.utils.AccountLog.e(r7, r9, r0)     // Catch:{ all -> 0x015f }
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r0 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this     // Catch:{ all -> 0x015f }
                android.app.Activity r0 = r0.getActivity()     // Catch:{ all -> 0x015f }
                r4.refreshAuthToken(r0)     // Catch:{ all -> 0x015f }
                int r7 = com.xiaomi.passport.ui.R.string.passport_bad_authentication     // Catch:{ all -> 0x015f }
                if (r5 == 0) goto L_0x0151
                r5.close()     // Catch:{ IOException -> 0x0149 }
                goto L_0x0151
            L_0x0149:
                r0 = move-exception
                java.lang.String r9 = "UserAvatarUpdateFragment"
                java.lang.String r10 = "fileInputStream"
                com.xiaomi.accountsdk.utils.AccountLog.i(r9, r10, r0)
            L_0x0151:
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r0 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                r0.deleteFile(r1)
                r2.recycle()
                int r6 = r6 + 1
                r0 = r5
                r5 = r8
                goto L_0x0053
            L_0x015f:
                r13 = move-exception
                r0 = r5
                goto L_0x0183
            L_0x0162:
                r13 = move-exception
            L_0x0163:
                java.lang.String r3 = "UserAvatarUpdateFragment"
                java.lang.String r4 = "uploadInfoToServer error"
                com.xiaomi.accountsdk.utils.AccountLog.e(r3, r4, r13)     // Catch:{ all -> 0x00dd }
                int r7 = com.xiaomi.passport.ui.R.string.passport_error_network     // Catch:{ all -> 0x00dd }
                if (r0 == 0) goto L_0x017a
                r0.close()     // Catch:{ IOException -> 0x0172 }
                goto L_0x017a
            L_0x0172:
                r13 = move-exception
            L_0x0173:
                java.lang.String r0 = "UserAvatarUpdateFragment"
                java.lang.String r3 = "fileInputStream"
                com.xiaomi.accountsdk.utils.AccountLog.i(r0, r3, r13)
            L_0x017a:
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r13 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                r13.deleteFile(r1)
                r2.recycle()
                goto L_0x019a
            L_0x0183:
                if (r0 == 0) goto L_0x0191
                r0.close()     // Catch:{ IOException -> 0x0189 }
                goto L_0x0191
            L_0x0189:
                r0 = move-exception
                java.lang.String r3 = "UserAvatarUpdateFragment"
                java.lang.String r4 = "fileInputStream"
                com.xiaomi.accountsdk.utils.AccountLog.i(r3, r4, r0)
            L_0x0191:
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r0 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                r0.deleteFile(r1)
                r2.recycle()
                throw r13
            L_0x019a:
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment$UploadAvatarTaskResult r13 = new com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment$UploadAvatarTaskResult
                com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment r0 = com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.this
                r13.<init>(r7, r5)
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment.UploadUserAvatarTask.doInBackground(java.lang.Void[]):com.xiaomi.passport.ui.settings.UserAvatarUpdateFragment$UploadAvatarTaskResult");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(UploadAvatarTaskResult uploadAvatarTaskResult) {
            super.onPostExecute(uploadAvatarTaskResult);
            UserAvatarUpdateFragment.this.finishActivity();
            this.mProgressDialog.dismiss();
            if (uploadAvatarTaskResult != null && uploadAvatarTaskResult.bitmap == null) {
                Toast.makeText(this.mContext, uploadAvatarTaskResult.errorMsgResId == -1 ? R.string.passport_error_unknown : uploadAvatarTaskResult.errorMsgResId, 0).show();
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(UploadAvatarTaskResult uploadAvatarTaskResult) {
            if (!(uploadAvatarTaskResult == null || uploadAvatarTaskResult.bitmap == null)) {
                uploadAvatarTaskResult.bitmap.recycle();
            }
            super.onCancelled(uploadAvatarTaskResult);
        }
    }

    /* access modifiers changed from: private */
    public void finishActivity() {
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();
    }

    private class UploadAvatarTaskResult {
        public Bitmap bitmap;
        int errorMsgResId;

        UploadAvatarTaskResult(int i, Bitmap bitmap2) {
            this.errorMsgResId = i;
            this.bitmap = bitmap2;
        }
    }

    /* access modifiers changed from: private */
    public void deleteFile(File file) {
        if (file != null && file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
