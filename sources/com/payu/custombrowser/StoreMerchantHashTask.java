package com.payu.custombrowser;

import android.os.AsyncTask;
import com.payu.custombrowser.util.CBAnalyticsConstant;
import org.json.JSONObject;

class StoreMerchantHashTask extends AsyncTask<String, Void, Void> {
    StoreMerchantHashTask() {
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(String... strArr) {
        String str = strArr[0];
        ClassLoader classLoader = Bank.class.getClassLoader();
        try {
            if (!isCancelled()) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(CBAnalyticsConstant.CARD_TOKEN) && jSONObject.has(CBAnalyticsConstant.MERCHANT_HASH)) {
                    Object invoke = classLoader.loadClass("com.payu.india.CallBackHandler.OnetapCallback").getDeclaredMethod("getOneTapCallback", new Class[0]).invoke((Object) null, new Object[0]);
                    invoke.getClass().getDeclaredMethod("saveOneClickHash", new Class[]{String.class, String.class}).invoke(invoke, new Object[]{jSONObject.getString(CBAnalyticsConstant.CARD_TOKEN), jSONObject.getString(CBAnalyticsConstant.MERCHANT_HASH)});
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
    }
}
