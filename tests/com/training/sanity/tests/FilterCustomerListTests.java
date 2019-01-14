package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.FilterCustomerListPOM;
import com.training.pom.FilterOrderDetails;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class FilterCustomerListTests {
	
	private WebDriver driver;
	private String baseUrl;
	private FilterCustomerListPOM filterCustomerListPOM;
	private static Properties properties;
	private ScreenShot screenShot;
  

  
  @BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterCustomerListPOM = new FilterCustomerListPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
  
  //This test is to validate the login 
  @Test (priority = 1)
	public void validLoginTest() {
	  filterCustomerListPOM.sendUserName("admin");
	  filterCustomerListPOM.sendPassword("admin@123");
	  filterCustomerListPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("FilterCustomerListTests_1");
	}

  /* Test case ID: RTTC_020
   * Description: This test is to validate the Customer details by performing the Filter by Customer name and filter by Email
  */
	@Test (priority = 2)
	public void validOrderDetails() throws InterruptedException {
		Thread.sleep(2000);
		//Fetching the customer details by Customer name
		filterCustomerListPOM.clickCustomerMenu();
		filterCustomerListPOM.clickCustomers();
		filterCustomerListPOM.sendCustomerName("subhashini rangaraju");
		filterCustomerListPOM.filterBtn();
		screenShot.captureScreenShot("FilterCustomerListTests_2");
		//This list is to fetch the customer details after clicking on filter button
		List<WebElement> rows = filterCustomerListPOM.table.findElements(By.tagName("tr"));
		//Looping the list to fetch customer details for multiple rows
		for(WebElement tdata: rows)
		{
			String rowdata = tdata.getText();
			System.out.println("Fetched from customer name" +rowdata);
			boolean expected1 = true;
			boolean actual1 = filterCustomerListPOM.verifyCustomerDet(rowdata);
			assertEquals(actual1, expected1);
		}
		
		//Fetching the customer details by Email
		filterCustomerListPOM.customerName.clear();
		filterCustomerListPOM.sendEmail("a@yahoo.com");
		filterCustomerListPOM.filterBtn(); 
		screenShot.captureScreenShot("FilterCustomerListTests_3");
		String resEmail = filterCustomerListPOM.rowtext.getText();
		System.out.println("Fetched from Email" +resEmail);
		boolean expected1 = true;
		boolean actual1 = filterCustomerListPOM.verifyEmail(resEmail);
		assertEquals(actual1, expected1);
		
	}
  

  @AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
