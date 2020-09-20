package org.magnum.dataup.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static String getUrlBaseForLocalServer() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String base =
                "http://"+request.getServerName()
                        + ((request.getServerPort() != 80) ? ":"+request.getServerPort() : "");
        return base;
    }
}
