package com.xiaomi.passport.ui.internal;

import android.widget.AbsListView;
import com.xiaomi.passport.ui.internal.AlphabetFastIndexer;
import com.xiaomi.passport.ui.internal.PhoneNumUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"com/xiaomi/passport/ui/internal/AreaCodePickerFragment$onViewCreated$2", "Lcom/xiaomi/passport/ui/internal/AlphabetFastIndexer$OnScrollerDecorator;", "(Lcom/xiaomi/passport/ui/internal/AlphabetFastIndexer;Lcom/xiaomi/passport/ui/internal/AlphabetFastIndexer;Landroid/widget/AbsListView$OnScrollListener;)V", "itemToString", "", "item", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class AreaCodePickerFragment$onViewCreated$2 extends AlphabetFastIndexer.OnScrollerDecorator {
    final /* synthetic */ AlphabetFastIndexer $mFastIndexer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AreaCodePickerFragment$onViewCreated$2(AlphabetFastIndexer alphabetFastIndexer, AlphabetFastIndexer alphabetFastIndexer2, AbsListView.OnScrollListener onScrollListener) {
        super(alphabetFastIndexer2, onScrollListener);
        this.$mFastIndexer = alphabetFastIndexer;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public String itemToString(@Nullable Object obj) {
        if (obj != null) {
            String str = ((PhoneNumUtil.CountryPhoneNumData) obj).countryName;
            Intrinsics.b(str, "(item as PhoneNumUtil.Co…PhoneNumData).countryName");
            return str;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.PhoneNumUtil.CountryPhoneNumData");
    }
}
