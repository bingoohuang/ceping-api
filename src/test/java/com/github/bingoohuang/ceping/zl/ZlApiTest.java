package com.github.bingoohuang.ceping.zl;

import com.github.bingoohuang.ceping.zl.model.LoginReq;
import com.github.bingoohuang.ceping.zl.model.ResultReq;
import com.github.bingoohuang.ceping.zl.model.Score;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ZlApiTest {

    @Test
    public void createAnswerLink() {
        String link = new ZlApi().createLoginLink(LoginReq.builder()
                .personId("h40685")
                .name("兵")
                .email("b@dingtalk.com")
                // 3577 职业行为风险测验
                // 2998 职业价值观测验
                // 238  情绪管理能力测验
                // 1662 领导力
                .paperIds("3577|2998|238")
                .jobName("软件架构师")
                .jobId("1000")
                .build());

        assertThat(link).isNotEmpty();
    }

    @Test
    public void getResultScore() {
        Score score = new ZlApi().getResultScore(ResultReq.builder()
                .personIds("h40685")
                .jobId("1000")
                .build());

        assertThat(score).isNotNull();
    }
}

