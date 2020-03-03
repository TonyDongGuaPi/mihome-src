package com.xiaomi.smarthome.device;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ChooseConnectDevice_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ChooseConnectDevice f14786a;

    @UiThread
    public ChooseConnectDevice_ViewBinding(ChooseConnectDevice chooseConnectDevice, View view) {
        this.f14786a = chooseConnectDevice;
        chooseConnectDevice.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.device_list, "field 'mListView'", ListView.class);
        chooseConnectDevice.inScanHint = Utils.findRequiredView(view, R.id.inScanHint, "field 'inScanHint'");
        chooseConnectDevice.mRadarImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.scanning_image, "field 'mRadarImage'", ImageView.class);
        chooseConnectDevice.mMoreDevice = (Button) Utils.findRequiredViewAsType(view, R.id.more_device, "field 'mMoreDevice'", Button.class);
        chooseConnectDevice.mHelpTips = (TextView) Utils.findRequiredViewAsType(view, R.id.can_not_find_device, "field 'mHelpTips'", TextView.class);
        chooseConnectDevice.mRadarBgImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.radar_bg, "field 'mRadarBgImg'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        ChooseConnectDevice chooseConnectDevice = this.f14786a;
        if (chooseConnectDevice != null) {
            this.f14786a = null;
            chooseConnectDevice.mListView = null;
            chooseConnectDevice.inScanHint = null;
            chooseConnectDevice.mRadarImage = null;
            chooseConnectDevice.mMoreDevice = null;
            chooseConnectDevice.mHelpTips = null;
            chooseConnectDevice.mRadarBgImg = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
