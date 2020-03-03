package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class GSTData {
    @SerializedName("valid")
    public boolean valid;

    public static GSTData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static GSTData decode(ProtoReader protoReader) throws IOException {
        GSTData gSTData = new GSTData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return gSTData;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                gSTData.valid = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
            }
        }
    }
}
