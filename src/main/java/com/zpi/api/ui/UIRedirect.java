package com.zpi.api.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIRedirect {

    public static final String DASHBOARD = "/dashboard/**";

    private static final String INDEX_URI = "forward:/index.html";

    @RequestMapping(value = DASHBOARD)
    public String dashboard() { return INDEX_URI; }
}