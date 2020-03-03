package com.xiaomi.smarthome.newui.amappoi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmapPoiActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static final String INTENT_KEY_AMAP_LOCATION = "map_location";
    public static final String INTENT_KEY_AMAP_RESULT = "map_location";

    /* renamed from: a  reason: collision with root package name */
    private ImageView f20438a;
    private ListView b;
    /* access modifiers changed from: private */
    public LinearLayout c;
    /* access modifiers changed from: private */
    public PoiAdapter d;
    private LocationBean e;
    private XQProgressDialog f;
    private EditText g;
    private PublishSubject<String> h;
    private CompositeDisposable i;
    /* access modifiers changed from: private */
    public AMapLocation j;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_amap_poi_layout);
        Intent intent = getIntent();
        if (intent != null) {
            this.j = (AMapLocation) intent.getParcelableExtra("map_location");
        }
        if (this.j == null) {
            finish();
        } else {
            a(bundle);
        }
    }

    private void a(Bundle bundle) {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.search_loc);
        this.c = (LinearLayout) findViewById(R.id.ll_poi);
        this.f20438a = (ImageView) findViewById(R.id.module_a_3_return_btn);
        this.f20438a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AmapPoiActivity.this.finish();
            }
        });
        this.b = (ListView) findViewById(R.id.lv_data);
        this.d = new PoiAdapter(this);
        this.b.setOnItemClickListener(this);
        this.b.setAdapter(this.d);
        this.g = (EditText) findViewById(R.id.search_et);
        this.h = PublishSubject.create();
        this.h.debounce(400, TimeUnit.MILLISECONDS).filter(new Predicate<String>() {
            /* renamed from: a */
            public boolean test(String str) throws Exception {
                return str.length() >= 0;
            }
        }).switchMap(new Function<String, ObservableSource<List<LocationBean>>>() {
            /* renamed from: a */
            public ObservableSource<List<LocationBean>> apply(String str) throws Exception {
                return AmapPoiActivity.this.b(str);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<List<LocationBean>>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            /* renamed from: a */
            public void onNext(List<LocationBean> list) {
                AmapPoiActivity.this.d.a(list);
                AmapPoiActivity.this.d.notifyDataSetChanged();
            }
        });
        this.i = new CompositeDisposable();
        this.i.add(this.i);
        this.g.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AmapPoiActivity.this.a(charSequence.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.h != null) {
            this.h.onNext(str);
        }
    }

    /* access modifiers changed from: private */
    public Observable<List<LocationBean>> b(final String str) {
        return Observable.create(new ObservableOnSubscribe<List<LocationBean>>() {
            public void subscribe(ObservableEmitter<List<LocationBean>> observableEmitter) throws Exception {
                LogUtil.a("SearchActivity", "开始请求，关键词为：" + str);
                ArrayList<LocationBean> arrayList = new ArrayList<>();
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str.trim())) {
                    arrayList = PoiSearchTask.a(AmapPoiActivity.this).a(str, "", AmapPoiActivity.this.j.getLatitude(), AmapPoiActivity.this.j.getLongitude(), AmapPoiActivity.this.j);
                    if (!AmapPoiActivity.this.isValid()) {
                        observableEmitter.onComplete();
                        return;
                    }
                }
                LogUtil.a("SearchActivity", "结束请求，关键词为：" + str);
                if (arrayList != null) {
                    observableEmitter.onNext(arrayList);
                }
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        if (adapterView.getId() == R.id.lv_data) {
            Intent intent = new Intent();
            intent.putExtra("map_location", (LocationBean) this.d.getItem(i2));
            setResult(-1, intent);
            finish();
        }
    }

    private void a() {
        this.f = new XQProgressDialog(this);
        this.f.setCancelable(false);
        this.f.setMessage(getResources().getString(R.string.loading));
        this.f.show();
        this.f.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    public void dismissLoadingDialog() {
        if (this.f != null) {
            this.f.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.i.clear();
    }

    class PoiAdapter extends BaseAdapter {
        private List<LocationBean> b = new ArrayList();

        public long getItemId(int i) {
            return (long) i;
        }

        public PoiAdapter(Context context) {
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = AmapPoiActivity.this.getLayoutInflater().inflate(R.layout.app_list_item_poi, viewGroup, false);
                viewHolder.f20447a = (TextView) view2.findViewById(R.id.address);
                viewHolder.b = (TextView) view2.findViewById(R.id.desc);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            LocationBean locationBean = (LocationBean) getItem(i);
            if (TextUtils.isEmpty(locationBean.c())) {
                viewHolder.f20447a.setVisibility(8);
            } else {
                viewHolder.f20447a.setVisibility(0);
                viewHolder.f20447a.setText(locationBean.c());
            }
            viewHolder.b.setText(locationBean.d());
            return view2;
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            public TextView f20447a;
            public TextView b;

            private ViewHolder() {
            }
        }

        public void a(List<LocationBean> list) {
            AmapPoiActivity.this.dismissLoadingDialog();
            this.b = list;
            if (list.size() > 0) {
                AmapPoiActivity.this.c.setVisibility(0);
                return;
            }
            ToastUtil.a((Context) AmapPoiActivity.this, (int) R.string.f13398no, 0);
            AmapPoiActivity.this.c.setVisibility(8);
        }
    }
}
