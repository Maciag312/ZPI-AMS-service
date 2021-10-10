package com.zpi

import org.springframework.test.web.servlet.ResultActions
import org.springframework.util.MultiValueMap
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder

import java.util.stream.Collectors

class UriParamsResult {
    private final MultiValueMap<String, String> queryParams
    private final String path;

    UriParamsResult(ResultActions result) {
        def components = uriComponentsFromResult(result)
        queryParams = components.getQueryParams()
        path = components.getPath()
    }

    private static UriComponents uriComponentsFromResult(ResultActions result) {
        def uri = result.andReturn().getResponse().getHeader("Location")
        return UriComponentsBuilder.fromUriString(uri).build()
    }

    String getParam(String name) {
        return listToString(queryParams.get(name))
    }

    private static String listToString(List<String> list) {
        return list.stream()
                .map(s -> s)
                .collect(Collectors.joining(" "))
                .trim()
    }

    String getPath() {
        return path;
    }
}
