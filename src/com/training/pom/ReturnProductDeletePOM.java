package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReturnProductDeletePOM {
	
	private WebDriver driver;
	
	public ReturnProductDeletePOM(WebDriver driver) {
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
	
	
	
	@FindBy(xpath = "//*[@id=\"menu-sale\"]/a")
	public WebElement salemenu; 

	@FindBy(linkText = "Returns")
	private WebElement selectReturns;
	
	@FindBy(xpath = "//div/div/a/i")
	private WebElement AddButton;
	
	@FindBy(id = "input-order-id")
	public WebElement returnOrderID;
	
	@FindBy(id = "input-date-ordered")
	private WebElement returnDate;
	
	@FindBy(id = "input-customer")
	private WebElement returnCustomer;
	
	@FindBy(id = "input-firstname")
	private WebElement firstName;
	
	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(id = "input-email")
	private WebElement email;
	
	@FindBy(id = "input-telephone")
	private WebElement telephone;
	
	@FindBy(id = "input-product")
	private WebElement returnProduct;
	
	@FindBy(id = "input-model")
	private WebElement model;
	
	@FindBy(xpath = "//button/i")
	private WebElement saveButton;

	@FindBy(xpath = "//tbody/tr[1]/td[1]")
	private WebElement tablerow;
	
	@FindBy(xpath = "//a[contains(text(),'Diamond necklace')]")
	private WebElement selectTextProductName;
	
	@FindBy(xpath = "//form[@id='form-return']/div/table/tbody/tr/td[3]")
	public WebElement returnOrderTable;
	
	
	public void clickAddBtn() {
		this.AddButton.click();
	}
	
	public void sendReturnOrderID(String returnOrderID) {
		this.returnOrderID.clear();
		this.returnOrderID.sendKeys(returnOrderID);
	}
	
	public void sendReturnDate(String returnDate) {
		this.returnDate.clear();
		this.returnDate.sendKeys(returnDate);
	}
	
	public void sendReturnCustomerName(String returnCustomer) {
		this.returnCustomer.clear();
		this.returnCustomer.sendKeys(returnCustomer);
	}
	
	
	public void selectProductName()
	{
		this.selectTextProductName.click();
	
	}
	
	public void sendFirstName(String firstName) {
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}
	
	public void sendLastName(String lastName) {
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
	
	public void sendEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}
	
	public void sendTelephone(String telephone) {
		this.telephone.clear();
		this.telephone.sendKeys(telephone);
	}
	
	public void sendReturnProduct(String returnProduct) {
		this.returnProduct.clear();
		this.returnProduct.sendKeys(returnProduct);
	}
	
	public void sendmodel(String model) {
		this.model.clear();
		this.model.sendKeys(model);
	}
	
	public void clickSaveBtn() {
		this.saveButton.click();
	}
	
	@FindBy(xpath = "//button/i")
	private WebElement deleteBtn;

	@FindBy(xpath = "//DIV[@class='alert alert-success']")
	public WebElement alertMsg;

	public void clickSaleMenu() {
		this.salemenu.click();
	}

	public void clickReturns() 
	{
		this.selectReturns.click();
	}

		
	public void tablerowcheckbox()
	{
		this.tablerow.click();
	}

	public void deleteBtn()
	{
		this.deleteBtn.click();
	}


	public boolean verifyAlertMsg(String s)
	{
		String s1;
		s1 = "Success: You have modified returns!";
		if(s.contains(s1))	
			return true;
			else
			return false;
	}

}
