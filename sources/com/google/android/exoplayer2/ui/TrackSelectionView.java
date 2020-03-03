package com.google.android.exoplayer2.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

public class TrackSelectionView extends LinearLayout {
    private boolean allowAdaptiveSelections;
    private final ComponentListener componentListener;
    private final CheckedTextView defaultView;
    private final CheckedTextView disableView;
    private final LayoutInflater inflater;
    private boolean isDisabled;
    @Nullable
    private DefaultTrackSelector.SelectionOverride override;
    private int rendererIndex;
    private final int selectableItemBackgroundResourceId;
    private TrackGroupArray trackGroups;
    private TrackNameProvider trackNameProvider;
    private DefaultTrackSelector trackSelector;
    private CheckedTextView[][] trackViews;

    public static Pair<AlertDialog, TrackSelectionView> getDialog(Activity activity, CharSequence charSequence, DefaultTrackSelector defaultTrackSelector, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View inflate = LayoutInflater.from(builder.getContext()).inflate(R.layout.exo_track_selection_dialog, (ViewGroup) null);
        TrackSelectionView trackSelectionView = (TrackSelectionView) inflate.findViewById(R.id.exo_track_selection_view);
        trackSelectionView.init(defaultTrackSelector, i);
        return Pair.create(builder.setTitle(charSequence).setView(inflate).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                TrackSelectionView.this.applySelection();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create(), trackSelectionView);
    }

    public TrackSelectionView(Context context) {
        this(context, (AttributeSet) null);
    }

    public TrackSelectionView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TrackSelectionView(Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843534});
        this.selectableItemBackgroundResourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        this.inflater = LayoutInflater.from(context);
        this.componentListener = new ComponentListener();
        this.trackNameProvider = new DefaultTrackNameProvider(getResources());
        this.disableView = (CheckedTextView) this.inflater.inflate(17367055, this, false);
        this.disableView.setBackgroundResource(this.selectableItemBackgroundResourceId);
        this.disableView.setText(R.string.exo_track_selection_none);
        this.disableView.setEnabled(false);
        this.disableView.setFocusable(true);
        this.disableView.setOnClickListener(this.componentListener);
        this.disableView.setVisibility(8);
        addView(this.disableView);
        addView(this.inflater.inflate(R.layout.exo_list_divider, this, false));
        this.defaultView = (CheckedTextView) this.inflater.inflate(17367055, this, false);
        this.defaultView.setBackgroundResource(this.selectableItemBackgroundResourceId);
        this.defaultView.setText(R.string.exo_track_selection_auto);
        this.defaultView.setEnabled(false);
        this.defaultView.setFocusable(true);
        this.defaultView.setOnClickListener(this.componentListener);
        addView(this.defaultView);
    }

    public void setAllowAdaptiveSelections(boolean z) {
        if (this.allowAdaptiveSelections != z) {
            this.allowAdaptiveSelections = z;
            updateViews();
        }
    }

    public void setShowDisableOption(boolean z) {
        this.disableView.setVisibility(z ? 0 : 8);
    }

    public void setTrackNameProvider(TrackNameProvider trackNameProvider2) {
        this.trackNameProvider = (TrackNameProvider) Assertions.checkNotNull(trackNameProvider2);
        updateViews();
    }

    public void init(DefaultTrackSelector defaultTrackSelector, int i) {
        this.trackSelector = defaultTrackSelector;
        this.rendererIndex = i;
        updateViews();
    }

    private void updateViews() {
        MappingTrackSelector.MappedTrackInfo mappedTrackInfo;
        for (int childCount = getChildCount() - 1; childCount >= 3; childCount--) {
            removeViewAt(childCount);
        }
        if (this.trackSelector == null) {
            mappedTrackInfo = null;
        } else {
            mappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        }
        if (this.trackSelector == null || mappedTrackInfo == null) {
            this.disableView.setEnabled(false);
            this.defaultView.setEnabled(false);
            return;
        }
        this.disableView.setEnabled(true);
        this.defaultView.setEnabled(true);
        this.trackGroups = mappedTrackInfo.getTrackGroups(this.rendererIndex);
        DefaultTrackSelector.Parameters parameters = this.trackSelector.getParameters();
        this.isDisabled = parameters.getRendererDisabled(this.rendererIndex);
        this.override = parameters.getSelectionOverride(this.rendererIndex, this.trackGroups);
        this.trackViews = new CheckedTextView[this.trackGroups.length][];
        for (int i = 0; i < this.trackGroups.length; i++) {
            TrackGroup trackGroup = this.trackGroups.get(i);
            boolean z = this.allowAdaptiveSelections && this.trackGroups.get(i).length > 1 && mappedTrackInfo.getAdaptiveSupport(this.rendererIndex, i, false) != 0;
            this.trackViews[i] = new CheckedTextView[trackGroup.length];
            for (int i2 = 0; i2 < trackGroup.length; i2++) {
                if (i2 == 0) {
                    addView(this.inflater.inflate(R.layout.exo_list_divider, this, false));
                }
                CheckedTextView checkedTextView = (CheckedTextView) this.inflater.inflate(z ? 17367056 : 17367055, this, false);
                checkedTextView.setBackgroundResource(this.selectableItemBackgroundResourceId);
                checkedTextView.setText(this.trackNameProvider.getTrackName(trackGroup.getFormat(i2)));
                if (mappedTrackInfo.getTrackSupport(this.rendererIndex, i, i2) == 4) {
                    checkedTextView.setFocusable(true);
                    checkedTextView.setTag(Pair.create(Integer.valueOf(i), Integer.valueOf(i2)));
                    checkedTextView.setOnClickListener(this.componentListener);
                } else {
                    checkedTextView.setFocusable(false);
                    checkedTextView.setEnabled(false);
                }
                this.trackViews[i][i2] = checkedTextView;
                addView(checkedTextView);
            }
        }
        updateViewStates();
    }

    private void updateViewStates() {
        this.disableView.setChecked(this.isDisabled);
        this.defaultView.setChecked(!this.isDisabled && this.override == null);
        int i = 0;
        while (i < this.trackViews.length) {
            for (int i2 = 0; i2 < this.trackViews[i].length; i2++) {
                this.trackViews[i][i2].setChecked(this.override != null && this.override.groupIndex == i && this.override.containsTrack(i2));
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public void applySelection() {
        DefaultTrackSelector.ParametersBuilder buildUponParameters = this.trackSelector.buildUponParameters();
        buildUponParameters.setRendererDisabled(this.rendererIndex, this.isDisabled);
        if (this.override != null) {
            buildUponParameters.setSelectionOverride(this.rendererIndex, this.trackGroups, this.override);
        } else {
            buildUponParameters.clearSelectionOverrides(this.rendererIndex);
        }
        this.trackSelector.setParameters(buildUponParameters);
    }

    /* access modifiers changed from: private */
    public void onClick(View view) {
        if (view == this.disableView) {
            onDisableViewClicked();
        } else if (view == this.defaultView) {
            onDefaultViewClicked();
        } else {
            onTrackViewClicked(view);
        }
        updateViewStates();
    }

    private void onDisableViewClicked() {
        this.isDisabled = true;
        this.override = null;
    }

    private void onDefaultViewClicked() {
        this.isDisabled = false;
        this.override = null;
    }

    private void onTrackViewClicked(View view) {
        this.isDisabled = false;
        Pair pair = (Pair) view.getTag();
        int intValue = ((Integer) pair.first).intValue();
        int intValue2 = ((Integer) pair.second).intValue();
        if (this.override == null || this.override.groupIndex != intValue || !this.allowAdaptiveSelections) {
            this.override = new DefaultTrackSelector.SelectionOverride(intValue, intValue2);
            return;
        }
        int i = this.override.length;
        int[] iArr = this.override.tracks;
        if (!((CheckedTextView) view).isChecked()) {
            this.override = new DefaultTrackSelector.SelectionOverride(intValue, getTracksAdding(iArr, intValue2));
        } else if (i == 1) {
            this.override = null;
            this.isDisabled = true;
        } else {
            this.override = new DefaultTrackSelector.SelectionOverride(intValue, getTracksRemoving(iArr, intValue2));
        }
    }

    private static int[] getTracksAdding(int[] iArr, int i) {
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        copyOf[copyOf.length - 1] = i;
        return copyOf;
    }

    private static int[] getTracksRemoving(int[] iArr, int i) {
        int[] iArr2 = new int[(iArr.length - 1)];
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 != i) {
                iArr2[i2] = i3;
                i2++;
            }
        }
        return iArr2;
    }

    private class ComponentListener implements View.OnClickListener {
        private ComponentListener() {
        }

        public void onClick(View view) {
            TrackSelectionView.this.onClick(view);
        }
    }
}
