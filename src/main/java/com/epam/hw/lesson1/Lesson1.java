package com.epam.hw.lesson1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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


    private WebElement getButtonText(ButtonsList buttonsList) {
        return driver.findElements(cssSelector("Button")).stream()
                .filter(button -> button.getText().equals(buttonsList.getButtonsDesc()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No button with such text " + buttonsList.getButtonsDesc()));
    }

    private WebElement selectButton(String buttonName) {
        return driver.findElements(cssSelector("Button")).stream()
                .filter(button -> button
                        .getText()
                        .equals(buttonName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No button with such name " + buttonName));
    }

    public void clickButton(String buttonName) {
        selectButton("button1").click();
    }
}
