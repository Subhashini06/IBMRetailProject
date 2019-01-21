package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;

import com.training.pom.RewardPointCustomerPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class RewaedPointCustomerTests {

	private WebDriver driver;
	private String baseUrl;
	private RewardPointCustomerPOM rewardPointCustomerPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		rewardPointCustomerPOM = new RewardPointCustomerPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	// This test is to validate the login
	@Test(priority = 1)
	public void validLoginTest() {
		rewardPointCustomerPOM.sendUserName("admin");
		rewardPointCustomerPOM.sendPassword("admin@123");
		rewardPointCustomerPOM.clickLoginBtn();
		screenShot.captureScreenShot("RewaedPointCustomerTests_1");
	}

	// RTTC_050 - edit Customer details & add reward points
	@Test(priority = 2)
	public void validCustomerRewardPoints() throws InterruptedException {
		rewardPointCustomerPOM.clickCustomerMenu();
		rewardPointCustomerPOM.clickCustomerSubMenu();
		// rewardPointCustomerPOM.sendCustomerName("subhashini Rangaraju");
		// rewardPointCustomerPOM.clickfilterbtn();
		rewardPointCustomerPOM.customerRowEditBtn();
		Thread.sleep(2000);
		rewardPointCustomerPOM.sendfirstName("asss");
		rewardPointCustomerPOM.sendLastName("rrr");
		rewardPointCustomerPOM.clicktabaddress1();
		// Verify Address1 is fetched from the Address Tab and display
		String address = rewardPointCustomerPOM.AddressDetails();
		System.out.println(address);

		rewardPointCustomerPOM.sendpostcode("560015");
		screenShot.captureScreenShot("RewaedPointCustomerTests_2");
		rewardPointCustomerPOM.clicktabRewardPoints();
		rewardPointCustomerPOM.sendrewardDescription("Points Rewarded-3");
		rewardPointCustomerPOM.sendrewardPoints("120");
		rewardPointCustomerPOM.clickaddRewardPointsBtn();

		// Verify the reward points added is present in the table
		String res = rewardPointCustomerPOM.rewardPointstable.getText();
		System.out.println(res);
		String expected1 = "Points Rewarded-3";
		assertTrue(res.contains(expected1));

		rewardPointCustomerPOM.clickSaveBtn();
		screenShot.captureScreenShot("RewaedPointCustomerTests_3");

		// Verify the success message after clicking on save button
		String message = rewardPointCustomerPOM.successmessage.getText();
		System.out.println(message);
		String expected = "Success: You have modified customers!";
		assertTrue(message.contains(expected));
		screenShot.captureScreenShot("RewaedPointCustomerTests_4");

			
		
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		// driver.quit();
	}

}
