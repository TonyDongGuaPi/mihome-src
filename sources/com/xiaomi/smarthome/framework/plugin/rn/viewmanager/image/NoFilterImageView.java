package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.image;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.generic.ExtendGenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageView;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
public class NoFilterImageView extends ReactImageView {
    public NoFilterImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
        super(context, abstractDraweeControllerBuilder, globalImageLoadListener, obj);
        setHierarchy(new ExtendGenericDraweeHierarchy(new GenericDraweeHierarchyBuilder(context.getResources()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f))));
        getTopLevelDrawable().setFilterBitmap(false);
        RnPluginLog.a("MHImageView...");
    }
}
