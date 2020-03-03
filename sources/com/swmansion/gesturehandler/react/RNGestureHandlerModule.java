package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.swmansion.gesturehandler.FlingGestureHandler;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.LongPressGestureHandler;
import com.swmansion.gesturehandler.NativeViewGestureHandler;
import com.swmansion.gesturehandler.OnTouchEventListener;
import com.swmansion.gesturehandler.PanGestureHandler;
import com.swmansion.gesturehandler.PinchGestureHandler;
import com.swmansion.gesturehandler.RotationGestureHandler;
import com.swmansion.gesturehandler.TapGestureHandler;
import com.taobao.weex.common.Constants;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ReactModule(name = "RNGestureHandlerModule")
public class RNGestureHandlerModule extends ReactContextBaseJavaModule {
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_HIT_SLOP = "hitSlop";
    private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
    private static final String KEY_HIT_SLOP_HEIGHT = "height";
    private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
    private static final String KEY_HIT_SLOP_LEFT = "left";
    private static final String KEY_HIT_SLOP_RIGHT = "right";
    private static final String KEY_HIT_SLOP_TOP = "top";
    private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
    private static final String KEY_HIT_SLOP_WIDTH = "width";
    private static final String KEY_LONG_PRESS_MAX_DIST = "maxDist";
    private static final String KEY_LONG_PRESS_MIN_DURATION_MS = "minDurationMs";
    private static final String KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION = "disallowInterruption";
    private static final String KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START = "shouldActivateOnStart";
    private static final String KEY_NUMBER_OF_POINTERS = "numberOfPointers";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
    private static final String KEY_PAN_AVG_TOUCHES = "avgTouches";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
    private static final String KEY_PAN_MAX_POINTERS = "maxPointers";
    private static final String KEY_PAN_MIN_DIST = "minDist";
    private static final String KEY_PAN_MIN_POINTERS = "minPointers";
    private static final String KEY_PAN_MIN_VELOCITY = "minVelocity";
    private static final String KEY_PAN_MIN_VELOCITY_X = "minVelocityX";
    private static final String KEY_PAN_MIN_VELOCITY_Y = "minVelocityY";
    private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";
    private static final String KEY_TAP_MAX_DELAY_MS = "maxDelayMs";
    private static final String KEY_TAP_MAX_DELTA_X = "maxDeltaX";
    private static final String KEY_TAP_MAX_DELTA_Y = "maxDeltaY";
    private static final String KEY_TAP_MAX_DIST = "maxDist";
    private static final String KEY_TAP_MAX_DURATION_MS = "maxDurationMs";
    private static final String KEY_TAP_MIN_POINTERS = "minPointers";
    private static final String KEY_TAP_NUMBER_OF_TAPS = "numberOfTaps";
    public static final String MODULE_NAME = "RNGestureHandlerModule";
    /* access modifiers changed from: private */
    public List<Integer> mEnqueuedRootViewInit = new ArrayList();
    private OnTouchEventListener mEventListener = new OnTouchEventListener() {
        public void a(GestureHandler gestureHandler, MotionEvent motionEvent) {
            RNGestureHandlerModule.this.onTouchEvent(gestureHandler, motionEvent);
        }

        public void a(GestureHandler gestureHandler, int i, int i2) {
            RNGestureHandlerModule.this.onStateChange(gestureHandler, i, i2);
        }
    };
    private HandlerFactory[] mHandlerFactories = {new NativeViewGestureHandlerFactory(), new TapGestureHandlerFactory(), new LongPressGestureHandlerFactory(), new PanGestureHandlerFactory(), new PinchGestureHandlerFactory(), new RotationGestureHandlerFactory(), new FlingGestureHandlerFactory()};
    private RNGestureHandlerInteractionManager mInteractionManager = new RNGestureHandlerInteractionManager();
    private final RNGestureHandlerRegistry mRegistry = new RNGestureHandlerRegistry();
    private List<RNGestureHandlerRootHelper> mRoots = new ArrayList();

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void handleClearJSResponder() {
    }

    private static abstract class HandlerFactory<T extends GestureHandler> implements RNGestureHandlerEventDataExtractor<T> {
        public abstract Class<T> a();

        public abstract T b(Context context);

        public abstract String b();

        private HandlerFactory() {
        }

        public void a(T t, ReadableMap readableMap) {
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                t.a(readableMap.getBoolean(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE));
            }
            if (readableMap.hasKey("enabled")) {
                t.b(readableMap.getBoolean("enabled"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP)) {
                RNGestureHandlerModule.handleHitSlopProperty(t, readableMap);
            }
        }

        public void a(T t, WritableMap writableMap) {
            writableMap.putDouble(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS, (double) t.h());
        }
    }

    private static class NativeViewGestureHandlerFactory extends HandlerFactory<NativeViewGestureHandler> {
        public String b() {
            return "NativeViewGestureHandler";
        }

        private NativeViewGestureHandlerFactory() {
            super();
        }

        public Class<NativeViewGestureHandler> a() {
            return NativeViewGestureHandler.class;
        }

        /* renamed from: a */
        public NativeViewGestureHandler b(Context context) {
            return new NativeViewGestureHandler();
        }

        public void a(NativeViewGestureHandler nativeViewGestureHandler, ReadableMap readableMap) {
            super.a(nativeViewGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START)) {
                nativeViewGestureHandler.c(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION)) {
                nativeViewGestureHandler.d(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION));
            }
        }

        public void a(NativeViewGestureHandler nativeViewGestureHandler, WritableMap writableMap) {
            super.a(nativeViewGestureHandler, writableMap);
            writableMap.putBoolean("pointerInside", nativeViewGestureHandler.i());
        }
    }

    private static class TapGestureHandlerFactory extends HandlerFactory<TapGestureHandler> {
        public String b() {
            return "TapGestureHandler";
        }

        private TapGestureHandlerFactory() {
            super();
        }

        public Class<TapGestureHandler> a() {
            return TapGestureHandler.class;
        }

        /* renamed from: a */
        public TapGestureHandler b(Context context) {
            return new TapGestureHandler();
        }

        public void a(TapGestureHandler tapGestureHandler, ReadableMap readableMap) {
            super.a(tapGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS)) {
                tapGestureHandler.a(readableMap.getInt(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS)) {
                tapGestureHandler.b((long) readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS)) {
                tapGestureHandler.a((long) readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)) {
                tapGestureHandler.b(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)) {
                tapGestureHandler.c(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)));
            }
            if (readableMap.hasKey("maxDist")) {
                tapGestureHandler.d(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
            if (readableMap.hasKey("minPointers")) {
                tapGestureHandler.b(readableMap.getInt("minPointers"));
            }
        }

        public void a(TapGestureHandler tapGestureHandler, WritableMap writableMap) {
            super.a(tapGestureHandler, writableMap);
            writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(tapGestureHandler.t()));
            writableMap.putDouble(Constants.Name.Y, (double) PixelUtil.toDIPFromPixel(tapGestureHandler.u()));
            writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(tapGestureHandler.r()));
            writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(tapGestureHandler.s()));
        }
    }

    private static class LongPressGestureHandlerFactory extends HandlerFactory<LongPressGestureHandler> {
        public String b() {
            return "LongPressGestureHandler";
        }

        private LongPressGestureHandlerFactory() {
            super();
        }

        public Class<LongPressGestureHandler> a() {
            return LongPressGestureHandler.class;
        }

        /* renamed from: a */
        public LongPressGestureHandler b(Context context) {
            return new LongPressGestureHandler(context);
        }

        public void a(LongPressGestureHandler longPressGestureHandler, ReadableMap readableMap) {
            super.a(longPressGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS)) {
                longPressGestureHandler.a((long) readableMap.getInt(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS));
            }
            if (readableMap.hasKey("maxDist")) {
                longPressGestureHandler.b(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
        }

        public void a(LongPressGestureHandler longPressGestureHandler, WritableMap writableMap) {
            super.a(longPressGestureHandler, writableMap);
            writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(longPressGestureHandler.t()));
            writableMap.putDouble(Constants.Name.Y, (double) PixelUtil.toDIPFromPixel(longPressGestureHandler.u()));
            writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(longPressGestureHandler.r()));
            writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(longPressGestureHandler.s()));
        }
    }

    private static class PanGestureHandlerFactory extends HandlerFactory<PanGestureHandler> {
        public String b() {
            return "PanGestureHandler";
        }

        private PanGestureHandlerFactory() {
            super();
        }

        public Class<PanGestureHandler> a() {
            return PanGestureHandler.class;
        }

        /* renamed from: a */
        public PanGestureHandler b(Context context) {
            return new PanGestureHandler(context);
        }

        public void a(PanGestureHandler panGestureHandler, ReadableMap readableMap) {
            boolean z;
            super.a(panGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)) {
                panGestureHandler.b(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)));
                z = true;
            } else {
                z = false;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)) {
                panGestureHandler.c(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)) {
                panGestureHandler.d(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)) {
                panGestureHandler.e(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)) {
                panGestureHandler.f(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)) {
                panGestureHandler.g(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)) {
                panGestureHandler.h(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)) {
                panGestureHandler.i(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)) {
                panGestureHandler.k(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)) {
                panGestureHandler.l(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)) {
                panGestureHandler.m(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_DIST)) {
                panGestureHandler.j(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_DIST)));
            } else if (z) {
                panGestureHandler.j(Float.MAX_VALUE);
            }
            if (readableMap.hasKey("minPointers")) {
                panGestureHandler.a(readableMap.getInt("minPointers"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS)) {
                panGestureHandler.b(readableMap.getInt(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES)) {
                panGestureHandler.c(readableMap.getBoolean(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES));
            }
        }

        public void a(PanGestureHandler panGestureHandler, WritableMap writableMap) {
            super.a(panGestureHandler, writableMap);
            writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(panGestureHandler.t()));
            writableMap.putDouble(Constants.Name.Y, (double) PixelUtil.toDIPFromPixel(panGestureHandler.u()));
            writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(panGestureHandler.r()));
            writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(panGestureHandler.s()));
            writableMap.putDouble("translationX", (double) PixelUtil.toDIPFromPixel(panGestureHandler.v()));
            writableMap.putDouble("translationY", (double) PixelUtil.toDIPFromPixel(panGestureHandler.w()));
            writableMap.putDouble("velocityX", (double) PixelUtil.toDIPFromPixel(panGestureHandler.x()));
            writableMap.putDouble("velocityY", (double) PixelUtil.toDIPFromPixel(panGestureHandler.y()));
        }
    }

    private static class PinchGestureHandlerFactory extends HandlerFactory<PinchGestureHandler> {
        public String b() {
            return "PinchGestureHandler";
        }

        private PinchGestureHandlerFactory() {
            super();
        }

        public Class<PinchGestureHandler> a() {
            return PinchGestureHandler.class;
        }

        /* renamed from: a */
        public PinchGestureHandler b(Context context) {
            return new PinchGestureHandler();
        }

        public void a(PinchGestureHandler pinchGestureHandler, WritableMap writableMap) {
            super.a(pinchGestureHandler, writableMap);
            writableMap.putDouble("scale", pinchGestureHandler.v());
            writableMap.putDouble("focalX", (double) PixelUtil.toDIPFromPixel(pinchGestureHandler.x()));
            writableMap.putDouble("focalY", (double) PixelUtil.toDIPFromPixel(pinchGestureHandler.y()));
            writableMap.putDouble("velocity", pinchGestureHandler.w());
        }
    }

    private static class FlingGestureHandlerFactory extends HandlerFactory<FlingGestureHandler> {
        public String b() {
            return "FlingGestureHandler";
        }

        private FlingGestureHandlerFactory() {
            super();
        }

        public Class<FlingGestureHandler> a() {
            return FlingGestureHandler.class;
        }

        /* renamed from: a */
        public FlingGestureHandler b(Context context) {
            return new FlingGestureHandler();
        }

        public void a(FlingGestureHandler flingGestureHandler, ReadableMap readableMap) {
            super.a(flingGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                flingGestureHandler.a(readableMap.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS));
            }
            if (readableMap.hasKey("direction")) {
                flingGestureHandler.b(readableMap.getInt("direction"));
            }
        }

        public void a(FlingGestureHandler flingGestureHandler, WritableMap writableMap) {
            super.a(flingGestureHandler, writableMap);
            writableMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(flingGestureHandler.t()));
            writableMap.putDouble(Constants.Name.Y, (double) PixelUtil.toDIPFromPixel(flingGestureHandler.u()));
            writableMap.putDouble("absoluteX", (double) PixelUtil.toDIPFromPixel(flingGestureHandler.r()));
            writableMap.putDouble("absoluteY", (double) PixelUtil.toDIPFromPixel(flingGestureHandler.s()));
        }
    }

    private static class RotationGestureHandlerFactory extends HandlerFactory<RotationGestureHandler> {
        public String b() {
            return "RotationGestureHandler";
        }

        private RotationGestureHandlerFactory() {
            super();
        }

        public Class<RotationGestureHandler> a() {
            return RotationGestureHandler.class;
        }

        /* renamed from: a */
        public RotationGestureHandler b(Context context) {
            return new RotationGestureHandler();
        }

        public void a(RotationGestureHandler rotationGestureHandler, WritableMap writableMap) {
            super.a(rotationGestureHandler, writableMap);
            writableMap.putDouble(ViewProps.ROTATION, rotationGestureHandler.v());
            writableMap.putDouble("anchorX", (double) PixelUtil.toDIPFromPixel(rotationGestureHandler.x()));
            writableMap.putDouble("anchorY", (double) PixelUtil.toDIPFromPixel(rotationGestureHandler.y()));
            writableMap.putDouble("velocity", rotationGestureHandler.w());
        }
    }

    public RNGestureHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void createGestureHandler(String str, int i, ReadableMap readableMap) {
        for (HandlerFactory handlerFactory : this.mHandlerFactories) {
            if (handlerFactory.b().equals(str)) {
                GestureHandler b = handlerFactory.b(getReactApplicationContext());
                b.c(i);
                b.a(this.mEventListener);
                this.mRegistry.a(b);
                this.mInteractionManager.a(b, readableMap);
                handlerFactory.a(b, readableMap);
                return;
            }
        }
        throw new JSApplicationIllegalArgumentException("Invalid handler name " + str);
    }

    @ReactMethod
    public void attachGestureHandler(int i, int i2) {
        tryInitializeHandlerForReactRootView(i2);
        if (!this.mRegistry.a(i, i2)) {
            throw new JSApplicationIllegalArgumentException("Handler with tag " + i + " does not exists");
        }
    }

    @ReactMethod
    public void updateGestureHandler(int i, ReadableMap readableMap) {
        HandlerFactory findFactoryForHandler;
        GestureHandler a2 = this.mRegistry.a(i);
        if (a2 != null && (findFactoryForHandler = findFactoryForHandler(a2)) != null) {
            this.mInteractionManager.a(i);
            this.mInteractionManager.a(a2, readableMap);
            findFactoryForHandler.a(a2, readableMap);
        }
    }

    @ReactMethod
    public void dropGestureHandler(int i) {
        this.mInteractionManager.a(i);
        this.mRegistry.b(i);
    }

    @ReactMethod
    public void handleSetJSResponder(int i, boolean z) {
        RNGestureHandlerRootHelper findRootHelperForViewAncestor;
        if (this.mRegistry != null && (findRootHelperForViewAncestor = findRootHelperForViewAncestor(i)) != null) {
            findRootHelperForViewAncestor.a(i, z);
        }
    }

    @Nullable
    public Map getConstants() {
        return MapBuilder.of("State", MapBuilder.of("UNDETERMINED", 0, "BEGAN", 2, "ACTIVE", 4, "CANCELLED", 3, "FAILED", 1, "END", 5), "Direction", MapBuilder.of("RIGHT", 1, "LEFT", 2, Constant.TOKENIZATION_PROVIDER, 4, "DOWN", 8));
    }

    public RNGestureHandlerRegistry getRegistry() {
        return this.mRegistry;
    }

    public void onCatalystInstanceDestroy() {
        this.mRegistry.a();
        this.mInteractionManager.a();
        synchronized (this.mRoots) {
            while (!this.mRoots.isEmpty()) {
                int size = this.mRoots.size();
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRoots.get(0);
                ReactRootView b = rNGestureHandlerRootHelper.b();
                if (b instanceof RNGestureHandlerEnabledRootView) {
                    ((RNGestureHandlerEnabledRootView) b).tearDown();
                } else {
                    rNGestureHandlerRootHelper.a();
                }
                if (this.mRoots.size() >= size) {
                    throw new IllegalStateException("Expected root helper to get unregistered while tearing down");
                }
            }
        }
        super.onCatalystInstanceDestroy();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r5 = r4.mEnqueuedRootViewInit;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        if (r4.mEnqueuedRootViewInit.contains(java.lang.Integer.valueOf(r1)) == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r4.mEnqueuedRootViewInit.add(java.lang.Integer.valueOf(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        r0.addUIBlock(new com.swmansion.gesturehandler.react.RNGestureHandlerModule.AnonymousClass2(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void tryInitializeHandlerForReactRootView(int r5) {
        /*
            r4 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r4.getReactApplicationContext()
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r1 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r0 = r0.getNativeModule(r1)
            com.facebook.react.uimanager.UIManagerModule r0 = (com.facebook.react.uimanager.UIManagerModule) r0
            int r1 = r0.resolveRootTagFromReactTag(r5)
            r2 = 1
            if (r1 < r2) goto L_0x0061
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r2 = r4.mRoots
            monitor-enter(r2)
            r5 = 0
        L_0x0017:
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r3 = r4.mRoots     // Catch:{ all -> 0x005e }
            int r3 = r3.size()     // Catch:{ all -> 0x005e }
            if (r5 >= r3) goto L_0x0036
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r3 = r4.mRoots     // Catch:{ all -> 0x005e }
            java.lang.Object r3 = r3.get(r5)     // Catch:{ all -> 0x005e }
            com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper r3 = (com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper) r3     // Catch:{ all -> 0x005e }
            com.facebook.react.ReactRootView r3 = r3.b()     // Catch:{ all -> 0x005e }
            int r3 = r3.getRootViewTag()     // Catch:{ all -> 0x005e }
            if (r3 != r1) goto L_0x0033
            monitor-exit(r2)     // Catch:{ all -> 0x005e }
            return
        L_0x0033:
            int r5 = r5 + 1
            goto L_0x0017
        L_0x0036:
            monitor-exit(r2)     // Catch:{ all -> 0x005e }
            java.util.List<java.lang.Integer> r5 = r4.mEnqueuedRootViewInit
            monitor-enter(r5)
            java.util.List<java.lang.Integer> r2 = r4.mEnqueuedRootViewInit     // Catch:{ all -> 0x005b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x005b }
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x005b }
            if (r2 == 0) goto L_0x0048
            monitor-exit(r5)     // Catch:{ all -> 0x005b }
            return
        L_0x0048:
            java.util.List<java.lang.Integer> r2 = r4.mEnqueuedRootViewInit     // Catch:{ all -> 0x005b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x005b }
            r2.add(r3)     // Catch:{ all -> 0x005b }
            monitor-exit(r5)     // Catch:{ all -> 0x005b }
            com.swmansion.gesturehandler.react.RNGestureHandlerModule$2 r5 = new com.swmansion.gesturehandler.react.RNGestureHandlerModule$2
            r5.<init>(r1)
            r0.addUIBlock(r5)
            return
        L_0x005b:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x005b }
            throw r0
        L_0x005e:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005e }
            throw r5
        L_0x0061:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Could find root view for a given ancestor with tag "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerModule.tryInitializeHandlerForReactRootView(int):void");
    }

    public void registerRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        synchronized (this.mRoots) {
            if (!this.mRoots.contains(rNGestureHandlerRootHelper)) {
                this.mRoots.add(rNGestureHandlerRootHelper);
            } else {
                throw new IllegalStateException("Root helper" + rNGestureHandlerRootHelper + " already registered");
            }
        }
    }

    public void unregisterRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        synchronized (this.mRoots) {
            this.mRoots.remove(rNGestureHandlerRootHelper);
        }
    }

    @Nullable
    private RNGestureHandlerRootHelper findRootHelperForViewAncestor(int i) {
        int resolveRootTagFromReactTag = ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).resolveRootTagFromReactTag(i);
        if (resolveRootTagFromReactTag < 1) {
            return null;
        }
        synchronized (this.mRoots) {
            for (int i2 = 0; i2 < this.mRoots.size(); i2++) {
                RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRoots.get(i2);
                if (rNGestureHandlerRootHelper.b().getRootViewTag() == resolveRootTagFromReactTag) {
                    return rNGestureHandlerRootHelper;
                }
            }
            return null;
        }
    }

    @Nullable
    private HandlerFactory findFactoryForHandler(GestureHandler gestureHandler) {
        for (HandlerFactory handlerFactory : this.mHandlerFactories) {
            if (handlerFactory.a().equals(gestureHandler.getClass())) {
                return handlerFactory;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onTouchEvent(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (gestureHandler.d() >= 0 && gestureHandler.k() == 4) {
            ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(RNGestureHandlerEvent.a(gestureHandler, findFactoryForHandler(gestureHandler)));
        }
    }

    /* access modifiers changed from: private */
    public void onStateChange(GestureHandler gestureHandler, int i, int i2) {
        if (gestureHandler.d() >= 0) {
            ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(RNGestureHandlerStateChangeEvent.a(gestureHandler, i, i2, findFactoryForHandler(gestureHandler)));
        }
    }

    /* access modifiers changed from: private */
    public static void handleHitSlopProperty(GestureHandler gestureHandler, ReadableMap readableMap) {
        float f;
        float f2;
        float f3;
        float f4;
        if (readableMap.getType(KEY_HIT_SLOP) == ReadableType.Number) {
            float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble(KEY_HIT_SLOP));
            gestureHandler.a(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
            return;
        }
        ReadableMap map = readableMap.getMap(KEY_HIT_SLOP);
        if (map.hasKey("horizontal")) {
            f2 = PixelUtil.toPixelFromDIP(map.getDouble("horizontal"));
            f = f2;
        } else {
            f2 = Float.NaN;
            f = Float.NaN;
        }
        if (map.hasKey(KEY_HIT_SLOP_VERTICAL)) {
            f4 = PixelUtil.toPixelFromDIP(map.getDouble(KEY_HIT_SLOP_VERTICAL));
            f3 = f4;
        } else {
            f4 = Float.NaN;
            f3 = Float.NaN;
        }
        if (map.hasKey("left")) {
            f2 = PixelUtil.toPixelFromDIP(map.getDouble("left"));
        }
        float f5 = f2;
        if (map.hasKey("top")) {
            f4 = PixelUtil.toPixelFromDIP(map.getDouble("top"));
        }
        float f6 = f4;
        if (map.hasKey("right")) {
            f = PixelUtil.toPixelFromDIP(map.getDouble("right"));
        }
        float f7 = f;
        if (map.hasKey("bottom")) {
            f3 = PixelUtil.toPixelFromDIP(map.getDouble("bottom"));
        }
        gestureHandler.a(f5, f6, f7, f3, map.hasKey("width") ? PixelUtil.toPixelFromDIP(map.getDouble("width")) : Float.NaN, map.hasKey("height") ? PixelUtil.toPixelFromDIP(map.getDouble("height")) : Float.NaN);
    }
}
