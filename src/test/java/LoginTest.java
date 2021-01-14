import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;
//import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/login");
        Assert.assertEquals(driver.getTitle(),"Login"); // Each title matches the URl pattern (except the map, see navigatePages function)
        driver.quit();
    }
    @Test
    public void checkLogin() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://phabpharmacy.herokuapp.com/login");
        // Entering the login details for John Smith
        WebElement email = driver.findElement(By.name("email")); //
        email.sendKeys("js@hotmail.com");
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys("qwerty");
        password.submit(); // Submits to the submit button
        WebElement resp = driver.findElement(By.name("loginResponse"));
        Assert.assertEquals(resp.getText(),"Welcome back, John!");
        driver.quit();

    }
    @Test
    public void checkLogin1() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://phabpharmacy.herokuapp.com/login");
        WebElement email1 = driver.findElement(By.name("email"));
        email1.sendKeys("mjc72@hotmail.com");
        WebElement password1 = driver.findElement(By.name("pass"));
        password1.sendKeys("tiger123");
        password1.submit();
        WebElement resp = driver.findElement(By.name("loginResponse"));
        Assert.assertEquals(resp.getText(),"Welcome back, Mia!");
        driver.quit();
        /*email.sendKeys("w-jones12@googlemail.co.uk");
        password.sendKeys("eXample567");
        password.submit();
        Assert.assertEquals(resp.getText(),"Welcome back, Will!");
        resp = driver.findElement(By.name("loginResponse"));
        email.sendKeys("cwy@gmail.com");
        password.sendKeys("password1");
        password.submit();
        resp = driver.findElement(By.name("loginResponse"));
        Assert.assertEquals(resp.getText(),"Welcome back, CW!");
        email.sendKeys("tpke@gmail.com");
        password.sendKeys("wasd&&");
        password.submit();
        resp = driver.findElement(By.name("loginResponse"));
        Assert.assertEquals(resp.getText(),"Welcome back, Tim!");*/
    }
    @Test
    public void navigatePages(){
        WebDriver driver = new HtmlUnitDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Not needed for this test
        driver.get("https://phabpharmacy.herokuapp.com/login");
        //Back to Homepage
        driver.findElement(By.linkText("Home")).click();
        Assert.assertEquals(driver.getTitle(),"Home");
        //Back to Browse page - having some issues as Browse isn't a hyperlink but a button that calls a function
        //driver.findElement(By.name("Browse")).click();
        //Assert.assertEquals(driver.findElement(By.name("Browse")).getText(),"Browse");
        //Assert.assertEquals(driver.getTitle(),"Browse");
        //Back to Register page
        driver.findElement(By.linkText("Register")).click();
        Assert.assertEquals(driver.getTitle(),"Register");
        //Back to Browse page
        driver.findElement(By.linkText("In-Store")).click();
        Assert.assertEquals(driver.getTitle(),"Find Items In-Store");
        //Back to Browse page
        driver.findElement(By.name("Basket")).click();
        Assert.assertEquals(driver.getTitle(),"Basket");

        // Order, AmendDetails pages aren't accessible through Login - only from Basket
        driver.quit();
    }
}
