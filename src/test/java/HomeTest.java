import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HomeTest {
    @Test
    public void displayTitle() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/home");
        Assert.assertEquals(driver.getTitle(),"Home"); // Each title matches the URl pattern (except the map, see navigatePages function)
        driver.quit();
    }
    @Test
    public void displayDescription() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/home");
        String target = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertTrue(target.contains("Welcome to the PhabPharmacy's home page!"));
        driver.quit();
    }
    @Test
    public void displayDescription2() throws Exception{
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://phabpharmacy.herokuapp.com/home");
        String target2 = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertTrue(target2.contains("Please login or register to create an account."));
        driver.quit();
    }
}
