package cn.com.xm.bt.sdk;

import android.content.Context;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.xm.bt.a.a;
import cn.com.xm.bt.profile.nfc.ApduResponse;
import cn.com.xm.bt.profile.nfc.HMNFCStatus;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test {
    private static final String TAG = "test";

    public static void test(Context context) {
        final HMBleDevice hMBleDevice = new HMBleDevice(context, "F2:19:CA:80:20:7A");
        hMBleDevice.setPair(true);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        hMBleDevice.connect(new IDeviceCallback() {
            public void onAuthStateChanged(int i, int i2) {
            }

            public byte[] onSignature(String str, String str2) {
                return null;
            }

            public void onConnectionStateChanged(int i) {
                if (i == 0) {
                    hMBleDevice.setPair(false);
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await(120, TimeUnit.SECONDS);
        } catch (Exception unused) {
        }
        a.b("test", "key:" + hMBleDevice.getKey());
        HMNFCStatus openApduChannel = hMBleDevice.openApduChannel();
        a.b("test", "open status:" + openApduChannel);
        if (openApduChannel.getStatus() == 0) {
            int realtimeStep = hMBleDevice.getRealtimeStep();
            a.b("test", "realtime step:" + realtimeStep);
            byte[] bArr = {0, ScriptToolsConst.TagName.CommandMultiple, 4, 0, 8, ScriptToolsConst.TagName.CommandSingle, 0, 0, 1, Constants.TagName.TERMINAL_BACK_MAIN_ID, 0, 0, 0};
            ApduResponse sendApduData = hMBleDevice.sendApduData(bArr, bArr.length, false);
            a.b("test", "response:" + sendApduData);
            HMNFCStatus closeApduChannel = hMBleDevice.closeApduChannel();
            a.b("test", "close status:" + closeApduChannel);
            hMBleDevice.disconnect();
        }
    }
}
