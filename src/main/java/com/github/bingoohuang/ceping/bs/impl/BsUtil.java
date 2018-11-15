package com.github.bingoohuang.ceping.bs.impl;

import com.github.bingoohuang.utils.lang.Classpath;
import com.github.bingoohuang.utils.net.Url;
import lombok.val;

import java.util.Map;
import java.util.Properties;

public interface BsUtil {
    Properties env = Classpath.loadEnvProperties("bs-config.properties");

    static String get(String name) {
        return env.getProperty(name);
    }

    static void appendQueryParams(String url, Map<String, String> signBaseParams) {
        int queryStart = url.indexOf("?");
        if (-1 == queryStart) return;

        String[] queryStrs = url.substring(queryStart + 1).split("&");
        for (String query : queryStrs) {
            String[] split = query.split("=");
            signBaseParams.put(Url.decode(split[0]), split.length == 2 ? Url.decode(split[1]) : "");
        }
    }

    static String encodeParams(Map<String, String> postParams, String splitter, boolean quoteValue) {
        val buf = new StringBuilder();
        for (val param : postParams.entrySet()) {
            if (buf.length() != 0) buf.append(splitter);

            buf.append(oauthEncode(param.getKey()));
            buf.append("=");
            if (quoteValue) buf.append("\"");
            buf.append(oauthEncode(param.getValue()));
            if (quoteValue) buf.append("\"");
        }

        return buf.toString();
    }

    static String oauthEncode(String value) {
        return Url.encode(value)
                .replace("+", "%20")
                .replace("*", "%2A")
                .replace("%7E", "~");
    }


    static String constructRequestURL(String url) {
        int index = url.indexOf("?");
        if (-1 != index) url = url.substring(0, index);

        int slashIndex = url.indexOf("/", 8);
        String baseURL = url.substring(0, slashIndex).toLowerCase();
        int colonIndex = baseURL.indexOf(":", 8);
        if (-1 != colonIndex) {
            // url contains port number
            if (baseURL.startsWith("impl://") && baseURL.endsWith(":80")) {
                // impl default port 80 MUST be excluded
                baseURL = baseURL.substring(0, colonIndex);
            } else if (baseURL.startsWith("https://") && baseURL.endsWith(":443")) {
                // impl default port 443 MUST be excluded
                baseURL = baseURL.substring(0, colonIndex);
            }
        }

        return baseURL + url.substring(slashIndex);
    }
}
