package com.xiaomi.smarthome.scene;

import android.os.Bundle;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.scene.SceneItemChooseFragment;
import java.util.ArrayList;
import java.util.List;

public class MiRouterChooseActivity extends CommonChooseActivity {
    /* access modifiers changed from: protected */
    public int getLayoutFile() {
        return R.layout.scene_mirouter_setting_activity;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.titleView != null) {
            this.titleView.setText(R.string.mi_router);
        }
        if (this.mFragment != null) {
            this.mFragment.a(getString(R.string.choose_home_router));
        }
    }

    /* access modifiers changed from: protected */
    public List<SceneItemChooseFragment.ItemData> getItemList() {
        ArrayList arrayList = new ArrayList();
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if (next.isOwner() && (next instanceof RouterDevice)) {
                this.mDevices.add(next);
                SceneItemChooseFragment.ItemData itemData = new SceneItemChooseFragment.ItemData();
                itemData.f21310a = next.did;
                itemData.b = next.getName().toString();
                arrayList.add(itemData);
            }
        }
        return arrayList;
    }
}
