/*
* This is is for creating reusable methods for application under test. This extends TestBase class
* for browser specific functions
* */

package com.ramkrishna.base;

import com.ramkrishna.utility.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class AppBase extends TestBase {



    @AfterMethod
    public void setupBrowser(){
        quitBrowser();
    }

    public void launchBrowser(String browserType){

        if (browserType.equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
            System.out.println("Chrome browser is launched");
        }
        if (browserType.equalsIgnoreCase("firefox")){
            this.driver = new FirefoxDriver();
            System.out.println("Firefox browser is launched");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }


}
