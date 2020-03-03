package com.lidroid.xutils.task;

public class PriorityObject<E> {

    /* renamed from: a  reason: collision with root package name */
    public final Priority f6363a;
    public final E b;

    public PriorityObject(Priority priority, E e) {
        this.f6363a = priority == null ? Priority.DEFAULT : priority;
        this.b = e;
    }
}
