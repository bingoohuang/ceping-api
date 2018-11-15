package com.github.bingoohuang.ceping.bs;

import com.alibaba.fastjson.JSON;
import com.github.bingoohuang.ceping.bs.impl.BsUtil;
import com.github.bingoohuang.ceping.bs.impl.HttpReq;
import com.github.bingoohuang.ceping.bs.impl.PostParam;
import com.github.bingoohuang.ceping.bs.model.*;
import lombok.val;
import org.joda.time.DateTime;

import java.util.List;

public class BsApi {
    private final HttpReq http = new HttpReq();
    private final String baseUrl;
    private final String accountParam;

    public BsApi() {
        this.baseUrl = "http://api.be" + "isenapp.com/assess/" + BsUtil.get("tenantId") + "/activity";
        this.accountParam = "?format=json&be" + "isen_account=" + BsUtil.get("bsAccount");
    }

    /**
     * 获取受测者作答状态.
     *
     * @param emails 英文逗号分隔的邮箱列表
     * @return 作答状态列表
     */
    public List<ActivityTesteeState> getActivityTesteeStates(String emails) {
        val url = baseUrl + "/testee/state" + accountParam + "&emails=" + emails;

        val response = http.get(url);
        return JSON.parseArray(response, ActivityTesteeState.class);
    }

    /**
     * 获取租户下所有测评活动列表，返回结果按照创建时间倒序排列，page_size最大100.
     *
     * @return ActivityResult
     */
    public ActivityResult getActivities() {
        val url = baseUrl + accountParam + "&page_index=1&page_size=100";

        val response = http.get(url);
        return JSON.parseObject(response, ActivityResult.class);
    }

    /**
     * 邀请受测者(不发送邮件和短信).
     *
     * @param activityId 活动id
     * @param endTime    通行证结束时间
     * @param testees    受测者列表
     * @return TesteeInviteResult
     */

    public TesteeInviteResult inviteTestees(String activityId, DateTime endTime, List<Testee> testees) {
        val fmt = "yyyy-MM-dd%20HH:mm:ss";
        val url = baseUrl + "/testee/invite" + accountParam + "&activity_id=" + activityId
                + "&start_time=" + DateTime.now().toString(fmt) + "&end_time=" + endTime.toString(fmt);

        val response = http.post(url, new PostParam("testee_list", JSON.toJSONString(testees)));
        return JSON.parseObject(response, TesteeInviteResult.class);
    }

    /**
     * 通过通行证号和邮箱获取租户下受测者作答结果.
     *
     * @param activityId 活动id
     * @param emailSns   邮箱和通行证对象列表
     * @return 活动测试结果列表
     */
    public List<ActivityTesteeResult> getTesteeResults(String activityId, List<EmailSn> emailSns) {
        val response = getTesteeResultsAsString(activityId, emailSns);
        return JSON.parseArray(response, ActivityTesteeResult.class);
    }

    /**
     * 通过通行证号和邮箱获取租户下受测者作答结果.
     *
     * @param activityId 活动id
     * @param emailSns   邮箱和通行证对象列表
     * @return 活动测试结果列表响应报文
     */
    public String getTesteeResultsAsString(String activityId, List<EmailSn> emailSns) {
        val url = baseUrl + "/testee/result/sn_email" + accountParam + "&activity_id=" + activityId;

        return http.post(url, new PostParam("email_sn_list", JSON.toJSONString(emailSns)));
    }

}