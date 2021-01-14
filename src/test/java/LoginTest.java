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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://phabpharmacy.herokuapp.com/login");
        /*WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("HTML");
        element.submit();
        //driver.findElement(By.linkText("Software Testing Material")).click();
        // Get the title of the site and store it in the variable Title*/
        Assert.assertEquals(driver.getTitle(),"Login");
        driver.quit();
    }
    @Test
    public void displayContent() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://phabpharmacy.herokuapp.com/login");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("js@hotmail.com");
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys("qwerty");
        password.submit(); // Submits to the submit button
        System.out.println();
        driver.quit();
        /*
        //driver.findElement(By.linkText("Software Testing Material")).click();
        // Get the title of the site and store it in the variable Title*/

        //Assert.assertEquals(,"js@hotmail.com");
    }
    @Test
    public void navigatePages(){
        WebDriver driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set implicit wait
        driver.get("https://phabpharmacy.herokuapp.com/login");
        //Back to Homepage
        driver.findElement(By.linkText("Home")).click();
        Assert.assertEquals(driver.getTitle(),"Home");
        //Back to Browse page
        driver.findElement(By.linkText("Browse")).click();
        Assert.assertEquals(driver.getTitle(),"Browse");
        //Back to Register page
        driver.findElement(By.linkText("Register")).click();
        Assert.assertEquals(driver.getTitle(),"Register");
        //Back to Browse page
        driver.findElement(By.linkText("In-Store")).click();
        Assert.assertEquals(driver.getTitle(),"Find Items In-Store");
        driver.quit();
    }
}
