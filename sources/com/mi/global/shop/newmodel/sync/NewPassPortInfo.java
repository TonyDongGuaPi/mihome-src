package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPassPortInfo {
    public String errInfo;
    public boolean tokenInvalid;

    public static NewPassPortInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPassPortInfo decode(ProtoReader protoReader) throws IOException {
        NewPassPortInfo newPassPortInfo = new NewPassPortInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPassPortInfo.tokenInvalid = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 2:
                        newPassPortInfo.errInfo = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPassPortInfo;
            }
        }
    }
}
