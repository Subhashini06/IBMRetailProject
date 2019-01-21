package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RewardPointCustomerPOM {
	
	private WebDriver driver;
	
	public RewardPointCustomerPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-username")
	private WebElement userName;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(xpath = "//*[@type='submit']")
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
	
	@FindBy(xpath = "//*[@id=\"menu-customer\"]/a")
	public WebElement customerMenu; 

	@FindBy(linkText = "Customers")
	private WebElement customerSubMenu;
	
	@FindBy(id = "input-name")
	private WebElement customerName;
	
	@FindBy(id = "button-filter")
	private WebElement filterBtn;
	
	@FindBy(xpath = "//form[@id='form-customer']/div/table/tbody/tr/td[8]/a")
	private WebElement customerEditBtn;

	@FindBy(id = "input-firstname")
	private WebElement firstName;
	
	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(xpath = "//a[contains(@href, '#tab-address1')]")
	private WebElement tabaddress1;
	
	@FindBy(id = "input-address-11")
	private WebElement address1;
	
	@FindBy(id = "input-postcode1")
	private WebElement postcode;
	
	@FindBy(xpath = "//a[contains(@href, '#tab-reward')]")
	private WebElement tabRewardPoints;
	
	@FindBy(id = "input-reward-description")
	private WebElement rewardDescription;
	
	@FindBy(id = "input-points")
	private WebElement rewardPoints;
	
	@FindBy(id = "button-reward")
	private WebElement addRewardPointsBtn;
	
	@FindBy(xpath = "//button/i")
	private WebElement saveButton;
	
	
	
	@FindBy(xpath = "//div[@class='alert alert-success']")
	public WebElement successmessage;
	
	//@FindBy(xpath = "//form[@id='form-customer']/div/table/thead/tr/td[2]")
	//public WebElement customertable;
	
	@FindBy(xpath = "//div[@id='reward']/div/table/tbody/tr/td[2]")
	public WebElement rewardPointstable;
	
	public void clickCustomerMenu() {
		this.customerMenu.click();
	}
	
	public void clickCustomerSubMenu() {
		this.customerSubMenu.click();
	}
	
	public void sendCustomerName(String customerName) {
		this.customerName.clear();
		this.customerName.sendKeys(customerName);
	}
	
	public void clickfilterbtn() {
		this.filterBtn.click();
	}
	
	public void customerRowEditBtn()
	{
		this.customerEditBtn.click();
	}
	
	public void sendfirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}
	
	public void sendLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
	
	public void clicktabaddress1()
	{
		this.tabaddress1.click();
	}
	
	//Getter method for fetching the address from Address Tab	
	public WebElement getAddress1() {
		return address1;
	}

	//Setter method for setting the address from Address Tab
	public void setAddress1(WebElement address1) {
		this.address1 = address1;
	}
	
	//Calling method for Getter and Setter methods
	public String AddressDetails()
	{
		String details = "";
		details += "Address : " + address1.getAttribute("value");
		return details;
	}

	public void sendpostcode(String postcode) {
		this.postcode.clear();
		this.postcode.sendKeys(postcode);
	}
	
	public void clicktabRewardPoints()
	{
		this.tabRewardPoints.click();
	}
	
	public void sendrewardDescription(String rewardDescription) {
		this.rewardDescription.clear();
		this.rewardDescription.sendKeys(rewardDescription);
	}
	
	public void sendrewardPoints(String rewardPoints) {
		this.rewardPoints.clear();
		this.rewardPoints.sendKeys(rewardPoints);
	}
	
	public void clickaddRewardPointsBtn()
	{
		this.addRewardPointsBtn.click();
	}
	
	public void clickSaveBtn() {
		this.saveButton.click();
	}
	
	
	
}
