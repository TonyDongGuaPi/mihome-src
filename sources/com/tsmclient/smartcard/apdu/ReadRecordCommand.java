package com.tsmclient.smartcard.apdu;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;

public class ReadRecordCommand extends BaseCommand {
    private byte mSfi = 0;

    public byte getCls() {
        return 0;
    }

    public byte getIns() {
        return Constants.TagName.APP_TYPE;
    }

    public byte getSfi() {
        return this.mSfi;
    }

    public void setSfi(byte b) {
        this.mSfi = b;
    }

    public ByteArray toRawAPDU() {
        if (getData().length() > 0) {
            return super.toRawAPDU();
        }
        return ByteArray.wrap(new byte[]{getCls(), getIns(), getP1(), getP2(), getSfi()});
    }
}
