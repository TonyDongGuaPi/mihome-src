package com.reactnative.camera;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.media.ExifInterface;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.cameraview.AspectRatio;
import com.google.zxing.BarcodeFormat;
import com.mi.global.shop.model.Tags;
import com.reactnative.camera.facedetector.RNFaceDetector;
import com.reactnative.camera.tasks.ResolveTakenPictureAsyncTask;
import com.reactnative.camera.utils.ScopedContext;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class CameraModule extends ReactContextBaseJavaModule {
    private static final String TAG = "CameraModule";
    public static final Map<String, Object> VALID_BARCODE_TYPES = Collections.unmodifiableMap(new HashMap<String, Object>() {
        {
            put("aztec", BarcodeFormat.AZTEC.toString());
            put("ean13", BarcodeFormat.EAN_13.toString());
            put("ean8", BarcodeFormat.EAN_8.toString());
            put("qr", BarcodeFormat.QR_CODE.toString());
            put("pdf417", BarcodeFormat.PDF_417.toString());
            put("upc_e", BarcodeFormat.UPC_E.toString());
            put("datamatrix", BarcodeFormat.DATA_MATRIX.toString());
            put("code39", BarcodeFormat.CODE_39.toString());
            put("code93", BarcodeFormat.CODE_93.toString());
            put("interleaved2of5", BarcodeFormat.ITF.toString());
            put("codabar", BarcodeFormat.CODABAR.toString());
            put("code128", BarcodeFormat.CODE_128.toString());
            put("maxicode", BarcodeFormat.MAXICODE.toString());
            put("rss14", BarcodeFormat.RSS_14.toString());
            put("rssexpanded", BarcodeFormat.RSS_EXPANDED.toString());
            put("upc_a", BarcodeFormat.UPC_A.toString());
            put("upc_ean", BarcodeFormat.UPC_EAN_EXTENSION.toString());
        }
    });
    static final int VIDEO_1080P = 1;
    static final int VIDEO_2160P = 0;
    static final int VIDEO_480P = 3;
    static final int VIDEO_4x3 = 4;
    static final int VIDEO_720P = 2;
    private ScopedContext mScopedContext;

    public String getName() {
        return "RNCameraModule";
    }

    public CameraModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mScopedContext = new ScopedContext(reactApplicationContext);
    }

    public ScopedContext getScopedContext() {
        return this.mScopedContext;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return Collections.unmodifiableMap(new HashMap<String, Object>() {
            {
                put("Type", a());
                put("FlashMode", b());
                put("AutoFocus", c());
                put(ExifInterface.TAG_WHITE_BALANCE, d());
                put("VideoQuality", e());
                put("BarCodeType", f());
                put("FaceDetection", Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("Mode", a());
                        put("Landmarks", c());
                        put("Classifications", b());
                    }

                    private Map<String, Object> a() {
                        return Collections.unmodifiableMap(new HashMap<String, Object>() {
                            {
                                put("fast", Integer.valueOf(RNFaceDetector.f));
                                put("accurate", Integer.valueOf(RNFaceDetector.e));
                            }
                        });
                    }

                    private Map<String, Object> b() {
                        return Collections.unmodifiableMap(new HashMap<String, Object>() {
                            {
                                put(Tags.BaiduLbs.ADDRTYPE, Integer.valueOf(RNFaceDetector.f8676a));
                                put("none", Integer.valueOf(RNFaceDetector.b));
                            }
                        });
                    }

                    private Map<String, Object> c() {
                        return Collections.unmodifiableMap(new HashMap<String, Object>() {
                            {
                                put(Tags.BaiduLbs.ADDRTYPE, Integer.valueOf(RNFaceDetector.c));
                                put("none", Integer.valueOf(RNFaceDetector.d));
                            }
                        });
                    }
                }));
            }

            private Map<String, Object> a() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("front", 1);
                        put("back", 0);
                    }
                });
            }

            private Map<String, Object> b() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("off", 0);
                        put("on", 1);
                        put("auto", 3);
                        put("torch", 2);
                    }
                });
            }

            private Map<String, Object> c() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("on", true);
                        put("off", false);
                    }
                });
            }

            private Map<String, Object> d() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("auto", 0);
                        put("cloudy", 1);
                        put("sunny", 2);
                        put("shadow", 3);
                        put("fluorescent", 4);
                        put("incandescent", 5);
                    }
                });
            }

            private Map<String, Object> e() {
                return Collections.unmodifiableMap(new HashMap<String, Object>() {
                    {
                        put("2160p", 0);
                        put("1080p", 1);
                        put("720p", 2);
                        put("480p", 3);
                        put("4:3", 4);
                    }
                });
            }

            private Map<String, Object> f() {
                return CameraModule.VALID_BARCODE_TYPES;
            }
        });
    }

    @ReactMethod
    public void takePicture(ReadableMap readableMap, int i, Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final File a2 = this.mScopedContext.a();
        final int i2 = i;
        final ReadableMap readableMap2 = readableMap;
        final Promise promise2 = promise;
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i2);
                try {
                    if (Build.FINGERPRINT.contains(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) {
                        Bitmap b2 = RNCameraViewHelper.b(rNCameraView.getWidth(), rNCameraView.getHeight());
                        ByteBuffer allocate = ByteBuffer.allocate(b2.getRowBytes() * b2.getHeight());
                        b2.copyPixelsToBuffer(allocate);
                        new ResolveTakenPictureAsyncTask(allocate.array(), promise2, readableMap2).execute(new Void[0]);
                    } else if (rNCameraView.isCameraOpened()) {
                        rNCameraView.takePicture(readableMap2, promise2, a2);
                    } else {
                        promise2.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                    }
                } catch (Exception unused) {
                    promise2.reject("E_CAMERA_BAD_VIEWTAG", "takePictureAsync: Expected a Camera component");
                }
            }
        });
    }

    @ReactMethod
    public void record(ReadableMap readableMap, int i, Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final File a2 = this.mScopedContext.a();
        final int i2 = i;
        final ReadableMap readableMap2 = readableMap;
        final Promise promise2 = promise;
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i2);
                    if (rNCameraView.isCameraOpened()) {
                        rNCameraView.record(readableMap2, promise2, a2);
                    } else {
                        promise2.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                    }
                } catch (Exception unused) {
                    promise2.reject("E_CAMERA_BAD_VIEWTAG", "recordAsync: Expected a Camera component");
                }
            }
        });
    }

    @ReactMethod
    public void stopRecording(final int i) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    if (rNCameraView.isCameraOpened()) {
                        rNCameraView.stopRecording();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @ReactMethod
    public void getSupportedRatios(final int i, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() {
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    RNCameraView rNCameraView = (RNCameraView) nativeViewHierarchyManager.resolveView(i);
                    WritableArray createArray = Arguments.createArray();
                    if (rNCameraView.isCameraOpened()) {
                        for (AspectRatio aspectRatio : rNCameraView.getSupportedAspectRatios()) {
                            createArray.pushString(aspectRatio.toString());
                        }
                        promise.resolve(createArray);
                        return;
                    }
                    promise.reject("E_CAMERA_UNAVAILABLE", "Camera is not running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
