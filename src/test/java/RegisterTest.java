import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class RegisterTest {
    @Test
    public void logOut() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/login");
        //Will need to be logged in prior to this test
        WebElement email = driver.findElement(By.name("email")); // Input field where user enters their email address
        email.sendKeys("js@hotmail.com");
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys("qwerty");
        password.submit();
        // Begin testing log out
        driver.get("https://phabpharmacy.herokuapp.com/register");
        List<WebElement> logOutButtons = driver.findElements(By.name("logOut")); // There are 2 buttons with the same name -
        int i = 0; // One with the purpose to log out, and another to prevent a nullPointException in the POST method, in the event the log out button is not pressed
        for (i=0;i<logOutButtons.size();i++){
            if (logOutButtons.get(i).getAttribute("value").equals("Log Out")){
                logOutButtons.get(i).click();
                break;
            }
        }
        WebElement output = driver.findElement(By.className("currentUser"));
        Assert.assertEquals(driver.getTitle(),"Home"); // Should redirect back to the homepage
        Assert.assertEquals(output.getText(),""); // Should output nothing as no one is logged in
        driver.quit();
    }
    @Test
    public void invalidRegistration()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/register");
        WebElement password = driver.findElement(By.name("pass"));
        password.submit(); // No entries made, only submit button is pressed
        WebElement resp = driver.findElement(By.name("registerResponse"));
        Assert.assertEquals(resp.getText(),"Incomplete fields, please enter all the information.");
        driver.quit();
    }

    @Test
    public void invalidEmail()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/register");
        WebElement fName = driver.findElement(By.name("fname"));
        WebElement lName = driver.findElement(By.name("lname"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("pass"));
        WebElement verify_password = driver.findElement(By.name("verify_pass"));
        WebElement cardNo = driver.findElement(By.name("card_no"));
        WebElement accountNo = driver.findElement(By.name("account_no"));
        WebElement sortcode = driver.findElement(By.name("sort_code"));
        WebElement cvv = driver.findElement(By.name("cvv"));
        WebElement address = driver.findElement(By.name("address"));
        WebElement postcode = driver.findElement(By.name("postcode"));
        WebElement phoneNo = driver.findElement(By.name("sort_code"));
        fName.sendKeys("John");
        lName.sendKeys("Doe");
        email.sendKeys("invalid email"); // Failing entry - see TestEmailValidation.java on phabPharmacy repo for granular tests
        password.sendKeys("pass1");
        verify_password.sendKeys("pass1");
        cardNo.sendKeys("1234123412341234");
        accountNo.sendKeys("12345678");
        sortcode.sendKeys("102030");
        cvv.sendKeys("123");
        postcode.sendKeys("SW72AZ");
        password.submit();
        WebElement resp = driver.findElement(By.name("registerResponse"));
        Assert.assertEquals(resp.getText(),"Invalid email, please try again.Valid emails must:\n- not contain consecutive special characters\n- not begin with a special character '_', '.' or '-'\n- not contain any of the following characters: ! # $ % & \' * + / = ? ^ ` { | ( )");
        driver.quit();
    }
    @Test
    public void failPasswordCheck()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/register");
        WebElement fName = driver.findElement(By.name("fname"));
        WebElement lName = driver.findElement(By.name("lname"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("pass"));
        WebElement verify_password = driver.findElement(By.name("verify_pass"));
        WebElement cardNo = driver.findElement(By.name("card_no"));
        WebElement accountNo = driver.findElement(By.name("account_no"));
        WebElement sortcode = driver.findElement(By.name("sort_code"));
        WebElement cvv = driver.findElement(By.name("cvv"));
        WebElement address = driver.findElement(By.name("address"));
        WebElement postcode = driver.findElement(By.name("postcode"));
        WebElement phoneNo = driver.findElement(By.name("sort_code"));
        fName.sendKeys("John");
        lName.sendKeys("Doe");
        email.sendKeys("johndoe@gmail.co.uk");
        password.sendKeys("pass1");
        verify_password.sendKeys("pass12"); // Failing entry
        cardNo.sendKeys("1234123412341234");
        accountNo.sendKeys("12345678");
        sortcode.sendKeys("102030");
        cvv.sendKeys("123");
        postcode.sendKeys("SW72AZ");
        password.submit();
        WebElement resp = driver.findElement(By.name("registerResponse"));
        Assert.assertEquals(resp.getText(),"Passwords don't match, please try again.");
        driver.quit();
    }
    @Test
    public void invalidCardNo()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/register");
        WebElement fName = driver.findElement(By.name("fname"));
        WebElement lName = driver.findElement(By.name("lname"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("pass"));
        WebElement verify_password = driver.findElement(By.name("verify_pass"));
        WebElement cardNo = driver.findElement(By.name("card_no"));
        WebElement accountNo = driver.findElement(By.name("account_no"));
        WebElement sortcode = driver.findElement(By.name("sort_code"));
        WebElement cvv = driver.findElement(By.name("cvv"));
        WebElement address = driver.findElement(By.name("address"));
        WebElement postcode = driver.findElement(By.name("postcode"));
        WebElement phoneNo = driver.findElement(By.name("sort_code"));
        fName.sendKeys("John");
        lName.sendKeys("Doe");
        email.sendKeys("johndoe@gmail.co.uk");
        password.sendKeys("pass1");
        verify_password.sendKeys("pass1");
        cardNo.sendKeys("invalid cardNo"); // Failing entry - see TestCreditCard.java on phabPharmacy repo for granular tests
        accountNo.sendKeys("12345678");
        sortcode.sendKeys("102030");
        cvv.sendKeys("123");
        postcode.sendKeys("SW72AZ");
        password.submit();
        WebElement resp = driver.findElement(By.name("registerResponse"));
        Assert.assertEquals(resp.getText(),"Invalid card details, please try again.");
        driver.quit();
    }
    @Test
    public void invalidCvv()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/register");
        WebElement fName = driver.findElement(By.name("fname"));
        WebElement lName = driver.findElement(By.name("lname"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("pass"));
        WebElement verify_password = driver.findElement(By.name("verify_pass"));
        WebElement cardNo = driver.findElement(By.name("card_no"));
        WebElement accountNo = driver.findElement(By.name("account_no"));
        WebElement sortcode = driver.findElement(By.name("sort_code"));
        WebElement cvv = driver.findElement(By.name("cvv"));
        WebElement address = driver.findElement(By.name("address"));
        WebElement postcode = driver.findElement(By.name("postcode"));
        WebElement phoneNo = driver.findElement(By.name("sort_code"));
        fName.sendKeys("John");
        lName.sendKeys("Doe");
        email.sendKeys("johndoe@gmail.co.uk");
        password.sendKeys("pass1");
        verify_password.sendKeys("pass1");
        cardNo.sendKeys("1234123412341234"); // Failing entry - see TestCreditCard.java on phabPharmacy repo for granular tests
        accountNo.sendKeys("12345678");
        sortcode.sendKeys("102030");
        cvv.sendKeys("invalid cvv"); // Failing entry - see TestCreditCard.java on phabPharmacy repo for granular tests
        postcode.sendKeys("SW72AZ");
        password.submit();
        WebElement resp = driver.findElement(By.name("registerResponse"));
        Assert.assertEquals(resp.getText(),"Invalid card details, please try again.");
        driver.quit();
    }
    @Test
    public void invalidAccountNo()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/register");
        WebElement fName = driver.findElement(By.name("fname"));
        WebElement lName = driver.findElement(By.name("lname"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement password = driver.findElement(By.name("pass"));
        WebElement verify_password = driver.findElement(By.name("verify_pass"));
        WebElement cardNo = driver.findElement(By.name("card_no"));
        WebElement accountNo = driver.findElement(By.name("account_no"));
        WebElement sortcode = driver.findElement(By.name("sort_code"));
        WebElement cvv = driver.findElement(By.name("cvv"));
        WebElement address = driver.findElement(By.name("address"));
        WebElement postcode = driver.findElement(By.name("postcode"));
        WebElement phoneNo = driver.findElement(By.name("sort_code"));
        fName.sendKeys("John");
        lName.sendKeys("Doe");
        email.sendKeys("johndoe@gmail.co.uk");
        password.sendKeys("pass1");
        verify_password.sendKeys("pass1");
        cardNo.sendKeys("1234123412341234"); // Failing entry - see TestCreditCard.java on phabPharmacy repo for granular tests
        accountNo.sendKeys("invalid accountNo");
        sortcode.sendKeys("102030");
        cvv.sendKeys("123"); // Failing entry - see TestCreditCard.java on phabPharmacy repo for granular tests
        postcode.sendKeys("SW72AZ");
        password.submit();
        WebElement resp = driver.findElement(By.name("registerResponse"));
        Assert.assertEquals(resp.getText(),"Invalid card details, please try again.");
        driver.quit();
    }
}
