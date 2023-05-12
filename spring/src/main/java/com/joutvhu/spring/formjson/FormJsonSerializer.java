package com.joutvhu.spring.formjson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FormJsonSerializer extends JsonSerializer<MultipartFile> {
    private List<MultipartFile> files;

    public FormJsonSerializer(List<MultipartFile> files) {
        this.files = files;
    }

    @Override
    public void serialize(MultipartFile multipartFile, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String key = "file_" + files.size();
        this.files.add(new MultipartFile() {
            @Override
            public String getName() {
                return key;
            }

            @Override
            public String getOriginalFilename() {
                return multipartFile.getOriginalFilename();
            }

            @Override
            public String getContentType() {
                return multipartFile.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return multipartFile.isEmpty();
            }

            @Override
            public long getSize() {
                return multipartFile.getSize();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return multipartFile.getBytes();
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return multipartFile.getInputStream();
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                multipartFile.transferTo(dest);
            }
        });
        jsonGenerator.writeString(key);
    }
}
