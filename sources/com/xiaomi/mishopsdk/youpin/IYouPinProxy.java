package com.xiaomi.mishopsdk.youpin;

import android.os.Bundle;
import java.util.HashMap;

public interface IYouPinProxy {
    void getShoppingCount(IShoppingCountCallback iShoppingCountCallback, boolean z);

    void gotoCheckout(Bundle bundle);

    void gotoHome();

    void gotoSearchPage();

    void gotoShopCart();

    void onAddCartSuccess(HashMap<String, String> hashMap);

    void onEvent(String str, HashMap<String, String> hashMap);

    void onPageEnd(String str, HashMap<String, String> hashMap);

    void onPageStart(String str, HashMap<String, String> hashMap);

    void showNotifcation(Bundle bundle);
}
