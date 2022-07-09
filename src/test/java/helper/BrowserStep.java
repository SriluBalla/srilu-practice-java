package helper;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;

import java.util.HashMap;
import java.util.Map;

import static helper.Browser_helper.getDriver;

public class BrowserStep {
    private static WebDriver driver = getDriver();
    static Wait<WebDriver> wait;

    //  //  Invoke Browser
// // Go to URL
    public static void goToURL(String url) throws InterruptedException {
        driver.navigate().to(url);
        System.out.println("Went to page = " + driver.getTitle());
        Thread.sleep(1000);
    }

    public static void verifyTitle (String title) throws Exception   {
        assert(driver.getTitle().contains(title));
    }

    // //  // Close Tab
    public static void closeTab(){
        driver.close();
    }

    // //  // Kill Browser process
    public static void quitBrowser(){
        driver.quit();
    }

    //  //  Cookies and Cache
    public static void delAllCookies(){
        driver.manage().deleteAllCookies();
        System.out.println("\n Deleted All Cookies");
    }

    public static void delCookie(Cookie cookie){
        driver.manage().deleteCookie(cookie);
        System.out.println("\n Deleted Cookie = " + cookie);
    }

    public static void addCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
        System.out.println("\n Added one Cookie = " + cookie);
    }


    // // // Hover
    public static void HoverOver(WebElement element){
        Actions hover = new Actions(driver);
        hover.moveToElement(element).build().perform();
        System.out.println("\n Hovering over element = " + element);
    }

    // // Scroll To
    public static void scrollTo(WebElement element) throws InterruptedException{
        Actions scroll = new Actions(driver);
        scroll.moveToElement(element);
        scroll.perform();
        System.out.println("\n Scroll to element = " + element);
    }

    public static void scrollToView(WebElement element) throws InterruptedException{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        System.out.println("\n Scroll to element = " + element);
    }

    // //  // Select between tabs
// For the main Tab
    public static void focusMain(){
        for(String Main : driver.getWindowHandles()){
            driver.switchTo().window(Main);
        }
    }

    // For the New Tab
    public static void focusNewTab(){
        for(String newTab : driver.getWindowHandles()){
            driver.switchTo().window(newTab);
        }
    }

    //  //  // Upload File
    public static void uploadFile(WebElement element, String filePath) {
        element.sendKeys(filePath);
        System.out.println("\n Uploaded File = " + filePath);
    }

    // //  // Maximize Window
    public static void full(){
        driver.manage().window().maximize();
        System.out.println("\n Window Maximied Fully");
    }

    // //  // Minimize / Maximize to Window
    public static void minim(int h, int w){
        Dimension s = new Dimension(h, w);
        driver.manage().window().setSize(s);
        System.out.println("\n Browser sized to = " + w + "x" + h);
    }

    public static void half(){
        minim(1250, 1100);
        System.out.println("\n Browser sized to half");
    }

    public static void tablet(){
        minim(800, 1000);
        System.out.println("\n Browser sized to Tablet");
    }

    public static void phone(){
        minim(400, 700);
        System.out.println("\n Browser sized to Phone");
    }

    //  //  //Get Current URL
    public static void getURL(){
        String currURL =  driver.getCurrentUrl();
    }

    // //  // Go back one page
    public static void oneBack(){
        driver.navigate().back();
    }

    // //  // Go forward one page
    public static void oneForward(){
        driver.navigate().forward();
    }

    // //  // Refresh current page
    public static void refresh(){
        driver.navigate().refresh();
    }

    // Emulate Chrome to immitate mobile browser
    public static void emulateChrome(String phoneType){
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", phoneType);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
    }

    public static void err404(WebDriver driver) {
        if (driver.getCurrentUrl().contains("404 Page Not Found")){
            System.err.println("Invalid URL - Page Title = " + driver.getTitle());
        }
    }

}
