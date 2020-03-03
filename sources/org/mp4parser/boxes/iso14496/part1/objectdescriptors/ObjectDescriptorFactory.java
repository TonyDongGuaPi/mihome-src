package org.mp4parser.boxes.iso14496.part1.objectdescriptors;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mp4parser.tools.IsoTypeReader;

public class ObjectDescriptorFactory {

    /* renamed from: a  reason: collision with root package name */
    protected static Logger f3829a = Logger.getLogger(ObjectDescriptorFactory.class.getName());
    protected static Map<Integer, Map<Integer, Class<? extends BaseDescriptor>>> b = new HashMap();

    static {
        HashSet<Class> hashSet = new HashSet<>();
        hashSet.add(DecoderSpecificInfo.class);
        hashSet.add(SLConfigDescriptor.class);
        hashSet.add(BaseDescriptor.class);
        hashSet.add(ExtensionDescriptor.class);
        hashSet.add(ObjectDescriptorBase.class);
        hashSet.add(ProfileLevelIndicationDescriptor.class);
        hashSet.add(AudioSpecificConfig.class);
        hashSet.add(ExtensionProfileLevelDescriptor.class);
        hashSet.add(ESDescriptor.class);
        hashSet.add(DecoderConfigDescriptor.class);
        for (Class cls : hashSet) {
            Descriptor descriptor = (Descriptor) cls.getAnnotation(Descriptor.class);
            int[] tags = descriptor.tags();
            int objectTypeIndication = descriptor.objectTypeIndication();
            Map map = b.get(Integer.valueOf(objectTypeIndication));
            if (map == null) {
                map = new HashMap();
            }
            for (int valueOf : tags) {
                map.put(Integer.valueOf(valueOf), cls);
            }
            b.put(Integer.valueOf(objectTypeIndication), map);
        }
    }

    public static BaseDescriptor a(int i, ByteBuffer byteBuffer) throws IOException {
        BaseDescriptor baseDescriptor;
        int f = IsoTypeReader.f(byteBuffer);
        Map map = b.get(Integer.valueOf(i));
        if (map == null) {
            map = b.get(-1);
        }
        Class cls = (Class) map.get(Integer.valueOf(f));
        if (cls == null || cls.isInterface() || Modifier.isAbstract(cls.getModifiers())) {
            Logger logger = f3829a;
            logger.warning("No ObjectDescriptor found for objectTypeIndication " + Integer.toHexString(i) + " and tag " + Integer.toHexString(f) + " found: " + cls);
            baseDescriptor = new UnknownDescriptor();
        } else {
            try {
                baseDescriptor = (BaseDescriptor) cls.newInstance();
            } catch (Exception e) {
                Logger logger2 = f3829a;
                Level level = Level.SEVERE;
                logger2.log(level, "Couldn't instantiate BaseDescriptor class " + cls + " for objectTypeIndication " + i + " and tag " + f, e);
                throw new RuntimeException(e);
            }
        }
        baseDescriptor.a(f, byteBuffer);
        return baseDescriptor;
    }
}
