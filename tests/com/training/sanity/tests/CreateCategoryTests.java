package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;

import com.training.pom.CreateCategoryPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;


import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;

public class CreateCategoryTests {

	private WebDriver driver;
	private String baseUrl;
	private CreateCategoryPOM createCategoryPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	//This test is to validate the login
	@Test(priority = 1)
	public void validLoginTest() {
		createCategoryPOM.sendUserName("admin");
		createCategoryPOM.sendPassword("admin@123");
		createCategoryPOM.clickLoginBtn();
		screenShot.captureScreenShot("CreateCategoryTests_1");
	}

	//RTTC_048 - Allow Admin to create category & add product on the created category
	//This test is to Create category
	@Test(priority = 2)
	public void validCreateCategoryTest() throws InterruptedException {
		createCategoryPOM.clickCategoryMenu();
		createCategoryPOM.clickCategorySubMenu();
		createCategoryPOM.clickAddBtn();
		//Opens Add Category Tab
		String CategoryTab = driver.getWindowHandle();
		ArrayList<String> addCategoryTab = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(2000);
		// System.out.println(addCategoryTab.size());
		driver.switchTo().window(addCategoryTab.get(0));
		//System.out.println(driver.getTitle());
		createCategoryPOM.sendCategoryName("Dresses");
		createCategoryPOM.sendDescription("Fashion wear for kids");
		createCategoryPOM.sendmetaTagTitle("Dresses");
		createCategoryPOM.sendmetaTagDescription("Fashion wear for kids");
		createCategoryPOM.clickSaveBtn();
		
		//Verify the success message after clicking on save button
		String message1 = createCategoryPOM.alertMsg.getText();
		System.out.println(message1);
		String expected2 = "Success: You have modified categories!";
		assertTrue(message1.contains(expected2));
		screenShot.captureScreenShot("CreateCategoryTests_2");
		
		// Verify newly added category is present in the table
		String resCategory = createCategoryPOM.categoryTable.getText();
		System.out.println(resCategory);
		String expected1 = "Dresses";
		assertTrue(resCategory.contains(expected1));
		
		

	}

	//This test is to create product
	@Test(priority = 2)
	public void validCreateProductTest() throws InterruptedException {
		createCategoryPOM.clickCategoryMenu();
		createCategoryPOM.clickProductSubMenu();
		createCategoryPOM.clickAddBtn();
		String ProductTab = driver.getWindowHandle();
		ArrayList<String> addProductTab = new ArrayList<String>(driver.getWindowHandles());
		Thread.sleep(2000);
		//Opens Add Product Tab
		driver.switchTo().window(addProductTab.get(0));
		createCategoryPOM.sendCategoryName("Baby Boy Fashion");
		createCategoryPOM.sendDescription("Fashion wear for boys");
		createCategoryPOM.sendmetaTagTitle("Baby Boy Fashion");
		createCategoryPOM.sendmetaTagDescription("Fashion wear for boys");
		createCategoryPOM.switchlinktab();
		createCategoryPOM.sendAddProductCategoryName("Dresses");
		createCategoryPOM.selectCategoryName();
		createCategoryPOM.switchDatatab();
		createCategoryPOM.sendAddProductModelName("SKU-06");
		createCategoryPOM.clickSaveBtn();
		
		//Verify the success message after clicking on save button
		String message2 = createCategoryPOM.alertMsg.getText();
		System.out.println(message2);
		String expected4 = "Success: You have modified products!";
		assertTrue(message2.contains(expected4));

		// Verify newly added product is present in the table
		String resProduct = createCategoryPOM.productTable.getText();
		System.out.println(resProduct);
		String expected3 = "Baby Boy Fashion";
		assertTrue(resProduct.contains(expected3));
		screenShot.captureScreenShot("CreateCategoryTests_3");

	}

	@BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		createCategoryPOM = new CreateCategoryPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
