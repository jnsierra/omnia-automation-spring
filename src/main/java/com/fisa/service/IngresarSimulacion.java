package com.fisa.service;

public interface IngresarSimulacion {

    Boolean inicioSimulacion() throws Exception;

    String getMainWindow();

    void setMainWindow(String mainWindow);

    String getChildWindow();

    void setChildWindow(String childWindow);

}
