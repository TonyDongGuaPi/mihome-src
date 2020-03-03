package com.xiaomi.smarthome.mitsmsdk;

import android.content.Context;
import cn.com.xm.bt.profile.nfc.ApduResponse;
import cn.com.xm.bt.profile.nfc.HMNFCStatus;
import cn.com.xm.bt.sdk.HMBleDevice;
import com.miui.tsmclient.util.LogUtils;
import com.tsmclient.smartcard.terminal.external.IChannel;

public class ChannelImpl implements IChannel {

    /* renamed from: a  reason: collision with root package name */
    private HMBleDevice f20057a = NfcChannelManager.a().f();
    private Context b;

    public ChannelImpl(Context context) {
        this.b = context;
    }

    public void open() {
        LogUtils.v("ChannelImpl open()");
        HMNFCStatus openApduChannel = this.f20057a.openApduChannel();
        if (openApduChannel != null && openApduChannel.getStatus() != 0) {
            LogUtils.e("ChannelImpl open failed! status = " + openApduChannel);
        }
    }

    public void close() {
        LogUtils.v("ChannelImpl close()");
        HMNFCStatus closeApduChannel = this.f20057a.closeApduChannel();
        if (closeApduChannel != null && closeApduChannel.getStatus() != 0) {
            LogUtils.e("ChannelImpl close failed! status = " + closeApduChannel);
        }
    }

    public byte[] transceive(byte[] bArr) {
        ApduResponse sendApduData = this.f20057a.sendApduData(bArr, bArr.length, false);
        if (sendApduData != null) {
            return sendApduData.getData();
        }
        return new byte[0];
    }
}
