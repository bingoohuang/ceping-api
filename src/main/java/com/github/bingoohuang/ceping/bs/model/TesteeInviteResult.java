package com.github.bingoohuang.ceping.bs.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class TesteeInviteResult {
    @JSONField(name = "testee_invite_result_list")
    private List<TesteeInvite> testeeInvites;
}
