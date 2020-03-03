package com.xiaomi.smarthome.framework.plugin.rn.nativemodule.thirdparty.chuangmi;

import com.tiqiaa.client.IRemoteClient;
import com.tiqiaa.client.ITvClient;
import com.tiqiaa.client.impl.RemoteClient;
import com.tiqiaa.client.impl.TvClient;
import com.tiqiaa.icontrol.util.TiqiaaService;
import com.tiqiaa.ircode.impl.InfraredFetcher;
import com.tiqiaa.irdnasdk.IrDnaSdkHelper;
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
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import com.xiaomi.smarthome.miio.ir.IRV2Codec;
import java.util.ArrayList;
import java.util.List;

public class CmInfraredDataProvider {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17451a = "Hq6UXid7b7i1LMO4lT7FMiOZ";
    private IRemoteClient b;
    private InfraredFetcher c;

    public interface IRAirRemoteStateCallback {
        void a(int i, AirRemoteState airRemoteState);

        void a(int i, String str);
    }

    public interface IRFetchInfraredCallback {
        void a(int i, String str);

        void a(int i, List<Infrared> list);
    }

    public interface IRLoadCityProvidersCallback {
        void a(int i, String str);

        void a(int i, List<CityProvider> list);
    }

    public interface IRLoadCitysCallback {
        void a(int i, String str);

        void a(int i, List<City> list);
    }

    public interface IRLoadProviderRemotesCallback {
        void a(int i, String str);

        void a(int i, List<CityProviderRemote> list);
    }

    public interface IRLoadProvidersCallback {
        void a(int i, String str);

        void a(int i, List<CityProvider> list);
    }

    public interface IRLoadProvincesCallback {
        void a(int i, String str);

        void a(int i, List<Province> list);
    }

    public interface IRLoadTvProviderCallback {
        void a(int i, String str);

        void a(int i, List<TvProvider> list);
    }

    public interface IROnBuildIRCodeCallback {
        void a(int i, String str);

        void b(int i, String str);
    }

    public interface IROnCommonCallback {
        void a(int i, String str);

        void a(int i, List<Remote> list);
    }

    public interface IROnConvertCodeCallback {
        void a(int i, String str);

        void a(int i, String str, String str2);
    }

    public interface IROnDownloadCallback {
        void a(int i, Remote remote);

        void a(int i, String str);
    }

    public interface IROnGetMatchRemoteListCallback {
        void a(int i, String str);

        void a(int i, List<Remote> list);
    }

    public interface IROnLoadBrandsCallback {
        void a(int i, String str);

        void a(int i, List<Brand> list);
    }

    public interface IROnSearchCallback {
        void a(int i, String str);

        void a(int i, List<Remote> list);
    }

    public CmInfraredDataProvider() {
        a();
    }

    public void a() {
        TiqiaaService.setLocalServer(false);
        TiqiaaService.init(XmPluginHostApi.instance().context(), f17451a);
        TiqiaaService.enableTestMode();
        this.b = new RemoteClient(XmPluginHostApi.instance().context());
        this.c = new InfraredFetcher(XmPluginHostApi.instance().context());
    }

    public void a(MatchPage matchPage, final IROnGetMatchRemoteListCallback iROnGetMatchRemoteListCallback) {
        b();
        this.b.exactMatchReomtes(matchPage, new IRemoteClient.CallbackOnMatchDone() {
            public void onMatchDone(int i, List<Remote> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnGetMatchRemoteListCallback.a(i, list);
                    return;
                }
                IROnGetMatchRemoteListCallback iROnGetMatchRemoteListCallback = iROnGetMatchRemoteListCallback;
                iROnGetMatchRemoteListCallback.a(i, "exactMatchRemote is error, errcoCode is " + i);
            }
        });
    }

    public void a(MatchPage matchPage, boolean z, final IROnGetMatchRemoteListCallback iROnGetMatchRemoteListCallback) {
        b();
        this.b.exactMatchReomtes(matchPage, z, new IRemoteClient.CallbackOnMatchDone() {
            public void onMatchDone(int i, List<Remote> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnGetMatchRemoteListCallback.a(i, list);
                    return;
                }
                IROnGetMatchRemoteListCallback iROnGetMatchRemoteListCallback = iROnGetMatchRemoteListCallback;
                iROnGetMatchRemoteListCallback.a(i, "exactMatchRemoteIfPower is error, errcoCode is " + i);
            }
        });
    }

    public void a(int i, Page page, IROnSearchCallback iROnSearchCallback) {
        if (i == 0) {
            a(page, iROnSearchCallback);
        } else if (i == 2) {
            b(page, iROnSearchCallback);
        } else if (i == 1) {
            c(page, iROnSearchCallback);
        }
    }

    private void a(Page page, final IROnSearchCallback iROnSearchCallback) {
        b();
        this.b.searchOfficial(page, new IRemoteClient.CallbackOnSearchDone() {
            public void onSearchDone(int i, List<Remote> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnSearchCallback.a(i, list);
                    return;
                }
                IROnSearchCallback iROnSearchCallback = iROnSearchCallback;
                iROnSearchCallback.a(i, "searchOfficial is error, errcoCode is " + i);
            }
        });
    }

    private void b(Page page, final IROnSearchCallback iROnSearchCallback) {
        b();
        this.b.searchDiy(page, new IRemoteClient.CallbackOnSearchDone() {
            public void onSearchDone(int i, List<Remote> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnSearchCallback.a(i, list);
                    return;
                }
                IROnSearchCallback iROnSearchCallback = iROnSearchCallback;
                iROnSearchCallback.a(i, "searchDiy is error, errcoCode is " + i);
            }
        });
    }

    private void c(Page page, final IROnSearchCallback iROnSearchCallback) {
        b();
        this.b.searchAirRemote(page, new IRemoteClient.CallbackOnSearchDone() {
            public void onSearchDone(int i, List<Remote> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnSearchCallback.a(i, list);
                    return;
                }
                IROnSearchCallback iROnSearchCallback = iROnSearchCallback;
                iROnSearchCallback.a(i, "searchAirRemote is error, errcoCode is " + i);
            }
        });
    }

    public void a(String str, final IROnDownloadCallback iROnDownloadCallback) {
        b();
        this.b.download_reomte(str, new IRemoteClient.CallBackOnRemoteDownloaded() {
            public void onRemoteDownloaded(int i, Remote remote) {
                if (i == 0) {
                    iROnDownloadCallback.a(i, remote);
                    return;
                }
                IROnDownloadCallback iROnDownloadCallback = iROnDownloadCallback;
                iROnDownloadCallback.a(i, "downloadRemote is error, errcoCode is " + i);
            }
        });
    }

    public void a(final IROnLoadBrandsCallback iROnLoadBrandsCallback) {
        b();
        this.b.load_brands(new IRemoteClient.CallbackOnBrandLoaded() {
            public void onBrandLoaded(int i, List<Brand> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnLoadBrandsCallback.a(i, list);
                    return;
                }
                IROnLoadBrandsCallback iROnLoadBrandsCallback = iROnLoadBrandsCallback;
                iROnLoadBrandsCallback.a(i, "loadBrands is error, errcoCode is " + i);
            }
        });
    }

    public void a(Page page, final IROnCommonCallback iROnCommonCallback) {
        b();
        this.b.autoMatchRemotes(page, new IRemoteClient.CallbackOnAutoMatchDone() {
            public void onAutoMatchDone(int i, List<Remote> list) {
                if (i == 0) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    iROnCommonCallback.a(i, list);
                    return;
                }
                IROnCommonCallback iROnCommonCallback = iROnCommonCallback;
                iROnCommonCallback.a(i, "autoMatchRemote is error, errcoCode is " + i);
            }
        });
    }

    public void a(int i, long j, String str) {
        b();
        this.b.miss_model(i, j, str);
    }

    public void a(int i, String str, IROnConvertCodeCallback iROnConvertCodeCallback) {
        byte[] irCode = IrDnaSdkHelper.getIrCode(i, Base64Coder.a(str));
        if (irCode == null || irCode.length < 8) {
            iROnConvertCodeCallback.a(-1, "IrCode result is null or length < 8");
            return;
        }
        byte[] bArr = new byte[8];
        System.arraycopy(irCode, 0, bArr, 0, 8);
        byte[] bArr2 = new byte[(irCode.length - 8)];
        System.arraycopy(irCode, 8, bArr2, 0, irCode.length - 8);
        iROnConvertCodeCallback.a(0, Base64Coder.a(bArr), Base64Coder.a(bArr2));
    }

    public void a(int i, String str, IROnBuildIRCodeCallback iROnBuildIRCodeCallback) {
        try {
            iROnBuildIRCodeCallback.a(0, Base64Coder.a(IrDnaSdkHelper.buildIrCode(i, IRV2Codec.a(str)).data));
        } catch (Exception e) {
            iROnBuildIRCodeCallback.b(-1, e.toString());
        }
    }

    public void a(Remote remote, Key key, IRFetchInfraredCallback iRFetchInfraredCallback) {
        c();
        try {
            List fetchNoAirInfrareds = this.c.fetchNoAirInfrareds(remote, key);
            if (fetchNoAirInfrareds == null) {
                fetchNoAirInfrareds = new ArrayList();
            }
            iRFetchInfraredCallback.a(0, (List<Infrared>) fetchNoAirInfrareds);
        } catch (Exception e) {
            iRFetchInfraredCallback.a(-1, e.toString());
        }
    }

    public void a(Remote remote, Key key, AirRemoteState airRemoteState, IRFetchInfraredCallback iRFetchInfraredCallback) {
        c();
        try {
            List fetchAirInfrareds = this.c.fetchAirInfrareds(remote, key, airRemoteState);
            if (fetchAirInfrareds == null) {
                fetchAirInfrareds = new ArrayList();
            }
            iRFetchInfraredCallback.a(0, (List<Infrared>) fetchAirInfrareds);
        } catch (Exception e) {
            iRFetchInfraredCallback.a(-1, e.toString());
        }
    }

    public void a(Key key, AirRemoteState airRemoteState, int i, IRFetchInfraredCallback iRFetchInfraredCallback) {
        c();
        try {
            List fetchAirTimeInfrareds = this.c.fetchAirTimeInfrareds(key, i, airRemoteState);
            if (fetchAirTimeInfrareds == null) {
                fetchAirTimeInfrareds = new ArrayList();
            }
            iRFetchInfraredCallback.a(0, (List<Infrared>) fetchAirTimeInfrareds);
        } catch (Exception e) {
            iRFetchInfraredCallback.a(-1, e.toString());
        }
    }

    public void a(Remote remote, IRAirRemoteStateCallback iRAirRemoteStateCallback) {
        c();
        try {
            iRAirRemoteStateCallback.a(0, this.c.getAirRemoteStatus(remote));
        } catch (Exception e) {
            iRAirRemoteStateCallback.a(-1, e.toString());
        }
    }

    public void a(final IRLoadProvincesCallback iRLoadProvincesCallback) {
        new TvClient(XmPluginHostApi.instance().context()).load_provinces_from_server(new ITvClient.CallBackOnProvincesLoaded() {
            public void onProviceLoaded(int i, List<Province> list) {
                if (i == 0) {
                    iRLoadProvincesCallback.a(i, list);
                    return;
                }
                IRLoadProvincesCallback iRLoadProvincesCallback = iRLoadProvincesCallback;
                iRLoadProvincesCallback.a(i, "load_provinces_from_server error, error code is " + i);
            }
        });
    }

    public void a(final IRLoadCitysCallback iRLoadCitysCallback) {
        new TvClient(XmPluginHostApi.instance().context()).load_cities_from_server(new ITvClient.CallBackOnCitiesLoaded() {
            public void onCityLoaded(int i, List<City> list) {
                if (i == 0) {
                    iRLoadCitysCallback.a(i, list);
                    return;
                }
                IRLoadCitysCallback iRLoadCitysCallback = iRLoadCitysCallback;
                iRLoadCitysCallback.a(i, "load_cities_from_server error, error code is " + i);
            }
        });
    }

    public void a(final IRLoadProvidersCallback iRLoadProvidersCallback) {
        new TvClient(XmPluginHostApi.instance().context()).load_cityproviders_from_server(new ITvClient.CallBackOnCityProvidersLoaded() {
            public void onCityProviderLoaded(int i, List<CityProvider> list) {
                if (i == 0) {
                    iRLoadProvidersCallback.a(i, list);
                    return;
                }
                IRLoadProvidersCallback iRLoadProvidersCallback = iRLoadProvidersCallback;
                iRLoadProvidersCallback.a(i, "load_cityproviders_from_server error, error code is " + i);
            }
        });
    }

    public void a(int i, int i2, final IRLoadProviderRemotesCallback iRLoadProviderRemotesCallback) {
        new TvClient(XmPluginHostApi.instance().context()).load_provider_remotes(i, i2, new ITvClient.CallBackOnCityProviderRemotesLoaded() {
            public void onLoaded(int i, List<CityProviderRemote> list) {
                if (i == 0) {
                    iRLoadProviderRemotesCallback.a(i, list);
                    return;
                }
                IRLoadProviderRemotesCallback iRLoadProviderRemotesCallback = iRLoadProviderRemotesCallback;
                iRLoadProviderRemotesCallback.a(i, "load_provider_remotes error, error code is " + i);
            }
        });
    }

    public void a(final IRLoadCityProvidersCallback iRLoadCityProvidersCallback) {
        new TvClient(XmPluginHostApi.instance().context()).load_cityproviders_from_server(new ITvClient.CallBackOnCityProvidersLoaded() {
            public void onCityProviderLoaded(int i, List<CityProvider> list) {
                if (i == 0) {
                    iRLoadCityProvidersCallback.a(i, list);
                    return;
                }
                IRLoadCityProvidersCallback iRLoadCityProvidersCallback = iRLoadCityProvidersCallback;
                iRLoadCityProvidersCallback.a(i, "load_cityproviders_from_server error, error code is " + i);
            }
        });
    }

    public void a(int i, IRLoadTvProviderCallback iRLoadTvProviderCallback) {
        List providersFromCity = new TvClient(XmPluginHostApi.instance().context()).getProvidersFromCity(i);
        if (providersFromCity == null) {
            providersFromCity = new ArrayList();
        }
        iRLoadTvProviderCallback.a(0, (List<TvProvider>) providersFromCity);
    }

    public void a(int i, IRLoadCitysCallback iRLoadCitysCallback) {
        List provinceCities = new TvClient(XmPluginHostApi.instance().context()).getProvinceCities(i);
        if (provinceCities == null) {
            provinceCities = new ArrayList();
        }
        iRLoadCitysCallback.a(0, (List<City>) provinceCities);
    }

    private void b() {
        if (this.b == null) {
            this.b = new RemoteClient(XmPluginHostApi.instance().context());
        }
    }

    private void c() {
        if (this.c == null) {
            this.c = new InfraredFetcher(XmPluginHostApi.instance().context());
        }
    }
}
