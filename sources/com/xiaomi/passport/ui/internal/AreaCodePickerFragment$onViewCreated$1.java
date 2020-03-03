package com.xiaomi.passport.ui.internal;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.xiaomi.passport.ui.internal.PhoneNumUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/AdapterView;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Landroid/view/View;", "position", "", "<anonymous parameter 3>", "", "onItemClick"}, k = 3, mv = {1, 1, 10})
final class AreaCodePickerFragment$onViewCreated$1 implements AdapterView.OnItemClickListener {
    final /* synthetic */ ListView $mListView;
    final /* synthetic */ AreaCodePickerFragment this$0;

    AreaCodePickerFragment$onViewCreated$1(AreaCodePickerFragment areaCodePickerFragment, ListView listView) {
        this.this$0 = areaCodePickerFragment;
        this.$mListView = listView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object item = this.$mListView.getAdapter().getItem(i);
        if (item != null) {
            PhoneNumUtil.CountryPhoneNumData countryPhoneNumData = (PhoneNumUtil.CountryPhoneNumData) item;
            Intent intent = new Intent();
            intent.putExtra("iso", countryPhoneNumData.countryISO);
            intent.putExtra("code", countryPhoneNumData.countryCode);
            FragmentActivity activity = this.this$0.getActivity();
            if (activity != null) {
                activity.setResult(-1, intent);
            }
            FragmentActivity activity2 = this.this$0.getActivity();
            if (activity2 != null) {
                activity2.finish();
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.PhoneNumUtil.CountryPhoneNumData");
    }
}
