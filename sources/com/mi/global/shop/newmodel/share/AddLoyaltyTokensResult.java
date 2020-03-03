package com.mi.global.shop.newmodel.share;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class AddLoyaltyTokensResult extends BaseResult {
    @SerializedName("data")
    public AddLoyaltyTokensData data;

    public static AddLoyaltyTokensResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static AddLoyaltyTokensResult decode(ProtoReader protoReader) throws IOException {
        AddLoyaltyTokensResult addLoyaltyTokensResult = new AddLoyaltyTokensResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        addLoyaltyTokensResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        addLoyaltyTokensResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        addLoyaltyTokensResult.data = AddLoyaltyTokensData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return addLoyaltyTokensResult;
            }
        }
    }
}
