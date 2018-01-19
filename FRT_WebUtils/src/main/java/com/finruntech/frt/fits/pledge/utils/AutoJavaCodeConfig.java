package com.finruntech.frt.fits.pledge.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yinan.zhang on 2018/1/12.
 */
@Configuration
public class AutoJavaCodeConfig {
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceDriver;

    @Value("${utils.autoGeneratedFile}")
    private String autoGeneratedFile;

    @Value("${utils.autoGeneratedTemplateDir}")
    private String autoGeneratedTemplateDir;


    @Bean
    public AutoGenerationJavaCode autoGenerationJavaCode(){
        return new AutoGenerationJavaCode(dataSourceUrl,dataSourceUsername,dataSourcePassword,dataSourceDriver,"",autoGeneratedFile,autoGeneratedTemplateDir);
    }


}
