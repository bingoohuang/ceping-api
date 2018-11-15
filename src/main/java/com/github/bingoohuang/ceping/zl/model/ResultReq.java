package com.github.bingoohuang.ceping.zl.model;

import com.github.bingoohuang.ceping.zl.impl.ZlUtil;
import lombok.*;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.TreeMap;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ResultReq {
    private String jobId;       // 职位ID, 客户系统的 项目/职位 ID, 例如10001，若没有 可传: -1
    private String personIds;   // 考试人的ID, 例如1,2,3

    public void addQueryParameters(HttpUrl.Builder urlBuilder) {
        val params = new TreeMap<String, String>() {{
            put("CompanyId", ZlUtil.get("companyId"));
            put("JobId", StringUtils.defaultIfEmpty(jobId, "-1"));
            put("PersonIds", personIds);
        }};

        params.forEach(urlBuilder::addQueryParameter);

        urlBuilder.addQueryParameter("Signature", ZlUtil.createSignature(params));
    }
}
