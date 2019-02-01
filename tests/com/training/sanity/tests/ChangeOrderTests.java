package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ChangeOrderPOM;
import com.training.pom.FilterOrderDetails;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class ChangeOrderTests {

	private WebDriver driver;
	private String baseUrl;
	private ChangeOrderPOM changeOrderPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	// This test is to validate the login
	@Test(priority = 1)
	public void validLoginTest() {
		changeOrderPOM.sendUserName("admin");
		changeOrderPOM.sendPassword("admin@123");
		changeOrderPOM.clickLoginBtn();
		screenShot.captureScreenShot("ChangeOrderTests_1");
	}
	
	//RTTC_046 - This test is to validate the Edit order
		@Test(priority = 2)
		public void validChangeOrder() throws InterruptedException {
			changeOrderPOM.clickSaleMenu();
			changeOrderPOM.clickOrdersItem();
			String orderTab = driver.getWindowHandle();
			//Click on Edit button
			changeOrderPOM.tableRowEditBtn();
			//Switching to New Tab
			ArrayList<String> customerTab = new ArrayList<String>(driver.getWindowHandles());
			Thread.sleep(2000);
			//System.out.println(customerTab.size());
			//Opens Edit Order Tab 
			driver.switchTo().window(customerTab.get(0));
			System.out.println(driver.getTitle());
			Thread.sleep(2000);
			screenShot.captureScreenShot("ChangeOrderTests_1");
			changeOrderPOM.clicktabCustomerContinueBtn();
			//Click on Remove button in the product table
			changeOrderPOM.clicktabCartRemoveBtn();
			//Choose product, enter the Quantity an\d click on Add button 
			changeOrderPOM.sendtabCartProductName("Samsung");
			changeOrderPOM.selectProductName();
			Thread.sleep(2000);
			changeOrderPOM.sendtabCartProductQty("1");
			changeOrderPOM.clicktabCartAddProductBtn();
			//Verify the added product details in the product table
			String res = changeOrderPOM.producttable.getText();
			System.out.println(res);
			boolean expected1 = true;
			boolean actual1= changeOrderPOM.verifyProductTable(res);
			assertEquals(actual1, expected1);
			
			Thread.sleep(2000);
			screenShot.captureScreenShot("ChangeOrderTests_2");
			changeOrderPOM.clicktabCartContinueBtn();
			Thread.sleep(2000);
			screenShot.captureScreenShot("ChangeOrderTests_3");
			changeOrderPOM.clicktabPaymentContinueBtn();
			Thread.sleep(2000);
			screenShot.captureScreenShot("ChangeOrderTests_4");
			changeOrderPOM.clicktabShippingContinueBtn();
			Thread.sleep(2000);
			screenShot.captureScreenShot("ChangeOrderTests_5");
			changeOrderPOM.selecttabShippingMethodDropdown("Free Shipping - Rs.0");
			changeOrderPOM.clicktabShippingSaveBtn();
			screenShot.captureScreenShot("ChangeOrderTests_6");
			
			String message = changeOrderPOM.successMsg.getText();
			System.out.println(message);
			String expected = "Success: You have modified orders!";
			assertTrue(message.contains(expected));
			
			
		}
	

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		changeOrderPOM = new ChangeOrderPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
	
	

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//driver.quit();
	}

}
