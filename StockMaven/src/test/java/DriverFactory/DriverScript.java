package DriverFactory;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;
public class DriverScript 
{
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;
    
	public void startTest() throws Throwable
	{
		ExcelFileUtil excel = new ExcelFileUtil();
		for (int i=1; i<=excel.rowCount("MasterTestCases");i++)
		{
			String ModuleStatus="";
			
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				//define the module
				String TCModule = excel.getData("MasterTestCases", i, 1);
				report = new ExtentReports("D:\\MohanSB\\StockMaven\\Reports\\"+TCModule+FunctionLibrary.generateDate()+".html");
		        logger=report.startTest(TCModule);		
				for (int j=1; j<=excel.rowCount(TCModule);j++)
				{	
					String Description = excel.getData(TCModule, j, 0);
					System.out.println(Description);
					String Object_Type = excel.getData(TCModule, j, 1);
					String Locator_Type = excel.getData(TCModule, j, 2);
					String Locator_Value = excel.getData(TCModule, j, 3);
					String Test_data = excel.getData(TCModule, j, 4);
					
					try
					{
					if (Object_Type.equalsIgnoreCase("startBrowser"))
					{
					  driver = FunctionLibrary.startBrowser(driver);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("openApplication"))
					{
					  FunctionLibrary.openApplication(driver);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("waitForElement"))
					{
					  FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_data);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("typeAction"))
					{
					  FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_data);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("clickAction"))
					{
					  FunctionLibrary.ClickAction(driver, Locator_Type, Locator_Value);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("closeBrowser"))
					{
					  FunctionLibrary.closeBrowser(driver);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("mouseAction"))
					{
					  FunctionLibrary.mouseAction(driver);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("clickStockCategories"))
					{
					  FunctionLibrary.clickStockCategories(driver);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("captureData"))
					{
					  FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
					  logger.log(LogStatus.INFO, Description);
					}
					if (Object_Type.equalsIgnoreCase("tableValidation"))
					{
					  FunctionLibrary.tableValidation(driver, Test_data);
					  logger.log(LogStatus.INFO, Description);
					}
					excel.setData(TCModule, j, 5,"PASS");
					logger.log(LogStatus.INFO, "PASS");
					ModuleStatus="true";
					} catch(Exception e)
					{
						File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						String fileName = "D:\\MohanSB\\StockMaven\\Screenshots\\"+Description+FunctionLibrary.generateDate()+".png";
						FileUtils.copyFile(srcFile, new File(fileName));
						excel.setData(TCModule, j, 5,"FAIL");
						logger.log(LogStatus.FAIL, "FAIL");
						ModuleStatus="false";
						break;
					}
				}
				if (ModuleStatus.equalsIgnoreCase("true"))	
				{
					excel.setData("MasterTestCases",i, 3,"PASS");
					logger.log(LogStatus.INFO, "PASS");
				}else
					if (ModuleStatus.equalsIgnoreCase("false"))	
					{
						excel.setData("MasterTestCases",i, 3,"FAIL");
						logger.log(LogStatus.FAIL, "FAIL");
					}
			    report.endTest(logger);
			    report.flush();
			}
			else
			{
				excel.setData("MasterTestCases",i, 3,"Not Executed");	
			}
		}
	}

}
