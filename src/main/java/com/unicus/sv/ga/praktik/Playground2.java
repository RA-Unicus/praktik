package com.unicus.sv.ga.praktik;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    public void testcase1() {
        ...
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
