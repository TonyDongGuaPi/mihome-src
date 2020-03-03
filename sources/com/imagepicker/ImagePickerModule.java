package com.imagepicker;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import android.webkit.MimeTypeMap;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.imagepicker.media.ImageConfig;
import com.imagepicker.permissions.OnImagePickerPermissionsCallback;
import com.imagepicker.permissions.PermissionUtils;
import com.imagepicker.utils.MediaUtils;
import com.imagepicker.utils.RealPathUtil;
import com.imagepicker.utils.UI;
import com.xiaomi.youpin.share.ShareObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class ImagePickerModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final int REQUEST_LAUNCH_IMAGE_CAPTURE = 13001;
    public static final int REQUEST_LAUNCH_IMAGE_LIBRARY = 13002;
    public static final int REQUEST_LAUNCH_VIDEO_CAPTURE = 13004;
    public static final int REQUEST_LAUNCH_VIDEO_LIBRARY = 13003;
    public static final int REQUEST_PERMISSIONS_FOR_CAMERA = 14001;
    public static final int REQUEST_PERMISSIONS_FOR_LIBRARY = 14002;
    protected Callback callback;
    protected Uri cameraCaptureURI;
    private final int dialogThemeId;
    private ImageConfig imageConfig = new ImageConfig((File) null, (File) null, 0, 0, 100, 0, false);
    private PermissionListener listener = new PermissionListener() {
        public boolean onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
            boolean z = true;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                z = z && (iArr[i2] == 0);
            }
            if (ImagePickerModule.this.callback == null || ImagePickerModule.this.options == null) {
                return false;
            }
            if (!z) {
                ImagePickerModule.this.responseHelper.b(ImagePickerModule.this.callback, "Permissions weren't granted");
                return false;
            }
            switch (i) {
                case ImagePickerModule.REQUEST_PERMISSIONS_FOR_CAMERA /*14001*/:
                    ImagePickerModule.this.launchCamera(ImagePickerModule.this.options, ImagePickerModule.this.callback);
                    break;
                case ImagePickerModule.REQUEST_PERMISSIONS_FOR_LIBRARY /*14002*/:
                    ImagePickerModule.this.launchImageLibrary(ImagePickerModule.this.options, ImagePickerModule.this.callback);
                    break;
            }
            return true;
        }
    };
    private Boolean noData = false;
    /* access modifiers changed from: private */
    public ReadableMap options;
    private Boolean pickVideo = false;
    private final ReactApplicationContext reactContext;
    /* access modifiers changed from: private */
    public ResponseHelper responseHelper = new ResponseHelper();
    @Deprecated
    private int videoDurationLimit = 0;
    @Deprecated
    private int videoQuality = 1;

    public String getName() {
        return "ImagePickerManager";
    }

    public void onNewIntent(Intent intent) {
    }

    public ImagePickerModule(ReactApplicationContext reactApplicationContext, @StyleRes int i) {
        super(reactApplicationContext);
        this.dialogThemeId = i;
        this.reactContext = reactApplicationContext;
        this.reactContext.addActivityEventListener(this);
    }

    @ReactMethod
    public void showImagePicker(ReadableMap readableMap, Callback callback2) {
        if (getCurrentActivity() == null) {
            this.responseHelper.b(callback2, "can't find current Activity");
            return;
        }
        this.callback = callback2;
        this.options = readableMap;
        this.imageConfig = new ImageConfig((File) null, (File) null, 0, 0, 100, 0, false);
        UI.a(this, readableMap, new UI.OnAction() {
            public void a(@NonNull ImagePickerModule imagePickerModule) {
                if (imagePickerModule != null) {
                    imagePickerModule.launchCamera();
                }
            }

            public void b(@NonNull ImagePickerModule imagePickerModule) {
                if (imagePickerModule != null) {
                    imagePickerModule.launchImageLibrary();
                }
            }

            public void c(@NonNull ImagePickerModule imagePickerModule) {
                if (imagePickerModule != null) {
                    imagePickerModule.doOnCancel();
                }
            }

            public void a(@NonNull ImagePickerModule imagePickerModule, @NonNull String str) {
                if (imagePickerModule != null) {
                    imagePickerModule.invokeCustomButton(str);
                }
            }
        }).show();
    }

    public void doOnCancel() {
        this.responseHelper.a(this.callback);
    }

    public void launchCamera() {
        launchCamera(this.options, this.callback);
    }

    @ReactMethod
    public void launchCamera(ReadableMap readableMap, Callback callback2) {
        int i;
        Intent intent;
        if (!isCameraAvailable()) {
            this.responseHelper.b(callback2, "Camera not available");
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.responseHelper.b(callback2, "can't find current Activity");
            return;
        }
        this.options = readableMap;
        if (permissionsCheck(currentActivity, callback2, REQUEST_PERMISSIONS_FOR_CAMERA)) {
            parseOptions(this.options);
            if (this.pickVideo.booleanValue()) {
                i = REQUEST_LAUNCH_VIDEO_CAPTURE;
                intent = new Intent("android.media.action.VIDEO_CAPTURE");
                intent.putExtra("android.intent.extra.videoQuality", this.videoQuality);
                if (this.videoDurationLimit > 0) {
                    intent.putExtra("android.intent.extra.durationLimit", this.videoDurationLimit);
                }
            } else {
                i = REQUEST_LAUNCH_IMAGE_CAPTURE;
                intent = new Intent("android.media.action.IMAGE_CAPTURE");
                this.imageConfig = this.imageConfig.a(MediaUtils.a(this.reactContext, this.options, false));
                if (this.imageConfig.f6065a != null) {
                    this.cameraCaptureURI = RealPathUtil.a((Context) this.reactContext, this.imageConfig.f6065a);
                    if (this.cameraCaptureURI == null) {
                        this.responseHelper.b(callback2, "Couldn't get file path for photo");
                        return;
                    }
                    intent.putExtra(AgentOptions.k, this.cameraCaptureURI);
                } else {
                    this.responseHelper.b(callback2, "Couldn't get file path for photo");
                    return;
                }
            }
            if (intent.resolveActivity(this.reactContext.getPackageManager()) == null) {
                this.responseHelper.b(callback2, "Cannot launch camera");
                return;
            }
            this.callback = callback2;
            if (Build.VERSION.SDK_INT <= 19) {
                for (ResolveInfo resolveInfo : this.reactContext.getPackageManager().queryIntentActivities(intent, 65536)) {
                    this.reactContext.grantUriPermission(resolveInfo.activityInfo.packageName, this.cameraCaptureURI, 3);
                }
            }
            try {
                currentActivity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                this.responseHelper.b(callback2, "Cannot launch camera");
            }
        }
    }

    public void launchImageLibrary() {
        launchImageLibrary(this.options, this.callback);
    }

    @ReactMethod
    public void launchImageLibrary(ReadableMap readableMap, Callback callback2) {
        int i;
        Intent intent;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.responseHelper.b(callback2, "can't find current Activity");
            return;
        }
        this.options = readableMap;
        if (permissionsCheck(currentActivity, callback2, REQUEST_PERMISSIONS_FOR_LIBRARY)) {
            parseOptions(this.options);
            if (this.pickVideo.booleanValue()) {
                i = REQUEST_LAUNCH_VIDEO_LIBRARY;
                intent = new Intent("android.intent.action.PICK");
                intent.setType(ShareObject.e);
            } else {
                i = REQUEST_LAUNCH_IMAGE_LIBRARY;
                intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
            if (intent.resolveActivity(this.reactContext.getPackageManager()) == null) {
                this.responseHelper.b(callback2, "Cannot launch photo library");
                return;
            }
            this.callback = callback2;
            try {
                currentActivity.startActivityForResult(intent, i);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                this.responseHelper.b(callback2, "Cannot launch photo library");
            }
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        Uri uri;
        if (!passResult(i)) {
            this.responseHelper.a();
            if (i2 != -1) {
                MediaUtils.a(i, this.imageConfig);
                this.responseHelper.a(this.callback);
                this.callback = null;
                return;
            }
            switch (i) {
                case REQUEST_LAUNCH_IMAGE_CAPTURE /*13001*/:
                    uri = this.cameraCaptureURI;
                    break;
                case REQUEST_LAUNCH_IMAGE_LIBRARY /*13002*/:
                    uri = intent.getData();
                    String realPathFromURI = getRealPathFromURI(uri);
                    boolean z = !TextUtils.isEmpty(realPathFromURI) && Patterns.WEB_URL.matcher(realPathFromURI).matches();
                    if (realPathFromURI == null || z) {
                        try {
                            File createFileFromURI = createFileFromURI(uri);
                            String absolutePath = createFileFromURI.getAbsolutePath();
                            uri = Uri.fromFile(createFileFromURI);
                            realPathFromURI = absolutePath;
                        } catch (Exception unused) {
                            this.responseHelper.a("error", "Could not read photo");
                            this.responseHelper.a("uri", uri.toString());
                            this.responseHelper.b(this.callback);
                            this.callback = null;
                            return;
                        }
                    }
                    this.imageConfig = this.imageConfig.a(new File(realPathFromURI));
                    break;
                case REQUEST_LAUNCH_VIDEO_LIBRARY /*13003*/:
                    this.responseHelper.a("uri", intent.getData().toString());
                    this.responseHelper.a("path", getRealPathFromURI(intent.getData()));
                    this.responseHelper.b(this.callback);
                    this.callback = null;
                    return;
                case REQUEST_LAUNCH_VIDEO_CAPTURE /*13004*/:
                    String realPathFromURI2 = getRealPathFromURI(intent.getData());
                    this.responseHelper.a("uri", intent.getData().toString());
                    this.responseHelper.a("path", realPathFromURI2);
                    MediaUtils.a((Context) this.reactContext, realPathFromURI2);
                    this.responseHelper.b(this.callback);
                    this.callback = null;
                    return;
                default:
                    uri = null;
                    break;
            }
            MediaUtils.ReadExifResult a2 = MediaUtils.a(this.responseHelper, this.imageConfig);
            if (a2.b != null) {
                MediaUtils.a(i, this.imageConfig);
                this.responseHelper.b(this.callback, a2.b.getMessage());
                this.callback = null;
                return;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.imageConfig.f6065a.getAbsolutePath(), options2);
            int i3 = options2.outWidth;
            int i4 = options2.outHeight;
            updatedResultResponse(uri, this.imageConfig.f6065a.getAbsolutePath());
            if (this.imageConfig.a(i3, i4, a2.f6070a)) {
                this.responseHelper.a("width", i3);
                this.responseHelper.a("height", i4);
                MediaUtils.a((Context) this.reactContext, this.imageConfig.f6065a.getAbsolutePath());
            } else {
                this.imageConfig = MediaUtils.a(this.reactContext, this.options, this.imageConfig, i3, i4, i);
                if (this.imageConfig.b == null) {
                    MediaUtils.a(i, this.imageConfig);
                    this.responseHelper.a("error", "Can't resize the image");
                } else {
                    Uri fromFile = Uri.fromFile(this.imageConfig.b);
                    BitmapFactory.decodeFile(this.imageConfig.b.getAbsolutePath(), options2);
                    this.responseHelper.a("width", options2.outWidth);
                    this.responseHelper.a("height", options2.outHeight);
                    updatedResultResponse(fromFile, this.imageConfig.b.getAbsolutePath());
                    MediaUtils.a((Context) this.reactContext, this.imageConfig.b.getAbsolutePath());
                }
            }
            if (this.imageConfig.g && i == 13001) {
                MediaUtils.RolloutPhotoResult a3 = MediaUtils.a(this.imageConfig);
                if (a3.b == null) {
                    this.imageConfig = a3.f6071a;
                    updatedResultResponse(Uri.fromFile(this.imageConfig.a()), this.imageConfig.a().getAbsolutePath());
                } else {
                    MediaUtils.a(i, this.imageConfig);
                    this.responseHelper.a("error", "Error moving image to camera roll: " + a3.b.getMessage());
                    return;
                }
            }
            this.responseHelper.b(this.callback);
            this.callback = null;
            this.options = null;
        }
    }

    public void invokeCustomButton(@NonNull String str) {
        this.responseHelper.a(this.callback, str);
    }

    public Context getContext() {
        return getReactApplicationContext();
    }

    @StyleRes
    public int getDialogThemeId() {
        return this.dialogThemeId;
    }

    @NonNull
    public Activity getActivity() {
        return getCurrentActivity();
    }

    private boolean passResult(int i) {
        return this.callback == null || (this.cameraCaptureURI == null && i == 13001) || !(i == 13001 || i == 13002 || i == 13003 || i == 13004);
    }

    private void updatedResultResponse(@Nullable Uri uri, @NonNull String str) {
        this.responseHelper.a("uri", uri.toString());
        this.responseHelper.a("path", str);
        if (!this.noData.booleanValue()) {
            this.responseHelper.a("data", getBase64StringFromFile(str));
        }
        putExtraFileInfo(str, this.responseHelper);
    }

    private boolean permissionsCheck(@NonNull Activity activity, @NonNull Callback callback2, @NonNull int i) {
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ActivityCompat.checkSelfPermission(activity, "android.permission.CAMERA") == 0) {
            return true;
        }
        if (Boolean.valueOf(ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.WRITE_EXTERNAL_STORAGE") && ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.CAMERA")).booleanValue()) {
            AlertDialog a2 = PermissionUtils.a(this, this.options, new PermissionUtils.OnExplainingPermissionCallback() {
                public void a(WeakReference<ImagePickerModule> weakReference, DialogInterface dialogInterface) {
                    ImagePickerModule imagePickerModule = (ImagePickerModule) weakReference.get();
                    if (imagePickerModule != null) {
                        imagePickerModule.doOnCancel();
                    }
                }

                public void b(WeakReference<ImagePickerModule> weakReference, DialogInterface dialogInterface) {
                    ImagePickerModule imagePickerModule = (ImagePickerModule) weakReference.get();
                    if (imagePickerModule != null) {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", imagePickerModule.getContext().getPackageName(), (String) null));
                        Activity activity = imagePickerModule.getActivity();
                        if (activity != null) {
                            activity.startActivityForResult(intent, 1);
                        }
                    }
                }
            });
            if (a2 != null) {
                a2.show();
            }
            return false;
        }
        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};
        if (activity instanceof ReactActivity) {
            ((ReactActivity) activity).requestPermissions(strArr, i, this.listener);
        } else if (activity instanceof PermissionAwareActivity) {
            ((PermissionAwareActivity) activity).requestPermissions(strArr, i, this.listener);
        } else if (activity instanceof OnImagePickerPermissionsCallback) {
            ((OnImagePickerPermissionsCallback) activity).setPermissionListener(this.listener);
            ActivityCompat.requestPermissions(activity, strArr, i);
        } else {
            throw new UnsupportedOperationException(activity.getClass().getSimpleName() + " must implement " + OnImagePickerPermissionsCallback.class.getSimpleName() + " or " + PermissionAwareActivity.class.getSimpleName());
        }
        return false;
    }

    private boolean isCameraAvailable() {
        return this.reactContext.getPackageManager().hasSystemFeature("android.hardware.camera") || this.reactContext.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    @NonNull
    private String getRealPathFromURI(@NonNull Uri uri) {
        return RealPathUtil.a((Context) this.reactContext, uri);
    }

    private File createFileFromURI(Uri uri) throws Exception {
        File externalCacheDir = this.reactContext.getExternalCacheDir();
        File file = new File(externalCacheDir, "photo-" + uri.getLastPathSegment());
        InputStream openInputStream = this.reactContext.getContentResolver().openInputStream(uri);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    return file;
                }
            }
        } finally {
            fileOutputStream.close();
            openInputStream.close();
        }
    }

    private String getBase64StringFromFile(String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(new File(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    private void putExtraFileInfo(@NonNull String str, @NonNull ResponseHelper responseHelper2) {
        try {
            File file = new File(str);
            responseHelper2.a("fileSize", (double) file.length());
            responseHelper2.a("fileName", file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            responseHelper2.a("type", MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl));
        }
    }

    private void parseOptions(ReadableMap readableMap) {
        this.noData = false;
        if (readableMap.hasKey("noData")) {
            this.noData = Boolean.valueOf(readableMap.getBoolean("noData"));
        }
        this.imageConfig = this.imageConfig.a(readableMap);
        this.pickVideo = false;
        if (readableMap.hasKey("mediaType") && readableMap.getString("mediaType").equals("video")) {
            this.pickVideo = true;
        }
        this.videoQuality = 1;
        if (readableMap.hasKey("videoQuality") && readableMap.getString("videoQuality").equals("low")) {
            this.videoQuality = 0;
        }
        this.videoDurationLimit = 0;
        if (readableMap.hasKey("durationLimit")) {
            this.videoDurationLimit = readableMap.getInt("durationLimit");
        }
    }
}
