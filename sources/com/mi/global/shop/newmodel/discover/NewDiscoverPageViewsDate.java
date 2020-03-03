package com.mi.global.shop.newmodel.discover;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewDiscoverPageViewsDate extends BaseResult {
    @SerializedName("common")
    public Object common;
    @SerializedName("data")
    public NewDiscoverPageViewsCountData data;
    @SerializedName("security")
    public boolean security;

    public static NewDiscoverPageViewsDate decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewDiscoverPageViewsDate decode(ProtoReader protoReader) throws IOException {
        NewDiscoverPageViewsDate newDiscoverPageViewsDate = new NewDiscoverPageViewsDate();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newDiscoverPageViewsDate.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newDiscoverPageViewsDate.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newDiscoverPageViewsDate.data = NewDiscoverPageViewsCountData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newDiscoverPageViewsDate;
            }
        }
    }
}
