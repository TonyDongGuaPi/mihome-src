package com.xiaomi.shopviews.gsvvideo.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.xiaomi.shopviews.gsvvideo.model.SwitchVideoModel;
import com.xiaomi.shopviews.widget.R;
import java.util.List;

public class SwitchVideoTypeDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    private Context f13158a;
    private ListView b = null;
    private ArrayAdapter<SwitchVideoModel> c = null;
    /* access modifiers changed from: private */
    public OnListItemClickListener d;
    private List<SwitchVideoModel> e;

    public interface OnListItemClickListener {
        void a(int i);
    }

    public SwitchVideoTypeDialog(Context context) {
        super(context, R.style.dialog_style);
        this.f13158a = context;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void a(List<SwitchVideoModel> list, OnListItemClickListener onListItemClickListener) {
        this.d = onListItemClickListener;
        this.e = list;
        View inflate = LayoutInflater.from(this.f13158a).inflate(R.layout.switch_video_dialog, (ViewGroup) null);
        this.b = (ListView) inflate.findViewById(R.id.switch_dialog_list);
        setContentView(inflate);
        this.c = new ArrayAdapter<>(this.f13158a, R.layout.switch_video_dialog_item, list);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(new OnItemClickListener());
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        double d2 = (double) this.f13158a.getResources().getDisplayMetrics().widthPixels;
        Double.isNaN(d2);
        attributes.width = (int) (d2 * 0.8d);
        window.setAttributes(attributes);
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        private OnItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SwitchVideoTypeDialog.this.dismiss();
            SwitchVideoTypeDialog.this.d.a(i);
        }
    }
}
