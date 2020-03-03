package com.xiaomi.smarthome.framework.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class PictureShareActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private PictureShareActivity f16905a;

    @UiThread
    public PictureShareActivity_ViewBinding(PictureShareActivity pictureShareActivity) {
        this(pictureShareActivity, pictureShareActivity.getWindow().getDecorView());
    }

    @UiThread
    public PictureShareActivity_ViewBinding(PictureShareActivity pictureShareActivity, View view) {
        this.f16905a = pictureShareActivity;
        pictureShareActivity.miliaoShare = (TextView) Utils.findRequiredViewAsType(view, R.id.miliao_share, "field 'miliaoShare'", TextView.class);
        pictureShareActivity.wxShare = (TextView) Utils.findRequiredViewAsType(view, R.id.wx_share, "field 'wxShare'", TextView.class);
        pictureShareActivity.friendsShare = (TextView) Utils.findRequiredViewAsType(view, R.id.friends_share, "field 'friendsShare'", TextView.class);
        pictureShareActivity.weiboShare = (TextView) Utils.findRequiredViewAsType(view, R.id.weibo_share, "field 'weiboShare'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PictureShareActivity pictureShareActivity = this.f16905a;
        if (pictureShareActivity != null) {
            this.f16905a = null;
            pictureShareActivity.miliaoShare = null;
            pictureShareActivity.wxShare = null;
            pictureShareActivity.friendsShare = null;
            pictureShareActivity.weiboShare = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
