package com.acme.notificacionappmailsender.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile(Profiles.LOCAL)
@Configuration
@EnableTransactionManagement
public class LocalConf {

}
