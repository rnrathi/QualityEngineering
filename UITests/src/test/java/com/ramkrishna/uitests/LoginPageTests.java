package com.ramkrishna.uitests;
import com.ramkrishna.base.AppBase;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;


public class LoginPageTests extends AppBase {

    @Test
    public void demoMVNRepoSiteTest() throws IOException, AWTException {

        String categoryLinkXpath = "//a[text()='Categories']";

        launchBrowser("chrome");
        navigateURL("https://mvnrepository.com/");
        maximizeWindow();
        takeScreenshot();
        System.out.println(getPageTitle());
        clickLink(categoryLinkXpath, "Categories");
        System.out.println(getPageTitle());

    }
}
