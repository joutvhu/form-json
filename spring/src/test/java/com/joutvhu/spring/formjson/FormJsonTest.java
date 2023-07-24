package com.joutvhu.spring.formjson;

import com.joutvhu.spring.formjson.model.DemoModel;
import com.joutvhu.spring.formjson.service.DemoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FormJsonApplication.class)
@AutoConfigureMockMvc
public class FormJsonTest {
    @MockBean
    private DemoService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testController() throws Exception {
        MockMultipartFile file_0 = new MockMultipartFile("file_0", "hello00.jpg", MediaType.IMAGE_JPEG_VALUE, "Hello, World 00!".getBytes());
        MockMultipartFile file_1 = new MockMultipartFile("file_1", "hello01.jpg", MediaType.IMAGE_JPEG_VALUE, "Hello, World 01!".getBytes());
        MockMultipartFile file_2 = new MockMultipartFile("file_2", "hello02.jpg", MediaType.IMAGE_JPEG_VALUE, "Hello, World 02!".getBytes());

        MockPart data = new MockPart("data", "{\"name\":\"hello\",\"file\":\"file_0\",\"children\":[{\"id\":0,\"file\":\"file_1\"},{\"id\":1,\"file\":\"file_2\"}]}".getBytes());

        Mockito.when(service.submit(Mockito.any())).then(invocation -> {
            DemoModel model = invocation.getArgument(0, DemoModel.class);
            if (model == null)
                return false;
            if (!"hello".equals(model.getName()))
                return false;
            if (model.getFile() == null || !"hello00.jpg".equals(model.getFile().getOriginalFilename()))
                return false;
            if (model.getChildren() == null || model.getChildren().size() != 2)
                return false;
            if (model.getChildren().get(0) == null || model.getChildren().get(0).getFile() == null ||
                    !"hello01.jpg".equals(model.getChildren().get(0).getFile().getOriginalFilename()))
                return false;
            if (model.getChildren().get(1) == null || model.getChildren().get(1).getFile() == null ||
                    !"hello02.jpg".equals(model.getChildren().get(1).getFile().getOriginalFilename()))
                return false;
            return true;
        });

        mvc.perform(MockMvcRequestBuilders.multipart("/demo")
                        .file(file_0)
                        .file(file_1)
                        .file(file_2)
                        .part(data)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }
}
