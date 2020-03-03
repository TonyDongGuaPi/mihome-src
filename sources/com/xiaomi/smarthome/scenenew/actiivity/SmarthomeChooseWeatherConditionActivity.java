package com.xiaomi.smarthome.scenenew.actiivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import java.util.ArrayList;
import java.util.List;

public class SmarthomeChooseWeatherConditionActivity extends BaseActivity {
    Adapter mAdapter;
    Context mContext;
    LayoutInflater mInflater;
    List<WeatherData> mList = new ArrayList();
    @BindView(2131430478)
    ListView mListView;
    @BindView(2131430969)
    ImageView mReturnIV;
    @BindView(2131430975)
    TextView mTitleTV;
    WeatherInnerCondition weatherInnerCondition;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_weather_condition_layout);
        ButterKnife.bind((Activity) this);
        this.mInflater = LayoutInflater.from(this);
        this.mContext = this;
        this.mTitleTV.setText(R.string.condition_weather_outer_weather);
        a();
        b();
    }

    private void a() {
        this.weatherInnerCondition = CreateSceneManager.a().n();
        if (this.weatherInnerCondition == null) {
            c();
        }
        this.mList.clear();
        ArrayList arrayList = new ArrayList();
        arrayList.add(WeatherInnerCondition.WeatherConditionType.TYPE_SUNRISE);
        arrayList.add(WeatherInnerCondition.WeatherConditionType.TYPE_SUNSET);
        this.mList.add(new WeatherData(getString(R.string.condition_weather_sunrise), R.drawable.scene_sun_icon, arrayList));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_TEMPERATURE);
        arrayList2.add(WeatherInnerCondition.WeatherConditionType.TYPE_LESS_TEMPERATURE);
        this.mList.add(new WeatherData(getString(R.string.condition_weather_temperature), R.drawable.scene_temperature_icon, arrayList2));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_HUMIDITY);
        arrayList3.add(WeatherInnerCondition.WeatherConditionType.TYPE_LESS_HUMIDITY);
        this.mList.add(new WeatherData(getString(R.string.condition_weather_humidity), R.drawable.scene_humidity_icon, arrayList3));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(WeatherInnerCondition.WeatherConditionType.TYPE_ABOVE_AQI);
        arrayList4.add(WeatherInnerCondition.WeatherConditionType.TYPE_LESS_AQI);
        this.mList.add(new WeatherData(getString(R.string.condition_weather_aqi), R.drawable.scene_pm25_icon, arrayList4));
    }

    public static class WeatherData implements Parcelable {
        public static final Parcelable.Creator<WeatherData> CREATOR = new Parcelable.Creator<WeatherData>() {
            /* renamed from: a */
            public WeatherData createFromParcel(Parcel parcel) {
                return new WeatherData(parcel);
            }

            /* renamed from: a */
            public WeatherData[] newArray(int i) {
                return new WeatherData[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        String f21835a;
        int b;
        ArrayList<WeatherInnerCondition.WeatherConditionType> c = new ArrayList<>();

        public int describeContents() {
            return 0;
        }

        public WeatherData(String str, int i, ArrayList<WeatherInnerCondition.WeatherConditionType> arrayList) {
            this.f21835a = str;
            this.b = i;
            this.c = arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f21835a);
            parcel.writeInt(this.b);
            parcel.writeList(this.c);
        }

        protected WeatherData(Parcel parcel) {
            this.f21835a = parcel.readString();
            this.b = parcel.readInt();
            this.c = new ArrayList<>();
            parcel.readList(this.c, WeatherInnerCondition.WeatherConditionType.class.getClassLoader());
        }
    }

    private void b() {
        this.mReturnIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmarthomeChooseWeatherConditionActivity.this.c();
            }
        });
        this.mAdapter = new Adapter();
        this.mListView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    public void c() {
        setResult(0);
        finish();
    }

    public void onBackPressed() {
        c();
    }

    public class Adapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        public Adapter() {
        }

        public int getCount() {
            return SmarthomeChooseWeatherConditionActivity.this.mList.size();
        }

        public Object getItem(int i) {
            return SmarthomeChooseWeatherConditionActivity.this.mList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            final WeatherData weatherData = SmarthomeChooseWeatherConditionActivity.this.mList.get(i);
            View inflate = SmarthomeChooseWeatherConditionActivity.this.mInflater.inflate(R.layout.smarthome_condition_choose_item_layout, (ViewGroup) null);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.content_icon);
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(SmarthomeChooseWeatherConditionActivity.this.mContext.getResources()).setPlaceholderImage((int) R.drawable.device_list_phone_no).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            simpleDraweeView.setImageURI(CommonUtils.c(weatherData.b));
            ((TextView) inflate.findViewById(R.id.content)).setText(weatherData.f21835a);
            inflate.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(SmarthomeChooseWeatherConditionActivity.this.mContext, SmarthomeChooseWeatherConditionDetailActivity.class);
                    intent.putExtra(SmarthomeChooseWeatherConditionDetailActivity.WEATHER_DATA, weatherData);
                    SmarthomeChooseWeatherConditionActivity.this.weatherInnerCondition.f = weatherData.f21835a;
                    SmarthomeChooseWeatherConditionActivity.this.startActivityForResult(intent, 106);
                }
            });
            return inflate;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 106 && i2 == -1) {
            setResult(-1);
            finish();
        }
    }
}
