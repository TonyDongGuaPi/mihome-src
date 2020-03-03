package com.xiaomi.smarthome.frame.login.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.SmartHomePhoneNumUtil;
import java.util.ArrayList;
import java.util.List;

public class CountryPhoneCodePickerActivity extends BaseActivity {
    public static final int REQUEST_CODE_COUNTRY_PHONE_CODE = 1101;

    /* renamed from: a  reason: collision with root package name */
    private RecyclerView f16193a;
    private MyAdapter b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.country_phone_code_picker_activity_layout);
        a();
    }

    private void a() {
        this.f16193a = (RecyclerView) findViewById(R.id.recycler_view);
        this.f16193a.setLayoutManager(new LinearLayoutManager(this));
        this.b = new MyAdapter(SmartHomePhoneNumUtil.a((Context) this));
        this.f16193a.setAdapter(this.b);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CountryPhoneCodePickerActivity.this.a(view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<SmartHomePhoneNumUtil.CountryPhoneNumData> b = new ArrayList();

        public MyAdapter(List<SmartHomePhoneNumUtil.CountryPhoneNumData> list) {
            this.b = list;
        }

        @NonNull
        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_phone_code_picker_item_layout, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.a(this.b.get(i));
        }

        public int getItemCount() {
            return this.b.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView b;
            private TextView c;

            public MyViewHolder(View view) {
                super(view);
                this.b = (TextView) view.findViewById(R.id.country_name);
                this.c = (TextView) view.findViewById(R.id.country_phone_code);
            }

            public void a(final SmartHomePhoneNumUtil.CountryPhoneNumData countryPhoneNumData) {
                this.b.setText(countryPhoneNumData.f18707a);
                TextView textView = this.c;
                textView.setText("+" + countryPhoneNumData.b);
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.putExtra("country_phone_code", countryPhoneNumData.b);
                        CountryPhoneCodePickerActivity.this.setResult(-1, intent);
                        CountryPhoneCodePickerActivity.this.finish();
                    }
                });
            }
        }
    }
}
