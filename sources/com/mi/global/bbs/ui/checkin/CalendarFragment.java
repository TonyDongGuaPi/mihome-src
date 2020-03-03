package com.mi.global.bbs.ui.checkin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.view.SignCalendarView;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class CalendarFragment extends BaseFragment implements SignCalendarView.OnMonthChangeListener {
    @BindView(2131493996)
    SignCalendarView mSignedCalendarView;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_check_calendar_layout, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSignedCalendarView.setOnMonthChangeListener(this);
        getSignCalendar(Calendar.getInstance());
    }

    private void getSignCalendar(Calendar calendar) {
        ApiClient.getApiService().getSignCalendar(getMonthString(calendar)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).compose(bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<JsonObject>() {
            public void accept(JsonObject jsonObject) throws Exception {
                CalendarFragment.this.handleSignData(jsonObject.toString());
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleSignData(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("data")) {
                this.mSignedCalendarView.setSignedData(jSONObject.getJSONObject("data").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getMonthString(Calendar calendar) {
        return new SimpleDateFormat("yyyyMM", Locale.getDefault()).format(calendar.getTime());
    }

    public void onMonthChange(View view, Calendar calendar) {
        getSignCalendar(calendar);
    }
}
