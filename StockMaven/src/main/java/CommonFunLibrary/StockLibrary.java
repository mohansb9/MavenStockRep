package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockLibrary {
	
	String res;
	WebDriver driver;
	
	//App Launch
	public String appLaunch(String url)
	{
	  System.setProperty("webdriver.chrome.driver", "D:\\MohanSB\\StockAccounting\\CommonJarFiles\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get(url);
	  driver.manage().window().maximize();
	  //validation
	  if (driver.findElement(By.id("username")).isDisplayed()) 
	  {
		res="Pass";		
	   }
	  else
	  {
		 res="Fail";
	  }
	  return res;	  		
	}
	
	public static void main(String[] args) throws Throwable
	{
	  StockLibrary app = new StockLibrary();
	  String results = app.appLaunch("http://webapp.qedge.com/login.php");
	  //System.out.println(results);
	  app.appLogin("admin","master");
	  Thread.sleep(2000);
	  //String result = app.supplierCreation("redmimc","ameerpet","Hyd","mc","india","1234","mc@12344","3324234","sb notes sb");
	  String result = app.createStockCategory("MyCat29jul20193");
	  System.out.println(result);
	  app.appLogout();
	  app.appClose();				
	}
	
	public String appLogin(String username,String password)
	{
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(username);
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(password);
	  driver.findElement(By.id("btnsubmit")).click();
	  if (driver.findElement(By.id("logout")).isDisplayed())
	  {
		res="Pass";	
	  }
	  else
	  {
		res="Fail";
	  }
	  return res;
	}
	public String appLogout() throws Exception
	{
		driver.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		//validation
		if (driver.findElement(By.id("username")).isDisplayed())
		{
		  res="Pass";
		}
		else
		{
		 res="Fail";		 	
		}
	
		return res;		
	}
	
	public void appClose()
	{
	   driver.close();			
	}
	
	public String supplierCreation(String suppliername,String address,String city,String country,String contactperson,String phonenumber,String email,String mobilenumber,String notes) throws Exception
	{
		driver.findElement(By.xpath("//*[@id='mi_a_suppliers']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
		Thread.sleep(2000);
		String exp_data = driver.findElement(By.xpath("//*[@id='x_Supplier_Number']")).getAttribute("value");	
		System.out.println(exp_data);
		driver.findElement(By.xpath("//*[@id='x_Supplier_Name']")).sendKeys(suppliername);
		//driver.findElement(By.xpath("//*[@id='x_Supplier_Name']")).sendKeys(suppliername);
		driver.findElement(By.xpath("//*[@id='x_Address']")).sendKeys(address);
		driver.findElement(By.xpath("//*[@id='x_City']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id='x_Country']")).sendKeys(country);
		driver.findElement(By.xpath("//*[@id='x_Contact_Person']")).sendKeys(contactperson);
		driver.findElement(By.xpath("//*[@id='x_Phone_Number']")).sendKeys(phonenumber);
		driver.findElement(By.xpath("//*[@id='x__Email']")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='x_Mobile_Number']")).sendKeys(mobilenumber);
	    driver.findElement(By.xpath("//*[@id='x_Notes']")).sendKeys(notes);	
	   Actions action = new Actions(driver);
	   action.sendKeys(Keys.PAGE_DOWN).build().perform();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id='btnAction']")).click();	
	    Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();    
        Thread.sleep(2000);
        if (!driver.findElement(By.xpath("//*[@id='psearch']")).isDisplayed())
        {
        	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
            Thread.sleep(2000);	
        }        
        driver.findElement(By.xpath("//*[@id='psearch']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_data);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
        Thread.sleep(2000);
      //*[@id="el1_a_suppliers_Supplier_Number"]/span
        String act_data = driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
        System.out.println(act_data);
       if (exp_data.equals(act_data))
       {
        res="Pass";      	   
       }
       else
       {
    	res="Fail";
       }       
       return res;                     
	}
	
	public String createStockCategory(String categoryName) throws Exception
	{
		/*driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a")).click();
		*/
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"))).build().perform();
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a"))).click().build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='x_Category_Name']")).sendKeys(categoryName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		Thread.sleep(2000);        
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(categoryName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		Thread.sleep(2000);
		String act_stockCategory = driver.findElement(By.xpath("//*[@id='el1_a_stock_categories_Category_Name']/span")).getText();
		System.out.println(categoryName);
		System.out.println(act_stockCategory);
		if (categoryName.equals(act_stockCategory)) {
		   res="Pass";
		}
		else
		{
			res="Fail";			
		}
				
		return res;
	  	
	}
	
}



