package com.zpi

import com.jayway.jsonpath.JsonPath
import org.springframework.test.web.servlet.ResultActions

class ResultHelpers {

    static String attributeFromResult(String attribute, ResultActions result) throws UnsupportedEncodingException {
        def response = result.andReturn().getResponse()
        def value = JsonPath.read(response.getContentAsString(), String.format("\$.%s", attribute))
        return value.toString()
    }

    static String headerFromResult(String header, ResultActions result) {
        def response = result.andReturn().getResponse()
        return response.getHeader(header);
    }
}
