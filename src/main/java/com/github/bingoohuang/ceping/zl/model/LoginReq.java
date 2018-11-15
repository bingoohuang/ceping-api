package com.github.bingoohuang.ceping.zl.model;

import com.github.bingoohuang.ceping.zl.impl.ZlUtil;
import lombok.*;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginReq {
    private String personId;    // 客户系统内的用户ID，用于唯一标示用户，必填
    private String name;        // 用户姓名 张三(所有中文要使用utf8来urlencode，但是参与签名的是encode之前的值)，必填
    private String email;       // 用户email，必填
    private String paperIds;    // 试卷ID,由智联提供(见固化试卷清单), 可传多个用|间隔, 例如237|238，必填
    private String jobName;     // 职位名称, 客户系统的 项目/职位 名称, 例如Java工程师，，必填
    private String jobId;       // 职位ID, 客户系统的 项目/职位 ID, 例如10001，若没有 可传: -1
    private String extend;      // 客户自定义扩展Json格式数据，成绩接口会原样返回，非必填
    private boolean isMobile;   // 是否移动端
    private String redirectUrl; // 用户作答完成后跳转的地址

    public void addQueryParameters(HttpUrl.Builder urlBuilder) {
        val params = new TreeMap<String, String>() {{
            put("CompanyId", ZlUtil.get("companyId"));
            put("Email", email);
            put("JobId", StringUtils.defaultIfEmpty(jobId, "-1"));
            put("JobName", jobName);
            put("Name", name);
            put("PaperIds", paperIds);
            put("PersonId", personId);
        }};

        params.forEach(urlBuilder::addQueryParameter);
        urlBuilder.addQueryParameter("Signature", ZlUtil.createSignature(params));

        if (isNotEmpty(extend)) urlBuilder.addQueryParameter("Extend", extend);
        if (isMobile) urlBuilder.addQueryParameter("IsMobile", "1");
        if (isNotEmpty(redirectUrl)) urlBuilder.addQueryParameter("RedirectUrl", redirectUrl);
    }
}
