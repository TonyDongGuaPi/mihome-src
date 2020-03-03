package com.miui.tsmclient.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoManager;
import com.miui.tsmclient.util.ResUtils;
import com.miui.tsmclient.util.StringUtils;

public class TSMPublicProvider extends ContentProvider {
    private static final String KEY_ALL_CARDS_COUNT = "all_cards_count";
    private static final String KEY_BANK_CARDS_COUNT = "bank_cards_count";
    private static final String KEY_DEFAULT_TRANS_CARD_BALANCE = "default_trans_card_balance";
    private static final String METHOD_CARDS_INFO = "cards_info";

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        String str3;
        String str4;
        Context context = getContext();
        Bundle bundle2 = new Bundle();
        if (METHOD_CARDS_INFO.equals(str)) {
            int i = 0;
            CardInfo defaultTransCard = CardInfoManager.getInstance(context).getDefaultTransCard();
            if (defaultTransCard != null) {
                i = 1;
                bundle2.putString(KEY_DEFAULT_TRANS_CARD_BALANCE, StringUtils.formatAmount(defaultTransCard.mCardBalance) + ResUtils.getString(context, "yuan"));
            }
            int bankCardsCount = CardInfoManager.getInstance(context).getBankCardsCount();
            if (bankCardsCount == 0) {
                str3 = "";
            } else {
                str3 = bankCardsCount + ResUtils.getString(context, "bank_card_unit");
            }
            bundle2.putString(KEY_BANK_CARDS_COUNT, str3);
            int mifareCardsCount = i + bankCardsCount + CardInfoManager.getInstance(context).getMifareCardsCount();
            if (mifareCardsCount == 0) {
                str4 = "";
            } else {
                str4 = mifareCardsCount + ResUtils.getString(context, "card_unit");
            }
            bundle2.putString(KEY_ALL_CARDS_COUNT, str4);
        }
        return bundle2;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
