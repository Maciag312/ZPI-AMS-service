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
class CommonHelpers {
    private final MockMvc mockMvc

    @Autowired
    private ObjectMapper mapper

    CommonHelpers(MockMvc mockMvc) {
        this.mockMvc = mockMvc
    }

    def <T> ResultActions postRequest(T payload, String url) throws Exception {
        def value = mapper.writeValueAsString(payload);
        def result = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(value)
        )
        return result
    }

    def <T> ResultActions postRequest(String url) throws Exception {
        return mockMvc.perform(post(url))
    }

    def <T> ResultActions getRequest(String url) throws Exception {
        return mockMvc.perform(get(url))
    }

}
