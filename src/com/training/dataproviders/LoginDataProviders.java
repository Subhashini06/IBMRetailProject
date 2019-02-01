package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.AddProductBean;
import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dao.RetailDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	/*public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}*/
	
	public Object [][] getDBData()
	{
		List<AddProductBean> list = new RetailDAO().getProduct();
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for (AddProductBean temp : list)
		{ 
			Object[] obj = new Object[2];
			obj[0] = temp.getProductName();
			obj[1] = temp.getQuantity();
			
			result[count ++] = obj;
		}
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:/Selenium/RetailProject/Excel/Testing.xlsx"; 
		String SheetName = "Sheet5";
		return new ApachePOIExcelRead().getExcelContent(fileName, SheetName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Selenium/RetailProject/Excel/Testing.xls", "Sheet1"); 
	}
}
