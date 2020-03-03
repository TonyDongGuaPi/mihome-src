package org.greenrobot.greendao.converter;

public interface PropertyConverter<P, D> {
    P a(D d);

    D b(P p);
}
