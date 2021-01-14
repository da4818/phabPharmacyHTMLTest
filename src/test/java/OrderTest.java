import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class OrderTest {
    @Test
    public void checkButtons()throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/order");
        WebElement editBasket = driver.findElement(By.name("editBasket"));
    }
}
