package com.xiaomi.passport.ui.settings.utils;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.IOUtils;
import com.xiaomi.passport.ui.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhoneNumUtil {
    private static final String TAG = "PhoneNumUtil";
    private static String pattern = "(\\+)?\\d{1,20}";
    private static Pattern regex = Pattern.compile(pattern);
    private static Context sContext;
    private static HashMap<String, CountryPhoneNumData> sMapCountryPhoneData;
    private static HashMap<String, CountryPhoneNumData> sMapRecommendCountryPhoneData;

    public static void initializeCountryPhoneData(Context context) {
        if (sContext == null) {
            sContext = context.getApplicationContext();
        }
    }

    public static synchronized void ensureDataLoaded() {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        IOException e;
        JSONException e2;
        synchronized (PhoneNumUtil.class) {
            if (sMapCountryPhoneData == null || sMapRecommendCountryPhoneData == null) {
                sMapCountryPhoneData = new HashMap<>();
                sMapRecommendCountryPhoneData = new HashMap<>();
                try {
                    inputStream = sContext.getResources().openRawResource(R.raw.passport_countries);
                    try {
                        byte[] bArr = new byte[512];
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            try {
                                int read = inputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (IOException e3) {
                                e = e3;
                                AccountLog.e(TAG, "error when load area codes", e);
                                IOUtils.closeQuietly(inputStream);
                                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                            } catch (JSONException e4) {
                                e2 = e4;
                                try {
                                    AccountLog.e(TAG, "error when parse json", e2);
                                    IOUtils.closeQuietly(inputStream);
                                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                                } catch (Throwable th) {
                                    th = th;
                                    IOUtils.closeQuietly(inputStream);
                                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                                    throw th;
                                }
                            }
                        }
                        sMapCountryPhoneData = processCountryDataFromJson(new JSONObject(byteArrayOutputStream.toString()).getJSONArray("countries"));
                        IOUtils.closeQuietly(inputStream);
                    } catch (IOException e5) {
                        byteArrayOutputStream = null;
                        e = e5;
                        AccountLog.e(TAG, "error when load area codes", e);
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    } catch (JSONException e6) {
                        byteArrayOutputStream = null;
                        e2 = e6;
                        AccountLog.e(TAG, "error when parse json", e2);
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    } catch (Throwable th2) {
                        byteArrayOutputStream = null;
                        th = th2;
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                        throw th;
                    }
                } catch (IOException e7) {
                    byteArrayOutputStream = null;
                    e = e7;
                    inputStream = null;
                    AccountLog.e(TAG, "error when load area codes", e);
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                } catch (JSONException e8) {
                    byteArrayOutputStream = null;
                    e2 = e8;
                    inputStream = null;
                    AccountLog.e(TAG, "error when parse json", e2);
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                } catch (Throwable th3) {
                    byteArrayOutputStream = null;
                    th = th3;
                    inputStream = null;
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    throw th;
                }
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
            }
        }
    }

    public static HashMap<String, CountryPhoneNumData> processCountryDataFromJson(JSONArray jSONArray) throws JSONException {
        HashMap<String, CountryPhoneNumData> hashMap = new HashMap<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("cn");
            String string2 = jSONObject.getString("ic");
            String string3 = jSONObject.getString("iso");
            CountryPhoneNumData countryPhoneNumData = new CountryPhoneNumData(string, string2, string3);
            JSONArray optJSONArray = jSONObject.optJSONArray("len");
            if (optJSONArray != null) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList.add(Integer.valueOf(optJSONArray.getInt(i2)));
                }
                countryPhoneNumData.lengths = arrayList;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("mc");
            if (optJSONArray2 != null) {
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    arrayList2.add(optJSONArray2.getString(i3));
                }
                countryPhoneNumData.prefix = arrayList2;
            }
            hashMap.put(string3, countryPhoneNumData);
        }
        return hashMap;
    }

    public static List<String> getCountryList() {
        ensureDataLoaded();
        ArrayList arrayList = new ArrayList(sMapCountryPhoneData.size());
        for (Map.Entry<String, CountryPhoneNumData> value : sMapCountryPhoneData.entrySet()) {
            arrayList.add(((CountryPhoneNumData) value.getValue()).countryName);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static List<CountryPhoneNumData> getCountryPhoneNumDataList() {
        ensureDataLoaded();
        return getCountryPhoneNumDataListFromData(sMapCountryPhoneData);
    }

    public static List<CountryPhoneNumData> getRecommendCountryPhoneNumDataList() {
        ensureDataLoaded();
        return getCountryPhoneNumDataListFromData(sMapRecommendCountryPhoneData);
    }

    private static List<CountryPhoneNumData> getCountryPhoneNumDataListFromData(HashMap<String, CountryPhoneNumData> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(hashMap.size());
        for (CountryPhoneNumData add : hashMap.values()) {
            arrayList.add(add);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static String getCountryISOFromPhoneNum(String str) {
        if (!str.startsWith("+")) {
            return "CN";
        }
        ensureDataLoaded();
        for (Map.Entry next : sMapCountryPhoneData.entrySet()) {
            if (str.startsWith("+" + ((CountryPhoneNumData) next.getValue()).countryCode)) {
                return (String) next.getKey();
            }
        }
        return "";
    }

    public static CountryPhoneNumData getCounrtyPhoneDataFromIso(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ensureDataLoaded();
        CountryPhoneNumData countryPhoneNumData = sMapRecommendCountryPhoneData.get(str.toUpperCase());
        if (countryPhoneNumData != null) {
            return countryPhoneNumData;
        }
        return sMapCountryPhoneData.get(str.toUpperCase());
    }

    public static String checkNumber(String str, CountryPhoneNumData countryPhoneNumData) {
        if (str == null || str.startsWith("+") || str.startsWith("00") || countryPhoneNumData == null || !regex.matcher(str).matches()) {
            AccountLog.e(TAG, "phoneNumber 为空或者是 data为空");
            return null;
        } else if (countryPhoneNumData.countryISO.equals("CN")) {
            return str;
        } else {
            return "+" + countryPhoneNumData.countryCode + str;
        }
    }

    public static String checkStrictNumber(String str, CountryPhoneNumData countryPhoneNumData) {
        boolean z;
        if (str == null || str.startsWith("+") || str.startsWith("00") || countryPhoneNumData == null || !regex.matcher(str).matches()) {
            AccountLog.e(TAG, "phoneNumber 为空或者是 data为空");
            return null;
        }
        boolean z2 = true;
        if (countryPhoneNumData.lengths != null) {
            Iterator<Integer> it = countryPhoneNumData.lengths.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (str.length() == it.next().intValue()) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return null;
            }
        }
        if (countryPhoneNumData.prefix != null) {
            Iterator<String> it2 = countryPhoneNumData.prefix.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                String next = it2.next();
                if (next.startsWith("x")) {
                    int lastIndexOf = next.lastIndexOf("x");
                    if (lastIndexOf > str.length()) {
                        continue;
                    } else {
                        int i = lastIndexOf + 1;
                        if (str.substring(i).startsWith(next.substring(i))) {
                            break;
                        }
                    }
                } else if (str.startsWith(next)) {
                    break;
                }
            }
            if (!z2) {
                return null;
            }
        }
        if (countryPhoneNumData.countryISO.equals("CN")) {
            return str;
        }
        return "+" + countryPhoneNumData.countryCode + str;
    }

    public static class CountryPhoneNumData implements Comparable<CountryPhoneNumData> {
        public String countryCode;
        public String countryISO;
        public String countryName;
        ArrayList<Integer> lengths;
        ArrayList<String> prefix;

        public CountryPhoneNumData(String str, String str2, String str3) {
            this.countryName = str;
            this.countryCode = str2;
            this.countryISO = str3;
        }

        public int compareTo(CountryPhoneNumData countryPhoneNumData) {
            return this.countryName.compareTo(countryPhoneNumData.countryName);
        }
    }
}
