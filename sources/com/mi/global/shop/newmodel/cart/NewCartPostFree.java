package com.mi.global.shop.newmodel.cart;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCartPostFree {
    @SerializedName("actName")
    public String actName;
    @SerializedName("activity_name")
    public String activity_name;

    public static NewCartPostFree decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCartPostFree decode(ProtoReader protoReader) throws IOException {
        NewCartPostFree newCartPostFree = new NewCartPostFree();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCartPostFree.actName = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newCartPostFree.activity_name = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCartPostFree;
            }
        }
    }
}
