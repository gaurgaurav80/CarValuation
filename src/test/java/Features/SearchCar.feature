Feature: Search car for valuation

  Scenario Outline: Fetch car valuation based on registration number
    Given I have vehicle reg from <InputFilename> file
    When I search for each registration number on the car valuation page
    Then I verify valuation details for each car with <OutputFilename> file
    Examples:
      | InputFilename          | OutputFilename     |
      | "car_input.txt"        | "car_output.txt"   |
