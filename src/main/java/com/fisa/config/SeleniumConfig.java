package com.fisa.config;

import com.fisa.driver.impl.DriverChrome;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SeleniumConfig {

    @Value("${selenium.browser}")
    private String browser;
    @Value("${selenium.path}")
    private String PATH_DRIVER;

    @Bean
    @Scope("singleton")
    public WebDriver getDriver(){
        if("chrome".equalsIgnoreCase(browser)){
            return new DriverChrome(PATH_DRIVER).getDriver();
        }
        return null;
    }
}
