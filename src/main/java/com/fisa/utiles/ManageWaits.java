package com.fisa.utiles;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class ManageWaits {

    protected enum TYPE_FIND_ELEMENT { xpath, id}

    protected WebElement waitElement(WebDriver driver, TYPE_FIND_ELEMENT type_find_element, String find, Long timeOut, Long pollingEvery) throws InterruptedException {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class);
        WebElement usernameElement = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if("xpath".equalsIgnoreCase(type_find_element.toString())){
                    return driver.findElement(By.xpath(find));
                }
                return driver.findElement(By.id(find));
            }
        });
        WebDriverWait waitT = new WebDriverWait(driver, timeOut);
        waitT.until(ExpectedConditions.elementToBeClickable(usernameElement));
        Thread.sleep(1000);
        return usernameElement;
    }
}
