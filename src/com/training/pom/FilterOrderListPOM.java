package com.training.pom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FilterOrderListPOM {
	
	private WebDriver driver; 
	
	public FilterOrderListPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-username")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement loginBtn; 
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	@FindBy(xpath = "//*[@id=\"menu-sale\"]/a")
	public WebElement salemenu; 
	
	@FindBy(linkText = "Orders")
	private WebElement selectOrders;
	
	@FindBy(id ="input-order-id")
	public WebElement orderID;
	
	@FindBy(id ="input-order-status")
	public WebElement orderStatus;
	
	@FindBy(id ="input-date-added")
	public WebElement inputDateAdded;
	
	
	@FindBy(id ="input-customer")
	public WebElement customerName; 
	
	@FindBy(id ="input-total")
	public WebElement total; 
	
	@FindBy(id ="input-date-modified")
	public WebElement inputDateModified;
	

	@FindBy(id ="button-filter")
	private WebElement filterBtn;
	
	@FindBy(xpath = "//*[@id=\"form-order\"]/div/table/tbody")
	public WebElement table;
	
	@FindBy(xpath = "//*[@id=\"form-order\"]/div/table/tbody/tr")
	public WebElement rowtext;
	
	public void clickSaleMenu() {
		this.salemenu.click();
	}
	
	public void clickOrdersItem() {
		this.selectOrders.click();
	}
	
	public void sendOrderID(String orderID) {
		this.orderID.clear();
		this.orderID.sendKeys(orderID);
	}
	
	public void sendorderStatus(String orderStatus) {
		Select ordStatus = new Select(this.orderStatus);
		ordStatus.selectByVisibleText(orderStatus);
		
	}
	
	public void sendDateAdded(String inputDateAdded) {
		this.inputDateAdded.clear();
		this.inputDateAdded.sendKeys(inputDateAdded);
		
	}
	
	public void sendDateModified(String dateModified) {
		this.inputDateModified.clear();
		this.inputDateModified.sendKeys(dateModified);
	}
	
	public void sendTotal(String total) {
		this.total.clear();
		this.total.sendKeys(total);
	}
	
	public void sendCustomerName(String customerName) {
		
		this.customerName.clear(); 
		this.customerName.sendKeys(customerName); 
	}
		
	public void filterBtn() {
		this.filterBtn.click(); 
	}
	
	
	public boolean verifyOrderID(String s)
	{
		String s1;
		s1 = this.orderID.getAttribute("value");
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	public boolean verifyCustomer(String s)
	{
		String s1;
		s1 = this.customerName.getAttribute("value");
		if(s.contains(s1))	
			return true;
			else
			return false;
	}

	public boolean verifyOrderStatus(String s)
	{
		String s1;
		s1 = this.orderStatus.getAttribute("value");
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	public boolean verifyDateAdded(String s) throws ParseException
	{
		String s1;
		s1 = this.inputDateAdded.getAttribute("value");
		SimpleDateFormat dateAddedformat = new SimpleDateFormat("yyyy-mm-dd");
		Date date = dateAddedformat.parse(s1);
		SimpleDateFormat convertdate = new SimpleDateFormat("dd/mm/yyyy");
		s1 = convertdate.format(date);
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	public boolean verifyDateModified(String s) throws ParseException
	{
		String s1;
		s1 = this.inputDateModified.getAttribute("value");
		SimpleDateFormat dateAddedformat = new SimpleDateFormat("yyyy-mm-dd");
		Date date = dateAddedformat.parse(s1);
		SimpleDateFormat convertdate = new SimpleDateFormat("dd/mm/yyyy");
		s1 = convertdate.format(date);
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	public boolean verifyTotal(String s)
	{
		String s1;
		s1 = this.total.getAttribute("value");
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	
}
