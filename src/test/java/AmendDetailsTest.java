import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

public class AmendDetailsTest {
    @Test
    public void displayDescription() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        Assert.assertEquals(driver.getTitle(),"Amend Details"); // Each title matches the URl pattern (except the map, see navigatePages function)
        String target = driver.findElement(By.xpath("/html/body/p")).getText();
        Assert.assertTrue(target.contains("Change any details as required (empty fields will not be updated). If everything is already up to date, press 'Cancel' to go back."));
        driver.quit();
    }
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        Assert.assertEquals(driver.getTitle(),"Amend Details"); // Each title matches the URl pattern (except the map, see navigatePages function)
        driver.quit();
    }
    @Test
    public void displayHeadings() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        String target = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertTrue(target.contains("Order Information"));
        String target2 = driver.findElement(By.xpath("//b")).getText();
        Assert.assertTrue(target2.contains("Payment Information"));
        String target3 = driver.findElement(By.xpath("//h3[2]")).getText();
        Assert.assertTrue(target3.contains("Shipping Information"));
        driver.quit();
    }
    @Test
    public void displayLabels() throws Exception {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        String target = driver.findElement(By.xpath("//label")).getText();
        Assert.assertTrue(target.contains("Card Number"));
        String target2 = driver.findElement(By.xpath("//label[2]")).getText();
        Assert.assertTrue(target2.contains("Sort Code"));
        String target3 = driver.findElement(By.xpath("//label[3]")).getText();
        Assert.assertTrue(target3.contains("Account Number"));
        String target4 = driver.findElement(By.xpath("//label[4]")).getText();
        Assert.assertTrue(target4.contains("CVV"));
        String target5 = driver.findElement(By.xpath("//label[5]")).getText();
        Assert.assertTrue(target5.contains("Address"));
        String target6 = driver.findElement(By.xpath("//label[6]")).getText();
        Assert.assertTrue(target6.contains("Postcode"));
        String target7 = driver.findElement(By.xpath("//label[7]")).getText();
        Assert.assertTrue(target7.contains("Phone Number"));
        driver.quit();
    }
    @Test
    public void checkCancelButton()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        driver.findElement(By.linkText("Cancel")).click();
        Assert.assertEquals(driver.getTitle(),"Confirm Order");
    }
    @Test
    public void checkUpdateButton()throws Exception{
        // This page should only be available if a user is logged in - we will program the test to login first
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/login");
        // Entering the login details for John Smith
        WebElement email = driver.findElement(By.name("email")); // Input field where user enters their email address
        email.sendKeys("js@hotmail.com");
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys("qwerty");
        password.submit();

        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        driver.findElement(By.name("update")).click();
        WebElement resp = driver.findElement(By.name("amendDetailsResponse"));
        Assert.assertEquals(resp.getText(),"Information updated.");
    }
    @Test
    public void navigatePages(){
        WebDriver driver = new HtmlUnitDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Not needed for this test
        driver.get("https://phabpharmacy.herokuapp.com/home");
        //Back to Homepage
        driver.findElement(By.linkText("Home")).click();
        Assert.assertEquals(driver.getTitle(),"Home");
        //Back to Login page
        driver.findElement(By.linkText("Login")).click();
        Assert.assertEquals(driver.getTitle(),"Login");
        //Back to Register page
        driver.findElement(By.linkText("Register")).click();
        Assert.assertEquals(driver.getTitle(),"Register");
        //Back to Map page
        driver.findElement(By.linkText("In-Store")).click();
        Assert.assertEquals(driver.getTitle(),"Find Items In-Store");
        //Back to Browse page - having some issues as Browse isn't a hyperlink like the others but a button that calls a function
        driver.findElement(By.name("Browse")).click();
        //Assert.assertEquals(driver.findElement(By.name("Browse")).getText(),"Browse");
        Assert.assertEquals(driver.getTitle(),"Browse");
        //Back to Basket page
        driver.findElement(By.name("Basket")).click();
        Assert.assertEquals(driver.getTitle(),"Basket");
        // Order, AmendDetails pages aren't accessible through Login - only from Basket
        driver.quit();
    }
}