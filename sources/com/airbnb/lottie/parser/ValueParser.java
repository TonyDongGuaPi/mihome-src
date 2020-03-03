package com.airbnb.lottie.parser;

import android.util.JsonReader;
import java.io.IOException;

interface ValueParser<V> {
    V parse(JsonReader jsonReader, float f) throws IOException;
}
