package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ActivityResult {
    @JSONField(name = "activity_list")
    private List<Activity> activities;
    @JSONField(name = "total_count")
    private int totalCount;
}
