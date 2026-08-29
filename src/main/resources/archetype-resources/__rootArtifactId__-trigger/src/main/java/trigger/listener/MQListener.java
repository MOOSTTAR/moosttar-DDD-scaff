package ${package}.trigger.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * RocketMQ 接收消息
 * @author moosttar
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "moosttar-mq", consumerGroup = "moosttar-group")
public class MQListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("接收到RocketMQ消息 {}", s);
    }

}
