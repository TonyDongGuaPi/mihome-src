package org.apache.commons.compress.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class ServiceLoaderIterator<E> implements Iterator<E> {

    /* renamed from: a  reason: collision with root package name */
    private E f3353a;
    private final Class<E> b;
    private final Iterator<E> c;

    public ServiceLoaderIterator(Class<E> cls) {
        this(cls, ClassLoader.getSystemClassLoader());
    }

    public ServiceLoaderIterator(Class<E> cls, ClassLoader classLoader) {
        this.b = cls;
        this.c = ServiceLoader.load(cls, classLoader).iterator();
        this.f3353a = null;
    }

    private boolean a() {
        while (this.f3353a == null) {
            try {
                if (!this.c.hasNext()) {
                    return false;
                }
                this.f3353a = this.c.next();
            } catch (ServiceConfigurationError e) {
                if (!(e.getCause() instanceof SecurityException)) {
                    throw e;
                }
            }
        }
        return true;
    }

    public boolean hasNext() {
        return a();
    }

    public E next() {
        if (a()) {
            E e = this.f3353a;
            this.f3353a = null;
            return e;
        }
        throw new NoSuchElementException("No more elements for service " + this.b.getName());
    }

    public void remove() {
        throw new UnsupportedOperationException("service=" + this.b.getName());
    }
}
