package com.cyrillrx.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Serialization helper that uses {@link Gson} implementations to serialize and deserialize objects.
 *
 * @author Cyril Leroux
 *         Created 31/03/2017.
 */
public class Serializer {

    private static final Gson DEFAULT;
    private static final Gson PRETTY_PRINT;
    private static final Gson NO_HTML_ESCAPING;

    static {
        DEFAULT = new Gson();

        PRETTY_PRINT = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        NO_HTML_ESCAPING = new GsonBuilder()
                .disableHtmlEscaping()
                .create();
    }

    /** Serializes the given object with the default {@link Gson} implementation. */
    public String serialize(Object object) { return DEFAULT.toJson(object); }

    public static <T> T deserialize(String json, Class<T> clazz) {
        return DEFAULT.fromJson(json, clazz);
    }

    /** Serializes the given object without escaping characters such as < > etc. */
    public String serializeNoEscaping(Object object) { return NO_HTML_ESCAPING.toJson(object); }

    /** Serializes and pretty prints the given object. */
    public String prettyPrint(Object object) { return PRETTY_PRINT.toJson(object); }

}