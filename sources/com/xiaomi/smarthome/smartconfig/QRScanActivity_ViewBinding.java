package com.xiaomi.smarthome.smartconfig;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class QRScanActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private QRScanActivity f22321a;

    @UiThread
    public QRScanActivity_ViewBinding(QRScanActivity qRScanActivity) {
        this(qRScanActivity, qRScanActivity.getWindow().getDecorView());
    }

    @UiThread
    public QRScanActivity_ViewBinding(QRScanActivity qRScanActivity, View view) {
        this.f22321a = qRScanActivity;
        qRScanActivity.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        qRScanActivity.mIvReturn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mIvReturn'", ImageView.class);
        qRScanActivity.mNextButton = (Button) Utils.findRequiredViewAsType(view, R.id.heard_qr_scaned, "field 'mNextButton'", Button.class);
        qRScanActivity.mHelpView = (TextView) Utils.findRequiredViewAsType(view, R.id.nothing_heard, "field 'mHelpView'", TextView.class);
        qRScanActivity.mBarCodeImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.barcode_for_camera, "field 'mBarCodeImage'", ImageView.class);
        qRScanActivity.mContentView = Utils.findRequiredView(view, R.id.content, "field 'mContentView'");
        qRScanActivity.mTvTopDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.top_desc, "field 'mTvTopDesc'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        QRScanActivity qRScanActivity = this.f22321a;
        if (qRScanActivity != null) {
            this.f22321a = null;
            qRScanActivity.mTitleBar = null;
            qRScanActivity.mIvReturn = null;
            qRScanActivity.mNextButton = null;
            qRScanActivity.mHelpView = null;
            qRScanActivity.mBarCodeImage = null;
            qRScanActivity.mContentView = null;
            qRScanActivity.mTvTopDesc = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
