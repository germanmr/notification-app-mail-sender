package com.acme.notificacionappmailsender.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile(Profiles.TEST)
@Configuration
//@MockBean({MessageRequestRepository.class})
public class TestConf {

}
