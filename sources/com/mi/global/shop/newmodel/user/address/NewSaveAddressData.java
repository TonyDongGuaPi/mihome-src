package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSaveAddressData {
    @SerializedName("addinfo")
    public NewAddressItem addinfo;
    @SerializedName("errors")
    public String errors;
    @SerializedName("result")
    public String result;

    public static NewSaveAddressData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSaveAddressData decode(ProtoReader protoReader) throws IOException {
        NewSaveAddressData newSaveAddressData = new NewSaveAddressData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newSaveAddressData.errors = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        newSaveAddressData.result = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newSaveAddressData.addinfo = NewAddressItem.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newSaveAddressData;
            }
        }
    }
}
