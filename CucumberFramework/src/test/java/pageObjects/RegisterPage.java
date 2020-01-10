package pageObjects;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.core.logging.Logger;
import junit.framework.Assert;

public class RegisterPage {
    
    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(how = How.NAME, using = "name") 
    private WebElement txtbx_Name;
    
    @FindBy(how = How.CSS, using = "#billing_last_name") 
    private WebElement txtbx_LastName;
    
    @FindBy(how = How.XPATH, using = "/html/body/app-root/form-comp/div/form/div[7]/input") 
    private WebElement enter_DtBirth;
    
    @FindBy(how = How.NAME, using = "email") 
    private WebElement txtbx_Email;
    
    @FindBy(how = How.XPATH, using = "//*[@id=\"exampleInputPassword1\"]") 
    private WebElement txtbx_Passwd;
    
    @FindBy(how = How.XPATH, using = "/html/body/app-root/form-comp/div/form/div[5]/select")
    private List<WebElement> Gender_List;
    
    @FindBy(how = How.CSS, using = "#billing_phone") 
    private WebElement txtbx_Phone;
    
    @FindBy(how = How.CSS, using = "#billing_country_field .select2-arrow") 
    private WebElement drpdwn_CountryDropDownArrow;
    
    @FindBy(how = How.CSS, using = "#billing_state_field .select2-arrow") 
    private WebElement drpdwn_CountyDropDownArrow;
    
    @FindAll(@FindBy(how = How.CSS, using = "#select2-drop ul li"))
    private List<WebElement> country_List;  
    
    @FindBy(how = How.CSS, using = "#billing_city") 
    private WebElement txtbx_City;
    
    @FindBy(how = How.CSS, using = "#billing_address_1") 
    private WebElement txtbx_Address;
    
    @FindBy(how = How.CSS, using = "#billing_postcode") 
    private WebElement txtbx_PostCode;
    
    @FindBy(how = How.CSS, using = "#ship-to-different-address-checkbox") 
    private WebElement chkbx_ShipToDifferetAddress;
    
    @FindAll(@FindBy(how = How.CSS, using = "ul.wc_payment_methods li"))
    private List<WebElement> paymentMethod_List;    
    
    @FindBy(how = How.CSS, using = "#terms.input-checkbox") 
    private WebElement chkbx_AcceptTermsAndCondition;
    
    @FindBy(how = How.CSS, using = "#place_order") 
    private WebElement btn_PlaceOrder;
    
    @FindBy(how = How.XPATH, using = "/html/body/app-root/form-comp/div/form/input") 
    private WebElement btn_Submit;
    
    @FindBy(how = How.XPATH, using = "/html/body/app-root/form-comp/div/div[2]/div/strong") 
    private WebElement msg_Success;
    
    public void msg_validate() {
        String msg_gabarito = "Success!";
        String msg_atual = msg_Success.getText().toString();
        assertEquals(msg_atual, msg_gabarito);
    }
    
    public void enter_DtBirth(String birth) {
        enter_DtBirth.sendKeys(birth);
    }
    
    public void enter_Name(String name) {
        txtbx_Name.sendKeys(name);
    }
    
    public void enter_LastName(String lastName) {
        txtbx_LastName.sendKeys(lastName);
    }

    public void enter_Email(String email) {
        txtbx_Email.sendKeys(email);
    }
    
    public void enter_Passwd(String passwd) {
        txtbx_Passwd.sendKeys(passwd);
    }
    
    public void enter_Phone(String phone) {
        txtbx_Phone.sendKeys(phone);
    }
    
    public void enter_City(String city) {
        txtbx_City.sendKeys(city);
    }
    
    public void enter_Address(String address) {
        txtbx_Address.sendKeys(address);
    }
    
    public void enter_PostCode(String postCode) {
        txtbx_PostCode.sendKeys(postCode);
    }
    
    public void check_ShipToDifferentAddress(boolean value) {
        if(!value) chkbx_ShipToDifferetAddress.click();
        try { Thread.sleep(3000);}
        catch (InterruptedException e) {}
    }
    
    public void select_Country(String countryName) {
        drpdwn_CountryDropDownArrow.click();
        try { Thread.sleep(2000);}
        catch (InterruptedException e) {}

        for(WebElement country : country_List){
            if(country.getText().equals(countryName)) {
                country.click();    
                try { Thread.sleep(3000);}
                catch (InterruptedException e) {}
                break;
            }
        }
    }
    
    public void select_County(String countyName) {
        drpdwn_CountyDropDownArrow.click();
        try { Thread.sleep(2000);}
        catch (InterruptedException e) {}

        for(WebElement county : country_List){
            if(county.getText().equals(countyName)) {
                county.click(); 
                try { Thread.sleep(3000);}
                catch (InterruptedException e) {}
                break;
            }
        }
    }
    
    public void select_PaymentMethod(String paymentMethod) {
        if(paymentMethod.equals("CheckPayment")) {
            paymentMethod_List.get(0).click();
        }else if(paymentMethod.equals("Cash")) {
            paymentMethod_List.get(1).click();
        }else {
            new Exception("Payment Method not recognised : " + paymentMethod);
        }
        try { Thread.sleep(3000);}
        catch (InterruptedException e) {}
        
    }
    
    public void check_TermsAndCondition(boolean value) {
        if(value) chkbx_AcceptTermsAndCondition.click();
    }
    
    public void clickOn_PlaceOrder() {
        btn_PlaceOrder.submit();
    }
    
    public void clickOn_Submit() {
        btn_Submit.submit();
    }
    
    public void select_Gender(String gender) {
        Gender_List.get(0).click();
        
        if (gender.equals("Male")) {
            Gender_List.get(0).click();
        } else if (gender.equals("Female")) {
            Gender_List.get(1).click();
        } else {
            new Exception("Gender not recognised : " + gender);
        }
    }
    
    public void fill_PersonalDetails() {
        enter_Name("NameTeste20190110");
        enter_Email("Test@Test.com.br");
        enter_Passwd("123");
        select_Gender("Male");
        enter_DtBirth("03/11/2020");
    }

}
