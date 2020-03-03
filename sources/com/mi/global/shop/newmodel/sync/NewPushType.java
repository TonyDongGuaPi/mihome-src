package com.mi.global.shop.newmodel.sync;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPushType {
    public boolean defaultStatus;
    public String desc;
    public boolean enableclose;
    public String key;
    public String title;

    public static NewPushType decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPushType decode(ProtoReader protoReader) throws IOException {
        NewPushType newPushType = new NewPushType();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPushType.title = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newPushType.desc = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newPushType.key = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        newPushType.enableclose = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    case 5:
                        newPushType.defaultStatus = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPushType;
            }
        }
    }
}
