package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCentralHeader {
    public String userCentralHeaderBg;
    public String userCentralHeaderTitleColor;

    public static NewCentralHeader decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCentralHeader decode(ProtoReader protoReader) throws IOException {
        NewCentralHeader newCentralHeader = new NewCentralHeader();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCentralHeader.userCentralHeaderBg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newCentralHeader.userCentralHeaderTitleColor = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCentralHeader;
            }
        }
    }
}
