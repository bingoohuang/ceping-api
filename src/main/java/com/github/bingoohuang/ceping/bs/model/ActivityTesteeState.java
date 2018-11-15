package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ActivityTesteeState {
    @JSONField(name = "activity_id")
    private String activityId;
    @JSONField(name = "testee_list")
    private List<TesteeSn> testeeList;
}
