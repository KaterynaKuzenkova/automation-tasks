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

    public void clickButton(ButtonsList buttonsList) {
        selectButton(buttonsList).click();
    }

    private WebElement selectButton(ButtonsList buttonsList) {
        return driver.findElements(cssSelector("Button")).stream()
                .filter(button -> button.getText().equals(buttonsList.getButtonsDesc()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No button with such text " + buttonsList.getButtonsDesc()));
    }
}
