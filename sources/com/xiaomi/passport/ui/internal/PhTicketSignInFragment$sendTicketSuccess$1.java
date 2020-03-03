package com.xiaomi.passport.ui.internal;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.TextView;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017¨\u0006\b"}, d2 = {"com/xiaomi/passport/ui/internal/PhTicketSignInFragment$sendTicketSuccess$1", "Landroid/os/CountDownTimer;", "(Lcom/xiaomi/passport/ui/internal/PhTicketSignInFragment;IJJ)V", "onFinish", "", "onTick", "millisUntilFinished", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhTicketSignInFragment$sendTicketSuccess$1 extends CountDownTimer {
    final /* synthetic */ int $time;
    final /* synthetic */ PhTicketSignInFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PhTicketSignInFragment$sendTicketSuccess$1(PhTicketSignInFragment phTicketSignInFragment, int i, long j, long j2) {
        super(j, j2);
        this.this$0 = phTicketSignInFragment;
        this.$time = i;
    }

    @SuppressLint({"SetTextI18n"})
    public void onTick(long j) {
        int i = (int) (j / ((long) 1000));
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.action_get_ph_ticket);
        if (textView != null) {
            textView.setText(String.valueOf(i) + "s");
        }
    }

    public void onFinish() {
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.action_get_ph_ticket);
        if (textView != null) {
            textView.setClickable(true);
        }
        TextView textView2 = (TextView) this.this$0._$_findCachedViewById(R.id.action_get_ph_ticket);
        if (textView2 != null) {
            textView2.setText(this.this$0.getString(R.string.passport_reload_ph_ticket));
        }
        this.this$0.setMIsCountingDown(false);
    }
}
