package com.tiqiaa.client.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.imi.fastjson.JSON;
import com.imi.fastjson.JSONObject;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.parser.Feature;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.mijia.app.AppCode;
import com.spare.pinyin.PinYin;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.tiqiaa.client.IRemoteClient;
import com.tiqiaa.database.DataBaseManager;
import com.tiqiaa.icontrol.util.LanguageUtils;
import com.tiqiaa.icontrol.util.LogUtil;
import com.tiqiaa.icontrol.util.NetUtils;
import com.tiqiaa.icontrol.util.PhoneHelper;
import com.tiqiaa.icontrol.util.TQResponse;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.tiqiaa.icontrol.util.Utils;
import com.tiqiaa.local.LocalIrDb;
import com.tiqiaa.remote.entity.AirRemoteState;
import com.tiqiaa.remote.entity.Brand;
import com.tiqiaa.remote.entity.Infrared;
import com.tiqiaa.remote.entity.IrMatchPageInfo;
import com.tiqiaa.remote.entity.IrMatchParam;
import com.tiqiaa.remote.entity.Key;
import com.tiqiaa.remote.entity.MatchKey;
import com.tiqiaa.remote.entity.MatchPage;
import com.tiqiaa.remote.entity.Page;
import com.tiqiaa.remote.entity.Remote;
import com.tiqiaa.remote.entity.Room;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.library.common.util.DateUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.afinal.simplecache.ACache;

public class RemoteClient implements IRemoteClient {
    private static final String BASE_REMOTE_URL = (TiqiaaService.isLocalServer() ? "http://192.168.0.108:8080/tqir/tjtt/remote" : "https://irdna.izazamall.com/tqir/tjtt/remote");
    protected static final String TAG = "RemoteClient";
    /* access modifiers changed from: private */
    public static int localSearchPageIndex = -1;
    /* access modifiers changed from: private */
    public NetUtils client = new NetUtils(this.context);
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public LocalIrDb localDb;

    public interface CallBackOnAuthenFinished {
        void authenDone(String str);
    }

    public interface CallBackOnMatchKeyLoaded {
        void onMatchKeyLoaded(List<MatchKey> list);
    }

    public RemoteClient(Context context2) {
        this.context = context2;
        this.localDb = LocalIrDb.getIrDb(context2);
    }

    public void exactMatchReomtes(final MatchPage matchPage, final IRemoteClient.CallbackOnMatchDone callbackOnMatchDone) {
        if (matchPage == null) {
            callbackOnMatchDone.onMatchDone(1, (List<Remote>) null);
        } else {
            getMatchKey(matchPage.getAppliance_type(), new CallBackOnMatchKeyLoaded() {
                /* JADX WARNING: Removed duplicated region for block: B:40:0x00ac A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:43:0x0052 A[SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onMatchKeyLoaded(java.util.List<com.tiqiaa.remote.entity.MatchKey> r8) {
                    /*
                        r7 = this;
                        if (r8 == 0) goto L_0x0008
                        int r0 = r8.size()
                        if (r0 != 0) goto L_0x001d
                    L_0x0008:
                        com.tiqiaa.database.DataBaseManager r8 = com.tiqiaa.database.DataBaseManager.getInstance()
                        com.tiqiaa.remote.entity.MatchPage r0 = r3
                        int r0 = r0.getAppliance_type()
                        java.util.List r8 = r8.getMatchKeyByType(r0)
                        java.lang.String r0 = "RemoteClient"
                        java.lang.String r1 = "list == null || list.size() == 0!"
                        com.tiqiaa.icontrol.util.LogUtil.e(r0, r1)
                    L_0x001d:
                        r0 = 0
                        r1 = 1
                        if (r8 == 0) goto L_0x00e7
                        int r2 = r8.size()
                        if (r2 != 0) goto L_0x0029
                        goto L_0x00e7
                    L_0x0029:
                        java.util.Collections.sort(r8)
                        com.tiqiaa.remote.entity.MatchPage r2 = r3
                        java.util.List r2 = r2.getOkMarks()
                        r3 = 0
                        if (r2 != 0) goto L_0x004e
                        com.tiqiaa.remote.entity.MatchPage r2 = r3
                        java.util.List r2 = r2.getFailedKeys()
                        if (r2 != 0) goto L_0x004e
                        com.tiqiaa.remote.entity.MatchPage r0 = r3
                        java.lang.Object r8 = r8.get(r3)
                        com.tiqiaa.remote.entity.MatchKey r8 = (com.tiqiaa.remote.entity.MatchKey) r8
                        int r8 = r8.getKey_type()
                        r0.setNext_key(r8)
                        goto L_0x00dd
                    L_0x004e:
                        java.util.Iterator r8 = r8.iterator()
                    L_0x0052:
                        boolean r2 = r8.hasNext()
                        if (r2 != 0) goto L_0x005b
                        r1 = 0
                        goto L_0x00cc
                    L_0x005b:
                        java.lang.Object r2 = r8.next()
                        com.tiqiaa.remote.entity.MatchKey r2 = (com.tiqiaa.remote.entity.MatchKey) r2
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getFailedKeys()
                        if (r4 == 0) goto L_0x007e
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getFailedKeys()
                        int r5 = r2.getKey_type()
                        java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                        boolean r4 = r4.contains(r5)
                        if (r4 == 0) goto L_0x007e
                        goto L_0x0052
                    L_0x007e:
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getOkMarks()
                        if (r4 == 0) goto L_0x00a9
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getOkMarks()
                        java.util.Iterator r4 = r4.iterator()
                    L_0x0090:
                        boolean r5 = r4.hasNext()
                        if (r5 != 0) goto L_0x0097
                        goto L_0x00a9
                    L_0x0097:
                        java.lang.Object r5 = r4.next()
                        com.tiqiaa.remote.entity.MatchPage$IRMark r5 = (com.tiqiaa.remote.entity.MatchPage.IRMark) r5
                        int r5 = r5.getKey_type()
                        int r6 = r2.getKey_type()
                        if (r5 != r6) goto L_0x0090
                        r4 = 1
                        goto L_0x00aa
                    L_0x00a9:
                        r4 = 0
                    L_0x00aa:
                        if (r4 != 0) goto L_0x0052
                        java.lang.String r8 = "RemoteClient"
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder
                        java.lang.String r4 = "Current match keyType:"
                        r3.<init>(r4)
                        int r4 = r2.getKey_type()
                        r3.append(r4)
                        java.lang.String r3 = r3.toString()
                        com.tiqiaa.icontrol.util.LogUtil.i(r8, r3)
                        com.tiqiaa.remote.entity.MatchPage r8 = r3
                        int r2 = r2.getKey_type()
                        r8.setNext_key(r2)
                    L_0x00cc:
                        if (r1 != 0) goto L_0x00dd
                        java.lang.String r8 = "RemoteClient"
                        java.lang.String r1 = "match completed!"
                        com.tiqiaa.icontrol.util.LogUtil.w(r8, r1)
                        com.tiqiaa.client.IRemoteClient$CallbackOnMatchDone r8 = r4
                        r1 = 6001(0x1771, float:8.409E-42)
                        r8.onMatchDone(r1, r0)
                        return
                    L_0x00dd:
                        com.tiqiaa.client.impl.RemoteClient r8 = com.tiqiaa.client.impl.RemoteClient.this
                        com.tiqiaa.remote.entity.MatchPage r0 = r3
                        com.tiqiaa.client.IRemoteClient$CallbackOnMatchDone r1 = r4
                        r8.match(r0, r1)
                        return
                    L_0x00e7:
                        com.tiqiaa.client.IRemoteClient$CallbackOnMatchDone r8 = r4
                        r8.onMatchDone(r1, r0)
                        java.lang.String r8 = "RemoteClient"
                        java.lang.String r0 = "获取MatchKey为空！"
                        com.tiqiaa.icontrol.util.LogUtil.e(r8, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.client.impl.RemoteClient.AnonymousClass1.onMatchKeyLoaded(java.util.List):void");
                }
            });
        }
    }

    public void download_reomte(final String str, final IRemoteClient.CallBackOnRemoteDownloaded callBackOnRemoteDownloaded) {
        String str2 = String.valueOf(BASE_REMOTE_URL) + Constants.q;
        if (!PhoneHelper.checkNet()) {
            callBackOnRemoteDownloaded.onRemoteDownloaded(0, this.localDb.downloadReomte(str, 0));
        } else {
            this.client.doPost(str2, str, new RequestCallBack<String>() {
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.d(RemoteClient.TAG, "downloadRemote......onSuccess..######..........response = " + ((String) responseInfo.f6335a));
                    if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                        Remote downloadReomte = RemoteClient.this.localDb.downloadReomte(str, 0);
                        if (downloadReomte != null) {
                            callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte);
                        } else {
                            callBackOnRemoteDownloaded.onRemoteDownloaded(1, (Remote) null);
                        }
                    } else {
                        TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                        if (tQResponse == null) {
                            callBackOnRemoteDownloaded.onRemoteDownloaded(1, (Remote) null);
                        } else if (tQResponse.getErrcode() == 10000) {
                            Remote remote = (Remote) tQResponse.getData(Remote.class);
                            if (remote != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, remote);
                                if (remote.getBrand() != null) {
                                    DataBaseManager.getInstance().saveBrand(remote.getBrand());
                                    return;
                                }
                                return;
                            }
                            callBackOnRemoteDownloaded.onRemoteDownloaded(1, (Remote) null);
                        } else if (tQResponse.getErrcode() == 10003) {
                            Remote downloadReomte2 = RemoteClient.this.localDb.downloadReomte(str, 0);
                            if (downloadReomte2 != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte2);
                            } else {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(3, (Remote) null);
                            }
                        } else if (tQResponse.getErrcode() == 10005) {
                            Remote downloadReomte3 = RemoteClient.this.localDb.downloadReomte(str, 0);
                            if (downloadReomte3 != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte3);
                            } else {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(4, (Remote) null);
                            }
                        } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                            Remote downloadReomte4 = RemoteClient.this.localDb.downloadReomte(str, 0);
                            if (downloadReomte4 != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte4);
                            } else {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(5, (Remote) null);
                            }
                        } else if (tQResponse.getErrcode() == 10016) {
                            Remote downloadReomte5 = RemoteClient.this.localDb.downloadReomte(str, 0);
                            if (downloadReomte5 != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte5);
                            } else {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(6, (Remote) null);
                            }
                        } else if (tQResponse.getErrcode() == 10017) {
                            Remote downloadReomte6 = RemoteClient.this.localDb.downloadReomte(str, 0);
                            if (downloadReomte6 != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte6);
                            } else {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(7001, (Remote) null);
                            }
                        } else {
                            Remote downloadReomte7 = RemoteClient.this.localDb.downloadReomte(str, 0);
                            if (downloadReomte7 != null) {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte7);
                            } else {
                                callBackOnRemoteDownloaded.onRemoteDownloaded(1, (Remote) null);
                            }
                        }
                    }
                }

                public void onFailure(HttpException httpException, String str) {
                    Remote downloadReomte = RemoteClient.this.localDb.downloadReomte(str, 0);
                    if (downloadReomte != null) {
                        callBackOnRemoteDownloaded.onRemoteDownloaded(0, downloadReomte);
                    } else {
                        callBackOnRemoteDownloaded.onRemoteDownloaded(1, (Remote) null);
                    }
                }
            });
        }
    }

    public void uploadReomte(final Remote remote, final IRemoteClient.CallBackOnRemoteUploaded callBackOnRemoteUploaded) {
        String str = String.valueOf(BASE_REMOTE_URL) + "/upload";
        if (UserClient.getCurrentUser() == null) {
            LogUtil.e(TAG, "未登陆");
            callBackOnRemoteUploaded.onUploaded(8001, (Remote) null);
            return;
        }
        final String id = remote.getId();
        if (UserClient.getCurrentUser().getId() != remote.getAuthor_id()) {
            String replace = UUID.randomUUID().toString().replace("-", "");
            remote.setId(replace);
            if (remote.getAuthor_id() == 0) {
                remote.setAuthor_id(UserClient.getCurrentUser().getId());
                fillDiyBasicData(remote);
            } else {
                remote.setModifier_id(UserClient.getCurrentUser().getId());
            }
            for (Key remote_id : remote.getKeys()) {
                remote_id.setRemote_id(replace);
            }
        }
        this.client.doPost(str, remote, new RequestCallBack<String>() {
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    callBackOnRemoteUploaded.onUploaded(1, (Remote) null);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    callBackOnRemoteUploaded.onUploaded(1, (Remote) null);
                } else if (tQResponse.getErrcode() == 10000) {
                    Brand brand = (Brand) tQResponse.getData(Brand.class);
                    if (brand != null) {
                        remote.setBrand(brand);
                    }
                    if (!id.equalsIgnoreCase(remote.getId())) {
                        final String str = id;
                        final Remote remote = remote;
                        new Thread(new Runnable() {
                            public void run() {
                                DataBaseManager.getInstance().refreshRemoteInDB(str, remote);
                            }
                        }).start();
                    }
                    callBackOnRemoteUploaded.onUploaded(0, remote);
                } else if (tQResponse.getErrcode() == 10003) {
                    callBackOnRemoteUploaded.onUploaded(3, (Remote) null);
                } else if (tQResponse.getErrcode() == 10005) {
                    callBackOnRemoteUploaded.onUploaded(4, (Remote) null);
                } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                    callBackOnRemoteUploaded.onUploaded(5, (Remote) null);
                } else {
                    callBackOnRemoteUploaded.onUploaded(1, (Remote) null);
                }
            }

            public void onFailure(HttpException httpException, String str) {
                callBackOnRemoteUploaded.onUploaded(1, (Remote) null);
            }
        });
    }

    private void fillDiyBasicData(Remote remote) {
        try {
            remote.setApp_ver(this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName);
            remote.setDiy_device(1);
            remote.setLang(LanguageUtils.getLang());
            remote.setDpi(Utils.getLocalResolution(this.context));
        } catch (Exception e) {
            LogUtil.printException(e);
        }
    }

    public void downloadRoomRemoteSettings(Long l, final IRemoteClient.CallBackOnRoomRemoteSettingsDownloaded callBackOnRoomRemoteSettingsDownloaded) {
        this.client.doPost(String.valueOf(BASE_REMOTE_URL) + "/download_scene_remote_settings", l, new RequestCallBack<String>() {
            public void onFailure(HttpException httpException, String str) {
                LogUtil.e(RemoteClient.TAG, "downloadRoomRemoteSettings......onFailure..######..........response = " + str);
                callBackOnRoomRemoteSettingsDownloaded.onDownloaded(1, (List<Room>) null);
            }

            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    LogUtil.e(RemoteClient.TAG, "downloadRoomRemoteSettings......onFailure..######..........response = " + ((String) responseInfo.f6335a));
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(1, (List<Room>) null);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(1, (List<Room>) null);
                } else if (tQResponse.getErrcode() == 10000) {
                    LogUtil.e(RemoteClient.TAG, "downloadRoomRemoteSettings......success");
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(0, (List) tQResponse.getData(new TypeReference<List<Room>>() {
                    }));
                } else if (tQResponse.getErrcode() == 10003) {
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(3, (List<Room>) null);
                } else if (tQResponse.getErrcode() == 10005) {
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(4, (List<Room>) null);
                } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(5, (List<Room>) null);
                } else {
                    LogUtil.e(RemoteClient.TAG, "downloadRoomRemoteSettings......onFailure..######..........response = " + ((String) responseInfo.f6335a));
                    callBackOnRoomRemoteSettingsDownloaded.onDownloaded(1, (List<Room>) null);
                }
            }
        });
    }

    public void uploadRoomRemoteSettings(Long l, List<Room> list, final IRemoteClient.CallBackOnRoomRemoteSettingsUploaded callBackOnRoomRemoteSettingsUploaded) {
        this.client.doPost(String.valueOf(BASE_REMOTE_URL) + "/upload_scene_remote_settings", list, new RequestCallBack<String>() {
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(1);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    LogUtil.e(RemoteClient.TAG, "uploadSceneRemoteSettings......onFailure..######..........response = " + responseInfo);
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(1);
                } else if (tQResponse.getErrcode() == 10000) {
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(0);
                } else if (tQResponse.getErrcode() == 10003) {
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(3);
                } else if (tQResponse.getErrcode() == 10005) {
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(4);
                } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(5);
                } else {
                    callBackOnRoomRemoteSettingsUploaded.onUploaded(1);
                }
            }

            public void onFailure(HttpException httpException, String str) {
                LogUtil.e(RemoteClient.TAG, "uploadSceneRemoteSettings......onFailure..######..........response = " + str);
                callBackOnRoomRemoteSettingsUploaded.onUploaded(1);
            }
        });
    }

    public void load_brands(final IRemoteClient.CallbackOnBrandLoaded callbackOnBrandLoaded) {
        String str = String.valueOf(BASE_REMOTE_URL) + "/load_brands";
        final List<Brand> brands = DataBaseManager.getInstance().getBrands();
        if (brands == null || brands.size() <= 0 || isBrandOverdue()) {
            this.client.doPost(str, (Object) null, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    LogUtil.e(RemoteClient.TAG, "load_brands......onFailure..######..........response = " + str);
                    if (brands == null || brands.size() <= 0) {
                        callbackOnBrandLoaded.onBrandLoaded(1, (List<Brand>) null);
                    } else {
                        callbackOnBrandLoaded.onBrandLoaded(0, brands);
                    }
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.d(RemoteClient.TAG, "load_brands......onSuccess..######..........response = " + ((String) responseInfo.f6335a));
                    if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                        RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                        return;
                    }
                    TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                    if (tQResponse == null) {
                        LogUtil.e(RemoteClient.TAG, "load_brands......onFailure..######..........response = " + responseInfo);
                        RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                    } else if (tQResponse.getErrcode() == 10000) {
                        final List list = (List) tQResponse.getData(new TypeReference<List<Brand>>() {
                        });
                        if (list == null || list.size() <= 0) {
                            RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                            return;
                        }
                        final IRemoteClient.CallbackOnBrandLoaded callbackOnBrandLoaded = callbackOnBrandLoaded;
                        new Thread(new Runnable() {
                            public void run() {
                                DataBaseManager.getInstance().saveBrands(list);
                                RemoteClient.this.refreshBrandSaveTime();
                                callbackOnBrandLoaded.onBrandLoaded(0, list);
                            }
                        }).start();
                    } else if (tQResponse.getErrcode() == 10003) {
                        RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                    } else if (tQResponse.getErrcode() == 10005) {
                        RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                    } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                        RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                    } else {
                        RemoteClient.this.returnLocalBrands(brands, callbackOnBrandLoaded);
                    }
                }
            });
        } else {
            callbackOnBrandLoaded.onBrandLoaded(0, brands);
        }
    }

    /* access modifiers changed from: private */
    public void returnLocalBrands(List<Brand> list, IRemoteClient.CallbackOnBrandLoaded callbackOnBrandLoaded) {
        if (list == null || list.size() <= 0) {
            callbackOnBrandLoaded.onBrandLoaded(1, (List<Brand>) null);
        } else {
            callbackOnBrandLoaded.onBrandLoaded(0, list);
        }
    }

    public void searchOfficial(final Page page, final IRemoteClient.CallbackOnSearchDone callbackOnSearchDone) {
        String str = String.valueOf(BASE_REMOTE_URL) + "/search_official";
        if (!PhoneHelper.checkNet()) {
            callbackOnSearchDone.onSearchDone(0, this.localDb.searchReomtes(page, false));
        } else {
            this.client.doPost(str, page, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    callbackOnSearchDone.onSearchDone(0, RemoteClient.this.localDb.searchReomtes(page, true));
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                        callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                        return;
                    }
                    TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                    if (tQResponse == null) {
                        LogUtil.e(RemoteClient.TAG, "search remote ......onFailure..######..........response = " + responseInfo);
                        callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10000) {
                        List list = (List) tQResponse.getData(new TypeReference<List<Remote>>() {
                        });
                        LogUtil.e(RemoteClient.TAG, "search remote ......onSuccess");
                        if (list == null || list.size() < 30) {
                            if (RemoteClient.localSearchPageIndex == -1) {
                                RemoteClient.localSearchPageIndex = page.getPage();
                            }
                            try {
                                Page page = (Page) page.clone();
                                page.setPage(page.getPage() - RemoteClient.localSearchPageIndex);
                                List<Remote> searchReomtes = RemoteClient.this.localDb.searchReomtes(page, false);
                                if (searchReomtes != null) {
                                    list.addAll(searchReomtes);
                                }
                            } catch (Exception e) {
                                LogUtil.printException(e);
                            }
                        } else {
                            RemoteClient.localSearchPageIndex = -1;
                        }
                        callbackOnSearchDone.onSearchDone(0, list);
                    } else if (tQResponse.getErrcode() == 10003) {
                        callbackOnSearchDone.onSearchDone(3, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10005) {
                        callbackOnSearchDone.onSearchDone(4, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                        callbackOnSearchDone.onSearchDone(5, (List<Remote>) null);
                    } else {
                        callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                    }
                }
            });
        }
    }

    public void searchDiy(final Page page, final IRemoteClient.CallbackOnSearchDone callbackOnSearchDone) {
        String str = String.valueOf(BASE_REMOTE_URL) + "/search_diy";
        LogUtil.e(TAG, "searchDiy url=" + str);
        if (!PhoneHelper.checkNet()) {
            callbackOnSearchDone.onSearchDone(2, (List<Remote>) null);
        } else {
            this.client.doPost(str, page, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    Log.e(RemoteClient.TAG, "searchDiy exception=" + str);
                    callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                        callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                        return;
                    }
                    TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                    StringBuilder sb = new StringBuilder("tqResponse=");
                    sb.append(tQResponse == null ? "null" : JSON.toJSONString(tQResponse));
                    LogUtil.e(RemoteClient.TAG, sb.toString());
                    if (tQResponse == null) {
                        LogUtil.e(RemoteClient.TAG, "search remote ......onFailure..######..........response = " + responseInfo);
                        callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10000) {
                        List<Remote> list = (List) tQResponse.getData(new TypeReference<List<Remote>>() {
                        });
                        LogUtil.e(RemoteClient.TAG, "search remote ......onSuccess");
                        if (list == null || list.size() == 0) {
                            if (RemoteClient.localSearchPageIndex == -1) {
                                RemoteClient.localSearchPageIndex = page.getPage();
                            }
                            try {
                                Page page = (Page) page.clone();
                                page.setPage(page.getPage() - RemoteClient.localSearchPageIndex);
                                list = RemoteClient.this.localDb.searchReomtes(page, false);
                            } catch (Exception e) {
                                LogUtil.printException(e);
                            }
                        } else {
                            RemoteClient.localSearchPageIndex = -1;
                        }
                        callbackOnSearchDone.onSearchDone(0, list);
                    } else if (tQResponse.getErrcode() == 10003) {
                        callbackOnSearchDone.onSearchDone(3, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10005) {
                        callbackOnSearchDone.onSearchDone(4, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                        callbackOnSearchDone.onSearchDone(5, (List<Remote>) null);
                    } else {
                        callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void match(final MatchPage matchPage, final IRemoteClient.CallbackOnMatchDone callbackOnMatchDone) {
        logMatchParam(matchPage);
        if (!PhoneHelper.checkNet()) {
            List<Remote> matchRemotes = this.localDb.matchRemotes(matchPage);
            if (matchRemotes != null) {
                LogUtil.i(TAG, "match...local...onSuccess..remote size = " + matchRemotes.size());
            }
            callbackOnMatchDone.onMatchDone(0, matchRemotes);
            return;
        }
        this.client.doPost(String.valueOf(BASE_REMOTE_URL) + "/match_official", matchPage, new RequestCallBack<String>() {
            public void onFailure(HttpException httpException, String str) {
                callbackOnMatchDone.onMatchDone(0, RemoteClient.this.localDb.matchRemotes(matchPage, true));
            }

            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    callbackOnMatchDone.onMatchDone(1, (List<Remote>) null);
                    LogUtil.w(RemoteClient.TAG, "match.......!!!!!!!!!!!!........CallbackOnMatchDone.ERROR_CODE_FAILED, status code or result error!" + responseInfo.toString());
                    RemoteClient.this.getLocalMatch(matchPage, callbackOnMatchDone);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    LogUtil.e(RemoteClient.TAG, "match.......!!!!!!!!!!!!.........tqResponse==null");
                    RemoteClient.this.getLocalMatch(matchPage, callbackOnMatchDone);
                } else if (tQResponse.getErrcode() == 10000) {
                    try {
                        List<Remote> list = (List) tQResponse.getData(new TypeReference<List<Remote>>() {
                        });
                        StringBuilder sb = new StringBuilder("match........######..........ErrorCode.ERRCODE_SUCCESS , remotes.size = ");
                        sb.append(list == null ? 0 : list.size());
                        LogUtil.d(RemoteClient.TAG, sb.toString());
                        if (list == null || list.size() < 5) {
                            LogUtil.i(RemoteClient.TAG, "从本地云获取匹配数据。");
                            try {
                                List<Remote> matchRemotes = RemoteClient.this.localDb.matchRemotes(matchPage);
                                if (list == null) {
                                    list = matchRemotes;
                                } else if (matchRemotes != null) {
                                    for (Remote next : matchRemotes) {
                                        if (!RemoteClient.this.containsIrMark(list, next)) {
                                            list.add(next);
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                RemoteClient.this.getLocalMatch(matchPage, callbackOnMatchDone);
                                LogUtil.printException(e);
                                LogUtil.e(RemoteClient.TAG, "从本地解析remotes数据失败");
                                return;
                            }
                        }
                        callbackOnMatchDone.onMatchDone(0, list);
                    } catch (Exception unused) {
                        LogUtil.e(RemoteClient.TAG, "解析remotes数据失败");
                        RemoteClient.this.getLocalMatch(matchPage, callbackOnMatchDone);
                    }
                } else if (tQResponse.getErrcode() == 10003) {
                    callbackOnMatchDone.onMatchDone(3, (List<Remote>) null);
                } else if (tQResponse.getErrcode() == 10005) {
                    callbackOnMatchDone.onMatchDone(4, (List<Remote>) null);
                } else if (tQResponse.getErrcode() == 10002 || tQResponse.getErrcode() == 10004) {
                    callbackOnMatchDone.onMatchDone(5, (List<Remote>) null);
                } else {
                    callbackOnMatchDone.onMatchDone(1, (List<Remote>) null);
                    LogUtil.w(RemoteClient.TAG, "match.......!!!!!!!!!!!!........CallbackOnMatchDone.ERROR_CODE_FAILED,errcode err:" + tQResponse.getErrcode());
                    RemoteClient.this.getLocalMatch(matchPage, callbackOnMatchDone);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void getLocalMatch(MatchPage matchPage, IRemoteClient.CallbackOnMatchDone callbackOnMatchDone) {
        List<Remote> matchRemotes = this.localDb.matchRemotes(matchPage);
        if (matchRemotes != null) {
            LogUtil.i(TAG, "match...local...onSuccess..remote size = " + matchRemotes.size());
        }
        callbackOnMatchDone.onMatchDone(0, matchRemotes);
    }

    private void logMatchParam(MatchPage matchPage) {
        try {
            LogUtil.e(TAG, "matchpage:brand->" + matchPage.getBrand_id() + ",appliance->" + matchPage.getAppliance_type() + ",testkey->" + matchPage.getNext_key());
            if (matchPage.getFailedKeys() != null) {
                LogUtil.e(TAG, ",\r\nfailedKeys->");
                for (Integer intValue : matchPage.getFailedKeys()) {
                    LogUtil.e(TAG, String.valueOf(intValue.intValue()) + ",");
                }
            }
            if (matchPage.getOkMarks() != null) {
                LogUtil.e(TAG, "\r\nokMarks->");
                for (MatchPage.IRMark next : matchPage.getOkMarks()) {
                    LogUtil.e(TAG, String.valueOf(next.getKey_type()) + ":" + next.getIr_mark() + ",");
                }
            }
            if (matchPage.getWrongMarks() != null) {
                LogUtil.e(TAG, "\r\nwrongMarks->");
                for (MatchPage.IRMark next2 : matchPage.getWrongMarks()) {
                    LogUtil.e(TAG, String.valueOf(next2.getKey_type()) + ":" + next2.getIr_mark() + ",");
                }
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public boolean containsIrMark(List<Remote> list, Remote remote) {
        if (list == null || remote == null) {
            return false;
        }
        for (Remote keys : list) {
            try {
                if (keys.getKeys().get(0).getInfrareds().get(0).getIr_mark() == remote.getKeys().get(0).getInfrareds().get(0).getIr_mark()) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public void load_match_keys(final int i, final CallBackOnMatchKeyLoaded callBackOnMatchKeyLoaded) {
        this.client.doPost(String.valueOf(BASE_REMOTE_URL) + "/load_match_keys", (Object) null, new RequestCallBack<String>() {
            public void onFailure(HttpException httpException, String str) {
                LogUtil.e(RemoteClient.TAG, "获取遥控器按键匹配序列失败。msg:" + str);
                callBackOnMatchKeyLoaded.onMatchKeyLoaded((List<MatchKey>) null);
            }

            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    LogUtil.e(RemoteClient.TAG, "获取遥控器按键匹配序列失败。msg:" + responseInfo);
                    callBackOnMatchKeyLoaded.onMatchKeyLoaded((List<MatchKey>) null);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null || tQResponse.getErrcode() != 10000) {
                    LogUtil.e(RemoteClient.TAG, "获取遥控器按键匹配序列失败。msg:tqResponse null or fail");
                    callBackOnMatchKeyLoaded.onMatchKeyLoaded((List<MatchKey>) null);
                    return;
                }
                final List<MatchKey> list = (List) tQResponse.getData(new TypeReference<List<MatchKey>>() {
                });
                if (list == null || list.size() <= 0) {
                    LogUtil.e(RemoteClient.TAG, "获取遥控器按键匹配序列失败。msg:matchKeys null");
                    callBackOnMatchKeyLoaded.onMatchKeyLoaded((List<MatchKey>) null);
                    return;
                }
                LogUtil.e(RemoteClient.TAG, "获取遥控器按键匹配序列成功");
                ArrayList arrayList = new ArrayList();
                for (MatchKey matchKey : list) {
                    if (matchKey.getAppliance_type() == i) {
                        arrayList.add(matchKey);
                    }
                }
                callBackOnMatchKeyLoaded.onMatchKeyLoaded(arrayList);
                new Thread(new Runnable() {
                    public void run() {
                        DataBaseManager.getInstance().updateMatchKeyTable(list);
                    }
                }).start();
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getMatchKey(int r8, com.tiqiaa.client.impl.RemoteClient.CallBackOnMatchKeyLoaded r9) {
        /*
            r7 = this;
            java.lang.String r0 = "yyyy-MM-dd"
            android.content.Context r1 = r7.context
            java.lang.String r2 = "match_key_update_date"
            r3 = 0
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            java.lang.String r2 = "lastUpDateDate"
            r3 = 0
            java.lang.String r1 = r1.getString(r2, r3)
            java.util.Date r2 = new java.util.Date
            r2.<init>()
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat
            r4.<init>(r0)
            if (r1 == 0) goto L_0x003a
            java.util.Date r0 = r4.parse(r1)     // Catch:{ Exception -> 0x0023 }
            goto L_0x003b
        L_0x0023:
            r0 = move-exception
            java.lang.String r4 = "RemoteClient"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "解析日期失败:"
            r5.<init>(r6)
            r5.append(r1)
            java.lang.String r1 = r5.toString()
            com.tiqiaa.icontrol.util.LogUtil.e(r4, r1)
            com.tiqiaa.icontrol.util.LogUtil.printException(r0)
        L_0x003a:
            r0 = r3
        L_0x003b:
            if (r0 == 0) goto L_0x0071
            int r1 = r2.getYear()
            int r3 = r0.getYear()
            if (r1 != r3) goto L_0x0071
            int r1 = r2.getMonth()
            int r3 = r0.getMonth()
            if (r1 != r3) goto L_0x0071
            int r1 = r2.getDay()
            int r0 = r0.getDay()
            int r1 = r1 - r0
            r0 = 1
            if (r1 >= r0) goto L_0x0071
            com.tiqiaa.database.DataBaseManager r0 = com.tiqiaa.database.DataBaseManager.getInstance()
            java.util.List r0 = r0.getMatchKeyByType(r8)
            if (r0 == 0) goto L_0x0071
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x0071
            r9.onMatchKeyLoaded(r0)
            return
        L_0x0071:
            r7.load_match_keys(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.client.impl.RemoteClient.getMatchKey(int, com.tiqiaa.client.impl.RemoteClient$CallBackOnMatchKeyLoaded):void");
    }

    private boolean isBrandOverdue() {
        long j = this.context.getSharedPreferences("Overdue", 0).getLong("BrandSaveDate", 0);
        Date date = new Date();
        if (j == 0) {
            refreshBrandSaveTime();
            return true;
        } else if (date.getTime() - j < DateUtils.d) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void refreshBrandSaveTime() {
        this.context.getSharedPreferences("Overdue", 0).edit().putLong("BrandSaveDate", new Date().getTime()).commit();
    }

    public void authen(final CallBackOnAuthenFinished callBackOnAuthenFinished) {
        this.client.doPost(String.valueOf(BASE_REMOTE_URL) + "/auth", (Object) null, new RequestCallBack<String>() {
            public void onFailure(HttpException httpException, String str) {
                LogUtil.e(RemoteClient.TAG, "认证失败。msg:" + str);
                callBackOnAuthenFinished.authenDone((String) null);
            }

            public void onSuccess(ResponseInfo<String> responseInfo) {
                TQResponse tQResponse;
                LogUtil.e(RemoteClient.TAG, "认证成功");
                if (responseInfo.d == 200 && responseInfo.f6335a != null && (tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class)) != null && tQResponse.getErrcode() == 10000) {
                    try {
                        callBackOnAuthenFinished.authenDone((String) tQResponse.getData());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                callBackOnAuthenFinished.authenDone((String) null);
            }
        });
    }

    public void searchAirRemote(Page page, final IRemoteClient.CallbackOnSearchDone callbackOnSearchDone) {
        boolean z;
        String str = String.valueOf(BASE_REMOTE_URL) + "/search_official";
        page.setPage(0);
        final List<Remote> matchReomtes = this.localDb.matchReomtes(page, true);
        if (matchReomtes == null || matchReomtes.size() == 0) {
            LogUtil.e(TAG, "searchAirRemote本地获取到的数据为空！");
            z = false;
        } else {
            z = true;
        }
        int i = 5;
        while (z && i > 0) {
            i--;
            page.setPage(page.getPage() + 1);
            List<Remote> matchReomtes2 = this.localDb.matchReomtes(page, true);
            if (matchReomtes2 == null || matchReomtes2.size() == 0) {
                z = false;
            } else {
                matchReomtes.addAll(matchReomtes2);
            }
        }
        if (!PhoneHelper.checkNet()) {
            callbackOnSearchDone.onSearchDone(0, matchReomtes);
            return;
        }
        page.setPage(0);
        this.client.doPost(str, page, new RequestCallBack<String>() {
            public void onFailure(HttpException httpException, String str) {
                callbackOnSearchDone.onSearchDone(0, matchReomtes);
            }

            public void onSuccess(ResponseInfo<String> responseInfo) {
                List list;
                if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                    callbackOnSearchDone.onSearchDone(0, matchReomtes);
                    return;
                }
                TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                if (tQResponse == null) {
                    LogUtil.e(RemoteClient.TAG, "search remote ......onFailure..######..........response = " + responseInfo);
                    callbackOnSearchDone.onSearchDone(1, (List<Remote>) null);
                } else if (tQResponse.getErrcode() == 10000 && (list = (List) tQResponse.getData(new TypeReference<List<Remote>>() {
                })) != null && list.size() > 0) {
                    LogUtil.e(RemoteClient.TAG, "search remote ......onSuccess");
                    if (matchReomtes != null) {
                        matchReomtes.addAll(list);
                    } else {
                        callbackOnSearchDone.onSearchDone(0, list);
                        return;
                    }
                }
                callbackOnSearchDone.onSearchDone(0, matchReomtes);
            }
        });
    }

    public void exactMatchReomtes(final MatchPage matchPage, final boolean z, final IRemoteClient.CallbackOnMatchDone callbackOnMatchDone) {
        if (matchPage == null) {
            callbackOnMatchDone.onMatchDone(1, (List<Remote>) null);
        } else {
            getMatchKey(matchPage.getAppliance_type(), new CallBackOnMatchKeyLoaded() {
                /* JADX WARNING: Removed duplicated region for block: B:50:0x00c5 A[SYNTHETIC] */
                /* JADX WARNING: Removed duplicated region for block: B:53:0x006b A[SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onMatchKeyLoaded(java.util.List<com.tiqiaa.remote.entity.MatchKey> r8) {
                    /*
                        r7 = this;
                        if (r8 == 0) goto L_0x0008
                        int r0 = r8.size()
                        if (r0 != 0) goto L_0x0016
                    L_0x0008:
                        com.tiqiaa.database.DataBaseManager r8 = com.tiqiaa.database.DataBaseManager.getInstance()
                        com.tiqiaa.remote.entity.MatchPage r0 = r3
                        int r0 = r0.getAppliance_type()
                        java.util.List r8 = r8.getMatchKeyByType(r0)
                    L_0x0016:
                        r0 = 0
                        r1 = 1
                        if (r8 == 0) goto L_0x0100
                        int r2 = r8.size()
                        if (r2 != 0) goto L_0x0022
                        goto L_0x0100
                    L_0x0022:
                        java.util.Collections.sort(r8)
                        boolean r2 = r4
                        if (r2 != 0) goto L_0x0045
                        java.util.Iterator r2 = r8.iterator()
                    L_0x002d:
                        boolean r3 = r2.hasNext()
                        if (r3 != 0) goto L_0x0034
                        goto L_0x0045
                    L_0x0034:
                        java.lang.Object r3 = r2.next()
                        com.tiqiaa.remote.entity.MatchKey r3 = (com.tiqiaa.remote.entity.MatchKey) r3
                        int r3 = r3.getKey_type()
                        r4 = 800(0x320, float:1.121E-42)
                        if (r3 != r4) goto L_0x002d
                        r2.remove()
                    L_0x0045:
                        com.tiqiaa.remote.entity.MatchPage r2 = r3
                        java.util.List r2 = r2.getOkMarks()
                        r3 = 0
                        if (r2 != 0) goto L_0x0067
                        com.tiqiaa.remote.entity.MatchPage r2 = r3
                        java.util.List r2 = r2.getFailedKeys()
                        if (r2 != 0) goto L_0x0067
                        com.tiqiaa.remote.entity.MatchPage r0 = r3
                        java.lang.Object r8 = r8.get(r3)
                        com.tiqiaa.remote.entity.MatchKey r8 = (com.tiqiaa.remote.entity.MatchKey) r8
                        int r8 = r8.getKey_type()
                        r0.setNext_key(r8)
                        goto L_0x00f6
                    L_0x0067:
                        java.util.Iterator r8 = r8.iterator()
                    L_0x006b:
                        boolean r2 = r8.hasNext()
                        if (r2 != 0) goto L_0x0074
                        r1 = 0
                        goto L_0x00e5
                    L_0x0074:
                        java.lang.Object r2 = r8.next()
                        com.tiqiaa.remote.entity.MatchKey r2 = (com.tiqiaa.remote.entity.MatchKey) r2
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getFailedKeys()
                        if (r4 == 0) goto L_0x0097
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getFailedKeys()
                        int r5 = r2.getKey_type()
                        java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                        boolean r4 = r4.contains(r5)
                        if (r4 == 0) goto L_0x0097
                        goto L_0x006b
                    L_0x0097:
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getOkMarks()
                        if (r4 == 0) goto L_0x00c2
                        com.tiqiaa.remote.entity.MatchPage r4 = r3
                        java.util.List r4 = r4.getOkMarks()
                        java.util.Iterator r4 = r4.iterator()
                    L_0x00a9:
                        boolean r5 = r4.hasNext()
                        if (r5 != 0) goto L_0x00b0
                        goto L_0x00c2
                    L_0x00b0:
                        java.lang.Object r5 = r4.next()
                        com.tiqiaa.remote.entity.MatchPage$IRMark r5 = (com.tiqiaa.remote.entity.MatchPage.IRMark) r5
                        int r5 = r5.getKey_type()
                        int r6 = r2.getKey_type()
                        if (r5 != r6) goto L_0x00a9
                        r4 = 1
                        goto L_0x00c3
                    L_0x00c2:
                        r4 = 0
                    L_0x00c3:
                        if (r4 != 0) goto L_0x006b
                        java.lang.String r8 = "RemoteClient"
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder
                        java.lang.String r4 = "Current match keyType:"
                        r3.<init>(r4)
                        int r4 = r2.getKey_type()
                        r3.append(r4)
                        java.lang.String r3 = r3.toString()
                        com.tiqiaa.icontrol.util.LogUtil.i(r8, r3)
                        com.tiqiaa.remote.entity.MatchPage r8 = r3
                        int r2 = r2.getKey_type()
                        r8.setNext_key(r2)
                    L_0x00e5:
                        if (r1 != 0) goto L_0x00f6
                        java.lang.String r8 = "RemoteClient"
                        java.lang.String r1 = "match completed!"
                        com.tiqiaa.icontrol.util.LogUtil.i(r8, r1)
                        com.tiqiaa.client.IRemoteClient$CallbackOnMatchDone r8 = r5
                        r1 = 6001(0x1771, float:8.409E-42)
                        r8.onMatchDone(r1, r0)
                        return
                    L_0x00f6:
                        com.tiqiaa.client.impl.RemoteClient r8 = com.tiqiaa.client.impl.RemoteClient.this
                        com.tiqiaa.remote.entity.MatchPage r0 = r3
                        com.tiqiaa.client.IRemoteClient$CallbackOnMatchDone r1 = r5
                        r8.match(r0, r1)
                        return
                    L_0x0100:
                        com.tiqiaa.client.IRemoteClient$CallbackOnMatchDone r8 = r5
                        r8.onMatchDone(r1, r0)
                        java.lang.String r8 = "RemoteClient"
                        java.lang.String r0 = "获取MatchKey为空！"
                        com.tiqiaa.icontrol.util.LogUtil.e(r8, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tiqiaa.client.impl.RemoteClient.AnonymousClass13.onMatchKeyLoaded(java.util.List):void");
                }
            });
        }
    }

    public void loadKeyInfrareds(String str, int i, AirRemoteState airRemoteState, IRemoteClient.CallBackOnKeyInfraredsLoaded callBackOnKeyInfraredsLoaded) {
        callBackOnKeyInfraredsLoaded.onInfraredsLoaded(0, (List<Infrared>) null);
    }

    public void delete_remote(String str, final IRemoteClient.CallBackOnRemoteDeleted callBackOnRemoteDeleted) {
        String str2 = String.valueOf(BASE_REMOTE_URL) + "/delete_remote";
        if (UserClient.getCurrentUser() == null) {
            LogUtil.e(TAG, "未登陆");
            callBackOnRemoteDeleted.onDeleted(9001);
        } else if (!PhoneHelper.checkNet()) {
            callBackOnRemoteDeleted.onDeleted(1);
        } else {
            long id = UserClient.getCurrentUser().getId();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("remote_id", (Object) str);
            jSONObject.put("user_id", (Object) Long.valueOf(id));
            this.client.doPost(str2, jSONObject, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    callBackOnRemoteDeleted.onDeleted(1);
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                        callBackOnRemoteDeleted.onDeleted(1);
                    } else if (((TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class)).getErrcode() == 10000) {
                        callBackOnRemoteDeleted.onDeleted(0);
                    } else {
                        callBackOnRemoteDeleted.onDeleted(1);
                    }
                }
            });
        }
    }

    public void miss_model(int i, long j, String str) {
        String str2 = String.valueOf(BASE_REMOTE_URL) + "/miss_model";
        if (PhoneHelper.checkNet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appliant_type", (Object) Integer.valueOf(i));
            jSONObject.put("brand_id", (Object) Long.valueOf(j));
            jSONObject.put("model", (Object) str);
            this.client.doPost(str2, jSONObject, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    LogUtil.e(RemoteClient.TAG, "onFailure...!" + RemoteClient.this.client.hashCode());
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    LogUtil.i(RemoteClient.TAG, "onSuccess...!");
                }
            });
        }
    }

    public void autoMatchRemotes(final Page page, final IRemoteClient.CallbackOnAutoMatchDone callbackOnAutoMatchDone) {
        String str = String.valueOf(BASE_REMOTE_URL) + "/search_official";
        page.setKeyword((String) null);
        if (page.getAppliance_type() == 2) {
            List matchReomtes = this.localDb.matchReomtes(page, true);
            if (page.getPage() == 0) {
                Collection noScreenAirRemotes = this.localDb.getNoScreenAirRemotes(page.getBrand_id());
                if (matchReomtes == null) {
                    matchReomtes = new ArrayList();
                }
                if (noScreenAirRemotes == null) {
                    noScreenAirRemotes = new ArrayList();
                }
                if (matchReomtes.size() < 4) {
                    matchReomtes.addAll(noScreenAirRemotes);
                } else {
                    matchReomtes.addAll(4, noScreenAirRemotes);
                }
            }
            callbackOnAutoMatchDone.onAutoMatchDone(0, matchReomtes);
        } else if (!PhoneHelper.checkNet()) {
            callbackOnAutoMatchDone.onAutoMatchDone(0, this.localDb.matchReomtes(page, true));
        } else {
            this.client.doPost(str, page, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    callbackOnAutoMatchDone.onAutoMatchDone(0, RemoteClient.this.localDb.matchReomtes(page, true));
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.d != 200 || responseInfo.f6335a == null) {
                        callbackOnAutoMatchDone.onAutoMatchDone(0, RemoteClient.this.localDb.matchReomtes(page, true));
                        return;
                    }
                    TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                    if (tQResponse == null) {
                        LogUtil.e(RemoteClient.TAG, "AutoMatchRemotes remote ......onFailure..######..........response = " + responseInfo);
                        callbackOnAutoMatchDone.onAutoMatchDone(1, (List<Remote>) null);
                    } else if (tQResponse.getErrcode() == 10000) {
                        List list = (List) tQResponse.getData(new TypeReference<List<Remote>>() {
                        });
                        if (list == null || list.size() < 30) {
                            if (list == null) {
                                list = new ArrayList();
                            }
                            List<Remote> matchReomtes = RemoteClient.this.localDb.matchReomtes(page, false);
                            if (matchReomtes != null && matchReomtes.size() > 0) {
                                for (Remote next : matchReomtes) {
                                    if (!list.contains(next)) {
                                        list.add(next);
                                    }
                                }
                            }
                        }
                        callbackOnAutoMatchDone.onAutoMatchDone(0, list);
                    }
                }
            });
        }
    }

    public void irmatch(IrMatchParam irMatchParam, final IRemoteClient.CallBackOnIrMatchDone callBackOnIrMatchDone) {
        if (irMatchParam == null) {
            LogUtil.e(TAG, "irmatch param is null!");
            callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
        } else if (irMatchParam.getAppliance_type() == 2) {
            String[] airIRPNameAndRemotes = LocalIrDb.getIrDb(this.context).getAirIRPNameAndRemotes(irMatchParam.getData(), irMatchParam.getBrand_id());
            if (airIRPNameAndRemotes != null) {
                ArrayList arrayList = new ArrayList();
                for (String str : airIRPNameAndRemotes) {
                    if (str != null && !str.equals("")) {
                        arrayList.add(str);
                    }
                }
                callBackOnIrMatchDone.onMatched(0, arrayList.size(), arrayList);
                return;
            }
            callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
        } else if (!PhoneHelper.checkNet()) {
            LogUtil.e(TAG, "irmatch param is null!");
            callBackOnIrMatchDone.onMatched(2, 0, (List<String>) null);
        } else {
            String str2 = String.valueOf(BASE_REMOTE_URL) + "/irmatch";
            IrMatchPageInfo irMatchPageInfo = new IrMatchPageInfo(irMatchParam);
            if (irMatchPageInfo.getMarks() == null || irMatchPageInfo.getMarks().size() <= 0 || irMatchPageInfo.getMarks().get(0).getMark() == null || irMatchPageInfo.getMarks().get(0).getMark().equals("")) {
                LogUtil.e(TAG, "irmatch pageInfo error!");
                callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
                return;
            }
            final LocalIrDb.PhysicalRemoteMatchResult physicalRemoteMatch = LocalIrDb.getIrDb(this.context).physicalRemoteMatch(irMatchPageInfo);
            if (!PhoneHelper.checkNet()) {
                callBackOnIrMatchDone.onMatched(0, physicalRemoteMatch.total_count, physicalRemoteMatch.ids);
            }
            this.client.doPost(str2, irMatchPageInfo, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    if (physicalRemoteMatch != null) {
                        callBackOnIrMatchDone.onMatched(0, physicalRemoteMatch.total_count, physicalRemoteMatch.ids);
                    } else {
                        callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
                    }
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.d == 200 && responseInfo.f6335a != null) {
                        TQResponse tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class);
                        if (tQResponse == null) {
                            LogUtil.e(RemoteClient.TAG, "irmatch onFailure..######..........response = " + responseInfo);
                            if (physicalRemoteMatch != null) {
                                callBackOnIrMatchDone.onMatched(0, physicalRemoteMatch.total_count, physicalRemoteMatch.ids);
                            } else {
                                callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
                            }
                        } else if (tQResponse.getErrcode() == 10000) {
                            List<String> list = (List) tQResponse.getData(new TypeReference<List<String>>() {
                            });
                            if (physicalRemoteMatch != null && physicalRemoteMatch.ids != null) {
                                if (list != null) {
                                    for (String str : list) {
                                        if (str != null && !str.equals("") && !physicalRemoteMatch.ids.contains(str)) {
                                            physicalRemoteMatch.ids.add(str);
                                        }
                                    }
                                }
                                callBackOnIrMatchDone.onMatched(0, physicalRemoteMatch.ids.size(), physicalRemoteMatch.ids);
                            } else if (list != null) {
                                callBackOnIrMatchDone.onMatched(0, list.size(), list);
                            } else {
                                callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
                            }
                        } else if (physicalRemoteMatch != null) {
                            callBackOnIrMatchDone.onMatched(0, physicalRemoteMatch.total_count, physicalRemoteMatch.ids);
                        } else {
                            callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
                        }
                    } else if (physicalRemoteMatch != null) {
                        callBackOnIrMatchDone.onMatched(0, physicalRemoteMatch.total_count, physicalRemoteMatch.ids);
                    } else {
                        callBackOnIrMatchDone.onMatched(1, 0, (List<String>) null);
                    }
                }
            });
        }
    }

    public List<Brand> getBrandByType(int i, String str) {
        int lang = LanguageUtils.getLang();
        WhereBuilder a2 = WhereBuilder.a();
        switch (i) {
            case 1:
                a2.b("remarks", "like", "%tv%");
                break;
            case 2:
                a2.b("remarks", "like", "%air%");
                break;
            case 3:
                a2.b("remarks", "like", "%fan%");
                break;
            case 4:
                a2.b("remarks", "like", "%pjt%");
                break;
            case 5:
                a2.b("remarks", "like", "%stb%");
                break;
            case 6:
                a2.b("remarks", "like", "%dvd%").c("remarks", "like", "%dc%").c("remarks", "like", "%cd%").c("remarks", "like", "%cs%").c("remarks", "like", "%dv%");
                break;
            case 7:
                a2.b("remarks", "like", "%cam%");
                break;
            case 8:
                a2.b("remarks", "like", "%light%");
                break;
            case 9:
                a2.b("remarks", "like", "%amp%");
                break;
            case 10:
                a2.b("remarks", "like", "%ipt%");
                break;
            case 11:
                a2.b("remarks", "like", "%box%");
                break;
            case 12:
                a2.b("remarks", "like", "%rsq%");
                break;
            case 13:
                a2.b("remarks", "like", "%jhq%");
                break;
        }
        String str2 = "brand_en";
        switch (lang) {
            case 0:
                str2 = "pinyin";
                a2.b("brand_cn", Operators.NOT_EQUAL2, "").b("brand_cn", Operators.NOT_EQUAL2, (Object) null);
                break;
            case 1:
                str2 = "pinyin";
                a2.b("brand_tw", Operators.NOT_EQUAL2, "").b("brand_tw", Operators.NOT_EQUAL2, (Object) null);
                break;
            case 2:
                a2.b("brand_en", Operators.NOT_EQUAL2, "").b("brand_en", Operators.NOT_EQUAL2, (Object) null);
                break;
            default:
                a2.b("brand_en", Operators.NOT_EQUAL2, "").b("brand_en", Operators.NOT_EQUAL2, (Object) null);
                break;
        }
        Selector a3 = Selector.a((Class<?>) Brand.class);
        a3.a(a2);
        if (str != null && !str.equals("")) {
            WhereBuilder a4 = WhereBuilder.a("brand_cn", "like", Operators.MOD + str + Operators.MOD);
            WhereBuilder c = a4.c("brand_tw", "like", Operators.MOD + str + Operators.MOD);
            WhereBuilder c2 = c.c("brand_ja", "like", Operators.MOD + str + Operators.MOD);
            WhereBuilder c3 = c2.c("brand_en", "like", Operators.MOD + str + Operators.MOD);
            a3.b(c3.c("brand_other", "like", Operators.MOD + str + Operators.MOD));
        }
        a3.c("upper(" + str2 + Operators.BRACKET_END_STR);
        return DataBaseManager.getInstance().getAllRecords(a3);
    }

    public List<Brand> getBrandByIds(List<Long> list, String str) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT * FROM tb_brand WHERE id in ");
        stringBuffer.append(Operators.BRACKET_START_STR);
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append(list.get(i));
            if (i != list.size() - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(Operators.BRACKET_END_STR);
        if (str != null && !str.equals("")) {
            String a2 = PinYin.a(str);
            if (a2.equalsIgnoreCase(str)) {
                a2 = null;
            }
            String str2 = "'%" + str.replaceAll("'", "''").replaceAll(Operators.MOD, "/%").replaceAll(JSMethod.NOT_SET, "/_").replaceAll("/", "//") + "%' ESCAPE '/'";
            stringBuffer.append(" AND (brand_cn like " + str2 + " OR brand_tw like " + str2 + " OR brand_en like " + str2 + " OR brand_other like " + str2 + " OR pinyin like " + str2);
            if (a2 != null) {
                String str3 = "'%" + a2.replaceAll("'", "''").replaceAll(Operators.MOD, "/%").replaceAll(JSMethod.NOT_SET, "/_").replaceAll("/", "//") + "%' ESCAPE '/'";
                stringBuffer.append(" OR brand_cn like " + str3 + " OR brand_tw like " + str3 + " OR brand_en like " + str3 + " OR brand_other like " + str3 + " OR pinyin like " + str3);
            }
            stringBuffer.append(Operators.BRACKET_END_STR);
        }
        stringBuffer.append(" ORDER BY upper(pinyin) ASC");
        Cursor execQuery = DataBaseManager.getInstance().execQuery(stringBuffer.toString());
        ArrayList arrayList = new ArrayList();
        if (execQuery != null && execQuery.getCount() > 0) {
            while (execQuery.moveToNext()) {
                Brand brand = new Brand();
                brand.setBrand_cn(execQuery.getString(execQuery.getColumnIndex("brand_cn")));
                brand.setBrand_en(execQuery.getString(execQuery.getColumnIndex("brand_en")));
                brand.setBrand_other(execQuery.getString(execQuery.getColumnIndex("brand_other")));
                brand.setBrand_tw(execQuery.getString(execQuery.getColumnIndex("brand_tw")));
                brand.setId(execQuery.getLong(execQuery.getColumnIndex("id")));
                brand.setPinyin(execQuery.getString(execQuery.getColumnIndex("pinyin")));
                brand.setPy(execQuery.getString(execQuery.getColumnIndex("py")));
                brand.setRemarks(execQuery.getString(execQuery.getColumnIndex("remarks")));
                arrayList.add(brand);
            }
        }
        execQuery.close();
        return arrayList;
    }

    public void getDataBrands(int i, int i2, String str, IRemoteClient.CallbackOnBrandLoaded callbackOnBrandLoaded) {
        List<Brand> brandListOfMachineType;
        String str2 = String.valueOf(BASE_REMOTE_URL) + "/search_data_brand";
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", (Object) Integer.valueOf(i));
        jSONObject.put("flag", (Object) Integer.valueOf(i2));
        String a2 = ACache.a(this.context).a(String.valueOf(i) + a.b + i2);
        if (a2 != null) {
            try {
                callbackOnBrandLoaded.onBrandLoaded(0, getBrandByIds((List) JSON.parseObject(a2, new TypeReference<List<Long>>() {
                }, new Feature[0]), str));
                return;
            } catch (Exception unused) {
            }
        }
        if (i == 2 && (brandListOfMachineType = LocalIrDb.getIrDb(this.context).getBrandListOfMachineType(i, i2)) != null) {
            callbackOnBrandLoaded.onBrandLoaded(0, getBrandByIds(cacheIdsAndSaveBrands(brandListOfMachineType, i, i2), str));
        } else if (!PhoneHelper.checkNet()) {
            List<Brand> brandListOfMachineType2 = LocalIrDb.getIrDb(this.context).getBrandListOfMachineType(i, i2);
            if (brandListOfMachineType2 != null) {
                callbackOnBrandLoaded.onBrandLoaded(0, getBrandByIds(getIdsAndSaveBrands(brandListOfMachineType2), str));
            } else {
                callbackOnBrandLoaded.onBrandLoaded(1, (List<Brand>) null);
            }
        } else {
            final int i3 = i;
            final int i4 = i2;
            final String str3 = str;
            final IRemoteClient.CallbackOnBrandLoaded callbackOnBrandLoaded2 = callbackOnBrandLoaded;
            this.client.doPost(str2, jSONObject, new RequestCallBack<String>() {
                public void onFailure(HttpException httpException, String str) {
                    List<Brand> brandListOfMachineType = LocalIrDb.getIrDb(RemoteClient.this.context).getBrandListOfMachineType(i3, i4);
                    if (brandListOfMachineType != null) {
                        callbackOnBrandLoaded2.onBrandLoaded(0, RemoteClient.this.getBrandByIds(RemoteClient.this.getIdsAndSaveBrands(brandListOfMachineType), str3));
                        return;
                    }
                    callbackOnBrandLoaded2.onBrandLoaded(1, (List<Brand>) null);
                }

                public void onSuccess(ResponseInfo<String> responseInfo) {
                    TQResponse tQResponse;
                    if (responseInfo.d != 200 || responseInfo.f6335a == null || (tQResponse = (TQResponse) Utils.JsonParseObject((String) responseInfo.f6335a, TQResponse.class)) == null || tQResponse.getErrcode() != 10000) {
                        callbackOnBrandLoaded2.onBrandLoaded(1, (List<Brand>) null);
                        return;
                    }
                    List list = (List) tQResponse.getData(new TypeReference<List<Brand>>() {
                    });
                    if (list == null) {
                        list = new ArrayList();
                    }
                    List<Brand> brandListOfMachineType = LocalIrDb.getIrDb(RemoteClient.this.context).getBrandListOfMachineType(i3, i4);
                    if (brandListOfMachineType != null && brandListOfMachineType.size() > 0) {
                        for (Brand next : brandListOfMachineType) {
                            if (!list.contains(next)) {
                                list.add(next);
                            }
                        }
                    }
                    callbackOnBrandLoaded2.onBrandLoaded(0, RemoteClient.this.getBrandByIds(RemoteClient.this.cacheIdsAndSaveBrands(list, i3, i4), str3));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public List<Long> getIdsAndSaveBrands(List<Brand> list) {
        return cacheIdsAndSaveBrands(list, 0, 0);
    }

    /* access modifiers changed from: private */
    public List<Long> cacheIdsAndSaveBrands(List<Brand> list, int i, int i2) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Brand next : list) {
            arrayList.add(Long.valueOf(next.getId()));
            if (next.getPinyin() == null || PinYin.b(next.getPinyin())) {
                next.setPinyin(PinYin.a(next.getBrand_cn()));
            }
        }
        DataBaseManager.getInstance().saveOrUpdataAll(list);
        if (i > 0 && i2 > 0) {
            ACache a2 = ACache.a(this.context);
            a2.a(i + a.b + i2, JSON.toJSONString(arrayList), (int) AppCode.m);
        }
        return arrayList;
    }
}
