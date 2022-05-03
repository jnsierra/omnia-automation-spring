package com.fisa.service;

public interface LoginAccess {

    Boolean generateAccess() throws InterruptedException;
    String getNamePrincipalWindow();
    String getChildWindow();

}
