package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void perform_Search(String search) {
        driver.navigate().to("https://rahulshettyacademy.com/angularpractice/shop?s=" + search + "&post_type=iphone X");
    }
    
    public void perform_Access() {
        link_Register.click();
    }
    
    public void navigateTo_HomePage() {
        driver.get("https://rahulshettyacademy.com");
    }
        
    @FindBy(how = How.XPATH, using = "/html/body/app-root/app-navbar/div/nav/a") 
    private WebElement link_Register;

}