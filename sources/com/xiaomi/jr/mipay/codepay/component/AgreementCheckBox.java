package com.xiaomi.jr.mipay.codepay.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.data.Agreement;
import java.util.ArrayList;
import java.util.Collection;

public class AgreementCheckBox extends CheckBox {
    /* access modifiers changed from: private */
    public ArrayList<Agreement> mAgreements = new ArrayList<>();
    /* access modifiers changed from: private */
    public OnAgreementClickedListener mClickedListener;

    public interface OnAgreementClickedListener {
        void onClicked(String str, String str2);
    }

    public AgreementCheckBox(Context context) {
        super(context);
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(getResources().getColor(17170445));
    }

    public AgreementCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(getResources().getColor(17170445));
    }

    public void setAgreement(Collection<Agreement> collection) {
        this.mAgreements.clear();
        this.mAgreements.addAll(collection);
        update();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && (getText() instanceof Spannable)) {
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) ((Spannable) getText()).getSpans(getSelectionStart(), getSelectionEnd(), ClickableSpan.class);
            if (clickableSpanArr.length > 0) {
                clickableSpanArr[0].onClick(this);
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void update() {
        if (this.mAgreements.isEmpty()) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (this.mAgreements.size() == 1) {
            final String str = this.mAgreements.get(0).mTitle;
            final String str2 = this.mAgreements.get(0).mUrl;
            String string = getContext().getString(R.string.jr_mipay_agreement, new Object[]{str});
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            int indexOf = string.indexOf(str);
            spannableStringBuilder.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    if (AgreementCheckBox.this.mClickedListener != null) {
                        AgreementCheckBox.this.mClickedListener.onClicked(str, str2);
                    }
                }

                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(AgreementCheckBox.this.getResources().getColor(R.color.jr_mipay_agreement_box));
                }
            }, indexOf, str.length() + indexOf, 33);
            setText(spannableStringBuilder);
            return;
        }
        String string2 = getContext().getString(R.string.jr_mipay_user_license);
        String string3 = getContext().getString(R.string.jr_mipay_agreement, new Object[]{string2});
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(string3);
        int indexOf2 = string3.indexOf(string2);
        spannableStringBuilder2.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                CharSequence[] charSequenceArr = new CharSequence[AgreementCheckBox.this.mAgreements.size()];
                for (int i = 0; i < AgreementCheckBox.this.mAgreements.size(); i++) {
                    charSequenceArr[i] = ((Agreement) AgreementCheckBox.this.mAgreements.get(i)).mTitle;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AgreementCheckBox.this.getContext());
                builder.setSingleChoiceItems(charSequenceArr, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Agreement agreement = (Agreement) AgreementCheckBox.this.mAgreements.get(i);
                        if (AgreementCheckBox.this.mClickedListener != null) {
                            AgreementCheckBox.this.mClickedListener.onClicked(agreement.mTitle, agreement.mUrl);
                        }
                    }
                });
                builder.create().show();
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(AgreementCheckBox.this.getResources().getColor(R.color.jr_mipay_agreement_box));
            }
        }, indexOf2, string2.length() + indexOf2, 33);
        setText(spannableStringBuilder2);
    }

    public void setOnAgreementClickedListener(OnAgreementClickedListener onAgreementClickedListener) {
        this.mClickedListener = onAgreementClickedListener;
    }
}
