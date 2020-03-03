package com.amap.api.services.weather;

public class LocalWeatherLiveResult {

    /* renamed from: a  reason: collision with root package name */
    private WeatherSearchQuery f4554a;
    private LocalWeatherLive b;

    public static LocalWeatherLiveResult createPagedResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherLive localWeatherLive) {
        return new LocalWeatherLiveResult(weatherSearchQuery, localWeatherLive);
    }

    private LocalWeatherLiveResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherLive localWeatherLive) {
        this.f4554a = weatherSearchQuery;
        this.b = localWeatherLive;
    }

    public WeatherSearchQuery getWeatherLiveQuery() {
        return this.f4554a;
    }

    public LocalWeatherLive getLiveResult() {
        return this.b;
    }
}
