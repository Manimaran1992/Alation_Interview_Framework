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
1. 



