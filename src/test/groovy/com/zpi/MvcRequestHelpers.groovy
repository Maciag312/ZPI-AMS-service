package com.zpi

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Component
class MvcRequestHelpers {
    private final MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    MvcRequestHelpers(MockMvc mockMvc) {
        this.mockMvc = mockMvc
    }

    def <T> ResultActions postRequest(T payload, String url) throws Exception {
        def object = mapper.writeValueAsString(payload);
        return mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(object)
        )
    }

    ResultActions getRequest(String url) throws Exception {
        return mockMvc.perform(get(url))
    }
}
