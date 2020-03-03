package com.xiaomi.smarthome.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthManagerListActivity extends BaseActivity {
    private static final int c = 1;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<AuthAppInfo> f13823a = new ArrayList();
    /* access modifiers changed from: private */
    public AuthManagerAdapter b;
    @BindView(2131430969)
    ImageView mBackIV;
    @BindView(2131429001)
    LinearLayout mEmptyLL;
    @BindView(2131427739)
    ListView mListView;
    @BindView(2131430975)
    TextView mTitleTV;

    class AuthManagerAdapter extends BaseAdapter {
        private Context b;
        private LayoutInflater c;

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f13829a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f13829a = viewHolder;
                viewHolder.mImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.image, "field 'mImage'", SimpleDraweeView.class);
                viewHolder.mNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'mNameTV'", TextView.class);
                viewHolder.mAuthTime = (TextView) Utils.findRequiredViewAsType(view, R.id.auth_time, "field 'mAuthTime'", TextView.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f13829a;
                if (viewHolder != null) {
                    this.f13829a = null;
                    viewHolder.mImage = null;
                    viewHolder.mNameTV = null;
                    viewHolder.mAuthTime = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public AuthManagerAdapter(Context context) {
            this.b = context;
            this.c = LayoutInflater.from(context);
        }

        public int getCount() {
            return AuthManagerListActivity.this.f13823a.size();
        }

        public Object getItem(int i) {
            return AuthManagerListActivity.this.f13823a.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.activity_auth_manager_list_item, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mImage.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.mImage.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.mImage.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setRoundingParams(RoundingParams.fromCornersRadius(20.0f)).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            final AuthAppInfo authAppInfo = (AuthAppInfo) AuthManagerListActivity.this.f13823a.get(i);
            if (!TextUtils.isEmpty(authAppInfo.d)) {
                viewHolder.mImage.setImageURI(Uri.parse(authAppInfo.d));
            }
            viewHolder.mNameTV.setText(authAppInfo.f13776a);
            viewHolder.mAuthTime.setText(SimpleDateFormat.getInstance().format(new Date(Long.valueOf(authAppInfo.g).longValue())));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(AuthManagerListActivity.this, AuthManagerDetailActivity.class);
                    intent.putExtra(AuthConstants.f13798a, authAppInfo.e);
                    AuthManagerListActivity.this.startActivityForResult(intent, 1);
                }
            });
            return view;
        }

        class ViewHolder {
            @BindView(2131427741)
            TextView mAuthTime;
            @BindView(2131429757)
            SimpleDraweeView mImage;
            @BindView(2131431126)
            TextView mNameTV;

            public ViewHolder(View view) {
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_auth_manager_list_layout);
        ButterKnife.bind((Activity) this);
        AuthManager.h().l();
        this.b = new AuthManagerAdapter(this);
        this.mListView.setAdapter(this.b);
        this.mTitleTV.setText(R.string.auth_manager);
        this.mBackIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AuthManagerListActivity.this.finish();
            }
        });
        a();
    }

    private void a() {
        AuthManager.h().a((AsyncCallback) new AsyncCallback() {
            public void onSuccess(Object obj) {
                AuthManagerListActivity.this.f13823a.clear();
                AuthManagerListActivity.this.f13823a.addAll((List) obj);
                AuthManagerListActivity.this.b.notifyDataSetChanged();
                if (AuthManagerListActivity.this.f13823a.size() == 0) {
                    AuthManagerListActivity.this.mEmptyLL.setVisibility(0);
                } else {
                    AuthManagerListActivity.this.mEmptyLL.setVisibility(8);
                }
            }

            public void onFailure(Error error) {
                AuthManagerListActivity.this.mEmptyLL.setVisibility(0);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            String stringExtra = intent.getStringExtra(AuthConstants.f13798a);
            if (intent != null && !TextUtils.isEmpty(stringExtra)) {
                for (int i3 = 0; i3 < this.f13823a.size(); i3++) {
                    if (this.f13823a.get(i3).e.equalsIgnoreCase(stringExtra)) {
                        this.f13823a.remove(i3);
                        if (this.f13823a.size() == 0) {
                            this.mEmptyLL.setVisibility(0);
                        } else {
                            this.mEmptyLL.setVisibility(8);
                        }
                        this.b.notifyDataSetChanged();
                        return;
                    }
                }
            }
        }
    }
}
