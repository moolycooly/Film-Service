package org.fintech.config.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Order(0)
@Slf4j
@RequiredArgsConstructor
public class ControllerLoggingInterceptor {

    private final static List<ErrorCode> PRINT_STACK_TRACE_ERROR_CODES = List.of(ErrorCode.INTERNAL_ERROR, ErrorCode.SERVICE_UNAVAILABLE);

    private final static List<PathPattern> EXCLUDE_REQUEST_PATHS = List.of(
            PathPatternParser.defaultInstance.parse("/v3/api-docs/**")
    );

    private final ObjectMapper objectMapper;

    @Around("org.fintech.config.logger.LoggerPointcuts.controllerPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String method = request.getMethod();
        String path = request.getServletPath();

        if (EXCLUDE_REQUEST_PATHS.stream().anyMatch(pattern -> pattern.matches(PathContainer.parsePath(path)))) {
            return joinPoint.proceed();
        }

        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        logBefore(joinPoint, methodName, method, path);
        try {
            Object result = joinPoint.proceed();
            logAfter(joinPoint, methodName, result);
            return result;
        } catch (Exception e) {
            logException(methodName, e);
            throw e;
        }
    }

    private void logBefore(ProceedingJoinPoint joinPoint, String methodName, String method, String path) {
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] methodArgs = joinPoint.getArgs();
        Map<String, Object> args = new LinkedHashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            args.put(parameterNames[i], paramToString(methodArgs[i]));
        }

        log.info("REQ {} {}; {}({})", method, path, methodName, args);
    }

    private void logException(String methodName, Exception e) {
        if (isPrintStackTrace(e)) {
            log.error("ERR method {} failed to execute {}", methodName, e.getMessage(), e);
        } else {
            log.error("ERR method {} failed to execute {}", methodName, e.getMessage());
        }
    }

    private void logAfter(ProceedingJoinPoint joinPoint, String methodName, Object result) {
        boolean isVoid = ((MethodSignature) joinPoint.getSignature()).getReturnType() == void.class;
        log.info(
                "ANS {} method {} completed successfully {}",
                isVoid ? "void" : "",
                methodName,
                isVoid ? "" : "with return value " + paramToString(result)
        );
    }

    private boolean isPrintStackTrace(Throwable e) {
        return !(e instanceof ServiceException serviceException)
                || PRINT_STACK_TRACE_ERROR_CODES.contains(serviceException.getErrorCode());
    }

    private String paramToString(Object methodArg) {
        try {
            return objectMapper.writeValueAsString(methodArg);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
