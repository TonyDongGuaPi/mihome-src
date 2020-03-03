package com.google.android.exoplayer2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SlidingPercentile {
    private static final Comparator<Sample> INDEX_COMPARATOR = $$Lambda$SlidingPercentile$IHMSNRVWSvKImU2XQD2j4ISb4U.INSTANCE;
    private static final int MAX_RECYCLED_SAMPLES = 5;
    private static final int SORT_ORDER_BY_INDEX = 1;
    private static final int SORT_ORDER_BY_VALUE = 0;
    private static final int SORT_ORDER_NONE = -1;
    private static final Comparator<Sample> VALUE_COMPARATOR = $$Lambda$SlidingPercentile$UufTq1Ma5g1qQu0Vqc6f2CE68bE.INSTANCE;
    private int currentSortOrder = -1;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final Sample[] recycledSamples = new Sample[5];

    /* renamed from: samples  reason: collision with root package name */
    private final ArrayList<Sample> f5335samples = new ArrayList<>();
    private int totalWeight;

    static /* synthetic */ int lambda$static$0(Sample sample, Sample sample2) {
        return sample.index - sample2.index;
    }

    public SlidingPercentile(int i) {
        this.maxWeight = i;
    }

    public void addSample(int i, float f) {
        Sample sample;
        ensureSortedByIndex();
        if (this.recycledSampleCount > 0) {
            Sample[] sampleArr = this.recycledSamples;
            int i2 = this.recycledSampleCount - 1;
            this.recycledSampleCount = i2;
            sample = sampleArr[i2];
        } else {
            sample = new Sample();
        }
        int i3 = this.nextSampleIndex;
        this.nextSampleIndex = i3 + 1;
        sample.index = i3;
        sample.weight = i;
        sample.value = f;
        this.f5335samples.add(sample);
        this.totalWeight += i;
        while (this.totalWeight > this.maxWeight) {
            int i4 = this.totalWeight - this.maxWeight;
            Sample sample2 = this.f5335samples.get(0);
            if (sample2.weight <= i4) {
                this.totalWeight -= sample2.weight;
                this.f5335samples.remove(0);
                if (this.recycledSampleCount < 5) {
                    Sample[] sampleArr2 = this.recycledSamples;
                    int i5 = this.recycledSampleCount;
                    this.recycledSampleCount = i5 + 1;
                    sampleArr2[i5] = sample2;
                }
            } else {
                sample2.weight -= i4;
                this.totalWeight -= i4;
            }
        }
    }

    public float getPercentile(float f) {
        ensureSortedByValue();
        float f2 = f * ((float) this.totalWeight);
        int i = 0;
        for (int i2 = 0; i2 < this.f5335samples.size(); i2++) {
            Sample sample = this.f5335samples.get(i2);
            i += sample.weight;
            if (((float) i) >= f2) {
                return sample.value;
            }
        }
        if (this.f5335samples.isEmpty()) {
            return Float.NaN;
        }
        return this.f5335samples.get(this.f5335samples.size() - 1).value;
    }

    private void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.f5335samples, INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }

    private void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.f5335samples, VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }

    private static class Sample {
        public int index;
        public float value;
        public int weight;

        private Sample() {
        }
    }
}
