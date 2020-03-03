package com.mi.global.shop.newmodel.virtualview;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class VirtualViewResult extends BaseResult {
    @SerializedName("data")
    public VirtualViewModel data = new VirtualViewModel();

    public static VirtualViewResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static VirtualViewResult decode(ProtoReader protoReader) throws IOException {
        VirtualViewResult virtualViewResult = new VirtualViewResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag == -1) {
                protoReader.endMessage(beginMessage);
                return virtualViewResult;
            } else if (nextTag != 3) {
                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
            } else {
                virtualViewResult.data = VirtualViewModel.decode(protoReader);
            }
        }
    }
}
