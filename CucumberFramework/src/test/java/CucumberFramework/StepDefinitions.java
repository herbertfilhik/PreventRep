package CucumberFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;
import pageObjects.RegisterPage;

public class StepDefinitions {
    WebDriver driver;
    HomePage home;
    ProductListingPage productListingPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    RegisterPage registerPage;
    
    @Given("^user is on Home Page$")
    public void user_is_on_Home_Page(){
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://rahulshettyacademy.com/angularpractice/shop ");
    }

    @When("^he search for \"([^\"]*)\"$")
    public void he_search_for(String product)  {
        home = new HomePage(driver);
        home.perform_Search(product);
    }

    @When("^choose to buy the first item$")
    public void choose_to_buy_the_first_item() {
        productListingPage = new ProductListingPage(driver);
        productListingPage.select_Product(0);
        productListingPage.clickOn_AddToCart();     
    }

    @When("he access the Register for new user")
    public void he_access_the_Register_for_new_user() {
        home = new HomePage(driver);
        home.perform_Access();
    }
    
    @When("^moves to checkout from mini cart$")
    public void moves_to_checkout_from_mini_cart(){
        cartPage = new CartPage(driver);
        cartPage.clickOn_Cart();
        cartPage.clickOn_ContinueToCheckout();  
    }
    
    @When("^enter personal details on checkout page$")
    public void enter_personal_details_on_checkout_page() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fill_PersonalDetails();    
    }
    
    @When("enter personal details")
    public void enter_personal_details() throws InterruptedException{
        registerPage = new RegisterPage(driver);
        registerPage.fill_PersonalDetails();
        registerPage.clickOn_Submit();
    }
    
    @When("^select same delivery address$")
    public void select_same_delivery_address() throws InterruptedException{
        checkoutPage.check_ShipToDifferentAddress(false);
    }
    
    @When("^select payment method as \"([^\"]*)\" payment$")
    public void select_payment_method_as_payment(String arg1){
        checkoutPage.select_PaymentMethod("CheckPayment");
    }

    @When("validate message and close")
    public void validate_message_and_close() {
        registerPage = new RegisterPage(driver);
        registerPage.msg_validate();
        driver.quit();
    }
    
    @When("^place the order$")
    public void place_the_order() throws InterruptedException {
        checkoutPage.check_TermsAndCondition(true);
        checkoutPage.clickOn_PlaceOrder();
        
        driver.quit();
    }   
}