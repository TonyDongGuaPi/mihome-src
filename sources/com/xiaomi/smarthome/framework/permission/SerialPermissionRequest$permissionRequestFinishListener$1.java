package com.xiaomi.smarthome.framework.permission;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.CompletableSubject;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
final class SerialPermissionRequest$permissionRequestFinishListener$1<T> implements Consumer<Disposable> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CompletableSubject f17126a;

    SerialPermissionRequest$permissionRequestFinishListener$1(CompletableSubject completableSubject) {
        this.f17126a = completableSubject;
    }

    /* renamed from: a */
    public final void accept(Disposable disposable) {
        this.f17126a.onComplete();
    }
}
