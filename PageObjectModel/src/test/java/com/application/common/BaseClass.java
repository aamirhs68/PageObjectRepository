package com.application.common;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends PageActions{
	
 
 
 private final static String filepath="TestData\\TestData.xlsx";
 private static File file;
 private static FileInputStream FIS;
 //private static FileOutputStream FOS;
 private static XSSFWorkbook workbook;
 public static HashMap<String, String> testData;
 public static HashMap<String, String> loginTestData;
 
 private static WebDriver driver;
 
 public static ExtentHtmlReporter htmlreport;
 public static ExtentReports report;
 public static ExtentTest logger;
 
 public WebDriver getDriver() throws IOException
 {
	 return driver;
 }
 
 @BeforeSuite
 public void setup() throws IOException
 {
	 getLoginData();	 
	 
	 htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\extent\\ExtentReport.html");
	 //System.out.println(System.getProperty("user.dir") +"\\extent\\ExtentReport.html");
	 htmlreport.config().setDocumentTitle("Automation Framework Extent Report");
	 htmlreport.config().setReportName("First Test Case");
	 htmlreport.config().setTheme(Theme.STANDARD);

	 report= new ExtentReports();
	 report.attachReporter(htmlreport);
	 logger = report.createTest("First Test Case");
	 
 }
 
 @AfterSuite
 public void teardown()
 {
	
	 
     report.flush();
     
     driver.close();
 }
 
 public static void invokeBrowser() throws IOException
 {
	 getTestData(filepath, filepath);  // add sheet and test test case method name
	 
	 browserSetup();
	 
	 enterUrl();
	 
 }
 
 public static WebDriver browserSetup() throws IOException {
	 
	 Properties properties = new Properties();
	 FileInputStream fis= new FileInputStream("C:\\Users\\mohammad.aamir\\eclipse-workspace\\PageObjectModel\\util.properties");
	 properties.load(fis);
	 
	 
	 if(properties.getProperty("browser").equalsIgnoreCase("chrome"))
	 {
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 
	 }
	 
	 if(properties.getProperty("browser").equalsIgnoreCase("edge"))
	 {
		 WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();
		 
	 }
	 
	 return driver;
 }
 
 public static void enterUrl() {
	 
	 driver.get(loginTestData.get("url"));
	 driver.manage().window().maximize();
 
 }
 
 public void getProperties() throws IOException
 {
	 Properties properties = new Properties();
	 FileInputStream fis= new FileInputStream("C:\\Users\\mohammad.aamir\\eclipse-workspace\\PageObjectModel\\util.properties");
	 properties.load(fis);
	 properties.getProperty("browser");
 }
 
 public static void getTestData(String sheetName, String testCase) throws IOException
 {
	 file = new File(filepath);
	 FIS = new FileInputStream(file);
	 workbook= new XSSFWorkbook(FIS);
	 testData = new LinkedHashMap<String, String>();
	 
	 XSSFSheet moduleSheet= workbook.getSheet(sheetName);
	 
	 try {
		 
		 int rowCount= moduleSheet.getPhysicalNumberOfRows();
		 int columnCount= moduleSheet.getRow(0).getPhysicalNumberOfCells();
		 
		 for (int i=0;i<rowCount;i++)
		 {
			 String caseId = moduleSheet.getRow(i).getCell(0).toString();
			 
			 if(caseId.equals(testCase))
			 {
				 for(int j=0;j<columnCount;j++)
				 {
					 String key= moduleSheet.getRow(0).getCell(j).toString();
					 String value = moduleSheet.getRow(i).getCell(j).toString();
					 
					 testData.put(key, value);
				 }
			 }
		 }
	 }
	 
	 catch(NullPointerException e)
	 {
		 e.printStackTrace();
	 }
 }
 
 
	 public static void getLoginData() throws IOException
	 {
		 file = new File(filepath);
		 FIS = new FileInputStream(file);
		 workbook = new XSSFWorkbook(FIS);
		 loginTestData = new LinkedHashMap<String, String>();
		 
		 XSSFSheet moduleSheet= workbook.getSheet("Credentials");
		 
		 try {
			 
			 int rowCount= moduleSheet.getPhysicalNumberOfRows();
			 int columnCount= moduleSheet.getRow(0).getPhysicalNumberOfCells();
			 
			 for (int i=0;i<rowCount;i++)
			 {
				 String caseId = moduleSheet.getRow(i).getCell(0).toString();
				 
				 if(caseId.equals("Yes"))
				 {
					 for(int j=0;j<columnCount;j++)
					 {
						 
						 String key= moduleSheet.getRow(0).getCell(j).toString();
						 String value = moduleSheet.getRow(i).getCell(j).toString();
						 
						 loginTestData.put(key, value);
					 }
				 }
			 }
		 }
		 catch(NullPointerException e)
		 {
			 e.printStackTrace();
		 }

	 
 }
    
}
