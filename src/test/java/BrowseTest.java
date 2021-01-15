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
        driver.get("https://phabpharmacy.herokuapp.com/browse");
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
    /*@Test
    public void checkAddToBasket()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        //Will need to be logged in prior to this test
        driver.get("https://phabpharmacy.herokuapp.com/login");
        WebElement email = driver.findElement(By.name("email")); // Input field where user enters their email address
        email.sendKeys("js@hotmail.com");
        WebElement password = driver.findElement(By.name("pass"));
        password.sendKeys("qwerty");
        password.submit();
        // Note this test doesn't take into account if the item added already exists in the basket - the quantity is updated, rather than added
        //As a workaround, we initially add the item to the basket, then remove it before adding it again
        driver.get("https://phabpharmacy.herokuapp.com/browse");
        String oldVal = driver.findElement(By.id("basket")).getText();
        int oldBasketSize = Integer.parseInt(oldVal);
        List<WebElement> quantities = driver.findElements(By.name("basketQuantity"));
        List<WebElement> selectButtons = driver.findElements(By.name("addToBasket"));
        String x = "3";
        quantities.get(0).sendKeys(x); // Add x of the first item in browse
        selectButtons.get(0).submit();

        driver.get("https://phabpharmacy.herokuapp.com/basket");
        List<WebElement> updateResponses = driver.findElements(By.name("update"));
        int finalTrashButton = updateResponses.size()-1;
        updateResponses.get(finalTrashButton).click();

        driver.get("https://phabpharmacy.herokuapp.com/browse");
        oldVal = driver.findElement(By.id("basket")).getText();
        oldBasketSize = Integer.parseInt(oldVal);
        x = "3";
        quantities.get(0).sendKeys(x); // Add x of the first item in browse
        selectButtons.get(0).submit();

        int newBasketSize = Integer.parseInt(driver.findElement(By.id("basket")).getText());
        Assert.assertEquals(newBasketSize,oldBasketSize + Integer.parseInt(x));
        driver.quit();
    }*/

    @Test
    public void checkLoggedIn()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        driver.get("https://phabpharmacy.herokuapp.com/browse");
        //We want to ensure that no one can add items to their basket if they are not logged in
        List<WebElement> logOutButtons = driver.findElements(By.name("logOut")); // There are 2 buttons with the same name -
        int i = 0; // One with the purpose to log out, and another to prevent a nullPointException in the POST method, in the event the log out button is not pressed
        for (i=0;i<logOutButtons.size();i++){
            if (logOutButtons.get(i).getAttribute("value").equals("Log Out")){
                logOutButtons.get(i).click();
                break;
            }
        }
        List<WebElement> quantityButtons = driver.findElements(By.name("basketQuantity"));
        quantityButtons.get(0).sendKeys("2");
        List<WebElement> submitButtons = driver.findElements(By.name("addToBasket"));
        submitButtons.get(0).submit();
        Alert alertMessage = driver.switchTo().alert();
        Assert.assertEquals(alertMessage.getText(),"Please ensure that you have created an account and logged in before adding items to your basket.");
        driver.quit();
    }

}
