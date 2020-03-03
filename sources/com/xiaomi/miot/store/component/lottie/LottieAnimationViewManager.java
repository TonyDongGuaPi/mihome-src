package com.xiaomi.miot.store.component.lottie;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.taobao.weex.common.Constants;
import java.util.Map;

public class LottieAnimationViewManager extends SimpleViewManager<LottieAnimationView> {
    private static final int COMMAND_PLAY = 1;
    private static final int COMMAND_RESET = 2;
    private static final String REACT_CLASS = "LottieAnimationView";
    private static final String TAG = "LottieAnimationViewManager";
    private static final int VERSION = 1;

    public String getName() {
        return REACT_CLASS;
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapBuilder.builder().put("VERSION", 1).build();
    }

    public LottieAnimationView createViewInstance(ThemedReactContext themedReactContext) {
        LottieAnimationView lottieAnimationView = new LottieAnimationView(themedReactContext);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return lottieAnimationView;
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(Constants.Value.PLAY, 1, "reset", 2);
    }

    public void receiveCommand(final LottieAnimationView lottieAnimationView, int i, final ReadableArray readableArray) {
        switch (i) {
            case 1:
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        int i = readableArray.getInt(0);
                        int i2 = readableArray.getInt(1);
                        if (!(i == -1 || i2 == -1)) {
                            lottieAnimationView.setMinAndMaxFrame(readableArray.getInt(0), readableArray.getInt(1));
                        }
                        if (ViewCompat.isAttachedToWindow(lottieAnimationView)) {
                            lottieAnimationView.setProgress(0.0f);
                            lottieAnimationView.playAnimation();
                            return;
                        }
                        lottieAnimationView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                            public void onViewAttachedToWindow(View view) {
                                LottieAnimationView lottieAnimationView = (LottieAnimationView) view;
                                lottieAnimationView.setProgress(0.0f);
                                lottieAnimationView.playAnimation();
                                lottieAnimationView.removeOnAttachStateChangeListener(this);
                            }

                            public void onViewDetachedFromWindow(View view) {
                                lottieAnimationView.removeOnAttachStateChangeListener(this);
                            }
                        });
                    }
                }, 180);
                return;
            case 2:
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            lottieAnimationView.cancelAnimation();
                            lottieAnimationView.setProgress(0.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "sourceName")
    public void setSourceName(LottieAnimationView lottieAnimationView, String str) {
        lottieAnimationView.setAnimation(str);
    }

    @ReactProp(name = "sourceJson")
    public void setSourceJson(LottieAnimationView lottieAnimationView, String str) {
        try {
            lottieAnimationView.setAnimationFromJson(str);
        } catch (Exception e) {
            Log.e(TAG, "setSourceJsonError", e);
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(LottieAnimationView lottieAnimationView, String str) {
        if (PlaceFields.COVER.equals(str)) {
            lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if ("contain".equals(str)) {
            lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else if ("center".equals(str)) {
            lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER);
        }
    }

    @ReactProp(name = "progress")
    public void setProgress(LottieAnimationView lottieAnimationView, float f) {
        lottieAnimationView.setProgress(f);
    }

    @ReactProp(name = "speed")
    public void setSpeed(LottieAnimationView lottieAnimationView, double d) {
        lottieAnimationView.setSpeed((float) d);
    }

    @ReactProp(name = "loop")
    public void setLoop(LottieAnimationView lottieAnimationView, boolean z) {
        lottieAnimationView.setRepeatCount(z ? -1 : 0);
    }

    @ReactProp(name = "hardwareAccelerationAndroid")
    public void setHardwareAcceleration(LottieAnimationView lottieAnimationView, boolean z) {
        lottieAnimationView.useHardwareAcceleration(z);
    }

    @ReactProp(name = "imageAssetsFolder")
    public void setImageAssetsFolder(LottieAnimationView lottieAnimationView, String str) {
        lottieAnimationView.setImageAssetsFolder(str);
    }

    @ReactProp(name = "enableMergePathsAndroidForKitKatAndAbove")
    public void setEnableMergePaths(LottieAnimationView lottieAnimationView, boolean z) {
        lottieAnimationView.enableMergePathsForKitKatAndAbove(z);
    }
}
