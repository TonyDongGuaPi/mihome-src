package com.xiaomi.smarthome.family;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.miio.db.record.FamilyRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.share.ShareActivity;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ShareFamilyActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ListView f15754a;
    /* access modifiers changed from: private */
    public List<FamilyRecord> b = new ArrayList();
    /* access modifiers changed from: private */
    public CustomAdapter c;
    private String d;
    private ArrayList<String> e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public boolean g = false;
    XQProgressDialog mProgressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.d = extras.getString("did");
        }
        setContentView(R.layout.share_family_device);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        startQuery();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.g = false;
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.f = findViewById(R.id.empty_add_familey);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ShareFamilyActivity.this, FamilyActivity.class);
                ShareFamilyActivity.this.startActivity(intent);
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.share_to_family);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareFamilyActivity.this.finish();
                STAT.d.av();
            }
        });
        this.f15754a = (ListView) findViewById(R.id.faimlly_list_view);
        this.c = new CustomAdapter(this, this.d);
        this.f15754a.setAdapter(this.c);
        this.mProgressDialog = new XQProgressDialog(this);
        this.mProgressDialog.setCancelable(false);
        this.mProgressDialog.setMessage(getResources().getString(R.string.loading_share_info));
    }

    private void a(List<String> list) {
        RemoteFamilyApi.a().a((Context) this, (List<String>) this.e, list, "", (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Toast.makeText(ShareFamilyActivity.this, R.string.sh_share_request_success, 0).show();
                ShareFamilyActivity.this.finish();
            }

            public void onFailure(Error error) {
                if (error.a() == ErrorCode.ERROR_MAXIMAL_SHARE_REQUEST.getCode()) {
                    new MLAlertDialog.Builder(ShareFamilyActivity.this).b((CharSequence) ShareFamilyActivity.this.getResources().getString(R.string.sh_share_fremax_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).b().show();
                } else if (error.a() == ErrorCode.ERROR_FEQUENT_REQUEST.getCode()) {
                    new MLAlertDialog.Builder(ShareFamilyActivity.this).b((CharSequence) ShareFamilyActivity.this.getResources().getString(R.string.sh_share_frequent_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).b().show();
                } else {
                    Toast.makeText(ShareFamilyActivity.this, R.string.sh_share_request_fail, 0).show();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void startQuery() {
        this.f.setVisibility(8);
        this.mProgressDialog.show();
        RemoteFamilyApi.a().b((Context) this, (AsyncCallback<List<FamilyRecord>, Error>) new AsyncCallback<List<FamilyRecord>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<FamilyRecord> list) {
                if (!ShareFamilyActivity.this.g) {
                    boolean unused = ShareFamilyActivity.this.g = true;
                    STAT.c.a(list.size());
                }
                if (ShareFamilyActivity.this.isValid()) {
                    ShareFamilyActivity.this.b.clear();
                    for (int size = list.size() - 1; size >= 0; size--) {
                        if (list.get(size).status.equals("0")) {
                            list.remove(size);
                        }
                    }
                    ShareFamilyActivity.this.b.addAll(list);
                    if (ShareFamilyActivity.this.b.size() == 0) {
                        ShareFamilyActivity.this.f15754a.setVisibility(8);
                        ShareFamilyActivity.this.f.setVisibility(0);
                    } else {
                        ShareFamilyActivity.this.f15754a.setVisibility(0);
                        ShareFamilyActivity.this.c.a((List<FamilyRecord>) ShareFamilyActivity.this.b);
                        ShareFamilyActivity.this.f.setVisibility(8);
                    }
                    ShareFamilyActivity.this.f15754a.postDelayed(new Runnable() {
                        public void run() {
                            if (ShareFamilyActivity.this.mProgressDialog != null) {
                                ShareFamilyActivity.this.mProgressDialog.dismiss();
                            }
                        }
                    }, 800);
                }
            }

            public void onFailure(Error error) {
                if (ShareFamilyActivity.this.isValid()) {
                    ShareFamilyActivity.this.f.setVisibility(8);
                    ShareFamilyActivity.this.mProgressDialog.dismiss();
                }
            }
        });
    }

    static class CustomAdapter extends BaseAdapter {
        private static final String e = "CustomAdapter";

        /* renamed from: a  reason: collision with root package name */
        private List<FamilyRecord> f15762a = null;
        /* access modifiers changed from: private */
        public Context b;
        private String c;
        private final String[] d;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public CustomAdapter(Context context, String str) {
            this.b = context;
            this.c = str;
            this.d = this.b.getResources().getStringArray(R.array.family_memeber_names);
        }

        public void a(List<FamilyRecord> list) {
            this.f15762a = list;
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.f15762a == null) {
                return 0;
            }
            return this.f15762a.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            String str;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.share_device_user_item, viewGroup, false);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CustomAdapter.this.a(i);
                }
            });
            view.findViewById(R.id.ckb_edit_selected).setVisibility(8);
            UserMamanger.a().b(this.f15762a.get(i).url, (SimpleDraweeView) view.findViewById(R.id.icon), new CircleAvatarProcessor());
            if (TextUtils.isEmpty(this.f15762a.get(i).tUserId)) {
                ((TextView) view.findViewById(R.id.id)).setText(R.string.no_invite);
            } else if (Integer.valueOf(this.f15762a.get(i).status).intValue() == 0) {
                String str2 = this.f15762a.get(i).relationship;
                if (str2.startsWith("1000_")) {
                    str2 = str2.substring("1000_".length());
                }
                if (Integer.valueOf(this.f15762a.get(i).relation_id).intValue() < 0) {
                    str = this.b.getString(R.string.family_accept_title);
                } else {
                    str = str2 + ", " + this.b.getString(R.string.family_wait_accept);
                }
                ((TextView) view.findViewById(R.id.id)).setText(str);
            } else {
                ((TextView) view.findViewById(R.id.id)).setText(FamilyUtils.a(this.b, this.d, this.f15762a.get(i)));
            }
            ((TextView) view.findViewById(R.id.name)).setText(this.f15762a.get(i).nickName);
            if (i != getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            }
            return view;
        }

        /* access modifiers changed from: private */
        public void a(int i) {
            STAT.d.aw();
            Intent intent = new Intent();
            intent.setClass(this.b, ShareActivity.class);
            FamilyRecord familyRecord = this.f15762a.get(i);
            if (familyRecord == null || TextUtils.isEmpty(this.c)) {
                LogUtil.a(e, "shareFamily: did is empty error");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("did", this.c);
            bundle.putString("share_type", "");
            bundle.putInt(ShareActivity.ARG_KEYS_FROM, ShareActivity.SHARE_FROM_FAMILY);
            UserInfo userInfo = new UserInfo();
            userInfo.f16462a = familyRecord.tUserId;
            userInfo.e = familyRecord.nickName;
            userInfo.c = familyRecord.url;
            bundle.putParcelable(ShareActivity.ARGS_KEY_USER_INFO, userInfo);
            intent.putExtras(bundle);
            this.b.startActivity(intent);
        }

        /* access modifiers changed from: package-private */
        public void a(final List<String> list, List<String> list2) {
            RemoteFamilyApi.a().a(this.b, list2, list, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    for (String jSONObject : list) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str).getJSONObject(jSONObject);
                            if (jSONObject2.isNull("error")) {
                                Toast.makeText(CustomAdapter.this.b, R.string.sh_share_request_success, 0).show();
                            } else if ("REPEATED".equals(jSONObject2.getString("error"))) {
                                Toast.makeText(CustomAdapter.this.b, R.string.sh_share_repeated_request, 0).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (error.a() == ErrorCode.ERROR_MAXIMAL_SHARE_REQUEST.getCode()) {
                        new MLAlertDialog.Builder(CustomAdapter.this.b).b((CharSequence) CustomAdapter.this.b.getResources().getString(R.string.sh_share_fremax_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).b().show();
                    } else if (error.a() == ErrorCode.ERROR_FEQUENT_REQUEST.getCode()) {
                        new MLAlertDialog.Builder(CustomAdapter.this.b).b((CharSequence) CustomAdapter.this.b.getResources().getString(R.string.sh_share_frequent_request)).a((int) R.string.sh_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).b().show();
                    } else {
                        Toast.makeText(CustomAdapter.this.b, R.string.sh_share_request_fail, 0).show();
                    }
                }
            });
        }
    }
}
