package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCategoryPOM {

	private WebDriver driver;

	public CreateCategoryPOM(WebDriver driver) {
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

	@FindBy(xpath = "//*[@id=\"menu-catalog\"]/a")
	private WebElement customerMenu;

	@FindBy(linkText = "Categories")
	private WebElement categoriesLink;
	
	@FindBy(linkText = "Products")
	private WebElement productsLink;

	@FindBy(xpath = "//div/div/a/i")
	private WebElement AddButton;

	@FindBy(id = "input-name1")
	private WebElement inputName;

	@FindBy(xpath = "//div[@id='language1']/div[2]/div/div/div[3]/div[2]")
	private WebElement Description;

	@FindBy(id = "input-meta-title1")
	private WebElement metaTagTitle;

	@FindBy(id = "input-meta-description1")
	private WebElement metaTagDescription;

	 @FindBy(xpath = "//button/i")
	//@FindBy(xpath = "//*[contains(@class, 'fa fa-save')]")
	private WebElement saveButton;

	@FindBy(xpath = "//form/ul/li[3]/a")
	private WebElement tabLink;
	
	@FindBy(id = "input-category")
	public WebElement inputCategories;
	
	@FindBy(xpath = "//a[contains(text(),'Dresses')]")
	private WebElement selectTextCategoryName;
	
	@FindBy(xpath = "//form/ul/li[2]/a")
	private WebElement tabData;
	
	@FindBy(id = "input-model")
	private WebElement inputModel;
	
	@FindBy(xpath = "//DIV[@class='alert alert-success']")
	public WebElement alertMsg;
	
	@FindBy(xpath = "//tbody/tr/td[3]")
	public WebElement productTable;
	
	
	@FindBy(xpath = "//tbody/tr/td[2]")
	public WebElement categoryTable;
	

	public void clickCategoryMenu() {
		this.customerMenu.click();
	}

	public void clickCategorySubMenu() {
		this.categoriesLink.click();
	}
	
	public void clickProductSubMenu() {
		this.productsLink.click();
	}

	public void clickAddBtn() {
		this.AddButton.click();
	}

	public void sendCategoryName(String inputName) {
		this.inputName.clear();
		this.inputName.sendKeys(inputName);
	}

	public void sendDescription(String Description) {
		this.Description.clear();
		this.Description.sendKeys(Description);
	}

	public void sendmetaTagTitle(String metaTagTitle) {
		this.metaTagTitle.clear();
		this.metaTagTitle.sendKeys(metaTagTitle);
	}

	public void sendmetaTagDescription(String metaTagDescription) {
		this.metaTagDescription.clear();
		this.metaTagDescription.sendKeys(metaTagDescription);
	}

	public void clickSaveBtn() {
		this.saveButton.click();
	}
	
	
	public boolean categorySuccessMessage(String s)
	{
		String s1;
		s1 = "Success: You have modified categories!";
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	public void switchlinktab() {
		this.tabLink.click();
	}
	
	
	public void sendAddProductCategoryName(String inputCategories)
	{
		this.inputCategories.clear();
		this.inputCategories.sendKeys(inputCategories);
	}
	
	public void selectCategoryName()
	{
		this.selectTextCategoryName.click();
	
	}
	
	public void switchDatatab() {
		this.tabData.click();
	}
	
	public void sendAddProductModelName(String inputModel)
	{
		this.inputModel.clear();
		this.inputModel.sendKeys(inputModel);
	}
	

	
	
	public boolean productSuccessMessage(String s)
	{
		String s1;
		s1 = "Success: You have modified products!";
		if(s.contains(s1))	
			return true;
			else
			return false;
	}
	
	
	

}
