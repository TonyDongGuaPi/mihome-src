package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.xiaomi.shopviews.model.BaseResult;
import java.io.IOException;
import okio.Buffer;

public class HomeItem extends BaseResult {
    @SerializedName("data")
    public HomeItemData c;

    public static HomeItem a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItem a(ProtoReader protoReader) throws IOException {
        HomeItem homeItem = new HomeItem();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        homeItem.f13160a = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        homeItem.b = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        homeItem.c = HomeItemData.a(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return homeItem;
            }
        }
    }
}
