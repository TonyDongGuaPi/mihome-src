package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPackageInfo {
    public String url;
    public int version;

    public static NewPackageInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPackageInfo decode(ProtoReader protoReader) throws IOException {
        NewPackageInfo newPackageInfo = new NewPackageInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPackageInfo.version = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newPackageInfo.url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPackageInfo;
            }
        }
    }
}
