package com.mi.global.shop.newmodel;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPageMessage {
    @SerializedName("icon")
    public String icon;
    @SerializedName("pagemsg")
    public String pagemsg;

    public static NewPageMessage decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPageMessage decode(ProtoReader protoReader) throws IOException {
        NewPageMessage newPageMessage = new NewPageMessage();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPageMessage.pagemsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newPageMessage.icon = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPageMessage;
            }
        }
    }
}
