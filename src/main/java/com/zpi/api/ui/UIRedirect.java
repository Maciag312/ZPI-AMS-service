package com.zpi.api.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIRedirect {

    private static final String ORGANIZATION_URI = "/organization/*";
    public static final String ALLOW_URI =  "/allow/**";
    public static final String SIGN_IN_URI =  ORGANIZATION_URI + "/signin/**";
    public static final String SIGN_UP_URI =  ORGANIZATION_URI + "/signup/**";
    public static final String DASHBOARD =  ORGANIZATION_URI + "/dashboard/**";

    private static final String INDEX_URI = "forward:/index.html";

    @RequestMapping(value = ALLOW_URI)
    public String allow() {
        return INDEX_URI;
    }

    @RequestMapping(value = SIGN_IN_URI)
    public String signin() {
        return INDEX_URI;
    }

    @RequestMapping(value = SIGN_UP_URI)
    public String signup() {
        return INDEX_URI;
    }

    @RequestMapping(value = DASHBOARD)
    public String dashboard() { return INDEX_URI; }
}