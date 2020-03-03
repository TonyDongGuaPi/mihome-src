package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HttpDnsModel {
    public boolean enable;
    public String hostname;
    public String ips;
    public String ttl;

    public static HttpDnsModel decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HttpDnsModel decode(ProtoReader protoReader) throws IOException {
        HttpDnsModel httpDnsModel = new HttpDnsModel();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        httpDnsModel.hostname = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        httpDnsModel.enable = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 3:
                        httpDnsModel.ips = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        httpDnsModel.ttl = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return httpDnsModel;
            }
        }
    }
}
