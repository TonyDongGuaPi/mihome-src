package org.jacoco.agent.rt.internal_8ff85ea;

public interface IExceptionLogger {

    /* renamed from: a  reason: collision with root package name */
    public static final IExceptionLogger f3581a = new IExceptionLogger() {
        public void a(Exception exc) {
            exc.printStackTrace();
        }
    };

    void a(Exception exc);
}
