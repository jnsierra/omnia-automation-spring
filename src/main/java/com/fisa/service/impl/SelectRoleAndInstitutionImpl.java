package com.fisa.service.impl;

import com.fisa.service.SelectRoleAndInstitution;
import com.fisa.utiles.ManageWaits;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelectRoleAndInstitutionImpl extends ManageWaits implements SelectRoleAndInstitution{

    @Getter @Setter
    private String mainWindow;
    @Getter @Setter
    private String childWindow;

    private WebDriver driver;

    private final static Logger logger = Logger.getLogger(SelectRoleAndInstitutionImpl.class);

    @Autowired
    public SelectRoleAndInstitutionImpl(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public Boolean selectCombos() {
        try {
            this.driver.switchTo().window(childWindow);
            logger.debug("Switch para ventana hija ");
            Boolean response = selectInstitution();
            logger.debug("Selecciono institucion: " + response);
            if(response){
                response = selectRol();
                logger.debug("Selecciono role: " + response);
                if(response){
                    response = selectSimulaciones();
                    logger.debug("Selecciono simulaciones: " + response);
                    if(response){
                        return Boolean.TRUE;
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return Boolean.FALSE;
    }

    public Boolean clickEngranaje(){
        try {
            //damos click sobre el engrane
            Thread.sleep(1000);
            waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[2]/table/tbody/tr/td[2]/form/table/tbody/tr/td[3]/span/span/span/span[1]", 6L, 2L)
                    .click();
            Thread.sleep(1000);
        }catch (Exception e){
            logger.error("Error al dar click en el engranage");
            logger.error(e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean selectInstitution(){
        try{
            clickEngranaje();

            waitElement(this.driver, TYPE_FIND_ELEMENT.xpath, "//*[@id=\"selectCompany\"]/tbody/tr/td[2]", 6L, 2L)
                    .click();


            waitElement(this.driver, TYPE_FIND_ELEMENT.xpath, "//*[@id=\"dijit_MenuItem_4_text\"]", 6L, 2L )
                    .click();
        }catch (Exception e){
            logger.error("ERROR AL DAR CLICK EN INSTITUCION");
            logger.error(e);
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean selectRol(){
        try{
            clickEngranaje();
            waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[6]/div/div[1]/div/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr/td[2]/input", 6L, 2L)
                    .click();
            waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[8]/table/tbody/tr[4]/td[2]", 6L, 2L)
                    .click();
        }catch (Exception e){
            logger.error("ERROR AL DAR CLICK EN ROL");
            logger.error(e);
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    public Boolean selectSimulaciones(){
        try {
            Thread.sleep(1000);
            waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[3]/div/div[5]", 6L, 2L)
                    .click();

            waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[9]/table/tbody/tr[3]/td[2]", 6L, 2L)
                    .click();
        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}