
# Selenium Java TestNG + Cucumber UI Automation Framework

This is a **UI automation framework** developed using **Selenium WebDriver**, **Java**, **TestNG**,**Cucumber (BDD)** and **Maven**.
It is designed to support cross-browser testing with **LambdaTest**, nightly CI/CD integration using **GitHub Actions**, and supports **data-driven testing** using various file formats.

The framework supports:
- Execution with TestNG or Cucumber BDD (via Maven profiles)
- Cross-browser testing on local & cloud (LambdaTest, BrowserStack)
- Retry Analyzer for flaky Cucumber scenarios
- Headless execution for faster test runs
- CI/CD integration with GitHub Actions (nightly runs + GitHub Pages reports)

## About Me

- Hi, my name is Pratik Gupte, and I have 8 years of experience working as a QA Engineer, including 2 years in Automation Testing using tools like Selenium Webdriver, Salesforce Automation using RobotFramework and Copado Robotic Testing, API Testing using Postman and RestAssured.


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/pgupteqa/)

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/pratik-gupte-19145156/)



## Prerequisites

Before running this framework, ensure the following software is installed on your system:
- Java 17 - Make sure Java is installed and the JAVA_HOME environment variable is set.
- Maven - Ensure Maven is installed and added to the system path.
- Downlod Link: https://maven.apache.org/download.cgi

## Key Features

- Built with Java + Selenium WebDriver
- TestNG as the test runner and suite manager
- Data-driven testing from **CSV, Excel, and JSON**
- Test data generation using **Java Faker**
- Modular code using **Page Object Model (POM)** and **POJO classes**
- Maven build with configurable test parameters
- Logging with **Log4j2** for detail logs
- Reporting with **ExtentReports**
- Cloud-based parallel execution on **LambdaTest**
- Faster tests execution by running tests in **Headless mode**.
- Nightly test execution using **GitHub Actions**
- Execution with **TestNG** or **Cucumber BDD** (via Maven profiles)

## Tech Stack

- **Language**: Java 17
- **Automation**: Selenium WebDriver 4.34.0
- **Test Framework**: TestNG 7.11.0, Cucumber 7.x
- **Build Tool**: Maven
- **Data Readers**: Gson, OpenCSV, Apache POI
- **Faker Library**: javafaker 1.0.2
- **Logging**: Log4j2 (v2.25.1)
- **Cloud Grid**: LambdaTest
- **CI/CD**: GitHub Actions
- **Reporting**: ExtentReport and Github Pages

## Setup Instruction

**Clone the Repository**

```bash
  git clone https://github.com/pgupteqa/Cucumber-TestNG-BDD-Framework.git
  cd Cucumber-TestNG-BDD-Framework

```
**Running Tests on Local Machine**

- **Running as TestNG Test**:

```bash
  mvn test -Dbrowsername=chrome  -DisLambdaTest=false -DisHeadLess=false -Denvname=QA -Dsuitexmlfile=testsuites/RegressionTests.xml -P TestNGTests 

```

- **Running as Cucumber BDD Test**:

```bash
  mvn test -Dbrowsername=chrome  -DisLambdaTest=false -DisHeadLess=false -Denvname=QA -P CucumberTests

```
**Running Tests on Local Machine in HeadLess Mode**

```bash
  mvn test -Dbrowsername=chrome  -DisLambdaTest=false -DisHeadLess=true -Denvname=QA -Dsuitexmlfile=testsuites/RegressionTests.xml -P TestNGTests 

```
**Running Tests on LambdaTest**

```bash
  mvn test -Dbrowsername=chrome  -DisLambdaTest=true -DisHeadLess=false -Denvname=QA -Dsuitexmlfile=testsuites/RegressionTests.xml -P TestNGTests 

```

**Running Tests on LambdaTest or HeadLess using a specific group tags**

- **Running as TestNG Test with Groups tags**

```bash
  mvn test -Dbrowsername=chrome  -DisLambdaTest=true -DisHeadLess=false -Denvname=QA -Dsuitexmlfile=testsuites/RegressionTests.xml -P TestNGTests -Dgroups=regression

```

- **Running as Cucumber BDD Test with tags**:

```bash
  mvn test -Dbrowsername=chrome -DisLambdaTest=false -DisHeadLess=true -Denvname=QA -P CucumberTests -Dcucumber.filter.tags=@Regression

```

**Retry Analyzer for Flaky Tests**:
- Failed cucumber scenarios are automatically retried.
- Failed scenario list is saved in:
```bash
 target/failed_scenarios.txt
 
```


## Project Files Structure



```bash
├───src
│   └───test
│       ├───java
│       │   └───org.automationcart
│       │       ├───constants/ # Static constants and enums
│       │       ├───dataproviders/ # Data providers for TestNG (CSV, Excel, JSON)
│       │       ├───listeners/ # TestNG listeners for custom logging/reporting
│       │       ├───pages/ # Page Object Model classes
│       │       ├───pojo/ # POJO classes for structured test data
│       │       ├───tests/ # Test classes
│       │       ├───utility/ # Common reusable utilities (LoggerUtility, BrowserUtility, LambdaTestUtility, etc)
|       |       ├── cucumberHooks/ # Hooks (setup/teardown, screenshots)
|       |       ├── cucumberRunner/    # TestNG Cucumber runner
|       |       └── stepdefinations/   # Step definitions for Cucumber
│       └───resources/ # log4j.xml
|       ├── features/              # Cucumber feature files
├───testData/ # Storing Testdata files in json, csv and xlsx
└───testsuites/ # testng xml suite and cucumber-testng suite files
├───config/ # Environment-specific config files
├───logs/ # Execution logs
├───screenshot/ # Failure screenshots
├───pom.xml/ # Maven dependencies and build configuration
├───report.html/ # Extent report output
├───Readme.md/ # Project documentation
```


## Example Cucumber Feature:

```bash

Feature: Login Functionality for OpenCart Website

  @ValidLogin @Smoke @Regression
  Scenario Outline: Successful Login with Valid Credentials
    Given I am on the application login page
    When I login with email <email> and password <password>
    Then I should be logged in successfully and sees the username "Prateek Guptey"

    Examples:
      | email     | password   |
      | useremail | userpassword |


```
## Data Driven Testing for TestNG Tests:

This framework implements multiple TestNG @DataProvider methods to feed test data dynamically from various sources:

- JSON → Loaded with Gson to deserialize into POJOs (User, InvalidUsers, Registration).
- CSV → Read via a custom CSVReaderUtility.
- Excel → Read via a custom ExcelReaderUtility.
- Centralized Config → File paths managed in TestDataFileName.properties using PropertiesUtil




## Reports & Logs

- Reports: After execution, a detailed HTML report will be generated at ./report.html and (cucumber bdd tests) ./cucumberbddreport.html
- The report contains information on test cases executed, passed, failed, and skipped, along with screenshots for failed tests.
- TestNG Extent Report -> ./report.html
- Cucumber Extent Report -> ./cucumberbddreport.html

## Logs:
- Logs are created during the test execution and stored in the ./logs/directory.

## LambdaTest Integration

To run tests on the cloud:
- Set the following environment variables (or GitHub Secrets):

```bash
LT_USERNAME
LT_ACCESS_KEY

```

- Note: In this POC I have used the GitHub Secrets to store these values.

- Ensure isLambdaTest=true in the Maven command.


## Integrated the Project using GitHub Actions

- This automation framework is integrated with github actions. The tests will be executed at **11:30PM IST** every single day.

- Trigger on push & pull requests to master branch.

- The reports will be archieved in gh-pages branch You can view the html reports at :
  https://pgupteqa.github.io/Cucumber-TestNG-BDD-Framework/cucumberbddreport.html

## Cucumber Extent Report Screenshots:

![All Tests Passed Extent Report](https://github.com/pgupteqa/Cucumber-TestNG-BDD-Framework/blob/master/extent-report-bdd-image2.png)

![Test Report with Failed Screenshot](https://github.com/pgupteqa/Cucumber-TestNG-BDD-Framework/blob/master/extent-report-bdd-image.png)

