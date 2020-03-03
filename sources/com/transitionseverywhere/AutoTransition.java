package com.transitionseverywhere;

import android.content.Context;
import android.util.AttributeSet;

public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        y();
    }

    public AutoTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        y();
    }

    private void y() {
        a(1);
        b((Transition) new Fade(2)).b((Transition) new ChangeBounds()).b((Transition) new Fade(1));
    }
}
