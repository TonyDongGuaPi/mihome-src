package com.mi.global.shop.newmodel.user.address;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewSetDefaultAddressData {
    @SerializedName("data")
    public boolean data;

    public static NewSetDefaultAddressData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewSetDefaultAddressData decode(ProtoReader protoReader) throws IOException {
        NewSetDefaultAddressData newSetDefaultAddressData = new NewSetDefaultAddressData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return newSetDefaultAddressData;
            } else if (nextTag != 1) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                newSetDefaultAddressData.data = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
            }
        }
    }
}
