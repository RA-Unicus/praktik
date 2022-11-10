package com.unicus.sv.ga.praktik.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import javax.swing.JOptionPane;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Gustav Task 1 of testing the Playground, with only Selenium
 */
public class Playground1 {

    public static boolean testscriptPlayground(RemoteWebDriver d) {
        d.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        d.get("http://timvroom.com/selenium/playground/");
        // 1
        String title = d.getTitle();
        d.findElement(By.id("answer1")).sendKeys(title);
        // 2
        d.findElement(By.id("name")).sendKeys("Kilgore Trout");
        // 3
        Select occupation = new Select(d.findElement(By.id("occupation")));
        occupation.selectByValue("scifiauthor");
        // 4
        int blueboxN = d.findElements(By.className("bluebox")).size();
        d.findElement(By.id("answer4")).sendKeys(String.valueOf(blueboxN));
        // 5
        d.findElement(By.linkText("click me")).click();
        // 6
        WebElement redbox = d.findElement(By.id("redbox"));
        String redboxClass = redbox.getAttribute("class");
        d.findElement(By.id("answer6")).sendKeys(redboxClass);
        // 7
        Object script1Result = d.executeScript("ran_this_js_function()");
        // 8
        Object script2Result = d.executeScript("return got_return_from_js_function()");
        String script2ResultS = script2Result.toString();
        d.findElement(By.id("answer8")).sendKeys(script2ResultS);
        // 9
        for (WebElement e : d.findElements(By.name("wrotebook"))) {
            if (e.getAttribute("value").equals("wrotebook")) {
                e.click();
                break;
            }
            throw new AssertionError("Checkbox not found");
        }
        // 10
        String redboxText = d.findElement(By.id("redbox")).getText();
        d.findElement(By.id("answer10")).sendKeys(redboxText);
        // 11
        WebElement arrangeHeading = d.findElements(By.tagName("h3")).get(3);
        WebElement topBox = d.findElement(RelativeLocator.with(By.tagName("span")).below(arrangeHeading));
        String topBoxText = topBox.getText();
        String topBoxColor = topBoxText.substring(0, topBoxText.indexOf(" "));
        d.findElement(By.id("answer11")).sendKeys(topBoxColor);
        // 12
        d.manage().window().setSize(new Dimension(850, 650));
        // 13
        WebElement answer13 = d.findElement(By.id("answer13"));
        if (d.findElements(By.id("ishere")).isEmpty()) {
            answer13.sendKeys("no");
        } else {
            answer13.sendKeys("yes");
        }
        // 14
        WebElement answer14 = d.findElement(By.id("answer14"));
        if (d.findElement(By.id("purplebox")).isDisplayed()) {
            answer14.sendKeys("yes");
        } else {
            answer14.sendKeys("no");
        }
        // 15
        d.findElement(By.linkText("click then wait")).click();
        By nextLinkFinder = By.linkText("click after wait");
        ExpectedCondition<WebElement> nextLinkIsReady = ExpectedConditions.elementToBeClickable(nextLinkFinder);
        WebElement nextLink = new WebDriverWait(d, Duration.ofSeconds(30), Duration.ofMillis(100)).until(nextLinkIsReady);
        nextLink.click();
        // 16
        new WebDriverWait(d, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = d.switchTo().alert();
        //<editor-fold defaultstate="collapsed" desc="taking metrics of #15">
        String alertText = alert.getText();
        int measuredWait = Integer.parseInt(
                alertText.substring(alertText.lastIndexOf(" ") + 1, alertText.lastIndexOf("ms")));
        System.out.println("#15 measured wait: " + measuredWait);
        if (measuredWait > 200) {
            throw new AssertionError("Wait was more than two intervals");
        }
//</editor-fold>
        alert.accept();
        // 17
        d.findElement(By.id("submitbutton")).click();
        // pass/fail check
        try {
            WebElement top = d.findElement(By.id("tophead"));
            new Actions(d).moveToElement(top).perform();
        } catch (Exception e) {
            // silent ignore, since just aestetic for the tester
        }
        d.findElement(By.id("checkresults")).click();
        int passN = 0;
        int failN = 0;
        for (WebElement e : d.findElements(By.className("ok"))) {
            if (e.getText().equals("OK")) {
                passN++;
            } else {
                failN++;
            }
        }
        JOptionPane.showMessageDialog(null, "pass/fail: " + passN + " / " + failN
                + "\noverall: " + (failN == 0 ? "PASS" : "FAIL"), "Result",
                failN == 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        d.quit();
        return true;
    }

    public static RemoteWebDriver createDriver(int browserN) {
        switch (browserN) {
            case 0 -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            case 1 -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case 2 -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case 5 -> {  // doesn't start
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            }
            case 3 -> {  // doesn't start
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            }
            case 4 -> {  // presence of Edge seems to block it
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            }
            default ->
                throw new AssertionError();
        }
    }
}
