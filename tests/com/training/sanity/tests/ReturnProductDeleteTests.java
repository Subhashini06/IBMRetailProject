package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;

import com.training.pom.ReturnProductDeletePOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class ReturnProductDeleteTests {

	private WebDriver driver;
	private String baseUrl;
	private ReturnProductDeletePOM returnProductDeletePOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		returnProductDeletePOM = new ReturnProductDeletePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// This test is to validate the login
	@Test(priority = 1)
	public void validLoginTest() {
		returnProductDeletePOM.sendUserName("admin");
		returnProductDeletePOM.sendPassword("admin@123");
		returnProductDeletePOM.clickLoginBtn();
		screenShot.captureScreenShot("ReturnProductDeleteTests_1");
	}

	// RTTC_049 - Allow admin to return product of customer & delete from return list
	// This test is to perform return order
	@Test(priority = 2)
	public void createReturnOrder() throws InterruptedException {
		returnProductDeletePOM.clickSaleMenu();
		returnProductDeletePOM.clickReturns();
		returnProductDeletePOM.clickAddBtn();
		returnProductDeletePOM.sendReturnOrderID("82");
		returnProductDeletePOM.sendReturnDate("2019-01-18");
		returnProductDeletePOM.sendReturnCustomerName("Shiromit");
		returnProductDeletePOM.sendFirstName("subhashini");
		returnProductDeletePOM.sendLastName("rangaraju");
		returnProductDeletePOM.sendEmail("subhashini.rangaraju@gmail.com");
		returnProductDeletePOM.sendTelephone("9448709548");
		returnProductDeletePOM.sendReturnProduct("Diamond necklace");
		returnProductDeletePOM.selectProductName();
		returnProductDeletePOM.sendmodel("SKU-06");
		returnProductDeletePOM.clickSaveBtn();
		screenShot.captureScreenShot("ReturnProductDeleteTests_2");

		// Verify the return order created is present in the table
		String res = returnProductDeletePOM.returnOrderTable.getText();
		System.out.println(res);
		String expected1 = "82";
		assertTrue(res.contains(expected1));

	}

	// Delete the return order
	@Test(priority = 3)
	public void validReturnProductDelete() throws InterruptedException {
		Thread.sleep(2000);
		returnProductDeletePOM.tablerowcheckbox();
		returnProductDeletePOM.deleteBtn();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String message = returnProductDeletePOM.alertMsg.getText();
		System.out.println(message);
		screenShot.captureScreenShot("ReturnProductDeleteTests_3");
		String expected = "Success: You have modified returns!";
		assertTrue(message.contains(expected));
		
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
