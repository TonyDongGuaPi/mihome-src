package org.mp4parser.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.util.Iterator;
import org.junit.Assert;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.tools.Path;

public class BoxComparator {
    public static boolean a(Container container, Box box, String[] strArr) {
        for (String a2 : strArr) {
            if (Path.a(container, box, a2)) {
                return true;
            }
        }
        return false;
    }

    public static void a(Container container, Box box, Container container2, Box box2, String... strArr) throws IOException {
        Assert.assertEquals(box.ae_(), box2.ae_());
        if (!a(container, box, strArr)) {
            Assert.assertEquals("Type differs. \ntypetrace ref : " + box + "\ntypetrace new : " + box2, box.ae_(), box2.ae_());
            boolean z = box instanceof Container;
            if (!((!(box2 instanceof Container)) ^ z)) {
                Assert.fail("Either both boxes are container boxes or none");
            } else if (z) {
                a(container, (Container) box, container2, (Container) box2, strArr);
            } else {
                b(container, box, container2, box2, strArr);
            }
        }
    }

    private static void b(Container container, Box box, Container container2, Box box2, String[] strArr) throws IOException {
        if (!a(container, box, strArr)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            box.b(Channels.newChannel(byteArrayOutputStream));
            box2.b(Channels.newChannel(byteArrayOutputStream2));
            byteArrayOutputStream.close();
            byteArrayOutputStream2.close();
            Assert.assertArrayEquals("Box at " + box + " differs from reference\n\n" + box.toString() + "\n" + box2.toString(), byteArrayOutputStream.toByteArray(), byteArrayOutputStream2.toByteArray());
        }
    }

    public static void a(Container container, Container container2, String... strArr) throws IOException {
        a(container, container, container2, container2, strArr);
    }

    public static void a(Container container, Container container2, Container container3, Container container4, String... strArr) throws IOException {
        Iterator<Box> it = container2.a().iterator();
        Iterator<Box> it2 = container4.a().iterator();
        while (it.hasNext() && it2.hasNext()) {
            a(container, it.next(), container3, it2.next(), strArr);
        }
        if (it.hasNext()) {
            Assert.fail("There is a box missing in the current output of the tool: " + it.next());
        }
        if (it2.hasNext()) {
            Assert.fail("There is a box too much in the current output of the tool: " + it2.next());
        }
    }
}
