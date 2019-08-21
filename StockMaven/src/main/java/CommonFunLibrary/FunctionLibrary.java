package CommonFunLibrary;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	
	WebDriver driver;
	
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
     	{
		  driver=new FirefoxDriver();
	    }else
	    	if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
 	        {
 	           System.setProperty("webdriver.chrome.driver","D:\\MohanSB\\StockAccounting\\CommonJarFiles\\chromedriver.exe");
	           driver=new ChromeDriver();
            }else	
            	if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE"))
 	           {
 	           System.setProperty("webdriver.ie.driver", "D:\\MohanSB\\StockAccounting\\CommonJarFiles\\IEDriverServer.exe");
	           driver=new InternetExplorerDriver();
               }
		return driver;
	}

	//openApplication
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.manage().window().maximize();		
		driver.get(PropertyFileUtil.getValueForKey("URL"));		
	}
	
	//click Action
	public static void ClickAction(WebDriver driver, String locatorType,String locatorValue)
	{
		if (locatorType.equalsIgnoreCase("id"))
		{
		  driver.findElement(By.id(locatorValue)).click();
		}else
			if (locatorType.equalsIgnoreCase("name"))
			{
			  driver.findElement(By.name(locatorValue)).click();
			}else
				if (locatorType.equalsIgnoreCase("xpath"))
				{
				  driver.findElement(By.xpath(locatorValue)).click();
				}
				
	}
	
	//type Action
	public static void typeAction(WebDriver driver, String locatorType,String locatorValue,String data)
	{
		if (locatorType.equalsIgnoreCase("id"))
		{
		  driver.findElement(By.id(locatorValue)).clear();
		  driver.findElement(By.id(locatorValue)).sendKeys(data);
		}else
			if (locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}else
				if (locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(data);
				}
				
	}
	
	//CloseBrowser
	public static void closeBrowser(WebDriver driver)
	{
	  driver.close();	
	}
	
	public static void waitForElement(WebDriver driver, String locatorType,String locatorValue,String waitTime)
	{
	  WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(waitTime));
	  if (locatorType.equalsIgnoreCase("id"))
	  {
		  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue))); 
	  }else
		  if (locatorType.equalsIgnoreCase("name"))
		  {
			  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue))); 
		  }else
			  if (locatorType.equalsIgnoreCase("xpath"))
			  {
				  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))); 
			  }
	}
	
	public static void mouseAction(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
	}
	
	public static void clickStockCategories(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"))).build().perform();
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a"))).click().build().perform();
		Thread.sleep(2000);
	}
	
	public static void main(String[] args) throws Throwable {
		
		WebDriver MyDriver;		
		MyDriver = FunctionLibrary.startBrowser(new FirefoxDriver());
	}
	
	public static void captureData(WebDriver driver, String locatorType,String locatorValue) throws Throwable
	{
   	   String data="";
   	   
   	   if (locatorType.equalsIgnoreCase("id"))
   	   {
   		 data=driver.findElement(By.id(locatorValue)).getAttribute("value");   		 
   	   }else
   		 if (locatorType.equalsIgnoreCase("name"))
     	   {
     		 data=driver.findElement(By.name(locatorValue)).getAttribute("value");   		 
     	   }else
     	   		 if (locatorType.equalsIgnoreCase("xpath"))
     	     	   {
     	     		 data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");   		 
     	     	   }
   		   
   	   
   	   FileWriter fw = new FileWriter("D:\\MohanSB\\StockAccounting\\captureData\\Data.txt");
   	   BufferedWriter bw = new BufferedWriter(fw);
   	   bw.write(data);
   	   bw.flush();
   	   bw.close();
   	  
	}
	
	public static void tableValidation(WebDriver driver,String colNum ) throws Throwable
	{
		FileReader fr= new FileReader("D:\\MohanSB\\StockAccounting\\captureData\\Data.txt");
		BufferedReader br = new BufferedReader(fr);
		String exp_data=br.readLine();
		int colNum1=Integer.parseInt(colNum);
        Thread.sleep(2000);
        if (!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).isDisplayed())
        {
        	driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
            Thread.sleep(2000);	
        }  
        driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
        Thread.sleep(2000);
        driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
        Thread.sleep(2000);
        
        WebElement webtable=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable")));
        List<WebElement> rows=webtable.findElements(By.tagName("tr"));
        
        for (int i=1;i<=rows.size();i++)
        {
          String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum1+"]/div/span/span")).getText();          
          System.out.println(act_data);
          Assert.assertEquals(exp_data, act_data);
          break;        	
        }
	}
	
	public static String generateDate()
	{
	  Date date = new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_hh_mm_ss");
	  return sdf.format(date);
	}

}

