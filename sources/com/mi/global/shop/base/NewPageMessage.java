package com.mi.global.shop.base;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPageMessage {
    @SerializedName("pagemsg")

    /* renamed from: a  reason: collision with root package name */
    public String f5594a;
    @SerializedName("icon")
    public String b;

    public static NewPageMessage a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPageMessage a(ProtoReader protoReader) throws IOException {
        NewPageMessage newPageMessage = new NewPageMessage();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPageMessage.f5594a = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newPageMessage.b = ProtoAdapter.STRING.decode(protoReader);
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
