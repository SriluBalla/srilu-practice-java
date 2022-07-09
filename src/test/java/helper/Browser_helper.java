package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser_helper {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver == null){
            driver = chooseDriver();
        }
        return driver;
    }

    public static WebDriver chooseDriver(){

        String browser = System.getProperty("BROWSER");

        if(browser == null) {
            browser = System.getenv("BROWSER");

            if (browser == null){
                browser = "chrome";
            }
        }
        String drPath = "";
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/_drivers/chromedriver");
                driver = new ChromeDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", drPath + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", drPath + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }
        System.out.println("\nInvoked browser = " +  browser);
        return driver;
    }
}