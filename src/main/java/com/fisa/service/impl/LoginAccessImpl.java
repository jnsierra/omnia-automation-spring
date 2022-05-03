package com.fisa.service.impl;

import com.fisa.service.LoginAccess;
import com.fisa.utiles.ManageWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;

@Scope("singleton")
@Service
public class LoginAccessImpl extends ManageWaits implements LoginAccess {

    private WebDriver webDriver;
    private String namePrincipalWindow;
    private String childWindow;

    private String USER;
    private String PASS;

    @Autowired
    public LoginAccessImpl(WebDriver webDriver
            , @Value("${automation.user}") String user
            , @Value("${automation.password}") String pass) {
        this.webDriver = webDriver;
        this.USER = user;
        this.PASS = pass;
    }

    @Override
    public Boolean generateAccess() throws InterruptedException{
        Boolean response = clickLogoInicial();
        if(response){
            generateLogin();
            webDriver.switchTo().window(namePrincipalWindow);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean clickLogoInicial(){
        webDriver.findElement(By.id("logoCompany")).click();
        return Boolean.TRUE;
    }

    private Boolean generateLogin()throws InterruptedException{
        namePrincipalWindow = webDriver.getWindowHandle();
        Boolean response = manageChildWindow();
        if(response){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean manageChildWindow() throws InterruptedException {
        Set<String> s1= webDriver.getWindowHandles();
        Iterator<String> i1=s1.iterator();
        while(i1.hasNext()){
            this.childWindow = i1.next();
            if(!namePrincipalWindow.equalsIgnoreCase(childWindow)){
                webDriver.switchTo().window(childWindow);
                waitElement(this.webDriver, TYPE_FIND_ELEMENT.id, "j_username", 30L, 5L )
                        .sendKeys(USER);
                webDriver.findElement(By.id("j_password"))
                        .sendKeys(PASS);
                webDriver.findElement(By.className("btnsession"))
                        .click();
            }
        }
        return Boolean.TRUE;
    }
    public String getNamePrincipalWindow() {
        return namePrincipalWindow;
    }

    public String getChildWindow() {
        return childWindow;
    }
}
