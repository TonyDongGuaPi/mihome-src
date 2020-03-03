package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.ShoppingCartActivity;
import com.mi.global.shop.adapter.cart.CartInvalidItemListAdapter;
import com.mi.global.shop.newmodel.cart.NewCartItem;
import com.mi.global.shop.widget.MaxHeightListView;
import com.mi.util.Coder;
import com.mi.util.Device;
import java.util.ArrayList;

public class CartDialogFragment extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7188a = "CartDialogFragment";
    private MaxHeightListView b;
    private CartInvalidItemListAdapter c;
    private ArrayList<NewCartItem> d;

    public static CartDialogFragment a(ArrayList<NewCartItem> arrayList) {
        CartDialogFragment cartDialogFragment = new CartDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("items", arrayList);
        cartDialogFragment.setArguments(bundle);
        return cartDialogFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = arguments.getParcelableArrayList("items");
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.shop_cart_dialog_fragment, (ViewGroup) null);
        inflate.findViewById(R.id.content).getLayoutParams().width = (Device.f1349a / 4) * 3;
        inflate.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CartDialogFragment.this.dismiss();
            }
        });
        inflate.findViewById(R.id.remove_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CartDialogFragment.this.dismiss();
                if (CartDialogFragment.this.getActivity() instanceof ShoppingCartActivity) {
                    ((ShoppingCartActivity) CartDialogFragment.this.getActivity()).deleteInvalidCart();
                }
            }
        });
        this.b = (MaxHeightListView) inflate.findViewById(R.id.list);
        this.b.setMaxHeight(Coder.a(getContext(), 170.0f));
        this.c = new CartInvalidItemListAdapter(getContext());
        this.c.a(this.d);
        this.b.setAdapter(this.c);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.getWindow().getDecorView().setBackgroundResource(17170445);
        return create;
    }
}
