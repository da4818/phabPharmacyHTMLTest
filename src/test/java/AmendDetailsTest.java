import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AmendDetailsTest {
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
}
