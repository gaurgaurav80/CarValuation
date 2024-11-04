package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory
{
    private static WebDriver webDriver;
    public static WebDriver CreateWebDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.edge.driver", "src/test/java/Drivers/msedgedriver");
            webDriver = new EdgeDriver();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
        return webDriver;
    }
}
