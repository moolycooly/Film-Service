package org.fintech.config.logger;

import org.aspectj.lang.annotation.Pointcut;

public class LoggerPointcuts {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerPointcut() {
    }

}
