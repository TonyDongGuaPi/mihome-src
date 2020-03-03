package com.amap.api.services.weather;

public class LocalWeatherForecastResult {

    /* renamed from: a  reason: collision with root package name */
    private WeatherSearchQuery f4552a;
    private LocalWeatherForecast b;

    public static LocalWeatherForecastResult createPagedResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherForecast localWeatherForecast) {
        return new LocalWeatherForecastResult(weatherSearchQuery, localWeatherForecast);
    }

    private LocalWeatherForecastResult(WeatherSearchQuery weatherSearchQuery, LocalWeatherForecast localWeatherForecast) {
        this.f4552a = weatherSearchQuery;
        this.b = localWeatherForecast;
    }

    public WeatherSearchQuery getWeatherForecastQuery() {
        return this.f4552a;
    }

    public LocalWeatherForecast getForecastResult() {
        return this.b;
    }
}
