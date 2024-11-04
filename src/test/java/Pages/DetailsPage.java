package Pages;

import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage
{
    WebDriver driver = DriverFactory.CreateWebDriver();

    private By variantReg  = By.cssSelector("div.details-vrm.ng-star-inserted");
    private By make = By.xpath("//div[contains(@class, 'details-vehicle-row') and contains(., 'Manufacturer:')]//div[contains(@class, 'value')]");
    private By model = By.xpath("//div[contains(@class, 'details-vehicle-row') and contains(., 'Model:')]//div[contains(@class, 'value')]");
    private By year = By.xpath("//div[contains(@class, 'details-vehicle-row') and contains(., 'Year:')]//div[contains(@class, 'value')]");

    public String GetCarReg()
    {
        return driver.findElement(variantReg).getText();
    }

    public String GetCarMake()
    {
        return driver.findElement(make).getText();
    }
    public String GetCarModel()
    {
        return driver.findElement(model).getText();
    }

    public String GetCarYear()
    {
        return driver.findElement(year).getText();
    }
}
