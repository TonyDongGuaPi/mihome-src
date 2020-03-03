package rx.internal.util;

import java.io.PrintStream;
import rx.plugins.RxJavaPlugins;

public final class RxJavaPluginUtils {
    public static void handleException(Throwable th) {
        try {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        } catch (Throwable th2) {
            handlePluginException(th2);
        }
    }

    private static void handlePluginException(Throwable th) {
        PrintStream printStream = System.err;
        printStream.println("RxJavaErrorHandler threw an Exception. It shouldn't. => " + th.getMessage());
        th.printStackTrace();
    }
}
