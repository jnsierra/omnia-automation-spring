package com.fisa.service.impl;

import com.fisa.service.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ExecuteTestImpl implements ExecuteTest {

    private WebDriver driver;
    private String URL;
    //Automatizacion del login
    private LoginAccess loginAccess;
    private SelectRoleAndInstitution selectRoleAndInstitution;
    private IngresarSimulacion ingresarSimulacion;
    private FormularioSimulacion formularioSimulacion;
    private Integer idTransaccion;

    @Value("${random.long}")
    private Long random;

    private final static Logger logger = Logger.getLogger(ExecuteTestImpl.class);
    @Autowired
    public ExecuteTestImpl(WebDriver driver
            , @Value("${automation.url}") String url
            ,LoginAccess loginAccess
            ,SelectRoleAndInstitution selectRoleAndInstitution
            ,IngresarSimulacion ingresarSimulacion
            ,FormularioSimulacion formularioSimulacion
            ,@Qualifier("IdTransaccional") Integer idTransaccion ) {
        this.driver = driver;
        this.URL = url;
        this.loginAccess = loginAccess;
        this.selectRoleAndInstitution = selectRoleAndInstitution;
        this.ingresarSimulacion = ingresarSimulacion;
        this.formularioSimulacion = formularioSimulacion;
        this.idTransaccion = idTransaccion;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void executeTest() {
        try {
            logger.info("Id de la transaccion Selenium: ".concat(idTransaccion.toString()));
            logger.debug("Se inicia automatizacion");
            Boolean success = initializeBrowser();
            if(success){
                success = loginAccess.generateAccess();
                logger.debug("Se inicia sesion en el aplicativo");
                if(success){
                    selectRoleAndInstitution.setMainWindow(loginAccess.getNamePrincipalWindow());
                    selectRoleAndInstitution.setChildWindow(loginAccess.getChildWindow());
                    success = selectRoleAndInstitution.selectCombos();
                    logger.debug("Seleccionamos los menus superiores: " + success);
                    if(success){
                        ingresarSimulacion.setMainWindow(loginAccess.getNamePrincipalWindow());
                        ingresarSimulacion.setChildWindow(loginAccess.getChildWindow());
                        success = ingresarSimulacion.inicioSimulacion();
                        logger.debug("Finalizamos la seleccion del plan de la simulacion");
                        if(success){
                            formularioSimulacion.setMainWindow(loginAccess.getNamePrincipalWindow());
                            formularioSimulacion.setChildWindow(loginAccess.getChildWindow());
                            success = formularioSimulacion.formularioSimulacion();
                            logger.debug("Finalizamos la realización del formulario inicial");
                            if(success){
                                logger.info("Finalizo la transaccion selenium " + idTransaccion);
                                this.driver.quit();
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error(e);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        this.driver.quit();
    }

    public Boolean initializeBrowser(){
        driver.get(URL);
        logger.debug("Abrimos la URL: ".concat(URL));
        driver.manage().window().maximize();
        return Boolean.TRUE;
    }
}
