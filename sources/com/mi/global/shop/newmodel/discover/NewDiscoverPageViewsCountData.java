package com.mi.global.shop.newmodel.discover;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewDiscoverPageViewsCountData {
    @SerializedName("cnt")
    public String cnt;
    @SerializedName("is_ok")
    public boolean isOk;

    public static NewDiscoverPageViewsCountData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewDiscoverPageViewsCountData decode(ProtoReader protoReader) throws IOException {
        NewDiscoverPageViewsCountData newDiscoverPageViewsCountData = new NewDiscoverPageViewsCountData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newDiscoverPageViewsCountData.cnt = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newDiscoverPageViewsCountData.isOk = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newDiscoverPageViewsCountData;
            }
        }
    }
}
