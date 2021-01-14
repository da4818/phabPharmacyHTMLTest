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
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/amend_details");
        driver.findElement(By.name("update")).click();
        WebElement resp = driver.findElement(By.name("amendDetailsResponse"));
        Assert.assertEquals(resp.getText(),"Information Updated.");
    }
}
