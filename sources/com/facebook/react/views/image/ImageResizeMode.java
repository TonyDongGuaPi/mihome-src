package com.facebook.react.views.image;

import android.graphics.Shader;
import android.support.annotation.Nullable;
import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;

public class ImageResizeMode {
    public static ScalingUtils.ScaleType toScaleType(@Nullable String str) {
        if ("contain".equals(str)) {
            return ScalingUtils.ScaleType.FIT_CENTER;
        }
        if (PlaceFields.COVER.equals(str)) {
            return ScalingUtils.ScaleType.CENTER_CROP;
        }
        if ("stretch".equals(str)) {
            return ScalingUtils.ScaleType.FIT_XY;
        }
        if ("center".equals(str)) {
            return ScalingUtils.ScaleType.CENTER_INSIDE;
        }
        if (ReactVideoViewManager.PROP_REPEAT.equals(str)) {
            return ScaleTypeStartInside.INSTANCE;
        }
        if (str == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static Shader.TileMode toTileMode(@Nullable String str) {
        if ("contain".equals(str) || PlaceFields.COVER.equals(str) || "stretch".equals(str) || "center".equals(str)) {
            return Shader.TileMode.CLAMP;
        }
        if (ReactVideoViewManager.PROP_REPEAT.equals(str)) {
            return Shader.TileMode.REPEAT;
        }
        if (str == null) {
            return defaultTileMode();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static ScalingUtils.ScaleType defaultValue() {
        return ScalingUtils.ScaleType.CENTER_CROP;
    }

    public static Shader.TileMode defaultTileMode() {
        return Shader.TileMode.CLAMP;
    }
}
