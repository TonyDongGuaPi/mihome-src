package com.xiaomi.smarthome.scene.condition;

import android.content.Intent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.api.SceneApi;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherInnerCondition extends BaseInnerCondition implements LocaleGetResourceFixHelper {
    public static final int d = 106;
    public int e;
    public String f;
    public String g;
    public String h;
    public String i;
    public Object j;
    public int k;

    public int a() {
        return R.string.condition_weather_name;
    }

    public int a(SceneApi.Condition condition) {
        return -1;
    }

    public String b(int i2) {
        return "";
    }

    public int c(int i2) {
        return 0;
    }

    public boolean g() {
        return false;
    }

    public enum WeatherConditionType {
        TYPE_SUNRISE(1, SceneApi.Condition.LAUNCH_TYPE.SUN_RISE, 101, R.string.sunrise, "cloud.weather.sunrise"),
        TYPE_SUNSET(2, SceneApi.Condition.LAUNCH_TYPE.SUN_SET, 101, R.string.sunset, "cloud.weather.sunset"),
        TYPE_LESS_HUMIDITY(3, SceneApi.Condition.LAUNCH_TYPE.HUMIDITY, 113, R.string.humidity_less, "cloud.weather.humidity"),
        TYPE_ABOVE_HUMIDITY(4, SceneApi.Condition.LAUNCH_TYPE.HUMIDITY, 112, R.string.humidity_above, "cloud.weather.humidity"),
        TYPE_LESS_TEMPERATURE(5, SceneApi.Condition.LAUNCH_TYPE.TEMPERATURE, 111, R.string.temperature_less, "cloud.weather.temperature"),
        TYPE_ABOVE_TEMPERATURE(6, SceneApi.Condition.LAUNCH_TYPE.TEMPERATURE, 110, R.string.temperature_above, "cloud.weather.temperature"),
        TYPE_LESS_AQI(7, SceneApi.Condition.LAUNCH_TYPE.AQI, 106, R.string.aqi_less, "cloud.weather.aqi"),
        TYPE_ABOVE_AQI(8, SceneApi.Condition.LAUNCH_TYPE.AQI, 105, R.string.aqi_above, "cloud.weather.aqi");
        
        public int compatibleId;
        public final int conditionId;
        public int conditionNameId;
        public String key;
        public SceneApi.Condition.LAUNCH_TYPE launchType;

        private WeatherConditionType(int i, SceneApi.Condition.LAUNCH_TYPE launch_type, int i2, int i3, String str) {
            this.conditionId = i;
            this.launchType = launch_type;
            this.compatibleId = i2;
            this.conditionNameId = i3;
            this.key = str;
        }
    }

    public WeatherInnerCondition(Device device) {
        super(device);
    }

    public SceneApi.Condition a(int i2, Intent intent) {
        SceneApi.Condition condition = new SceneApi.Condition();
        SceneApi.ConditionWeather conditionWeather = new SceneApi.ConditionWeather();
        WeatherConditionType d2 = d(i2);
        conditionWeather.b = this.f;
        conditionWeather.d = d2.conditionId;
        conditionWeather.f21525a = d2.key;
        conditionWeather.g = this.i;
        conditionWeather.e = this.h;
        conditionWeather.f = this.g;
        conditionWeather.c = this.j;
        conditionWeather.h = this.k;
        if (intent != null) {
            try {
                if (intent.hasExtra("value")) {
                    conditionWeather.c = new JSONObject(intent.getStringExtra("value"));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        condition.k = d2.compatibleId;
        condition.f21522a = d2.launchType;
        condition.j = conditionWeather;
        return condition;
    }

    private WeatherConditionType d(int i2) {
        WeatherConditionType[] values = WeatherConditionType.values();
        for (int i3 = 0; i3 < values.length; i3++) {
            if (values[i3].conditionId == i2) {
                return values[i3];
            }
        }
        return null;
    }

    public String e() {
        return SHApplication.getAppContext().getString(R.string.condition_weather_name);
    }

    public void a(SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.std_scene_icon_weather));
    }
}
