package android.support.design.ripple;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.ColorUtils;
import android.util.StateSet;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class RippleUtils {
    private static final int[] FOCUSED_STATE_SET = {16842908};
    private static final int[] HOVERED_FOCUSED_STATE_SET = {16843623, 16842908};
    private static final int[] HOVERED_STATE_SET = {16843623};
    private static final int[] PRESSED_STATE_SET = {16842919};
    private static final int[] SELECTED_FOCUSED_STATE_SET = {16842913, 16842908};
    private static final int[] SELECTED_HOVERED_FOCUSED_STATE_SET = {16842913, 16843623, 16842908};
    private static final int[] SELECTED_HOVERED_STATE_SET = {16842913, 16843623};
    private static final int[] SELECTED_PRESSED_STATE_SET = {16842913, 16842919};
    private static final int[] SELECTED_STATE_SET = {16842913};
    public static final boolean USE_FRAMEWORK_RIPPLE = (Build.VERSION.SDK_INT >= 21);

    private RippleUtils() {
    }

    @NonNull
    public static ColorStateList convertToRippleDrawableColor(@Nullable ColorStateList colorStateList) {
        if (USE_FRAMEWORK_RIPPLE) {
            return new ColorStateList(new int[][]{SELECTED_STATE_SET, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, SELECTED_PRESSED_STATE_SET), getColorForState(colorStateList, PRESSED_STATE_SET)});
        }
        return new ColorStateList(new int[][]{SELECTED_PRESSED_STATE_SET, SELECTED_HOVERED_FOCUSED_STATE_SET, SELECTED_FOCUSED_STATE_SET, SELECTED_HOVERED_STATE_SET, SELECTED_STATE_SET, PRESSED_STATE_SET, HOVERED_FOCUSED_STATE_SET, FOCUSED_STATE_SET, HOVERED_STATE_SET, StateSet.NOTHING}, new int[]{getColorForState(colorStateList, SELECTED_PRESSED_STATE_SET), getColorForState(colorStateList, SELECTED_HOVERED_FOCUSED_STATE_SET), getColorForState(colorStateList, SELECTED_FOCUSED_STATE_SET), getColorForState(colorStateList, SELECTED_HOVERED_STATE_SET), 0, getColorForState(colorStateList, PRESSED_STATE_SET), getColorForState(colorStateList, HOVERED_FOCUSED_STATE_SET), getColorForState(colorStateList, FOCUSED_STATE_SET), getColorForState(colorStateList, HOVERED_STATE_SET), 0});
    }

    @ColorInt
    private static int getColorForState(@Nullable ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return USE_FRAMEWORK_RIPPLE ? doubleAlpha(colorForState) : colorForState;
    }

    @ColorInt
    @TargetApi(21)
    private static int doubleAlpha(@ColorInt int i) {
        return ColorUtils.setAlphaComponent(i, Math.min(Color.alpha(i) * 2, 255));
    }
}
