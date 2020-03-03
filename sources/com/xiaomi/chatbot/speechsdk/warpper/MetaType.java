package com.xiaomi.chatbot.speechsdk.warpper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public enum MetaType {
    TRANSACTION_BEGIN("TRANSACTION_BEGIN"),
    TRANSACTION_END("TRANSACTION_END"),
    DATA_TTS("DATA_TTS"),
    RESULT_ASR_PARTIAL("RESULT_ASR_PARTIAL"),
    RESULT_ASR_FINAL("RESULT_ASR_FINAL"),
    RESULT_TTS_END("RESULT_TTS_END"),
    ERROR("ERROR"),
    SERVICE_EVENT("SERVICE_EVENT"),
    RESULT_ASR_VAD("RESULT_ASR_VAD");
    
    private String name;

    private MetaType(String str) {
        this.name = str;
    }

    public static MetaType getEnum(String str) {
        for (MetaType metaType : values()) {
            if (metaType.name.equalsIgnoreCase(str)) {
                return metaType;
            }
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        return this.name;
    }

    public static class Adapter extends TypeAdapter<MetaType> {
        public void write(JsonWriter jsonWriter, MetaType metaType) throws IOException {
            if (metaType == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(metaType.toString());
            }
        }

        public MetaType read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return MetaType.getEnum(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }
    }
}
