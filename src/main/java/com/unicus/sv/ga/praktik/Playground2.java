package com.unicus.sv.ga.praktik;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

/**
 *
 * @author Gustav
 *
 * Task 2 of testing http://timvroom.com/selenium/playground/ now combining
 * Selenium with TestNG
 */
@Test
public class Playground2 {

    RemoteWebDriver d;

    @Test(priority = 1)
    public void testcase01() {
        d.findElement(By.id("answer1")).sendKeys(d.getTitle());
    }

    @Test(priority = 2)
    public void testcase02() {
        d.findElement(By.id("name")).sendKeys("Kilgore Trout");
    }

    @Test(priority = 3)
    public void testcase03() {
        Select occupation = new Select(d.findElement(By.id("occupation")));
        occupation.selectByValue("scifiauthor");
    }

    @Test(priority = 4)
    public void testcase04() {
        d.findElement(By.id("answer4")).sendKeys(String.valueOf(
                d.findElements(By.className("bluebox")).size()));
    }

    @Test(priority = 5)
    public void testcase05() {
        d.findElement(By.linkText("click me")).click();
    }

    @Test(priority = 6)
    public void testcase06() {
        d.findElement(By.id("answer6")).sendKeys(d.findElement(By.id("redbox"))
                .getAttribute("class"));
    }

    @Test(priority = 7)
    public void testcase07() {
        d.executeScript("ran_this_ja_function");
    }

    @Test(priority = 8)
    public void testcase08() {
        Object scriptReturn = d.executeScript("got_return_from_js_function");
        d.findElement(By.id("answer8")).sendKeys(scriptReturn.toString());
    }

    @Test(priority = 9)
    public void testcase09() {
        Select author = new Select(d.findElement(By.name("wrotebook")));
        author.selectByValue("wrotebook");
    }

    @Test(priority = 10)
    public void testcase10() {
        d.findElement(By.id("answer10")).sendKeys(
                d.findElement(By.id("redbox")).getText());
    }

    @Test(priority = 11)
    public void testcase11() {
        String topBoxS = d.findElement(RelativeLocator.with(By.tagName("span")).below(
                d.findElements(By.tagName("h3")).get(2))).getText();
        topBoxS.substring(0, topBoxS.indexOf(' '));
        d.findElement(By.id("answer11")).sendKeys();
    }

    @Test(priority = 12)
    public void testcase12() {
        d.manage().window().setSize(new Dimension(850, 650));
    }

    @Test(priority = 13)
    public void testcase13() {
        boolean isHere = !d.findElements(By.id("ishere")).isEmpty();
        d.findElement(By.id("answer13")).sendKeys(isHere ? "yes" : "no");
    }

    @Test(priority = 14)
    public void testcase14() {
        boolean visible = d.findElement(By.id("purplebox")).getAttribute("display").equals("none");
        d.findElement(By.id("answer14")).sendKeys(visible ? "yes" : "no");
    }

    @Test(priority = 15)
    public void testcase15() {
        d.findElement(By.linkText("click then wait")).click();
        new WebDriverWait(d, Duration.ofSeconds(15)).until(
                ExpectedConditions.elementToBeClickable(By.linkText("click after wait")));
    }

    @Test(priority = 16)
    public void testcase16() {
        Alert alert = new WebDriverWait(d, Duration.ofSeconds(5)).until(
                ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Test(priority = 17)
    public void testcase17() {
        d.findElement(By.id("submitbutton")).click();
    }

    @AfterTest
    public void summarizeAndClose() {
        ...
        d.quit();
    }

    @BeforeTest
    public void openBrowser() {
        d = new EdgeDriver();
        d.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        d.get("http://timvroom.com/selenium/playground/");
    }

    @BeforeSuite
    public void manageBrowsers() {
        WebDriverManager.edgedriver().setup();
    }
}
