package com.jdfcc.logannotation.aop;



import com.alibaba.fastjson.JSONObject;
import com.jdfcc.logannotation.entity.MyLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.logging.Logger;

import static com.jdfcc.logannotation.constants.RabbitMqConstants.LogExchange;
import static com.jdfcc.logannotation.constants.RabbitMqConstants.LogRoutingKey;


/**
 * @author Jdfcc
 * @Description 通过日志注解打印日志
 * @DateTime 2023/6/26 10:06
 */
@Aspect
@Component
@Order(0)
public class LogAop {

    private static Logger logger = Logger.getLogger(String.valueOf(LogAop.class));

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }



    @Around("@annotation(com.jdfcc.logannotation.annotations.LogAnnotation)")
    public Object logPointCut(ProceedingJoinPoint pjp) {
        System.out.println("进来了");
//        String filePath = "folder/example.txt";
        long startTime = System.currentTimeMillis();
        Object obj = null;
        //
        try {
            obj = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        LocalTime currentTime = LocalTime.now();
        Integer minute = currentTime.getMinute();
        Integer second = currentTime.getSecond();

        Signature signature = pjp.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

        String time = hour + ":" + minute + ":" + second + "  ";
        String value = time +"method: "+methodName+ " inputArgs" + ": " + JSONObject.toJSONString(pjp.getArgs()) + " " + " " + " outputArgs" + ": " + obj.toString();
//        writeFile(JSONObject.toJSONString(pjp.getArgs()),obj.toString());


        MyLog myLog = new MyLog();
        myLog.setValue(value);
        Long endTime = System.currentTimeMillis();
        myLog.setTime(endTime - startTime);
        Object o = JSONObject.toJSON(myLog);

        rabbitTemplate.convertAndSend(LogExchange, LogRoutingKey, o.toString());

        return obj;

    }

}
