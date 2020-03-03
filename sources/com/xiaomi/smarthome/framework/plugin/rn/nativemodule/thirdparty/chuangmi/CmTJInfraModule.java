package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.chuangmi;

import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.tiqiaa.icontrol.util.LanguageUtils;
import com.tiqiaa.remote.entity.AirRemoteState;
import com.tiqiaa.remote.entity.Brand;
import com.tiqiaa.remote.entity.Infrared;
import com.tiqiaa.remote.entity.Key;
import com.tiqiaa.remote.entity.MatchPage;
import com.tiqiaa.remote.entity.Page;
import com.tiqiaa.remote.entity.Remote;
import com.tiqiaa.tv.entity.City;
import com.tiqiaa.tv.entity.CityProvider;
import com.tiqiaa.tv.entity.CityProviderRemote;
import com.tiqiaa.tv.entity.Province;
import com.tiqiaa.tv.entity.TvProvider;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.chuangmi.CmInfraredDataProvider;
import com.xiaomi.smarthome.framework.plugin.rn.utils.JSONUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class CmTJInfraModule extends ReactContextBaseJavaModule {
    private String mCurrentClient;
    private Map<String, CmInfraredDataProvider> mInfraredDataProviderMap = new HashMap();

    public String getName() {
        return "TJInfra";
    }

    public CmTJInfraModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("MachineType_TV", 2);
        hashMap.put("MachineType_AirCond", 7);
        hashMap.put("MachineType_Fan", 6);
        hashMap.put("MachineType_Projector", 5);
        hashMap.put("MachineType_STB", 1);
        hashMap.put("MachineType_DVD", 3);
        hashMap.put("MachineType_Camera", 200);
        hashMap.put("MachineType_IRSwitch", 11);
        hashMap.put("MachineType_Amplifier", 201);
        hashMap.put("MachineType_IPTV", 4);
        hashMap.put("MachineType_OTTBox", 10);
        hashMap.put("MachineType_Other", 0);
        hashMap.put("deviceLanguage", "");
        hashMap.put("fetchCurrentLang", Integer.valueOf(LanguageUtils.getLang()));
        return super.getConstants();
    }

    @ReactMethod
    public void createClient(String str) {
        if (this.mInfraredDataProviderMap.get(str) == null) {
            this.mCurrentClient = str;
            this.mInfraredDataProviderMap.put(str, new CmInfraredDataProvider());
        }
    }

    @ReactMethod
    public void destroyClient(String str) {
        if (this.mInfraredDataProviderMap.get(str) != null) {
            this.mInfraredDataProviderMap.remove(str);
            this.mCurrentClient = null;
        }
    }

    @ReactMethod
    public void exactMatchRemote(String str, String str2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
            if (cmInfraredDataProvider == null) {
                callBackBundle(-1, false, "client not found for " + str, callback);
                return;
            }
            try {
                cmInfraredDataProvider.a((MatchPage) JSONUtil.a(str2, MatchPage.class), (CmInfraredDataProvider.IROnGetMatchRemoteListCallback) new CmInfraredDataProvider.IROnGetMatchRemoteListCallback() {
                    public void a(int i, List<Remote> list) {
                        ArrayList arrayList = new ArrayList();
                        if (list != null && list.size() > 0) {
                            for (Remote access$000 : list) {
                                arrayList.add(CmTJInfraModule.this.paraseRemoteToJsRemote(access$000));
                            }
                        }
                        CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(arrayList), callback);
                    }

                    public void a(int i, String str) {
                        CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                    }
                });
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
            }
        }
    }

    @ReactMethod
    public void exactMatchRemoteIfPower(String str, boolean z, String str2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
            if (cmInfraredDataProvider == null) {
                callBackBundle(-1, false, "client not found for" + str, callback);
                return;
            }
            try {
                cmInfraredDataProvider.a((MatchPage) JSONUtil.a(str2, MatchPage.class), z, (CmInfraredDataProvider.IROnGetMatchRemoteListCallback) new CmInfraredDataProvider.IROnGetMatchRemoteListCallback() {
                    public void a(int i, List<Remote> list) {
                        ArrayList arrayList = new ArrayList();
                        if (list != null && list.size() > 0) {
                            for (Remote access$000 : list) {
                                arrayList.add(CmTJInfraModule.this.paraseRemoteToJsRemote(access$000));
                            }
                        }
                        CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(arrayList), callback);
                    }

                    public void a(int i, String str) {
                        CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                    }
                });
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
            }
        }
    }

    @ReactMethod
    public void searchDiy(String str, String str2, Callback callback) {
        search(2, str, str2, callback);
    }

    @ReactMethod
    public void searchOfficial(String str, String str2, Callback callback) {
        search(0, str, str2, callback);
    }

    @ReactMethod
    public void searchAirRemote(String str, String str2, Callback callback) {
        search(1, str, str2, callback);
    }

    private void search(int i, String str, String str2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
            if (cmInfraredDataProvider == null) {
                callBackBundle(-1, false, "client not found for" + str, callback);
                return;
            }
            try {
                cmInfraredDataProvider.a(i, (Page) JSONUtil.a(str2, Page.class), (CmInfraredDataProvider.IROnSearchCallback) new CmInfraredDataProvider.IROnSearchCallback() {
                    public void a(int i, List<Remote> list) {
                        ArrayList arrayList = new ArrayList();
                        if (list != null && list.size() > 0) {
                            for (Remote access$000 : list) {
                                arrayList.add(CmTJInfraModule.this.paraseRemoteToJsRemote(access$000));
                            }
                        }
                        CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(arrayList), callback);
                    }

                    public void a(int i, String str) {
                        CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                    }
                });
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
            }
        }
    }

    @ReactMethod
    public void downloadRemote(String str, String str2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
            if (cmInfraredDataProvider == null) {
                callBackBundle(-1, false, "client not found for" + str, callback);
                return;
            }
            cmInfraredDataProvider.a(str2, (CmInfraredDataProvider.IROnDownloadCallback) new CmInfraredDataProvider.IROnDownloadCallback() {
                public void a(int i, Remote remote) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(CmTJInfraModule.this.paraseRemoteToJsRemote(remote)), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public Remote paraseRemoteToJsRemote(Remote remote) {
        List<Key> keys;
        if (!(remote == null || remote == null || (keys = remote.getKeys()) == null || keys.size() <= 0)) {
            int size = keys.size();
            for (int i = 0; i < size; i++) {
                Key key = keys.get(i);
                if (!(key == null || key.getInfrareds() == null || key.getInfrareds().size() <= 0)) {
                    List<Infrared> infrareds = key.getInfrareds();
                    int size2 = infrareds.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        Infrared infrared = infrareds.get(i2);
                        if (TextUtils.isEmpty(infrared.getData_str())) {
                            infrared.setData_Str(base64Encode(infrared.getData()));
                        }
                        infrared.setData((byte[]) null);
                    }
                }
            }
        }
        return remote;
    }

    private String base64Encode(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            return Base64Coder.a(bArr);
        }
        RnPluginLog.a("TJInfra  byte array data is null or length is 0");
        return "";
    }

    @ReactMethod
    public void loadBrands(String str, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
            if (cmInfraredDataProvider == null) {
                callBackBundle(-1, false, "client not found for" + str, callback);
                return;
            }
            cmInfraredDataProvider.a((CmInfraredDataProvider.IROnLoadBrandsCallback) new CmInfraredDataProvider.IROnLoadBrandsCallback() {
                public void a(int i, List<Brand> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void autoMatchRemote(String str, String str2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
            if (cmInfraredDataProvider == null) {
                callBackBundle(-1, false, "client not found for" + str, callback);
                return;
            }
            cmInfraredDataProvider.a((Page) JSONUtil.a(str2, Page.class), (CmInfraredDataProvider.IROnCommonCallback) new CmInfraredDataProvider.IROnCommonCallback() {
                public void a(int i, List<Remote> list) {
                    ArrayList arrayList = new ArrayList();
                    if (list != null && list.size() > 0) {
                        for (Remote access$000 : list) {
                            arrayList.add(CmTJInfraModule.this.paraseRemoteToJsRemote(access$000));
                        }
                    }
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(arrayList), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void missModel(String str, int i, long j, String str2) {
        CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(str);
        if (cmInfraredDataProvider != null) {
            cmInfraredDataProvider.a(i, j, str2);
        }
    }

    @ReactMethod
    public void getIRCode(int i, String str, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a(i, str, (CmInfraredDataProvider.IROnConvertCodeCallback) new CmInfraredDataProvider.IROnConvertCodeCallback() {
                public void a(int i, String str, String str2) {
                    callback.invoke(true, str, str2);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void buildIRCode(int i, String str, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a(i, str, (CmInfraredDataProvider.IROnBuildIRCodeCallback) new CmInfraredDataProvider.IROnBuildIRCodeCallback() {
                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, true, str, callback);
                }

                public void b(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void fetchRemoteInfrared(String str, String str2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            try {
                cmInfraredDataProvider.a((Remote) JSONUtil.a(str, Remote.class), (Key) JSONUtil.a(str2, Key.class), (CmInfraredDataProvider.IRFetchInfraredCallback) new CmInfraredDataProvider.IRFetchInfraredCallback() {
                    public void a(int i, List<Infrared> list) {
                        CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(CmTJInfraModule.this.paraseListInfrared(list)), callback);
                    }

                    public void a(int i, String str) {
                        CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                    }
                });
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
            }
        }
    }

    /* access modifiers changed from: private */
    public List<Infrared> paraseListInfrared(List<Infrared> list) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Infrared infrared = list.get(i);
                if (TextUtils.isEmpty(infrared.getData_str())) {
                    infrared.setData_Str(base64Encode(infrared.getData()));
                }
                infrared.setData((byte[]) null);
            }
        }
        return list;
    }

    @ReactMethod
    public void fetchAirRemoteInfrared(String str, String str2, String str3, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            try {
                Remote remote = (Remote) JSONUtil.a(str, Remote.class);
                Key key = (Key) JSONUtil.a(str2, Key.class);
                final AirRemoteState airRemoteState = (AirRemoteState) JSONUtil.a(str3, AirRemoteState.class);
                cmInfraredDataProvider.a(remote, key, airRemoteState, (CmInfraredDataProvider.IRFetchInfraredCallback) new CmInfraredDataProvider.IRFetchInfraredCallback() {
                    public void a(int i, List<Infrared> list) {
                        List access$200 = CmTJInfraModule.this.paraseListInfrared(list);
                        String b2 = JSONUtil.b(airRemoteState);
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.putOpt("AirRemoteState", b2);
                            jSONObject.putOpt("Infrareds", JSONUtil.b(access$200));
                        } catch (JSONException e) {
                            RnPluginLog.a("CmTJInfraModule:  " + e.toString());
                        }
                        CmTJInfraModule.this.callBackBundle(i, true, jSONObject.toString(), callback);
                    }

                    public void a(int i, String str) {
                        CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                    }
                });
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
            }
        }
    }

    @ReactMethod
    public void fetchAirTimerInfrared(String str, String str2, int i, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            try {
                cmInfraredDataProvider.a((Key) JSONUtil.a(str, Key.class), (AirRemoteState) JSONUtil.a(str2, AirRemoteState.class), i, (CmInfraredDataProvider.IRFetchInfraredCallback) new CmInfraredDataProvider.IRFetchInfraredCallback() {
                    public void a(int i, List<Infrared> list) {
                        CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(CmTJInfraModule.this.paraseListInfrared(list)), callback);
                    }

                    public void a(int i, String str) {
                        CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                    }
                });
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
            }
        }
    }

    @ReactMethod
    public void getAirRemoteState(String str, final Callback callback) {
        Remote remote;
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            try {
                remote = (Remote) JSONUtil.a(str, Remote.class);
            } catch (Exception e) {
                callBackBundle(-1, false, e.toString(), callback);
                remote = null;
            }
            cmInfraredDataProvider.a(remote, (CmInfraredDataProvider.IRAirRemoteStateCallback) new CmInfraredDataProvider.IRAirRemoteStateCallback() {
                public void a(int i, AirRemoteState airRemoteState) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(airRemoteState), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void isMemoryKey(String str, Callback callback) {
        callBackBundle(-1, false, "this method is not support for android", callback);
    }

    @ReactMethod
    public void isCustomKey(String str, Callback callback) {
        callBackBundle(-1, false, "this method is not support for android", callback);
    }

    @ReactMethod
    public void getAllProvincesCallback(final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a((CmInfraredDataProvider.IRLoadProvincesCallback) new CmInfraredDataProvider.IRLoadProvincesCallback() {
                public void a(int i, List<Province> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void getAllCitiesCallback(final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a((CmInfraredDataProvider.IRLoadCitysCallback) new CmInfraredDataProvider.IRLoadCitysCallback() {
                public void a(int i, List<City> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void getAllProvidersCallback(final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a((CmInfraredDataProvider.IRLoadProvidersCallback) new CmInfraredDataProvider.IRLoadProvidersCallback() {
                public void a(int i, List<CityProvider> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void loadProviderRemotesByCity(int i, int i2, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a(i, i2, (CmInfraredDataProvider.IRLoadProviderRemotesCallback) new CmInfraredDataProvider.IRLoadProviderRemotesCallback() {
                public void a(int i, List<CityProviderRemote> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void loadCityProvidersCallback(final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a((CmInfraredDataProvider.IRLoadCityProvidersCallback) new CmInfraredDataProvider.IRLoadCityProvidersCallback() {
                public void a(int i, List<CityProvider> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void getProviderInCity(int i, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a(i, (CmInfraredDataProvider.IRLoadTvProviderCallback) new CmInfraredDataProvider.IRLoadTvProviderCallback() {
                public void a(int i, List<TvProvider> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    @ReactMethod
    public void getCityInProvince(int i, final Callback callback) {
        if (callback != null) {
            CmInfraredDataProvider cmInfraredDataProvider = this.mInfraredDataProviderMap.get(this.mCurrentClient);
            if (cmInfraredDataProvider == null) {
                cmInfraredDataProvider = new CmInfraredDataProvider();
            }
            cmInfraredDataProvider.a(i, (CmInfraredDataProvider.IRLoadCitysCallback) new CmInfraredDataProvider.IRLoadCitysCallback() {
                public void a(int i, List<City> list) {
                    CmTJInfraModule.this.callBackBundle(i, true, JSONUtil.b(list), callback);
                }

                public void a(int i, String str) {
                    CmTJInfraModule.this.callBackBundle(i, false, str, callback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void callBackBundle(int i, boolean z, String str, Callback callback) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("result", str);
        createMap.putInt("code", i);
        callback.invoke(Boolean.valueOf(z), createMap);
    }
}
