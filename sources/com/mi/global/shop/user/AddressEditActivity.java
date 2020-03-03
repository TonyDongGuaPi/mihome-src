package com.mi.global.shop.user;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.activity.BaseActivity;
import com.mi.global.shop.newmodel.user.address.NewAddressItem;
import com.mi.global.shop.newmodel.user.address.NewRegionItem;
import com.mi.global.shop.newmodel.user.address.NewSaveAddressResult;
import com.mi.global.shop.newmodel.user.address.NewZipCodeData;
import com.mi.global.shop.newmodel.user.address.NewZipCodeResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.SlidingButton;
import com.mi.global.shop.widget.dialog.CustomCancelDialog;
import com.mi.log.LogUtil;
import com.mi.util.RequestQueueUtil;
import com.miui.tsmclient.entity.CardInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class AddressEditActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "AddressEditActivity";

    /* renamed from: a  reason: collision with root package name */
    private ArrayAdapter<String> f7011a;
    @BindView(2131494311)
    SlidingButton addressDefaultSwitch;
    @BindView(2131494308)
    CustomEditTextView addressEdit;
    String addressType;
    @BindView(2131494307)
    View addressView;
    private String[] b;
    private String c;
    @BindView(2131494310)
    CustomEditTextView cityEdit;
    @BindView(2131494309)
    View cityView;
    /* access modifiers changed from: private */
    public String d;
    @BindView(2131493253)
    View defaultAddressView;
    /* access modifiers changed from: private */
    public NewAddressItem e;
    @BindView(2131494313)
    CustomEditTextView emailEdit;
    @BindView(2131494312)
    View emailView;
    /* access modifiers changed from: private */
    public ArrayList<NewRegionItem> f;
    /* access modifiers changed from: private */
    public ProgressDialog g;
    private TextWatcher h = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.length() >= 6 && i == 5 && i2 == 0 && i3 == 1) {
                LogUtil.b(AddressEditActivity.TAG, "manual input.clear foucs");
                if (AddressEditActivity.this.pincodeEdit != null) {
                    AddressEditActivity.this.pincodeEdit.clearFocus();
                }
                if (AddressEditActivity.this.addressEdit != null) {
                    AddressEditActivity.this.addressEdit.requestFocus();
                }
            }
        }
    };
    @BindView(2131494315)
    CustomEditTextView landmarkEdit;
    @BindView(2131494314)
    View landmarkView;
    @BindView(2131494317)
    CustomEditTextView nameEdit;
    @BindView(2131494316)
    View nameView;
    @BindView(2131494319)
    CustomEditTextView phoneEdit;
    @BindView(2131494318)
    View phoneView;
    @BindView(2131494321)
    CustomEditTextView pincodeEdit;
    @BindView(2131494320)
    View pincodeView;
    @BindView(2131494322)
    CustomTextView pincodeWarningView;
    @BindView(2131494323)
    CustomButtonView saveBtn;
    @BindView(2131494325)
    Spinner stateSpinner;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_address_edit);
        ButterKnife.bind((Activity) this);
        this.mCartView.setVisibility(8);
        this.mBackView.setVisibility(0);
        this.mBackView.setOnClickListener(this);
        this.e = (NewAddressItem) getIntent().getParcelableExtra("address_item");
        this.f = getIntent().getParcelableArrayListExtra("region_list");
        this.addressType = getIntent().getStringExtra("com.mi.global.shop.extra_user_address_type");
        if (this.e == null) {
            setTitle(R.string.user_address_add);
        } else {
            setTitle(R.string.user_address_edit);
        }
        this.nameView.setOnClickListener(this);
        this.pincodeView.setOnClickListener(this);
        this.addressView.setOnClickListener(this);
        this.cityView.setOnClickListener(this);
        this.landmarkView.setOnClickListener(this);
        this.emailView.setOnClickListener(this);
        this.phoneView.setOnClickListener(this);
        this.saveBtn.setOnClickListener(this);
        if (this.e == null || this.e.is_default != 1) {
            this.addressDefaultSwitch.setChecked(false);
            this.defaultAddressView.setVisibility(0);
        } else {
            this.addressDefaultSwitch.setChecked(true);
            this.defaultAddressView.setVisibility(8);
        }
        this.pincodeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    String obj = AddressEditActivity.this.pincodeEdit.getText().toString();
                    if (!TextUtils.isEmpty(obj) && obj.length() == 6) {
                        AddressEditActivity.this.a(obj);
                    }
                }
            }
        });
        this.pincodeEdit.addTextChangedListener(this.h);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.pincodeWarningView.setVisibility(8);
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.Y()).buildUpon();
        buildUpon.appendQueryParameter("zipcode", str);
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(buildUpon.toString(), NewZipCodeResult.class, new SimpleCallback<NewZipCodeResult>() {
            public void a(NewZipCodeResult newZipCodeResult) {
                AddressEditActivity.this.a(newZipCodeResult.data);
            }

            public void a(String str) {
                AddressEditActivity.this.pincodeWarningView.setVisibility(0);
                AddressEditActivity.this.saveBtn.setEnabled(false);
            }
        });
        simpleProtobufRequest.setTag(TAG);
        RequestQueueUtil.a().add(simpleProtobufRequest);
    }

    /* access modifiers changed from: private */
    public void a(NewZipCodeData newZipCodeData) {
        if (newZipCodeData != null) {
            this.d = newZipCodeData.parent_id;
            this.c = newZipCodeData.region_id;
            String str = newZipCodeData.citys;
            if (!TextUtils.isEmpty(str)) {
                this.cityEdit.setText(str);
            }
            if (!TextUtils.isEmpty(this.d)) {
                String str2 = null;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= this.f.size()) {
                        break;
                    } else if (this.f.get(i2).region_id.equalsIgnoreCase(this.d)) {
                        str2 = this.f.get(i2).region_name;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (!TextUtils.isEmpty(str2)) {
                    while (true) {
                        if (i >= this.b.length) {
                            break;
                        } else if (this.b[i].equalsIgnoreCase(str2)) {
                            this.stateSpinner.setSelection(i);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
            this.pincodeWarningView.setVisibility(8);
            this.saveBtn.setEnabled(true);
        }
    }

    private void a() {
        LogUtil.b(TAG, "updateView");
        NewAddressItem newAddressItem = this.e;
        int i = 0;
        if (this.f != null) {
            String str = TAG;
            LogUtil.b(str, "update regionList:" + this.f.size());
            this.b = new String[(this.f.size() + 1)];
            this.b[0] = "";
            for (int i2 = 1; i2 < this.b.length; i2++) {
                this.b[i2] = this.f.get(i2 - 1).region_name;
            }
            if (this.b.length > 1) {
                Arrays.sort(this.b, 1, this.b.length);
            }
            this.f7011a = new ArrayAdapter<>(ShopApp.g(), R.layout.shop_buy_confirm_payment_spinner_default_item, this.b);
            this.f7011a.setDropDownViewResource(R.layout.shop_buy_confirm_payment_spinneritem);
            this.stateSpinner.setAdapter(this.f7011a);
            this.stateSpinner.setOnItemSelectedListener(new StateSelectedListener());
        }
        if (newAddressItem != null) {
            LogUtil.b(TAG, "fill address");
            this.nameEdit.setText(newAddressItem.consignee);
            this.pincodeEdit.setText(newAddressItem.zipcode);
            this.addressEdit.setText(newAddressItem.addr_india.addr);
            this.landmarkEdit.setText(newAddressItem.addr_india.landmark);
            this.cityEdit.setText(newAddressItem.addr_india.city);
            this.emailEdit.setText(newAddressItem.email);
            this.phoneEdit.setText(newAddressItem.tel);
            if (!TextUtils.isEmpty(newAddressItem.city.name)) {
                while (true) {
                    if (i >= this.b.length) {
                        break;
                    } else if (this.b[i].equalsIgnoreCase(newAddressItem.city.name)) {
                        this.stateSpinner.setSelection(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        LogUtil.b(TAG, "updateView finish");
    }

    private void b() {
        LogUtil.b(TAG, "clearView");
        this.nameEdit.setText("");
        this.pincodeEdit.setText("");
        this.addressEdit.setText("");
        this.cityEdit.setText("");
        this.landmarkEdit.setText("");
        this.emailEdit.setText("");
        this.phoneEdit.setText("");
    }

    private void c() {
        LogUtil.b(TAG, "clearWarning");
        this.pincodeWarningView.setVisibility(8);
    }

    private void a(int i) {
        Toast makeText = Toast.makeText(this, i, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    private boolean d() {
        c();
        String obj = this.nameEdit.getText().toString();
        if (TextUtils.isEmpty(obj) || obj.length() < 2) {
            this.nameEdit.requestFocus();
            a(R.string.user_address_namewarning);
            return false;
        } else if (!this.pincodeEdit.getText().toString().matches("^[0-9]{6}$")) {
            this.pincodeEdit.requestFocus();
            this.pincodeWarningView.setVisibility(0);
            a(R.string.user_address_pincodewarning);
            return false;
        } else {
            String obj2 = this.addressEdit.getText().toString();
            if (TextUtils.isEmpty(obj2) || obj2.length() < 5) {
                this.addressEdit.requestFocus();
                a(R.string.user_address_addresswarning);
                return false;
            }
            String obj3 = this.emailEdit.getText().toString();
            if (!obj3.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$") && !obj3.contains("*")) {
                this.emailEdit.requestFocus();
                a(R.string.user_address_emailwarning);
                return false;
            } else if (this.phoneEdit.getText().toString().matches("^[0-9]{10}$")) {
                return true;
            } else {
                this.phoneEdit.requestFocus();
                a(R.string.user_address_phonewarning);
                return false;
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.user_address_save) {
            LogUtil.b(TAG, "confirm clicked");
            if (d()) {
                e();
            }
        } else if (id == R.id.title_bar_back) {
            if (this.g != null) {
                this.g.dismiss();
            }
            onBackPressed();
        } else if (id == R.id.user_address_name) {
            a((View) this.nameEdit);
        } else if (id == R.id.user_address_pincode) {
            a((View) this.pincodeEdit);
        } else if (id == R.id.user_address_address) {
            a((View) this.addressEdit);
        } else if (id == R.id.user_address_city) {
            a((View) this.cityEdit);
        } else if (id == R.id.user_address_landmark) {
            a((View) this.landmarkEdit);
        } else if (id == R.id.user_address_email) {
            a((View) this.emailEdit);
        } else if (id == R.id.user_address_phone) {
            a((View) this.phoneEdit);
        }
    }

    private void a(View view) {
        if (view != null) {
            view.requestFocus();
            ((InputMethodManager) getSystemService("input_method")).showSoftInput(view, 1);
        }
    }

    public void onBackPressed() {
        if (hasEditAddress()) {
            CustomCancelDialog.Builder builder = new CustomCancelDialog.Builder(this);
            builder.a(getString(R.string.user_address_discardpromote)).a((Boolean) true).a(getString(R.string.user_address_discard), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AddressEditActivity.this.finish();
                }
            }).b(getString(R.string.user_address_cancel), (DialogInterface.OnClickListener) null);
            builder.a().show();
            return;
        }
        super.onBackPressed();
    }

    public boolean hasEditAddress() {
        if (this.e == null) {
            return !TextUtils.isEmpty(this.nameEdit.getText().toString()) || !TextUtils.isEmpty(this.pincodeEdit.getText().toString()) || !TextUtils.isEmpty(this.addressEdit.getText().toString()) || !TextUtils.isEmpty(this.landmarkEdit.getText().toString()) || !TextUtils.isEmpty(this.cityEdit.getText().toString()) || !TextUtils.isEmpty(this.stateSpinner.getSelectedItem().toString()) || !TextUtils.isEmpty(this.emailEdit.getText().toString()) || !TextUtils.isEmpty(this.phoneEdit.getText().toString()) || this.addressDefaultSwitch.isChecked();
        }
        if (TextUtils.equals(this.e.consignee, this.nameEdit.getText().toString()) && TextUtils.equals(this.e.zipcode, this.pincodeEdit.getText().toString()) && TextUtils.equals(this.e.addr_india.addr, this.addressEdit.getText().toString()) && TextUtils.equals(this.e.addr_india.landmark, this.landmarkEdit.getText().toString()) && TextUtils.equals(this.e.addr_india.city, this.cityEdit.getText().toString()) && TextUtils.equals(this.e.email, this.emailEdit.getText().toString()) && TextUtils.equals(this.e.tel, this.phoneEdit.getText().toString())) {
            return this.e.is_default != (this.addressDefaultSwitch.isChecked() ? NewAddressItem.DEFAULT_ADDRESS : NewAddressItem.OTHER_ADDRESS);
        }
    }

    public void onResume() {
        LogUtil.b(TAG, "onResume");
        a();
        super.onResume();
    }

    /* access modifiers changed from: private */
    public String b(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                sb.append(jSONObject.optString(keys.next().toString()));
                sb.append("\n");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    private void e() {
        Class<NewSaveAddressResult> cls = NewSaveAddressResult.class;
        SimpleProtobufRequest simpleProtobufRequest = new SimpleProtobufRequest(ConnectionHelper.T(), cls, f(), new SimpleCallback<NewSaveAddressResult>() {
            /* renamed from: a */
            public void onResponse(NewSaveAddressResult newSaveAddressResult) {
                if (newSaveAddressResult == null) {
                    a(AddressEditActivity.this.getString(R.string.shop_error_network));
                    return;
                }
                if (newSaveAddressResult.data != null && !TextUtils.isEmpty(newSaveAddressResult.data.errors)) {
                    String access$200 = AddressEditActivity.this.b(newSaveAddressResult.data.errors);
                    if (!TextUtils.isEmpty(access$200)) {
                        a(access$200);
                        return;
                    }
                }
                super.onResponse(newSaveAddressResult);
            }

            /* renamed from: b */
            public void a(NewSaveAddressResult newSaveAddressResult) {
                if (AddressEditActivity.this.g != null) {
                    AddressEditActivity.this.g.dismiss();
                }
                if (newSaveAddressResult.data != null && newSaveAddressResult.data.addinfo != null) {
                    Intent intent = new Intent();
                    if (AddressEditActivity.this.e == null) {
                        intent.putExtra("add_item", newSaveAddressResult.data.addinfo);
                    } else {
                        intent.putExtra("update_item", newSaveAddressResult.data.addinfo);
                    }
                    AddressEditActivity.this.setResult(-1, intent);
                    AddressEditActivity.this.finish();
                }
            }

            public void a(String str) {
                super.a(str);
                if (AddressEditActivity.this.g != null) {
                    AddressEditActivity.this.g.dismiss();
                }
            }
        });
        simpleProtobufRequest.setTag(TAG);
        RequestQueueUtil.a().add(simpleProtobufRequest);
        if (this.g == null) {
            this.g = new ProgressDialog(this);
            this.g.setMessage(getString(R.string.please_wait));
            this.g.setIndeterminate(true);
            this.g.setCancelable(false);
        }
        this.g.show();
    }

    private HashMap f() {
        String str;
        HashMap hashMap = new HashMap();
        if (this.e != null) {
            hashMap.put("addressId", this.e.address_id);
        } else {
            hashMap.put("addressId", "0");
        }
        String obj = this.nameEdit.getText().toString();
        String obj2 = this.phoneEdit.getText().toString();
        String obj3 = this.emailEdit.getText().toString();
        String obj4 = this.pincodeEdit.getText().toString();
        String obj5 = this.cityEdit.getText().toString();
        String obj6 = this.addressEdit.getText().toString();
        String obj7 = this.landmarkEdit.getText().toString();
        this.stateSpinner.getSelectedItem().toString();
        if (!TextUtils.isEmpty(this.c)) {
            str = this.c;
        } else {
            str = (this.e == null || this.e.district == null) ? "" : this.e.district.id;
        }
        String str2 = !TextUtils.isEmpty(this.d) ? this.d : "";
        hashMap.put("address[zipcode]", obj4);
        hashMap.put("address[consignee]", obj);
        hashMap.put("address[address_line1]", obj5);
        hashMap.put("address[address_line2]", obj6);
        hashMap.put("address[city]", str2);
        hashMap.put("address[state]", str2);
        hashMap.put("address[tel]", obj2);
        hashMap.put("address[landmark]", obj7);
        hashMap.put("address[email]", obj3);
        hashMap.put("address[district]", str);
        hashMap.put(CardInfo.KEY_IS_DEFAULT, String.valueOf(this.addressDefaultSwitch.isChecked() ? NewAddressItem.DEFAULT_ADDRESS : NewAddressItem.OTHER_ADDRESS));
        return hashMap;
    }

    class StateSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        StateSelectedListener() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            String str = AddressEditActivity.TAG;
            LogUtil.b(str, "onItemSelected position:" + i);
            String obj = AddressEditActivity.this.stateSpinner.getItemAtPosition(i).toString();
            for (int i2 = 0; i2 < AddressEditActivity.this.f.size(); i2++) {
                NewRegionItem newRegionItem = (NewRegionItem) AddressEditActivity.this.f.get(i2);
                if (obj.equalsIgnoreCase(newRegionItem.region_name)) {
                    String unused = AddressEditActivity.this.d = newRegionItem.region_id;
                }
            }
        }
    }
}
