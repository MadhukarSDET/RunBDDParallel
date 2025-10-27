Cucumber + Selenium + JUnit + ExtentReports Sample Framework
==========================================================

How to run:
1. Update browsers in src/test/resources/config/config.properties (browser=chrome/edge/firefox)
2. Run: mvn clean test
3. Extent report: target/extent-report/extent.html

Tags:
- @Smoke and @Regression are included in sample feature.

Note:
- This sample uses WebDriverManager to download drivers automatically.
- The framework uses a JUnit-based Cucumber runner (CucumberOptions in TestRunner).
