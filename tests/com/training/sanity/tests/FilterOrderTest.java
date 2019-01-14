package com.training.sanity.tests;

import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.FilterOrderDetails;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class FilterOrderTest {

	private WebDriver driver;
	private String baseUrl;
	private FilterOrderDetails filterOrderDetails;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterOrderDetails = new FilterOrderDetails(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
	
	//This test is to validate the login 
	@Test (priority = 1)
	public void validLoginTest() {
		filterOrderDetails.sendUserName("admin");
		filterOrderDetails.sendPassword("admin@123");
		filterOrderDetails.clickLoginBtn(); 
		screenShot.captureScreenShot("FirstOrderTest_1");
	}

	/* Test case ID: RTTC_016
	   * Description: This test is to validate the Order details by performing the Filter by OrderID and filter by Customer name
	  */
	@Test (priority = 2)
	public void validOrderDetails() throws InterruptedException {
		Thread.sleep(2000);
		//Filter by OrderID and verifying the order list
		filterOrderDetails.clickSaleMenu();
		filterOrderDetails.clickOrdersItem();
		filterOrderDetails.sendOrderID("148");
		filterOrderDetails.filterBtn();
		screenShot.captureScreenShot("FirstOrderTest_2");
		String resOrderID = filterOrderDetails.rowtext.getText();
		System.out.println("Fetched from orderid:" +resOrderID);
		boolean expected = true;
		boolean actual = filterOrderDetails.verifyOrderID(resOrderID);
		assertEquals(actual, expected);
		//Filter by Customer name and verifying the order list
		filterOrderDetails.orderID.clear();
		filterOrderDetails.sendCustomerName("subhashini rangaraju");
		filterOrderDetails.filterBtn(); 
		screenShot.captureScreenShot("FirstOrderTest_3");
		//This list is to fetch the order details after clicking on filter button
		List<WebElement> rows = filterOrderDetails.table.findElements(By.tagName("tr"));
		//Looping the list to fetch Order details for multiple rows
		for(WebElement tdata: rows)
		{
			String rowdata = tdata.getText();
			System.out.println("Fetched from customer name:" +rowdata);
			boolean expected1 = true;
			boolean actual1 = filterOrderDetails.verifyCustomer(rowdata);
			assertEquals(actual1, expected1);
		}
	
	}

	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}