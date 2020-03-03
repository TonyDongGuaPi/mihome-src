package com.mi.global.shop.adapter.user;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.user.address.NewAddressItem;
import com.mi.global.shop.user.AddressListActivity;
import com.mi.global.shop.widget.CustomTextView;

public class AddressListAdapter extends ArrayAdapter<NewAddressItem> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5571a = 0;
    public static final int b = 1;
    private static final String c = "AddressListAdapter";
    private String g;

    public int getViewTypeCount() {
        return 2;
    }

    public class AddressChooseViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private AddressChooseViewHolder f5573a;

        @UiThread
        public AddressChooseViewHolder_ViewBinding(AddressChooseViewHolder addressChooseViewHolder, View view) {
            this.f5573a = addressChooseViewHolder;
            addressChooseViewHolder.icon = (ImageView) Utils.findRequiredViewAsType(view, R.id.address_select_icon, "field 'icon'", ImageView.class);
            addressChooseViewHolder.consignee = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_consignee, "field 'consignee'", CustomTextView.class);
            addressChooseViewHolder.tel = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_tel, "field 'tel'", CustomTextView.class);
            addressChooseViewHolder.addressline1 = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_line1, "field 'addressline1'", CustomTextView.class);
            addressChooseViewHolder.addressline2 = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_line2, "field 'addressline2'", CustomTextView.class);
            addressChooseViewHolder.editAddress = Utils.findRequiredView(view, R.id.edit_address, "field 'editAddress'");
            addressChooseViewHolder.addressTipView = Utils.findRequiredView(view, R.id.address_tip_layout, "field 'addressTipView'");
            addressChooseViewHolder.addressTipTextView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_tip_text, "field 'addressTipTextView'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            AddressChooseViewHolder addressChooseViewHolder = this.f5573a;
            if (addressChooseViewHolder != null) {
                this.f5573a = null;
                addressChooseViewHolder.icon = null;
                addressChooseViewHolder.consignee = null;
                addressChooseViewHolder.tel = null;
                addressChooseViewHolder.addressline1 = null;
                addressChooseViewHolder.addressline2 = null;
                addressChooseViewHolder.editAddress = null;
                addressChooseViewHolder.addressTipView = null;
                addressChooseViewHolder.addressTipTextView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class AddressManageViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private AddressManageViewHolder f5577a;

        @UiThread
        public AddressManageViewHolder_ViewBinding(AddressManageViewHolder addressManageViewHolder, View view) {
            this.f5577a = addressManageViewHolder;
            addressManageViewHolder.consignee = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_consignee, "field 'consignee'", CustomTextView.class);
            addressManageViewHolder.tel = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_tel, "field 'tel'", CustomTextView.class);
            addressManageViewHolder.addressline1 = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_line1, "field 'addressline1'", CustomTextView.class);
            addressManageViewHolder.addressline2 = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_line2, "field 'addressline2'", CustomTextView.class);
            addressManageViewHolder.editAddress = Utils.findRequiredView(view, R.id.edit_address, "field 'editAddress'");
            addressManageViewHolder.deleteAddress = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.delete_address, "field 'deleteAddress'", CustomTextView.class);
            addressManageViewHolder.defaultAddressView = Utils.findRequiredView(view, R.id.default_address_layout, "field 'defaultAddressView'");
            addressManageViewHolder.defaultAddressBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.default_address_btn, "field 'defaultAddressBtn'", ImageView.class);
            addressManageViewHolder.addressTipView = Utils.findRequiredView(view, R.id.address_tip_layout, "field 'addressTipView'");
            addressManageViewHolder.addressTipTextView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.address_tip_text, "field 'addressTipTextView'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            AddressManageViewHolder addressManageViewHolder = this.f5577a;
            if (addressManageViewHolder != null) {
                this.f5577a = null;
                addressManageViewHolder.consignee = null;
                addressManageViewHolder.tel = null;
                addressManageViewHolder.addressline1 = null;
                addressManageViewHolder.addressline2 = null;
                addressManageViewHolder.editAddress = null;
                addressManageViewHolder.deleteAddress = null;
                addressManageViewHolder.defaultAddressView = null;
                addressManageViewHolder.defaultAddressBtn = null;
                addressManageViewHolder.addressTipView = null;
                addressManageViewHolder.addressTipTextView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public AddressListAdapter(Context context, String str) {
        super(context);
        this.g = str;
    }

    public View a(Context context, int i, NewAddressItem newAddressItem, ViewGroup viewGroup) {
        Object obj;
        View view;
        if (getItemViewType(i) == 1) {
            view = LayoutInflater.from(this.d).inflate(R.layout.shop_address_choose_list_item, viewGroup, false);
            obj = new AddressChooseViewHolder(view);
        } else {
            view = LayoutInflater.from(this.d).inflate(R.layout.shop_address_manage_list_item, viewGroup, false);
            obj = new AddressManageViewHolder(view);
        }
        view.setTag(obj);
        return view;
    }

    public int getItemViewType(int i) {
        return this.g.equalsIgnoreCase("address_choose") ? 1 : 0;
    }

    public void a(View view, int i, NewAddressItem newAddressItem) {
        ((ViewHolder) view.getTag()).a(this.d, newAddressItem);
    }

    static abstract class ViewHolder {
        /* access modifiers changed from: package-private */
        public abstract void a(Context context, NewAddressItem newAddressItem);

        ViewHolder() {
        }
    }

    static class AddressManageViewHolder extends ViewHolder {
        @BindView(2131492944)
        CustomTextView addressTipTextView;
        @BindView(2131492943)
        View addressTipView;
        @BindView(2131492937)
        CustomTextView addressline1;
        @BindView(2131492938)
        CustomTextView addressline2;
        @BindView(2131492933)
        CustomTextView consignee;
        @BindView(2131493254)
        ImageView defaultAddressBtn;
        @BindView(2131493255)
        View defaultAddressView;
        @BindView(2131493256)
        CustomTextView deleteAddress;
        @BindView(2131493288)
        View editAddress;
        @BindView(2131492942)
        CustomTextView tel;

        public AddressManageViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        /* access modifiers changed from: package-private */
        public void a(final Context context, final NewAddressItem newAddressItem) {
            this.addressTipView.setVisibility(8);
            boolean z = false;
            if (newAddressItem.is_invalid != 0) {
                this.addressTipView.setVisibility(0);
                this.addressTipTextView.setText(R.string.user_address_invalid);
            } else if (newAddressItem.can_cod == 0) {
                this.addressTipView.setVisibility(0);
                this.addressTipTextView.setText(R.string.no_COD_address);
            }
            this.consignee.setText(newAddressItem.consignee);
            this.tel.setText(context.getString(R.string.buy_confirm_COD_phonezone) + " " + newAddressItem.tel);
            String str = "";
            if (newAddressItem.addr_india != null) {
                if (TextUtils.isEmpty(newAddressItem.addr_india.landmark)) {
                    str = newAddressItem.addr_india.addr;
                } else {
                    str = newAddressItem.addr_india.addr + "  " + newAddressItem.addr_india.landmark;
                }
            }
            String str2 = newAddressItem.zipcode;
            if (newAddressItem.addr_india != null && !TextUtils.isEmpty(newAddressItem.addr_india.city)) {
                str2 = str2 + "  " + newAddressItem.addr_india.city;
            }
            if (newAddressItem.city != null && !TextUtils.isEmpty(newAddressItem.city.name)) {
                str2 = str2 + "  " + newAddressItem.city.name;
            }
            this.addressline1.setText(str);
            this.addressline2.setText(str2);
            this.editAddress.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (context instanceof AddressListActivity) {
                        ((AddressListActivity) context).gotoEditAddress(newAddressItem);
                    }
                }
            });
            this.deleteAddress.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (context instanceof AddressListActivity) {
                        ((AddressListActivity) context).delAddressDialog(newAddressItem.address_id);
                    }
                }
            });
            this.defaultAddressView.setVisibility(0);
            ImageView imageView = this.defaultAddressBtn;
            if (newAddressItem.is_default == NewAddressItem.DEFAULT_ADDRESS) {
                z = true;
            }
            imageView.setSelected(z);
            this.defaultAddressView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (newAddressItem.is_default != NewAddressItem.DEFAULT_ADDRESS && (context instanceof AddressListActivity)) {
                        ((AddressListActivity) context).setDefaultAddress(newAddressItem.address_id);
                    }
                }
            });
        }
    }

    static class AddressChooseViewHolder extends ViewHolder {
        @BindView(2131492944)
        CustomTextView addressTipTextView;
        @BindView(2131492943)
        View addressTipView;
        @BindView(2131492937)
        CustomTextView addressline1;
        @BindView(2131492938)
        CustomTextView addressline2;
        @BindView(2131492933)
        CustomTextView consignee;
        @BindView(2131493288)
        View editAddress;
        @BindView(2131492941)
        ImageView icon;
        @BindView(2131492942)
        CustomTextView tel;

        public AddressChooseViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        /* access modifiers changed from: package-private */
        public void a(final Context context, final NewAddressItem newAddressItem) {
            this.addressTipView.setVisibility(8);
            if (newAddressItem.is_invalid != 0) {
                this.addressTipView.setVisibility(0);
                this.addressTipTextView.setText(R.string.user_address_invalid);
            } else if (newAddressItem.can_cod == 0) {
                this.addressTipView.setVisibility(0);
                this.addressTipTextView.setText(R.string.no_COD_address);
            }
            this.consignee.setText(newAddressItem.consignee);
            this.tel.setText(context.getString(R.string.buy_confirm_COD_phonezone) + " " + newAddressItem.tel);
            String str = "";
            if (newAddressItem.addr_india != null) {
                if (TextUtils.isEmpty(newAddressItem.addr_india.landmark)) {
                    str = newAddressItem.addr_india.addr;
                } else {
                    str = newAddressItem.addr_india.addr + "  " + newAddressItem.addr_india.landmark;
                }
            }
            if (newAddressItem.is_default == NewAddressItem.DEFAULT_ADDRESS) {
                SpannableString spannableString = new SpannableString(" Default  " + str);
                spannableString.setSpan(new BackgroundColorSpan(-25075), 0, 9, 33);
                spannableString.setSpan(new ForegroundColorSpan(-1), 0, 9, 33);
                this.addressline1.setText(spannableString);
            } else {
                this.addressline1.setText(str);
            }
            String str2 = newAddressItem.zipcode;
            if (newAddressItem.addr_india != null && !TextUtils.isEmpty(newAddressItem.addr_india.city)) {
                str2 = str2 + "  " + newAddressItem.addr_india.city;
            }
            if (newAddressItem.city != null && !TextUtils.isEmpty(newAddressItem.city.name)) {
                str2 = str2 + "  " + newAddressItem.city.name;
            }
            this.addressline2.setText(str2);
            this.editAddress.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (context instanceof AddressListActivity) {
                        ((AddressListActivity) context).gotoEditAddress(newAddressItem);
                    }
                }
            });
            this.icon.setVisibility(0);
            this.icon.setSelected(newAddressItem.selected);
        }
    }
}
