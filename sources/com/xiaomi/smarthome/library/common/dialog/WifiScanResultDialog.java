package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import java.util.List;

public class WifiScanResultDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f18613a;
    private CustomPullDownRefreshListView b;
    /* access modifiers changed from: private */
    public List<ScanResult> c;
    private BaseAdapter d;
    /* access modifiers changed from: private */
    public LayoutInflater e;
    /* access modifiers changed from: private */
    public OnWifiScanListener f;
    private CustomPullDownRefreshLinearLayout g;

    public interface OnWifiScanListener {
        void a();

        void a(DialogInterface dialogInterface, int i);
    }

    public static WifiScanResultDialog a(Context context, CharSequence charSequence, CharSequence charSequence2) {
        return a(context, charSequence, charSequence2, true);
    }

    public static WifiScanResultDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return a(context, charSequence, charSequence2, z, false);
    }

    public static WifiScanResultDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        return a(context, charSequence, charSequence2, z, z2, (DialogInterface.OnCancelListener) null);
    }

    public static WifiScanResultDialog a(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        WifiScanResultDialog wifiScanResultDialog = new WifiScanResultDialog(context);
        wifiScanResultDialog.setTitle(charSequence);
        wifiScanResultDialog.setMessage(charSequence2);
        wifiScanResultDialog.setCancelable(z2);
        wifiScanResultDialog.setOnCancelListener(onCancelListener);
        wifiScanResultDialog.show();
        return wifiScanResultDialog;
    }

    class SimpleAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return WifiScanResultDialog.this.c.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = WifiScanResultDialog.this.e.inflate(R.layout.ml_select_dialog_item, (ViewGroup) null);
            }
            ((TextView) view.findViewById(R.id.text1)).setText(((ScanResult) WifiScanResultDialog.this.c.get(i)).SSID);
            view.findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    WifiScanResultDialog.this.f.a(WifiScanResultDialog.this, i);
                    WifiScanResultDialog.this.dismiss();
                }
            });
            return view;
        }
    }

    public WifiScanResultDialog(Context context) {
        this(context, 2131559365);
        this.f18613a = context;
        setCancelable(true);
    }

    public WifiScanResultDialog(Context context, int i) {
        super(context, i);
        this.f18613a = context;
        setCancelable(true);
        this.e = LayoutInflater.from(this.f18613a);
    }

    public void a(List<ScanResult> list) {
        this.c = list;
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
        if (this.g != null) {
            if (this.g != null) {
                this.g.postRefresh();
            }
            if (this.b != null) {
                this.b.postRefresh();
            }
        }
    }

    public void a(OnWifiScanListener onWifiScanListener) {
        this.f = onWifiScanListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.wifi_scan_result_dialog, (ViewGroup) null);
        this.b = (CustomPullDownRefreshListView) inflate.findViewById(R.id.wifi_list);
        this.b.setDivider((Drawable) null);
        this.b.setCacheColorHint(0);
        this.g = (CustomPullDownRefreshLinearLayout) inflate.findViewById(R.id.common_white_empty_view);
        this.b.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                WifiScanResultDialog.this.f.a();
            }
        });
        this.d = new SimpleAdapter();
        this.b.setAdapter(this.d);
        ((TextView) inflate.findViewById(R.id.common_white_empty_text)).setText(R.string.get_wifi_scan_result_error);
        if (this.c.size() == 0) {
            this.g.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            this.g.setVisibility(8);
            this.b.setVisibility(0);
        }
        setView(inflate);
        super.onCreate(bundle);
    }

    public void setCancelable(boolean z) {
        super.setCancelable(z);
    }
}
