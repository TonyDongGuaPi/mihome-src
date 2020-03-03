package com.xiaomi.smarthome.smartconfig.step;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.wificonfig.GifView;

public class QRConfigStep_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private QRConfigStep f22656a;

    @UiThread
    public QRConfigStep_ViewBinding(QRConfigStep qRConfigStep, View view) {
        this.f22656a = qRConfigStep;
        qRConfigStep.mNextButton = (Button) Utils.findRequiredViewAsType(view, R.id.next, "field 'mNextButton'", Button.class);
        qRConfigStep.mHelpView = (TextView) Utils.findRequiredViewAsType(view, R.id.help, "field 'mHelpView'", TextView.class);
        qRConfigStep.mBarCodeImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.barcode_image, "field 'mBarCodeImage'", ImageView.class);
        qRConfigStep.contentView = Utils.findRequiredView(view, R.id.content, "field 'contentView'");
        qRConfigStep.mTipsView = Utils.findRequiredView(view, R.id.tips, "field 'mTipsView'");
        qRConfigStep.mTipsGifView = (GifView) Utils.findRequiredViewAsType(view, R.id.tips_gif, "field 'mTipsGifView'", GifView.class);
        qRConfigStep.mTipsImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.tips_image, "field 'mTipsImageView'", ImageView.class);
        qRConfigStep.mTipsBtn = (TextView) Utils.findRequiredViewAsType(view, R.id.tips_ok_btn, "field 'mTipsBtn'", TextView.class);
        qRConfigStep.mResetTips = Utils.findRequiredView(view, R.id.reset_tips, "field 'mResetTips'");
        qRConfigStep.mGenCode = (Button) Utils.findRequiredViewAsType(view, R.id.gen_barcode, "field 'mGenCode'", Button.class);
    }

    @CallSuper
    public void unbind() {
        QRConfigStep qRConfigStep = this.f22656a;
        if (qRConfigStep != null) {
            this.f22656a = null;
            qRConfigStep.mNextButton = null;
            qRConfigStep.mHelpView = null;
            qRConfigStep.mBarCodeImage = null;
            qRConfigStep.contentView = null;
            qRConfigStep.mTipsView = null;
            qRConfigStep.mTipsGifView = null;
            qRConfigStep.mTipsImageView = null;
            qRConfigStep.mTipsBtn = null;
            qRConfigStep.mResetTips = null;
            qRConfigStep.mGenCode = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
