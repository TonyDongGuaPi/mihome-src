package com.xiaomi.smarthome.scene.location;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.scene.location.model.UsrLocInfo;
import com.xiaomi.smarthome.scene.location.model.UsrLocInfoManger;
import java.util.ArrayList;
import java.util.List;

public class InitialUsrLocListActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SimpleAdapter f21578a;
    @BindView(2131427577)
    TextView add;
    @BindView(2131430457)
    PullDownDragListView list;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131432919)
    FrameLayout titleBar;

    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        super.onCreate(bundle, persistableBundle);
        setContentView(R.layout.activity_usr_loc_list_add);
        ButterKnife.bind((Activity) this);
        this.f21578a = new SimpleAdapter();
        this.list.setAdapter(this.f21578a);
        this.list.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                InitialUsrLocListActivity.this.a();
            }
        });
        if (!UsrLocInfoManger.a().b()) {
            this.list.doRefresh();
        } else {
            a();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        UsrLocInfoManger.a().a((AsyncCallback<List<UsrLocInfo>, Error>) new AsyncCallback<List<UsrLocInfo>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(List<UsrLocInfo> list) {
                InitialUsrLocListActivity.this.f21578a.a(list);
                InitialUsrLocListActivity.this.f21578a.notifyDataSetChanged();
            }
        });
    }

    @OnClick({2131430969, 2131427577})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.add) {
            b();
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    private class SimpleAdapter extends BaseAdapter {
        private List<UsrLocInfo> b;

        public long getItemId(int i) {
            return 0;
        }

        private SimpleAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<UsrLocInfo> list) {
            this.b.clear();
            if (list != null) {
                this.b.addAll(list);
            }
            notifyDataSetChanged();
        }

        public void a(UsrLocInfo usrLocInfo) {
            this.b.add(usrLocInfo);
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i > this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(InitialUsrLocListActivity.this).inflate(R.layout.usr_loc_info_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f21585a = (TextView) view.findViewById(R.id.name);
                viewHolder.b = (TextView) view.findViewById(R.id.hint_not_set);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            UsrLocInfo usrLocInfo = this.b.get(i);
            viewHolder.f21585a.setText(usrLocInfo.c());
            List<String> d = usrLocInfo.d();
            if (d == null || d.size() == 0) {
                viewHolder.b.setVisibility(0);
            } else {
                viewHolder.b.setVisibility(8);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            return view;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f21585a;
            TextView b;

            ViewHolder() {
            }
        }
    }

    private void b() {
        new MLAlertDialog.Builder(this).a((int) R.string.add_btn_text).a("", true).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = ((MLAlertDialog) dialogInterface).getInputView().getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    ToastUtil.a((int) R.string.back_name_less_limit_not_null);
                } else {
                    InitialUsrLocListActivity.this.a(obj);
                }
            }
        }).b().show();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        UsrLocInfo usrLocInfo = new UsrLocInfo();
        usrLocInfo.a(0);
        usrLocInfo.a(str);
        UsrLocInfoManger.a().a(usrLocInfo, (AsyncCallback<UsrLocInfo, Error>) new AsyncCallback<UsrLocInfo, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(UsrLocInfo usrLocInfo) {
                InitialUsrLocListActivity.this.f21578a.a(usrLocInfo);
                InitialUsrLocListActivity.this.f21578a.notifyDataSetChanged();
            }
        });
    }
}
