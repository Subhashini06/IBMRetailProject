package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.DeleteOrderPOM;
import com.training.pom.DeleteReturnOrderPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class DeleteReturnOrderTests {
	
	private WebDriver driver;
	private String baseUrl;
	private DeleteReturnOrderPOM deleteReturnOrderPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
  
  @BeforeClass
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		deleteReturnOrderPOM = new DeleteReturnOrderPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
	}
  
  //This test is to validate the login
  @Test (priority = 1)
	public void validLoginTest() {
	  deleteReturnOrderPOM.sendUserName("admin");
	  deleteReturnOrderPOM.sendPassword("admin@123");
	  deleteReturnOrderPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("DeleteReturnOrderTests_1");
	}
  
  /* Test case ID: RTTC_019
   * Description: This test is to delete the return order 
  */
  @Test (priority = 2)
  public void validDeleteReturnOrder() throws InterruptedException {
	  	Thread.sleep(2000);
	  	deleteReturnOrderPOM.clickSaleMenu();
	  	deleteReturnOrderPOM.clickReturns();
	  	screenShot.captureScreenShot("DeleteReturnOrderTests_2");
	  	deleteReturnOrderPOM.tablerowcheckbox();
	  	deleteReturnOrderPOM.deleteBtn();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String res = deleteReturnOrderPOM.alertMsg.getText();
		boolean expected = true;
		boolean actual = deleteReturnOrderPOM.verifyAlertMsg(res);
		assertEquals(actual, expected);
  }
  

  @AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
