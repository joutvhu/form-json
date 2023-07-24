package com.joutvhu.spring.formjson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

public class FormJsonDeserializer extends JsonDeserializer<MultipartFile> {
    private MultipartRequest request;

    public FormJsonDeserializer(MultipartRequest request) {
        this.request = request;
    }

    @Override
    public MultipartFile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String name = jsonParser.getValueAsString();
        return name != null ? request.getFile(name) : null;
    }
}
