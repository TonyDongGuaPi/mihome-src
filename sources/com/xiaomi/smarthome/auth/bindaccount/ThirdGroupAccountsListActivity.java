package com.xiaomi.smarthome.auth.bindaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.auth.bindaccount.model.ThirdAccount;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.stat.STAT;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.ArrayList;
import java.util.List;

public class ThirdGroupAccountsListActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private ListView f13915a;
    private SimpleAdapter b;
    private View c;
    /* access modifiers changed from: private */
    public DevicePtrFrameLayout d;
    private PtrIndicator e;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_third_group_accounts_list);
        this.c = findViewById(R.id.empty_all_page);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.third_account_bind);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThirdGroupAccountsListActivity.this.a(view);
            }
        });
        this.d = (DevicePtrFrameLayout) findViewById(R.id.pull_down_refresh);
        this.d.disableWhenHorizontalMove(true);
        this.d.setPullToRefresh(false);
        this.d.setPtrIndicator(new PtrIndicator());
        this.d.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ThirdGroupAccountsListActivity.this.a();
            }
        });
        this.f13915a = (ListView) findViewById(R.id.recycler_view);
        this.b = new SimpleAdapter();
        this.f13915a.setAdapter(this.b);
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a() {
        ThirdAccountBindManager.a().c((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (ThirdGroupAccountsListActivity.this.isValid()) {
                    ThirdGroupAccountsListActivity.this.b();
                    ThirdGroupAccountsListActivity.this.d.refreshComplete();
                }
            }

            public void onFailure(Error error) {
                if (ThirdGroupAccountsListActivity.this.isValid()) {
                    ToastUtil.a((int) R.string.load_failed);
                    ThirdGroupAccountsListActivity.this.d.refreshComplete();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        List<ThirdAccount> b2 = ThirdAccountBindManager.a().b();
        if (b2 == null || b2.isEmpty()) {
            d();
            return;
        }
        c();
        this.b.a(b2);
    }

    private void c() {
        this.c.setVisibility(8);
        this.f13915a.setVisibility(0);
    }

    private void d() {
        this.c.setVisibility(0);
        this.f13915a.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.d.autoRefresh();
    }

    private class SimpleAdapter extends BaseAdapter {
        private final List<ThirdAccount> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private SimpleAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<ThirdAccount> list) {
            this.b.clear();
            if (list != null) {
                this.b.addAll(list);
            }
            notifyDataSetChanged();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ThirdGroupAccountsListActivity.this.getContext()).inflate(R.layout.third_account_item_layout, viewGroup, false);
            }
            a(view, i);
            return view;
        }

        public void a(View view, int i) {
            final ThirdAccount thirdAccount = this.b.get(i);
            ((TextView) view.findViewById(R.id.title)).setText(thirdAccount.a());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (thirdAccount.d() != 0) {
                        Intent intent = new Intent(ThirdGroupAccountsListActivity.this.getContext(), ThirdAccountDetailActivity.class);
                        intent.putExtra("account_id", thirdAccount.b());
                        ThirdGroupAccountsListActivity.this.startActivity(intent);
                        STAT.d.a(thirdAccount.a());
                    }
                }
            });
            View findViewById = view.findViewById(R.id.next_btn);
            findViewById.setVisibility(0);
            TextView textView = (TextView) view.findViewById(R.id.state_tv);
            view.findViewById(R.id.next_btn).setVisibility(0);
            view.findViewById(R.id.state_tv).setVisibility(8);
            ThirdAccountBindManager.a((SimpleDraweeView) view.findViewById(R.id.icon), thirdAccount.c());
            if (thirdAccount.d() == 0) {
                textView.setText(R.string.third_account_state_binded);
                textView.setBackgroundResource(0);
                textView.setVisibility(0);
                findViewById.setVisibility(8);
            }
        }

        public int getCount() {
            return this.b.size();
        }
    }
}
