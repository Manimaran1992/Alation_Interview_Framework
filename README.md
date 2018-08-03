Avation Interview Framework TestNG Maven

Precondition: 
Install Maven and Java on your local machine to run the test script.

Maven installation steps: https://www.mkyong.com/maven/install-maven-on-mac-osx/

Java installation steps: https://www.java.com/en/download/help/mac_10_10.xml

Test Execution Steps:
1. Download the framework on below github link

2. unzip and paste the code on your local machine

3. Open the "Terminal" on you Mac machine.

4. Navigate to framework folder. Run below command on Terminal

>> cd "framework_folder"

5. Run the below command to run the maven project

>> mvn test

6. Once execution over navigate to below file to view the Extent report, 

it will show you the detailed test exection status.

"framework_folder">>\Extent_Report\ExtentReport.html

7. Once execution over navigate to below file to view the text file to 

view the saved details

"framework_folder">>\src\test\resources\product_data

\product_informations.txt

8. You can view the testcase Pass and Failed screenshots on below folder

"framework_folder">>\src\test\resources\screenshots


Note: https://www.amazon.com I am unable to click on Books category using 

selenium methods like select, list, robot, actions. In my current project 

for these kind of issues we use "Raft" desktop automation tool. "Raft" is 

an apple proprietary tool and we cannot use this tool outside of my 

project. For windows we can use Autoit tool for these kind of issues. For 

this testcase now I have hardcoded the category selection method using 

load the category url.


Framework Structure Explanation:
1. I have designed TestNG Maven project
2. pom.xml file contains selenium, testng, apache commons and extent report dependencies. Added surefire plugin dependency to build and run the maven project on terminal.
3. Below page object folder you can see all the pageobject class files
\src\test\java\pageobject\amazon_pageobject.java
3.1. On top of page object file you can see the locators. On top of page object file you can modify the locators.
4. Below test case folder you can see all the testcase class files
Avation_interview\src\test\java\testcase\catalog_search.java
4.1. On top testcase file initialized selenium webdriver
4.2. Initialized testdata globally which are going to use it on testcase file
4.3. Initialized extent report and extent report log
4.4. Under Before suite initialized extent report environment
4.5. Under Before method initialized configured browser property, maximize window and implicit wait
4.6. Under @Test method start the extent report logger and call the page object methods which are required for the testcase
4.7. Under After method take screenshot for Pass and failed test case, then log the test status on extent report and quit the webdriver
4.8. Under After suite flush and close the extent report logger
5. View the automation test execution results on below extent report
Avation_interview\Extent_Report\ExtentReport.html
6. Under below resources folder we can see the screenshots, browser driver and book details which are captured on automation test script
Avation_interview\src\test\resources
