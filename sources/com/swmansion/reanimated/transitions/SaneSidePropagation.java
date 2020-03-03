package com.swmansion.reanimated.transitions;

import android.support.annotation.RequiresApi;
import android.transition.SidePropagation;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.ViewGroup;

@RequiresApi(api = 21)
public class SaneSidePropagation extends SidePropagation {
    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        long startDelay = super.getStartDelay(viewGroup, transition, transitionValues, transitionValues2);
        return (startDelay == 0 || !(transitionValues2 == null || getViewVisibility(transitionValues) == 0)) ? startDelay : -startDelay;
    }
}
