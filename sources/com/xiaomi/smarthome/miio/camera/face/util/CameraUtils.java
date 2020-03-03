package com.xiaomi.smarthome.miio.camera.face.util;

import android.app.Activity;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import com.mijia.debug.SDKLog;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CameraUtils {
    public static int DEFAULT_HEIGHT = 1920;
    public static int DEFAULT_WIDTH = 1920;
    public static final int DESIRED_PREVIEW_FPS = 30;
    public static final String TAG = "CameraUtils";
    private static AutoFocusCallback autoFocusCallback = new AutoFocusCallback();
    private static AutoFocusCallback autoFocusListener = null;
    private static Camera.ErrorCallback errorCallback = new Camera.ErrorCallback() {
        public void onError(int i, Camera camera) {
            String str = CameraUtils.TAG;
            SDKLog.b(str, "onError" + i);
        }
    };
    private static Camera mCamera;
    private static int mCameraID = 1;
    private static int mCameraPreviewFps;
    private static int mOrientation = 0;
    public static int mPreviewHeight;
    public static int mPreviewWidth;
    private static Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            if (bArr != null) {
                CameraUtils.yuvData = bArr;
            }
        }
    };
    private static Camera.Size previewSize;
    public static double rate;
    public static byte[] yuvData;

    static {
        double d = (double) DEFAULT_WIDTH;
        double d2 = (double) DEFAULT_HEIGHT;
        Double.isNaN(d);
        Double.isNaN(d2);
        rate = d / d2;
    }

    public static void openFrontalCamera(int i) {
        if (mCamera != null) {
            mCamera.setDisplayOrientation(mOrientation);
            SDKLog.b(TAG, "camera already initialized!");
            return;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        int i2 = 0;
        while (true) {
            if (i2 >= numberOfCameras) {
                break;
            }
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 1) {
                mCamera = Camera.open(i2);
                mCameraID = cameraInfo.facing;
                break;
            }
            i2++;
        }
        if (mCamera == null) {
            mCamera = Camera.open();
            mCameraID = 0;
        }
        if (mCamera != null) {
            Camera.Parameters parameters = mCamera.getParameters();
            mCameraPreviewFps = chooseFixedPreviewFps(parameters, i * 1000);
            parameters.setPictureFormat(256);
            parameters.setRecordingHint(true);
            parameters.setJpegQuality(100);
            if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
                parameters.setFocusMode("continuous-picture");
            }
            mCamera.setParameters(parameters);
            setPreviewSize(mCamera, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            setPictureSize(mCamera, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            mCamera.setDisplayOrientation(mOrientation);
            mCamera.cancelAutoFocus();
            mCamera.setPreviewCallback(previewCallback);
            mCamera.setErrorCallback(errorCallback);
            return;
        }
        throw new RuntimeException("Unable to open camera");
    }

    public static int getPreviewFormat() {
        return mCamera.getParameters().getPreviewFormat();
    }

    public static void openCamera(int i, int i2) {
        if (mCamera == null) {
            mCamera = Camera.open(i);
            if (mCamera != null) {
                mCameraID = i;
                Camera.Parameters parameters = mCamera.getParameters();
                mCameraPreviewFps = chooseFixedPreviewFps(parameters, i2 * 1000);
                parameters.setPictureFormat(256);
                parameters.setRecordingHint(true);
                parameters.setJpegQuality(100);
                if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
                    parameters.setFocusMode("continuous-picture");
                }
                mCamera.setParameters(parameters);
                setPreviewSize(mCamera, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                setPictureSize(mCamera, DEFAULT_WIDTH, DEFAULT_HEIGHT);
                mCamera.setDisplayOrientation(mOrientation);
                mCamera.cancelAutoFocus();
                mCamera.setPreviewCallback(previewCallback);
                mCamera.setErrorCallback(errorCallback);
                return;
            }
            throw new RuntimeException("Unable to open camera");
        }
        throw new RuntimeException("camera already initialized!");
    }

    public static void autoFocus(AutoFocusCallback autoFocusCallback2) {
        mCamera.autoFocus(autoFocusCallback2);
    }

    static class AutoFocusCallback implements Camera.AutoFocusCallback {
        AutoFocusCallback() {
        }

        public void onAutoFocus(boolean z, Camera camera) {
            SDKLog.b(CameraUtils.TAG, "onAutoFocus");
        }
    }

    public static void startPreviewDisplay(SurfaceHolder surfaceHolder) {
        if (mCamera == null) {
            SDKLog.b(TAG, "Camera must be set when start preview");
            return;
        }
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchCamera(int i, SurfaceHolder surfaceHolder) {
        if (mCameraID != i) {
            mCameraID = i;
            releaseCamera();
            openCamera(i, 30);
            startPreviewDisplay(surfaceHolder);
        }
    }

    public static void releaseCamera() {
        if (mCamera != null) {
            mCamera.setPreviewCallback((Camera.PreviewCallback) null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera.release();
            mCamera = null;
        }
    }

    public static void startPreview() {
        if (mCamera != null) {
            mCamera.startPreview();
        }
    }

    public static void stopPreview() {
        if (mCamera != null) {
            mCamera.stopPreview();
        }
    }

    public static void takePicture(Camera.ShutterCallback shutterCallback, Camera.PictureCallback pictureCallback, Camera.PictureCallback pictureCallback2, Camera.PictureCallback pictureCallback3) {
        if (mCamera != null) {
            mCamera.takePicture(shutterCallback, pictureCallback, pictureCallback2, pictureCallback3);
        }
    }

    public static void setPreviewSize(Camera camera, int i, int i2) {
        List<Camera.Size> supportedPreviewSizes = getSupportedPreviewSizes();
        if (supportedPreviewSizes != null) {
            SDKLog.b(TAG, "previewSizes:");
            Camera.Size size = supportedPreviewSizes.get(0);
            for (Camera.Size next : supportedPreviewSizes) {
                String str = TAG;
                SDKLog.b(str, next.width + "-" + next.height);
            }
        }
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size calculatePerfectSize = calculatePerfectSize(parameters.getSupportedPreviewSizes(), i, i2);
        String str2 = TAG;
        SDKLog.b(str2, "calculated : " + calculatePerfectSize.width + "-" + calculatePerfectSize.height);
        String str3 = TAG;
        SDKLog.b(str3, "expectWidth : " + i + "-" + i2);
        mPreviewWidth = calculatePerfectSize.width;
        mPreviewHeight = calculatePerfectSize.height;
        previewSize = calculatePerfectSize;
        parameters.setPreviewSize(calculatePerfectSize.width, calculatePerfectSize.height);
        camera.setParameters(parameters);
    }

    public static Camera.Size getPreviewSize() {
        if (previewSize != null) {
            return previewSize;
        }
        String str = TAG;
        SDKLog.b(str, "getPreviewSize result=" + previewSize.width + "-" + previewSize.height);
        return previewSize;
    }

    public static List<Camera.Size> getSupportedPreviewSizes() {
        if (mCamera != null) {
            return mCamera.getParameters().getSupportedPreviewSizes();
        }
        return null;
    }

    public static void setPictureSize(Camera camera, int i, int i2) {
        List<Camera.Size> supportedPictureSizes = getSupportedPictureSizes();
        if (supportedPictureSizes != null) {
            SDKLog.b(TAG, "pictureSizes:");
            Camera.Size size = supportedPictureSizes.get(0);
            for (Camera.Size next : supportedPictureSizes) {
                String str = TAG;
                SDKLog.b(str, next.width + "-" + next.height);
            }
        }
        Camera.Parameters parameters = camera.getParameters();
        Camera.Size calculatePerfectSize = calculatePerfectSize(parameters.getSupportedPictureSizes(), i, i2);
        String str2 = TAG;
        SDKLog.b(str2, "calculated : " + calculatePerfectSize.width + "-" + calculatePerfectSize.height);
        String str3 = TAG;
        SDKLog.b(str3, "expectWidth : " + i + "-" + i2);
        parameters.setPictureSize(calculatePerfectSize.width, calculatePerfectSize.height);
        camera.setParameters(parameters);
    }

    public static Camera.Size getPictureSize() {
        if (mCamera != null) {
            return mCamera.getParameters().getPictureSize();
        }
        return null;
    }

    public static List<Camera.Size> getSupportedPictureSizes() {
        if (mCamera != null) {
            return mCamera.getParameters().getSupportedPictureSizes();
        }
        return null;
    }

    public static Camera.Size calculatePerfectSize(List<Camera.Size> list, int i, int i2) {
        sortList(list);
        Camera.Size size = list.get(0);
        double d = Double.MAX_VALUE;
        for (Camera.Size next : list) {
            String str = TAG;
            SDKLog.b(str, "calculatePerfectSize" + next.width + "-" + next.height);
            double d2 = (double) next.width;
            double d3 = (double) next.height;
            Double.isNaN(d2);
            Double.isNaN(d3);
            double d4 = d2 / d3;
            double abs = Math.abs(d4 - rate);
            if (d4 <= 1.5d) {
                return next;
            }
            if (next.width < 1080) {
                break;
            } else if (abs < d) {
                size = next;
                d = abs;
            }
        }
        return size;
    }

    private static void sortList(List<Camera.Size> list) {
        Collections.sort(list, new Comparator<Camera.Size>() {
            public int compare(Camera.Size size, Camera.Size size2) {
                if (size.width < size2.width) {
                    return 1;
                }
                return size.width > size2.width ? -1 : 0;
            }
        });
    }

    public static int chooseFixedPreviewFps(Camera.Parameters parameters, int i) {
        for (int[] next : parameters.getSupportedPreviewFpsRange()) {
            if (next[0] == next[1] && next[0] == i) {
                parameters.setPreviewFpsRange(next[0], next[1]);
                return next[0];
            }
        }
        int[] iArr = new int[2];
        parameters.getPreviewFpsRange(iArr);
        if (iArr[0] == iArr[1]) {
            return iArr[0];
        }
        return iArr[1] / 2;
    }

    public static int calculateCameraPreviewOrientation(Activity activity) {
        int i;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraID, cameraInfo);
        int i2 = 0;
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
        }
        String str = TAG;
        SDKLog.b(str, "degrees=" + i2);
        String str2 = TAG;
        SDKLog.b(str2, "info.orientation=" + cameraInfo.orientation);
        if (cameraInfo.facing == 1) {
            i = (360 - ((cameraInfo.orientation + i2) % 360)) % 360;
        } else {
            i = ((cameraInfo.orientation - i2) + 360) % 360;
        }
        mOrientation = i;
        return i;
    }

    public static int getCameraID() {
        return mCameraID;
    }

    public static int getPreviewOrientation() {
        return mOrientation;
    }

    public static int getCameraPreviewThousandFps() {
        return mCameraPreviewFps;
    }
}
