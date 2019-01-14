package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.FilterOrderDetails;
import com.training.pom.FilterReturnListPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterTest;

public class ReturnOrderTests {
	
	private WebDriver driver;
	private String baseUrl;
	private FilterReturnListPOM filterReturnListPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
  

  @BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterReturnListPOM = new FilterReturnListPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
  
  //This test is to validate the login 
  @Test (priority = 1)
	public void validLoginTest() {
	  filterReturnListPOM.sendUserName("admin");
	  filterReturnListPOM.sendPassword("admin@123");
	  filterReturnListPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("ReturnOrderTests_1");
	}
  
  /* Test case ID: RTTC_018
   * Description: This test is to validate the Return order details by performing the Filter by ReturnID and filter by Customer name
  */
  @Test (priority = 2)
	public void validOrderDetails() throws InterruptedException {
		Thread.sleep(2000);
		////Filter by ReturnID and verifying the list
		filterReturnListPOM.clickSaleMenu();
		filterReturnListPOM.clickReturns();
		filterReturnListPOM.sendReturnID("25");
		filterReturnListPOM.filterBtn();
		screenShot.captureScreenShot("ReturnOrderTests_2");
		String resreturnID = filterReturnListPOM.rowtext.getText();
		System.out.println(" Fetched from Returnid:" +resreturnID);
		boolean expected = true;
		boolean actual = filterReturnListPOM.verifyreturnID(resreturnID);
		assertEquals(actual, expected);
		//Filter by Customer name and verifying the order list
		filterReturnListPOM.returnID.clear();
		filterReturnListPOM.sendretCustomerName("subhashini rangaraju");
		filterReturnListPOM.filterBtn(); 
		screenShot.captureScreenShot("ReturnOrderTests_3");
		//This list is to fetch the return order details after clicking on filter button
		List<WebElement> rows = filterReturnListPOM.table.findElements(By.tagName("tr"));
		//Looping the list to fetch Return Order details for multiple rows
		for(WebElement tdata: rows)
		{
			String rowdata = tdata.getText();
			System.out.println("Fetched from customer name:" +rowdata);
			boolean expected1 = true;
			boolean actual1 = filterReturnListPOM.verifyCustomer(rowdata);
			assertEquals(actual1, expected1);
		}
		
	}
  

  @AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
