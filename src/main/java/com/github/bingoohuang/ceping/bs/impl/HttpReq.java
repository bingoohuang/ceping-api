package com.github.bingoohuang.ceping.bs.impl;

import com.github.bingoohuang.utils.net.Url;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpReq {
    private final OAuthToken oauthToken = new OAuthToken();

    @SneakyThrows
    public String post(String url, PostParam... postParameters) {
        val postParam = encodeParameters(postParameters);
        log.debug("URL Post {}", url);
        log.debug("Post Params: {}", postParam);

        @Cleanup("disconnect") val con = getConnection(url);
        con.setDoInput(true);
        val auth = oauthToken.generateAuth("POST", url, postParameters);
        con.addRequestProperty("Authorization", auth);

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        val bytes = postParam.getBytes(StandardCharsets.UTF_8);
        con.setRequestProperty("Content-Length", "" + bytes.length);

        @Cleanup val osw = con.getOutputStream();
        osw.write(bytes);

        return dealResponse(con);
    }

    @SneakyThrows public String get(String url) {
        log.debug("URL Get {}", url);

        @Cleanup("disconnect") val con = getConnection(url);
        con.setDoInput(true);
        val auth = oauthToken.generateAuth("GET", url, null);
        con.addRequestProperty("Authorization", auth);

        return dealResponse(con);
    }


    @SneakyThrows
    private String dealResponse(HttpURLConnection con) {
        String res = asString(con);
        val responseCode = con.getResponseCode();
        if (responseCode != 200) throw new RuntimeException(responseCode + ", " + res);

        return res;
    }

    /**
     * Returns the response body as string.<br>
     * Disconnects the internal HttpURLConnection silently.
     */
    @SneakyThrows
    private String asString(HttpURLConnection con) {
        @Cleanup val is = con.getInputStream();

        val responseAsString = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
        log.debug("Response: {}", responseAsString);

        return responseAsString;
    }

    private static String encodeParameters(PostParam[] postParams) {
        val buf = new StringBuilder();
        for (int j = 0; j < postParams.length; j++) {
            if (j != 0) buf.append("&");

            val key = Url.encode(postParams[j].getKey());
            val val = Url.encode(postParams[j].getValue());
            buf.append(key).append("=").append(val);
        }

        return buf.toString();
    }

    @SneakyThrows
    private HttpURLConnection getConnection(String url) {
        val con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(50000);
        return con;
    }
}
