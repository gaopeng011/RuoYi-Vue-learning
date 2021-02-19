package com.ruoyi.project.shop;

import com.ruoyi.common.task.Task;
import com.ruoyi.common.utils.GrouponConstant;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.shop.common.utils.OrderUtil;
import com.ruoyi.project.shop.domain.LitemallGroupon;
import com.ruoyi.project.shop.domain.LitemallGrouponRules;
import com.ruoyi.project.shop.domain.LitemallOrder;
import com.ruoyi.project.shop.service.impl.LitemallGrouponRulesServiceImpl;
import com.ruoyi.project.shop.service.impl.LitemallGrouponServiceImpl;
import com.ruoyi.project.shop.service.impl.LitemallOrderServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GrouponRuleExpiredTask extends Task {
    private final Log logger = LogFactory.getLog(GrouponRuleExpiredTask.class);
    private long grouponRuleId = -1;

    public GrouponRuleExpiredTask(Long grouponRuleId, long delayInMilliseconds){
        super("GrouponRuleExpiredTask-" + grouponRuleId, delayInMilliseconds);
        this.grouponRuleId = grouponRuleId;
    }

    @Override
    public void run() {
        logger.info("系统开始处理延时任务---团购规则过期---" + this.grouponRuleId);

        LitemallOrderServiceImpl orderService = SpringUtils.getBean(LitemallOrderServiceImpl.class);
        LitemallGrouponServiceImpl grouponService = SpringUtils.getBean(LitemallGrouponServiceImpl.class);
        LitemallGrouponRulesServiceImpl grouponRulesService = SpringUtils.getBean(LitemallGrouponRulesServiceImpl.class);

        LitemallGrouponRules grouponRules = grouponRulesService.selectLitemallGrouponRulesById(grouponRuleId);
        if(grouponRules == null){
            return;
        }
        if(!grouponRules.getStatus().equals(GrouponConstant.RULE_STATUS_ON)){
            return;
        }

        // 团购活动取消
        grouponRules.setStatus(GrouponConstant.RULE_STATUS_DOWN_EXPIRE);
        grouponRulesService.updateLitemallGrouponRules(grouponRules);

        LitemallGroupon litemallGroupon=new LitemallGroupon();
        litemallGroupon.setRulesId(grouponRuleId);
        List<LitemallGroupon> grouponList = grouponService.selectLitemallGrouponList(litemallGroupon);
        // 用户团购处理
        for(LitemallGroupon groupon : grouponList){
            Short status = groupon.getStatus();
            LitemallOrder order = orderService.selectLitemallOrderById(groupon.getOrderId());
            if(status.equals(GrouponConstant.STATUS_NONE)){
                groupon.setStatus(GrouponConstant.STATUS_FAIL);
                grouponService.updateLitemallGroupon(groupon);
            }
            else if(status.equals(GrouponConstant.STATUS_ON)){
                // 如果团购进行中
                // (1) 团购设置团购失败等待退款状态
                groupon.setStatus(GrouponConstant.STATUS_FAIL);
                grouponService.updateLitemallGroupon(groupon);
                // (2) 团购订单申请退款
                if(OrderUtil.isPayStatus(order)) {
                    order.setOrderStatus(OrderUtil.STATUS_REFUND);
                    orderService.updateLitemallOrder(order);
                }
            }
        }
        logger.info("系统结束处理延时任务---团购规则过期---" + this.grouponRuleId);
    }
}
