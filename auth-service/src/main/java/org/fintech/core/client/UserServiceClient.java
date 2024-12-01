package org.fintech.core.client;

import org.fintech.api.model.RegistrationRequest;
import org.fintech.api.model.SignInRequest;
import org.fintech.api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/user")
    User registerUser(@RequestBody RegistrationRequest registrationRequest);

    @GetMapping("/user/login")
    User checkUser(@RequestParam SignInRequest signInRequest);
}
