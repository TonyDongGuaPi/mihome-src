package com.google.android.cameraview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.CamcorderProfile;
import android.media.ImageReader;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.cameraview.CameraViewImpl;
import com.google.android.cameraview.PreviewImpl;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.passport.ui.settings.UserAvatarUpdateActivity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import org.reactnative.camera.utils.ObjectUtils;

@TargetApi(21)
class Camera2 extends CameraViewImpl implements MediaRecorder.OnErrorListener, MediaRecorder.OnInfoListener {
    private static final int FOCUS_AREA_SIZE_DEFAULT = 300;
    private static final int FOCUS_METERING_AREA_WEIGHT_DEFAULT = 1000;
    private static final SparseIntArray INTERNAL_FACINGS = new SparseIntArray();
    private static final int MAX_PREVIEW_HEIGHT = 1080;
    private static final int MAX_PREVIEW_WIDTH = 1920;
    private static final String TAG = "Camera2";
    private String _mCameraId;
    private AspectRatio mAspectRatio = Constants.DEFAULT_ASPECT_RATIO;
    private boolean mAutoFocus;
    Set<String> mAvailableCameras = new HashSet();
    CameraDevice mCamera;
    private CameraCharacteristics mCameraCharacteristics;
    private final CameraDevice.StateCallback mCameraDeviceCallback = new CameraDevice.StateCallback() {
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            Camera2.this.mCamera = cameraDevice;
            Camera2.this.mCallback.onCameraOpened();
            Camera2.this.startCaptureSession();
        }

        public void onClosed(@NonNull CameraDevice cameraDevice) {
            Camera2.this.mCallback.onCameraClosed();
        }

        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            Camera2.this.mCamera = null;
        }

        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            Log.e(Camera2.TAG, "onError: " + cameraDevice.getId() + " (" + i + Operators.BRACKET_END_STR);
            Camera2.this.mCamera = null;
        }
    };
    private String mCameraId;
    private final CameraManager mCameraManager;
    private int mCameraOrientation;
    PictureCaptureCallback mCaptureCallback = new PictureCaptureCallback() {
        public void onPrecaptureRequired() {
            Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
            setState(3);
            try {
                Camera2.this.mCaptureSession.capture(Camera2.this.mPreviewRequestBuilder.build(), this, (Handler) null);
                Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
            } catch (CameraAccessException e) {
                Log.e(Camera2.TAG, "Failed to run precapture sequence.", e);
            }
        }

        public void onReady() {
            Camera2.this.captureStillPicture();
        }
    };
    CameraCaptureSession mCaptureSession;
    private int mDeviceOrientation;
    /* access modifiers changed from: private */
    public int mDisplayOrientation;
    private float mExposure;
    private int mFacing;
    private int mFlash;
    private float mFocusDepth;
    private int mImageFormat;
    /* access modifiers changed from: private */
    public Rect mInitialCropRegion;
    private AspectRatio mInitialRatio;
    private boolean mIsRecording;
    private boolean mIsScanning;
    private MediaRecorder mMediaRecorder;
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x004a, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
            if (r7 != null) goto L_0x0050;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
            if (r0 != null) goto L_0x0052;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            r7.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
            r7.close();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onImageAvailable(android.media.ImageReader r7) {
            /*
                r6 = this;
                android.media.Image r7 = r7.acquireNextImage()
                r0 = 0
                android.media.Image$Plane[] r1 = r7.getPlanes()     // Catch:{ Throwable -> 0x004c }
                int r2 = r1.length     // Catch:{ Throwable -> 0x004c }
                if (r2 <= 0) goto L_0x0044
                r2 = 0
                r1 = r1[r2]     // Catch:{ Throwable -> 0x004c }
                java.nio.ByteBuffer r1 = r1.getBuffer()     // Catch:{ Throwable -> 0x004c }
                int r3 = r1.remaining()     // Catch:{ Throwable -> 0x004c }
                byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x004c }
                r1.get(r3)     // Catch:{ Throwable -> 0x004c }
                int r1 = r7.getFormat()     // Catch:{ Throwable -> 0x004c }
                r4 = 256(0x100, float:3.59E-43)
                if (r1 != r4) goto L_0x002c
                com.google.android.cameraview.Camera2 r1 = com.google.android.cameraview.Camera2.this     // Catch:{ Throwable -> 0x004c }
                com.google.android.cameraview.CameraViewImpl$Callback r1 = r1.mCallback     // Catch:{ Throwable -> 0x004c }
                r1.onPictureTaken(r3, r2)     // Catch:{ Throwable -> 0x004c }
                goto L_0x0041
            L_0x002c:
                com.google.android.cameraview.Camera2 r1 = com.google.android.cameraview.Camera2.this     // Catch:{ Throwable -> 0x004c }
                com.google.android.cameraview.CameraViewImpl$Callback r1 = r1.mCallback     // Catch:{ Throwable -> 0x004c }
                int r2 = r7.getWidth()     // Catch:{ Throwable -> 0x004c }
                int r4 = r7.getHeight()     // Catch:{ Throwable -> 0x004c }
                com.google.android.cameraview.Camera2 r5 = com.google.android.cameraview.Camera2.this     // Catch:{ Throwable -> 0x004c }
                int r5 = r5.mDisplayOrientation     // Catch:{ Throwable -> 0x004c }
                r1.onFramePreview(r3, r2, r4, r5)     // Catch:{ Throwable -> 0x004c }
            L_0x0041:
                r7.close()     // Catch:{ Throwable -> 0x004c }
            L_0x0044:
                if (r7 == 0) goto L_0x0049
                r7.close()
            L_0x0049:
                return
            L_0x004a:
                r1 = move-exception
                goto L_0x004e
            L_0x004c:
                r0 = move-exception
                throw r0     // Catch:{ all -> 0x004a }
            L_0x004e:
                if (r7 == 0) goto L_0x0059
                if (r0 == 0) goto L_0x0056
                r7.close()     // Catch:{ Throwable -> 0x0059 }
                goto L_0x0059
            L_0x0056:
                r7.close()
            L_0x0059:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.cameraview.Camera2.AnonymousClass4.onImageAvailable(android.media.ImageReader):void");
        }
    };
    private Size mPictureSize;
    private final SizeMap mPictureSizes = new SizeMap();
    CaptureRequest.Builder mPreviewRequestBuilder;
    private final SizeMap mPreviewSizes = new SizeMap();
    private Surface mPreviewSurface;
    private ImageReader mScanImageReader;
    private final CameraCaptureSession.StateCallback mSessionCallback = new CameraCaptureSession.StateCallback() {
        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
            if (Camera2.this.mCamera != null) {
                Camera2.this.mCaptureSession = cameraCaptureSession;
                Rect unused = Camera2.this.mInitialCropRegion = (Rect) Camera2.this.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION);
                Camera2.this.updateAutoFocus();
                Camera2.this.updateFlash();
                Camera2.this.updateFocusDepth();
                Camera2.this.updateWhiteBalance();
                Camera2.this.updateZoom();
                try {
                    Camera2.this.mCaptureSession.setRepeatingRequest(Camera2.this.mPreviewRequestBuilder.build(), Camera2.this.mCaptureCallback, (Handler) null);
                } catch (CameraAccessException e) {
                    Log.e(Camera2.TAG, "Failed to start camera preview because it couldn't access camera", e);
                } catch (IllegalStateException e2) {
                    Log.e(Camera2.TAG, "Failed to start camera preview.", e2);
                }
            }
        }

        public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
            Log.e(Camera2.TAG, "Failed to configure capture session.");
        }

        public void onClosed(@NonNull CameraCaptureSession cameraCaptureSession) {
            if (Camera2.this.mCaptureSession != null && Camera2.this.mCaptureSession.equals(cameraCaptureSession)) {
                Camera2.this.mCaptureSession = null;
            }
        }
    };
    private ImageReader mStillImageReader;
    private String mVideoPath;
    private int mWhiteBalance;
    private float mZoom;

    private boolean isLandscape(int i) {
        return i == 90 || i == 270;
    }

    static {
        INTERNAL_FACINGS.put(0, 1);
        INTERNAL_FACINGS.put(1, 0);
    }

    Camera2(CameraViewImpl.Callback callback, PreviewImpl previewImpl, Context context, Handler handler) {
        super(callback, previewImpl, handler);
        this.mCameraManager = (CameraManager) context.getSystemService(UserAvatarUpdateActivity.CAMERA);
        this.mCameraManager.registerAvailabilityCallback(new CameraManager.AvailabilityCallback() {
            public void onCameraAvailable(@NonNull String str) {
                super.onCameraAvailable(str);
                Camera2.this.mAvailableCameras.add(str);
            }

            public void onCameraUnavailable(@NonNull String str) {
                super.onCameraUnavailable(str);
                Camera2.this.mAvailableCameras.remove(str);
            }
        }, (Handler) null);
        this.mImageFormat = this.mIsScanning ? 35 : 256;
        this.mPreview.setCallback(new PreviewImpl.Callback() {
            public void onSurfaceChanged() {
                Camera2.this.startCaptureSession();
            }

            public void onSurfaceDestroyed() {
                Camera2.this.stop();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean start() {
        if (!chooseCameraIdByFacing()) {
            this.mAspectRatio = this.mInitialRatio;
            return false;
        }
        collectCameraInfo();
        setAspectRatio(this.mInitialRatio);
        this.mInitialRatio = null;
        prepareStillImageReader();
        prepareScanImageReader();
        startOpeningCamera();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        if (this.mCaptureSession != null) {
            this.mCaptureSession.close();
            this.mCaptureSession = null;
        }
        if (this.mCamera != null) {
            this.mCamera.close();
            this.mCamera = null;
        }
        if (this.mStillImageReader != null) {
            this.mStillImageReader.close();
            this.mStillImageReader = null;
        }
        if (this.mScanImageReader != null) {
            this.mScanImageReader.close();
            this.mScanImageReader = null;
        }
        if (this.mMediaRecorder != null) {
            this.mMediaRecorder.stop();
            this.mMediaRecorder.reset();
            this.mMediaRecorder.release();
            this.mMediaRecorder = null;
            if (this.mIsRecording) {
                this.mCallback.onVideoRecorded(this.mVideoPath, 0, 0);
                this.mIsRecording = false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isCameraOpened() {
        return this.mCamera != null;
    }

    /* access modifiers changed from: package-private */
    public void setFacing(int i) {
        if (this.mFacing != i) {
            this.mFacing = i;
            if (isCameraOpened()) {
                stop();
                start();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getFacing() {
        return this.mFacing;
    }

    /* access modifiers changed from: package-private */
    public void setCameraId(String str) {
        if (!ObjectUtils.a(this._mCameraId, str)) {
            this._mCameraId = str;
            if (!ObjectUtils.a(this._mCameraId, this.mCameraId) && isCameraOpened()) {
                stop();
                start();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getCameraId() {
        return this._mCameraId;
    }

    /* access modifiers changed from: package-private */
    public Set<AspectRatio> getSupportedAspectRatios() {
        return this.mPreviewSizes.ratios();
    }

    /* access modifiers changed from: package-private */
    public List<Properties> getCameraIds() {
        try {
            ArrayList arrayList = new ArrayList();
            for (String str : this.mCameraManager.getCameraIdList()) {
                Properties properties = new Properties();
                properties.put("id", str);
                properties.put("type", String.valueOf(((Integer) this.mCameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING)).intValue() == 0 ? 1 : 0));
                arrayList.add(properties);
            }
            return arrayList;
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to get a list of camera ids", e);
        }
    }

    /* access modifiers changed from: package-private */
    public SortedSet<Size> getAvailablePictureSizes(AspectRatio aspectRatio) {
        return this.mPictureSizes.sizes(aspectRatio);
    }

    /* access modifiers changed from: package-private */
    public void setPictureSize(Size size) {
        if (this.mCaptureSession != null) {
            try {
                this.mCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            this.mCaptureSession.close();
            this.mCaptureSession = null;
        }
        if (this.mStillImageReader != null) {
            this.mStillImageReader.close();
        }
        if (size != null) {
            this.mPictureSize = size;
        } else if (this.mAspectRatio != null && this.mPictureSize != null) {
            this.mPictureSizes.sizes(this.mAspectRatio).last();
        } else {
            return;
        }
        prepareStillImageReader();
        startCaptureSession();
    }

    /* access modifiers changed from: package-private */
    public Size getPictureSize() {
        return this.mPictureSize;
    }

    /* access modifiers changed from: package-private */
    public boolean setAspectRatio(AspectRatio aspectRatio) {
        if (aspectRatio != null && this.mPreviewSizes.isEmpty()) {
            this.mInitialRatio = aspectRatio;
            return false;
        } else if (aspectRatio == null || aspectRatio.equals(this.mAspectRatio) || !this.mPreviewSizes.ratios().contains(aspectRatio)) {
            return false;
        } else {
            this.mAspectRatio = aspectRatio;
            prepareStillImageReader();
            prepareScanImageReader();
            if (this.mCaptureSession == null) {
                return true;
            }
            this.mCaptureSession.close();
            this.mCaptureSession = null;
            startCaptureSession();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public AspectRatio getAspectRatio() {
        return this.mAspectRatio;
    }

    /* access modifiers changed from: package-private */
    public void setAutoFocus(boolean z) {
        if (this.mAutoFocus != z) {
            this.mAutoFocus = z;
            if (this.mPreviewRequestBuilder != null) {
                updateAutoFocus();
                if (this.mCaptureSession != null) {
                    try {
                        this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
                    } catch (CameraAccessException unused) {
                        this.mAutoFocus = !this.mAutoFocus;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getAutoFocus() {
        return this.mAutoFocus;
    }

    /* access modifiers changed from: package-private */
    public void setFlash(int i) {
        if (this.mFlash != i) {
            int i2 = this.mFlash;
            this.mFlash = i;
            if (this.mPreviewRequestBuilder != null) {
                updateFlash();
                if (this.mCaptureSession != null) {
                    try {
                        this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
                    } catch (CameraAccessException unused) {
                        this.mFlash = i2;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getFlash() {
        return this.mFlash;
    }

    /* access modifiers changed from: package-private */
    public float getExposureCompensation() {
        return this.mExposure;
    }

    /* access modifiers changed from: package-private */
    public void setExposureCompensation(float f) {
        Log.e("CAMERA_2:: ", "Adjusting exposure is not currently supported for Camera2");
    }

    /* access modifiers changed from: package-private */
    public void takePicture(ReadableMap readableMap) {
        this.mCaptureCallback.setOptions(readableMap);
        if (this.mAutoFocus) {
            lockFocus();
        } else {
            captureStillPicture();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean record(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile, int i3) {
        if (this.mIsRecording) {
            return false;
        }
        setUpMediaRecorder(str, i, i2, z, camcorderProfile);
        try {
            this.mMediaRecorder.prepare();
            if (this.mCaptureSession != null) {
                this.mCaptureSession.close();
                this.mCaptureSession = null;
            }
            Size chooseOptimalSize = chooseOptimalSize();
            this.mPreview.setBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
            Surface previewSurface = getPreviewSurface();
            Surface surface = this.mMediaRecorder.getSurface();
            this.mPreviewRequestBuilder = this.mCamera.createCaptureRequest(3);
            this.mPreviewRequestBuilder.addTarget(previewSurface);
            this.mPreviewRequestBuilder.addTarget(surface);
            this.mCamera.createCaptureSession(Arrays.asList(new Surface[]{previewSurface, surface}), this.mSessionCallback, (Handler) null);
            this.mMediaRecorder.start();
            this.mIsRecording = true;
            return true;
        } catch (CameraAccessException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void stopRecording() {
        if (this.mIsRecording) {
            stopMediaRecorder();
            if (this.mCaptureSession != null) {
                this.mCaptureSession.close();
                this.mCaptureSession = null;
            }
            startCaptureSession();
        }
    }

    public void setFocusDepth(float f) {
        if (this.mFocusDepth != f) {
            float f2 = this.mFocusDepth;
            this.mFocusDepth = f;
            if (this.mCaptureSession != null) {
                updateFocusDepth();
                try {
                    this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
                } catch (CameraAccessException unused) {
                    this.mFocusDepth = f2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public float getFocusDepth() {
        return this.mFocusDepth;
    }

    public void setZoom(float f) {
        if (this.mZoom != f) {
            float f2 = this.mZoom;
            this.mZoom = f;
            if (this.mCaptureSession != null) {
                updateZoom();
                try {
                    this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
                } catch (CameraAccessException unused) {
                    this.mZoom = f2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public float getZoom() {
        return this.mZoom;
    }

    public void setWhiteBalance(int i) {
        if (this.mWhiteBalance != i) {
            int i2 = this.mWhiteBalance;
            this.mWhiteBalance = i;
            if (this.mCaptureSession != null) {
                updateWhiteBalance();
                try {
                    this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
                } catch (CameraAccessException unused) {
                    this.mWhiteBalance = i2;
                }
            }
        }
    }

    public int getWhiteBalance() {
        return this.mWhiteBalance;
    }

    /* access modifiers changed from: package-private */
    public void setScanning(boolean z) {
        if (this.mIsScanning != z) {
            this.mIsScanning = z;
            if (!this.mIsScanning) {
                this.mImageFormat = 256;
            } else {
                this.mImageFormat = 35;
            }
            if (this.mCaptureSession != null) {
                this.mCaptureSession.close();
                this.mCaptureSession = null;
            }
            startCaptureSession();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getScanning() {
        return this.mIsScanning;
    }

    /* access modifiers changed from: package-private */
    public int getCameraOrientation() {
        return this.mCameraOrientation;
    }

    /* access modifiers changed from: package-private */
    public void setDisplayOrientation(int i) {
        this.mDisplayOrientation = i;
        this.mPreview.setDisplayOrientation(this.mDisplayOrientation);
    }

    /* access modifiers changed from: package-private */
    public void setDeviceOrientation(int i) {
        this.mDeviceOrientation = i;
    }

    private boolean chooseCameraIdByFacing() {
        int i = 0;
        if (this._mCameraId == null) {
            try {
                int i2 = INTERNAL_FACINGS.get(this.mFacing);
                String[] cameraIdList = this.mCameraManager.getCameraIdList();
                if (cameraIdList.length != 0) {
                    for (String str : cameraIdList) {
                        CameraCharacteristics cameraCharacteristics = this.mCameraManager.getCameraCharacteristics(str);
                        Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                        if (num != null) {
                            if (num.intValue() != 2) {
                                Integer num2 = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                                if (num2 == null) {
                                    throw new NullPointerException("Unexpected state: LENS_FACING null");
                                } else if (num2.intValue() == i2) {
                                    this.mCameraId = str;
                                    this.mCameraCharacteristics = cameraCharacteristics;
                                    return true;
                                }
                            }
                        }
                    }
                    this.mCameraId = cameraIdList[0];
                    this.mCameraCharacteristics = this.mCameraManager.getCameraCharacteristics(this.mCameraId);
                    Integer num3 = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                    if (num3 != null) {
                        if (num3.intValue() != 2) {
                            Integer num4 = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                            if (num4 != null) {
                                int size = INTERNAL_FACINGS.size();
                                for (int i3 = 0; i3 < size; i3++) {
                                    if (INTERNAL_FACINGS.valueAt(i3) == num4.intValue()) {
                                        this.mFacing = INTERNAL_FACINGS.keyAt(i3);
                                        return true;
                                    }
                                }
                                this.mFacing = 0;
                                return true;
                            }
                            throw new NullPointerException("Unexpected state: LENS_FACING null");
                        }
                    }
                    return false;
                }
                throw new RuntimeException("No camera available.");
            } catch (CameraAccessException e) {
                throw new RuntimeException("Failed to get a list of camera devices", e);
            }
        } else {
            try {
                this.mCameraCharacteristics = this.mCameraManager.getCameraCharacteristics(this._mCameraId);
                Integer num5 = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                if (num5 != null) {
                    if (num5.intValue() != 2) {
                        Integer num6 = (Integer) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                        if (num6 != null) {
                            int size2 = INTERNAL_FACINGS.size();
                            while (true) {
                                if (i >= size2) {
                                    break;
                                } else if (INTERNAL_FACINGS.valueAt(i) == num6.intValue()) {
                                    this.mFacing = INTERNAL_FACINGS.keyAt(i);
                                    break;
                                } else {
                                    i++;
                                }
                            }
                            this.mCameraId = this._mCameraId;
                            return true;
                        }
                        throw new NullPointerException("Unexpected state: LENS_FACING null");
                    }
                }
                return false;
            } catch (Exception e2) {
                throw new RuntimeException("Failed to get camera characteristics", e2);
            }
        }
    }

    private void collectCameraInfo() {
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap != null) {
            this.mPreviewSizes.clear();
            for (Size size : streamConfigurationMap.getOutputSizes(this.mPreview.getOutputClass())) {
                int width = size.getWidth();
                int height = size.getHeight();
                if (width <= 1920 && height <= 1080) {
                    this.mPreviewSizes.add(new Size(width, height));
                }
            }
            this.mPictureSizes.clear();
            collectPictureSizes(this.mPictureSizes, streamConfigurationMap);
            if (this.mPictureSize == null) {
                this.mPictureSize = this.mPictureSizes.sizes(this.mAspectRatio).last();
            }
            for (AspectRatio next : this.mPreviewSizes.ratios()) {
                if (!this.mPictureSizes.ratios().contains(next)) {
                    this.mPreviewSizes.remove(next);
                }
            }
            if (!this.mPreviewSizes.ratios().contains(this.mAspectRatio)) {
                this.mAspectRatio = this.mPreviewSizes.ratios().iterator().next();
            }
            this.mCameraOrientation = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
            return;
        }
        throw new IllegalStateException("Failed to get configuration map: " + this.mCameraId);
    }

    /* access modifiers changed from: protected */
    public void collectPictureSizes(SizeMap sizeMap, StreamConfigurationMap streamConfigurationMap) {
        for (Size size : streamConfigurationMap.getOutputSizes(this.mImageFormat)) {
            this.mPictureSizes.add(new Size(size.getWidth(), size.getHeight()));
        }
    }

    private void prepareStillImageReader() {
        if (this.mStillImageReader != null) {
            this.mStillImageReader.close();
        }
        this.mStillImageReader = ImageReader.newInstance(this.mPictureSize.getWidth(), this.mPictureSize.getHeight(), 256, 1);
        this.mStillImageReader.setOnImageAvailableListener(this.mOnImageAvailableListener, (Handler) null);
    }

    private void prepareScanImageReader() {
        if (this.mScanImageReader != null) {
            this.mScanImageReader.close();
        }
        Size last = this.mPreviewSizes.sizes(this.mAspectRatio).last();
        this.mScanImageReader = ImageReader.newInstance(last.getWidth(), last.getHeight(), 35, 1);
        this.mScanImageReader.setOnImageAvailableListener(this.mOnImageAvailableListener, (Handler) null);
    }

    private void startOpeningCamera() {
        try {
            this.mCameraManager.openCamera(this.mCameraId, this.mCameraDeviceCallback, (Handler) null);
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to open camera: " + this.mCameraId, e);
        }
    }

    /* access modifiers changed from: package-private */
    public void startCaptureSession() {
        if (isCameraOpened() && this.mPreview.isReady() && this.mStillImageReader != null && this.mScanImageReader != null) {
            Size chooseOptimalSize = chooseOptimalSize();
            this.mPreview.setBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
            Surface previewSurface = getPreviewSurface();
            try {
                this.mPreviewRequestBuilder = this.mCamera.createCaptureRequest(1);
                this.mPreviewRequestBuilder.addTarget(previewSurface);
                if (this.mIsScanning) {
                    this.mPreviewRequestBuilder.addTarget(this.mScanImageReader.getSurface());
                }
                this.mCamera.createCaptureSession(Arrays.asList(new Surface[]{previewSurface, this.mStillImageReader.getSurface(), this.mScanImageReader.getSurface()}), this.mSessionCallback, (Handler) null);
            } catch (CameraAccessException unused) {
                this.mCallback.onMountError();
            }
        }
    }

    public void resumePreview() {
        unlockFocus();
    }

    public void pausePreview() {
        try {
            this.mCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public Surface getPreviewSurface() {
        if (this.mPreviewSurface != null) {
            return this.mPreviewSurface;
        }
        return this.mPreview.getSurface();
    }

    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null) {
            this.mPreviewSurface = new Surface(surfaceTexture);
        } else {
            this.mPreviewSurface = null;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (Camera2.this.mCaptureSession != null) {
                    Camera2.this.mCaptureSession.close();
                    Camera2.this.mCaptureSession = null;
                }
                Camera2.this.startCaptureSession();
            }
        });
    }

    public Size getPreviewSize() {
        return new Size(this.mPreview.getWidth(), this.mPreview.getHeight());
    }

    private Size chooseOptimalSize() {
        int width = this.mPreview.getWidth();
        int height = this.mPreview.getHeight();
        if (width < height) {
            int i = height;
            height = width;
            width = i;
        }
        SortedSet<Size> sizes = this.mPreviewSizes.sizes(this.mAspectRatio);
        for (Size size : sizes) {
            if (size.getWidth() >= width && size.getHeight() >= height) {
                return size;
            }
        }
        return sizes.last();
    }

    /* access modifiers changed from: package-private */
    public void updateAutoFocus() {
        if (this.mAutoFocus) {
            int[] iArr = (int[]) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            if (iArr == null || iArr.length == 0 || (iArr.length == 1 && iArr[0] == 0)) {
                this.mAutoFocus = false;
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
                return;
            }
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 4);
            return;
        }
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
    }

    /* access modifiers changed from: package-private */
    public void updateFlash() {
        switch (this.mFlash) {
            case 0:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
                return;
            case 1:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 3);
                this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
                return;
            case 2:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 1);
                this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 2);
                return;
            case 3:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 2);
                this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
                return;
            case 4:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, 4);
                this.mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateFocusDepth() {
        if (!this.mAutoFocus) {
            Float f = (Float) this.mCameraCharacteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
            if (f != null) {
                this.mPreviewRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(this.mFocusDepth * f.floatValue()));
                return;
            }
            throw new NullPointerException("Unexpected state: LENS_INFO_MINIMUM_FOCUS_DISTANCE null");
        }
    }

    /* access modifiers changed from: package-private */
    public void updateZoom() {
        float floatValue = (this.mZoom * (((Float) this.mCameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() - 1.0f)) + 1.0f;
        Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (rect != null) {
            int width = rect.width();
            int height = rect.height();
            int i = (width - ((int) (((float) width) / floatValue))) / 2;
            int i2 = (height - ((int) (((float) height) / floatValue))) / 2;
            Rect rect2 = new Rect(rect.left + i, rect.top + i2, rect.right - i, rect.bottom - i2);
            if (floatValue != 1.0f) {
                this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, rect2);
            } else {
                this.mPreviewRequestBuilder.set(CaptureRequest.SCALER_CROP_REGION, this.mInitialCropRegion);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateWhiteBalance() {
        switch (this.mWhiteBalance) {
            case 0:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 1);
                return;
            case 1:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 6);
                return;
            case 2:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 5);
                return;
            case 3:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 8);
                return;
            case 4:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 3);
                return;
            case 5:
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AWB_MODE, 2);
                return;
            default:
                return;
        }
    }

    private void lockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        try {
            this.mCaptureCallback.setState(1);
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to lock focus.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void setFocusArea(float f, float f2) {
        if (this.mCaptureSession != null) {
            AnonymousClass8 r0 = new CameraCaptureSession.CaptureCallback() {
                public void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
                    super.onCaptureCompleted(cameraCaptureSession, captureRequest, totalCaptureResult);
                    if (captureRequest.getTag() == "FOCUS_TAG") {
                        Camera2.this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, (Object) null);
                        try {
                            Camera2.this.mCaptureSession.setRepeatingRequest(Camera2.this.mPreviewRequestBuilder.build(), (CameraCaptureSession.CaptureCallback) null, (Handler) null);
                        } catch (CameraAccessException e) {
                            Log.e(Camera2.TAG, "Failed to manual focus.", e);
                        }
                    }
                }

                public void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
                    super.onCaptureFailed(cameraCaptureSession, captureRequest, captureFailure);
                    Log.e(Camera2.TAG, "Manual AF failure: " + captureFailure);
                }
            };
            try {
                this.mCaptureSession.stopRepeating();
            } catch (CameraAccessException e) {
                Log.e(TAG, "Failed to manual focus.", e);
            }
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 0);
            try {
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), r0, (Handler) null);
            } catch (CameraAccessException e2) {
                Log.e(TAG, "Failed to manual focus.", e2);
            }
            if (isMeteringAreaAFSupported()) {
                MeteringRectangle calculateFocusArea = calculateFocusArea(f, f2);
                this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{calculateFocusArea});
            }
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_MODE, 1);
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, 1);
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            this.mPreviewRequestBuilder.setTag("FOCUS_TAG");
            try {
                this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), r0, (Handler) null);
            } catch (CameraAccessException e3) {
                Log.e(TAG, "Failed to manual focus.", e3);
            }
        }
    }

    private boolean isMeteringAreaAFSupported() {
        return ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() >= 1;
    }

    private MeteringRectangle calculateFocusArea(float f, float f2) {
        Rect rect = (Rect) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        return new MeteringRectangle(Math.max(((int) (f * ((float) rect.width()))) - 150, 0), Math.max(((int) (f2 * ((float) rect.height()))) - 150, 0), 300, 300, 999);
    }

    /* access modifiers changed from: package-private */
    public void captureStillPicture() {
        try {
            CaptureRequest.Builder createCaptureRequest = this.mCamera.createCaptureRequest(2);
            if (this.mIsScanning) {
                this.mImageFormat = 256;
                createCaptureRequest.removeTarget(this.mScanImageReader.getSurface());
            }
            createCaptureRequest.addTarget(this.mStillImageReader.getSurface());
            createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, this.mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_MODE));
            switch (this.mFlash) {
                case 0:
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                    createCaptureRequest.set(CaptureRequest.FLASH_MODE, 0);
                    break;
                case 1:
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 3);
                    break;
                case 2:
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
                    createCaptureRequest.set(CaptureRequest.FLASH_MODE, 2);
                    break;
                case 3:
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 2);
                    break;
                case 4:
                    createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 2);
                    break;
            }
            createCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(getOutputRotation()));
            if (this.mCaptureCallback.getOptions().hasKey(Constants.Name.QUALITY)) {
                createCaptureRequest.set(CaptureRequest.JPEG_QUALITY, Byte.valueOf((byte) ((int) (this.mCaptureCallback.getOptions().getDouble(Constants.Name.QUALITY) * 100.0d))));
            }
            createCaptureRequest.set(CaptureRequest.SCALER_CROP_REGION, this.mPreviewRequestBuilder.get(CaptureRequest.SCALER_CROP_REGION));
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.capture(createCaptureRequest.build(), new CameraCaptureSession.CaptureCallback() {
                public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
                    if (Camera2.this.mCaptureCallback.getOptions().hasKey("pauseAfterCapture") && !Camera2.this.mCaptureCallback.getOptions().getBoolean("pauseAfterCapture")) {
                        Camera2.this.unlockFocus();
                    }
                }
            }, (Handler) null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Cannot capture a still picture.", e);
        }
    }

    private int getOutputRotation() {
        int intValue = ((Integer) this.mCameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        if (this.mFacing == 0) {
            return (intValue + this.mDeviceOrientation) % 360;
        }
        return ((intValue + this.mDeviceOrientation) + (isLandscape(this.mDeviceOrientation) ? 180 : 0)) % 360;
    }

    private void setUpMediaRecorder(String str, int i, int i2, boolean z, CamcorderProfile camcorderProfile) {
        this.mMediaRecorder = new MediaRecorder();
        this.mMediaRecorder.setVideoSource(2);
        if (z) {
            this.mMediaRecorder.setAudioSource(1);
        }
        this.mMediaRecorder.setOutputFile(str);
        this.mVideoPath = str;
        CamcorderProfile camcorderProfile2 = !CamcorderProfile.hasProfile(Integer.parseInt(this.mCameraId), camcorderProfile.quality) ? CamcorderProfile.get(1) : camcorderProfile;
        camcorderProfile2.videoBitRate = camcorderProfile.videoBitRate;
        setCamcorderProfile(camcorderProfile2, z);
        this.mMediaRecorder.setOrientationHint(getOutputRotation());
        if (i != -1) {
            this.mMediaRecorder.setMaxDuration(i);
        }
        if (i2 != -1) {
            this.mMediaRecorder.setMaxFileSize((long) i2);
        }
        this.mMediaRecorder.setOnInfoListener(this);
        this.mMediaRecorder.setOnErrorListener(this);
    }

    private void setCamcorderProfile(CamcorderProfile camcorderProfile, boolean z) {
        this.mMediaRecorder.setOutputFormat(camcorderProfile.fileFormat);
        this.mMediaRecorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
        this.mMediaRecorder.setVideoSize(camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
        this.mMediaRecorder.setVideoEncodingBitRate(camcorderProfile.videoBitRate);
        this.mMediaRecorder.setVideoEncoder(camcorderProfile.videoCodec);
        if (z) {
            this.mMediaRecorder.setAudioEncodingBitRate(camcorderProfile.audioBitRate);
            this.mMediaRecorder.setAudioChannels(camcorderProfile.audioChannels);
            this.mMediaRecorder.setAudioSamplingRate(camcorderProfile.audioSampleRate);
            this.mMediaRecorder.setAudioEncoder(camcorderProfile.audioCodec);
        }
    }

    private void stopMediaRecorder() {
        this.mIsRecording = false;
        try {
            this.mCaptureSession.stopRepeating();
            this.mCaptureSession.abortCaptures();
            this.mMediaRecorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mMediaRecorder.reset();
        this.mMediaRecorder.release();
        this.mMediaRecorder = null;
        if (this.mVideoPath == null || !new File(this.mVideoPath).exists()) {
            this.mCallback.onVideoRecorded((String) null, 0, 0);
            return;
        }
        this.mCallback.onVideoRecorded(this.mVideoPath, 0, 0);
        this.mVideoPath = null;
    }

    /* access modifiers changed from: package-private */
    public void unlockFocus() {
        this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
        try {
            this.mCaptureSession.capture(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
            updateAutoFocus();
            updateFlash();
            if (this.mIsScanning) {
                this.mImageFormat = 35;
                startCaptureSession();
                return;
            }
            this.mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            this.mCaptureSession.setRepeatingRequest(this.mPreviewRequestBuilder.build(), this.mCaptureCallback, (Handler) null);
            this.mCaptureCallback.setState(0);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to restart camera preview.", e);
        }
    }

    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        if (i == 800 || i == 801) {
            stopRecording();
        }
    }

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        stopRecording();
    }

    private static abstract class PictureCaptureCallback extends CameraCaptureSession.CaptureCallback {
        static final int STATE_CAPTURING = 5;
        static final int STATE_LOCKED = 2;
        static final int STATE_LOCKING = 1;
        static final int STATE_PRECAPTURE = 3;
        static final int STATE_PREVIEW = 0;
        static final int STATE_WAITING = 4;
        private ReadableMap mOptions = null;
        private int mState;

        public abstract void onPrecaptureRequired();

        public abstract void onReady();

        PictureCaptureCallback() {
        }

        /* access modifiers changed from: package-private */
        public void setState(int i) {
            this.mState = i;
        }

        /* access modifiers changed from: package-private */
        public void setOptions(ReadableMap readableMap) {
            this.mOptions = readableMap;
        }

        /* access modifiers changed from: package-private */
        public ReadableMap getOptions() {
            return this.mOptions;
        }

        public void onCaptureProgressed(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull CaptureResult captureResult) {
            process(captureResult);
        }

        public void onCaptureCompleted(@NonNull CameraCaptureSession cameraCaptureSession, @NonNull CaptureRequest captureRequest, @NonNull TotalCaptureResult totalCaptureResult) {
            process(totalCaptureResult);
        }

        private void process(@NonNull CaptureResult captureResult) {
            int i = this.mState;
            if (i != 1) {
                switch (i) {
                    case 3:
                        Integer num = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                        if (num == null || num.intValue() == 5 || num.intValue() == 4 || num.intValue() == 2) {
                            setState(4);
                            return;
                        }
                        return;
                    case 4:
                        Integer num2 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                        if (num2 == null || num2.intValue() != 5) {
                            setState(5);
                            onReady();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                Integer num3 = (Integer) captureResult.get(CaptureResult.CONTROL_AF_STATE);
                if (num3 != null) {
                    if (num3.intValue() == 4 || num3.intValue() == 5) {
                        Integer num4 = (Integer) captureResult.get(CaptureResult.CONTROL_AE_STATE);
                        if (num4 == null || num4.intValue() == 2) {
                            setState(5);
                            onReady();
                            return;
                        }
                        setState(2);
                        onPrecaptureRequired();
                    }
                }
            }
        }
    }
}
