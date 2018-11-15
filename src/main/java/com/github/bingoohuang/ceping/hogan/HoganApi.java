package com.github.bingoohuang.ceping.hogan;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.HttpUrl;
import org.apache.commons.lang3.StringUtils;

/**
 * 霍根测评(Hogan Evaluation) ref:https://wiki.mbalib.com/wiki/%E9%9C%8D%E6%A0%B9%E6%B5%8B%E8%AF%84
 * <p>
 * 霍根测评是指一套专业的专注于性能相关行为的个性评定工具，由Robert Hogan博士在20世纪70年代所发表著名的Hogan人格测评所发展出的，
 * 该测评工具被誉为第一个专门针对商业组织应用的人格测量工具。1987年他与Joyce Hogan合作成立了Hogan Assessments，
 * 直至今天仍在为众多企业和组织提供针对人格的测评服务，尤其是在领导力方面的测评。Robert Hogan博士认为领导者的人格对整个组织的影响重大，
 * 可以说决定了组织的命运与前途。企业或组织使用霍根测评是为了了解组织成员和领导者去激发出他们的长处、能力和关于他人局限性决定的绩效能力的自我意识。
 * 霍根领导力通过分析潜力、挑战、价值观等信息，让领导者清楚地理解他们的工作表现和核心驱动因素，并为他们提供战略层面的自我认识，令良好的领导变成伟大的领导。
 */
@Slf4j
public class HoganApi {
    /**
     * 创建Hogan测评自动登录链接。
     *
     * @param hoganUserId HAS(Hogan Assessment System)用户名
     * @param password    HAS密码
     * @param languageId  语言, en:English (American),zh: Chinese (Simplified)，更多见Hogan HTTP POST to Login Participant.doc文档。传递空值时，默认zh。
     * @return 自动登录的链接
     */
    public String createAutoLoginLink(String hoganUserId, String password, String languageId) {
        val url = "https://www.assessmentlink.com/coreparticipant/participant/Login.aspx";
        val link = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("UserId", hoganUserId)
                .addQueryParameter("Password", password)
                .addQueryParameter("LanguageID", StringUtils.defaultIfEmpty(languageId, "zh"))
                .toString();
        log.debug("Link: {}", link);

        return link;
    }
}

