package com.mi.global.shop.newmodel.pay.payparam;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPayGoData {
    @SerializedName("ext")
    public String ext;
    @SerializedName("html")
    public String html;
    @SerializedName("params")
    public String params;

    public static NewPayGoData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPayGoData decode(ProtoReader protoReader) throws IOException {
        NewPayGoData newPayGoData = new NewPayGoData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPayGoData.html = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newPayGoData.params = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newPayGoData.ext = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPayGoData;
            }
        }
    }
}
