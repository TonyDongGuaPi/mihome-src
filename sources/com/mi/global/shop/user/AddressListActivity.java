package com.mi.global.shop.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.adapter.user.AddressListAdapter;
import com.mi.global.shop.newmodel.NewSimpleResult;
import com.mi.global.shop.newmodel.user.address.NewAddressItem;
import com.mi.global.shop.newmodel.user.address.NewAddressResult;
import com.mi.global.shop.newmodel.user.address.NewRegionItem;
import com.mi.global.shop.newmodel.user.address.NewSetDefaultAddressResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.dialog.CustomCancelDialog;
import com.mi.log.LogUtil;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class AddressListActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final int ADDRESS_LIST_EDIT_CODE = 100;

    /* renamed from: a  reason: collision with root package name */
    private static final String f7019a = "AddressListActivity";
    private String b;
    /* access modifiers changed from: private */
    public ArrayList<NewAddressItem> c;
    /* access modifiers changed from: private */
    public ArrayList<NewRegionItem> d;
    private String e;
    private NewAddressItem f;
    /* access modifiers changed from: private */
    public String g;
    private ListView h;
    private AddressListAdapter i;
    private View j;
    private CustomButtonView k;
    public ProgressDialog mProgressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_address_list);
        setTitle(R.string.user_address_title);
        this.mCartView.setVisibility(8);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(this);
        Intent intent = getIntent();
        this.b = intent.getStringExtra("com.mi.global.shop.extra_user_address_type");
        if (TextUtils.isEmpty(this.b)) {
            LogUtil.b(f7019a, "addressType is null");
            finish();
        }
        String str = f7019a;
        LogUtil.b(str, "get addressType:" + this.b);
        this.j = findViewById(R.id.empty_view);
        this.h = (ListView) findViewById(R.id.address_list);
        this.h.setOnItemClickListener(this);
        this.i = new AddressListAdapter(this, this.b);
        this.h.setAdapter(this.i);
        this.k = (CustomButtonView) findViewById(R.id.add_address_btn);
        this.k.setOnClickListener(this);
        Utils.Preference.setStringPref(ShopApp.g(), "pref_address", "");
        if (this.b.equalsIgnoreCase("address_manage")) {
            b();
        }
        if (this.b.equalsIgnoreCase("address_choose")) {
            a(intent);
            a();
            if (this.c != null && this.c.size() == 0) {
                onNewAddress();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.c != null && (!TextUtils.isEmpty(this.e) || !TextUtils.isEmpty(this.g))) {
            Iterator<NewAddressItem> it = this.c.iterator();
            while (it.hasNext()) {
                NewAddressItem next = it.next();
                if (!TextUtils.isEmpty(this.e)) {
                    if (next.address_id.equalsIgnoreCase(this.e)) {
                        next.selected = true;
                    } else {
                        next.selected = false;
                    }
                }
                if (!TextUtils.isEmpty(this.g)) {
                    if (next.address_id.equalsIgnoreCase(this.g)) {
                        next.is_default = NewAddressItem.DEFAULT_ADDRESS;
                    } else {
                        next.is_default = NewAddressItem.OTHER_ADDRESS;
                    }
                }
            }
        }
        this.i.c();
        this.i.a(this.c);
        if (this.c == null || this.c.size() == 0) {
            this.j.setVisibility(0);
        } else {
            this.j.setVisibility(8);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        String str = f7019a;
        LogUtil.b(str, "onItemClick, position:" + i2);
        if (adapterView == this.h) {
            Object itemAtPosition = adapterView.getItemAtPosition(i2);
            if (itemAtPosition instanceof NewAddressItem) {
                NewAddressItem newAddressItem = (NewAddressItem) itemAtPosition;
                String str2 = f7019a;
                LogUtil.b(str2, "click add id:" + newAddressItem.address_id);
                if (this.b.equalsIgnoreCase("address_manage")) {
                    gotoEditAddress(newAddressItem);
                }
                if (this.b.equalsIgnoreCase("address_choose")) {
                    for (int i3 = 0; i3 < this.c.size(); i3++) {
                        if (this.c.get(i3).address_id.equalsIgnoreCase(newAddressItem.address_id)) {
                            this.c.get(i3).selected = true;
                        } else {
                            this.c.get(i3).selected = false;
                        }
                    }
                    this.i.a(this.c);
                    this.i.notifyDataSetChanged();
                    returnOK(newAddressItem);
                }
            }
        }
    }

    public void gotoEditAddress(NewAddressItem newAddressItem) {
        if (newAddressItem != null || !TextUtils.isEmpty(newAddressItem.address_id)) {
            Intent intent = new Intent(this, AddressEditActivity.class);
            intent.putExtra("address_item", newAddressItem);
            intent.putExtra("com.mi.global.shop.extra_user_address_type", this.b);
            intent.putParcelableArrayListExtra("region_list", this.d);
            startActivityForResult(intent, 100);
        }
    }

    public void setDefaultAddress(final String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.W()).buildUpon();
            buildUpon.appendQueryParameter("address_id", str);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewSetDefaultAddressResult.class, new SimpleCallback<NewSetDefaultAddressResult>() {
                public void a(NewSetDefaultAddressResult newSetDefaultAddressResult) {
                    if (AddressListActivity.this.mProgressDialog != null) {
                        AddressListActivity.this.mProgressDialog.dismiss();
                    }
                    if (newSetDefaultAddressResult.data != null && newSetDefaultAddressResult.data.data) {
                        MiToast.a((Context) AddressListActivity.this, R.string.user_address_default_success, 0);
                        String unused = AddressListActivity.this.g = str;
                        AddressListActivity.this.a();
                    }
                }

                public void a(String str) {
                    super.a(str);
                    if (AddressListActivity.this.mProgressDialog != null) {
                        AddressListActivity.this.mProgressDialog.dismiss();
                    }
                }
            });
            simpleProtobufRequest.setTag(f7019a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
            if (this.mProgressDialog == null) {
                this.mProgressDialog = new ProgressDialog(this);
                this.mProgressDialog.setMessage(getString(R.string.please_wait));
                this.mProgressDialog.setIndeterminate(true);
                this.mProgressDialog.setCancelable(false);
            }
            this.mProgressDialog.show();
        }
    }

    public void returnOK(NewAddressItem newAddressItem) {
        Intent intent = new Intent();
        intent.putExtra("address_id", newAddressItem.address_id);
        intent.putExtra("name", newAddressItem.consignee);
        intent.putExtra("zipcode", newAddressItem.zipcode);
        intent.putExtra("tel", newAddressItem.tel);
        intent.putExtra("can_cod", newAddressItem.can_cod + "");
        intent.putExtra("limit", newAddressItem.limit);
        intent.putExtra("limit_cod", newAddressItem.limit_cod);
        intent.putExtra("address", newAddressItem.addr_india.addr);
        intent.putExtra("city", newAddressItem.addr_india.city);
        intent.putExtra("state", newAddressItem.city.name);
        intent.putExtra("city_id", newAddressItem.city.id);
        intent.putExtra("landmark", newAddressItem.addr_india.landmark);
        intent.putExtra("is_invalid", newAddressItem.is_invalid + "");
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void a(Intent intent) {
        this.e = intent.getStringExtra("address_id");
        String stringExtra = intent.getStringExtra("address_list");
        String stringExtra2 = intent.getStringExtra("region_list");
        Gson gson = new Gson();
        this.c = (ArrayList) gson.fromJson(stringExtra, new TypeToken<ArrayList<NewAddressItem>>() {
        }.getType());
        this.d = (ArrayList) gson.fromJson(stringExtra2, new TypeToken<ArrayList<NewRegionItem>>() {
        }.getType());
    }

    private void b() {
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.Q(), NewAddressResult.class, new SimpleCallback<NewAddressResult>() {
            public void a(NewAddressResult newAddressResult) {
                if (AddressListActivity.this.mProgressDialog != null) {
                    AddressListActivity.this.mProgressDialog.dismiss();
                }
                ArrayList unused = AddressListActivity.this.c = newAddressResult.data.list;
                ArrayList unused2 = AddressListActivity.this.d = newAddressResult.data.region;
                AddressListActivity.this.a();
            }

            public void a(String str) {
                super.a(str);
                if (AddressListActivity.this.mProgressDialog != null) {
                    AddressListActivity.this.mProgressDialog.dismiss();
                }
                AddressListActivity.this.finish();
            }
        });
        simpleProtobufRequest.setTag(f7019a);
        RequestQueueUtil.a().add(simpleProtobufRequest);
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setMessage(getString(R.string.please_wait));
            this.mProgressDialog.setIndeterminate(true);
            this.mProgressDialog.setCancelable(false);
        }
        this.mProgressDialog.show();
    }

    private void c() {
        MiToast.a((Context) this, R.string.shop_error_network, 0);
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        setResult(0);
        finish();
        LogUtil.b(f7019a, "JSON parse error");
    }

    private void d() {
        MiToast.a((Context) this, R.string.shop_error_network, 0);
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        LogUtil.b(f7019a, "network error");
    }

    public void removeAddressList(String str) {
        if (!TextUtils.isEmpty(str) && this.c != null) {
            if (str.equals(this.g)) {
                this.g = "";
            }
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                if (str.equalsIgnoreCase(this.c.get(i2).address_id)) {
                    this.c.remove(i2);
                    a();
                    e();
                    return;
                }
            }
        }
    }

    public void updateAddressItem(NewAddressItem newAddressItem) {
        if (this.c != null && newAddressItem != null) {
            if (newAddressItem.is_default == NewAddressItem.DEFAULT_ADDRESS) {
                this.g = newAddressItem.address_id;
            }
            if (!TextUtils.isEmpty(this.e) && this.e.equalsIgnoreCase(newAddressItem.address_id)) {
                this.f = newAddressItem;
            }
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                if (newAddressItem.address_id.equalsIgnoreCase(this.c.get(i2).address_id)) {
                    this.c.remove(i2);
                    this.c.add(i2, newAddressItem);
                    a();
                    e();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri.Builder buildUpon = Uri.parse(ConnectionHelper.V()).buildUpon();
            buildUpon.appendQueryParameter("addressId", str);
            SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewSimpleResult.class, new SimpleCallback<NewSimpleResult>() {
                public void a(NewSimpleResult newSimpleResult) {
                    if (AddressListActivity.this.mProgressDialog != null) {
                        AddressListActivity.this.mProgressDialog.dismiss();
                    }
                    AddressListActivity.this.removeAddressList(str);
                }

                public void a(String str) {
                    super.a(str);
                    if (AddressListActivity.this.mProgressDialog != null) {
                        AddressListActivity.this.mProgressDialog.dismiss();
                    }
                }
            });
            simpleProtobufRequest.setTag(f7019a);
            RequestQueueUtil.a().add(simpleProtobufRequest);
            if (this.mProgressDialog == null) {
                this.mProgressDialog = new ProgressDialog(this);
                this.mProgressDialog.setMessage(getString(R.string.please_wait));
                this.mProgressDialog.setIndeterminate(true);
                this.mProgressDialog.setCancelable(false);
            }
            this.mProgressDialog.show();
        }
    }

    public void delAddressDialog(final String str) {
        CustomCancelDialog.Builder builder = new CustomCancelDialog.Builder(this);
        builder.a(getString(R.string.user_address_delpromote)).a((Boolean) true).a(getString(R.string.user_address_confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AddressListActivity.this.a(str);
            }
        }).b(getString(R.string.user_address_cancel), (DialogInterface.OnClickListener) null);
        builder.a().show();
    }

    public void addAddressItem(final NewAddressItem newAddressItem) {
        if (this.c != null && newAddressItem != null) {
            if (newAddressItem.is_default == NewAddressItem.DEFAULT_ADDRESS) {
                this.g = newAddressItem.address_id;
            }
            this.c.add(0, newAddressItem);
            a();
            e();
            if (this.b.equalsIgnoreCase("address_choose")) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        AddressListActivity.this.returnOK(newAddressItem);
                    }
                });
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.add_address_btn) {
            LogUtil.b(f7019a, "clicked new item");
            onNewAddress();
        }
        if (view.getId() == R.id.title_bar_back) {
            onBackPressed();
        }
    }

    public void onBackPressed() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        if (this.f == null) {
            setResult(0);
            finish();
            return;
        }
        returnOK(this.f);
    }

    private void e() {
        if (this.c != null) {
            Utils.Preference.setStringPref(ShopApp.g(), "pref_address", new Gson().toJson((Object) this.c));
        }
    }

    public void onNewAddress() {
        LogUtil.b(f7019a, "onCreateAddress:");
        Intent intent = new Intent(this, AddressEditActivity.class);
        intent.putExtra("com.mi.global.shop.extra_user_address_type", this.b);
        intent.putParcelableArrayListExtra("region_list", this.d);
        startActivityForResult(intent, 100);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && intent != null && i3 == -1) {
            updateAddressItem((NewAddressItem) intent.getParcelableExtra("update_item"));
            addAddressItem((NewAddressItem) intent.getParcelableExtra("add_item"));
            removeAddressList(intent.getStringExtra("delete_item_id"));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }
        super.onDestroy();
    }
}
