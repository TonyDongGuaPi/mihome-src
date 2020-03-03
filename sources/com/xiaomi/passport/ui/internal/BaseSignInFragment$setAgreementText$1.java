package com.xiaomi.passport.ui.internal;

import android.text.style.ClickableSpan;
import android.view.View;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/xiaomi/passport/ui/internal/BaseSignInFragment$setAgreementText$1", "Landroid/text/style/ClickableSpan;", "(Lcom/xiaomi/passport/ui/internal/BaseSignInFragment;Ljava/util/Map$Entry;)V", "onClick", "", "widget", "Landroid/view/View;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class BaseSignInFragment$setAgreementText$1 extends ClickableSpan {
    final /* synthetic */ Map.Entry $license;
    final /* synthetic */ BaseSignInFragment this$0;

    BaseSignInFragment$setAgreementText$1(BaseSignInFragment baseSignInFragment, Map.Entry entry) {
        this.this$0 = baseSignInFragment;
        this.$license = entry;
    }

    public void onClick(@Nullable View view) {
        this.this$0.gotoFragment(this.this$0.getMWebAuth().getNotificationFragment(((URLLicense) this.$license.getValue()).getUrl()), true);
    }
}
