package com.projectseptember.RNGL;

import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.facebook.imagepipeline.core.DefaultExecutorSupplier;
import com.facebook.imagepipeline.core.ExecutorSupplier;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.taobao.weex.annotation.JSMethod;
import java.util.Locale;
import java.util.Map;

public class GLCanvasManager extends SimpleViewManager<GLCanvas> {
    public static final int COMMAND_CAPTURE_FRAME = 1;
    public static final String REACT_CLASS = "GLCanvas";
    private ExecutorSupplier executorSupplier;

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "pixelRatio")
    public void setPixelRatio(GLCanvas gLCanvas, float f) {
        gLCanvas.setPixelRatio(f);
    }

    @ReactProp(name = "nbContentTextures")
    public void setNbContentTextures(GLCanvas gLCanvas, int i) {
        gLCanvas.setNbContentTextures(i);
    }

    @ReactProp(name = "renderId")
    public void setRenderId(GLCanvas gLCanvas, int i) {
        gLCanvas.setRenderId(i);
    }

    @ReactProp(name = "autoRedraw")
    public void setAutoRedraw(GLCanvas gLCanvas, boolean z) {
        gLCanvas.setAutoRedraw(z);
    }

    @ReactProp(name = "overlay")
    public void setZOrderMediaOverlay(GLCanvas gLCanvas, boolean z) {
        gLCanvas.setZOrderMediaOverlay(z);
    }

    @ReactProp(name = "setZOrderOnTop")
    public void setZOrderOnTop(GLCanvas gLCanvas, boolean z) {
        gLCanvas.setZOrderOnTop(z);
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(GLCanvas gLCanvas, Integer num) {
        gLCanvas.setBackgroundColor(num.intValue());
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(GLCanvas gLCanvas, @Nullable String str) {
        if (str != null) {
            gLCanvas.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", JSMethod.NOT_SET)));
        }
    }

    @ReactProp(name = "data")
    public void setData(GLCanvas gLCanvas, @Nullable ReadableMap readableMap) {
        gLCanvas.setData(readableMap == null ? null : GLData.a(readableMap));
    }

    @ReactProp(name = "imagesToPreload")
    public void setImagesToPreload(GLCanvas gLCanvas, @Nullable ReadableArray readableArray) {
        gLCanvas.setImagesToPreload(readableArray);
    }

    public GLCanvas createViewInstance(ThemedReactContext themedReactContext) {
        if (this.executorSupplier == null) {
            this.executorSupplier = new DefaultExecutorSupplier(new PoolFactory(PoolConfig.newBuilder().build()).getFlexByteArrayPoolMaxNumThreads());
        }
        return new GLCanvas(themedReactContext, this.executorSupplier);
    }

    public void receiveCommand(GLCanvas gLCanvas, int i, @Nullable ReadableArray readableArray) {
        Assertions.assertNotNull(gLCanvas);
        Assertions.assertNotNull(readableArray);
        if (i == 1) {
            gLCanvas.requestCaptureFrame(CaptureConfig.a(readableArray.getMap(0)));
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", new Object[]{Integer.valueOf(i), getClass().getSimpleName()}));
        }
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("captureFrame", MapBuilder.of("registrationName", "onGLCaptureFrame"), "load", MapBuilder.of("registrationName", "onGLLoad"), NotificationCompat.CATEGORY_PROGRESS, MapBuilder.of("registrationName", "onGLProgress"));
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("captureFrame", 1);
    }
}
