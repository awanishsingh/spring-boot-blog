/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Cam Luc
 */
@Configuration
public class WebConfig {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
