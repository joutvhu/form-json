package com.joutvhu.spring.formjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.invoker.AbstractNamedValueArgumentResolver;
import org.springframework.web.service.invoker.HttpRequestValues;

import java.util.ArrayList;
import java.util.List;

public class FormJsonArgumentResolver extends AbstractNamedValueArgumentResolver {
    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        FormJson ann = parameter.getParameterAnnotation(FormJson.class);
        return ann != null ? new NamedValueInfo(ann.name(), false, null, "request form json", false) : null;
    }

    @SneakyThrows
    @Override
    protected void addRequestValue(String name, Object value, MethodParameter parameter, HttpRequestValues.Builder requestValues) {
        List<MultipartFile> files = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(MultipartFile.class, new FormJsonSerializer(files));
        mapper.registerModule(module);

        String data = mapper.writeValueAsString(value);
        if (data != null && StringUtils.hasText(data)) {
            requestValues.addRequestPart(name, data);
            for (MultipartFile file : files) {
                requestValues.addRequestPart(file.getName(), file);
            }
        }
    }
}
