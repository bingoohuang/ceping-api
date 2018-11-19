package com.github.bingoohuang.ceping.bs;

import com.github.bingoohuang.ceping.bs.model.EmailSn;
import com.github.bingoohuang.ceping.bs.model.Testee;
import com.google.common.collect.Lists;
import lombok.val;
import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;

public class BsApiTest {
    @Test @Ignore
    public void getActivities() {
        // 第一步.获取租户下所有测评活动列表，返回结果按照创建时间倒序排列，page_size最大100
        val activities = new BsApi().getActivities();
        System.out.println(activities);
    }

    @Test @Ignore
    public void inviteTestees() {
        // 第二步.邀请受测者(不发送邮件和短信),一次最多邀请1000人
        val userList = Lists.newArrayList(new Testee("d@r.com"));
        val inviteTesteeResult = new BsApi().inviteTestees("387249", DateTime.now().plusDays(30), userList);

        System.out.println(inviteTesteeResult);
    }

    @Test @Ignore
    public void getActivityTesteeStates() {
        // 通过邮箱取作答状态，再根据状态获取作答结果，每次最多50个人的结果
        val activityTesteeStates = new BsApi().getActivityTesteeStates("d@r.com");
        activityTesteeStates.forEach(i -> i.getTesteeList().forEach(j -> j.getSns().forEach(k -> {
            if (k.getState().equals("3"))/*3:已完成*/ {
                System.out.println((new EmailSn(j.getEmail(), k.getSn())));
            }
        })));
    }

    @Test @Ignore
    public void result() {
        val result = new BsApi().getTesteeResults("387249", Lists.newArrayList(
                new EmailSn("d@r.com", "1701823904227")));
        System.out.println(result);
    }

}
