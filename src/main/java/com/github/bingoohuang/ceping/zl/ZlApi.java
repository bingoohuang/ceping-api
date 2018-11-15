package com.github.bingoohuang.ceping.zl;

import com.alibaba.fastjson.JSON;
import com.github.bingoohuang.ceping.zl.model.LoginReq;
import com.github.bingoohuang.ceping.zl.model.ResultReq;
import com.github.bingoohuang.ceping.zl.model.Score;
import com.github.bingoohuang.utils.net.OkHttp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * 智联测评API.
 */
@Slf4j
public class ZlApi {

    /**
     * 直接登录并进入答题(以链接形式直接打开).
     *
     * @param req 请求数据
     * @return 跳转链接
     */
    public String createLoginLink(LoginReq req) {
        val url = "http://eas.zha" + "opin.com/Assessment/Api/OpenApiLogin.ashx";
        val urlBuilder = HttpUrl.get(url).newBuilder();
        req.addQueryParameters(urlBuilder);

        val link = urlBuilder.toString();
        log.debug("Link: {}", link);

        return link;
    }

    /**
     * 获取成绩。
     *
     * @param req 请求数据
     * @return 成绩对象
     */
    public Score getResultScore(ResultReq req) {
        val responseString = getResultScoreAsString(req);

        return JSON.parseObject(responseString, Score.class);
    }

    /**
     * 获取成绩。
     *
     * @param req 请求数据
     * @return 响应报文
     */
    @SneakyThrows
    public String getResultScoreAsString(ResultReq req) {
        val url = "http://eas.zha" + "opin.com/Assessment/Api/OpenApiResult.ashx";
        val urlBuilder = HttpUrl.get(url).newBuilder();
        req.addQueryParameters(urlBuilder);

        val link = urlBuilder.toString();
        log.debug("Link: {}", link);
        val request = new Request.Builder().url(link).get().build();
        val response = OkHttp.client.newCall(request).execute();
        val responseString = response.body().string();
        log.debug("Response: {}", responseString);
        return responseString;
    }
}
