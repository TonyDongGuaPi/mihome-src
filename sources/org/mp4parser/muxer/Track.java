package org.mp4parser.muxer;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.samplegrouping.GroupEntry;

public interface Track extends Closeable {
    List<CompositionTimeToSample.Entry> a();

    long[] b();

    List<SampleDependencyTypeBox.Entry> c();

    SubSampleInformationBox d();

    long e();

    String f();

    List<Edit> g();

    Map<GroupEntry, long[]> h();

    List<Sample> l();

    long[] m();

    SampleDescriptionBox n();

    TrackMetaData o();

    String p();
}
