package Steps;

import Model.Car;
import Pages.DetailsPage;
import Pages.SearchCarPage;
import Utilities.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class SearchCarSteps
{
    WebDriver _webDriver = DriverFactory.CreateWebDriver();
    List<String> _regNumbers = new ArrayList<>();

    SearchCarPage _searchCarPage = new SearchCarPage();
    DetailsPage _detailsPage = new DetailsPage();
    //Car details from webbuyanycar site
    List<Car> _carDetails = new ArrayList<>();
    //Car details from car_output.txt file
    List<Car> _carDetailsOutput = new ArrayList<>();
    String _carReg, _carMake, _carModel, _carYear;


    @Given("I have vehicle reg from {string} file")
    public void iHaveVehicleRegFromFile(String inputFilename) throws IOException
    {
        BufferedReader buffReader = new BufferedReader(new FileReader("src/main/resources/" + inputFilename));
        String line;
        Pattern pattern = Pattern.compile("\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b");

        while ((line = buffReader.readLine()) != null)
        {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find())
            {
                _regNumbers.add(matcher.group().replaceAll("\\s+", ""));
            }
        }
        buffReader.close();
        //System.out.println("Vehicle Registration Number2: " + regNumbers);
    }

    @When("I search for each registration number on the car valuation page")
    public void iSearchForEachRegistrationNumberOnTheCarValuationPage()
    {
        for (String regNumber : _regNumbers)
        {
            _webDriver.get("https://www.webuyanycar.com/");
            _searchCarPage.EnterRegNumber(regNumber);
            _searchCarPage.EnterMileage("10000");
            _searchCarPage.ClickGetMyValuation();

            _carReg = _detailsPage.GetCarReg();
            _carMake = _detailsPage.GetCarMake();
            _carModel = _detailsPage.GetCarModel();
            _carYear = _detailsPage.GetCarYear();

            _carDetails.add(new Car(_carReg,_carMake,_carModel,_carYear));
        }
      }

    @Then("I verify valuation details for each car with {string} file")
    public void iShouldSeeTheValuationDetailsForEachCar(String outputFilename) throws IOException
    {
        BufferedReader buffReader = new BufferedReader(new FileReader("src/main/resources/" + outputFilename));
        String line;
        buffReader.readLine();
        while ((line = buffReader.readLine()) != null) {
            String[] details = line.split(",");
            _carDetailsOutput.add(new Car(details[0], details[1], details[2], details[3]));
        }
        buffReader.close();

        for (int i = 0; i < _carDetailsOutput.size(); i++) {
            Car expectedCar = _carDetailsOutput.get(i);
            Car actualCar = _carDetails.get(i);
            assertEquals("Car details do not match for Reg : " + _carDetailsOutput.get(i).getVariantReg(), _carDetailsOutput, _carDetails);
        }
    }
}


