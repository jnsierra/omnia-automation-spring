package com.fisa.service;

public interface FormularioSimulacion {

    Boolean formularioSimulacion() throws Exception;

    String getMainWindow();

    void setMainWindow(String mainWindow);

    String getChildWindow();

    void setChildWindow(String childWindow);
}
