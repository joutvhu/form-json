package com.joutvhu.spring.formjson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class FormJsonDeserializer extends JsonDeserializer<MultipartFile> {
    private List<MultipartFile> files;

    public FormJsonDeserializer(List<MultipartFile> files) {
        this.files = files;
    }

    @Override
    public MultipartFile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String name = jsonParser.getValueAsString();
        if (name != null) {
            for (MultipartFile file : files) {
                if (name.equals(file.getName())) {
                    return file;
                }
            }
        }
        return null;
    }
}
