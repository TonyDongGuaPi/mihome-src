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

public class CameraApChooseConnection_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private CameraApChooseConnection f22232a;

    @UiThread
    public CameraApChooseConnection_ViewBinding(CameraApChooseConnection cameraApChooseConnection) {
        this(cameraApChooseConnection, cameraApChooseConnection.getWindow().getDecorView());
    }

    @UiThread
    public CameraApChooseConnection_ViewBinding(CameraApChooseConnection cameraApChooseConnection, View view) {
        this.f22232a = cameraApChooseConnection;
        cameraApChooseConnection.mIvReturn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mIvReturn'", ImageView.class);
        cameraApChooseConnection.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTvTitle'", TextView.class);
        cameraApChooseConnection.mSettingWifiBtn = (Button) Utils.findRequiredViewAsType(view, R.id.next_btn, "field 'mSettingWifiBtn'", Button.class);
        cameraApChooseConnection.mTvAp = (TextView) Utils.findRequiredViewAsType(view, R.id.ap, "field 'mTvAp'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CameraApChooseConnection cameraApChooseConnection = this.f22232a;
        if (cameraApChooseConnection != null) {
            this.f22232a = null;
            cameraApChooseConnection.mIvReturn = null;
            cameraApChooseConnection.mTvTitle = null;
            cameraApChooseConnection.mSettingWifiBtn = null;
            cameraApChooseConnection.mTvAp = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
