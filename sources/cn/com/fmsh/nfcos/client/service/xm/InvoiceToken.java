package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class InvoiceToken implements Parcelable {
    public static final Parcelable.Creator<InvoiceToken> CREATOR = new Parcelable.Creator<InvoiceToken>() {
        public InvoiceToken createFromParcel(Parcel parcel) {
            InvoiceToken invoiceToken = new InvoiceToken();
            invoiceToken.token = parcel.readString();
            return invoiceToken;
        }

        public InvoiceToken[] newArray(int i) {
            return new InvoiceToken[i];
        }
    };
    public String token;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.token);
    }

    public void readFromParcel(Parcel parcel) {
        this.token = parcel.readString();
    }
}
