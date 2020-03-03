package com.mi.global.shop.newmodel.pay.payinfo;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewPayGoBFLResult extends BaseResult {
    @SerializedName("data")
    public NewPayGoResultData data;

    public static class Params {
        @SerializedName("code")
        public String code;
        @SerializedName("msg")
        public String msg;
        @SerializedName("url")
        public String url;
    }

    public static NewPayGoBFLResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewPayGoBFLResult decode(ProtoReader protoReader) throws IOException {
        NewPayGoBFLResult newPayGoBFLResult = new NewPayGoBFLResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newPayGoBFLResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newPayGoBFLResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newPayGoBFLResult.data = NewPayGoResultData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newPayGoBFLResult;
            }
        }
    }

    public static class NewPayGoResultData {
        @SerializedName("ext")
        public String ext;
        @SerializedName("html")
        public String html;
        @SerializedName("params")
        public String params;

        public static NewPayGoResultData decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static NewPayGoResultData decode(ProtoReader protoReader) throws IOException {
            NewPayGoResultData newPayGoResultData = new NewPayGoResultData();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            newPayGoResultData.html = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            newPayGoResultData.params = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 3:
                            newPayGoResultData.ext = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return newPayGoResultData;
                }
            }
        }
    }
}
