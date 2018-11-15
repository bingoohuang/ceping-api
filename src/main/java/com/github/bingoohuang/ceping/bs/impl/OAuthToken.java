package com.github.bingoohuang.ceping.bs.impl;

import com.github.bingoohuang.westid.WestId;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.github.bingoohuang.ceping.bs.impl.BsUtil.*;

@Slf4j
class OAuthToken {
    private final String token;
    private final String consumerKey;
    private final SecretKeySpec secretKeySpec;

    OAuthToken() {
        this.token = BsUtil.get("token");
        this.consumerKey = BsUtil.get("consumerKey");
        val oauthSignature = BsUtil.get("consumerSecret") + "&" + BsUtil.get("tokenSecret");
        this.secretKeySpec = new SecretKeySpec(oauthSignature.getBytes(), "HmacSHA1");
    }

    @SneakyThrows private String generateSignature(String data) {
        val mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        val byteHMAC = mac.doFinal(data.getBytes());
        return DatatypeConverter.printBase64Binary(byteHMAC);
    }

    String generateAuth(String method, String url, PostParam[] params) {
        if (null == params) params = new PostParam[0];

        val oauthHeaders = new HashMap<String, String>() {{
            put("oauth_consumer_key", consumerKey);
            put("oauth_signature_method", "HMAC-SHA1");
            put("oauth_timestamp", String.valueOf(System.currentTimeMillis() / 1000));
            put("oauth_nonce", String.valueOf(WestId.next()));
            put("oauth_version", "1.0");
            put("oauth_token", token);
        }};

        Map<String, String> signBaseParams = Maps.newTreeMap();
        signBaseParams.putAll(oauthHeaders);

        Stream.of(params).forEach(x -> signBaseParams.put(x.getKey(), x.getValue()));
        appendQueryParams(url, signBaseParams);

        val constructRequestURL = BsUtil.constructRequestURL(url);
        val str = oauthEncode(encodeParams(signBaseParams, "&", false));
        val oauthBaseString = method + "&" + oauthEncode(constructRequestURL) + "&" + str;
        log.debug("OAuthBaseString: {}", oauthBaseString);

        val signature = generateSignature(oauthBaseString);
        oauthHeaders.put("oauth_signature", signature);
        val oauth = "OAuth " + encodeParams(oauthHeaders, ",", true);
        log.debug("OAuth: {}", oauth);
        return oauth;
    }
}
