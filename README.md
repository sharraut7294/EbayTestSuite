EbayTestSuite project for UI Automation of Ebay website - https://www.ebay.com/. The framework contains automated funcional test cases targeted to verify functionality of different pages of the website.

# Design pattern and concepts included -
- Page Object Model -  Page Object Model, also known as POM, is a design pattern in Selenium that creates an object repository for storing all web elements. In Page Object Model, consider each web page of an application as a class file. Each class file will contain only corresponding web page elements. Using these elements, you can perform operations on the website under test
- Page Factory - Page Factory is a class provided by Selenium WebDriver to support Page Object Design patterns. In Page Factory, you can use @FindBy annotation. The initElements method is used to initialize all web elements at once.
- Data Driven Testing - Data-driven testing is creation of test scripts where test data and/or output values are read from data files instead of using the same hard-coded values each time the test runs.

# Tools used -
- Maven
    - Maven is a build automation tool used primarily for Java projects. For any UI test framework you would need to add multiple dependencies such as depedency of Automation tool, Test framework tool, Reporting tool for end to end execution of your framework. Maven saves the overhead of downloading and adding this dependencies manually to your framework. All you have to do this add your required dependency with it's appropriate version in pom.xml file and maven will download these dependencies for you.

- Selenium Webdriver
    - Selenium WebDriver is a browser automation framework that works with open source APIs. The framework operates by accepting commands, sending those commands to a browser, and interacting with applications.
        - Advantages
            - It permits you to execute cross-browser tests(Chrome, Firefox, Safari, Microsoft Edge)
            - Supports a wide array of language bindings (Java, Python, Groovy, Scala, C#, Ruby, Perl)
            - Supportes multiple Test Runner Frameworks Mocha, Cucumber, Jest, Jasmine which enable you to add BDD support to your framework

        - Potential Drawbacks
            - Cannot be used to automate Native Mobile Applications as it only supports web browser automation
            - Does not have the option to add custom commands.

- TestNG
    - TestNG is a testing framework inspired from JUnit and NUnit
    - For any UI test framework you would need a mechanism to help you identify methods you have written as different tests, categorize your different test cases under different test suites, priortize some test cases over the others, parametrize your test cases. TestNG enables you to do all of that by providing different features such as - Annotations, Support for parameters.

- Allure reporting
    -  Test automation reporting is a crucial component in an automation framework. When your automated test suites are executed, the results will be the only artifacts for which you could analyze their failures.
    -  Allure Report tool is a flexible lightweight test report tool which displays your test report in html format on your web browser. It provides a wide array of features such as
        - Provides annotation to add description to your test case
        - You can add steps in allure report to identify test actions in your test scenarios
        - You can also add attachements in allure reports such as screenshots to record test failure
        - You can also specify Severity of test case using annotation provided by allure report
- Log4J
    - Logging is a mechanism which provides you with informational logs which helps you easily troubleshoot and debug any test failures as well as keep track of different stages of test execution
    - Log4j is a logging framework written in Java that provides an easy way for logging in Selenium. In a nutshell, the framework gives out information about everything that goes on during the software execution.

# Pre-requisites/Requirements
- [Maven](https://maven.apache.org/install.html)
- [Chrome browser and respective Chromedriver.exe](https://chromedriver.chromium.org/downloads)
- [Java 1.8](https://www.java.com/en/download/)

# Steps/Commands for usage
- Clone the github repo using - *git clone git@github.com:sharvari-raut/EbayTestSuite.git*
- Open the cloned project in your choice of IDE(preferably IntellIJ), select correct JDK version while opening the project
- Once project is opened in IDE, you will see link like:
  ![image](https://user-images.githubusercontent.com/32537661/134026902-a998f121-8a49-4c1d-8d63-43bece8a81f5.png)

-  Click on *Load Maven Project*. This will auto download all depedencies added in pom.xml for your project.
-  If you don't see above link *Right click on pom.xml file -> Maven -> Reload Project*
-  Once all dependencies are downloaded to run the test suite follow either of the following two steps:
    - *In your test suite Right click on suite.xml -> Select run option*
    - *Open Command prompt -> Go to the project folder path -> Run **mvn test** command*

- Once all test cases are executed to generate allure report:
    -  Install Allure Binary binary file from this [link](https://github.com/allure-framework/allure2/releases) into a local machine and unzip it to a particular location.
       *(Make sure version of allure binary downloaded and maven dependency version is same)*
    -  Go to Windows Control Panel and Set environment variable & provide the path of this binary file till bin folder.
    -  For Validation type allure --version in command prompt to make sure that allure binary file is set up correctly.
    -  Now Execute your TestNg project again
    -  Refresh Our project now we will see one new folder named as allure-results will be generated which will contain one result and JSON file corresponding to each test case.
    -  Now-Again go to Command Prompt and type below command
       *allure serve Path of allure-result folder*
       ![image](https://user-images.githubusercontent.com/32537661/134029442-b9ae2e59-8359-44ed-b4fd-74dd33bf7cdb.png)

    - Once report is executed it will auto launched in your browser and should look like this:
      ![image](https://user-images.githubusercontent.com/32537661/134029845-a306def3-a32d-44fc-8491-2f21495cab7e.png)


# Useful resources:
- [Selenium tutorial](https://www.seleniumeasy.com/selenium-webdriver-tutorials)
- [TestNG](https://testng.org/doc/)
- [Allure reporting](https://www.swtestacademy.com/allure-report-testng/)
- [Log4J](http://www.appliedselenium.com/2019/02/how-to-use-log4j-in-selenium-automate/)