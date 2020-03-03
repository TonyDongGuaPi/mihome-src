package org.mp4parser.muxer.tracks;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.mp4parser.Box;
import org.mp4parser.boxes.adobe.ActionMessageFormat0SampleEntryBox;
import org.mp4parser.boxes.iso14496.part12.CompositionTimeToSample;
import org.mp4parser.boxes.iso14496.part12.SampleDependencyTypeBox;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.SampleImpl;
import org.mp4parser.muxer.TrackMetaData;

public class Amf0Track extends AbstractTrack {
    SortedMap<Long, byte[]> d = new TreeMap<Long, byte[]>() {
    };
    private TrackMetaData e = new TrackMetaData();

    public List<CompositionTimeToSample.Entry> a() {
        return null;
    }

    public long[] b() {
        return null;
    }

    public List<SampleDependencyTypeBox.Entry> c() {
        return null;
    }

    public void close() throws IOException {
    }

    public SubSampleInformationBox d() {
        return null;
    }

    public String p() {
        return "data";
    }

    public Amf0Track(Map<Long, byte[]> map) {
        super(ActionMessageFormat0SampleEntryBox.f3784a);
        this.d = new TreeMap(map);
        this.e.b(new Date());
        this.e.a(new Date());
        this.e.a(1000);
        this.e.a("eng");
    }

    public List<Sample> l() {
        LinkedList linkedList = new LinkedList();
        for (byte[] wrap : this.d.values()) {
            linkedList.add(new SampleImpl(ByteBuffer.wrap(wrap)));
        }
        return linkedList;
    }

    public SampleDescriptionBox n() {
        SampleDescriptionBox sampleDescriptionBox = new SampleDescriptionBox();
        ActionMessageFormat0SampleEntryBox actionMessageFormat0SampleEntryBox = new ActionMessageFormat0SampleEntryBox();
        actionMessageFormat0SampleEntryBox.a(1);
        sampleDescriptionBox.a((Box) actionMessageFormat0SampleEntryBox);
        return sampleDescriptionBox;
    }

    public long[] m() {
        LinkedList linkedList = new LinkedList(this.d.keySet());
        Collections.sort(linkedList);
        long[] jArr = new long[linkedList.size()];
        for (int i = 0; i < linkedList.size(); i++) {
            jArr[i] = ((Long) linkedList.get(i)).longValue() - 0;
        }
        return jArr;
    }

    public TrackMetaData o() {
        return this.e;
    }
}
