package org.mp4parser.muxer.builder;

import org.mp4parser.Container;
import org.mp4parser.muxer.Movie;

public interface Mp4Builder {
    Container a(Movie movie);
}
