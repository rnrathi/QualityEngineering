/*
* Purpose of this class is to include all the browser/browser driver specific functions for
* Application under test to utilize.
*
* */

package com.ramkrishna.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestBase {

    protected WebDriver driver;

    public void createDirectoryProjectBase(String directoryName){
        String directoryPath = System.getProperty("user.dir") + "/" + directoryName;

        File directory = new File(directoryPath);

        System.out.println("Directory Path " + directoryPath);

        if (!directory.exists()){
            if (directory.mkdir())
                System.out.println("Directory is created : " + directory.getPath());
            else
                System.out.println("Directory is not created");
        }
        else
            System.out.println("directory already exist");

    }


    public void quitBrowser(){
        driver.quit();
        System.out.println("Quitting the browser driver");
    }

    public void navigateURL(String urlLink){
        driver.get(urlLink);
        System.out.println("Url is accessed");
    }
    public void maximizeWindow(){
        driver.manage().window().maximize();
        System.out.println("Browser window is maximized");
    }
    public void minimizeWindow(){
        driver.manage().window().minimize();
        System.out.println("Browser window is minimized");
    }
    public String getPageTitle(){
        System.out.println("Browser title is returned");
        return driver.getTitle();
    }
    public void clickLink(String elementXpath, String linkName){
        driver.findElement(By.xpath(elementXpath)).click();
        System.out.println("Link clicked: " + linkName);
    }


    /*
    * Below methods for taking screenshots from the screen
    * takeScreenshots() - This takes screenshot and capture in .png file
    * takeScreenshots(filename, imagetype) - Capture screenshot with filename name and image type supplied by caller
    * takeScreenshots(filename, imagetype,x,y,width, height)- Capture screenshot with filename name, image type, location
    *                 and size supplied by caller
    *
    * */


    public void takeScreenshot() throws AWTException, IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HHmm");
        String filename = "./testresults/" + LocalDateTime.now().format(formatter) + ".png";
        takeScreenshot(filename, "png");

    }
    public void takeScreenshot(String filename, String imgType) throws AWTException, IOException {
        takeScreenshot(filename, imgType, 0,0,1300,700);
    }

    public void takeScreenshot(String filename, String imgType, int x, int y, int width, int height) throws AWTException, IOException {
        Robot robot = new Robot();
        Rectangle rectangle = new Rectangle();

        /*
        * Creating user test results folder so that screenshots are captured
        * */

        createDirectoryProjectBase("testresults");


        //Setting up the size of the screenshot
        rectangle.setLocation(x,y);
        rectangle.setSize(width,height);
        System.out.println("file name " + filename);

        BufferedImage image = robot.createScreenCapture(rectangle);
        ImageIO.write(image,imgType, new File(filename));
    }

}
