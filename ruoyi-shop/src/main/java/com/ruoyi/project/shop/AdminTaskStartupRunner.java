package com.ruoyi.project.shop;

import com.ruoyi.common.task.TaskService;
import com.ruoyi.common.utils.GrouponConstant;
import com.ruoyi.project.shop.domain.LitemallGrouponRules;
import com.ruoyi.project.shop.service.ILitemallGrouponRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class AdminTaskStartupRunner implements ApplicationRunner {

    @Autowired
    private ILitemallGrouponRulesService rulesService;
    @Autowired
    private TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LitemallGrouponRules litemallGrouponRules=new LitemallGrouponRules();
        litemallGrouponRules.setStatus(GrouponConstant.RULE_STATUS_ON);
        List<LitemallGrouponRules> grouponRulesList = rulesService.selectLitemallGrouponRulesList(litemallGrouponRules);
        for(LitemallGrouponRules grouponRules : grouponRulesList){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expire =  grouponRules.getExpireTime();
            if(expire.isBefore(now)) {
                // 已经过期，则加入延迟队列
                taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), 0));
            }
            else{
                // 还没过期，则加入延迟队列
                long delay = ChronoUnit.MILLIS.between(now, expire);
                taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), delay));
            }
        }
    }
}
