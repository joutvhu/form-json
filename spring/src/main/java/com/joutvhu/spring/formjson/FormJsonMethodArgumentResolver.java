package com.joutvhu.spring.formjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.List;

public class FormJsonMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {
    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        FormJson ann = parameter.getParameterAnnotation(FormJson.class);
        return ann != null ? new NamedValueInfo(ann.name(), false, null) : null;
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        MultipartRequest multipartRequest = request.getNativeRequest(MultipartRequest.class);
        if (multipartRequest != null) {
            String dataValue = request.getParameter(name);
            if (dataValue != null) {
                Class<?> modelType = parameter.getParameterType();
                List<MultipartFile> files = multipartRequest.getFiles(name);

                ObjectMapper mapper = new ObjectMapper();
                SimpleModule module = new SimpleModule();
                module.addDeserializer(MultipartFile.class, new FormJsonDeserializer(files));
                mapper.registerModule(module);

                return mapper.readValue(dataValue, modelType);
            }
        }
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(FormJson.class)) {
            FormJson ann = parameter.getParameterAnnotation(FormJson.class);
            return (ann != null && StringUtils.hasText(ann.name()));
        }
        return false;
    }
}
