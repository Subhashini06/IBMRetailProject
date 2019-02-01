package com.training.pom;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ChangeOrderPOM {
	
	private WebDriver driver;
	
	public ChangeOrderPOM(WebDriver driver) {
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
	public WebElement saleMenu; 
	
	@FindBy(linkText = "Orders")
	private WebElement ordersLink;
	
	@FindBy(xpath = "//*[@id=\"form-order\"]/div/table/tbody/tr[1]/td[8]/a[2]")
	private WebElement tableRow;
	
	
	
	@FindBy(xpath = "//*[@id=\"order\"]/li[1]/a")
	private WebElement tabCustomer;

	
	@FindBy(id = "button-customer")
	private WebElement tabCustomerContinueBtn;
	
	@FindBy(xpath = "//td[6]/button")
	private WebElement tabCartRemoveBtn;
	
	
	@FindBy(id = "input-product")
	private WebElement tabCartProductName;
	
	@FindBy(xpath = "//div[@id='tab-product']/fieldset/div/div/ul/li/a")
	private WebElement selectTextProductName;
	
	@FindBy(id = "input-quantity")
	private WebElement tabCartProductQty;
	
	@FindBy(id = "button-product-add")
	private WebElement tabCartAddProductBtn;
	
	@FindBy(id = "button-cart")
	private WebElement tabCartContinueBtn;
	
	@FindBy(id = "button-payment-address")
	private WebElement tabPaymentContinueBtn;
	
	@FindBy(id = "button-shipping-address")
	private WebElement tabShippingContinueBtn;
	
	@FindBy(id = "input-shipping-method")
	private WebElement tabShippingMethodDropdown;
	
	@FindBy(id = "button-save")
	private WebElement tabShippingSaveBtn;
	
	@FindBy(xpath = "//DIV[@class='alert alert-success']")
	public WebElement successMsg;
	
	
	
	
	@FindBy(xpath = "//tbody[@id='cart']/tr/td[1]")
	public WebElement producttable;
	
	
	public void clickSaleMenu() {
		this.saleMenu.click();
	}
	
	public void clickOrdersItem() {
		this.ordersLink.click();
	}
	
	public void tableRowEditBtn()
	{
		this.tableRow.click();
		
	}
	
	
	
	public void clicktabCustomerContinueBtn()
	{
		this.tabCustomerContinueBtn.click();
	}
	
	
	public void clicktabCartRemoveBtn()
	{
		this.tabCartRemoveBtn.click();
	}

	
	public void sendtabCartProductName(String tabCartProductName)
	{
		this.tabCartProductName.clear();
		this.tabCartProductName.sendKeys(tabCartProductName);
		
	}
	
	public void selectProductName()
	{
		this.selectTextProductName.click();
	
	}
	
	public void sendtabCartProductQty(String tabCartProductQty)
	{
		this.tabCartProductQty.clear();
		this.tabCartProductQty.sendKeys(tabCartProductQty);
		
	}
	
	public void clicktabCartAddProductBtn()
	{
		this.tabCartAddProductBtn.click();
	}
	
	public void clicktabCartContinueBtn()
	{
		this.tabCartContinueBtn.click();
	}
	
	public void clicktabPaymentContinueBtn()
	{
		this.tabPaymentContinueBtn.click();
	}
	
	public void clicktabShippingContinueBtn()
	{
		this.tabShippingContinueBtn.click();
	}
	
	public void selecttabShippingMethodDropdown(String tabShippingMethodDropdown)
	{
		Select shippingMethod = new Select(this.tabShippingMethodDropdown); 
		shippingMethod.selectByVisibleText(tabShippingMethodDropdown);
		
	}

	public void clicktabShippingSaveBtn()
	{
		this.tabShippingSaveBtn.click();
	}
	
	public boolean verifySuccessMsg(String s)
	{
		String s1;
		s1 = "Success: You have modified orders!";
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	public boolean verifyProductTable(String s)
	{
		String s1;
		s1 = "Samsung";
		if(s.contains(s1))	
			return true;
			else
			return false;
	}

}
