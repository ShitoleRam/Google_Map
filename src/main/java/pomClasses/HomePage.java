package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilclasses.utilClass;

public class HomePage extends utilClass {
	WebDriver driver;
	
	@FindBy(xpath= "//input[@id='searchboxinput']")
	private WebElement search;
	
	@FindBy(xpath= "//button[@id='searchbox-searchbutton']")
	private WebElement searchButton;
	
	@FindBy(xpath= "//h1[@class='DUwDvf fontHeadlineLarge']")
	private WebElement name;
	
	@FindBy(xpath= "(//span[@aria-hidden='true'])[5]")
	private WebElement rating;
	
	@FindBy(xpath= "//span[@jsan='0.aria-label,0.vet']")
	private WebElement reviews;
	
	@FindBy(xpath= "(//div[@class='Io6YTe fontBodyMedium'])[2]")
	private WebElement link;
	
	@FindBy(xpath= "//div[@class='Io6YTe fontBodyMedium']")
	private WebElement address;
	
	@FindBy(xpath= "(//div[@class='Io6YTe fontBodyMedium'])[3]")
	private WebElement phone;
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void enterText()
	{
		explicitWait(driver,search).sendKeys("Wankhede Stadium");
		
		searchButton.click();
		
	
	}
	public Boolean name()
	{
		String a= explicitWait(driver, name).getText().trim();
		
		if(a.contains("Stadium"))
		{
			return true;
		}else {
			return false;
		}
		
		
	}
	public Boolean getTitle()
	{
		
		if( driver.getTitle().equalsIgnoreCase("Wankhede Stadium - Google Maps"))
		{
			return true;
		}
		else {
			return false;
		}
	}
	public String rating()
	{
		return explicitWait(driver, rating).getText();
		
	}
	public String reviews()
	{
		return reviews.getText();
	}
	public Boolean link()
	{
		if(link.getText().equalsIgnoreCase("mumbaicricket.com"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String address()
	{
		return address.getText();
	}
	public Boolean phoneNumber()
	{
		if(phone.getText().equals("022 2279 5500"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
