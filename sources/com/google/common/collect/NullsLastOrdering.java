package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class NullsLastOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> ordering;

    public <S extends T> Ordering<S> nullsLast() {
        return this;
    }

    NullsLastOrdering(Ordering<? super T> ordering2) {
        this.ordering = ordering2;
    }

    public int compare(@Nullable T t, @Nullable T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        return this.ordering.compare(t, t2);
    }

    public <S extends T> Ordering<S> reverse() {
        return this.ordering.reverse().nullsFirst();
    }

    public <S extends T> Ordering<S> nullsFirst() {
        return this.ordering.nullsFirst();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NullsLastOrdering) {
            return this.ordering.equals(((NullsLastOrdering) obj).ordering);
        }
        return false;
    }

    public int hashCode() {
        return this.ordering.hashCode() ^ -921210296;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.ordering));
        StringBuilder sb = new StringBuilder(valueOf.length() + 12);
        sb.append(valueOf);
        sb.append(".nullsLast()");
        return sb.toString();
    }
}
