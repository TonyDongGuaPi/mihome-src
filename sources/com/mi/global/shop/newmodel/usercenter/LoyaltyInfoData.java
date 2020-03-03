package com.mi.global.shop.newmodel.usercenter;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class LoyaltyInfoData {
    @SerializedName("desc")
    public String desc;
    @SerializedName("micon")
    public String micon;
    @SerializedName("pcicon")
    public String pcicon;
    @SerializedName("score")
    public int score;

    public static LoyaltyInfoData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static LoyaltyInfoData decode(ProtoReader protoReader) throws IOException {
        LoyaltyInfoData loyaltyInfoData = new LoyaltyInfoData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        loyaltyInfoData.score = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        loyaltyInfoData.desc = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        loyaltyInfoData.micon = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        loyaltyInfoData.pcicon = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return loyaltyInfoData;
            }
        }
    }
}
