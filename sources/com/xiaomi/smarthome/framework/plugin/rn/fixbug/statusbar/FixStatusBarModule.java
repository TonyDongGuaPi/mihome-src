package com.xiaomi.smarthome.framework.plugin.rn.fixbug.statusbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.WindowInsets;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.uimanager.PixelUtil;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.util.Map;
import javax.annotation.Nullable;

public class FixStatusBarModule extends ReactContextBaseJavaModule {
    private static final String HEIGHT_KEY = "HEIGHT";

    public String getName() {
        return StatusBarModule.NAME;
    }

    public FixStatusBarModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        int identifier = reactApplicationContext.getResources().getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android");
        float dIPFromPixel = identifier > 0 ? PixelUtil.toDIPFromPixel((float) reactApplicationContext.getResources().getDimensionPixelSize(identifier)) : 0.0f;
        FLog.i(ReactConstants.TAG, "StatusBarModule getConstants  height: " + dIPFromPixel);
        return MapBuilder.of(HEIGHT_KEY, Float.valueOf(dIPFromPixel));
    }

    @ReactMethod
    public void setColor(int i, boolean z) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule setColor: Ignored status bar change, current activity is null.");
        } else if (Build.VERSION.SDK_INT >= 21) {
            FLog.i(ReactConstants.TAG, "StatusBarModule: animated=" + z);
            final boolean z2 = z;
            final int i2 = i;
            UiThreadUtil.runOnUiThread(new GuardedRunnable(getReactApplicationContext()) {
                @TargetApi(21)
                public void runGuarded() {
                    currentActivity.getWindow().addFlags(Integer.MIN_VALUE);
                    if (z2) {
                        int statusBarColor = currentActivity.getWindow().getStatusBarColor();
                        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(statusBarColor), Integer.valueOf(i2)});
                        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                currentActivity.getWindow().setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                            }
                        });
                        ofObject.setDuration(300).setStartDelay(0);
                        ofObject.start();
                        return;
                    }
                    currentActivity.getWindow().setStatusBarColor(i2);
                }
            });
        } else {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP, cannot support setColor");
        }
    }

    @ReactMethod
    public void setTranslucent(final boolean z) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule setTranslucent: Ignored status bar change, current activity is null.");
        } else if (Build.VERSION.SDK_INT >= 21) {
            FLog.i(ReactConstants.TAG, "StatusBarModule: translucent=" + z);
            UiThreadUtil.runOnUiThread(new GuardedRunnable(getReactApplicationContext()) {
                @TargetApi(21)
                public void runGuarded() {
                    View decorView = currentActivity.getWindow().getDecorView();
                    if (z) {
                        decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                            public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                                WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
                                return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
                            }
                        });
                    } else {
                        decorView.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
                    }
                    ViewCompat.requestApplyInsets(decorView);
                }
            });
        } else {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP, cannot support setTranslucent");
        }
    }

    @ReactMethod
    public void setHidden(final boolean z) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule setHidden: Ignored status bar change, current activity is null.");
            return;
        }
        FLog.i(ReactConstants.TAG, "StatusBarModule: hidden=" + z);
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                if (z) {
                    currentActivity.getWindow().addFlags(1024);
                    currentActivity.getWindow().clearFlags(2048);
                    return;
                }
                currentActivity.getWindow().addFlags(2048);
                currentActivity.getWindow().clearFlags(1024);
            }
        });
    }

    @ReactMethod
    public void setStyle(final String str) {
        final Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule  setStyle: Ignored status bar change, current activity is null.");
        } else if (Build.VERSION.SDK_INT >= 23) {
            FLog.i(ReactConstants.TAG, "StatusBarModule: style=" + str);
            UiThreadUtil.runOnUiThread(new Runnable() {
                @TargetApi(23)
                public void run() {
                    currentActivity.getWindow().getDecorView().setSystemUiVisibility(str.equals("dark-content") ? 8192 : 0);
                }
            });
        } else {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Build.VERSION.SDK_INT < Build.VERSION_CODES.M, cannot support setStyle");
        }
    }
}
