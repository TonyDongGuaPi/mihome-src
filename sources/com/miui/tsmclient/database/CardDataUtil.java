package com.miui.tsmclient.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.CardInfoFactory;
import com.miui.tsmclient.util.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CardDataUtil {
    public static void saveCardInfo(Context context, CardInfo cardInfo) {
        if (context != null && cardInfo != null && cardInfo.mCardType != null) {
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("key", cardInfo.mCardType);
            contentValues.put("value", cardInfo.serialize().toString());
            context.getContentResolver().insert(DatabaseConstants.CONTENT_URI_CACHE, contentValues);
            context.getContentResolver().notifyChange(DatabaseConstants.PUBLIC_CONTENT_URI, (ContentObserver) null);
        }
    }

    public static List<CardInfo> loadCardList(Context context, String str) {
        String str2 = null;
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            str2 = "key IN ('" + str + "')";
        }
        Cursor query = context.getContentResolver().query(DatabaseConstants.CONTENT_URI_CACHE, DatabaseConstants.PROJECTION_CACHE, str2, (String[]) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    int columnIndex = query.getColumnIndex("key");
                    int columnIndex2 = query.getColumnIndex("value");
                    String string = query.getString(columnIndex);
                    Object nextValue = new JSONTokener(query.getString(columnIndex2)).nextValue();
                    if (nextValue instanceof JSONObject) {
                        CardInfo buildCardInfo = buildCardInfo(context, string, (JSONObject) nextValue);
                        if (buildCardInfo != null) {
                            arrayList.add(buildCardInfo);
                        }
                    } else if (nextValue instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) nextValue;
                        for (int i = 0; i < jSONArray.length(); i++) {
                            CardInfo buildCardInfo2 = buildCardInfo(context, string, jSONArray.getJSONObject(i));
                            if (buildCardInfo2 != null) {
                                arrayList.add(buildCardInfo2);
                            }
                        }
                    }
                } catch (JSONException e) {
                    LogUtils.e("invalid JSON format", e);
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            }
            query.close();
        }
        return arrayList;
    }

    private static CardInfo buildCardInfo(Context context, String str, JSONObject jSONObject) {
        CardInfo makeCardInfo = CardInfoFactory.makeCardInfo(str, jSONObject);
        if (jSONObject.has("cardName")) {
            JSONObject query = query(context, DatabaseConstants.CACHE_KEY_PRODUCT_PREFFIX + str);
            if (query != null) {
                LogUtils.d("product:" + query.toString());
                makeCardInfo.mCardSubName = query.optString("product_name");
            }
        }
        return makeCardInfo;
    }

    public static void saveCardList(Context context, List<? extends CardInfo> list, String str) {
        if (context != null) {
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("key", str);
            JSONArray jSONArray = new JSONArray();
            for (CardInfo serialize : list) {
                jSONArray.put(serialize.serialize());
            }
            contentValues.put("value", jSONArray.toString());
            context.getContentResolver().insert(DatabaseConstants.CONTENT_URI_CACHE, contentValues);
            context.getContentResolver().notifyChange(DatabaseConstants.PUBLIC_CONTENT_URI, (ContentObserver) null);
        }
    }

    public static JSONObject query(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Cursor query = context.getContentResolver().query(DatabaseConstants.CONTENT_URI_CACHE, DatabaseConstants.PROJECTION_CACHE, "key ='" + str + "'", (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    JSONObject jSONObject = new JSONObject(query.getString(query.getColumnIndex("value")));
                    query.close();
                    return jSONObject;
                }
            } catch (JSONException e) {
                LogUtils.e("invalid jsonobject readed from db", e);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
            query.close();
        }
        return null;
    }

    public static int deleteCards(Context context, String str) {
        if (context == null || str == null) {
            return 0;
        }
        int delete = context.getContentResolver().delete(DatabaseConstants.CONTENT_URI_CACHE, "key = ?", new String[]{str});
        LogUtils.i("delete " + str + ", cache count: " + delete);
        return delete;
    }

    public static void deleteCard(Context context, CardInfo cardInfo) {
        List<CardInfo> loadCardList;
        if (context != null && cardInfo != null && (loadCardList = loadCardList(context, cardInfo.getCardType())) != null) {
            Iterator<CardInfo> it = loadCardList.iterator();
            while (it.hasNext()) {
                if (TextUtils.equals(it.next().mAid, cardInfo.mAid)) {
                    it.remove();
                    saveCardList(context, loadCardList, cardInfo.getCardType());
                    return;
                }
            }
        }
    }

    public static int deleteAllCards(Context context) {
        if (context == null) {
            return 0;
        }
        int delete = context.getContentResolver().delete(DatabaseConstants.CONTENT_URI_CACHE, (String) null, (String[]) null);
        LogUtils.i("delete all cards, cache count: " + delete);
        return delete;
    }
}
