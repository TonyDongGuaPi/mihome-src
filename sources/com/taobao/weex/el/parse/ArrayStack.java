package com.taobao.weex.el.parse;

import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ArrayStack<T> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private ArrayList<T> stack = new ArrayList<>(4);

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-8766930754820658377L, "com/taobao/weex/el/parse/ArrayStack", 11);
        $jacocoData = a2;
        return a2;
    }

    public ArrayStack() {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        $jacocoInit[1] = true;
    }

    public int size() {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.stack.size();
        $jacocoInit[2] = true;
        return size;
    }

    public T pop() {
        boolean[] $jacocoInit = $jacocoInit();
        T remove = this.stack.remove(this.stack.size() - 1);
        $jacocoInit[3] = true;
        return remove;
    }

    public void push(T t) {
        boolean[] $jacocoInit = $jacocoInit();
        this.stack.add(t);
        $jacocoInit[4] = true;
    }

    public T peek() {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.stack.get(this.stack.size() - 1);
        $jacocoInit[5] = true;
        return t;
    }

    public T get(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        T t = this.stack.get(i);
        $jacocoInit[6] = true;
        return t;
    }

    public T remove(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        T remove = this.stack.remove(i);
        $jacocoInit[7] = true;
        return remove;
    }

    public void add(int i, T t) {
        boolean[] $jacocoInit = $jacocoInit();
        this.stack.add(i, t);
        $jacocoInit[8] = true;
    }

    public boolean isEmpty() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isEmpty = this.stack.isEmpty();
        $jacocoInit[9] = true;
        return isEmpty;
    }

    public List<T> getList() {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList<T> arrayList = this.stack;
        $jacocoInit[10] = true;
        return arrayList;
    }
}
