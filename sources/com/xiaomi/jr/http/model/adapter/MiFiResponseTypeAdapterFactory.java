package com.xiaomi.jr.http.model.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.xiaomi.jr.http.model.MiFiResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MiFiResponseTypeAdapterFactory<V> implements TypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = ((ParameterizedType) typeToken.getType()).getActualTypeArguments()[0];
        return a(gson.getDelegateAdapter(this, typeToken), gson.getAdapter(TypeToken.get(type)), type);
    }

    private TypeAdapter<MiFiResponse<V>> a(final TypeAdapter<MiFiResponse<V>> typeAdapter, final TypeAdapter<V> typeAdapter2, final Type type) {
        return new TypeAdapter<MiFiResponse<V>>() {
            /* renamed from: a */
            public void write(JsonWriter jsonWriter, MiFiResponse<V> miFiResponse) throws IOException {
                typeAdapter.write(jsonWriter, miFiResponse);
            }

            /* renamed from: a */
            public MiFiResponse<V> read(JsonReader jsonReader) throws IOException {
                try {
                    MiFiResponse<V> miFiResponse = new MiFiResponse<>();
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        String nextName = jsonReader.nextName();
                        char c2 = 65535;
                        int hashCode = nextName.hashCode();
                        if (hashCode != -1867169789) {
                            if (hashCode != 3059181) {
                                if (hashCode != 96784904) {
                                    if (hashCode == 111972721) {
                                        if (nextName.equals("value")) {
                                            c2 = 3;
                                        }
                                    }
                                } else if (nextName.equals("error")) {
                                    c2 = 1;
                                }
                            } else if (nextName.equals("code")) {
                                c2 = 0;
                            }
                        } else if (nextName.equals("success")) {
                            c2 = 2;
                        }
                        switch (c2) {
                            case 0:
                                miFiResponse.a(jsonReader.nextInt());
                                break;
                            case 1:
                                miFiResponse.a(jsonReader.nextString());
                                break;
                            case 2:
                                miFiResponse.a(Boolean.valueOf(jsonReader.nextBoolean()));
                                break;
                            case 3:
                                if (jsonReader.peek() == JsonToken.STRING && !type.equals(String.class)) {
                                    miFiResponse.a(typeAdapter2.fromJson(jsonReader.nextString()));
                                    break;
                                } else {
                                    miFiResponse.a(typeAdapter2.read(jsonReader));
                                    break;
                                }
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    jsonReader.endObject();
                    return miFiResponse;
                } catch (JsonIOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }
}
