package com.xiaomi.smarthome.newui.amappoi;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import java.util.ArrayList;
import java.util.List;

public class InputTipTask implements Inputtips.InputtipsListener {

    /* renamed from: a  reason: collision with root package name */
    private static InputTipTask f20449a;
    private Inputtips b;
    private Context c;
    private AutoCompleteTextView d;
    private List<LocationBean> e = new ArrayList();

    private InputTipTask(Context context) {
        this.c = context;
    }

    public static InputTipTask a(Context context) {
        if (f20449a == null) {
            synchronized (InputTipTask.class) {
                if (f20449a == null) {
                    f20449a = new InputTipTask(context);
                }
            }
        }
        return f20449a;
    }

    public InputTipTask a(AutoCompleteTextView autoCompleteTextView) {
        this.d = autoCompleteTextView;
        return this;
    }

    public List<LocationBean> a() {
        return this.e;
    }

    public void a(String str, String str2) {
        InputtipsQuery inputtipsQuery = new InputtipsQuery(str, str2);
        inputtipsQuery.setCityLimit(true);
        this.b = new Inputtips(this.c, inputtipsQuery);
        this.b.setInputtipsListener(this);
        this.b.requestInputtipsAsyn();
    }

    public void onGetInputtips(List<Tip> list, int i) {
        if (i == 1000) {
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                this.e.clear();
                for (Tip next : list) {
                    arrayList.add(next.getName());
                    this.e.add(new LocationBean(next.getPoint().getLongitude(), next.getPoint().getLatitude(), next.getAddress(), next.getDistrict()));
                }
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this.c, 17367043, arrayList);
            this.d.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
