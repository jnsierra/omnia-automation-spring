package com.fisa.service.impl;

import com.fisa.service.FormularioSimulacion;
import com.fisa.utiles.ManageWaits;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioSimulacionImpl extends ManageWaits implements FormularioSimulacion {

    @Getter
    @Setter
    private String mainWindow;
    @Getter @Setter
    private String childWindow;
    private final static Logger logger = Logger.getLogger(FormularioSimulacionImpl.class);

    private WebDriver driver;

    @Autowired
    public FormularioSimulacionImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public Boolean formularioSimulacion() throws Exception{
        Boolean response = seleccionTipo();
        logger.debug("Se selecciono tipo");
        if(response){
            response = seleccionTipoUso();
            logger.debug("Se selecciono tipo uso");
            if(response){
                response = seleccionaMarca();
                logger.debug("Se selecciono marca");
                if(response){
                    response = seleccionarModelo();
                    logger.debug("Se selecciono modelo");
                    if(response){
                        response = seleccionVersion();
                        logger.debug("Se selecciono version");
                        if(response){
                            response = simulacionPlazo();
                            logger.debug("Se selecciono plazo");
                            if(response){
                                response = obtenerDatosDeLaSolicitud();
                                logger.debug("Obtuvo datos de la solicitud");
                                Thread.sleep(5000);
                            }
                        }
                    }
                }

            }
        }
        return Boolean.TRUE;
    }

    public Boolean seleccionTipo()throws Exception{
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[2]", 6L, 2L)
                .click();
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, "/html/body/div[13]/table/tbody/tr[2]/td[2]", 6L, 2L)
                .click();
        return Boolean.TRUE;
    }

    public Boolean seleccionTipoUso()throws Exception{
        String xpathTipoU = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[4]/div/table/tbody/tr/td[2]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, xpathTipoU, 6L, 2L)
                .click();
        String xpathParticular = "/html/body/div[14]/table/tbody/tr[3]/td[2]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, xpathParticular, 6L, 2L)
                .click();
        return Boolean.TRUE;
    }

    public Boolean seleccionaMarca()throws Exception{
        String seleccionXPath = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr/td[2]/span/span/span/span[1]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, seleccionXPath, 6L, 2L)
                .click();
        String marcaButton = "/html/body/div[15]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/table/tbody/tr/td[1]/div";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, marcaButton, 6L, 2L)
                .click();
        String confirmationButton = "/html/body/div[15]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/span/span/span/span[3]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, confirmationButton, 6L, 2L)
                .click();
        return Boolean.TRUE;
    }
    public Boolean seleccionarModelo()throws Exception{
        String seleccionXPath = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[2]/td[4]/div/table/tbody/tr/td[2]/span/span/span/span[1]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, seleccionXPath, 6L, 2L)
                .click();

        String radioButton = "/html/body/div[16]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[1]/table/tbody/tr/td[1]/div";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, radioButton, 6L, 2L)
                .click();

        String buttonXpath = "/html/body/div[16]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/span/span/span/span[3]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, buttonXpath, 6L, 2L)
                .click();
        return Boolean.TRUE;
    }

    public Boolean seleccionVersion()throws Exception{

        String seleccionXpath = "//*[@id=\"ec_fisa_lov_ListOfValues_6_button\"]/span[1]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, seleccionXpath, 12L, 2L)
                .click();

        String radioButton = "/html/body/div[16]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div/table/tbody/tr/td[1]/div";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, radioButton, 12L, 2L)
                .click();


        String button = "/html/body/div[16]/div[2]/div[2]/div[2]/div[2]/div/div/div/div[3]/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/span/span/span/span[3]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, button, 12L, 2L)
                .click();

        String buttonSimular = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[3]/div/div[2]/div/div/table[6]/tbody/tr[9]/td[2]/span/span/span/span[3]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, buttonSimular, 12L, 2L)
                .click();

        return Boolean.TRUE;
    }

    public Boolean simulacionPlazo()throws Exception{
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[4]/div/div[2]/div/div/table[2]/tbody/tr[2]/td/div/div/div[2]/div/div/div/div/div[2]/table/tbody/tr/td[7]/div/div/input", 120L, 10L)
                .click();

        String ingresos = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[5]/div/div[2]/div/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]/div/div/div[2]/input[1]";
        WebElement inputIngresos = this.driver.findElement(By.xpath(ingresos));
        inputIngresos.sendKeys("2000000");

        String comboPolitica = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[5]/div/div[2]/div/div/table/tbody/tr[1]/td[4]/div/div/input";
        this.driver.findElement(By.xpath(comboPolitica)).click();
        Thread.sleep(2000);
        String comboSimulacion = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[3]/div[3]/div/div/div[6]/div/div[2]/div/div/table/tbody/tr/td[2]/div/div/input";
        this.driver.findElement(By.xpath(comboSimulacion)).click();
        Thread.sleep(2000);
        String botonConfirmar = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[3]/div/div[4]/table/tbody/tr/td[1]/span/span/span/span[3]";
        this.driver.findElement(By.xpath(botonConfirmar)).click();
        Thread.sleep(10000);
        return Boolean.TRUE;
    }

    public Boolean obtenerDatosDeLaSolicitud()throws Exception{

        String xpathDatosVendendor = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[2]/div/div[3]/div[3]/div/div/div[1]/div/div[1]/div/span[3]";
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, xpathDatosVendendor, 10L, 2L)
                .click();
        String xpathInputIdSolicitud = "/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[2]/div/div[3]/div[3]/div/div/div[1]/div/div[2]/div/div/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[1]/div/div/input";
        String id = waitElement(this.driver,TYPE_FIND_ELEMENT.xpath, xpathInputIdSolicitud, 10L, 2L)
                .getAttribute("value");
        logger.info("Id de la solicitud: " + id);
        return Boolean.TRUE;
    }

}