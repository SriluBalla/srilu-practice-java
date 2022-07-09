package tests;

import helper.BrowserStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static helper.Browser_helper.getDriver;

public class gotoLinks {

    private static final WebDriver driver = getDriver();

    public static void main(String[] args) throws InterruptedException {

        BrowserStep.goToURL("https://sensei.com/");

        List<String> hrefs = new ArrayList<>();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total number of hrefs on the page = " + links.size());


        for (WebElement pageLike : links ) {
            hrefs.add(pageLike.getAttribute("href"));
        }

        for ( String href : hrefs ) {
// To skip the hrefs for phone numbers and emails and go straight to hrefs with urls
            if(href.startsWith("https")) {
                System.out.println("\n Going to HREF = " + href);
                BrowserStep.goToURL(href);
            }
        }
        driver.quit();


    }
}
