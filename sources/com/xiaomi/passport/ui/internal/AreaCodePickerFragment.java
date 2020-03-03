package com.xiaomi.passport.ui.internal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import com.xiaomi.passport.ui.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000e"}, d2 = {"Lcom/xiaomi/passport/ui/internal/AreaCodePickerFragment;", "Landroid/support/v4/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class AreaCodePickerFragment extends Fragment {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.f(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.area_code_picker, viewGroup, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.f(view, "view");
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.fast_indexer_list);
        if (findViewById != null) {
            ListView listView = (ListView) findViewById;
            listView.setDividerHeight(0);
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.a();
            }
            listView.setAdapter(new AreaCodePickerAdapter(activity));
            listView.setOnItemClickListener(new AreaCodePickerFragment$onViewCreated$1(this, listView));
            View findViewById2 = view.findViewById(R.id.fast_indexer);
            if (findViewById2 != null) {
                AlphabetFastIndexer alphabetFastIndexer = (AlphabetFastIndexer) findViewById2;
                listView.setOnScrollListener(new AreaCodePickerFragment$onViewCreated$2(alphabetFastIndexer, alphabetFastIndexer, (AbsListView.OnScrollListener) null));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.AlphabetFastIndexer");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.ListView");
    }
}
