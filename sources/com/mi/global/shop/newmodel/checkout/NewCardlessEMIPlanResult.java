package com.mi.global.shop.newmodel.checkout;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class NewCardlessEMIPlanResult extends BaseResult {
    @SerializedName("data")
    public EmiPlansData data;

    public static NewCardlessEMIPlanResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static NewCardlessEMIPlanResult decode(ProtoReader protoReader) throws IOException {
        NewCardlessEMIPlanResult newCardlessEMIPlanResult = new NewCardlessEMIPlanResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        newCardlessEMIPlanResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        newCardlessEMIPlanResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        newCardlessEMIPlanResult.data = EmiPlansData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return newCardlessEMIPlanResult;
            }
        }
    }

    public static class EmiPlansData {
        @SerializedName("approvedAmt")
        public String approvedAmt;
        @SerializedName("availableAmt")
        public String availableAmt;
        @SerializedName("emiPlans")
        public CardlessEMIPlans emiPlans;
        @SerializedName("phoneNumber")
        public String phoneNumber;
        @SerializedName("status")
        public int status;
        @SerializedName("statusMessage")
        public String statusMessage;
        @SerializedName("terms")
        public int terms;

        public static EmiPlansData decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static EmiPlansData decode(ProtoReader protoReader) throws IOException {
            EmiPlansData emiPlansData = new EmiPlansData();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            emiPlansData.status = ProtoAdapter.INT32.decode(protoReader).intValue();
                            break;
                        case 2:
                            emiPlansData.statusMessage = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 3:
                            emiPlansData.emiPlans = CardlessEMIPlans.decode(protoReader);
                            break;
                        case 4:
                            emiPlansData.approvedAmt = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 5:
                            emiPlansData.availableAmt = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 6:
                            emiPlansData.phoneNumber = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 7:
                            emiPlansData.terms = ProtoAdapter.INT32.decode(protoReader).intValue();
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return emiPlansData;
                }
            }
        }

        public static class CardlessEMIPlans {
            @SerializedName("basketAmount")
            public String basketAmount;
            @SerializedName("downpaymentAmount")
            public String downpaymentAmount;
            @SerializedName("downpaymentRate")
            public Double downpaymentRate;
            @SerializedName("emiOptions")
            public ArrayList<CardlessEMIPlanOption> emiOptions = new ArrayList<>();
            @SerializedName("interestRate")
            public Double interestRate;
            @SerializedName("loanAmount")
            public String loanAmount;
            @SerializedName("processingFee")
            public String processingFee;
            @SerializedName("processingFeeRate")
            public Double processingFeeRate;

            public static CardlessEMIPlans decode(byte[] bArr) throws IOException {
                return decode(new ProtoReader(new Buffer().write(bArr)));
            }

            public static CardlessEMIPlans decode(ProtoReader protoReader) throws IOException {
                CardlessEMIPlans cardlessEMIPlans = new CardlessEMIPlans();
                long beginMessage = protoReader.beginMessage();
                while (true) {
                    int nextTag = protoReader.nextTag();
                    if (nextTag != -1) {
                        switch (nextTag) {
                            case 1:
                                cardlessEMIPlans.basketAmount = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 2:
                                cardlessEMIPlans.loanAmount = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 3:
                                cardlessEMIPlans.downpaymentAmount = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 4:
                                cardlessEMIPlans.processingFee = ProtoAdapter.STRING.decode(protoReader);
                                break;
                            case 5:
                                cardlessEMIPlans.processingFeeRate = ProtoAdapter.DOUBLE.decode(protoReader);
                                break;
                            case 6:
                                cardlessEMIPlans.downpaymentRate = ProtoAdapter.DOUBLE.decode(protoReader);
                                break;
                            case 7:
                                cardlessEMIPlans.interestRate = ProtoAdapter.DOUBLE.decode(protoReader);
                                break;
                            case 8:
                                cardlessEMIPlans.emiOptions.add(CardlessEMIPlanOption.decode(protoReader));
                                break;
                            default:
                                protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                                break;
                        }
                    } else {
                        protoReader.endMessage(beginMessage);
                        return cardlessEMIPlans;
                    }
                }
            }
        }
    }

    public static class CardlessEMIPlanOption {
        @SerializedName("interestAmount")
        public String interestAmount;
        @SerializedName("interestFreeMonths")
        public int interestFreeMonths;
        @SerializedName("isDefault")
        public boolean isDefault;
        @SerializedName("numberOfMonths")
        public int numberOfMonths;
        @SerializedName("totalAmount")
        public String totalAmount;
        @SerializedName("totalMonthlyAmount")
        public String totalMonthlyAmount;

        public static CardlessEMIPlanOption decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static CardlessEMIPlanOption decode(ProtoReader protoReader) throws IOException {
            CardlessEMIPlanOption cardlessEMIPlanOption = new CardlessEMIPlanOption();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            cardlessEMIPlanOption.isDefault = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                            break;
                        case 2:
                            cardlessEMIPlanOption.numberOfMonths = ProtoAdapter.INT32.decode(protoReader).intValue();
                            break;
                        case 3:
                            cardlessEMIPlanOption.totalMonthlyAmount = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 4:
                            cardlessEMIPlanOption.interestAmount = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 5:
                            cardlessEMIPlanOption.interestFreeMonths = ProtoAdapter.INT32.decode(protoReader).intValue();
                            break;
                        case 6:
                            cardlessEMIPlanOption.totalAmount = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return cardlessEMIPlanOption;
                }
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CardlessEMIPlanOption cardlessEMIPlanOption = (CardlessEMIPlanOption) obj;
            if (cardlessEMIPlanOption.isDefault != this.isDefault || cardlessEMIPlanOption.numberOfMonths != this.numberOfMonths || cardlessEMIPlanOption.totalMonthlyAmount != this.totalMonthlyAmount || cardlessEMIPlanOption.interestAmount != this.interestAmount || cardlessEMIPlanOption.interestFreeMonths != this.interestFreeMonths) {
                return false;
            }
            if (cardlessEMIPlanOption.isDefault == this.isDefault && cardlessEMIPlanOption.numberOfMonths == this.numberOfMonths && cardlessEMIPlanOption.totalMonthlyAmount == this.totalMonthlyAmount && cardlessEMIPlanOption.interestAmount == this.interestAmount && cardlessEMIPlanOption.interestFreeMonths == this.interestFreeMonths) {
                return true;
            }
            return super.equals(obj);
        }
    }
}
