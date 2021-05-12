package com.epam.hw.lesson1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.By.cssSelector;

public class Lesson1 {
    private WebDriver driver;

    @BeforeClass
    public void appSetup() {
        driver = new ChromeDriver();

    }

    @AfterClass
    public void closeUp() {
        driver.close();
    }

    @Test
    public void selectTheButton(String buttonText) {
        List<WebElement> webElements = driver.findElements(cssSelector("Button"));
        Optional<WebElement> button = webElements
                .stream()
                .filter(webElement -> webElement.getText().equals(buttonText))
                .findFirst();
        button.get().click();

        button.ifPresentOrElse(WebElement::click,
                () -> button.orElseThrow(
                        () -> new RuntimeException("Button with such text is not found "))
        );
    }
}
