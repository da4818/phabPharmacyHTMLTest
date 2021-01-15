import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class BrowseTest {
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/browse");
        Assert.assertEquals(driver.getTitle(),"Browse"); // Each title matches the URl pattern (except the map, see navigatePages function)
        driver.quit();
    }
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
        List<WebElement> logOutButtons = driver.findElements(By.name("logOut")); // There are 2 buttons with the same name -
        int i = 0; // One with the purpose to log out, and another to prevent a nullPointException in the POST method, in the event the log out button is not pressed
        for (i=0;i<logOutButtons.size();i++){
            if (logOutButtons.get(i).getAttribute("value").equals("Log Out")){
                break;
            }
        }
        logOutButtons.get(i).click();
        WebElement output = driver.findElement(By.className("currentUser"));
        Assert.assertEquals(driver.getTitle(),"Home"); // Should redirect back to the homepage
        Assert.assertEquals(output.getText(),""); // Should output nothing as no one is logged in
        driver.quit();
    }
    @Test
    public void checkLoggedIn()throws Exception{ // Test this immediately after the logOut test
        //We want to ensure that no one can add items to their basket if they are not logged in
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/browse");
        //WebElement quantity = driver.findElement(By.name("basketQuantity"));
        WebElement quantity = driver.findElement(By.name("basketQuantity"));
        quantity.sendKeys("2");
        WebElement submit = driver.findElement(By.name("addToBasket"));
        submit.getAttribute("value");
        submit.submit();
        // Switching to Alert
        Alert alert = driver.switchTo().alert();
        // Capturing alert message.
        String alertMessage= driver.switchTo().alert().getText();
        Assert.assertTrue(alertMessage.contains("Please ensure that you have created an account and logged in before adding items to your basket."));
    }

}
