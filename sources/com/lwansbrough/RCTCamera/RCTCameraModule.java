package com.lwansbrough.RCTCamera;

import android.content.ContentValues;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaActionSound;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.media.ExifInterface;
import android.util.Log;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.android.exoplayer2.util.MimeTypes;
import com.taobao.weex.common.Constants;
import com.xiaomi.miot.support.monitor.core.tasks.MiotApmTask;
import com.xiaomi.smarthome.download.Downloads;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class RCTCameraModule extends ReactContextBaseJavaModule implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener, LifecycleEventListener {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int RCT_CAMERA_ASPECT_FILL = 0;
    public static final int RCT_CAMERA_ASPECT_FIT = 1;
    public static final int RCT_CAMERA_ASPECT_STRETCH = 2;
    public static final int RCT_CAMERA_CAPTURE_MODE_STILL = 0;
    public static final int RCT_CAMERA_CAPTURE_MODE_VIDEO = 1;
    public static final String RCT_CAMERA_CAPTURE_QUALITY_1080P = "1080p";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_480P = "480p";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_720P = "720p";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_HIGH = "high";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_LOW = "low";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_MEDIUM = "medium";
    public static final String RCT_CAMERA_CAPTURE_QUALITY_PREVIEW = "preview";
    public static final int RCT_CAMERA_CAPTURE_TARGET_CAMERA_ROLL = 2;
    public static final int RCT_CAMERA_CAPTURE_TARGET_DISK = 1;
    public static final int RCT_CAMERA_CAPTURE_TARGET_MEMORY = 0;
    public static final int RCT_CAMERA_CAPTURE_TARGET_TEMP = 3;
    public static final int RCT_CAMERA_FLASH_MODE_AUTO = 2;
    public static final int RCT_CAMERA_FLASH_MODE_OFF = 0;
    public static final int RCT_CAMERA_FLASH_MODE_ON = 1;
    public static final int RCT_CAMERA_ORIENTATION_AUTO = Integer.MAX_VALUE;
    public static final int RCT_CAMERA_ORIENTATION_LANDSCAPE_LEFT = 1;
    public static final int RCT_CAMERA_ORIENTATION_LANDSCAPE_RIGHT = 3;
    public static final int RCT_CAMERA_ORIENTATION_PORTRAIT = 0;
    public static final int RCT_CAMERA_ORIENTATION_PORTRAIT_UPSIDE_DOWN = 2;
    public static final int RCT_CAMERA_TORCH_MODE_AUTO = 2;
    public static final int RCT_CAMERA_TORCH_MODE_OFF = 0;
    public static final int RCT_CAMERA_TORCH_MODE_ON = 1;
    public static final int RCT_CAMERA_TYPE_BACK = 2;
    public static final int RCT_CAMERA_TYPE_FRONT = 1;
    private static final String TAG = "RCTCameraModule";
    private long MRStartTime;
    private ReactApplicationContext _reactContext;
    /* access modifiers changed from: private */
    public RCTSensorOrientationChecker _sensorOrientationChecker;
    private Camera mCamera = null;
    private MediaRecorder mMediaRecorder;
    private ReadableMap mRecordingOptions;
    private Promise mRecordingPromise = null;
    /* access modifiers changed from: private */
    public Boolean mSafeToCapture = true;
    private File mVideoFile;

    public String getName() {
        return TAG;
    }

    public void onHostDestroy() {
    }

    public RCTCameraModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this._reactContext = reactApplicationContext;
        this._sensorOrientationChecker = new RCTSensorOrientationChecker(this._reactContext);
        this._reactContext.addLifecycleEventListener(this);
    }

    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        if ((i == 800 || i == 801) && this.mRecordingPromise != null) {
            releaseMediaRecorder();
        }
    }

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        if (this.mRecordingPromise != null) {
            releaseMediaRecorder();
        }
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("Aspect", getAspectConstants());
                put("BarCodeType", getBarCodeConstants());
                put("Type", getTypeConstants());
                put("CaptureQuality", getCaptureQualityConstants());
                put("CaptureMode", getCaptureModeConstants());
                put("CaptureTarget", getCaptureTargetConstants());
                put(ExifInterface.TAG_ORIENTATION, getOrientationConstants());
                put("FlashMode", getFlashModeConstants());
                put("TorchMode", getTorchModeConstants());
            }

            private Map<String, Object> getAspectConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("stretch", 2);
                        put("fit", 1);
                        put("fill", 0);
                    }
                });
            }

            private Map<String, Object> getBarCodeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                });
            }

            private Map<String, Object> getTypeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("front", 1);
                        put("back", 2);
                    }
                });
            }

            private Map<String, Object> getCaptureQualityConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("low", "low");
                        put("medium", "medium");
                        put("high", "high");
                        put("photo", "high");
                        put("preview", "preview");
                        put("480p", "480p");
                        put("720p", "720p");
                        put("1080p", "1080p");
                    }
                });
            }

            private Map<String, Object> getCaptureModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("still", 0);
                        put("video", 1);
                    }
                });
            }

            private Map<String, Object> getCaptureTargetConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put(MiotApmTask.d, 0);
                        put("disk", 1);
                        put("cameraRoll", 2);
                        put("temp", 3);
                    }
                });
            }

            private Map<String, Object> getOrientationConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("auto", Integer.MAX_VALUE);
                        put("landscapeLeft", 1);
                        put("landscapeRight", 3);
                        put("portrait", 0);
                        put("portraitUpsideDown", 2);
                    }
                });
            }

            private Map<String, Object> getFlashModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("off", 0);
                        put("on", 1);
                        put("auto", 2);
                    }
                });
            }

            private Map<String, Object> getTorchModeConstants() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("off", 0);
                        put("on", 1);
                        put("auto", 2);
                    }
                });
            }
        });
    }

    private Throwable prepareMediaRecorder(ReadableMap readableMap, int i) {
        CamcorderProfile captureVideoQuality = RCTCamera.getInstance().setCaptureVideoQuality(readableMap.getInt("type"), readableMap.getString(Constants.Name.QUALITY));
        if (captureVideoQuality == null) {
            return new RuntimeException("CamcorderProfile not found in prepareMediaRecorder.");
        }
        this.mCamera.unlock();
        this.mMediaRecorder = new MediaRecorder();
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setOnErrorListener(this);
        this.mMediaRecorder.setCamera(this.mCamera);
        this.mMediaRecorder.setAudioSource(5);
        this.mMediaRecorder.setVideoSource(1);
        switch (i) {
            case 0:
                this.mMediaRecorder.setOrientationHint(90);
                break;
            case 1:
                this.mMediaRecorder.setOrientationHint(0);
                break;
            case 2:
                this.mMediaRecorder.setOrientationHint(270);
                break;
            case 3:
                this.mMediaRecorder.setOrientationHint(180);
                break;
        }
        captureVideoQuality.fileFormat = 2;
        this.mMediaRecorder.setProfile(captureVideoQuality);
        this.mVideoFile = null;
        int i2 = readableMap.getInt(TouchesHelper.TARGET_KEY);
        if (i2 != 0) {
            switch (i2) {
                case 2:
                    this.mVideoFile = getOutputCameraRollFile(2);
                    break;
                case 3:
                    this.mVideoFile = getTempMediaFile(2);
                    break;
                default:
                    this.mVideoFile = getOutputMediaFile(2);
                    break;
            }
        } else {
            this.mVideoFile = getTempMediaFile(2);
        }
        if (this.mVideoFile == null) {
            return new RuntimeException("Error while preparing output file in prepareMediaRecorder.");
        }
        this.mMediaRecorder.setOutputFile(this.mVideoFile.getPath());
        if (readableMap.hasKey("totalSeconds")) {
            this.mMediaRecorder.setMaxDuration(readableMap.getInt("totalSeconds") * 1000);
        }
        if (readableMap.hasKey("maxFileSize")) {
            this.mMediaRecorder.setMaxFileSize((long) readableMap.getInt("maxFileSize"));
        }
        try {
            this.mMediaRecorder.prepare();
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Media recorder prepare error.", e);
            releaseMediaRecorder();
            return e;
        }
    }

    private void record(ReadableMap readableMap, Promise promise, int i) {
        if (this.mRecordingPromise == null) {
            this.mCamera = RCTCamera.getInstance().acquireCameraInstance(readableMap.getInt("type"));
            if (this.mCamera == null) {
                promise.reject((Throwable) new RuntimeException("No camera found."));
                return;
            }
            Throwable prepareMediaRecorder = prepareMediaRecorder(readableMap, i);
            if (prepareMediaRecorder != null) {
                promise.reject(prepareMediaRecorder);
                return;
            }
            try {
                this.mMediaRecorder.start();
                this.MRStartTime = System.currentTimeMillis();
                this.mRecordingOptions = readableMap;
                this.mRecordingPromise = promise;
            } catch (Exception e) {
                Log.e(TAG, "Media recorder start error.", e);
                promise.reject((Throwable) e);
            }
        }
    }

    private void releaseMediaRecorder() {
        long currentTimeMillis = System.currentTimeMillis() - this.MRStartTime;
        if (currentTimeMillis < com.xiaomi.smarthome.download.Constants.x) {
            try {
                Thread.sleep(com.xiaomi.smarthome.download.Constants.x - currentTimeMillis);
            } catch (InterruptedException e) {
                Log.e(TAG, "releaseMediaRecorder thread sleep error.", e);
            }
        }
        if (this.mMediaRecorder != null) {
            try {
                this.mMediaRecorder.stop();
            } catch (RuntimeException e2) {
                Log.e(TAG, "Media recorder stop error.", e2);
            }
            this.mMediaRecorder.reset();
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
        }
        if (this.mCamera != null) {
            this.mCamera.lock();
        }
        if (this.mRecordingPromise != null) {
            File file = new File(this.mVideoFile.getPath());
            if (!file.exists()) {
                this.mRecordingPromise.reject((Throwable) new RuntimeException("There is nothing recorded."));
                this.mRecordingPromise = null;
                return;
            }
            file.setReadable(true, false);
            file.setWritable(true, false);
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            switch (this.mRecordingOptions.getInt(TouchesHelper.TARGET_KEY)) {
                case 0:
                    writableNativeMap.putString("data", new String(convertFileToByteArray(this.mVideoFile), 2));
                    this.mRecordingPromise.resolve(writableNativeMap);
                    file.delete();
                    break;
                case 1:
                case 3:
                    writableNativeMap.putString("path", Uri.fromFile(this.mVideoFile).toString());
                    this.mRecordingPromise.resolve(writableNativeMap);
                    break;
                case 2:
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(Downloads._DATA, this.mVideoFile.getPath());
                    contentValues.put("title", this.mRecordingOptions.hasKey("title") ? this.mRecordingOptions.getString("title") : "video");
                    if (this.mRecordingOptions.hasKey("description")) {
                        contentValues.put("description", Boolean.valueOf(this.mRecordingOptions.hasKey("description")));
                    }
                    if (this.mRecordingOptions.hasKey("latitude")) {
                        contentValues.put("latitude", this.mRecordingOptions.getString("latitude"));
                    }
                    if (this.mRecordingOptions.hasKey("longitude")) {
                        contentValues.put("longitude", this.mRecordingOptions.getString("longitude"));
                    }
                    contentValues.put("mime_type", MimeTypes.VIDEO_MP4);
                    this._reactContext.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                    addToMediaStore(this.mVideoFile.getAbsolutePath());
                    writableNativeMap.putString("path", Uri.fromFile(this.mVideoFile).toString());
                    this.mRecordingPromise.resolve(writableNativeMap);
                    break;
            }
            this.mRecordingPromise = null;
        }
    }

    public static byte[] convertFileToByteArray(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ReactMethod
    public void capture(final ReadableMap readableMap, final Promise promise) {
        if (RCTCamera.getInstance() == null) {
            promise.reject("Camera is not ready yet.");
            return;
        }
        int i = readableMap.hasKey("orientation") ? readableMap.getInt("orientation") : RCTCamera.getInstance().getOrientation();
        if (i == Integer.MAX_VALUE) {
            this._sensorOrientationChecker.onResume();
            this._sensorOrientationChecker.registerOrientationListener(new RCTSensorOrientationListener() {
                public void orientationEvent() {
                    int orientation = RCTCameraModule.this._sensorOrientationChecker.getOrientation();
                    RCTCameraModule.this._sensorOrientationChecker.unregisterOrientationListener();
                    RCTCameraModule.this._sensorOrientationChecker.onPause();
                    RCTCameraModule.this.captureWithOrientation(readableMap, promise, orientation);
                }
            });
            return;
        }
        captureWithOrientation(readableMap, promise, i);
    }

    /* access modifiers changed from: private */
    public void captureWithOrientation(final ReadableMap readableMap, final Promise promise, int i) {
        final Camera acquireCameraInstance = RCTCamera.getInstance().acquireCameraInstance(readableMap.getInt("type"));
        if (acquireCameraInstance == null) {
            promise.reject("No camera found.");
        } else if (readableMap.getInt("mode") == 1) {
            record(readableMap, promise, i);
        } else {
            RCTCamera.getInstance().setCaptureQuality(readableMap.getInt("type"), readableMap.getString(Constants.Name.QUALITY));
            if (readableMap.hasKey("playSoundOnCapture") && readableMap.getBoolean("playSoundOnCapture")) {
                new MediaActionSound().play(0);
            }
            if (readableMap.hasKey(Constants.Name.QUALITY)) {
                RCTCamera.getInstance().setCaptureQuality(readableMap.getInt("type"), readableMap.getString(Constants.Name.QUALITY));
            }
            RCTCamera.getInstance().adjustCameraRotationToDeviceOrientation(readableMap.getInt("type"), i);
            acquireCameraInstance.setPreviewCallback((Camera.PreviewCallback) null);
            AnonymousClass3 r1 = new Camera.PictureCallback() {
                public void onPictureTaken(final byte[] bArr, Camera camera) {
                    camera.stopPreview();
                    camera.startPreview();
                    AsyncTask.execute(new Runnable() {
                        public void run() {
                            RCTCameraModule.this.processImage(new MutableImage(bArr), readableMap, promise);
                        }
                    });
                    Boolean unused = RCTCameraModule.this.mSafeToCapture = true;
                }
            };
            AnonymousClass4 r6 = new Camera.ShutterCallback() {
                public void onShutter() {
                    try {
                        acquireCameraInstance.setPreviewCallback((Camera.PreviewCallback) null);
                        acquireCameraInstance.setPreviewTexture((SurfaceTexture) null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            if (this.mSafeToCapture.booleanValue()) {
                try {
                    acquireCameraInstance.takePicture(r6, (Camera.PictureCallback) null, r1);
                    this.mSafeToCapture = false;
                } catch (RuntimeException e) {
                    Log.e(TAG, "Couldn't capture photo.", e);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0195, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void processImage(com.lwansbrough.RCTCamera.MutableImage r13, com.facebook.react.bridge.ReadableMap r14, com.facebook.react.bridge.Promise r15) {
        /*
            r12 = this;
            monitor-enter(r12)
            java.lang.String r0 = "fixOrientation"
            boolean r0 = r14.hasKey(r0)     // Catch:{ all -> 0x0196 }
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0015
            java.lang.String r0 = "fixOrientation"
            boolean r0 = r14.getBoolean(r0)     // Catch:{ all -> 0x0196 }
            if (r0 == 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 == 0) goto L_0x0022
            r13.fixOrientation()     // Catch:{ ImageMutationFailedException -> 0x001c }
            goto L_0x0022
        L_0x001c:
            r0 = move-exception
            java.lang.String r3 = "Error fixing orientation image"
            r15.reject((java.lang.String) r3, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0196 }
        L_0x0022:
            int r0 = r13.getWidth()     // Catch:{ all -> 0x0196 }
            double r3 = (double) r0     // Catch:{ all -> 0x0196 }
            int r0 = r13.getHeight()     // Catch:{ all -> 0x0196 }
            double r5 = (double) r0
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r5)
            double r3 = r3 / r5
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.String r0 = "type"
            int r0 = r14.getInt(r0)     // Catch:{ IllegalArgumentException -> 0x006a }
            com.lwansbrough.RCTCamera.RCTCamera r7 = com.lwansbrough.RCTCamera.RCTCamera.getInstance()     // Catch:{ IllegalArgumentException -> 0x006a }
            int r7 = r7.getPreviewVisibleWidth(r0)     // Catch:{ IllegalArgumentException -> 0x006a }
            double r7 = (double) r7     // Catch:{ IllegalArgumentException -> 0x006a }
            com.lwansbrough.RCTCamera.RCTCamera r9 = com.lwansbrough.RCTCamera.RCTCamera.getInstance()     // Catch:{ IllegalArgumentException -> 0x006a }
            int r0 = r9.getPreviewVisibleHeight(r0)     // Catch:{ IllegalArgumentException -> 0x006a }
            double r9 = (double) r0
            java.lang.Double.isNaN(r7)
            java.lang.Double.isNaN(r9)
            double r7 = r7 / r9
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x005b
            r0 = 1
            goto L_0x005c
        L_0x005b:
            r0 = 0
        L_0x005c:
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 <= 0) goto L_0x0062
            r3 = 1
            goto L_0x0063
        L_0x0062:
            r3 = 0
        L_0x0063:
            if (r0 == r3) goto L_0x0067
            r0 = 1
            goto L_0x0068
        L_0x0067:
            r0 = 0
        L_0x0068:
            r3 = r7
            goto L_0x006b
        L_0x006a:
            r0 = 0
        L_0x006b:
            java.lang.String r7 = "cropToPreview"
            boolean r7 = r14.hasKey(r7)     // Catch:{ all -> 0x0196 }
            if (r7 == 0) goto L_0x007d
            java.lang.String r7 = "cropToPreview"
            boolean r7 = r14.getBoolean(r7)     // Catch:{ all -> 0x0196 }
            if (r7 == 0) goto L_0x007d
            r7 = 1
            goto L_0x007e
        L_0x007d:
            r7 = 0
        L_0x007e:
            if (r7 == 0) goto L_0x008e
            if (r0 == 0) goto L_0x0084
            double r3 = r5 / r3
        L_0x0084:
            r13.cropToPreview(r3)     // Catch:{ IllegalArgumentException -> 0x0088 }
            goto L_0x008e
        L_0x0088:
            r3 = move-exception
            java.lang.String r4 = "Error cropping image to preview"
            r15.reject((java.lang.String) r4, (java.lang.Throwable) r3)     // Catch:{ all -> 0x0196 }
        L_0x008e:
            java.lang.String r3 = "mirrorImage"
            boolean r3 = r14.hasKey(r3)     // Catch:{ all -> 0x0196 }
            if (r3 == 0) goto L_0x00a0
            java.lang.String r3 = "mirrorImage"
            boolean r3 = r14.getBoolean(r3)     // Catch:{ all -> 0x0196 }
            if (r3 == 0) goto L_0x00a0
            r3 = 1
            goto L_0x00a1
        L_0x00a0:
            r3 = 0
        L_0x00a1:
            if (r3 == 0) goto L_0x00ad
            r13.mirrorImage()     // Catch:{ ImageMutationFailedException -> 0x00a7 }
            goto L_0x00ad
        L_0x00a7:
            r3 = move-exception
            java.lang.String r4 = "Error mirroring image"
            r15.reject((java.lang.String) r4, (java.lang.Throwable) r3)     // Catch:{ all -> 0x0196 }
        L_0x00ad:
            r3 = 80
            java.lang.String r4 = "jpegQuality"
            boolean r4 = r14.hasKey(r4)     // Catch:{ all -> 0x0196 }
            if (r4 == 0) goto L_0x00bd
            java.lang.String r3 = "jpegQuality"
            int r3 = r14.getInt(r3)     // Catch:{ all -> 0x0196 }
        L_0x00bd:
            java.lang.String r4 = "targetWidth"
            boolean r4 = r14.hasKey(r4)     // Catch:{ all -> 0x0196 }
            if (r4 == 0) goto L_0x00ce
            java.lang.String r4 = "targetWidth"
            int r4 = r14.getInt(r4)     // Catch:{ all -> 0x0196 }
            goto L_0x00cf
        L_0x00ce:
            r4 = 0
        L_0x00cf:
            java.lang.String r5 = "targetHeight"
            boolean r5 = r14.hasKey(r5)     // Catch:{ all -> 0x0196 }
            if (r5 == 0) goto L_0x00e1
            java.lang.String r2 = "targetHeight"
            int r2 = r14.getInt(r2)     // Catch:{ all -> 0x0196 }
            r5 = r2
            goto L_0x00e2
        L_0x00e1:
            r5 = 0
        L_0x00e2:
            if (r0 == 0) goto L_0x00e9
            int r2 = r13.getHeight()     // Catch:{ all -> 0x0196 }
            goto L_0x00ed
        L_0x00e9:
            int r2 = r13.getWidth()     // Catch:{ all -> 0x0196 }
        L_0x00ed:
            r8 = r2
            if (r0 == 0) goto L_0x00f5
            int r0 = r13.getWidth()     // Catch:{ all -> 0x0196 }
            goto L_0x00f9
        L_0x00f5:
            int r0 = r13.getHeight()     // Catch:{ all -> 0x0196 }
        L_0x00f9:
            r9 = r0
            java.lang.String r0 = "target"
            int r0 = r14.getInt(r0)     // Catch:{ all -> 0x0196 }
            switch(r0) {
                case 0: goto L_0x0178;
                case 1: goto L_0x0154;
                case 2: goto L_0x012b;
                case 3: goto L_0x0106;
                default: goto L_0x0104;
            }     // Catch:{ all -> 0x0196 }
        L_0x0104:
            goto L_0x0194
        L_0x0106:
            java.io.File r7 = r12.getTempMediaFile(r1)     // Catch:{ all -> 0x0196 }
            if (r7 != 0) goto L_0x0113
            java.lang.String r13 = "Error creating media file."
            r15.reject((java.lang.String) r13)     // Catch:{ all -> 0x0196 }
            monitor-exit(r12)
            return
        L_0x0113:
            r3 = 85
            r0 = r13
            r1 = r7
            r2 = r14
            r0.writeDataToFile(r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x0123 }
            r11 = 0
            r6 = r12
            r10 = r15
            r6.resolveImage(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0196 }
            goto L_0x0194
        L_0x0123:
            r13 = move-exception
            java.lang.String r14 = "failed to save image file"
            r15.reject((java.lang.String) r14, (java.lang.Throwable) r13)     // Catch:{ all -> 0x0196 }
            monitor-exit(r12)
            return
        L_0x012b:
            java.io.File r7 = r12.getOutputCameraRollFile(r1)     // Catch:{ all -> 0x0196 }
            if (r7 != 0) goto L_0x0138
            java.lang.String r13 = "Error creating media file."
            r15.reject((java.lang.String) r13)     // Catch:{ all -> 0x0196 }
            monitor-exit(r12)
            return
        L_0x0138:
            r0 = r13
            r1 = r7
            r2 = r14
            r0.writeDataToFile(r1, r2, r3, r4, r5)     // Catch:{ IOException | NullPointerException -> 0x014c }
            java.lang.String r13 = r7.getAbsolutePath()     // Catch:{ all -> 0x0196 }
            r12.addToMediaStore(r13)     // Catch:{ all -> 0x0196 }
            r11 = 1
            r6 = r12
            r10 = r15
            r6.resolveImage(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0196 }
            goto L_0x0194
        L_0x014c:
            r13 = move-exception
            java.lang.String r14 = "failed to save image file"
            r15.reject((java.lang.String) r14, (java.lang.Throwable) r13)     // Catch:{ all -> 0x0196 }
            monitor-exit(r12)
            return
        L_0x0154:
            java.io.File r7 = r12.getOutputMediaFile(r1)     // Catch:{ all -> 0x0196 }
            if (r7 != 0) goto L_0x0161
            java.lang.String r13 = "Error creating media file."
            r15.reject((java.lang.String) r13)     // Catch:{ all -> 0x0196 }
            monitor-exit(r12)
            return
        L_0x0161:
            r3 = 85
            r0 = r13
            r1 = r7
            r2 = r14
            r0.writeDataToFile(r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x0170 }
            r11 = 0
            r6 = r12
            r10 = r15
            r6.resolveImage(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0196 }
            goto L_0x0194
        L_0x0170:
            r13 = move-exception
            java.lang.String r14 = "failed to save image file"
            r15.reject((java.lang.String) r14, (java.lang.Throwable) r13)     // Catch:{ all -> 0x0196 }
            monitor-exit(r12)
            return
        L_0x0178:
            java.lang.String r13 = r13.toBase64(r3, r4, r5)     // Catch:{ all -> 0x0196 }
            com.facebook.react.bridge.WritableNativeMap r14 = new com.facebook.react.bridge.WritableNativeMap     // Catch:{ all -> 0x0196 }
            r14.<init>()     // Catch:{ all -> 0x0196 }
            java.lang.String r0 = "data"
            r14.putString(r0, r13)     // Catch:{ all -> 0x0196 }
            java.lang.String r13 = "width"
            r14.putInt(r13, r8)     // Catch:{ all -> 0x0196 }
            java.lang.String r13 = "height"
            r14.putInt(r13, r9)     // Catch:{ all -> 0x0196 }
            r15.resolve(r14)     // Catch:{ all -> 0x0196 }
        L_0x0194:
            monitor-exit(r12)
            return
        L_0x0196:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lwansbrough.RCTCamera.RCTCameraModule.processImage(com.lwansbrough.RCTCamera.MutableImage, com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void stopCapture(Promise promise) {
        if (this.mRecordingPromise != null) {
            releaseMediaRecorder();
            promise.resolve("Finished recording.");
            return;
        }
        promise.resolve("Not recording.");
    }

    @ReactMethod
    public void hasFlash(ReadableMap readableMap, Promise promise) {
        Camera acquireCameraInstance = RCTCamera.getInstance().acquireCameraInstance(readableMap.getInt("type"));
        if (acquireCameraInstance == null) {
            promise.reject("No camera found.");
            return;
        }
        List<String> supportedFlashModes = acquireCameraInstance.getParameters().getSupportedFlashModes();
        promise.resolve(Boolean.valueOf(supportedFlashModes != null && !supportedFlashModes.isEmpty()));
    }

    @ReactMethod
    public void setZoom(ReadableMap readableMap, int i) {
        Camera acquireCameraInstance;
        RCTCamera instance = RCTCamera.getInstance();
        if (instance != null && (acquireCameraInstance = instance.acquireCameraInstance(readableMap.getInt("type"))) != null) {
            Camera.Parameters parameters = acquireCameraInstance.getParameters();
            int maxZoom = parameters.getMaxZoom();
            if (parameters.isZoomSupported() && i >= 0 && i < maxZoom) {
                parameters.setZoom(i);
                try {
                    acquireCameraInstance.setParameters(parameters);
                } catch (RuntimeException e) {
                    Log.e(TAG, "setParameters failed", e);
                }
            }
        }
    }

    private File getOutputMediaFile(int i) {
        String str;
        if (i == 1) {
            str = Environment.DIRECTORY_PICTURES;
        } else if (i == 2) {
            str = Environment.DIRECTORY_MOVIES;
        } else {
            Log.e(TAG, "Unsupported media type:" + i);
            return null;
        }
        return getOutputFile(i, Environment.getExternalStoragePublicDirectory(str));
    }

    private File getOutputCameraRollFile(int i) {
        return getOutputFile(i, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    }

    private File getOutputFile(int i, File file) {
        String str;
        if (file.exists() || file.mkdirs()) {
            String format = String.format("%s", new Object[]{new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())});
            if (i == 1) {
                str = String.format("IMG_%s.jpg", new Object[]{format});
            } else if (i == 2) {
                str = String.format("VID_%s.mp4", new Object[]{format});
            } else {
                Log.e(TAG, "Unsupported media type:" + i);
                return null;
            }
            return new File(String.format("%s%s%s", new Object[]{file.getPath(), File.separator, str}));
        }
        Log.e(TAG, "failed to create directory:" + file.getAbsolutePath());
        return null;
    }

    private File getTempMediaFile(int i) {
        try {
            String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File cacheDir = this._reactContext.getCacheDir();
            if (i == 1) {
                return File.createTempFile("IMG_" + format, ".jpg", cacheDir);
            } else if (i == 2) {
                return File.createTempFile("VID_" + format, ".mp4", cacheDir);
            } else {
                Log.e(TAG, "Unsupported media type:" + i);
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    private void addToMediaStore(String str) {
        MediaScannerConnection.scanFile(this._reactContext, new String[]{str}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
    }

    public void onHostResume() {
        this.mSafeToCapture = true;
    }

    public void onHostPause() {
        if (this.mRecordingPromise != null) {
            releaseMediaRecorder();
        }
    }

    private void resolveImage(File file, int i, int i2, final Promise promise, boolean z) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("path", Uri.fromFile(file).toString());
        writableNativeMap.putInt("width", i);
        writableNativeMap.putInt("height", i2);
        if (z) {
            MediaScannerConnection.scanFile(this._reactContext, new String[]{file.getAbsolutePath()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                    if (uri != null) {
                        writableNativeMap.putString("mediaUri", uri.toString());
                    }
                    promise.resolve(writableNativeMap);
                }
            });
            return;
        }
        promise.resolve(writableNativeMap);
    }
}
