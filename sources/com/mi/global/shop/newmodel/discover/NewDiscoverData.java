package com.mi.global.shop.newmodel.discover;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewDiscoverData {
    @SerializedName("currentpage")
    public int currentpage = 0;
    @SerializedName("items")
    public ArrayList<NewDiscoverListItem> items = new ArrayList<>();
    @SerializedName("totalpages")
    public long totalpages = 0;

    public static NewDiscoverData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewDiscoverData decode(ProtoReader protoReader) throws IOException {
        NewDiscoverData newDiscoverData = new NewDiscoverData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newDiscoverData.totalpages = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    case 2:
                        newDiscoverData.currentpage = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 3:
                        newDiscoverData.items.add(NewDiscoverListItem.decode(protoReader));
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newDiscoverData;
            }
        }
    }
}
