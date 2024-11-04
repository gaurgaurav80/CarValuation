package Pages;
import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SearchCarPage
{
    WebDriver driver = DriverFactory.CreateWebDriver();

    private By regNumberField = By.id("vehicleReg");
    private By mileageField = By.id("Mileage");
    private By getMyValuationButton = By.id("btn-go");
    private By valuationResult = By.id("valuation_result");

    public void EnterRegNumber(String regNumber) {
        driver.findElement(regNumberField).sendKeys(regNumber);
    }

    public void EnterMileage(String mileage) {
        driver.findElement(mileageField).sendKeys(mileage);
    }

    public void ClickGetMyValuation() {
        driver.findElement(getMyValuationButton).click();
    }

    public String GetCarSearchResult() {
        return driver.findElement(valuationResult).getText();
    }
}
