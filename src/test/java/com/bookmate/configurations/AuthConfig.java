package com.bookmate.configurations;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/authorization.properties"})
public interface AuthConfig extends Config {
    String validEmail();
    String validPassword();
}
