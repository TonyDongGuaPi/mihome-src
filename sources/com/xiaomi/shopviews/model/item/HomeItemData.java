package com.xiaomi.shopviews.model.item;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class HomeItemData {
    @SerializedName("type")

    /* renamed from: a  reason: collision with root package name */
    public int f13215a;
    @SerializedName("name")
    public String b;
    @SerializedName("content")
    public HomeItemContentBase c;

    public static HomeItemData a(byte[] bArr) throws IOException {
        return a(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomeItemData a(ProtoReader protoReader) throws IOException {
        HomeItemData homeItemData = new HomeItemData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        homeItemData.f13215a = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        homeItemData.b = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        homeItemData.c = HomeItemContentFactory.a(protoReader, homeItemData);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return homeItemData;
            }
        }
    }
}
