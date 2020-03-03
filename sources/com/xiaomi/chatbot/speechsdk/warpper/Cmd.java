package com.xiaomi.chatbot.speechsdk.warpper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public enum Cmd {
    AI_CMD_ASR("AI_CMD_ASR"),
    AI_CMD_TTS("AI_CMD_TTS");
    
    private String name;

    private Cmd(String str) {
        this.name = str;
    }

    public static Cmd getEnum(String str) {
        for (Cmd cmd : values()) {
            if (cmd.name.equalsIgnoreCase(str)) {
                return cmd;
            }
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        return this.name;
    }

    public static class Adapter extends TypeAdapter<Cmd> {
        public void write(JsonWriter jsonWriter, Cmd cmd) throws IOException {
            if (cmd == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(cmd.toString());
            }
        }

        public Cmd read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() != JsonToken.NULL) {
                return Cmd.getEnum(jsonReader.nextString());
            }
            jsonReader.nextNull();
            return null;
        }
    }
}
