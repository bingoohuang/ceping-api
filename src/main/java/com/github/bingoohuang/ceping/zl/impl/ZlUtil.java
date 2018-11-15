package com.github.bingoohuang.ceping.zl.impl;

import com.github.bingoohuang.utils.codec.Md5;
import com.github.bingoohuang.utils.lang.Classpath;
import lombok.val;

import java.util.Map;
import java.util.Properties;

public interface ZlUtil {
    Properties env = Classpath.loadEnvProperties("zl-config.properties");

    static String get(String name) {
        return env.getProperty(name);
    }

    // 所有参数按 照参数名字母序排序，再加secret后小写md5。
    // 即md5(CompanyId+Email+JobId+JobName+Name+PaperIds+PersonId+Secret)
    static String createSignature(Map<String, String> params) {
        val sb = new StringBuilder();
        params.forEach((k, v) -> sb.append(v));
        sb.append(get("secret"));

        val original = sb.toString();
        return Md5.md5Hex(original);
    }
}
