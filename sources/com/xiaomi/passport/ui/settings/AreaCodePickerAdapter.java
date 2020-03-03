package com.xiaomi.passport.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.settings.utils.PhoneNumUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class AreaCodePickerAdapter extends BaseAdapter implements SectionIndexer {
    private Context mContext;
    private List<PhoneNumUtil.CountryPhoneNumData> mList;
    private Map<Integer, String> mSectionText;
    private int[] mSectionToPosition;
    private String[] mSections;
    private boolean mShowAreaCode = true;

    public long getItemId(int i) {
        return (long) i;
    }

    public AreaCodePickerAdapter(Context context, Bundle bundle) {
        this.mContext = context;
        buildPickerSectionList();
        if (bundle != null) {
            this.mShowAreaCode = bundle.getBoolean("show_country_code", true);
        }
    }

    private void buildPickerSectionList() {
        int i;
        this.mList = PhoneNumUtil.getCountryPhoneNumDataList();
        TreeSet treeSet = new TreeSet();
        ArrayList arrayList = new ArrayList();
        Iterator<PhoneNumUtil.CountryPhoneNumData> it = this.mList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String upperCase = it.next().countryName.substring(0, 1).toUpperCase();
            arrayList.add(upperCase);
            treeSet.add(upperCase);
        }
        this.mSections = (String[]) treeSet.toArray(new String[0]);
        this.mSectionToPosition = new int[this.mSections.length];
        this.mSectionText = new HashMap();
        for (i = 0; i < this.mSections.length; i++) {
            this.mSectionToPosition[i] = arrayList.indexOf(this.mSections[i]);
            this.mSectionText.put(Integer.valueOf(this.mSectionToPosition[i]), this.mSections[i]);
        }
    }

    public String getSectionTitleForPostion(int i) {
        return this.mList.get(i).countryName.substring(0, 1).toUpperCase();
    }

    public int getCount() {
        return this.mList.size();
    }

    public PhoneNumUtil.CountryPhoneNumData getItem(int i) {
        return this.mList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        AreaCodePickerListItem areaCodePickerListItem = (AreaCodePickerListItem) view;
        if (areaCodePickerListItem == null) {
            areaCodePickerListItem = (AreaCodePickerListItem) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.passport_area_code_list_item, (ViewGroup) null);
        }
        ((TextView) areaCodePickerListItem.findViewById(R.id.area_code)).setVisibility(this.mShowAreaCode ? 0 : 8);
        areaCodePickerListItem.bind(getItem(i), this.mSectionText.get(Integer.valueOf(i)));
        return areaCodePickerListItem;
    }

    public Object[] getSections() {
        return this.mSections;
    }

    public int getPositionForSection(int i) {
        return this.mSectionToPosition[i];
    }

    public int getSectionForPosition(int i) {
        int i2 = 1;
        int i3 = 0;
        while (i2 < this.mSectionToPosition.length && this.mSectionToPosition[i2] <= i) {
            i3++;
            i2++;
        }
        return i3;
    }
}
