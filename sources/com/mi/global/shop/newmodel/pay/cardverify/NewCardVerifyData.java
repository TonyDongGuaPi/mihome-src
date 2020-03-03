package com.mi.global.shop.newmodel.pay.cardverify;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCardVerifyData {
    @SerializedName("isEmiCard")
    public boolean isEmiCard;

    public static NewCardVerifyData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCardVerifyData decode(ProtoReader protoReader) throws IOException {
        NewCardVerifyData newCardVerifyData = new NewCardVerifyData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newCardVerifyData;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                newCardVerifyData.isEmiCard = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
            }
        }
    }
}
