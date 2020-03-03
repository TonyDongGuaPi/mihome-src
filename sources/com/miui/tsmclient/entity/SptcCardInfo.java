package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;

public class SptcCardInfo extends FmshCardInfo {
    public static final Parcelable.Creator<SptcCardInfo> CREATOR = new Parcelable.Creator<SptcCardInfo>() {
        public SptcCardInfo createFromParcel(Parcel parcel) {
            SptcCardInfo sptcCardInfo = new SptcCardInfo((String) null);
            sptcCardInfo.readFromParcel(parcel);
            return sptcCardInfo;
        }

        public SptcCardInfo[] newArray(int i) {
            return new SptcCardInfo[i];
        }
    };

    public SptcCardInfo(String str) {
        super((CardAppInfo) null, str);
    }

    public SptcCardInfo(CardAppInfo cardAppInfo, String str) {
        super(cardAppInfo, str);
    }
}
