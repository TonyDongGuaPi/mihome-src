package com.mi.global.shop.newmodel.home;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewHomeBlockInfo {
    @SerializedName("desc")
    public NewHomeBlockInfoDesc mDesc;
    @SerializedName("items")
    public ArrayList<NewHomeBlockInfoItem> mItems = new ArrayList<>();

    public static NewHomeBlockInfo decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewHomeBlockInfo decode(ProtoReader protoReader) throws IOException {
        NewHomeBlockInfo newHomeBlockInfo = new NewHomeBlockInfo();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newHomeBlockInfo.mItems.add(NewHomeBlockInfoItem.decode(protoReader));
                        break;
                    case 2:
                        newHomeBlockInfo.mDesc = NewHomeBlockInfoDesc.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newHomeBlockInfo;
            }
        }
    }
}
