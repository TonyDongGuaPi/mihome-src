package com.xiaomi.smarthome.bluetooth;

import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.smarthome.bluetooth.IBleUpgradeController;

public abstract class BleUpgrader extends IBleUpgradeController.Stub {
    private IBleUpgradeViewer mBleUpgradeViewer;

    public boolean onPreEnterActivity(Bundle bundle) throws RemoteException {
        return false;
    }

    public void attachUpgradeCaller(IBleUpgradeViewer iBleUpgradeViewer) {
        this.mBleUpgradeViewer = iBleUpgradeViewer;
    }

    public void detachUpgradeCaller() throws RemoteException {
        this.mBleUpgradeViewer = null;
    }

    public void showPage(int i, Bundle bundle) {
        if (this.mBleUpgradeViewer != null) {
            try {
                this.mBleUpgradeViewer.showPage(i, bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setBtnBackEnabled(boolean z) {
        if (this.mBleUpgradeViewer != null) {
            try {
                this.mBleUpgradeViewer.setBtnBackEnabled(z);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
