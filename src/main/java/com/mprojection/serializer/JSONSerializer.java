package com.mprojection.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public final class JSONSerializer implements ObjectSerializer {

    private static final String CONTENT_TYPE = "application/json";
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONSerializer.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public void serialize(OutputStream stream, Object o) {
        try {
            mapper.writeValue(stream, o);
        } catch (Exception e) {
            LOGGER.warn("Cannot serialize object.", e);
            throw new SerializerException("Cannot serialize object", e);
        }
    }

    @Override
    public String serialize(Object o) {
        OutputStream stream = new ByteArrayOutputStream();
        serialize(stream, o);
        return stream.toString();
    }

    @Override
    public <T> void serializeList(OutputStream stream, List<T> o, Class<T> c) {
        try {
            mapper.writeValue(stream, o);
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize list of objects.", e);
            throw new SerializerException("Cannot deserialize list of objects", e);
        }
    }

    @Override
    public <T> T deserialize(InputStream stream, Class<T> c) {
        try {
            return mapper.readValue(stream, c);
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize object", e);
            throw new SerializerException("Cannot deserialize object", e);
        }
    }

    @Override
    public <T> List<T> deserializeList(InputStream stream, Class<T> c) {
        try {
            return mapper.readValue(stream, mapper.getTypeFactory().constructCollectionType(List.class, c));
        } catch (Exception e) {
            LOGGER.warn("Cannot deserialize list of object.", e);
            throw new SerializerException("Cannot deserialize list of objects", e);
        }
    }

}
