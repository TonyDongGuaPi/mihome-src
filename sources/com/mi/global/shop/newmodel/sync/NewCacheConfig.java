package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCacheConfig {
    public boolean css;
    public boolean enable;
    public boolean html;
    public boolean js;

    public static NewCacheConfig decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCacheConfig decode(ProtoReader protoReader) throws IOException {
        NewCacheConfig newCacheConfig = new NewCacheConfig();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCacheConfig.enable = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 2:
                        newCacheConfig.js = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 3:
                        newCacheConfig.css = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 4:
                        newCacheConfig.html = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCacheConfig;
            }
        }
    }
}
