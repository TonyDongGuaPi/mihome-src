package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class SwitchInfo {
    public boolean assembleTEZ = true;
    public boolean authorizedStoreSwitch;
    public boolean hardwareAccelerateModel = true;
    public boolean loyaltySwitch;
    public boolean usingGoMifileHostSwitch;

    public static SwitchInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static SwitchInfo decode(ProtoReader protoReader) throws IOException {
        SwitchInfo switchInfo = new SwitchInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        switchInfo.loyaltySwitch = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 2:
                        switchInfo.authorizedStoreSwitch = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 3:
                        switchInfo.usingGoMifileHostSwitch = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 4:
                        switchInfo.hardwareAccelerateModel = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 5:
                        switchInfo.assembleTEZ = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return switchInfo;
            }
        }
    }
}
