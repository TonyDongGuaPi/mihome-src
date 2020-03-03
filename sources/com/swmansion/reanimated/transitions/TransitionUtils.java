package com.swmansion.reanimated.transitions;

import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionPropagation;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.taobao.weex.common.Constants;

@RequiresApi(api = 26)
class TransitionUtils {
    TransitionUtils() {
    }

    @Nullable
    static Transition a(ReadableMap readableMap) {
        String string = readableMap.getString("type");
        if ("group".equals(string)) {
            return b(readableMap);
        }
        if ("in".equals(string)) {
            return c(readableMap);
        }
        if ("out".equals(string)) {
            return d(readableMap);
        }
        if (Constants.Event.CHANGE.equals(string)) {
            return e(readableMap);
        }
        throw new JSApplicationIllegalArgumentException("Unrecognized transition type " + string);
    }

    @Nullable
    private static Transition b(ReadableMap readableMap) {
        TransitionSet transitionSet = new TransitionSet();
        if (!readableMap.hasKey("sequence") || !readableMap.getBoolean("sequence")) {
            transitionSet.setOrdering(0);
        } else {
            transitionSet.setOrdering(1);
        }
        ReadableArray array = readableMap.getArray("transitions");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            Transition a2 = a(array.getMap(i));
            if (a2 != null) {
                transitionSet.addTransition(a2);
            }
        }
        return transitionSet;
    }

    static Visibility a(String str) {
        if (str == null || "none".equals(str)) {
            return null;
        }
        if ("fade".equals(str)) {
            return new Fade(3);
        }
        if ("scale".equals(str)) {
            return new Scale();
        }
        if ("slide-top".equals(str)) {
            return new Slide(48);
        }
        if ("slide-bottom".equals(str)) {
            return new Slide(80);
        }
        if ("slide-right".equals(str)) {
            return new Slide(5);
        }
        if ("slide-left".equals(str)) {
            return new Slide(3);
        }
        throw new JSApplicationIllegalArgumentException("Invalid transition type " + str);
    }

    private static Transition c(ReadableMap readableMap) {
        Visibility b = b(readableMap.getString("animation"));
        if (b == null) {
            return null;
        }
        b.setMode(1);
        a(b, readableMap);
        return b;
    }

    private static Transition d(ReadableMap readableMap) {
        Visibility b = b(readableMap.getString("animation"));
        if (b == null) {
            return null;
        }
        b.setMode(2);
        a(b, readableMap);
        return b;
    }

    private static Transition e(ReadableMap readableMap) {
        ChangeBounds changeBounds = new ChangeBounds();
        ChangeTransform changeTransform = new ChangeTransform();
        a(changeBounds, readableMap);
        a(changeTransform, readableMap);
        return new TransitionSet().addTransition(changeBounds).addTransition(changeTransform);
    }

    private static Visibility b(String str) {
        if (str == null || "none".equals(str)) {
            return null;
        }
        if ("fade".equals(str)) {
            return new Fade(3);
        }
        if ("scale".equals(str)) {
            return new Scale();
        }
        if ("slide-top".equals(str)) {
            return new Slide(48);
        }
        if ("slide-bottom".equals(str)) {
            return new Slide(80);
        }
        if ("slide-right".equals(str)) {
            return new Slide(5);
        }
        if ("slide-left".equals(str)) {
            return new Slide(3);
        }
        throw new JSApplicationIllegalArgumentException("Invalid transition type " + str);
    }

    private static void a(Transition transition, ReadableMap readableMap) {
        if (readableMap.hasKey("durationMs")) {
            transition.setDuration((long) readableMap.getInt("durationMs"));
        }
        if (readableMap.hasKey("interpolation")) {
            String string = readableMap.getString("interpolation");
            if (string.equals("easeIn")) {
                transition.setInterpolator(new AccelerateInterpolator());
            } else if (string.equals("easeOut")) {
                transition.setInterpolator(new DecelerateInterpolator());
            } else if (string.equals("easeInOut")) {
                transition.setInterpolator(new AccelerateDecelerateInterpolator());
            } else if (string.equals("linear")) {
                transition.setInterpolator(new LinearInterpolator());
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid interpolation type " + string);
            }
        }
        if (readableMap.hasKey("propagation")) {
            String string2 = readableMap.getString("propagation");
            SaneSidePropagation saneSidePropagation = new SaneSidePropagation();
            if ("top".equals(string2)) {
                saneSidePropagation.setSide(80);
            } else if ("bottom".equals(string2)) {
                saneSidePropagation.setSide(48);
            } else if ("left".equals(string2)) {
                saneSidePropagation.setSide(5);
            } else if ("right".equals(string2)) {
                saneSidePropagation.setSide(3);
            }
            transition.setPropagation(saneSidePropagation);
        } else {
            transition.setPropagation((TransitionPropagation) null);
        }
        if (readableMap.hasKey("delayMs")) {
            transition.setStartDelay((long) readableMap.getInt("delayMs"));
        }
    }
}
