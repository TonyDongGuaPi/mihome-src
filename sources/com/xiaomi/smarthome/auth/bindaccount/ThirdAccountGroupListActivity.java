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
import com.xiaomi.smarthome.operation.OperationCollector;
import com.xiaomi.smarthome.operation.appcolud.AppCloudOperation;
import com.xiaomi.smarthome.stat.STAT;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.ArrayList;
import java.util.List;

public class ThirdAccountGroupListActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public DevicePtrFrameLayout f13910a;
    private TextView b;
    private ListView c;
    private SimpleAdapter d;
    private View e;
    boolean firstEnterResume = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_third_account_list_layout);
        a();
        e();
    }

    private void a() {
        b();
        c();
        d();
    }

    private void b() {
        this.f13910a = (DevicePtrFrameLayout) findViewById(R.id.pull_down_refresh);
        this.f13910a.disableWhenHorizontalMove(true);
        this.f13910a.setPullToRefresh(false);
        this.f13910a.setPtrIndicator(new PtrIndicator());
        this.f13910a.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ThirdAccountGroupListActivity.this.g();
            }
        });
        this.b = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.b.setText(R.string.add_btn_text);
        this.b.setVisibility(8);
        this.b.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThirdAccountGroupListActivity.this.c(view);
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.other_platform_device);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThirdAccountGroupListActivity.this.b(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        startActivity(new Intent(getContext(), ThirdGroupAccountsListActivity.class));
        STAT.d.a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    private void c() {
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ThirdAccountGroupListActivity.this.a(view);
            }
        });
        this.e = findViewById(R.id.empty_group);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        startActivity(new Intent(getContext(), ThirdGroupAccountsListActivity.class));
        STAT.d.a();
    }

    private void d() {
        this.c = (ListView) findViewById(R.id.recycler_view);
        this.d = new SimpleAdapter();
        this.c.setAdapter(this.d);
        if (this.c.findViewById(R.id.footer) == null) {
            this.c.addFooterView(getLayoutInflater().inflate(R.layout.activity_third_account_list_layout_banner_footer, this.c, false));
        }
    }

    private void e() {
        updateView();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        g();
        f();
    }

    private void f() {
        if (this.firstEnterResume) {
            this.firstEnterResume = false;
        } else {
            AppCloudOperation.a();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        ThirdAccountBindManager.a().c((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (ThirdAccountGroupListActivity.this.isValid()) {
                    ThirdAccountGroupListActivity.this.updateView();
                    ThirdAccountGroupListActivity.this.f13910a.refreshComplete();
                }
            }

            public void onFailure(Error error) {
                if (ThirdAccountGroupListActivity.this.isValid()) {
                    ToastUtil.a((int) R.string.load_failed);
                    ThirdAccountGroupListActivity.this.f13910a.refreshComplete();
                }
            }
        });
    }

    public void updateView() {
        List<ThirdAccount> c2 = ThirdAccountBindManager.a().c();
        if (c2 == null || c2.size() <= 0) {
            i();
        } else {
            h();
            this.d.a(c2);
        }
        this.f13910a.postDelayed(new Runnable() {
            public final void run() {
                ThirdAccountGroupListActivity.this.j();
            }
        }, 500);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void j() {
        if (isValid()) {
            OperationCollector.c(this).a(getWindow().getDecorView());
        }
    }

    private void h() {
        this.e.setVisibility(8);
        this.c.setVisibility(0);
        this.b.setVisibility(0);
    }

    private void i() {
        this.e.setVisibility(0);
        this.c.setVisibility(8);
        this.b.setVisibility(8);
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
                view = LayoutInflater.from(ThirdAccountGroupListActivity.this.getContext()).inflate(R.layout.third_account_item_layout, viewGroup, false);
            }
            a(view, i);
            return view;
        }

        public void a(View view, int i) {
            final ThirdAccount thirdAccount = this.b.get(i);
            ((TextView) view.findViewById(R.id.title)).setText(thirdAccount.a());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ThirdAccountGroupListActivity.this.getContext(), ThirdAccountDeviceListActivity.class);
                    intent.putExtra("account_id", thirdAccount.b());
                    ThirdAccountGroupListActivity.this.startActivity(intent);
                }
            });
            view.findViewById(R.id.next_btn).setVisibility(0);
            ((TextView) view.findViewById(R.id.state_tv)).setVisibility(8);
            ThirdAccountBindManager.a((SimpleDraweeView) view.findViewById(R.id.icon), thirdAccount.c());
        }

        public int getCount() {
            return this.b.size();
        }
    }
}
