package com.example.assignmentproject.utils;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParse {

    private static final Moshi moshi = new Moshi.Builder()
            .add(new KotlinJsonAdapterFactory())
            .build();

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return moshi.adapter(clazz).fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> toList(String jsonArrayText, Class<T> clazz) {
        if (jsonArrayText == null || jsonArrayText.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return (List<T>) moshi.adapter(Types.newParameterizedType(List.class, clazz)).fromJson(jsonArrayText);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static String toJson(Object object) {
        return moshi.adapter(Object.class).toJson(object);
    }

    public static String toJsons(Object... objects) {
        StringBuilder builder = new StringBuilder("[");
        for (Object object : objects) {
            if (object != null) {
                String json = toJson(object);
                if (builder.length() > 1) {
                    builder.append(",");
                }
                builder.append(json);
            }
        }
        builder.append("]");
        return builder.toString();
    }

}

