package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

final class ApplicationDescriptor extends AbstractChainedDescriptor<Application> {
    /* access modifiers changed from: private */
    public final ActivityTracker mActivityTracker = ActivityTracker.get();
    private final Map<Application, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());

    ApplicationDescriptor() {
    }

    private ElementContext getContext(Application application) {
        return this.mElementToContextMap.get(application);
    }

    /* access modifiers changed from: protected */
    public void onHook(Application application) {
        ElementContext elementContext = new ElementContext();
        elementContext.hook(application);
        this.mElementToContextMap.put(application, elementContext);
    }

    /* access modifiers changed from: protected */
    public void onUnhook(Application application) {
        this.mElementToContextMap.remove(application).unhook();
    }

    /* access modifiers changed from: protected */
    public NodeType onGetNodeType(Application application) {
        return NodeType.ELEMENT_NODE;
    }

    /* access modifiers changed from: protected */
    public void onGetChildren(Application application, Accumulator<Object> accumulator) {
        List<WeakReference<Activity>> activitiesList = getContext(application).getActivitiesList();
        for (int size = activitiesList.size() - 1; size >= 0; size--) {
            Activity activity = (Activity) activitiesList.get(size).get();
            if (activity != null) {
                accumulator.store(activity);
            }
        }
    }

    private class ElementContext {
        private Application mElement;
        private final ActivityTracker.Listener mListener = new ActivityTracker.Listener() {
            public void onActivityAdded(Activity activity) {
            }

            public void onActivityRemoved(Activity activity) {
            }
        };

        public ElementContext() {
        }

        public void hook(Application application) {
            this.mElement = application;
            ApplicationDescriptor.this.mActivityTracker.registerListener(this.mListener);
        }

        public void unhook() {
            ApplicationDescriptor.this.mActivityTracker.unregisterListener(this.mListener);
            this.mElement = null;
        }

        public List<WeakReference<Activity>> getActivitiesList() {
            return ApplicationDescriptor.this.mActivityTracker.getActivitiesView();
        }
    }
}
