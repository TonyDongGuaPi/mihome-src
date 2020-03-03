package com.mi.global.shop.newmodel;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class GetOtpResult extends BaseResult {
    @SerializedName("data")
    public GetOtpData data;

    public static GetOtpResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static GetOtpResult decode(ProtoReader protoReader) throws IOException {
        GetOtpResult getOtpResult = new GetOtpResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        getOtpResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        getOtpResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        getOtpResult.data = GetOtpData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return getOtpResult;
            }
        }
    }

    public static class GetOtpData {
        @SerializedName("mobileNumber")
        public String mobileNumber;
        @SerializedName("transactionCode")
        public String transactionCode;

        public static GetOtpData decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static GetOtpData decode(ProtoReader protoReader) throws IOException {
            GetOtpData getOtpData = new GetOtpData();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            getOtpData.transactionCode = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            getOtpData.mobileNumber = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return getOtpData;
                }
            }
        }
    }
}
