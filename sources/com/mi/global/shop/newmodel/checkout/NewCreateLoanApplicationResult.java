package com.mi.global.shop.newmodel.checkout;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewCreateLoanApplicationResult extends BaseResult {
    @SerializedName("data")
    public LoanApplicationData data;

    public static NewCreateLoanApplicationResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCreateLoanApplicationResult decode(ProtoReader protoReader) throws IOException {
        NewCreateLoanApplicationResult newCreateLoanApplicationResult = new NewCreateLoanApplicationResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCreateLoanApplicationResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newCreateLoanApplicationResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCreateLoanApplicationResult.data = LoanApplicationData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCreateLoanApplicationResult;
            }
        }
    }

    public static class LoanApplicationData {
        @SerializedName("content")
        public String content;
        @SerializedName("result")
        public boolean result;

        public static LoanApplicationData decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static LoanApplicationData decode(ProtoReader protoReader) throws IOException {
            LoanApplicationData loanApplicationData = new LoanApplicationData();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            loanApplicationData.result = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                            break;
                        case 2:
                            loanApplicationData.content = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return loanApplicationData;
                }
            }
        }
    }
}
