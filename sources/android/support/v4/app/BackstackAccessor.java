package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;

public class BackstackAccessor {
    private BackstackAccessor() {
        throw new IllegalStateException("Not instantiatable");
    }

    public static boolean isFragmentOnBackStack(Fragment fragment) {
        try {
            return fragment.isInBackStack();
        } catch (IllegalAccessError unused) {
            return isInBackStackAndroidX(fragment);
        }
    }

    private static boolean isInBackStackAndroidX(Fragment fragment) {
        StringWriter stringWriter = new StringWriter();
        fragment.dump("", (FileDescriptor) null, new PrintWriter(stringWriter), (String[]) null);
        return !stringWriter.toString().contains("mBackStackNesting=0");
    }
}
