package org.mp4parser.tools;

import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.ParsableBox;

public class Offsets {
    public static long a(Container container, ParsableBox parsableBox, long j) {
        for (Box next : container.a()) {
            if (next == parsableBox) {
                return j;
            }
            if (next instanceof Container) {
                long a2 = a((Container) next, parsableBox, 0);
                if (a2 > 0) {
                    return a2 + j;
                }
                j += next.c();
            } else {
                j += next.c();
            }
        }
        return -1;
    }
}
