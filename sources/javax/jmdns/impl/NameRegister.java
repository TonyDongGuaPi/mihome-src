package javax.jmdns.impl;

import java.net.InetAddress;

public interface NameRegister {

    public enum NameType {
        HOST,
        SERVICE
    }

    public static class UniqueNameAcrossInterface implements NameRegister {
        public void a(InetAddress inetAddress, String str, NameType nameType) {
        }

        public boolean b(InetAddress inetAddress, String str, NameType nameType) {
            return false;
        }

        public String c(InetAddress inetAddress, String str, NameType nameType) {
            return null;
        }
    }

    public static class UniqueNamePerInterface implements NameRegister {
        public void a(InetAddress inetAddress, String str, NameType nameType) {
        }

        public boolean b(InetAddress inetAddress, String str, NameType nameType) {
            return false;
        }

        public String c(InetAddress inetAddress, String str, NameType nameType) {
            return null;
        }
    }

    void a(InetAddress inetAddress, String str, NameType nameType);

    boolean b(InetAddress inetAddress, String str, NameType nameType);

    String c(InetAddress inetAddress, String str, NameType nameType);

    public static class Factory {

        /* renamed from: a  reason: collision with root package name */
        private static volatile NameRegister f2662a;

        public static void a(NameRegister nameRegister) throws IllegalStateException {
            if (f2662a != null) {
                throw new IllegalStateException("The register can only be set once.");
            } else if (nameRegister != null) {
                f2662a = nameRegister;
            }
        }

        public static NameRegister a() {
            if (f2662a == null) {
                f2662a = new UniqueNamePerInterface();
            }
            return f2662a;
        }
    }
}
