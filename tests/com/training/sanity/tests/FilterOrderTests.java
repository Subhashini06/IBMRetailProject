package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;

import com.training.pom.FilterOrderListPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class FilterOrderTests {

	private WebDriver driver;
	private String baseUrl;
	private FilterOrderListPOM filterOrderListPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		filterOrderListPOM = new FilterOrderListPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// This test is to validate the login
	@Test(priority = 1)
	public void validLoginTest() {
		filterOrderListPOM.sendUserName("admin");
		filterOrderListPOM.sendPassword("admin@123");
		filterOrderListPOM.clickLoginBtn();
		screenShot.captureScreenShot("FirstOrderTest_1");
	}

	// RTTC_047 - Filter details of order
	@Test(priority = 2)
	public void validOrderList() throws InterruptedException, ParseException {
		Thread.sleep(2000);
		// Filter by OrderID and verifying the order list
		filterOrderListPOM.clickSaleMenu();
		filterOrderListPOM.clickOrdersItem();
		filterOrderListPOM.sendOrderID("148");
		filterOrderListPOM.filterBtn();
		screenShot.captureScreenShot("FirstOrderTest_2");
		String resOrderID = filterOrderListPOM.rowtext.getText();
		System.out.println("Fetched from orderid:" + resOrderID);
		boolean expected = true;
		boolean actual = filterOrderListPOM.verifyOrderID(resOrderID);
		assertEquals(actual, expected);

		// Filter by Date Added and verifying the order list
		filterOrderListPOM.orderID.clear();
		filterOrderListPOM.sendDateAdded("2019-01-16");
		filterOrderListPOM.filterBtn();
		screenShot.captureScreenShot("FirstOrderTest_3");
		// This list is to fetch the order details after clicking on filter button
		List<WebElement> rows1 = filterOrderListPOM.table.findElements(By.tagName("tr"));
		// Looping the list to fetch Order details for multiple rows
		for (WebElement tdata1 : rows1) {
			String rowdata1 = tdata1.getText();
			System.out.println("Fetched from Date Added:" + rowdata1);
			boolean expected1 = true;
			boolean actual1 = filterOrderListPOM.verifyDateAdded(rowdata1);
			assertEquals(actual1, expected1);
		}

		// Filter by Customer name and verifying the order list
		filterOrderListPOM.inputDateAdded.clear();
		filterOrderListPOM.sendCustomerName("subhashini rangaraju");
		filterOrderListPOM.filterBtn();
		screenShot.captureScreenShot("FirstOrderTest_3");
		// This list is to fetch the order details after clicking on filter button
		List<WebElement> rows2 = filterOrderListPOM.table.findElements(By.tagName("tr"));
		// Looping the list to fetch Order details for multiple rows
		for (WebElement tdata2 : rows2) {
			String rowdata2 = tdata2.getText();
			System.out.println("Fetched from customer name:" + rowdata2);
			boolean expected2 = true;
			boolean actual2 = filterOrderListPOM.verifyCustomer(rowdata2);
			assertEquals(actual2, expected2);
		}

		// Filter by Total and verifying the order list
		filterOrderListPOM.customerName.clear();
		filterOrderListPOM.sendTotal("350");
		filterOrderListPOM.filterBtn();
		screenShot.captureScreenShot("FirstOrderTest_4");
		// This list is to fetch the order details after clicking on filter button
		List<WebElement> rows3 = filterOrderListPOM.table.findElements(By.tagName("tr"));
		// Looping the list to fetch Order details for multiple rows
		for (WebElement tdata3 : rows3) {
			String rowdata3 = tdata3.getText();
			System.out.println("Fetched from total:" + rowdata3);
			boolean expected3 = true;
			boolean actual3 = filterOrderListPOM.verifyCustomer(rowdata3);
			assertEquals(actual3, expected3);
		}

		// Filter by Date modified and verifying the order list
		filterOrderListPOM.total.clear();
		filterOrderListPOM.sendDateModified("2019-01-14");
		filterOrderListPOM.filterBtn();
		screenShot.captureScreenShot("FirstOrderTest_5");
		// This list is to fetch the order details after clicking on filter button
		List<WebElement> rows4 = filterOrderListPOM.table.findElements(By.tagName("tr"));
		// Looping the list to fetch Order details for multiple rows
		for (WebElement tdata4 : rows4) {
			String rowdata4 = tdata4.getText();
			System.out.println("Fetched from Date Added:" + rowdata4);
			boolean expected4 = true;
			boolean actual4 = filterOrderListPOM.verifyDateModified(rowdata4);
			assertEquals(actual4, expected4);
		}

		// Filter by Order Status and verifying the order list
		filterOrderListPOM.inputDateModified.clear();
		filterOrderListPOM.sendorderStatus("Pending");
		filterOrderListPOM.filterBtn();
		// This list is to fetch the order details after clicking on filter button
		List<WebElement> rows5 = filterOrderListPOM.table.findElements(By.tagName("tr"));
		// Looping the list to fetch Order details for multiple rows
		for (WebElement tdata5 : rows5) {
			String rowdata5 = tdata5.getText();
			System.out.println("Fetched from order status:" + rowdata5);
			boolean expected5 = true;
			boolean actual5 = filterOrderListPOM.verifyCustomer(rowdata5);
			assertEquals(actual5, expected5);
		}

	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
