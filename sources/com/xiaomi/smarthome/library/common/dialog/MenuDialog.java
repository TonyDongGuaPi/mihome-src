package com.xiaomi.smarthome.library.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class MenuDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    CharSequence[] f18580a;
    DialogInterface.OnClickListener b;
    ListView c;
    BaseAdapter d;
    LayoutInflater e;
    int f = -1;

    public MenuDialog(Context context) {
        super(context, 2131559371);
    }

    public void a(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        this.f18580a = charSequenceArr;
        this.b = onClickListener;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(BaseAdapter baseAdapter) {
        this.d = baseAdapter;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        getWindow().addFlags(1024);
        getWindow().setGravity(48);
        if (!ApiHelper.f) {
            TitleBarUtil.b(getWindow());
        }
        this.e = LayoutInflater.from(getContext());
        View inflate = this.e.inflate(R.layout.menu_dialog, (ViewGroup) null);
        getWindow().setContentView(inflate);
        if (this.f > 0) {
            inflate.setBackgroundColor(this.f);
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.y = 0;
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
        this.c = (ListView) findViewById(R.id.select_dialog_listview);
        this.c.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (MenuDialog.this.b != null) {
                    MenuDialog.this.dismiss();
                    MenuDialog.this.b.onClick((DialogInterface) null, i);
                }
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MenuDialog.this.dismiss();
            }
        });
        if (this.d == null && this.f18580a != null) {
            this.d = new BaseAdapter() {
                public long getItemId(int i) {
                    return 0;
                }

                public int getCount() {
                    return MenuDialog.this.f18580a.length;
                }

                public Object getItem(int i) {
                    return MenuDialog.this.f18580a[i];
                }

                public View getView(int i, View view, ViewGroup viewGroup) {
                    if (view == null) {
                        view = MenuDialog.this.e.inflate(R.layout.menu_dialog_item, (ViewGroup) null);
                    }
                    ((TextView) view.findViewById(R.id.text1)).setText(MenuDialog.this.f18580a[i]);
                    return view;
                }
            };
        }
        if (this.d != null) {
            this.c.setAdapter(this.d);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.a((View) this.c);
    }
}
