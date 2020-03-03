package com.mi.global.shop.adapter.checkout;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.widget.CustomTextView;

public class UPISelectListAdapter extends ArrayAdapter<String> {

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5533a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5533a = viewHolder;
            viewHolder.mSelectedView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_selected, "field 'mSelectedView'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5533a;
            if (viewHolder != null) {
                this.f5533a = null;
                viewHolder.mSelectedView = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public UPISelectListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, String str, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_tez_upi_select_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, String str) {
        ((ViewHolder) view.getTag()).mSelectedView.setText(str);
    }

    static class ViewHolder {
        @BindView(2131494263)
        CustomTextView mSelectedView;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
