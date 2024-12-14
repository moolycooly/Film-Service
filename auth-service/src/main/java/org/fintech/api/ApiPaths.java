package org.fintech.api;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiPaths {

    public static final String REGISTRATION = "/auth/sign-up";

    public static final String AUTHORIZATION = "/auth/sign-in";

    public static final String ACTIVATE_ACCOUNT = "/auth/activate";

    public static final String TOKEN_VALIDATE = "/auth/token/validate";
}
