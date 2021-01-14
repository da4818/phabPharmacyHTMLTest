import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

public class OrderTest {
    @Test
    public void checkButtons()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        driver.get("https://phabpharmacy.herokuapp.com/order");
        driver.findElement(By.name("editBasket")).click();

        System.out.println(driver.getCurrentUrl());
    }
}
