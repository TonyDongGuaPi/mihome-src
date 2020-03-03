package com.mi.global.shop.model.push;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.Utils;
import com.mi.util.MiToast;
import java.util.ArrayList;
import java.util.Iterator;

public class PushClassifyModel {
    @SerializedName("pushTypeList")
    public ArrayList<PushClassifyItem> items;

    public static class PushClassifyItem {
        @SerializedName("defaultStatus")
        public boolean defaultStatus;
        @SerializedName("desc")
        public String desc;
        @SerializedName("enableclose")
        public boolean enableclose;
        @SerializedName("key")
        public String key;
        @SerializedName("title")
        public String title;
    }

    public static void changeKeyChecked(Context context, String str, boolean z) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        try {
            arrayList = (ArrayList) gson.fromJson(Utils.Preference.getStringPref(ShopApp.g(), "pref_key_push_classify_key", ""), new TypeToken<ArrayList<PushClassifyItem>>() {
            }.getType());
        } catch (Exception e) {
            MiToast.a(context, (CharSequence) context.getResources().getString(R.string.invalid_data), 3000);
            e.printStackTrace();
        }
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        boolean z2 = true;
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                PushClassifyItem pushClassifyItem = (PushClassifyItem) it.next();
                if (pushClassifyItem.key.equals(str)) {
                    pushClassifyItem.defaultStatus = z;
                    z2 = false;
                }
            }
        }
        if (z2) {
            PushClassifyItem pushClassifyItem2 = new PushClassifyItem();
            pushClassifyItem2.key = str;
            pushClassifyItem2.defaultStatus = z;
            arrayList.add(pushClassifyItem2);
        }
        Utils.Preference.setStringPref(ShopApp.g(), "pref_key_push_classify_key", gson.toJson((Object) arrayList));
    }
}
