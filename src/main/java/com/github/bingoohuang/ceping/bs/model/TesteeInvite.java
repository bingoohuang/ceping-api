package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class TesteeInvite {
    @JSONField(name = "activity_id")
    private String activityId;
    private String name;
    private String email;
    private String sn;
    @JSONField(name = "test_url")
    private String testUrl;
    @JSONField(name = "start_time")
    private String startTime;
    @JSONField(name = "end_time")
    private String endTime;

}
