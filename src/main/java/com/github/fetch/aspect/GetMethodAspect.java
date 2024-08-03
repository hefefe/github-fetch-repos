package com.github.fetch.aspect;

import com.github.fetch.utils.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class GetMethodAspect {

    private final ObjectMapperUtil objectMapper;

    private static final String MESSAGE_FORMAT = "Method signature - %s\n" +
            "Execution time - [%s]\n" +
            "Duration - [%sms]\n" +
            "Input - %s\n" +
            "Output - %s";

    @Pointcut("within(com.github.fetch..*) " +
            "&& @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void restControllerMethodPointcut() {
    }

    @Around("restControllerMethodPointcut()")
    public Object logRequestAndResponse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        var args = Arrays.stream(proceedingJoinPoint.getArgs())
                .map(objectMapper::toJson)
                .toList();


        long timeStampBeforeExecution = System.currentTimeMillis();
        var result = proceedingJoinPoint.proceed();
        long duration = System.currentTimeMillis() - timeStampBeforeExecution;

        log.info(String.format(MESSAGE_FORMAT,
                proceedingJoinPoint.getSignature(),
                LocalDateTime.now(),
                duration,
                createArgumentsString(args),
                objectMapper.toJson(result)
        ));

        return result;
    }

    private String createArgumentsString(List<String> args) {
        return StringUtils.join(args, '\n');
    }

}
