# Todo_MVC

Setup Instructions - Prerequisite

Install IntelliJ
  1) Install Java 22 and set path variables
  2) Install Maven and set path variables
  3) TestNG will be bundled in IntelliJ, it is not required to explicitly add the plugin
  4) Clone the repository and start using
  5) Instructions to run the project

Run the TC.xml file as TestNG test

Project Structure
  1. Base.java consists the code to setup and launch the browser also quits the browser at the end of execution.
  2. Methods.java contains the definition of addItem, delItem, markComplete, activeFilter, completedFilter and assertions/verifications of each operation.
  3. TestCase.java is where we call all the methods required for test scenarios.
  4. TC.xml holds the Testcase class and helps us run the automation.
