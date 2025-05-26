package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;
    /**
     * 每天分钟执行一次,处理超时订单
     */
//    @Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "1/5 * * * * ?")
    public void processTimeoutOrders() {
        log.info("定时处理超时订单：{}", LocalDateTime.now());
        // 业务逻辑
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15); // 15分钟前的时间
        List<Orders> orders = orderMapper.getByStatusAndOrderTime(Orders.PENDING_PAYMENT, time);
        if (orders!= null && orders.size() > 0) {
            for (Orders order : orders) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }
    }

    /**
     * 每天凌晨一点点执行一次,处理派送中订单
     */
//    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "0/5 * * * * ?")
    public  void processDeliveryOrders() {
        log.info("定时处理派送中国的订单：{}",LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60); // 1小时前的时间
        List<Orders> orders =orderMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, time);
        if (orders!= null && orders.size() > 0) {
            for (Orders order : orders) {
                order.setStatus(Orders.COMPLETED); //  完成
                orderMapper.update(order);
            }
        }
    }
}
