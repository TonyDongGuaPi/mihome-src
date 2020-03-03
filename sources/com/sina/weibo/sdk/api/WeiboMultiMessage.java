package com.sina.weibo.sdk.api;

import android.os.Bundle;
import android.os.Parcelable;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.Serializable;

public final class WeiboMultiMessage implements Serializable {
    public static int NineImageType = 2;
    public static int OneImageType = 1;
    private static final String TAG = "WeiboMultiMessage";
    public ImageObject imageObject;
    public BaseMediaObject mediaObject;
    public int msgType;
    public MultiImageObject multiImageObject;
    public TextObject textObject;
    public VideoSourceObject videoSourceObject;

    public WeiboMultiMessage() {
    }

    public WeiboMultiMessage(Bundle bundle) {
        toBundle(bundle);
    }

    public Bundle toBundle(Bundle bundle) {
        if (this.textObject != null) {
            bundle.putParcelable(WBConstants.Msg.f8823a, this.textObject);
            bundle.putString(WBConstants.Msg.f, this.textObject.toExtraMediaString());
        } else {
            bundle.putParcelable(WBConstants.Msg.f8823a, (Parcelable) null);
            bundle.putString(WBConstants.Msg.f, (String) null);
        }
        if (this.imageObject != null) {
            bundle.putParcelable(WBConstants.Msg.b, this.imageObject);
            bundle.putString(WBConstants.Msg.g, this.imageObject.toExtraMediaString());
        } else {
            bundle.putParcelable(WBConstants.Msg.b, (Parcelable) null);
            bundle.putString(WBConstants.Msg.g, (String) null);
        }
        if (this.mediaObject != null) {
            bundle.putParcelable(WBConstants.Msg.c, this.mediaObject);
            bundle.putString(WBConstants.Msg.h, this.mediaObject.toExtraMediaString());
        } else {
            bundle.putParcelable(WBConstants.Msg.c, (Parcelable) null);
            bundle.putString(WBConstants.Msg.h, (String) null);
        }
        if (this.multiImageObject != null) {
            bundle.putParcelable(WBConstants.Msg.d, this.multiImageObject);
        } else {
            bundle.putParcelable(WBConstants.Msg.d, (Parcelable) null);
        }
        if (this.videoSourceObject != null) {
            bundle.putParcelable(WBConstants.Msg.e, this.videoSourceObject);
        } else {
            bundle.putParcelable(WBConstants.Msg.e, (Parcelable) null);
        }
        return bundle;
    }

    public WeiboMultiMessage toObject(Bundle bundle) {
        this.textObject = (TextObject) bundle.getParcelable(WBConstants.Msg.f8823a);
        if (this.textObject != null) {
            this.textObject.toExtraMediaObject(bundle.getString(WBConstants.Msg.f));
        }
        this.imageObject = (ImageObject) bundle.getParcelable(WBConstants.Msg.b);
        if (this.imageObject != null) {
            this.imageObject.toExtraMediaObject(bundle.getString(WBConstants.Msg.g));
        }
        this.mediaObject = (BaseMediaObject) bundle.getParcelable(WBConstants.Msg.c);
        if (this.mediaObject != null) {
            this.mediaObject.toExtraMediaObject(bundle.getString(WBConstants.Msg.h));
        }
        this.multiImageObject = (MultiImageObject) bundle.getParcelable(WBConstants.Msg.d);
        this.videoSourceObject = (VideoSourceObject) bundle.getParcelable(WBConstants.Msg.e);
        return this;
    }

    public boolean checkArgs() {
        if (this.textObject != null && !this.textObject.checkArgs()) {
            LogUtil.c(TAG, "checkArgs fail, textObject is invalid");
            return false;
        } else if (this.imageObject != null && !this.imageObject.checkArgs()) {
            LogUtil.c(TAG, "checkArgs fail, imageObject is invalid");
            return false;
        } else if (this.mediaObject != null && !this.mediaObject.checkArgs()) {
            LogUtil.c(TAG, "checkArgs fail, mediaObject is invalid");
            return false;
        } else if (this.textObject != null || this.imageObject != null || this.mediaObject != null) {
            return true;
        } else {
            LogUtil.c(TAG, "checkArgs fail, textObject and imageObject and mediaObject is null");
            return false;
        }
    }

    public void setMsgType(int i) {
        this.msgType = i;
    }

    public int getMsgType() {
        return this.msgType;
    }
}
