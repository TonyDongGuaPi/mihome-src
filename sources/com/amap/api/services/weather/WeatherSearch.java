package com.amap.api.services.weather;

import android.content.Context;
import com.amap.api.services.a.bm;
import com.amap.api.services.interfaces.IWeatherSearch;

public class WeatherSearch {

    /* renamed from: a  reason: collision with root package name */
    private IWeatherSearch f4555a = null;

    public interface OnWeatherSearchListener {
        void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int i);

        void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int i);
    }

    public WeatherSearch(Context context) {
        if (this.f4555a == null) {
            try {
                this.f4555a = new bm(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public WeatherSearchQuery getQuery() {
        if (this.f4555a != null) {
            return this.f4555a.getQuery();
        }
        return null;
    }

    public void setQuery(WeatherSearchQuery weatherSearchQuery) {
        if (this.f4555a != null) {
            this.f4555a.setQuery(weatherSearchQuery);
        }
    }

    public void searchWeatherAsyn() {
        if (this.f4555a != null) {
            this.f4555a.searchWeatherAsyn();
        }
    }

    public void setOnWeatherSearchListener(OnWeatherSearchListener onWeatherSearchListener) {
        if (this.f4555a != null) {
            this.f4555a.setOnWeatherSearchListener(onWeatherSearchListener);
        }
    }
}
