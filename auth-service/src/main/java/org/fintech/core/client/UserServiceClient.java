package org.fintech.core.client;

import org.fintech.api.model.RegistrationRequest;
import org.fintech.api.model.SignInRequest;
import org.fintech.api.model.User;
import org.fintech.core.client.config.UserServiceClientConfiguration;
import org.fintech.core.model.ActivateUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "${integration.user-service.name}", configuration = UserServiceClientConfiguration.class)
public interface UserServiceClient {

    @PostMapping("/user")
    User registerUser(@RequestBody RegistrationRequest registrationRequest);

    @PostMapping("/user/activate")
    void activateUser(@RequestBody ActivateUser activateUser);

    @PostMapping("/user/login")
    User checkUser(@RequestBody SignInRequest signInRequest);

}
