package com.mi.global.shop.newmodel.checkout;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class NewLoanPayResult extends BaseResult {
    @SerializedName("data")
    public LoanAgreementData data;

    public static class Params {
        @SerializedName("url")
        public String url;
    }

    public static NewLoanPayResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewLoanPayResult decode(ProtoReader protoReader) throws IOException {
        NewLoanPayResult newLoanPayResult = new NewLoanPayResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newLoanPayResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newLoanPayResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newLoanPayResult.data = LoanAgreementData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newLoanPayResult;
            }
        }
    }

    public static class LoanAgreementData {
        @SerializedName("html")
        public String html;
        @SerializedName("params")
        public String params;

        public static LoanAgreementData decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static LoanAgreementData decode(ProtoReader protoReader) throws IOException {
            LoanAgreementData loanAgreementData = new LoanAgreementData();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            loanAgreementData.html = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            loanAgreementData.params = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return loanAgreementData;
                }
            }
        }
    }
}
