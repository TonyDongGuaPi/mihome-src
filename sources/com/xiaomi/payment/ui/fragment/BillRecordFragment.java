package com.xiaomi.payment.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.base.BasePaymentTaskAdapter;
import com.mibi.common.base.TaskManager;
import com.mibi.common.data.PageableListener;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.BillRecordDeleteTask;
import com.xiaomi.payment.task.BillRecordTask;
import com.xiaomi.payment.ui.adapter.BillRecordAdapter;
import java.util.ArrayList;

public class BillRecordFragment extends BaseFragment {
    protected Button A;
    /* access modifiers changed from: private */
    public BillRecordAdapter B;
    /* access modifiers changed from: private */
    public BillRecordTaskAdapter C;
    /* access modifiers changed from: private */
    public BillReordDeleteTaskAdapter D;
    /* access modifiers changed from: private */
    public int E = -1;
    private View.OnClickListener F = new View.OnClickListener() {
        public void onClick(View view) {
            BillRecordFragment.this.C.i();
        }
    };
    private View.OnCreateContextMenuListener G = new View.OnCreateContextMenuListener() {
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            int unused = BillRecordFragment.this.E = ((AdapterView.AdapterContextMenuInfo) contextMenuInfo).position;
            BillRecordFragment.this.getActivity().getMenuInflater().inflate(R.menu.mibi_bill_record_menu, contextMenu);
            String string = BillRecordFragment.this.getString(R.string.mibi_bill_delete_dialog_title, new Object[]{""});
            if (BillRecordFragment.this.E > -1) {
                string = ((BillRecordTask.Result.BillRecordItem) BillRecordFragment.this.B.getItem(BillRecordFragment.this.E)).mBillRecordDesc;
            }
            contextMenu.setHeaderTitle(string);
            contextMenu.findItem(R.id.menu_delete_item).setOnMenuItemClickListener(BillRecordFragment.this.H);
        }
    };
    /* access modifiers changed from: private */
    public MenuItem.OnMenuItemClickListener H = new MenuItem.OnMenuItemClickListener() {
        public boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem.getItemId() != R.id.menu_delete_item) {
                return false;
            }
            BillRecordFragment.this.K();
            return true;
        }
    };
    protected ListView t;
    protected View u;
    protected ProgressBar v;
    protected ProgressBar w;
    protected TextView x;
    protected TextView y;
    protected ImageView z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_bill_list, viewGroup, false);
        this.t = (ListView) inflate.findViewById(16908298);
        this.v = (ProgressBar) inflate.findViewById(R.id.progress);
        this.w = (ProgressBar) inflate.findViewById(R.id.append_progress);
        this.x = (TextView) inflate.findViewById(R.id.progress_text);
        this.y = (TextView) inflate.findViewById(R.id.error);
        this.z = (ImageView) inflate.findViewById(R.id.error_icon);
        this.u = inflate.findViewById(R.id.empty);
        this.A = (Button) inflate.findViewById(R.id.button_retry);
        this.t.setEmptyView(this.u);
        this.f7451a = (BaseActivity) getActivity();
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a(R.string.mibi_bill_record);
        this.B = new BillRecordAdapter(this.f7451a);
        this.C = new BillRecordTaskAdapter(getActivity(), q(), new BillRecordTask(getActivity(), p()));
        this.D = new BillReordDeleteTaskAdapter(getActivity(), q(), new BillRecordDeleteTask(getActivity(), p()));
        this.t.setAdapter(this.B);
        this.A.setOnClickListener(this.F);
        this.t.setOnScrollListener(new PageableListener() {
            public void a() {
                BillRecordFragment.this.C.k();
            }
        });
        this.t.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(MibiConstants.dm, (BillRecordTask.Result.BillRecordItem) BillRecordFragment.this.B.getItem(i));
                BillRecordFragment.this.a_(RecordDetailFragment.class, bundle);
            }
        });
        this.t.setOnCreateContextMenuListener(this.G);
        this.C.i();
    }

    /* access modifiers changed from: private */
    public void K() {
        if (-1 != this.E) {
            BillRecordTask.Result.BillRecordItem billRecordItem = (BillRecordTask.Result.BillRecordItem) this.B.getItem(this.E);
            String str = billRecordItem.mBillRecordType;
            String str2 = "";
            if (TextUtils.equals(str, MibiConstants.dn)) {
                str2 = getString(R.string.mibi_detail_recharge_title);
            } else if (TextUtils.equals(str, "trade")) {
                str2 = getString(R.string.mibi_detail_consume_title);
            }
            new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.mibi_bill_delete_dialog_title, new Object[]{str2})).setMessage(getString(R.string.mibi_bill_delete_message, new Object[]{billRecordItem.mBillRecordDesc})).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BillRecordFragment.this.D.start();
                }
            }).show();
        }
    }

    /* access modifiers changed from: protected */
    public void d(String str) {
        this.y.setText(str);
        this.y.setVisibility(0);
        this.z.setVisibility(0);
    }

    private class BillReordDeleteTaskAdapter extends BasePaymentTaskAdapter<BillRecordDeleteTask, Void, BasePaymentTask.Result> {
        public BillReordDeleteTaskAdapter(Context context, TaskManager taskManager, BillRecordDeleteTask billRecordDeleteTask) {
            super(context, taskManager, billRecordDeleteTask);
        }

        /* access modifiers changed from: protected */
        public SortedParameter j() {
            SortedParameter sortedParameter = new SortedParameter();
            if (-1 != BillRecordFragment.this.E) {
                BillRecordTask.Result.BillRecordItem billRecordItem = (BillRecordTask.Result.BillRecordItem) BillRecordFragment.this.B.getItem(BillRecordFragment.this.E);
                sortedParameter.a(MibiConstants.dH, (Object) billRecordItem.mBillRecordType);
                sortedParameter.a(MibiConstants.dI, (Object) billRecordItem.mBillId);
            }
            return sortedParameter;
        }

        /* access modifiers changed from: protected */
        public void c(BasePaymentTask.Result result) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(BillRecordFragment.this.B.a());
            if (!arrayList.isEmpty()) {
                if (BillRecordFragment.this.E > -1 && BillRecordFragment.this.E < arrayList.size()) {
                    arrayList.remove(BillRecordFragment.this.E);
                    BillRecordFragment.this.B.a(arrayList);
                    BillRecordFragment.this.C.f();
                }
                if (arrayList.isEmpty()) {
                    BillRecordFragment.this.d(BillRecordFragment.this.getString(R.string.mibi_bill_record_empty));
                    return;
                }
                long e2 = BillRecordFragment.this.C.e();
                if (arrayList.size() < 10 && ((long) BillRecordFragment.this.C.g()) < e2 - 1) {
                    BillRecordFragment.this.C.k();
                    return;
                }
                return;
            }
            BillRecordFragment.this.C.i();
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public void a(String str, int i, BasePaymentTask.Result result) {
            Toast.makeText(this.f7450a, str, 0).show();
        }
    }

    private class BillRecordTaskAdapter extends BasePaymentTaskAdapter<BillRecordTask, Void, BillRecordTask.Result> {
        private long f;
        private boolean g;

        public BillRecordTaskAdapter(Context context, TaskManager taskManager, BillRecordTask billRecordTask) {
            super(context, taskManager, billRecordTask);
        }

        /* access modifiers changed from: protected */
        public void c() {
            super.c();
            this.g = true;
            if (((BillRecordTask) this.d).e()) {
                BillRecordFragment.this.t.setVisibility(8);
                BillRecordFragment.this.u.setVisibility(0);
                BillRecordFragment.this.v.setVisibility(0);
                BillRecordFragment.this.x.setVisibility(0);
                BillRecordFragment.this.y.setVisibility(8);
                BillRecordFragment.this.z.setVisibility(8);
                BillRecordFragment.this.A.setVisibility(8);
                return;
            }
            BillRecordFragment.this.w.setVisibility(0);
        }

        /* access modifiers changed from: protected */
        public boolean d() {
            BillRecordFragment.this.v.setVisibility(8);
            BillRecordFragment.this.x.setVisibility(8);
            BillRecordFragment.this.w.setVisibility(8);
            this.g = false;
            return super.d();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void c(BillRecordTask.Result result) {
            ArrayList<BillRecordTask.Result.BillRecordItem> arrayList = result.mBillItemsList;
            this.f = result.mTotalNum;
            if (arrayList != null && !arrayList.isEmpty()) {
                if (h()) {
                    BillRecordFragment.this.B.a(arrayList);
                } else {
                    BillRecordFragment.this.B.a(arrayList, true);
                }
                l();
            } else if (h()) {
                BillRecordFragment.this.d(BillRecordFragment.this.getString(R.string.mibi_bill_record_empty));
            } else {
                Toast.makeText(this.f7450a, BillRecordFragment.this.getString(R.string.mibi_list_item_bottom_hint), 0).show();
            }
        }

        /* access modifiers changed from: protected */
        public void a(String str, int i, BillRecordTask.Result result) {
            if (h()) {
                BillRecordFragment.this.d(str);
            } else {
                Toast.makeText(this.f7450a, str, 0).show();
            }
        }

        /* access modifiers changed from: protected */
        public void a(int i, int i2, BillRecordTask.Result result) {
            super.a(i, i2, result);
            if (h()) {
                BillRecordFragment.this.A.setVisibility(0);
            }
        }

        public long e() {
            return this.f;
        }

        public void f() {
            ((BillRecordTask) this.d).c();
        }

        public int g() {
            return ((BillRecordTask) this.d).d();
        }

        public boolean h() {
            return ((BillRecordTask) this.d).e();
        }

        public void i() {
            if (!this.g) {
                ((BillRecordTask) this.d).q();
                start();
            }
        }

        public void k() {
            if (!this.g) {
                restart();
            }
        }

        public void l() {
            ((BillRecordTask) this.d).r();
        }
    }
}
