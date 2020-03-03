package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.sdk.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartHomePhoneNumUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18706a = "SHPhoneNumUtil";
    private static String b = "(\\+)?\\d{1,20}";
    private static Pattern c = Pattern.compile(b);
    private static HashMap<String, CountryPhoneNumData> d;
    private static HashMap<String, CountryPhoneNumData> e;

    private static boolean a() {
        return Locale.getDefault().getLanguage().equals(Locale.CHINESE.getLanguage());
    }

    public static CountryPhoneNumData a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        c(CommonApplication.getAppContext());
        CountryPhoneNumData countryPhoneNumData = e.get(str.toUpperCase());
        if (countryPhoneNumData != null) {
            return countryPhoneNumData;
        }
        return d.get(str.toUpperCase());
    }

    private static synchronized void c(Context context) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e2;
        JSONException e3;
        Exception e4;
        synchronized (SmartHomePhoneNumUtil.class) {
            if (d == null || e == null) {
                d = new HashMap<>();
                e = new HashMap<>();
                try {
                    inputStream = context.getResources().openRawResource(a() ? R.raw.smarthome_countries_cn : R.raw.smarthome_countries);
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
                            } catch (IOException e5) {
                                e2 = e5;
                                LogUtilGrey.a(f18706a, "error when load area codes" + e2.getMessage());
                                IOUtils.a(inputStream);
                                IOUtils.a((OutputStream) byteArrayOutputStream);
                            } catch (JSONException e6) {
                                e3 = e6;
                                LogUtilGrey.a("PhoneNumUtil", "error when parse json" + e3.getMessage());
                                IOUtils.a(inputStream);
                                IOUtils.a((OutputStream) byteArrayOutputStream);
                            } catch (Exception e7) {
                                e4 = e7;
                                try {
                                    LogUtilGrey.a("PhoneNumUtil", "unknown exception" + e4.getMessage());
                                    IOUtils.a(inputStream);
                                    IOUtils.a((OutputStream) byteArrayOutputStream);
                                } catch (Throwable th) {
                                    th = th;
                                    IOUtils.a(inputStream);
                                    IOUtils.a((OutputStream) byteArrayOutputStream);
                                    throw th;
                                }
                            }
                        }
                        JSONObject jSONObject = new JSONObject(byteArrayOutputStream.toString());
                        d = a(jSONObject.getJSONArray("countries"));
                        e = a(jSONObject.getJSONArray("recommend countries"));
                        IOUtils.a(inputStream);
                    } catch (IOException e8) {
                        byteArrayOutputStream = null;
                        e2 = e8;
                        LogUtilGrey.a(f18706a, "error when load area codes" + e2.getMessage());
                        IOUtils.a(inputStream);
                        IOUtils.a((OutputStream) byteArrayOutputStream);
                    } catch (JSONException e9) {
                        byteArrayOutputStream = null;
                        e3 = e9;
                        LogUtilGrey.a("PhoneNumUtil", "error when parse json" + e3.getMessage());
                        IOUtils.a(inputStream);
                        IOUtils.a((OutputStream) byteArrayOutputStream);
                    } catch (Exception e10) {
                        byteArrayOutputStream = null;
                        e4 = e10;
                        LogUtilGrey.a("PhoneNumUtil", "unknown exception" + e4.getMessage());
                        IOUtils.a(inputStream);
                        IOUtils.a((OutputStream) byteArrayOutputStream);
                    } catch (Throwable th2) {
                        byteArrayOutputStream = null;
                        th = th2;
                        IOUtils.a(inputStream);
                        IOUtils.a((OutputStream) byteArrayOutputStream);
                        throw th;
                    }
                } catch (IOException e11) {
                    byteArrayOutputStream = null;
                    e2 = e11;
                    inputStream = null;
                    LogUtilGrey.a(f18706a, "error when load area codes" + e2.getMessage());
                    IOUtils.a(inputStream);
                    IOUtils.a((OutputStream) byteArrayOutputStream);
                } catch (JSONException e12) {
                    byteArrayOutputStream = null;
                    e3 = e12;
                    inputStream = null;
                    LogUtilGrey.a("PhoneNumUtil", "error when parse json" + e3.getMessage());
                    IOUtils.a(inputStream);
                    IOUtils.a((OutputStream) byteArrayOutputStream);
                } catch (Exception e13) {
                    byteArrayOutputStream = null;
                    e4 = e13;
                    inputStream = null;
                    LogUtilGrey.a("PhoneNumUtil", "unknown exception" + e4.getMessage());
                    IOUtils.a(inputStream);
                    IOUtils.a((OutputStream) byteArrayOutputStream);
                } catch (Throwable th3) {
                    byteArrayOutputStream = null;
                    th = th3;
                    inputStream = null;
                    IOUtils.a(inputStream);
                    IOUtils.a((OutputStream) byteArrayOutputStream);
                    throw th;
                }
                IOUtils.a((OutputStream) byteArrayOutputStream);
            }
        }
    }

    public static HashMap<String, CountryPhoneNumData> a(JSONArray jSONArray) throws JSONException {
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
                countryPhoneNumData.d = arrayList;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("mc");
            if (optJSONArray2 != null) {
                ArrayList<String> arrayList2 = new ArrayList<>();
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    arrayList2.add(optJSONArray2.getString(i3));
                }
                countryPhoneNumData.e = arrayList2;
            }
            hashMap.put(string3, countryPhoneNumData);
        }
        return hashMap;
    }

    public static List<CountryPhoneNumData> a(Context context) {
        c(context);
        return a(d);
    }

    public static List<CountryPhoneNumData> b(Context context) {
        c(context);
        return a(e);
    }

    private static List<CountryPhoneNumData> a(HashMap<String, CountryPhoneNumData> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(hashMap.size());
        for (CountryPhoneNumData next : hashMap.values()) {
            if (next != null) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static class CountryPhoneNumData implements Comparable<CountryPhoneNumData> {

        /* renamed from: a  reason: collision with root package name */
        public String f18707a;
        public String b;
        public String c;
        ArrayList<Integer> d;
        ArrayList<String> e;

        CountryPhoneNumData(String str, String str2, String str3) {
            this.f18707a = str;
            this.b = str2;
            this.c = str3;
        }

        /* renamed from: a */
        public int compareTo(CountryPhoneNumData countryPhoneNumData) {
            return this.f18707a.compareTo(countryPhoneNumData.f18707a);
        }
    }
}
