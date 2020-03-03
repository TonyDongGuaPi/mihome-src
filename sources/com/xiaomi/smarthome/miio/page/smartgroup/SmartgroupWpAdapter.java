package com.xiaomi.smarthome.miio.page.smartgroup;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.api.model.AreaPropAirWaterInfo;
import com.xiaomi.smarthome.library.common.util.FontManager;
import java.util.ArrayList;
import java.util.List;

public class SmartgroupWpAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Context f19908a;
    private LayoutInflater b;
    private List<AreaPropAirWaterInfo> c = new ArrayList();
    private int d = 0;
    private Typeface e;

    public long getItemId(int i) {
        return (long) i;
    }

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f19910a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f19910a = viewHolder;
            viewHolder.tvWpTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_title, "field 'tvWpTitle'", TextView.class);
            viewHolder.tvWpDataMax = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_data_max, "field 'tvWpDataMax'", TextView.class);
            viewHolder.tvWpDataDivider = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_data_divider, "field 'tvWpDataDivider'", TextView.class);
            viewHolder.tvWpDataMin = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_data_min, "field 'tvWpDataMin'", TextView.class);
            viewHolder.tvWpDataUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_wp_data_unit, "field 'tvWpDataUnit'", TextView.class);
            viewHolder.tvAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_address, "field 'tvAddress'", TextView.class);
            viewHolder.tvDataSource = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_data_source, "field 'tvDataSource'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f19910a;
            if (viewHolder != null) {
                this.f19910a = null;
                viewHolder.tvWpTitle = null;
                viewHolder.tvWpDataMax = null;
                viewHolder.tvWpDataDivider = null;
                viewHolder.tvWpDataMin = null;
                viewHolder.tvWpDataUnit = null;
                viewHolder.tvAddress = null;
                viewHolder.tvDataSource = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public SmartgroupWpAdapter(Context context, int i) {
        this.f19908a = context;
        this.b = LayoutInflater.from(this.f19908a);
        this.e = FontManager.a(SmartGroupConstants.f);
        this.d = i;
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public void a(List<AreaPropAirWaterInfo> list) {
        if (list != null) {
            this.c = list;
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.b.inflate(R.layout.smartgroup_wp_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        AreaPropAirWaterInfo areaPropAirWaterInfo = this.c.get(i);
        if (areaPropAirWaterInfo != null) {
            a(viewHolder, areaPropAirWaterInfo);
        }
        return view;
    }

    private void a(ViewHolder viewHolder, AreaPropAirWaterInfo areaPropAirWaterInfo) {
        viewHolder.tvWpDataMax.setTypeface(this.e);
        viewHolder.tvWpDataMin.setTypeface(this.e);
        viewHolder.tvAddress.setText(SmartgroupWpActivity.getProvince(areaPropAirWaterInfo.r));
        switch (this.d) {
            case 0:
                viewHolder.tvWpTitle.setText(R.string.smartgroup_ap_title);
                try {
                    viewHolder.tvDataSource.setText(this.f19908a.getResources().getQuantityString(R.plurals.smartgroup_data_source, Integer.parseInt(areaPropAirWaterInfo.f), new Object[]{areaPropAirWaterInfo.f}));
                } catch (Exception unused) {
                    viewHolder.tvDataSource.setText(this.f19908a.getResources().getQuantityString(R.plurals.smartgroup_data_source, 4000, new Object[]{areaPropAirWaterInfo.f}));
                }
                viewHolder.tvWpDataMax.setText(areaPropAirWaterInfo.i);
                viewHolder.tvWpDataMax.setTextColor(SmartGroupConstants.h);
                viewHolder.tvWpDataDivider.setTextColor(SmartGroupConstants.h);
                viewHolder.tvWpDataMin.setTextColor(SmartGroupConstants.h);
                viewHolder.tvWpDataMin.setText(areaPropAirWaterInfo.c());
                return;
            case 1:
                viewHolder.tvWpTitle.setText(R.string.smartgroup_wp_title);
                try {
                    viewHolder.tvDataSource.setText(this.f19908a.getResources().getQuantityString(R.plurals.smartgroup_data_source, Integer.parseInt(areaPropAirWaterInfo.h), new Object[]{areaPropAirWaterInfo.h}));
                } catch (Exception unused2) {
                    viewHolder.tvDataSource.setText(this.f19908a.getResources().getQuantityString(R.plurals.smartgroup_data_source, 4000, new Object[]{areaPropAirWaterInfo.h}));
                }
                viewHolder.tvWpDataMax.setText(areaPropAirWaterInfo.a());
                viewHolder.tvWpDataMax.setTextColor(SmartGroupConstants.i);
                viewHolder.tvWpDataDivider.setTextColor(SmartGroupConstants.i);
                viewHolder.tvWpDataMin.setTextColor(SmartGroupConstants.i);
                viewHolder.tvWpDataMin.setText(areaPropAirWaterInfo.b());
                return;
            case 2:
                viewHolder.tvWpTitle.setText(R.string.smartgroup_ah_title_item);
                viewHolder.tvWpDataUnit.setVisibility(0);
                viewHolder.tvWpDataUnit.setTextColor(SmartGroupConstants.j);
                try {
                    viewHolder.tvDataSource.setText(this.f19908a.getResources().getQuantityString(R.plurals.smartgroup_data_source, Integer.parseInt(areaPropAirWaterInfo.o), new Object[]{areaPropAirWaterInfo.o}));
                } catch (Exception unused3) {
                    viewHolder.tvDataSource.setText(this.f19908a.getResources().getQuantityString(R.plurals.smartgroup_data_source, 4000, new Object[]{areaPropAirWaterInfo.o}));
                }
                viewHolder.tvWpDataMax.setText(areaPropAirWaterInfo.m);
                viewHolder.tvWpDataMax.setTextColor(SmartGroupConstants.j);
                viewHolder.tvWpDataDivider.setTextColor(SmartGroupConstants.j);
                viewHolder.tvWpDataMin.setTextColor(SmartGroupConstants.j);
                viewHolder.tvWpDataMin.setText(areaPropAirWaterInfo.n);
                return;
            default:
                return;
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19909a;
        @BindView(2131433182)
        TextView tvAddress;
        @BindView(2131433269)
        TextView tvDataSource;
        @BindView(2131433553)
        TextView tvWpDataDivider;
        @BindView(2131433554)
        TextView tvWpDataMax;
        @BindView(2131433555)
        TextView tvWpDataMin;
        @BindView(2131433556)
        TextView tvWpDataUnit;
        @BindView(2131433557)
        TextView tvWpTitle;

        ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
            this.f19909a = view;
        }
    }
}
