package com.xiaomi.mishopsdk.widget.wheel.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWheelAdapter implements WheelViewAdapter {
    private List<DataSetObserver> datasetObservers;

    public View getEmptyItem(View view, ViewGroup viewGroup) {
        return null;
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.datasetObservers == null) {
            this.datasetObservers = new LinkedList();
        }
        this.datasetObservers.add(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        if (this.datasetObservers != null) {
            this.datasetObservers.remove(dataSetObserver);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDataChangedEvent() {
        if (this.datasetObservers != null) {
            for (DataSetObserver onChanged : this.datasetObservers) {
                onChanged.onChanged();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void notifyDataInvalidatedEvent() {
        if (this.datasetObservers != null) {
            for (DataSetObserver onInvalidated : this.datasetObservers) {
                onInvalidated.onInvalidated();
            }
        }
    }
}
