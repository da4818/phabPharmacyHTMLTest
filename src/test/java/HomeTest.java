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
    @Test
    public void navigatePages(){
        WebDriver driver = new HtmlUnitDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Not needed for this test
        driver.get("https://phabpharmacy.herokuapp.com/home");
        //Back to Homepage
        driver.findElement(By.linkText("Login")).click();
        Assert.assertEquals(driver.getTitle(),"Login");
        //Back to Register page
        driver.findElement(By.linkText("Register")).click();
        Assert.assertEquals(driver.getTitle(),"Register");
        //Back to Map page
        driver.findElement(By.linkText("In-Store")).click();
        Assert.assertEquals(driver.getTitle(),"Find Items In-Store");
        //Back to Browse page - having some issues as Browse isn't a hyperlink like the others but a button that calls a function
        driver.findElement(By.name("Browse")).click();
        //Assert.assertEquals(driver.findElement(By.name("Browse")).getText(),"Browse");
        Assert.assertEquals(driver.getTitle(),"Browse");
        //Back to Basket page
        driver.findElement(By.name("Basket")).click();
        Assert.assertEquals(driver.getTitle(),"Basket");
        // Order, AmendDetails pages aren't accessible through Login - only from Basket
        driver.quit();
    }
}
