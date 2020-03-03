package miuipub.util.concurrent;

public interface Queue<T> {

    public interface Predicate<T> {
        boolean a(T t);
    }

    int a(Predicate<T> predicate);

    boolean a(T t);

    int b();

    boolean b(T t);

    boolean c();

    int d();

    T g();
}
