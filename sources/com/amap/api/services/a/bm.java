package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.ac;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IWeatherSearch;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;

public class bm implements IWeatherSearch {

    /* renamed from: a  reason: collision with root package name */
    private Context f4340a;
    /* access modifiers changed from: private */
    public WeatherSearchQuery b;
    /* access modifiers changed from: private */
    public WeatherSearch.OnWeatherSearchListener c;
    /* access modifiers changed from: private */
    public LocalWeatherLiveResult d;
    /* access modifiers changed from: private */
    public LocalWeatherForecastResult e;
    /* access modifiers changed from: private */
    public Handler f = null;

    public bm(Context context) {
        this.f4340a = context.getApplicationContext();
        this.f = ac.a();
    }

    public WeatherSearchQuery getQuery() {
        return this.b;
    }

    public void setQuery(WeatherSearchQuery weatherSearchQuery) {
        this.b = weatherSearchQuery;
    }

    public void searchWeatherAsyn() {
        try {
            as.a().a(new Runnable() {
                public void run() {
                    ac.k kVar;
                    ac.l lVar;
                    Message obtainMessage = ac.a().obtainMessage();
                    obtainMessage.arg1 = 13;
                    Bundle bundle = new Bundle();
                    if (bm.this.b == null) {
                        try {
                            throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
                        } catch (AMapException e) {
                            s.a(e, "WeatherSearch", "searchWeatherAsyn");
                        }
                    } else if (bm.this.b.getType() == 1) {
                        try {
                            LocalWeatherLiveResult unused = bm.this.d = bm.this.a();
                            bundle.putInt("errorCode", 1000);
                            lVar = new ac.l();
                        } catch (AMapException e2) {
                            bundle.putInt("errorCode", e2.getErrorCode());
                            s.a(e2, "WeatherSearch", "searchWeatherAsyn");
                            lVar = new ac.l();
                        } catch (Throwable th) {
                            ac.l lVar2 = new ac.l();
                            obtainMessage.what = 1301;
                            lVar2.b = bm.this.c;
                            lVar2.f4288a = bm.this.d;
                            obtainMessage.obj = lVar2;
                            obtainMessage.setData(bundle);
                            bm.this.f.sendMessage(obtainMessage);
                            throw th;
                        }
                        obtainMessage.what = 1301;
                        lVar.b = bm.this.c;
                        lVar.f4288a = bm.this.d;
                        obtainMessage.obj = lVar;
                        obtainMessage.setData(bundle);
                        bm.this.f.sendMessage(obtainMessage);
                    } else if (bm.this.b.getType() == 2) {
                        try {
                            LocalWeatherForecastResult unused2 = bm.this.e = bm.this.b();
                            bundle.putInt("errorCode", 1000);
                            kVar = new ac.k();
                        } catch (AMapException e3) {
                            bundle.putInt("errorCode", e3.getErrorCode());
                            s.a(e3, "WeatherSearch", "searchWeatherAsyn");
                            kVar = new ac.k();
                        } catch (Throwable th2) {
                            ac.k kVar2 = new ac.k();
                            obtainMessage.what = 1302;
                            kVar2.b = bm.this.c;
                            kVar2.f4287a = bm.this.e;
                            obtainMessage.obj = kVar2;
                            obtainMessage.setData(bundle);
                            bm.this.f.sendMessage(obtainMessage);
                            throw th2;
                        }
                        obtainMessage.what = 1302;
                        kVar.b = bm.this.c;
                        kVar.f4287a = bm.this.e;
                        obtainMessage.obj = kVar;
                        obtainMessage.setData(bundle);
                        bm.this.f.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public LocalWeatherLiveResult a() throws AMapException {
        aa.a(this.f4340a);
        if (this.b != null) {
            aw awVar = new aw(this.f4340a, this.b);
            return LocalWeatherLiveResult.createPagedResult((WeatherSearchQuery) awVar.j(), (LocalWeatherLive) awVar.c());
        }
        throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
    }

    /* access modifiers changed from: private */
    public LocalWeatherForecastResult b() throws AMapException {
        aa.a(this.f4340a);
        if (this.b != null) {
            av avVar = new av(this.f4340a, this.b);
            return LocalWeatherForecastResult.createPagedResult((WeatherSearchQuery) avVar.j(), (LocalWeatherForecast) avVar.c());
        }
        throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
    }

    public void setOnWeatherSearchListener(WeatherSearch.OnWeatherSearchListener onWeatherSearchListener) {
        this.c = onWeatherSearchListener;
    }
}
