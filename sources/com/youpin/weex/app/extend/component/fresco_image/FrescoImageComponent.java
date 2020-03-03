package com.youpin.weex.app.extend.component.fresco_image;

import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.places.model.PlaceFields;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.taobao.weex.utils.WXDomUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Map;

public class FrescoImageComponent extends WXImage {
    public FrescoImageComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    /* access modifiers changed from: protected */
    public ImageView initComponentHostView(@NonNull Context context) {
        FrescoImageView frescoImageView = new FrescoImageView(context);
        frescoImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return frescoImageView;
    }

    public void updateProperties(Map<String, Object> map) {
        float[] fArr;
        super.updateProperties(map);
        if (getHostView() instanceof FrescoImageView) {
            FrescoImageView frescoImageView = (FrescoImageView) getHostView();
            BorderDrawable borderDrawable = WXViewUtils.getBorderDrawable(getHostView());
            if (borderDrawable != null) {
                fArr = borderDrawable.getBorderRadius(new RectF(0.0f, 0.0f, WXDomUtils.getContentWidth(this), WXDomUtils.getContentHeight(this)));
            } else {
                fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            }
            ((GenericDraweeHierarchy) frescoImageView.getHierarchy()).setRoundingParams(RoundingParams.fromCornersRadii(fArr[0], fArr[2], fArr[4], fArr[6]));
            readyToRender();
        }
    }

    @WXComponentProp(name = "resizeMode")
    public void setResizeMode(String str) {
        View hostView = getHostView();
        if (hostView instanceof FrescoImageView) {
            ((GenericDraweeHierarchy) ((FrescoImageView) hostView).getHierarchy()).setActualImageScaleType(getResizeModeForFresco(str));
        }
    }

    private ScalingUtils.ScaleType getResizeModeForFresco(String str) {
        ScalingUtils.ScaleType scaleType = ScalingUtils.ScaleType.FIT_XY;
        if (TextUtils.isEmpty(str)) {
            return scaleType;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1881872635) {
            if (hashCode != 94852023) {
                if (hashCode == 951526612 && str.equals("contain")) {
                    c = 1;
                }
            } else if (str.equals(PlaceFields.COVER)) {
                c = 0;
            }
        } else if (str.equals("stretch")) {
            c = 2;
        }
        switch (c) {
            case 0:
                return ScalingUtils.ScaleType.CENTER_CROP;
            case 1:
                return ScalingUtils.ScaleType.FIT_CENTER;
            case 2:
                return ScalingUtils.ScaleType.FIT_XY;
            default:
                return scaleType;
        }
    }
}
