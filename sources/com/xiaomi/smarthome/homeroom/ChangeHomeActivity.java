package com.xiaomi.smarthome.homeroom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.newui.widget.ChangeHomeListItemView;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class ChangeHomeActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<Home> f17927a = new ArrayList();
    private homeAdapter b;
    @BindView(2131430508)
    ListView listView;
    @BindView(2131428538)
    ViewGroup mContainer;
    XQProgressDialog mXQProgressDialogSimple;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    TextView tvHomeCount;
    TextView tvUsrName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_change_home);
        ButterKnife.bind((Activity) this);
        a();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f17927a = HomeManager.a().f();
        if (this.f17927a == null || this.f17927a.isEmpty()) {
            finish();
            return;
        }
        STAT.d.p(this.f17927a.size());
        this.b.notifyDataSetChanged();
        String l = HomeManager.a().l();
        if (this.b.a(l) != -1) {
            this.listView.setItemChecked(this.b.a(l) + 1, true);
        }
        this.tvHomeCount.setText(getResources().getQuantityString(R.plurals.home_count, this.f17927a.size(), new Object[]{Integer.valueOf(this.f17927a.size())}));
    }

    private void a() {
        if (DarkModeCompat.a((Context) this)) {
            this.mContainer.setBackground((Drawable) null);
        }
        this.moduleA3ReturnBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ChangeHomeActivity.this.b(view);
            }
        });
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_homelist_footview_v3, this.listView, false);
        inflate.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ChangeHomeActivity.this.a(view);
            }
        });
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.item_homelist_header, this.listView, false);
        this.tvUsrName = (TextView) inflate2.findViewById(R.id.tv_usr_name);
        this.tvHomeCount = (TextView) inflate2.findViewById(R.id.tv_home_count);
        this.listView.addHeaderView(inflate2, (Object) null, false);
        this.listView.addFooterView(inflate, (Object) null, false);
        this.b = new homeAdapter();
        this.listView.setAdapter(this.b);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.xiaomi.smarthome.homeroom.model.Home r1 = (com.xiaomi.smarthome.homeroom.model.Home) r1
                    if (r1 == 0) goto L_0x0033
                    java.lang.String r2 = r1.j()
                    com.xiaomi.smarthome.homeroom.HomeManager r3 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    java.lang.String r3 = r3.l()
                    boolean r2 = android.text.TextUtils.equals(r2, r3)
                    if (r2 != 0) goto L_0x0033
                    com.xiaomi.smarthome.homeroom.ChangeHomeActivity r2 = com.xiaomi.smarthome.homeroom.ChangeHomeActivity.this
                    r2.b()
                    com.xiaomi.smarthome.homeroom.HomeManager r2 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    java.lang.String r1 = r1.j()
                    com.xiaomi.smarthome.homeroom.ChangeHomeActivity$1$1 r3 = new com.xiaomi.smarthome.homeroom.ChangeHomeActivity$1$1
                    r3.<init>()
                    r2.a((java.lang.String) r1, (com.xiaomi.smarthome.frame.AsyncCallback) r3)
                L_0x0033:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.ChangeHomeActivity.AnonymousClass1.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        Intent intent = new Intent(this, MultiHomeManagerActivity.class);
        intent.putExtra("from", 7);
        startActivity(intent);
        STAT.d.Z();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.mXQProgressDialogSimple != null && this.mXQProgressDialogSimple.isShowing()) {
            this.mXQProgressDialogSimple.dismiss();
        }
        this.mXQProgressDialogSimple = XQProgressDialog.a(this, "", getString(R.string.loading));
    }

    private void c() {
        UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
            public void a(int i) {
            }

            public void a(int i, Object obj) {
            }

            public void a(ShareUserRecord shareUserRecord) {
                if (ChangeHomeActivity.this.isValid()) {
                    try {
                        ChangeHomeActivity.this.tvUsrName.setText(shareUserRecord.nickName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private class homeAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        public homeAdapter() {
        }

        public int a(String str) {
            for (int i = 0; i < ChangeHomeActivity.this.f17927a.size(); i++) {
                if (TextUtils.equals(str, ((Home) ChangeHomeActivity.this.f17927a.get(i)).j())) {
                    return i;
                }
            }
            return -1;
        }

        public int getCount() {
            if (ChangeHomeActivity.this.f17927a != null) {
                return ChangeHomeActivity.this.f17927a.size();
            }
            return 0;
        }

        /* renamed from: a */
        public Home getItem(int i) {
            if (ChangeHomeActivity.this.f17927a != null) {
                return (Home) ChangeHomeActivity.this.f17927a.get(i);
            }
            return null;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = LayoutInflater.from(ChangeHomeActivity.this).inflate(R.layout.item_single_choice_v2, viewGroup, false);
                viewHolder.f17932a = (ChangeHomeListItemView) view2.findViewById(R.id.root);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            Home a2 = getItem(i);
            if (a2 != null) {
                viewHolder.f17932a.setText(a2.k());
                if (a2.p()) {
                    int size = HomeManager.a().a(a2.j(), new boolean[0]).size();
                    int size2 = a2.d().size();
                    String quantityString = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.home_room_size, size2, new Object[]{Integer.valueOf(size2)});
                    String quantityString2 = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.home_device_size, size, new Object[]{Integer.valueOf(size)});
                    viewHolder.f17932a.setSubText(quantityString + " | " + quantityString2);
                } else {
                    viewHolder.f17932a.setSubText(ChangeHomeActivity.this.getResources().getString(R.string.share_polymerization_shared_home));
                }
            }
            viewHolder.f17932a.setDividerVisivle(false);
            return view2;
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            ChangeHomeListItemView f17932a;

            private ViewHolder() {
            }
        }
    }
}
