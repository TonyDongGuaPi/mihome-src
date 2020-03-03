package com.scwang.smartrefresh.layout.api;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
public interface RefreshFooter extends RefreshInternal {
    boolean setNoMoreData(boolean z);
}
