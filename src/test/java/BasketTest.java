import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class BasketTest {
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/basket");
        Assert.assertEquals(driver.getTitle(),"Basket"); // Each title matches the URl pattern (except the map, see navigatePages function)
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
        driver.get("https://phabpharmacy.herokuapp.com/basket");
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
    public void checkBasket(){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/basket");
        WebElement quantity = driver.findElement(By.name("basketItemQuantity"));
        System.out.println(quantity.getAttribute("value"));
        List<WebElement> updateResponses = driver.findElements(By.name("update"));
        System.out.println(updateResponses.get(0).getAttribute("value")+" and " +updateResponses.get(1).getAttribute("value"));
        updateResponses.get(0).click();
        /*WebElement quantity = driver.findElement(By.name("basketItemQuantity"));
        quantity.sendKeys("2");
        WebElement update = driver.findElement(By.name("update"));
        update.click();
        String output = driver.findElement(By.name("basketItemQuantity")).getText();
        System.out.println(output);*/
        driver.findElement(By.linkText("Proceed to Checkout")).click();
        Assert.assertEquals(driver.getTitle(),"Confirm Order");
    }
}
