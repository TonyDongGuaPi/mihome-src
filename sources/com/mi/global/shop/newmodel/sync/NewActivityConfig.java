package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewActivityConfig {
    public int duration;
    public int endTime;
    public int startTime;
    public String url;

    public static NewActivityConfig decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewActivityConfig decode(ProtoReader protoReader) throws IOException {
        NewActivityConfig newActivityConfig = new NewActivityConfig();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newActivityConfig.url = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newActivityConfig.startTime = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 3:
                        newActivityConfig.endTime = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 4:
                        newActivityConfig.duration = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newActivityConfig;
            }
        }
    }
}
