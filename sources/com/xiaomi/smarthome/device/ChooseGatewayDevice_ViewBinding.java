package com.xiaomi.smarthome.device;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ChooseGatewayDevice_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ChooseGatewayDevice f14812a;

    @UiThread
    public ChooseGatewayDevice_ViewBinding(ChooseGatewayDevice chooseGatewayDevice) {
        this(chooseGatewayDevice, chooseGatewayDevice.getWindow().getDecorView());
    }

    @UiThread
    public ChooseGatewayDevice_ViewBinding(ChooseGatewayDevice chooseGatewayDevice, View view) {
        this.f14812a = chooseGatewayDevice;
        chooseGatewayDevice.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        chooseGatewayDevice.mBackBt = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mBackBt'");
        chooseGatewayDevice.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.list, "field 'mListView'", ListView.class);
        chooseGatewayDevice.mFindNoDeviceView = Utils.findRequiredView(view, R.id.find_no_device, "field 'mFindNoDeviceView'");
        chooseGatewayDevice.mListTitle = Utils.findRequiredView(view, R.id.list_title, "field 'mListTitle'");
        chooseGatewayDevice.mEmptyImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.empty_icon, "field 'mEmptyImage'", ImageView.class);
        chooseGatewayDevice.mEmptyTv = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'mEmptyTv'", TextView.class);
        chooseGatewayDevice.mMoreMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.more_message, "field 'mMoreMessage'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        ChooseGatewayDevice chooseGatewayDevice = this.f14812a;
        if (chooseGatewayDevice != null) {
            this.f14812a = null;
            chooseGatewayDevice.mTitle = null;
            chooseGatewayDevice.mBackBt = null;
            chooseGatewayDevice.mListView = null;
            chooseGatewayDevice.mFindNoDeviceView = null;
            chooseGatewayDevice.mListTitle = null;
            chooseGatewayDevice.mEmptyImage = null;
            chooseGatewayDevice.mEmptyTv = null;
            chooseGatewayDevice.mMoreMessage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
