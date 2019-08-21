package CommonFunLibrary;

import Utilities.ExcelFileUtil;

public class DataDrivenStockAccounting {
	
	

	public static void main(String[] args) throws Throwable {
	    ExcelFileUtil excel = new ExcelFileUtil();
	    StockLibrary stock = new StockLibrary();
	    stock.appLaunch("http://webapp.qedge.com/login.php");
	    stock.appLogin("admin","master");
	    excel.setData("SupplierData", 0, 9,"Status");
	    for (int i = 1; i<=excel.rowCount("SupplierData"); i++) {
	    	String suppliername = excel.getData("SupplierData", i, 0);
	    	String address = excel.getData("SupplierData", i, 1);
	    	String city = excel.getData("SupplierData", i, 2);
	    	String country = excel.getData("SupplierData", i, 3);
	    	String contactperson = excel.getData("SupplierData", i, 4);
	    	String phonenumber = excel.getData("SupplierData", i, 5);
	    	String email = excel.getData("SupplierData", i, 6);
	    	String mobilenumber = excel.getData("SupplierData", i, 7);	    	
	    	String notes = excel.getData("SupplierData", i, 8);
	    	String result = stock.supplierCreation(suppliername, address, city, country, contactperson, phonenumber, email, mobilenumber, notes);	    	
	    	excel.setData("SupplierData", i, 9,result);	    	
	    }	
    	stock.appLogout();
    	stock.appClose();	
	}

}
