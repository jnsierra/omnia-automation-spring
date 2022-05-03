package com.fisa.service.impl;

import com.fisa.service.IngresarSimulacion;
import com.fisa.utiles.ManageWaits;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IngresarSimulacionImpl extends ManageWaits implements IngresarSimulacion {

    @Getter
    @Setter
    private String mainWindow;
    @Getter @Setter
    private String childWindow;

    private WebDriver driver;

    private String documento;
    private final static Logger logger = Logger.getLogger(IngresarSimulacionImpl.class);

    @Autowired
    public IngresarSimulacionImpl(WebDriver driver, @Value("${automation.simulation.document}") String documento) {
        this.driver = driver;
        this.documento = documento;
    }

    @Override
    public Boolean inicioSimulacion() throws Exception{
        logger.debug("Inicio la simulacion");
        this.driver.switchTo().window(childWindow);
        logger.debug("Switch sobre la ventana hija");
        Boolean response = clickRadioButton();
        logger.debug("Da click sobre el radio button");
        if(response){
            response = ingresarIdentificacion();
            logger.debug("Ingresa la identificacion");
            if(response){
                response = generarUnaNuevaSimulacion();
                logger.debug("Da click sobre el boton");
                Thread.sleep(5000);
            }
        }
        return Boolean.TRUE;
    }

    public Boolean clickRadioButton() throws Exception{
        waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div/div/div[3]/div[2]/div[2]/div/div/div/div[2]/div/div/div/div/div[3]/table/tbody/tr/td[1]/div", 6L, 2L)
                .click();
        logger.debug("Da click sobre el radio button 3");
        waitElement(this.driver,TYPE_FIND_ELEMENT.id,"dijit_form_Button_3_label", 6L, 2L)
                .click();
        logger.debug("Da click sobre el button ");
        return Boolean.TRUE;
    }

    public Boolean ingresarIdentificacion() throws Exception{
        WebElement input = waitElement(this.driver,TYPE_FIND_ELEMENT.xpath,"/html/body/div[1]/div[2]/div[2]/div/div[3]/div/div/div[3]/div[2]/div/div[3]/div[3]/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[4]/div/table/tbody/tr/td[1]/div/div/input", 6L, 2L);
        input.sendKeys(documento);
        input.sendKeys(Keys.TAB);
        return Boolean.TRUE;
    }

    public Boolean generarUnaNuevaSimulacion()throws Exception{
        waitElement(this.driver,TYPE_FIND_ELEMENT.id,"ec_fisa_grid_FisaNonEditableGrid_0_newButton_label", 6L, 2L)
                .click();
        Thread.sleep(3000);
        return Boolean.TRUE;
    }
}
