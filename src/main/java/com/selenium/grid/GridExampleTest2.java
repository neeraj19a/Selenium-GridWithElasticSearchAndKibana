package com.selenium.grid;

/** * Created by NEERAJ on 4/23/2017. */import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExecutionListener.class)
public class GridExampleTest2 {

    public RemoteWebDriver driver;
    public static String appURL = "http://www.google.com";

    @BeforeClass    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
// Here we are using URL of Docker  where our Grid Console is present "http://192.168.99.100:5000/wd/hub" 
      
      //driver = new RemoteWebDriver(new URL("http://192.168.99.100:5000/wd/hub"), capabilities);
      //  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        System.setProperty("webdriver.chrome.driver", ".//src//test//java//resources//chromedriver.exe");
		driver = new ChromeDriver();
        
        driver.manage().window().maximize();
    }

    @Test    public void testGooglePageTitleInIEBrowser() {
        System.out.println("*** Navigation to Application ***");
        driver.navigate().to(appURL);
        String strPageTitle = driver.getTitle();
        System.out.println("*** Verifying page title ***");
        Assert.assertTrue(strPageTitle.equalsIgnoreCase("Google"), "Page title doesn't match");
    }

    @AfterClass    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}