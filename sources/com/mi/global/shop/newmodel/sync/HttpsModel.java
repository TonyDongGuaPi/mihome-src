package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HttpsModel {
    public boolean api;
    public boolean file;
    public boolean page;

    public static HttpsModel decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HttpsModel decode(ProtoReader protoReader) throws IOException {
        HttpsModel httpsModel = new HttpsModel();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        httpsModel.api = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 2:
                        httpsModel.file = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 3:
                        httpsModel.page = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return httpsModel;
            }
        }
    }
}
