package com.epam.hw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) {
        driver.get("https://www.google.com/");
        driver.close();
    }
}
