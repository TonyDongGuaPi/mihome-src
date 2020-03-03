package com.mobikwik.sdk.lib.payinstrument;

import com.mobikwik.sdk.lib.utils.RSAEncUtils;
import java.io.Serializable;

public class Card implements PaymentInstrument, Serializable {
    public static final int CARD_FAMILY_CRADIT = 1;
    public static final int CARD_FAMILY_DEBIT = 2;
    public static final int CARD_FAMILY_UNSPECIFIED = 0;
    public static final String CARD_TYPE_AMEX = "amex";
    public static final String CARD_TYPE_DINERS = "diners";
    public static final String CARD_TYPE_DISCOVER = "discover";
    public static final String CARD_TYPE_MAESTRO = "maestro";
    public static final String CARD_TYPE_MC = "mastercard";
    public static final String CARD_TYPE_UNSUPPORTED = "Unsupported Card";
    public static final String CARD_TYPE_VISA = "visa";
    /* access modifiers changed from: private */
    public String cardCvv;
    /* access modifiers changed from: private */
    public String cardExpiryMonth;
    /* access modifiers changed from: private */
    public String cardExpiryYear;
    /* access modifiers changed from: private */
    public String cardHoldersName;
    /* access modifiers changed from: private */
    public String cardNumber;
    String cardValidityError;
    /* access modifiers changed from: private */
    public boolean saveCard;
    /* access modifiers changed from: private */
    public String savedCardId;

    public static class Builder {
        private String cardCvv;
        private String cardExpiryMonth;
        private String cardExpiryYear;
        private String cardHoldersName;
        private String cardId;
        private String cardNumber;
        private boolean saveCard;

        public Card build() {
            Card card = new Card();
            String unused = card.cardNumber = this.cardNumber;
            String unused2 = card.savedCardId = this.cardId;
            String unused3 = card.cardExpiryYear = this.cardExpiryYear == null ? "" : this.cardExpiryYear;
            String unused4 = card.cardExpiryMonth = this.cardExpiryMonth == null ? "" : this.cardExpiryMonth;
            String unused5 = card.cardCvv = this.cardCvv == null ? "" : this.cardCvv;
            String unused6 = card.cardHoldersName = this.cardHoldersName;
            if (!card.isSavedCard() && this.cardNumber != null) {
                boolean unused7 = card.saveCard = this.saveCard;
            }
            return card;
        }

        public Builder setCardCVV(String str) {
            this.cardCvv = str;
            return this;
        }

        public Builder setCardExpMonth(String str) {
            this.cardExpiryMonth = str;
            return this;
        }

        public Builder setCardExpYear(String str) {
            this.cardExpiryYear = str;
            return this;
        }

        public Builder setCardFamily(int i) {
            return this;
        }

        public Builder setCardId(String str) {
            this.cardId = str;
            return this;
        }

        public Builder setCardNumber(String str) {
            this.cardNumber = str;
            return this;
        }

        public Builder setNameOnCard(String str) {
            this.cardHoldersName = str;
            return this;
        }

        public Builder setStoreThisCard(boolean z) {
            this.saveCard = z;
            return this;
        }
    }

    private Card() {
    }

    public Card encrypt(RSAEncUtils rSAEncUtils) {
        Card card = new Card();
        if (this.savedCardId != null) {
            card.savedCardId = this.savedCardId;
        } else {
            card.cardNumber = rSAEncUtils.encrypt(this.cardNumber);
            card.cardExpiryMonth = rSAEncUtils.encrypt(this.cardExpiryMonth);
            card.cardExpiryYear = rSAEncUtils.encrypt(this.cardExpiryYear);
        }
        card.cardCvv = rSAEncUtils.encrypt(this.cardCvv);
        card.cardHoldersName = this.cardHoldersName;
        card.saveCard = this.saveCard;
        return card;
    }

    public String getCardCVV() {
        return this.cardCvv;
    }

    public String getCardExpMonth() {
        return this.cardExpiryMonth;
    }

    public String getCardExpYear() {
        return this.cardExpiryYear;
    }

    public String getCardId() {
        return this.savedCardId;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getNameOnCard() {
        return this.cardHoldersName;
    }

    public boolean isSavedCard() {
        return this.savedCardId != null;
    }

    public boolean isToStoreThisCard() {
        return this.saveCard;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ab, code lost:
        if (r7.cardCvv.length() == 3) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b3, code lost:
        if (r7.cardCvv.length() == 4) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0116, code lost:
        if (r0 < r3) goto L_0x0118;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isValid() {
        /*
            r7 = this;
            boolean r0 = r7.isSavedCard()
            r1 = 0
            if (r0 != 0) goto L_0x002f
            java.lang.String r0 = r7.cardNumber
            int r0 = r0.length()
            r2 = 13
            if (r0 < r2) goto L_0x002a
            java.lang.String r0 = r7.cardNumber
            int r0 = r0.length()
            r2 = 19
            if (r0 > r2) goto L_0x002a
            java.lang.String r0 = r7.cardNumber
            boolean r0 = com.mobikwik.sdk.lib.utils.CardUtils.validateCardNumber(r0)
            if (r0 == 0) goto L_0x002a
            java.lang.String r0 = r7.cardNumber
            java.lang.String r0 = com.mobikwik.sdk.lib.utils.CardUtils.detectType(r0)
            goto L_0x0030
        L_0x002a:
            java.lang.String r0 = "Invalid card number"
        L_0x002c:
            r7.cardValidityError = r0
            return r1
        L_0x002f:
            r0 = 0
        L_0x0030:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Card.isValid : cardType "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.mobikwik.sdk.lib.utils.Utils.print(r2)
            java.lang.String r2 = "maestro"
            boolean r0 = r2.equalsIgnoreCase(r0)
            boolean r2 = r7.isSavedCard()
            r3 = 4
            r4 = 3
            r5 = 1
            if (r2 == 0) goto L_0x0084
            java.lang.String r2 = r7.cardCvv
            boolean r2 = com.mobikwik.sdk.lib.utils.Utils.isNull(r2)
            if (r2 != 0) goto L_0x0075
            java.lang.String r2 = r7.cardCvv
            boolean r2 = com.mobikwik.sdk.lib.utils.Utils.isNumeric(r2)
            if (r2 == 0) goto L_0x0075
            java.lang.String r2 = r7.cardCvv
            int r2 = r2.length()
            if (r2 == r4) goto L_0x0073
            java.lang.String r2 = r7.cardCvv
            int r2 = r2.length()
            if (r2 != r3) goto L_0x0075
        L_0x0073:
            r2 = 1
            goto L_0x0076
        L_0x0075:
            r2 = 0
        L_0x0076:
            java.lang.String r3 = r7.cardCvv
            boolean r3 = com.mobikwik.sdk.lib.utils.Utils.isNull(r3)
            if (r3 == 0) goto L_0x00b6
            java.lang.String r2 = ""
            r7.cardCvv = r2
        L_0x0082:
            r2 = 1
            goto L_0x00b6
        L_0x0084:
            java.lang.String r2 = r7.cardCvv
            boolean r2 = com.mobikwik.sdk.lib.utils.Utils.isNull(r2)
            if (r2 == 0) goto L_0x0094
            if (r0 == 0) goto L_0x0094
            java.lang.String r2 = ""
            r7.cardCvv = r2
            r2 = 1
            goto L_0x0095
        L_0x0094:
            r2 = 0
        L_0x0095:
            java.lang.String r6 = r7.cardCvv
            boolean r6 = com.mobikwik.sdk.lib.utils.Utils.isNull(r6)
            if (r6 != 0) goto L_0x00ad
            java.lang.String r6 = r7.cardCvv
            boolean r6 = com.mobikwik.sdk.lib.utils.Utils.isNumeric(r6)
            if (r6 == 0) goto L_0x00ad
            java.lang.String r6 = r7.cardCvv
            int r6 = r6.length()
            if (r6 == r4) goto L_0x0082
        L_0x00ad:
            java.lang.String r4 = r7.cardCvv
            int r4 = r4.length()
            if (r4 != r3) goto L_0x00b6
            goto L_0x0082
        L_0x00b6:
            if (r2 != 0) goto L_0x00bc
            java.lang.String r0 = "Invalid card cvv"
            goto L_0x002c
        L_0x00bc:
            boolean r2 = r7.isSavedCard()
            if (r2 != 0) goto L_0x0124
            java.lang.String r2 = r7.cardExpiryYear
            boolean r2 = com.mobikwik.sdk.lib.utils.Utils.isNull(r2)
            if (r2 == 0) goto L_0x00d5
            java.lang.String r2 = r7.cardExpiryMonth
            boolean r2 = com.mobikwik.sdk.lib.utils.Utils.isNull(r2)
            if (r2 == 0) goto L_0x00d5
            if (r0 == 0) goto L_0x00d5
            goto L_0x0124
        L_0x00d5:
            java.lang.String r0 = r7.cardExpiryYear
            boolean r0 = com.mobikwik.sdk.lib.utils.Utils.isNull(r0)
            if (r0 != 0) goto L_0x0120
            java.lang.String r0 = r7.cardExpiryMonth
            boolean r0 = com.mobikwik.sdk.lib.utils.Utils.isNull(r0)
            if (r0 == 0) goto L_0x00e6
            goto L_0x0120
        L_0x00e6:
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            int r0 = r0.get(r5)
            int r0 = r0 + -2000
            java.lang.String r2 = r7.cardExpiryYear
            int r2 = java.lang.Integer.parseInt(r2)
            int r2 = r2 - r0
            int r2 = r2 + r5
            if (r2 <= 0) goto L_0x011c
            r0 = 30
            if (r2 > r0) goto L_0x011c
            java.lang.String r0 = r7.cardExpiryMonth
            int r0 = java.lang.Integer.parseInt(r0)
            if (r0 <= 0) goto L_0x0118
            r3 = 12
            if (r0 > r3) goto L_0x0118
            java.util.Calendar r3 = java.util.Calendar.getInstance()
            r4 = 2
            int r3 = r3.get(r4)
            int r3 = r3 + r5
            if (r2 != r5) goto L_0x0124
            if (r0 >= r3) goto L_0x0124
        L_0x0118:
            java.lang.String r0 = "Invalid card Expiry Month"
            goto L_0x002c
        L_0x011c:
            java.lang.String r0 = "Invalid card Expiry Year"
            goto L_0x002c
        L_0x0120:
            java.lang.String r0 = "Invalid card Expiry"
            goto L_0x002c
        L_0x0124:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.lib.payinstrument.Card.isValid():boolean");
    }
}
