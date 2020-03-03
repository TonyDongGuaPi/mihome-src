package samples;

import java.io.PrintStream;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceTypeListener;

public class DiscoverServiceTypes {

    static class SampleListener implements ServiceTypeListener {
        SampleListener() {
        }

        public void a(ServiceEvent serviceEvent) {
            PrintStream printStream = System.out;
            printStream.println("Service type added: " + serviceEvent.getType());
        }

        public void b(ServiceEvent serviceEvent) {
            PrintStream printStream = System.out;
            printStream.println("SubType for service type added: " + serviceEvent.getType());
        }
    }

    public static void a(String[] strArr) {
        try {
            JmDNS a2 = JmDNS.a();
            a2.a((ServiceTypeListener) new SampleListener());
            System.out.println("Press q and Enter, to quit");
            while (true) {
                int read = System.in.read();
                if (read == -1 || ((char) read) == 'q') {
                    a2.close();
                    System.out.println("Done");
                }
            }
            a2.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
