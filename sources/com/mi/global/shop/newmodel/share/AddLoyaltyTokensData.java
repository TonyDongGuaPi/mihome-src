package com.mi.global.shop.newmodel.share;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class AddLoyaltyTokensData {
    @SerializedName("miTokens")
    public long miTokens;
    @SerializedName("msg")
    public String msg;

    public static AddLoyaltyTokensData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static AddLoyaltyTokensData decode(ProtoReader protoReader) throws IOException {
        AddLoyaltyTokensData addLoyaltyTokensData = new AddLoyaltyTokensData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        addLoyaltyTokensData.miTokens = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    case 2:
                        addLoyaltyTokensData.msg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return addLoyaltyTokensData;
            }
        }
    }
}
