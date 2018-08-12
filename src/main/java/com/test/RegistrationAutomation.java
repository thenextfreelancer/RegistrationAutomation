package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegistrationAutomation
{
   public static WebDriver driver;
   
   public static final String baseUrl = "http://transport.wb.gov.in/sta-luxury-taxi-registration/";
   
   private static Properties prop = null;
   
   @BeforeClass(alwaysRun = true)
   public void setUp() throws Exception
   {
      System.setProperty("webdriver.chrome.driver", "driver" + File.separatorChar + "chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
   }
   
   public static void main(String[] args) {
      System.setProperty("webdriver.chrome.driver", "driver" + File.separatorChar + "chromedriver.exe");
      driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.manage().window().maximize();
      try
      {
         initSuite();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   @Test
   public static void initSuite() throws Exception
   {
      if(prop == null) {
         prop = loadPropertiesFile("data//data.properties");
      }
      System.out.println("Property file loaded. Press <Enter> to continue filling the form...");
      @SuppressWarnings("resource")
      Scanner sc = new Scanner(System.in);
      sc.nextLine();
      
      driver.get(baseUrl);
      
      Select permitSel = new Select(driver.findElement(By.id("permit_under")));
      permitSel.selectByVisibleText(prop.getProperty("permit_under"));
      
      Select permitDistrict = new Select(driver.findElement(By.id("district")));
      permitDistrict.selectByVisibleText(prop.getProperty("district"));
      
      try {
         driver.findElement(By.name("applicant_name")).sendKeys(prop.getProperty("full_name"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("eb_no").isEmpty())
         driver.findElement(By.id("eb_no")).sendKeys(prop.getProperty("eb_no"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("full_address").isEmpty())
         driver.findElement(By.id("full_address")).sendKeys(prop.getProperty("full_address"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("fathers_name").isEmpty())
         driver.findElement(By.id("fathers_name")).sendKeys(prop.getProperty("fathers_name"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("caste").isEmpty()) {
            Select caste = new Select(driver.findElement(By.id("caste")));
            caste.selectByVisibleText(prop.getProperty("caste"));
         }
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("gender").isEmpty()) {
            Select gender = new Select(driver.findElement(By.id("gender")));
            gender.selectByVisibleText(prop.getProperty("gender"));
         }
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("voter_id").isEmpty())
         driver.findElement(By.id("voter_id")).sendKeys(prop.getProperty("voter_id"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("mobile_no").isEmpty())
         driver.findElement(By.name("mobile_no")).sendKeys(prop.getProperty("mobile_no"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("email").isEmpty())
         driver.findElement(By.name("email")).sendKeys(prop.getProperty("email"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("interviewing_office").isEmpty())
         driver.findElement(By.id("interviewing_office")).sendKeys(prop.getProperty("interviewing_office"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("interview_date").isEmpty())
         driver.findElement(By.id("interview_date")).sendKeys(prop.getProperty("interview_date"));
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      try {
         if(!prop.getProperty("interview_shift").isEmpty()) {
            Select interview_shift = new Select(driver.findElement(By.id("interview_shift")));
            interview_shift.selectByVisibleText(prop.getProperty("interview_shift"));
         }
         
         //option 1: 1st half (11:30 AM to 1:30 PM)
         //option 2: 2nd Half (2:00 PM to 4:00 PM)
      } catch(Exception e) {
         //ignore, if field is not available.
      }
      
      String question = driver.findElement(By.id("captchaOperation")).getText();
      String[] a = question.split(" ");
      int answer = Integer.parseInt(a[0]) + Integer.parseInt(a[2]);
      System.out.println("captcha answer:"+answer);
      driver.findElement(By.name("captcha")).sendKeys(String.valueOf(answer));
      
      driver.findElement(By.id("btn_submit")).click();
   }
   
   private static Properties loadPropertiesFile(String filePath) {
      File file = new File(filePath);
      
      FileInputStream fileInput = null;
      try {
          fileInput = new FileInputStream(file);
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      }
      Properties prop = new Properties();
      
      //load properties file
      try {
          prop.load(fileInput);
      } catch (IOException e) {
          e.printStackTrace();
      }
      return prop;
   }

   @AfterClass(alwaysRun = true)
   public void tearDown() throws InterruptedException
   {
      Thread.sleep(50000);
      driver.quit();
   }

}