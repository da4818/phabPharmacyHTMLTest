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
