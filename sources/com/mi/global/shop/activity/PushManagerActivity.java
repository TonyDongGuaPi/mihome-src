package com.mi.global.shop.activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.model.push.PushClassifyModel;
import com.mi.global.shop.newmodel.NewSimpleResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.RecycleViewDivider;
import com.mi.global.shop.widget.SlidingButton;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.Coder;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class PushManagerActivity extends BaseActivity {
    public static final String TAG = "PushManagerActivity";

    /* renamed from: a  reason: collision with root package name */
    private PushManagerAdapter f5417a;
    private ArrayList<PushClassifyModel.PushClassifyItem> b = new ArrayList<>();
    private ArrayList<PushClassifyModel.PushClassifyItem> c = new ArrayList<>();
    @BindView(2131493921)
    RecyclerView pushRecycleView;

    public static class PushManagerAdapter extends RecyclerView.Adapter<PushManagerViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        public static final String f5422a = "PushManagerAdapter";
        public ArrayList<PushClassifyModel.PushClassifyItem> b = new ArrayList<>();
        /* access modifiers changed from: private */
        public Context c;
        private ArrayList<PushClassifyModel.PushClassifyItem> d = new ArrayList<>();

        public class PushManagerViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private PushManagerViewHolder f5424a;

            @UiThread
            public PushManagerViewHolder_ViewBinding(PushManagerViewHolder pushManagerViewHolder, View view) {
                this.f5424a = pushManagerViewHolder;
                pushManagerViewHolder.itemTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_title, "field 'itemTitle'", CustomTextView.class);
                pushManagerViewHolder.itemDesc = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_desc, "field 'itemDesc'", CustomTextView.class);
                pushManagerViewHolder.btnSwitch = (SlidingButton) Utils.findRequiredViewAsType(view, R.id.btn_switch, "field 'btnSwitch'", SlidingButton.class);
            }

            @CallSuper
            public void unbind() {
                PushManagerViewHolder pushManagerViewHolder = this.f5424a;
                if (pushManagerViewHolder != null) {
                    this.f5424a = null;
                    pushManagerViewHolder.itemTitle = null;
                    pushManagerViewHolder.itemDesc = null;
                    pushManagerViewHolder.btnSwitch = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public PushManagerAdapter(Context context) {
            this.c = context;
        }

        public void a(ArrayList<PushClassifyModel.PushClassifyItem> arrayList) {
            Iterator<PushClassifyModel.PushClassifyItem> it = arrayList.iterator();
            while (it.hasNext()) {
                PushClassifyModel.PushClassifyItem next = it.next();
                PushClassifyModel.PushClassifyItem pushClassifyItem = new PushClassifyModel.PushClassifyItem();
                pushClassifyItem.key = next.key;
                pushClassifyItem.defaultStatus = next.defaultStatus;
                this.b.add(pushClassifyItem);
            }
        }

        public void b(ArrayList<PushClassifyModel.PushClassifyItem> arrayList) {
            if (arrayList != null) {
                this.d.clear();
                this.d.addAll(arrayList);
                notifyDataSetChanged();
            }
        }

        public void c(ArrayList<PushClassifyModel.PushClassifyItem> arrayList) {
            if (arrayList != null) {
                this.d.addAll(arrayList);
                notifyDataSetChanged();
            }
        }

        public String a() {
            String str = "";
            for (int i = 0; i < this.d.size(); i++) {
                if (i < this.b.size()) {
                    if (!this.b.get(i).defaultStatus && this.d.get(i).defaultStatus) {
                        str = str + this.d.get(i).key + ",";
                    }
                } else if (this.d.get(i).defaultStatus) {
                    str = str + this.d.get(i).key + ",";
                }
            }
            if (str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            Log.d("watchTypes", " 1 " + str);
            return str;
        }

        public String b() {
            String str = "";
            for (int i = 0; i < this.d.size(); i++) {
                if (i < this.b.size()) {
                    if (this.b.get(i).defaultStatus && !this.d.get(i).defaultStatus) {
                        str = str + this.d.get(i).key + ",";
                    }
                } else if (!this.d.get(i).defaultStatus) {
                    str = str + this.d.get(i).key + ",";
                }
            }
            if (str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            Log.d("watchTypes", " 2" + str);
            return str;
        }

        public String c() {
            String str = "";
            for (int i = 0; i < this.d.size(); i++) {
                if (!this.d.get(i).defaultStatus) {
                    str = str + this.d.get(i).key + ",";
                }
            }
            if (str.length() > 0) {
                str = str.substring(0, str.length() - 1);
            }
            Log.d("watchTypes", " 3 " + str);
            return str;
        }

        /* renamed from: a */
        public void onBindViewHolder(PushManagerViewHolder pushManagerViewHolder, int i) {
            final PushClassifyModel.PushClassifyItem pushClassifyItem = this.d.get(i);
            pushManagerViewHolder.itemTitle.setText(pushClassifyItem.title);
            pushManagerViewHolder.itemDesc.setText(pushClassifyItem.desc);
            pushManagerViewHolder.btnSwitch.setChecked(pushClassifyItem.defaultStatus);
            pushManagerViewHolder.btnSwitch.setOnCheckedChangedListener(new SlidingButton.OnCheckedChangedListener() {
                public void a(SlidingButton slidingButton, boolean z) {
                    if (z || (!z && pushClassifyItem.enableclose)) {
                        pushClassifyItem.defaultStatus = z;
                        PushClassifyModel.changeKeyChecked(PushManagerAdapter.this.c, pushClassifyItem.key, z);
                        return;
                    }
                    slidingButton.setChecked(!z);
                    MiToast.a(PushManagerAdapter.this.c, (CharSequence) PushManagerAdapter.this.c.getResources().getString(R.string.push_type_cannot_closed), 3000);
                }
            });
        }

        /* renamed from: a */
        public PushManagerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new PushManagerViewHolder(LayoutInflater.from(this.c).inflate(R.layout.shop_push_manager_item, viewGroup, false));
        }

        public int getItemCount() {
            return this.d.size();
        }

        static class PushManagerViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131493039)
            SlidingButton btnSwitch;
            @BindView(2131493511)
            CustomTextView itemDesc;
            @BindView(2131493523)
            CustomTextView itemTitle;

            PushManagerViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_push_manager_activity);
        ButterKnife.bind((Activity) this);
        setTitle((CharSequence) getString(R.string.account_my_push_manager));
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PushManagerActivity.this.onBackPressed();
            }
        });
        findViewById(R.id.title_bar_cart_view).setVisibility(4);
        initView();
    }

    public void initView() {
        this.pushRecycleView.setLayoutManager(new LinearLayoutManager(this));
        this.f5417a = new PushManagerAdapter(this);
        this.pushRecycleView.addItemDecoration(new RecycleViewDivider(this, 0, Coder.a(0.5f), getResources().getColor(R.color.divider_color)));
        this.pushRecycleView.setAdapter(this.f5417a);
        Gson gson = new Gson();
        try {
            this.b = (ArrayList) gson.fromJson(Utils.Preference.getStringPref(ShopApp.g(), "pref_key_push_classify_data", ""), new TypeToken<ArrayList<PushClassifyModel.PushClassifyItem>>() {
            }.getType());
            this.c = (ArrayList) gson.fromJson(Utils.Preference.getStringPref(ShopApp.g(), "pref_key_push_classify_key", ""), new TypeToken<ArrayList<PushClassifyModel.PushClassifyItem>>() {
            }.getType());
        } catch (Exception e) {
            MiToast.a((Context) this, (CharSequence) getString(R.string.shop_error_network), 3000);
            CrashReport.postCrash(Thread.currentThread(), (Throwable) e);
            finish();
        }
        if (this.b != null && this.b.size() > 0 && this.c != null && this.c.size() > 0) {
            Iterator<PushClassifyModel.PushClassifyItem> it = this.b.iterator();
            while (it.hasNext()) {
                PushClassifyModel.PushClassifyItem next = it.next();
                Iterator<PushClassifyModel.PushClassifyItem> it2 = this.c.iterator();
                while (it2.hasNext()) {
                    PushClassifyModel.PushClassifyItem next2 = it2.next();
                    if (next.key.equals(next2.key)) {
                        next.defaultStatus = next2.defaultStatus;
                    }
                }
            }
        }
        this.f5417a.c(this.b);
        this.f5417a.a(this.b);
    }

    public void onBackPressed() {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.y()).buildUpon();
        buildUpon.appendQueryParameter("watchTypes", this.f5417a.a());
        buildUpon.appendQueryParameter("unwatchTypes", this.f5417a.b());
        buildUpon.appendQueryParameter("syncUnwatchTypes", this.f5417a.c());
        AnonymousClass4 r1 = new SimpleCallback<NewSimpleResult>() {
            public void a(NewSimpleResult newSimpleResult) {
                PushManagerActivity.this.hideLoading();
                PushManagerActivity.this.doSuperBack();
            }

            public void a(String str) {
                super.a(str);
                LogUtil.b(PushManagerActivity.TAG, "PushManagerActivity Exception:" + str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewSimpleResult.class, r1);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewSimpleResult.class, r1);
        }
        request.setTag(TAG);
        RequestQueueUtil.a().add(request);
        showLoading();
    }

    public void doSuperBack() {
        super.onBackPressed();
    }
}
